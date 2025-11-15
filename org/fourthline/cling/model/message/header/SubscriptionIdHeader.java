package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;

/* loaded from: classes.dex */
public class SubscriptionIdHeader extends UpnpHeader<String> {
    public static final String PREFIX = "uuid:";

    public SubscriptionIdHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (!str.startsWith("uuid:")) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid subscription ID header value, must start with 'uuid:': ", str));
        }
        setValue(str);
    }

    public SubscriptionIdHeader(String str) {
        setValue(str);
    }
}
