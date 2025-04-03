package section.stringer.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Jama.Matrix;
import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.TransformationMatrix;
import de.iils.dc43.core.geometry.API.GeometryAPI;
import de.iils.dc43.core.geometry.objectgraph.Graph;
import de.iils.dc43.core.geometry.publication.Arc;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.ControlPoint;
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
import opencascade.TopoDS_Shape;
import opencascade.gp_Dir;
import opencascade.gp_Pnt;
import opencascade.gp_Vec;
import section.Panel;
import section.Section;
import section.StringerProfile;

@SuppressWarnings("all")
public class CreateStringer_0 extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);

		Set<Panel> panels = getGraph().allInstances(Panel.class);

		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h / section.getNPanelZ();

		List<Geom_Curve> intersections = new ArrayList<>();

		double alpha;
		int i = 0;

		for (Panel p : panels) {

			alpha = ((zA + p.getZi() * dh) + (zA + (p.getZi() + 1) * dh)) / 2;

			System.out.println("za: " + zA + " zi: " + p.getZi() + " dh: " + dh + " alpha: " + alpha);

			intersections.add(BRepApi.getIntersectionCurveInPlaneAtPoint(section.getSurface(), new gp_Pnt(0, 0, 0),
					new gp_Dir(0, Math.cos(alpha / 180 * Math.PI), Math.sin(alpha / 180 * Math.PI))));

//			BRepApi.visualizeShape(p.getSurface(),"Surface: " + i);
			BRepApi.visualizeShape(intersections.get(i), "Intersection: " + i);
			i++;
		}

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

		Line linie = Line.create(Point.create(0.0, 0.0, 0.0),
				Point.create(richtung.X() * 10000, richtung.Y() * 10000, richtung.Z() * 10000));

		Component linieC = Component.create().setId("Linie");
		G linieG = G.create();
		linieC.setShape(linieG);
		linieG.getGeometric().add(linie);
		linieG.getGeometric().add(Point.create(x, y, z));

		System.out.println("atan: " + Math.atan(richtung.Y() / richtung.Z()) / Math.PI * 180.0);
		System.out.println("atan2: " + Math.atan2(richtung.Y(), richtung.Z()) / Math.PI * 180.0);

		if (richtung.Y() < 0) {
			pos.setPhi(-(Math.atan(richtung.Y() / richtung.Z()) / Math.PI * 180.0));
		} else {
			pos.setPhi(-(Math.atan(richtung.Y() / richtung.Z()) / Math.PI * 180.0));
		}

		List<Spline> splines = new ArrayList<>();
		for (Geom_Curve inter : intersections) {
			splines.add(BRepApi.createSplineAGFromGeom_Curve(inter, 10));
		}

		Wire wire = getGraph().firstInstance(StringerProfile.class).getProfile();

		Component wiresComp = Component.create().setId("Wires");
		G wiresG = G.create();
		wiresComp.setShape(wiresG);

		List<Loft> lofts = new ArrayList<>();
		for (Spline spline : splines) {
			lofts.add(createLongStringer_(spline, section.getSurface(), wire, wiresG));
//			transformProfile(section.getSurface(), spline);
		}

		Component loftsComp = Component.create().setId("Lofts");
		loftsComp.setShape(lofts.get(0));

//		createLongStringer(intersectionCurveAsSpline, section.getSurface());

//		pos.setPhi(-(90-Math.atan(richtung.Z()/richtung.Y())/Math.PI*180.0));
//		pos.setPhi(90.0);

