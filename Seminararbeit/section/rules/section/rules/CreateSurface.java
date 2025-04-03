package section.rules;

import java.util.Iterator;

import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.API.GeometryAPI;
import de.iils.dc43.core.geometry.objectgraph.Graph;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.Loft;
import de.iils.dc43.core.geometry.publication.Spline;
import opencascade.TopoDS_Face;
import opencascade.TopoDS_Shape;
import section.Section;
import section.SectionProfile;

@SuppressWarnings("all")
public class CreateSurface extends JavaRule {

	@Override
	public void execute() throws Exception {

		Iterator<SectionProfile> iterator = getGraph().allInstances(SectionProfile.class).iterator();

		SectionProfile profile_A = iterator.next();
		SectionProfile profile_B = iterator.next();

		Spline spline_1 = null;
		Spline spline_2 = null;

		if (profile_A.getI() == 1) {
			spline_1 = profile_A.getProfile();
			spline_2 = profile_B.getProfile();
		} else {
			spline_1 = profile_B.getProfile();
			spline_2 = profile_A.getProfile();
		}

		spline_1.getNext().add(spline_2);
		Loft surface = Loft.create().setStart(spline_1);
		surface.getElement().add(spline_1);
		surface.getElement().add(spline_2);

		Section section = getGraph().firstInstance(Section.class);
		Component c = Component.create().setId("0_Section Surface");
		c.setShape(surface);

		GeometryAPI api = new GeometryAPI(getRunningDC43Project());
		Graph<TopoDS_Shape> graph = api.generateGeometry();

		TopoDS_Shape surface_TopoDS = graph.getNode(surface).getRepresentation();

		TopoDS_Face face = BRepApi.findElements(surface_TopoDS, TopoDS_Face.class).get(0);

		section.setSurface(face);
	}

}