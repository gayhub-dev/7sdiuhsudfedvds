package p183x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p010b0.InterfaceC0426l;
import p028d0.C0863a;
import p076j0.C1183c;
import p076j0.C1184d;
import p076j0.InterfaceC1182b;
import p100m0.C1454b;
import p100m0.C1455c;
import p141r.C1810e;
import p141r.C1812g;
import p141r.EnumC1811f;
import p142r0.C1822g;
import p162u.C1966j;
import p162u.InterfaceC1964h;
import p162u.InterfaceC1969m;
import p183x.C2048i;
import p183x.RunnableC2045f;
import p197z.InterfaceC2134a;

/* compiled from: DecodeHelper.java */
/* renamed from: x.e */
/* loaded from: classes.dex */
public final class C2044e<Transcode> {

    /* renamed from: a */
    public final List<InterfaceC0426l.a<?>> f5971a = new ArrayList();

    /* renamed from: b */
    public final List<InterfaceC1964h> f5972b = new ArrayList();

    /* renamed from: c */
    public C1810e f5973c;

    /* renamed from: d */
    public Object f5974d;

    /* renamed from: e */
    public int f5975e;

    /* renamed from: f */
    public int f5976f;

    /* renamed from: g */
    public Class<?> f5977g;

    /* renamed from: h */
    public RunnableC2045f.d f5978h;

    /* renamed from: i */
    public C1966j f5979i;

    /* renamed from: j */
    public Map<Class<?>, InterfaceC1969m<?>> f5980j;

    /* renamed from: k */
    public Class<Transcode> f5981k;

    /* renamed from: l */
    public boolean f5982l;

    /* renamed from: m */
    public boolean f5983m;

    /* renamed from: n */
    public InterfaceC1964h f5984n;

    /* renamed from: o */
    public EnumC1811f f5985o;

    /* renamed from: p */
    public AbstractC2047h f5986p;

    /* renamed from: q */
    public boolean f5987q;

    /* renamed from: r */
    public boolean f5988r;

    /* renamed from: a */
    public List<InterfaceC1964h> m2404a() {
        if (!this.f5983m) {
            this.f5983m = true;
            this.f5972b.clear();
            List<InterfaceC0426l.a<?>> listM2406c = m2406c();
            int size = listM2406c.size();
            for (int i7 = 0; i7 < size; i7++) {
                InterfaceC0426l.a<?> aVar = listM2406c.get(i7);
                if (!this.f5972b.contains(aVar.f231a)) {
                    this.f5972b.add(aVar.f231a);
                }
                for (int i8 = 0; i8 < aVar.f232b.size(); i8++) {
                    if (!this.f5972b.contains(aVar.f232b.get(i8))) {
                        this.f5972b.add(aVar.f232b.get(i8));
                    }
                }
            }
        }
        return this.f5972b;
    }

    /* renamed from: b */
    public InterfaceC2134a m2405b() {
        return ((C2048i.c) this.f5978h).m2434a();
    }

