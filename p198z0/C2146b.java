package p198z0;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import java.util.UUID;
import p019c1.C0504a;
import p019c1.C0505b;
import p163u0.C1970a;

/* compiled from: CCTVDeviceUtils.java */
/* renamed from: z0.b */
/* loaded from: classes.dex */
public class C2146b {
    /* renamed from: a */
    public static String m2587a(Context context) {
        if (!TextUtils.isEmpty(C1970a.f5758p)) {
            return C1970a.f5758p;
        }
        String strM313a = C0504a.m313a(context, "CTVIT_ANDROID_ID", "");
        if (!TextUtils.isEmpty(strM313a)) {
            C1970a.f5758p = strM313a;
            return strM313a;
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            C1970a.f5758p = string;
            C0504a.m314b(context, "CTVIT_ANDROID_ID", string);
            return string;
        } catch (Exception e7) {
            if (C1970a.f5743a) {
                e7.printStackTrace();
            }
            return "";
        }
    }

    /* renamed from: b */
    public static String m2588b(Context context) {
        String strM316b;
        if (!TextUtils.isEmpty(C1970a.f5755m)) {
            return C1970a.f5755m;
        }
        try {
            strM316b = C0505b.m316b(context);
        } catch (Exception e7) {
            if (C1970a.f5743a) {
                e7.printStackTrace();
            }
            strM316b = "";
        }
        C1970a.f5755m = strM316b;
        return strM316b;
    }

    /* renamed from: c */
    public static String m2589c(Context context) {
        if (!TextUtils.isEmpty(C1970a.f5756n)) {
            return C1970a.f5756n;
        }
        int i7 = Build.VERSION.SDK_INT;
        String strM2587a = "";
        String strM315a = i7 < 29 ? C0505b.m315a() : "";
        if (!TextUtils.isEmpty(strM315a)) {
            C1970a.f5756n = strM315a;
            return strM315a;
        }
        try {
            if (TextUtils.isEmpty("")) {
                strM2587a = m2587a(context);
            }
            if (TextUtils.isEmpty(strM2587a) || "9774d56d682e549c".equals(strM2587a)) {
                strM2587a = UUID.randomUUID().toString();
            }
            if (i7 < 29) {
                C0505b.m317c(strM2587a);
            }
        } catch (Exception e7) {
            if (C1970a.f5743a) {
                e7.printStackTrace();
            }
        }
        C1970a.f5756n = strM2587a;
        return strM2587a;
    }
}
