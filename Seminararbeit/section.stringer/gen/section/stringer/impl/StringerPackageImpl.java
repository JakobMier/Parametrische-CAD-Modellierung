package section.stringer.impl;
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
* 2023-02-28T23:18:49.477+0100
*/
@SuppressWarnings("all")
public class StringerPackageImpl extends DC43Package {
	/**
	* The package name
	*/
	public static final String eNAME = "stringer";
	/**
	* The package namespace URI.
	*/
	public static final String eNS_URI = "http://iils.de/dc43/section.stringer";
	/**
	* The package namespace name.
	*/
	public static final String eNS_PREFIX = "stringer";
	/**
	* The singleton instance of the package.
	*/
	public static final StringerPackageImpl eINSTANCE = section.stringer.impl.StringerPackageImpl.init();
	private StringerPackageImpl() {
		super(eNS_URI, ((EFactory)StringerFactoryImpl.eINSTANCE));
	}

	

	private static boolean isInited = false;
	/**
	* Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	*/
	public static StringerPackageImpl init() {
		if (isInited) return (StringerPackageImpl)EPackage.Registry.INSTANCE.getEPackage(StringerPackageImpl.eNS_URI);

		// Obtain or create and register package
		StringerPackageImpl theStringerPackageImpl = (StringerPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StringerPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StringerPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		GraphPackageImpl.eINSTANCE.eClass();
		section.impl.SectionPackageImpl.eINSTANCE.eClass();

		// Obtain or create and register interdependencies

		// Create package meta-data objects
		theStringerPackageImpl.createPackageContents();

		// Initialize created meta-data
		theStringerPackageImpl.initializePackageContents();

		// Register package validator
		registerValidator(theStringerPackageImpl, new EValidator.Descriptor() {
			public EValidator getEValidator() {
				return StringerConstraints.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theStringerPackageImpl.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StringerPackageImpl.eNS_URI, theStringerPackageImpl);
		return theStringerPackageImpl;
	}
	/**
	* Returns the factory that creates the instances of the model.
	*/
	public StringerFactoryImpl getStringerFactory() {
		return (StringerFactoryImpl)getEFactoryInstance();
	}
	@Override
	public IEquationProvider getConstraintProvider() {
		return StringerConstraints.INSTANCE;
	}
	@Override
	public IRuleProvider getRuleProvider() {
		return section.stringer.impl.rules.StringerModule.INSTANCE;
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