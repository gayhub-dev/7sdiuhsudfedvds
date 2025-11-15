package p078j2;

import android.net.Uri;
import android.text.TextUtils;

/* compiled from: DrmUtils.java */
/* renamed from: j2.g */
/* loaded from: classes.dex */
public class C1192g {
    /* renamed from: a */
    public static String m1411a(String str) {
        return TextUtils.isEmpty(str) ? "" : Uri.parse(str).getQueryParameter("contentid");
    }

    /* renamed from: b */
    public static boolean m1412b(String str) {
        return "2".equals(TextUtils.isEmpty(str) ? "" : Uri.parse(str).getQueryParameter("encrypt"));
    }
}
