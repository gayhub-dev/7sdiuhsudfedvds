package p194y3;

import android.support.constraint.motion.C0079a;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import io.reactivex.ObservableSource;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0441a;
import p014b4.InterfaceC0442b;
import p014b4.InterfaceC0443c;
import p014b4.InterfaceC0444d;
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
import p022c4.EnumC0515c;
import p032d4.C0870a;
import p032d4.C0871b;
import p040e4.InterfaceC0949b;
import p040e4.InterfaceCallableC0953f;
import p048f4.C1000e;
import p048f4.C1001f;
import p048f4.C1006k;
import p048f4.C1010o;
import p048f4.FutureC1007l;
import p064h4.C1087b;
import p064h4.C1089d;
import p064h4.C1090e;
import p064h4.C1091f;
import p064h4.C1092g;
import p074i6.InterfaceC1167a;
import p080j4.C1204a;
import p080j4.C1205b;
import p080j4.C1206c;
import p080j4.C1207d;
import p080j4.C1208e;
import p080j4.C1209f;
import p088k4.AbstractC1238a;
import p088k4.C1239a0;
import p088k4.C1241a2;
import p088k4.C1242a3;
import p088k4.C1243a4;
import p088k4.C1244b;
import p088k4.C1245b0;
import p088k4.C1246b1;
import p088k4.C1247b2;
import p088k4.C1248b3;
import p088k4.C1249b4;
import p088k4.C1250c;
import p088k4.C1251c0;
import p088k4.C1252c1;
import p088k4.C1253c2;
import p088k4.C1254c3;
import p088k4.C1255c4;
import p088k4.C1256d;
import p088k4.C1257d0;
import p088k4.C1258d1;
import p088k4.C1259d2;
import p088k4.C1260d3;
import p088k4.C1261d4;
import p088k4.C1262e;
import p088k4.C1263e0;
import p088k4.C1264e1;
import p088k4.C1265e2;
import p088k4.C1266e3;
import p088k4.C1267e4;
import p088k4.C1269f0;
import p088k4.C1270f1;
import p088k4.C1271f2;
import p088k4.C1272f3;
import p088k4.C1273f4;
import p088k4.C1274g;
import p088k4.C1275g0;
import p088k4.C1276g1;
import p088k4.C1277g2;
import p088k4.C1278g3;
import p088k4.C1279g4;
import p088k4.C1280h;
import p088k4.C1281h0;
import p088k4.C1282h1;
import p088k4.C1283h2;
import p088k4.C1284h3;
import p088k4.C1285h4;
import p088k4.C1287i0;
import p088k4.C1288i1;
import p088k4.C1289i2;
import p088k4.C1290i3;
import p088k4.C1291i4;
import p088k4.C1292j;
import p088k4.C1293j0;
import p088k4.C1294j1;
import p088k4.C1295j2;
import p088k4.C1296j3;
import p088k4.C1297j4;
import p088k4.C1298k;
import p088k4.C1299k0;
import p088k4.C1301k2;
import p088k4.C1303k4;
import p088k4.C1304l;
import p088k4.C1305l0;
import p088k4.C1307l2;
import p088k4.C1308l3;
import p088k4.C1309l4;
import p088k4.C1310m;
import p088k4.C1311m0;
import p088k4.C1312m1;
import p088k4.C1313m2;
import p088k4.C1314m3;
import p088k4.C1315m4;
import p088k4.C1316n;
import p088k4.C1317n0;
import p088k4.C1319n2;
import p088k4.C1320n3;
import p088k4.C1321n4;
import p088k4.C1322o;
import p088k4.C1324o1;
import p088k4.C1325o2;
import p088k4.C1326o3;
import p088k4.C1327o4;
import p088k4.C1328p;
import p088k4.C1329p0;
import p088k4.C1330p1;
import p088k4.C1331p2;
import p088k4.C1332p3;
import p088k4.C1333p4;
import p088k4.C1335q0;
import p088k4.C1336q1;
import p088k4.C1337q2;
import p088k4.C1338q3;
import p088k4.C1339q4;
import p088k4.C1340r;
import p088k4.C1341r0;
import p088k4.C1342r1;
import p088k4.C1343r2;
import p088k4.C1344r3;
import p088k4.C1345r4;
import p088k4.C1346s;
import p088k4.C1347s0;
import p088k4.C1348s1;
import p088k4.C1349s2;
import p088k4.C1350s3;
import p088k4.C1351s4;
import p088k4.C1352t;
import p088k4.C1353t0;
import p088k4.C1355t2;
import p088k4.C1356t3;
import p088k4.C1357t4;
import p088k4.C1358u;
import p088k4.C1360u1;
import p088k4.C1361u2;
import p088k4.C1362u3;
import p088k4.C1363u4;
import p088k4.C1364v;
import p088k4.C1365v0;
import p088k4.C1366v1;
import p088k4.C1367v2;
import p088k4.C1368v3;
import p088k4.C1369v4;
import p088k4.C1370w;
import p088k4.C1371w0;
import p088k4.C1372w1;
import p088k4.C1373w2;
import p088k4.C1374w3;
import p088k4.C1375w4;
import p088k4.C1376x;
import p088k4.C1377x0;
import p088k4.C1379x2;
import p088k4.C1380x3;
import p088k4.C1383y0;
import p088k4.C1384y1;
import p088k4.C1385y2;
import p088k4.C1386y3;
import p088k4.C1387z;
import p088k4.C1388z0;
import p088k4.C1389z1;
import p088k4.C1390z2;
import p088k4.C1391z3;
import p088k4.CallableC1240a1;
import p088k4.CallableC1300k1;
import p088k4.CallableC1306l1;
import p088k4.CallableC1354t1;
import p088k4.CallableC1378x1;
import p096l4.C1434a;
import p096l4.C1435b;
import p138q4.C1772d;
import p138q4.C1774f;
import p138q4.EnumC1770b;
import p138q4.EnumC1775g;
import p146r4.AbstractC1837a;
import p153s4.C1881d;
import p153s4.C1883f;
import p160t4.C1908a;
import p174v4.C2012a;
import p174v4.C2013b;
import p186x2.C2074b;
import p201z3.InterfaceC2153b;

/* compiled from: Observable.java */
/* renamed from: y3.l */
/* loaded from: classes.dex */
public abstract class AbstractC2120l<T> implements InterfaceC2125q<T> {
    public static <T> AbstractC2120l<T> amb(Iterable<? extends InterfaceC2125q<? extends T>> iterable) {
        Objects.requireNonNull(iterable, "sources is null");
        return new C1280h(null, iterable);
    }

    public static <T> AbstractC2120l<T> ambArray(ObservableSource<? extends T>... observableSourceArr) {
        Objects.requireNonNull(observableSourceArr, "sources is null");
        int length = observableSourceArr.length;
        return length == 0 ? empty() : length == 1 ? wrap(observableSourceArr[0]) : new C1280h(observableSourceArr, null);
    }

    public static int bufferSize() {
        return AbstractC2114f.f6246e;
    }

    public static <T, R> AbstractC2120l<R> combineLatest(Iterable<? extends InterfaceC2125q<? extends T>> iterable, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7) {
        Objects.requireNonNull(iterable, "sources is null");
        Objects.requireNonNull(interfaceC0454n, "combiner is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1346s(null, iterable, interfaceC0454n, i7 << 1, false);
    }

    public static <T, R> AbstractC2120l<R> combineLatestDelayError(Iterable<? extends InterfaceC2125q<? extends T>> iterable, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7) {
        Objects.requireNonNull(iterable, "sources is null");
        Objects.requireNonNull(interfaceC0454n, "combiner is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1346s(null, iterable, interfaceC0454n, i7 << 1, true);
    }

    public static <T> AbstractC2120l<T> concat(Iterable<? extends InterfaceC2125q<? extends T>> iterable) {
        Objects.requireNonNull(iterable, "sources is null");
        return fromIterable(iterable).concatMapDelayError(C0870a.f1298a, bufferSize(), false);
    }

    public static <T> AbstractC2120l<T> concatArray(ObservableSource<? extends T>... observableSourceArr) {
        return observableSourceArr.length == 0 ? empty() : observableSourceArr.length == 1 ? wrap(observableSourceArr[0]) : new C1352t(fromArray(observableSourceArr), C0870a.f1298a, bufferSize(), 2);
    }

    public static <T> AbstractC2120l<T> concatArrayDelayError(ObservableSource<? extends T>... observableSourceArr) {
        return observableSourceArr.length == 0 ? empty() : observableSourceArr.length == 1 ? wrap(observableSourceArr[0]) : concatDelayError(fromArray(observableSourceArr));
    }

    public static <T> AbstractC2120l<T> concatArrayEager(ObservableSource<? extends T>... observableSourceArr) {
        return concatArrayEager(bufferSize(), bufferSize(), observableSourceArr);
    }

    public static <T> AbstractC2120l<T> concatArrayEagerDelayError(ObservableSource<? extends T>... observableSourceArr) {
        return concatArrayEagerDelayError(bufferSize(), bufferSize(), observableSourceArr);
    }

    public static <T> AbstractC2120l<T> concatDelayError(Iterable<? extends InterfaceC2125q<? extends T>> iterable) {
        Objects.requireNonNull(iterable, "sources is null");
        return concatDelayError(fromIterable(iterable));
    }

    public static <T> AbstractC2120l<T> concatEager(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q) {
        return concatEager(interfaceC2125q, bufferSize(), bufferSize());
    }

    public static <T> AbstractC2120l<T> create(InterfaceC2123o<T> interfaceC2123o) {
        Objects.requireNonNull(interfaceC2123o, "source is null");
        return new C1239a0(interfaceC2123o);
    }

    public static <T> AbstractC2120l<T> defer(Callable<? extends InterfaceC2125q<? extends T>> callable) {
        Objects.requireNonNull(callable, "supplier is null");
        return new C1257d0(callable, 0);
    }

    public static <T> AbstractC2120l<T> empty() {
        return (AbstractC2120l<T>) C1341r0.f3627e;
    }

    public static <T> AbstractC2120l<T> error(Throwable th) {
        Objects.requireNonNull(th, "exception is null");
        return error(new C0870a.u(th));
    }

    public static <T> AbstractC2120l<T> fromArray(T... tArr) {
        Objects.requireNonNull(tArr, "items is null");
        return tArr.length == 0 ? empty() : tArr.length == 1 ? just(tArr[0]) : new C1388z0(tArr);
    }

    public static <T> AbstractC2120l<T> fromCallable(Callable<? extends T> callable) {
        Objects.requireNonNull(callable, "supplier is null");
        return new CallableC1240a1(callable);
    }

    public static <T> AbstractC2120l<T> fromFuture(Future<? extends T> future) {
        Objects.requireNonNull(future, "future is null");
        return new C1246b1(future, 0L, null);
    }

    public static <T> AbstractC2120l<T> fromIterable(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "source is null");
        return new C1252c1(iterable);
    }

    public static <T> AbstractC2120l<T> fromPublisher(InterfaceC1167a<? extends T> interfaceC1167a) {
        Objects.requireNonNull(interfaceC1167a, "publisher is null");
        return new C1258d1(interfaceC1167a);
    }

    public static <T> AbstractC2120l<T> generate(InterfaceC0446f<InterfaceC2113e<T>> interfaceC0446f) {
        Objects.requireNonNull(interfaceC0446f, "generator is null");
        return generate(C0870a.f1305h, new C1372w1(interfaceC0446f), C0870a.f1301d);
    }

    public static AbstractC2120l<Long> interval(long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1389z1(Math.max(0L, j7), Math.max(0L, j8), timeUnit, abstractC2128t);
    }

    public static AbstractC2120l<Long> intervalRange(long j7, long j8, long j9, long j10, TimeUnit timeUnit) {
        return intervalRange(j7, j8, j9, j10, timeUnit, C2012a.f5853a);
    }

    public static <T> AbstractC2120l<T> just(T t6) {
        Objects.requireNonNull(t6, "item is null");
        return new C1253c2(t6);
    }

    public static <T> AbstractC2120l<T> merge(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "sources is null");
        return new C1353t0(interfaceC2125q, C0870a.f1298a, false, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, bufferSize());
    }

