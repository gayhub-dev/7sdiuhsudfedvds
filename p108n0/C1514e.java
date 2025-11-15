package p108n0;

import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.util.Pools;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import p009b.C0413b;
import p116o0.InterfaceC1576e;
import p116o0.InterfaceC1577f;
import p126p0.C1732a;
import p126p0.InterfaceC1734c;
import p141r.C1810e;
import p141r.EnumC1811f;
import p142r0.C1819d;
import p142r0.C1823h;
import p149s0.AbstractC1863d;
import p149s0.C1860a;
import p162u.C1966j;
import p162u.EnumC1957a;
import p162u.InterfaceC1964h;
import p162u.InterfaceC1969m;
import p183x.AbstractC2047h;
import p183x.C2044e;
import p183x.C2048i;
import p183x.C2049j;
import p183x.C2051l;
import p183x.C2052m;
import p183x.C2053n;
import p183x.InterfaceC2043d;
import p183x.InterfaceC2057r;
import p183x.RunnableC2045f;
import p197z.C2141h;

/* compiled from: SingleRequest.java */
/* renamed from: n0.e */
/* loaded from: classes.dex */
public final class C1514e<R> implements InterfaceC1510a, InterfaceC1576e, InterfaceC1513d, C1860a.d {

    /* renamed from: A */
    public static final Pools.Pool<C1514e<?>> f4364A = C1860a.m2131a(150, new a());

    /* renamed from: B */
    public static boolean f4365B = true;

    /* renamed from: e */
    public final AbstractC1863d f4366e;

    /* renamed from: f */
    public C1515f f4367f;

    /* renamed from: g */
    public C1810e f4368g;

    /* renamed from: h */
    @Nullable
    public Object f4369h;

    /* renamed from: i */
    public Class<R> f4370i;

    /* renamed from: j */
    public C1512c f4371j;

    /* renamed from: k */
    public int f4372k;

    /* renamed from: l */
    public int f4373l;

    /* renamed from: m */
    public EnumC1811f f4374m;

    /* renamed from: n */
    public InterfaceC1577f<R> f4375n;

    /* renamed from: o */
    public InterfaceC1511b<R> f4376o;

    /* renamed from: p */
    public C2048i f4377p;

    /* renamed from: q */
    public InterfaceC1734c<? super R> f4378q;

    /* renamed from: r */
    public InterfaceC2057r<R> f4379r;

    /* renamed from: s */
    public C2048i.d f4380s;

    /* renamed from: t */
    public long f4381t;

    /* renamed from: u */
    public int f4382u;

    /* renamed from: v */
    public Drawable f4383v;

    /* renamed from: w */
    public Drawable f4384w;

    /* renamed from: x */
    public Drawable f4385x;

    /* renamed from: y */
    public int f4386y;

    /* renamed from: z */
    public int f4387z;

    /* compiled from: SingleRequest.java */
    /* renamed from: n0.e$a */
    public static class a implements C1860a.b<C1514e<?>> {
        @Override // p149s0.C1860a.b
        /* renamed from: a */
        public C1514e<?> mo1703a() {
            return new C1514e<>();
        }
    }

    public C1514e() {
        String.valueOf(hashCode());
        this.f4366e = new AbstractC1863d.b();
    }

