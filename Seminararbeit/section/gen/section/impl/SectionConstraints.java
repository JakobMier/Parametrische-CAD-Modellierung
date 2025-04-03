package section.impl;
import java.util.Map;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import de.iils.dc43.core.ecore.DC43Constraints;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import de.iils.dc43.core.evaluation.notation.storage.ConstraintData;
import static de.iils.dc43.core.evaluation.notation.generation.code.MathmodelASTFactory.*;
import java.util.Arrays;
import de.iils.dc43.core.evaluation.notation.storage.MathConstraintData;
import de.iils.dc43.core.evaluation.notation.storage.ReferenceData;
import de.iils.dc43.core.evaluation.notation.storage.OCLConditionData;
import de.iils.dc43.core.evaluation.notation.storage.CodeConstraintData;
import de.iils.dc43.core.evaluation.notation.InVariables;
import de.iils.dc43.core.evaluation.notation.OutVariables;
import section.impl.SectionPackageImpl;
import section.*;

/**
* The implementation of the model <b>Validator</b>.
* 2023-01-10T01:54:25.765+0100
*/
@SuppressWarnings("all")
public class SectionConstraints extends DC43Constraints {
	/**
	* The singleton instance of the constraint holder.
	*/
	public static final SectionConstraints INSTANCE = new SectionConstraints();

	public static final String DIAGNOSTIC_SOURCE = "Section";

	public SectionConstraints() {
		super();
	}
	@Override
	protected EPackage getEPackage() {
		 return SectionPackageImpl.eINSTANCE;
	}

	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID){
			case SectionPackageImpl.SECTION:
				return validateSection((Section)value, diagnostics, context);
			case SectionPackageImpl.PANEL:
				return validatePanel((Panel)value, diagnostics, context);
			case SectionPackageImpl.SKIN:
				return validateSkin((Skin)value, diagnostics, context);
			case SectionPackageImpl.SPANT:
				return validateSpant((Spant)value, diagnostics, context);
			case SectionPackageImpl.STRINGER:
				return validateStringer((Stringer)value, diagnostics, context);
			case SectionPackageImpl.CLIP:
				return validateClip((Clip)value, diagnostics, context);
			case SectionPackageImpl.SECTION_PROFILE:
				return validateSectionProfile((SectionProfile)value, diagnostics, context);
			case SectionPackageImpl.STRINGER_PROFILE:
				return validateStringerProfile((StringerProfile)value, diagnostics, context);
			case SectionPackageImpl.SPANT_PROFILE:
				return validateSpantProfile((SpantProfile)value, diagnostics, context);
			case SectionPackageImpl.BUTT_STRAP:
				return validateButtStrap((ButtStrap)value, diagnostics, context);
			case SectionPackageImpl.STRINGER_COUPLING:
				return validateStringerCoupling((StringerCoupling)value, diagnostics, context);
			case SectionPackageImpl.RIVET:
				return validateRivet((Rivet)value, diagnostics, context);
			case SectionPackageImpl.RUN_CONTROL:
				return validateRunControl((RunControl)value, diagnostics, context);
			default:
				return true;
		}
	}

	public boolean validateSection(Section instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		return result;
	}
	public boolean validatePanel(Panel instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		return result;
	}
	public boolean validateSkin(Skin instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.TopologyElement.classifier(), instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.Component.classifier(), instance, diagnostics, context);
		return result;
	}
	public boolean validateSpant(Spant instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.TopologyElement.classifier(), instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.Component.classifier(), instance, diagnostics, context);
		return result;
	}
	public boolean validateStringer(Stringer instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.TopologyElement.classifier(), instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.Component.classifier(), instance, diagnostics, context);
		return result;
	}
	public boolean validateClip(Clip instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.TopologyElement.classifier(), instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.Component.classifier(), instance, diagnostics, context);
		return result;
	}
	public boolean validateSectionProfile(SectionProfile instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		return result;
	}
	public boolean validateStringerProfile(StringerProfile instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		return result;
	}
	public boolean validateSpantProfile(SpantProfile instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		return result;
	}
	public boolean validateButtStrap(ButtStrap instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.TopologyElement.classifier(), instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.Component.classifier(), instance, diagnostics, context);
		return result;
	}
	public boolean validateStringerCoupling(StringerCoupling instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.TopologyElement.classifier(), instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.Component.classifier(), instance, diagnostics, context);
		return result;
	}
	public boolean validateRivet(Rivet instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.TopologyElement.classifier(), instance, diagnostics, context);
		result &= org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(de.iils.dc43.core.geometry.publication.Component.classifier(), instance, diagnostics, context);
		return result;
	}
	public boolean validateRunControl(RunControl instance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryDefaultConstraint(instance, diagnostics, context);
		return result;
	}

	//SPG PART
	@Override
	public boolean isPartOfConstraintSystem(EAttribute eAttribute) {

		return false;
	}

	@Override
	public ConstraintData[] getEquation(EClass eClass) {
		switch (eClass.getClassifierID()) {
		default:
			return null;
		}
	}

}