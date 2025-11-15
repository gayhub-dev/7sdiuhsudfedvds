package org.fourthline.cling.model.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.fourthline.cling.model.ServiceReference;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.Datatype;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.ServiceType;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class Service<D extends Device, S extends Service> {
    private static final Logger log = Logger.getLogger(Service.class.getName());
    private final Map<String, Action> actions;
    private D device;
    private final ServiceId serviceId;
    private final ServiceType serviceType;
    private final Map<String, StateVariable> stateVariables;

    public Service(ServiceType serviceType, ServiceId serviceId) {
        this(serviceType, serviceId, null, null);
    }

    public Action<S> getAction(String str) {
        Map<String, Action> map = this.actions;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public Action<S>[] getActions() {
        Map<String, Action> map = this.actions;
        if (map == null) {
            return null;
        }
        return (Action[]) map.values().toArray(new Action[this.actions.values().size()]);
    }

    public Datatype<S> getDatatype(ActionArgument actionArgument) {
        return getRelatedStateVariable(actionArgument).getTypeDetails().getDatatype();
    }

    public D getDevice() {
        return this.device;
    }

    public abstract Action getQueryStateVariableAction();

    public ServiceReference getReference() {
        return new ServiceReference(getDevice().getIdentity().getUdn(), getServiceId());
    }

    public StateVariable<S> getRelatedStateVariable(ActionArgument actionArgument) {
        return getStateVariable(actionArgument.getRelatedStateVariableName());
    }

    public ServiceId getServiceId() {
        return this.serviceId;
    }

    public ServiceType getServiceType() {
        return this.serviceType;
    }

    public StateVariable<S> getStateVariable(String str) {
        if (QueryStateVariableAction.VIRTUAL_STATEVARIABLE_INPUT.equals(str)) {
            return new StateVariable<>(QueryStateVariableAction.VIRTUAL_STATEVARIABLE_INPUT, new StateVariableTypeDetails(Datatype.Builtin.STRING.getDatatype()));
        }
        if (QueryStateVariableAction.VIRTUAL_STATEVARIABLE_OUTPUT.equals(str)) {
            return new StateVariable<>(QueryStateVariableAction.VIRTUAL_STATEVARIABLE_OUTPUT, new StateVariableTypeDetails(Datatype.Builtin.STRING.getDatatype()));
        }
        Map<String, StateVariable> map = this.stateVariables;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public StateVariable<S>[] getStateVariables() {
        Map<String, StateVariable> map = this.stateVariables;
        if (map == null) {
            return null;
        }
        return (StateVariable[]) map.values().toArray(new StateVariable[this.stateVariables.values().size()]);
    }

    public boolean hasActions() {
        return getActions() != null && getActions().length > 0;
    }

    public boolean hasStateVariables() {
        return getStateVariables() != null && getStateVariables().length > 0;
    }

    public void setDevice(D d7) {
        if (this.device != null) {
            throw new IllegalStateException("Final value has been set already, model is immutable");
        }
        this.device = d7;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") ServiceId: ");
        sbM112a.append(getServiceId());
        return sbM112a.toString();
    }

    public List<ValidationError> validate() {
        ArrayList arrayList = new ArrayList();
        if (getServiceType() == null) {
            arrayList.add(new ValidationError(getClass(), "serviceType", "Service type/info is required"));
        }
        if (getServiceId() == null) {
            arrayList.add(new ValidationError(getClass(), "serviceId", "Service ID is required"));
        }
        if (hasStateVariables()) {
            for (StateVariable<S> stateVariable : getStateVariables()) {
                arrayList.addAll(stateVariable.validate());
            }
        }
        if (hasActions()) {
            for (Action<S> action : getActions()) {
                List<ValidationError> listValidate = action.validate();
                if (listValidate.size() > 0) {
                    this.actions.remove(action.getName());
                    Logger logger = log;
                    StringBuilder sbM112a = C0413b.m112a("Discarding invalid action of service '");
                    sbM112a.append(getServiceId());
                    sbM112a.append("': ");
                    sbM112a.append(action.getName());
                    logger.warning(sbM112a.toString());
                    for (ValidationError validationError : listValidate) {
                        Logger logger2 = log;
                        StringBuilder sbM112a2 = C0413b.m112a("Invalid action '");
                        sbM112a2.append(action.getName());
                        sbM112a2.append("': ");
                        sbM112a2.append(validationError);
                        logger2.warning(sbM112a2.toString());
                    }
                }
            }
        }
        return arrayList;
    }

    public Service(ServiceType serviceType, ServiceId serviceId, Action<S>[] actionArr, StateVariable<S>[] stateVariableArr) {
        this.actions = new HashMap();
        this.stateVariables = new HashMap();
        this.serviceType = serviceType;
        this.serviceId = serviceId;
        if (actionArr != null) {
            for (Action<S> action : actionArr) {
                this.actions.put(action.getName(), action);
                action.setService(this);
            }
        }
        if (stateVariableArr != null) {
            for (StateVariable<S> stateVariable : stateVariableArr) {
                this.stateVariables.put(stateVariable.getName(), stateVariable);
                stateVariable.setService(this);
            }
        }
    }
}
