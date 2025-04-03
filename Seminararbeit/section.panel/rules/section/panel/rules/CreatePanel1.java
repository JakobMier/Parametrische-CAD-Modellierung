package section.panel.rules;

import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.API.GeometryAPI;
import de.iils.dc43.core.geometry.objectgraph.Graph;
import de.iils.dc43.core.geometry.publication.Common;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.Cuboid;
import de.iils.dc43.core.geometry.publication.Cylinder;
import de.iils.dc43.core.geometry.publication.G;
import de.iils.dc43.core.geometry.publication.Loft;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Transform;
import opencascade.BRep_Tool;
import opencascade.TopoDS_Face;
import opencascade.TopoDS_Shape;
import opencascade.TopoDS_Vertex;
import opencascade.gp_Pnt;
import section.*;
import section.panel.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class CreatePanel1 extends JavaRule {

	@Override
	public void execute() throws Exception {
		Section section = getGraph().firstInstance(Section.class);
		Loft surface = getGraph().firstInstance(Loft.class);
		
		int nPanelX = section.getNPanelX();
		int nPanelZ = section.getNPanelZ();
		
		Panel[][] panel = new Panel[nPanelZ][nPanelX];
		
		for (int i = 0; i < nPanelZ; i++) {
			for (int j = 0; j < nPanelX; j++) {
				panel[i][j] = Panel.create();
				section.getPanel().add(panel[i][j]);
				panel[i][j].setXi(j);
				panel[i][j].setZi(i);
			}
		}
		
		
//		double alphaO = 45.0;
//		double l = 90.0;
//		
//		List<Common> com = new ArrayList<>();
//		
//		Cylinder cy = Cylinder.create();
//		cy.setRadius(2500.0);
//		cy.setHeight(2000.0);
//		
//		cy.setPhi(l);
//		
//		cy.setRx(1.0);
//		cy.setRy(0.0);
//		cy.setRz(0.0);
//		
//		Transform t = Transform.create();
//		
//		t.setLive(cy);
//		
//		t.setPhi(-alphaO-l);
//		
//		Common com1 = Common.create();
//		com1.getDie().add(surface);
//		com1.getDie().add(t);
//		
//	
//		
//		Component c = Component.create().setId("Cylinder");
//		c.setShape(com1);
		
		
		Set<Panel> panels = getGraph().allInstances(Panel.class);
		
		
		List<TopoDS_Face> panelFaces = new ArrayList<>();

		
		List<Common> com = new ArrayList<>();
		List<Cylinder> cyl = new ArrayList<>();
		List<Transform> t = new ArrayList<>();
		
		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h/section.getNPanelZ();
		double dl = section.getL().getValue()/section.getNPanelX();
		
		
		int i = 0;
		for (Panel p : panels) {
			
			cyl.add(Cylinder.create());
			cyl.get(i).setRadius(2500.0);
			cyl.get(i).setHeight(dl);
			cyl.get(i).setPhi(dh);
			cyl.get(i).setRx(1.0);
			cyl.get(i).setRy(0.0);
			cyl.get(i).setRz(0.0);

			t.add(Transform.create());
			t.get(i).setLive(cyl.get(i));
			
			t.get(i).setPhi(-zA-(dh*(p.getZi()+1)));
			t.get(i).setDx(dl*p.getXi());
						
			com.add(Common.create());
			com.get(i).getDie().add(t.get(i));
			com.get(i).getDie().add(surface);
			

		
			
			Component q = Component.create().setId("Ausschnitt" + i);
			q.setShape(com.get(i));
			i++;
		}
		
		
		GeometryAPI api = new GeometryAPI(getRunningDC43Project());
		Graph<TopoDS_Shape> graph = api.generateGeometry();
		
//		TopoDS_Shape shape = graph.getNode(cyl.get(0)).getRepresentation();

		
		for (Common co : com) {
			panelFaces.add(findCorrectFace(graph.getNode(co).getRepresentation()));
		}
		
		i = 0;
		for (Panel p : panels) {
			p.setSurface(panelFaces.get(i));
			i++;
		}
		
		
		int panelCounter = 0;
		for (TopoDS_Face topoDS_Face : panelFaces) {
			BRepApi.visualizeShape(topoDS_Face, "Correct Face: " + panelCounter);
			panelCounter++;
		}
		
//		panel[0][0].setSurface(panelFaces.get(0));
		
		
		// auf != 0 prüfen, sonst Absturz
//		panel[0][0].getSurface();
		
	}
	
	
	
	
	
	TopoDS_Face findCorrectFace(TopoDS_Shape shape) {
		List<TopoDS_Face> faces = BRepApi.findElements(shape, TopoDS_Face.class);
		List<Point> centerPoints = new ArrayList<>();
		TopoDS_Face correctFace = new TopoDS_Face();
		
		System.out.println(faces.size());
		
		for (TopoDS_Face topoDS_Face : faces) {
			centerPoints.add(centerPointFromFace(topoDS_Face));
		}
		
		Component c = Component.create().setId("Punkte");
		G g = G.create();
		c.setShape(g);
		
		int panelCounter = 0;
		for (Point cp : centerPoints) {
			g.getGeometric().add(cp);
			panelCounter++;
		}
		
		double yMin = centerPoints.get(0).getY().getValue();
		double yMax = centerPoints.get(0).getY().getValue();
		
		for (Point point : centerPoints) {
			if (point.getY().getValue() < yMin) {
				yMin = point.getY().getValue();
			}
			if (point.getY().getValue() > yMax) {
				yMax = point.getY().getValue();
			}
		}		
		
		if (faces.size()==5 | faces.size()==10) {
			for (int i = 0; i < faces.size(); i++) {
				if (centerPoints.get(i).getY().getValue() == yMax) {
					correctFace = faces.get(i);
				}
			}
		} else if (faces.size()==6 | faces.size()==12) {
			
			for (int i = 0; i < faces.size(); i++) {
				if (centerPoints.get(i).getY().getValue() == yMin) {
					correctFace = faces.get(i);
				}
			}
		} else {
			System.out.println("Problem mit Faces");			
		}
		
		return correctFace;
	}
	
	
	
	Point centerPointFromFace(TopoDS_Face face) {
		List<TopoDS_Vertex> points = BRepApi.findElements(face, TopoDS_Vertex.class);
		
		double avgX = 0;
		double avgY = 0;
		double avgZ = 0;
		
		int i = 0;
		for (TopoDS_Vertex ver : points) {
			gp_Pnt p = BRep_Tool.Pnt(ver);
			avgX = avgX + p.X();
			avgY = avgY + p.Y();
			avgZ = avgZ + p.Z();
			i++;			
		}
		Point p = Point.create(avgX/points.size(), avgY/points.size(), avgZ/points.size());
		return p;
	}
	
}