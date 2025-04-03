package section.spant.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import Jama.Matrix;
import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.TransformationMatrix;
import de.iils.dc43.core.geometry.API.InterpolateCurveNetwork;
import de.iils.dc43.core.geometry.API.OCCUtils;
import de.iils.dc43.core.geometry.publication.Arc;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.Curve;
import de.iils.dc43.core.geometry.publication.CustomG;
import de.iils.dc43.core.geometry.publication.Direction;
import de.iils.dc43.core.geometry.publication.G;
import de.iils.dc43.core.geometry.publication.Line;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Profile;
import de.iils.dc43.core.geometry.publication.Wire;
import opencascade.BRepBuilderAPI_MakeEdge;
import opencascade.BRepBuilderAPI_MakeFace;
import opencascade.BRep_Tool;
import opencascade.GC_MakeArcOfCircle;
import opencascade.GC_MakeCircle;
import opencascade.GC_MakeSegment;
import opencascade.GeomAbs_Shape;
import opencascade.GeomConvert_ApproxCurve;
import opencascade.GeomConvert_CompCurveToBSplineCurve;
import opencascade.Geom_BSplineCurve;
import opencascade.Geom_BSplineSurface;
import opencascade.Geom_Circle;
import opencascade.Geom_Curve;
import opencascade.Geom_TrimmedCurve;
import opencascade.TopAbs_ShapeEnum;
import opencascade.TopExp_Explorer;
import opencascade.TopoDS;
import opencascade.TopoDS_Edge;
import opencascade.TopoDS_Face;
import opencascade.TopoDS_Shape;
import opencascade.TopoDS_Wire;
import opencascade.gp_Ax1;
import opencascade.gp_Dir;
import opencascade.gp_Pnt;
import opencascade.gp_Vec;
import section.Panel;
import section.Section;
import section.SpantProfile;
import section.StringerProfile;

@SuppressWarnings("all")
public class CreateSpant_2 extends JavaRule {

