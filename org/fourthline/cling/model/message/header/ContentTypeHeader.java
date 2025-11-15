package org.fourthline.cling.model.message.header;

import p098l6.C1448b;

/* loaded from: classes.dex */
public class ContentTypeHeader extends UpnpHeader<C1448b> {
    public static final C1448b DEFAULT_CONTENT_TYPE = C1448b.m1633a("text/xml");
    public static final C1448b DEFAULT_CONTENT_TYPE_UTF8 = C1448b.m1633a("text/xml;charset=\"utf-8\"");

    public ContentTypeHeader() {
        setValue(DEFAULT_CONTENT_TYPE);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    public boolean isText() {
        return getValue() != null && getValue().f4204a.equals(DEFAULT_CONTENT_TYPE.f4204a);
    }

    public boolean isUDACompliantXML() {
        return isText() && getValue().f4205b.equals(DEFAULT_CONTENT_TYPE.f4205b);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        setValue(C1448b.m1633a(str));
    }

    public ContentTypeHeader(C1448b c1448b) {
        setValue(c1448b);
    }

    public ContentTypeHeader(String str) {
        setString(str);
    }
}
