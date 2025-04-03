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
public interface Rivet extends de.iils.dc43.core.geometry.publication.Component {

	public static Set<Rivet> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(Rivet.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getRivet();
	}
	public static EReference reference_StringerCoupling() {
		return SectionPackageImpl.eINSTANCE.getRivet_StringerCoupling();
	}
	public static EReference reference_Stringer() {
		return SectionPackageImpl.eINSTANCE.getRivet_Stringer();
	}
	public static EReference reference_Clip() {
		return SectionPackageImpl.eINSTANCE.getRivet_Clip();
	}
	public static EReference reference_Spant() {
		return SectionPackageImpl.eINSTANCE.getRivet_Spant();
	}
	public static EReference reference_ButtStrap() {
		return SectionPackageImpl.eINSTANCE.getRivet_ButtStrap();
	}
	public static EReference reference_Skin() {
		return SectionPackageImpl.eINSTANCE.getRivet_Skin();
	}
	/**
	* Create a instance of <code>Rivet</code>
	*/
	public static Rivet create() {
		return (Rivet)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	 * Get the reference <code>stringerCoupling</code>
	 */
	section.StringerCoupling getStringerCoupling();
	/**
	* Set the reference <code>stringerCoupling</code>
	* @param value the instance that will be set
	*/
	Rivet setStringerCoupling(section.StringerCoupling value);
	/**
	* Returns whether this feature is considered to be set <code>stringerCoupling</code>
	*/
	boolean isStringerCouplingSet();
	/**
	* Unset the property <code>stringerCoupling</code>
	*/
	void unsetStringerCoupling();
	/**
	 * Get the reference <code>stringer</code>
	 */
	section.Stringer getStringer();
	/**
	* Set the reference <code>stringer</code>
	* @param value the instance that will be set
	*/
	Rivet setStringer(section.Stringer value);
	/**
	* Returns whether this feature is considered to be set <code>stringer</code>
	*/
	boolean isStringerSet();
	/**
	* Unset the property <code>stringer</code>
	*/
	void unsetStringer();
	/**
	 * Get the reference <code>clip</code>
	 */
	section.Clip getClip();
	/**
	* Set the reference <code>clip</code>
	* @param value the instance that will be set
	*/
	Rivet setClip(section.Clip value);
	/**
	* Returns whether this feature is considered to be set <code>clip</code>
	*/
	boolean isClipSet();
	/**
	* Unset the property <code>clip</code>
	*/
	void unsetClip();
	/**
	 * Get the reference <code>spant</code>
	 */
	section.Spant getSpant();
	/**
	* Set the reference <code>spant</code>
	* @param value the instance that will be set
	*/
	Rivet setSpant(section.Spant value);
	/**
	* Returns whether this feature is considered to be set <code>spant</code>
	*/
	boolean isSpantSet();
	/**
	* Unset the property <code>spant</code>
	*/
	void unsetSpant();
	/**
	 * Get the reference <code>buttStrap</code>
	 */
	section.ButtStrap getButtStrap();
	/**
	* Set the reference <code>buttStrap</code>
	* @param value the instance that will be set
	*/
	Rivet setButtStrap(section.ButtStrap value);
	/**
	* Returns whether this feature is considered to be set <code>buttStrap</code>
	*/
	boolean isButtStrapSet();
	/**
	* Unset the property <code>buttStrap</code>
	*/
	void unsetButtStrap();
	/**
	 * Get the reference <code>skin</code>
	 */
	section.Skin getSkin();
	/**
	* Set the reference <code>skin</code>
	* @param value the instance that will be set
	*/
	Rivet setSkin(section.Skin value);
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
	Rivet setId(String value);

}