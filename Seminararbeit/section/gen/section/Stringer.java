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
public interface Stringer extends de.iils.dc43.core.geometry.publication.Component {

	public static Set<Stringer> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(Stringer.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getStringer();
	}
	public static EReference reference_StringerProfile() {
		return SectionPackageImpl.eINSTANCE.getStringer_StringerProfile();
	}
	public static EAttribute property_I() {
		return SectionPackageImpl.eINSTANCE.getStringer_I();
	}
	public static EReference reference_StringerCoupling() {
		return SectionPackageImpl.eINSTANCE.getStringer_StringerCoupling();
	}
	/**
	* Create a instance of <code>Stringer</code>
	*/
	public static Stringer create() {
		return (Stringer)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	 * Get the reference <code>stringerProfile</code>
	 */
	section.StringerProfile getStringerProfile();
	/**
	* Set the reference <code>stringerProfile</code>
	* @param value the instance that will be set
	*/
	Stringer setStringerProfile(section.StringerProfile value);
	/**
	* Returns whether this feature is considered to be set <code>stringerProfile</code>
	*/
	boolean isStringerProfileSet();
	/**
	* Unset the property <code>stringerProfile</code>
	*/
	void unsetStringerProfile();
	/**
	 * Get the property <code>i</code>
	 */
	Integer getI();
	/**
	* Set the property <code>i</code>
	* @param value the value that will be set
	*/
	Stringer setI(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>i</code>
	*/
	boolean isISet();
	/**
	* Unset the property <code>i</code>
	*/
	void unsetI();
	/**
	* Get the reference <code>stringerCoupling</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.StringerCoupling> getStringerCoupling();
	/**
	* Set the property <code>id</code>
	* @param value the value that will be set
	*/
	Stringer setId(String value);

}