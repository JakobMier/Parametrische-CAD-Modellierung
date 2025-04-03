package section.buttStrap.impl;
import java.util.ArrayList;import java.util.Collections;import java.util.List;import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ETypeParameter;
import de.iils.dc43.core.ecore.IEquationProvider;
import de.iils.dc43.core.ecore.IRuleProvider;
import de.iils.dc43.core.ecore.EAttributeWithUnit;
import de.iils.dc43.core.ecore.DC43Package;
import de.iils.dc43.core.graph.BaseInstance;
import de.iils.dc43.core.graph.impl.GraphPackageImpl;
/**
* The <b>Package</b> for the model.
* 2023-01-04T04:17:08.415+0100
*/
@SuppressWarnings("all")
public class ButtStrapPackageImpl extends DC43Package {
	/**
	* The package name
	*/
	public static final String eNAME = "buttStrap";
	/**
	* The package namespace URI.
	*/
	public static final String eNS_URI = "http://iils.de/dc43/section.buttStrap";
	/**
	* The package namespace name.
	*/
	public static final String eNS_PREFIX = "buttStrap";
	/**
	* The singleton instance of the package.
	*/
	public static final ButtStrapPackageImpl eINSTANCE = section.buttStrap.impl.ButtStrapPackageImpl.init();
	private ButtStrapPackageImpl() {
		super(eNS_URI, ((EFactory)ButtStrapFactoryImpl.eINSTANCE));
	}

	

	private static boolean isInited = false;
	/**
	* Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	*/
	public static ButtStrapPackageImpl init() {
		if (isInited) return (ButtStrapPackageImpl)EPackage.Registry.INSTANCE.getEPackage(ButtStrapPackageImpl.eNS_URI);

		// Obtain or create and register package
		ButtStrapPackageImpl theButtStrapPackageImpl = (ButtStrapPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ButtStrapPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ButtStrapPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		GraphPackageImpl.eINSTANCE.eClass();
		section.impl.SectionPackageImpl.eINSTANCE.eClass();

		// Obtain or create and register interdependencies

		// Create package meta-data objects
		theButtStrapPackageImpl.createPackageContents();

		// Initialize created meta-data
		theButtStrapPackageImpl.initializePackageContents();

		// Register package validator
		registerValidator(theButtStrapPackageImpl, new EValidator.Descriptor() {
			public EValidator getEValidator() {
				return ButtStrapConstraints.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theButtStrapPackageImpl.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ButtStrapPackageImpl.eNS_URI, theButtStrapPackageImpl);
		return theButtStrapPackageImpl;
	}
	/**
	* Returns the factory that creates the instances of the model.
	*/
	public ButtStrapFactoryImpl getButtStrapFactory() {
		return (ButtStrapFactoryImpl)getEFactoryInstance();
	}
	@Override
	public IEquationProvider getConstraintProvider() {
		return ButtStrapConstraints.INSTANCE;
	}
	@Override
	public IRuleProvider getRuleProvider() {
		return section.buttStrap.impl.rules.ButtStrapModule.INSTANCE;
	}
	private List<DC43Package> referencedDC43LibraryPackages;
	@Override
	public List<DC43Package> getReferencedDC43LibraryPackages() {
		return referencedDC43LibraryPackages;
	}
	private boolean isCreated = false;
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		// Create enums
		// Create dataType
	}
	private boolean isInitialized = false;
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);
		// Obtain other dependent packages
		GraphPackageImpl theGraphPackage = (GraphPackageImpl)EPackage.Registry.INSTANCE.getEPackage(GraphPackageImpl.eNS_URI);

		// Collect referenced DC43 library packages
		referencedDC43LibraryPackages = Collections.emptyList();

		// Create type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		EOperation op;
		EGenericType generic;

		// Initialize enums and add enum literals

		// Initialize DataType
		// Create resource
		createResource(eNS_URI);
	}


}