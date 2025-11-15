package p183x;

import android.arch.lifecycle.C0063n;
import android.os.Build;
import android.os.SystemClock;
import android.support.constraint.motion.C0080b;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.Pools;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p009b.C0413b;
import p036e0.C0912k;
import p141r.C1810e;
import p141r.EnumC1811f;
import p142r0.C1819d;
import p149s0.AbstractC1863d;
import p149s0.C1860a;
import p162u.C1965i;
import p162u.C1966j;
import p162u.EnumC1957a;
import p162u.InterfaceC1964h;
import p162u.InterfaceC1968l;
import p169v.C1988d;
import p169v.InterfaceC1986b;
import p169v.InterfaceC1987c;
import p183x.C2046g;
import p183x.C2048i;
import p183x.InterfaceC2043d;

/* compiled from: DecodeJob.java */
/* renamed from: x.f */
/* loaded from: classes.dex */
public class RunnableC2045f<R> implements InterfaceC2043d.a, Runnable, Comparable<RunnableC2045f<?>>, C1860a.d {

    /* renamed from: A */
    public InterfaceC1964h f5989A;

    /* renamed from: B */
    public InterfaceC1964h f5990B;

    /* renamed from: C */
    public Object f5991C;

    /* renamed from: D */
    public EnumC1957a f5992D;

    /* renamed from: E */
    public InterfaceC1986b<?> f5993E;

    /* renamed from: F */
    public volatile InterfaceC2043d f5994F;

    /* renamed from: G */
    public volatile boolean f5995G;

    /* renamed from: H */
    public volatile boolean f5996H;

    /* renamed from: h */
    public final d f6000h;

    /* renamed from: i */
    public final Pools.Pool<RunnableC2045f<?>> f6001i;

    /* renamed from: l */
    public C1810e f6004l;

    /* renamed from: m */
    public InterfaceC1964h f6005m;

    /* renamed from: n */
    public EnumC1811f f6006n;

    /* renamed from: o */
    public C2051l f6007o;

    /* renamed from: p */
    public int f6008p;

    /* renamed from: q */
    public int f6009q;

    /* renamed from: r */
    public AbstractC2047h f6010r;

    /* renamed from: s */
    public C1966j f6011s;

    /* renamed from: t */
    public a<R> f6012t;

    /* renamed from: u */
    public int f6013u;

    /* renamed from: v */
    public g f6014v;

    /* renamed from: w */
    public f f6015w;

    /* renamed from: x */
    public long f6016x;

    /* renamed from: y */
    public boolean f6017y;

    /* renamed from: z */
    public Thread f6018z;

    /* renamed from: e */
    public final C2044e<R> f5997e = new C2044e<>();

    /* renamed from: f */
    public final List<Exception> f5998f = new ArrayList();

    /* renamed from: g */
    public final AbstractC1863d f5999g = new AbstractC1863d.b();

    /* renamed from: j */
    public final c<?> f6002j = new c<>();

    /* renamed from: k */
    public final e f6003k = new e();

    /* compiled from: DecodeJob.java */
    /* renamed from: x.f$a */
    public interface a<R> {
    }

    /* compiled from: DecodeJob.java */
    /* renamed from: x.f$b */
    public final class b<Z> implements C2046g.a<Z> {

        /* renamed from: a */
        public final EnumC1957a f6019a;

        public b(EnumC1957a enumC1957a) {
            this.f6019a = enumC1957a;
        }
    }

    /* compiled from: DecodeJob.java */
    /* renamed from: x.f$c */
    public static class c<Z> {

        /* renamed from: a */
        public InterfaceC1964h f6021a;

        /* renamed from: b */
        public InterfaceC1968l<Z> f6022b;

        /* renamed from: c */
        public C2056q<Z> f6023c;

        /* renamed from: a */
        public void m2423a(d dVar, C1966j c1966j) {
            TraceCompat.beginSection("DecodeJob.encode");
            try {
                ((C2048i.c) dVar).m2434a().mo2567b(this.f6021a, new C2042c(this.f6022b, this.f6023c, c1966j));
            } finally {
                this.f6023c.m2446c();
                TraceCompat.endSection();
            }
        }
    }

    /* compiled from: DecodeJob.java */
    /* renamed from: x.f$d */
    public interface d {
    }

    /* compiled from: DecodeJob.java */
    /* renamed from: x.f$e */
    public static class e {

        /* renamed from: a */
        public boolean f6024a;

        /* renamed from: b */
        public boolean f6025b;

        /* renamed from: c */
        public boolean f6026c;

        /* renamed from: a */
        public final boolean m2424a(boolean z6) {
            return (this.f6026c || z6 || this.f6025b) && this.f6024a;
        }
    }

