package section.spant.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import Jama.Matrix;
import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.TransformationMatrix;
import de.iils.dc43.core.geometry.API.GeometryAPI;
import de.iils.dc43.core.geometry.API.InterpolateCurveNetwork;
import de.iils.dc43.core.geometry.API.OCCUtils;
import de.iils.dc43.core.geometry.objectgraph.Graph;
import de.iils.dc43.core.geometry.publication.Arc;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.ControlPoint;
import de.iils.dc43.core.geometry.publication.Curve;
import de.iils.dc43.core.geometry.publication.CustomG;
import de.iils.dc43.core.geometry.publication.Direction;
import de.iils.dc43.core.geometry.publication.G;
import de.iils.dc43.core.geometry.publication.Line;
import de.iils.dc43.core.geometry.publication.Pipe;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Profile;
import de.iils.dc43.core.geometry.publication.Spline;
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

@SuppressWarnings("all")
public class CreateSpant_1 extends JavaRule {

	@Override
	public void execute() throws Exception {
		Section section = getGraph().firstInstance(Section.class);

		Set<Panel> panels = getGraph().allInstances(Panel.class);

		List<Geom_Curve> intersections = new ArrayList<>();

		double x;
		double dx = section.getL().getValue() / (section.getNSpante() * section.getNPanelX());

		for (int i = 0; i < section.getNSpante() * section.getNPanelX(); i++) {

			x = dx / 2 + i * dx;
			intersections.add(BRepApi.getIntersectionCurveInPlaneAtPoint(section.getSurface(), new gp_Pnt(x, 0, 0),
					new gp_Dir(1.0, 0, 0)));
			System.out.println("spant" + i);
			BRepApi.visualizeShape(intersections.get(i), "Spant Intersection: " + i);
		}

		List<Spline> splines = new ArrayList<>();
		for (Geom_Curve inter : intersections) {
			splines.add(BRepApi.createSplineAGFromGeom_Curve(inter, 30));
		}

		Wire definitionProfile = getGraph().firstInstance(SpantProfile.class).getProfile();
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
			bspline.Translate(new gp_Vec(0, 0, 30));
			approxSegments.add(bspline);
			comp.Add(bspline, 0.00001);
		}
		Geom_BSplineCurve profileCurve = comp.BSplineCurve();

//		profileCurve.Rotate(new gp_Ax1(new gp_Pnt(0, 0, 0), new gp_Dir(0, 0, 1)), Math.PI / 2);
//		profileCurve.Translate(new gp_Vec(0, 0, 30));

		TopoDS_Edge profileEdge = new BRepBuilderAPI_MakeEdge(profileCurve).Edge();
		create43GeometryFromTopoDSShape(profileEdge, "profile", spantComponent);

		// gordon surface (OCC direkt) Probleme durch aggregierte Kurve profileCurve.
//		createSpant(intersections.get(0), section.getSurface(), profileCurve);

