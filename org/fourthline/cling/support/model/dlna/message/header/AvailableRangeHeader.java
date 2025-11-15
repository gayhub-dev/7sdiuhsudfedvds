package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import org.fourthline.cling.model.message.header.InvalidHeaderException;
import org.fourthline.cling.support.model.dlna.types.NormalPlayTimeRange;

/* loaded from: classes.dex */
public class AvailableRangeHeader extends DLNAHeader<NormalPlayTimeRange> {
    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str.length() != 0) {
            try {
                setValue(NormalPlayTimeRange.valueOf(str, true));
                return;
            } catch (Exception unused) {
            }
        }
        throw new InvalidHeaderException(C0063n.m88a("Invalid AvailableRange header value: ", str));
    }
}
