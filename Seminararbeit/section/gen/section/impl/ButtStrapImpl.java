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
public class ButtStrapImpl extends ComponentImpl implements ButtStrap {
	protected ButtStrapImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getButtStrap();
	}
	public List<section.Skin> getSkin() {
		return (List<section.Skin>)eGet(SectionPackageImpl.eINSTANCE.getButtStrap_Skin());
	}
	public Integer getXi() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getButtStrap_Xi());
	}
	public section.ButtStrap setXi(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getButtStrap_Xi(), value);
		return this;
	}
	public boolean isXiSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getButtStrap_Xi());
	}
	public void unsetXi() {
		eSet(SectionPackageImpl.eINSTANCE.getButtStrap_Xi(), null);
	}
	public Integer getZi() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getButtStrap_Zi());
	}
	public section.ButtStrap setZi(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getButtStrap_Zi(), value);
		return this;
	}
	public boolean isZiSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getButtStrap_Zi());
	}
	public void unsetZi() {
		eSet(SectionPackageImpl.eINSTANCE.getButtStrap_Zi(), null);
	}
	public String getOrientation() {
		return (String)eGetWithUnit(SectionPackageImpl.eINSTANCE.getButtStrap_Orientation());
	}
	public section.ButtStrap setOrientation(String value) {
		eSet(SectionPackageImpl.eINSTANCE.getButtStrap_Orientation(), value);
		return this;
	}
	public boolean isOrientationSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getButtStrap_Orientation());
	}
	public void unsetOrientation() {
		eSet(SectionPackageImpl.eINSTANCE.getButtStrap_Orientation(), null);
	}
	@Override
	public section.ButtStrap setColor(de.iils.dc43.core.geometry.publication.Color value) {
		super.setColor(value);
		return this;
	}
	@Override
	public section.ButtStrap setShape(de.iils.dc43.core.geometry.publication.Shape value) {
		super.setShape(value);
		return this;
	}
	@Override
	public section.ButtStrap setExistingComponent(de.iils.dc43.core.geometry.publication.ExistingComponent value) {
		super.setExistingComponent(value);
		return this;
	}
	@Override
	public section.ButtStrap setUserDefinedFeature(de.iils.dc43.core.geometry.publication.UserDefinedFeature value) {
		super.setUserDefinedFeature(value);
		return this;
	}
	@Override
	public section.ButtStrap setId(String value) {
		super.setId(value);
		return this;
	}
}