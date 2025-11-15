package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.message.header.InvalidHeaderException;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;

/* loaded from: classes.dex */
public class BufferBytesHeader extends DLNAHeader<UnsignedIntegerFourBytes> {
    public BufferBytesHeader() {
        setValue(new UnsignedIntegerFourBytes(0L));
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(new UnsignedIntegerFourBytes(str));
        } catch (NumberFormatException unused) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid header value: ", str));
        }
    }
}
