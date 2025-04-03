package section.stringer.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Jama.Matrix;
import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.TransformationMatrix;
import de.iils.dc43.core.geometry.publication.Arc;
import de.iils.dc43.core.geometry.publication.Common;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.ControlPoint;
import de.iils.dc43.core.geometry.publication.Cuboid;
import de.iils.dc43.core.geometry.publication.Curve;
import de.iils.dc43.core.geometry.publication.Line;
import de.iils.dc43.core.geometry.publication.Loft;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Profile;
import de.iils.dc43.core.geometry.publication.Spline;
import de.iils.dc43.core.geometry.publication.Transform;
import de.iils.dc43.core.geometry.publication.Wire;
import opencascade.Geom_Curve;
import opencascade.TopoDS_Face;
import opencascade.gp_Dir;
import opencascade.gp_Pnt;
import opencascade.gp_Vec;
import section.Panel;
import section.Section;
import section.Stringer;
import section.StringerProfile;

@SuppressWarnings("all")
public class CreateStringer extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);
		Set<Panel> panels = getGraph().allInstances(Panel.class);

		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h / section.getNPanelZ(); // "Height" of a panel or skin in degree

		// Creating stringers as long as section
		// Stringers are distributed at regular intervals defined by section height and
		// number of panel rows

		List<Geom_Curve> intersections = new ArrayList<>();

		double alpha;

		for (int i = 0; i < section.getNStringer() * section.getNPanelZ(); i++) {
			alpha = zA + (0.5 + i) * (dh / section.getNStringer());
			intersections.add(BRepApi.getIntersectionCurveInPlaneAtPoint(section.getSurface(), new gp_Pnt(0, 0, 0),
					new gp_Dir(0, Math.cos(alpha / 180 * Math.PI), Math.sin(alpha / 180 * Math.PI))));
		}

		List<Spline> splines = new ArrayList<>();
		for (Geom_Curve inter : intersections) {
			splines.add(BRepApi.createSplineAGFromGeom_Curve(inter, 20));
		}

		Wire stringerProfile = getGraph().firstInstance(StringerProfile.class).getProfile();

		List<Loft> lofts_stringer = new ArrayList<>();

		for (Spline spline : splines) {
			lofts_stringer.add(createLongStringer(spline, section.getSurface(), stringerProfile));
		}

		// Separate the long stringers to smaller pieces
		double edge = section.getButtStrapWidth().getValue();
		List<Transform> transformed_cubes = new ArrayList<>();

		for (int i = 0; i < section.getNPanelX(); i++) {
			Cuboid cube = Cuboid.create();
			cube.setLengthX(section.getL().getValue() / section.getNPanelX() - edge);
			cube.setLengthY(10000.);
			cube.setLengthZ(10000.);
			Transform transform = Transform.create();
			transform.setLive(cube);
			transform.setDx(i * section.getL().getValue() / section.getNPanelX() + edge / 2);
			transform.setDz(-5000.);
			transformed_cubes.add(transform);
		}

		Component comp_stringers = Component.create().setId("Stringer");

		int counter = 0;
		for (Panel p : panels) {
			counter = 0;
			for (int i = p.getZi() * section.getNStringer(); i < (p.getZi() + 1) * section.getNStringer(); i++) {

				Common comp_stringer = Common.create();
				comp_stringer.getDie().add(lofts_stringer.get(i));
				comp_stringer.getDie().add(transformed_cubes.get(p.getXi()));

				Stringer stringer = Stringer.create();
				p.getStringer().add(stringer);
				stringer.setShape(comp_stringer);
				stringer.setI(counter);
				stringer.setId("Stringer " + counter);

				comp_stringers.getSub().add(stringer);
				counter++;
			}
		}
		comp_stringers.setId("Stringer (" + comp_stringers.getSub().size() + ")");
	}

	/////////////////////////// FUNCTIONS ///////////////////////////

	// Creates a stringer as long as the whole section
	Loft createLongStringer(Spline spline, TopoDS_Face surface, Wire wire) throws Exception {

		List<ControlPoint> controlPoints = spline.getElement();
		gp_Vec surfaceRichtung = new gp_Vec();
		gp_Vec punktRichtung = new gp_Vec();
		gp_Pnt punkt = new gp_Pnt();
		List<Wire> wires = new ArrayList<>();

		for (ControlPoint cP : controlPoints) {

			punkt.SetX(cP.getX().getValue());
			punkt.SetY(cP.getY().getValue());
			punkt.SetZ(cP.getZ().getValue());

			surfaceRichtung = BRepApi.getNormalToSurfaceAtPointAlternative(surface, new gp_Pnt(0., 50., 0.));
			punktRichtung = BRepApi.getNormalToSurfaceAtPointAlternative(surface, punkt);

			double phi;
			if (surfaceRichtung.Y() > 0) {
				phi = (Math.PI - Math.atan2(punktRichtung.Y(), punktRichtung.Z()));
			} else {
				phi = (Math.PI + Math.atan2(punktRichtung.Y(), punktRichtung.Z()));
			}

			TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(punkt.X(), punkt.Y(),
					punkt.Z(), phi, 0., 0.);

			wires.add(copyWire(wire, matrix));
		}

		Loft loft = Loft.create().setStart(wires.get(0));

		for (int i = 0; i < wires.size() - 1; i++) {
			wires.get(i).getNext().add(wires.get(i + 1));
		}
		for (Wire w : wires) {
			loft.getElement().add(w);
		}

		return loft;
	}

	// Reconstructs a new wire with new elements at a new position according to the
	// given matrix
	private Wire copyWire(Wire wire, TransformationMatrix matrix) {

		Wire newWire = Wire.create();

		Curve oldWireStart = wire.getStart();

		Curve newWireStart = null;

		if (oldWireStart instanceof Arc) {
			newWireStart = (Arc) newWireStart;
			newWireStart = copy((Arc) oldWireStart, matrix);

		} else if (oldWireStart instanceof Line) {
			newWireStart = (Line) newWireStart;
			newWireStart = copy((Line) oldWireStart, matrix);
		}

		newWire.setStart(newWireStart);
		newWire.getElement().add(newWireStart);

		Curve old = null;
		Curve oldWireElement = oldWireStart;

		Curve newWireElement = null;

		Curve newPrevCurve = newWireStart;

		Profile profile = oldWireStart;

		int counter = 0;

		while (profile.getNext().size() != 0 && counter < 20) {

			profile = profile.getNext().get(0);
			oldWireElement = (Curve) profile;

			if (oldWireElement instanceof Arc) {
				oldWireElement = (Arc) oldWireElement;
				newWireElement = copy((Arc) oldWireElement, matrix);
			} else if (oldWireElement instanceof Line) {
				oldWireElement = (Line) oldWireElement;
				newWireElement = copy((Line) oldWireElement, matrix);
			}

			newPrevCurve.getNext().add(newWireElement);

			newPrevCurve = newWireElement;

			newWire.getElement().add(newWireElement);

			if (oldWireStart == oldWireElement) {
				break;
			}
			counter++;
		}

		newWireElement.getNext().add(newWireStart);

		return newWire;
	}

	// Reconstructs a new arc at a new position according to the given matrix
	private Arc copy(Arc arc, TransformationMatrix transformationMatrix) {

		Point newStartPoint = copyPoint(arc.getStartPoint(), transformationMatrix);
		Point newCentrePoint = copyPoint(arc.getCentre(), transformationMatrix);
		Point newEndPoint = copyPoint(arc.getEndPoint(), transformationMatrix);

		Arc arcTransformed = Arc.create();
		arcTransformed.setStartPoint(newStartPoint);
		arcTransformed.setEndPoint(newEndPoint);
		arcTransformed.setCentre(newCentrePoint);
		arcTransformed.setNormal(arc.getNormal());
		return arcTransformed;
	}

	// Reconstructs a new line at a new position according to the given matrix
	private Line copy(Line line, TransformationMatrix transformationMatrix) {

		Point newStartPoint = copyPoint(line.getStartPoint(), transformationMatrix);
		Point newEndPoint = copyPoint(line.getEndPoint(), transformationMatrix);

		Line lineTransformed = Line.create(newStartPoint, newEndPoint);
		return lineTransformed;
	}

	// Creates a new point at a new position according to the given matrix
	private Point copyPoint(Point point, TransformationMatrix transformationMatrix) {
		Double xOrignal = point.getX().getValue();
		Double yOrignal = point.getY().getValue();
		Double zOrignal = point.getZ().getValue();

		TransformationMatrix pointOriginalMatrix = TransformationMatrix.createTransformationMatrix(xOrignal, yOrignal,
				zOrignal, 0., 0., 0.);

		Matrix pointTransformedMatrix = transformationMatrix.times(pointOriginalMatrix);

		double[] pointTransformedCoordinates = TransformationMatrix.createTransformationMatrix(pointTransformedMatrix)
				.getPosition();
		double x = pointTransformedCoordinates[0];
		double y = pointTransformedCoordinates[1];
		double z = pointTransformedCoordinates[2];

		Point pointTransformed = Point.create(x, y, z);
		return pointTransformed;
	}
}