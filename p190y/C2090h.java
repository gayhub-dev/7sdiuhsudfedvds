package p190y;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;
import p009b.C0413b;

/* compiled from: LruArrayPool.java */
/* renamed from: y.h */
/* loaded from: classes.dex */
public final class C2090h implements InterfaceC2084b {

    /* renamed from: a */
    public final C2089g<a, Object> f6187a = new C2089g<>();

    /* renamed from: b */
    public final b f6188b = new b();

    /* renamed from: c */
    public final Map<Class<?>, NavigableMap<Integer, Integer>> f6189c = new HashMap();

    /* renamed from: d */
    public final Map<Class<?>, InterfaceC2083a<?>> f6190d = new HashMap();

    /* renamed from: e */
    public final int f6191e;

    /* renamed from: f */
    public int f6192f;

    /* compiled from: LruArrayPool.java */
    /* renamed from: y.h$a */
    public static final class a implements InterfaceC2093k {

        /* renamed from: a */
        public final b f6193a;

        /* renamed from: b */
        public int f6194b;

        /* renamed from: c */
        public Class<?> f6195c;

        public a(b bVar) {
            this.f6193a = bVar;
        }

        @Override // p190y.InterfaceC2093k
        /* renamed from: a */
        public void mo2529a() {
            this.f6193a.m2515d(this);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f6194b == aVar.f6194b && this.f6195c == aVar.f6195c;
        }

        public int hashCode() {
            int i7 = this.f6194b * 31;
            Class<?> cls = this.f6195c;
            return i7 + (cls != null ? cls.hashCode() : 0);
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("Key{size=");
            sbM112a.append(this.f6194b);
            sbM112a.append("array=");
            sbM112a.append(this.f6195c);
            sbM112a.append('}');
            return sbM112a.toString();
        }
    }

    /* compiled from: LruArrayPool.java */
    /* renamed from: y.h$b */
    public static final class b extends AbstractC2085c<a> {
        public b() {
            super(0);
        }

        @Override // p190y.AbstractC2085c
        /* renamed from: a */
        public InterfaceC2093k mo2512a() {
            return new a(this);
        }

        /* renamed from: e */
        public a m2530e(int i7, Class<?> cls) {
            a aVarM2513b = m2513b();
            aVarM2513b.f6194b = i7;
            aVarM2513b.f6195c = cls;
            return aVarM2513b;
        }
    }

    public C2090h(int i7) {
        this.f6191e = i7;
    }

    @Override // p190y.InterfaceC2084b
    /* renamed from: a */
    public synchronized void mo2508a(int i7) {
        if (i7 >= 40) {
            synchronized (this) {
                m2526f(0);
            }
        } else if (i7 >= 20) {
            m2526f(this.f6191e / 2);
        }
    }

    @Override // p190y.InterfaceC2084b
    /* renamed from: b */
    public synchronized void mo2509b() {
        m2526f(0);
    }