	@Override
	public void execute() throws Exception {
		Section section = getGraph().firstInstance(Section.class);

		Set<Panel> panels = getGraph().allInstances(Panel.class);

		List<Geom_Curve> intersections = new ArrayList<>();

		double dx = section.getL().getValue() / (section.getNSpante() * section.getNPanelX());
		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h / section.getNPanelZ();

//		for (int i = 0; i < section.getNSpante() * section.getNPanelX(); i++) {

//		for (int i = 0; i < 1; i++) {
//			x = dx / 2 + i * dx;
//			intersections.add(BRepApi.getIntersectionCurveInPlaneAtPoint(section.getSurface(), new gp_Pnt(x, 0, 0),
//					new gp_Dir(1.0, 0, 0)));
//			System.out.println("spant" + i);
//			BRepApi.visualizeShape(intersections.get(i), "Spant Intersection: " + i);
//		}
//
//		List<Spline> splines = new ArrayList<>();
//		for (Geom_Curve inter : intersections) {
//			splines.add(BRepApi.createSplineAGFromGeom_Curve(inter, 30));
//		}
//		

//		Wire definitionProfile = getGraph().firstInstance(SpantProfile.class).getProfile();
//		Wire wire = getGraph().firstInstance(StringerProfile.class).getProfile();

//		List<Loft> lofts = new ArrayList<>();
//		for (Spline spline : splines) {
//			lofts.add(createLongSpant(spline, section.getSurface(), wire));
//		}

//		createLongSpant(splines.get(0), section.getSurface(), definitionProfile);

		// ________________________________________-

		double t = 2.0;
		double l1 = 60.0;
		double l2 = 20.0;
		double l3 = 10.0;
		double l4 = 20.0;
		double r1 = 1.0;
		double r2 = 1.0;
		double r3 = 1.0;

		SpantProfile spProf = SpantProfile.create();
		double stringerHeight = getGraph().firstInstance(StringerProfile.class).getHeight().getValue();
		spProf.setHeightForClip(t + r1 + l1 + stringerHeight);

		gp_Pnt p1 = new gp_Pnt(0.0, 0.0, 0.0);
		gp_Pnt p2 = new gp_Pnt(0.0, l4, 0.0);
		gp_Pnt p3 = new gp_Pnt(0.0, l4, t);
		gp_Pnt p4 = new gp_Pnt(0.0, 0.0, t);
		gp_Pnt p5 = new gp_Pnt(0.0, -r1, t + r1);
		gp_Pnt p6 = new gp_Pnt(0.0, -r1, t + r1 + l1);
		gp_Pnt p7 = new gp_Pnt(0.0, -(r1 + t + r2), t + r1 + l1 + r2 + t);
		gp_Pnt p8 = new gp_Pnt(0.0, -(r1 + t + r2 + l2), t + r1 + l1 + r2 + t);
		gp_Pnt p9 = new gp_Pnt(0.0, -(r1 + t + r2 + l2 + r3 + t), t + r1 + l1 + r2 - r3);
		gp_Pnt p10 = new gp_Pnt(0.0, -(r1 + t + r2 + l2 + r3 + t), t + r1 + l1 + r2 - r3 - l3);
		gp_Pnt p11 = new gp_Pnt(0.0, -(r1 + t + r2 + l2 + r3), t + r1 + l1 + r2 - r3 - l3);
		gp_Pnt p12 = new gp_Pnt(0.0, -(r1 + t + r2 + l2 + r3), t + r1 + l1 + r2 - r3);
		gp_Pnt p13 = new gp_Pnt(0.0, -(r1 + t + r2 + l2), t + r1 + l1 + r2);
		gp_Pnt p14 = new gp_Pnt(0.0, -(r1 + t + r2), t + r1 + l1 + r2);
		gp_Pnt p15 = new gp_Pnt(0.0, -(r1 + t), t + r1 + l1);
		gp_Pnt p16 = new gp_Pnt(0.0, -(r1 + t), r1 + t);

		gp_Pnt center1 = new gp_Pnt(0, 0, t + r1);
		gp_Pnt center2 = new gp_Pnt(0, -(r1 + t + r2), t + r1 + l1);
		gp_Pnt center3 = new gp_Pnt(0.0, -(r1 + t + r2 + l2), t + r1 + l1);
		gp_Vec normal = new gp_Vec(-1, 0, 0);
		gp_Vec invNormal = new gp_Vec(1, 0, 0);

		List<Geom_TrimmedCurve> segments = Lists.newArrayList();
		// L0
		segments.add(new GC_MakeSegment(p1, p2).Value());
		// L1
		segments.add(new GC_MakeSegment(p2, p3).Value());
		// L2
		segments.add(new GC_MakeSegment(p3, p4).Value());
		// L3
		segments.add(makeArc(p4, p5, center1, normal));
		// L4
		segments.add(new GC_MakeSegment(p5, p6).Value());
		// L5
		segments.add(makeArc(p6, p7, center2, invNormal));
		// L6
		segments.add(new GC_MakeSegment(p7, p8).Value());
		// L7
		segments.add(makeArc(p8, p9, center3, invNormal));
		// L8
		segments.add(new GC_MakeSegment(p9, p10).Value());
		// L9
		segments.add(new GC_MakeSegment(p10, p11).Value());
		// L10
		segments.add(new GC_MakeSegment(p11, p12).Value());
		// L11
		segments.add(makeArc(p12, p13, center3, normal));
		// L12
		segments.add(new GC_MakeSegment(p13, p14).Value());
		// L13
		segments.add(makeArc(p14, p15, center2, normal));
		// L14
		segments.add(new GC_MakeSegment(p15, p16).Value());
		// L15
		segments.add(makeArc(p16, p1, center1, invNormal));

		GeomConvert_CompCurveToBSplineCurve comp = new GeomConvert_CompCurveToBSplineCurve();

		List<Geom_BSplineCurve> approxSegments = Lists.newArrayList();
		Component spantComponent = Component.create().setId("spant profile");
		for (Geom_TrimmedCurve curve : segments) {
			create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(curve).Edge(), "profile segment",
					spantComponent);
			GeomConvert_ApproxCurve approx = new GeomConvert_ApproxCurve(curve, 1e-4, GeomAbs_Shape.GeomAbs_C1, 2, 10);
			Geom_BSplineCurve bspline = approx.Curve();

			bspline.Rotate(new gp_Ax1(new gp_Pnt(0, 0, 0), new gp_Dir(0, 0, 1)), Math.PI / 2);

			bspline.Translate(new gp_Vec(0, 0, stringerHeight));
			bspline.Translate(new gp_Vec(-(t + r1), 0., 0.));

			approxSegments.add(bspline);
			comp.Add(bspline, 0.00001);
		}
		Geom_BSplineCurve profileCurve = comp.BSplineCurve();

