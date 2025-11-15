package p032d4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import p005a4.C0011c;
import p009b.C0413b;
import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0442b;
import p014b4.InterfaceC0443c;
import p014b4.InterfaceC0445e;
import p014b4.InterfaceC0446f;
import p014b4.InterfaceC0447g;
import p014b4.InterfaceC0448h;
import p014b4.InterfaceC0449i;
import p014b4.InterfaceC0450j;
import p014b4.InterfaceC0451k;
import p014b4.InterfaceC0452l;
import p014b4.InterfaceC0453m;
import p014b4.InterfaceC0454n;
import p014b4.InterfaceC0455o;
import p138q4.EnumC1776h;
import p160t4.C1908a;
import p174v4.C2013b;
import p192y1.C2103g;
import p194y3.AbstractC2128t;
import p194y3.C2119k;

/* compiled from: Functions.java */
/* renamed from: d4.a */
/* loaded from: classes.dex */
public final class C0870a {

    /* renamed from: a */
    public static final InterfaceC0454n<Object, Object> f1298a = new t();

    /* renamed from: b */
    public static final Runnable f1299b = new p();

    /* renamed from: c */
    public static final InterfaceC0441a f1300c = new n();

    /* renamed from: d */
    public static final InterfaceC0446f<Object> f1301d = new o();

    /* renamed from: e */
    public static final InterfaceC0446f<Throwable> f1302e = new c0();

    /* renamed from: f */
    public static final InterfaceC0455o<Object> f1303f = new h0();

    /* renamed from: g */
    public static final InterfaceC0455o<Object> f1304g = new r();

    /* renamed from: h */
    public static final Callable<Object> f1305h = new b0();

    /* renamed from: i */
    public static final Comparator<Object> f1306i = new x();

    /* compiled from: Functions.java */
    /* renamed from: d4.a$a */
    public static final class a<T> implements InterfaceC0446f<T> {

        /* renamed from: e */
        public final InterfaceC0441a f1307e;

        public a(InterfaceC0441a interfaceC0441a) {
            this.f1307e = interfaceC0441a;
        }

