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
import de.iils.dc43.core.geometry.publication.G;
import de.iils.dc43.core.geometry.publication.Line;
import de.iils.dc43.core.geometry.publication.Loft;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Position;
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
import section.StringerProfile;

@SuppressWarnings("all")
public class CreateStringer2 extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);

		Set<Panel> panels = getGraph().allInstances(Panel.class);

		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h / section.getNPanelZ() / section.getNStringer();

		List<Geom_Curve> intersections = new ArrayList<>();

		double alpha;
		for (int i = 0; i < section.getNStringer() * section.getNPanelZ(); i++) {

			alpha = zA + dh / 2 + i * dh;

			System.out.println("zA: " + zA + " dh: " + dh + " alpha: " + alpha);

			intersections.add(BRepApi.getIntersectionCurveInPlaneAtPoint(section.getSurface(), new gp_Pnt(0, 0, 0),
					new gp_Dir(0, Math.cos(alpha / 180 * Math.PI), Math.sin(alpha / 180 * Math.PI))));

//			BRepApi.visualizeShape(intersections.get(i), "Intersection: " + i);
		}

//		for (Panel p : panels) {
//
//			alpha = ((zA + p.getZi() * dh) + (zA + (p.getZi() + 1) * dh)) / 2;
//
//			System.out.println("za: " + zA + " zi: " + p.getZi() + " dh: " + dh + " alpha: " + alpha);
//
//			intersections.add(BRepApi.getIntersectionCurveInPlaneAtPoint(section.getSurface(), new gp_Pnt(0, 0, 0),
//					new gp_Dir(0, Math.cos(alpha / 180 * Math.PI), Math.sin(alpha / 180 * Math.PI))));
//
////			BRepApi.visualizeShape(p.getSurface(),"Surface: " + i);
//			BRepApi.visualizeShape(intersections.get(i), "Intersection: " + i);
//			i++;
//		}

		Component profileComp = Component.create().setId("profil");
		G g = G.create();

		profileComp.setShape(g);

		Wire profile = getGraph().firstInstance(StringerProfile.class).getProfile();

		g.getGeometric().add(profile);

		Component positionComp = Component.create();

		Position pos = Position.create();

		positionComp.getSub().add(pos);

		pos.getSub().add(profileComp);

//		pos.setDy(500.0);

		Spline intersectionCurveAsSpline = BRepApi.createSplineAGFromGeom_Curve(intersections.get(0), 10);

		double x = intersectionCurveAsSpline.getElement().get(0).getX().getValue();
		double y = intersectionCurveAsSpline.getElement().get(0).getY().getValue();
		double z = intersectionCurveAsSpline.getElement().get(0).getZ().getValue();

		pos.setDx(x);
		pos.setDy(y);
		pos.setDz(z);

		gp_Pnt punkt = new gp_Pnt(x, y, z);

		gp_Vec richtung = BRepApi.getNormalToSurfaceAtPointAlternative(section.getSurface(), punkt);

		System.out.println("X: " + richtung.X() + " Y: " + richtung.Y() + "Z: " + richtung.Z());

//		Line linie = Line.create(Point.create(0.0, 0.0, 0.0),
//				Point.create(richtung.X() * 10000, richtung.Y() * 10000, richtung.Z() * 10000));
//
//		Component linieC = Component.create().setId("Linie");
//		G linieG = G.create();
//		linieC.setShape(linieG);
//		linieG.getGeometric().add(linie);
//		linieG.getGeometric().add(Point.create(x, y, z));

		System.out.println("atan: " + Math.atan(richtung.Y() / richtung.Z()) / Math.PI * 180.0);
		System.out.println("atan2: " + Math.atan2(richtung.Y(), richtung.Z()) / Math.PI * 180.0);

		if (richtung.Y() < 0) {
			pos.setPhi(-(Math.atan(richtung.Y() / richtung.Z()) / Math.PI * 180.0));
		} else {
			pos.setPhi(-(Math.atan(richtung.Y() / richtung.Z()) / Math.PI * 180.0));
		}

		List<Spline> splines = new ArrayList<>();
		for (Geom_Curve inter : intersections) {
			splines.add(BRepApi.createSplineAGFromGeom_Curve(inter, 20));
		}

//		for (Spline spline : splines) {
//			
//		}

		Wire wire = getGraph().firstInstance(StringerProfile.class).getProfile();

		Component wiresComp = Component.create().setId("Wires");
		G wiresG = G.create();
		wiresComp.setShape(wiresG);

		List<Loft> lofts = new ArrayList<>();
		for (Spline spline : splines) {
			lofts.add(createLongStringer_(spline, section.getSurface(), wire, wiresG));
		}

