package p154s5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import p006a5.C0027m;
import p006a5.C0029o;
import p006a5.InterfaceC0018d;
import p006a5.InterfaceC0024j;
import p006a5.InterfaceC0028n;
import p097l5.AbstractC1443h;
import p097l5.C1438c;
import p113n5.C1555p;
import p113n5.InterfaceC1548i;
import p113n5.InterfaceC1549j;
import p131p5.C1743c;
import p131p5.C1745e;
import p131p5.C1746f;
import p131p5.C1747g;
import p147r5.C1844g;
import p161t5.C1918j;

/* compiled from: ServletContextHandler.java */
/* renamed from: s5.d */
/* loaded from: classes.dex */
public class C1887d extends C1743c {

    /* renamed from: J */
    public final List<b> f5496J;

    /* renamed from: K */
    public Class<? extends AbstractC1443h> f5497K;

    /* renamed from: L */
    public C1844g f5498L;

    /* renamed from: M */
    public AbstractC1443h f5499M;

    /* renamed from: N */
    public C1888e f5500N;

    /* renamed from: O */
    public C1747g f5501O;

    /* renamed from: P */
    public int f5502P;

    /* compiled from: ServletContextHandler.java */
    /* renamed from: s5.d$a */
    public class a extends C1743c.b {
        public a() {
            super();
        }

        /* renamed from: c */
        public <T extends InterfaceC0018d> T m2155c(Class<T> cls) throws IllegalAccessException, InstantiationException, C0029o {
            try {
                T tNewInstance = cls.newInstance();
                for (int size = C1887d.this.f5496J.size() - 1; size >= 0; size--) {
                    tNewInstance = (T) C1887d.this.f5496J.get(size).m2159c(tNewInstance);
                }
                return tNewInstance;
            } catch (IllegalAccessException e7) {
                throw new C0029o(e7);
            } catch (InstantiationException e8) {
                throw new C0029o(e8);
            }
        }

        /* renamed from: e */
        public <T extends InterfaceC0024j> T m2156e(Class<T> cls) throws IllegalAccessException, InstantiationException, C0029o {
            try {
                T tNewInstance = cls.newInstance();
                for (int size = C1887d.this.f5496J.size() - 1; size >= 0; size--) {
                    tNewInstance = (T) C1887d.this.f5496J.get(size).m2158b(tNewInstance);
                }
                return tNewInstance;
            } catch (IllegalAccessException e7) {
                throw new C0029o(e7);
            } catch (InstantiationException e8) {
                throw new C0029o(e8);
            }
        }
    }

    /* compiled from: ServletContextHandler.java */
    /* renamed from: s5.d$b */
    public interface b {
        /* renamed from: a */
        void m2157a(C1884a c1884a);

        /* renamed from: b */
        <T extends InterfaceC0024j> T m2158b(T t6);

        /* renamed from: c */
        <T extends InterfaceC0018d> T m2159c(T t6);

        /* renamed from: d */
        void m2160d(C1889f c1889f);

        /* renamed from: e */
        void m2161e(InterfaceC0024j interfaceC0024j);

        /* renamed from: f */
        void m2162f(InterfaceC0018d interfaceC0018d);
    }

    public C1887d() {
        this(null, null, null, null, null, null);
    }

    @Override // p131p5.C1743c
    /* renamed from: U */
    public void mo1898U(InterfaceC0028n interfaceC0028n, C0027m c0027m) {
        try {
            interfaceC0028n.m21n(c0027m);
        } finally {
            Objects.requireNonNull(this.f4938n);
        }
    }

