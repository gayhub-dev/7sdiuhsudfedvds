package p113n5;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Objects;
import java.util.Properties;
import p065h5.AbstractC1093a;
import p065h5.C1094b;
import p065h5.C1100h;
import p065h5.C1101i;
import p065h5.C1105m;
import p065h5.C1107o;
import p065h5.C1108p;
import p065h5.C1109q;
import p065h5.C1113u;
import p065h5.C1114v;
import p065h5.InterfaceC1095c;
import p065h5.InterfaceC1096d;
import p065h5.InterfaceC1098f;
import p065h5.InterfaceC1116x;
import p073i5.AbstractC1148a;
import p073i5.AbstractC1150c;
import p073i5.C1153f;
import p073i5.C1155h;
import p073i5.C1158k;
import p073i5.C1162o;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1161n;
import p139q5.InterfaceC1779a;
import p161t5.C1914f;
import p161t5.C1923o;
import p161t5.C1928t;
import p161t5.ConcurrentMapC1920l;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;
import p182w5.AbstractC2038e;

/* compiled from: AbstractHttpConnection.java */
/* renamed from: n5.b */
/* loaded from: classes.dex */
public abstract class AbstractC1541b extends AbstractC1150c {

    /* renamed from: A */
    public static final ThreadLocal<AbstractC1541b> f4540A;

    /* renamed from: z */
    public static final InterfaceC2016c f4541z;

    /* renamed from: c */
    public int f4542c;

    /* renamed from: d */
    public final InterfaceC1545f f4543d;

    /* renamed from: e */
    public final C1555p f4544e;

    /* renamed from: f */
    public final C1113u f4545f;

    /* renamed from: g */
    public final InterfaceC1116x f4546g;

    /* renamed from: h */
    public final C1101i f4547h;

    /* renamed from: i */
    public final C1553n f4548i;

    /* renamed from: j */
    public volatile C1550k f4549j;

    /* renamed from: k */
    public final InterfaceC1095c f4550k;

    /* renamed from: l */
    public final C1101i f4551l;

    /* renamed from: m */
    public final C1554o f4552m;

    /* renamed from: n */
    public volatile b f4553n;

    /* renamed from: o */
    public volatile c f4554o;

    /* renamed from: p */
    public volatile PrintWriter f4555p;

    /* renamed from: q */
    public int f4556q;

    /* renamed from: r */
    public String f4557r;

    /* renamed from: s */
    public boolean f4558s;

    /* renamed from: t */
    public boolean f4559t;

    /* renamed from: u */
    public boolean f4560u;

    /* renamed from: v */
    public boolean f4561v;

    /* renamed from: w */
    public boolean f4562w;

    /* renamed from: x */
    public boolean f4563x;

    /* renamed from: y */
    public boolean f4564y;

    /* compiled from: AbstractHttpConnection.java */
    /* renamed from: n5.b$a */
    public class a extends PrintWriter {
        public a(AbstractC1541b abstractC1541b, Writer writer) {
            super(writer);
        }

