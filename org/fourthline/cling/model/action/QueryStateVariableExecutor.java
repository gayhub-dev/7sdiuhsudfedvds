package org.fourthline.cling.model.action;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.QueryStateVariableAction;
import org.fourthline.cling.model.meta.StateVariable;
import org.fourthline.cling.model.state.StateVariableAccessor;
import org.fourthline.cling.model.types.ErrorCode;
import p009b.C0413b;

/* loaded from: classes.dex */
public class QueryStateVariableExecutor extends AbstractActionExecutor {
    @Override // org.fourthline.cling.model.action.AbstractActionExecutor
    public void execute(ActionInvocation<LocalService> actionInvocation, Object obj) throws ActionException {
        if (!(actionInvocation.getAction() instanceof QueryStateVariableAction)) {
            StringBuilder sbM112a = C0413b.m112a("This class can only execute QueryStateVariableAction's, not: ");
            sbM112a.append(actionInvocation.getAction());
            throw new IllegalStateException(sbM112a.toString());
        }
        if (((LocalService) actionInvocation.getAction().getService()).isSupportsQueryStateVariables()) {
            executeQueryStateVariable(actionInvocation, obj);
        } else {
            actionInvocation.setFailure(new ActionException(ErrorCode.INVALID_ACTION, "This service does not support querying state variables"));
        }
    }

    public void executeQueryStateVariable(ActionInvocation<LocalService> actionInvocation, Object obj) throws ActionException {
        LocalService localService = (LocalService) actionInvocation.getAction().getService();
        String string = actionInvocation.getInput(QueryStateVariableAction.INPUT_ARG_VAR_NAME).toString();
        StateVariable<LocalService> stateVariable = localService.getStateVariable(string);
        if (stateVariable == null) {
            throw new ActionException(ErrorCode.ARGUMENT_VALUE_INVALID, C0063n.m88a("No state variable found: ", string));
        }
        StateVariableAccessor accessor = localService.getAccessor(stateVariable.getName());
        if (accessor == null) {
            throw new ActionException(ErrorCode.ARGUMENT_VALUE_INVALID, C0063n.m88a("No accessor for state variable, can't read state: ", string));
        }
        try {
            setOutputArgumentValue(actionInvocation, actionInvocation.getAction().getOutputArgument(QueryStateVariableAction.OUTPUT_ARG_RETURN), accessor.read(stateVariable, obj).toString());
        } catch (Exception e7) {
            throw new ActionException(ErrorCode.ACTION_FAILED, e7.getMessage());
        }
    }
}
