package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;
import android.support.constraint.C0072a;
import p009b.C0413b;

/* loaded from: classes.dex */
public class PragmaType {
    private boolean quote;
    private String token;
    private String value;

    public PragmaType(String str, String str2, boolean z6) {
        this.token = str;
        this.value = str2;
        this.quote = z6;
    }

    public static PragmaType valueOf(String str) {
        if (str.length() == 0) {
            throw new InvalidValueException(C0063n.m88a("Can't parse Bytes Range: ", str));
        }
        String str2 = null;
        String[] strArrSplit = str.split("=");
        boolean z6 = false;
        if (strArrSplit.length > 1) {
            str2 = strArrSplit[0];
            str = strArrSplit[1];
            if (str.startsWith("\"") && str.endsWith("\"")) {
                str = str.substring(1, str.length() - 1);
                z6 = true;
            }
        }
        return new PragmaType(str2, str, z6);
    }

    public String getString() {
        StringBuilder sbM112a = C0413b.m112a(this.token != null ? C0072a.m92a(C0413b.m112a(""), this.token, "=") : "");
        sbM112a.append(this.quote ? C0072a.m92a(C0413b.m112a("\""), this.value, "\"") : this.value);
        return sbM112a.toString();
    }

    public String getToken() {
        return this.token;
    }

    public String getValue() {
        return this.value;
    }

    public PragmaType(String str, String str2) {
        this.token = str;
        this.value = str2;
    }

    public PragmaType(String str) {
        this.token = null;
        this.value = str;
    }
}
