package p154s5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import javax.servlet.FilterChain;
import p006a5.InterfaceC0018d;
import p006a5.InterfaceC0019e;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p009b.C0413b;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p043f.C0986c;
import p065h5.C1117y;
import p097l5.AbstractC1443h;
import p097l5.InterfaceC1440e;
import p113n5.AbstractC1541b;
import p113n5.C1553n;
import p113n5.C1555p;
import p131p5.AbstractC1748h;
import p131p5.C1743c;
import p161t5.C1918j;
import p161t5.C1919k;
import p161t5.C1928t;
import p161t5.ConcurrentMapC1920l;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: ServletHandler.java */
/* renamed from: s5.e */
/* loaded from: classes.dex */
public class C1888e extends AbstractC1748h {

    /* renamed from: D */
    public static final InterfaceC2016c f5504D;

    /* renamed from: E */
    public static final InterfaceC2016c f5505E;

    /* renamed from: A */
    public C1117y f5506A;

    /* renamed from: n */
    public C1887d f5509n;

    /* renamed from: o */
    public C1743c.b f5510o;

    /* renamed from: q */
    public C1885b[] f5512q;

    /* renamed from: t */
    public InterfaceC1440e f5515t;

    /* renamed from: v */
    public C1890g[] f5517v;

    /* renamed from: x */
    public List<C1885b> f5519x;

    /* renamed from: y */
    public ConcurrentMapC1920l<String> f5520y;

    /* renamed from: p */
    public C1884a[] f5511p = new C1884a[0];

    /* renamed from: r */
    public boolean f5513r = true;

    /* renamed from: s */
    public int f5514s = 512;

    /* renamed from: u */
    public C1889f[] f5516u = new C1889f[0];

    /* renamed from: w */
    public final Map<String, C1884a> f5518w = new HashMap();

    /* renamed from: z */
    public final Map<String, C1889f> f5521z = new HashMap();

    /* renamed from: B */
    public final ConcurrentMap<String, FilterChain>[] f5507B = new ConcurrentMap[31];

    /* renamed from: C */
    public final Queue<String>[] f5508C = new Queue[31];

    /* compiled from: ServletHandler.java */
    /* renamed from: s5.e$a */
    public class a implements InterfaceC0019e {

        /* renamed from: a */
        public C1884a f5522a;

        /* renamed from: b */
        public a f5523b;

        /* renamed from: c */
        public C1889f f5524c;

        public a(Object obj, C1889f c1889f) {
            if (C1918j.m2228x(obj) <= 0) {
                this.f5524c = c1889f;
            } else {
                this.f5522a = (C1884a) C1918j.m2225j(obj, 0);
                this.f5523b = C1888e.this.new a(C1918j.m2227q(obj, 0), c1889f);
            }
        }

        @Override // p006a5.InterfaceC0019e
        /* renamed from: a */
        public void mo15a(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w) {
            C1553n c1553n = interfaceC0032r instanceof C1553n ? (C1553n) interfaceC0032r : AbstractC1541b.m1735g().f4548i;
            if (this.f5522a == null) {
                InterfaceC0458c interfaceC0458c = (InterfaceC0458c) interfaceC0032r;
                if (this.f5524c != null) {
                    InterfaceC2016c interfaceC2016c = C1888e.f5504D;
                    if (interfaceC2016c.mo2353d()) {
                        StringBuilder sbM112a = C0413b.m112a("call servlet ");
                        sbM112a.append(this.f5524c);
                        interfaceC2016c.mo2351a(sbM112a.toString(), new Object[0]);
                    }
                    this.f5524c.m2172K(c1553n, interfaceC0032r, interfaceC0037w);
                    return;
                }
                C1888e c1888e = C1888e.this;
                if (c1888e.f4960j != null) {
                    c1888e.m1911S(C1928t.m2264b(interfaceC0458c.mo160B(), interfaceC0458c.mo161G()), c1553n, interfaceC0458c, (InterfaceC0460e) interfaceC0037w);
                    return;
                } else {
                    c1888e.m2166W(interfaceC0458c);
                    return;
                }
            }
            InterfaceC2016c interfaceC2016c2 = C1888e.f5504D;
            if (interfaceC2016c2.mo2353d()) {
                StringBuilder sbM112a2 = C0413b.m112a("call filter ");
                sbM112a2.append(this.f5522a);
                interfaceC2016c2.mo2351a(sbM112a2.toString(), new Object[0]);
            }
            C1884a c1884a = this.f5522a;
            InterfaceC0018d interfaceC0018d = c1884a.f5483n;
            if (c1884a.f5492j) {
                interfaceC0018d.m13a(interfaceC0032r, interfaceC0037w, this.f5523b);
                return;
            }
            if (!c1553n.f4633b) {
                interfaceC0018d.m13a(interfaceC0032r, interfaceC0037w, this.f5523b);
                return;
            }
            try {
                c1553n.f4633b = false;
                interfaceC0018d.m13a(interfaceC0032r, interfaceC0037w, this.f5523b);
            } finally {
                c1553n.f4633b = true;
            }
        }