        @Override // java.io.PrintWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            synchronized (((PrintWriter) this).lock) {
                try {
                    ((PrintWriter) this).out.close();
                } catch (IOException unused) {
                    setError();
                }
            }
        }
    }

    /* compiled from: AbstractHttpConnection.java */
    /* renamed from: n5.b$b */
    public class b extends C1551l {
        public b() {
            super(AbstractC1541b.this);
        }

        @Override // p006a5.AbstractC0030p
        /* renamed from: a */
        public void mo22a(String str) throws IOException {
            if (this.f4608h) {
                throw new IOException("Closed");
            }
            AbstractC1541b.this.m1738i(null).print(str);
        }

        @Override // p113n5.C1551l, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f4608h) {
                return;
            }
            Objects.requireNonNull(AbstractC1541b.this);
            if (this.f4607g.m1187g()) {
                AbstractC1541b abstractC1541b = AbstractC1541b.this;
                Objects.requireNonNull(abstractC1541b);
                try {
                    abstractC1541b.m1736f(false);
                    ((C1105m) abstractC1541b.f4550k).mo1184d();
                } catch (IOException e7) {
                    if (!(e7 instanceof C1162o)) {
                        throw new C1162o(e7);
                    }
                    throw e7;
                }
            } else {
                AbstractC1541b.this.m1736f(true);
            }
            this.f4608h = true;
        }

        /* renamed from: e */
        public void m1741e(Object obj) {
            if (this.f4608h) {
                throw new IOException("Closed");
            }
            boolean z6 = false;
            if (this.f4607g.f2250i > 0) {
                throw new IllegalStateException("!empty");
            }
            AbstractC2038e abstractC2038e = null;
            if (obj instanceof InterfaceC1098f) {
                InterfaceC1098f interfaceC1098f = (InterfaceC1098f) obj;
                InterfaceC1152e contentType = interfaceC1098f.getContentType();
                if (contentType != null) {
                    C1101i c1101i = AbstractC1541b.this.f4551l;
                    InterfaceC1152e interfaceC1152e = C1107o.f2331i;
                    if (!c1101i.f2297b.containsKey(C1107o.f2326d.m1359g(interfaceC1152e))) {
                        AbstractC1541b abstractC1541b = AbstractC1541b.this;
                        String str = abstractC1541b.f4552m.f4664f;
                        if (str == null) {
                            abstractC1541b.f4551l.m1222a(interfaceC1152e, contentType);
                        } else if (contentType instanceof C1153f.a) {
                            C1153f.a aVarM1361b = ((C1153f.a) contentType).m1361b(str);
                            if (aVarM1361b != null) {
                                AbstractC1541b.this.f4551l.m1228i(interfaceC1152e, aVarM1361b);
                            } else {
                                AbstractC1541b.this.f4551l.m1229j(interfaceC1152e, contentType + ";charset=" + C1923o.m2239c(str, ";= "));
                            }
                        } else {
                            abstractC1541b.f4551l.m1229j(interfaceC1152e, contentType + ";charset=" + C1923o.m2239c(str, ";= "));
                        }
                    }
                }
                if (interfaceC1098f.m1216g() > 0) {
                    C1101i c1101i2 = AbstractC1541b.this.f4551l;
                    InterfaceC1152e interfaceC1152e2 = C1107o.f2328f;
                    long jM1216g = interfaceC1098f.m1216g();
                    Objects.requireNonNull(c1101i2);
                    C1158k c1158k = new C1158k(32);
                    C1155h.m1363a(c1158k, jM1216g);
                    c1101i2.m1228i(interfaceC1152e2, c1158k);
                }
                InterfaceC1152e interfaceC1152eM1215f = interfaceC1098f.m1215f();
                long jMo2394c = interfaceC1098f.m1214e().mo2394c();
                if (interfaceC1152eM1215f != null) {
                    AbstractC1541b.this.f4551l.m1228i(C1107o.f2333k, interfaceC1152eM1215f);
                } else if (interfaceC1098f.m1214e() != null && jMo2394c != -1) {
                    AbstractC1541b.this.f4551l.m1231l(C1107o.f2333k, jMo2394c);
                }
                InterfaceC1152e interfaceC1152eM1213d = interfaceC1098f.m1213d();
                if (interfaceC1152eM1213d != null) {
                    AbstractC1541b.this.f4551l.m1228i(C1107o.f2335m, interfaceC1152eM1213d);
                }
                InterfaceC1545f interfaceC1545f = AbstractC1541b.this.f4543d;
                if ((interfaceC1545f instanceof InterfaceC1779a) && ((InterfaceC1779a) interfaceC1545f).m1968a()) {
                    InterfaceC1545f interfaceC1545f2 = AbstractC1541b.this.f4543d;
                    z6 = true;
                }
                InterfaceC1152e interfaceC1152eM1211b = z6 ? interfaceC1098f.m1211b() : interfaceC1098f.m1210a();
                obj = interfaceC1152eM1211b == null ? interfaceC1098f.m1212c() : interfaceC1152eM1211b;
            } else if (obj instanceof AbstractC2038e) {
                abstractC2038e = (AbstractC2038e) obj;
                AbstractC1541b.this.f4551l.m1231l(C1107o.f2333k, abstractC2038e.mo2394c());
                obj = abstractC2038e.mo2393b();
            }
            if (obj instanceof InterfaceC1152e) {
                ((C1105m) this.f4607g).m1235t((InterfaceC1152e) obj, true);
                AbstractC1541b.this.m1736f(true);
                return;
            }
            if (!(obj instanceof InputStream)) {
                throw new IllegalArgumentException("unknown content type?");
            }
            InputStream inputStream = (InputStream) obj;
            try {
                int iMo1333n = this.f4607g.f2257p.mo1333n(inputStream, this.f4607g.mo1193m());
                while (iMo1333n >= 0) {
                    AbstractC1541b abstractC1541b2 = AbstractC1541b.this;
                    InterfaceC2016c interfaceC2016c = AbstractC1541b.f4541z;
                    if (abstractC1541b2.f2538a.mo925r()) {
                        break;
                    }
                    this.f4607g.m1183c();
                    AbstractC1541b.this.f4553n.flush();
                    iMo1333n = this.f4607g.f2257p.mo1333n(inputStream, this.f4607g.mo1193m());
                }
                this.f4607g.m1183c();
                AbstractC1541b.this.f4553n.flush();
                if (abstractC2038e != null) {
                    abstractC2038e.mo2395f();
                } else {
                    inputStream.close();
                }
            } catch (Throwable th) {
                if (abstractC2038e != null) {
                    abstractC2038e.mo2395f();
                } else {
                    inputStream.close();
                }
                throw th;
            }
        }

        /* renamed from: f */
        public void m1742f(InterfaceC1152e interfaceC1152e) {
            InterfaceC1152e interfaceC1152e2;
            C1105m c1105m = (C1105m) this.f4607g;
            if (c1105m.f2254m || c1105m.f2244c != 0 || (((interfaceC1152e2 = c1105m.f2258q) != null && interfaceC1152e2.length() > 0) || c1105m.f2320x || c1105m.f2253l)) {
                throw new IllegalStateException();
            }
            c1105m.f2252k = true;
            c1105m.f2258q = interfaceC1152e;
            c1105m.f2317u = true;
            c1105m.f2244c = 3;
            long length = ((AbstractC1148a) interfaceC1152e).length();
            c1105m.f2250i = length;
            c1105m.f2251j = length;
        }

        @Override // p113n5.C1551l, java.io.OutputStream, java.io.Flushable
        public void flush() throws C1100h, C1162o {
            if (!this.f4607g.m1187g()) {
                AbstractC1541b.this.m1736f(false);
            }
            super.flush();
        }
    }

    /* compiled from: AbstractHttpConnection.java */
    /* renamed from: n5.b$c */
    public class c extends C1552m {
        public c(AbstractC1541b abstractC1541b) {
            super(abstractC1541b.f4553n);
        }
    }

    /* compiled from: AbstractHttpConnection.java */
    /* renamed from: n5.b$d */
    public class d extends C1109q.a {
        public d(a aVar) {
        }

        @Override // p065h5.C1109q.a
        /* renamed from: a */
        public void mo868a(InterfaceC1152e interfaceC1152e) throws Throwable {
            AbstractC1541b abstractC1541b = AbstractC1541b.this;
            if (abstractC1541b.f4563x) {
                abstractC1541b.f4563x = false;
                abstractC1541b.mo1739j();
            }
        }

        @Override // p065h5.C1109q.a
        /* renamed from: b */
        public void mo869b() {
            AbstractC1541b.this.f4564y = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x011c  */
        @Override // p065h5.C1109q.a
        /* renamed from: c */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void mo870c() throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 295
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p113n5.AbstractC1541b.d.mo870c():void");
        }

        @Override // p065h5.C1109q.a
        /* renamed from: d */
        public void mo871d(long j7) throws Throwable {
            AbstractC1541b abstractC1541b = AbstractC1541b.this;
            if (abstractC1541b.f4563x) {
                abstractC1541b.f4563x = false;
                abstractC1541b.mo1739j();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x0084  */
        @Override // p065h5.C1109q.a
        /* renamed from: e */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void mo872e(p073i5.InterfaceC1152e r9, p073i5.InterfaceC1152e r10) {
            /*
                r8 = this;
                n5.b r0 = p113n5.AbstractC1541b.this
                java.util.Objects.requireNonNull(r0)
                h5.o r1 = p065h5.C1107o.f2326d
                int r1 = r1.m1357e(r9)
                r2 = 16
                if (r1 == r2) goto L8b
                r2 = 21
                if (r1 == r2) goto L84
                r2 = 24
                r3 = 1
                if (r1 == r2) goto L26
                r2 = 27
                if (r1 == r2) goto L22
                r2 = 40
                if (r1 == r2) goto L84
                goto L97
            L22:
                r0.f4562w = r3
                goto L97
            L26:
                int r1 = r0.f4556q
                r2 = 11
                if (r1 < r2) goto L97
                h5.n r1 = p065h5.C1106n.f2323d
                i5.e r10 = r1.m1359g(r10)
                int r1 = r1.m1357e(r10)
                r2 = 6
                if (r1 == r2) goto L7d
                r4 = 7
                if (r1 == r4) goto L76
                java.lang.String r1 = r10.toString()
                java.lang.String r5 = ","
                java.lang.String[] r1 = r1.split(r5)
                r5 = 0
            L47:
                if (r1 == 0) goto L97
                int r6 = r1.length
                if (r5 >= r6) goto L97
                h5.n r6 = p065h5.C1106n.f2323d
                r7 = r1[r5]
                java.lang.String r7 = r7.trim()
                i5.f$a r6 = r6.m1355c(r7)
                if (r6 != 0) goto L5d
                r0.f4558s = r3
                goto L73
            L5d:
                int r6 = r6.f2542r
                if (r6 == r2) goto L6d
                if (r6 == r4) goto L66
                r0.f4558s = r3
                goto L73
            L66:
                h5.c r6 = r0.f4550k
                boolean r6 = r6 instanceof p065h5.C1105m
                r0.f4560u = r6
                goto L73
            L6d:
                h5.c r6 = r0.f4550k
                boolean r6 = r6 instanceof p065h5.C1105m
                r0.f4559t = r6
            L73:
                int r5 = r5 + 1
                goto L47
            L76:
                h5.c r1 = r0.f4550k
                boolean r1 = r1 instanceof p065h5.C1105m
                r0.f4560u = r1
                goto L97
            L7d:
                h5.c r1 = r0.f4550k
                boolean r1 = r1 instanceof p065h5.C1105m
                r0.f4559t = r1
                goto L97
            L84:
                h5.n r1 = p065h5.C1106n.f2323d
                i5.e r10 = r1.m1359g(r10)
                goto L97
            L8b:
                i5.f r1 = p065h5.C1115w.f2385c
                i5.e r10 = r1.m1359g(r10)
                java.lang.String r1 = p065h5.C1115w.m1257a(r10)
                r0.f4557r = r1
            L97:
                h5.i r0 = r0.f4547h
                r0.m1222a(r9, r10)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p113n5.AbstractC1541b.d.mo872e(i5.e, i5.e):void");
        }

        @Override // p065h5.C1109q.a
        /* renamed from: f */
        public void mo873f(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2, InterfaceC1152e interfaceC1152e3) throws C1100h {
            AbstractC1541b abstractC1541b = AbstractC1541b.this;
            Objects.requireNonNull(abstractC1541b);
            InterfaceC1152e interfaceC1152eMo1325T = interfaceC1152e2.mo1325T();
            abstractC1541b.f4562w = false;
            abstractC1541b.f4558s = false;
            abstractC1541b.f4559t = false;
            abstractC1541b.f4560u = false;
            abstractC1541b.f4563x = false;
            abstractC1541b.f4557r = null;
            C1553n c1553n = abstractC1541b.f4548i;
            if (c1553n.f4628K == 0) {
                c1553n.f4628K = System.currentTimeMillis();
            }
            abstractC1541b.f4548i.f4649r = interfaceC1152e.toString();
            try {
                abstractC1541b.f4561v = false;
                int iM1357e = C1108p.f2338a.m1357e(interfaceC1152e);
                if (iM1357e == 3) {
                    abstractC1541b.f4561v = true;
                    abstractC1541b.f4545f.m1253l(interfaceC1152eMo1325T.mo1349P(), interfaceC1152eMo1325T.mo1316C(), interfaceC1152eMo1325T.length());
                } else if (iM1357e != 8) {
                    abstractC1541b.f4545f.m1253l(interfaceC1152eMo1325T.mo1349P(), interfaceC1152eMo1325T.mo1316C(), interfaceC1152eMo1325T.length());
                } else {
                    abstractC1541b.f4545f.m1255n(interfaceC1152eMo1325T.mo1349P(), interfaceC1152eMo1325T.mo1316C(), interfaceC1152eMo1325T.length());
                }
                C1553n c1553n2 = abstractC1541b.f4548i;
                c1553n2.f4630M = abstractC1541b.f4545f;
                if (interfaceC1152e3 == null) {
                    c1553n2.f4654w = "";
                    abstractC1541b.f4556q = 9;
                    return;
                }
                C1153f c1153f = C1114v.f2380a;
                C1153f.a aVar = (C1153f.a) c1153f.f2539a.get(interfaceC1152e3);
                if (aVar == null) {
                    throw new C1100h(400, null);
                }
                int iM1357e2 = c1153f.m1357e(aVar);
                abstractC1541b.f4556q = iM1357e2;
                if (iM1357e2 <= 0) {
                    abstractC1541b.f4556q = 10;
                }
                abstractC1541b.f4548i.f4654w = aVar.toString();
            } catch (Exception e7) {
                AbstractC1541b.f4541z.mo2359j(e7);
                if (!(e7 instanceof C1100h)) {
                    throw new C1100h(400, null, e7);
                }
                throw ((C1100h) e7);
            }
        }

        @Override // p065h5.C1109q.a
        /* renamed from: g */
        public void mo874g(InterfaceC1152e interfaceC1152e, int i7, InterfaceC1152e interfaceC1152e2) {
            InterfaceC2016c interfaceC2016c = AbstractC1541b.f4541z;
            if (interfaceC2016c.mo2353d()) {
                interfaceC2016c.mo2351a("Bad request!: " + interfaceC1152e + " " + i7 + " " + interfaceC1152e2, new Object[0]);
            }
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f4541z = C2015b.m2349a(AbstractC1541b.class.getName());
        f4540A = new ThreadLocal<>();
    }

    public AbstractC1541b(InterfaceC1545f interfaceC1545f, InterfaceC1161n interfaceC1161n, C1555p c1555p) {
        super(interfaceC1161n);
        this.f4556q = -2;
        this.f4558s = false;
        this.f4559t = false;
        this.f4560u = false;
        this.f4561v = false;
        this.f4562w = false;
        this.f4563x = false;
        this.f4564y = false;
        String str = C1928t.f5703e;
        this.f4545f = "UTF-8".equals(str) ? new C1113u() : new C1094b(str);
        this.f4543d = interfaceC1545f;
        InterfaceC1096d interfaceC1096d = (InterfaceC1096d) interfaceC1545f;
        this.f4546g = new C1109q(interfaceC1096d.mo891F(), interfaceC1161n, new d(null));
        this.f4547h = new C1101i();
        this.f4551l = new C1101i();
        this.f4548i = new C1553n(this);
        this.f4552m = new C1554o(this);
        C1105m c1105m = new C1105m(interfaceC1096d.mo895t(), interfaceC1161n);
        this.f4550k = c1105m;
        c1105m.f2260s = c1555p.f4675p;
        this.f4544e = c1555p;
    }

    /* renamed from: g */
    public static AbstractC1541b m1735g() {
        return f4540A.get();
    }

    @Override // p073i5.InterfaceC1160m
    /* renamed from: b */
    public boolean mo862b() {
        return ((AbstractC1093a) this.f4550k).m1189i() && (((C1109q) this.f4546g).m1246f(-14) || this.f4563x);
    }

    /* renamed from: f */
    public void m1736f(boolean z6) throws C1100h, C1162o {
        if (!((AbstractC1093a) this.f4550k).m1187g()) {
            InterfaceC1095c interfaceC1095c = this.f4550k;
            C1554o c1554o = this.f4552m;
            ((AbstractC1093a) interfaceC1095c).m1198r(c1554o.f4660b, c1554o.f4661c);
            try {
                if (this.f4559t && this.f4552m.f4660b != 100) {
                    ((AbstractC1093a) this.f4550k).m1197q(false);
                }
                ((C1105m) this.f4550k).mo1182b(this.f4551l, z6);
            } catch (RuntimeException e7) {
                f4541z.mo2356g("header full: " + e7, new Object[0]);
                this.f4552m.m1796d();
                this.f4550k.reset();
                ((AbstractC1093a) this.f4550k).m1198r(500, null);
                ((C1105m) this.f4550k).mo1182b(this.f4551l, true);
                this.f4550k.complete();
                throw new C1100h(500);
            }
        }
        if (z6) {
            this.f4550k.complete();
        }
    }

    /* renamed from: h */
    public int m1737h() {
        return (this.f4543d.mo1722E() && this.f2538a.mo916i() == this.f4543d.mo1726i()) ? this.f4543d.mo1730q() : this.f2538a.mo916i() > 0 ? this.f2538a.mo916i() : this.f4543d.mo1726i();
    }

    /* renamed from: i */
    public PrintWriter m1738i(String str) {
        if (this.f4553n == null) {
            this.f4553n = new b();
        }
        if (this.f4554o == null) {
            this.f4554o = new c(this);
            Objects.requireNonNull(this.f4544e);
            this.f4555p = new a(this, this.f4554o);
        }
        c cVar = this.f4554o;
        Objects.requireNonNull(cVar);
        if (str == null || "ISO-8859-1".equalsIgnoreCase(str)) {
            cVar.f4615f = 1;
        } else if ("UTF-8".equalsIgnoreCase(str)) {
            cVar.f4615f = 2;
        } else {
            cVar.f4615f = 0;
            String str2 = cVar.f4614e.f4610j;
            if (str2 == null || !str2.equalsIgnoreCase(str)) {
                cVar.f4614e.f4611k = null;
            }
        }
        C1551l c1551l = cVar.f4614e;
        c1551l.f4610j = str;
        if (c1551l.f4613m == null) {
            c1551l.f4613m = new C1914f(512);
        }
        return this.f4555p;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01fc A[PHI: r3 r4
      0x01fc: PHI (r3v49 boolean) = (r3v37 boolean), (r3v46 boolean), (r3v61 boolean) binds: [B:138:0x028b, B:104:0x01fa, B:123:0x024e] A[DONT_GENERATE, DONT_INLINE]
      0x01fc: PHI (r4v15 i5.q) = (r4v86 i5.q), (r4v87 i5.q), (r4v88 i5.q) binds: [B:138:0x028b, B:104:0x01fa, B:123:0x024e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x034e A[Catch: all -> 0x0353, TryCatch #14 {all -> 0x0353, blocks: (B:184:0x032c, B:186:0x0334, B:187:0x033d, B:189:0x034e, B:190:0x0352), top: B:269:0x032c }] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x032c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:286:? A[Catch: all -> 0x0353, SYNTHETIC, TRY_LEAVE, TryCatch #14 {all -> 0x0353, blocks: (B:184:0x032c, B:186:0x0334, B:187:0x033d, B:189:0x034e, B:190:0x0352), top: B:269:0x032c }] */
    /* JADX WARN: Removed duplicated region for block: B:287:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:288:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v38, types: [h5.h, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r4v59 */
    /* JADX WARN: Type inference failed for: r4v60 */
    /* JADX WARN: Type inference failed for: r4v63 */
    /* JADX WARN: Type inference failed for: r4v69 */
    /* JADX WARN: Type inference failed for: r4v70 */
    /* JADX WARN: Type inference failed for: r4v71 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v83 */
    /* JADX WARN: Type inference failed for: r4v84 */
    /* JADX WARN: Type inference failed for: r4v85 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r7v8, types: [n5.c] */
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo1739j() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1172
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p113n5.AbstractC1541b.mo1739j():void");
    }

    /* renamed from: k */
    public void m1740k() {
        ((C1109q) this.f4546g).m1249i();
        ((C1109q) this.f4546g).m1250j();
        this.f4547h.m1223b();
        C1553n c1553n = this.f4548i;
        if (c1553n.f4648q == 2) {
            try {
                throw null;
            } catch (Exception e7) {
                C1553n.f4617O.mo2360k(e7);
            }
        }
        c1553n.f4635d = InterfaceC1543d.f4584b;
        C1542c c1542c = c1553n.f4632a;
        synchronized (c1542c) {
            int i7 = c1542c.f4571d;
            if (i7 == 1 || i7 == 6) {
                throw new IllegalStateException(c1542c.m1753k());
            }
            c1542c.f4571d = 0;
            c1542c.f4572e = true;
            c1542c.f4573f = false;
            c1542c.f4574g = false;
            c1542c.m1744b();
            c1542c.f4575h = 30000L;
        }
        c1553n.f4633b = true;
        c1553n.f4647p = false;
        if (c1553n.f4639h != null) {
            throw new IllegalStateException("Request in context!");
        }
        if (c1553n.f4634c != null) {
            c1553n.f4634c.mo896v();
        }
        c1553n.f4637f = null;
        c1553n.f4641j = null;
        C1546g c1546g = c1553n.f4642k;
        if (c1546g != null) {
            c1546g.f4589a = null;
            c1546g.f4592d = 0;
        }
        c1553n.f4643l = false;
        c1553n.f4639h = null;
        c1553n.f4624G = null;
        c1553n.f4649r = null;
        c1553n.f4652u = null;
        c1553n.f4653v = 0;
        c1553n.f4654w = "HTTP/1.1";
        c1553n.f4655x = null;
        c1553n.f4656y = null;
        c1553n.f4619B = null;
        c1553n.f4620C = false;
        c1553n.f4626I = null;
        c1553n.f4627J = null;
        c1553n.f4621D = null;
        c1553n.f4623F = null;
        c1553n.f4622E = "http";
        c1553n.f4625H = null;
        c1553n.f4628K = 0L;
        c1553n.f4629L = null;
        c1553n.f4630M = null;
        ConcurrentMapC1920l<String> concurrentMapC1920l = c1553n.f4636e;
        if (concurrentMapC1920l != null) {
            concurrentMapC1920l.f5650e.clear();
        }
        c1553n.f4650s = null;
        c1553n.f4651t = false;
        c1553n.f4648q = 0;
        c1553n.f4631N = null;
        this.f4550k.reset();
        ((AbstractC1093a) this.f4550k).m1194n();
        this.f4551l.m1223b();
        C1554o c1554o = this.f4552m;
        c1554o.f4660b = 200;
        c1554o.f4661c = null;
        c1554o.f4662d = null;
        c1554o.f4663e = null;
        c1554o.f4664f = null;
        c1554o.f4665g = null;
        c1554o.f4667i = null;
        c1554o.f4666h = 0;
        C1113u c1113u = this.f4545f;
        c1113u.f2378l = 0;
        c1113u.f2377k = 0;
        c1113u.f2376j = 0;
        c1113u.f2375i = 0;
        c1113u.f2374h = 0;
        c1113u.f2372f = 0;
        c1113u.f2371e = 0;
        c1113u.f2370d = 0;
        c1113u.f2369c = 0;
        c1113u.f2367a = C1113u.f2366n;
        c1113u.f2368b = "";
        this.f4554o = null;
        this.f4564y = false;
    }

    @Override // p073i5.InterfaceC1160m
    public void onClose() {
        f4541z.mo2351a("closed {}", this);
    }

    @Override // p073i5.AbstractC1150c
    public String toString() {
        return String.format("%s,g=%s,p=%s,r=%d", super.toString(), this.f4550k, this.f4546g, Integer.valueOf(this.f4542c));
    }
}