    @Override // p108n0.InterfaceC1513d
    /* renamed from: a */
    public void mo1692a(C2053n c2053n) {
        m1701l(c2053n, 5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p108n0.InterfaceC1513d
    /* renamed from: b */
    public void mo1693b(InterfaceC2057r<?> interfaceC2057r, EnumC1957a enumC1957a) {
        this.f4366e.mo2133a();
        this.f4380s = null;
        if (interfaceC2057r == 0) {
            StringBuilder sbM112a = C0413b.m112a("Expected to receive a Resource<R> with an object of ");
            sbM112a.append(this.f4370i);
            sbM112a.append(" inside, but instead got null.");
            m1701l(new C2053n(sbM112a.toString()), 5);
            return;
        }
        Object obj = interfaceC2057r.get();
        if (obj == null || !this.f4370i.isAssignableFrom(obj.getClass())) {
            m1702m(interfaceC2057r);
            StringBuilder sbM112a2 = C0413b.m112a("Expected to receive an object of ");
            sbM112a2.append(this.f4370i);
            sbM112a2.append(" but instead got ");
            sbM112a2.append(obj != null ? obj.getClass() : "");
            sbM112a2.append("{");
            sbM112a2.append(obj);
            sbM112a2.append("} inside Resource{");
            sbM112a2.append(interfaceC2057r);
            sbM112a2.append("}.");
            sbM112a2.append(obj == null ? " To indicate failure return a null Resource object, rather than a Resource object containing null data." : "");
            m1701l(new C2053n(sbM112a2.toString()), 5);
            return;
        }
        C1515f c1515f = this.f4367f;
        if (!(c1515f == null || c1515f.m1705b(this))) {
            m1702m(interfaceC2057r);
            this.f4382u = 4;
            return;
        }
        C1515f c1515f2 = this.f4367f;
        boolean z6 = c1515f2 == null || !c1515f2.m1706e();
        this.f4382u = 4;
        this.f4379r = interfaceC2057r;
        if (this.f4368g.f5240f <= 3) {
            Objects.toString(enumC1957a);
            Objects.toString(this.f4369h);
            C1819d.m2050a(this.f4381t);
        }
        InterfaceC1511b<R> interfaceC1511b = this.f4376o;
        if (interfaceC1511b == 0 || !interfaceC1511b.m1674a(obj, this.f4369h, this.f4375n, enumC1957a, z6)) {
            Objects.requireNonNull(this.f4378q);
            this.f4375n.mo1308e(obj, C1732a.f4918a);
        }
        C1515f c1515f3 = this.f4367f;
        if (c1515f3 != null) {
            c1515f3.m1707f(this);
        }
    }

    @Override // p108n0.InterfaceC1510a
    /* renamed from: c */
    public void mo1672c() {
        this.f4366e.mo2133a();
        int i7 = C1819d.f5292b;
        this.f4381t = SystemClock.elapsedRealtimeNanos();
        if (this.f4369h == null) {
            if (C1823h.m2065i(this.f4372k, this.f4373l)) {
                this.f4386y = this.f4372k;
                this.f4387z = this.f4373l;
            }
            m1701l(new C2053n("Received null model"), m1697h() == null ? 5 : 3);
            return;
        }
        int i8 = this.f4382u;
        if (i8 == 2) {
            throw new IllegalArgumentException("Cannot restart a running request");
        }
        if (i8 == 4) {
            mo1693b(this.f4379r, EnumC1957a.MEMORY_CACHE);
            return;
        }
        this.f4382u = 3;
        if (C1823h.m2065i(this.f4372k, this.f4373l)) {
            mo1695f(this.f4372k, this.f4373l);
        } else {
            this.f4375n.mo1822c(this);
        }
        int i9 = this.f4382u;
        if ((i9 == 2 || i9 == 3) && m1696g()) {
            this.f4375n.mo1818g(m1698i());
        }
        if (Log.isLoggable("Request", 2)) {
            C1819d.m2050a(this.f4381t);
        }
    }

    @Override // p108n0.InterfaceC1510a
    public void clear() {
        C1823h.m2057a();
        if (this.f4382u == 7) {
            return;
        }
        this.f4366e.mo2133a();
        this.f4375n.mo1823d(this);
        this.f4382u = 6;
        C2048i.d dVar = this.f4380s;
        if (dVar != null) {
            C2049j<?> c2049j = dVar.f6066a;
            InterfaceC1513d interfaceC1513d = dVar.f6067b;
            Objects.requireNonNull(c2049j);
            C1823h.m2057a();
            c2049j.f6074f.mo2133a();
            if (c2049j.f6086r || c2049j.f6088t) {
                if (c2049j.f6089u == null) {
                    c2049j.f6089u = new ArrayList(2);
                }
                if (!c2049j.f6089u.contains(interfaceC1513d)) {
                    c2049j.f6089u.add(interfaceC1513d);
                }
            } else {
                c2049j.f6073e.remove(interfaceC1513d);
                if (c2049j.f6073e.isEmpty() && !c2049j.f6088t && !c2049j.f6086r && !c2049j.f6092x) {
                    c2049j.f6092x = true;
                    RunnableC2045f<?> runnableC2045f = c2049j.f6091w;
                    runnableC2045f.f5996H = true;
                    InterfaceC2043d interfaceC2043d = runnableC2045f.f5994F;
                    if (interfaceC2043d != null) {
                        interfaceC2043d.cancel();
                    }
                    ((C2048i) c2049j.f6077i).m2432b(c2049j, c2049j.f6081m);
                }
            }
            this.f4380s = null;
        }
        InterfaceC2057r<R> interfaceC2057r = this.f4379r;
        if (interfaceC2057r != null) {
            m1702m(interfaceC2057r);
        }
        if (m1696g()) {
            this.f4375n.mo1820i(m1698i());
        }
        this.f4382u = 7;
    }

    @Override // p108n0.InterfaceC1510a
    /* renamed from: d */
    public boolean mo1673d() {
        return this.f4382u == 4;
    }

    @Override // p149s0.C1860a.d
    /* renamed from: e */
    public AbstractC1863d mo1694e() {
        return this.f4366e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p116o0.InterfaceC1576e
    /* renamed from: f */
    public void mo1695f(int i7, int i8) {
        C2051l c2051l;
        Object objRemove;
        C2052m c2052m;
        C1514e c1514e;
        C2052m<?> c2052m2;
        C2048i.d dVar;
        WeakReference<C2052m<?>> weakReference;
        int iRound = i7;
        this.f4366e.mo2133a();
        if (Log.isLoggable("Request", 2)) {
            C1819d.m2050a(this.f4381t);
        }
        if (this.f4382u != 3) {
            return;
        }
        this.f4382u = 2;
        float f7 = this.f4371j.f4343f;
        if (iRound != Integer.MIN_VALUE) {
            iRound = Math.round(iRound * f7);
        }
        this.f4386y = iRound;
        this.f4387z = i8 == Integer.MIN_VALUE ? i8 : Math.round(f7 * i8);
        if (Log.isLoggable("Request", 2)) {
            C1819d.m2050a(this.f4381t);
        }
        C2048i c2048i = this.f4377p;
        C1810e c1810e = this.f4368g;
        Object obj = this.f4369h;
        C1512c c1512c = this.f4371j;
        InterfaceC1964h interfaceC1964h = c1512c.f4353p;
        int i9 = this.f4386y;
        int i10 = this.f4387z;
        Class<?> cls = c1512c.f4360w;
        Class<R> cls2 = this.f4370i;
        EnumC1811f enumC1811f = this.f4374m;
        AbstractC2047h abstractC2047h = c1512c.f4344g;
        Map<Class<?>, InterfaceC1969m<?>> map = c1512c.f4359v;
        boolean z6 = c1512c.f4354q;
        boolean z7 = c1512c.f4341C;
        C1966j c1966j = c1512c.f4358u;
        boolean z8 = c1512c.f4350m;
        boolean z9 = c1512c.f4339A;
        boolean z10 = c1512c.f4340B;
        Objects.requireNonNull(c2048i);
        EnumC1957a enumC1957a = EnumC1957a.MEMORY_CACHE;
        C1823h.m2057a();
        int i11 = C1819d.f5292b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        Objects.requireNonNull(c2048i.f6047b);
        C2051l c2051l2 = new C2051l(obj, interfaceC1964h, i9, i10, map, cls, cls2, c1966j);
        C2048i.d dVar2 = null;
        boolean z11 = true;
        if (z8) {
            C2141h c2141h = (C2141h) c2048i.f6048c;
            synchronized (c2141h) {
                c2051l = c2051l2;
                objRemove = c2141h.f5293a.remove(c2051l);
                if (objRemove != null) {
                    c2141h.f5295c -= c2141h.mo2052b(objRemove);
                }
            }
            InterfaceC2057r interfaceC2057r = (InterfaceC2057r) objRemove;
            c2052m = interfaceC2057r == null ? null : interfaceC2057r instanceof C2052m ? (C2052m) interfaceC2057r : new C2052m(interfaceC2057r, true);
            if (c2052m != null) {
                c2052m.m2437a();
                c2048i.f6050e.put(c2051l, new C2048i.f(c2051l, c2052m, c2048i.m2431a()));
            }
        } else {
            c2052m = null;
            c2051l = c2051l2;
        }
        if (c2052m != null) {
            c1514e = this;
            c1514e.mo1693b(c2052m, enumC1957a);
            if (Log.isLoggable("Engine", 2)) {
                C1819d.m2050a(jElapsedRealtimeNanos);
                c2051l.toString();
            }
        } else {
            c1514e = this;
            if (z8 && (weakReference = c2048i.f6050e.get(c2051l)) != null) {
                c2052m2 = weakReference.get();
                if (c2052m2 != null) {
                    c2052m2.m2437a();
                } else {
                    c2048i.f6050e.remove(c2051l);
                }
            } else {
                c2052m2 = null;
            }
            if (c2052m2 != null) {
                c1514e.mo1693b(c2052m2, enumC1957a);
                if (Log.isLoggable("Engine", 2)) {
                    C1819d.m2050a(jElapsedRealtimeNanos);
                    c2051l.toString();
                }
            } else {
                C2049j<?> c2049j = c2048i.f6046a.get(c2051l);
                if (c2049j != null) {
                    c2049j.m2435a(c1514e);
                    if (Log.isLoggable("Engine", 2)) {
                        C1819d.m2050a(jElapsedRealtimeNanos);
                        c2051l.toString();
                    }
                    dVar = new C2048i.d(c1514e, c2049j);
                } else {
                    C2049j<?> c2049jAcquire = c2048i.f6049d.f6062e.acquire();
                    c2049jAcquire.f6081m = c2051l;
                    c2049jAcquire.f6082n = z8;
                    c2049jAcquire.f6083o = z9;
                    C2048i.a aVar = c2048i.f6052g;
                    RunnableC2045f<R> runnableC2045f = (RunnableC2045f) aVar.f6055b.acquire();
                    int i12 = aVar.f6056c;
                    aVar.f6056c = i12 + 1;
                    C2044e<R> c2044e = runnableC2045f.f5997e;
                    RunnableC2045f.d dVar3 = runnableC2045f.f6000h;
                    c2044e.f5973c = c1810e;
                    c2044e.f5974d = obj;
                    c2044e.f5984n = interfaceC1964h;
                    c2044e.f5975e = i9;
                    c2044e.f5976f = i10;
                    c2044e.f5986p = abstractC2047h;
                    c2044e.f5977g = cls;
                    c2044e.f5978h = dVar3;
                    c2044e.f5981k = cls2;
                    c2044e.f5985o = enumC1811f;
                    c2044e.f5979i = c1966j;
                    c2044e.f5980j = map;
                    c2044e.f5987q = z6;
                    c2044e.f5988r = z7;
                    runnableC2045f.f6004l = c1810e;
                    runnableC2045f.f6005m = interfaceC1964h;
                    runnableC2045f.f6006n = enumC1811f;
                    runnableC2045f.f6007o = c2051l;
                    runnableC2045f.f6008p = i9;
                    runnableC2045f.f6009q = i10;
                    runnableC2045f.f6010r = abstractC2047h;
                    runnableC2045f.f6017y = z10;
                    runnableC2045f.f6011s = c1966j;
                    runnableC2045f.f6012t = c2049jAcquire;
                    runnableC2045f.f6013u = i12;
                    runnableC2045f.f6015w = RunnableC2045f.f.INITIALIZE;
                    c2048i.f6046a.put(c2051l, c2049jAcquire);
                    c2049jAcquire.m2435a(c1514e);
                    c2049jAcquire.f6091w = runnableC2045f;
                    RunnableC2045f.g gVarM2415m = runnableC2045f.m2415m(RunnableC2045f.g.INITIALIZE);
                    if (gVarM2415m != RunnableC2045f.g.RESOURCE_CACHE && gVarM2415m != RunnableC2045f.g.DATA_CACHE) {
                        z11 = false;
                    }
                    (z11 ? c2049jAcquire.f6078j : c2049jAcquire.f6083o ? c2049jAcquire.f6080l : c2049jAcquire.f6079k).execute(runnableC2045f);
                    if (Log.isLoggable("Engine", 2)) {
                        C1819d.m2050a(jElapsedRealtimeNanos);
                        c2051l.toString();
                    }
                    dVar = new C2048i.d(c1514e, c2049jAcquire);
                }
                dVar2 = dVar;
            }
        }
        c1514e.f4380s = dVar2;
        if (Log.isLoggable("Request", 2)) {
            C1819d.m2050a(c1514e.f4381t);
        }
    }

    /* renamed from: g */
    public final boolean m1696g() {
        C1515f c1515f = this.f4367f;
        return c1515f == null || c1515f.m1704a(this);
    }

    /* renamed from: h */
    public final Drawable m1697h() {
        int i7;
        if (this.f4385x == null) {
            C1512c c1512c = this.f4371j;
            Drawable drawable = c1512c.f4356s;
            this.f4385x = drawable;
            if (drawable == null && (i7 = c1512c.f4357t) > 0) {
                this.f4385x = m1700k(i7);
            }
        }
        return this.f4385x;
    }

    /* renamed from: i */
    public final Drawable m1698i() {
        int i7;
        if (this.f4384w == null) {
            C1512c c1512c = this.f4371j;
            Drawable drawable = c1512c.f4348k;
            this.f4384w = drawable;
            if (drawable == null && (i7 = c1512c.f4349l) > 0) {
                this.f4384w = m1700k(i7);
            }
        }
        return this.f4384w;
    }

    @Override // p108n0.InterfaceC1510a
    public boolean isCancelled() {
        int i7 = this.f4382u;
        return i7 == 6 || i7 == 7;
    }

    @Override // p108n0.InterfaceC1510a
    public boolean isRunning() {
        int i7 = this.f4382u;
        return i7 == 2 || i7 == 3;
    }

    /* renamed from: j */
    public boolean m1699j(InterfaceC1510a interfaceC1510a) {
        if (!(interfaceC1510a instanceof C1514e)) {
            return false;
        }
        C1514e c1514e = (C1514e) interfaceC1510a;
        return this.f4372k == c1514e.f4372k && this.f4373l == c1514e.f4373l && C1823h.m2058b(this.f4369h, c1514e.f4369h) && this.f4370i.equals(c1514e.f4370i) && this.f4371j.equals(c1514e.f4371j) && this.f4374m == c1514e.f4374m;
    }

    /* renamed from: k */
    public final Drawable m1700k(@DrawableRes int i7) {
        if (!f4365B) {
            return ResourcesCompat.getDrawable(this.f4368g.getResources(), i7, this.f4371j.f4362y);
        }
        try {
            return AppCompatResources.getDrawable(this.f4368g, i7);
        } catch (NoClassDefFoundError unused) {
            f4365B = false;
            return ResourcesCompat.getDrawable(this.f4368g.getResources(), i7, this.f4371j.f4362y);
        }
    }

    /* renamed from: l */
    public final void m1701l(C2053n c2053n, int i7) {
        int i8;
        this.f4366e.mo2133a();
        int i9 = this.f4368g.f5240f;
        if (i9 <= i7) {
            Objects.toString(this.f4369h);
            if (i9 <= 4) {
                Objects.requireNonNull(c2053n);
                ArrayList arrayList = new ArrayList();
                c2053n.m2442a(c2053n, arrayList);
                int size = arrayList.size();
                int i10 = 0;
                while (i10 < size) {
                    int i11 = i10 + 1;
                    i10 = i11;
                }
            }
        }
        this.f4380s = null;
        this.f4382u = 5;
        InterfaceC1511b<R> interfaceC1511b = this.f4376o;
        if (interfaceC1511b != null) {
            Object obj = this.f4369h;
            InterfaceC1577f<R> interfaceC1577f = this.f4375n;
            C1515f c1515f = this.f4367f;
            if (interfaceC1511b.m1675b(c2053n, obj, interfaceC1577f, c1515f == null || !c1515f.m1706e())) {
                return;
            }
        }
        if (m1696g()) {
            Drawable drawableM1697h = this.f4369h == null ? m1697h() : null;
            if (drawableM1697h == null) {
                if (this.f4383v == null) {
                    C1512c c1512c = this.f4371j;
                    Drawable drawable = c1512c.f4346i;
                    this.f4383v = drawable;
                    if (drawable == null && (i8 = c1512c.f4347j) > 0) {
                        this.f4383v = m1700k(i8);
                    }
                }
                drawableM1697h = this.f4383v;
            }
            if (drawableM1697h == null) {
                drawableM1697h = m1698i();
            }
            this.f4375n.mo1816b(drawableM1697h);
        }
    }

    /* renamed from: m */
    public final void m1702m(InterfaceC2057r<?> interfaceC2057r) {
        Objects.requireNonNull(this.f4377p);
        C1823h.m2057a();
        if (!(interfaceC2057r instanceof C2052m)) {
            throw new IllegalArgumentException("Cannot release anything but an EngineResource");
        }
        ((C2052m) interfaceC2057r).m2438c();
        this.f4379r = null;
    }

    @Override // p108n0.InterfaceC1510a
    public void pause() {
        clear();
        this.f4382u = 8;
    }

    @Override // p108n0.InterfaceC1510a
    public void recycle() {
        this.f4368g = null;
        this.f4369h = null;
        this.f4370i = null;
        this.f4371j = null;
        this.f4372k = -1;
        this.f4373l = -1;
        this.f4375n = null;
        this.f4376o = null;
        this.f4367f = null;
        this.f4378q = null;
        this.f4380s = null;
        this.f4383v = null;
        this.f4384w = null;
        this.f4385x = null;
        this.f4386y = -1;
        this.f4387z = -1;
        ((C1860a.c) f4364A).release(this);
    }
}
