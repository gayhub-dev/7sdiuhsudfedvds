package p087k3;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/* compiled from: PolyvDetection.java */
/* renamed from: k3.c */
/* loaded from: classes.dex */
public class C1234c {
    /* renamed from: a */
    public static boolean m1458a(Context context, String... strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: b */
    public static boolean m1459b(Context context) {
        boolean zIsWiredHeadsetOn;
        BluetoothAdapter defaultAdapter;
        boolean z6 = false;
        if (!(Build.VERSION.SDK_INT < 31 ? m1458a(context, "android.permission.BLUETOOTH") : m1458a(context, "android.permission.BLUETOOTH_CONNECT"))) {
            return false;
        }
        try {
            zIsWiredHeadsetOn = ((AudioManager) context.getSystemService("audio")).isWiredHeadsetOn();
            try {
                defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            } catch (Exception unused) {
                z6 = zIsWiredHeadsetOn;
            }
        } catch (Exception unused2) {
        }
        if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
            return zIsWiredHeadsetOn;
        }
        int profileConnectionState = defaultAdapter.getProfileConnectionState(2);
        int profileConnectionState2 = defaultAdapter.getProfileConnectionState(1);
        int profileConnectionState3 = defaultAdapter.getProfileConnectionState(3);
        if (zIsWiredHeadsetOn || profileConnectionState == 2 || profileConnectionState2 == 2 || profileConnectionState3 == 2) {
            z6 = true;
        }
        return z6;
    }
}
