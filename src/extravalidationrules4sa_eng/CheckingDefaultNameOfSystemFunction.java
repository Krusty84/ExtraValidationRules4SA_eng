package extravalidationrules4sa_eng;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class CheckingDefaultNameOfSystemFunction extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
		// TODO Auto-generated method stub
		//Get EObject instance what will be initialized current env, https://download.eclipse.org/modeling/emf/emf/javadoc/2.9.0/org/eclipse/emf/ecore/EObject.html
		EObject eObject = ctx.getTarget();
		//checking type object that based on EObject, for us has interest SystemFunction  
		if (eObject instanceof SystemFunction) {
			//Type converstion form EObject to System Function
			SystemFunction currentSysFunc = (SystemFunction)eObject;
			//For name of system function
			String strNameOfSystemFunction;
			//Get Name of System Function
			strNameOfSystemFunction=currentSysFunc.getName().toString();
			//Checking name, the name is a default?
			if (strNameOfSystemFunction.matches("SystemFunction(.*)(\\d*{1,})")) {
				if (currentSysFunc instanceof AbstractNamedElement) {
					AbstractNamedElement currentNamedElement = (AbstractNamedElement)currentSysFunc;
					//return error if System Function has name
					return ctx.createFailureStatus(new Object [] {currentSysFunc.eClass().getName(), currentNamedElement.getName()});
				}
				//return error if System Function does not have a name
				return ctx.createFailureStatus(new Object[] {currentSysFunc.eClass().getName(), "<no name>"});
			}
		}
		//return nothing because this rule did not found errors
		return ctx.createSuccessStatus();
	}

}