    /* renamed from: c */
    public List<InterfaceC0426l.a<?>> m2406c() {
        if (!this.f5982l) {
            this.f5982l = true;
            this.f5971a.clear();
            ArrayList arrayList = (ArrayList) this.f5973c.f5235a.m2029d(this.f5974d);
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                InterfaceC0426l.a<?> aVarMo117a = ((InterfaceC0426l) arrayList.get(i7)).mo117a(this.f5974d, this.f5975e, this.f5976f, this.f5979i);
                if (aVarMo117a != null) {
                    this.f5971a.add(aVarMo117a);
                }
            }
        }
        return this.f5971a;
    }

    /* renamed from: d */
    public <Data> C2055p<Data, ?, Transcode> m2407d(Class<Data> cls) {
        C2055p<Data, ?, Transcode> c2055p;
        boolean zContainsKey;
        ArrayList arrayList;
        InterfaceC1182b interfaceC1182b;
        C1812g c1812g = this.f5973c.f5235a;
        Class<?> cls2 = this.f5977g;
        Class<Transcode> cls3 = this.f5981k;
        C1454b c1454b = c1812g.f5254i;
        C1822g c1822gM1637a = c1454b.m1637a(cls, cls2, cls3);
        synchronized (c1454b.f4215a) {
            c2055p = (C2055p) c1454b.f4215a.get(c1822gM1637a);
        }
        c1454b.f4216b.set(c1822gM1637a);
        if (c2055p != null) {
            return c2055p;
        }
        C1454b c1454b2 = c1812g.f5254i;
        C1822g c1822gM1637a2 = c1454b2.m1637a(cls, cls2, cls3);
        synchronized (c1454b2.f4215a) {
            zContainsKey = c1454b2.f4215a.containsKey(c1822gM1637a2);
        }
        c1454b2.f4216b.set(c1822gM1637a2);
        if (zContainsKey) {
            return c2055p;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = ((ArrayList) c1812g.f5248c.m1639a(cls, cls2)).iterator();
        while (it.hasNext()) {
            Class<?> cls4 = (Class) it.next();
            Iterator it2 = ((ArrayList) c1812g.f5251f.m1379a(cls4, cls3)).iterator();
            while (it2.hasNext()) {
                Class<?> cls5 = (Class) it2.next();
                C1455c c1455c = c1812g.f5248c;
                synchronized (c1455c) {
                    arrayList = new ArrayList();
                    for (C1455c.a<?, ?> aVar : c1455c.f4217a) {
                        if (aVar.m1640a(cls, cls4)) {
                            arrayList.add(aVar.f4220c);
                        }
                    }
                }
                C1183c c1183c = c1812g.f5251f;
                synchronized (c1183c) {
                    if (!cls5.isAssignableFrom(cls4)) {
                        for (C1183c.a<?, ?> aVar2 : c1183c.f2597a) {
                            if (aVar2.m1380a(cls4, cls5)) {
                                interfaceC1182b = aVar2.f2600c;
                            }
                        }
                        throw new IllegalArgumentException("No transcoder registered to transcode from " + cls4 + " to " + cls5);
                    }
                    interfaceC1182b = C1184d.f2601a;
                }
                arrayList2.add(new C2046g(cls, cls4, cls5, arrayList, interfaceC1182b, c1812g.f5255j));
            }
        }
        C2055p<Data, ?, Transcode> c2055p2 = arrayList2.isEmpty() ? null : new C2055p<>(cls, cls2, cls3, arrayList2, c1812g.f5255j);
        c1812g.f5254i.m1638b(cls, cls2, cls3, c2055p2);
        return c2055p2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
    
        r1 = (p162u.InterfaceC1960d<X>) r3.f4214b;
     */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <X> p162u.InterfaceC1960d<X> m2408e(X r6) {
        /*
            r5 = this;
            r.e r0 = r5.f5973c
            r.g r0 = r0.f5235a
            m0.a r0 = r0.f5247b
            java.lang.Class r1 = r6.getClass()
            monitor-enter(r0)
            java.util.List<m0.a$a<?>> r2 = r0.f4212a     // Catch: java.lang.Throwable -> L38
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L38
        L11:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L38
            if (r3 == 0) goto L29
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L38
            m0.a$a r3 = (p100m0.C1453a.a) r3     // Catch: java.lang.Throwable -> L38
            java.lang.Class<T> r4 = r3.f4213a     // Catch: java.lang.Throwable -> L38
            boolean r4 = r4.isAssignableFrom(r1)     // Catch: java.lang.Throwable -> L38
            if (r4 == 0) goto L11
            u.d<T> r1 = r3.f4214b     // Catch: java.lang.Throwable -> L38
            monitor-exit(r0)
            goto L2b
        L29:
            r1 = 0
            monitor-exit(r0)
        L2b:
            if (r1 == 0) goto L2e
            return r1
        L2e:
            r.g$e r0 = new r.g$e
            java.lang.Class r6 = r6.getClass()
            r0.<init>(r6)
            throw r0
        L38:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p183x.C2044e.m2408e(java.lang.Object):u.d");
    }

    /* renamed from: f */
    public <Z> InterfaceC1969m<Z> m2409f(Class<Z> cls) {
        InterfaceC1969m<Z> interfaceC1969m = (InterfaceC1969m) this.f5980j.get(cls);
        if (interfaceC1969m != null) {
            return interfaceC1969m;
        }
        if (!this.f5980j.isEmpty() || !this.f5987q) {
            return (C0863a) C0863a.f1288b;
        }
        throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: g */
    public boolean m2410g(Class<?> cls) {
        return m2407d(cls) != null;
    }
}
