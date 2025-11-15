package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;

/* loaded from: classes.dex */
public class ShortDatatype extends AbstractDatatype<Short> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public boolean isHandlingJavaType(Class cls) {
        return cls == Short.TYPE || Short.class.isAssignableFrom(cls);
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public Short valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        try {
            Short shValueOf = Short.valueOf(Short.parseShort(str.trim()));
            if (isValid(shValueOf)) {
                return shValueOf;
            }
            throw new InvalidValueException("Not a valid short: " + str);
        } catch (NumberFormatException e7) {
            throw new InvalidValueException(C0063n.m88a("Can't convert string to number: ", str), e7);
        }
    }
}
