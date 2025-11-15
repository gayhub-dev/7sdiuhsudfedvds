package p015b5;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/* compiled from: Cookie.java */
/* renamed from: b5.a */
/* loaded from: classes.dex */
public class C0456a implements Cloneable, Serializable {

    /* renamed from: g */
    public static final String f274g;

    /* renamed from: h */
    public static ResourceBundle f275h = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");
    private static final long serialVersionUID = -6454587001725327448L;

    /* renamed from: e */
    public String f276e;

    /* renamed from: f */
    public String f277f;

    static {
        if (Boolean.valueOf(System.getProperty("org.glassfish.web.rfc2109_cookie_names_enforced", "true")).booleanValue()) {
            f274g = "/()<>@,;:\\\"[]?={} \t";
        } else {
            f274g = ",; ";
        }
    }

    public C0456a(String str, String str2) {
        boolean z6;
        if (str.length() == 0) {
            throw new IllegalArgumentException(f275h.getString("err.cookie_name_blank"));
        }
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt < ' ' || cCharAt >= 127 || f274g.indexOf(cCharAt) != -1) {
                z6 = false;
                break;
            }
        }
        z6 = true;
        if (!z6 || str.equalsIgnoreCase("Comment") || str.equalsIgnoreCase("Discard") || str.equalsIgnoreCase("Domain") || str.equalsIgnoreCase("Expires") || str.equalsIgnoreCase("Max-Age") || str.equalsIgnoreCase("Path") || str.equalsIgnoreCase("Secure") || str.equalsIgnoreCase("Version") || str.startsWith("$")) {
            throw new IllegalArgumentException(MessageFormat.format(f275h.getString("err.cookie_name_is_token"), str));
        }
        this.f276e = str;
        this.f277f = str2;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e7) {
            throw new RuntimeException(e7.getMessage());
        }
    }
}
