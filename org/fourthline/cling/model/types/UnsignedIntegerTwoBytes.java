package org.fourthline.cling.model.types;

import org.fourthline.cling.model.types.UnsignedVariableInteger;

/* loaded from: classes.dex */
public final class UnsignedIntegerTwoBytes extends UnsignedVariableInteger {
    public UnsignedIntegerTwoBytes(long j7) {
        super(j7);
    }

    @Override // org.fourthline.cling.model.types.UnsignedVariableInteger
    public UnsignedVariableInteger.Bits getBits() {
        return UnsignedVariableInteger.Bits.SIXTEEN;
    }

    public UnsignedIntegerTwoBytes(String str) {
        super(str);
    }
}
