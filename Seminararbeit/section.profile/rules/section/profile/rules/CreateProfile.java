package section.profile.rules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.ISymbol;
import org.matheclipse.parser.client.SyntaxError;
import org.matheclipse.parser.client.math.MathException;

import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.publication.ControlPoint;
import de.iils.dc43.core.geometry.publication.Spline;
import section.Section;
import section.SectionProfile;

@SuppressWarnings("all")
public class CreateProfile extends JavaRule {

	@Override
	public void execute() throws Exception {
		try {

			Section section = getGraph().firstInstance(Section.class);

			// Identifying profiles, for setting start and end profile
			Iterator<SectionProfile> iterator = getGraph().allInstances(SectionProfile.class).iterator();
			SectionProfile p0 = iterator.next();
			SectionProfile p1 = iterator.next();

			// X positions of the profiles
			double x_a;
			double x_b;

			if (p0.getI() == 0) {
				x_a = 0.;
				x_b = section.getL().getValue();
			} else {
				x_a = section.getL().getValue();
				x_b = 0.;
			}

			ExprEvaluator util = new ExprEvaluator();
			IExpr curve;

			ISymbol r1 = F.Dummy("R1");
			ISymbol r2 = F.Dummy("R2");
			ISymbol h = F.Dummy("H");
			ISymbol z = F.Dummy("z");
			ISymbol z1 = F.Dummy("z1");
			ISymbol z2 = F.Dummy("z2");

			// Egg curve function
			curve = util.eval("f(z_) := sqrt((R1^2*R2^2 - R2^2*z^2)/(R1^2 + H^2 + 2*H*z))");

			// First profile
			double l = section.getL().getValue();

			curve = util.eval("R1=" + p0.getR1a().getValue());
			curve = util.eval("R2=" + p0.getR2a().getValue());
			curve = util.eval("H=" + p0.getHA().getValue());

			List<ControlPoint> cP1 = new ArrayList<>();
			double u;
			double z_bottom = -p0.getR1a().getValue() + 0.5; // Function may not start and and at y=0, so its easier to
																// find out in which direction the skin thickens in
																// section.skin
			double z_top = p0.getR1a().getValue() - 0.5;

			int n = 20; // Number of support points for spline function

			for (int i = 0; i <= n; i++) {
				u = z_bottom - i * z_bottom * 2 / n;
				curve = util.eval("z=" + u);
				curve = util.eval("f(z)");
				cP1.add(ControlPoint.create(x_a, curve.getReal(), u));
			}
			for (int i = 0; i < cP1.size() - 1; i++) {
				cP1.get(i).getNextControlPoint().add(cP1.get(i + 1));
			}

			Spline spline0 = Spline.create().setStart(cP1.get(0));
			for (int i = 0; i < cP1.size(); i++) {
				spline0.getElement().add(cP1.get(i));
			}

			// Second spline
			curve = util.eval("R1=" + p1.getR1a().getValue());
			curve = util.eval("R2=" + p1.getR2a().getValue());
			curve = util.eval("H=" + p1.getHA().getValue());

			List<ControlPoint> cp2 = new ArrayList<>();
			z_bottom = -p1.getR1a().getValue() + 0.5;
			z_top = p1.getR1a().getValue() - 0.5;

			for (int i = 0; i <= n; i++) {

				u = z_bottom - i * z_bottom * 2 / n;

				curve = util.eval("z=" + u);
				curve = util.eval("f(z)");
				cp2.add(ControlPoint.create(x_b, curve.getReal(), u));
			}

			for (int i = 0; i < cp2.size() - 1; i++) {
				cp2.get(i).getNextControlPoint().add(cp2.get(i + 1));
			}

			Spline spline1 = Spline.create().setStart(cp2.get(0));
			for (int i = 0; i < cp2.size(); i++) {
				spline1.getElement().add(cp2.get(i));
			}

			if (p0.getI() == 0) {
				p0.setProfile(spline0);
				p1.setProfile(spline1);
			} else if (p0.getI() == 1) {
				p0.setProfile(spline1);
				p1.setProfile(spline0);
			}

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