//		Pipe pipe = Pipe.create();
//		
//		pipe.setSpine(intersectionCurveAsSpline);
//		
//		pipe.setProfile(profile);
//		
//		
//		
//		Transform t = Transform.create();
//		t.setLive(pipe);
//		
//		t.setDx(intersectionCurveAsSpline.getElement().get(0).getX());
//		t.setDy(intersectionCurveAsSpline.getElement().get(0).getY());
//		t.setDz(intersectionCurveAsSpline.getElement().get(0).getZ());
//		
//		Component stringerComp = Component.create().setId("Stringer");
//		stringerComp.setShape(t);
	}

	void transformProfile(TopoDS_Face surface, Spline spline) throws Exception {

		GeometryAPI api = new GeometryAPI(getRunningDC43Project());

//		Graph<TopoDS_Shape> graph = api.generateGeometry();

		List<ControlPoint> controlPoints = spline.getElement();
		List<Transform> transformList = new ArrayList<>();
		gp_Vec richtung = new gp_Vec();
		gp_Pnt punkt = new gp_Pnt();

		Component profileComp = Component.create().setId("Transformed");
		G g = G.create();

		Wire profile = getGraph().firstInstance(StringerProfile.class).getProfile();
		g.getGeometric().add(profile);

		int i = 0;
		for (ControlPoint cP : controlPoints) {
			Transform transform = Transform.create();
//			positionComp.getSub().add(transformList.get(i));
//			transformList.get(i).getSub().add(profileComp);

			transform.setDx(cP.getX());
			transform.setDy(cP.getY());
			transform.setDz(cP.getZ());

			punkt = new gp_Pnt(cP.getX().getValue(), cP.getY().getValue(), cP.getZ().getValue());

			richtung = BRepApi.getNormalToSurfaceAtPointAlternative(surface, punkt);

			if (richtung.Y() > 0) {
				transform.setPhi(180.0 - Math.atan2(richtung.Y(), richtung.Z()) / Math.PI * 180.0);
			} else {
				transform.setPhi(-Math.atan2(richtung.Y(), richtung.Z()) / Math.PI * 180.0);
			}

			profileComp.setShape(transform);
			transform.setLive(g);

			api.generateEmptyGeometryGraph();
			Graph<TopoDS_Shape> graph = api.generateGeometry();
			TransformationMatrix transformationMatrix = api.getLoc2GlobTrans(graph, g);

//			copyProfile(wire, matrix);

//			Point p1 = new Point (x,y,z)
//					p_matrix = new p_matrix (x,y,z,1)

//					Point p1 = new Point (p_matrix_transforemd.x,p_matrix_transforemd.y,p_matrix_transforemd.z)

//			TopoDS_Shape representation = graph.getNode(transform).getRepresentation();
//			TopExp_Explorer explorer = new TopExp_Explorer(representation, TopAbs_ShapeEnum.TopAbs_EDGE);
//			while(explorer.More()) {
//				explorer.Next();
//			}
			i++;

//			TransformationMatrix transformationMatrix_ = TransformationMatrix.createTransformationMatrix(cP.getX(), y, z, phi, theta, psi)		
		}

	}

	////////////////////////////////////////////////////////////////////

	Loft createLongStringer_(Spline spline, TopoDS_Face surface, Wire wire, G wiresG) throws Exception {

		List<ControlPoint> controlPoints = spline.getElement();
		List<Position> position = new ArrayList<>();
		gp_Vec richtung = new gp_Vec();
		gp_Pnt punkt = new gp_Pnt();
		List<Wire> wires = new ArrayList<>();

		for (ControlPoint cP : controlPoints) {

//			punkt = new gp_Pnt(cP.getX().getValue(), cP.getY().getValue(), cP.getZ().getValue());
			punkt.SetX(cP.getX().getValue());
			punkt.SetY(cP.getY().getValue());
			punkt.SetZ(cP.getZ().getValue());

			richtung = BRepApi.getNormalToSurfaceAtPointAlternative(surface, punkt);
			double phi;
			if (richtung.Y() > 0) {
				phi = (180.0 - Math.atan2(richtung.Y(), richtung.Z()));
			} else {
				phi = (-Math.atan2(richtung.Y(), richtung.Z()));
			}

//			Math.PI * 180.0

			TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(punkt.X(), punkt.Y(),
					punkt.Z(), phi, 0., 0.);

			wires.add(copyWire(wire, matrix));
		}

		for (Wire w : wires) {
			wiresG.getGeometric().add(w);

		}

		Loft loft = Loft.create();

		for (int i = 0; i < wires.size() - 1; i++) {
			wires.get(i).getNext().add(wires.get(i + 1));
		}
		for (Wire w : wires) {
			loft.getElement().add(w);
		}

		return loft;
	}

	void createLongStringer(Spline spline, TopoDS_Face surface) throws Exception {

		Component profileComp = Component.create();
		G g = G.create();
		profileComp.setShape(g);
		Wire profile = getGraph().firstInstance(StringerProfile.class).getProfile();
		g.getGeometric().add(profile);

		Position pos = Position.create();

		Component positionComp = Component.create();

		positionComp.getSub().add(pos);

		pos.getSub().add(profileComp);

		List<ControlPoint> controlPoints = spline.getElement();
		List<Position> position = new ArrayList<>();
		gp_Vec richtung = new gp_Vec();
		gp_Pnt punkt = new gp_Pnt();

		int i = 0;
		for (ControlPoint cP : controlPoints) {
			position.add(Position.create());
			positionComp.getSub().add(position.get(i));
			position.get(i).getSub().add(profileComp);
			position.get(i).setDx(i * 50.0);

			position.get(i).setDx(cP.getX());
			position.get(i).setDy(cP.getY());
			position.get(i).setDz(cP.getZ());

			punkt = new gp_Pnt(cP.getX().getValue(), cP.getY().getValue(), cP.getZ().getValue());

			richtung = BRepApi.getNormalToSurfaceAtPointAlternative(surface, punkt);

			if (richtung.Y() > 0) {
				position.get(i).setPhi(180.0 - Math.atan2(richtung.Y(), richtung.Z()) / Math.PI * 180.0);
			} else {
				position.get(i).setPhi(-Math.atan2(richtung.Y(), richtung.Z()) / Math.PI * 180.0);
			}

			i++;
		}

		Component component = (Component) position.get(0).getSub().get(0);

		G reG = (G) component.getShape();

		Profile reProfile = (Profile) reG.getGeometric().get(0);

		Loft loft = Loft.create().setStart(reProfile);
		loft.getElement().add(reProfile);

		for (int j = 0; j < position.size(); j++) {

			Component componentP = (Component) position.get(j).getSub().get(0);

			G reGP = (G) componentP.getShape();

			Profile reProfileP = (Profile) reGP.getGeometric().get(0);

			loft.getElement().add(reProfileP);

			reProfile.getNext().add(reProfileP);

			reProfile = reProfileP;

		}

		Component comp2 = Component.create().setId("loft");

		comp2.setShape(loft);

		return;
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

			System.out.println("While");

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
				System.out.println("Break");
				break;

			}

			counter++;

		}

//		for (int i = 0; i < wire.getElement().size(); i++) {
//
//			
//			
//			
//			oldWireElement = (Curve) oldWireElement.getNext().get(0);
//
//			if (oldWireElement instanceof Arc) {
//				oldWireElement = (Arc) oldWireElement;
//				newWireElement = copy((Arc) oldWireElement, matrix);
//			} else if (oldWireElement instanceof Line) {
//				oldWireElement = (Line) newWireStart;
//				newWireElement = copy((Line) oldWireElement, matrix);
//			}
//
//			
//			if (i==0) {
//			 Curve oldWireStart = wire.getElement().get(i);
//				newWire.setStart();
//			}
//			
//			newPrevCurve.getNext().add(newWireElement);
//
//			newPrevCurve = newWireElement;
//
//			newWire.getElement().add(newWireElement);
//		}

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