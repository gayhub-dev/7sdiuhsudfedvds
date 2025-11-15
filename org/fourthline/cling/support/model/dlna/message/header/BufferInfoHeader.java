package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.message.header.InvalidHeaderException;
import org.fourthline.cling.support.model.dlna.types.BufferInfoType;

/* loaded from: classes.dex */
public class BufferInfoHeader extends DLNAHeader<BufferInfoType> {
    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().getString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str.length() != 0) {
            try {
                setValue(BufferInfoType.valueOf(str));
                return;
            } catch (Exception unused) {
            }
        }
        throw new InvalidHeaderException(C0063n.m88a("Invalid BufferInfo header value: ", str));
    }
}