        @Override // p014b4.InterfaceC0446f
        public void accept(T t6) {
            this.f1307e.run();
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$a0 */
    public static final class a0<T> implements InterfaceC0446f<T> {

        /* renamed from: e */
        public final InterfaceC0446f<? super C2119k<T>> f1308e;

        public a0(InterfaceC0446f<? super C2119k<T>> interfaceC0446f) {
            this.f1308e = interfaceC0446f;
        }

        @Override // p014b4.InterfaceC0446f
        public void accept(T t6) {
            InterfaceC0446f<? super C2119k<T>> interfaceC0446f = this.f1308e;
            Objects.requireNonNull(t6, "value is null");
            interfaceC0446f.accept(new C2119k(t6));
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$b */
    public static final class b<T1, T2, R> implements InterfaceC0454n<Object[], R> {

        /* renamed from: e */
        public final InterfaceC0443c<? super T1, ? super T2, ? extends R> f1309e;

        public b(InterfaceC0443c<? super T1, ? super T2, ? extends R> interfaceC0443c) {
            this.f1309e = interfaceC0443c;
        }

        @Override // p014b4.InterfaceC0454n
        public Object apply(Object[] objArr) {
            Object[] objArr2 = objArr;
            if (objArr2.length == 2) {
                return this.f1309e.apply(objArr2[0], objArr2[1]);
            }
            StringBuilder sbM112a = C0413b.m112a("Array of size 2 expected but got ");
            sbM112a.append(objArr2.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$b0 */
    public static final class b0 implements Callable<Object> {
        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$c */
    public static final class c<T1, T2, T3, R> implements InterfaceC0454n<Object[], R> {

        /* renamed from: e */
        public final InterfaceC0447g<T1, T2, T3, R> f1310e;

        public c(InterfaceC0447g<T1, T2, T3, R> interfaceC0447g) {
            this.f1310e = interfaceC0447g;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p014b4.InterfaceC0454n
        public Object apply(Object[] objArr) {
            Object[] objArr2 = objArr;
            if (objArr2.length == 3) {
                return this.f1310e.m153a(objArr2[0], objArr2[1], objArr2[2]);
            }
            StringBuilder sbM112a = C0413b.m112a("Array of size 3 expected but got ");
            sbM112a.append(objArr2.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$c0 */
    public static final class c0 implements InterfaceC0446f<Throwable> {
        @Override // p014b4.InterfaceC0446f
        public void accept(Throwable th) {
            C1908a.m2205b(new C0011c(th));
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$d */
    public static final class d<T1, T2, T3, T4, R> implements InterfaceC0454n<Object[], R> {

        /* renamed from: e */
        public final InterfaceC0448h<T1, T2, T3, T4, R> f1311e;

        public d(InterfaceC0448h<T1, T2, T3, T4, R> interfaceC0448h) {
            this.f1311e = interfaceC0448h;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p014b4.InterfaceC0454n
        public Object apply(Object[] objArr) {
            Object[] objArr2 = objArr;
            if (objArr2.length == 4) {
                return this.f1311e.m154a(objArr2[0], objArr2[1], objArr2[2], objArr2[3]);
            }
            StringBuilder sbM112a = C0413b.m112a("Array of size 4 expected but got ");
            sbM112a.append(objArr2.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$d0 */
    public static final class d0<T> implements InterfaceC0454n<T, C2013b<T>> {

        /* renamed from: e */
        public final TimeUnit f1312e;

        /* renamed from: f */
        public final AbstractC2128t f1313f;

        public d0(TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
            this.f1312e = timeUnit;
            this.f1313f = abstractC2128t;
        }

        @Override // p014b4.InterfaceC0454n
        public Object apply(Object obj) {
            return new C2013b(obj, this.f1313f.now(this.f1312e), this.f1312e);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$e */
    public static final class e<T1, T2, T3, T4, T5, R> implements InterfaceC0454n<Object[], R> {

        /* renamed from: e */
        public final InterfaceC0449i<T1, T2, T3, T4, T5, R> f1314e;

        public e(InterfaceC0449i<T1, T2, T3, T4, T5, R> interfaceC0449i) {
            this.f1314e = interfaceC0449i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p014b4.InterfaceC0454n
        public Object apply(Object[] objArr) {
            Object[] objArr2 = objArr;
            if (objArr2.length == 5) {
                return this.f1314e.m155a(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4]);
            }
            StringBuilder sbM112a = C0413b.m112a("Array of size 5 expected but got ");
            sbM112a.append(objArr2.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$e0 */
    public static final class e0<K, T> implements InterfaceC0442b<Map<K, T>, T> {

        /* renamed from: a */
        public final InterfaceC0454n<? super T, ? extends K> f1315a;

        public e0(InterfaceC0454n<? super T, ? extends K> interfaceC0454n) {
            this.f1315a = interfaceC0454n;
        }

        @Override // p014b4.InterfaceC0442b
        /* renamed from: a */
        public void mo152a(Object obj, Object obj2) {
            ((Map) obj).put(this.f1315a.apply(obj2), obj2);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$f */
    public static final class f<T1, T2, T3, T4, T5, T6, R> implements InterfaceC0454n<Object[], R> {

        /* renamed from: e */
        public final InterfaceC0450j<T1, T2, T3, T4, T5, T6, R> f1316e;

        public f(InterfaceC0450j<T1, T2, T3, T4, T5, T6, R> interfaceC0450j) {
            this.f1316e = interfaceC0450j;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p014b4.InterfaceC0454n
        public Object apply(Object[] objArr) {
            Object[] objArr2 = objArr;
            if (objArr2.length == 6) {
                return this.f1316e.m156a(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            }
            StringBuilder sbM112a = C0413b.m112a("Array of size 6 expected but got ");
            sbM112a.append(objArr2.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$f0 */
    public static final class f0<K, V, T> implements InterfaceC0442b<Map<K, V>, T> {

        /* renamed from: a */
        public final InterfaceC0454n<? super T, ? extends V> f1317a;

        /* renamed from: b */
        public final InterfaceC0454n<? super T, ? extends K> f1318b;

        public f0(InterfaceC0454n<? super T, ? extends V> interfaceC0454n, InterfaceC0454n<? super T, ? extends K> interfaceC0454n2) {
            this.f1317a = interfaceC0454n;
            this.f1318b = interfaceC0454n2;
        }

        @Override // p014b4.InterfaceC0442b
        /* renamed from: a */
        public void mo152a(Object obj, Object obj2) {
            ((Map) obj).put(this.f1318b.apply(obj2), this.f1317a.apply(obj2));
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$g */
    public static final class g<T1, T2, T3, T4, T5, T6, T7, R> implements InterfaceC0454n<Object[], R> {

        /* renamed from: e */
        public final InterfaceC0451k<T1, T2, T3, T4, T5, T6, T7, R> f1319e;

        public g(InterfaceC0451k<T1, T2, T3, T4, T5, T6, T7, R> interfaceC0451k) {
            this.f1319e = interfaceC0451k;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p014b4.InterfaceC0454n
        public Object apply(Object[] objArr) {
            Object[] objArr2 = objArr;
            if (objArr2.length == 7) {
                return this.f1319e.m157a(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6]);
            }
            StringBuilder sbM112a = C0413b.m112a("Array of size 7 expected but got ");
            sbM112a.append(objArr2.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$g0 */
    public static final class g0<K, V, T> implements InterfaceC0442b<Map<K, Collection<V>>, T> {

        /* renamed from: a */
        public final InterfaceC0454n<? super K, ? extends Collection<? super V>> f1320a;

        /* renamed from: b */
        public final InterfaceC0454n<? super T, ? extends V> f1321b;

        /* renamed from: c */
        public final InterfaceC0454n<? super T, ? extends K> f1322c;

        public g0(InterfaceC0454n<? super K, ? extends Collection<? super V>> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2, InterfaceC0454n<? super T, ? extends K> interfaceC0454n3) {
            this.f1320a = interfaceC0454n;
            this.f1321b = interfaceC0454n2;
            this.f1322c = interfaceC0454n3;
        }

        @Override // p014b4.InterfaceC0442b
        /* renamed from: a */
        public void mo152a(Object obj, Object obj2) {
            Map map = (Map) obj;
            K kApply = this.f1322c.apply(obj2);
            Collection<? super V> collectionApply = (Collection) map.get(kApply);
            if (collectionApply == null) {
                collectionApply = this.f1320a.apply(kApply);
                map.put(kApply, collectionApply);
            }
            collectionApply.add(this.f1321b.apply(obj2));
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$h */
    public static final class h<T1, T2, T3, T4, T5, T6, T7, T8, R> implements InterfaceC0454n<Object[], R> {

        /* renamed from: e */
        public final InterfaceC0452l<T1, T2, T3, T4, T5, T6, T7, T8, R> f1323e;

        public h(InterfaceC0452l<T1, T2, T3, T4, T5, T6, T7, T8, R> interfaceC0452l) {
            this.f1323e = interfaceC0452l;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p014b4.InterfaceC0454n
        public Object apply(Object[] objArr) {
            Object[] objArr2 = objArr;
            if (objArr2.length == 8) {
                return this.f1323e.m158a(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6], objArr2[7]);
            }
            StringBuilder sbM112a = C0413b.m112a("Array of size 8 expected but got ");
            sbM112a.append(objArr2.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$h0 */
    public static final class h0 implements InterfaceC0455o<Object> {
        @Override // p014b4.InterfaceC0455o
        public boolean test(Object obj) {
            return true;
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$i */
    public static final class i<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements InterfaceC0454n<Object[], R> {

        /* renamed from: e */
        public final InterfaceC0453m<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> f1324e;

        public i(InterfaceC0453m<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> interfaceC0453m) {
            this.f1324e = interfaceC0453m;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p014b4.InterfaceC0454n
        public Object apply(Object[] objArr) {
            Object[] objArr2 = objArr;
            if (objArr2.length == 9) {
                return this.f1324e.m159a(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6], objArr2[7], objArr2[8]);
            }
            StringBuilder sbM112a = C0413b.m112a("Array of size 9 expected but got ");
            sbM112a.append(objArr2.length);
            throw new IllegalArgumentException(sbM112a.toString());
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$j */
    public static final class j<T> implements Callable<List<T>> {

        /* renamed from: e */
        public final int f1325e;

        public j(int i7) {
            this.f1325e = i7;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            return new ArrayList(this.f1325e);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$k */
    public static final class k<T> implements InterfaceC0455o<T> {

        /* renamed from: e */
        public final InterfaceC0445e f1326e;

        public k(InterfaceC0445e interfaceC0445e) {
            this.f1326e = interfaceC0445e;
        }

        @Override // p014b4.InterfaceC0455o
        public boolean test(T t6) {
            return !((C2103g) this.f1326e).m2546a();
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$l */
    public static final class l<T, U> implements InterfaceC0454n<T, U> {

        /* renamed from: e */
        public final Class<U> f1327e;

        public l(Class<U> cls) {
            this.f1327e = cls;
        }

        @Override // p014b4.InterfaceC0454n
        public U apply(T t6) {
            return this.f1327e.cast(t6);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$m */
    public static final class m<T, U> implements InterfaceC0455o<T> {

        /* renamed from: e */
        public final Class<U> f1328e;

        public m(Class<U> cls) {
            this.f1328e = cls;
        }

        @Override // p014b4.InterfaceC0455o
        public boolean test(T t6) {
            return this.f1328e.isInstance(t6);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$n */
    public static final class n implements InterfaceC0441a {
        @Override // p014b4.InterfaceC0441a
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$o */
    public static final class o implements InterfaceC0446f<Object> {
        @Override // p014b4.InterfaceC0446f
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$p */
    public static final class p implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$q */
    public static final class q<T> implements InterfaceC0455o<T> {

        /* renamed from: e */
        public final T f1329e;

        public q(T t6) {
            this.f1329e = t6;
        }

        @Override // p014b4.InterfaceC0455o
        public boolean test(T t6) {
            return C0871b.m676a(t6, this.f1329e);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$r */
    public static final class r implements InterfaceC0455o<Object> {
        @Override // p014b4.InterfaceC0455o
        public boolean test(Object obj) {
            return false;
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$s */
    public enum s implements Callable<Set<Object>> {
        INSTANCE;

        @Override // java.util.concurrent.Callable
        public Set<Object> call() {
            return new HashSet();
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$t */
    public static final class t implements InterfaceC0454n<Object, Object> {
        @Override // p014b4.InterfaceC0454n
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$u */
    public static final class u<T, U> implements Callable<U>, InterfaceC0454n<T, U> {

        /* renamed from: e */
        public final U f1332e;

        public u(U u6) {
            this.f1332e = u6;
        }

        @Override // p014b4.InterfaceC0454n
        public U apply(T t6) {
            return this.f1332e;
        }

        @Override // java.util.concurrent.Callable
        public U call() {
            return this.f1332e;
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$v */
    public static final class v<T> implements InterfaceC0454n<List<T>, List<T>> {

        /* renamed from: e */
        public final Comparator<? super T> f1333e;

        public v(Comparator<? super T> comparator) {
            this.f1333e = comparator;
        }

        @Override // p014b4.InterfaceC0454n
        public Object apply(Object obj) {
            List list = (List) obj;
            Collections.sort(list, this.f1333e);
            return list;
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$w */
    public enum w implements Comparator<Object> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$x */
    public static final class x implements Comparator<Object> {
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$y */
    public static final class y<T> implements InterfaceC0441a {

        /* renamed from: e */
        public final InterfaceC0446f<? super C2119k<T>> f1336e;

        public y(InterfaceC0446f<? super C2119k<T>> interfaceC0446f) {
            this.f1336e = interfaceC0446f;
        }

        @Override // p014b4.InterfaceC0441a
        public void run() {
            this.f1336e.accept(C2119k.f6247b);
        }
    }

    /* compiled from: Functions.java */
    /* renamed from: d4.a$z */
    public static final class z<T> implements InterfaceC0446f<Throwable> {

        /* renamed from: e */
        public final InterfaceC0446f<? super C2119k<T>> f1337e;

        public z(InterfaceC0446f<? super C2119k<T>> interfaceC0446f) {
            this.f1337e = interfaceC0446f;
        }

        @Override // p014b4.InterfaceC0446f
        public void accept(Throwable th) {
            Throwable th2 = th;
            InterfaceC0446f<? super C2119k<T>> interfaceC0446f = this.f1337e;
            Objects.requireNonNull(th2, "error is null");
            interfaceC0446f.accept(new C2119k(new EnumC1776h.b(th2)));
        }
    }
}
