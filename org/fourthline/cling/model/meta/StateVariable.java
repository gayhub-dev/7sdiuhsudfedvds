package org.fourthline.cling.model.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.Validatable;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.Datatype;
import p009b.C0413b;

/* loaded from: classes.dex */
public class StateVariable<S extends Service> implements Validatable {
    private static final Logger log = Logger.getLogger(StateVariable.class.getName());
    private final StateVariableEventDetails eventDetails;
    private final String name;
    private S service;
    private final StateVariableTypeDetails type;

    public StateVariable(String str, StateVariableTypeDetails stateVariableTypeDetails) {
        this(str, stateVariableTypeDetails, new StateVariableEventDetails());
    }

    public StateVariable<S> deepCopy() {
        return new StateVariable<>(getName(), getTypeDetails(), getEventDetails());
    }

    public StateVariableEventDetails getEventDetails() {
        return this.eventDetails;
    }

    public String getName() {
        return this.name;
    }

    public S getService() {
        return this.service;
    }

    public StateVariableTypeDetails getTypeDetails() {
        return this.type;
    }

    public boolean isModeratedNumericType() {
        return Datatype.Builtin.isNumeric(getTypeDetails().getDatatype().getBuiltin()) && getEventDetails().getEventMinimumDelta() > 0;
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
        sbM112a.append(", Name: ");
        sbM112a.append(getName());
        sbM112a.append(", Type: ");
        sbM112a.append(getTypeDetails().getDatatype().getDisplayString());
        sbM112a.append(")");
        if (!getEventDetails().isSendEvents()) {
            sbM112a.append(" (No Events)");
        }
        if (getTypeDetails().getDefaultValue() != null) {
            sbM112a.append(" Default Value: ");
            sbM112a.append("'");
            sbM112a.append(getTypeDetails().getDefaultValue());
            sbM112a.append("'");
        }
        if (getTypeDetails().getAllowedValues() != null) {
            sbM112a.append(" Allowed Values: ");
            for (String str : getTypeDetails().getAllowedValues()) {
                sbM112a.append(str);
                sbM112a.append("|");
            }
        }
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.Validatable
    public List<ValidationError> validate() {
        ArrayList arrayList = new ArrayList();
        if (getName() == null || getName().length() == 0) {
            Class<?> cls = getClass();
            StringBuilder sbM112a = C0413b.m112a("StateVariable without name of: ");
            sbM112a.append(getService());
            arrayList.add(new ValidationError(cls, "name", sbM112a.toString()));
        } else if (!ModelUtil.isValidUDAName(getName())) {
            Logger logger = log;
            StringBuilder sbM112a2 = C0413b.m112a("UPnP specification violation of: ");
            sbM112a2.append(getService().getDevice());
            logger.warning(sbM112a2.toString());
            logger.warning("Invalid state variable name: " + this);
        }
        arrayList.addAll(getTypeDetails().validate());
        return arrayList;
    }

    public StateVariable(String str, StateVariableTypeDetails stateVariableTypeDetails, StateVariableEventDetails stateVariableEventDetails) {
        this.name = str;
        this.type = stateVariableTypeDetails;
        this.eventDetails = stateVariableEventDetails;
    }
}
