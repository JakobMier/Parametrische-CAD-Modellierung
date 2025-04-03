package section.stringer.rules;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.TransformationMatrix;
import de.iils.dc43.core.geometry.publication.Arc;
import de.iils.dc43.core.geometry.publication.Curve;
import de.iils.dc43.core.geometry.publication.Direction;
import de.iils.dc43.core.geometry.publication.Line;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Profile;
import de.iils.dc43.core.geometry.publication.Wire;
import section.StringerProfile;

@SuppressWarnings("all")
public class CreateStringerProfile extends JavaRule {

	@Override
	public void execute() throws Exception {

		// Thickness of profile
		double t = 2.0;

		// Dimensions of profile
		double l1 = 50.0;
		double l2 = 20.0;
		double l3 = 10.0;
		double l4 = 30.0;

		// Radius of profile
		double r1 = 1.0;
		double r2 = 1.0;
		double r3 = 1.0;

		List<Point> points = new ArrayList<>();

		// P0
		points.add(Point.create(0.0, 0.0, 0.0));
		// P1
		points.add(Point.create(0.0, l4, 0.0));
		// P3
		points.add(Point.create(0.0, l4, t));
		// P3
		points.add(Point.create(0.0, 0.0, t));
		// P4
		points.add(Point.create(0.0, -r1, t + r1));
		// P5
		points.add(Point.create(0.0, -r1, t + r1 + l1));
		// P6
		points.add(Point.create(0.0, -(r1 + t + r2), t + r1 + l1 + r2 + t));
		// P7
		points.add(Point.create(0.0, -(r1 + t + r2 + l2), t + r1 + l1 + r2 + t));
		// P8
		points.add(Point.create(0.0, -(r1 + t + r2 + l2 + r3 + t), t + r1 + l1 + r2 - r3));
		// P9
		points.add(Point.create(0.0, -(r1 + t + r2 + l2 + r3 + t), t + r1 + l1 + r2 - r3 - l3));
		// P10
		points.add(Point.create(0.0, -(r1 + t + r2 + l2 + r3), t + r1 + l1 + r2 - r3 - l3));
		// P11
		points.add(Point.create(0.0, -(r1 + t + r2 + l2 + r3), t + r1 + l1 + r2 - r3));
		// P12
		points.add(Point.create(0.0, -(r1 + t + r2 + l2), t + r1 + l1 + r2));
		// P13
		points.add(Point.create(0.0, -(r1 + t + r2), t + r1 + l1 + r2));
		// P14
		points.add(Point.create(0.0, -(r1 + t), t + r1 + l1));
		// P15
		points.add(Point.create(0.0, -(r1 + t), r1 + t));

		Wire wire_profile = Wire.create();

		// L0
		Line line0 = Line.create();
		line0.setStartPoint(points.get(0));
		line0.setEndPoint(points.get(1));
		wire_profile.getElement().add(line0);
		wire_profile.setStart(line0);
		// L1
		Line line1 = Line.create();
		line1.setStartPoint(points.get(1));
		line1.setEndPoint(points.get(2));
		wire_profile.getElement().add(line1);
		wire_profile.setStart(line1);
		line0.getNext().add(line1);
		// L2
		Line line2 = Line.create();
		line2.setStartPoint(points.get(2));
		line2.setEndPoint(points.get(3));
		wire_profile.getElement().add(line2);
		wire_profile.setStart(line2);
		line1.getNext().add(line2);
		// L3
		Point centre1 = Point.create(0.0, 0.0, t + r1);
		Arc arc3 = Arc.create();
		arc3.setCentre(centre1);
		arc3.setStartPoint(points.get(3));
		arc3.setEndPoint(points.get(4));
		Direction dir = Direction.create(-1.0, 0.0, 0.0);
		arc3.setNormal(dir);
		wire_profile.getElement().add(arc3);
		line2.getNext().add(arc3);
		// L4
		Line line4 = Line.create();
		line4.setStartPoint(points.get(4));
		line4.setEndPoint(points.get(5));
		wire_profile.getElement().add(line4);
		arc3.getNext().add(line4);
		// L5
		Point centre2 = Point.create(0.0, -(r1 + t + r2), t + r1 + l1);
		Arc arc5 = Arc.create();
		arc5.setCentre(centre2);
		arc5.setStartPoint(points.get(5));
		arc5.setEndPoint(points.get(6));
		Direction dir3 = Direction.create(1.0, 0.0, 0.0);
		arc5.setNormal(dir3);
		wire_profile.getElement().add(arc5);
		line4.getNext().add(arc5);
		// L6
		Line line6 = Line.create();
		line6.setStartPoint(points.get(6));
		line6.setEndPoint(points.get(7));
		wire_profile.getElement().add(line6);
		arc5.getNext().add(line6);
		// L7
		Point centre3 = Point.create(0.0, -(r1 + t + r2 + l2), t + r1 + l1);
		Arc arc7 = Arc.create();
		arc7.setCentre(centre3);
		arc7.setStartPoint(points.get(7));
		arc7.setEndPoint(points.get(8));
		Direction dir5 = Direction.create(1.0, 0.0, 0.0);
		arc7.setNormal(dir5);
		wire_profile.getElement().add(arc7);
		line6.getNext().add(arc7);
		// L8
		Line line8 = Line.create();
		line8.setStartPoint(points.get(8));
		line8.setEndPoint(points.get(9));
		wire_profile.getElement().add(line8);
		arc7.getNext().add(line8);
		// L9
		Line line9 = Line.create();
		line9.setStartPoint(points.get(9));
		line9.setEndPoint(points.get(10));
		wire_profile.getElement().add(line9);
		line8.getNext().add(line9);
		// L10
		Line line10 = Line.create();
		line10.setStartPoint(points.get(10));
		line10.setEndPoint(points.get(11));
		wire_profile.getElement().add(line10);
		line9.getNext().add(line10);
		// L11
		Arc arc11 = Arc.create();
		arc11.setCentre(centre3);
		arc11.setStartPoint(points.get(11));
		arc11.setEndPoint(points.get(12));
		Direction dir4 = Direction.create(-1.0, 0.0, 0.0);
		arc11.setNormal(dir4);
		wire_profile.getElement().add(arc11);
		line10.getNext().add(arc11);
		// L12
		Line line12 = Line.create();
		line12.setStartPoint(points.get(12));
		line12.setEndPoint(points.get(13));
		wire_profile.getElement().add(line12);
		arc11.getNext().add(line12);
		wire_profile.getElement().add(line12);
		arc11.getNext().add(line12);
		// L13
		Arc arc13 = Arc.create();
		arc13.setCentre(centre2);
		arc13.setStartPoint(points.get(13));
		arc13.setEndPoint(points.get(14));
		Direction dir10 = Direction.create(-1.0, 0.0, 0.0);
		arc13.setNormal(dir10);
		wire_profile.getElement().add(arc13);
		line12.getNext().add(arc13);
		// L14
		Line line14 = Line.create();
		line14.setStartPoint(points.get(14));
		line14.setEndPoint(points.get(15));
		wire_profile.getElement().add(line14);
		arc13.getNext().add(line14);
		wire_profile.getElement().add(line14);
		arc13.getNext().add(line14);
		// L15
		Arc arc15 = Arc.create();
		arc15.setCentre(centre1);
		arc15.setStartPoint(points.get(15));
		arc15.setEndPoint(points.get(0));
		Direction dir13 = Direction.create(1.0, 0.0, 0.0);
		arc15.setNormal(dir13);
		wire_profile.getElement().add(arc15);
		line14.getNext().add(arc15);

		arc15.getNext().add(line0);

		TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(0., -(l4 + 1.), 0., 0., 0., 0.);
		wire_profile = copyWire(wire_profile, matrix);

		StringerProfile stringerProfile = StringerProfile.create();

		stringerProfile.setProfile(wire_profile);
		stringerProfile.setHeight(2 * t + l1 + r1 + r2);

	}

	////////////////////// FUNCTIONS //////////////////////////////////////

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