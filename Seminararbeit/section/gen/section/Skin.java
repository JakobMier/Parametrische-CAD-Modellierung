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
public interface Skin extends de.iils.dc43.core.geometry.publication.Component {

	public static Set<Skin> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(Skin.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getSkin();
	}
	public static EAttributeWithUnit property_Ts() {
		return SectionPackageImpl.eINSTANCE.getSkin_Ts();
	}
	public static EReference reference_ButtStrap() {
		return SectionPackageImpl.eINSTANCE.getSkin_ButtStrap();
	}
	/**
	* Create a instance of <code>Skin</code>
	*/
	public static Skin create() {
		return (Skin)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	 * Get the property <code>ts</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getTs();
	/**
	* Set the property <code>ts</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	Skin setTs(NumberQuantity value);
	/**
	* Set the property <code>ts</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	Skin setTs(Double value);
	/**
	* Returns whether this feature is considered to be set <code>ts</code>
	*/
	boolean isTsSet();
	/**
	* Unset the property <code>ts</code>
	*/
	void unsetTs();
	/**
	* Get the reference <code>buttStrap</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.ButtStrap> getButtStrap();
	/**
	* Set the property <code>id</code>
	* @param value the value that will be set
	*/
	Skin setId(String value);

}