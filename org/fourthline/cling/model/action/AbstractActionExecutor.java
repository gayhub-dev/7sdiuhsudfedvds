package org.fourthline.cling.model.action;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.Command;
import org.fourthline.cling.model.ServiceManager;
import org.fourthline.cling.model.meta.Action;
import org.fourthline.cling.model.meta.ActionArgument;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.state.StateVariableAccessor;
import org.fourthline.cling.model.types.ErrorCode;
import org.fourthline.cling.model.types.InvalidValueException;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class AbstractActionExecutor implements ActionExecutor {
    private static Logger log = Logger.getLogger(AbstractActionExecutor.class.getName());
    public Map<ActionArgument<LocalService>, StateVariableAccessor> outputArgumentAccessors;

    public AbstractActionExecutor() {
        this.outputArgumentAccessors = new HashMap();
    }

    @Override // org.fourthline.cling.model.action.ActionExecutor
    public void execute(final ActionInvocation<LocalService> actionInvocation) {
        log.fine("Invoking on local service: " + actionInvocation);
        LocalService localService = (LocalService) actionInvocation.getAction().getService();
        try {
            if (localService.getManager() == null) {
                throw new IllegalStateException("Service has no implementation factory, can't get service instance");
            }
            localService.getManager().execute(new Command() { // from class: org.fourthline.cling.model.action.AbstractActionExecutor.1
                @Override // org.fourthline.cling.model.Command
                public void execute(ServiceManager serviceManager) {
                    AbstractActionExecutor.this.execute(actionInvocation, serviceManager.getImplementation());
                }

                public String toString() {
                    StringBuilder sbM112a = C0413b.m112a("Action invocation: ");
                    sbM112a.append(actionInvocation.getAction());
                    return sbM112a.toString();
                }
            });
        } catch (InterruptedException e7) {
            Logger logger = log;
            Level level = Level.FINE;
            if (logger.isLoggable(level)) {
                log.fine("InterruptedException thrown by service, wrapping in invocation and returning: " + e7);
                log.log(level, "Exception root cause: ", C2074b.m2475O(e7));
            }
            actionInvocation.setFailure(new ActionCancelledException(e7));
        } catch (ActionException e8) {
            Logger logger2 = log;
            Level level2 = Level.FINE;
            if (logger2.isLoggable(level2)) {
                log.fine("ActionException thrown by service, wrapping in invocation and returning: " + e8);
                log.log(level2, "Exception root cause: ", C2074b.m2475O(e8));
            }
            actionInvocation.setFailure(e8);
        } catch (Throwable th) {
            Throwable thM2475O = C2074b.m2475O(th);
            Logger logger3 = log;
            Level level3 = Level.FINE;
            if (logger3.isLoggable(level3)) {
                log.fine("Execution has thrown, wrapping root cause in ActionException and returning: " + th);
                log.log(level3, "Exception root cause: ", thM2475O);
            }
            actionInvocation.setFailure(new ActionException(ErrorCode.ACTION_FAILED, thM2475O.getMessage() != null ? thM2475O.getMessage() : thM2475O.toString(), thM2475O));
        }
    }

    public abstract void execute(ActionInvocation<LocalService> actionInvocation, Object obj);

    public Map<ActionArgument<LocalService>, StateVariableAccessor> getOutputArgumentAccessors() {
        return this.outputArgumentAccessors;
    }

    public Object readOutputArgumentValues(Action<LocalService> action, Object obj) {
        int length = action.getOutputArguments().length;
        Object[] objArr = new Object[length];
        log.fine("Attempting to retrieve output argument values using accessor: " + length);
        Object[] outputArguments = action.getOutputArguments();
        int length2 = outputArguments.length;
        int i7 = 0;
        int i8 = 0;
        while (i7 < length2) {
            Object obj2 = outputArguments[i7];
            log.finer("Calling acccessor method for: " + obj2);
            StateVariableAccessor stateVariableAccessor = getOutputArgumentAccessors().get(obj2);
            if (stateVariableAccessor == null) {
                throw new IllegalStateException("No accessor bound for: " + obj2);
            }
            log.fine("Calling accessor to read output argument value: " + stateVariableAccessor);
            objArr[i8] = stateVariableAccessor.read(obj);
            i7++;
            i8++;
        }
        if (length == 1) {
            return objArr[0];
        }
        if (length > 0) {
            return objArr;
        }
        return null;
    }

    public void setOutputArgumentValue(ActionInvocation<LocalService> actionInvocation, ActionArgument<LocalService> actionArgument, Object obj) {
        LocalService localService = (LocalService) actionInvocation.getAction().getService();
        if (obj == null) {
            log.fine("Result of invocation is null, not setting any output argument value(s)");
            return;
        }
        try {
            if (localService.isStringConvertibleType(obj)) {
                log.fine("Result of invocation matches convertible type, setting toString() single output argument value");
                actionInvocation.setOutput(new ActionArgumentValue<>(actionArgument, obj.toString()));
            } else {
                log.fine("Result of invocation is Object, setting single output argument value");
                actionInvocation.setOutput(new ActionArgumentValue<>(actionArgument, obj));
            }
        } catch (InvalidValueException e7) {
            ErrorCode errorCode = ErrorCode.ARGUMENT_VALUE_INVALID;
            StringBuilder sbM112a = C0413b.m112a("Wrong type or invalid value for '");
            sbM112a.append(actionArgument.getName());
            sbM112a.append("': ");
            sbM112a.append(e7.getMessage());
            throw new ActionException(errorCode, sbM112a.toString(), e7);
        }
    }

    public AbstractActionExecutor(Map<ActionArgument<LocalService>, StateVariableAccessor> map) {
        this.outputArgumentAccessors = new HashMap();
        this.outputArgumentAccessors = map;
    }
}
