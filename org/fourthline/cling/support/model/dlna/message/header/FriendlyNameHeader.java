package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.message.header.InvalidHeaderException;

/* loaded from: classes.dex */
public class FriendlyNameHeader extends DLNAHeader<String> {
    public FriendlyNameHeader() {
        setValue("");
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str.length() == 0) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid GetAvailableSeekRange header value: ", str));
        }
        setValue(str);
    }

    public FriendlyNameHeader(String str) {
        setValue(str);
    }
}
