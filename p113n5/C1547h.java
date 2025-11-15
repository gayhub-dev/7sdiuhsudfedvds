package p113n5;

import java.util.Objects;
import p006a5.InterfaceC0023i;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p009b.C0413b;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p131p5.C1743c;
import p161t5.C1910b;
import p161t5.ConcurrentMapC1920l;
import p161t5.InterfaceC1909a;

/* compiled from: Dispatcher.java */
/* renamed from: n5.h */
/* loaded from: classes.dex */
public class C1547h implements InterfaceC0023i {

    /* renamed from: a */
    public final C1743c f4593a;

    /* renamed from: b */
    public final String f4594b;

    /* renamed from: c */
    public final String f4595c;

    /* renamed from: d */
    public final String f4596d;

    /* compiled from: Dispatcher.java */
    /* renamed from: n5.h$a */
    public class a implements InterfaceC1909a {

        /* renamed from: e */
        public final InterfaceC1909a f4597e;

        /* renamed from: f */
        public String f4598f;

        /* renamed from: g */
        public String f4599g;

        /* renamed from: h */
        public String f4600h;

        /* renamed from: i */
        public String f4601i;

        /* renamed from: j */
        public String f4602j;

        public a(InterfaceC1909a interfaceC1909a) {
            this.f4597e = interfaceC1909a;
        }

        @Override // p161t5.InterfaceC1909a
        /* renamed from: a */
        public Object mo892a(String str) {
            Objects.requireNonNull(C1547h.this);
            if (str.equals("javax.servlet.forward.path_info")) {
                return this.f4601i;
            }
            if (str.equals("javax.servlet.forward.request_uri")) {
                return this.f4598f;
            }
            if (str.equals("javax.servlet.forward.servlet_path")) {
                return this.f4600h;
            }
            if (str.equals("javax.servlet.forward.context_path")) {
                return this.f4599g;
            }
            if (str.equals("javax.servlet.forward.query_string")) {
                return this.f4602j;
            }
            if (str.startsWith("javax.servlet.include.")) {
                return null;
            }
            return this.f4597e.mo892a(str);
        }

        @Override // p161t5.InterfaceC1909a
        /* renamed from: b */
        public void mo893b(String str, Object obj) {
            Objects.requireNonNull(C1547h.this);
            if (!str.startsWith("javax.servlet.")) {
                if (obj == null) {
                    this.f4597e.mo894e(str);
                    return;
                } else {
                    this.f4597e.mo893b(str, obj);
                    return;
                }
            }
            if (str.equals("javax.servlet.forward.path_info")) {
                this.f4601i = (String) obj;
                return;
            }
            if (str.equals("javax.servlet.forward.request_uri")) {
                this.f4598f = (String) obj;
                return;
            }
            if (str.equals("javax.servlet.forward.servlet_path")) {
                this.f4600h = (String) obj;
                return;
            }
            if (str.equals("javax.servlet.forward.context_path")) {
                this.f4599g = (String) obj;
                return;
            }
            if (str.equals("javax.servlet.forward.query_string")) {
                this.f4602j = (String) obj;
            } else if (obj == null) {
                this.f4597e.mo894e(str);
            } else {
                this.f4597e.mo893b(str, obj);
            }
        }

        @Override // p161t5.InterfaceC1909a
        /* renamed from: e */
        public void mo894e(String str) {
            mo893b(str, null);
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("FORWARD+");
            sbM112a.append(this.f4597e.toString());
            return sbM112a.toString();
        }

        @Override // p161t5.InterfaceC1909a
        /* renamed from: v */
        public void mo896v() {
            throw new IllegalStateException();
        }
    }

    public C1547h(C1743c c1743c, String str, String str2, String str3) {
        this.f4593a = c1743c;
        this.f4594b = str;
        this.f4595c = str2;
        this.f4596d = str3;
    }

    /* renamed from: a */
    public void m1770a(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w, int i7) {
        InterfaceC0032r c1556q = interfaceC0032r;
        InterfaceC0037w c1557r = interfaceC0037w;
        C1553n c1553n = c1556q instanceof C1553n ? (C1553n) c1556q : AbstractC1541b.m1735g().f4548i;
        C1554o c1554o = c1553n.f4638g.f4552m;
        interfaceC0037w.mo47o();
        c1554o.mo47o();
        c1554o.f4667i = null;
        c1554o.f4666h = 0;
        if (!(c1556q instanceof InterfaceC0458c)) {
            c1556q = new C1556q(c1556q);
        }
        if (!(c1557r instanceof InterfaceC0460e)) {
            c1557r = new C1557r(c1557r);
        }
        boolean z6 = c1553n.f4647p;
        String strMo167U = c1553n.mo167U();
        String str = c1553n.f4641j;
        String strMo160B = c1553n.mo160B();
        String str2 = c1553n.f4652u;
        String strMo166O = c1553n.mo166O();
        if (c1553n.f4634c == null) {
            c1553n.f4634c = new C1910b();
        }
        InterfaceC1909a interfaceC1909a = c1553n.f4634c;
        int i8 = c1553n.f4644m;
        ConcurrentMapC1920l<String> concurrentMapC1920l = c1553n.f4650s;
        try {
            c1553n.f4647p = false;
            c1553n.f4644m = i7;
            String str3 = this.f4596d;
            if (str3 != null) {
                if (concurrentMapC1920l == null) {
                    c1553n.m1784j();
                    concurrentMapC1920l = c1553n.f4650s;
                }
                c1553n.m1789s(str3);
            }
            a aVar = new a(interfaceC1909a);
            if (interfaceC1909a.mo892a("javax.servlet.forward.request_uri") != null) {
                aVar.f4601i = (String) interfaceC1909a.mo892a("javax.servlet.forward.path_info");
                aVar.f4602j = (String) interfaceC1909a.mo892a("javax.servlet.forward.query_string");
                aVar.f4598f = (String) interfaceC1909a.mo892a("javax.servlet.forward.request_uri");
                aVar.f4599g = (String) interfaceC1909a.mo892a("javax.servlet.forward.context_path");
                aVar.f4600h = (String) interfaceC1909a.mo892a("javax.servlet.forward.servlet_path");
            } else {
                aVar.f4601i = str2;
                aVar.f4602j = strMo166O;
                aVar.f4598f = strMo167U;
                aVar.f4599g = str;
                aVar.f4600h = strMo160B;
            }
            String str4 = this.f4594b;
            c1553n.f4621D = str4;
            c1553n.f4641j = this.f4593a.f4942r;
            c1553n.f4625H = null;
            c1553n.f4652u = str4;
            c1553n.f4634c = aVar;
            this.f4593a.mo1630u(this.f4595c, c1553n, (InterfaceC0458c) c1556q, (InterfaceC0460e) c1557r);
            if (!c1553n.f4632a.m1755m()) {
                if (c1553n.f4638g.f4552m.f4666h == 2) {
                    try {
                        c1557r.mo41F().close();
                    } catch (IllegalStateException unused) {
                        c1557r.mo45i().close();
                    }
                } else {
                    try {
                        c1557r.mo45i().close();
                    } catch (IllegalStateException unused2) {
                        c1557r.mo41F().close();
                    }
                }
            }
        } finally {
            c1553n.f4647p = z6;
            c1553n.f4621D = strMo167U;
            c1553n.f4641j = str;
            c1553n.f4625H = strMo160B;
            c1553n.f4652u = str2;
            c1553n.f4634c = interfaceC1909a;
            c1553n.m1779C(concurrentMapC1920l);
            c1553n.m1781F(strMo166O);
            c1553n.f4644m = i8;
        }
    }
}
