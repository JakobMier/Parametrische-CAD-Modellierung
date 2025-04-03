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
public interface Panel extends BaseInstance {

	public static Set<Panel> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(Panel.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getPanel();
	}
	public static EReference reference_Skin() {
		return SectionPackageImpl.eINSTANCE.getPanel_Skin();
	}
	public static EReference reference_Stringer() {
		return SectionPackageImpl.eINSTANCE.getPanel_Stringer();
	}
	public static EReference reference_Spant() {
		return SectionPackageImpl.eINSTANCE.getPanel_Spant();
	}
	public static EReference reference_Clip() {
		return SectionPackageImpl.eINSTANCE.getPanel_Clip();
	}
	public static EAttribute property_Xi() {
		return SectionPackageImpl.eINSTANCE.getPanel_Xi();
	}
	public static EAttribute property_Zi() {
		return SectionPackageImpl.eINSTANCE.getPanel_Zi();
	}
	public static EAttribute property_Surface() {
		return SectionPackageImpl.eINSTANCE.getPanel_Surface();
	}
	/**
	* Create a instance of <code>Panel</code>
	*/
	public static Panel create() {
		return (Panel)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	 * Get the reference <code>skin</code>
	 */
	section.Skin getSkin();
	/**
	* Set the reference <code>skin</code>
	* @param value the instance that will be set
	*/
	Panel setSkin(section.Skin value);
	/**
	* Returns whether this feature is considered to be set <code>skin</code>
	*/
	boolean isSkinSet();
	/**
	* Unset the property <code>skin</code>
	*/
	void unsetSkin();
	/**
	* Get the reference <code>stringer</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.Stringer> getStringer();
	/**
	* Get the reference <code>spant</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.Spant> getSpant();
	/**
	* Get the reference <code>clip</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.Clip> getClip();
	/**
	 * Get the property <code>xi</code>
	 */
	Integer getXi();
	/**
	* Set the property <code>xi</code>
	* @param value the value that will be set
	*/
	Panel setXi(Integer value);
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
	Panel setZi(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>zi</code>
	*/
	boolean isZiSet();
	/**
	* Unset the property <code>zi</code>
	*/
	void unsetZi();
	/**
	 * Get the property <code>surface</code>
	 */
	opencascade.TopoDS_Shape getSurface();
	/**
	* Set the property <code>surface</code>
	* @param value the value that will be set
	*/
	Panel setSurface(opencascade.TopoDS_Shape value);
	/**
	* Returns whether this feature is considered to be set <code>surface</code>
	*/
	boolean isSurfaceSet();
	/**
	* Unset the property <code>surface</code>
	*/
	void unsetSurface();

}