package section.impl;
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
* 2023-01-10T01:54:25.844+0100
*/
@SuppressWarnings("all")
public class SectionPackageImpl extends DC43Package {
	/**
	* The package name
	*/
	public static final String eNAME = "section";
	/**
	* The package namespace URI.
	*/
	public static final String eNS_URI = "http://iils.de/dc43/section";
	/**
	* The package namespace name.
	*/
	public static final String eNS_PREFIX = "section";
	/**
	* The singleton instance of the package.
	*/
	public static final SectionPackageImpl eINSTANCE = section.impl.SectionPackageImpl.init();
	private SectionPackageImpl() {
		super(eNS_URI, ((EFactory)SectionFactoryImpl.eINSTANCE));
	}
	public static final int SECTION = 0;
	public static final int SECTION__PANEL = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 0;
	public static final int SECTION__ZA = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 1;
	public static final int SECTION__NPANEL_X = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 2;
	public static final int SECTION__NPANEL_Z = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 3;
	public static final int SECTION__PROFILE = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 4;
	public static final int SECTION__L = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 5;
	public static final int SECTION__H = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 6;
	public static final int SECTION__NSTRINGER = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 7;
	public static final int SECTION__SURFACE = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 8;
	public static final int SECTION__NSPANTE = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 9;
	public static final int SECTION__BUTT_STRAP = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 10;
	public static final int SECTION__RIVETS = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 11;
	public static final int SECTION__BUTT_STRAP_WIDTH = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 12;
	public static final int SECTION__STRINGER_COUPLING_LENGHT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 13;
	public static final int SECTION__STRINGER_COUPLING = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 14;
	public static final int SECTION__CLIP_FLOOR_WIDTH = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 15;
	public static final int SECTION__CLIP_FLOOR_LENGHT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 16;
	public static final int SECTION__NRIVET_ROWS_BS_V = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 17;
	public static final int SECTION__NRIVET_COLUMNS_BS_V = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 18;
	public static final int SECTION__NRIVET_ROWS_BS_H = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 19;
	public static final int SECTION__NRIVET_COLUMNS_BS_H = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 20;
	public static final int SECTION__NRIVET_CLIP_FLOOR = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 21;
	public static final int SECTION__NRIVET_CLIP_WALL1_V = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 22;
	public static final int SECTION__NRIVET_CLIP_WALL1_H = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 23;
	public static final int SECTION__NRIVET_CLIP_WALL2 = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 24;
	public static final int SECTION_FEATURE_COUNT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 25;
	public static final int SECTION_OPERATION_COUNT = GraphPackageImpl.BASE_INSTANCE_OPERATION_COUNT + 0;
	public static final int PANEL = 1;
	public static final int PANEL__SKIN = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 0;
	public static final int PANEL__STRINGER = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 1;
	public static final int PANEL__SPANT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 2;
	public static final int PANEL__CLIP = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 3;
	public static final int PANEL__XI = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 4;
	public static final int PANEL__ZI = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 5;
	public static final int PANEL__SURFACE = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 6;
	public static final int PANEL_FEATURE_COUNT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 7;
	public static final int PANEL_OPERATION_COUNT = GraphPackageImpl.BASE_INSTANCE_OPERATION_COUNT + 0;
	public static final int SECTION_PROFILE = 2;
	public static final int SECTION_PROFILE__R1A = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 0;
	public static final int SECTION_PROFILE__R2A = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 1;
	public static final int SECTION_PROFILE__HA = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 2;
	public static final int SECTION_PROFILE__I = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 3;
	public static final int SECTION_PROFILE__PROFILE = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 4;
	public static final int SECTION_PROFILE_FEATURE_COUNT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 5;
	public static final int SECTION_PROFILE_OPERATION_COUNT = GraphPackageImpl.BASE_INSTANCE_OPERATION_COUNT + 0;
	public static final int TOPO_DS_SHAPE = 3;
	public static final int WIRE = 4;
	public static final int TOPO_DS_FACE = 5;
	public static final int STRINGER_PROFILE = 6;
	public static final int STRINGER_PROFILE__PROFILE = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 0;
	public static final int STRINGER_PROFILE__HEIGHT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 1;
	public static final int STRINGER_PROFILE_FEATURE_COUNT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 2;
	public static final int STRINGER_PROFILE_OPERATION_COUNT = GraphPackageImpl.BASE_INSTANCE_OPERATION_COUNT + 0;
	public static final int SPLINE = 7;
	public static final int SPANT_PROFILE = 8;
	public static final int SPANT_PROFILE__PROFILE = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 0;
	public static final int SPANT_PROFILE__HEIGHT_FOR_CLIP = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 1;
	public static final int SPANT_PROFILE_FEATURE_COUNT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 2;
	public static final int SPANT_PROFILE_OPERATION_COUNT = GraphPackageImpl.BASE_INSTANCE_OPERATION_COUNT + 0;
	public static final int GEOM_BSPLINE_CURVE = 9;
	public static final int RUN_CONTROL = 10;
	public static final int RUN_CONTROL__LOOP_COUNTER = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 0;
	public static final int RUN_CONTROL_FEATURE_COUNT = GraphPackageImpl.BASE_INSTANCE_FEATURE_COUNT + 1;
	public static final int RUN_CONTROL_OPERATION_COUNT = GraphPackageImpl.BASE_INSTANCE_OPERATION_COUNT + 0;
	public static final int SKIN = 11;
	public static final int SKIN__COLOR = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__COLOR;
	public static final int SKIN__SHAPE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__SHAPE;
	public static final int SKIN__EXISTING_COMPONENT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__EXISTING_COMPONENT;
	public static final int SKIN__USER_DEFINED_FEATURE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__USER_DEFINED_FEATURE;
	public static final int SKIN__SUB = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__SUB;
	public static final int SKIN__ID = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__ID;
	public static final int SKIN__TS = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 0;
	public static final int SKIN__BUTT_STRAP = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 1;
	public static final int SKIN_FEATURE_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 2;
	public static final int SKIN_OPERATION_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_OPERATION_COUNT + 0;
	public static final int SPANT = 12;
	public static final int SPANT__COLOR = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__COLOR;
	public static final int SPANT__SHAPE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__SHAPE;
	public static final int SPANT__EXISTING_COMPONENT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__EXISTING_COMPONENT;
	public static final int SPANT__USER_DEFINED_FEATURE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__USER_DEFINED_FEATURE;
	public static final int SPANT__SUB = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__SUB;
	public static final int SPANT__ID = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__ID;
	public static final int SPANT__SPANT_PROFILE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 0;
	public static final int SPANT__I = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 1;
	public static final int SPANT__CLIP = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 2;
	public static final int SPANT_FEATURE_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 3;
	public static final int SPANT_OPERATION_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_OPERATION_COUNT + 0;
	public static final int STRINGER = 13;
	public static final int STRINGER__COLOR = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__COLOR;
	public static final int STRINGER__SHAPE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__SHAPE;
	public static final int STRINGER__EXISTING_COMPONENT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__EXISTING_COMPONENT;
	public static final int STRINGER__USER_DEFINED_FEATURE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__USER_DEFINED_FEATURE;
	public static final int STRINGER__SUB = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__SUB;
	public static final int STRINGER__ID = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__ID;
	public static final int STRINGER__STRINGER_PROFILE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 0;
	public static final int STRINGER__I = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 1;
	public static final int STRINGER__STRINGER_COUPLING = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 2;
	public static final int STRINGER_FEATURE_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 3;
	public static final int STRINGER_OPERATION_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_OPERATION_COUNT + 0;
	public static final int CLIP = 14;
	public static final int CLIP__COLOR = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__COLOR;
	public static final int CLIP__SHAPE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__SHAPE;
	public static final int CLIP__EXISTING_COMPONENT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__EXISTING_COMPONENT;
	public static final int CLIP__USER_DEFINED_FEATURE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__USER_DEFINED_FEATURE;
	public static final int CLIP__SUB = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__SUB;
	public static final int CLIP__ID = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__ID;
	public static final int CLIP__HIEGHT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 0;
	public static final int CLIP__XI = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 1;
	public static final int CLIP__ZI = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 2;
	public static final int CLIP__SPANT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 3;
	public static final int CLIP__STRINGER = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 4;
	public static final int CLIP__SKIN = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 5;
	public static final int CLIP_FEATURE_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 6;
	public static final int CLIP_OPERATION_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_OPERATION_COUNT + 0;
	public static final int BUTT_STRAP = 15;
	public static final int BUTT_STRAP__COLOR = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__COLOR;
	public static final int BUTT_STRAP__SHAPE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__SHAPE;
	public static final int BUTT_STRAP__EXISTING_COMPONENT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__EXISTING_COMPONENT;
	public static final int BUTT_STRAP__USER_DEFINED_FEATURE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__USER_DEFINED_FEATURE;
	public static final int BUTT_STRAP__SUB = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__SUB;
	public static final int BUTT_STRAP__ID = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__ID;
	public static final int BUTT_STRAP__SKIN = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 0;
	public static final int BUTT_STRAP__XI = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 1;
	public static final int BUTT_STRAP__ZI = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 2;
	public static final int BUTT_STRAP__ORIENTATION = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 3;
	public static final int BUTT_STRAP_FEATURE_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 4;
	public static final int BUTT_STRAP_OPERATION_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_OPERATION_COUNT + 0;
	public static final int STRINGER_COUPLING = 16;
	public static final int STRINGER_COUPLING__COLOR = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__COLOR;
	public static final int STRINGER_COUPLING__SHAPE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__SHAPE;
	public static final int STRINGER_COUPLING__EXISTING_COMPONENT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__EXISTING_COMPONENT;
	public static final int STRINGER_COUPLING__USER_DEFINED_FEATURE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__USER_DEFINED_FEATURE;
	public static final int STRINGER_COUPLING__SUB = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__SUB;
	public static final int STRINGER_COUPLING__ID = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__ID;
	public static final int STRINGER_COUPLING__STRINGER = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 0;
	public static final int STRINGER_COUPLING__XI = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 1;
	public static final int STRINGER_COUPLING__ZI = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 2;
	public static final int STRINGER_COUPLING_FEATURE_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 3;
	public static final int STRINGER_COUPLING_OPERATION_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_OPERATION_COUNT + 0;
	public static final int RIVET = 17;
	public static final int RIVET__COLOR = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__COLOR;
	public static final int RIVET__SHAPE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__SHAPE;
	public static final int RIVET__EXISTING_COMPONENT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__EXISTING_COMPONENT;
	public static final int RIVET__USER_DEFINED_FEATURE = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT__USER_DEFINED_FEATURE;
	public static final int RIVET__SUB = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__SUB;
	public static final int RIVET__ID = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.TOPOLOGY_ELEMENT__ID;
	public static final int RIVET__STRINGER_COUPLING = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 0;
	public static final int RIVET__STRINGER = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 1;
	public static final int RIVET__CLIP = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 2;
	public static final int RIVET__SPANT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 3;
	public static final int RIVET__BUTT_STRAP = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 4;
	public static final int RIVET__SKIN = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 5;
	public static final int RIVET_FEATURE_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_FEATURE_COUNT + 6;
	public static final int RIVET_OPERATION_COUNT = de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.COMPONENT_OPERATION_COUNT + 0;
	
