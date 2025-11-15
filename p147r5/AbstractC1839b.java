package p147r5;

import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import p113n5.InterfaceC1558s;
import p168u5.AbstractC1980a;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AbstractSessionIdManager.java */
/* renamed from: r5.b */
/* loaded from: classes.dex */
public abstract class AbstractC1839b extends AbstractC1980a implements InterfaceC1558s {

    /* renamed from: h */
    public static final InterfaceC2016c f5355h;

    /* renamed from: e */
    public Random f5356e;

    /* renamed from: f */
    public boolean f5357f;

    /* renamed from: g */
    public long f5358g = 100000;

    static {
        Properties properties = C2015b.f5863a;
        f5355h = C2015b.m2349a(AbstractC1839b.class.getName());
    }

    @Override // p168u5.AbstractC1980a
    public void doStart() {
        Random random = this.f5356e;
        if (random != null) {
            random.setSeed(((random.nextLong() ^ System.currentTimeMillis()) ^ hashCode()) ^ Runtime.getRuntime().freeMemory());
            return;
        }
        try {
            this.f5356e = new SecureRandom();
        } catch (Exception e7) {
            f5355h.mo2354e("Could not generate SecureRandom for session-id randomness", e7);
            this.f5356e = new Random();
            this.f5357f = true;
        }
    }

    @Override // p168u5.AbstractC1980a
    public void doStop() {
    }
}
