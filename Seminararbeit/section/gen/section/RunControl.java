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
public interface RunControl extends BaseInstance {

	public static Set<RunControl> allInstances() {
		if (DC43Factory.getGraph() != null) {
			return DC43Factory.getGraph().allInstances(RunControl.class);
		}
		return new HashSet<>();
	}
	// accessors for the classifier, properties and references
	public static EClass classifier() {
		return SectionPackageImpl.eINSTANCE.getRunControl();
	}
	public static EAttribute property_LoopCounter() {
		return SectionPackageImpl.eINSTANCE.getRunControl_LoopCounter();
	}
	/**
	* Create a instance of <code>RunControl</code>
	*/
	public static RunControl create() {
		return (RunControl)SectionFactoryImpl.eINSTANCE.create(classifier(), null);
	}
	/**
	 * Get the property <code>loopCounter</code>
	 */
	Integer getLoopCounter();
	/**
	* Set the property <code>loopCounter</code>
	* @param value the value that will be set
	*/
	RunControl setLoopCounter(Integer value);
	/**
	* Returns whether this feature is considered to be set <code>loopCounter</code>
	*/
	boolean isLoopCounterSet();
	/**
	* Unset the property <code>loopCounter</code>
	*/
	void unsetLoopCounter();

}