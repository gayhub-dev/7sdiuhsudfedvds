package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p009b.C0413b;

/* loaded from: classes.dex */
public class MANHeader extends UpnpHeader<String> {
    public String namespace;
    public static final Pattern PATTERN = Pattern.compile("\"(.+?)\"(;.+?)??");
    public static final Pattern NAMESPACE_PATTERN = Pattern.compile(";\\s?ns\\s?=\\s?([0-9]{2})");

    public MANHeader() {
    }

    public String getNamespace() {
        return this.namespace;
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        if (getValue() == null) {
            return null;
        }
        StringBuilder sbM112a = C0413b.m112a("\"");
        sbM112a.append(getValue());
        sbM112a.append("\"");
        if (getNamespace() != null) {
            sbM112a.append("; ns=");
            sbM112a.append(getNamespace());
        }
        return sbM112a.toString();
    }

    public void setNamespace(String str) {
        this.namespace = str;
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        Matcher matcher = PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid MAN header value: ", str));
        }
        setValue(matcher.group(1));
        if (matcher.group(2) != null) {
            Matcher matcher2 = NAMESPACE_PATTERN.matcher(matcher.group(2));
            if (!matcher2.matches()) {
                throw new InvalidHeaderException(C0063n.m88a("Invalid namespace in MAN header value: ", str));
            }
            setNamespace(matcher2.group(1));
        }
    }

    public MANHeader(String str) {
        setValue(str);
    }

    public MANHeader(String str, String str2) {
        this(str);
        this.namespace = str2;
    }
}
