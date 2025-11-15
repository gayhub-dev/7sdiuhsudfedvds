package p105m5;

import java.io.PrintWriter;
import java.util.Properties;
import p006a5.AbstractC0030p;
import p006a5.InterfaceC0032r;
import p015b5.InterfaceC0460e;
import p097l5.C1444i;
import p097l5.InterfaceC1440e;
import p113n5.InterfaceC1543d;
import p161t5.C1917i;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: DeferredAuthentication.java */
/* renamed from: m5.c */
/* loaded from: classes.dex */
public class C1492c implements InterfaceC1543d.e {

    /* renamed from: g */
    public static final InterfaceC2016c f4249g;

    /* renamed from: h */
    public static final InterfaceC0460e f4250h;

    /* renamed from: i */
    public static AbstractC0030p f4251i;

    /* renamed from: e */
    public final AbstractC1495f f4252e;

    /* renamed from: f */
    public Object f4253f;

    /* compiled from: DeferredAuthentication.java */
    /* renamed from: m5.c$a */
    public static class a implements InterfaceC0460e {
        @Override // p015b5.InterfaceC0460e
        /* renamed from: A */
        public void mo175A(int i7, String str) {
        }

        @Override // p015b5.InterfaceC0460e
        /* renamed from: C */
        public boolean mo176C(String str) {
            return false;
        }

        @Override // p006a5.InterfaceC0037w
        /* renamed from: F */
        public PrintWriter mo41F() {
            return C1917i.f5648e;
        }

        @Override // p006a5.InterfaceC0037w
        /* renamed from: H */
        public void mo42H(int i7) {
        }

        @Override // p015b5.InterfaceC0460e
        /* renamed from: J */
        public void mo177J(String str, String str2) {
        }

        @Override // p006a5.InterfaceC0037w
        /* renamed from: M */
        public void mo43M(String str) {
        }

        @Override // p006a5.InterfaceC0037w
        /* renamed from: P */
        public boolean mo44P() {
            return true;
        }

        @Override // p015b5.InterfaceC0460e
        /* renamed from: S */
        public void mo178S(int i7) {
        }

        @Override // p015b5.InterfaceC0460e
        /* renamed from: T */
        public void mo179T(String str) {
        }

        @Override // p006a5.InterfaceC0037w
        /* renamed from: i */
        public AbstractC0030p mo45i() {
            return C1492c.f4251i;
        }

        @Override // p006a5.InterfaceC0037w
        /* renamed from: j */
        public String mo46j() {
            return null;
        }

        @Override // p015b5.InterfaceC0460e
        /* renamed from: k */
        public void mo180k(String str, long j7) {
        }

        @Override // p015b5.InterfaceC0460e
        /* renamed from: m */
        public void mo181m(String str, long j7) {
        }

        @Override // p006a5.InterfaceC0037w
        /* renamed from: o */
        public void mo47o() {
        }

        @Override // p015b5.InterfaceC0460e
        /* renamed from: q */
        public void mo182q(String str, String str2) {
        }

        @Override // p015b5.InterfaceC0460e
        /* renamed from: v */
        public void mo183v(int i7) {
        }

        @Override // p006a5.InterfaceC0037w
        /* renamed from: y */
        public void mo48y(String str) {
        }

        @Override // p015b5.InterfaceC0460e
        /* renamed from: z */
        public String mo184z(String str) {
            return null;
        }
    }

    /* compiled from: DeferredAuthentication.java */
    /* renamed from: m5.c$b */
    public static class b extends AbstractC0030p {
        @Override // p006a5.AbstractC0030p
        /* renamed from: a */
        public void mo22a(String str) {
        }

        @Override // java.io.OutputStream
        public void write(int i7) {
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f4249g = C2015b.m2349a(C1492c.class.getName());
        f4250h = new a();
        f4251i = new b();
    }

    public C1492c(AbstractC1495f abstractC1495f) {
        this.f4252e = abstractC1495f;
    }

    /* renamed from: b */
    public static boolean m1651b(InterfaceC0460e interfaceC0460e) {
        return interfaceC0460e == f4250h;
    }

    @Override // p113n5.InterfaceC1543d.e
    /* renamed from: A */
    public InterfaceC1543d mo1652A(InterfaceC0032r interfaceC0032r) {
        try {
            InterfaceC1543d interfaceC1543dMo1611d = this.f4252e.mo1611d(interfaceC0032r, f4250h, true);
            if (interfaceC1543dMo1611d != null && (interfaceC1543dMo1611d instanceof InterfaceC1543d.g) && !(interfaceC1543dMo1611d instanceof InterfaceC1543d.f)) {
                InterfaceC1440e interfaceC1440eM1622b = this.f4252e.f4273a.m1622b();
                if (interfaceC1440eM1622b != null) {
                    this.f4253f = interfaceC1440eM1622b.m1620e(((InterfaceC1543d.g) interfaceC1543dMo1611d).mo1632h());
                }
                return interfaceC1543dMo1611d;
            }
        } catch (C1444i e7) {
            f4249g.mo2359j(e7);
        }
        return this;
    }
}
