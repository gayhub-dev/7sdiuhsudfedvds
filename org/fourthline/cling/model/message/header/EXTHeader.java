package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;

/* loaded from: classes.dex */
public class EXTHeader extends UpnpHeader<String> {
    public static final String DEFAULT_VALUE = "";

    public EXTHeader() {
        setValue("");
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str != null && str.length() > 0) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid EXT header, it has no value: ", str));
        }
    }
}
