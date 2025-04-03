package section.panel.impl;
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
* 2023-01-04T04:09:18.635+0100
*/
@SuppressWarnings("all")
public class PanelPackageImpl extends DC43Package {
	/**
	* The package name
	*/
	public static final String eNAME = "panel";
	/**
	* The package namespace URI.
	*/
	public static final String eNS_URI = "http://iils.de/dc43/section.panel";
	/**
	* The package namespace name.
	*/
	public static final String eNS_PREFIX = "panel";
	/**
	* The singleton instance of the package.
	*/
	public static final PanelPackageImpl eINSTANCE = section.panel.impl.PanelPackageImpl.init();
	private PanelPackageImpl() {
		super(eNS_URI, ((EFactory)PanelFactoryImpl.eINSTANCE));
	}

	

	private static boolean isInited = false;
	/**
	* Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	*/
	public static PanelPackageImpl init() {
		if (isInited) return (PanelPackageImpl)EPackage.Registry.INSTANCE.getEPackage(PanelPackageImpl.eNS_URI);

		// Obtain or create and register package
		PanelPackageImpl thePanelPackageImpl = (PanelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PanelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PanelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		GraphPackageImpl.eINSTANCE.eClass();
		section.impl.SectionPackageImpl.eINSTANCE.eClass();
		de.iils.dc43.core.geometry.impl.GeometryPackageImpl.eINSTANCE.eClass();

		// Obtain or create and register interdependencies

		// Create package meta-data objects
		thePanelPackageImpl.createPackageContents();

		// Initialize created meta-data
		thePanelPackageImpl.initializePackageContents();

		// Register package validator
		registerValidator(thePanelPackageImpl, new EValidator.Descriptor() {
			public EValidator getEValidator() {
				return PanelConstraints.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		thePanelPackageImpl.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PanelPackageImpl.eNS_URI, thePanelPackageImpl);
		return thePanelPackageImpl;
	}
	/**
	* Returns the factory that creates the instances of the model.
	*/
	public PanelFactoryImpl getPanelFactory() {
		return (PanelFactoryImpl)getEFactoryInstance();
	}
	@Override
	public IEquationProvider getConstraintProvider() {
		return PanelConstraints.INSTANCE;
	}
	@Override
	public IRuleProvider getRuleProvider() {
		return section.panel.impl.rules.PanelModule.INSTANCE;
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