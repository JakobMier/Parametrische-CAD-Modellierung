package section;
import section.impl.SectionFactoryImpl;
import section.impl.SectionPackageImpl;
import de.iils.dc43.core.graph.BaseInstance;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import tec.uom.se.*;
import tec.uom.se.quantity.Quantities;
import org.apache.commons.math3.linear.RealMatrix;
import de.iils.dc43.core.ecore.DC43Factory;
import de.iils.dc43.core.ecore.EAttributeWithUnit;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

@SuppressWarnings("all")
public interface StringerProfile extends BaseInstance {

	public static Set<StringerProfile> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(StringerProfile.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getStringerProfile();
	}
	public static EAttribute property_Profile() {
		return SectionPackageImpl.eINSTANCE.getStringerProfile_Profile();
	}
	public static EAttributeWithUnit property_Height() {
		return SectionPackageImpl.eINSTANCE.getStringerProfile_Height();
	}
	/**
	* Create a instance of <code>StringerProfile</code>
	*/
	public static StringerProfile create() {
		return (StringerProfile)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	 * Get the property <code>profile</code>
	 */
	de.iils.dc43.core.geometry.publication.Wire getProfile();
	/**
	* Set the property <code>profile</code>
	* @param value the value that will be set
	*/
	StringerProfile setProfile(de.iils.dc43.core.geometry.publication.Wire value);
	/**
	* Returns whether this feature is considered to be set <code>profile</code>
	*/
	boolean isProfileSet();
	/**
	* Unset the property <code>profile</code>
	*/
	void unsetProfile();
	/**
	 * Get the property <code>height</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getHeight();
	/**
	* Set the property <code>height</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	StringerProfile setHeight(NumberQuantity value);
	/**
	* Set the property <code>height</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	StringerProfile setHeight(Double value);
	/**
	* Returns whether this feature is considered to be set <code>height</code>
	*/
	boolean isHeightSet();
	/**
	* Unset the property <code>height</code>
	*/
	void unsetHeight();

}