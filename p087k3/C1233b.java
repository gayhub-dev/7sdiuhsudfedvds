package p087k3;

import android.content.Context;
import android.media.AudioManager;

/* compiled from: PolyvControlUtils.java */
/* renamed from: k3.b */
/* loaded from: classes.dex */
public class C1233b {
    /* renamed from: a */
    public static int m1457a(Context context, int i7) {
        return Math.max((int) Math.ceil(100.0d / ((AudioManager) context.getSystemService("audio")).getStreamMaxVolume(3)), i7);
    }
}
