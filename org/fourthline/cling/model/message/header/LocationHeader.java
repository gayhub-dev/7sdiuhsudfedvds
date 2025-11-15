package org.fourthline.cling.model.message.header;

import java.net.MalformedURLException;
import java.net.URL;
import p009b.C0413b;

/* loaded from: classes.dex */
public class LocationHeader extends UpnpHeader<URL> {
    public LocationHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            setValue(new URL(str));
        } catch (MalformedURLException e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid URI: ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public LocationHeader(URL url) {
        setValue(url);
    }

    public LocationHeader(String str) {
        setString(str);
    }
}
