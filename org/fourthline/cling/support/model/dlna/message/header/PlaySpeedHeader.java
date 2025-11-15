package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.message.header.InvalidHeaderException;
import org.fourthline.cling.model.types.InvalidValueException;
import org.fourthline.cling.support.avtransport.lastchange.AVTransportVariable;

/* loaded from: classes.dex */
public class PlaySpeedHeader extends DLNAHeader<AVTransportVariable.TransportPlaySpeed> {
    public PlaySpeedHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().getValue();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str.length() != 0) {
            try {
                setValue(new AVTransportVariable.TransportPlaySpeed(str));
                return;
            } catch (InvalidValueException unused) {
            }
        }
        throw new InvalidHeaderException(C0063n.m88a("Invalid PlaySpeed header value: ", str));
    }

    public PlaySpeedHeader(AVTransportVariable.TransportPlaySpeed transportPlaySpeed) {
        setValue(transportPlaySpeed);
    }
}
