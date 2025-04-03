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
public interface SectionProfile extends BaseInstance {

	public static Set<SectionProfile> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(SectionProfile.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getSectionProfile();
	}
	public static EAttributeWithUnit property_R1a() {
		return SectionPackageImpl.eINSTANCE.getSectionProfile_R1a();
	}
	public static EAttributeWithUnit property_R2a() {
		return SectionPackageImpl.eINSTANCE.getSectionProfile_R2a();
	}
	public static EAttributeWithUnit property_HA() {
		return SectionPackageImpl.eINSTANCE.getSectionProfile_HA();
	}
	public static EAttribute property_I() {
		return SectionPackageImpl.eINSTANCE.getSectionProfile_I();
	}
	public static EAttribute property_Profile() {
		return SectionPackageImpl.eINSTANCE.getSectionProfile_Profile();
	}
	/**
	* Create a instance of <code>SectionProfile</code>
	*/
	public static SectionProfile create() {
		return (SectionProfile)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	 * Get the property <code>r1a</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getR1a();
	/**
	* Set the property <code>r1a</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	SectionProfile setR1a(NumberQuantity value);
	/**
	* Set the property <code>r1a</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	SectionProfile setR1a(Double value);
	/**
	* Returns whether this feature is considered to be set <code>r1a</code>
	*/
	boolean isR1aSet();
	/**
	* Unset the property <code>r1a</code>
	*/
	void unsetR1a();
	/**
	 * Get the property <code>r2a</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getR2a();
	/**
	* Set the property <code>r2a</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	SectionProfile setR2a(NumberQuantity value);
	/**
	* Set the property <code>r2a</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	SectionProfile setR2a(Double value);
	/**
	* Returns whether this feature is considered to be set <code>r2a</code>
	*/
	boolean isR2aSet();
	/**
	* Unset the property <code>r2a</code>
	*/
	void unsetR2a();
	/**
	 * Get the property <code>hA</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getHA();
	/**
	* Set the property <code>hA</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	SectionProfile setHA(NumberQuantity value);
	/**
	* Set the property <code>hA</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	SectionProfile setHA(Double value);
	/**
	* Returns whether this feature is considered to be set <code>hA</code>
	*/
	boolean isHASet();
	/**
	* Unset the property <code>hA</code>
	*/
	void unsetHA();
	/**
	 * Get the property <code>i</code>
	 */
	Integer getI();
	/**
	* Set the property <code>i</code>
	* @param value the value that will be set
	*/
	SectionProfile setI(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>i</code>
	*/
	boolean isISet();
	/**
	* Unset the property <code>i</code>
	*/
	void unsetI();
	/**
	 * Get the property <code>profile</code>
	 */
	de.iils.dc43.core.geometry.publication.Spline getProfile();
	/**
	* Set the property <code>profile</code>
	* @param value the value that will be set
	*/
	SectionProfile setProfile(de.iils.dc43.core.geometry.publication.Spline value);
	/**
	* Returns whether this feature is considered to be set <code>profile</code>
	*/
	boolean isProfileSet();
	/**
	* Unset the property <code>profile</code>
	*/
	void unsetProfile();

}