        public String toString() {
            if (this.f5522a == null) {
                C1889f c1889f = this.f5524c;
                return c1889f != null ? c1889f.f5493k : "null";
            }
            return this.f5522a + "->" + this.f5523b.toString();
        }
    }

    /* compiled from: ServletHandler.java */
    /* renamed from: s5.e$b */
    public class b implements InterfaceC0019e {

        /* renamed from: a */
        public final C1553n f5526a;

        /* renamed from: b */
        public final Object f5527b;

        /* renamed from: c */
        public final C1889f f5528c;

        /* renamed from: d */
        public int f5529d = 0;

        public b(C1553n c1553n, Object obj, C1889f c1889f) {
            this.f5526a = c1553n;
            this.f5527b = obj;
            this.f5528c = c1889f;
        }

        @Override // p006a5.InterfaceC0019e
        /* renamed from: a */
        public void mo15a(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w) {
            InterfaceC2016c interfaceC2016c = C1888e.f5504D;
            if (interfaceC2016c.mo2353d()) {
                StringBuilder sbM112a = C0413b.m112a("doFilter ");
                sbM112a.append(this.f5529d);
                interfaceC2016c.mo2351a(sbM112a.toString(), new Object[0]);
            }
            if (this.f5529d >= C1918j.m2228x(this.f5527b)) {
                InterfaceC0458c interfaceC0458c = (InterfaceC0458c) interfaceC0032r;
                if (this.f5528c != null) {
                    if (interfaceC2016c.mo2353d()) {
                        StringBuilder sbM112a2 = C0413b.m112a("call servlet ");
                        sbM112a2.append(this.f5528c);
                        interfaceC2016c.mo2351a(sbM112a2.toString(), new Object[0]);
                    }
                    this.f5528c.m2172K(this.f5526a, interfaceC0032r, interfaceC0037w);
                    return;
                }
                C1888e c1888e = C1888e.this;
                if (c1888e.f4960j != null) {
                    C1888e.this.m1911S(C1928t.m2264b(interfaceC0458c.mo160B(), interfaceC0458c.mo161G()), interfaceC0032r instanceof C1553n ? (C1553n) interfaceC0032r : AbstractC1541b.m1735g().f4548i, interfaceC0458c, (InterfaceC0460e) interfaceC0037w);
                    return;
                } else {
                    c1888e.m2166W(interfaceC0458c);
                    return;
                }
            }
            Object obj = this.f5527b;
            int i7 = this.f5529d;
            this.f5529d = i7 + 1;
            C1884a c1884a = (C1884a) C1918j.m2225j(obj, i7);
            if (interfaceC2016c.mo2353d()) {
                interfaceC2016c.mo2351a("call filter " + c1884a, new Object[0]);
            }
            InterfaceC0018d interfaceC0018d = c1884a.f5483n;
            if (!c1884a.f5492j) {
                C1553n c1553n = this.f5526a;
                if (c1553n.f4633b) {
                    try {
                        c1553n.f4633b = false;
                        interfaceC0018d.m13a(interfaceC0032r, interfaceC0037w, this);
                        return;
                    } finally {
                        this.f5526a.f4633b = true;
                    }
                }
            }
            interfaceC0018d.m13a(interfaceC0032r, interfaceC0037w, this);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i7 = 0; i7 < C1918j.m2228x(this.f5527b); i7++) {
                sb.append(C1918j.m2225j(this.f5527b, i7).toString());
                sb.append("->");
            }
            sb.append(this.f5528c);
            return sb.toString();
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        InterfaceC2016c interfaceC2016cM2349a = C2015b.m2349a(C1888e.class.getName());
        f5504D = interfaceC2016cM2349a;
        f5505E = interfaceC2016cM2349a.mo2348b("unhandled");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x013b A[Catch: all -> 0x015c, TryCatch #7 {all -> 0x015c, blocks: (B:244:0x0137, B:246:0x013b, B:248:0x013f, B:250:0x0143, B:252:0x014b, B:264:0x019d, B:266:0x01ad, B:268:0x01b1, B:270:0x01b8, B:271:0x01be, B:272:0x01c4, B:273:0x01ca, B:255:0x015f, B:257:0x0163, B:260:0x0168, B:262:0x018e, B:263:0x0196, B:280:0x01eb, B:281:0x01ee, B:282:0x01ef, B:283:0x01f2, B:284:0x01f3, B:285:0x01f6), top: B:299:0x0137 }] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x01f3 A[Catch: all -> 0x015c, TryCatch #7 {all -> 0x015c, blocks: (B:244:0x0137, B:246:0x013b, B:248:0x013f, B:250:0x0143, B:252:0x014b, B:264:0x019d, B:266:0x01ad, B:268:0x01b1, B:270:0x01b8, B:271:0x01be, B:272:0x01c4, B:273:0x01ca, B:255:0x015f, B:257:0x0163, B:260:0x0168, B:262:0x018e, B:263:0x0196, B:280:0x01eb, B:281:0x01ee, B:282:0x01ef, B:283:0x01f2, B:284:0x01f3, B:285:0x01f6), top: B:299:0x0137 }] */
    /* JADX WARN: Removed duplicated region for block: B:292:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0203  */
    /* JADX WARN: Type inference failed for: r0v19, types: [v5.c] */
    /* JADX WARN: Type inference failed for: r19v0, types: [a5.r, b5.c, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r5v6, types: [v5.c] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.Object, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r8v4 */
    @Override // p131p5.AbstractC1748h
    /* renamed from: Q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo1895Q(java.lang.String r17, p113n5.C1553n r18, p015b5.InterfaceC0458c r19, p015b5.InterfaceC0460e r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 531
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p154s5.C1888e.mo1895Q(java.lang.String, n5.n, b5.c, b5.e):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00bb  */
    @Override // p131p5.AbstractC1748h
    /* renamed from: R */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo1896R(java.lang.String r17, p113n5.C1553n r18, p015b5.InterfaceC0458c r19, p015b5.InterfaceC0460e r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p154s5.C1888e.mo1896R(java.lang.String, n5.n, b5.c, b5.e):void");
    }

    /* renamed from: T */
    public InterfaceC0019e m2163T(C1553n c1553n, String str, C1889f c1889f) {
        Object objM2222b;
        ConcurrentMap<String, FilterChain>[] concurrentMapArr;
        InterfaceC0019e interfaceC0019e;
        String str2 = str == null ? c1889f.f5493k : str;
        int i7 = c1553n.f4644m;
        int iM950c = C0986c.m950c(i7);
        int i8 = 2;
        if (iM950c != 0) {
            if (iM950c == 1) {
                i8 = 4;
            } else if (iM950c == 2) {
                i8 = 1;
            } else if (iM950c == 3) {
                i8 = 16;
            } else {
                if (iM950c != 4) {
                    throw new IllegalArgumentException(C0986c.m952e(i7));
                }
                i8 = 8;
            }
        }
        if (this.f5513r && (concurrentMapArr = this.f5507B) != null && (interfaceC0019e = concurrentMapArr[i8].get(str2)) != null) {
            return interfaceC0019e;
        }
        if (str != null && this.f5519x != null) {
            for (int i9 = 0; i9 < this.f5519x.size(); i9++) {
                if (this.f5519x.get(i9).m2152a(i8)) {
                    throw null;
                }
            }
        }
        ConcurrentMapC1920l<String> concurrentMapC1920l = this.f5520y;
        if (concurrentMapC1920l == null || concurrentMapC1920l.size() <= 0 || this.f5520y.size() <= 0) {
            objM2222b = null;
        } else {
            Object obj = this.f5520y.get(c1889f.f5493k);
            objM2222b = null;
            for (int i10 = 0; i10 < C1918j.m2228x(obj); i10++) {
                C1885b c1885b = (C1885b) C1918j.m2225j(obj, i10);
                if (c1885b.m2152a(i8)) {
                    objM2222b = C1918j.m2222b(objM2222b, c1885b.f5485b);
                }
            }
            Object obj2 = this.f5520y.get("*");
            for (int i11 = 0; i11 < C1918j.m2228x(obj2); i11++) {
                C1885b c1885b2 = (C1885b) C1918j.m2225j(obj2, i11);
                if (c1885b2.m2152a(i8)) {
                    objM2222b = C1918j.m2222b(objM2222b, c1885b2.f5485b);
                }
            }
        }
        if (objM2222b == null) {
            return null;
        }
        if (!this.f5513r) {
            if (C1918j.m2228x(objM2222b) > 0) {
                return new b(c1553n, objM2222b, c1889f);
            }
            return null;
        }
        FilterChain aVar = C1918j.m2228x(objM2222b) > 0 ? new a(objM2222b, c1889f) : null;
        ConcurrentMap<String, FilterChain> concurrentMap = this.f5507B[i8];
        Queue<String> queue = this.f5508C[i8];
        while (true) {
            if (this.f5514s <= 0 || concurrentMap.size() < this.f5514s) {
                break;
            }
            String strPoll = queue.poll();
            if (strPoll == null) {
                concurrentMap.clear();
                break;
            }
            concurrentMap.remove(strPoll);
        }
        concurrentMap.put(str2, aVar);
        queue.add(str2);
        return aVar;
    }

    /* renamed from: U */
    public void m2164U() {
        C1919k c1919k = new C1919k();
        if (this.f5511p != null) {
            int i7 = 0;
            while (true) {
                C1884a[] c1884aArr = this.f5511p;
                if (i7 >= c1884aArr.length) {
                    break;
                }
                c1884aArr[i7].start();
                i7++;
            }
        }
        C1889f[] c1889fArr = this.f5516u;
        if (c1889fArr != null) {
            C1889f[] c1889fArr2 = (C1889f[]) c1889fArr.clone();
            Arrays.sort(c1889fArr2);
            for (int i8 = 0; i8 < c1889fArr2.length; i8++) {
                try {
                    if (c1889fArr2[i8].f5490h == null) {
                        Objects.requireNonNull(c1889fArr2[i8]);
                    }
                    c1889fArr2[i8].start();
                } catch (Throwable th) {
                    f5504D.mo2355f("EXCEPTION ", th);
                    c1919k.m2229a(th);
                }
            }
            c1919k.m2230b();
        }
    }

    /* renamed from: V */
    public void m2165V() {
        Queue<String>[] queueArr = this.f5508C;
        if (queueArr[1] != null) {
            queueArr[1].clear();
            this.f5508C[2].clear();
            this.f5508C[4].clear();
            this.f5508C[8].clear();
            this.f5508C[16].clear();
            this.f5507B[1].clear();
            this.f5507B[2].clear();
            this.f5507B[4].clear();
            this.f5507B[8].clear();
            this.f5507B[16].clear();
        }
    }

    /* renamed from: W */
    public void m2166W(InterfaceC0458c interfaceC0458c) {
        InterfaceC2016c interfaceC2016c = f5504D;
        if (interfaceC2016c.mo2353d()) {
            StringBuilder sbM112a = C0413b.m112a("Not Found ");
            sbM112a.append(interfaceC0458c.mo167U());
            interfaceC2016c.mo2351a(sbM112a.toString(), new Object[0]);
        }
    }

    /* renamed from: X */
    public synchronized void m2167X(C1889f[] c1889fArr) {
        C1555p c1555p = this.f4928h;
        if (c1555p != null) {
            c1555p.f4670k.m2316h(this, this.f5516u, c1889fArr, "servlet", true);
        }
        this.f5516u = c1889fArr;
        m2169Z();
        m2165V();
    }

    /* renamed from: Y */
    public synchronized void m2168Y() {
        if (this.f5512q != null) {
            this.f5519x = new ArrayList();
            this.f5520y = new ConcurrentMapC1920l<>();
            int i7 = 0;
            while (true) {
                C1885b[] c1885bArr = this.f5512q;
                if (i7 >= c1885bArr.length) {
                    break;
                }
                C1884a c1884a = this.f5518w.get(c1885bArr[i7].f5484a);
                if (c1884a == null) {
                    throw new IllegalStateException("No filter named " + this.f5512q[i7].f5484a);
                }
                C1885b[] c1885bArr2 = this.f5512q;
                C1885b c1885b = c1885bArr2[i7];
                c1885b.f5485b = c1884a;
                c1885b.f5484a = c1884a.f5493k;
                Objects.requireNonNull(c1885bArr2[i7]);
                Objects.requireNonNull(this.f5512q[i7]);
                i7++;
            }
        } else {
            this.f5519x = null;
            this.f5520y = null;
        }
        if (this.f5517v == null || this.f5521z == null) {
            this.f5506A = null;
        } else {
            C1117y c1117y = new C1117y();
            int i8 = 0;
            while (true) {
                C1890g[] c1890gArr = this.f5517v;
                if (i8 >= c1890gArr.length) {
                    this.f5506A = c1117y;
                    break;
                }
                C1889f c1889f = this.f5521z.get(c1890gArr[i8].f5543b);
                if (c1889f == null) {
                    throw new IllegalStateException("No such servlet: " + this.f5517v[i8].f5543b);
                }
                if (c1889f.f5537s) {
                    C1890g[] c1890gArr2 = this.f5517v;
                    if (c1890gArr2[i8].f5542a != null) {
                        String[] strArr = c1890gArr2[i8].f5542a;
                        for (int i9 = 0; i9 < strArr.length; i9++) {
                            if (strArr[i9] != null) {
                                c1117y.put(strArr[i9], c1889f);
                            }
                        }
                    }
                }
                i8++;
            }
        }
        ConcurrentMap<String, FilterChain>[] concurrentMapArr = this.f5507B;
        if (concurrentMapArr != null) {
            int length = concurrentMapArr.length;
            while (true) {
                int i10 = length - 1;
                if (length <= 0) {
                    break;
                }
                ConcurrentMap<String, FilterChain>[] concurrentMapArr2 = this.f5507B;
                if (concurrentMapArr2[i10] != null) {
                    concurrentMapArr2[i10].clear();
                }
                length = i10;
            }
        }
        InterfaceC2016c interfaceC2016c = f5504D;
        if (interfaceC2016c.mo2353d()) {
            interfaceC2016c.mo2351a("filterNameMap=" + this.f5518w, new Object[0]);
            interfaceC2016c.mo2351a("pathFilters=" + this.f5519x, new Object[0]);
            interfaceC2016c.mo2351a("servletFilterMap=" + this.f5520y, new Object[0]);
            interfaceC2016c.mo2351a("servletPathMap=" + this.f5506A, new Object[0]);
            interfaceC2016c.mo2351a("servletNameMap=" + this.f5521z, new Object[0]);
        }
        try {
            C1887d c1887d = this.f5509n;
            if ((c1887d != null && c1887d.isStarted()) || (this.f5509n == null && isStarted())) {
                m2164U();
            }
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }

    /* renamed from: Z */
    public synchronized void m2169Z() {
        this.f5518w.clear();
        int i7 = 0;
        if (this.f5511p != null) {
            int i8 = 0;
            while (true) {
                C1884a[] c1884aArr = this.f5511p;
                if (i8 >= c1884aArr.length) {
                    break;
                }
                this.f5518w.put(c1884aArr[i8].f5493k, c1884aArr[i8]);
                this.f5511p[i8].f5494l = this;
                i8++;
            }
        }
        this.f5521z.clear();
        if (this.f5516u != null) {
            while (true) {
                C1889f[] c1889fArr = this.f5516u;
                if (i7 >= c1889fArr.length) {
                    break;
                }
                this.f5521z.put(c1889fArr[i7].f5493k, c1889fArr[i7]);
                this.f5516u[i7].f5494l = this;
                i7++;
            }
        }
    }

    @Override // p131p5.AbstractC1748h, p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    public synchronized void doStart() {
        AbstractC1443h abstractC1443h;
        C1743c.b bVarM1894W = C1743c.m1894W();
        this.f5510o = bVarM1894W;
        C1887d c1887d = (C1887d) (bVarM1894W == null ? null : C1743c.this);
        this.f5509n = c1887d;
        if (c1887d != null && (abstractC1443h = (AbstractC1443h) c1887d.m1893O(AbstractC1443h.class)) != null) {
            this.f5515t = abstractC1443h.f4196q;
        }
        m2169Z();
        m2168Y();
        if (this.f5513r) {
            this.f5507B[1] = new ConcurrentHashMap();
            this.f5507B[2] = new ConcurrentHashMap();
            this.f5507B[4] = new ConcurrentHashMap();
            this.f5507B[8] = new ConcurrentHashMap();
            this.f5507B[16] = new ConcurrentHashMap();
            this.f5508C[1] = new ConcurrentLinkedQueue();
            this.f5508C[2] = new ConcurrentLinkedQueue();
            this.f5508C[4] = new ConcurrentLinkedQueue();
            this.f5508C[8] = new ConcurrentLinkedQueue();
            this.f5508C[16] = new ConcurrentLinkedQueue();
        }
        super.doStart();
        C1887d c1887d2 = this.f5509n;
        if (c1887d2 == null || !(c1887d2 instanceof C1887d)) {
            m2164U();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x00de A[Catch: all -> 0x00fb, EDGE_INSN: B:133:0x00de->B:114:0x00de BREAK  A[LOOP:2: B:98:0x008d->B:113:0x00dc], TRY_LEAVE, TryCatch #1 {, blocks: (B:73:0x0001, B:75:0x0014, B:78:0x0019, B:82:0x0029, B:84:0x0031, B:85:0x0041, B:87:0x0047, B:89:0x005b, B:90:0x005f, B:81:0x0022, B:92:0x0066, B:94:0x007c, B:95:0x007d, B:97:0x008c, B:100:0x0091, B:104:0x00a1, B:106:0x00a9, B:107:0x00b9, B:109:0x00bf, B:111:0x00d3, B:112:0x00d7, B:103:0x009a, B:114:0x00de), top: B:122:0x0001, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x007c A[Catch: all -> 0x00fb, TryCatch #1 {, blocks: (B:73:0x0001, B:75:0x0014, B:78:0x0019, B:82:0x0029, B:84:0x0031, B:85:0x0041, B:87:0x0047, B:89:0x005b, B:90:0x005f, B:81:0x0022, B:92:0x0066, B:94:0x007c, B:95:0x007d, B:97:0x008c, B:100:0x0091, B:104:0x00a1, B:106:0x00a9, B:107:0x00b9, B:109:0x00bf, B:111:0x00d3, B:112:0x00d7, B:103:0x009a, B:114:0x00de), top: B:122:0x0001, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x008c A[Catch: all -> 0x00fb, TRY_LEAVE, TryCatch #1 {, blocks: (B:73:0x0001, B:75:0x0014, B:78:0x0019, B:82:0x0029, B:84:0x0031, B:85:0x0041, B:87:0x0047, B:89:0x005b, B:90:0x005f, B:81:0x0022, B:92:0x0066, B:94:0x007c, B:95:0x007d, B:97:0x008c, B:100:0x0091, B:104:0x00a1, B:106:0x00a9, B:107:0x00b9, B:109:0x00bf, B:111:0x00d3, B:112:0x00d7, B:103:0x009a, B:114:0x00de), top: B:122:0x0001, inners: #0, #2 }] */
    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p168u5.C1981b, p168u5.AbstractC1980a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void doStop() {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p154s5.C1888e.doStop():void");
    }

    @Override // p131p5.C1747g, p131p5.AbstractC1741a, p113n5.InterfaceC1548i
    /* renamed from: h */
    public void mo1772h(C1555p c1555p) {
        C1555p c1555p2 = this.f4928h;
        if (c1555p2 != null && c1555p2 != c1555p) {
            c1555p2.f4670k.m2316h(this, this.f5511p, null, "filter", true);
            this.f4928h.f4670k.m2316h(this, this.f5512q, null, "filterMapping", true);
            this.f4928h.f4670k.m2316h(this, this.f5516u, null, "servlet", true);
            this.f4928h.f4670k.m2316h(this, this.f5517v, null, "servletMapping", true);
        }
        super.mo1772h(c1555p);
        if (c1555p == null || c1555p2 == c1555p) {
            return;
        }
        c1555p.f4670k.m2316h(this, null, this.f5511p, "filter", true);
        c1555p.f4670k.m2316h(this, null, this.f5512q, "filterMapping", true);
        c1555p.f4670k.m2316h(this, null, this.f5516u, "servlet", true);
        c1555p.f4670k.m2316h(this, null, this.f5517v, "servletMapping", true);
    }
}
