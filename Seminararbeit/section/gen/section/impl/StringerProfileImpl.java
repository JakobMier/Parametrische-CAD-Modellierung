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
public class StringerProfileImpl extends BaseInstanceImpl implements StringerProfile {
	protected StringerProfileImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getStringerProfile();
	}
	public de.iils.dc43.core.geometry.publication.Wire getProfile() {
		return (de.iils.dc43.core.geometry.publication.Wire)eGetWithUnit(SectionPackageImpl.eINSTANCE.getStringerProfile_Profile());
	}
	public section.StringerProfile setProfile(de.iils.dc43.core.geometry.publication.Wire value) {
		eSet(SectionPackageImpl.eINSTANCE.getStringerProfile_Profile(), value);
		return this;
	}
	public boolean isProfileSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getStringerProfile_Profile());
	}
	public void unsetProfile() {
		eSet(SectionPackageImpl.eINSTANCE.getStringerProfile_Profile(), null);
	}
	public NumberQuantity getHeight() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getStringerProfile_Height());
	}
	public section.StringerProfile setHeight(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getStringerProfile_Height(), value);
		return this;
	}
	public section.StringerProfile setHeight(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getStringerProfile_Height(), value);
		return this;
	}
	public boolean isHeightSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getStringerProfile_Height());
	}
	public void unsetHeight() {
		eSet(SectionPackageImpl.eINSTANCE.getStringerProfile_Height(), null);
	}
}