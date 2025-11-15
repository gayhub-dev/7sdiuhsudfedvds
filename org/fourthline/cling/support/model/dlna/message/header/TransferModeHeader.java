package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.message.header.InvalidHeaderException;

/* loaded from: classes.dex */
public class TransferModeHeader extends DLNAHeader<Type> {

    public enum Type {
        Streaming,
        Interactive,
        Background
    }

    public TransferModeHeader() {
        setValue(Type.Interactive);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str.length() != 0) {
            try {
                setValue(Type.valueOf(str));
                return;
            } catch (Exception unused) {
            }
        }
        throw new InvalidHeaderException(C0063n.m88a("Invalid TransferMode header value: ", str));
    }

    public TransferModeHeader(Type type) {
        setValue(type);
    }
}
