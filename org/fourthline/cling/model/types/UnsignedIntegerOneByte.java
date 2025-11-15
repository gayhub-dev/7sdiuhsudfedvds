package org.fourthline.cling.model.types;

import org.fourthline.cling.model.types.UnsignedVariableInteger;

/* loaded from: classes.dex */
public final class UnsignedIntegerOneByte extends UnsignedVariableInteger {
    public UnsignedIntegerOneByte(long j7) {
        super(j7);
    }

    @Override // org.fourthline.cling.model.types.UnsignedVariableInteger
    public UnsignedVariableInteger.Bits getBits() {
        return UnsignedVariableInteger.Bits.EIGHT;
    }

    public UnsignedIntegerOneByte(String str) {
        super(str);
    }
}
