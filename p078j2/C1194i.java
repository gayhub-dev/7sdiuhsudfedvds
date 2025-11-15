package p078j2;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.widget.ActivityChooserModel;

/* compiled from: MemoryUtils.java */
/* renamed from: j2.i */
/* loaded from: classes.dex */
public class C1194i {
    /* renamed from: a */
    public static long m1413a() {
        String externalStorageState = Environment.getExternalStorageState();
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        if (externalStorageState.equals("mounted")) {
            return ((statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1000) / 1000;
        }
        return -1L;
    }

    /* renamed from: b */
    public static long m1414b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return (memoryInfo.availMem / 1000) / 1000;
    }

    /* renamed from: c */
    public static String m1415c(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(m1414b(context));
        sb.append("MB/");
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        sb.append((memoryInfo.totalMem / 1000) / 1000);
        sb.append("MB");
        return sb.toString();
    }

    /* renamed from: d */
    public static long m1416d() {
        String externalStorageState = Environment.getExternalStorageState();
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        if (externalStorageState.equals("mounted")) {
            return ((statFs.getBlockCount() * statFs.getBlockSize()) / 1000) / 1000;
        }
        return -1L;
    }
}
