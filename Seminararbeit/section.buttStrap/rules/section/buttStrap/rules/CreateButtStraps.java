package section.buttStrap.rules;

import java.util.ArrayList;
import java.util.List;

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
import opencascade.TopoDS_Face;
import opencascade.TopoDS_Shape;
import opencascade.gp_Dir;
import opencascade.gp_Pnt;
import opencascade.gp_Vec;
import section.ButtStrap;
import section.Panel;
import section.Section;

@SuppressWarnings("all")
public class CreateButtStraps extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);

		TopoDS_Face surface = section.getSurface();

		double width_buttStrap = section.getButtStrapWidth().getValue();

		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h / section.getNPanelZ();
		double dl = section.getL().getValue() / section.getNPanelX();

		TopoDS_Shape cutShape = BRepApi.thickenShape(surface, 2.);

		int n_panel_x = section.getNPanelX();
		int n_panel_z = section.getNPanelZ();

		// Creating vertical ButtStraps
		Cylinder[][] cylinder_vertical = new Cylinder[n_panel_z][n_panel_x - 1];
		Transform[][] transformedCylinder_vertical = new Transform[n_panel_z][n_panel_x - 1];

		Component comp_help = Component.create().setId("0_Buttstrap");

		for (int i = 0; i < n_panel_z; i++) {
			for (int j = 0; j < n_panel_x - 1; j++) {
				cylinder_vertical[i][j] = Cylinder.create();
				cylinder_vertical[i][j].setRadius(10000.);
				cylinder_vertical[i][j].setHeight(width_buttStrap);
				cylinder_vertical[i][j].setPhi(dh);
				cylinder_vertical[i][j].setRx(1.0);
				cylinder_vertical[i][j].setRy(0.0);
				cylinder_vertical[i][j].setRz(0.0);

				transformedCylinder_vertical[i][j] = Transform.create();
				transformedCylinder_vertical[i][j].setLive(cylinder_vertical[i][j]);

				transformedCylinder_vertical[i][j].setPhi(-180.0 + zA + (dh * (i)));
				transformedCylinder_vertical[i][j].setDx(dl * (j + 1) - width_buttStrap / 2);

				Component comp_cut = Component.create().setId("Cutout vertical: " + j + i);
				comp_help.getSub().add(comp_cut);
				comp_cut.setShape(transformedCylinder_vertical[i][j]);

			}
		}

		// Horizontal Buttstraps

		List<Cylinder> cylinder_horizontal = new ArrayList<>();
		List<Transform> transformedCylinder_horizontal = new ArrayList<>();

		for (int i = 0; i < section.getNPanelX(); i++) {
			cylinder_horizontal.add(Cylinder.create());
			cylinder_horizontal.get(i).setRadius(10000.);
			cylinder_horizontal.get(i).setHeight(dl - width_buttStrap);
			cylinder_horizontal.get(i).setPhi(h);
			cylinder_horizontal.get(i).setRx(1.0);
			cylinder_horizontal.get(i).setRy(0.0);
			cylinder_horizontal.get(i).setRz(0.0);

			transformedCylinder_horizontal.add(Transform.create());
			transformedCylinder_horizontal.get(i).setLive(cylinder_horizontal.get(i));

			transformedCylinder_horizontal.get(i).setPhi(-180.0 + zA);
			transformedCylinder_horizontal.get(i).setDx(dl * i + width_buttStrap / 2);

			Component comp_cut = Component.create().setId("Cutout horizontal: " + +i);
			comp_help.getSub().add(comp_cut);
			comp_cut.setShape(transformedCylinder_horizontal.get(i));
		}

		List<Loft> longButtStraps = new ArrayList<>();

		for (int i = 0; i < section.getNPanelZ() - 1; i++) {
			double alpha = zA + dh * (i + 1);
			Loft longButtStrap = createLongButtStrap(alpha, surface, width_buttStrap);
			longButtStraps.add(longButtStrap);
			comp_help.getSub().add(Component.create().setShape(longButtStrap).setId("Loft"));
		}

		GeometryAPI api = new GeometryAPI(getRunningDC43Project());
		Graph<TopoDS_Shape> graph = api.generateGeometry();

		TopoDS_Shape[][] transformedCylinder_vertical_TOPODS = new TopoDS_Shape[n_panel_z][n_panel_x - 1];
		TopoDS_Shape[][] buttStraps_negative = new TopoDS_Shape[n_panel_z][n_panel_x - 1];
		TopoDS_Shape[][] buttStraps = new TopoDS_Shape[n_panel_z][n_panel_x - 1];

		Component comp_buttStraps = Component.create().setId("Buttstraps");

		for (int i = 0; i < n_panel_z; i++) {
			for (int j = 0; j < n_panel_x - 1; j++) {
				transformedCylinder_vertical_TOPODS[i][j] = graph.getNode(transformedCylinder_vertical[i][j])
						.getRepresentation();
				buttStraps_negative[i][j] = BRepApi.getIntersection_(transformedCylinder_vertical_TOPODS[i][j],
						cutShape);
				buttStraps[i][j] = BRepApi.getIntersection_cut(transformedCylinder_vertical_TOPODS[i][j],
						buttStraps_negative[i][j]);

				ButtStrap buttStrap = ButtStrap.create().setId("Vertical Buttstrap (" + i + "," + j + ")");
				buttStrap.setOrientation("vertical");
				buttStrap.setXi(j);
				buttStrap.setZi(i);

				buttStrap.getSub().add(BRepApi.visualizeShape(buttStraps[i][j]));
				comp_buttStraps.getSub().add(buttStrap);

				buttStrap.getSkin().add(searchPanel(section, j, i).getSkin());
				buttStrap.getSkin().add(searchPanel(section, j + 1, i).getSkin());

			}
		}

		List<TopoDS_Shape> longButtStraps_TOPODS = new ArrayList<>();

		for (Loft l : longButtStraps) {
			longButtStraps_TOPODS.add(graph.getNode(l).getRepresentation());
		}

		for (int i = 0; i < section.getNPanelZ() - 1; i++) {
			for (int j = 0; j < section.getNPanelX(); j++) {
				TopoDS_Shape cutout_horizontal = graph.getNode(transformedCylinder_horizontal.get(j))
						.getRepresentation();
				TopoDS_Shape intersection_1 = BRepApi.getIntersection_(cutout_horizontal, cutShape);
				TopoDS_Shape intersection_2 = BRepApi.getIntersection_cut(cutout_horizontal, intersection_1);
				TopoDS_Shape intersection_3 = BRepApi.getIntersection_(intersection_2, longButtStraps_TOPODS.get(i));
				TopoDS_Shape intersection_4 = BRepApi.getIntersection_cut(intersection_2, intersection_3);

				ButtStrap buttStrap = ButtStrap.create().setId("Horizontal Buttstrap (" + i + "," + j + ")");
				buttStrap.setOrientation("horizontal");
				buttStrap.setXi(j);
				buttStrap.setZi(i);
				buttStrap.getSub().add(BRepApi.visualizeShape(intersection_4));
				comp_buttStraps.getSub().add(buttStrap);

				buttStrap.getSkin().add(searchPanel(section, j, i).getSkin());
				buttStrap.getSkin().add(searchPanel(section, j, i + 1).getSkin());
			}
		}

		for (int i = 0; i < n_panel_z - 1; i++) {
			TopoDS_Shape longHorizointalButtStrapNeg = BRepApi.getIntersection_(longButtStraps_TOPODS.get(i), cutShape);
			TopoDS_Shape longHorizointalButtStrap = BRepApi.getIntersection_cut(longButtStraps_TOPODS.get(i),
					longHorizointalButtStrapNeg);
		}

		comp_buttStraps.setId("Buttstraps (" + comp_buttStraps.getSub().size() + ")");

	}

	/////////////////////// FUNCTIONS /////////////////////////

	// Creates a long horizontal buttStrap at the intersection of the surface and plane which is oriented with the angle
	private Loft createLongButtStrap(double angle, TopoDS_Face surface, double width) throws Exception {
		List<Point> p = new ArrayList<>();
		p.add(Point.create(0., -width / 2, -50.));
		p.add(Point.create(0., -width / 2, 50.));
		p.add(Point.create(0., width / 2, 50.));
		p.add(Point.create(0., width / 2, -50.));

		Wire wire = Wire.create();

		Line line0 = Line.create();
		line0.setStartPoint(p.get(0));
		line0.setEndPoint(p.get(1));
		wire.getElement().add(line0);
		wire.setStart(line0);

		Line line1 = Line.create();
		line1.setStartPoint(p.get(1));
		line1.setEndPoint(p.get(2));
		wire.getElement().add(line1);
		line0.getNext().add(line1);

		Line line2 = Line.create();
		line2.setStartPoint(p.get(2));
		line2.setEndPoint(p.get(3));
		wire.getElement().add(line2);
		line1.getNext().add(line2);

		Line line3 = Line.create();
		line3.setStartPoint(p.get(3));
		line3.setEndPoint(p.get(0));
		wire.getElement().add(line3);
		line2.getNext().add(line3);

		line3.getNext().add(line0);

		Geom_Curve curve = BRepApi.getIntersectionCurveInPlaneAtPoint(surface, new gp_Pnt(0, 0, 0),
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

			normalVec = BRepApi.getNormalToSurfaceAtPointAlternative(surface, point);

			double phi = (Math.PI - Math.atan2(normalVec.Y(), normalVec.Z()));

			TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(point.X(), point.Y(),
					point.Z(), phi, 0., 0.);

			wires.add(copyWire(wire, matrix));
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

	// Reconstructs a arc at a new position according to the
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

	// Reconstructs a line at a new position according to the
	// given matrix
	private Line copy(Line line, TransformationMatrix transformationMatrix) {

		Point newStartPoint = copyPoint(line.getStartPoint(), transformationMatrix);
		Point newEndPoint = copyPoint(line.getEndPoint(), transformationMatrix);

		Line lineTransformed = Line.create(newStartPoint, newEndPoint);
		return lineTransformed;
	}

	// Reconstructs a point at a new position according to the
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
