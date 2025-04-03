package section.impl;
import section.*;
import section.impl.SectionPackageImpl;
import java.util.List;
import tec.uom.se.*;
import static tec.uom.se.quantity.Quantities.*;
import tec.uom.se.format.SimpleUnitFormat;
import org.apache.commons.math3.linear.RealMatrix;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.common.util.EList;
import java.lang.reflect.InvocationTargetException;
import de.iils.dc43.core.graph.impl.BaseInstanceImpl;
@SuppressWarnings("all")
public class SectionProfileImpl extends BaseInstanceImpl implements SectionProfile {
	protected SectionProfileImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getSectionProfile();
	}
	public NumberQuantity getR1a() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSectionProfile_R1a());
	}
	public section.SectionProfile setR1a(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_R1a(), value);
		return this;
	}
	public section.SectionProfile setR1a(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_R1a(), value);
		return this;
	}
	public boolean isR1aSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSectionProfile_R1a());
	}
	public void unsetR1a() {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_R1a(), null);
	}
	public NumberQuantity getR2a() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSectionProfile_R2a());
	}
	public section.SectionProfile setR2a(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_R2a(), value);
		return this;
	}
	public section.SectionProfile setR2a(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_R2a(), value);
		return this;
	}
	public boolean isR2aSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSectionProfile_R2a());
	}
	public void unsetR2a() {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_R2a(), null);
	}
	public NumberQuantity getHA() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSectionProfile_HA());
	}
	public section.SectionProfile setHA(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_HA(), value);
		return this;
	}
	public section.SectionProfile setHA(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_HA(), value);
		return this;
	}
	public boolean isHASet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSectionProfile_HA());
	}
	public void unsetHA() {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_HA(), null);
	}
	public Integer getI() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSectionProfile_I());
	}
	public section.SectionProfile setI(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_I(), value);
		return this;
	}
	public boolean isISet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSectionProfile_I());
	}
	public void unsetI() {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_I(), null);
	}
	public de.iils.dc43.core.geometry.publication.Spline getProfile() {
		return (de.iils.dc43.core.geometry.publication.Spline)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSectionProfile_Profile());
	}
	public section.SectionProfile setProfile(de.iils.dc43.core.geometry.publication.Spline value) {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_Profile(), value);
		return this;
	}
	public boolean isProfileSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSectionProfile_Profile());
	}
	public void unsetProfile() {
		eSet(SectionPackageImpl.eINSTANCE.getSectionProfile_Profile(), null);
	}
}