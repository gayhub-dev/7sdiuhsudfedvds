package p183x;

import android.support.v4.util.Pools;
import p149s0.AbstractC1863d;
import p149s0.C1860a;

/* compiled from: LockedResource.java */
/* renamed from: x.q */
/* loaded from: classes.dex */
public final class C2056q<Z> implements InterfaceC2057r<Z>, C1860a.d {

    /* renamed from: i */
    public static final Pools.Pool<C2056q<?>> f6118i = new C1860a.c(new Pools.SynchronizedPool(20), new a(), C1860a.f5434a);

    /* renamed from: e */
    public final AbstractC1863d f6119e = new AbstractC1863d.b();

    /* renamed from: f */
    public InterfaceC2057r<Z> f6120f;

    /* renamed from: g */
    public boolean f6121g;

    /* renamed from: h */
    public boolean f6122h;

    /* compiled from: LockedResource.java */
    /* renamed from: x.q$a */
    public static class a implements C1860a.b<C2056q<?>> {
        @Override // p149s0.C1860a.b
        /* renamed from: a */
        public C2056q<?> mo1703a() {
            return new C2056q<>();
        }
    }

    /* renamed from: a */
    public static <Z> C2056q<Z> m2445a(InterfaceC2057r<Z> interfaceC2057r) {
        C2056q<Z> c2056q = (C2056q) ((C1860a.c) f6118i).acquire();
        c2056q.f6122h = false;
        c2056q.f6121g = true;
        c2056q.f6120f = interfaceC2057r;
        return c2056q;
    }

    @Override // p183x.InterfaceC2057r
    /* renamed from: b */
    public Class<Z> mo824b() {
        return this.f6120f.mo824b();
    }

    /* renamed from: c */
    public synchronized void m2446c() {
        this.f6119e.mo2133a();
        if (!this.f6121g) {
            throw new IllegalStateException("Already unlocked");
        }
        this.f6121g = false;
        if (this.f6122h) {
            recycle();
        }
    }

    @Override // p149s0.C1860a.d
    /* renamed from: e */
    public AbstractC1863d mo1694e() {
        return this.f6119e;
    }

    @Override // p183x.InterfaceC2057r
    public Z get() {
        return this.f6120f.get();
    }

    @Override // p183x.InterfaceC2057r
    public int getSize() {
        return this.f6120f.getSize();
    }

    @Override // p183x.InterfaceC2057r
    public synchronized void recycle() {
        this.f6119e.mo2133a();
        this.f6122h = true;
        if (!this.f6121g) {
            this.f6120f.recycle();
            this.f6120f = null;
            ((C1860a.c) f6118i).release(this);
        }
    }
}
