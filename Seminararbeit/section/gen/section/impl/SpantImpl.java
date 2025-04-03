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
public class SpantImpl extends ComponentImpl implements Spant {
	protected SpantImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getSpant();
	}
	public section.SpantProfile getSpantProfile() {
		return (section.SpantProfile)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSpant_SpantProfile());
	}
	public section.Spant setSpantProfile(section.SpantProfile value) {
		eSet(SectionPackageImpl.eINSTANCE.getSpant_SpantProfile(), value);
		return this;
	}
	public boolean isSpantProfileSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSpant_SpantProfile());
	}
	public void unsetSpantProfile() {
		eSet(SectionPackageImpl.eINSTANCE.getSpant_SpantProfile(), null);
	}
	public Integer getI() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSpant_I());
	}
	public section.Spant setI(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSpant_I(), value);
		return this;
	}
	public boolean isISet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSpant_I());
	}
	public void unsetI() {
		eSet(SectionPackageImpl.eINSTANCE.getSpant_I(), null);
	}
	public List<section.Clip> getClip() {
		return (List<section.Clip>)eGet(SectionPackageImpl.eINSTANCE.getSpant_Clip());
	}
	@Override
	public section.Spant setColor(de.iils.dc43.core.geometry.publication.Color value) {
		super.setColor(value);
		return this;
	}
	@Override
	public section.Spant setShape(de.iils.dc43.core.geometry.publication.Shape value) {
		super.setShape(value);
		return this;
	}
	@Override
	public section.Spant setExistingComponent(de.iils.dc43.core.geometry.publication.ExistingComponent value) {
		super.setExistingComponent(value);
		return this;
	}
	@Override
	public section.Spant setUserDefinedFeature(de.iils.dc43.core.geometry.publication.UserDefinedFeature value) {
		super.setUserDefinedFeature(value);
		return this;
	}
	@Override
	public section.Spant setId(String value) {
		super.setId(value);
		return this;
	}
}