		TopoDS_Edge profileEdge = new BRepBuilderAPI_MakeEdge(profileCurve).Edge();
		create43GeometryFromTopoDSShape(profileEdge, "profile", spantComponent);

//		double startAngle = 180 - zA;
//		double endAngle = (zA + dh);
//
//		Point origin = Point.create(dx / 2, 0., 0.);
//
//		Point startPoint = Point.create(dx / 2, Math.sin(startAngle / 180 * Math.PI) * 3000,
//				Math.cos(startAngle / 180 * Math.PI) * 3000);
//
//		Point endPoint = Point.create(dx / 2, Math.sin(endAngle / 180 * Math.PI) * 3000,
//				Math.cos(endAngle / 180 * Math.PI) * 3000);
//
//		Geom_Curve startLine = BRepApi.createLine(origin, startPoint);
//		Geom_Curve endLine = BRepApi.createLine(origin, endPoint);
//
//		BRepApi.visualizeShape(startLine, "Start Line");
//		BRepApi.visualizeShape(endLine, "End Line");
//
//		List<gp_Pnt> startIntersectionPoint = BRepApi.getIntersectionPointCurveSurface(startLine,
//				BRep_Tool.Surface(section.getSurface()));
//
//		List<gp_Pnt> endIntersectionPoint = BRepApi.getIntersectionPointCurveSurface(endLine,
//				BRep_Tool.Surface(section.getSurface()));
//
//		double startParam = BRepApi.getParameterOnCurveAtPoint(intersections.get(0), startIntersectionPoint.get(0));
//		double endParam = BRepApi.getParameterOnCurveAtPoint(intersections.get(0), endIntersectionPoint.get(0));

//		createSpantSegments(intersections.get(0), section.getSurface(), approxSegments, startParam, endParam);

//		double x;
//		double dx = section.getL().getValue() / (section.getNSpante() * section.getNPanelX());
//		double zA = section.getZA().getValue();
//		double h = section.getH().getValue();
//		double dh = h / section.getNPanelZ();
		double dl = section.getL().getValue() / section.getNPanelX();

