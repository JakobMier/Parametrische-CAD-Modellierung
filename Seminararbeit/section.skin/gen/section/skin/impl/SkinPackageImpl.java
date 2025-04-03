package section.skin.impl;
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
* 2023-01-10T04:25:56.125+0100
*/
@SuppressWarnings("all")
public class SkinPackageImpl extends DC43Package {
	/**
	* The package name
	*/
	public static final String eNAME = "skin";
	/**
	* The package namespace URI.
	*/
	public static final String eNS_URI = "http://iils.de/dc43/section.skin";
	/**
	* The package namespace name.
	*/
	public static final String eNS_PREFIX = "skin";
	/**
	* The singleton instance of the package.
	*/
	public static final SkinPackageImpl eINSTANCE = section.skin.impl.SkinPackageImpl.init();
	private SkinPackageImpl() {
		super(eNS_URI, ((EFactory)SkinFactoryImpl.eINSTANCE));
	}

	

	private static boolean isInited = false;
	/**
	* Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	*/
	public static SkinPackageImpl init() {
		if (isInited) return (SkinPackageImpl)EPackage.Registry.INSTANCE.getEPackage(SkinPackageImpl.eNS_URI);

		// Obtain or create and register package
		SkinPackageImpl theSkinPackageImpl = (SkinPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SkinPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SkinPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		GraphPackageImpl.eINSTANCE.eClass();
		section.impl.SectionPackageImpl.eINSTANCE.eClass();
		de.iils.dc43.core.geometry.impl.GeometryPackageImpl.eINSTANCE.eClass();
		de.ifb.pigroup.geometry.brep.impl.BrepPackageImpl.eINSTANCE.eClass();

		// Obtain or create and register interdependencies

		// Create package meta-data objects
		theSkinPackageImpl.createPackageContents();

		// Initialize created meta-data
		theSkinPackageImpl.initializePackageContents();

		// Register package validator
		registerValidator(theSkinPackageImpl, new EValidator.Descriptor() {
			public EValidator getEValidator() {
				return SkinConstraints.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theSkinPackageImpl.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SkinPackageImpl.eNS_URI, theSkinPackageImpl);
		return theSkinPackageImpl;
	}
	/**
	* Returns the factory that creates the instances of the model.
	*/
	public SkinFactoryImpl getSkinFactory() {
		return (SkinFactoryImpl)getEFactoryInstance();
	}
	@Override
	public IEquationProvider getConstraintProvider() {
		return SkinConstraints.INSTANCE;
	}
	@Override
	public IRuleProvider getRuleProvider() {
		return section.skin.impl.rules.SkinModule.INSTANCE;
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
		List<DC43Package> libraryPackages = new ArrayList<>();
		libraryPackages.add(de.iils.dc43.core.geometry.impl.GeometryPackageImpl.eINSTANCE);
		referencedDC43LibraryPackages = Collections.unmodifiableList(libraryPackages);


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