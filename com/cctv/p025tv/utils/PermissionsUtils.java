package com.cctv.p025tv.utils;

import android.app.Activity;
import android.os.Build;
import com.cctv.p025tv.R;
import com.cctv.p025tv.app.MyApplication;
import com.ctvit.utils.app.CtvitPermissionsUtils;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import p078j2.C1189d;
import p094l2.C1420a;
import p122o6.AbstractC1595d;
import p200z2.C2150a;
import pub.devrel.easypermissions.C1753b;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.R$string;

/* loaded from: classes.dex */
public class PermissionsUtils {
    public static final String[] WRITE = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final List initList = new ArrayList(2);

    public static final void init() {
        File file = new File(C1420a.f4156d);
        if (!file.exists() && !file.mkdirs()) {
            initList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            saveReadWritePermissionsStatus(false);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            String str = Build.BRAND;
            if (str.contains("MStar") || "MStar".contains(str)) {
                return;
            }
            initList.add("android.permission.ACCESS_FINE_LOCATION");
        }
    }

    public static void initPermissions(Activity activity) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (activity != null) {
            List list = initList;
            if (list.isEmpty()) {
                return;
            }
            if (C1189d.m1402d(MyApplication.f561e)) {
                list.remove("android.permission.ACCESS_FINE_LOCATION");
            }
            int i7 = Build.VERSION.SDK_INT;
            if (i7 >= 30) {
                list.remove("android.permission.WRITE_EXTERNAL_STORAGE");
            }
            String[] strArrPermissions = CtvitPermissionsUtils.permissions((String[]) list.toArray(new String[list.size()]));
            AbstractC1595d<? extends Activity> abstractC1595dM1863c = AbstractC1595d.m1863c(activity);
            String string = abstractC1595dM1863c.mo1860b().getString(i7 >= 30 ? R.string.permissin_text_2 : R.string.permissin_text);
            String string2 = abstractC1595dM1863c.mo1860b().getString(R.string.request_permissions_ok);
            String string3 = abstractC1595dM1863c.mo1860b().getString(R.string.request_permissions_cancel);
            if (string == null) {
                string = abstractC1595dM1863c.mo1860b().getString(R$string.rationale_ask);
            }
            EasyPermissions.m1917c(new C1753b(abstractC1595dM1863c, strArrPermissions, 1001, string, string2 == null ? abstractC1595dM1863c.mo1860b().getString(android.R.string.ok) : string2, string3 == null ? abstractC1595dM1863c.mo1860b().getString(android.R.string.cancel) : string3, -1, null));
        }
    }

    public static boolean isReadWritePermissionsStatus() {
        if (Build.VERSION.SDK_INT >= 30) {
            return true;
        }
        return ((Boolean) C2150a.m2590a("READ_WRITE_PERMISSIONS_STATUS", Boolean.TRUE)).booleanValue();
    }

    public static void readWritePermissions(Activity activity) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String[] strArrPermissions = CtvitPermissionsUtils.permissions(WRITE);
        AbstractC1595d<? extends Activity> abstractC1595dM1863c = AbstractC1595d.m1863c(activity);
        String string = abstractC1595dM1863c.mo1860b().getString(R.string.permissin_text);
        String string2 = abstractC1595dM1863c.mo1860b().getString(R.string.request_permissions_ok);
        String string3 = abstractC1595dM1863c.mo1860b().getString(R.string.request_permissions_cancel);
        if (string == null) {
            string = abstractC1595dM1863c.mo1860b().getString(R$string.rationale_ask);
        }
        EasyPermissions.m1917c(new C1753b(abstractC1595dM1863c, strArrPermissions, 1000, string, string2 == null ? abstractC1595dM1863c.mo1860b().getString(android.R.string.ok) : string2, string3 == null ? abstractC1595dM1863c.mo1860b().getString(android.R.string.cancel) : string3, -1, null));
    }

    public static void saveReadWritePermissionsStatus(boolean z6) {
        C2150a.m2591b("READ_WRITE_PERMISSIONS_STATUS", Boolean.valueOf(z6));
    }
}
