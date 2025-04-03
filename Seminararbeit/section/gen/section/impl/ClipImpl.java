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
public class ClipImpl extends ComponentImpl implements Clip {
	protected ClipImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getClip();
	}
	public NumberQuantity getHieght() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getClip_Hieght());
	}
	public section.Clip setHieght(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Hieght(), value);
		return this;
	}
	public section.Clip setHieght(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Hieght(), value);
		return this;
	}
	public boolean isHieghtSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getClip_Hieght());
	}
	public void unsetHieght() {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Hieght(), null);
	}
	public Integer getXi() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getClip_Xi());
	}
	public section.Clip setXi(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Xi(), value);
		return this;
	}
	public boolean isXiSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getClip_Xi());
	}
	public void unsetXi() {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Xi(), null);
	}
	public Integer getZi() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getClip_Zi());
	}
	public section.Clip setZi(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Zi(), value);
		return this;
	}
	public boolean isZiSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getClip_Zi());
	}
	public void unsetZi() {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Zi(), null);
	}
	public section.Spant getSpant() {
		return (section.Spant)eGetWithUnit(SectionPackageImpl.eINSTANCE.getClip_Spant());
	}
	public section.Clip setSpant(section.Spant value) {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Spant(), value);
		return this;
	}
	public boolean isSpantSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getClip_Spant());
	}
	public void unsetSpant() {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Spant(), null);
	}
	public section.Stringer getStringer() {
		return (section.Stringer)eGetWithUnit(SectionPackageImpl.eINSTANCE.getClip_Stringer());
	}
	public section.Clip setStringer(section.Stringer value) {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Stringer(), value);
		return this;
	}
	public boolean isStringerSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getClip_Stringer());
	}
	public void unsetStringer() {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Stringer(), null);
	}
	public section.Skin getSkin() {
		return (section.Skin)eGetWithUnit(SectionPackageImpl.eINSTANCE.getClip_Skin());
	}
	public section.Clip setSkin(section.Skin value) {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Skin(), value);
		return this;
	}
	public boolean isSkinSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getClip_Skin());
	}
	public void unsetSkin() {
		eSet(SectionPackageImpl.eINSTANCE.getClip_Skin(), null);
	}
	@Override
	public section.Clip setColor(de.iils.dc43.core.geometry.publication.Color value) {
		super.setColor(value);
		return this;
	}
	@Override
	public section.Clip setShape(de.iils.dc43.core.geometry.publication.Shape value) {
		super.setShape(value);
		return this;
	}
	@Override
	public section.Clip setExistingComponent(de.iils.dc43.core.geometry.publication.ExistingComponent value) {
		super.setExistingComponent(value);
		return this;
	}
	@Override
	public section.Clip setUserDefinedFeature(de.iils.dc43.core.geometry.publication.UserDefinedFeature value) {
		super.setUserDefinedFeature(value);
		return this;
	}
	@Override
	public section.Clip setId(String value) {
		super.setId(value);
		return this;
	}
}