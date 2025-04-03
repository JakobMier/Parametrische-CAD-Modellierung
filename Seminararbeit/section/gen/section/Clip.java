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
public interface Clip extends de.iils.dc43.core.geometry.publication.Component {

	public static Set<Clip> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(Clip.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getClip();
	}
	public static EAttributeWithUnit property_Hieght() {
		return SectionPackageImpl.eINSTANCE.getClip_Hieght();
	}
	public static EAttribute property_Xi() {
		return SectionPackageImpl.eINSTANCE.getClip_Xi();
	}
	public static EAttribute property_Zi() {
		return SectionPackageImpl.eINSTANCE.getClip_Zi();
	}
	public static EReference reference_Spant() {
		return SectionPackageImpl.eINSTANCE.getClip_Spant();
	}
	public static EReference reference_Stringer() {
		return SectionPackageImpl.eINSTANCE.getClip_Stringer();
	}
	public static EReference reference_Skin() {
		return SectionPackageImpl.eINSTANCE.getClip_Skin();
	}
	/**
	* Create a instance of <code>Clip</code>
	*/
	public static Clip create() {
		return (Clip)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	 * Get the property <code>hieght</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getHieght();
	/**
	* Set the property <code>hieght</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	Clip setHieght(NumberQuantity value);
	/**
	* Set the property <code>hieght</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	Clip setHieght(Double value);
	/**
	* Returns whether this feature is considered to be set <code>hieght</code>
	*/
	boolean isHieghtSet();
	/**
	* Unset the property <code>hieght</code>
	*/
	void unsetHieght();
	/**
	 * Get the property <code>xi</code>
	 */
	Integer getXi();
	/**
	* Set the property <code>xi</code>
	* @param value the value that will be set
	*/
	Clip setXi(Integer value);
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
	Clip setZi(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>zi</code>
	*/
	boolean isZiSet();
	/**
	* Unset the property <code>zi</code>
	*/
	void unsetZi();
	/**
	 * Get the reference <code>spant</code>
	 */
	section.Spant getSpant();
	/**
	* Set the reference <code>spant</code>
	* @param value the instance that will be set
	*/
	Clip setSpant(section.Spant value);
	/**
	* Returns whether this feature is considered to be set <code>spant</code>
	*/
	boolean isSpantSet();
	/**
	* Unset the property <code>spant</code>
	*/
	void unsetSpant();
	/**
	 * Get the reference <code>stringer</code>
	 */
	section.Stringer getStringer();
	/**
	* Set the reference <code>stringer</code>
	* @param value the instance that will be set
	*/
	Clip setStringer(section.Stringer value);
	/**
	* Returns whether this feature is considered to be set <code>stringer</code>
	*/
	boolean isStringerSet();
	/**
	* Unset the property <code>stringer</code>
	*/
	void unsetStringer();
	/**
	 * Get the reference <code>skin</code>
	 */
	section.Skin getSkin();
	/**
	* Set the reference <code>skin</code>
	* @param value the instance that will be set
	*/
	Clip setSkin(section.Skin value);
	/**
	* Returns whether this feature is considered to be set <code>skin</code>
	*/
	boolean isSkinSet();
	/**
	* Unset the property <code>skin</code>
	*/
	void unsetSkin();
	/**
	* Set the property <code>id</code>
	* @param value the value that will be set
	*/
	Clip setId(String value);

}