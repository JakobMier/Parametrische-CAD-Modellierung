package section.stringer.impl;
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
import section.stringer.impl.StringerPackageImpl;
import section.stringer.*;

/**
* The implementation of the model <b>Validator</b>.
* 2023-02-28T23:18:49.527+0100
*/
@SuppressWarnings("all")
public class StringerConstraints extends DC43Constraints {
	/**
	* The singleton instance of the constraint holder.
	*/
	public static final StringerConstraints INSTANCE = new StringerConstraints();

	public static final String DIAGNOSTIC_SOURCE = "Stringer";

	public StringerConstraints() {
		super();
	}
	@Override
	protected EPackage getEPackage() {
		 return StringerPackageImpl.eINSTANCE;
	}

	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID){
			default:
				return true;
		}
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