    @Override // p190y.InterfaceC2084b
    /* renamed from: c */
    public synchronized <T> void mo2510c(T t6, Class<T> cls) {
        InterfaceC2083a<T> interfaceC2083aM2527g = m2527g(cls);
        int iMo2506a = interfaceC2083aM2527g.mo2506a(t6);
        int iMo2507b = interfaceC2083aM2527g.mo2507b() * iMo2506a;
        int iIntValue = 1;
        if (iMo2507b <= this.f6191e / 2) {
            a aVarM2530e = this.f6188b.m2530e(iMo2506a, cls);
            this.f6187a.m2522b(aVarM2530e, t6);
            NavigableMap<Integer, Integer> navigableMapM2528h = m2528h(cls);
            Integer num = (Integer) navigableMapM2528h.get(Integer.valueOf(aVarM2530e.f6194b));
            Integer numValueOf = Integer.valueOf(aVarM2530e.f6194b);
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            navigableMapM2528h.put(numValueOf, Integer.valueOf(iIntValue));
            this.f6192f += iMo2507b;
            m2526f(this.f6191e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0033 A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x0018, B:8:0x001c, B:14:0x0027, B:19:0x0033, B:21:0x004a, B:23:0x0052, B:24:0x0068, B:20:0x003e), top: B:34:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003e A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x0018, B:8:0x001c, B:14:0x0027, B:19:0x0033, B:21:0x004a, B:23:0x0052, B:24:0x0068, B:20:0x003e), top: B:34:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0052 A[Catch: all -> 0x007d, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x0018, B:8:0x001c, B:14:0x0027, B:19:0x0033, B:21:0x004a, B:23:0x0052, B:24:0x0068, B:20:0x003e), top: B:34:0x0005 }] */
    @Override // p190y.InterfaceC2084b
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T mo2511d(int r8, java.lang.Class<T> r9) {
        /*
            r7 = this;
            y.a r0 = r7.m2527g(r9)
            monitor-enter(r7)
            java.util.NavigableMap r1 = r7.m2528h(r9)     // Catch: java.lang.Throwable -> L7d
            java.lang.Integer r2 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r1 = r1.ceilingKey(r2)     // Catch: java.lang.Throwable -> L7d
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Throwable -> L7d
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L30
            int r5 = r7.f6192f     // Catch: java.lang.Throwable -> L7d
            if (r5 == 0) goto L24
            int r6 = r7.f6191e     // Catch: java.lang.Throwable -> L7d
            int r6 = r6 / r5
            if (r6 < r2) goto L22
            goto L24
        L22:
            r5 = 0
            goto L25
        L24:
            r5 = 1
        L25:
            if (r5 != 0) goto L31
            int r5 = r1.intValue()     // Catch: java.lang.Throwable -> L7d
            int r6 = r8 * 8
            if (r5 > r6) goto L30
            goto L31
        L30:
            r3 = 0
        L31:
            if (r3 == 0) goto L3e
            y.h$b r3 = r7.f6188b     // Catch: java.lang.Throwable -> L7d
            int r1 = r1.intValue()     // Catch: java.lang.Throwable -> L7d
            y.h$a r1 = r3.m2530e(r1, r9)     // Catch: java.lang.Throwable -> L7d
            goto L4a
        L3e:
            y.h$b r1 = r7.f6188b     // Catch: java.lang.Throwable -> L7d
            y.k r1 = r1.m2513b()     // Catch: java.lang.Throwable -> L7d
            y.h$a r1 = (p190y.C2090h.a) r1     // Catch: java.lang.Throwable -> L7d
            r1.f6194b = r8     // Catch: java.lang.Throwable -> L7d
            r1.f6195c = r9     // Catch: java.lang.Throwable -> L7d
        L4a:
            y.g<y.h$a, java.lang.Object> r3 = r7.f6187a     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r1 = r3.m2521a(r1)     // Catch: java.lang.Throwable -> L7d
            if (r1 == 0) goto L68
            int r3 = r7.f6192f     // Catch: java.lang.Throwable -> L7d
            int r4 = r0.mo2506a(r1)     // Catch: java.lang.Throwable -> L7d
            int r5 = r0.mo2507b()     // Catch: java.lang.Throwable -> L7d
            int r4 = r4 * r5
            int r3 = r3 - r4
            r7.f6192f = r3     // Catch: java.lang.Throwable -> L7d
            int r3 = r0.mo2506a(r1)     // Catch: java.lang.Throwable -> L7d
            r7.m2525e(r3, r9)     // Catch: java.lang.Throwable -> L7d
        L68:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L7d
            if (r1 != 0) goto L7c
            java.lang.String r9 = r0.getTag()
            boolean r9 = android.util.Log.isLoggable(r9, r2)
            if (r9 == 0) goto L78
            r0.getTag()
        L78:
            java.lang.Object r1 = r0.newArray(r8)
        L7c:
            return r1
        L7d:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L7d
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p190y.C2090h.mo2511d(int, java.lang.Class):java.lang.Object");
    }

    /* renamed from: e */
    public final void m2525e(int i7, Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMapM2528h = m2528h(cls);
        Integer num = (Integer) navigableMapM2528h.get(Integer.valueOf(i7));
        if (num != null) {
            if (num.intValue() == 1) {
                navigableMapM2528h.remove(Integer.valueOf(i7));
                return;
            } else {
                navigableMapM2528h.put(Integer.valueOf(i7), Integer.valueOf(num.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + i7 + ", this: " + this);
    }

    /* renamed from: f */
    public final void m2526f(int i7) {
        while (this.f6192f > i7) {
            Object objM2523c = this.f6187a.m2523c();
            Objects.requireNonNull(objM2523c, "Argument must not be null");
            InterfaceC2083a interfaceC2083aM2527g = m2527g(objM2523c.getClass());
            this.f6192f -= interfaceC2083aM2527g.mo2507b() * interfaceC2083aM2527g.mo2506a(objM2523c);
            m2525e(interfaceC2083aM2527g.mo2506a(objM2523c), objM2523c.getClass());
            if (Log.isLoggable(interfaceC2083aM2527g.getTag(), 2)) {
                interfaceC2083aM2527g.getTag();
                interfaceC2083aM2527g.mo2506a(objM2523c);
            }
        }
    }

    /* renamed from: g */
    public final <T> InterfaceC2083a<T> m2527g(Class<T> cls) {
        InterfaceC2083a<T> c2088f = (InterfaceC2083a) this.f6190d.get(cls);
        if (c2088f == null) {
            if (cls.equals(int[].class)) {
                c2088f = new C2088f(1);
            } else {
                if (!cls.equals(byte[].class)) {
                    StringBuilder sbM112a = C0413b.m112a("No array pool found for: ");
                    sbM112a.append(cls.getSimpleName());
                    throw new IllegalArgumentException(sbM112a.toString());
                }
                c2088f = new C2088f(0);
            }
            this.f6190d.put(cls, c2088f);
        }
        return c2088f;
    }

    /* renamed from: h */
    public final NavigableMap<Integer, Integer> m2528h(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.f6189c.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f6189c.put(cls, treeMap);
        return treeMap;
    }
}