    public static <T> AbstractC2120l<T> mergeArray(int i7, int i8, ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).flatMap((InterfaceC0454n) C0870a.f1298a, false, i7, i8);
    }

    public static <T> AbstractC2120l<T> mergeArrayDelayError(int i7, int i8, ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).flatMap((InterfaceC0454n) C0870a.f1298a, true, i7, i8);
    }

    public static <T> AbstractC2120l<T> mergeDelayError(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "sources is null");
        return new C1353t0(interfaceC2125q, C0870a.f1298a, true, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, bufferSize());
    }

    public static <T> AbstractC2120l<T> never() {
        return (AbstractC2120l<T>) C1313m2.f3390e;
    }

    public static AbstractC2120l<Integer> range(int i7, int i8) {
        if (i8 < 0) {
            throw new IllegalArgumentException(C0079a.m93a("count >= 0 required but it was ", i8));
        }
        if (i8 == 0) {
            return empty();
        }
        if (i8 == 1) {
            return just(Integer.valueOf(i7));
        }
        if (i7 + (i8 - 1) <= 2147483647L) {
            return new C1349s2(i7, i8);
        }
        throw new IllegalArgumentException("Integer overflow");
    }

    public static AbstractC2120l<Long> rangeLong(long j7, long j8) {
        if (j8 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("count >= 0 required but it was ", j8));
        }
        if (j8 == 0) {
            return empty();
        }
        if (j8 == 1) {
            return just(Long.valueOf(j7));
        }
        long j9 = (j8 - 1) + j7;
        if (j7 <= 0 || j9 >= 0) {
            return new C1355t2(j7, j8);
        }
        throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
    }

    public static <T> AbstractC2129u<Boolean> sequenceEqual(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC0444d<? super T, ? super T> interfaceC0444d, int i7) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC0444d, "isEqual is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1308l3(interfaceC2125q, interfaceC2125q2, interfaceC0444d, i7);
    }

    public static <T> AbstractC2120l<T> switchOnNext(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q, int i7) {
        Objects.requireNonNull(interfaceC2125q, "sources is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1374w3(interfaceC2125q, C0870a.f1298a, i7, false);
    }

    public static <T> AbstractC2120l<T> switchOnNextDelayError(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q, int i7) {
        Objects.requireNonNull(interfaceC2125q, "sources is null");
        C0871b.m677b(i7, "prefetch");
        return new C1374w3(interfaceC2125q, C0870a.f1298a, i7, true);
    }

    private AbstractC2120l<T> timeout0(long j7, TimeUnit timeUnit, InterfaceC2125q<? extends T> interfaceC2125q, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(timeUnit, "timeUnit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1291i4(this, j7, timeUnit, abstractC2128t, interfaceC2125q);
    }

    public static AbstractC2120l<Long> timer(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1297j4(Math.max(j7, 0L), timeUnit, abstractC2128t);
    }

    public static <T> AbstractC2120l<T> unsafeCreate(InterfaceC2125q<T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "onSubscribe is null");
        if (interfaceC2125q instanceof AbstractC2120l) {
            throw new IllegalArgumentException("unsafeCreate(Observable) should be upgraded");
        }
        return new C1257d0(interfaceC2125q);
    }

    public static <T, D> AbstractC2120l<T> using(Callable<? extends D> callable, InterfaceC0454n<? super D, ? extends InterfaceC2125q<? extends T>> interfaceC0454n, InterfaceC0446f<? super D> interfaceC0446f, boolean z6) {
        Objects.requireNonNull(callable, "resourceSupplier is null");
        Objects.requireNonNull(interfaceC0454n, "sourceSupplier is null");
        Objects.requireNonNull(interfaceC0446f, "disposer is null");
        return new C1321n4(callable, interfaceC0454n, interfaceC0446f, z6);
    }

    public static <T> AbstractC2120l<T> wrap(InterfaceC2125q<T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "source is null");
        return interfaceC2125q instanceof AbstractC2120l ? (AbstractC2120l) interfaceC2125q : new C1257d0(interfaceC2125q);
    }

    public static <T, R> AbstractC2120l<R> zip(Iterable<? extends InterfaceC2125q<? extends T>> iterable, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "zipper is null");
        Objects.requireNonNull(iterable, "sources is null");
        return new C1369v4(null, iterable, interfaceC0454n, bufferSize(), false);
    }

    public static <T, R> AbstractC2120l<R> zipArray(InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, boolean z6, int i7, ObservableSource<? extends T>... observableSourceArr) {
        if (observableSourceArr.length == 0) {
            return empty();
        }
        Objects.requireNonNull(interfaceC0454n, "zipper is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1369v4(observableSourceArr, null, interfaceC0454n, i7, z6);
    }

    public static <T, R> AbstractC2120l<R> zipIterable(Iterable<? extends InterfaceC2125q<? extends T>> iterable, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, boolean z6, int i7) {
        Objects.requireNonNull(interfaceC0454n, "zipper is null");
        Objects.requireNonNull(iterable, "sources is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1369v4(null, iterable, interfaceC0454n, i7, z6);
    }

    public final AbstractC2129u<Boolean> all(InterfaceC0455o<? super T> interfaceC0455o) {
        Objects.requireNonNull(interfaceC0455o, "predicate is null");
        return new C1274g(this, interfaceC0455o);
    }

    public final AbstractC2120l<T> ambWith(InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return ambArray(this, interfaceC2125q);
    }

    public final AbstractC2129u<Boolean> any(InterfaceC0455o<? super T> interfaceC0455o) {
        Objects.requireNonNull(interfaceC0455o, "predicate is null");
        return new C1292j(this, interfaceC0455o);
    }

    /* renamed from: as */
    public final <R> R m2559as(InterfaceC2121m<T, ? extends R> interfaceC2121m) {
        Objects.requireNonNull(interfaceC2121m, "converter is null");
        return interfaceC2121m.apply(this);
    }

    public final T blockingFirst() {
        C1000e c1000e = new C1000e(0);
        subscribe(c1000e);
        T tM1015a = c1000e.m1015a();
        if (tM1015a != null) {
            return tM1015a;
        }
        throw new NoSuchElementException();
    }

    public final void blockingForEach(InterfaceC0446f<? super T> interfaceC0446f) {
        Iterator<T> it = blockingIterable().iterator();
        while (it.hasNext()) {
            try {
                interfaceC0446f.accept(it.next());
            } catch (Throwable th) {
                C2074b.m2470J(th);
                ((InterfaceC2153b) it).dispose();
                throw C1774f.m1961d(th);
            }
        }
    }

    public final Iterable<T> blockingIterable() {
        return blockingIterable(bufferSize());
    }

    public final T blockingLast() {
        C1000e c1000e = new C1000e(1);
        subscribe(c1000e);
        T tM1015a = c1000e.m1015a();
        if (tM1015a != null) {
            return tM1015a;
        }
        throw new NoSuchElementException();
    }

    public final Iterable<T> blockingLatest() {
        return new C1250c(this);
    }

    public final Iterable<T> blockingMostRecent(T t6) {
        return new C1256d(this, t6);
    }

    public final Iterable<T> blockingNext() {
        return new C1262e(this);
    }

    public final T blockingSingle() {
        AbstractC2116h<T> abstractC2116hSingleElement = singleElement();
        Objects.requireNonNull(abstractC2116hSingleElement);
        C1001f c1001f = new C1001f();
        abstractC2116hSingleElement.mo2555b(c1001f);
        T t6 = (T) c1001f.m1017b();
        if (t6 != null) {
            return t6;
        }
        throw new NoSuchElementException();
    }

    public final void blockingSubscribe() throws InterruptedException {
        C1772d c1772d = new C1772d();
        InterfaceC0446f<Object> interfaceC0446f = C0870a.f1301d;
        C1010o c1010o = new C1010o(interfaceC0446f, c1772d, c1772d, interfaceC0446f);
        subscribe(c1010o);
        if (c1772d.getCount() != 0) {
            try {
                c1772d.await();
            } catch (InterruptedException e7) {
                c1010o.dispose();
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while waiting for subscription to complete.", e7);
            }
        }
        Throwable th = c1772d.f5052e;
        if (th != null) {
            throw C1774f.m1961d(th);
        }
    }

    public final <U extends Collection<? super T>> AbstractC2120l<U> buffer(long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t, Callable<U> callable) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        Objects.requireNonNull(callable, "bufferSupplier is null");
        return new C1322o(this, j7, j8, timeUnit, abstractC2128t, callable, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, false);
    }

    public final AbstractC2120l<T> cache() {
        return cacheWithInitialCapacity(16);
    }

    public final AbstractC2120l<T> cacheWithInitialCapacity(int i7) {
        C0871b.m677b(i7, "initialCapacity");
        return new C1328p(this, i7);
    }

    public final <U> AbstractC2120l<U> cast(Class<U> cls) {
        Objects.requireNonNull(cls, "clazz is null");
        return (AbstractC2120l<U>) map(new C0870a.l(cls));
    }

    public final <U> AbstractC2129u<U> collect(Callable<? extends U> callable, InterfaceC0442b<? super U, ? super T> interfaceC0442b) {
        Objects.requireNonNull(callable, "initialValueSupplier is null");
        Objects.requireNonNull(interfaceC0442b, "collector is null");
        return new C1340r(this, callable, interfaceC0442b);
    }

    public final <U> AbstractC2129u<U> collectInto(U u6, InterfaceC0442b<? super U, ? super T> interfaceC0442b) {
        Objects.requireNonNull(u6, "initialValue is null");
        return collect(new C0870a.u(u6), interfaceC0442b);
    }

    public final <R> AbstractC2120l<R> compose(InterfaceC2126r<? super T, ? extends R> interfaceC2126r) {
        Objects.requireNonNull(interfaceC2126r, "composer is null");
        return wrap(interfaceC2126r.apply(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> AbstractC2120l<R> concatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "prefetch");
        if (!(this instanceof InterfaceCallableC0953f)) {
            return new C1352t(this, interfaceC0454n, i7, 1);
        }
        Object objCall = ((InterfaceCallableC0953f) this).call();
        return objCall == null ? empty() : new C1284h3.b(objCall, interfaceC0454n);
    }

    public final AbstractC2110b concatMapCompletable(InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "capacityHint");
        return new C1204a(this, interfaceC0454n, 1, i7);
    }

    public final AbstractC2110b concatMapCompletableDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, boolean z6, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "prefetch");
        return new C1204a(this, interfaceC0454n, z6 ? 3 : 2, i7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> AbstractC2120l<R> concatMapDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7, boolean z6) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "prefetch");
        if (!(this instanceof InterfaceCallableC0953f)) {
            return new C1352t(this, interfaceC0454n, i7, z6 ? 3 : 2);
        }
        Object objCall = ((InterfaceCallableC0953f) this).call();
        return objCall == null ? empty() : new C1284h3.b(objCall, interfaceC0454n);
    }

    public final <R> AbstractC2120l<R> concatMapEager(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7, int i8) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "maxConcurrency");
        C0871b.m677b(i8, "prefetch");
        return new C1358u(this, interfaceC0454n, 1, i7, i8);
    }

    public final <R> AbstractC2120l<R> concatMapEagerDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7, int i8, boolean z6) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "maxConcurrency");
        C0871b.m677b(i8, "prefetch");
        return new C1358u(this, interfaceC0454n, z6 ? 3 : 2, i7, i8);
    }

    public final <U> AbstractC2120l<U> concatMapIterable(InterfaceC0454n<? super T, ? extends Iterable<? extends U>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1383y0(this, interfaceC0454n);
    }

    public final <R> AbstractC2120l<R> concatMapMaybe(InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "prefetch");
        return new C1205b(this, interfaceC0454n, 1, i7);
    }

    public final <R> AbstractC2120l<R> concatMapMaybeDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n, boolean z6, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "prefetch");
        return new C1205b(this, interfaceC0454n, z6 ? 3 : 2, i7);
    }

    public final <R> AbstractC2120l<R> concatMapSingle(InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "prefetch");
        return new C1206c(this, interfaceC0454n, 1, i7);
    }

    public final <R> AbstractC2120l<R> concatMapSingleDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n, boolean z6, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "prefetch");
        return new C1206c(this, interfaceC0454n, z6 ? 3 : 2, i7);
    }

    public final AbstractC2120l<T> concatWith(InterfaceC2112d interfaceC2112d) {
        Objects.requireNonNull(interfaceC2112d, "other is null");
        return new C1364v(this, interfaceC2112d);
    }

    public final AbstractC2129u<Boolean> contains(Object obj) {
        Objects.requireNonNull(obj, "element is null");
        return any(new C0870a.q(obj));
    }

    public final AbstractC2129u<Long> count() {
        return new C1387z(this);
    }

    public final AbstractC2120l<T> debounce(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1251c0(this, j7, timeUnit, abstractC2128t);
    }

    public final AbstractC2120l<T> defaultIfEmpty(T t6) {
        Objects.requireNonNull(t6, "defaultItem is null");
        return switchIfEmpty(just(t6));
    }

    public final AbstractC2120l<T> delay(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1263e0(this, j7, timeUnit, abstractC2128t, z6);
    }

    public final <U> AbstractC2120l<T> delaySubscription(InterfaceC2125q<U> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return new C1269f0(this, interfaceC2125q);
    }

    public final <R> AbstractC2120l<R> dematerialize(InterfaceC0454n<? super T, C2119k<R>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "selector is null");
        return new C1275g0(this, interfaceC0454n);
    }

    public final <K> AbstractC2120l<T> distinct(InterfaceC0454n<? super T, K> interfaceC0454n, Callable<? extends Collection<? super K>> callable) {
        Objects.requireNonNull(interfaceC0454n, "keySelector is null");
        Objects.requireNonNull(callable, "collectionSupplier is null");
        return new C1287i0(this, interfaceC0454n, callable);
    }

    public final AbstractC2120l<T> distinctUntilChanged(InterfaceC0444d<? super T, ? super T> interfaceC0444d) {
        Objects.requireNonNull(interfaceC0444d, "comparer is null");
        return new C1293j0(this, C0870a.f1298a, interfaceC0444d);
    }

    public final AbstractC2120l<T> doAfterNext(InterfaceC0446f<? super T> interfaceC0446f) {
        Objects.requireNonNull(interfaceC0446f, "onAfterNext is null");
        return new C1299k0(this, interfaceC0446f);
    }

    public final AbstractC2120l<T> doAfterTerminate(InterfaceC0441a interfaceC0441a) {
        Objects.requireNonNull(interfaceC0441a, "onFinally is null");
        InterfaceC0446f<? super T> interfaceC0446f = C0870a.f1301d;
        return doOnEach(interfaceC0446f, interfaceC0446f, C0870a.f1300c, interfaceC0441a);
    }

    public final AbstractC2120l<T> doFinally(InterfaceC0441a interfaceC0441a) {
        Objects.requireNonNull(interfaceC0441a, "onFinally is null");
        return new C1305l0(this, interfaceC0441a);
    }

    public final AbstractC2120l<T> doOnComplete(InterfaceC0441a interfaceC0441a) {
        InterfaceC0446f<? super T> interfaceC0446f = C0870a.f1301d;
        return doOnEach(interfaceC0446f, interfaceC0446f, interfaceC0441a, C0870a.f1300c);
    }

    public final AbstractC2120l<T> doOnDispose(InterfaceC0441a interfaceC0441a) {
        return doOnLifecycle(C0870a.f1301d, interfaceC0441a);
    }

    public final AbstractC2120l<T> doOnEach(InterfaceC0446f<? super C2119k<T>> interfaceC0446f) {
        Objects.requireNonNull(interfaceC0446f, "onNotification is null");
        return doOnEach(new C0870a.a0(interfaceC0446f), new C0870a.z(interfaceC0446f), new C0870a.y(interfaceC0446f), C0870a.f1300c);
    }

    public final AbstractC2120l<T> doOnError(InterfaceC0446f<? super Throwable> interfaceC0446f) {
        InterfaceC0446f<? super T> interfaceC0446f2 = C0870a.f1301d;
        InterfaceC0441a interfaceC0441a = C0870a.f1300c;
        return doOnEach(interfaceC0446f2, interfaceC0446f, interfaceC0441a, interfaceC0441a);
    }

    public final AbstractC2120l<T> doOnLifecycle(InterfaceC0446f<? super InterfaceC2153b> interfaceC0446f, InterfaceC0441a interfaceC0441a) {
        Objects.requireNonNull(interfaceC0446f, "onSubscribe is null");
        Objects.requireNonNull(interfaceC0441a, "onDispose is null");
        return new C1317n0(this, interfaceC0446f, interfaceC0441a);
    }

    public final AbstractC2120l<T> doOnNext(InterfaceC0446f<? super T> interfaceC0446f) {
        InterfaceC0446f<? super Throwable> interfaceC0446f2 = C0870a.f1301d;
        InterfaceC0441a interfaceC0441a = C0870a.f1300c;
        return doOnEach(interfaceC0446f, interfaceC0446f2, interfaceC0441a, interfaceC0441a);
    }

    public final AbstractC2120l<T> doOnSubscribe(InterfaceC0446f<? super InterfaceC2153b> interfaceC0446f) {
        return doOnLifecycle(interfaceC0446f, C0870a.f1300c);
    }

    public final AbstractC2120l<T> doOnTerminate(InterfaceC0441a interfaceC0441a) {
        Objects.requireNonNull(interfaceC0441a, "onTerminate is null");
        return doOnEach(C0870a.f1301d, new C0870a.a(interfaceC0441a), interfaceC0441a, C0870a.f1300c);
    }

    public final AbstractC2129u<T> elementAt(long j7, T t6) {
        if (j7 < 0) {
            throw new IndexOutOfBoundsException(C0534b.m341a("index >= 0 required but it was ", j7));
        }
        Objects.requireNonNull(t6, "defaultItem is null");
        return new C1335q0(this, j7, t6);
    }

    public final AbstractC2129u<T> elementAtOrError(long j7) {
        if (j7 >= 0) {
            return new C1335q0(this, j7, null);
        }
        throw new IndexOutOfBoundsException(C0534b.m341a("index >= 0 required but it was ", j7));
    }

    public final AbstractC2120l<T> filter(InterfaceC0455o<? super T> interfaceC0455o) {
        Objects.requireNonNull(interfaceC0455o, "predicate is null");
        return new C1347s0(this, interfaceC0455o);
    }

    public final AbstractC2129u<T> first(T t6) {
        return elementAt(0L, t6);
    }

    public final AbstractC2116h<T> firstElement() {
        return elementAt(0L);
    }

    public final AbstractC2129u<T> firstOrError() {
        return elementAtOrError(0L);
    }

    public final <U, R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> interfaceC0454n, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c, boolean z6, int i7, int i8) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        Objects.requireNonNull(interfaceC0443c, "combiner is null");
        return flatMap(new C1324o1(interfaceC0443c, interfaceC0454n), z6, i7, i8);
    }

    public final AbstractC2110b flatMapCompletable(InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, boolean z6) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1365v0(this, interfaceC0454n, z6);
    }

    public final <U> AbstractC2120l<U> flatMapIterable(InterfaceC0454n<? super T, ? extends Iterable<? extends U>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1383y0(this, interfaceC0454n);
    }

    public final <R> AbstractC2120l<R> flatMapMaybe(InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n, boolean z6) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1371w0(this, interfaceC0454n, z6);
    }

    public final <R> AbstractC2120l<R> flatMapSingle(InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n, boolean z6) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1377x0(this, interfaceC0454n, z6);
    }

    public final InterfaceC2153b forEach(InterfaceC0446f<? super T> interfaceC0446f) {
        return subscribe(interfaceC0446f);
    }

    public final InterfaceC2153b forEachWhile(InterfaceC0455o<? super T> interfaceC0455o, InterfaceC0446f<? super Throwable> interfaceC0446f, InterfaceC0441a interfaceC0441a) {
        Objects.requireNonNull(interfaceC0455o, "onNext is null");
        Objects.requireNonNull(interfaceC0446f, "onError is null");
        Objects.requireNonNull(interfaceC0441a, "onComplete is null");
        C1006k c1006k = new C1006k(interfaceC0455o, interfaceC0446f, interfaceC0441a);
        subscribe(c1006k);
        return c1006k;
    }

    public final <K, V> AbstractC2120l<AbstractC1238a> groupBy(InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2, boolean z6, int i7) {
        Objects.requireNonNull(interfaceC0454n, "keySelector is null");
        Objects.requireNonNull(interfaceC0454n2, "valueSelector is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1270f1(this, interfaceC0454n, interfaceC0454n2, i7, z6);
    }

    public final <TRight, TLeftEnd, TRightEnd, R> AbstractC2120l<R> groupJoin(InterfaceC2125q<? extends TRight> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<TLeftEnd>> interfaceC0454n, InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> interfaceC0454n2, InterfaceC0443c<? super T, ? super AbstractC2120l<TRight>, ? extends R> interfaceC0443c) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        Objects.requireNonNull(interfaceC0454n, "leftEnd is null");
        Objects.requireNonNull(interfaceC0454n2, "rightEnd is null");
        Objects.requireNonNull(interfaceC0443c, "resultSelector is null");
        return new C1276g1(this, interfaceC2125q, interfaceC0454n, interfaceC0454n2, interfaceC0443c);
    }

    public final AbstractC2120l<T> hide() {
        return new C1282h1(this);
    }

    public final AbstractC2110b ignoreElements() {
        return new C1294j1(this);
    }

    public final AbstractC2129u<Boolean> isEmpty() {
        return all(C0870a.f1304g);
    }

    public final <TRight, TLeftEnd, TRightEnd, R> AbstractC2120l<R> join(InterfaceC2125q<? extends TRight> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<TLeftEnd>> interfaceC0454n, InterfaceC0454n<? super TRight, ? extends InterfaceC2125q<TRightEnd>> interfaceC0454n2, InterfaceC0443c<? super T, ? super TRight, ? extends R> interfaceC0443c) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        Objects.requireNonNull(interfaceC0454n, "leftEnd is null");
        Objects.requireNonNull(interfaceC0454n2, "rightEnd is null");
        Objects.requireNonNull(interfaceC0443c, "resultSelector is null");
        return new C1247b2(this, interfaceC2125q, interfaceC0454n, interfaceC0454n2, interfaceC0443c);
    }

    public final AbstractC2129u<T> last(T t6) {
        Objects.requireNonNull(t6, "defaultItem is null");
        return new C1265e2(this, t6);
    }

    public final AbstractC2116h<T> lastElement() {
        return new C1259d2(this);
    }

    public final AbstractC2129u<T> lastOrError() {
        return new C1265e2(this, null);
    }

    public final <R> AbstractC2120l<R> lift(InterfaceC2124p<? extends R, ? super T> interfaceC2124p) {
        Objects.requireNonNull(interfaceC2124p, "lifter is null");
        return new C1271f2(this, interfaceC2124p);
    }

    public final <R> AbstractC2120l<R> map(InterfaceC0454n<? super T, ? extends R> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1277g2(this, interfaceC0454n);
    }

    public final AbstractC2120l<C2119k<T>> materialize() {
        return new C1289i2(this);
    }

    public final AbstractC2120l<T> mergeWith(InterfaceC2112d interfaceC2112d) {
        Objects.requireNonNull(interfaceC2112d, "other is null");
        return new C1295j2(this, interfaceC2112d);
    }

    public final AbstractC2120l<T> observeOn(AbstractC2128t abstractC2128t, boolean z6, int i7) {
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1319n2(this, abstractC2128t, z6, i7);
    }

    public final <U> AbstractC2120l<U> ofType(Class<U> cls) {
        Objects.requireNonNull(cls, "clazz is null");
        return filter(new C0870a.m(cls)).cast(cls);
    }

    public final AbstractC2120l<T> onErrorResumeNext(InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends T>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "resumeFunction is null");
        return new C1325o2(this, interfaceC0454n, false);
    }

    public final AbstractC2120l<T> onErrorReturn(InterfaceC0454n<? super Throwable, ? extends T> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "valueSupplier is null");
        return new C1331p2(this, interfaceC0454n);
    }

    public final AbstractC2120l<T> onErrorReturnItem(T t6) {
        Objects.requireNonNull(t6, "item is null");
        return onErrorReturn(new C0870a.u(t6));
    }

    public final AbstractC2120l<T> onExceptionResumeNext(InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "next is null");
        return new C1325o2(this, new C0870a.u(interfaceC2125q), true);
    }

    public final AbstractC2120l<T> onTerminateDetach() {
        return new C1281h0(this);
    }

    public final <R> AbstractC2120l<R> publish(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "selector is null");
        return new C1343r2(this, interfaceC0454n);
    }

    public final AbstractC2116h<T> reduce(InterfaceC0443c<T, T, T> interfaceC0443c) {
        Objects.requireNonNull(interfaceC0443c, "reducer is null");
        return new C1361u2(this, interfaceC0443c);
    }

    public final <R> AbstractC2129u<R> reduceWith(Callable<R> callable, InterfaceC0443c<R, ? super T, R> interfaceC0443c) {
        Objects.requireNonNull(callable, "seedSupplier is null");
        Objects.requireNonNull(interfaceC0443c, "reducer is null");
        return new C1373w2(this, callable, interfaceC0443c);
    }

    public final AbstractC2120l<T> repeat() {
        return repeat(RecyclerView.FOREVER_NS);
    }

    public final AbstractC2120l<T> repeatUntil(InterfaceC0445e interfaceC0445e) {
        Objects.requireNonNull(interfaceC0445e, "stop is null");
        return new C1390z2(this, interfaceC0445e);
    }

    public final AbstractC2120l<T> repeatWhen(InterfaceC0454n<? super AbstractC2120l<Object>, ? extends InterfaceC2125q<?>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "handler is null");
        return new C1242a3(this, interfaceC0454n);
    }

    public final AbstractC1837a<T> replay(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return C1248b3.m1474c(this, new C1248b3.l(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, j7, timeUnit, abstractC2128t));
    }

    public final AbstractC2120l<T> retry(long j7, InterfaceC0455o<? super Throwable> interfaceC0455o) {
        if (j7 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("times >= 0 required but it was ", j7));
        }
        Objects.requireNonNull(interfaceC0455o, "predicate is null");
        return new C1260d3(this, j7, interfaceC0455o);
    }

    public final AbstractC2120l<T> retryUntil(InterfaceC0445e interfaceC0445e) {
        Objects.requireNonNull(interfaceC0445e, "stop is null");
        return retry(RecyclerView.FOREVER_NS, new C0870a.k(interfaceC0445e));
    }

    public final AbstractC2120l<T> retryWhen(InterfaceC0454n<? super AbstractC2120l<Throwable>, ? extends InterfaceC2125q<?>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "handler is null");
        return new C1266e3(this, interfaceC0454n);
    }

    public final void safeSubscribe(InterfaceC2127s<? super T> interfaceC2127s) {
        Objects.requireNonNull(interfaceC2127s, "observer is null");
        if (interfaceC2127s instanceof C1881d) {
            subscribe(interfaceC2127s);
        } else {
            subscribe(new C1881d(interfaceC2127s));
        }
    }

    public final AbstractC2120l<T> sample(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1272f3(this, j7, timeUnit, abstractC2128t, false);
    }

    public final AbstractC2120l<T> scan(InterfaceC0443c<T, T, T> interfaceC0443c) {
        Objects.requireNonNull(interfaceC0443c, "accumulator is null");
        return new C1290i3(this, interfaceC0443c);
    }

    public final <R> AbstractC2120l<R> scanWith(Callable<R> callable, InterfaceC0443c<R, ? super T, R> interfaceC0443c) {
        Objects.requireNonNull(callable, "seedSupplier is null");
        Objects.requireNonNull(interfaceC0443c, "accumulator is null");
        return new C1296j3(this, callable, interfaceC0443c);
    }

    public final AbstractC2120l<T> serialize() {
        return new C1314m3(this);
    }

    public final AbstractC2120l<T> share() {
        AbstractC1837a<T> abstractC1837aPublish = publish();
        Objects.requireNonNull(abstractC1837aPublish);
        return new C1379x2(abstractC1837aPublish);
    }

    public final AbstractC2129u<T> single(T t6) {
        Objects.requireNonNull(t6, "defaultItem is null");
        return new C1326o3(this, t6);
    }

    public final AbstractC2116h<T> singleElement() {
        return new C1320n3(this);
    }

    public final AbstractC2129u<T> singleOrError() {
        return new C1326o3(this, null);
    }

    public final AbstractC2120l<T> skip(long j7) {
        return j7 <= 0 ? this : new C1332p3(this, j7);
    }

    public final AbstractC2120l<T> skipLast(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6, int i7) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1344r3(this, j7, timeUnit, abstractC2128t, i7 << 1, z6);
    }

    public final <U> AbstractC2120l<T> skipUntil(InterfaceC2125q<U> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return new C1350s3(this, interfaceC2125q);
    }

    public final AbstractC2120l<T> skipWhile(InterfaceC0455o<? super T> interfaceC0455o) {
        Objects.requireNonNull(interfaceC0455o, "predicate is null");
        return new C1356t3(this, interfaceC0455o);
    }

    public final AbstractC2120l<T> sorted(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator, "sortFunction is null");
        AbstractC2129u<List<T>> list = toList();
        Objects.requireNonNull(list);
        return (list instanceof InterfaceC0949b ? ((InterfaceC0949b) list).mo860a() : new C1435b(list)).map(new C0870a.v(comparator)).flatMapIterable(C0870a.f1298a);
    }

    public final AbstractC2120l<T> startWith(T t6) {
        Objects.requireNonNull(t6, "item is null");
        return concatArray(just(t6), this);
    }

    public final AbstractC2120l<T> startWithArray(T... tArr) {
        AbstractC2120l abstractC2120lFromArray = fromArray(tArr);
        return abstractC2120lFromArray == empty() ? this : concatArray(abstractC2120lFromArray, this);
    }

    public final InterfaceC2153b subscribe(InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2, InterfaceC0441a interfaceC0441a, InterfaceC0446f<? super InterfaceC2153b> interfaceC0446f3) {
        Objects.requireNonNull(interfaceC0446f, "onNext is null");
        Objects.requireNonNull(interfaceC0446f2, "onError is null");
        Objects.requireNonNull(interfaceC0441a, "onComplete is null");
        Objects.requireNonNull(interfaceC0446f3, "onSubscribe is null");
        C1010o c1010o = new C1010o(interfaceC0446f, interfaceC0446f2, interfaceC0441a, interfaceC0446f3);
        subscribe(c1010o);
        return c1010o;
    }

    public abstract void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s);

    public final AbstractC2120l<T> subscribeOn(AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1362u3(this, abstractC2128t);
    }

    public final <E extends InterfaceC2127s<? super T>> E subscribeWith(E e7) {
        subscribe(e7);
        return e7;
    }

    public final AbstractC2120l<T> switchIfEmpty(InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return new C1368v3(this, interfaceC2125q);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> AbstractC2120l<R> switchMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "bufferSize");
        if (!(this instanceof InterfaceCallableC0953f)) {
            return new C1374w3(this, interfaceC0454n, i7, false);
        }
        Object objCall = ((InterfaceCallableC0953f) this).call();
        return objCall == null ? empty() : new C1284h3.b(objCall, interfaceC0454n);
    }

    public final AbstractC2110b switchMapCompletable(InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1207d(this, interfaceC0454n, false);
    }

    public final AbstractC2110b switchMapCompletableDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1207d(this, interfaceC0454n, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> AbstractC2120l<R> switchMapDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "bufferSize");
        if (!(this instanceof InterfaceCallableC0953f)) {
            return new C1374w3(this, interfaceC0454n, i7, true);
        }
        Object objCall = ((InterfaceCallableC0953f) this).call();
        return objCall == null ? empty() : new C1284h3.b(objCall, interfaceC0454n);
    }

    public final <R> AbstractC2120l<R> switchMapMaybe(InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1208e(this, interfaceC0454n, false);
    }

    public final <R> AbstractC2120l<R> switchMapMaybeDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1208e(this, interfaceC0454n, true);
    }

    public final <R> AbstractC2120l<R> switchMapSingle(InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1209f(this, interfaceC0454n, false);
    }

    public final <R> AbstractC2120l<R> switchMapSingleDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        return new C1209f(this, interfaceC0454n, true);
    }

    public final AbstractC2120l<T> take(long j7) {
        if (j7 >= 0) {
            return new C1380x3(this, j7);
        }
        throw new IllegalArgumentException(C0534b.m341a("count >= 0 required but it was ", j7));
    }

    public final AbstractC2120l<T> takeLast(long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6, int i7) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        C0871b.m677b(i7, "bufferSize");
        if (j7 >= 0) {
            return new C1243a4(this, j7, j8, timeUnit, abstractC2128t, i7, z6);
        }
        throw new IndexOutOfBoundsException(C0534b.m341a("count >= 0 required but it was ", j7));
    }

    public final AbstractC2120l<T> takeUntil(InterfaceC0455o<? super T> interfaceC0455o) {
        Objects.requireNonNull(interfaceC0455o, "stopPredicate is null");
        return new C1255c4(this, interfaceC0455o);
    }

    public final AbstractC2120l<T> takeWhile(InterfaceC0455o<? super T> interfaceC0455o) {
        Objects.requireNonNull(interfaceC0455o, "predicate is null");
        return new C1261d4(this, interfaceC0455o);
    }

    public final C1883f<T> test() {
        C1883f<T> c1883f = new C1883f<>();
        subscribe(c1883f);
        return c1883f;
    }

    public final AbstractC2120l<T> throttleFirst(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1267e4(this, j7, timeUnit, abstractC2128t);
    }

    public final AbstractC2120l<T> throttleLast(long j7, TimeUnit timeUnit) {
        return sample(j7, timeUnit);
    }

    public final AbstractC2120l<T> throttleLatest(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1273f4(this, j7, timeUnit, abstractC2128t, z6);
    }

    public final AbstractC2120l<T> throttleWithTimeout(long j7, TimeUnit timeUnit) {
        return debounce(j7, timeUnit);
    }

    public final AbstractC2120l<C2013b<T>> timeInterval(TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1279g4(this, timeUnit, abstractC2128t);
    }

    public final AbstractC2120l<T> timeout(long j7, TimeUnit timeUnit, InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return timeout0(j7, timeUnit, interfaceC2125q, C2012a.f5853a);
    }

    public final AbstractC2120l<C2013b<T>> timestamp(TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return (AbstractC2120l<C2013b<T>>) map(new C0870a.d0(timeUnit, abstractC2128t));
    }

    /* renamed from: to */
    public final <R> R m2560to(InterfaceC0454n<? super AbstractC2120l<T>, R> interfaceC0454n) {
        try {
            Objects.requireNonNull(interfaceC0454n, "converter is null");
            return interfaceC0454n.apply(this);
        } catch (Throwable th) {
            C2074b.m2470J(th);
            throw C1774f.m1961d(th);
        }
    }

    public final AbstractC2114f<T> toFlowable(EnumC2109a enumC2109a) {
        C1087b c1087b = new C1087b(this);
        int iOrdinal = enumC2109a.ordinal();
        if (iOrdinal == 0) {
            return c1087b;
        }
        if (iOrdinal == 1) {
            return new C1091f(c1087b);
        }
        if (iOrdinal == 3) {
            return new C1090e(c1087b);
        }
        if (iOrdinal == 4) {
            return new C1092g(c1087b);
        }
        int i7 = AbstractC2114f.f6246e;
        C0871b.m677b(i7, "capacity");
        return new C1089d(c1087b, i7, true, false, C0870a.f1300c);
    }

    public final Future<T> toFuture() {
        return (Future) subscribeWith(new FutureC1007l());
    }

    public final <U extends Collection<? super T>> AbstractC2129u<U> toList(Callable<U> callable) {
        Objects.requireNonNull(callable, "collectionSupplier is null");
        return new C1309l4(this, callable);
    }

    public final <K> AbstractC2129u<Map<K, T>> toMap(InterfaceC0454n<? super T, ? extends K> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "keySelector is null");
        return (AbstractC2129u<Map<K, T>>) collect(EnumC1775g.INSTANCE, new C0870a.e0(interfaceC0454n));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <K, V> AbstractC2129u<Map<K, Collection<V>>> toMultimap(InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2, Callable<? extends Map<K, Collection<V>>> callable, InterfaceC0454n<? super K, ? extends Collection<? super V>> interfaceC0454n3) {
        Objects.requireNonNull(interfaceC0454n, "keySelector is null");
        Objects.requireNonNull(interfaceC0454n2, "valueSelector is null");
        Objects.requireNonNull(callable, "mapSupplier is null");
        Objects.requireNonNull(interfaceC0454n3, "collectionFactory is null");
        return (AbstractC2129u<Map<K, Collection<V>>>) collect(callable, new C0870a.g0(interfaceC0454n3, interfaceC0454n2, interfaceC0454n));
    }

    public final AbstractC2129u<List<T>> toSortedList(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator, "comparator is null");
        AbstractC2129u<List<T>> list = toList();
        C0870a.v vVar = new C0870a.v(comparator);
        Objects.requireNonNull(list);
        return new C1434a(list, vVar);
    }

    public final AbstractC2120l<T> unsubscribeOn(AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1315m4(this, abstractC2128t);
    }

    public final <B> AbstractC2120l<AbstractC2120l<T>> window(Callable<? extends InterfaceC2125q<B>> callable, int i7) {
        Objects.requireNonNull(callable, "boundary is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1345r4(this, callable, i7);
    }

    public final <R> AbstractC2120l<R> withLatestFrom(Iterable<? extends InterfaceC2125q<?>> iterable, InterfaceC0454n<? super Object[], R> interfaceC0454n) {
        Objects.requireNonNull(iterable, "others is null");
        Objects.requireNonNull(interfaceC0454n, "combiner is null");
        return new C1363u4(this, iterable, interfaceC0454n);
    }

    public final <U, R> AbstractC2120l<R> zipWith(Iterable<U> iterable, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c) {
        Objects.requireNonNull(iterable, "other is null");
        Objects.requireNonNull(interfaceC0443c, "zipper is null");
        return new C1375w4(this, iterable, interfaceC0443c);
    }

    public static <T> AbstractC2120l<T> concatArrayEager(int i7, int i8, ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).concatMapEagerDelayError(C0870a.f1298a, i7, i8, false);
    }

    public static <T> AbstractC2120l<T> concatArrayEagerDelayError(int i7, int i8, ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).concatMapEagerDelayError(C0870a.f1298a, i7, i8, true);
    }

    public static <T> AbstractC2120l<T> concatEager(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q, int i7, int i8) {
        return wrap(interfaceC2125q).concatMapEager(C0870a.f1298a, i7, i8);
    }

    public static <T> AbstractC2120l<T> mergeArray(ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).flatMap(C0870a.f1298a, observableSourceArr.length);
    }

    public static <T> AbstractC2120l<T> mergeArrayDelayError(ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).flatMap((InterfaceC0454n) C0870a.f1298a, true, observableSourceArr.length);
    }

    public final Iterable<T> blockingIterable(int i7) {
        C0871b.m677b(i7, "bufferSize");
        return new C1244b(this, i7);
    }

    public final AbstractC2120l<T> repeat(long j7) {
        if (j7 >= 0) {
            return j7 == 0 ? empty() : new C1385y2(this, j7);
        }
        throw new IllegalArgumentException(C0534b.m341a("times >= 0 required but it was ", j7));
    }

    public final AbstractC2120l<T> skip(long j7, TimeUnit timeUnit) {
        return skipUntil(timer(j7, timeUnit));
    }

    public final AbstractC2120l<T> throttleLast(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return sample(j7, timeUnit, abstractC2128t);
    }

    public final AbstractC2120l<T> throttleWithTimeout(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return debounce(j7, timeUnit, abstractC2128t);
    }

    public static <T> AbstractC2120l<T> concat(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q, int i7) {
        Objects.requireNonNull(interfaceC2125q, "sources is null");
        C0871b.m677b(i7, "prefetch");
        return new C1352t(interfaceC2125q, C0870a.f1298a, i7, 1);
    }

    public static <T> AbstractC2120l<T> concatDelayError(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q, int i7, boolean z6) {
        Objects.requireNonNull(interfaceC2125q, "sources is null");
        C0871b.m677b(i7, "prefetch is null");
        return new C1352t(interfaceC2125q, C0870a.f1298a, i7, z6 ? 3 : 2);
    }

    public static <T> AbstractC2120l<T> concatEager(Iterable<? extends InterfaceC2125q<? extends T>> iterable) {
        return concatEager(iterable, bufferSize(), bufferSize());
    }

    public static <T> AbstractC2120l<T> fromFuture(Future<? extends T> future, long j7, TimeUnit timeUnit) {
        Objects.requireNonNull(future, "future is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        return new C1246b1(future, j7, timeUnit);
    }

    public static AbstractC2120l<Long> intervalRange(long j7, long j8, long j9, long j10, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        if (j8 < 0) {
            throw new IllegalArgumentException(C0534b.m341a("count >= 0 required but it was ", j8));
        }
        if (j8 == 0) {
            return empty().delay(j9, timeUnit, abstractC2128t);
        }
        long j11 = (j8 - 1) + j7;
        if (j7 > 0 && j11 < 0) {
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1241a2(j7, j11, Math.max(0L, j9), Math.max(0L, j10), timeUnit, abstractC2128t);
    }

    public static <T> AbstractC2120l<T> just(T t6, T t7) {
        Objects.requireNonNull(t6, "item1 is null");
        Objects.requireNonNull(t7, "item2 is null");
        return fromArray(t6, t7);
    }

    public static <T> AbstractC2120l<T> merge(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q, int i7) {
        Objects.requireNonNull(interfaceC2125q, "sources is null");
        C0871b.m677b(i7, "maxConcurrency");
        return new C1353t0(interfaceC2125q, C0870a.f1298a, false, i7, bufferSize());
    }

    public static <T> AbstractC2120l<T> mergeDelayError(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q, int i7) {
        Objects.requireNonNull(interfaceC2125q, "sources is null");
        C0871b.m677b(i7, "maxConcurrency");
        return new C1353t0(interfaceC2125q, C0870a.f1298a, true, i7, bufferSize());
    }

    public final <U> AbstractC2120l<U> concatMapIterable(InterfaceC0454n<? super T, ? extends Iterable<? extends U>> interfaceC0454n, int i7) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "prefetch");
        return (AbstractC2120l<U>) concatMap(new C1312m1(interfaceC0454n), i7);
    }

    public final AbstractC2120l<T> concatWith(InterfaceC2118j<? extends T> interfaceC2118j) {
        Objects.requireNonNull(interfaceC2118j, "other is null");
        return new C1370w(this, interfaceC2118j);
    }

    public final AbstractC2120l<T> delaySubscription(long j7, TimeUnit timeUnit) {
        return delaySubscription(j7, timeUnit, C2012a.f5853a);
    }

    @Deprecated
    public final <T2> AbstractC2120l<T2> dematerialize() {
        return new C1275g0(this, C0870a.f1298a);
    }

    public final <K> AbstractC2120l<T> distinctUntilChanged(InterfaceC0454n<? super T, K> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "keySelector is null");
        return new C1293j0(this, interfaceC0454n, C0871b.f1338a);
    }

    public final AbstractC2110b flatMapCompletable(InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n) {
        return flatMapCompletable(interfaceC0454n, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <U, V> AbstractC2120l<V> flatMapIterable(InterfaceC0454n<? super T, ? extends Iterable<? extends U>> interfaceC0454n, InterfaceC0443c<? super T, ? super U, ? extends V> interfaceC0443c) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        Objects.requireNonNull(interfaceC0443c, "resultSelector is null");
        return (AbstractC2120l<V>) flatMap(new C1312m1(interfaceC0454n), interfaceC0443c, false, bufferSize(), bufferSize());
    }

    public final <R> AbstractC2120l<R> flatMapMaybe(InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n) {
        return flatMapMaybe(interfaceC0454n, false);
    }

    public final <R> AbstractC2120l<R> flatMapSingle(InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n) {
        return flatMapSingle(interfaceC0454n, false);
    }

    public final AbstractC2120l<T> mergeWith(InterfaceC2118j<? extends T> interfaceC2118j) {
        Objects.requireNonNull(interfaceC2118j, "other is null");
        return new C1301k2(this, interfaceC2118j);
    }

    public final AbstractC2120l<T> onErrorResumeNext(InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "next is null");
        return onErrorResumeNext(new C0870a.u(interfaceC2125q));
    }

    public final AbstractC1837a<T> publish() {
        AtomicReference atomicReference = new AtomicReference();
        return new C1337q2(new C1337q2.c(atomicReference), this, atomicReference);
    }

    public final <R> AbstractC2129u<R> reduce(R r6, InterfaceC0443c<R, ? super T, R> interfaceC0443c) {
        Objects.requireNonNull(r6, "seed is null");
        Objects.requireNonNull(interfaceC0443c, "reducer is null");
        return new C1367v2(this, r6, interfaceC0443c);
    }

    public final <R> AbstractC2120l<R> scan(R r6, InterfaceC0443c<R, ? super T, R> interfaceC0443c) {
        Objects.requireNonNull(r6, "initialValue is null");
        return scanWith(new C0870a.u(r6), interfaceC0443c);
    }

    public final AbstractC2120l<T> skip(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return skipUntil(timer(j7, timeUnit, abstractC2128t));
    }

    public final AbstractC2120l<T> startWith(InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return concatArray(interfaceC2125q, this);
    }

    public final AbstractC2120l<T> take(long j7, TimeUnit timeUnit) {
        return takeUntil(timer(j7, timeUnit));
    }

    public final <U> AbstractC2120l<T> takeUntil(InterfaceC2125q<U> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return new C1249b4(this, interfaceC2125q);
    }

    public final C1883f<T> test(boolean z6) {
        C1883f<T> c1883f = new C1883f<>();
        if (z6) {
            EnumC0515c.m323a(c1883f.f5479k);
        }
        subscribe(c1883f);
        return c1883f;
    }

    public final AbstractC2129u<List<T>> toList() {
        return toList(16);
    }

    public static <T> AbstractC2120l<T> concatEager(Iterable<? extends InterfaceC2125q<? extends T>> iterable, int i7, int i8) {
        return fromIterable(iterable).concatMapEagerDelayError(C0870a.f1298a, i7, i8, false);
    }

    public static <T> AbstractC2120l<T> error(Callable<? extends Throwable> callable) {
        Objects.requireNonNull(callable, "errorSupplier is null");
        return new C1257d0(callable, 1);
    }

    public static AbstractC2120l<Long> interval(long j7, long j8, TimeUnit timeUnit) {
        return interval(j7, j8, timeUnit, C2012a.f5853a);
    }

    public static <T> AbstractC2120l<T> switchOnNext(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q) {
        return switchOnNext(interfaceC2125q, bufferSize());
    }

    public static <T> AbstractC2120l<T> switchOnNextDelayError(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q) {
        return switchOnNextDelayError(interfaceC2125q, bufferSize());
    }

    private <U, V> AbstractC2120l<T> timeout0(InterfaceC2125q<U> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<V>> interfaceC0454n, InterfaceC2125q<? extends T> interfaceC2125q2) {
        Objects.requireNonNull(interfaceC0454n, "itemTimeoutIndicator is null");
        return new C1285h4(this, interfaceC2125q, interfaceC0454n, interfaceC2125q2);
    }

    public static AbstractC2120l<Long> timer(long j7, TimeUnit timeUnit) {
        return timer(j7, timeUnit, C2012a.f5853a);
    }

    public static <T, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "zipper is null");
        Objects.requireNonNull(interfaceC2125q, "sources is null");
        return new C1303k4(interfaceC2125q, 16).flatMap(new C1384y1(interfaceC0454n));
    }

    public final AbstractC2110b concatMapCompletable(InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n) {
        return concatMapCompletable(interfaceC0454n, 2);
    }

    public final AbstractC2110b concatMapCompletableDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n) {
        return concatMapCompletableDelayError(interfaceC0454n, true, 2);
    }

    public final <R> AbstractC2120l<R> concatMapMaybe(InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n) {
        return concatMapMaybe(interfaceC0454n, 2);
    }

    public final <R> AbstractC2120l<R> concatMapMaybeDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n) {
        return concatMapMaybeDelayError(interfaceC0454n, true, 2);
    }

    public final <R> AbstractC2120l<R> concatMapSingle(InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n) {
        return concatMapSingle(interfaceC0454n, 2);
    }

    public final <R> AbstractC2120l<R> concatMapSingleDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n) {
        return concatMapSingleDelayError(interfaceC0454n, true, 2);
    }

    public final <U> AbstractC2120l<T> debounce(InterfaceC0454n<? super T, ? extends InterfaceC2125q<U>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "debounceSelector is null");
        return new C1245b0(this, interfaceC0454n);
    }

    public final <U> AbstractC2120l<T> delay(InterfaceC0454n<? super T, ? extends InterfaceC2125q<U>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "itemDelay is null");
        return (AbstractC2120l<T>) flatMap(new C1330p1(interfaceC0454n));
    }

    public final AbstractC2120l<T> distinct() {
        return distinct(C0870a.f1298a, C0870a.s.INSTANCE);
    }

    public final AbstractC2116h<T> elementAt(long j7) {
        if (j7 >= 0) {
            return new C1329p0(this, j7);
        }
        throw new IndexOutOfBoundsException(C0534b.m341a("index >= 0 required but it was ", j7));
    }

    public final AbstractC2120l<T> observeOn(AbstractC2128t abstractC2128t) {
        return observeOn(abstractC2128t, false, bufferSize());
    }

    public final AbstractC1837a<T> replay(AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        AbstractC1837a<T> abstractC1837aReplay = replay();
        return new C1248b3.g(abstractC1837aReplay, abstractC1837aReplay.observeOn(abstractC2128t));
    }

    public final AbstractC2120l<T> retry(InterfaceC0444d<? super Integer, ? super Throwable> interfaceC0444d) {
        Objects.requireNonNull(interfaceC0444d, "predicate is null");
        return new C1254c3(this, interfaceC0444d);
    }

    public final AbstractC2120l<T> sample(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1272f3(this, j7, timeUnit, abstractC2128t, z6);
    }

    public final AbstractC2120l<T> take(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return takeUntil(timer(j7, timeUnit, abstractC2128t));
    }

    public final AbstractC2120l<T> throttleFirst(long j7, TimeUnit timeUnit) {
        return throttleFirst(j7, timeUnit, C2012a.f5853a);
    }

    public final AbstractC2120l<T> throttleLatest(long j7, TimeUnit timeUnit) {
        return throttleLatest(j7, timeUnit, C2012a.f5853a, false);
    }

    public final AbstractC2120l<C2013b<T>> timeInterval(TimeUnit timeUnit) {
        return timeInterval(timeUnit, C2012a.f5853a);
    }

    public final AbstractC2120l<T> timeout(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return timeout0(j7, timeUnit, interfaceC2125q, abstractC2128t);
    }

    public final AbstractC2129u<List<T>> toList(int i7) {
        C0871b.m677b(i7, "capacityHint");
        return new C1309l4(this, i7);
    }

    public final <B> AbstractC2120l<AbstractC2120l<T>> window(InterfaceC2125q<B> interfaceC2125q, int i7) {
        Objects.requireNonNull(interfaceC2125q, "boundary is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1333p4(this, interfaceC2125q, i7);
    }

    public final <U, R> AbstractC2120l<R> withLatestFrom(InterfaceC2125q<? extends U> interfaceC2125q, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        Objects.requireNonNull(interfaceC0443c, "combiner is null");
        return new C1357t4(this, interfaceC0443c, interfaceC2125q);
    }

    public final <U, R> AbstractC2120l<R> zipWith(InterfaceC2125q<? extends U> interfaceC2125q, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return zip(this, interfaceC2125q, interfaceC0443c);
    }

    public static <T1, T2, R> AbstractC2120l<R> combineLatest(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC0443c<? super T1, ? super T2, ? extends R> interfaceC0443c) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC0443c, "f is null");
        return combineLatest(new C0870a.b(interfaceC0443c), bufferSize(), interfaceC2125q, interfaceC2125q2);
    }

    public static <T, R> AbstractC2120l<R> combineLatestDelayError(ObservableSource<? extends T>[] observableSourceArr, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n) {
        return combineLatestDelayError((InterfaceC2125q[]) observableSourceArr, (InterfaceC0454n) interfaceC0454n, bufferSize());
    }

    public static <T, D> AbstractC2120l<T> using(Callable<? extends D> callable, InterfaceC0454n<? super D, ? extends InterfaceC2125q<? extends T>> interfaceC0454n, InterfaceC0446f<? super D> interfaceC0446f) {
        return using(callable, interfaceC0454n, interfaceC0446f, true);
    }

    public final T blockingFirst(T t6) {
        C1000e c1000e = new C1000e(0);
        subscribe(c1000e);
        T tM1015a = c1000e.m1015a();
        return tM1015a != null ? tM1015a : t6;
    }

    public final T blockingLast(T t6) {
        C1000e c1000e = new C1000e(1);
        subscribe(c1000e);
        T tM1015a = c1000e.m1015a();
        return tM1015a != null ? tM1015a : t6;
    }

    public final <U extends Collection<? super T>> AbstractC2120l<U> buffer(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, int i7, Callable<U> callable, boolean z6) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        Objects.requireNonNull(callable, "bufferSupplier is null");
        C0871b.m677b(i7, "count");
        return new C1322o(this, j7, j7, timeUnit, abstractC2128t, callable, i7, z6);
    }

    public final AbstractC2110b concatMapCompletableDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2112d> interfaceC0454n, boolean z6) {
        return concatMapCompletableDelayError(interfaceC0454n, z6, 2);
    }

    public final <R> AbstractC2120l<R> concatMapEager(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n) {
        return concatMapEager(interfaceC0454n, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, bufferSize());
    }

    public final <R> AbstractC2120l<R> concatMapEagerDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, boolean z6) {
        return concatMapEagerDelayError(interfaceC0454n, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, bufferSize(), z6);
    }

    public final <R> AbstractC2120l<R> concatMapMaybeDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2118j<? extends R>> interfaceC0454n, boolean z6) {
        return concatMapMaybeDelayError(interfaceC0454n, z6, 2);
    }

    public final <R> AbstractC2120l<R> concatMapSingleDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2131w<? extends R>> interfaceC0454n, boolean z6) {
        return concatMapSingleDelayError(interfaceC0454n, z6, 2);
    }

    public final AbstractC2120l<T> concatWith(InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return concat(this, interfaceC2125q);
    }

    public final AbstractC2120l<T> delaySubscription(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return delaySubscription(timer(j7, timeUnit, abstractC2128t));
    }

    public final <K> AbstractC2120l<T> distinct(InterfaceC0454n<? super T, K> interfaceC0454n) {
        return distinct(interfaceC0454n, C0870a.s.INSTANCE);
    }

    public final AbstractC2120l<T> distinctUntilChanged() {
        return distinctUntilChanged(C0870a.f1298a);
    }

    public final <R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, InterfaceC0454n<? super Throwable, ? extends InterfaceC2125q<? extends R>> interfaceC0454n2, Callable<? extends InterfaceC2125q<? extends R>> callable) {
        Objects.requireNonNull(interfaceC0454n, "onNextMapper is null");
        Objects.requireNonNull(interfaceC0454n2, "onErrorMapper is null");
        Objects.requireNonNull(callable, "onCompleteSupplier is null");
        return merge(new C1283h2(this, interfaceC0454n, interfaceC0454n2, callable));
    }

    public final <K> AbstractC2120l<AbstractC1238a> groupBy(InterfaceC0454n<? super T, ? extends K> interfaceC0454n) {
        return groupBy(interfaceC0454n, C0870a.f1298a, false, bufferSize());
    }

    public final AbstractC2120l<T> mergeWith(InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return merge(this, interfaceC2125q);
    }

    public final AbstractC2120l<T> observeOn(AbstractC2128t abstractC2128t, boolean z6) {
        return observeOn(abstractC2128t, z6, bufferSize());
    }

    public final AbstractC2120l<T> skipLast(long j7, TimeUnit timeUnit) {
        return skipLast(j7, timeUnit, C2012a.f5855c, false, bufferSize());
    }

    public final AbstractC2120l<T> startWith(Iterable<? extends T> iterable) {
        return concatArray(fromIterable(iterable), this);
    }

    public final AbstractC2120l<C2013b<T>> timestamp(TimeUnit timeUnit) {
        return timestamp(timeUnit, C2012a.f5853a);
    }

    public final <K, V> AbstractC2129u<Map<K, V>> toMap(InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2) {
        Objects.requireNonNull(interfaceC0454n, "keySelector is null");
        Objects.requireNonNull(interfaceC0454n2, "valueSelector is null");
        return (AbstractC2129u<Map<K, V>>) collect(EnumC1775g.INSTANCE, new C0870a.f0(interfaceC0454n2, interfaceC0454n));
    }

    public static <T, R> AbstractC2120l<R> combineLatestDelayError(InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7, ObservableSource<? extends T>... observableSourceArr) {
        return combineLatestDelayError((InterfaceC2125q[]) observableSourceArr, (InterfaceC0454n) interfaceC0454n, i7);
    }

    public static <T> AbstractC2120l<T> concat(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        return concatArray(interfaceC2125q, interfaceC2125q2);
    }

    public static <T> AbstractC2120l<T> concatDelayError(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q) {
        return concatDelayError(interfaceC2125q, bufferSize(), true);
    }

    public static <T> AbstractC2120l<T> fromFuture(Future<? extends T> future, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return fromFuture(future, j7, timeUnit).subscribeOn(abstractC2128t);
    }

    public static <T, S> AbstractC2120l<T> generate(Callable<S> callable, InterfaceC0442b<S, InterfaceC2113e<T>> interfaceC0442b) {
        Objects.requireNonNull(interfaceC0442b, "generator is null");
        return generate(callable, new C1366v1(interfaceC0442b), C0870a.f1301d);
    }

    public static AbstractC2120l<Long> interval(long j7, TimeUnit timeUnit) {
        return interval(j7, j7, timeUnit, C2012a.f5853a);
    }

    public static <T> AbstractC2120l<T> just(T t6, T t7, T t8) {
        Objects.requireNonNull(t6, "item1 is null");
        Objects.requireNonNull(t7, "item2 is null");
        Objects.requireNonNull(t8, "item3 is null");
        return fromArray(t6, t7, t8);
    }

    public static <T> AbstractC2120l<T> merge(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        return fromArray(interfaceC2125q, interfaceC2125q2).flatMap((InterfaceC0454n) C0870a.f1298a, false, 2);
    }

    public static <T> AbstractC2120l<T> mergeDelayError(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        return fromArray(interfaceC2125q, interfaceC2125q2).flatMap((InterfaceC0454n) C0870a.f1298a, true, 2);
    }

    public static <T> AbstractC2129u<Boolean> sequenceEqual(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2) {
        return sequenceEqual(interfaceC2125q, interfaceC2125q2, C0871b.f1338a, bufferSize());
    }

    public final T blockingSingle(T t6) {
        AbstractC2129u<T> abstractC2129uSingle = single(t6);
        Objects.requireNonNull(abstractC2129uSingle);
        C1001f c1001f = new C1001f();
        abstractC2129uSingle.mo2562b(c1001f);
        return (T) c1001f.m1017b();
    }

    public final AbstractC2120l<T> debounce(long j7, TimeUnit timeUnit) {
        return debounce(j7, timeUnit, C2012a.f5853a);
    }

    public final InterfaceC2153b forEachWhile(InterfaceC0455o<? super T> interfaceC0455o) {
        return forEachWhile(interfaceC0455o, C0870a.f1302e, C0870a.f1300c);
    }

    public final <K> AbstractC2120l<AbstractC1238a> groupBy(InterfaceC0454n<? super T, ? extends K> interfaceC0454n, boolean z6) {
        return groupBy(interfaceC0454n, C0870a.f1298a, z6, bufferSize());
    }

    public final AbstractC2120l<T> retry() {
        return retry(RecyclerView.FOREVER_NS, C0870a.f1303f);
    }

    public final AbstractC2120l<T> takeLast(long j7, long j8, TimeUnit timeUnit) {
        return takeLast(j7, j8, timeUnit, C2012a.f5855c, false, bufferSize());
    }

    public final AbstractC2120l<T> throttleLatest(long j7, TimeUnit timeUnit, boolean z6) {
        return throttleLatest(j7, timeUnit, C2012a.f5853a, z6);
    }

    public final AbstractC2120l<C2013b<T>> timeInterval() {
        return timeInterval(TimeUnit.MILLISECONDS, C2012a.f5853a);
    }

    public final <V> AbstractC2120l<T> timeout(InterfaceC0454n<? super T, ? extends InterfaceC2125q<V>> interfaceC0454n, InterfaceC2125q<? extends T> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "other is null");
        return timeout0(null, interfaceC0454n, interfaceC2125q);
    }

    public final AbstractC2129u<List<T>> toSortedList(Comparator<? super T> comparator, int i7) {
        Objects.requireNonNull(comparator, "comparator is null");
        AbstractC2129u<List<T>> list = toList(i7);
        C0870a.v vVar = new C0870a.v(comparator);
        Objects.requireNonNull(list);
        return new C1434a(list, vVar);
    }

    public final <U, R> AbstractC2120l<R> zipWith(InterfaceC2125q<? extends U> interfaceC2125q, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c, boolean z6) {
        return zip(this, interfaceC2125q, interfaceC0443c, z6);
    }

    public static <T, R> AbstractC2120l<R> combineLatestDelayError(ObservableSource<? extends T>[] observableSourceArr, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7) {
        C0871b.m677b(i7, "bufferSize");
        Objects.requireNonNull(interfaceC0454n, "combiner is null");
        if (observableSourceArr.length == 0) {
            return empty();
        }
        return new C1346s(observableSourceArr, null, interfaceC0454n, i7 << 1, true);
    }

    private AbstractC2120l<T> doOnEach(InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2, InterfaceC0441a interfaceC0441a, InterfaceC0441a interfaceC0441a2) {
        Objects.requireNonNull(interfaceC0446f, "onNext is null");
        Objects.requireNonNull(interfaceC0446f2, "onError is null");
        Objects.requireNonNull(interfaceC0441a, "onComplete is null");
        Objects.requireNonNull(interfaceC0441a2, "onAfterTerminate is null");
        return new C1311m0(this, interfaceC0446f, interfaceC0446f2, interfaceC0441a, interfaceC0441a2);
    }

    public static <T> AbstractC2129u<Boolean> sequenceEqual(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC0444d<? super T, ? super T> interfaceC0444d) {
        return sequenceEqual(interfaceC2125q, interfaceC2125q2, interfaceC0444d, bufferSize());
    }

    public final AbstractC2120l<T> concatWith(InterfaceC2131w<? extends T> interfaceC2131w) {
        Objects.requireNonNull(interfaceC2131w, "other is null");
        return new C1376x(this, interfaceC2131w);
    }

    public final AbstractC2120l<T> delay(long j7, TimeUnit timeUnit) {
        return delay(j7, timeUnit, C2012a.f5853a, false);
    }

    public final InterfaceC2153b forEachWhile(InterfaceC0455o<? super T> interfaceC0455o, InterfaceC0446f<? super Throwable> interfaceC0446f) {
        return forEachWhile(interfaceC0455o, interfaceC0446f, C0870a.f1300c);
    }

    public final <K, V> AbstractC2120l<AbstractC1238a> groupBy(InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2) {
        return groupBy(interfaceC0454n, interfaceC0454n2, false, bufferSize());
    }

    public final AbstractC2120l<T> mergeWith(InterfaceC2131w<? extends T> interfaceC2131w) {
        Objects.requireNonNull(interfaceC2131w, "other is null");
        return new C1307l2(this, interfaceC2131w);
    }

    public final AbstractC2120l<T> retry(long j7) {
        return retry(j7, C0870a.f1303f);
    }

    public final <U> AbstractC2120l<T> sample(InterfaceC2125q<U> interfaceC2125q) {
        Objects.requireNonNull(interfaceC2125q, "sampler is null");
        return new C1278g3(this, interfaceC2125q, false);
    }

    public final AbstractC2120l<T> skipLast(long j7, TimeUnit timeUnit, boolean z6) {
        return skipLast(j7, timeUnit, C2012a.f5855c, z6, bufferSize());
    }

    @Override // p194y3.InterfaceC2125q
    public final void subscribe(InterfaceC2127s<? super T> interfaceC2127s) {
        Objects.requireNonNull(interfaceC2127s, "observer is null");
        try {
            subscribeActual(interfaceC2127s);
        } catch (NullPointerException e7) {
            throw e7;
        } catch (Throwable th) {
            C2074b.m2470J(th);
            C1908a.m2205b(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final AbstractC2120l<C2013b<T>> timestamp() {
        return timestamp(TimeUnit.MILLISECONDS, C2012a.f5853a);
    }

    public final <K> AbstractC2129u<Map<K, Collection<T>>> toMultimap(InterfaceC0454n<? super T, ? extends K> interfaceC0454n) {
        return (AbstractC2129u<Map<K, Collection<T>>>) toMultimap(interfaceC0454n, C0870a.f1298a, EnumC1775g.INSTANCE, EnumC1770b.INSTANCE);
    }

    public final <U, V> AbstractC2120l<AbstractC2120l<T>> window(InterfaceC2125q<U> interfaceC2125q, InterfaceC0454n<? super U, ? extends InterfaceC2125q<V>> interfaceC0454n, int i7) {
        Objects.requireNonNull(interfaceC2125q, "openingIndicator is null");
        Objects.requireNonNull(interfaceC0454n, "closingIndicator is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1339q4(this, interfaceC2125q, interfaceC0454n, i7);
    }

    public final <T1, T2, R> AbstractC2120l<R> withLatestFrom(InterfaceC2125q<T1> interfaceC2125q, InterfaceC2125q<T2> interfaceC2125q2, InterfaceC0447g<? super T, ? super T1, ? super T2, R> interfaceC0447g) {
        Objects.requireNonNull(interfaceC2125q, "o1 is null");
        Objects.requireNonNull(interfaceC2125q2, "o2 is null");
        Objects.requireNonNull(interfaceC0447g, "combiner is null");
        return withLatestFrom((ObservableSource<?>[]) new InterfaceC2125q[]{interfaceC2125q, interfaceC2125q2}, (InterfaceC0454n) new C0870a.c<>(interfaceC0447g));
    }

    public final <U, R> AbstractC2120l<R> zipWith(InterfaceC2125q<? extends U> interfaceC2125q, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c, boolean z6, int i7) {
        return zip(this, interfaceC2125q, interfaceC0443c, z6, i7);
    }

    public static AbstractC2120l<Long> interval(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return interval(j7, j7, timeUnit, abstractC2128t);
    }

    public static <T> AbstractC2129u<Boolean> sequenceEqual(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, int i7) {
        return sequenceEqual(interfaceC2125q, interfaceC2125q2, C0871b.f1338a, i7);
    }

    public final <R> AbstractC2120l<R> concatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n) {
        return concatMap(interfaceC0454n, 2);
    }

    public final <R> AbstractC2120l<R> concatMapDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n) {
        return concatMapDelayError(interfaceC0454n, bufferSize(), true);
    }

    public final <K, V> AbstractC2120l<AbstractC1238a> groupBy(InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2, boolean z6) {
        return groupBy(interfaceC0454n, interfaceC0454n2, z6, bufferSize());
    }

    public final <R> AbstractC2120l<R> replay(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC0454n, "selector is null");
        return new C1248b3.e(new CallableC1354t1(this), interfaceC0454n);
    }

    public final AbstractC2120l<T> retry(InterfaceC0455o<? super Throwable> interfaceC0455o) {
        return retry(RecyclerView.FOREVER_NS, interfaceC0455o);
    }

    public final AbstractC2120l<T> sorted() {
        AbstractC2120l<T> c1435b;
        AbstractC2129u<List<T>> list = toList();
        Objects.requireNonNull(list);
        if (list instanceof InterfaceC0949b) {
            c1435b = ((InterfaceC0949b) list).mo860a();
        } else {
            c1435b = new C1435b(list);
        }
        return c1435b.map(new C0870a.v(C0870a.w.INSTANCE)).flatMapIterable(C0870a.f1298a);
    }

    public final <R> AbstractC2120l<R> switchMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n) {
        return switchMap(interfaceC0454n, bufferSize());
    }

    public final <R> AbstractC2120l<R> switchMapDelayError(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n) {
        return switchMapDelayError(interfaceC0454n, bufferSize());
    }

    public final AbstractC2120l<T> takeLast(long j7, TimeUnit timeUnit) {
        return takeLast(j7, timeUnit, C2012a.f5855c, false, bufferSize());
    }

    public final AbstractC2120l<T> throttleLatest(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return throttleLatest(j7, timeUnit, abstractC2128t, false);
    }

    public final <U, V> AbstractC2120l<T> timeout(InterfaceC2125q<U> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<V>> interfaceC0454n) {
        Objects.requireNonNull(interfaceC2125q, "firstTimeoutIndicator is null");
        return timeout0(interfaceC2125q, interfaceC0454n, null);
    }

    public static <T> AbstractC2120l<T> concat(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC2125q<? extends T> interfaceC2125q3) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        return concatArray(interfaceC2125q, interfaceC2125q2, interfaceC2125q3);
    }

    public static <T> AbstractC2120l<T> fromFuture(Future<? extends T> future, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return fromFuture(future).subscribeOn(abstractC2128t);
    }

    public static <T, S> AbstractC2120l<T> generate(Callable<S> callable, InterfaceC0442b<S, InterfaceC2113e<T>> interfaceC0442b, InterfaceC0446f<? super S> interfaceC0446f) {
        Objects.requireNonNull(interfaceC0442b, "generator is null");
        return generate(callable, new C1366v1(interfaceC0442b), interfaceC0446f);
    }

    public static <T> AbstractC2120l<T> merge(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC2125q<? extends T> interfaceC2125q3) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        return fromArray(interfaceC2125q, interfaceC2125q2, interfaceC2125q3).flatMap((InterfaceC0454n) C0870a.f1298a, false, 3);
    }

    public static <T> AbstractC2120l<T> mergeDelayError(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC2125q<? extends T> interfaceC2125q3) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        return fromArray(interfaceC2125q, interfaceC2125q2, interfaceC2125q3).flatMap((InterfaceC0454n) C0870a.f1298a, true, 3);
    }

    public static <T1, T2, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC0443c<? super T1, ? super T2, ? extends R> interfaceC0443c) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC0443c, "f is null");
        return zipArray(new C0870a.b(interfaceC0443c), false, bufferSize(), interfaceC2125q, interfaceC2125q2);
    }

    public final AbstractC2120l<T> delay(long j7, TimeUnit timeUnit, boolean z6) {
        return delay(j7, timeUnit, C2012a.f5853a, z6);
    }

    public final <R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, InterfaceC0454n<Throwable, ? extends InterfaceC2125q<? extends R>> interfaceC0454n2, Callable<? extends InterfaceC2125q<? extends R>> callable, int i7) {
        Objects.requireNonNull(interfaceC0454n, "onNextMapper is null");
        Objects.requireNonNull(interfaceC0454n2, "onErrorMapper is null");
        Objects.requireNonNull(callable, "onCompleteSupplier is null");
        return merge(new C1283h2(this, interfaceC0454n, interfaceC0454n2, callable), i7);
    }

    public final <U> AbstractC2120l<T> sample(InterfaceC2125q<U> interfaceC2125q, boolean z6) {
        Objects.requireNonNull(interfaceC2125q, "sampler is null");
        return new C1278g3(this, interfaceC2125q, z6);
    }

    public final AbstractC2120l<T> skipLast(int i7) {
        if (i7 >= 0) {
            return i7 == 0 ? this : new C1338q3(this, i7);
        }
        throw new IndexOutOfBoundsException(C0079a.m93a("count >= 0 required but it was ", i7));
    }

    public final AbstractC2120l<C2013b<T>> timeInterval(AbstractC2128t abstractC2128t) {
        return timeInterval(TimeUnit.MILLISECONDS, abstractC2128t);
    }

    public static <T1, T2, T3, R> AbstractC2120l<R> combineLatest(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC0447g<? super T1, ? super T2, ? super T3, ? extends R> interfaceC0447g) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC0447g, "f is null");
        return combineLatest(new C0870a.c(interfaceC0447g), bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3);
    }

    public static <T> AbstractC2120l<T> just(T t6, T t7, T t8, T t9) {
        Objects.requireNonNull(t6, "item1 is null");
        Objects.requireNonNull(t7, "item2 is null");
        Objects.requireNonNull(t8, "item3 is null");
        Objects.requireNonNull(t9, "item4 is null");
        return fromArray(t6, t7, t8, t9);
    }

    public final <B, U extends Collection<? super T>> AbstractC2120l<U> buffer(Callable<? extends InterfaceC2125q<B>> callable, Callable<U> callable2) {
        Objects.requireNonNull(callable, "boundarySupplier is null");
        Objects.requireNonNull(callable2, "bufferSupplier is null");
        return new C1310m(this, callable, callable2);
    }

    public final AbstractC2120l<T> takeLast(long j7, TimeUnit timeUnit, boolean z6) {
        return takeLast(j7, timeUnit, C2012a.f5855c, z6, bufferSize());
    }

    public final <U, V> AbstractC2120l<T> timeout(InterfaceC2125q<U> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<V>> interfaceC0454n, InterfaceC2125q<? extends T> interfaceC2125q2) {
        Objects.requireNonNull(interfaceC2125q, "firstTimeoutIndicator is null");
        Objects.requireNonNull(interfaceC2125q2, "other is null");
        return timeout0(interfaceC2125q, interfaceC0454n, interfaceC2125q2);
    }

    public final AbstractC2120l<C2013b<T>> timestamp(AbstractC2128t abstractC2128t) {
        return timestamp(TimeUnit.MILLISECONDS, abstractC2128t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <K, V> AbstractC2129u<Map<K, V>> toMap(InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2, Callable<? extends Map<K, V>> callable) {
        Objects.requireNonNull(interfaceC0454n, "keySelector is null");
        Objects.requireNonNull(interfaceC0454n2, "valueSelector is null");
        Objects.requireNonNull(callable, "mapSupplier is null");
        return (AbstractC2129u<Map<K, V>>) collect(callable, new C0870a.f0(interfaceC0454n2, interfaceC0454n));
    }

    public final AbstractC2120l<T> delay(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return delay(j7, timeUnit, abstractC2128t, false);
    }

    public final <R> AbstractC2120l<R> replay(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n, int i7) {
        Objects.requireNonNull(interfaceC0454n, "selector is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1248b3.e(new CallableC1300k1(this, i7), interfaceC0454n);
    }

    public final AbstractC2120l<T> sample(long j7, TimeUnit timeUnit) {
        return sample(j7, timeUnit, C2012a.f5853a);
    }

    public final AbstractC2120l<T> skipLast(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return skipLast(j7, timeUnit, abstractC2128t, false, bufferSize());
    }

    public final <K, V> AbstractC2129u<Map<K, Collection<V>>> toMultimap(InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2) {
        return toMultimap(interfaceC0454n, interfaceC0454n2, EnumC1775g.INSTANCE, EnumC1770b.INSTANCE);
    }

    public final AbstractC2129u<List<T>> toSortedList() {
        return toSortedList(C0870a.f1306i);
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, long j8, TimeUnit timeUnit) {
        return window(j7, j8, timeUnit, C2012a.f5853a, bufferSize());
    }

    public static <T, R> AbstractC2120l<R> combineLatestDelayError(Iterable<? extends InterfaceC2125q<? extends T>> iterable, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n) {
        return combineLatestDelayError(iterable, interfaceC0454n, bufferSize());
    }

    public static <T, S> AbstractC2120l<T> generate(Callable<S> callable, InterfaceC0443c<S, InterfaceC2113e<T>, S> interfaceC0443c, InterfaceC0446f<? super S> interfaceC0446f) {
        Objects.requireNonNull(callable, "initialState is null");
        Objects.requireNonNull(interfaceC0443c, "generator is null");
        Objects.requireNonNull(interfaceC0446f, "disposeState is null");
        return new C1264e1(callable, interfaceC0443c, interfaceC0446f);
    }

    public final void blockingSubscribe(InterfaceC0446f<? super T> interfaceC0446f) {
        C2074b.m2468H(this, interfaceC0446f, C0870a.f1302e, C0870a.f1300c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <U, V> AbstractC2120l<T> delay(InterfaceC2125q<U> interfaceC2125q, InterfaceC0454n<? super T, ? extends InterfaceC2125q<V>> interfaceC0454n) {
        return delaySubscription(interfaceC2125q).delay(interfaceC0454n);
    }

    public final AbstractC2120l<T> doOnEach(InterfaceC2127s<? super T> interfaceC2127s) {
        Objects.requireNonNull(interfaceC2127s, "observer is null");
        return doOnEach(new C1348s1(interfaceC2127s), new C1342r1(interfaceC2127s), new C1336q1(interfaceC2127s), C0870a.f1300c);
    }

    public final AbstractC2120l<T> skipLast(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6) {
        return skipLast(j7, timeUnit, abstractC2128t, z6, bufferSize());
    }

    public final AbstractC2120l<T> takeLast(int i7) {
        if (i7 < 0) {
            throw new IndexOutOfBoundsException(C0079a.m93a("count >= 0 required but it was ", i7));
        }
        if (i7 == 0) {
            return new C1288i1(this);
        }
        if (i7 == 1) {
            return new C1391z3(this);
        }
        return new C1386y3(this, i7);
    }

    public final AbstractC2129u<List<T>> toSortedList(int i7) {
        return toSortedList(C0870a.f1306i, i7);
    }

    public final <T1, T2, T3, R> AbstractC2120l<R> withLatestFrom(InterfaceC2125q<T1> interfaceC2125q, InterfaceC2125q<T2> interfaceC2125q2, InterfaceC2125q<T3> interfaceC2125q3, InterfaceC0448h<? super T, ? super T1, ? super T2, ? super T3, R> interfaceC0448h) {
        Objects.requireNonNull(interfaceC2125q, "o1 is null");
        Objects.requireNonNull(interfaceC2125q2, "o2 is null");
        Objects.requireNonNull(interfaceC2125q3, "o3 is null");
        Objects.requireNonNull(interfaceC0448h, "combiner is null");
        return withLatestFrom((ObservableSource<?>[]) new InterfaceC2125q[]{interfaceC2125q, interfaceC2125q2, interfaceC2125q3}, (InterfaceC0454n) new C0870a.d<>(interfaceC0448h));
    }

    public static <T> AbstractC2120l<T> concat(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC2125q<? extends T> interfaceC2125q3, InterfaceC2125q<? extends T> interfaceC2125q4) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        return concatArray(interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4);
    }

    public static <T> AbstractC2120l<T> merge(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC2125q<? extends T> interfaceC2125q3, InterfaceC2125q<? extends T> interfaceC2125q4) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        return fromArray(interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4).flatMap((InterfaceC0454n) C0870a.f1298a, false, 4);
    }

    public static <T> AbstractC2120l<T> mergeDelayError(InterfaceC2125q<? extends T> interfaceC2125q, InterfaceC2125q<? extends T> interfaceC2125q2, InterfaceC2125q<? extends T> interfaceC2125q3, InterfaceC2125q<? extends T> interfaceC2125q4) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        return fromArray(interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4).flatMap((InterfaceC0454n) C0870a.f1298a, true, 4);
    }

    public final void blockingSubscribe(InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2) {
        C2074b.m2468H(this, interfaceC0446f, interfaceC0446f2, C0870a.f1300c);
    }

    public final <TOpening, TClosing, U extends Collection<? super T>> AbstractC2120l<U> buffer(InterfaceC2125q<? extends TOpening> interfaceC2125q, InterfaceC0454n<? super TOpening, ? extends InterfaceC2125q<? extends TClosing>> interfaceC0454n, Callable<U> callable) {
        Objects.requireNonNull(interfaceC2125q, "openingIndicator is null");
        Objects.requireNonNull(interfaceC0454n, "closingIndicator is null");
        Objects.requireNonNull(callable, "bufferSupplier is null");
        return new C1304l(this, interfaceC2125q, interfaceC0454n, callable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, boolean z6, int i7, int i8) {
        Objects.requireNonNull(interfaceC0454n, "mapper is null");
        C0871b.m677b(i7, "maxConcurrency");
        C0871b.m677b(i8, "bufferSize");
        if (this instanceof InterfaceCallableC0953f) {
            Object objCall = ((InterfaceCallableC0953f) this).call();
            if (objCall == null) {
                return empty();
            }
            return new C1284h3.b(objCall, interfaceC0454n);
        }
        return new C1353t0(this, interfaceC0454n, z6, i7, i8);
    }

    public final AbstractC2120l<T> sample(long j7, TimeUnit timeUnit, boolean z6) {
        return sample(j7, timeUnit, C2012a.f5853a, z6);
    }

    public final AbstractC2120l<T> timeout(long j7, TimeUnit timeUnit) {
        return timeout0(j7, timeUnit, null, C2012a.f5853a);
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, TimeUnit timeUnit) {
        return window(j7, timeUnit, C2012a.f5853a, RecyclerView.FOREVER_NS, false);
    }

    public static <T1, T2, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC0443c<? super T1, ? super T2, ? extends R> interfaceC0443c, boolean z6) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC0443c, "f is null");
        return zipArray(new C0870a.b(interfaceC0443c), z6, bufferSize(), interfaceC2125q, interfaceC2125q2);
    }

    public final void blockingSubscribe(InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2, InterfaceC0441a interfaceC0441a) {
        C2074b.m2468H(this, interfaceC0446f, interfaceC0446f2, interfaceC0441a);
    }

    public final <K, V> AbstractC2129u<Map<K, Collection<V>>> toMultimap(InterfaceC0454n<? super T, ? extends K> interfaceC0454n, InterfaceC0454n<? super T, ? extends V> interfaceC0454n2, Callable<Map<K, Collection<V>>> callable) {
        return toMultimap(interfaceC0454n, interfaceC0454n2, callable, EnumC1770b.INSTANCE);
    }

    public static <T> AbstractC2120l<T> just(T t6, T t7, T t8, T t9, T t10) {
        Objects.requireNonNull(t6, "item1 is null");
        Objects.requireNonNull(t7, "item2 is null");
        Objects.requireNonNull(t8, "item3 is null");
        Objects.requireNonNull(t9, "item4 is null");
        Objects.requireNonNull(t10, "item5 is null");
        return fromArray(t6, t7, t8, t9, t10);
    }

    public final void blockingSubscribe(InterfaceC2127s<? super T> interfaceC2127s) {
        C2074b.m2469I(this, interfaceC2127s);
    }

    public final <R> AbstractC2120l<R> replay(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n, int i7, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(interfaceC0454n, "selector is null");
        C0871b.m677b(i7, "bufferSize");
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1248b3.e(new CallableC1306l1(this, i7, j7, timeUnit, abstractC2128t), interfaceC0454n);
    }

    public final InterfaceC2153b subscribe() {
        InterfaceC0446f<? super T> interfaceC0446f = C0870a.f1301d;
        return subscribe(interfaceC0446f, C0870a.f1302e, C0870a.f1300c, interfaceC0446f);
    }

    public final <V> AbstractC2120l<T> timeout(InterfaceC0454n<? super T, ? extends InterfaceC2125q<V>> interfaceC0454n) {
        return timeout0(null, interfaceC0454n, null);
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, TimeUnit timeUnit, long j8) {
        return window(j7, timeUnit, C2012a.f5853a, j8, false);
    }

    public static <T1, T2, T3, T4, R> AbstractC2120l<R> combineLatest(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC0448h<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> interfaceC0448h) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC0448h, "f is null");
        return combineLatest(new C0870a.d(interfaceC0448h), bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4);
    }

    public static <T, S> AbstractC2120l<T> generate(Callable<S> callable, InterfaceC0443c<S, InterfaceC2113e<T>, S> interfaceC0443c) {
        return generate(callable, interfaceC0443c, C0870a.f1301d);
    }

    public final InterfaceC2153b subscribe(InterfaceC0446f<? super T> interfaceC0446f) {
        return subscribe(interfaceC0446f, C0870a.f1302e, C0870a.f1300c, C0870a.f1301d);
    }

    public final AbstractC2120l<T> takeLast(long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return takeLast(j7, j8, timeUnit, abstractC2128t, false, bufferSize());
    }

    public final AbstractC2120l<T> timeout(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return timeout0(j7, timeUnit, null, abstractC2128t);
    }

    public final <B, U extends Collection<? super T>> AbstractC2120l<U> buffer(InterfaceC2125q<B> interfaceC2125q, Callable<U> callable) {
        Objects.requireNonNull(interfaceC2125q, "boundary is null");
        Objects.requireNonNull(callable, "bufferSupplier is null");
        return new C1316n(this, interfaceC2125q, callable);
    }

    public final InterfaceC2153b subscribe(InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2) {
        return subscribe(interfaceC0446f, interfaceC0446f2, C0870a.f1300c, C0870a.f1301d);
    }

    public final AbstractC2120l<T> takeLast(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return takeLast(j7, timeUnit, abstractC2128t, false, bufferSize());
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, TimeUnit timeUnit, long j8, boolean z6) {
        return window(j7, timeUnit, C2012a.f5853a, j8, z6);
    }

    public static <T> AbstractC2120l<T> concat(InterfaceC2125q<? extends InterfaceC2125q<? extends T>> interfaceC2125q) {
        return concat(interfaceC2125q, bufferSize());
    }

    public static <T> AbstractC2120l<T> merge(Iterable<? extends InterfaceC2125q<? extends T>> iterable, int i7, int i8) {
        return fromIterable(iterable).flatMap((InterfaceC0454n) C0870a.f1298a, false, i7, i8);
    }

    public static <T> AbstractC2120l<T> mergeDelayError(Iterable<? extends InterfaceC2125q<? extends T>> iterable) {
        return fromIterable(iterable).flatMap((InterfaceC0454n) C0870a.f1298a, true);
    }

    public final InterfaceC2153b subscribe(InterfaceC0446f<? super T> interfaceC0446f, InterfaceC0446f<? super Throwable> interfaceC0446f2, InterfaceC0441a interfaceC0441a) {
        return subscribe(interfaceC0446f, interfaceC0446f2, interfaceC0441a, C0870a.f1301d);
    }

    public final AbstractC2120l<T> takeLast(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6) {
        return takeLast(j7, timeUnit, abstractC2128t, z6, bufferSize());
    }

    public final <T1, T2, T3, T4, R> AbstractC2120l<R> withLatestFrom(InterfaceC2125q<T1> interfaceC2125q, InterfaceC2125q<T2> interfaceC2125q2, InterfaceC2125q<T3> interfaceC2125q3, InterfaceC2125q<T4> interfaceC2125q4, InterfaceC0449i<? super T, ? super T1, ? super T2, ? super T3, ? super T4, R> interfaceC0449i) {
        Objects.requireNonNull(interfaceC2125q, "o1 is null");
        Objects.requireNonNull(interfaceC2125q2, "o2 is null");
        Objects.requireNonNull(interfaceC2125q3, "o3 is null");
        Objects.requireNonNull(interfaceC2125q4, "o4 is null");
        Objects.requireNonNull(interfaceC0449i, "combiner is null");
        return withLatestFrom((ObservableSource<?>[]) new InterfaceC2125q[]{interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4}, (InterfaceC0454n) new C0870a.e<>(interfaceC0449i));
    }

    public static <T> AbstractC2120l<T> merge(Iterable<? extends InterfaceC2125q<? extends T>> iterable) {
        return fromIterable(iterable).flatMap(C0870a.f1298a);
    }

    public static <T> AbstractC2120l<T> mergeDelayError(Iterable<? extends InterfaceC2125q<? extends T>> iterable, int i7, int i8) {
        return fromIterable(iterable).flatMap((InterfaceC0454n) C0870a.f1298a, true, i7, i8);
    }

    public static <T1, T2, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC0443c<? super T1, ? super T2, ? extends R> interfaceC0443c, boolean z6, int i7) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC0443c, "f is null");
        return zipArray(new C0870a.b(interfaceC0443c), z6, i7, interfaceC2125q, interfaceC2125q2);
    }

    public final AbstractC2120l<T> takeLast(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, boolean z6, int i7) {
        return takeLast(RecyclerView.FOREVER_NS, j7, timeUnit, abstractC2128t, z6, i7);
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7) {
        return window(j7, j7, bufferSize());
    }

    public static <T> AbstractC2120l<T> merge(Iterable<? extends InterfaceC2125q<? extends T>> iterable, int i7) {
        return fromIterable(iterable).flatMap(C0870a.f1298a, i7);
    }

    public static <T> AbstractC2120l<T> mergeDelayError(Iterable<? extends InterfaceC2125q<? extends T>> iterable, int i7) {
        return fromIterable(iterable).flatMap((InterfaceC0454n) C0870a.f1298a, true, i7);
    }

    public final AbstractC2120l<List<T>> buffer(long j7, long j8, TimeUnit timeUnit) {
        return (AbstractC2120l<List<T>>) buffer(j7, j8, timeUnit, C2012a.f5853a, EnumC1770b.INSTANCE);
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, long j8) {
        return window(j7, j8, bufferSize());
    }

    public static <T> AbstractC2120l<T> just(T t6, T t7, T t8, T t9, T t10, T t11) {
        Objects.requireNonNull(t6, "item1 is null");
        Objects.requireNonNull(t7, "item2 is null");
        Objects.requireNonNull(t8, "item3 is null");
        Objects.requireNonNull(t9, "item4 is null");
        Objects.requireNonNull(t10, "item5 is null");
        Objects.requireNonNull(t11, "item6 is null");
        return fromArray(t6, t7, t8, t9, t10, t11);
    }

    public final <R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n) {
        return flatMap((InterfaceC0454n) interfaceC0454n, false);
    }

    public final <R> AbstractC2120l<R> replay(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n, int i7, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(interfaceC0454n, "selector is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1248b3.e(new CallableC1300k1(this, i7), new C1360u1(interfaceC0454n, abstractC2128t));
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, long j8, int i7) {
        C0871b.m678c(j7, "count");
        C0871b.m678c(j8, "skip");
        C0871b.m677b(i7, "bufferSize");
        return new C1327o4(this, j7, j8, i7);
    }

    public final AbstractC2120l<List<T>> buffer(long j7, TimeUnit timeUnit) {
        return buffer(j7, timeUnit, C2012a.f5853a, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public final <R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, boolean z6) {
        return flatMap(interfaceC0454n, z6, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public static <T1, T2, T3, T4, T5, R> AbstractC2120l<R> combineLatest(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC0449i<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> interfaceC0449i) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC0449i, "f is null");
        return combineLatest(new C0870a.e(interfaceC0449i), bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5);
    }

    public final <R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, boolean z6, int i7) {
        return flatMap(interfaceC0454n, z6, i7, bufferSize());
    }

    public static <T1, T2, T3, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC0447g<? super T1, ? super T2, ? super T3, ? extends R> interfaceC0447g) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC0447g, "f is null");
        return zipArray(new C0870a.c(interfaceC0447g), false, bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3);
    }

    public final AbstractC2120l<List<T>> buffer(long j7, TimeUnit timeUnit, int i7) {
        return buffer(j7, timeUnit, C2012a.f5853a, i7);
    }

    public final <R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends R>> interfaceC0454n, int i7) {
        return flatMap((InterfaceC0454n) interfaceC0454n, false, i7, bufferSize());
    }

    public final <U, R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> interfaceC0454n, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c) {
        return flatMap(interfaceC0454n, interfaceC0443c, false, bufferSize(), bufferSize());
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return window(j7, j8, timeUnit, abstractC2128t, bufferSize());
    }

    public final <R> AbstractC2120l<R> withLatestFrom(ObservableSource<?>[] observableSourceArr, InterfaceC0454n<? super Object[], R> interfaceC0454n) {
        Objects.requireNonNull(observableSourceArr, "others is null");
        Objects.requireNonNull(interfaceC0454n, "combiner is null");
        return new C1363u4(this, observableSourceArr, interfaceC0454n);
    }

    public final AbstractC2120l<List<T>> buffer(int i7) {
        return buffer(i7, i7);
    }

    public final <U, R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> interfaceC0454n, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c, boolean z6) {
        return flatMap(interfaceC0454n, interfaceC0443c, z6, bufferSize(), bufferSize());
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t, int i7) {
        C0871b.m678c(j7, "timespan");
        C0871b.m678c(j8, "timeskip");
        C0871b.m677b(i7, "bufferSize");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        return new C1351s4(this, j7, j8, timeUnit, abstractC2128t, RecyclerView.FOREVER_NS, i7, false);
    }

    public final AbstractC2120l<List<T>> buffer(int i7, int i8) {
        return (AbstractC2120l<List<T>>) buffer(i7, i8, EnumC1770b.INSTANCE);
    }

    public final <U, R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> interfaceC0454n, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c, boolean z6, int i7) {
        return flatMap(interfaceC0454n, interfaceC0443c, z6, i7, bufferSize());
    }

    public final <R> AbstractC2120l<R> replay(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(interfaceC0454n, "selector is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1248b3.e(new CallableC1378x1(this, j7, timeUnit, abstractC2128t), interfaceC0454n);
    }

    public static <T> AbstractC2120l<T> just(T t6, T t7, T t8, T t9, T t10, T t11, T t12) {
        Objects.requireNonNull(t6, "item1 is null");
        Objects.requireNonNull(t7, "item2 is null");
        Objects.requireNonNull(t8, "item3 is null");
        Objects.requireNonNull(t9, "item4 is null");
        Objects.requireNonNull(t10, "item5 is null");
        Objects.requireNonNull(t11, "item6 is null");
        Objects.requireNonNull(t12, "item7 is null");
        return fromArray(t6, t7, t8, t9, t10, t11, t12);
    }

    public final <U extends Collection<? super T>> AbstractC2120l<U> buffer(int i7, int i8, Callable<U> callable) {
        C0871b.m677b(i7, "count");
        C0871b.m677b(i8, "skip");
        Objects.requireNonNull(callable, "bufferSupplier is null");
        return new C1298k(this, i7, i8, callable);
    }

    public final <U, R> AbstractC2120l<R> flatMap(InterfaceC0454n<? super T, ? extends InterfaceC2125q<? extends U>> interfaceC0454n, InterfaceC0443c<? super T, ? super U, ? extends R> interfaceC0443c, int i7) {
        return flatMap(interfaceC0454n, interfaceC0443c, false, i7, bufferSize());
    }

    public static <T1, T2, T3, T4, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC0448h<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> interfaceC0448h) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC0448h, "f is null");
        return zipArray(new C0870a.d(interfaceC0448h), false, bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4);
    }

    public static <T1, T2, T3, T4, T5, T6, R> AbstractC2120l<R> combineLatest(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC2125q<? extends T6> interfaceC2125q6, InterfaceC0450j<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> interfaceC0450j) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC2125q6, "source6 is null");
        Objects.requireNonNull(interfaceC0450j, "f is null");
        return combineLatest(new C0870a.f(interfaceC0450j), bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5, interfaceC2125q6);
    }

    public final <U extends Collection<? super T>> AbstractC2120l<U> buffer(int i7, Callable<U> callable) {
        return buffer(i7, i7, callable);
    }

    public final <R> AbstractC2120l<R> replay(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n, AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(interfaceC0454n, "selector is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return new C1248b3.e(new CallableC1354t1(this), new C1360u1(interfaceC0454n, abstractC2128t));
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return window(j7, timeUnit, abstractC2128t, RecyclerView.FOREVER_NS, false);
    }

    public final AbstractC2120l<List<T>> buffer(long j7, long j8, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return (AbstractC2120l<List<T>>) buffer(j7, j8, timeUnit, abstractC2128t, EnumC1770b.INSTANCE);
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, long j8) {
        return window(j7, timeUnit, abstractC2128t, j8, false);
    }

    public final AbstractC2120l<List<T>> buffer(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, int i7) {
        return (AbstractC2120l<List<T>>) buffer(j7, timeUnit, abstractC2128t, i7, EnumC1770b.INSTANCE, false);
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, long j8, boolean z6) {
        return window(j7, timeUnit, abstractC2128t, j8, z6, bufferSize());
    }

    public final AbstractC2120l<List<T>> buffer(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        return (AbstractC2120l<List<T>>) buffer(j7, timeUnit, abstractC2128t, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, EnumC1770b.INSTANCE, false);
    }

    public final AbstractC2120l<AbstractC2120l<T>> window(long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t, long j8, boolean z6, int i7) {
        C0871b.m677b(i7, "bufferSize");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        C0871b.m678c(j8, "count");
        return new C1351s4(this, j7, j7, timeUnit, abstractC2128t, j8, i7, z6);
    }

    public static <T> AbstractC2120l<T> just(T t6, T t7, T t8, T t9, T t10, T t11, T t12, T t13) {
        Objects.requireNonNull(t6, "item1 is null");
        Objects.requireNonNull(t7, "item2 is null");
        Objects.requireNonNull(t8, "item3 is null");
        Objects.requireNonNull(t9, "item4 is null");
        Objects.requireNonNull(t10, "item5 is null");
        Objects.requireNonNull(t11, "item6 is null");
        Objects.requireNonNull(t12, "item7 is null");
        Objects.requireNonNull(t13, "item8 is null");
        return fromArray(t6, t7, t8, t9, t10, t11, t12, t13);
    }

    public final <TOpening, TClosing> AbstractC2120l<List<T>> buffer(InterfaceC2125q<? extends TOpening> interfaceC2125q, InterfaceC0454n<? super TOpening, ? extends InterfaceC2125q<? extends TClosing>> interfaceC0454n) {
        return (AbstractC2120l<List<T>>) buffer(interfaceC2125q, interfaceC0454n, EnumC1770b.INSTANCE);
    }

    public static <T1, T2, T3, T4, T5, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC0449i<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> interfaceC0449i) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC0449i, "f is null");
        return zipArray(new C0870a.e(interfaceC0449i), false, bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5);
    }

    public final <B> AbstractC2120l<List<T>> buffer(InterfaceC2125q<B> interfaceC2125q) {
        return (AbstractC2120l<List<T>>) buffer((InterfaceC2125q) interfaceC2125q, (Callable) EnumC1770b.INSTANCE);
    }

    public final AbstractC1837a<T> replay() {
        return C1248b3.m1474c(this, C1248b3.f2854i);
    }

    public final <B> AbstractC2120l<List<T>> buffer(InterfaceC2125q<B> interfaceC2125q, int i7) {
        C0871b.m677b(i7, "initialCapacity");
        return (AbstractC2120l<List<T>>) buffer(interfaceC2125q, new C0870a.j(i7));
    }

    public final AbstractC1837a<T> replay(int i7, long j7, TimeUnit timeUnit) {
        return replay(i7, j7, timeUnit, C2012a.f5853a);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> AbstractC2120l<R> combineLatest(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC2125q<? extends T6> interfaceC2125q6, InterfaceC2125q<? extends T7> interfaceC2125q7, InterfaceC0451k<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> interfaceC0451k) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC2125q6, "source6 is null");
        Objects.requireNonNull(interfaceC2125q7, "source7 is null");
        Objects.requireNonNull(interfaceC0451k, "f is null");
        return combineLatest(new C0870a.g(interfaceC0451k), bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5, interfaceC2125q6, interfaceC2125q7);
    }

    public final AbstractC1837a<T> replay(long j7, TimeUnit timeUnit) {
        return replay(j7, timeUnit, C2012a.f5853a);
    }

    public final <B> AbstractC2120l<AbstractC2120l<T>> window(InterfaceC2125q<B> interfaceC2125q) {
        return window(interfaceC2125q, bufferSize());
    }

    public final <B> AbstractC2120l<List<T>> buffer(Callable<? extends InterfaceC2125q<B>> callable) {
        return (AbstractC2120l<List<T>>) buffer(callable, EnumC1770b.INSTANCE);
    }

    public final <U, V> AbstractC2120l<AbstractC2120l<T>> window(InterfaceC2125q<U> interfaceC2125q, InterfaceC0454n<? super U, ? extends InterfaceC2125q<V>> interfaceC0454n) {
        return window(interfaceC2125q, interfaceC0454n, bufferSize());
    }

    public final <R> AbstractC2120l<R> replay(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n, int i7, long j7, TimeUnit timeUnit) {
        return replay(interfaceC0454n, i7, j7, timeUnit, C2012a.f5853a);
    }

    public final <B> AbstractC2120l<AbstractC2120l<T>> window(Callable<? extends InterfaceC2125q<B>> callable) {
        return window(callable, bufferSize());
    }

    public final <R> AbstractC2120l<R> replay(InterfaceC0454n<? super AbstractC2120l<T>, ? extends InterfaceC2125q<R>> interfaceC0454n, long j7, TimeUnit timeUnit) {
        return replay(interfaceC0454n, j7, timeUnit, C2012a.f5853a);
    }

    public static <T> AbstractC2120l<T> just(T t6, T t7, T t8, T t9, T t10, T t11, T t12, T t13, T t14) {
        Objects.requireNonNull(t6, "item1 is null");
        Objects.requireNonNull(t7, "item2 is null");
        Objects.requireNonNull(t8, "item3 is null");
        Objects.requireNonNull(t9, "item4 is null");
        Objects.requireNonNull(t10, "item5 is null");
        Objects.requireNonNull(t11, "item6 is null");
        Objects.requireNonNull(t12, "item7 is null");
        Objects.requireNonNull(t13, "item8 is null");
        Objects.requireNonNull(t14, "item9 is null");
        return fromArray(t6, t7, t8, t9, t10, t11, t12, t13, t14);
    }

    public static <T1, T2, T3, T4, T5, T6, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC2125q<? extends T6> interfaceC2125q6, InterfaceC0450j<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> interfaceC0450j) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC2125q6, "source6 is null");
        Objects.requireNonNull(interfaceC0450j, "f is null");
        return zipArray(new C0870a.f(interfaceC0450j), false, bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5, interfaceC2125q6);
    }

    public final AbstractC1837a<T> replay(int i7) {
        C0871b.m677b(i7, "bufferSize");
        if (i7 == Integer.MAX_VALUE) {
            return C1248b3.m1474c(this, C1248b3.f2854i);
        }
        return C1248b3.m1474c(this, new C1248b3.i(i7));
    }

    public final AbstractC1837a<T> replay(int i7, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
        C0871b.m677b(i7, "bufferSize");
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(abstractC2128t, "scheduler is null");
        return C1248b3.m1474c(this, new C1248b3.l(i7, j7, timeUnit, abstractC2128t));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> AbstractC2120l<R> combineLatest(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC2125q<? extends T6> interfaceC2125q6, InterfaceC2125q<? extends T7> interfaceC2125q7, InterfaceC2125q<? extends T8> interfaceC2125q8, InterfaceC0452l<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> interfaceC0452l) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC2125q6, "source6 is null");
        Objects.requireNonNull(interfaceC2125q7, "source7 is null");
        Objects.requireNonNull(interfaceC2125q8, "source8 is null");
        Objects.requireNonNull(interfaceC0452l, "f is null");
        return combineLatest(new C0870a.h(interfaceC0452l), bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5, interfaceC2125q6, interfaceC2125q7, interfaceC2125q8);
    }

    public final AbstractC1837a<T> replay(int i7, AbstractC2128t abstractC2128t) {
        C0871b.m677b(i7, "bufferSize");
        AbstractC1837a<T> abstractC1837aReplay = replay(i7);
        return new C1248b3.g(abstractC1837aReplay, abstractC1837aReplay.observeOn(abstractC2128t));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC2125q<? extends T6> interfaceC2125q6, InterfaceC2125q<? extends T7> interfaceC2125q7, InterfaceC0451k<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> interfaceC0451k) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC2125q6, "source6 is null");
        Objects.requireNonNull(interfaceC2125q7, "source7 is null");
        Objects.requireNonNull(interfaceC0451k, "f is null");
        return zipArray(new C0870a.g(interfaceC0451k), false, bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5, interfaceC2125q6, interfaceC2125q7);
    }

    public static <T> AbstractC2120l<T> just(T t6, T t7, T t8, T t9, T t10, T t11, T t12, T t13, T t14, T t15) {
        Objects.requireNonNull(t6, "item1 is null");
        Objects.requireNonNull(t7, "item2 is null");
        Objects.requireNonNull(t8, "item3 is null");
        Objects.requireNonNull(t9, "item4 is null");
        Objects.requireNonNull(t10, "item5 is null");
        Objects.requireNonNull(t11, "item6 is null");
        Objects.requireNonNull(t12, "item7 is null");
        Objects.requireNonNull(t13, "item8 is null");
        Objects.requireNonNull(t14, "item9 is null");
        Objects.requireNonNull(t15, "item10 is null");
        return fromArray(t6, t7, t8, t9, t10, t11, t12, t13, t14, t15);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> AbstractC2120l<R> combineLatest(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC2125q<? extends T6> interfaceC2125q6, InterfaceC2125q<? extends T7> interfaceC2125q7, InterfaceC2125q<? extends T8> interfaceC2125q8, InterfaceC2125q<? extends T9> interfaceC2125q9, InterfaceC0453m<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> interfaceC0453m) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC2125q6, "source6 is null");
        Objects.requireNonNull(interfaceC2125q7, "source7 is null");
        Objects.requireNonNull(interfaceC2125q8, "source8 is null");
        Objects.requireNonNull(interfaceC2125q9, "source9 is null");
        Objects.requireNonNull(interfaceC0453m, "f is null");
        return combineLatest(new C0870a.i(interfaceC0453m), bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5, interfaceC2125q6, interfaceC2125q7, interfaceC2125q8, interfaceC2125q9);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC2125q<? extends T6> interfaceC2125q6, InterfaceC2125q<? extends T7> interfaceC2125q7, InterfaceC2125q<? extends T8> interfaceC2125q8, InterfaceC0452l<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> interfaceC0452l) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC2125q6, "source6 is null");
        Objects.requireNonNull(interfaceC2125q7, "source7 is null");
        Objects.requireNonNull(interfaceC2125q8, "source8 is null");
        Objects.requireNonNull(interfaceC0452l, "f is null");
        return zipArray(new C0870a.h(interfaceC0452l), false, bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5, interfaceC2125q6, interfaceC2125q7, interfaceC2125q8);
    }

    public static <T, R> AbstractC2120l<R> combineLatest(ObservableSource<? extends T>[] observableSourceArr, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7) {
        Objects.requireNonNull(observableSourceArr, "sources is null");
        if (observableSourceArr.length == 0) {
            return empty();
        }
        Objects.requireNonNull(interfaceC0454n, "combiner is null");
        C0871b.m677b(i7, "bufferSize");
        return new C1346s(observableSourceArr, null, interfaceC0454n, i7 << 1, false);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> AbstractC2120l<R> zip(InterfaceC2125q<? extends T1> interfaceC2125q, InterfaceC2125q<? extends T2> interfaceC2125q2, InterfaceC2125q<? extends T3> interfaceC2125q3, InterfaceC2125q<? extends T4> interfaceC2125q4, InterfaceC2125q<? extends T5> interfaceC2125q5, InterfaceC2125q<? extends T6> interfaceC2125q6, InterfaceC2125q<? extends T7> interfaceC2125q7, InterfaceC2125q<? extends T8> interfaceC2125q8, InterfaceC2125q<? extends T9> interfaceC2125q9, InterfaceC0453m<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> interfaceC0453m) {
        Objects.requireNonNull(interfaceC2125q, "source1 is null");
        Objects.requireNonNull(interfaceC2125q2, "source2 is null");
        Objects.requireNonNull(interfaceC2125q3, "source3 is null");
        Objects.requireNonNull(interfaceC2125q4, "source4 is null");
        Objects.requireNonNull(interfaceC2125q5, "source5 is null");
        Objects.requireNonNull(interfaceC2125q6, "source6 is null");
        Objects.requireNonNull(interfaceC2125q7, "source7 is null");
        Objects.requireNonNull(interfaceC2125q8, "source8 is null");
        Objects.requireNonNull(interfaceC2125q9, "source9 is null");
        Objects.requireNonNull(interfaceC0453m, "f is null");
        return zipArray(new C0870a.i(interfaceC0453m), false, bufferSize(), interfaceC2125q, interfaceC2125q2, interfaceC2125q3, interfaceC2125q4, interfaceC2125q5, interfaceC2125q6, interfaceC2125q7, interfaceC2125q8, interfaceC2125q9);
    }

    public static <T, R> AbstractC2120l<R> combineLatest(InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n, int i7, ObservableSource<? extends T>... observableSourceArr) {
        return combineLatest((InterfaceC2125q[]) observableSourceArr, (InterfaceC0454n) interfaceC0454n, i7);
    }

    public static <T, R> AbstractC2120l<R> combineLatest(Iterable<? extends InterfaceC2125q<? extends T>> iterable, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n) {
        return combineLatest(iterable, interfaceC0454n, bufferSize());
    }

    public static <T, R> AbstractC2120l<R> combineLatest(ObservableSource<? extends T>[] observableSourceArr, InterfaceC0454n<? super Object[], ? extends R> interfaceC0454n) {
        return combineLatest((InterfaceC2125q[]) observableSourceArr, (InterfaceC0454n) interfaceC0454n, bufferSize());
    }
}
