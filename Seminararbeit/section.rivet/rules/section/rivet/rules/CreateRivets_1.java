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
public class CreateRivets_1 extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);

		// Test rivet
//		createRivet(new gp_Pnt(0., 0., 0.), new gp_Vec(0., 1., 1.), 6., new gp_Pnt(0., -30., 30.), 90., 0., 0.);

//		double distance = 20.;
//		int n = (int) (section.getStringerCouplingLenght().getValue() / distance);
		int n = 10; // muss gerade Zahl sein, damit Niete nicht an zwei benachbarten Skins
					// angebracht wird
		double distance = section.getStringerCouplingLenght().getValue() / n;

		double dl = section.getL().getValue() / section.getNPanelX();
		double zA = section.getZA().getValue();
		double h = section.getH().getValue();
		double dh = h / section.getNPanelZ();

		Component rivetsComp = Component.create().setId("Rivets");

		// Stringer Coupling

		if (true) {
			for (int i = 0; i < section.getNPanelZ() * section.getNStringer(); i++) {
				for (int j = 0; j < section.getNPanelX() - 1; j++) {
					for (int j2 = 0; j2 < n; j2++) {

						double x = (j + 1) * dl - section.getStringerCouplingLenght().getValue() / 2 + distance / 2
								+ j2 * distance;

						double angle = 180. - (zA + (0.5 + i) * dh / section.getNStringer());
						Rivet rivet = Rivet.create().setId("Rivet Stringer Coupling");
						rivet.setShape(createRivetAt(section, x, angle, 6., new gp_Pnt(0., -15., -2.), 0., 0., 0.));
						rivet.setStringerCoupling(searchCoupling(j, i));
						rivetsComp.getSub().add(rivet);

						if (Math.abs(x - (j + 1) * dl) > section.getButtStrapWidth().getValue() / 2) {

							rivet = Rivet.create().setId("Rivet Stringer Coupling");
							rivet.setShape(createRivetAt(section, x, angle, 4., new gp_Pnt(0., -30., 30), 90., 0., 0.));
							rivet.setStringerCoupling(searchCoupling(j, i));
							rivetsComp.getSub().add(rivet);

//						Component rivetComp_1 = Component.create().setId("Rivet");
//						Transform rivet_1 = createRivetAt(section, x, angle, 4., new gp_Pnt(0., -30., 30), 90., 0., 0.);
//						rivetComp_1.setShape(rivet_1);
//						rivetsComp.getSub().add(rivetComp_1);
						}
					}
				}
			}
		}

		// Vertical Butt Strap

		int nReihen_vertical = 6;
		int nSpalten_vertical = 2;
		double abstand = 2.;
		double dAngle = (dh / (2 * section.getNStringer()) - abstand) / nReihen_vertical;

		double dx = section.getButtStrapWidth().getValue() / (2 * nSpalten_vertical);

		if (true) {
			for (int i = 0; i < section.getNPanelX() - 1; i++) {
				double x0 = (i + 1) * dl;
				for (int j1 = 0; j1 < section.getNPanelZ(); j1++) {
					for (int j2 = 0; j2 < section.getNStringer(); j2++) {

						int j = j1 * section.getNStringer() + j2;

						double stringerAngle = 180. - (zA + (0.5 + j) * dh / section.getNStringer());
						for (int k = 0; k < nReihen_vertical; k++) {
							for (int k2 = 0; k2 < nSpalten_vertical; k2++) {

								Rivet rivet = Rivet.create().setId("Rivet vertical ButtStrap");
								rivet.setShape(createRivetAt(section, x0 + (0.5 + k2) * dx,
										stringerAngle + (abstand + dAngle * (0.5 + k)), 4., new gp_Pnt(0., 0., -2.), 0.,
										0., 0.));
								rivetsComp.getSub().add(rivet);
								rivet.setButtStrap(searchButtStrap(i, j1, "vertical"));

//						rivetsComp.getSub()
//								.add(Rivet.create().setId("Rivet ButtStrap")
//										.setShape(createRivetAt(section, x0 + (0.5 + k2) * dx,
//												stringerAngle + (abstand + dAngle * (0.5 + k)), 4.,
//												new gp_Pnt(0., 0., -2.), 0., 0., 0.)));

								rivet = Rivet.create().setId("Rivet vertical ButtStrap");
								rivet.setShape(createRivetAt(section, x0 - (0.5 + k2) * dx,
										stringerAngle - (abstand + dAngle * (0.5 + k)), 4., new gp_Pnt(0., 0., -2.), 0.,
										0., 0.));
								rivetsComp.getSub().add(rivet);
								rivet.setButtStrap(searchButtStrap(i, j1, "vertical"));

//						rivetsComp.getSub()
//								.add(Rivet.create().setId("Rivet ButtStrap")
//										.setShape(createRivetAt(section, x0 - (0.5 + k2) * dx,
//												stringerAngle - (abstand + dAngle * (0.5 + k)), 4.,
//												new gp_Pnt(0., 0., -2.), 0., 0., 0.)));

								rivet = Rivet.create().setId("Rivet vertical ButtStrap");
								rivet.setShape(createRivetAt(section, x0 - (0.5 + k2) * dx,
										stringerAngle + (abstand + dAngle * (0.5 + k)), 4., new gp_Pnt(0., 0., -2.), 0.,
										0., 0.));
								rivetsComp.getSub().add(rivet);
								rivet.setButtStrap(searchButtStrap(i, j1, "vertical"));

//						rivetsComp.getSub()
//								.add(Rivet.create().setId("Rivet ButtStrap")
//										.setShape(createRivetAt(section, x0 - (0.5 + k2) * dx,
//												stringerAngle + (abstand + dAngle * (0.5 + k)), 4.,
//												new gp_Pnt(0., 0., -2.), 0., 0., 0.)));

								rivet = Rivet.create().setId("Rivet vertical ButtStrap");
								rivet.setShape(createRivetAt(section, x0 + (0.5 + k2) * dx,
										stringerAngle - (abstand + dAngle * (0.5 + k)), 4., new gp_Pnt(0., 0., -2.), 0.,
										0., 0.));
								rivetsComp.getSub().add(rivet);
								rivet.setButtStrap(searchButtStrap(i, j1, "vertical"));

//						rivetsComp.getSub()
//								.add(Rivet.create().setId("Rivet ButtStrap")
//										.setShape(createRivetAt(section, x0 + (0.5 + k2) * dx,
//												stringerAngle - (abstand + dAngle * (0.5 + k)), 4.,
//												new gp_Pnt(0., 0., -2.), 0., 0., 0.)));
							}
						}
					}
				}
			}
		}

		// Horizontal Butt Straps

		int nReihen_horizontal = 4;
		int nSpalten_horizontal = 10;

		if (true) {
			for (int j = 0; j < section.getNPanelZ() - 1; j++) {
				double angle0 = 180. - (zA + (j + 1) * dh);
				for (int j2 = 0; j2 < section.getNPanelX(); j2++) {
					double x0 = j2 * dl;
					for (int l = 0; l < nReihen_horizontal; l++) {

						for (int l2 = 0; l2 < nSpalten_horizontal; l2++) {

							double x = x0 + (0.5 + l2) * dl / nSpalten_horizontal;

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

//					gp_Pnt newPnt = new gp_Pnt();
//					newPnt.SetX(x);
//					newPnt.SetY(pnt.Y()+5*vec.Z());
//					newPnt.SetZ(pnt.Z()+5*vec.Y());

//					point_B.setY(Math.sin(angle0 / 180. * Math.PI) * 5000);
//					point_B.setZ(Math.cos(angle0 / 180. * Math.PI) * 5000);

							double width = section.getButtStrapWidth().getValue();
							double dA = width / nReihen_horizontal;

							double angle = (Math.atan2(pnt.Y() + ((0.5 + l) * dA - width / 2) * vec.Z(),
									pnt.Z() + ((0.5 + l) * dA - width / 2) * vec.Y())) * 180. / Math.PI;

							Rivet rivet = Rivet.create().setId("Rivet horizontal ButtStrap");
							rivet.setShape(createRivetAt(section, x, angle, 4., new gp_Pnt(0., 0., -2.), 0., 0., 0.));
							rivetsComp.getSub().add(rivet);
							rivet.setButtStrap(searchButtStrap(j2, j, "horizontal"));
						}
					}
				}
			}
		}

		// Clips
		if (true) {
			int nFloorReihen = 5;
			int nFloorSpalten = 3;

			int nWall1Vertikal = 4;
			double dh_vertical = (getGraph().firstInstance(SpantProfile.class).getHeightForClip().getValue() - 10
					- getGraph().firstInstance(StringerProfile.class).getHeight().getValue()) / nWall1Vertikal;

			int nWall1Horizontal = 7;
			double dh_horizontal = (section.getClipFloorLenght().getValue()) / nWall1Horizontal;

			int nWall2 = 2;

//		List<Panel> panels = section.getPanel();
//		for (Panel panel : panels) {
//			for (int i = 0; i < section.getNStringer(); i++) {
//				for (int j = 0; j < section.getNSpante(); j++) {
//					
//					double angle0 = 180. - (zA + (dh / section.getNStringer()) / 2 + i * (dh / section.getNStringer()));
//					double x0 = (0.5 + j) * (dl / section.getNSpante());
//					
//					
//					
//					
//				}
//			}
//		}

			for (int i1 = 0; i1 < section.getNPanelZ(); i1++) {
				for (int i2 = 0; i2 < section.getNStringer(); i2++) {
					int i = i1 * section.getNStringer() + i2;
					for (int j1 = 0; j1 < section.getNPanelX(); j1++) {
						for (int j2 = 0; j2 < section.getNSpante(); j2++) {
							int j = j1 * section.getNSpante() + j2;

//			for (int j = 0; j < section.getNPanelX() * section.getNSpante(); j++) {

							double angle0 = 180.
									- (zA + (dh / section.getNStringer()) / 2 + i * (dh / section.getNStringer()));
							double x0 = (0.5 + j) * (dl / section.getNSpante());

							// Nieten am Clipboden
							for (int k = 0; k < nFloorSpalten; k++) {
								for (int k2 = 0; k2 < nFloorReihen; k2++) {

									double x = x0
											+ (0.5 + k) * (section.getClipFloorWidth().getValue() / nFloorSpalten);
									double angle = angle0
											- (0.5 + k2) * (section.getClipFloorLenght().getValue() / nFloorReihen);

									Rivet rivet = Rivet.create().setId("Rivet Clip");
									rivet.setShape(
											createRivetAt(section, x, angle, 5., new gp_Pnt(0., 0., -2.), 0., 0., 0.));
									rivetsComp.getSub().add(rivet);
									rivet.setClip(searchClip(searchPanel(i1, j1), i2, j2));
									rivetsComp.getSub().add(rivet);

//						rivetsComp.getSub().add(Rivet.create().setId("Rivet Clip")
//								.setShape(createRivetAt(section, x, angle, 5., new gp_Pnt(0., 0., -2.), 0., 0., 0.)));

								}
							}

							// Nieten an erster Wand
							for (int k = 0; k < nWall1Vertikal; k++) {

								double h_rivet = getGraph().firstInstance(SpantProfile.class).getHeightForClip()
										.getValue() - (0.5 + k) * dh_vertical;

								Rivet rivet = Rivet.create().setId("Rivet Clip");
								rivet.setShape(createRivetAtAlt(section, x0, angle0, 4., new gp_Pnt(-2., -25, h_rivet),
										0., 0., 90.));
								rivet.setClip(searchClip(searchPanel(i1, j1), i2, j2));
								rivetsComp.getSub().add(rivet);

//							rivetsComp.getSub()
//									.add(Rivet.create().setId("Rivet Clip").setShape(createRivetAtAlt(section, x0,
//											angle0, 4., new gp_Pnt(-2., -25, h_rivet), 0., 0., 90.)));
							}
							for (int k = 0; k < nWall1Horizontal; k++) {

								double angle = angle0 - (0.5 + k) * dh_horizontal;

								Rivet rivet = Rivet.create().setId("Rivet Clip");
								rivet.setShape(createRivetAtAlt(section, x0, angle, 4., new gp_Pnt(-2., 0.,
										getGraph().firstInstance(StringerProfile.class).getHeight().getValue() + 10),
										0., 0., 90.));
								rivet.setClip(searchClip(searchPanel(i1, j1), i2, j2));
								rivetsComp.getSub().add(rivet);

//							rivetsComp.getSub()
//									.add(Rivet.create().setId("Rivet Clip").setShape(createRivetAtAlt(section, x0,
//											angle, 4., new gp_Pnt(-2., 0., getGraph()
//													.firstInstance(StringerProfile.class).getHeight().getValue() + 10),
//											0., 0., 90.)));
							}

							// Nieten an zweiter Wand
							for (int k = 0; k < nWall2; k++) {

								double h_rivet = getGraph().firstInstance(StringerProfile.class).getHeight().getValue()
										- (0.5 + k) * (getGraph().firstInstance(StringerProfile.class).getHeight()
												.getValue() / nWall2);

								Rivet rivet = Rivet.create().setId("Rivet Clip");
								rivet.setShape(createRivetAtAlt(section, x0, angle0, 4., new gp_Pnt(10., -30, h_rivet),
										90., 0., 0.));
								rivet.setClip(searchClip(searchPanel(i1, j1), i2, j2));
								rivetsComp.getSub().add(rivet);

//							rivetsComp.getSub()
//									.add(Rivet.create().setId("Rivet Clip").setShape(createRivetAtAlt(section, x0,
//											angle0, 4., new gp_Pnt(10., -30, h_rivet), 90., 0., 0.)));
							}
						}
					}
				}
			}
		}
