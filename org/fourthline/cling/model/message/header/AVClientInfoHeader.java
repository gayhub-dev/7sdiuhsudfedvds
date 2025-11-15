package org.fourthline.cling.model.message.header;

/* loaded from: classes.dex */
public class AVClientInfoHeader extends UpnpHeader<String> {
    public AVClientInfoHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        setValue(str);
    }

    public AVClientInfoHeader(String str) {
        setValue(str);
    }
}