    /* compiled from: DecodeJob.java */
    /* renamed from: x.f$f */
    public enum f {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    /* compiled from: DecodeJob.java */
    /* renamed from: x.f$g */
    public enum g {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    public RunnableC2045f(d dVar, Pools.Pool<RunnableC2045f<?>> pool) {
        this.f6000h = dVar;
        this.f6001i = pool;
    }

    @Override // p183x.InterfaceC2043d.a
    /* renamed from: a */
    public void mo2401a(InterfaceC1964h interfaceC1964h, Exception exc, InterfaceC1986b<?> interfaceC1986b, EnumC1957a enumC1957a) {
        interfaceC1986b.mo124b();
        C2053n c2053n = new C2053n("Fetching data failed", exc);
        Class<?> clsMo123a = interfaceC1986b.mo123a();
        c2053n.f6110f = interfaceC1964h;
        c2053n.f6111g = enumC1957a;
        c2053n.f6112h = clsMo123a;
        this.f5998f.add(c2053n);
        if (Thread.currentThread() == this.f6018z) {
            m2420r();
            return;
        }
        this.f6015w = f.SWITCH_TO_SOURCE_SERVICE;
        C2049j c2049j = (C2049j) this.f6012t;
        (c2049j.f6083o ? c2049j.f6080l : c2049j.f6079k).execute(this);
    }

    @Override // p183x.InterfaceC2043d.a
    /* renamed from: b */
    public void mo2402b() {
        this.f6015w = f.SWITCH_TO_SOURCE_SERVICE;
        C2049j c2049j = (C2049j) this.f6012t;
        (c2049j.f6083o ? c2049j.f6080l : c2049j.f6079k).execute(this);
    }

