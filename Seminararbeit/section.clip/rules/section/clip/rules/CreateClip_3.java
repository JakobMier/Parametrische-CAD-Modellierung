package section.clip.rules;

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
import de.iils.dc43.core.geometry.publication.Block;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.Curve;
import de.iils.dc43.core.geometry.publication.Cylinder;
import de.iils.dc43.core.geometry.publication.Direction;
import de.iils.dc43.core.geometry.publication.G;
import de.iils.dc43.core.geometry.publication.Line;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Profile;
import de.iils.dc43.core.geometry.publication.Transform;
import de.iils.dc43.core.geometry.publication.Wire;
import opencascade.BRep_Tool;
import opencascade.TopoDS_Face;
import opencascade.TopoDS_Shape;
import opencascade.TopoDS_Vertex;
import opencascade.gp_Pnt;
import opencascade.gp_Vec;
import section.Clip;
import section.Panel;
import section.Section;
import section.SpantProfile;
import section.StringerProfile;

@SuppressWarnings("all")
public class CreateClip_3 extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);
		TopoDS_Face surface = section.getSurface();
		Set<Panel> panels = getGraph().allInstances(Panel.class);

		TopoDS_Shape floorSkin = BRepApi.thickenShape(surface, 3.0);
		TopoDS_Shape cutSkin = BRepApi.thickenShape(surface, -62.0);

		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h / section.getNPanelZ();
		double dl = section.getL().getValue() / section.getNPanelX();

		Component clipsComp = Component.create().setId("Clips");

		Component helpComp = Component.create().setId("0_Clips");
		G helpG = G.create();
		helpComp.setShape(helpG);

		double length = section.getClipFloorLenght().getValue();
		double width = section.getClipFloorWidth().getValue();
		double u = 30.;
		double l2 = getGraph().firstInstance(StringerProfile.class).getHeight().getValue() + 20.;
		double heightForClip = getGraph().firstInstance(SpantProfile.class).getHeightForClip().getValue();
		double l4 = heightForClip - l2;
		double hi = 10.; // höhe von freiraum für stringer

		List<Transform> transforms = new ArrayList<>();
		List<Clip> clips = new ArrayList<>();

		for (Panel p : panels) {
			for (int i = 0; i < section.getNStringer(); i++) {
				for (int j = 0; j < section.getNSpante(); j++) {

					Clip clip = Clip.create().setId("Clip " + "(" + i + "," + j + ")");
					clips.add(clip);
					clipsComp.getSub().add(clip);
					p.getClip().add(clip);
					clip.setZi(i);
					clip.setXi(j);
					clip.setStringer(p.getStringer().get(i));
					clip.setSpant(p.getSpant().get(j));
					clip.setSkin(p.getSkin());

					double x = p.getXi() * dl + dl / section.getNSpante() * (0.5 + j);
					double angle = 180. - (zA + p.getZi() * dh + dh / section.getNStringer() * (0.5 + i));

					transforms.add(createClip_1(x, angle, length, width, helpComp));

				}
			}
		}

		GeometryAPI api = new GeometryAPI(getRunningDC43Project());
		Graph<TopoDS_Shape> graph = api.generateGeometry();

		List<TopoDS_Shape> floor = new ArrayList<>();