    @Override // p131p5.C1743c
    /* renamed from: a0 */
    public void mo1903a0() {
        C1747g c1747g;
        if (this.f5498L == null && (this.f5502P & 1) != 0 && !isStarted()) {
            this.f5498L = new C1844g();
        }
        if (this.f5499M == null && (this.f5502P & 2) != 0 && !isStarted()) {
            try {
                this.f5499M = this.f5497K.newInstance();
            } catch (Exception e7) {
                throw new IllegalStateException(e7);
            }
        }
        if (this.f5500N == null && !isStarted()) {
            this.f5500N = new C1888e();
        }
        C1747g c1747g2 = this.f5500N;
        AbstractC1443h abstractC1443h = this.f5499M;
        if (abstractC1443h != null) {
            abstractC1443h.m1910P(c1747g2);
            c1747g2 = this.f5499M;
        }
        C1844g c1844g = this.f5498L;
        if (c1844g != null) {
            c1844g.m1910P(c1747g2);
            c1747g2 = this.f5498L;
        }
        this.f5501O = this;
        while (true) {
            c1747g = this.f5501O;
            if (c1747g == c1747g2) {
                break;
            }
            InterfaceC1548i interfaceC1548i = c1747g.f4960j;
            if (!(interfaceC1548i instanceof C1747g)) {
                break;
            } else {
                this.f5501O = (C1747g) interfaceC1548i;
            }
        }
        if (c1747g != c1747g2) {
            if (c1747g.f4960j != null) {
                throw new IllegalStateException("!ScopedHandler");
            }
            c1747g.m1910P(c1747g2);
        }
        super.mo1903a0();
        C1888e c1888e = this.f5500N;
        if (c1888e == null || !c1888e.isStarted()) {
            return;
        }
        int size = this.f5496J.size();
        while (true) {
            size--;
            if (size < 0) {
                this.f5500N.m2164U();
                return;
            }
            b bVar = this.f5496J.get(size);
            C1884a[] c1884aArr = this.f5500N.f5511p;
            if (c1884aArr != null) {
                for (C1884a c1884a : c1884aArr) {
                    bVar.m2157a(c1884a);
                }
            }
            C1889f[] c1889fArr = this.f5500N.f5516u;
            if (c1889fArr != null) {
                for (C1889f c1889f : c1889fArr) {
                    bVar.m2160d(c1889f);
                }
            }
        }
    }

    /* renamed from: b0 */
    public void m2154b0(C1889f c1889f, String str) {
        if (this.f5500N == null && !isStarted()) {
            this.f5500N = new C1888e();
        }
        C1888e c1888e = this.f5500N;
        C1889f[] c1889fArr = c1888e.f5516u;
        if (c1889fArr != null) {
            c1889fArr = (C1889f[]) c1889fArr.clone();
        }
        C1889f[] c1889fArr2 = c1889fArr;
        try {
            c1888e.m2167X((C1889f[]) C1918j.m2223d(c1889fArr2, c1889f, C1889f.class));
            C1890g c1890g = new C1890g();
            c1890g.f5543b = c1889f.f5493k;
            c1890g.f5542a = new String[]{str};
            C1890g[] c1890gArr = (C1890g[]) C1918j.m2223d(c1888e.f5517v, c1890g, C1890g.class);
            C1555p c1555p = c1888e.f4928h;
            if (c1555p != null) {
                c1555p.f4670k.m2316h(c1888e, c1888e.f5517v, c1890gArr, "servletMapping", true);
            }
            c1888e.f5517v = c1890gArr;
            c1888e.m2168Y();
            c1888e.m2165V();
        } catch (Exception e7) {
            c1888e.m2167X(c1889fArr2);
            if (!(e7 instanceof RuntimeException)) {
                throw new RuntimeException(e7);
            }
            throw ((RuntimeException) e7);
        }
    }

    @Override // p131p5.C1743c, p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public void doStop() {
        super.doStop();
        List<b> list = this.f5496J;
        if (list != null) {
            list.clear();
        }
        C1747g c1747g = this.f5501O;
        if (c1747g != null) {
            c1747g.m1910P(null);
        }
    }

    public C1887d(InterfaceC1549j interfaceC1549j, String str, C1844g c1844g, AbstractC1443h abstractC1443h, C1888e c1888e, C1745e c1745e) {
        super(null);
        this.f5496J = new ArrayList();
        this.f5497K = C1438c.class;
        this.f4938n = new a();
        this.f5498L = c1844g;
        this.f5499M = abstractC1443h;
        this.f5500N = c1888e;
        if (c1745e != null) {
            c1745e.mo1772h(this.f4928h);
            C1555p c1555p = this.f4928h;
            if (c1555p != null) {
                c1555p.f4670k.m2314f(this, this.f4944t, c1745e, "errorHandler", true);
            }
            this.f4944t = c1745e;
        }
        if (str != null) {
            m1901Y(str);
        }
        if (interfaceC1549j instanceof C1747g) {
            ((C1747g) interfaceC1549j).m1910P(this);
        } else if (interfaceC1549j instanceof C1746f) {
            C1746f c1746f = (C1746f) interfaceC1549j;
            c1746f.mo1906P((InterfaceC1548i[]) C1918j.m2223d(c1746f.f4959k, this, InterfaceC1548i.class));
        }
    }
}
