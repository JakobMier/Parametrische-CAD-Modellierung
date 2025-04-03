package section.impl;
import section.*;
import section.impl.SectionPackageImpl;
import java.util.List;
import tec.uom.se.*;
import static tec.uom.se.quantity.Quantities.*;
import tec.uom.se.format.SimpleUnitFormat;
import org.apache.commons.math3.linear.RealMatrix;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.common.util.EList;
import java.lang.reflect.InvocationTargetException;
import de.iils.dc43.core.graph.impl.BaseInstanceImpl;
@SuppressWarnings("all")
public class SectionImpl extends BaseInstanceImpl implements Section {
	protected SectionImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getSection();
	}
	public List<section.Panel> getPanel() {
		return (List<section.Panel>)eGet(SectionPackageImpl.eINSTANCE.getSection_Panel());
	}
	public NumberQuantity getZA() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSection_ZA());
	}
	public section.Section setZA(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ZA(), value);
		return this;
	}
	public section.Section setZA(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ZA(), value);
		return this;
	}
	public boolean isZASet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_ZA());
	}
	public void unsetZA() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ZA(), null);
	}
	public Integer getNPanelX() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NPanelX());
	}
	public section.Section setNPanelX(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NPanelX(), value);
		return this;
	}
	public boolean isNPanelXSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NPanelX());
	}
	public void unsetNPanelX() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NPanelX(), null);
	}
	public Integer getNPanelZ() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NPanelZ());
	}
	public section.Section setNPanelZ(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NPanelZ(), value);
		return this;
	}
	public boolean isNPanelZSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NPanelZ());
	}
	public void unsetNPanelZ() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NPanelZ(), null);
	}
	public List<section.SectionProfile> getProfile() {
		return (List<section.SectionProfile>)eGet(SectionPackageImpl.eINSTANCE.getSection_Profile());
	}
	public NumberQuantity getL() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSection_L());
	}
	public section.Section setL(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_L(), value);
		return this;
	}
	public section.Section setL(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_L(), value);
		return this;
	}
	public boolean isLSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_L());
	}
	public void unsetL() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_L(), null);
	}
	public NumberQuantity getH() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSection_H());
	}
	public section.Section setH(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_H(), value);
		return this;
	}
	public section.Section setH(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_H(), value);
		return this;
	}
	public boolean isHSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_H());
	}
	public void unsetH() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_H(), null);
	}
	public Integer getNStringer() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NStringer());
	}
	public section.Section setNStringer(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NStringer(), value);
		return this;
	}
	public boolean isNStringerSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NStringer());
	}
	public void unsetNStringer() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NStringer(), null);
	}
	public opencascade.TopoDS_Face getSurface() {
		return (opencascade.TopoDS_Face)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSection_Surface());
	}
	public section.Section setSurface(opencascade.TopoDS_Face value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_Surface(), value);
		return this;
	}
	public boolean isSurfaceSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_Surface());
	}
	public void unsetSurface() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_Surface(), null);
	}
	public Integer getNSpante() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NSpante());
	}
	public section.Section setNSpante(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NSpante(), value);
		return this;
	}
	public boolean isNSpanteSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NSpante());
	}
	public void unsetNSpante() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NSpante(), null);
	}
	public List<section.ButtStrap> getButtStrap() {
		return (List<section.ButtStrap>)eGet(SectionPackageImpl.eINSTANCE.getSection_ButtStrap());
	}
	public List<section.Rivet> getRivets() {
		return (List<section.Rivet>)eGet(SectionPackageImpl.eINSTANCE.getSection_Rivets());
	}
	public NumberQuantity getButtStrapWidth() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSection_ButtStrapWidth());
	}
	public section.Section setButtStrapWidth(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ButtStrapWidth(), value);
		return this;
	}
	public section.Section setButtStrapWidth(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ButtStrapWidth(), value);
		return this;
	}
	public boolean isButtStrapWidthSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_ButtStrapWidth());
	}
	public void unsetButtStrapWidth() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ButtStrapWidth(), null);
	}
	public NumberQuantity getStringerCouplingLenght() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSection_StringerCouplingLenght());
	}
	public section.Section setStringerCouplingLenght(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_StringerCouplingLenght(), value);
		return this;
	}
	public section.Section setStringerCouplingLenght(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_StringerCouplingLenght(), value);
		return this;
	}
	public boolean isStringerCouplingLenghtSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_StringerCouplingLenght());
	}
	public void unsetStringerCouplingLenght() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_StringerCouplingLenght(), null);
	}
	public List<section.StringerCoupling> getStringerCoupling() {
		return (List<section.StringerCoupling>)eGet(SectionPackageImpl.eINSTANCE.getSection_StringerCoupling());
	}
	public NumberQuantity getClipFloorWidth() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSection_ClipFloorWidth());
	}
	public section.Section setClipFloorWidth(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ClipFloorWidth(), value);
		return this;
	}
	public section.Section setClipFloorWidth(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ClipFloorWidth(), value);
		return this;
	}
	public boolean isClipFloorWidthSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_ClipFloorWidth());
	}
	public void unsetClipFloorWidth() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ClipFloorWidth(), null);
	}
	public NumberQuantity getClipFloorLenght() {
		return (NumberQuantity)eGetWithUnit(SectionPackageImpl.eINSTANCE.getSection_ClipFloorLenght());
	}
	public section.Section setClipFloorLenght(NumberQuantity value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ClipFloorLenght(), value);
		return this;
	}
	public section.Section setClipFloorLenght(Double value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ClipFloorLenght(), value);
		return this;
	}
	public boolean isClipFloorLenghtSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_ClipFloorLenght());
	}
	public void unsetClipFloorLenght() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_ClipFloorLenght(), null);
	}
	public Integer getNRivetRowsBS_v() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_v());
	}
	public section.Section setNRivetRowsBS_v(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_v(), value);
		return this;
	}
	public boolean isNRivetRowsBS_vSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_v());
	}
	public void unsetNRivetRowsBS_v() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_v(), null);
	}
	public Integer getNRivetColumnsBS_v() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_v());
	}
	public section.Section setNRivetColumnsBS_v(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_v(), value);
		return this;
	}
	public boolean isNRivetColumnsBS_vSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_v());
	}
	public void unsetNRivetColumnsBS_v() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_v(), null);
	}
	public Integer getNRivetRowsBS_h() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_h());
	}
	public section.Section setNRivetRowsBS_h(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_h(), value);
		return this;
	}
	public boolean isNRivetRowsBS_hSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_h());
	}
	public void unsetNRivetRowsBS_h() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetRowsBS_h(), null);
	}
	public Integer getNRivetColumnsBS_h() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_h());
	}
	public section.Section setNRivetColumnsBS_h(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_h(), value);
		return this;
	}
	public boolean isNRivetColumnsBS_hSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_h());
	}
	public void unsetNRivetColumnsBS_h() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetColumnsBS_h(), null);
	}
	public Integer getNRivetClip_floor() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_floor());
	}
	public section.Section setNRivetClip_floor(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_floor(), value);
		return this;
	}
	public boolean isNRivetClip_floorSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_floor());
	}
	public void unsetNRivetClip_floor() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_floor(), null);
	}
	public Integer getNRivetClip_wall1_v() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_v());
	}
	public section.Section setNRivetClip_wall1_v(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_v(), value);
		return this;
	}
	public boolean isNRivetClip_wall1_vSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_v());
	}
	public void unsetNRivetClip_wall1_v() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_v(), null);
	}
	public Integer getNRivetClip_wall1_h() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_h());
	}
	public section.Section setNRivetClip_wall1_h(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_h(), value);
		return this;
	}
	public boolean isNRivetClip_wall1_hSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_h());
	}
	public void unsetNRivetClip_wall1_h() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall1_h(), null);
	}
	public Integer getNRivetClip_wall2() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall2());
	}
	public section.Section setNRivetClip_wall2(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall2(), value);
		return this;
	}
	public boolean isNRivetClip_wall2Set() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall2());
	}
	public void unsetNRivetClip_wall2() {
		eSet(SectionPackageImpl.eINSTANCE.getSection_NRivetClip_wall2(), null);
	}
}