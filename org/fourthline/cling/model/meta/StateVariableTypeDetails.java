package org.fourthline.cling.model.meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.fourthline.cling.model.Validatable;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.types.Datatype;
import p009b.C0413b;

/* loaded from: classes.dex */
public class StateVariableTypeDetails implements Validatable {
    private static final Logger log = Logger.getLogger(StateVariableTypeDetails.class.getName());
    private final StateVariableAllowedValueRange allowedValueRange;
    private final String[] allowedValues;
    private final Datatype datatype;
    private final String defaultValue;

    public StateVariableTypeDetails(Datatype datatype) {
        this(datatype, null, null, null);
    }

    public boolean foundDefaultInAllowedValues(String str, String[] strArr) {
        if (str == null || strArr == null) {
            return true;
        }
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public StateVariableAllowedValueRange getAllowedValueRange() {
        return this.allowedValueRange;
    }

    public String[] getAllowedValues() {
        if (foundDefaultInAllowedValues(this.defaultValue, this.allowedValues)) {
            return this.allowedValues;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(this.allowedValues));
        arrayList.add(getDefaultValue());
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public Datatype getDatatype() {
        return this.datatype;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    @Override // org.fourthline.cling.model.Validatable
    public List<ValidationError> validate() {
        ArrayList arrayList = new ArrayList();
        if (getDatatype() == null) {
            arrayList.add(new ValidationError(getClass(), "datatype", "Service state variable has no datatype"));
        }
        if (getAllowedValues() != null) {
            if (getAllowedValueRange() != null) {
                arrayList.add(new ValidationError(getClass(), "allowedValues", "Allowed value list of state variable can not also be restricted with allowed value range"));
            }
            if (!Datatype.Builtin.STRING.equals(getDatatype().getBuiltin())) {
                Class<?> cls = getClass();
                StringBuilder sbM112a = C0413b.m112a("Allowed value list of state variable only available for string datatype, not: ");
                sbM112a.append(getDatatype());
                arrayList.add(new ValidationError(cls, "allowedValues", sbM112a.toString()));
            }
            for (String str : getAllowedValues()) {
                if (str.length() > 31) {
                    log.warning("UPnP specification violation, allowed value string must be less than 32 chars: " + str);
                }
            }
            if (!foundDefaultInAllowedValues(this.defaultValue, this.allowedValues)) {
                Logger logger = log;
                StringBuilder sbM112a2 = C0413b.m112a("UPnP specification violation, allowed string values don't contain default value: ");
                sbM112a2.append(this.defaultValue);
                logger.warning(sbM112a2.toString());
            }
        }
        if (getAllowedValueRange() != null) {
            arrayList.addAll(getAllowedValueRange().validate());
        }
        return arrayList;
    }

    public StateVariableTypeDetails(Datatype datatype, String str) {
        this(datatype, str, null, null);
    }

    public StateVariableTypeDetails(Datatype datatype, String str, String[] strArr, StateVariableAllowedValueRange stateVariableAllowedValueRange) {
        this.datatype = datatype;
        this.defaultValue = str;
        this.allowedValues = strArr;
        this.allowedValueRange = stateVariableAllowedValueRange;
    }
}
