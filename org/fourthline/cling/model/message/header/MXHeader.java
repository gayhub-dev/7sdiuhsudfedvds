package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;

/* loaded from: classes.dex */
public class MXHeader extends UpnpHeader<Integer> {
    public static final Integer DEFAULT_VALUE = 3;

    public MXHeader() {
        setValue(DEFAULT_VALUE);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            Integer numValueOf = Integer.valueOf(Integer.parseInt(str));
            if (numValueOf.intValue() < 0 || numValueOf.intValue() > 120) {
                setValue(DEFAULT_VALUE);
            } else {
                setValue(numValueOf);
            }
        } catch (Exception unused) {
            throw new InvalidHeaderException(C0063n.m88a("Can't parse MX seconds integer from: ", str));
        }
    }

    public MXHeader(Integer num) {
        setValue(num);
    }
}
