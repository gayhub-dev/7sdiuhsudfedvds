package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.message.header.InvalidHeaderException;

/* loaded from: classes.dex */
public class SCIDHeader extends DLNAHeader<String> {
    public SCIDHeader() {
        setValue("");
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str.length() == 0) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid SCID header value: ", str));
        }
        setValue(str);
    }
}
