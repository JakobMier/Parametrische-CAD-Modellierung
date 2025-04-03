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
public class SkinImpl extends ComponentImpl implements Skin {
	protected SkinImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getSkin();
	}
	public NumberQuantity getTs() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSkin_Ts());
	}
	public section.Skin setTs(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSkin_Ts(), value);
		return this;
	}
	public section.Skin setTs(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSkin_Ts(), value);
		return this;
	}
	public boolean isTsSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSkin_Ts());
	}
	public void unsetTs() {
		eSet(SectionPackageImpl.eINSTANCE.getSkin_Ts(), null);
	}
	public List<section.ButtStrap> getButtStrap() {
		return (List<section.ButtStrap>)eGet(SectionPackageImpl.eINSTANCE.getSkin_ButtStrap());
	}
	@Override
	public section.Skin setColor(de.iils.dc43.core.geometry.publication.Color value) {
		super.setColor(value);
		return this;
	}
	@Override
	public section.Skin setShape(de.iils.dc43.core.geometry.publication.Shape value) {
		super.setShape(value);
		return this;
	}
	@Override
	public section.Skin setExistingComponent(de.iils.dc43.core.geometry.publication.ExistingComponent value) {
		super.setExistingComponent(value);
		return this;
	}
	@Override
	public section.Skin setUserDefinedFeature(de.iils.dc43.core.geometry.publication.UserDefinedFeature value) {
		super.setUserDefinedFeature(value);
		return this;
	}
	@Override
	public section.Skin setId(String value) {
		super.setId(value);
		return this;
	}
}