package section.stringerCoupling.impl;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import de.iils.dc43.core.ecore.DC43Factory;
import tec.uom.se.*;
import section.stringerCoupling.*;

/**
* The implementation of the model <b>Factory</b>.
* 2023-01-04T04:20:07.899+0100
*/
@SuppressWarnings("all")
public class StringerCouplingFactoryImpl extends DC43Factory {
	/**
	* The singleton instance of the factory.
	*/
	public static final StringerCouplingFactoryImpl eINSTANCE = init();

	/**
	* Creates the default factory implementation.
	*/
	public static StringerCouplingFactoryImpl init() {
		try {
			StringerCouplingFactoryImpl factory = (StringerCouplingFactoryImpl)EPackage.Registry.INSTANCE.getEFactory(StringerCouplingPackageImpl.eNS_URI);
			if (factory instanceof StringerCouplingFactoryImpl) {
				return factory;
			}
		}
		catch(Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StringerCouplingFactoryImpl();
	}
	public StringerCouplingFactoryImpl() {
		super();
	}
	public StringerCouplingPackageImpl getStringerCouplingPackage() {
		return (StringerCouplingPackageImpl)getEPackage();
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