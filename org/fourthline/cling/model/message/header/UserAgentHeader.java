package org.fourthline.cling.model.message.header;

/* loaded from: classes.dex */
public class UserAgentHeader extends UpnpHeader<String> {
    public UserAgentHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        setValue(str);
    }

    public UserAgentHeader(String str) {
        setValue(str);
    }
}