    @Override // p183x.InterfaceC2043d.a
    /* renamed from: c */
    public void mo2403c(InterfaceC1964h interfaceC1964h, Object obj, InterfaceC1986b<?> interfaceC1986b, EnumC1957a enumC1957a, InterfaceC1964h interfaceC1964h2) {
        this.f5989A = interfaceC1964h;
        this.f5991C = obj;
        this.f5993E = interfaceC1986b;
        this.f5992D = enumC1957a;
        this.f5990B = interfaceC1964h2;
        if (Thread.currentThread() != this.f6018z) {
            this.f6015w = f.DECODE_DATA;
            C2049j c2049j = (C2049j) this.f6012t;
            (c2049j.f6083o ? c2049j.f6080l : c2049j.f6079k).execute(this);
        } else {
            TraceCompat.beginSection("DecodeJob.decodeFromRetrievedData");
            try {
                m2413i();
            } finally {
                TraceCompat.endSection();
            }
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(RunnableC2045f<?> runnableC2045f) {
        RunnableC2045f<?> runnableC2045f2 = runnableC2045f;
        int iOrdinal = this.f6006n.ordinal() - runnableC2045f2.f6006n.ordinal();
        return iOrdinal == 0 ? this.f6013u - runnableC2045f2.f6013u : iOrdinal;
    }

    @Override // p149s0.C1860a.d
    /* renamed from: e */
    public AbstractC1863d mo1694e() {
        return this.f5999g;
    }

    /* renamed from: f */
    public final <Data> InterfaceC2057r<R> m2411f(InterfaceC1986b<?> interfaceC1986b, Data data, EnumC1957a enumC1957a) {
        if (data == null) {
            return null;
        }
        try {
            int i7 = C1819d.f5292b;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            InterfaceC2057r<R> interfaceC2057rM2412h = m2412h(data, enumC1957a);
            if (Log.isLoggable("DecodeJob", 2)) {
                m2416n("Decoded result " + interfaceC2057rM2412h, jElapsedRealtimeNanos, null);
            }
            return interfaceC2057rM2412h;
        } finally {
            interfaceC1986b.mo124b();
        }
    }

    /* renamed from: h */
    public final <Data> InterfaceC2057r<R> m2412h(Data data, EnumC1957a enumC1957a) {
        InterfaceC1987c<Data> interfaceC1987cMo1006b;
        C2055p<Data, ?, R> c2055pM2407d = this.f5997e.m2407d(data.getClass());
        C1966j c1966j = this.f6011s;
        if (Build.VERSION.SDK_INT >= 26) {
            C1965i<Boolean> c1965i = C0912k.f1647i;
            if (c1966j.m2296c(c1965i) == null && (enumC1957a == EnumC1957a.RESOURCE_DISK_CACHE || this.f5997e.f5988r)) {
                c1966j = new C1966j();
                c1966j.m2297d(this.f6011s);
                c1966j.f5742b.put(c1965i, Boolean.TRUE);
            }
        }
        C1966j c1966j2 = c1966j;
        C1988d c1988d = this.f6004l.f5235a.f5250e;
        synchronized (c1988d) {
            InterfaceC1987c.a<?> aVar = c1988d.f5806a.get(data.getClass());
            if (aVar == null) {
                Iterator<InterfaceC1987c.a<?>> it = c1988d.f5806a.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InterfaceC1987c.a<?> next = it.next();
                    if (next.mo1005a().isAssignableFrom(data.getClass())) {
                        aVar = next;
                        break;
                    }
                }
            }
            if (aVar == null) {
                aVar = C1988d.f5805b;
            }
            interfaceC1987cMo1006b = aVar.mo1006b(data);
        }
        try {
            return c2055pM2407d.m2444a(interfaceC1987cMo1006b, c1966j2, this.f6008p, this.f6009q, new b(enumC1957a));
        } finally {
            interfaceC1987cMo1006b.mo1004b();
        }
    }

    /* renamed from: i */
    public final void m2413i() {
        C2056q c2056qM2411f;
        if (Log.isLoggable("DecodeJob", 2)) {
            long j7 = this.f6016x;
            StringBuilder sbM112a = C0413b.m112a("data: ");
            sbM112a.append(this.f5991C);
            sbM112a.append(", cache key: ");
            sbM112a.append(this.f5989A);
            sbM112a.append(", fetcher: ");
            sbM112a.append(this.f5993E);
            m2416n("Retrieved data", j7, sbM112a.toString());
        }
        C2056q c2056qM2445a = null;
        try {
            c2056qM2411f = m2411f(this.f5993E, this.f5991C, this.f5992D);
        } catch (C2053n e7) {
            InterfaceC1964h interfaceC1964h = this.f5990B;
            EnumC1957a enumC1957a = this.f5992D;
            e7.f6110f = interfaceC1964h;
            e7.f6111g = enumC1957a;
            e7.f6112h = null;
            this.f5998f.add(e7);
            c2056qM2411f = null;
        }
        if (c2056qM2411f == null) {
            m2420r();
            return;
        }
        EnumC1957a enumC1957a2 = this.f5992D;
        if (c2056qM2411f instanceof InterfaceC2054o) {
            ((InterfaceC2054o) c2056qM2411f).mo823a();
        }
        if (this.f6002j.f6023c != null) {
            c2056qM2445a = C2056q.m2445a(c2056qM2411f);
            c2056qM2411f = c2056qM2445a;
        }
        m2422t();
        C2049j c2049j = (C2049j) this.f6012t;
        c2049j.f6084p = c2056qM2411f;
        c2049j.f6085q = enumC1957a2;
        C2049j.f6072z.obtainMessage(1, c2049j).sendToTarget();
        this.f6014v = g.ENCODE;
        try {
            c<?> cVar = this.f6002j;
            if (cVar.f6023c != null) {
                cVar.m2423a(this.f6000h, this.f6011s);
            }
        } finally {
            if (c2056qM2445a != null) {
                c2056qM2445a.m2446c();
            }
            m2418p();
        }
    }

    /* renamed from: l */
    public final InterfaceC2043d m2414l() {
        int iOrdinal = this.f6014v.ordinal();
        if (iOrdinal == 1) {
            return new C2058s(this.f5997e, this);
        }
        if (iOrdinal == 2) {
            return new C2040a(this.f5997e, this);
        }
        if (iOrdinal == 3) {
            return new C2061v(this.f5997e, this);
        }
        if (iOrdinal == 5) {
            return null;
        }
        StringBuilder sbM112a = C0413b.m112a("Unrecognized stage: ");
        sbM112a.append(this.f6014v);
        throw new IllegalStateException(sbM112a.toString());
    }

    /* renamed from: m */
    public final g m2415m(g gVar) {
        g gVar2 = g.RESOURCE_CACHE;
        g gVar3 = g.DATA_CACHE;
        g gVar4 = g.FINISHED;
        int iOrdinal = gVar.ordinal();
        if (iOrdinal == 0) {
            return this.f6010r.mo2428b() ? gVar2 : m2415m(gVar2);
        }
        if (iOrdinal == 1) {
            return this.f6010r.mo2427a() ? gVar3 : m2415m(gVar3);
        }
        if (iOrdinal == 2) {
            return this.f6017y ? gVar4 : g.SOURCE;
        }
        if (iOrdinal == 3 || iOrdinal == 5) {
            return gVar4;
        }
        throw new IllegalArgumentException("Unrecognized stage: " + gVar);
    }

    /* renamed from: n */
    public final void m2416n(String str, long j7, String str2) {
        StringBuilder sbM94a = C0080b.m94a(str, " in ");
        sbM94a.append(C1819d.m2050a(j7));
        sbM94a.append(", load key: ");
        sbM94a.append(this.f6007o);
        sbM94a.append(str2 != null ? C0063n.m88a(", ", str2) : "");
        sbM94a.append(", thread: ");
        sbM94a.append(Thread.currentThread().getName());
    }

    /* renamed from: o */
    public final void m2417o() {
        boolean zM2424a;
        m2422t();
        C2053n c2053n = new C2053n("Failed to load resource", new ArrayList(this.f5998f));
        C2049j c2049j = (C2049j) this.f6012t;
        c2049j.f6087s = c2053n;
        C2049j.f6072z.obtainMessage(2, c2049j).sendToTarget();
        e eVar = this.f6003k;
        synchronized (eVar) {
            eVar.f6026c = true;
            zM2424a = eVar.m2424a(false);
        }
        if (zM2424a) {
            m2419q();
        }
    }

    /* renamed from: p */
    public final void m2418p() {
        boolean zM2424a;
        e eVar = this.f6003k;
        synchronized (eVar) {
            eVar.f6025b = true;
            zM2424a = eVar.m2424a(false);
        }
        if (zM2424a) {
            m2419q();
        }
    }

    /* renamed from: q */
    public final void m2419q() {
        e eVar = this.f6003k;
        synchronized (eVar) {
            eVar.f6025b = false;
            eVar.f6024a = false;
            eVar.f6026c = false;
        }
        c<?> cVar = this.f6002j;
        cVar.f6021a = null;
        cVar.f6022b = null;
        cVar.f6023c = null;
        C2044e<R> c2044e = this.f5997e;
        c2044e.f5973c = null;
        c2044e.f5974d = null;
        c2044e.f5984n = null;
        c2044e.f5977g = null;
        c2044e.f5981k = null;
        c2044e.f5979i = null;
        c2044e.f5985o = null;
        c2044e.f5980j = null;
        c2044e.f5986p = null;
        c2044e.f5971a.clear();
        c2044e.f5982l = false;
        c2044e.f5972b.clear();
        c2044e.f5983m = false;
        this.f5995G = false;
        this.f6004l = null;
        this.f6005m = null;
        this.f6011s = null;
        this.f6006n = null;
        this.f6007o = null;
        this.f6012t = null;
        this.f6014v = null;
        this.f5994F = null;
        this.f6018z = null;
        this.f5989A = null;
        this.f5991C = null;
        this.f5992D = null;
        this.f5993E = null;
        this.f6016x = 0L;
        this.f5996H = false;
        this.f5998f.clear();
        this.f6001i.release(this);
    }

    /* renamed from: r */
    public final void m2420r() {
        this.f6018z = Thread.currentThread();
        int i7 = C1819d.f5292b;
        this.f6016x = SystemClock.elapsedRealtimeNanos();
        boolean zMo2400f = false;
        while (!this.f5996H && this.f5994F != null && !(zMo2400f = this.f5994F.mo2400f())) {
            this.f6014v = m2415m(this.f6014v);
            this.f5994F = m2414l();
            if (this.f6014v == g.SOURCE) {
                mo2402b();
                return;
            }
        }
        if ((this.f6014v == g.FINISHED || this.f5996H) && !zMo2400f) {
            m2417o();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        InterfaceC1986b<?> interfaceC1986b;
        TraceCompat.beginSection("DecodeJob#run");
        try {
            try {
            } catch (RuntimeException e7) {
                if (Log.isLoggable("DecodeJob", 3)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("DecodeJob threw unexpectedly, isCancelled: ");
                    sb.append(this.f5996H);
                    sb.append(", stage: ");
                    sb.append(this.f6014v);
                }
                if (this.f6014v != g.ENCODE) {
                    m2417o();
                }
                if (!this.f5996H) {
                    throw e7;
                }
                interfaceC1986b = this.f5993E;
                if (interfaceC1986b != null) {
                }
            }
            if (this.f5996H) {
                m2417o();
                return;
            }
            m2421s();
            interfaceC1986b = this.f5993E;
            if (interfaceC1986b != null) {
                interfaceC1986b.mo124b();
            }
            TraceCompat.endSection();
        } finally {
            InterfaceC1986b<?> interfaceC1986b2 = this.f5993E;
            if (interfaceC1986b2 != null) {
                interfaceC1986b2.mo124b();
            }
            TraceCompat.endSection();
        }
    }

    /* renamed from: s */
    public final void m2421s() {
        int iOrdinal = this.f6015w.ordinal();
        if (iOrdinal == 0) {
            this.f6014v = m2415m(g.INITIALIZE);
            this.f5994F = m2414l();
            m2420r();
        } else if (iOrdinal == 1) {
            m2420r();
        } else if (iOrdinal == 2) {
            m2413i();
        } else {
            StringBuilder sbM112a = C0413b.m112a("Unrecognized run reason: ");
            sbM112a.append(this.f6015w);
            throw new IllegalStateException(sbM112a.toString());
        }
    }

    /* renamed from: t */
    public final void m2422t() {
        this.f5999g.mo2133a();
        if (this.f5995G) {
            throw new IllegalStateException("Already notified");
        }
        this.f5995G = true;
    }
}
