package section.skin.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.API.GeometryAPI;
import de.iils.dc43.core.geometry.objectgraph.Graph;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.Cylinder;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Transform;
import opencascade.BRep_Tool;
import opencascade.TopoDS_Face;
import opencascade.TopoDS_Shape;
import opencascade.TopoDS_Vertex;
import opencascade.gp_Pnt;
import section.*;
import section.Skin;
import section.skin.*;

@SuppressWarnings("all")
public class CreateSkin extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);

		TopoDS_Face surface = section.getSurface();

		TopoDS_Shape surface_thick = BRepApi.thickenShape(surface, -2.0);

		// Checks if skin thickens in correct direction. If it does not, the whole graph
		// gets deleted and the program runs again from the beginning.
		Point middlePoint_surface = centerPointFromFace(surface);
		Point middlePoint_thickSurface = centerPointFromShape(surface_thick);

		if (middlePoint_thickSurface.getY().getValue() < middlePoint_surface.getY().getValue()) {
			int loopCounter = 0;
			RunControl runControl = getGraph().firstInstance(RunControl.class);

			if (runControl == null) {
				loopCounter = 1;
			} else {
				loopCounter = runControl.getLoopCounter().intValue() + 1;
			}
			if (loopCounter < 10) { // for safety
				getGraph().clear();

				RunControl r = RunControl.create();
				r.setLoopCounter(loopCounter);
				System.out.println(
						"****************************** FAIL NEXT LOOP ******************************: " + loopCounter);
				throw new Exception("Wrong extrusion direction. Trying new run.");
			}
		}

		Set<Panel> panels = getGraph().allInstances(Panel.class);

		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h / section.getNPanelZ(); // "Height" of a panel or skin in degree
		double dl = section.getL().getValue() / section.getNPanelX(); // Lenght of a panel or skin in mm

		Component comp_help = Component.create().setId("0_Skins");

		List<Cylinder> cylinder_skin = new ArrayList<>();
		List<Transform> transformedCylinder_skin = new ArrayList<>();

		int i = 0;
		for (Panel p : panels) {
			cylinder_skin.add(Cylinder.create());
			cylinder_skin.get(i).setRadius(10000.0);
			cylinder_skin.get(i).setHeight(dl);
			cylinder_skin.get(i).setPhi(dh);
			cylinder_skin.get(i).setRx(1.0);
			cylinder_skin.get(i).setRy(0.0);
			cylinder_skin.get(i).setRz(0.0);

			transformedCylinder_skin.add(Transform.create());
			transformedCylinder_skin.get(i).setLive(cylinder_skin.get(i));

			transformedCylinder_skin.get(i).setPhi(-180.0 + zA + (dh * (p.getZi())));

			transformedCylinder_skin.get(i).setDx(dl * p.getXi());

			Component comp_cut = Component.create().setId("Cutout: " + i);
			comp_help.getSub().add(comp_cut);
			comp_cut.setShape(transformedCylinder_skin.get(i));
			i++;
		}

		GeometryAPI api = new GeometryAPI(getRunningDC43Project());
		Graph<TopoDS_Shape> graph = api.generateGeometry();

		List<TopoDS_Shape> transformedCylinder_skin_TOPODS = new ArrayList<>();

		for (Transform transform : transformedCylinder_skin) {
			transformedCylinder_skin_TOPODS.add(graph.getNode(transform).getRepresentation());
		}

		List<TopoDS_Shape> skins = new ArrayList<>();

		for (TopoDS_Shape tr : transformedCylinder_skin_TOPODS) {
			skins.add(BRepApi.getIntersection_(tr, surface_thick));
		}

		Component comp_skins = Component.create();

		i = 0;
		for (Panel p : panels) {
			Skin skin = Skin.create().setId("Skin (" + p.getZi() + "," + p.getXi() + ")");
			p.setSkin(skin);
			comp_skins.getSub().add(skin);
			skin.getSub().add(BRepApi.visualizeShape(skins.get(i)));
			i++;
		}

		comp_skins.setId("Skins (" + comp_skins.getSub().size() + ")");

	}

	/////////////////////// FUNCTIONS ////////////////////////////////

	// This Function calculates the average position of all points of a given shape
	Point centerPointFromShape(TopoDS_Shape shape) {
		List<TopoDS_Vertex> points = BRepApi.findElements(shape, TopoDS_Vertex.class);

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
		Point p = Point.create(avgX / points.size(), avgY / points.size(), avgZ / points.size());
		return p;
	}

	// This Function calculates the average position of all points of a given face
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
		Point p = Point.create(avgX / points.size(), avgY / points.size(), avgZ / points.size());
		return p;
	}

}