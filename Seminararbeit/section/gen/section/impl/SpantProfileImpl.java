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
public class SpantProfileImpl extends BaseInstanceImpl implements SpantProfile {
	protected SpantProfileImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getSpantProfile();
	}
	public de.iils.dc43.core.geometry.publication.Wire getProfile() {
		return (de.iils.dc43.core.geometry.publication.Wire)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSpantProfile_Profile());
	}
	public section.SpantProfile setProfile(de.iils.dc43.core.geometry.publication.Wire value) {
		eSet(SectionPackageImpl.eINSTANCE.getSpantProfile_Profile(), value);
		return this;
	}
	public boolean isProfileSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSpantProfile_Profile());
	}
	public void unsetProfile() {
		eSet(SectionPackageImpl.eINSTANCE.getSpantProfile_Profile(), null);
	}
	public NumberQuantity getHeightForClip() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSpantProfile_HeightForClip());
	}
	public section.SpantProfile setHeightForClip(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSpantProfile_HeightForClip(), value);
		return this;
	}
	public section.SpantProfile setHeightForClip(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSpantProfile_HeightForClip(), value);
		return this;
	}
	public boolean isHeightForClipSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSpantProfile_HeightForClip());
	}
	public void unsetHeightForClip() {
		eSet(SectionPackageImpl.eINSTANCE.getSpantProfile_HeightForClip(), null);
	}
}