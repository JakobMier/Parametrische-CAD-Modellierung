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
public interface Spant extends de.iils.dc43.core.geometry.publication.Component {

	public static Set<Spant> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(Spant.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getSpant();
	}
	public static EReference reference_SpantProfile() {
		return SectionPackageImpl.eINSTANCE.getSpant_SpantProfile();
	}
	public static EAttribute property_I() {
		return SectionPackageImpl.eINSTANCE.getSpant_I();
	}
	public static EReference reference_Clip() {
		return SectionPackageImpl.eINSTANCE.getSpant_Clip();
	}
	/**
	* Create a instance of <code>Spant</code>
	*/
	public static Spant create() {
		return (Spant)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	 * Get the reference <code>spantProfile</code>
	 */
	section.SpantProfile getSpantProfile();
	/**
	* Set the reference <code>spantProfile</code>
	* @param value the instance that will be set
	*/
	Spant setSpantProfile(section.SpantProfile value);
	/**
	* Returns whether this feature is considered to be set <code>spantProfile</code>
	*/
	boolean isSpantProfileSet();
	/**
	* Unset the property <code>spantProfile</code>
	*/
	void unsetSpantProfile();
	/**
	 * Get the property <code>i</code>
	 */
	Integer getI();
	/**
	* Set the property <code>i</code>
	* @param value the value that will be set
	*/
	Spant setI(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>i</code>
	*/
	boolean isISet();
	/**
	* Unset the property <code>i</code>
	*/
	void unsetI();
	/**
	* Get the reference <code>clip</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.Clip> getClip();
	/**
	* Set the property <code>id</code>
	* @param value the value that will be set
	*/
	Spant setId(String value);

}