//		Component loftsComp = Component.create().setId("Lofts");
//		int j = 0;
//		for (Loft loft : lofts) {
//			Component subLoftsComp = Component.create().setId("Loft: " + j);
//			loftsComp.getSub().add(subLoftsComp);
//			subLoftsComp.setShape(loft);
//		}

		double edge = section.getButtStrapWidth().getValue();

		List<Transform> transforms = new ArrayList<>();
		List<Cuboid> cubes = new ArrayList<>();
		for (int i = 0; i < section.getNPanelX(); i++) {
			Cuboid cube = Cuboid.create();
			cube.setLengthX(section.getL().getValue() / section.getNPanelX() - edge);
			cube.setLengthY(10000.);
			cube.setLengthZ(10000.);
			Transform trans = Transform.create();
			trans.setLive(cube);
			trans.setDx(i * section.getL().getValue() / section.getNPanelX() + edge / 2);
			trans.setDz(-5000.);
			transforms.add(trans);
		}

//		Component transComp = Component.create().setId("Stringer Bereiche");
//		int i = 0;
//		for (Transform transform : transforms) {
//			Component subTransComp = Component.create().setId("Stringer Bereich: " + i);
//			transComp.getSub().add(subTransComp);
//			subTransComp.setShape(transform);
//			i++;
//		}

		Component shortStringerComp = Component.create().setId("Short Stringer");
		for (Panel p : panels) {
//			for (int i = p.getZi() * section.getNStringer(); i < p.getZi()+1 * section.getNStringer()
//					+ section.getNPanelZ(); i++) {
			for (int i = p.getZi() * section.getNStringer(); i < (p.getZi() + 1) * section.getNStringer(); i++) {
				Common com = Common.create();
				com.getDie().add(lofts.get(i));
				com.getDie().add(transforms.get(p.getXi()));
				Component subShortStringerComp = Component.create()
						.setId("Short Stringer (" + p.getXi() + ", " + p.getZi() + ")");
				shortStringerComp.getSub().add(subShortStringerComp);
				subShortStringerComp.setShape(com);
			}
		}
//		Component loftsComp = Component.create().setId("Lofts");
//		loftsComp.setShape(createLongStringer_(splines.get(0), section.getSurface(), wire, wiresG));

//		Component loftsComp = Component.create().setId("Lofts");
//		loftsComp.setShape(lofts.get(0));

//		Cuboid cube = Cuboid.create();
//		cube.setLengthX(500.);
//		cube.setLengthY(3000.);
//		cube.setLengthZ(500.);
//
//		Component cubeComp = Component.create().setId("Cube");
//		cubeComp.setShape(cube);
//
//		Common com = Common.create();
//		com.getDie().add(cube);
//		com.getDie().add(lofts.get(5));

//		Cut cut = Cut.create();
//		cut.setDie(cube);
//		cut.setLive(lofts.get(5));

//		Component comComp = Component.create().setId("Common");
//		comComp.setShape(com);
//
//		GeometryAPI api = new GeometryAPI(getRunningDC43Project());
//		Graph<TopoDS_Shape> graph = api.generateGeometry();
//
//		TopoDS_Shape comTopoDS = graph.getNode(com).getRepresentation();
//		List<TopoDS_Shape> comShapes = BRepApi.findElements(comTopoDS, TopoDS_Shape.class);
//
//		for (TopoDS_Shape shape : comShapes) {
//			BRepApi.visualizeShape(shape, "Shape");
//		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	Loft createLongStringer_(Spline spline, TopoDS_Face surface, Wire wire, G wiresG) throws Exception {

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
//				phi = (180.0 - Math.atan2(richtung.Y(), richtung.Z()));
//				System.out.println("Y > 0");
				phi = (Math.PI - Math.atan2(punktRichtung.Y(), punktRichtung.Z()));
			} else {
//				System.out.println("Y < 0");
				phi = (Math.PI + Math.atan2(punktRichtung.Y(), punktRichtung.Z()));
			}

//			Math.PI * 180.0

			TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(punkt.X(), punkt.Y(),
					punkt.Z(), phi, 0., 0.);

			wires.add(copyWire(wire, matrix));
		}

//		for (Wire w : wires) {
//			wiresG.getGeometric().add(w);
//
//		}
		Loft loft = Loft.create().setStart(wires.get(0));

		for (int i = 0; i < wires.size() - 1; i++) {
			wires.get(i).getNext().add(wires.get(i + 1));
		}
		for (Wire w : wires) {
			loft.getElement().add(w);
		}

		return loft;
	}

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

	private Line copy(Line line, TransformationMatrix transformationMatrix) {

		Point newStartPoint = copyPoint(line.getStartPoint(), transformationMatrix);
		Point newEndPoint = copyPoint(line.getEndPoint(), transformationMatrix);

		Line lineTransformed = Line.create(newStartPoint, newEndPoint);
		return lineTransformed;
	}

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