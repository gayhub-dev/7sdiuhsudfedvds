package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;

/* loaded from: classes.dex */
public class UnsignedIntegerTwoBytesDatatype extends AbstractDatatype<UnsignedIntegerTwoBytes> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public UnsignedIntegerTwoBytes valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        try {
            return new UnsignedIntegerTwoBytes(str);
        } catch (NumberFormatException e7) {
            throw new InvalidValueException(C0063n.m88a("Can't convert string to number or not in range: ", str), e7);
        }
    }
}
