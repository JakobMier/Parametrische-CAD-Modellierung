package section.assembly.impl;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import de.iils.dc43.core.ecore.DC43Factory;
import tec.uom.se.*;
import section.assembly.*;

/**
* The implementation of the model <b>Factory</b>.
* 2023-02-28T23:07:00.208+0100
*/
@SuppressWarnings("all")
public class AssemblyFactoryImpl extends DC43Factory {
	/**
	* The singleton instance of the factory.
	*/
	public static final AssemblyFactoryImpl eINSTANCE = init();

	/**
	* Creates the default factory implementation.
	*/
	public static AssemblyFactoryImpl init() {
		try {
			AssemblyFactoryImpl factory = (AssemblyFactoryImpl)EPackage.Registry.INSTANCE.getEFactory(AssemblyPackageImpl.eNS_URI);
			if (factory instanceof AssemblyFactoryImpl) {
				return factory;
			}
		}
		catch(Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AssemblyFactoryImpl();
	}
	public AssemblyFactoryImpl() {
		super();
	}
	public AssemblyPackageImpl getAssemblyPackage() {
		return (AssemblyPackageImpl)getEPackage();
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