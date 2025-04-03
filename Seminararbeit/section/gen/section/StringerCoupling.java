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
public interface StringerCoupling extends de.iils.dc43.core.geometry.publication.Component {

	public static Set<StringerCoupling> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(StringerCoupling.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getStringerCoupling();
	}
	public static EReference reference_Stringer() {
		return SectionPackageImpl.eINSTANCE.getStringerCoupling_Stringer();
	}
	public static EAttribute property_Xi() {
		return SectionPackageImpl.eINSTANCE.getStringerCoupling_Xi();
	}
	public static EAttribute property_Zi() {
		return SectionPackageImpl.eINSTANCE.getStringerCoupling_Zi();
	}
	/**
	* Create a instance of <code>StringerCoupling</code>
	*/
	public static StringerCoupling create() {
		return (StringerCoupling)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	* Get the reference <code>stringer</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.Stringer> getStringer();
	/**
	 * Get the property <code>xi</code>
	 */
	Integer getXi();
	/**
	* Set the property <code>xi</code>
	* @param value the value that will be set
	*/
	StringerCoupling setXi(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>xi</code>
	*/
	boolean isXiSet();
	/**
	* Unset the property <code>xi</code>
	*/
	void unsetXi();
	/**
	 * Get the property <code>zi</code>
	 */
	Integer getZi();
	/**
	* Set the property <code>zi</code>
	* @param value the value that will be set
	*/
	StringerCoupling setZi(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>zi</code>
	*/
	boolean isZiSet();
	/**
	* Unset the property <code>zi</code>
	*/
	void unsetZi();
	/**
	* Set the property <code>id</code>
	* @param value the value that will be set
	*/
	StringerCoupling setId(String value);

}