package org.fourthline.cling.model.message.header;

import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import p009b.C0413b;

/* loaded from: classes.dex */
public class EventSequenceHeader extends UpnpHeader<UnsignedIntegerFourBytes> {
    public EventSequenceHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (!"0".equals(str)) {
            while (str.startsWith("0")) {
                str = str.substring(1);
            }
        }
        try {
            setValue(new UnsignedIntegerFourBytes(str));
        } catch (NumberFormatException e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid event sequence, ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public EventSequenceHeader(long j7) {
        setValue(new UnsignedIntegerFourBytes(j7));
    }
}
