package p154s5;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Properties;
import java.util.Stack;
import p006a5.C0015a0;
import p006a5.C0029o;
import p006a5.InterfaceC0024j;
import p006a5.InterfaceC0025k;
import p006a5.InterfaceC0031q;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p006a5.InterfaceC0040z;
import p009b.C0413b;
import p097l5.InterfaceC1440e;
import p113n5.InterfaceC1561v;
import p131p5.C1743c;
import p154s5.C1887d;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: ServletHolder.java */
/* renamed from: s5.f */
/* loaded from: classes.dex */
public class C1889f extends C1886c<InterfaceC0024j> implements InterfaceC1561v.a, Comparable {

    /* renamed from: u */
    public static final InterfaceC2016c f5531u;

    /* renamed from: n */
    public InterfaceC1440e f5532n;

    /* renamed from: o */
    public InterfaceC0031q f5533o;

    /* renamed from: p */
    public transient InterfaceC0024j f5534p;

    /* renamed from: q */
    public transient b f5535q;

    /* renamed from: r */
    public transient long f5536r;

    /* renamed from: s */
    public transient boolean f5537s;

    /* renamed from: t */
    public transient C0015a0 f5538t;

    /* compiled from: ServletHolder.java */
    /* renamed from: s5.f$a */
    public class a extends C0015a0 {
        public a(C1889f c1889f, String str, int i7, Throwable th) {
            super(str, i7);
            initCause(th);
        }
    }

    /* compiled from: ServletHolder.java */
    /* renamed from: s5.f$b */
    public class b extends C1886c<InterfaceC0024j>.a implements InterfaceC0025k {
        public b() {
            super();
        }

        @Override // p006a5.InterfaceC0025k
        public String getServletName() {
            return C1889f.this.f5493k;
        }
    }

    /* compiled from: ServletHolder.java */
    /* renamed from: s5.f$c */
    public class c extends C1886c<InterfaceC0024j>.b implements InterfaceC0031q {
        public c(C1889f c1889f) {
            super(c1889f);
        }
    }

    /* compiled from: ServletHolder.java */
    /* renamed from: s5.f$d */
    public class d implements InterfaceC0024j {

        /* renamed from: e */
        public Stack<InterfaceC0024j> f5540e = new Stack<>();

        public d(a aVar) {
        }

        @Override // p006a5.InterfaceC0024j
        public void destroy() {
            synchronized (this) {
                while (this.f5540e.size() > 0) {
                    try {
                        this.f5540e.pop().destroy();
                    } catch (Exception e7) {
                        C1889f.f5531u.mo2358i(e7);
                    }
                }
            }
        }

        @Override // p006a5.InterfaceC0024j
        public void init(InterfaceC0025k interfaceC0025k) {
            synchronized (this) {
                if (this.f5540e.size() == 0) {
                    try {
                        InterfaceC0024j interfaceC0024jM2179R = C1889f.this.m2179R();
                        interfaceC0024jM2179R.init(interfaceC0025k);
                        this.f5540e.push(interfaceC0024jM2179R);
                    } catch (C0029o e7) {
                        throw e7;
                    } catch (Exception e8) {
                        throw new C0029o(e8);
                    }
                }
            }
        }

        @Override // p006a5.InterfaceC0024j
        public void service(InterfaceC0032r interfaceC0032r, InterfaceC0037w interfaceC0037w) {
            InterfaceC0024j interfaceC0024jM2179R;
            synchronized (this) {
                if (this.f5540e.size() > 0) {
                    interfaceC0024jM2179R = this.f5540e.pop();
                } else {
                    try {
                        interfaceC0024jM2179R = C1889f.this.m2179R();
                        interfaceC0024jM2179R.init(C1889f.this.f5535q);
                    } catch (C0029o e7) {
                        throw e7;
                    } catch (Exception e8) {
                        throw new C0029o(e8);
                    }
                }
            }
            try {
                interfaceC0024jM2179R.service(interfaceC0032r, interfaceC0037w);
                synchronized (this) {
                    this.f5540e.push(interfaceC0024jM2179R);
                }
            } catch (Throwable th) {
                synchronized (this) {
                    this.f5540e.push(interfaceC0024jM2179R);
                    throw th;
                }
            }
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f5531u = C2015b.m2349a(C1889f.class.getName());
        Collections.emptyMap();
    }

    public C1889f() {
        super(1);
        this.f5537s = true;
    }

