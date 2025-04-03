package section.rivet.rules;

import java.util.List;
import java.util.Set;

import de.ifb.pigroup.geometry.brep.elements.BRepApi;
import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.publication.Add;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.Cylinder;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.Sphere;
import de.iils.dc43.core.geometry.publication.Transform;
import opencascade.BRep_Tool;
import opencascade.Geom_Curve;
import opencascade.TopoDS_Face;
import opencascade.gp_Pnt;
import opencascade.gp_Vec;
import section.ButtStrap;
import section.Clip;
import section.Panel;
import section.Rivet;
import section.Section;
import section.SpantProfile;
import section.StringerCoupling;
import section.StringerProfile;

@SuppressWarnings("all")
public class CreateRivets extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);

		TopoDS_Face surface = section.getSurface();

		double dl = section.getL().getValue() / section.getNPanelX();
		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h / section.getNPanelZ();

		Component comp_rivets = Component.create().setId("Rivets");

		// Rivets for Stringer Coupling

		// Number of rivets
		int n = 10; // Must be uneven number, so there is no rivet in the middle

		// Distance between rivets
		double distance = section.getStringerCouplingLenght().getValue() / n;

		if (true) {
			for (int i = 0; i < section.getNPanelZ() * section.getNStringer(); i++) {
				for (int j = 0; j < section.getNPanelX() - 1; j++) {
					for (int j2 = 0; j2 < n; j2++) {

						double x = (j + 1) * dl - section.getStringerCouplingLenght().getValue() / 2 + distance / 2
								+ j2 * distance;

						double angle = 180. - (zA + (0.5 + i) * dh / section.getNStringer()); // Position der Stringer
																								// Couplings

						Rivet rivet = Rivet.create().setId("Rivet Stringer Coupling");

						// TODO Kommentar zu den eingaben
						rivet.setShape(createRivetAt(surface, x, angle, 6., new gp_Pnt(0., -15., -2.), 0., 0., 0.));

						rivet.setStringerCoupling(searchCoupling(j, i));
						comp_rivets.getSub().add(rivet);

						if (Math.abs(x - (j + 1) * dl) > section.getButtStrapWidth().getValue() / 2) {

							rivet = Rivet.create().setId("Rivet Stringer Coupling");

							rivet.setShape(createRivetAt(surface, x, angle, 4., new gp_Pnt(0., -30., 30), 90., 0., 0.));

							rivet.setStringerCoupling(searchCoupling(j, i));
							comp_rivets.getSub().add(rivet);

						}
					}
				}
			}
		}

		// Rivets for Vertical Butt Strap

		int n_rows_vertical = section.getNRivetRowsBS_v(); // Number of rivet rows
		int n_columns_vertical = section.getNRivetColumnsBS_v() / 2; // Number of rivet columns *0.5 because of symmetry
		distance = 2.; // Distance between rivets and stringer couplings in degree

		double dAngle = (dh / (2 * section.getNStringer()) - distance) / n_rows_vertical;

		double dx = section.getButtStrapWidth().getValue() / (2 * n_columns_vertical);

		if (true) {
			for (int i = 0; i < section.getNPanelX() - 1; i++) {
				double x0 = (i + 1) * dl;
				for (int j1 = 0; j1 < section.getNPanelZ(); j1++) {
					for (int j2 = 0; j2 < section.getNStringer(); j2++) {

						int j = j1 * section.getNStringer() + j2;

						double stringerAngle = 180. - (zA + (0.5 + j) * dh / section.getNStringer());
						for (int k = 0; k < n_rows_vertical; k++) {
							for (int k2 = 0; k2 < n_columns_vertical; k2++) {

								Rivet rivet = Rivet.create().setId("Rivet vertical ButtStrap");
								rivet.setShape(createRivetAt(surface, x0 + (0.5 + k2) * dx,
										stringerAngle + (distance + dAngle * (0.5 + k)), 4., new gp_Pnt(0., 0., -2.),
										0., 0., 0.));
								comp_rivets.getSub().add(rivet);
								rivet.setButtStrap(searchButtStrap(i, j1, "vertical"));

								rivet = Rivet.create().setId("Rivet vertical ButtStrap");
								rivet.setShape(createRivetAt(surface, x0 - (0.5 + k2) * dx,
										stringerAngle - (distance + dAngle * (0.5 + k)), 4., new gp_Pnt(0., 0., -2.),
										0., 0., 0.));
								comp_rivets.getSub().add(rivet);
								rivet.setButtStrap(searchButtStrap(i, j1, "vertical"));

								rivet = Rivet.create().setId("Rivet vertical ButtStrap");
								rivet.setShape(createRivetAt(surface, x0 - (0.5 + k2) * dx,
										stringerAngle + (distance + dAngle * (0.5 + k)), 4., new gp_Pnt(0., 0., -2.),
										0., 0., 0.));
								comp_rivets.getSub().add(rivet);
								rivet.setButtStrap(searchButtStrap(i, j1, "vertical"));

								rivet = Rivet.create().setId("Rivet vertical ButtStrap");
								rivet.setShape(createRivetAt(surface, x0 + (0.5 + k2) * dx,
										stringerAngle - (distance + dAngle * (0.5 + k)), 4., new gp_Pnt(0., 0., -2.),
										0., 0., 0.));
								comp_rivets.getSub().add(rivet);
								rivet.setButtStrap(searchButtStrap(i, j1, "vertical"));

							}
						}
					}
				}
			}
		}

		// Rivets for Horizontal Butt Straps

		int n_rows_horizontal = section.getNRivetRowsBS_h(); // Number of rivet rows (must be an even number for
																// symmetry)
		int n_columns_horizontal = section.getNRivetColumnsBS_h(); // Number of rivet columns

		if (true) {
			for (int j = 0; j < section.getNPanelZ() - 1; j++) {
				double angle0 = 180. - (zA + (j + 1) * dh);
				for (int j2 = 0; j2 < section.getNPanelX(); j2++) {
					double x0 = j2 * dl;
					for (int l = 0; l < n_rows_horizontal; l++) {

						for (int l2 = 0; l2 < n_columns_horizontal; l2++) {

							double x = x0 + (0.5 + l2) * dl / n_columns_horizontal;

							Point point_A = Point.create();
							point_A.setX(x);
							point_A.setY(0.);
							point_A.setZ(0.);

							Point point_B = Point.create();
							point_B.setX(x);
							point_B.setY(Math.sin(angle0 / 180. * Math.PI) * 5000);
							point_B.setZ(Math.cos(angle0 / 180. * Math.PI) * 5000);

							Geom_Curve line = BRepApi.createLine(point_A, point_B);

							gp_Pnt pnt = BRepApi
									.getIntersectionPointCurveSurface(line, BRep_Tool.Surface(section.getSurface()))
									.get(0);
							gp_Vec vec = BRepApi.getNormalToSurfaceAtPointAlternative(section.getSurface(), pnt);

							double width_buttStrap = section.getButtStrapWidth().getValue();
							double dA = width_buttStrap / n_rows_horizontal;

							double angle = (Math.atan2(pnt.Y() + ((0.5 + l) * dA - width_buttStrap / 2) * vec.Z(),
									pnt.Z() + ((0.5 + l) * dA - width_buttStrap / 2) * vec.Y())) * 180. / Math.PI;

							Rivet rivet = Rivet.create().setId("Rivet horizontal ButtStrap");
							rivet.setShape(createRivetAt(surface, x, angle, 4., new gp_Pnt(0., 0., -2.), 0., 0., 0.));
							comp_rivets.getSub().add(rivet);
							rivet.setButtStrap(searchButtStrap(j2, j, "horizontal"));
						}
					}
				}
			}
		}

		// Rivets for Clips
		if (true) {
			int n_rows_clipFloor = section.getNRivetClip_floor(); // Number of rivet row on clip floor
			int n_columns_clipFloor = 1; // Number of rivet columns on clip floor

			int n_wall1_vertical = section.getNRivetClip_wall1_v(); // Number of rivets connecting clip with spant in
																	// vertical order
			double dh_vertical = (getGraph().firstInstance(SpantProfile.class).getHeightForClip().getValue() - 10
					- getGraph().firstInstance(StringerProfile.class).getHeight().getValue()) / n_wall1_vertical;

			int n_wall1_horizontal = section.getNRivetClip_wall1_h(); // Number of rivets connecting clip with spant in
																		// horizontal order
			double dh_horizontal = (section.getClipFloorLenght().getValue()) / n_wall1_horizontal;

			int n_wall2 = section.getNRivetClip_wall2(); // Number of rivets connecting clip with stringer

			for (int i1 = 0; i1 < section.getNPanelZ(); i1++) {
				for (int i2 = 0; i2 < section.getNStringer(); i2++) {
					int i = i1 * section.getNStringer() + i2;
					for (int j1 = 0; j1 < section.getNPanelX(); j1++) {
						for (int j2 = 0; j2 < section.getNSpante(); j2++) {
							int j = j1 * section.getNSpante() + j2;

							double angle0 = 180.
									- (zA + (dh / section.getNStringer()) / 2 + i * (dh / section.getNStringer()));
							double x0 = (0.5 + j) * (dl / section.getNSpante());

							// Rivets on clip floor
							for (int k = 0; k < n_columns_clipFloor; k++) {
								for (int k2 = 0; k2 < n_rows_clipFloor; k2++) {

									double x = x0 + (0.5 + k)
											* (section.getClipFloorWidth().getValue() / n_columns_clipFloor);
									double angle = angle0
											- (0.5 + k2) * (section.getClipFloorLenght().getValue() / n_rows_clipFloor);

									Rivet rivet = Rivet.create().setId("Rivet Clip");
									rivet.setShape(
											createRivetAt(surface, x, angle, 5., new gp_Pnt(0., 0., -2.), 0., 0., 0.));
									comp_rivets.getSub().add(rivet);
									rivet.setClip(searchClip(searchPanel(j1, i1), j2, i2));
									comp_rivets.getSub().add(rivet);

								}
							}

							// Rivets on first wall
							for (int k = 0; k < n_wall1_vertical; k++) {

								double h_rivet = getGraph().firstInstance(SpantProfile.class).getHeightForClip()
										.getValue() - (0.5 + k) * dh_vertical;

								Rivet rivet = Rivet.create().setId("Rivet Clip");
								rivet.setShape(createRivetAtAlternative(section, x0, angle0, 4.,
										new gp_Pnt(-2., -25, h_rivet), 0., 0., 90.));
								rivet.setClip(searchClip(searchPanel(j1, i1), j2, i2));
								comp_rivets.getSub().add(rivet);

							}
							for (int k = 0; k < n_wall1_horizontal; k++) {

								double angle = angle0 - (0.5 + k) * dh_horizontal;

								Rivet rivet = Rivet.create().setId("Rivet Clip");
								rivet.setShape(createRivetAtAlternative(section, x0, angle, 4., new gp_Pnt(-2., 0.,
										getGraph().firstInstance(StringerProfile.class).getHeight().getValue() + 10),
										0., 0., 90.));
								rivet.setClip(searchClip(searchPanel(j1, i1), j2, i2));
								comp_rivets.getSub().add(rivet);

							}

							// Rivets on second wall
							for (int k = 0; k < n_wall2; k++) {

								double h_rivet = getGraph().firstInstance(StringerProfile.class).getHeight().getValue()
										- (0.5 + k) * (getGraph().firstInstance(StringerProfile.class).getHeight()
												.getValue() / n_wall2);

								Rivet rivet = Rivet.create().setId("Rivet Clip");
								rivet.setShape(createRivetAtAlternative(section, x0, angle0, 4.,
										new gp_Pnt(10., -30, h_rivet), 90., 0., 0.));
								rivet.setClip(searchClip(searchPanel(j1, i1), j2, i2));
								comp_rivets.getSub().add(rivet);

							}
						}
					}
				}
			}
		}

		comp_rivets.setId("Rivets (" + comp_rivets.getSub().size() + ")");

	}

	//////////////////// FUNCTIONS //////////////////////

	// Find clip with index numbers
	public Clip searchClip(Panel panel, int xi, int zi) {
		List<Clip> clips = panel.getClip();
		for (Clip clip : clips) {
			if (clip.getXi() == xi && clip.getZi() == zi) {
				return clip;
			}
		}
		return null;
	}

	// Find panel with index numbers
	public Panel searchPanel(int xi, int zi) {
		Set<Panel> panels = getGraph().allInstances(Panel.class);
		for (Panel panel : panels) {
			if (panel.getXi() == xi && panel.getZi() == zi) {
				return panel;
			}
		}
		return null;
	}

	// Find stringerCoupling with index numbers
	public StringerCoupling searchCoupling(int xi, int zi) {
		Set<StringerCoupling> couplings = getGraph().allInstances(StringerCoupling.class);
		for (StringerCoupling coup : couplings) {
			if (coup.getXi() == xi && coup.getZi() == zi) {
				return coup;
			}
		}
		return null;
	}

	// Find buttStrap with index numbers and orientation "horizontal" or "vertical"
	public ButtStrap searchButtStrap(int xi, int zi, String orientation) {
		Set<ButtStrap> buttStraps = getGraph().allInstances(ButtStrap.class);
		for (ButtStrap b : buttStraps) {
			if (b.getXi() == xi && b.getZi() == zi && b.getOrientation() == orientation) {
				return b;
			}
		}
		return null;
	}

	// Creates a rivet with position defined by an angle and x-position
	// l: length cylinder of the rivet
	// offPnt, phi, psi, theta: parameters for orientation of rivet
	private Transform createRivetAt(TopoDS_Face surface, double x, double angle, double l, gp_Pnt offPnt, double phi,
			double psi, double theta) throws Exception {

		// Defining a line in the y-z-plane from a point on the x-axis with a given
		// angle
		// The intersection of this line with the skin is the point, where the rivet
		// will be positioned to
		Point point_A = Point.create();
		point_A.setX(x);
		point_A.setY(0.);
		point_A.setZ(0.);

		Point point_B = Point.create();
		point_B.setX(x);
		point_B.setY(Math.sin(angle / 180. * Math.PI) * 10000);
		point_B.setZ(Math.cos(angle / 180. * Math.PI) * 10000);

		Geom_Curve line = BRepApi.createLine(point_A, point_B);

		// Intersection Point
		gp_Pnt pnt = BRepApi.getIntersectionPointCurveSurface(line, BRep_Tool.Surface(surface)).get(0); // Ursprung

		// Normal vector to the skin at the location of the point
		gp_Vec vec = BRepApi.getNormalToSurfaceAtPointAlternative(surface, pnt);

		return createRivet(pnt, vec, l, offPnt, phi, psi, theta);
	}

	// Creates a rivet with position defined by an angle and x-position without
	// adapting the orientation around the z and y axis
	// l: length cylinder of the rivet
	// offPnt, phi, psi, theta: parameters for orientation of rivet
	private Transform createRivetAtAlternative(Section section, double x, double angle, double l, gp_Pnt offPnt,
			double phi, double psi, double theta) throws Exception {

		Point point_A = Point.create();
		point_A.setX(x);
		point_A.setY(0.);
		point_A.setZ(0.);

		Point point_B = Point.create();
		point_B.setX(x);
		point_B.setY(Math.sin(angle / 180. * Math.PI) * 5000);
		point_B.setZ(Math.cos(angle / 180. * Math.PI) * 5000);

		Geom_Curve line = BRepApi.createLine(point_A, point_B);

		gp_Pnt pnt = BRepApi.getIntersectionPointCurveSurface(line, BRep_Tool.Surface(section.getSurface())).get(0);
		gp_Vec vec = BRepApi.getNormalToSurfaceAtPointAlternative(section.getSurface(), pnt);

		return createRivetAlternative(pnt, vec, l, offPnt, phi, psi, theta);
	}

	// Creates a rivet
	private Transform createRivet(gp_Pnt transPnt, gp_Vec transVec, double l, gp_Pnt offPnt, double phi, double psi,
			double theta) {

		double a = 30;
		double r1 = 4;

		Sphere sp_A = Sphere.create();
		sp_A.setRadius(r1);
		sp_A.setA1(-90.);
		sp_A.setA2(-a);
		sp_A.setLz(r1 * Math.sin(a / 180. * Math.PI));

		Sphere sp_B = Sphere.create();
		sp_B.setRadius(r1);
		sp_B.setA1(a);
		sp_B.setA2(90.);
		sp_B.setLz(-r1 * Math.sin(a / 180. * Math.PI) + l);

		Cylinder cyl = Cylinder.create();
		cyl.setHeight(l);
		cyl.setRadius(2.);

		Add rivet = Add.create();
		rivet.getLive().add(sp_A);
		rivet.getLive().add(sp_B);
		rivet.getLive().add(cyl);

		double v = -0.001;
		double u = -(transPnt.Y() * transVec.Y() + transPnt.Z() * transVec.Z()) * v / transVec.X();

		// Tangential vector at the location point which is normal to the normal vector
		// an tangential to the skin
		gp_Pnt tanVec = new gp_Pnt();
		tanVec.SetX(u);
		tanVec.SetY(v * transPnt.Y());
		tanVec.SetZ(v * transPnt.Z());

		// First Transformation moves (smaller offset) and rotates the rivet
		// First Transformation is supposed to position the rivet as if the (for
		// example) would still be at the origin
		Transform trans1 = Transform.create();
		trans1.setLive(rivet);
		trans1.setDx(offPnt.X());
		trans1.setDy(offPnt.Y());
		trans1.setDz(offPnt.Z());

		trans1.setPhi(phi);
		trans1.setPsi(psi);
		trans1.setTheta(theta);

		Transform trans2 = Transform.create();

		trans2.setLive(trans1);

		//
		if (tanVec.X() < 0) {
			tanVec.SetX(-tanVec.X());
		}

		// Second Transformation adapts the orientation of the rivet to the incline of
		// the skin
		trans2.setPhi(180. - Math.atan2(transVec.Y(), transVec.Z()) * 180. / Math.PI);
		trans2.setPsi(Math.atan2(tanVec.Y(), tanVec.X()) * 180. / Math.PI);
		trans2.setTheta(-(Math.atan2(tanVec.Z(), tanVec.X())) * 180. / Math.PI);

		// Third transformation moves the rivet to the positions of the components
		Transform trans3 = Transform.create();
		trans3.setLive(trans2);
		trans3.setDx(transPnt.X());
		trans3.setDy(transPnt.Y());
		trans3.setDz(transPnt.Z());
		return trans3;
	}

	// Creates rivet without psi and theta at second transform
	private Transform createRivetAlternative(gp_Pnt transPnt, gp_Vec transVec, double l, gp_Pnt offPnt, double phi,
			double psi, double theta) {

		double a = 30;
		double r1 = 4;

		Sphere sp_A = Sphere.create();
		sp_A.setRadius(r1);
		sp_A.setA1(-90.);
		sp_A.setA2(-a);
		sp_A.setLz(r1 * Math.sin(a / 180. * Math.PI));

		Sphere sp_B = Sphere.create();
		sp_B.setRadius(r1);
		sp_B.setA1(a);
		sp_B.setA2(90.);
		sp_B.setLz(-r1 * Math.sin(a / 180. * Math.PI) + l);

		Cylinder cyl = Cylinder.create();
		cyl.setHeight(l);
		cyl.setRadius(2.);

		Add rivet = Add.create();
		rivet.getLive().add(sp_A);
		rivet.getLive().add(sp_B);
		rivet.getLive().add(cyl);

		double v = -0.001;
		double u = -(transPnt.Y() * transVec.Y() + transPnt.Z() * transVec.Z()) * v / transVec.X();

		gp_Pnt tanVec = new gp_Pnt();
		tanVec.SetX(u);
		tanVec.SetY(v * transPnt.Y());
		tanVec.SetZ(v * transPnt.Z());

		Transform trans1 = Transform.create();
		trans1.setLive(rivet);
		trans1.setDx(offPnt.X());
		trans1.setDy(offPnt.Y());
		trans1.setDz(offPnt.Z());

		trans1.setPhi(phi);
		trans1.setPsi(psi);
		trans1.setTheta(theta);

		Transform trans2 = Transform.create();
		trans2.setLive(trans1);
		trans2.setPhi(180. - Math.atan2(transVec.Y(), transVec.Z()) * 180. / Math.PI);

		Transform trans3 = Transform.create();
		trans3.setLive(trans2);
		trans3.setDx(transPnt.X());
		trans3.setDy(transPnt.Y());
		trans3.setDz(transPnt.Z());
		return trans3;
	}

}