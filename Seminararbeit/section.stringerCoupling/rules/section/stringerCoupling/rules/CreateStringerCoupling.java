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
import section.Section;

@SuppressWarnings("all")
public class CreateStringerCoupling extends JavaRule {

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

		Component loftsComp = Component.create().setId("Coupling Lofts");
		int j = 0;
		for (Loft loft : lofts) {
			Component subLoftsComp = Component.create().setId("Loft:" + j);
			loftsComp.getSub().add(subLoftsComp);
			subLoftsComp.setShape(loft);
			j++;
		}

//		Component subLoftsComp = Component.create().setId("Loft");
//		Loft loft = createLongCoupling(170., section);
//		subLoftsComp.setShape(loft);

		double width = section.getStringerCouplingLenght().getValue();
		double h = section.getH().getValue();
		double dl = section.getL().getValue() / section.getNPanelX();

		List<Cylinder> cyl = new ArrayList<>();
		List<Transform> transform = new ArrayList<>();

		Component c = Component.create().setId("Cutout Coupling");

		for (int i = 0; i < section.getNPanelX() - 1; i++) {
			cyl.add(Cylinder.create());
			cyl.get(i).setRadius(2500.); // evtl. generisch anpassen
			cyl.get(i).setHeight(width);
			cyl.get(i).setPhi(h);
			cyl.get(i).setRx(1.0);
			cyl.get(i).setRy(0.0);
			cyl.get(i).setRz(0.0);

			transform.add(Transform.create());
			transform.get(i).setLive(cyl.get(i));

			transform.get(i).setPhi(-180.0 + zA);
			transform.get(i).setDx(dl * (i + 1) - width / 2);

			Component cCut = Component.create().setId("Cutout: " + i);
			c.getSub().add(cCut);
			cCut.setShape(transform.get(i));
		}

//		GeometryAPI api = new GeometryAPI(getRunningDC43Project());
//		Graph<TopoDS_Shape> graph = api.generateGeometry();

		Component couplingComp = Component.create().setId("Couplings");
		for (Transform t : transform) {
			for (Loft loft : lofts) {
				Common com = Common.create();
				com.getDie().add(loft);
				com.getDie().add(t);
				Component subCouplingComp = Component.create().setId("Coupling");
				couplingComp.getSub().add(subCouplingComp);
				subCouplingComp.setShape(com);
			}
		}
	}

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

//	Component c = Component.create().setId("Coupling Profile");
//	G g = G.create();
//	c.setShape(g);
//	g.getGeometric().add(profile);

//		for (Point point : p) {
//			g.getGeometric().add(point);
//		}

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