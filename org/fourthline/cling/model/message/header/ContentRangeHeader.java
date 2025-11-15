package org.fourthline.cling.model.message.header;

import org.fourthline.cling.model.types.BytesRange;
import org.fourthline.cling.model.types.InvalidValueException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ContentRangeHeader extends UpnpHeader<BytesRange> {
    public static final String PREFIX = "bytes ";

    public ContentRangeHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().getString(true, PREFIX);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(BytesRange.valueOf(str, PREFIX));
        } catch (InvalidValueException e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid Range Header: ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public ContentRangeHeader(BytesRange bytesRange) {
        setValue(bytesRange);
    }

    public ContentRangeHeader(String str) {
        setString(str);
    }
}
