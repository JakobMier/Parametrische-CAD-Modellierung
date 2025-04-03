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
public class PanelImpl extends BaseInstanceImpl implements Panel {
	protected PanelImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getPanel();
	}
	public section.Skin getSkin() {
		return (section.Skin)eGetWithUnit(SectionPackageImpl.eINSTANCE.getPanel_Skin());
	}
	public section.Panel setSkin(section.Skin value) {
		eSet(SectionPackageImpl.eINSTANCE.getPanel_Skin(), value);
		return this;
	}
	public boolean isSkinSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getPanel_Skin());
	}
	public void unsetSkin() {
		eSet(SectionPackageImpl.eINSTANCE.getPanel_Skin(), null);
	}
	public List<section.Stringer> getStringer() {
		return (List<section.Stringer>)eGet(SectionPackageImpl.eINSTANCE.getPanel_Stringer());
	}
	public List<section.Spant> getSpant() {
		return (List<section.Spant>)eGet(SectionPackageImpl.eINSTANCE.getPanel_Spant());
	}
	public List<section.Clip> getClip() {
		return (List<section.Clip>)eGet(SectionPackageImpl.eINSTANCE.getPanel_Clip());
	}
	public Integer getXi() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getPanel_Xi());
	}
	public section.Panel setXi(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getPanel_Xi(), value);
		return this;
	}
	public boolean isXiSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getPanel_Xi());
	}
	public void unsetXi() {
		eSet(SectionPackageImpl.eINSTANCE.getPanel_Xi(), null);
	}
	public Integer getZi() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getPanel_Zi());
	}
	public section.Panel setZi(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getPanel_Zi(), value);
		return this;
	}
	public boolean isZiSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getPanel_Zi());
	}
	public void unsetZi() {
		eSet(SectionPackageImpl.eINSTANCE.getPanel_Zi(), null);
	}
	public opencascade.TopoDS_Shape getSurface() {
		return (opencascade.TopoDS_Shape)eGetWithUnit(SectionPackageImpl.eINSTANCE.getPanel_Surface());
	}
	public section.Panel setSurface(opencascade.TopoDS_Shape value) {
		eSet(SectionPackageImpl.eINSTANCE.getPanel_Surface(), value);
		return this;
	}
	public boolean isSurfaceSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getPanel_Surface());
	}
	public void unsetSurface() {
		eSet(SectionPackageImpl.eINSTANCE.getPanel_Surface(), null);
	}
}