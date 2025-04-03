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
public class RunControlImpl extends BaseInstanceImpl implements RunControl {
	protected RunControlImpl() {
		super();
	}
	@Override
	protected EClass eStaticClass() {
		return SectionPackageImpl.eINSTANCE.getRunControl();
	}
	public Integer getLoopCounter() {
		return (Integer)eGet(SectionPackageImpl.eINSTANCE.getRunControl_LoopCounter());
	}
	public section.RunControl setLoopCounter(Integer value) {
		eSet(SectionPackageImpl.eINSTANCE.getRunControl_LoopCounter(), value);
		return this;
	}
	public boolean isLoopCounterSet() {
		return eIsSet(SectionPackageImpl.eINSTANCE.getRunControl_LoopCounter());
	}
	public void unsetLoopCounter() {
		eSet(SectionPackageImpl.eINSTANCE.getRunControl_LoopCounter(), null);
	}
}