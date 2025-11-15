package p198z0;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import p019c1.C0504a;
import p043f.C0988e;
import p163u0.C1970a;
import p170v0.C2000e;

/* compiled from: CCTVCommonUtils.java */
/* renamed from: z0.a */
/* loaded from: classes.dex */
public class C2145a {

    /* renamed from: a */
    public static final /* synthetic */ int f6310a = 0;

    static {
        try {
            Class.class.getDeclaredMethod("forName", String.class);
            Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            Class.class.getDeclaredMethod("getDeclaredField", String.class);
        } catch (Exception e7) {
            C0988e.m977c(new C2000e(e7));
            if (C1970a.f5743a) {
                e7.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static String m2573a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e7) {
            C0988e.m977c(new C2000e(e7));
            if (!C1970a.f5743a) {
                return "";
            }
            e7.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static String m2574b() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        Date date = new Date();
        simpleDateFormat.format(date);
        return String.valueOf(date.getTime());
    }

    /* renamed from: c */
    public static String m2575c() {
        int i7 = C1970a.f5749g;
        return i7 == 1 ? "PHONE" : i7 == 2 ? "PAD" : i7 == 3 ? "TV" : "PHONE";
    }

    /* renamed from: d */
    public static String m2576d(Exception exc) throws IOException {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            exc.printStackTrace(printWriter);
            String str = "\r\n" + stringWriter.toString() + "\r\n";
            stringWriter.close();
            printWriter.close();
            return str;
        } catch (Exception unused) {
            return "ErrorInfoFromException";
        }
    }

    /* renamed from: e */
    public static String m2577e() {
        int i7 = C1970a.f5750h;
        return i7 == 2 ? "CHINESE" : i7 == 1 ? "TIBETAN" : i7 == 3 ? "ENGLISH" : "";
    }

    /* renamed from: f */
    public static String m2578f(Context context) {
        if (!TextUtils.isEmpty(C1970a.f5757o)) {
            return C1970a.f5757o;
        }
        String strM313a = C0504a.m313a(context, "CTVIT_MAC", "");
        if (!TextUtils.isEmpty(strM313a)) {
            C1970a.f5757o = strM313a;
            return strM313a;
        }
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null && connectionInfo.getMacAddress() != null) {
                String strTrim = connectionInfo.getMacAddress().trim();
                C1970a.f5757o = strTrim;
                C0504a.m314b(context, "CTVIT_MAC", strTrim);
                return strTrim;
            }
        } catch (Exception unused) {
        }
        return "";
    }

    /* renamed from: g */
    public static String m2579g() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception e7) {
            C0988e.m977c(new C2000e(e7));
            if (!C1970a.f5743a) {
                return "";
            }
            e7.printStackTrace();
            return "";
        }
    }

    /* renamed from: h */
    public static String m2580h(Context context) {
        if (context == null) {
            return "";
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "无网络";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "WIFI";
        }
        if (activeNetworkInfo.getType() != 0) {
            return "未知网络";
        }
        String subtypeName = activeNetworkInfo.getSubtypeName();
        int subtype = activeNetworkInfo.getSubtype();
        if (subtype == 1 || subtype == 2 || subtype == 4 || subtype == 7 || subtype == 11) {
            return "2G";
        }
        if (subtype != 3 && subtype != 5 && subtype != 6 && subtype != 8 && subtype != 9 && subtype != 10 && subtype != 12 && subtype != 14 && subtype != 15) {
            if (subtype == 13) {
                return "4G";
            }
            if (subtype == 20) {
                return "5G";
            }
            if (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA") && !subtypeName.equalsIgnoreCase("CDMA2000")) {
                return "其它";
            }
        }
        return "3G";
    }

    /* renamed from: i */
    public static String m2581i() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Exception e7) {
            C0988e.m977c(new C2000e(e7));
            if (!C1970a.f5743a) {
                return "";
            }
            e7.printStackTrace();
            return "";
        }
    }

    /* renamed from: j */
    public static String m2582j() {
        try {
            return Build.MODEL.replace(m2579g(), "").replace(" ", "");
        } catch (Exception e7) {
            C0988e.m977c(new C2000e(e7));
            if (C1970a.f5743a) {
                e7.printStackTrace();
            }
            return "";
        }
    }

    /* renamed from: k */
    public static String m2583k(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
        } catch (Exception e7) {
            C0988e.m977c(new C2000e(e7));
            if (!C1970a.f5743a) {
                return "";
            }
            e7.printStackTrace();
            return "";
        }
    }

    /* renamed from: l */
    public static String m2584l() {
        return "1.0.0";
    }

    /* renamed from: m */
    public static String m2585m() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        boolean zEquals = false;
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            Method method = cls.getMethod("getOsBrand", new Class[0]);
            ClassLoader classLoader = cls.getClassLoader();
            if (classLoader != null && classLoader.getParent() == null) {
                zEquals = "harmony".equals(method.invoke(cls, new Object[0]));
            }
        } catch (Exception unused) {
        }
        return zEquals ? "Harmony" : "Android";
    }

    /* renamed from: n */
    public static String m2586n(String str, int i7) {
        return TextUtils.isEmpty(str) ? "" : str.length() > i7 ? str.substring(0, i7) : str;
    }
}
