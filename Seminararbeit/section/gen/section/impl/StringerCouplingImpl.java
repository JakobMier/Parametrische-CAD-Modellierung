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
public class StringerCouplingImpl extends ComponentImpl implements StringerCoupling {
	protected StringerCouplingImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getStringerCoupling();
	}
	public List<section.Stringer> getStringer() {
		return (List<section.Stringer>)eGet(SectionPackageImpl.eINSTANCE.getStringerCoupling_Stringer());
	}
	public Integer getXi() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getStringerCoupling_Xi());
	}
	public section.StringerCoupling setXi(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getStringerCoupling_Xi(), value);
		return this;
	}
	public boolean isXiSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getStringerCoupling_Xi());
	}
	public void unsetXi() {
		eSet(SectionPackageImpl.eINSTANCE.getStringerCoupling_Xi(), null);
	}
	public Integer getZi() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getStringerCoupling_Zi());
	}
	public section.StringerCoupling setZi(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getStringerCoupling_Zi(), value);
		return this;
	}
	public boolean isZiSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getStringerCoupling_Zi());
	}
	public void unsetZi() {
		eSet(SectionPackageImpl.eINSTANCE.getStringerCoupling_Zi(), null);
	}
	@Override
	public section.StringerCoupling setColor(de.iils.dc43.core.geometry.publication.Color value) {
		super.setColor(value);
		return this;
	}
	@Override
	public section.StringerCoupling setShape(de.iils.dc43.core.geometry.publication.Shape value) {
		super.setShape(value);
		return this;
	}
	@Override
	public section.StringerCoupling setExistingComponent(de.iils.dc43.core.geometry.publication.ExistingComponent value) {
		super.setExistingComponent(value);
		return this;
	}
	@Override
	public section.StringerCoupling setUserDefinedFeature(de.iils.dc43.core.geometry.publication.UserDefinedFeature value) {
		super.setUserDefinedFeature(value);
		return this;
	}
	@Override
	public section.StringerCoupling setId(String value) {
		super.setId(value);
		return this;
	}
}