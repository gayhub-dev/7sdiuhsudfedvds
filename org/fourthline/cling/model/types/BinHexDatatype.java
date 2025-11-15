package org.fourthline.cling.model.types;

import p106m6.C1499b;

/* loaded from: classes.dex */
public class BinHexDatatype extends AbstractDatatype<byte[]> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype
    public Class<byte[]> getValueType() {
        return byte[].class;
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public String getString(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            byte[] bArr2 = C1499b.f4299a;
            return new String(C1499b.m1663b(bArr));
        } catch (Exception e7) {
            throw new InvalidValueException(e7.getMessage(), e7);
        }
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public byte[] valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        try {
            byte[] bArr = C1499b.f4299a;
            return C1499b.m1662a(str.getBytes());
        } catch (Exception e7) {
            throw new InvalidValueException(e7.getMessage(), e7);
        }
    }
}
