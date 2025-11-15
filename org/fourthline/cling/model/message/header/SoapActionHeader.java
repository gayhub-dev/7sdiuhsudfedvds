package org.fourthline.cling.model.message.header;

import java.net.URI;
import org.fourthline.cling.model.types.SoapActionType;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SoapActionHeader extends UpnpHeader<SoapActionType> {
    public SoapActionHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        StringBuilder sbM112a = C0413b.m112a("\"");
        sbM112a.append(getValue().toString());
        sbM112a.append("\"");
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        try {
            if (!str.startsWith("\"") && str.endsWith("\"")) {
                throw new InvalidHeaderException("Invalid SOAP action header, must be enclosed in doublequotes:" + str);
            }
            setValue(SoapActionType.valueOf(str.substring(1, str.length() - 1)));
        } catch (RuntimeException e7) {
            StringBuilder sbM112a = C0413b.m112a("Invalid SOAP action header value, ");
            sbM112a.append(e7.getMessage());
            throw new InvalidHeaderException(sbM112a.toString());
        }
    }

    public SoapActionHeader(URI uri) {
        setValue(SoapActionType.valueOf(uri.toString()));
    }

    public SoapActionHeader(SoapActionType soapActionType) {
        setValue(soapActionType);
    }

    public SoapActionHeader(String str) {
        setString(str);
    }
}
