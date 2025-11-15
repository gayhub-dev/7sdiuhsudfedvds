package p041e5;

import android.support.v7.widget.ActivityChooserView;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import p007a6.C0045e;
import p007a6.ExecutorC0042b;
import p007a6.InterfaceC0044d;
import p065h5.C1097e;
import p065h5.InterfaceC1096d;
import p073i5.InterfaceC1156i;
import p161t5.C1910b;
import p161t5.InterfaceC1909a;
import p168u5.C1981b;
import p168u5.InterfaceC1984e;
import p196y5.C2133a;

/* compiled from: HttpClient.java */
/* renamed from: e5.g */
/* loaded from: classes.dex */
public class C0962g extends C1981b implements InterfaceC1096d, InterfaceC1909a {

    /* renamed from: h */
    public int f1741h;

    /* renamed from: i */
    public boolean f1742i;

    /* renamed from: j */
    public boolean f1743j;

    /* renamed from: k */
    public int f1744k;

    /* renamed from: l */
    public int f1745l;

    /* renamed from: m */
    public ConcurrentMap<C0957b, C0963h> f1746m;

    /* renamed from: n */
    public InterfaceC0044d f1747n;

    /* renamed from: o */
    public b f1748o;

    /* renamed from: p */
    public long f1749p;

    /* renamed from: q */
    public long f1750q;

    /* renamed from: r */
    public int f1751r;

    /* renamed from: s */
    public C0045e f1752s;

    /* renamed from: t */
    public C0045e f1753t;

    /* renamed from: u */
    public int f1754u;

    /* renamed from: v */
    public final C2133a f1755v;

    /* renamed from: w */
    public C1910b f1756w;

    /* renamed from: x */
    public final C1097e f1757x;

    /* compiled from: HttpClient.java */
    /* renamed from: e5.g$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (C0962g.this.isRunning()) {
                C0962g.this.f1752s.m54e(System.currentTimeMillis());
                C0962g c0962g = C0962g.this;
                c0962g.f1753t.m54e(c0962g.f1752s.f61c);
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    /* compiled from: HttpClient.java */
    /* renamed from: e5.g$b */
    public interface b extends InterfaceC1984e {
        /* renamed from: n */
        void mo897n(C0963h c0963h);
    }

    /* compiled from: HttpClient.java */
    /* renamed from: e5.g$c */
    public static class c extends ExecutorC0042b {
        public c(a aVar) {
        }
    }

    public C0962g() {
        C2133a c2133a = new C2133a();
        this.f1741h = 2;
        this.f1742i = true;
        this.f1743j = true;
        this.f1744k = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f1745l = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f1746m = new ConcurrentHashMap();
        this.f1749p = 20000L;
        this.f1750q = 320000L;
        this.f1751r = 75000;
        this.f1752s = new C0045e();
        this.f1753t = new C0045e();
        this.f1754u = 3;
        this.f1756w = new C1910b();
        C1097e c1097e = new C1097e();
        this.f1757x = c1097e;
        this.f1755v = c2133a;
        mo1798G(c2133a);
        mo1798G(c1097e);
    }

    @Override // p065h5.InterfaceC1096d
    /* renamed from: F */
    public InterfaceC1156i mo891F() {
        return this.f1757x.f2271n;
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: a */
    public Object mo892a(String str) {
        return this.f1756w.f5612e.get(str);
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: b */
    public void mo893b(String str, Object obj) {
        C1910b c1910b = this.f1756w;
        if (obj == null) {
            c1910b.f5612e.remove(str);
        } else {
            c1910b.f5612e.put(str, obj);
        }
    }

    @Override // p168u5.C1981b, p168u5.AbstractC1980a
    public void doStart() {
        if (this.f1741h == 0) {
            C1097e c1097e = this.f1757x;
            c1097e.f2267j = 1;
            c1097e.f2268k = 1;
            c1097e.f2269l = 1;
            c1097e.f2270m = 1;
        } else {
            C1097e c1097e2 = this.f1757x;
            c1097e2.f2267j = 2;
            boolean z6 = this.f1742i;
            c1097e2.f2268k = z6 ? 2 : 3;
            c1097e2.f2269l = 2;
            c1097e2.f2270m = z6 ? 2 : 3;
        }
        C0045e c0045e = this.f1752s;
        c0045e.f60b = this.f1750q;
        c0045e.f61c = System.currentTimeMillis();
        C0045e c0045e2 = this.f1753t;
        c0045e2.f60b = this.f1749p;
        c0045e2.f61c = System.currentTimeMillis();
        if (this.f1747n == null) {
            c cVar = new c(null);
            cVar.f47m = 16;
            if (cVar.f48n > 16) {
                cVar.f48n = 16;
            }
            cVar.f51q = true;
            if (cVar.isRunning()) {
                throw new IllegalStateException("started");
            }
            cVar.f45k = "HttpClient";
            this.f1747n = cVar;
            m2306H(cVar, true);
        }
        b c0966k = this.f1741h == 2 ? new C0966k(this) : new C0967l(this);
        this.f1748o = c0966k;
        m2306H(c0966k, true);
        super.doStart();
        this.f1747n.dispatch(new a());
    }

    @Override // p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() {
        for (C0963h c0963h : this.f1746m.values()) {
            synchronized (c0963h) {
                Iterator<AbstractC0956a> it = c0963h.f1761b.iterator();
                while (it.hasNext()) {
                    it.next().m863f();
                }
            }
        }
        this.f1752s.m50a();
        this.f1753t.m50a();
        super.doStop();
        InterfaceC0044d interfaceC0044d = this.f1747n;
        if (interfaceC0044d instanceof c) {
            mo1799L(interfaceC0044d);
            this.f1747n = null;
        }
        mo1799L(this.f1748o);
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: e */
    public void mo894e(String str) {
        this.f1756w.f5612e.remove(str);
    }

    @Override // p065h5.InterfaceC1096d
    /* renamed from: t */
    public InterfaceC1156i mo895t() {
        return this.f1757x.f2272o;
    }

    @Override // p161t5.InterfaceC1909a
    /* renamed from: v */
    public void mo896v() {
        this.f1756w.f5612e.clear();
    }
}
