package org.fourthline.cling.model.message.header;

import org.fourthline.cling.model.types.InvalidValueException;
import org.fourthline.cling.model.types.PragmaType;
import p009b.C0413b;

/* loaded from: classes.dex */
public class PragmaHeader extends UpnpHeader<PragmaType> {
    public PragmaHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().getString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(PragmaType.valueOf(str));
        } catch (InvalidValueException e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid Range Header: ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public PragmaHeader(PragmaType pragmaType) {
        setValue(pragmaType);
    }

    public PragmaHeader(String str) {
        setString(str);
    }
}
