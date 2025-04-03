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
public interface ButtStrap extends de.iils.dc43.core.geometry.publication.Component {

	public static Set<ButtStrap> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(ButtStrap.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getButtStrap();
	}
	public static EReference reference_Skin() {
		return SectionPackageImpl.eINSTANCE.getButtStrap_Skin();
	}
	public static EAttribute property_Xi() {
		return SectionPackageImpl.eINSTANCE.getButtStrap_Xi();
	}
	public static EAttribute property_Zi() {
		return SectionPackageImpl.eINSTANCE.getButtStrap_Zi();
	}
	public static EAttribute property_Orientation() {
		return SectionPackageImpl.eINSTANCE.getButtStrap_Orientation();
	}
	/**
	* Create a instance of <code>ButtStrap</code>
	*/
	public static ButtStrap create() {
		return (ButtStrap)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	* Get the reference <code>skin</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.Skin> getSkin();
	/**
	 * Get the property <code>xi</code>
	 */
	Integer getXi();
	/**
	* Set the property <code>xi</code>
	* @param value the value that will be set
	*/
	ButtStrap setXi(Integer value);
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
	ButtStrap setZi(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>zi</code>
	*/
	boolean isZiSet();
	/**
	* Unset the property <code>zi</code>
	*/
	void unsetZi();
	/**
	 * Get the property <code>orientation</code>
	 */
	String getOrientation();
	/**
	* Set the property <code>orientation</code>
	* @param value the value that will be set
	*/
	ButtStrap setOrientation(String value);
	/**
	* Returns whether this feature is considered to be set <code>orientation</code>
	*/
	boolean isOrientationSet();
	/**
	* Unset the property <code>orientation</code>
	*/
	void unsetOrientation();
	/**
	* Set the property <code>id</code>
	* @param value the value that will be set
	*/
	ButtStrap setId(String value);

}