		// gordon surface aus einzel segmenten -> funktioniert. TODO: Surfaces zu Solid
		// aggregieren, matching an Kanten? -> evtl. jeweils nur eine Führungskurve für
		// jeweils benachbarte Segmente.
		createSpantSegments(intersections.get(0), section.getSurface(), approxSegments, 0.3, 0.6);
//____________________________________________________________________________________________________________
	}

	private void createSpantSegments(Geom_Curve intersection, TopoDS_Face face, List<Geom_BSplineCurve> segments,
			double paramStart, double paramEnd) throws Exception {

		for (int k = 0; k < segments.size(); k++) {

			Geom_BSplineCurve segment = segments.get(k);
			List<Geom_Curve> wireCurves = Lists.newArrayList();

			Component comp = Component.create().setId("Transformed Profiles");

			List<Geom_Curve> profileCurves = Lists.newArrayList();
			List<Geom_Curve> guideCurves = Lists.newArrayList();
			List<gp_Pnt> spline1Pnts = Lists.newArrayList();
			List<gp_Pnt> spline2Pnts = Lists.newArrayList();

			int numberOfX = 50;
			for (int i = 0; i <= numberOfX; i++) {
//				double curveParameter = intersection.FirstParameter()
//						+ (intersection.LastParameter() - intersection.FirstParameter()) / numberOfX * i;

				double curveParameter = paramStart + (paramEnd - paramStart) / numberOfX * i;

				if (i == 0) {
					// Verhalten an Kante unvorhergesehen -> parameter vergrößern
					curveParameter = 0.01;
				}
				gp_Pnt xPnt = new gp_Pnt();
				intersection.D0(curveParameter, xPnt);
				gp_Vec direction = BRepApi.getNormalToSurfaceAtPointAlternative(face, xPnt);

				double c = -0.05;

				double u = -(xPnt.Y() * direction.Y() + xPnt.Z() * direction.Z()) * c / direction.X();
				double v = c;

				gp_Vec tanDir = new gp_Vec(u, v * xPnt.Y(), v * xPnt.Z());

				double phi = (Math.PI - Math.atan2(direction.Y(), direction.Z()));
				double theta = -(Math.atan2(tanDir.Z(), tanDir.X()));
				double psi = (Math.atan2(tanDir.Y(), tanDir.X()));
				System.out.println("Punkt: " + i + "; Psi: " + psi * 180 / Math.PI);

				gp_Ax1 rotAxis1 = new gp_Ax1(xPnt, new gp_Dir(direction));
				gp_Ax1 rotAxis2 = new gp_Ax1(xPnt, new gp_Dir(tanDir));
				visualizeDir(rotAxis1, "normal " + i, comp);
				visualizeDir(rotAxis2, "tangent " + i, comp);

				gp_Vec translationVec = new gp_Vec(xPnt.X(), xPnt.Y(), xPnt.Z());

				// TODO test
				Geom_Curve copy = Geom_Curve.DownCast(segment.Copy());
				copy.Translate(translationVec);
				copy.Rotate(rotAxis2, phi);
//			copy.Rotate(rotAxis1, theta);
//			copy.Rotate(rotAxis1, psi);

				Geom_Curve transformed = copy;

				create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(transformed).Edge(), "transformed", comp);
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
			create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(spline1Curve).Edge(), "guide spline 1", comp);
			create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(spline2Curve).Edge(), "guide spline 2", comp);
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

	private void createSpant(Geom_Curve intersection, TopoDS_Face face, Geom_BSplineCurve profile, double paramStart,
			double paramEnd) throws Exception {
		List<Geom_Curve> wireCurves = Lists.newArrayList();

		Component comp = Component.create().setId("Transformed Profiles");

		List<Geom_Curve> profileCurves = Lists.newArrayList();
		List<Geom_Curve> guideCurves = Lists.newArrayList();
		List<List<gp_Pnt>> splinePnts = Lists.newArrayList();
		int numberOfGuides = 2;
		for (int i = 0; i <= numberOfGuides; i++) {
			splinePnts.add(new ArrayList<>());
		}

		int numberOfX = 20;
		for (int i = 0; i <= numberOfX; i++) {
//			double curveParameter = intersection.FirstParameter()
//					+ (intersection.LastParameter() - intersection.FirstParameter()) / numberOfX * i;
			double curveParameter = paramStart + (paramEnd - paramStart) / numberOfX * i;
			if (i == 0) {
				// Verhalten an Kante unvorhergesehen -> parameter vergrößern
				curveParameter = 0.01;
			}
			gp_Pnt xPnt = new gp_Pnt();
			intersection.D0(curveParameter, xPnt);
			gp_Vec direction = BRepApi.getNormalToSurfaceAtPointAlternative(face, xPnt);

			double c = -0.05;

			double u = -(xPnt.Y() * direction.Y() + xPnt.Z() * direction.Z()) * c / direction.X();
			double v = c;

			gp_Vec tanDir = new gp_Vec(u, v * xPnt.Y(), v * xPnt.Z());

			double phi = (Math.PI - Math.atan2(direction.Y(), direction.Z()));
			double theta = -(Math.atan2(tanDir.Z(), tanDir.X()));
			double psi = (Math.atan2(tanDir.Y(), tanDir.X()));
			System.out.println("Punkt: " + i + "; Psi: " + psi * 180 / Math.PI);

			gp_Ax1 rotAxis1 = new gp_Ax1(xPnt, new gp_Dir(direction));
			gp_Ax1 rotAxis2 = new gp_Ax1(xPnt, new gp_Dir(tanDir));
			visualizeDir(rotAxis1, "normal " + i, comp);
			visualizeDir(rotAxis2, "tangent " + i, comp);

			gp_Vec translationVec = new gp_Vec(xPnt.X(), xPnt.Y(), xPnt.Z());

			// TODO test
			Geom_Curve copy = Geom_Curve.DownCast(profile.Copy());
			copy.Translate(translationVec);
			copy.Rotate(rotAxis2, phi);
//			copy.Rotate(rotAxis1, theta);
//			copy.Rotate(rotAxis1, psi);

			Geom_Curve transformed = copy;

			create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(transformed).Edge(), "transformed", comp);
			profileCurves.add(transformed);

			for (int j = 0; j <= numberOfGuides; j++) {
				double profileParameter = transformed.FirstParameter()
						+ (transformed.LastParameter() - transformed.FirstParameter()) / numberOfGuides * j;
				gp_Pnt splineNode = new gp_Pnt();
				transformed.D0(profileParameter, splineNode);
				splinePnts.get(j).add(splineNode);
			}
		}
		for (List<gp_Pnt> list : splinePnts) {
			if (!list.isEmpty()) {
				Geom_BSplineCurve splineCurve = OCCUtils.makeBSplineFromPoints(list);
				create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(splineCurve).Edge(), "guide spline", comp);
				guideCurves.add(splineCurve);
			}
		}

		InterpolateCurveNetwork interpolateCurveNetwork = new InterpolateCurveNetwork(profileCurves, guideCurves, 0.1);
		Geom_BSplineSurface gordonSurface = interpolateCurveNetwork.getGordonSurface();
		TopoDS_Face gordonFace = new BRepBuilderAPI_MakeFace(gordonSurface, 0.1).Face();

		create43GeometryFromTopoDSShape(gordonFace, "gordon surface", Component.create().setId("Spant"));
	}

	private void visualizeDir(gp_Ax1 axis, String name, Component parentComponent) {
		gp_Pnt startP = axis.Location();
		gp_Vec dir = new gp_Vec(axis.Direction()).Normalized().Multiplied(100);
		gp_Pnt endP = new gp_Pnt(startP.X() + dir.X(), startP.Y() + dir.Y(), startP.Z() + dir.Z());

		Geom_TrimmedCurve curve = new GC_MakeSegment(startP, endP).Value();
		create43GeometryFromTopoDSShape(new BRepBuilderAPI_MakeEdge(curve).Edge(), name, parentComponent);
	}

	private void createLongSpant(Spline spline, TopoDS_Face surface, Wire profileWire) throws Exception {

		List<ControlPoint> controlPoints = spline.getElement();
		gp_Vec punktRichtung = new gp_Vec();
		gp_Pnt punkt = new gp_Pnt();
		List<Wire> wires = new ArrayList<>();

		List<Geom_Curve> wireCurves = Lists.newArrayList();

		controlPoints.remove(0);

		for (ControlPoint cP : controlPoints) {
			punkt.SetX(cP.getX().getValue());
			punkt.SetY(cP.getY().getValue());
			punkt.SetZ(cP.getZ().getValue());

			punktRichtung = BRepApi.getNormalToSurfaceAtPointAlternative(surface, punkt);

			double c = -0.05;

			double u = -(punkt.Y() * punktRichtung.Y() + punkt.Z() * punktRichtung.Z()) * c / punktRichtung.X();
			double v = c;

			Point tanRichtung = Point.create();
			tanRichtung.setX(u);
			tanRichtung.setY(v * punkt.Y());
			tanRichtung.setZ(v * punkt.Z());

			Line tangentiale = Line.create();
			tangentiale.setStartPoint(cP);
			tangentiale.setEndPoint(Point.create(punkt.X() + tanRichtung.getX().getValue() * 1,
					punkt.Y() + tanRichtung.getY().getValue() * 1, punkt.Z() + tanRichtung.getZ().getValue() * 1));

			Component tanComp = Component.create().setId("cP Tangentiale");
			G tanG = G.create();
			tanComp.setShape(tanG);
			tanG.getGeometric().add(tangentiale);

			double phi = (Math.PI - Math.atan2(punktRichtung.Y(), punktRichtung.Z()));
			double theta = -(Math.atan2(tanRichtung.getZ().getValue(), tanRichtung.getX().getValue()));
			double psi = (Math.atan2(tanRichtung.getY().getValue(), tanRichtung.getX().getValue()));
			System.out.println("Punkt: " + controlPoints.indexOf(cP) + "; Psi: " + psi * 180 / Math.PI);

//			double phi = 0.;
//			double theta = 0.;
//			double psi = 0.;

			TransformationMatrix matrix = TransformationMatrix.createTransformationMatrix(punkt.X(), punkt.Y(),
					punkt.Z(), phi, theta, psi);

			Wire copyWire = copyWire(profileWire, matrix);
			wires.add(copyWire);

			Line orthogonale = Line.create();
			orthogonale.setStartPoint(cP);
			orthogonale.setEndPoint(Point.create(punkt.X() + punktRichtung.X() * 100,
					punkt.Y() + punktRichtung.Y() * 100, punkt.Z() + punktRichtung.Z() * 100));

			Component richtungComp = Component.create().setId("cP Orthogonale");
			G richtungG = G.create();
			richtungComp.setShape(richtungG);
			richtungG.getGeometric().add(orthogonale);
		}

		List<ControlPoint> firstSplinePoints = new ArrayList<>();
		for (Wire w : wires) {
			Curve curve = w.getElement().get(0);

			if (curve instanceof Line) {
				Line line = (Line) curve;
				Point point = line.getStartPoint();
				firstSplinePoints.add(
						ControlPoint.create(point.getX().getValue(), point.getY().getValue(), point.getZ().getValue()));
			} else if (curve instanceof Arc) {
				Arc arc = (Arc) curve;
				Point point = arc.getStartPoint();
				firstSplinePoints.add(
						ControlPoint.create(point.getX().getValue(), point.getY().getValue(), point.getZ().getValue()));
			}
		}

		List<ControlPoint> secondSplinePoints = new ArrayList<>();
		for (Wire w : wires) {
			Curve curve = w.getElement().get(5);

			if (curve instanceof Line) {
				Line line = (Line) curve;
				Point point = line.getStartPoint();
				secondSplinePoints.add(
						ControlPoint.create(point.getX().getValue(), point.getY().getValue(), point.getZ().getValue()));
			} else if (curve instanceof Arc) {
				Arc arc = (Arc) curve;
				Point point = arc.getStartPoint();
				secondSplinePoints.add(
						ControlPoint.create(point.getX().getValue(), point.getY().getValue(), point.getZ().getValue()));
			}
		}

		Spline firstSpline = Spline.create().setStart(firstSplinePoints.get(0));
		for (int i = 0; i < firstSplinePoints.size() - 1; i++) {
			firstSplinePoints.get(i).getNextControlPoint().add(firstSplinePoints.get(i + 1));
		}
		for (ControlPoint controlPoint : firstSplinePoints) {
			firstSpline.getElement().add(controlPoint);
		}

		Spline secondSpline = Spline.create().setStart(secondSplinePoints.get(0));
		for (int i = 0; i < secondSplinePoints.size() - 1; i++) {
			secondSplinePoints.get(i).getNextControlPoint().add(secondSplinePoints.get(i + 1));
		}
		for (ControlPoint controlPoint : secondSplinePoints) {
			secondSpline.getElement().add(controlPoint);
		}

		Component pointsComp = Component.create().setId("Spline");
		G pointsG = G.create();
		pointsComp.setShape(pointsG);
		pointsG.getGeometric().add(firstSpline);
		pointsG.getGeometric().add(secondSpline);

		// TODO unnötig? grundsätzlich Geometrie mehr strukturieren (Component
		// Schachtelung mit sprechenden Namen, entfernen unnötiger Darstellungen)
//		for (ControlPoint controlPoint : firstSplinePoints) {
//			pointsG.getGeometric().add(controlPoint);
//		}
//		for (ControlPoint controlPoint : secondSplinePoints) {
//			pointsG.getGeometric().add(controlPoint);
//		}

//		for (Wire wire2 : wires) {
//			visualizeCurve(wire2, "TEST");
//		}

//		Loft loft = Loft.create().setStart(wires.get(0));

		visualizeCurve(wires.get(0), "PIPE PROFILE");

		Pipe pipe = Pipe.create();
		pipe.setProfile(wires.get(0));
		pipe.setSpine(firstSpline);
		Component.create().setId("PIPE TEST").setShape(pipe);

		GeometryAPI geometryAPI = new GeometryAPI(getRunningDC43Project());
		Graph<TopoDS_Shape> graph = geometryAPI.generateGeometry();

//		List<Geom_Curve> profileCurves = Lists.newArrayList();
//		List<Geom_Curve> guideCurves = Lists.newArrayList();
//
//		for (int i = 0; i < wires.size(); i++) {
//			System.out.println("wire " + i);
//			Wire w = wires.get(i);
//			List<Curve> element = w.getElement();
//			for (Curve curve : element) {
//				TopoDS_Shape shape = RepresentationManager.getInstance().getRepresentation(curve).getTopoDS();
//				System.out.println(shape.ShapeType());
//			}
//			visualizeCurve(w, "spantProfile" + i);
//			Representation representation = RepresentationManager.getInstance().getRepresentation(w);
//			TopoDS_Shape shape = representation.getTopoDS();
//
//			TopExp_Explorer explorer = new TopExp_Explorer(shape, TopAbs_ShapeEnum.TopAbs_WIRE);
//			while (explorer.More()) {
//				System.out.println("wire");
//				TopoDS_Shape current = explorer.Current();
//				explorer.Next();
//
//			}
//			System.out.println(shape.ShapeType());
//			Node<TopoDS_Shape> node = graph.getNode(w);
//			TopoDS_Shape shape = node.getRepresentation();
//			GeomObject data = node.getData();
//			TopoDS_Shape shape = graph.getRepresentation(data);
//			if (shape.ShapeType() == TopAbs_ShapeEnum.TopAbs_WIRE) {
//				System.out.println("a");
//				Geom_Curve profileSpline = wire2BSpline((TopoDS_Wire) shape);
//				profileCurves.add(profileSpline);
//			}

//			loft.getElement().add(wires.get(i));
//			if (i < wires.size()-1) {
//				wires.get(i).getNext().add(wires.get(i + 1));
//			}
//	}
//
//		TopoDS_Shape guide1SplineShape = RepresentationManager.getInstance().getRepresentation(firstSpline).getTopoDS();
//		if (guide1SplineShape.ShapeType() == TopAbs_ShapeEnum.TopAbs_WIRE) {
//			System.out.println("spline wire");
//			Geom_Curve guideCurve = wire2BSpline((TopoDS_Wire) guide1SplineShape);
//			guideCurves.add(guideCurve);
//		}
//
//		TopoDS_Shape guide2SplineShape = RepresentationManager.getInstance().getRepresentation(secondSpline)
//				.getTopoDS();
//		if (guide2SplineShape.ShapeType() == TopAbs_ShapeEnum.TopAbs_WIRE) {
//			System.out.println("spline wire");
//			Geom_Curve guideCurve = wire2BSpline((TopoDS_Wire) guide2SplineShape);
//			guideCurves.add(guideCurve);
//		}

//		InterpolateCurveNetwork interpolateCurveNetwork = new InterpolateCurveNetwork(profileCurves, guideCurves, 1E-6);
//		Geom_BSplineSurface gordon = interpolateCurveNetwork.getGordonSurface();
//		TopoDS_Face face = new BRepBuilderAPI_MakeFace(gordon, 0.01).Face();

//		loft.getGuides().add(firstSpline);
//		loft.getGuides().add(secondSpline);

//		List<Geometric> guides = new ArrayList<>();
//		guides.add(firstSpline);
//		guides.add(secondSpline);
//
//		List<Geometric> geoWires = new ArrayList<>();
//		for (Geometric w : wires) {
//			geoWires.add((Geometric) wire);
//		}
//
//		Loft loft = BRepApi.createGordonLoft(geoWires, guides);
//
//		Component comp = Component.create().setId("Spant");
//		create43GeometryFromTopoDSShape(face, "gordon", comp);
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