package section.skin.impl;
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
import section.skin.impl.SkinPackageImpl;
import section.skin.*;

/**
* The implementation of the model <b>Validator</b>.
* 2023-01-10T04:25:56.168+0100
*/
@SuppressWarnings("all")
public class SkinConstraints extends DC43Constraints {
	/**
	* The singleton instance of the constraint holder.
	*/
	public static final SkinConstraints INSTANCE = new SkinConstraints();

	public static final String DIAGNOSTIC_SOURCE = "Skin";

	public SkinConstraints() {
		super();
	}
	@Override
	protected EPackage getEPackage() {
		 return SkinPackageImpl.eINSTANCE;
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