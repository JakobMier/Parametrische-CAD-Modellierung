package section.panel.impl;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import de.iils.dc43.core.ecore.DC43Factory;
import tec.uom.se.*;
import section.panel.*;

/**
* The implementation of the model <b>Factory</b>.
* 2023-01-04T04:09:18.596+0100
*/
@SuppressWarnings("all")
public class PanelFactoryImpl extends DC43Factory {
	/**
	* The singleton instance of the factory.
	*/
	public static final PanelFactoryImpl eINSTANCE = init();

	/**
	* Creates the default factory implementation.
	*/
	public static PanelFactoryImpl init() {
		try {
			PanelFactoryImpl factory = (PanelFactoryImpl)EPackage.Registry.INSTANCE.getEFactory(PanelPackageImpl.eNS_URI);
			if (factory instanceof PanelFactoryImpl) {
				return factory;
			}
		}
		catch(Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PanelFactoryImpl();
	}
	public PanelFactoryImpl() {
		super();
	}
	public PanelPackageImpl getPanelPackage() {
		return (PanelPackageImpl)getEPackage();
	}
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()){
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	@Override
	public EObject create(EClass eClass, Object... arguments) {
		switch (eClass.getClassifierID()){
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()){
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()){
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}
}