package org.fourthline.cling.model.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.Validatable;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.meta.ActionArgument;
import org.fourthline.cling.model.meta.Service;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Action<S extends Service> implements Validatable {
    private static final Logger log = Logger.getLogger(Action.class.getName());
    private final ActionArgument[] arguments;
    private final ActionArgument[] inputArguments;
    private final String name;
    private final ActionArgument[] outputArguments;
    private S service;

    public Action(String str, ActionArgument[] actionArgumentArr) {
        this.name = str;
        if (actionArgumentArr == null) {
            this.arguments = new ActionArgument[0];
            this.inputArguments = new ActionArgument[0];
            this.outputArguments = new ActionArgument[0];
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ActionArgument actionArgument : actionArgumentArr) {
            actionArgument.setAction(this);
            if (actionArgument.getDirection().equals(ActionArgument.Direction.IN)) {
                arrayList.add(actionArgument);
            }
            if (actionArgument.getDirection().equals(ActionArgument.Direction.OUT)) {
                arrayList2.add(actionArgument);
            }
        }
        this.arguments = actionArgumentArr;
        this.inputArguments = (ActionArgument[]) arrayList.toArray(new ActionArgument[arrayList.size()]);
        this.outputArguments = (ActionArgument[]) arrayList2.toArray(new ActionArgument[arrayList2.size()]);
    }

    public Action<S> deepCopy() {
        ActionArgument[] actionArgumentArr = new ActionArgument[getArguments().length];
        for (int i7 = 0; i7 < getArguments().length; i7++) {
            actionArgumentArr[i7] = getArguments()[i7].deepCopy();
        }
        return new Action<>(getName(), actionArgumentArr);
    }

    public ActionArgument[] getArguments() {
        return this.arguments;
    }

    public ActionArgument<S> getFirstInputArgument() {
        if (hasInputArguments()) {
            return getInputArguments()[0];
        }
        throw new IllegalStateException("No input arguments: " + this);
    }

    public ActionArgument<S> getFirstOutputArgument() {
        if (hasOutputArguments()) {
            return getOutputArguments()[0];
        }
        throw new IllegalStateException("No output arguments: " + this);
    }

    public ActionArgument<S> getInputArgument(String str) {
        for (ActionArgument<S> actionArgument : getInputArguments()) {
            if (actionArgument.isNameOrAlias(str)) {
                return actionArgument;
            }
        }
        return null;
    }

    public ActionArgument<S>[] getInputArguments() {
        return this.inputArguments;
    }

    public String getName() {
        return this.name;
    }

    public ActionArgument<S> getOutputArgument(String str) {
        for (ActionArgument<S> actionArgument : getOutputArguments()) {
            if (actionArgument.getName().equals(str)) {
                return actionArgument;
            }
        }
        return null;
    }

    public ActionArgument<S>[] getOutputArguments() {
        return this.outputArguments;
    }

    public S getService() {
        return this.service;
    }

    public boolean hasArguments() {
        return getArguments() != null && getArguments().length > 0;
    }

    public boolean hasInputArguments() {
        return getInputArguments() != null && getInputArguments().length > 0;
    }

    public boolean hasOutputArguments() {
        return getOutputArguments() != null && getOutputArguments().length > 0;
    }

    public void setService(S s6) {
        if (this.service != null) {
            throw new IllegalStateException("Final value has been set already, model is immutable");
        }
        this.service = s6;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(", Arguments: ");
        sbM112a.append(getArguments() != null ? Integer.valueOf(getArguments().length) : "NO ARGS");
        sbM112a.append(") ");
        sbM112a.append(getName());
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.Validatable
    public List<ValidationError> validate() {
        ArrayList arrayList = new ArrayList();
        if (getName() == null || getName().length() == 0) {
            Class<?> cls = getClass();
            StringBuilder sbM112a = C0413b.m112a("Action without name of: ");
            sbM112a.append(getService());
            arrayList.add(new ValidationError(cls, "name", sbM112a.toString()));
        } else if (!ModelUtil.isValidUDAName(getName())) {
            Logger logger = log;
            StringBuilder sbM112a2 = C0413b.m112a("UPnP specification violation of: ");
            sbM112a2.append(getService().getDevice());
            logger.warning(sbM112a2.toString());
            logger.warning("Invalid action name: " + this);
        }
        for (ActionArgument actionArgument : getArguments()) {
            if (getService().getStateVariable(actionArgument.getRelatedStateVariableName()) == null) {
                Class<?> cls2 = getClass();
                StringBuilder sbM112a3 = C0413b.m112a("Action argument references an unknown state variable: ");
                sbM112a3.append(actionArgument.getRelatedStateVariableName());
                arrayList.add(new ValidationError(cls2, "arguments", sbM112a3.toString()));
            }
        }
        ActionArgument actionArgument2 = null;
        int i7 = 0;
        int i8 = 0;
        for (ActionArgument actionArgument3 : getArguments()) {
            if (actionArgument3.isReturnValue()) {
                if (actionArgument3.getDirection() == ActionArgument.Direction.IN) {
                    Logger logger2 = log;
                    StringBuilder sbM112a4 = C0413b.m112a("UPnP specification violation of :");
                    sbM112a4.append(getService().getDevice());
                    logger2.warning(sbM112a4.toString());
                    logger2.warning("Input argument can not have <retval/>");
                } else {
                    if (actionArgument2 != null) {
                        Logger logger3 = log;
                        StringBuilder sbM112a5 = C0413b.m112a("UPnP specification violation of: ");
                        sbM112a5.append(getService().getDevice());
                        logger3.warning(sbM112a5.toString());
                        logger3.warning("Only one argument of action '" + getName() + "' can be <retval/>");
                    }
                    i8 = i7;
                    actionArgument2 = actionArgument3;
                }
            }
            i7++;
        }
        if (actionArgument2 != null) {
            for (int i9 = 0; i9 < i8; i9++) {
                if (getArguments()[i9].getDirection() == ActionArgument.Direction.OUT) {
                    Logger logger4 = log;
                    StringBuilder sbM112a6 = C0413b.m112a("UPnP specification violation of: ");
                    sbM112a6.append(getService().getDevice());
                    logger4.warning(sbM112a6.toString());
                    logger4.warning("Argument '" + actionArgument2.getName() + "' of action '" + getName() + "' is <retval/> but not the first OUT argument");
                }
            }
        }
        for (ActionArgument actionArgument4 : this.arguments) {
            arrayList.addAll(actionArgument4.validate());
        }
        return arrayList;
    }
}
