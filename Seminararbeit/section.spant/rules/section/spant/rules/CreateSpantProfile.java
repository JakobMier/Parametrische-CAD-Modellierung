package section.spant.rules;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.TransformationMatrix;
import de.iils.dc43.core.geometry.publication.Arc;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.Curve;
import de.iils.dc43.core.geometry.publication.Direction;
import de.iils.dc43.core.geometry.publication.G;
import de.iils.dc43.core.geometry.publication.Line;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Profile;
import de.iils.dc43.core.geometry.publication.Wire;
import section.SpantProfile;
import section.StringerProfile;

@SuppressWarnings("all")
public class CreateSpantProfile extends JavaRule {

	@Override
	public void execute() throws Exception {
		double t = 2.0;
		double l1 = 60.0;
		double l2 = 20.0;
		double l3 = 10.0;
		double l4 = 20.0;
		double r1 = 1.0;
		double r2 = 1.0;
		double r3 = 1.0;

		List<Point> p = new ArrayList<>();

		// P0
		p.add(Point.create(0.0, 0.0, 0.0));

		// P1
		p.add(Point.create(0.0, l4, 0.0));

		// P3
		p.add(Point.create(0.0, l4, t));

		// P3
		p.add(Point.create(0.0, 0.0, t));

		// P4
		p.add(Point.create(0.0, -r1, t + r1));

		// P5
		p.add(Point.create(0.0, -r1, t + r1 + l1));

		// P6
		p.add(Point.create(0.0, -(r1 + t + r2), t + r1 + l1 + r2 + t));

		// P7
		p.add(Point.create(0.0, -(r1 + t + r2 + l2), t + r1 + l1 + r2 + t));

		// P8
		p.add(Point.create(0.0, -(r1 + t + r2 + l2 + r3 + t), t + r1 + l1 + r2 - r3));

		// P9
		p.add(Point.create(0.0, -(r1 + t + r2 + l2 + r3 + t), t + r1 + l1 + r2 - r3 - l3));

		// P10
		p.add(Point.create(0.0, -(r1 + t + r2 + l2 + r3), t + r1 + l1 + r2 - r3 - l3));

		// P11
		p.add(Point.create(0.0, -(r1 + t + r2 + l2 + r3), t + r1 + l1 + r2 - r3));

		// P12
		p.add(Point.create(0.0, -(r1 + t + r2 + l2), t + r1 + l1 + r2));

		// P13
		p.add(Point.create(0.0, -(r1 + t + r2), t + r1 + l1 + r2));

		// P14
		p.add(Point.create(0.0, -(r1 + t), t + r1 + l1));

		// P15
		p.add(Point.create(0.0, -(r1 + t), r1 + t));

		Component c = Component.create().setId("Spant Profil");
		G g = G.create();
		c.setShape(g);

//		List<Line> curves = new ArrayList<>();

		Wire profile = Wire.create();

		// L0
		Line line0 = Line.create();
		line0.setStartPoint(p.get(0));
		line0.setEndPoint(p.get(1));
		profile.getElement().add(line0);
		profile.setStart(line0);

//		g.getGeometric().add(line0);

		// L1
		Line line1 = Line.create();
		line1.setStartPoint(p.get(1));
		line1.setEndPoint(p.get(2));
		profile.getElement().add(line1);
		profile.setStart(line1);
		line0.getNext().add(line1);

//		g.getGeometric().add(line1);

		// L2
		Line line2 = Line.create();
		line2.setStartPoint(p.get(2));
		line2.setEndPoint(p.get(3));
		profile.getElement().add(line2);
		profile.setStart(line2);
		line1.getNext().add(line2);

//		g.getGeometric().add(line2);

		// L3
		Point centre1 = Point.create(0.0, 0.0, t + r1);
		Arc arc3 = Arc.create();
		arc3.setCentre(centre1);
		arc3.setStartPoint(p.get(3));
		arc3.setEndPoint(p.get(4));
		Direction dir = Direction.create(-1.0, 0.0, 0.0);
		arc3.setNormal(dir);
		profile.getElement().add(arc3);
		line2.getNext().add(arc3);

//		g.getGeometric().add(arc3);

		// L4
		Line line4 = Line.create();
		line4.setStartPoint(p.get(4));
		line4.setEndPoint(p.get(5));
		profile.getElement().add(line4);
		arc3.getNext().add(line4);

//		g.getGeometric().add(line4);

		// L5
		Point centre2 = Point.create(0.0, -(r1 + t + r2), t + r1 + l1);
		Arc arc5 = Arc.create();
		arc5.setCentre(centre2);
		arc5.setStartPoint(p.get(5));
		arc5.setEndPoint(p.get(6));
		Direction dir3 = Direction.create(1.0, 0.0, 0.0);
		arc5.setNormal(dir3);
		profile.getElement().add(arc5);
		line4.getNext().add(arc5);

//		g.getGeometric().add(arc5);

		// L6
		Line line6 = Line.create();
		line6.setStartPoint(p.get(6));
		line6.setEndPoint(p.get(7));
		profile.getElement().add(line6);
		arc5.getNext().add(line6);

//		g.getGeometric().add(line6);

		// L7
		Point centre3 = Point.create(0.0, -(r1 + t + r2 + l2), t + r1 + l1);
		Arc arc7 = Arc.create();
		arc7.setCentre(centre3);
		arc7.setStartPoint(p.get(7));
		arc7.setEndPoint(p.get(8));
		Direction dir5 = Direction.create(1.0, 0.0, 0.0);
		arc7.setNormal(dir5);
		profile.getElement().add(arc7);
		line6.getNext().add(arc7);

//		g.getGeometric().add(arc7);

		// L8
		Line line8 = Line.create();
		line8.setStartPoint(p.get(8));
		line8.setEndPoint(p.get(9));
		profile.getElement().add(line8);
		arc7.getNext().add(line8);

//		g.getGeometric().add(line8);

		// L9
		Line line9 = Line.create();
		line9.setStartPoint(p.get(9));
		line9.setEndPoint(p.get(10));
		profile.getElement().add(line9);
		line8.getNext().add(line9);

//		g.getGeometric().add(line9);

		// L10
		Line line10 = Line.create();
		line10.setStartPoint(p.get(10));
		line10.setEndPoint(p.get(11));
		profile.getElement().add(line10);
		line9.getNext().add(line10);

//		g.getGeometric().add(line10);

		// L11
		Arc arc11 = Arc.create();
		arc11.setCentre(centre3);
		arc11.setStartPoint(p.get(11));
		arc11.setEndPoint(p.get(12));
		Direction dir4 = Direction.create(-1.0, 0.0, 0.0);
		arc11.setNormal(dir4);
		profile.getElement().add(arc11);
		line10.getNext().add(arc11);

//		g.getGeometric().add(arc11);

		// L12
		Line line12 = Line.create();
		line12.setStartPoint(p.get(12));
		line12.setEndPoint(p.get(13));
		profile.getElement().add(line12);
		arc11.getNext().add(line12);
		profile.getElement().add(line12);
		arc11.getNext().add(line12);

//		g.getGeometric().add(line12);

		// L13
		Arc arc13 = Arc.create();
		arc13.setCentre(centre2);
		arc13.setStartPoint(p.get(13));
		arc13.setEndPoint(p.get(14));
		Direction dir10 = Direction.create(-1.0, 0.0, 0.0);
		arc13.setNormal(dir10);
		profile.getElement().add(arc13);
		line12.getNext().add(arc13);

//		g.getGeometric().add(arc13);

		// L14
		Line line14 = Line.create();
		line14.setStartPoint(p.get(14));
		line14.setEndPoint(p.get(15));
		profile.getElement().add(line14);
		arc13.getNext().add(line14);
		profile.getElement().add(line14);
		arc13.getNext().add(line14);

//		g.getGeometric().add(line14);

		// L15
		Arc arc15 = Arc.create();
		arc15.setCentre(centre1);
		arc15.setStartPoint(p.get(15));
		arc15.setEndPoint(p.get(0));
		Direction dir13 = Direction.create(1.0, 0.0, 0.0);
		arc15.setNormal(dir13);
		profile.getElement().add(arc15);
		line14.getNext().add(arc15);

		arc15.getNext().add(line0);

//		g.getGeometric().add(arc15);

		double stringerHeight = getGraph().firstInstance(StringerProfile.class).getHeight().getValue();

		TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(0., 0., stringerHeight, 0., 0.,
				Math.PI / 2);
		profile = copyWire(profile, matrix);

		g.getGeometric().add(profile);

		SpantProfile pro = SpantProfile.create();

		pro.setProfile(profile);
		pro.setHeightForClip(t + l1 + r1);

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

		visualizeCurve(newWireStart);

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

//			visualizeCurve(newWireElement);

			counter++;

		}

		newWireElement.getNext().add(newWireStart);

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

	private void visualizeCurve(Curve curve) {
		Component comp = Component.create();
		G g = G.create();
		comp.setShape(g);
		g.getGeometric().add(curve);
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
		System.out.println(normalPoint.getX().getValue() + "; " + normalPoint.getY().getValue() + "; "
				+ normalPoint.getZ().getValue());

		TransformationMatrix localMatrix = TransformationMatrix.createTransformationMatrix(transformationMatrix);

		System.out.println("0");
		double[] position = { 0, 0, 0 };
		System.out.println("1");
		localMatrix.setPosition(position);

		normalPoint = copyPoint(normalPoint, localMatrix);
		System.out.println(normalPoint.getX().getValue() + "; " + normalPoint.getY().getValue() + "; "
				+ normalPoint.getZ().getValue());

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
