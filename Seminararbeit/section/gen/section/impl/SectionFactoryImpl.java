package section.impl;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import de.iils.dc43.core.ecore.DC43Factory;
import tec.uom.se.*;
import section.*;

/**
* The implementation of the model <b>Factory</b>.
* 2023-01-10T01:54:25.692+0100
*/
@SuppressWarnings("all")
public class SectionFactoryImpl extends DC43Factory {
	/**
	* The singleton instance of the factory.
	*/
	public static final SectionFactoryImpl eINSTANCE = init();

	/**
	* Creates the default factory implementation.
	*/
	public static SectionFactoryImpl init() {
		try {
			SectionFactoryImpl factory = (SectionFactoryImpl)EPackage.Registry.INSTANCE.getEFactory(SectionPackageImpl.eNS_URI);
			if (factory instanceof SectionFactoryImpl) {
				return factory;
			}
		}
		catch(Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SectionFactoryImpl();
	}
	public SectionFactoryImpl() {
		super();
	}
	public SectionPackageImpl getSectionPackage() {
		return (SectionPackageImpl)getEPackage();
	}
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()){
			case SectionPackageImpl.SECTION:
				return new SectionImpl();
			case SectionPackageImpl.PANEL:
				return new PanelImpl();
			case SectionPackageImpl.SKIN:
				return new SkinImpl();
			case SectionPackageImpl.SPANT:
				return new SpantImpl();
			case SectionPackageImpl.STRINGER:
				return new StringerImpl();
			case SectionPackageImpl.CLIP:
				return new ClipImpl();
			case SectionPackageImpl.SECTION_PROFILE:
				return new SectionProfileImpl();
			case SectionPackageImpl.STRINGER_PROFILE:
				return new StringerProfileImpl();
			case SectionPackageImpl.SPANT_PROFILE:
				return new SpantProfileImpl();
			case SectionPackageImpl.BUTT_STRAP:
				return new ButtStrapImpl();
			case SectionPackageImpl.STRINGER_COUPLING:
				return new StringerCouplingImpl();
			case SectionPackageImpl.RIVET:
				return new RivetImpl();
			case SectionPackageImpl.RUN_CONTROL:
				return new RunControlImpl();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	@Override
	public EObject create(EClass eClass, Object... arguments) {
		switch (eClass.getClassifierID()){
			case SectionPackageImpl.SECTION:
				SectionImpl inst0 = new SectionImpl();
				addToGraph(inst0);
				if (arguments == null) {}
				return inst0;
			case SectionPackageImpl.PANEL:
				PanelImpl inst1 = new PanelImpl();
				addToGraph(inst1);
				if (arguments == null) {}
				return inst1;
			case SectionPackageImpl.SKIN:
				SkinImpl inst2 = new SkinImpl();
				addToGraph(inst2);
				if (arguments == null) {}
				return inst2;
			case SectionPackageImpl.SPANT:
				SpantImpl inst3 = new SpantImpl();
				addToGraph(inst3);
				if (arguments == null) {}
				return inst3;
			case SectionPackageImpl.STRINGER:
				StringerImpl inst4 = new StringerImpl();
				addToGraph(inst4);
				if (arguments == null) {}
				return inst4;
			case SectionPackageImpl.CLIP:
				ClipImpl inst5 = new ClipImpl();
				addToGraph(inst5);
				if (arguments == null) {}
				return inst5;
			case SectionPackageImpl.SECTION_PROFILE:
				SectionProfileImpl inst6 = new SectionProfileImpl();
				addToGraph(inst6);
				if (arguments == null) {}
				return inst6;
			case SectionPackageImpl.STRINGER_PROFILE:
				StringerProfileImpl inst7 = new StringerProfileImpl();
				addToGraph(inst7);
				if (arguments == null) {}
				return inst7;
			case SectionPackageImpl.SPANT_PROFILE:
				SpantProfileImpl inst8 = new SpantProfileImpl();
				addToGraph(inst8);
				if (arguments == null) {}
				return inst8;
			case SectionPackageImpl.BUTT_STRAP:
				ButtStrapImpl inst9 = new ButtStrapImpl();
				addToGraph(inst9);
				if (arguments == null) {}
				return inst9;
			case SectionPackageImpl.STRINGER_COUPLING:
				StringerCouplingImpl inst10 = new StringerCouplingImpl();
				addToGraph(inst10);
				if (arguments == null) {}
				return inst10;
			case SectionPackageImpl.RIVET:
				RivetImpl inst11 = new RivetImpl();
				addToGraph(inst11);
				if (arguments == null) {}
				return inst11;
			case SectionPackageImpl.RUN_CONTROL:
				RunControlImpl inst12 = new RunControlImpl();
				addToGraph(inst12);
				if (arguments == null) {}
				return inst12;
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()){
		case SectionPackageImpl.TOPO_DS_SHAPE:
			return super.createFromString(initialValue);
		case SectionPackageImpl.WIRE:
			return super.createFromString(initialValue);
		case SectionPackageImpl.TOPO_DS_FACE:
			return super.createFromString(initialValue);
		case SectionPackageImpl.SPLINE:
			return super.createFromString(initialValue);
		case SectionPackageImpl.GEOM_BSPLINE_CURVE:
			return super.createFromString(initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()){
		case SectionPackageImpl.TOPO_DS_SHAPE:
			return null;
		case SectionPackageImpl.WIRE:
			return null;
		case SectionPackageImpl.TOPO_DS_FACE:
			return null;
		case SectionPackageImpl.SPLINE:
			return null;
		case SectionPackageImpl.GEOM_BSPLINE_CURVE:
			return null;
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}
}