//		List<Component> clipComp = new ArrayList<>();

		int counter = 0;
		for (Panel p : panels) {
			for (int i = 0; i < section.getNStringer(); i++) {
				for (int j = 0; j < section.getNSpante(); j++) {

					double x = p.getXi() * dl + dl / section.getNSpante() * (0.5 + j);
					double angle = 180. - (zA + p.getZi() * dh + dh / section.getNStringer() * (0.5 + i));

					floor.add(createClip_2(graph, transforms.get(counter), floorSkin, clips.get(counter)));

					counter++;
				}
			}
		}

		List<Block> wall_1 = new ArrayList<>();

		counter = 0;
		for (Panel p : panels) {
			for (int i = 0; i < section.getNStringer(); i++) {
				for (int j = 0; j < section.getNSpante(); j++) {

					double x = p.getXi() * dl + dl / section.getNSpante() * (0.5 + j);
					double angle = 180. - (zA + p.getZi() * dh + dh / section.getNStringer() * (0.5 + i));

					wall_1.add(createClip_3(x, angle, length, graph, transforms.get(counter), floorSkin, surface, helpG,
							u, l2, l4, hi, floor.get(counter), helpComp));
					counter++;
				}
			}
		}

		graph = api.generateGeometry();

		counter = 0;
		for (Panel p : panels) {
			for (int i = 0; i < section.getNStringer(); i++) {
				for (int j = 0; j < section.getNSpante(); j++) {

					double x = p.getXi() * dl + dl / section.getNSpante() * (0.5 + j);
					double angle = 180. - (zA + p.getZi() * dh + dh / section.getNStringer() * (0.5 + i));

					createClip_4(x, angle, wall_1.get(counter), graph, cutSkin, u, l2, l4, hi, surface,
							floor.get(counter), clips.get(counter));

					counter++;
				}
			}
		}

		clipsComp.setId("Clips (" + clipsComp.getSub().size() + ")");

	}

	private Transform createClip_1(double x, double angle, double length, double width, Component helpComp) {

		// Floor
		Cylinder cyl = Cylinder.create();
		cyl.setRadius(2500.);
		cyl.setHeight(50.);
		cyl.setPhi(length);
		cyl.setRx(1.0);
		cyl.setRy(0.0);
		cyl.setRz(0.0);

		Transform t = Transform.create();
		t.setLive(cyl);
		t.setDx(x);
		t.setPhi(-angle);

		Component cutoutComp = Component.create().setId("Cut Cylinder");
		helpComp.getSub().add(cutoutComp);
		cutoutComp.setShape(t);

		return t;
	}

	private TopoDS_Shape createClip_2(Graph<TopoDS_Shape> graph, Transform t, TopoDS_Shape floorSkin, Clip clip) {

		TopoDS_Shape cylTopoDS = graph.getNode(t).getRepresentation();
		TopoDS_Shape floorNegative = BRepApi.getIntersection_(cylTopoDS, floorSkin);
		TopoDS_Shape floor1 = BRepApi.getIntersection_cut(cylTopoDS, floorNegative);
		Component floor = BRepApi.visualizeShape(floor1, "Clip Floor");

		clip.getSub().add(floor);

		return floor1;
	}

	private Block createClip_3(double x, double angle, double height, Graph<TopoDS_Shape> graph, Transform t,
			TopoDS_Shape floorSkin, TopoDS_Face surface, G helpG, double u, double l2, double l4, double h,
			TopoDS_Shape floor1, Component helpComp) throws Exception {

		// 1st Wall

		// Calculate height of clip floor
		List<TopoDS_Vertex> vertex = BRepApi.findElements(floor1, TopoDS_Vertex.class);

		gp_Pnt pnt_A = findPoint(vertex, angle, x, false);
		gp_Pnt pnt_B = findPoint(vertex, angle - height, x, false);

		double dy = pnt_A.Y() - pnt_B.Y();
		double dz = pnt_A.Z() - pnt_B.Z();
		double clipHeighth = Math.sqrt((dy * dy) + (dz * dz));

		double l1 = clipHeighth;

//		double l3 = 30.;

//		double l5 = l1 - l3;
		double l5 = -15.;
		double l3 = l1 - l5;
		double l6 = l2 + l4;
		double r1 = 10.;
		double r2 = 5.;
		double r3 = 10.;
		double d = 20.; // tiefe in Haut rein

		List<Point> p1 = new ArrayList<>();

		p1.add(Point.create(0.0, 0.0, -d));
		p1.add(Point.create(0.0, l1, -d));
		p1.add(Point.create(0.0, l1, l2 - r1));
		p1.add(Point.create(0.0, l1 - r1, l2));
		p1.add(Point.create(0.0, l1 - l3 + r2, l2));
		p1.add(Point.create(0.0, l1 - l3, l2 + r2));
		p1.add(Point.create(0.0, l1 - l3, l2 + l4 - r3));
		p1.add(Point.create(0.0, l1 - l3 - r3, l2 + l4));
		p1.add(Point.create(0.0, 0.0, l2 + l4));
		p1.add(Point.create(0.0, -u, l2 + l4));
		p1.add(Point.create(0.0, -u, h));
		p1.add(Point.create(0.0, 0., h));

		Wire wallWire1 = Wire.create();

		Line line0 = Line.create();
		line0.setStartPoint(p1.get(0));
		line0.setEndPoint(p1.get(1));
		wallWire1.getElement().add(line0);
		wallWire1.setStart(line0);

		Line line1 = Line.create();
		line1.setStartPoint(p1.get(1));
		line1.setEndPoint(p1.get(2));
		wallWire1.getElement().add(line1);
		line0.getNext().add(line1);

		Arc arc2 = Arc.create();
		arc2.setStartPoint(p1.get(2));
		arc2.setEndPoint(p1.get(3));
		Point centre2 = Point.create(0.0, l1 - r1, l2 - r1);
		arc2.setCentre(centre2);
		Direction dir2 = Direction.create(1.0, 0.0, 0.0);
		arc2.setNormal(dir2);
		wallWire1.getElement().add(arc2);
		line1.getNext().add(arc2);

		Line line3 = Line.create();
		line3.setStartPoint(p1.get(3));
		line3.setEndPoint(p1.get(4));
		wallWire1.getElement().add(line3);
		arc2.getNext().add(line3);

		Arc arc4 = Arc.create();
		arc4.setStartPoint(p1.get(4));
		arc4.setEndPoint(p1.get(5));
		Point centre4 = Point.create(0.0, l1 - l3 + r2, l2 + r2);
		arc4.setCentre(centre4);
		Direction dir4 = Direction.create(-1.0, 0.0, 0.0);
		arc4.setNormal(dir4);
		wallWire1.getElement().add(arc4);
		line3.getNext().add(arc4);

		Line line5 = Line.create();
		line5.setStartPoint(p1.get(5));
		line5.setEndPoint(p1.get(6));
		wallWire1.getElement().add(line5);
		arc4.getNext().add(line5);

		Arc arc6 = Arc.create();
		arc6.setStartPoint(p1.get(6));
		arc6.setEndPoint(p1.get(7));
		Point centre6 = Point.create(0.0, l1 - l3 - r3, l2 + l4 - r3);
		arc6.setCentre(centre6);
		Direction dir6 = Direction.create(1.0, 0.0, 0.0);
		arc6.setNormal(dir6);
		wallWire1.getElement().add(arc6);
		line5.getNext().add(arc6);

		Line line7 = Line.create();
		line7.setStartPoint(p1.get(7));
		line7.setEndPoint(p1.get(9));
		wallWire1.getElement().add(line7);
		arc6.getNext().add(line7);

//		Line line8 = Line.create();
//		line8.setStartPoint(p1.get(8));
//		line8.setEndPoint(p1.get(9));
//		wallWire1.getElement().add(line8);
//		line7.getNext().add(line8);

		Line line9 = Line.create();
		line9.setStartPoint(p1.get(9));
		line9.setEndPoint(p1.get(10));
		wallWire1.getElement().add(line9);
		line7.getNext().add(line9);

		Line line10 = Line.create();
		line10.setStartPoint(p1.get(10));
		line10.setEndPoint(p1.get(11));
		wallWire1.getElement().add(line10);
		line9.getNext().add(line10);

		Line line11 = Line.create();
		line11.setStartPoint(p1.get(11));
		line11.setEndPoint(p1.get(0));
		wallWire1.getElement().add(line11);
		line10.getNext().add(line11);

		line11.getNext().add(line0);

//		Line line7 = Line.create();
//		line7.setStartPoint(p1.get(7));
//		line7.setEndPoint(p1.get(8));
//		wallWire1.getElement().add(line7);
//		arc6.getNext().add(line7);
//
//		Line line8 = Line.create();
//		line8.setStartPoint(p1.get(8));
//		line8.setEndPoint(p1.get(9));
//		wallWire1.getElement().add(line8);
//		line7.getNext().add(line8);
//
//		Line line9 = Line.create();
//		line9.setStartPoint(p1.get(9));
//		line9.setEndPoint(p1.get(10));
//		wallWire1.getElement().add(line9);
//		line8.getNext().add(line9);
//
//		Line line10 = Line.create();
//		line10.setStartPoint(p1.get(10));
//		line10.setEndPoint(p1.get(11));
//		wallWire1.getElement().add(line10);
//		line9.getNext().add(line10);
//
//		Line line11 = Line.create();
//		line11.setStartPoint(p1.get(11));
//		line11.setEndPoint(p1.get(0));
//		wallWire1.getElement().add(line11);
//		line10.getNext().add(line11);
//
//		line11.getNext().add(line0);

		Component compWall = Component.create().setId("Wall preCut");
		G gWall = G.create();
		compWall.setShape(gWall);
		helpComp.getSub().add(compWall);

		// Move Walls

		Line line = Line.create();
		line.setStartPoint(Point.create(x, 0., 0.));
		line.setEndPoint(Point.create(x, 2500., 2500 / Math.tan((angle - height) / 180 * Math.PI)));
		helpG.getGeometric().add(line);

		gp_Pnt cornerPnt = findPoint(vertex, angle, x, true);

		Point point = Point.create(cornerPnt.X(), cornerPnt.Y(), cornerPnt.Z());

//		helpG.getGeometric().add(point);

		gp_Vec normVec = BRepApi.getNormalToSurfaceAtPointAlternative(surface, cornerPnt);

		double phi = (Math.PI - Math.atan2(normVec.Y(), normVec.Z()));
		TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(cornerPnt.X(), cornerPnt.Y(),
				cornerPnt.Z(), phi, 0., 0.);

		Wire wallWire_1 = copyWire(wallWire1, matrix);
		gWall.getGeometric().add(wallWire_1);

		Block wall_1 = Block.create();
		wall_1.setProfile(wallWire_1);
		wall_1.setHeight(2.);
		Direction dir_1 = Direction.create(1., 0., 0.);
		wall_1.setDirection(dir_1);
		compWall.setShape(wall_1);

		return wall_1;
	}

	private void createClip_4(double x, double angle, Block wall_1, Graph<TopoDS_Shape> graph, TopoDS_Shape cutSkin,
			double u, double l2, double l4, double h, TopoDS_Face surface, TopoDS_Shape floor1, Clip clip)
			throws Exception {

		List<TopoDS_Vertex> vertex = BRepApi.findElements(floor1, TopoDS_Vertex.class);
		gp_Pnt cornerPnt = findPoint(vertex, angle, x, true);
		gp_Vec normVec = BRepApi.getNormalToSurfaceAtPointAlternative(surface, cornerPnt);
		double phi = (Math.PI - Math.atan2(normVec.Y(), normVec.Z()));
		TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(cornerPnt.X(), cornerPnt.Y(),
				cornerPnt.Z(), phi, 0., 0.);

		TopoDS_Shape wall_1_TopoDS = graph.getNode(wall_1).getRepresentation();
		TopoDS_Shape correctWall_1 = BRepApi.getIntersection_cut(wall_1_TopoDS, cutSkin);

		Component clipWall1Comp = BRepApi.visualizeShape(correctWall_1, "Clip Wall_1");
		clip.getSub().add(clipWall1Comp);

		// 2nd Wall
		List<Point> p2 = new ArrayList<>();

		double l7 = 20.;
		double l8 = 50.;
		double l9 = 2.;
		double l10 = l2 + l4;

		p2.add(Point.create(0.0, -u, h));
		p2.add(Point.create(l7, -u, h));
		p2.add(Point.create(l7, -u, h + l8));
		p2.add(Point.create(l9, -u, l10));
		p2.add(Point.create(0.0, -u, l10));

		Wire wallWire2 = Wire.create();

		Line line0_2 = Line.create();
		line0_2.setStartPoint(p2.get(0));
		line0_2.setEndPoint(p2.get(1));
		wallWire2.getElement().add(line0_2);
		wallWire2.setStart(line0_2);

		Line line1_2 = Line.create();
		line1_2.setStartPoint(p2.get(1));
		line1_2.setEndPoint(p2.get(2));
		wallWire2.getElement().add(line1_2);
		line0_2.getNext().add(line1_2);

		Line line2_2 = Line.create();
		line2_2.setStartPoint(p2.get(2));
		line2_2.setEndPoint(p2.get(3));
		wallWire2.getElement().add(line2_2);
		line1_2.getNext().add(line2_2);

		Line line3_2 = Line.create();
		line3_2.setStartPoint(p2.get(3));
		line3_2.setEndPoint(p2.get(4));
		wallWire2.getElement().add(line3_2);
		line2_2.getNext().add(line3_2);

		Line line4_2 = Line.create();
		line4_2.setStartPoint(p2.get(4));
		line4_2.setEndPoint(p2.get(0));
		wallWire2.getElement().add(line4_2);
		line3_2.getNext().add(line4_2);

		line4_2.getNext().add(line0_2);

		Wire wallWire_2 = copyWire(wallWire2, matrix);
		Block wall_2 = Block.create();
		wall_2.setProfile(wallWire_2);
		wall_2.setHeight(2.);
		Direction dir_2 = Direction.create(0., normVec.Z(), -Math.abs(normVec.Y()));
		wall_2.setDirection(dir_2);

		Component clipWall2Comp = Component.create().setId("Clip Wall_2").setShape(wall_2);
		clip.getSub().add(clipWall2Comp);
//		compWall_2.setShape(wall_2);

		return;
	}

	private gp_Pnt findPoint(List<TopoDS_Vertex> vertex, double angle, double x, boolean farthest) {

		double y;
		if (farthest) {
			y = 0.;
		} else {
			y = Double.MAX_VALUE;
		}

		gp_Pnt cornerPnt = new gp_Pnt();

		for (TopoDS_Vertex topoDS_Vertex : vertex) {

			gp_Pnt pnt = BRep_Tool.Pnt(topoDS_Vertex);

			if (pnt.X() == x) {

				if (Math.abs(pnt.Z() - pnt.Y() / Math.tan((angle) / 180 * Math.PI)) < 0.00001) {

					if (farthest) {
						if (pnt.Y() > y) {
							y = pnt.Y();
							cornerPnt = pnt;
						}
					} else {
						if (pnt.Y() < y) {
							y = pnt.Y();
							cornerPnt = pnt;
						}
					}
				}
			}
		}

		return cornerPnt;
	}

	// Kopiert und transformiert Wire
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