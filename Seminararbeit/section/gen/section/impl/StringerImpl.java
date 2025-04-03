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
import de.iils.dc43.core.geometry.publication.impl.*;
@SuppressWarnings("all")
public class StringerImpl extends ComponentImpl implements Stringer {
	protected StringerImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getStringer();
	}
	public section.StringerProfile getStringerProfile() {
		return (section.StringerProfile)eGetWithUnit(SectionPackageImpl.eINSTANCE.getStringer_StringerProfile());
	}
	public section.Stringer setStringerProfile(section.StringerProfile value) {
		eSet(SectionPackageImpl.eINSTANCE.getStringer_StringerProfile(), value);
		return this;
	}
	public boolean isStringerProfileSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getStringer_StringerProfile());
	}
	public void unsetStringerProfile() {
		eSet(SectionPackageImpl.eINSTANCE.getStringer_StringerProfile(), null);
	}
	public Integer getI() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getStringer_I());
	}
	public section.Stringer setI(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getStringer_I(), value);
		return this;
	}
	public boolean isISet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getStringer_I());
	}
	public void unsetI() {
		eSet(SectionPackageImpl.eINSTANCE.getStringer_I(), null);
	}
	public List<section.StringerCoupling> getStringerCoupling() {
		return (List<section.StringerCoupling>)eGet(SectionPackageImpl.eINSTANCE.getStringer_StringerCoupling());
	}
	@Override
	public section.Stringer setColor(de.iils.dc43.core.geometry.publication.Color value) {
		super.setColor(value);
		return this;
	}
	@Override
	public section.Stringer setShape(de.iils.dc43.core.geometry.publication.Shape value) {
		super.setShape(value);
		return this;
	}
	@Override
	public section.Stringer setExistingComponent(de.iils.dc43.core.geometry.publication.ExistingComponent value) {
		super.setExistingComponent(value);
		return this;
	}
	@Override
	public section.Stringer setUserDefinedFeature(de.iils.dc43.core.geometry.publication.UserDefinedFeature value) {
		super.setUserDefinedFeature(value);
		return this;
	}
	@Override
	public section.Stringer setId(String value) {
		super.setId(value);
		return this;
	}
}