package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;

/* loaded from: classes.dex */
public class DoubleDatatype extends AbstractDatatype<Double> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public boolean isHandlingJavaType(Class cls) {
        return cls == Double.TYPE || Double.class.isAssignableFrom(cls);
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public Double valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(str));
        } catch (NumberFormatException e7) {
            throw new InvalidValueException(C0063n.m88a("Can't convert string to number: ", str), e7);
        }
    }
}