	private EClass sectionEClass = null;
	private EClass panelEClass = null;
	private EClass skinEClass = null;
	private EClass spantEClass = null;
	private EClass stringerEClass = null;
	private EClass clipEClass = null;
	private EClass sectionProfileEClass = null;
	private EDataType topoDS_ShapeEDataType = null;
	private EDataType wireEDataType = null;
	private EDataType topoDS_FaceEDataType = null;
	private EClass stringerProfileEClass = null;
	private EDataType splineEDataType = null;
	private EClass spantProfileEClass = null;
	private EClass buttStrapEClass = null;
	private EClass stringerCouplingEClass = null;
	private EDataType geom_BSplineCurveEDataType = null;
	private EClass rivetEClass = null;
	private EClass runControlEClass = null;
	

	private static boolean isInited = false;
	/**
	* Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	*/
	public static SectionPackageImpl init() {
		if (isInited) return (SectionPackageImpl)EPackage.Registry.INSTANCE.getEPackage(SectionPackageImpl.eNS_URI);

		// Obtain or create and register package
		SectionPackageImpl theSectionPackageImpl = (SectionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SectionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SectionPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		GraphPackageImpl.eINSTANCE.eClass();
		de.iils.dc43.core.geometry.impl.GeometryPackageImpl.eINSTANCE.eClass();

		// Obtain or create and register interdependencies

		// Create package meta-data objects
		theSectionPackageImpl.createPackageContents();

		// Initialize created meta-data
		theSectionPackageImpl.initializePackageContents();

		// Register package validator
		registerValidator(theSectionPackageImpl, new EValidator.Descriptor() {
			public EValidator getEValidator() {
				return SectionConstraints.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theSectionPackageImpl.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SectionPackageImpl.eNS_URI, theSectionPackageImpl);
		return theSectionPackageImpl;
	}
	/**
	* Returns the factory that creates the instances of the model.
	*/
	public SectionFactoryImpl getSectionFactory() {
		return (SectionFactoryImpl)getEFactoryInstance();
	}
	@Override
	public IEquationProvider getConstraintProvider() {
		return SectionConstraints.INSTANCE;
	}
	@Override
	public IRuleProvider getRuleProvider() {
		return section.impl.rules.SectionModule.INSTANCE;
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
		sectionEClass = createEClass(SECTION);
		createEReference(sectionEClass, SECTION__PANEL);
		createEAttributeWithUnit(sectionEClass, SECTION__ZA);
		createEAttribute(sectionEClass, SECTION__NPANEL_X);
		createEAttribute(sectionEClass, SECTION__NPANEL_Z);
		createEReference(sectionEClass, SECTION__PROFILE);
		createEAttributeWithUnit(sectionEClass, SECTION__L);
		createEAttributeWithUnit(sectionEClass, SECTION__H);
		createEAttribute(sectionEClass, SECTION__NSTRINGER);
		createEAttribute(sectionEClass, SECTION__SURFACE);
		createEAttribute(sectionEClass, SECTION__NSPANTE);
		createEReference(sectionEClass, SECTION__BUTT_STRAP);
		createEReference(sectionEClass, SECTION__RIVETS);
		createEAttributeWithUnit(sectionEClass, SECTION__BUTT_STRAP_WIDTH);
		createEAttributeWithUnit(sectionEClass, SECTION__STRINGER_COUPLING_LENGHT);
		createEReference(sectionEClass, SECTION__STRINGER_COUPLING);
		createEAttributeWithUnit(sectionEClass, SECTION__CLIP_FLOOR_WIDTH);
		createEAttributeWithUnit(sectionEClass, SECTION__CLIP_FLOOR_LENGHT);
		createEAttribute(sectionEClass, SECTION__NRIVET_ROWS_BS_V);
		createEAttribute(sectionEClass, SECTION__NRIVET_COLUMNS_BS_V);
		createEAttribute(sectionEClass, SECTION__NRIVET_ROWS_BS_H);
		createEAttribute(sectionEClass, SECTION__NRIVET_COLUMNS_BS_H);
		createEAttribute(sectionEClass, SECTION__NRIVET_CLIP_FLOOR);
		createEAttribute(sectionEClass, SECTION__NRIVET_CLIP_WALL1_V);
		createEAttribute(sectionEClass, SECTION__NRIVET_CLIP_WALL1_H);
		createEAttribute(sectionEClass, SECTION__NRIVET_CLIP_WALL2);

		panelEClass = createEClass(PANEL);
		createEReference(panelEClass, PANEL__SKIN);
		createEReference(panelEClass, PANEL__STRINGER);
		createEReference(panelEClass, PANEL__SPANT);
		createEReference(panelEClass, PANEL__CLIP);
		createEAttribute(panelEClass, PANEL__XI);
		createEAttribute(panelEClass, PANEL__ZI);
		createEAttribute(panelEClass, PANEL__SURFACE);

		skinEClass = createEClass(SKIN);
		createEAttributeWithUnit(skinEClass, SKIN__TS);
		createEReference(skinEClass, SKIN__BUTT_STRAP);

		spantEClass = createEClass(SPANT);
		createEReference(spantEClass, SPANT__SPANT_PROFILE);
		createEAttribute(spantEClass, SPANT__I);
		createEReference(spantEClass, SPANT__CLIP);

		stringerEClass = createEClass(STRINGER);
		createEReference(stringerEClass, STRINGER__STRINGER_PROFILE);
		createEAttribute(stringerEClass, STRINGER__I);
		createEReference(stringerEClass, STRINGER__STRINGER_COUPLING);

		clipEClass = createEClass(CLIP);
		createEAttributeWithUnit(clipEClass, CLIP__HIEGHT);
		createEAttribute(clipEClass, CLIP__XI);
		createEAttribute(clipEClass, CLIP__ZI);
		createEReference(clipEClass, CLIP__SPANT);
		createEReference(clipEClass, CLIP__STRINGER);
		createEReference(clipEClass, CLIP__SKIN);

		sectionProfileEClass = createEClass(SECTION_PROFILE);
		createEAttributeWithUnit(sectionProfileEClass, SECTION_PROFILE__R1A);
		createEAttributeWithUnit(sectionProfileEClass, SECTION_PROFILE__R2A);
		createEAttributeWithUnit(sectionProfileEClass, SECTION_PROFILE__HA);
		createEAttribute(sectionProfileEClass, SECTION_PROFILE__I);
		createEAttribute(sectionProfileEClass, SECTION_PROFILE__PROFILE);

		stringerProfileEClass = createEClass(STRINGER_PROFILE);
		createEAttribute(stringerProfileEClass, STRINGER_PROFILE__PROFILE);
		createEAttributeWithUnit(stringerProfileEClass, STRINGER_PROFILE__HEIGHT);

		spantProfileEClass = createEClass(SPANT_PROFILE);
		createEAttribute(spantProfileEClass, SPANT_PROFILE__PROFILE);
		createEAttributeWithUnit(spantProfileEClass, SPANT_PROFILE__HEIGHT_FOR_CLIP);

		buttStrapEClass = createEClass(BUTT_STRAP);
		createEReference(buttStrapEClass, BUTT_STRAP__SKIN);
		createEAttribute(buttStrapEClass, BUTT_STRAP__XI);
		createEAttribute(buttStrapEClass, BUTT_STRAP__ZI);
		createEAttribute(buttStrapEClass, BUTT_STRAP__ORIENTATION);

		stringerCouplingEClass = createEClass(STRINGER_COUPLING);
		createEReference(stringerCouplingEClass, STRINGER_COUPLING__STRINGER);
		createEAttribute(stringerCouplingEClass, STRINGER_COUPLING__XI);
		createEAttribute(stringerCouplingEClass, STRINGER_COUPLING__ZI);

		rivetEClass = createEClass(RIVET);
		createEReference(rivetEClass, RIVET__STRINGER_COUPLING);
		createEReference(rivetEClass, RIVET__STRINGER);
		createEReference(rivetEClass, RIVET__CLIP);
		createEReference(rivetEClass, RIVET__SPANT);
		createEReference(rivetEClass, RIVET__BUTT_STRAP);
		createEReference(rivetEClass, RIVET__SKIN);

		runControlEClass = createEClass(RUN_CONTROL);
		createEAttribute(runControlEClass, RUN_CONTROL__LOOP_COUNTER);

		// Create enums
		// Create dataType
		topoDS_ShapeEDataType = createEDataType(TOPO_DS_SHAPE);
		wireEDataType = createEDataType(WIRE);
		topoDS_FaceEDataType = createEDataType(TOPO_DS_FACE);
		splineEDataType = createEDataType(SPLINE);
		geom_BSplineCurveEDataType = createEDataType(GEOM_BSPLINE_CURVE);
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
		de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl thePublicationPackage = (de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl)EPackage.Registry.INSTANCE.getEPackage(de.iils.dc43.core.geometry.publication.impl.PublicationPackageImpl.eNS_URI);
		section.impl.SectionPackageImpl theSectionPackage = (section.impl.SectionPackageImpl)EPackage.Registry.INSTANCE.getEPackage(section.impl.SectionPackageImpl.eNS_URI);

		// Collect referenced DC43 library packages
		List<DC43Package> libraryPackages = new ArrayList<>();
		libraryPackages.add(de.iils.dc43.core.geometry.impl.GeometryPackageImpl.eINSTANCE);
		referencedDC43LibraryPackages = Collections.unmodifiableList(libraryPackages);


		// Create type parameters

		// Add supertypes to classes
		sectionEClass.getESuperTypes().add(theGraphPackage.getBaseInstance());
		panelEClass.getESuperTypes().add(theGraphPackage.getBaseInstance());
		skinEClass.getESuperTypes().add(thePublicationPackage.getComponent());
		spantEClass.getESuperTypes().add(thePublicationPackage.getComponent());
		stringerEClass.getESuperTypes().add(thePublicationPackage.getComponent());
		clipEClass.getESuperTypes().add(thePublicationPackage.getComponent());
		sectionProfileEClass.getESuperTypes().add(theGraphPackage.getBaseInstance());
		stringerProfileEClass.getESuperTypes().add(theGraphPackage.getBaseInstance());
		spantProfileEClass.getESuperTypes().add(theGraphPackage.getBaseInstance());
		buttStrapEClass.getESuperTypes().add(thePublicationPackage.getComponent());
		stringerCouplingEClass.getESuperTypes().add(thePublicationPackage.getComponent());
		rivetEClass.getESuperTypes().add(thePublicationPackage.getComponent());
		runControlEClass.getESuperTypes().add(theGraphPackage.getBaseInstance());

		// Initialize classes, features, and operations; add parameters
		EOperation op;
		EGenericType generic;
		initEClass(sectionEClass, section.Section.class, "Section", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSection_Panel(), this.getPanel(), null, "panel", null, 1, -1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttributeWithUnit(getSection_ZA(), ecorePackage.getEDouble(), "zA", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "°");
		initEAttribute(getSection_NPanelX(), ecorePackage.getEInt(), "nPanelX", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_NPanelZ(), ecorePackage.getEInt(), "nPanelZ", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSection_Profile(), this.getSectionProfile(), null, "profile", null, 2, 2, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttributeWithUnit(getSection_L(), ecorePackage.getEDouble(), "l", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");
		initEAttributeWithUnit(getSection_H(), ecorePackage.getEDouble(), "h", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "°");
		initEAttribute(getSection_NStringer(), ecorePackage.getEInt(), "nStringer", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_Surface(), this.getTopoDS_Face(), "surface", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_NSpante(), ecorePackage.getEInt(), "nSpante", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSection_ButtStrap(), this.getButtStrap(), null, "buttStrap", null, 1, -1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSection_Rivets(), this.getRivet(), null, "rivets", null, 1, -1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttributeWithUnit(getSection_ButtStrapWidth(), ecorePackage.getEDouble(), "buttStrapWidth", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");
		initEAttributeWithUnit(getSection_StringerCouplingLenght(), ecorePackage.getEDouble(), "stringerCouplingLenght", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");
		initEReference(getSection_StringerCoupling(), this.getStringerCoupling(), null, "stringerCoupling", null, 0, -1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttributeWithUnit(getSection_ClipFloorWidth(), ecorePackage.getEDouble(), "clipFloorWidth", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");
		initEAttributeWithUnit(getSection_ClipFloorLenght(), ecorePackage.getEDouble(), "clipFloorLenght", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "°");
		initEAttribute(getSection_NRivetRowsBS_v(), ecorePackage.getEInt(), "nRivetRowsBS_v", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_NRivetColumnsBS_v(), ecorePackage.getEInt(), "nRivetColumnsBS_v", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_NRivetRowsBS_h(), ecorePackage.getEInt(), "nRivetRowsBS_h", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_NRivetColumnsBS_h(), ecorePackage.getEInt(), "nRivetColumnsBS_h", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_NRivetClip_floor(), ecorePackage.getEInt(), "nRivetClip_floor", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_NRivetClip_wall1_v(), ecorePackage.getEInt(), "nRivetClip_wall1_v", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_NRivetClip_wall1_h(), ecorePackage.getEInt(), "nRivetClip_wall1_h", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSection_NRivetClip_wall2(), ecorePackage.getEInt(), "nRivetClip_wall2", null, 0, 1, section.Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(panelEClass, section.Panel.class, "Panel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPanel_Skin(), this.getSkin(), null, "skin", null, 1, 1, section.Panel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPanel_Stringer(), this.getStringer(), null, "stringer", null, 1, -1, section.Panel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPanel_Spant(), this.getSpant(), null, "spant", null, 1, -1, section.Panel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPanel_Clip(), this.getClip(), null, "clip", null, 1, -1, section.Panel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getPanel_Xi(), ecorePackage.getEInt(), "xi", null, 0, 1, section.Panel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPanel_Zi(), ecorePackage.getEInt(), "zi", null, 0, 1, section.Panel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPanel_Surface(), this.getTopoDS_Shape(), "surface", null, 0, 1, section.Panel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(skinEClass, section.Skin.class, "Skin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttributeWithUnit(getSkin_Ts(), ecorePackage.getEDouble(), "ts", null, 0, 1, section.Skin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");
		initEReference(getSkin_ButtStrap(), this.getButtStrap(), this.getButtStrap_Skin(), "buttStrap", null, 0, 4, section.Skin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(spantEClass, section.Spant.class, "Spant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpant_SpantProfile(), this.getSpantProfile(), null, "spantProfile", null, 0, 1, section.Spant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getSpant_I(), ecorePackage.getEInt(), "i", null, 0, 1, section.Spant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpant_Clip(), this.getClip(), this.getClip_Spant(), "clip", null, 0, -1, section.Spant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(stringerEClass, section.Stringer.class, "Stringer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStringer_StringerProfile(), this.getStringerProfile(), null, "stringerProfile", null, 1, 1, section.Stringer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStringer_I(), ecorePackage.getEInt(), "i", null, 0, 1, section.Stringer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringer_StringerCoupling(), this.getStringerCoupling(), this.getStringerCoupling_Stringer(), "stringerCoupling", null, 1, 2, section.Stringer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(clipEClass, section.Clip.class, "Clip", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttributeWithUnit(getClip_Hieght(), ecorePackage.getEDouble(), "hieght", null, 0, 1, section.Clip.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");
		initEAttribute(getClip_Xi(), ecorePackage.getEInt(), "xi", null, 0, 1, section.Clip.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClip_Zi(), ecorePackage.getEInt(), "zi", null, 0, 1, section.Clip.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClip_Spant(), this.getSpant(), this.getSpant_Clip(), "spant", null, 1, 1, section.Clip.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getClip_Stringer(), this.getStringer(), null, "stringer", null, 1, 1, section.Clip.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getClip_Skin(), this.getSkin(), null, "skin", null, 1, 1, section.Clip.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(sectionProfileEClass, section.SectionProfile.class, "SectionProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttributeWithUnit(getSectionProfile_R1a(), ecorePackage.getEDouble(), "r1a", null, 0, 1, section.SectionProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");
		initEAttributeWithUnit(getSectionProfile_R2a(), ecorePackage.getEDouble(), "r2a", null, 0, 1, section.SectionProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");
		initEAttributeWithUnit(getSectionProfile_HA(), ecorePackage.getEDouble(), "hA", null, 0, 1, section.SectionProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");
		initEAttribute(getSectionProfile_I(), ecorePackage.getEInt(), "i", null, 0, 1, section.SectionProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSectionProfile_Profile(), this.getSpline(), "profile", null, 0, 1, section.SectionProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringerProfileEClass, section.StringerProfile.class, "StringerProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringerProfile_Profile(), this.getWire(), "profile", null, 0, 1, section.StringerProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttributeWithUnit(getStringerProfile_Height(), ecorePackage.getEDouble(), "height", null, 0, 1, section.StringerProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");

		initEClass(spantProfileEClass, section.SpantProfile.class, "SpantProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpantProfile_Profile(), this.getWire(), "profile", null, 0, 1, section.SpantProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttributeWithUnit(getSpantProfile_HeightForClip(), ecorePackage.getEDouble(), "heightForClip", null, 0, 1, section.SpantProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED, "mm");

		initEClass(buttStrapEClass, section.ButtStrap.class, "ButtStrap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getButtStrap_Skin(), this.getSkin(), this.getSkin_ButtStrap(), "skin", null, 2, 2, section.ButtStrap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getButtStrap_Xi(), ecorePackage.getEInt(), "xi", null, 0, 1, section.ButtStrap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getButtStrap_Zi(), ecorePackage.getEInt(), "zi", null, 0, 1, section.ButtStrap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getButtStrap_Orientation(), ecorePackage.getEString(), "orientation", null, 0, 1, section.ButtStrap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringerCouplingEClass, section.StringerCoupling.class, "StringerCoupling", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStringerCoupling_Stringer(), this.getStringer(), this.getStringer_StringerCoupling(), "stringer", null, 2, 2, section.StringerCoupling.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getStringerCoupling_Xi(), ecorePackage.getEInt(), "xi", null, 0, 1, section.StringerCoupling.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringerCoupling_Zi(), ecorePackage.getEInt(), "zi", null, 0, 1, section.StringerCoupling.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rivetEClass, section.Rivet.class, "Rivet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRivet_StringerCoupling(), this.getStringerCoupling(), null, "stringerCoupling", null, 0, 1, section.Rivet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRivet_Stringer(), this.getStringer(), null, "stringer", null, 0, 1, section.Rivet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRivet_Clip(), this.getClip(), null, "clip", null, 0, 1, section.Rivet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRivet_Spant(), this.getSpant(), null, "spant", null, 0, 1, section.Rivet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRivet_ButtStrap(), this.getButtStrap(), null, "buttStrap", null, 0, 1, section.Rivet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRivet_Skin(), this.getSkin(), null, "skin", null, 0, 1, section.Rivet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(runControlEClass, section.RunControl.class, "RunControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRunControl_LoopCounter(), ecorePackage.getEInt(), "loopCounter", null, 0, 1, section.RunControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);


		// Initialize enums and add enum literals

		// Initialize DataType
		initEDataType(topoDS_ShapeEDataType, opencascade.TopoDS_Shape.class, "TopoDS_Shape", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(wireEDataType, de.iils.dc43.core.geometry.publication.Wire.class, "Wire", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(topoDS_FaceEDataType, opencascade.TopoDS_Face.class, "TopoDS_Face", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(splineEDataType, de.iils.dc43.core.geometry.publication.Spline.class, "Spline", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(geom_BSplineCurveEDataType, opencascade.Geom_BSplineCurve.class, "Geom_BSplineCurve", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		// Create resource
		createResource(eNS_URI);
	}

	public EClass getSection() {
		return sectionEClass;
	}
	public EReference getSection_Panel() {
		return (EReference)sectionEClass.getEStructuralFeatures().get(0);
	}
	public EAttributeWithUnit getSection_ZA() {
		return (EAttributeWithUnit)sectionEClass.getEStructuralFeatures().get(1);
	}
	public EAttribute getSection_NPanelX() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(2);
	}
	public EAttribute getSection_NPanelZ() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(3);
	}
	public EReference getSection_Profile() {
		return (EReference)sectionEClass.getEStructuralFeatures().get(4);
	}
	public EAttributeWithUnit getSection_L() {
		return (EAttributeWithUnit)sectionEClass.getEStructuralFeatures().get(5);
	}
	public EAttributeWithUnit getSection_H() {
		return (EAttributeWithUnit)sectionEClass.getEStructuralFeatures().get(6);
	}
	public EAttribute getSection_NStringer() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(7);
	}
	public EAttribute getSection_Surface() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(8);
	}
	public EAttribute getSection_NSpante() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(9);
	}
	public EReference getSection_ButtStrap() {
		return (EReference)sectionEClass.getEStructuralFeatures().get(10);
	}
	public EReference getSection_Rivets() {
		return (EReference)sectionEClass.getEStructuralFeatures().get(11);
	}
	public EAttributeWithUnit getSection_ButtStrapWidth() {
		return (EAttributeWithUnit)sectionEClass.getEStructuralFeatures().get(12);
	}
	public EAttributeWithUnit getSection_StringerCouplingLenght() {
		return (EAttributeWithUnit)sectionEClass.getEStructuralFeatures().get(13);
	}
	public EReference getSection_StringerCoupling() {
		return (EReference)sectionEClass.getEStructuralFeatures().get(14);
	}
	public EAttributeWithUnit getSection_ClipFloorWidth() {
		return (EAttributeWithUnit)sectionEClass.getEStructuralFeatures().get(15);
	}
	public EAttributeWithUnit getSection_ClipFloorLenght() {
		return (EAttributeWithUnit)sectionEClass.getEStructuralFeatures().get(16);
	}
	public EAttribute getSection_NRivetRowsBS_v() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(17);
	}
	public EAttribute getSection_NRivetColumnsBS_v() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(18);
	}
	public EAttribute getSection_NRivetRowsBS_h() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(19);
	}
	public EAttribute getSection_NRivetColumnsBS_h() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(20);
	}
	public EAttribute getSection_NRivetClip_floor() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(21);
	}
	public EAttribute getSection_NRivetClip_wall1_v() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(22);
	}
	public EAttribute getSection_NRivetClip_wall1_h() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(23);
	}
	public EAttribute getSection_NRivetClip_wall2() {
		return (EAttribute)sectionEClass.getEStructuralFeatures().get(24);
	}
	public EClass getPanel() {
		return panelEClass;
	}
	public EReference getPanel_Skin() {
		return (EReference)panelEClass.getEStructuralFeatures().get(0);
	}
	public EReference getPanel_Stringer() {
		return (EReference)panelEClass.getEStructuralFeatures().get(1);
	}
	public EReference getPanel_Spant() {
		return (EReference)panelEClass.getEStructuralFeatures().get(2);
	}
	public EReference getPanel_Clip() {
		return (EReference)panelEClass.getEStructuralFeatures().get(3);
	}
	public EAttribute getPanel_Xi() {
		return (EAttribute)panelEClass.getEStructuralFeatures().get(4);
	}
	public EAttribute getPanel_Zi() {
		return (EAttribute)panelEClass.getEStructuralFeatures().get(5);
	}
	public EAttribute getPanel_Surface() {
		return (EAttribute)panelEClass.getEStructuralFeatures().get(6);
	}
	public EClass getSkin() {
		return skinEClass;
	}
	public EAttributeWithUnit getSkin_Ts() {
		return (EAttributeWithUnit)skinEClass.getEStructuralFeatures().get(0);
	}
	public EReference getSkin_ButtStrap() {
		return (EReference)skinEClass.getEStructuralFeatures().get(1);
	}
	public EClass getSpant() {
		return spantEClass;
	}
	public EReference getSpant_SpantProfile() {
		return (EReference)spantEClass.getEStructuralFeatures().get(0);
	}
	public EAttribute getSpant_I() {
		return (EAttribute)spantEClass.getEStructuralFeatures().get(1);
	}
	public EReference getSpant_Clip() {
		return (EReference)spantEClass.getEStructuralFeatures().get(2);
	}
	public EClass getStringer() {
		return stringerEClass;
	}
	public EReference getStringer_StringerProfile() {
		return (EReference)stringerEClass.getEStructuralFeatures().get(0);
	}
	public EAttribute getStringer_I() {
		return (EAttribute)stringerEClass.getEStructuralFeatures().get(1);
	}
	public EReference getStringer_StringerCoupling() {
		return (EReference)stringerEClass.getEStructuralFeatures().get(2);
	}
	public EClass getClip() {
		return clipEClass;
	}
	public EAttributeWithUnit getClip_Hieght() {
		return (EAttributeWithUnit)clipEClass.getEStructuralFeatures().get(0);
	}
	public EAttribute getClip_Xi() {
		return (EAttribute)clipEClass.getEStructuralFeatures().get(1);
	}
	public EAttribute getClip_Zi() {
		return (EAttribute)clipEClass.getEStructuralFeatures().get(2);
	}
	public EReference getClip_Spant() {
		return (EReference)clipEClass.getEStructuralFeatures().get(3);
	}
	public EReference getClip_Stringer() {
		return (EReference)clipEClass.getEStructuralFeatures().get(4);
	}
	public EReference getClip_Skin() {
		return (EReference)clipEClass.getEStructuralFeatures().get(5);
	}
	public EClass getSectionProfile() {
		return sectionProfileEClass;
	}
	public EAttributeWithUnit getSectionProfile_R1a() {
		return (EAttributeWithUnit)sectionProfileEClass.getEStructuralFeatures().get(0);
	}
	public EAttributeWithUnit getSectionProfile_R2a() {
		return (EAttributeWithUnit)sectionProfileEClass.getEStructuralFeatures().get(1);
	}
	public EAttributeWithUnit getSectionProfile_HA() {
		return (EAttributeWithUnit)sectionProfileEClass.getEStructuralFeatures().get(2);
	}
	public EAttribute getSectionProfile_I() {
		return (EAttribute)sectionProfileEClass.getEStructuralFeatures().get(3);
	}
	public EAttribute getSectionProfile_Profile() {
		return (EAttribute)sectionProfileEClass.getEStructuralFeatures().get(4);
	}
	public EDataType getTopoDS_Shape() {
		return topoDS_ShapeEDataType;
	}
	public EDataType getWire() {
		return wireEDataType;
	}
	public EDataType getTopoDS_Face() {
		return topoDS_FaceEDataType;
	}
	public EClass getStringerProfile() {
		return stringerProfileEClass;
	}
	public EAttribute getStringerProfile_Profile() {
		return (EAttribute)stringerProfileEClass.getEStructuralFeatures().get(0);
	}
	public EAttributeWithUnit getStringerProfile_Height() {
		return (EAttributeWithUnit)stringerProfileEClass.getEStructuralFeatures().get(1);
	}
	public EDataType getSpline() {
		return splineEDataType;
	}
	public EClass getSpantProfile() {
		return spantProfileEClass;
	}
	public EAttribute getSpantProfile_Profile() {
		return (EAttribute)spantProfileEClass.getEStructuralFeatures().get(0);
	}
	public EAttributeWithUnit getSpantProfile_HeightForClip() {
		return (EAttributeWithUnit)spantProfileEClass.getEStructuralFeatures().get(1);
	}
	public EClass getButtStrap() {
		return buttStrapEClass;
	}
	public EReference getButtStrap_Skin() {
		return (EReference)buttStrapEClass.getEStructuralFeatures().get(0);
	}
	public EAttribute getButtStrap_Xi() {
		return (EAttribute)buttStrapEClass.getEStructuralFeatures().get(1);
	}
	public EAttribute getButtStrap_Zi() {
		return (EAttribute)buttStrapEClass.getEStructuralFeatures().get(2);
	}
	public EAttribute getButtStrap_Orientation() {
		return (EAttribute)buttStrapEClass.getEStructuralFeatures().get(3);
	}
	public EClass getStringerCoupling() {
		return stringerCouplingEClass;
	}
	public EReference getStringerCoupling_Stringer() {
		return (EReference)stringerCouplingEClass.getEStructuralFeatures().get(0);
	}
	public EAttribute getStringerCoupling_Xi() {
		return (EAttribute)stringerCouplingEClass.getEStructuralFeatures().get(1);
	}
	public EAttribute getStringerCoupling_Zi() {
		return (EAttribute)stringerCouplingEClass.getEStructuralFeatures().get(2);
	}
	public EDataType getGeom_BSplineCurve() {
		return geom_BSplineCurveEDataType;
	}
	public EClass getRivet() {
		return rivetEClass;
	}
	public EReference getRivet_StringerCoupling() {
		return (EReference)rivetEClass.getEStructuralFeatures().get(0);
	}
	public EReference getRivet_Stringer() {
		return (EReference)rivetEClass.getEStructuralFeatures().get(1);
	}
	public EReference getRivet_Clip() {
		return (EReference)rivetEClass.getEStructuralFeatures().get(2);
	}
	public EReference getRivet_Spant() {
		return (EReference)rivetEClass.getEStructuralFeatures().get(3);
	}
	public EReference getRivet_ButtStrap() {
		return (EReference)rivetEClass.getEStructuralFeatures().get(4);
	}
	public EReference getRivet_Skin() {
		return (EReference)rivetEClass.getEStructuralFeatures().get(5);
	}
	public EClass getRunControl() {
		return runControlEClass;
	}
	public EAttribute getRunControl_LoopCounter() {
		return (EAttribute)runControlEClass.getEStructuralFeatures().get(0);
	}

}