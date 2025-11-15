package p183x;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.Pools;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import p001a0.C0004b;
import p009b.C0413b;
import p108n0.InterfaceC1513d;
import p142r0.C1823h;
import p149s0.AbstractC1863d;
import p149s0.C1860a;
import p162u.EnumC1957a;
import p162u.InterfaceC1964h;
import p183x.RunnableC2045f;

/* compiled from: EngineJob.java */
/* renamed from: x.j */
/* loaded from: classes.dex */
public class C2049j<R> implements RunnableC2045f.a<R>, C1860a.d {

    /* renamed from: y */
    public static final a f6071y = new a();

    /* renamed from: z */
    public static final Handler f6072z = new Handler(Looper.getMainLooper(), new b());

    /* renamed from: e */
    public final List<InterfaceC1513d> f6073e;

    /* renamed from: f */
    public final AbstractC1863d f6074f;

    /* renamed from: g */
    public final Pools.Pool<C2049j<?>> f6075g;

    /* renamed from: h */
    public final a f6076h;

    /* renamed from: i */
    public final InterfaceC2050k f6077i;

    /* renamed from: j */
    public final C0004b f6078j;

    /* renamed from: k */
    public final C0004b f6079k;

    /* renamed from: l */
    public final C0004b f6080l;

    /* renamed from: m */
    public InterfaceC1964h f6081m;

    /* renamed from: n */
    public boolean f6082n;

    /* renamed from: o */
    public boolean f6083o;

    /* renamed from: p */
    public InterfaceC2057r<?> f6084p;

    /* renamed from: q */
    public EnumC1957a f6085q;

    /* renamed from: r */
    public boolean f6086r;

    /* renamed from: s */
    public C2053n f6087s;

    /* renamed from: t */
    public boolean f6088t;

    /* renamed from: u */
    public List<InterfaceC1513d> f6089u;

    /* renamed from: v */
    public C2052m<?> f6090v;

    /* renamed from: w */
    public RunnableC2045f<R> f6091w;

    /* renamed from: x */
    public volatile boolean f6092x;

    /* compiled from: EngineJob.java */
    /* renamed from: x.j$a */
    public static class a {
    }

    /* compiled from: EngineJob.java */
    /* renamed from: x.j$b */
    public static class b implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            C2049j c2049j = (C2049j) message.obj;
            int i7 = message.what;
            if (i7 == 1) {
                c2049j.f6074f.mo2133a();
                if (c2049j.f6092x) {
                    c2049j.f6084p.recycle();
                    c2049j.m2436b(false);
                } else {
                    if (c2049j.f6073e.isEmpty()) {
                        throw new IllegalStateException("Received a resource without any callbacks to notify");
                    }
                    if (c2049j.f6086r) {
                        throw new IllegalStateException("Already have resource");
                    }
                    a aVar = c2049j.f6076h;
                    InterfaceC2057r<?> interfaceC2057r = c2049j.f6084p;
                    boolean z6 = c2049j.f6082n;
                    Objects.requireNonNull(aVar);
                    C2052m<?> c2052m = new C2052m<>(interfaceC2057r, z6);
                    c2049j.f6090v = c2052m;
                    c2049j.f6086r = true;
                    c2052m.m2437a();
                    ((C2048i) c2049j.f6077i).m2433c(c2049j.f6081m, c2049j.f6090v);
                    for (InterfaceC1513d interfaceC1513d : c2049j.f6073e) {
                        List<InterfaceC1513d> list = c2049j.f6089u;
                        if (!(list != null && list.contains(interfaceC1513d))) {
                            c2049j.f6090v.m2437a();
                            interfaceC1513d.mo1693b(c2049j.f6090v, c2049j.f6085q);
                        }
                    }
                    c2049j.f6090v.m2438c();
                    c2049j.m2436b(false);
                }
            } else if (i7 == 2) {
                c2049j.f6074f.mo2133a();
                if (c2049j.f6092x) {
                    c2049j.m2436b(false);
                } else {
                    if (c2049j.f6073e.isEmpty()) {
                        throw new IllegalStateException("Received an exception without any callbacks to notify");
                    }
                    if (c2049j.f6088t) {
                        throw new IllegalStateException("Already failed once");
                    }
                    c2049j.f6088t = true;
                    ((C2048i) c2049j.f6077i).m2433c(c2049j.f6081m, null);
                    for (InterfaceC1513d interfaceC1513d2 : c2049j.f6073e) {
                        List<InterfaceC1513d> list2 = c2049j.f6089u;
                        if (!(list2 != null && list2.contains(interfaceC1513d2))) {
                            interfaceC1513d2.mo1692a(c2049j.f6087s);
                        }
                    }
                    c2049j.m2436b(false);
                }
            } else {
                if (i7 != 3) {
                    StringBuilder sbM112a = C0413b.m112a("Unrecognized message: ");
                    sbM112a.append(message.what);
                    throw new IllegalStateException(sbM112a.toString());
                }
                c2049j.f6074f.mo2133a();
                if (!c2049j.f6092x) {
                    throw new IllegalStateException("Not cancelled");
                }
                ((C2048i) c2049j.f6077i).m2432b(c2049j, c2049j.f6081m);
                c2049j.m2436b(false);
            }
            return true;
        }
    }

    public C2049j(C0004b c0004b, C0004b c0004b2, C0004b c0004b3, InterfaceC2050k interfaceC2050k, Pools.Pool<C2049j<?>> pool) {
        a aVar = f6071y;
        this.f6073e = new ArrayList(2);
        this.f6074f = new AbstractC1863d.b();
        this.f6078j = c0004b;
        this.f6079k = c0004b2;
        this.f6080l = c0004b3;
        this.f6077i = interfaceC2050k;
        this.f6075g = pool;
        this.f6076h = aVar;
    }

    /* renamed from: a */
    public void m2435a(InterfaceC1513d interfaceC1513d) {
        C1823h.m2057a();
        this.f6074f.mo2133a();
        if (this.f6086r) {
            interfaceC1513d.mo1693b(this.f6090v, this.f6085q);
        } else if (this.f6088t) {
            interfaceC1513d.mo1692a(this.f6087s);
        } else {
            this.f6073e.add(interfaceC1513d);
        }
    }

    /* renamed from: b */
    public final void m2436b(boolean z6) {
        boolean zM2424a;
        C1823h.m2057a();
        this.f6073e.clear();
        this.f6081m = null;
        this.f6090v = null;
        this.f6084p = null;
        List<InterfaceC1513d> list = this.f6089u;
        if (list != null) {
            list.clear();
        }
        this.f6088t = false;
        this.f6092x = false;
        this.f6086r = false;
        RunnableC2045f<R> runnableC2045f = this.f6091w;
        RunnableC2045f.e eVar = runnableC2045f.f6003k;
        synchronized (eVar) {
            eVar.f6024a = true;
            zM2424a = eVar.m2424a(z6);
        }
        if (zM2424a) {
            runnableC2045f.m2419q();
        }
        this.f6091w = null;
        this.f6087s = null;
        this.f6085q = null;
        this.f6075g.release(this);
    }

    @Override // p149s0.C1860a.d
    /* renamed from: e */
    public AbstractC1863d mo1694e() {
        return this.f6074f;
    }
}
