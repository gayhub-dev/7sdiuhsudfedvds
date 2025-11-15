package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;

/* loaded from: classes.dex */
public class FloatDatatype extends AbstractDatatype<Float> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public boolean isHandlingJavaType(Class cls) {
        return cls == Float.TYPE || Float.class.isAssignableFrom(cls);
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public Float valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        try {
            return Float.valueOf(Float.parseFloat(str.trim()));
        } catch (NumberFormatException e7) {
            throw new InvalidValueException(C0063n.m88a("Can't convert string to number: ", str), e7);
        }
    }
}
