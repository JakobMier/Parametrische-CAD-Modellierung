package section.skin.rules;

import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.API.GeometryAPI;
import de.iils.dc43.core.geometry.objectgraph.Graph;
import de.iils.dc43.core.geometry.publication.Circle;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.ControlPoint;
import de.iils.dc43.core.geometry.publication.Direction;
import de.iils.dc43.core.geometry.publication.G;
import de.iils.dc43.core.geometry.publication.Loft;
import de.iils.dc43.core.geometry.publication.Pipe;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Spline;
import section.*;
import section.skin.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import opencascade.Geom_Curve;
import opencascade.Geom_Plane;
import opencascade.TopoDS_Face;
import opencascade.TopoDS_Shape;
import opencascade.TopoDS_Solid;
import opencascade.gp_Dir;
import opencascade.gp_Pnt;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.ISymbol;
import org.matheclipse.parser.client.SyntaxError;
import org.matheclipse.parser.client.math.MathException;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Skin extends JavaRule {

	@Override
	public void execute() throws Exception {
		try {
		System.out.println("Java Skin");
		
		ExprEvaluator util = new ExprEvaluator();
		Section section =getGraph().firstInstance(Section.class);
		
		System.out.println("r1: " + section.getR1a().getValue());
		
		
//		Point testPoint1 = Point.create(5.,5.,5.);
//		Component comp = Component.create().setId("compLine");
//		G g = G.create();
//		comp.setShape(g);
//		g.getGeometric().add(testPoint1);
		
		
		IExpr eikurve;
		
		ISymbol r1 = F.Dummy("R1");
		ISymbol r2 = F.Dummy("R2");
		ISymbol h = F.Dummy("H");
		ISymbol z = F.Dummy("z");
		
		
		System.out.println("R1: " + section.getR1a().getValue());
		System.out.println("R2: " + section.getR2a().getValue());
		System.out.println("H: " + section.getHA().getValue());
		
		
		eikurve = util.eval("R1=" + section.getR1a().getValue());
		eikurve = util.eval("R2=" + section.getR2a().getValue());
		eikurve = util.eval("H=" + section.getHA().getValue());
		
		Component c = Component.create().setId("compLine");
		G g = G.create();
		c.setShape(g);
		
		
		
		
		
		
		
		//First spline
		
		List<ControlPoint> cP1 = new ArrayList<>();
		double u;
		double z1 = -section.getR1a().getValue();
		int n = 20;
				
		for (int i = 0; i <= n; i++) {
		
			u = z1 - i*z1*2/n;
			
			eikurve = util.eval("z=" + u);
			
			System.out.println("z= " + eikurve.toString());
			
			eikurve = util.eval("(sqrt((R1^2*R2^2 - R2^2*z^2)/(R1^2 + H^2 + 2*H*z)))");
			cP1.add(ControlPoint.create(0.,eikurve.getReal(),u));
			g.getGeometric().add(cP1.get(i));
		}
		
		for (int i = 0; i < cP1.size()-1; i++) {
			cP1.get(i).getNextControlPoint().add(cP1.get(i+1));
		}
		
		Spline spline1 = Spline.create().setStart(cP1.get(0));
		for (int i = 0; i < cP1.size(); i++) {
			spline1.getElement().add(cP1.get(i));
		}
		
		g.getGeometric().add(spline1);
				

		//Second spline
		
//		eikurve = util.eval("R1=" + 1500);
//		eikurve = util.eval("R2=" + 1500);
//		eikurve = util.eval("H=" + 1500);
		
		
		List<ControlPoint> cp2 = new ArrayList<>();
				
		for (int i = 0; i <= n; i++) {
		
			u = z1 - i*z1*2/n;
			
			eikurve = util.eval("z=" + u);
			
			System.out.println("z= " + eikurve.toString());
			
			eikurve = util.eval("(sqrt((R1^2*R2^2 - R2^2*z^2)/(R1^2 + H^2 + 2*H*z)))");
			cp2.add(ControlPoint.create(5000.,eikurve.getReal(),u));
			g.getGeometric().add(cp2.get(i));
		}
		
		for (int i = 0; i < cp2.size()-1; i++) {
			cp2.get(i).getNextControlPoint().add(cp2.get(i+1));
		}
		
		Spline spline2 = Spline.create().setStart(cp2.get(0));
		for (int i = 0; i < cp2.size(); i++) {
			spline2.getElement().add(cp2.get(i));
		}
		
		g.getGeometric().add(spline2);
		
		//Loft
		
		spline1.getNext().add(spline2);
		
		Loft surface = Loft.create().setStart(spline1);
		surface.getElement().add(spline1);
		surface.getElement().add(spline2);
		c.setShape(surface);
		
		GeometryAPI api = new GeometryAPI(getRunningDC43Project());
		Graph<TopoDS_Shape> graph = api.generateGeometry();

		// das Ergebnis was hier von dem Loft zurück kam, war kein TopoDS_Face sondern ein TopoDS_Solid
		// das führte auch dazu, dass "thickenShape" nicht korrekt ausgeführt werden konnte
		TopoDS_Shape surfaceTopoDS = graph.getNode(surface).getRepresentation();
		
		// da surfaceTopoDS ein Solid ist, der aus mehreren Flächen zusammengesetzt ist, müssen wir uns aus dem Solid zunächst alle Flächen holen
		// das machen wir über die Funktion "findElements" indem wir dort angeben, dass wir nur TopoDS_Faces von surfaceTopoDS haben wollen
		// als Ergebnis erhalten wir eine Liste mit den Faces im Solid surfaceTopoDS
		List<TopoDS_Face> faces = BRepApi.findElements(surfaceTopoDS, TopoDS_Face.class);

		// das erste Element in dieser Liste ist die gesuchte Fläche, die die Innenseite der Haut repräsentiert
		TopoDS_Face innerSkinFace = faces.get(0);
		
		// diese übergeben wir an die Funktion "thickenShape" um sie aufzudicken
		// als Ergebnis erhalten wir einen Volumenkörper, den wir uns als "outerSkinVolume" visualisieren
		TopoDS_Shape thickSurface = BRepApi.thickenShape(innerSkinFace, 2.0);
		BRepApi.visualizeShape(thickSurface,"outerSkinVolume");
		
		// jetzt definieren wir die Ebene, mit der wir die Innenseite der Haut schneiden wollen über einen Punkt und eine Richtung
		gp_Pnt basePoint = new gp_Pnt(0,0,0);
		gp_Dir baseDir = new gp_Dir(0,0,1);
		
		// mit der Funktion "getIntersectionCurveInPlaneAtPoint" erhalten wir die Schnittkurve von innerSkinFace und der Ebene als Geom_Curve
		Geom_Curve intersectionCurve = BRepApi.getIntersectionCurveInPlaneAtPoint(innerSkinFace, basePoint, baseDir);
		
		// um auf abstrakter Geometrie Ebene mit der Schnittkurve arbeiten zu können, 
		// müssen wir sie in einen "Spline" aus der abstrakten Geometrie umwandeln.
		// dazu müssen wir eine Anzahl an Punkten angeben, durch die der Spline aus der Geom_Curve rekonstruiert wird (hier 10)
		Spline intersectionCurveAsSpline = BRepApi.createSplineAGFromGeom_Curve(intersectionCurve, 10);
		G intersectionCurveG = G.create();
		intersectionCurveG.getGeometric().add(intersectionCurveAsSpline);
		Component intersectionSpline = Component.create().setId("intersectionSpline");
		intersectionSpline.setShape(intersectionCurveG);
		
		// jetzt wird der Stringer als Pipe in abstrakter Geometrie erstellt
		// ein Teil des Grundprofils (Wires) wird ja durch die Außenhaut vorgegeben (damit sich der Stringer genau an die Haut "anschmiegt") 
		// du musst dir überlegen, wie du diese Kontur (das ist ja ein Abschnitt der Eikurve) ableiten kannst (analytisch berechnen oder über Schnitt(e) oder ...)
		// für das Beispiel nehmen wir jetzt als Grundprofil hier einen Kreis
		// als Spine setzen wir intersectionCurveAsSpline von oben
		Circle circle = Circle.create().setRadius(10.);
		Direction normalCircle = Direction.create(1.,0.,0.);
		circle.setNormal(normalCircle);
		// wir nehmen als Basispunkt des Profils den ersten Punkt des Führungssplines (intersectionCurveAsSpline)
		circle.setCentre(intersectionCurveAsSpline.getElement().get(0));
		Pipe pipe = Pipe.create();
		pipe.setSpine(intersectionCurveAsSpline);
		pipe.setProfile(circle);
		Component stringerComp = Component.create().setId("Stringer");
		stringerComp.setShape(pipe);
		
		} catch (SyntaxError e) {
			// catch Symja parser errors here
			System.out.println(e.getMessage());
		} catch (MathException me) {
			// catch Symja math errors here
			System.out.println(me.getMessage());
		} catch (final Exception ex) {
			System.out.println(ex.getMessage());
		} catch (final StackOverflowError soe) {
			System.out.println(soe.getMessage());
		} catch (final OutOfMemoryError oome) {
			System.out.println(oome.getMessage());
		}
	}

}