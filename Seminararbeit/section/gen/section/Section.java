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
public interface Section extends BaseInstance {

	public static Set<Section> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(Section.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getSection();
	}
	public static EReference reference_Panel() {
		return SectionPackageImpl.eINSTANCE.getSection_Panel();
	}
	public static EAttributeWithUnit property_ZA() {
		return SectionPackageImpl.eINSTANCE.getSection_ZA();
	}
	public static EAttribute property_NPanelX() {
		return SectionPackageImpl.eINSTANCE.getSection_NPanelX();
	}
	public static EAttribute property_NPanelZ() {
		return SectionPackageImpl.eINSTANCE.getSection_NPanelZ();
	}
	public static EReference reference_Profile() {
		return SectionPackageImpl.eINSTANCE.getSection_Profile();
	}
	public static EAttributeWithUnit property_L() {
		return SectionPackageImpl.eINSTANCE.getSection_L();
	}
	public static EAttributeWithUnit property_H() {
		return SectionPackageImpl.eINSTANCE.getSection_H();
	}
	public static EAttribute property_NStringer() {
		return SectionPackageImpl.eINSTANCE.getSection_NStringer();
	}
	public static EAttribute property_Surface() {
		return SectionPackageImpl.eINSTANCE.getSection_Surface();
	}
	public static EAttribute property_NSpante() {
		return SectionPackageImpl.eINSTANCE.getSection_NSpante();
	}
	public static EReference reference_ButtStrap() {
		return SectionPackageImpl.eINSTANCE.getSection_ButtStrap();
	}
	public static EReference reference_Rivets() {
		return SectionPackageImpl.eINSTANCE.getSection_Rivets();
	}
	public static EAttributeWithUnit property_ButtStrapWidth() {
		return SectionPackageImpl.eINSTANCE.getSection_ButtStrapWidth();
	}
	public static EAttributeWithUnit property_StringerCouplingLenght() {
		return SectionPackageImpl.eINSTANCE.getSection_StringerCouplingLenght();
	}
	public static EReference reference_StringerCoupling() {
		return SectionPackageImpl.eINSTANCE.getSection_StringerCoupling();
	}
	public static EAttributeWithUnit property_ClipFloorWidth() {
		return SectionPackageImpl.eINSTANCE.getSection_ClipFloorWidth();
	}
	public static EAttributeWithUnit property_ClipFloorLenght() {
		return SectionPackageImpl.eINSTANCE.getSection_ClipFloorLenght();
	}
	public static EAttribute property_NRivetRowsBS_v() {
		return SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_v();
	}
	public static EAttribute property_NRivetColumnsBS_v() {
		return SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_v();
	}
	public static EAttribute property_NRivetRowsBS_h() {
		return SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_h();
	}
	public static EAttribute property_NRivetColumnsBS_h() {
		return SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_h();
	}
	public static EAttribute property_NRivetClip_floor() {
		return SectionPackageImpl.eINSTANCE.getSection_NRivetClip_floor();
	}
	public static EAttribute property_NRivetClip_wall1_v() {
		return SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_v();
	}
	public static EAttribute property_NRivetClip_wall1_h() {
		return SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_h();
	}
	public static EAttribute property_NRivetClip_wall2() {
		return SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall2();
	}
	/**
	* Create a instance of <code>Section</code>
	*/
	public static Section create() {
		return (Section)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	* Get the reference <code>panel</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.Panel> getPanel();
	/**
	 * Get the property <code>zA</code> in the unit <code>°</code>
	 * @return {@link NumberQuantity} value with the unit <code>°</code>
	 */
	NumberQuantity getZA();
	/**
	* Set the property <code>zA</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>°</code>
	*/
	Section setZA(NumberQuantity value);
	/**
	* Set the property <code>zA</code> in the unit <code>°</code>
	* @param value the value that will be set
	*/
	Section setZA(Double value);
	/**
	* Returns whether this feature is considered to be set <code>zA</code>
	*/
	boolean isZASet();
	/**
	* Unset the property <code>zA</code>
	*/
	void unsetZA();
	/**
	 * Get the property <code>nPanelX</code>
	 */
	Integer getNPanelX();
	/**
	* Set the property <code>nPanelX</code>
	* @param value the value that will be set
	*/
	Section setNPanelX(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nPanelX</code>
	*/
	boolean isNPanelXSet();
	/**
	* Unset the property <code>nPanelX</code>
	*/
	void unsetNPanelX();
	/**
	 * Get the property <code>nPanelZ</code>
	 */
	Integer getNPanelZ();
	/**
	* Set the property <code>nPanelZ</code>
	* @param value the value that will be set
	*/
	Section setNPanelZ(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nPanelZ</code>
	*/
	boolean isNPanelZSet();
	/**
	* Unset the property <code>nPanelZ</code>
	*/
	void unsetNPanelZ();
	/**
	* Get the reference <code>profile</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.SectionProfile> getProfile();
	/**
	 * Get the property <code>l</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getL();
	/**
	* Set the property <code>l</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	Section setL(NumberQuantity value);
	/**
	* Set the property <code>l</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	Section setL(Double value);
	/**
	* Returns whether this feature is considered to be set <code>l</code>
	*/
	boolean isLSet();
	/**
	* Unset the property <code>l</code>
	*/
	void unsetL();
	/**
	 * Get the property <code>h</code> in the unit <code>°</code>
	 * @return {@link NumberQuantity} value with the unit <code>°</code>
	 */
	NumberQuantity getH();
	/**
	* Set the property <code>h</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>°</code>
	*/
	Section setH(NumberQuantity value);
	/**
	* Set the property <code>h</code> in the unit <code>°</code>
	* @param value the value that will be set
	*/
	Section setH(Double value);
	/**
	* Returns whether this feature is considered to be set <code>h</code>
	*/
	boolean isHSet();
	/**
	* Unset the property <code>h</code>
	*/
	void unsetH();
	/**
	 * Get the property <code>nStringer</code>
	 */
	Integer getNStringer();
	/**
	* Set the property <code>nStringer</code>
	* @param value the value that will be set
	*/
	Section setNStringer(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nStringer</code>
	*/
	boolean isNStringerSet();
	/**
	* Unset the property <code>nStringer</code>
	*/
	void unsetNStringer();
	/**
	 * Get the property <code>surface</code>
	 */
	opencascade.TopoDS_Face getSurface();
	/**
	* Set the property <code>surface</code>
	* @param value the value that will be set
	*/
	Section setSurface(opencascade.TopoDS_Face value);
	/**
	* Returns whether this feature is considered to be set <code>surface</code>
	*/
	boolean isSurfaceSet();
	/**
	* Unset the property <code>surface</code>
	*/
	void unsetSurface();
	/**
	 * Get the property <code>nSpante</code>
	 */
	Integer getNSpante();
	/**
	* Set the property <code>nSpante</code>
	* @param value the value that will be set
	*/
	Section setNSpante(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nSpante</code>
	*/
	boolean isNSpanteSet();
	/**
	* Unset the property <code>nSpante</code>
	*/
	void unsetNSpante();
	/**
	 *
	* Get the reference <code>buttStrap</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.ButtStrap> getButtStrap();
	/**
	* Get the reference <code>rivets</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.Rivet> getRivets();
	/**
	 * Get the property <code>buttStrapWidth</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getButtStrapWidth();
	/**
	* Set the property <code>buttStrapWidth</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	Section setButtStrapWidth(NumberQuantity value);
	/**
	* Set the property <code>buttStrapWidth</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	Section setButtStrapWidth(Double value);
	/**
	* Returns whether this feature is considered to be set <code>buttStrapWidth</code>
	*/
	boolean isButtStrapWidthSet();
	/**
	* Unset the property <code>buttStrapWidth</code>
	*/
	void unsetButtStrapWidth();
	/**
	 * Get the property <code>stringerCouplingLenght</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getStringerCouplingLenght();
	/**
	* Set the property <code>stringerCouplingLenght</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	Section setStringerCouplingLenght(NumberQuantity value);
	/**
	* Set the property <code>stringerCouplingLenght</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	Section setStringerCouplingLenght(Double value);
	/**
	* Returns whether this feature is considered to be set <code>stringerCouplingLenght</code>
	*/
	boolean isStringerCouplingLenghtSet();
	/**
	* Unset the property <code>stringerCouplingLenght</code>
	*/
	void unsetStringerCouplingLenght();
	/**
	* Get the reference <code>stringerCoupling</code>
	* @return a list of instances. Changes of the list will be reflected in the model
	*/
	List<section.StringerCoupling> getStringerCoupling();
	/**
	 * Get the property <code>clipFloorWidth</code> in the unit <code>mm</code>
	 * @return {@link NumberQuantity} value with the unit <code>mm</code>
	 */
	NumberQuantity getClipFloorWidth();
	/**
	* Set the property <code>clipFloorWidth</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>mm</code>
	*/
	Section setClipFloorWidth(NumberQuantity value);
	/**
	* Set the property <code>clipFloorWidth</code> in the unit <code>mm</code>
	* @param value the value that will be set
	*/
	Section setClipFloorWidth(Double value);
	/**
	* Returns whether this feature is considered to be set <code>clipFloorWidth</code>
	*/
	boolean isClipFloorWidthSet();
	/**
	* Unset the property <code>clipFloorWidth</code>
	*/
	void unsetClipFloorWidth();
	/**
	 * Get the property <code>clipFloorLenght</code> in the unit <code>°</code>
	 * @return {@link NumberQuantity} value with the unit <code>°</code>
	 */
	NumberQuantity getClipFloorLenght();
	/**
	* Set the property <code>clipFloorLenght</code>
	* @param value {@link NumberQuantity} value with a unit. Value will be converted to the base unit of the property: <code>°</code>
	*/
	Section setClipFloorLenght(NumberQuantity value);
	/**
	* Set the property <code>clipFloorLenght</code> in the unit <code>°</code>
	* @param value the value that will be set
	*/
	Section setClipFloorLenght(Double value);
	/**
	* Returns whether this feature is considered to be set <code>clipFloorLenght</code>
	*/
	boolean isClipFloorLenghtSet();
	/**
	* Unset the property <code>clipFloorLenght</code>
	*/
	void unsetClipFloorLenght();
	/**
	 * Get the property <code>nRivetRowsBS_v</code>
	 */
	Integer getNRivetRowsBS_v();
	/**
	* Set the property <code>nRivetRowsBS_v</code>
	* @param value the value that will be set
	*/
	Section setNRivetRowsBS_v(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nRivetRowsBS_v</code>
	*/
	boolean isNRivetRowsBS_vSet();
	/**
	* Unset the property <code>nRivetRowsBS_v</code>
	*/
	void unsetNRivetRowsBS_v();
	/**
	 * Get the property <code>nRivetColumnsBS_v</code>
	 */
	Integer getNRivetColumnsBS_v();
	/**
	* Set the property <code>nRivetColumnsBS_v</code>
	* @param value the value that will be set
	*/
	Section setNRivetColumnsBS_v(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nRivetColumnsBS_v</code>
	*/
	boolean isNRivetColumnsBS_vSet();
	/**
	* Unset the property <code>nRivetColumnsBS_v</code>
	*/
	void unsetNRivetColumnsBS_v();
	/**
	 * Get the property <code>nRivetRowsBS_h</code>
	 */
	Integer getNRivetRowsBS_h();
	/**
	* Set the property <code>nRivetRowsBS_h</code>
	* @param value the value that will be set
	*/
	Section setNRivetRowsBS_h(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nRivetRowsBS_h</code>
	*/
	boolean isNRivetRowsBS_hSet();
	/**
	* Unset the property <code>nRivetRowsBS_h</code>
	*/
	void unsetNRivetRowsBS_h();
	/**
	 * Get the property <code>nRivetColumnsBS_h</code>
	 */
	Integer getNRivetColumnsBS_h();
	/**
	* Set the property <code>nRivetColumnsBS_h</code>
	* @param value the value that will be set
	*/
	Section setNRivetColumnsBS_h(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nRivetColumnsBS_h</code>
	*/
	boolean isNRivetColumnsBS_hSet();
	/**
	* Unset the property <code>nRivetColumnsBS_h</code>
	*/
	void unsetNRivetColumnsBS_h();
	/**
	 * Get the property <code>nRivetClip_floor</code>
	 */
	Integer getNRivetClip_floor();
	/**
	* Set the property <code>nRivetClip_floor</code>
	* @param value the value that will be set
	*/
	Section setNRivetClip_floor(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nRivetClip_floor</code>
	*/
	boolean isNRivetClip_floorSet();
	/**
	* Unset the property <code>nRivetClip_floor</code>
	*/
	void unsetNRivetClip_floor();
	/**
	 * Get the property <code>nRivetClip_wall1_v</code>
	 */
	Integer getNRivetClip_wall1_v();
	/**
	* Set the property <code>nRivetClip_wall1_v</code>
	* @param value the value that will be set
	*/
	Section setNRivetClip_wall1_v(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nRivetClip_wall1_v</code>
	*/
	boolean isNRivetClip_wall1_vSet();
	/**
	* Unset the property <code>nRivetClip_wall1_v</code>
	*/
	void unsetNRivetClip_wall1_v();
	/**
	 * Get the property <code>nRivetClip_wall1_h</code>
	 */
	Integer getNRivetClip_wall1_h();
	/**
	* Set the property <code>nRivetClip_wall1_h</code>
	* @param value the value that will be set
	*/
	Section setNRivetClip_wall1_h(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nRivetClip_wall1_h</code>
	*/
	boolean isNRivetClip_wall1_hSet();
	/**
	* Unset the property <code>nRivetClip_wall1_h</code>
	*/
	void unsetNRivetClip_wall1_h();
	/**
	 * Get the property <code>nRivetClip_wall2</code>
	 */
	Integer getNRivetClip_wall2();
	/**
	* Set the property <code>nRivetClip_wall2</code>
	* @param value the value that will be set
	*/
	Section setNRivetClip_wall2(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>nRivetClip_wall2</code>
	*/
	boolean isNRivetClip_wall2Set();
	/**
	* Unset the property <code>nRivetClip_wall2</code>
	*/
	void unsetNRivetClip_wall2();

}