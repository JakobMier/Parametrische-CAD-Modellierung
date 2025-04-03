package section.skin.rules;

import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.API.GeometryAPI;
import de.iils.dc43.core.geometry.objectgraph.Graph;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.ControlPoint;
import de.iils.dc43.core.geometry.publication.G;
import de.iils.dc43.core.geometry.publication.Loft;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Spline;
import opencascade.TopoDS_Face;
import opencascade.TopoDS_Shape;
import section.*;
import section.skin.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.ISymbol;
import org.matheclipse.parser.client.SyntaxError;
import org.matheclipse.parser.client.math.MathException;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Skin2 extends JavaRule {

	@Override
	public void execute() throws Exception {
		Set<Panel> panel = getGraph().allInstances(Panel.class);
		
		List<TopoDS_Shape> skins = new ArrayList<>();
		
		GeometryAPI api = new GeometryAPI(getRunningDC43Project());
		Graph<TopoDS_Shape> graph = api.generateGeometry();
		
		
		for (Panel f : panel) {
			
			System.out.println(f.getSurface());
			
			skins.add(BRepApi.thickenShape(f.getSurface(), 2.0));
		}
		
		int i = 0;
		for (TopoDS_Shape s : skins) {
			BRepApi.visualizeShape(s,"Skin: " + i);
			i++;
		}
	}

}