//	}
//		}

		rivetsComp.setId("Rivets (" + rivetsComp.getSub().size() + ")");

	}

	public Clip searchClip(Panel panel, int xi, int zi) {
		List<Clip> clips = panel.getClip();
		for (Clip clip : clips) {
			if (clip.getXi() == xi && clip.getZi() == zi) {
				return clip;
			}
		}
		return null;
	}

	public Panel searchPanel(int xi, int zi) {
		Set<Panel> panels = getGraph().allInstances(Panel.class);
		for (Panel panel : panels) {
			if (panel.getXi() == xi && panel.getZi() == zi) {
				return panel;
			}
		}
		return null;
	}

	public StringerCoupling searchCoupling(int xi, int zi) {
		Set<StringerCoupling> couplings = getGraph().allInstances(StringerCoupling.class);
		for (StringerCoupling coup : couplings) {
			if (coup.getXi() == xi && coup.getZi() == zi) {
				return coup;
			}
		}
		return null;
	}

	public ButtStrap searchButtStrap(int xi, int zi, String orientation) {
		Set<ButtStrap> buttStraps = getGraph().allInstances(ButtStrap.class);
		for (ButtStrap b : buttStraps) {
			if (b.getXi() == xi && b.getZi() == zi && b.getOrientation() == orientation) {
				return b;
			}
		}
		return null;
	}

	private Transform createRivetAtAlt(Section section, double x, double angle, double l, gp_Pnt offPnt, double phi,
			double psi, double theta) throws Exception {

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

		return createRivetAlt(pnt, vec, l, offPnt, phi, psi, theta);
	}

	private Transform createRivetAt(Section section, double x, double angle, double l, gp_Pnt offPnt, double phi,
			double psi, double theta) throws Exception {

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

		return createRivet(pnt, vec, l, offPnt, phi, psi, theta);
	}

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