		for (Panel p : panels) {
			for (int i = 0; i < section.getNSpante(); i++) {

				double x = p.getXi() * dl + dx * i + dx / 2;

				Geom_Curve guideLine = BRepApi.getIntersectionCurveInPlaneAtPoint(section.getSurface(),
						new gp_Pnt(x, 0, 0), new gp_Dir(1.0, 0, 0));

				double startAngle = 180 - (zA + p.getZi() * dh);
				double endAngle = 180 - (zA + (p.getZi() + 1) * dh);

				Point origin = Point.create(dx / 2, 0., 0.);

				Point startPoint = Point.create(dx / 2, Math.sin(startAngle / 180 * Math.PI) * 3000,
						Math.cos(startAngle / 180 * Math.PI) * 3000);

				Point endPoint = Point.create(dx / 2, Math.sin(endAngle / 180 * Math.PI) * 3000,
						Math.cos(endAngle / 180 * Math.PI) * 3000);

				Geom_Curve startLine = BRepApi.createLine(origin, startPoint);
				Geom_Curve endLine = BRepApi.createLine(origin, endPoint);

				List<gp_Pnt> startIntersectionPoint = BRepApi.getIntersectionPointCurveSurface(startLine,
						BRep_Tool.Surface(section.getSurface()));

				List<gp_Pnt> endIntersectionPoint = BRepApi.getIntersectionPointCurveSurface(endLine,
						BRep_Tool.Surface(section.getSurface()));

				double startParam = BRepApi.getParameterOnCurveAtPoint(guideLine, startIntersectionPoint.get(0));
				double endParam = BRepApi.getParameterOnCurveAtPoint(guideLine, endIntersectionPoint.get(0));

				createSpantSegments(guideLine, section.getSurface(), approxSegments, startParam, endParam);
			}
		}

//____________________________________________________________________________________________________

	}

	private void createSpantSegments(Geom_Curve intersection, TopoDS_Face face, List<Geom_BSplineCurve> segments,
			double paramStart, double paramEnd) throws Exception {

		for (int k = 0; k < segments.size(); k++) {

			Geom_BSplineCurve segment = segments.get(k);
			List<Geom_Curve> wireCurves = Lists.newArrayList();

//			Component comp = Component.create().setId("Transformed Profiles");

			List<Geom_Curve> profileCurves = Lists.newArrayList();
			List<Geom_Curve> guideCurves = Lists.newArrayList();
			List<gp_Pnt> spline1Pnts = Lists.newArrayList();
			List<gp_Pnt> spline2Pnts = Lists.newArrayList();

			int numberOfX = 50;
			for (int i = 0; i <= numberOfX; i++) {

				double curveParameter = paramStart + (paramEnd - paramStart) / numberOfX * i;

				gp_Pnt xPnt = new gp_Pnt();
				intersection.D0(curveParameter, xPnt);
				gp_Vec direction = BRepApi.getNormalToSurfaceAtPointAlternative(face, xPnt);

				double c = -0.05;

				double u = -(xPnt.Y() * direction.Y() + xPnt.Z() * direction.Z()) * c / direction.X();
				double v = c;

//				gp_Vec tanDir = new gp_Vec(u, v * xPnt.Y(), v * xPnt.Z());
				gp_Vec tanDir = new gp_Vec(1., 0., 0.);

				double phi = (Math.PI - Math.atan2(direction.Y(), direction.Z()));
				double theta = -(Math.atan2(tanDir.Z(), tanDir.X()));
				double psi = (Math.atan2(tanDir.Y(), tanDir.X()));
//				System.out.println("Punkt: " + i + "; Psi: " + psi * 180 / Math.PI);

				gp_Ax1 rotAxis1 = new gp_Ax1(xPnt, new gp_Dir(direction));
				gp_Ax1 rotAxis2 = new gp_Ax1(xPnt, new gp_Dir(tanDir));
//				visualizeDir(rotAxis1, "normal " + i, comp);
//				visualizeDir(rotAxis2, "tangent " + i, comp);

				gp_Vec translationVec = new gp_Vec(xPnt.X(), xPnt.Y(), xPnt.Z());

				// TODO test
				Geom_Curve copy = Geom_Curve.DownCast(segment.Copy());
				copy.Translate(translationVec);
				copy.Rotate(rotAxis2, phi);
//			copy.Rotate(rotAxis1, theta);
//			copy.Rotate(rotAxis1, psi);

				Geom_Curve transformed = copy;

//				create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(transformed).Edge(), "transformed", comp);
				profileCurves.add(transformed);

				gp_Pnt spline1Node = new gp_Pnt();
				transformed.D0(transformed.FirstParameter(), spline1Node);
				spline1Pnts.add(spline1Node);

				gp_Pnt spline2Node = new gp_Pnt();
				transformed.D0(transformed.LastParameter(), spline2Node);
				spline2Pnts.add(spline2Node);
			}
			Geom_BSplineCurve spline1Curve = OCCUtils.makeBSplineFromPoints(spline1Pnts, 1e-4);
			Geom_BSplineCurve spline2Curve = OCCUtils.makeBSplineFromPoints(spline2Pnts, 1e-4);
//			create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(spline1Curve).Edge(), "guide spline 1", comp);
//			create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(spline2Curve).Edge(), "guide spline 2", comp);
			guideCurves.add(spline1Curve);
			guideCurves.add(spline2Curve);

			try {
				InterpolateCurveNetwork interpolateCurveNetwork = new InterpolateCurveNetwork(profileCurves,
						guideCurves, 1e-4);
				Geom_BSplineSurface gordonSurface = interpolateCurveNetwork.getGordonSurface();
				TopoDS_Face gordonFace = new BRepBuilderAPI_MakeFace(gordonSurface, 0.1).Face();

				create43GeometryFromTopoDSShape(gordonFace, "gordon surface segment",
						Component.create().setId("Spant segment"));
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

	}

	private void visualizeDir(gp_Ax1 axis, String name, Component parentComponent) {
		gp_Pnt startP = axis.Location();
		gp_Vec dir = new gp_Vec(axis.Direction()).Normalized().Multiplied(100);
		gp_Pnt endP = new gp_Pnt(startP.X() + dir.X(), startP.Y() + dir.Y(), startP.Z() + dir.Z());

		Geom_TrimmedCurve curve = new GC_MakeSegment(startP, endP).Value();
		create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(curve).Edge(), name, parentComponent);
	}

	private void visualizeCurve(Wire curve, String name) {
		Component comp = Component.create().setId(name);
		G g = G.create();
		comp.setShape(g);
		g.getGeometric().add(curve);
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
//		System.out.println(normalPoint.getX().getValue() + "; " + normalPoint.getY().getValue() + "; "
//				+ normalPoint.getZ().getValue());

		TransformationMatrix localMatrix = TransformationMatrix.createTransformationMatrix(transformationMatrix);

//		System.out.println("0");
		double[] position = { 0, 0, 0 };
//		System.out.println("1");
		localMatrix.setPosition(position);

		normalPoint = copyPoint(normalPoint, localMatrix);
//		System.out.println(normalPoint.getX().getValue() + "; " + normalPoint.getY().getValue() + "; "
//				+ normalPoint.getZ().getValue());

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

	private Geom_Curve wire2BSpline(TopoDS_Wire wire) {
		GeomConvert_CompCurveToBSplineCurve res = new GeomConvert_CompCurveToBSplineCurve();
		for (TopExp_Explorer explorer = new TopExp_Explorer(wire, TopAbs_ShapeEnum.TopAbs_EDGE); explorer
				.More(); explorer.Next()) {
			double[] first = new double[1];
			double[] last = new double[1];
			Geom_Curve curve = BRep_Tool.Curve(TopoDS.ToEdge(explorer.Current()), first, last);
			GeomConvert_ApproxCurve c = new GeomConvert_ApproxCurve(new Geom_TrimmedCurve(curve, first[0], last[0]),
					1e-4, GeomAbs_Shape.GeomAbs_C1, 2, 10);
			res.Add(c.Curve(), 0.001);
		}

		return res.BSplineCurve();
	}

	public static void create43GeometryFromTopoDSShape(TopoDS_Shape shape, String name, Component parentComponent) {
		Component component = Component.create();
		CustomG customG = CustomG.create((graph, func) -> shape);
		component.setId(name);
		component.setShape(customG);
		parentComponent.getSub().add(component);
	}

	private Geom_TrimmedCurve makeArc(gp_Pnt startP, gp_Pnt endP, gp_Pnt centerP, gp_Vec normal) {
		gp_Vec startToCenter = new gp_Vec(startP, centerP);
		double radius = startToCenter.Magnitude();
		gp_Vec endToCenter = new gp_Vec(endP, centerP);
		startToCenter.Cross(endToCenter);
		gp_Ax1 ax1 = new gp_Ax1(centerP, new gp_Dir(normal));
		Geom_Circle circle = new GC_MakeCircle(ax1, radius).Value();

		boolean sense;
		if (normal.X() >= 0) {
			sense = true;
		} else {
			sense = false;
		}
		Geom_TrimmedCurve arcCurve = new GC_MakeArcOfCircle(circle.Circ(), startP, endP, sense).Value();

		return arcCurve;
	}

}