package p127p1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import p009b.C0413b;
import p156t0.C1896a;
import p186x2.C2073a;
import p198z0.C2145a;

/* compiled from: CCTVCommonUtils.java */
/* renamed from: p1.a */
/* loaded from: classes.dex */
public class C1735a {

    /* renamed from: a */
    public static final /* synthetic */ int f4920a = 0;

    static {
        try {
            Class.class.getDeclaredMethod("forName", String.class);
            Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            Class.class.getDeclaredMethod("getDeclaredField", String.class);
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("report getSubId e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
        }
    }

    /* renamed from: a */
    public static String m1877a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("report getAppVersion e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
            return "";
        }
    }

    /* renamed from: b */
    public static String m1878b() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        Date date = new Date();
        simpleDateFormat.format(date);
        return String.valueOf(date.getTime());
    }

    /* renamed from: c */
    public static String m1879c(Context context) {
        Objects.requireNonNull(C1896a.m2197a());
        return C2145a.m2578f(context);
    }

    /* renamed from: d */
    public static String m1880d() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("report getManufacturer e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
            return "";
        }
    }

    /* renamed from: e */
    public static String m1881e(Context context) {
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

    /* renamed from: f */
    public static String m1882f() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("report getOsVersion e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
            return "";
        }
    }

    /* renamed from: g */
    public static String m1883g() {
        try {
            return Build.MODEL.replace(m1880d(), "").replace(" ", "");
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("report getProductModel e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
            return "";
        }
    }

    /* renamed from: h */
    public static String m1884h(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("report getResolution e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
            return "";
        }
    }

    /* renamed from: i */
    public static String m1885i() throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        boolean zEquals = false;
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            Method method = cls.getMethod("getOsBrand", new Class[0]);
            ClassLoader classLoader = cls.getClassLoader();
            if (classLoader != null && classLoader.getParent() == null) {
                zEquals = "harmony".equals(method.invoke(cls, new Object[0]));
            }
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("report isHarmonyOSa e = ");
            sbM112a.append(e7.getMessage());
            C2073a.m2456a(sbM112a.toString());
        }
        return zEquals ? "Harmony" : "Android";
    }

    /* renamed from: j */
    public static String m1886j(String str, int i7) {
        return TextUtils.isEmpty(str) ? "" : str.length() > i7 ? str.substring(0, i7) : str;
    }
}