//		System.out.println(transPnt.X() + "; " + transPnt.Y() + "; " + transPnt.Z());
//		System.out.println("Y: " + tanVec.Y() + ", X: " + tanVec.X() + ", Atan: "
//				+ Math.atan2(tanVec.Y(), tanVec.X()) * 180. / Math.PI);
		trans2.setPsi(Math.atan2(tanVec.Y(), tanVec.X()) * 180. / Math.PI);

		trans2.setTheta(-(Math.atan2(tanVec.Z(), tanVec.X())) * 180. / Math.PI);

		Transform trans3 = Transform.create();
		trans3.setLive(trans2);
		trans3.setDx(transPnt.X());
		trans3.setDy(transPnt.Y());
		trans3.setDz(transPnt.Z());
		return trans3;
	}

	private Transform createRivetAlt(gp_Pnt transPnt, gp_Vec transVec, double l, gp_Pnt offPnt, double phi, double psi,
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

//		System.out.println(transPnt.X() + "; " + transPnt.Y() + "; " + transPnt.Z());
//		System.out.println("Y: " + tanVec.Y() + ", X: " + tanVec.X() + ", Atan: "
//				+ Math.atan2(tanVec.Y(), tanVec.X()) * 180. / Math.PI);
//		trans2.setPsi(Math.atan2(tanVec.Y(), tanVec.X()) * 180. / Math.PI);

//		trans2.setTheta(-(Math.atan2(tanVec.Z(), tanVec.X())) * 180. / Math.PI);

		Transform trans3 = Transform.create();
		trans3.setLive(trans2);
		trans3.setDx(transPnt.X());
		trans3.setDy(transPnt.Y());
		trans3.setDz(transPnt.Z());
		return trans3;
	}

}