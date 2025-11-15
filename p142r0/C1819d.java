package p142r0;

import android.os.SystemClock;

/* compiled from: LogTime.java */
/* renamed from: r0.d */
/* loaded from: classes.dex */
public final class C1819d {

    /* renamed from: a */
    public static final double f5291a = 1.0d / Math.pow(10.0d, 6.0d);

    /* renamed from: b */
    public static final /* synthetic */ int f5292b = 0;

    /* renamed from: a */
    public static double m2050a(long j7) {
        return (SystemClock.elapsedRealtimeNanos() - j7) * f5291a;
    }
}
