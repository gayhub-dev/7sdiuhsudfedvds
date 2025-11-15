package org.fourthline.cling.model.types;

import java.io.IOException;
import p106m6.C1498a;

/* loaded from: classes.dex */
public class Base64Datatype extends AbstractDatatype<byte[]> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype
    public Class<byte[]> getValueType() {
        return byte[].class;
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public String getString(byte[] bArr) throws Throwable {
        String strM1660d;
        if (bArr == null) {
            return "";
        }
        try {
            try {
                strM1660d = C1498a.m1660d(bArr, 0, bArr.length, 0);
            } catch (IOException unused) {
                strM1660d = null;
            }
            return new String(strM1660d.getBytes(), "UTF-8");
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
            return C1498a.m1657a(str);
        } catch (Exception e7) {
            throw new InvalidValueException(e7.getMessage(), e7);
        }
    }
}
