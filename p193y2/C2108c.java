package p193y2;

import java.util.Timer;

/* compiled from: CtvitNetSpeedUtils.java */
/* renamed from: y2.c */
/* loaded from: classes.dex */
public class C2108c {

    /* renamed from: a */
    public long f6240a;

    /* renamed from: b */
    public int f6241b = 1;

    /* renamed from: c */
    public a f6242c;

    /* renamed from: d */
    public Timer f6243d;

    /* compiled from: CtvitNetSpeedUtils.java */
    /* renamed from: y2.c$a */
    public interface a {
    }

    /* renamed from: a */
    public void m2551a() {
        Timer timer = this.f6243d;
        if (timer != null) {
            timer.cancel();
        }
    }
}
