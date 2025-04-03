package section.stringerCoupling.rules;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;
import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.TransformationMatrix;
import de.iils.dc43.core.geometry.publication.Arc;
import de.iils.dc43.core.geometry.publication.Common;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.ControlPoint;
import de.iils.dc43.core.geometry.publication.Curve;
import de.iils.dc43.core.geometry.publication.Cylinder;
import de.iils.dc43.core.geometry.publication.Direction;
import de.iils.dc43.core.geometry.publication.Line;
import de.iils.dc43.core.geometry.publication.Loft;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Profile;
import de.iils.dc43.core.geometry.publication.Spline;
import de.iils.dc43.core.geometry.publication.Transform;
import de.iils.dc43.core.geometry.publication.Wire;
import opencascade.Geom_Curve;
import opencascade.gp_Dir;
import opencascade.gp_Pnt;
import opencascade.gp_Vec;
import section.Panel;
import section.Section;
import section.StringerCoupling;

@SuppressWarnings("all")
public class CreateStringerCouplings extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);

		if (section.getNPanelX() < 2) {
			return;
		}

		List<Loft> lofts = new ArrayList<>();

		double angle;
		double zA = section.getZA().getValue();
		double h_section = section.getH().getValue();
		double dh = h_section / section.getNPanelZ() / section.getNStringer();

		for (int i = 0; i < section.getNStringer() * section.getNPanelZ(); i++) {
			angle = zA + dh / 2 + i * dh;
			lofts.add(createLongCoupling(angle, section));
		}

		double lenght_stringerCoupling = section.getStringerCouplingLenght().getValue();
		double h = section.getH().getValue();
		double dl = section.getL().getValue() / section.getNPanelX();

		List<Cylinder> cylinder = new ArrayList<>();
		List<Transform> transformedCylinder = new ArrayList<>();

		for (int i = 0; i < section.getNPanelX() - 1; i++) {
			cylinder.add(Cylinder.create());
			cylinder.get(i).setRadius(2500.); // evtl. generisch anpassen
			cylinder.get(i).setHeight(lenght_stringerCoupling);
			cylinder.get(i).setPhi(h);
			cylinder.get(i).setRx(1.0);
			cylinder.get(i).setRy(0.0);
			cylinder.get(i).setRz(0.0);

			transformedCylinder.add(Transform.create());
			transformedCylinder.get(i).setLive(cylinder.get(i));

			transformedCylinder.get(i).setPhi(-180.0 + zA);
			transformedCylinder.get(i).setDx(dl * (i + 1) - lenght_stringerCoupling / 2);
		}

		Component comp_stringerCouplings = Component.create().setId("Stringer couplings");

		for (int i = 0; i < lofts.size(); i++) {
			for (int j = 0; j < transformedCylinder.size(); j++) {
				Common com = Common.create();
				com.getDie().add(lofts.get(i));
				com.getDie().add(transformedCylinder.get(j));

				StringerCoupling stringerCoupling = StringerCoupling.create().setId("Coupling (" + i + "," + j + ")");
				stringerCoupling.setShape(com);
				comp_stringerCouplings.getSub().add(stringerCoupling);
				stringerCoupling.setZi(i);
				stringerCoupling.setXi(j);

				Panel panel_1 = searchPanel(section, j, i / section.getNStringer());
				Panel panel_2 = searchPanel(section, j + 1, i / section.getNStringer());

				stringerCoupling.getStringer().add(panel_1.getStringer().get(i % section.getNStringer()));
				stringerCoupling.getStringer().add(panel_2.getStringer().get(i % section.getNStringer()));
			}
		}

		comp_stringerCouplings.setId("Stringer Couplings (" + comp_stringerCouplings.getSub().size() + ")");
	}

	//////////////// FUNCTIONS ///////////////////////

	// Finds panel with index numbers
	public Panel searchPanel(Section section, int xi, int zi) {
		List<Panel> panels = section.getPanel();
		for (Panel p : panels) {
			if (p.getXi() == xi && p.getZi() == zi) {
				return p;
			}
		}
		return null;
	}

	// Creates long loft for stringer coupling
	private Loft createLongCoupling(double angle, Section section) throws Exception {

		double h = 2.;
		double t = 2.;
		double l1 = 30.;
		double l2 = 50.;
		double r = 2.;

		List<Point> p = new ArrayList<>();

		p.add(Point.create(0.0, 0.0, h));
		p.add(Point.create(0.0, 0.0, h + t));
		p.add(Point.create(0.0, -l1 + r, h + t));
		p.add(Point.create(0.0, -l1, h + t + r));
		p.add(Point.create(0.0, -l1, h + t + l2));
		p.add(Point.create(0.0, -l1 - t, h + t + l2));
		p.add(Point.create(0.0, -l1 - t, h + t + r));
		p.add(Point.create(0.0, -l1 + r, h));

		Wire profile = Wire.create();

		Line line0 = Line.create();
		line0.setStartPoint(p.get(0));
		line0.setEndPoint(p.get(1));
		profile.getElement().add(line0);
		profile.setStart(line0);

		Line line1 = Line.create();
		line1.setStartPoint(p.get(1));
		line1.setEndPoint(p.get(2));
		profile.getElement().add(line1);
		line0.getNext().add(line1);

		Point centre = Point.create(0.0, -l1 + r, h + t + r);
		Arc arc2 = Arc.create();
		arc2.setCentre(centre);
		arc2.setStartPoint(p.get(2));
		arc2.setEndPoint(p.get(3));
		Direction dir = Direction.create(-1.0, 0.0, 0.0);
		arc2.setNormal(dir);
		profile.getElement().add(arc2);
		line1.getNext().add(arc2);

		Line line3 = Line.create();
		line3.setStartPoint(p.get(3));
		line3.setEndPoint(p.get(4));
		profile.getElement().add(line3);
		arc2.getNext().add(line3);

		Line line4 = Line.create();
		line4.setStartPoint(p.get(4));
		line4.setEndPoint(p.get(5));
		profile.getElement().add(line4);
		line3.getNext().add(line4);

		Line line5 = Line.create();
		line5.setStartPoint(p.get(5));
		line5.setEndPoint(p.get(6));
		profile.getElement().add(line5);
		line4.getNext().add(line5);

		Arc arc6 = Arc.create();
		arc6.setCentre(centre);
		arc6.setStartPoint(p.get(6));
		arc6.setEndPoint(p.get(7));
		Direction dir6 = Direction.create(1.0, 0.0, 0.0);
		arc6.setNormal(dir6);
		profile.getElement().add(arc6);
		line5.getNext().add(arc6);

		Line line7 = Line.create();
		line7.setStartPoint(p.get(7));
		line7.setEndPoint(p.get(0));
		profile.getElement().add(line7);
		arc6.getNext().add(line7);

		line7.getNext().add(line0);

		Geom_Curve curve = BRepApi.getIntersectionCurveInPlaneAtPoint(section.getSurface(), new gp_Pnt(0, 0, 0),
				new gp_Dir(0, Math.cos(angle / 180 * Math.PI), Math.sin(angle / 180 * Math.PI)));

		Spline spline = BRepApi.createSplineAGFromGeom_Curve(curve, 20);

		List<ControlPoint> controlPoints = spline.getElement();

		gp_Pnt point = new gp_Pnt();
		gp_Vec normalVec = new gp_Vec();
		List<Wire> wires = new ArrayList<>();

		for (ControlPoint cP : controlPoints) {
			point.SetX(cP.getX().getValue());
			point.SetY(cP.getY().getValue());
			point.SetZ(cP.getZ().getValue());

			normalVec = BRepApi.getNormalToSurfaceAtPointAlternative(section.getSurface(), point);

			double phi = (Math.PI - Math.atan2(normalVec.Y(), normalVec.Z()));

			TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(point.X(), point.Y(),
					point.Z(), phi, 0., 0.);

			wires.add(copyWire(profile, matrix));
		}

		Loft loft = Loft.create().setStart(wires.get(0));

		for (int j = 0; j < wires.size() - 1; j++) {
			wires.get(j).getNext().add(wires.get(j + 1));
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

	// Reconstructs a new arc at a new position according to the
	// given matrix
	private Arc copy(Arc arc, TransformationMatrix transformationMatrix) {

		Point newStartPoint = copyPoint(arc.getStartPoint(), transformationMatrix);
		Point newCentrePoint = copyPoint(arc.getCentre(), transformationMatrix);
		Point newEndPoint = copyPoint(arc.getEndPoint(), transformationMatrix);

		Arc arcTransformed = Arc.create();
		arcTransformed.setStartPoint(newStartPoint);
		arcTransformed.setEndPoint(newEndPoint);
		arcTransformed.setCentre(newCentrePoint);

		Point normalPoint = Point.create();
		normalPoint.setX(arc.getNormal().getDx().getValue());
		normalPoint.setY(arc.getNormal().getDy().getValue());
		normalPoint.setZ(arc.getNormal().getDz().getValue());

		TransformationMatrix localMatrix = TransformationMatrix.createTransformationMatrix(transformationMatrix);

		double[] position = { 0, 0, 0 };
		localMatrix.setPosition(position);

		normalPoint = copyPoint(normalPoint, localMatrix);

		arcTransformed.setNormal(Direction.create(normalPoint.getX().getValue(), normalPoint.getY().getValue(),
				normalPoint.getZ().getValue()));

		return arcTransformed;
	}

	// Reconstructs a new line at a new position according to the
	// given matrix
	private Line copy(Line line, TransformationMatrix transformationMatrix) {

		Point newStartPoint = copyPoint(line.getStartPoint(), transformationMatrix);
		Point newEndPoint = copyPoint(line.getEndPoint(), transformationMatrix);

		Line lineTransformed = Line.create(newStartPoint, newEndPoint);
		return lineTransformed;
	}

	// Reconstructs a new point at a new position according to the
	// given matrix
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