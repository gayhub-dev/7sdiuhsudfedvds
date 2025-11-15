package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;

/* loaded from: classes.dex */
public class UnsignedIntegerOneByteDatatype extends AbstractDatatype<UnsignedIntegerOneByte> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public UnsignedIntegerOneByte valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        try {
            return new UnsignedIntegerOneByte(str);
        } catch (NumberFormatException e7) {
            throw new InvalidValueException(C0063n.m88a("Can't convert string to number or not in range: ", str), e7);
        }
    }
}
