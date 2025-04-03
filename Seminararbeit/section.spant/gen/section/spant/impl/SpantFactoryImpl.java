package section.spant.impl;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import de.iils.dc43.core.ecore.DC43Factory;
import tec.uom.se.*;
import section.spant.*;

/**
* The implementation of the model <b>Factory</b>.
* 2023-01-12T04:00:13.262+0100
*/
@SuppressWarnings("all")
public class SpantFactoryImpl extends DC43Factory {
	/**
	* The singleton instance of the factory.
	*/
	public static final SpantFactoryImpl eINSTANCE = init();

	/**
	* Creates the default factory implementation.
	*/
	public static SpantFactoryImpl init() {
		try {
			SpantFactoryImpl factory = (SpantFactoryImpl)EPackage.Registry.INSTANCE.getEFactory(SpantPackageImpl.eNS_URI);
			if (factory instanceof SpantFactoryImpl) {
				return factory;
			}
		}
		catch(Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SpantFactoryImpl();
	}
	public SpantFactoryImpl() {
		super();
	}
	public SpantPackageImpl getSpantPackage() {
		return (SpantPackageImpl)getEPackage();
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