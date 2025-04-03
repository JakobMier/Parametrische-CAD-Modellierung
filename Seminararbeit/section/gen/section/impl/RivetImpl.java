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
public class RivetImpl extends ComponentImpl implements Rivet {
	protected RivetImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getRivet();
	}
	public section.StringerCoupling getStringerCoupling() {
		return (section.StringerCoupling)eGetWithUnit(SectionPackageImpl.eINSTANCE.getRivet_StringerCoupling());
	}
	public section.Rivet setStringerCoupling(section.StringerCoupling value) {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_StringerCoupling(), value);
		return this;
	}
	public boolean isStringerCouplingSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getRivet_StringerCoupling());
	}
	public void unsetStringerCoupling() {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_StringerCoupling(), null);
	}
	public section.Stringer getStringer() {
		return (section.Stringer)eGetWithUnit(SectionPackageImpl.eINSTANCE.getRivet_Stringer());
	}
	public section.Rivet setStringer(section.Stringer value) {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_Stringer(), value);
		return this;
	}
	public boolean isStringerSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getRivet_Stringer());
	}
	public void unsetStringer() {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_Stringer(), null);
	}
	public section.Clip getClip() {
		return (section.Clip)eGetWithUnit(SectionPackageImpl.eINSTANCE.getRivet_Clip());
	}
	public section.Rivet setClip(section.Clip value) {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_Clip(), value);
		return this;
	}
	public boolean isClipSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getRivet_Clip());
	}
	public void unsetClip() {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_Clip(), null);
	}
	public section.Spant getSpant() {
		return (section.Spant)eGetWithUnit(SectionPackageImpl.eINSTANCE.getRivet_Spant());
	}
	public section.Rivet setSpant(section.Spant value) {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_Spant(), value);
		return this;
	}
	public boolean isSpantSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getRivet_Spant());
	}
	public void unsetSpant() {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_Spant(), null);
	}
	public section.ButtStrap getButtStrap() {
		return (section.ButtStrap)eGetWithUnit(SectionPackageImpl.eINSTANCE.getRivet_ButtStrap());
	}
	public section.Rivet setButtStrap(section.ButtStrap value) {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_ButtStrap(), value);
		return this;
	}
	public boolean isButtStrapSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getRivet_ButtStrap());
	}
	public void unsetButtStrap() {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_ButtStrap(), null);
	}
	public section.Skin getSkin() {
		return (section.Skin)eGetWithUnit(SectionPackageImpl.eINSTANCE.getRivet_Skin());
	}
	public section.Rivet setSkin(section.Skin value) {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_Skin(), value);
		return this;
	}
	public boolean isSkinSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getRivet_Skin());
	}
	public void unsetSkin() {
		eSet(SectionPackageImpl.eINSTANCE.getRivet_Skin(), null);
	}
	@Override
	public section.Rivet setColor(de.iils.dc43.core.geometry.publication.Color value) {
		super.setColor(value);
		return this;
	}
	@Override
	public section.Rivet setShape(de.iils.dc43.core.geometry.publication.Shape value) {
		super.setShape(value);
		return this;
	}
	@Override
	public section.Rivet setExistingComponent(de.iils.dc43.core.geometry.publication.ExistingComponent value) {
		super.setExistingComponent(value);
		return this;
	}
	@Override
	public section.Rivet setUserDefinedFeature(de.iils.dc43.core.geometry.publication.UserDefinedFeature value) {
		super.setUserDefinedFeature(value);
		return this;
	}
	@Override
	public section.Rivet setId(String value) {
		super.setId(value);
		return this;
	}
}