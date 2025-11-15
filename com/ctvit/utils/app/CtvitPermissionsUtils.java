package com.ctvit.utils.app;

import android.support.annotation.NonNull;
import android.support.annotation.Size;
import java.util.ArrayList;
import p086k2.C1231b;
import pub.devrel.easypermissions.EasyPermissions;

/* loaded from: classes.dex */
public class CtvitPermissionsUtils {
    public static String[] permissions(@Size(min = 1) @NonNull String... strArr) {
        ArrayList arrayList = new ArrayList();
        if (!EasyPermissions.m1915a(C1231b.f2761c, strArr)) {
            for (String str : strArr) {
                if (!EasyPermissions.m1915a(C1231b.f2761c, str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList.isEmpty() ? strArr : (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
