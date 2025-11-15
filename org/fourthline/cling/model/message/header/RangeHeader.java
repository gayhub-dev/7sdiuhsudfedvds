package org.fourthline.cling.model.message.header;

import org.fourthline.cling.model.types.BytesRange;
import org.fourthline.cling.model.types.InvalidValueException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class RangeHeader extends UpnpHeader<BytesRange> {
    public RangeHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().getString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(BytesRange.valueOf(str));
        } catch (InvalidValueException e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid Range Header: ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public RangeHeader(BytesRange bytesRange) {
        setValue(bytesRange);
    }

    public RangeHeader(String str) {
        setString(str);
    }
}