    /* renamed from: H */
    public void m2170H() throws C0015a0 {
        Class<? extends T> cls = this.f5488f;
        if (cls == 0 || !InterfaceC0024j.class.isAssignableFrom(cls)) {
            StringBuilder sbM112a = C0413b.m112a("Servlet ");
            sbM112a.append(this.f5488f);
            sbM112a.append(" is not a javax.servlet.Servlet");
            throw new C0015a0(sbM112a.toString());
        }
    }

    /* renamed from: J */
    public void m2171J(Object obj) {
        if (obj == null) {
            return;
        }
        InterfaceC0024j interfaceC0024j = (InterfaceC0024j) obj;
        C1887d c1887d = this.f5494l.f5509n;
        if (c1887d != null) {
            Iterator<C1887d.b> it = c1887d.f5496J.iterator();
            while (it.hasNext()) {
                it.next().m2161e(interfaceC0024j);
            }
        }
        interfaceC0024j.destroy();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x003e A[Catch: all -> 0x0076, a0 -> 0x0078, TryCatch #1 {a0 -> 0x0078, blocks: (B:26:0x003a, B:28:0x003e, B:30:0x0044, B:32:0x004c, B:33:0x0050, B:35:0x0054, B:36:0x0057, B:38:0x005b, B:39:0x0062), top: B:67:0x003a, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0054 A[Catch: all -> 0x0076, a0 -> 0x0078, TryCatch #1 {a0 -> 0x0078, blocks: (B:26:0x003a, B:28:0x003e, B:30:0x0044, B:32:0x004c, B:33:0x0050, B:35:0x0054, B:36:0x0057, B:38:0x005b, B:39:0x0062), top: B:67:0x003a, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x005b A[Catch: all -> 0x0076, a0 -> 0x0078, TryCatch #1 {a0 -> 0x0078, blocks: (B:26:0x003a, B:28:0x003e, B:30:0x0044, B:32:0x004c, B:33:0x0050, B:35:0x0054, B:36:0x0057, B:38:0x005b, B:39:0x0062), top: B:67:0x003a, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /* renamed from: K */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m2172K(p113n5.C1553n r9, p006a5.InterfaceC0032r r10, p006a5.InterfaceC0037w r11) {
        /*
            r8 = this;
            java.lang.Class<? extends T> r0 = r8.f5488f
            if (r0 == 0) goto Lb8
            monitor-enter(r8)
            boolean r0 = r8.isStarted()     // Catch: java.lang.Throwable -> Lb5
            if (r0 == 0) goto Lac
            r0 = 0
            monitor-enter(r8)     // Catch: java.lang.Throwable -> Lb5
            long r2 = r8.f5536r     // Catch: java.lang.Throwable -> La9
            r4 = 0
            int r5 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r5 == 0) goto L2b
            if (r5 < 0) goto L28
            if (r5 <= 0) goto L23
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> La9
            long r5 = r8.f5536r     // Catch: java.lang.Throwable -> La9
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 < 0) goto L28
        L23:
            r8.f5536r = r0     // Catch: java.lang.Throwable -> La9
            r8.f5538t = r4     // Catch: java.lang.Throwable -> La9
            goto L2b
        L28:
            a5.a0 r9 = r8.f5538t     // Catch: java.lang.Throwable -> La9
            throw r9     // Catch: java.lang.Throwable -> La9
        L2b:
            a5.j r0 = r8.f5534p     // Catch: java.lang.Throwable -> La9
            if (r0 != 0) goto L32
            r8.m2175N()     // Catch: java.lang.Throwable -> La9
        L32:
            a5.j r0 = r8.f5534p     // Catch: java.lang.Throwable -> La9
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb5
            if (r0 == 0) goto L90
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb5
            boolean r1 = r9.f4633b
            l5.e r2 = r8.f5532n     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            if (r2 == 0) goto L50
            n5.d r3 = r9.f4635d     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            boolean r5 = r3 instanceof p113n5.InterfaceC1543d.g     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            if (r5 == 0) goto L4b
            n5.d$g r3 = (p113n5.InterfaceC1543d.g) r3     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            n5.v r3 = r3.mo1632h()     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            goto L4c
        L4b:
            r3 = r4
        L4c:
            java.lang.Object r4 = r2.m1619d(r3, r4)     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
        L50:
            boolean r2 = r8.f5492j     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            if (r2 != 0) goto L57
            r2 = 0
            r9.f4633b = r2     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
        L57:
            a5.q r2 = r8.f5533o     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            if (r2 != 0) goto L62
            s5.f$c r2 = new s5.f$c     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            r2.<init>(r8)     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            r8.f5533o = r2     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
        L62:
            a5.q r2 = r8.f5533o     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            s5.f$c r2 = (p154s5.C1889f.c) r2     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            java.util.Objects.requireNonNull(r2)     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            r0.service(r10, r11)     // Catch: java.lang.Throwable -> L76 p006a5.C0015a0 -> L78
            r9.f4633b = r1
            l5.e r9 = r8.f5532n
            if (r9 == 0) goto L75
            r9.m1616a(r4)
        L75:
            return
        L76:
            r11 = move-exception
            goto L7f
        L78:
            r11 = move-exception
            r8.m2177P(r11)     // Catch: java.lang.Throwable -> L76
            a5.a0 r11 = r8.f5538t     // Catch: java.lang.Throwable -> L76
            throw r11     // Catch: java.lang.Throwable -> L76
        L7f:
            r9.f4633b = r1
            l5.e r9 = r8.f5532n
            if (r9 == 0) goto L88
            r9.m1616a(r4)
        L88:
            java.lang.String r9 = "javax.servlet.error.servlet_name"
            java.lang.String r0 = r8.f5493k
            r10.mo27b(r9, r0)
            throw r11
        L90:
            a5.a0 r9 = new a5.a0     // Catch: java.lang.Throwable -> Lb5
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb5
            r10.<init>()     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r11 = "Could not instantiate "
            r10.append(r11)     // Catch: java.lang.Throwable -> Lb5
            java.lang.Class<? extends T> r11 = r8.f5488f     // Catch: java.lang.Throwable -> Lb5
            r10.append(r11)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> Lb5
            r9.<init>(r10)     // Catch: java.lang.Throwable -> Lb5
            throw r9     // Catch: java.lang.Throwable -> Lb5
        La9:
            r9 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb5
            throw r9     // Catch: java.lang.Throwable -> Lb5
        Lac:
            a5.a0 r9 = new a5.a0     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r10 = "Servlet not initialized"
            r11 = -1
            r9.<init>(r10, r11)     // Catch: java.lang.Throwable -> Lb5
            throw r9     // Catch: java.lang.Throwable -> Lb5
        Lb5:
            r9 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb5
            throw r9
        Lb8:
            a5.a0 r9 = new a5.a0
            java.lang.String r10 = "Servlet Not Initialized"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p154s5.C1889f.m2172K(n5.n, a5.r, a5.w):void");
    }

    /* renamed from: L */
    public void m2173L() {
        C1743c c1743c = C1743c.this;
        Objects.requireNonNull(c1743c);
        c1743c.mo893b("org.apache.catalina.jsp_classpath", null);
        throw null;
    }

    /* renamed from: M */
    public void m2174M() {
        if (this.f5533o == null) {
            this.f5533o = new c(this);
        }
        Objects.requireNonNull((c) this.f5533o);
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX WARN: Failed to calculate best type for var: r0v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r0v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r0v1 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r0v1 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r0v3 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r2v3 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyInvokeTypes(TypeUpdate.java:390)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.invokeListener(TypeUpdate.java:355)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r2v3 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyInvokeTypes(TypeUpdate.java:390)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.invokeListener(TypeUpdate.java:355)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x007d: MOVE (r4 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:48:0x007d */
    /* renamed from: N */
    public final void m2175N() throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            a5.j r1 = r5.f5534p     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
            if (r1 != 0) goto Lb
            a5.j r1 = r5.m2179R()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
            r5.f5534p = r1     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
        Lb:
            s5.f$b r1 = r5.f5535q     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
            if (r1 != 0) goto L16
            s5.f$b r1 = new s5.f$b     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
            r1.<init>()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
            r5.f5535q = r1     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
        L16:
            l5.e r1 = r5.f5532n     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
            if (r1 == 0) goto L23
            n5.v r2 = r1.m1617b()     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
            java.lang.Object r1 = r1.m1619d(r2, r0)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L48 p006a5.C0029o -> L5a p006a5.C0015a0 -> L71
            goto L24
        L23:
            r1 = r0
        L24:
            boolean r2 = r5.m2176O()     // Catch: java.lang.Exception -> L40 p006a5.C0029o -> L42 p006a5.C0015a0 -> L44 java.lang.Throwable -> L7c
            if (r2 != 0) goto L3c
            r5.m2174M()     // Catch: java.lang.Exception -> L40 p006a5.C0029o -> L42 p006a5.C0015a0 -> L44 java.lang.Throwable -> L7c
            a5.j r2 = r5.f5534p     // Catch: java.lang.Exception -> L40 p006a5.C0029o -> L42 p006a5.C0015a0 -> L44 java.lang.Throwable -> L7c
            s5.f$b r3 = r5.f5535q     // Catch: java.lang.Exception -> L40 p006a5.C0029o -> L42 p006a5.C0015a0 -> L44 java.lang.Throwable -> L7c
            r2.init(r3)     // Catch: java.lang.Exception -> L40 p006a5.C0029o -> L42 p006a5.C0015a0 -> L44 java.lang.Throwable -> L7c
            l5.e r0 = r5.f5532n
            if (r0 == 0) goto L3b
            r0.m1616a(r1)
        L3b:
            return
        L3c:
            r5.m2173L()     // Catch: java.lang.Exception -> L40 p006a5.C0029o -> L42 p006a5.C0015a0 -> L44 java.lang.Throwable -> L7c
            throw r0     // Catch: java.lang.Exception -> L40 p006a5.C0029o -> L42 p006a5.C0015a0 -> L44 java.lang.Throwable -> L7c
        L40:
            r2 = move-exception
            goto L4b
        L42:
            r2 = move-exception
            goto L5d
        L44:
            r2 = move-exception
            goto L74
        L46:
            r1 = move-exception
            goto L80
        L48:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L4b:
            r5.m2178Q(r2)     // Catch: java.lang.Throwable -> L7c
            r5.f5534p = r0     // Catch: java.lang.Throwable -> L7c
            r5.f5535q = r0     // Catch: java.lang.Throwable -> L7c
            a5.o r0 = new a5.o     // Catch: java.lang.Throwable -> L7c
            java.lang.String r3 = r5.f5493k     // Catch: java.lang.Throwable -> L7c
            r0.<init>(r3, r2)     // Catch: java.lang.Throwable -> L7c
            throw r0     // Catch: java.lang.Throwable -> L7c
        L5a:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L5d:
            java.lang.Throwable r3 = r2.getCause()     // Catch: java.lang.Throwable -> L7c
            if (r3 != 0) goto L65
            r3 = r2
            goto L69
        L65:
            java.lang.Throwable r3 = r2.getCause()     // Catch: java.lang.Throwable -> L7c
        L69:
            r5.m2178Q(r3)     // Catch: java.lang.Throwable -> L7c
            r5.f5534p = r0     // Catch: java.lang.Throwable -> L7c
            r5.f5535q = r0     // Catch: java.lang.Throwable -> L7c
            throw r2     // Catch: java.lang.Throwable -> L7c
        L71:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L74:
            r5.m2177P(r2)     // Catch: java.lang.Throwable -> L7c
            r5.f5534p = r0     // Catch: java.lang.Throwable -> L7c
            r5.f5535q = r0     // Catch: java.lang.Throwable -> L7c
            throw r2     // Catch: java.lang.Throwable -> L7c
        L7c:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L80:
            l5.e r2 = r5.f5532n
            if (r2 == 0) goto L87
            r2.m1616a(r0)
        L87:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p154s5.C1889f.m2175N():void");
    }

    /* renamed from: O */
    public final boolean m2176O() {
        InterfaceC0024j interfaceC0024j = this.f5534p;
        boolean zEquals = false;
        if (interfaceC0024j == null) {
            return false;
        }
        for (Class<?> superclass = interfaceC0024j.getClass(); superclass != null && !zEquals; superclass = superclass.getSuperclass()) {
            zEquals = "org.apache.jasper.servlet.JspServlet".equals(superclass.getName());
        }
        return zEquals;
    }

    /* renamed from: P */
    public final void m2177P(C0015a0 c0015a0) {
        if (this.f5538t != c0015a0 || this.f5536r == 0) {
            C1743c.this.f4946v.mo2354e("unavailable", c0015a0);
            this.f5538t = c0015a0;
            this.f5536r = -1L;
            boolean z6 = c0015a0.f25f;
            if (z6) {
                this.f5536r = -1L;
                return;
            }
            if ((z6 ? -1 : c0015a0.f26g) <= 0) {
                this.f5536r = System.currentTimeMillis() + 5000;
                return;
            }
            this.f5536r = System.currentTimeMillis() + ((this.f5538t.f25f ? -1 : r6.f26g) * 1000);
        }
    }

    /* renamed from: Q */
    public final void m2178Q(Throwable th) {
        if (th instanceof C0015a0) {
            m2177P((C0015a0) th);
            return;
        }
        C1743c.b bVar = this.f5494l.f5510o;
        if (bVar == null) {
            f5531u.mo2352c("unavailable", th);
        } else {
            C1743c.this.f4946v.mo2354e("unavailable", th);
        }
        this.f5538t = new a(this, String.valueOf(th), -1, th);
        this.f5536r = -1L;
    }

    /* renamed from: R */
    public InterfaceC0024j m2179R() throws IllegalAccessException, InstantiationException, C0029o {
        try {
            C1743c.b bVar = this.f5494l.f5510o;
            return bVar == null ? (InterfaceC0024j) this.f5488f.newInstance() : ((C1887d.a) bVar).m2156e(this.f5488f);
        } catch (C0029o e7) {
            Throwable th = e7.f31e;
            if (th instanceof InstantiationException) {
                throw ((InstantiationException) th);
            }
            if (th instanceof IllegalAccessException) {
                throw ((IllegalAccessException) th);
            }
            throw e7;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        String str;
        if (!(obj instanceof C1889f)) {
            return 1;
        }
        C1889f c1889f = (C1889f) obj;
        int iCompareTo = 0;
        if (c1889f == this) {
            return 0;
        }
        Objects.requireNonNull(c1889f);
        String str2 = this.f5490h;
        if (str2 != null && (str = c1889f.f5490h) != null) {
            iCompareTo = str2.compareTo(str);
        }
        return iCompareTo == 0 ? this.f5493k.compareTo(c1889f.f5493k) : iCompareTo;
    }

    @Override // p154s5.C1886c, p168u5.AbstractC1980a
    public void doStart() throws Exception {
        this.f5536r = 0L;
        if (this.f5537s) {
            try {
                super.doStart();
                try {
                    m2170H();
                    this.f5532n = this.f5494l.f5515t;
                    this.f5535q = new b();
                    Class<? extends T> cls = this.f5488f;
                    if (cls != 0 && InterfaceC0040z.class.isAssignableFrom(cls)) {
                        this.f5534p = new d(null);
                    }
                    if (this.f5491i) {
                        try {
                            m2175N();
                        } catch (Exception e7) {
                            Objects.requireNonNull(this.f5494l);
                            throw e7;
                        }
                    }
                } catch (C0015a0 e8) {
                    m2177P(e8);
                    Objects.requireNonNull(this.f5494l);
                    throw e8;
                }
            } catch (C0015a0 e9) {
                m2177P(e9);
                Objects.requireNonNull(this.f5494l);
                throw e9;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0049  */
    @Override // p154s5.C1886c, p168u5.AbstractC1980a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doStop() throws java.lang.Throwable {
        /*
            r5 = this;
            a5.j r0 = r5.f5534p
            r1 = 0
            if (r0 == 0) goto L45
            l5.e r0 = r5.f5532n     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            if (r0 == 0) goto L12
            n5.v r2 = r0.m1617b()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            java.lang.Object r0 = r0.m1619d(r2, r1)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            goto L13
        L12:
            r0 = r1
        L13:
            a5.j r2 = r5.f5534p     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L25
            r5.m2171J(r2)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L25
            l5.e r2 = r5.f5532n
            if (r2 == 0) goto L45
            r2.m1616a(r0)
            goto L45
        L20:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L3d
        L25:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L2e
        L2a:
            r0 = move-exception
            goto L3d
        L2c:
            r0 = move-exception
            r2 = r1
        L2e:
            v5.c r3 = p154s5.C1889f.f5531u     // Catch: java.lang.Throwable -> L3b
            r3.mo2358i(r0)     // Catch: java.lang.Throwable -> L3b
            l5.e r0 = r5.f5532n
            if (r0 == 0) goto L45
            r0.m1616a(r2)
            goto L45
        L3b:
            r0 = move-exception
            r1 = r2
        L3d:
            l5.e r2 = r5.f5532n
            if (r2 == 0) goto L44
            r2.m1616a(r1)
        L44:
            throw r0
        L45:
            boolean r0 = r5.f5491i
            if (r0 != 0) goto L4b
            r5.f5534p = r1
        L4b:
            r5.f5535q = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p154s5.C1889f.doStop():void");
    }

    public boolean equals(Object obj) {
        return compareTo(obj) == 0;
    }

    public int hashCode() {
        String str = this.f5493k;
        return str == null ? System.identityHashCode(this) : str.hashCode();
    }

    public C1889f(InterfaceC0024j interfaceC0024j) {
        super(1);
        this.f5537s = true;
        synchronized (this) {
            if (interfaceC0024j != null) {
                if (!(interfaceC0024j instanceof InterfaceC0040z)) {
                    this.f5491i = true;
                    this.f5534p = interfaceC0024j;
                    m2153G(interfaceC0024j.getClass());
                    if (this.f5493k == null) {
                        this.f5493k = interfaceC0024j.getClass().getName() + "-" + super.hashCode();
                    }
                }
            }
            throw new IllegalArgumentException();
        }
    }
}
