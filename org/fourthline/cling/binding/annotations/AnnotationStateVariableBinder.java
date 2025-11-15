package org.fourthline.cling.binding.annotations;

import java.util.Set;
import java.util.logging.Logger;
import org.fourthline.cling.binding.AllowedValueProvider;
import org.fourthline.cling.binding.AllowedValueRangeProvider;
import org.fourthline.cling.binding.LocalServiceBindingException;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.meta.StateVariableAllowedValueRange;
import org.fourthline.cling.model.state.StateVariableAccessor;
import org.fourthline.cling.model.types.Datatype;
import p009b.C0413b;

/* loaded from: classes.dex */
public class AnnotationStateVariableBinder {
    private static Logger log = Logger.getLogger(AnnotationLocalServiceBinder.class.getName());
    public StateVariableAccessor accessor;
    public UpnpStateVariable annotation;
    public String name;
    public Set<Class> stringConvertibleTypes;

    public AnnotationStateVariableBinder(UpnpStateVariable upnpStateVariable, String str, StateVariableAccessor stateVariableAccessor, Set<Class> set) {
        this.annotation = upnpStateVariable;
        this.name = str;
        this.accessor = stateVariableAccessor;
        this.stringConvertibleTypes = set;
    }

    public Datatype createDatatype() {
        String strDatatype = getAnnotation().datatype();
        if (strDatatype.length() == 0 && getAccessor() != null) {
            Class<?> returnType = getAccessor().getReturnType();
            log.finer("Using accessor return type as state variable type: " + returnType);
            if (ModelUtil.isStringConvertibleType(getStringConvertibleTypes(), returnType)) {
                log.finer("Return type is string-convertible, using string datatype");
                return Datatype.Default.STRING.getBuiltinType().getDatatype();
            }
            Datatype.Default byJavaType = Datatype.Default.getByJavaType(returnType);
            if (byJavaType != null) {
                log.finer("Return type has default UPnP datatype: " + byJavaType);
                return byJavaType.getBuiltinType().getDatatype();
            }
        }
        if (strDatatype.length() == 0 && (getAnnotation().allowedValues().length > 0 || getAnnotation().allowedValuesEnum() != Void.TYPE)) {
            log.finer("State variable has restricted allowed values, hence using 'string' datatype");
            strDatatype = "string";
        }
        if (strDatatype.length() == 0) {
            StringBuilder sbM112a = C0413b.m112a("Could not detect datatype of state variable: ");
            sbM112a.append(getName());
            throw new LocalServiceBindingException(sbM112a.toString());
        }
        log.finer("Trying to find built-in UPnP datatype for detected name: " + strDatatype);
        Datatype.Builtin byDescriptorName = Datatype.Builtin.getByDescriptorName(strDatatype);
        if (byDescriptorName == null) {
            throw new LocalServiceBindingException("No built-in UPnP datatype found, using CustomDataType (TODO: NOT IMPLEMENTED)");
        }
        log.finer("Found built-in UPnP datatype: " + byDescriptorName);
        return byDescriptorName.getDatatype();
    }

    public String createDefaultValue(Datatype datatype) {
        if (getAnnotation().defaultValue().length() == 0) {
            return null;
        }
        try {
            datatype.valueOf(getAnnotation().defaultValue());
            log.finer("Found state variable default value: " + getAnnotation().defaultValue());
            return getAnnotation().defaultValue();
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Default value doesn't match datatype of state variable '");
            sbM112a.append(getName());
            sbM112a.append("': ");
            sbM112a.append(e7.getMessage());
            throw new LocalServiceBindingException(sbM112a.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x021b A[PHI: r5
      0x021b: PHI (r5v1 int) = (r5v0 int), (r5v6 int), (r5v6 int) binds: [B:62:0x01b4, B:68:0x01ec, B:70:0x01f6] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.fourthline.cling.model.meta.StateVariable createStateVariable() {
        /*
            Method dump skipped, instructions count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fourthline.cling.binding.annotations.AnnotationStateVariableBinder.createStateVariable():org.fourthline.cling.model.meta.StateVariable");
    }

    public StateVariableAccessor getAccessor() {
        return this.accessor;
    }

    public StateVariableAllowedValueRange getAllowedRangeFromProvider() {
        Class clsAllowedValueRangeProvider = getAnnotation().allowedValueRangeProvider();
        if (AllowedValueRangeProvider.class.isAssignableFrom(clsAllowedValueRangeProvider)) {
            try {
                AllowedValueRangeProvider allowedValueRangeProvider = (AllowedValueRangeProvider) clsAllowedValueRangeProvider.newInstance();
                return getAllowedValueRange(allowedValueRangeProvider.getMinimum(), allowedValueRangeProvider.getMaximum(), allowedValueRangeProvider.getStep());
            } catch (Exception e7) {
                StringBuilder sbM112a = C0413b.m112a("Allowed value range provider can't be instantiated: ");
                sbM112a.append(getName());
                throw new LocalServiceBindingException(sbM112a.toString(), e7);
            }
        }
        throw new LocalServiceBindingException("Allowed value range provider is not of type " + AllowedValueRangeProvider.class + ": " + getName());
    }

    public StateVariableAllowedValueRange getAllowedValueRange(long j7, long j8, long j9) {
        if (j8 >= j7) {
            return new StateVariableAllowedValueRange(j7, j8, j9);
        }
        StringBuilder sbM112a = C0413b.m112a("Allowed value range maximum is smaller than minimum: ");
        sbM112a.append(getName());
        throw new LocalServiceBindingException(sbM112a.toString());
    }

    public String[] getAllowedValues(Class cls) {
        if (!cls.isEnum()) {
            throw new LocalServiceBindingException("Allowed values type is not an Enum: " + cls);
        }
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Restricting allowed values of state variable to Enum: ");
        sbM112a.append(getName());
        logger.finer(sbM112a.toString());
        String[] strArr = new String[cls.getEnumConstants().length];
        for (int i7 = 0; i7 < cls.getEnumConstants().length; i7++) {
            Object obj = cls.getEnumConstants()[i7];
            if (obj.toString().length() > 32) {
                StringBuilder sbM112a2 = C0413b.m112a("Allowed value string (that is, Enum constant name) is longer than 32 characters: ");
                sbM112a2.append(obj.toString());
                throw new LocalServiceBindingException(sbM112a2.toString());
            }
            Logger logger2 = log;
            StringBuilder sbM112a3 = C0413b.m112a("Adding allowed value (converted to string): ");
            sbM112a3.append(obj.toString());
            logger2.finer(sbM112a3.toString());
            strArr[i7] = obj.toString();
        }
        return strArr;
    }

    public String[] getAllowedValuesFromProvider() {
        Class clsAllowedValueProvider = getAnnotation().allowedValueProvider();
        if (AllowedValueProvider.class.isAssignableFrom(clsAllowedValueProvider)) {
            try {
                return ((AllowedValueProvider) clsAllowedValueProvider.newInstance()).getValues();
            } catch (Exception e7) {
                StringBuilder sbM112a = C0413b.m112a("Allowed value provider can't be instantiated: ");
                sbM112a.append(getName());
                throw new LocalServiceBindingException(sbM112a.toString(), e7);
            }
        }
        throw new LocalServiceBindingException("Allowed value provider is not of type " + AllowedValueProvider.class + ": " + getName());
    }

    public UpnpStateVariable getAnnotation() {
        return this.annotation;
    }

    public String getName() {
        return this.name;
    }

    public Set<Class> getStringConvertibleTypes() {
        return this.stringConvertibleTypes;
    }
}
