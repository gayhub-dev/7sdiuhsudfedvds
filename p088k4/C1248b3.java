package p088k4;

import io.reactivex.internal.operators.observable.ObservableReplay;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0446f;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p022c4.InterfaceC0517e;
import p138q4.C1774f;
import p138q4.EnumC1776h;
import p146r4.AbstractC1837a;
import p160t4.C1908a;
import p174v4.C2013b;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableReplay.java */
/* renamed from: k4.b3 */
/* loaded from: classes.dex */
public final class C1248b3<T> extends AbstractC1837a<T> implements InterfaceC0517e {

    /* renamed from: i */
    public static final b f2854i = new o();

    /* renamed from: e */
    public final InterfaceC2125q<T> f2855e;

    /* renamed from: f */
    public final AtomicReference<j<T>> f2856f;

    /* renamed from: g */
    public final b<T> f2857g;

    /* renamed from: h */
    public final InterfaceC2125q<T> f2858h;

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$a */
    public static abstract class a<T> extends AtomicReference<f> implements h<T> {
        private static final long serialVersionUID = 2346567790059478686L;

        /* renamed from: e */
        public f f2859e;

        /* renamed from: f */
        public int f2860f;

        public a() {
            f fVar = new f(null);
            this.f2859e = fVar;
            set(fVar);
        }

        /* renamed from: a */
        public Object mo1476a(Object obj) {
            return obj;
        }

        @Override // p088k4.C1248b3.h
        /* renamed from: b */
        public final void mo1477b(d<T> dVar) {
            if (dVar.getAndIncrement() != 0) {
                return;
            }
            int iAddAndGet = 1;
            do {
                f fVarMo1478c = (f) dVar.f2864g;
                if (fVarMo1478c == null) {
                    fVarMo1478c = mo1478c();
                    dVar.f2864g = fVarMo1478c;
                }
                while (!dVar.f2865h) {
                    f fVar = fVarMo1478c.get();
                    if (fVar == null) {
                        dVar.f2864g = fVarMo1478c;
                        iAddAndGet = dVar.addAndGet(-iAddAndGet);
                    } else {
                        if (EnumC1776h.m1962a(mo1480e(fVar.f2868e), dVar.f2863f)) {
                            dVar.f2864g = null;
                            return;
                        }
                        fVarMo1478c = fVar;
                    }
                }
                dVar.f2864g = null;
                return;
            } while (iAddAndGet != 0);
        }

        /* renamed from: c */
        public f mo1478c() {
            return get();
        }

        @Override // p088k4.C1248b3.h
        public final void complete() {
            f fVar = new f(mo1476a(EnumC1776h.COMPLETE));
            this.f2859e.set(fVar);
            this.f2859e = fVar;
            this.f2860f++;
            mo1483h();
        }

        @Override // p088k4.C1248b3.h
        /* renamed from: d */
        public final void mo1479d(T t6) {
            f fVar = new f(mo1476a(t6));
            this.f2859e.set(fVar);
            this.f2859e = fVar;
            this.f2860f++;
            mo1481f();
        }

        /* renamed from: e */
        public Object mo1480e(Object obj) {
            return obj;
        }

        /* renamed from: f */
        public abstract void mo1481f();

        @Override // p088k4.C1248b3.h
        /* renamed from: g */
        public final void mo1482g(Throwable th) {
            f fVar = new f(mo1476a(new EnumC1776h.b(th)));
            this.f2859e.set(fVar);
            this.f2859e = fVar;
            this.f2860f++;
            mo1483h();
        }

        /* renamed from: h */
        public void mo1483h() {
            f fVar = get();
            if (fVar.f2868e != null) {
                f fVar2 = new f(null);
                fVar2.lazySet(fVar.get());
                set(fVar2);
            }
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$b */
    public interface b<T> {
        h<T> call();
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$c */
    public static final class c<R> implements InterfaceC0446f<InterfaceC2153b> {

        /* renamed from: e */
        public final C1381x4<R> f2861e;

        public c(C1381x4<R> c1381x4) {
            this.f2861e = c1381x4;
        }

        @Override // p014b4.InterfaceC0446f
        public void accept(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m326e(this.f2861e, interfaceC2153b);
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$d */
    public static final class d<T> extends AtomicInteger implements InterfaceC2153b {
        private static final long serialVersionUID = 2728361546769921047L;

        /* renamed from: e */
        public final j<T> f2862e;

        /* renamed from: f */
        public final InterfaceC2127s<? super T> f2863f;

        /* renamed from: g */
        public Object f2864g;

        /* renamed from: h */
        public volatile boolean f2865h;

        public d(j<T> jVar, InterfaceC2127s<? super T> interfaceC2127s) {
            this.f2862e = jVar;
            this.f2863f = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f2865h) {
                return;
            }
            this.f2865h = true;
            this.f2862e.m1484a(this);
            this.f2864g = null;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2865h;
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$e */
    public static final class e<R, U> extends AbstractC2120l<R> {

        /* renamed from: e */
        public final Callable<? extends AbstractC1837a<U>> f2866e;

        /* renamed from: f */
        public final InterfaceC0454n<? super AbstractC2120l<U>, ? extends InterfaceC2125q<R>> f2867f;

        public e(Callable<? extends AbstractC1837a<U>> callable, InterfaceC0454n<? super AbstractC2120l<U>, ? extends InterfaceC2125q<R>> interfaceC0454n) {
            this.f2866e = callable;
            this.f2867f = interfaceC0454n;
        }

        @Override // p194y3.AbstractC2120l
        public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
            try {
                AbstractC1837a<U> abstractC1837aCall = this.f2866e.call();
                Objects.requireNonNull(abstractC1837aCall, "The connectableFactory returned a null ConnectableObservable");
                AbstractC1837a<U> abstractC1837a = abstractC1837aCall;
                InterfaceC2125q<R> interfaceC2125qApply = this.f2867f.apply(abstractC1837a);
                Objects.requireNonNull(interfaceC2125qApply, "The selector returned a null ObservableSource");
                InterfaceC2125q<R> interfaceC2125q = interfaceC2125qApply;
                C1381x4 c1381x4 = new C1381x4(interfaceC2127s);
                interfaceC2125q.subscribe(c1381x4);
                abstractC1837a.mo1475b(new c(c1381x4));
            } catch (Throwable th) {
                C2074b.m2470J(th);
                interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
                interfaceC2127s.onError(th);
            }
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$f */
    public static final class f extends AtomicReference<f> {
        private static final long serialVersionUID = 245354315435971818L;

        /* renamed from: e */
        public final Object f2868e;

        public f(Object obj) {
            this.f2868e = obj;
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$g */
    public static final class g<T> extends AbstractC1837a<T> {

        /* renamed from: e */
        public final AbstractC1837a<T> f2869e;

        /* renamed from: f */
        public final AbstractC2120l<T> f2870f;

        public g(AbstractC1837a<T> abstractC1837a, AbstractC2120l<T> abstractC2120l) {
            this.f2869e = abstractC1837a;
            this.f2870f = abstractC2120l;
        }

        @Override // p146r4.AbstractC1837a
        /* renamed from: b */
        public void mo1475b(InterfaceC0446f<? super InterfaceC2153b> interfaceC0446f) {
            this.f2869e.mo1475b(interfaceC0446f);
        }

        @Override // p194y3.AbstractC2120l
        public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f2870f.subscribe(interfaceC2127s);
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$h */
    public interface h<T> {
        /* renamed from: b */
        void mo1477b(d<T> dVar);

        void complete();

        /* renamed from: d */
        void mo1479d(T t6);

        /* renamed from: g */
        void mo1482g(Throwable th);
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$i */
    public static final class i<T> implements b<T> {

        /* renamed from: a */
        public final int f2871a;

        public i(int i7) {
            this.f2871a = i7;
        }

        @Override // p088k4.C1248b3.b
        public h<T> call() {
            return new n(this.f2871a);
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$j */
    public static final class j<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: i */
        public static final d[] f2872i = new d[0];

        /* renamed from: j */
        public static final d[] f2873j = new d[0];
        private static final long serialVersionUID = -533785617179540163L;

        /* renamed from: e */
        public final h<T> f2874e;

        /* renamed from: f */
        public boolean f2875f;

        /* renamed from: g */
        public final AtomicReference<ObservableReplay.InnerDisposable[]> f2876g = new AtomicReference<>(f2872i);

        /* renamed from: h */
        public final AtomicBoolean f2877h = new AtomicBoolean();

        public j(h<T> hVar) {
            this.f2874e = hVar;
        }

        /* renamed from: a */
        public void m1484a(d<T> dVar) {
            ObservableReplay.InnerDisposable[] innerDisposableArr;
            d[] dVarArr;
            do {
                innerDisposableArr = (d[]) this.f2876g.get();
                int length = innerDisposableArr.length;
                if (length == 0) {
                    return;
                }
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        i7 = -1;
                        break;
                    } else if (innerDisposableArr[i7].equals(dVar)) {
                        break;
                    } else {
                        i7++;
                    }
                }
                if (i7 < 0) {
                    return;
                }
                if (length == 1) {
                    dVarArr = f2872i;
                } else {
                    d[] dVarArr2 = new d[length - 1];
                    System.arraycopy(innerDisposableArr, 0, dVarArr2, 0, i7);
                    System.arraycopy(innerDisposableArr, i7 + 1, dVarArr2, i7, (length - i7) - 1);
                    dVarArr = dVarArr2;
                }
            } while (!this.f2876g.compareAndSet(innerDisposableArr, dVarArr));
        }

        /* renamed from: b */
        public void m1485b() {
            for (d<T> dVar : this.f2876g.get()) {
                this.f2874e.mo1477b(dVar);
            }
        }

        /* renamed from: c */
        public void m1486c() {
            for (d<T> dVar : this.f2876g.getAndSet(f2873j)) {
                this.f2874e.mo1477b(dVar);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2876g.set(f2873j);
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2876g.get() == f2873j;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f2875f) {
                return;
            }
            this.f2875f = true;
            this.f2874e.complete();
            m1486c();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f2875f) {
                C1908a.m2205b(th);
                return;
            }
            this.f2875f = true;
            this.f2874e.mo1482g(th);
            m1486c();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f2875f) {
                return;
            }
            this.f2874e.mo1479d(t6);
            m1485b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m327f(this, interfaceC2153b)) {
                m1485b();
            }
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$k */
    public static final class k<T> implements InterfaceC2125q<T> {

        /* renamed from: e */
        public final AtomicReference<j<T>> f2878e;

        /* renamed from: f */
        public final b<T> f2879f;

        public k(AtomicReference<j<T>> atomicReference, b<T> bVar) {
            this.f2878e = atomicReference;
            this.f2879f = bVar;
        }

        @Override // p194y3.InterfaceC2125q
        public void subscribe(InterfaceC2127s<? super T> interfaceC2127s) {
            j<T> jVar;
            ObservableReplay.InnerDisposable[] innerDisposableArr;
            ObservableReplay.InnerDisposable[] innerDisposableArr2;
            while (true) {
                jVar = this.f2878e.get();
                if (jVar != null) {
                    break;
                }
                j<T> jVar2 = new j<>(this.f2879f.call());
                if (this.f2878e.compareAndSet(null, jVar2)) {
                    jVar = jVar2;
                    break;
                }
            }
            ObservableReplay.InnerDisposable dVar = new d(jVar, interfaceC2127s);
            interfaceC2127s.onSubscribe(dVar);
            do {
                innerDisposableArr = (d[]) jVar.f2876g.get();
                if (innerDisposableArr == j.f2873j) {
                    break;
                }
                int length = innerDisposableArr.length;
                innerDisposableArr2 = new d[length + 1];
                System.arraycopy(innerDisposableArr, 0, innerDisposableArr2, 0, length);
                innerDisposableArr2[length] = dVar;
            } while (!jVar.f2876g.compareAndSet(innerDisposableArr, innerDisposableArr2));
            if (dVar.f2865h) {
                jVar.m1484a(dVar);
            } else {
                jVar.f2874e.mo1477b(dVar);
            }
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$l */
    public static final class l<T> implements b<T> {

        /* renamed from: a */
        public final int f2880a;

        /* renamed from: b */
        public final long f2881b;

        /* renamed from: c */
        public final TimeUnit f2882c;

        /* renamed from: d */
        public final AbstractC2128t f2883d;

        public l(int i7, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
            this.f2880a = i7;
            this.f2881b = j7;
            this.f2882c = timeUnit;
            this.f2883d = abstractC2128t;
        }

        @Override // p088k4.C1248b3.b
        public h<T> call() {
            return new m(this.f2880a, this.f2881b, this.f2882c, this.f2883d);
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$m */
    public static final class m<T> extends a<T> {
        private static final long serialVersionUID = 3457957419649567404L;

        /* renamed from: g */
        public final AbstractC2128t f2884g;

        /* renamed from: h */
        public final long f2885h;

        /* renamed from: i */
        public final TimeUnit f2886i;

        /* renamed from: j */
        public final int f2887j;

        public m(int i7, long j7, TimeUnit timeUnit, AbstractC2128t abstractC2128t) {
            this.f2884g = abstractC2128t;
            this.f2887j = i7;
            this.f2885h = j7;
            this.f2886i = timeUnit;
        }

        @Override // p088k4.C1248b3.a
        /* renamed from: a */
        public Object mo1476a(Object obj) {
            return new C2013b(obj, this.f2884g.now(this.f2886i), this.f2886i);
        }

        @Override // p088k4.C1248b3.a
        /* renamed from: c */
        public f mo1478c() {
            f fVar;
            long jNow = this.f2884g.now(this.f2886i) - this.f2885h;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 == null) {
                    break;
                }
                C2013b c2013b = (C2013b) fVar2.f2868e;
                if (EnumC1776h.m1964c(c2013b.f5860a) || (c2013b.f5860a instanceof EnumC1776h.b) || c2013b.f5861b > jNow) {
                    break;
                }
                fVar3 = fVar2.get();
            }
            return fVar;
        }

        @Override // p088k4.C1248b3.a
        /* renamed from: e */
        public Object mo1480e(Object obj) {
            return ((C2013b) obj).f5860a;
        }

        @Override // p088k4.C1248b3.a
        /* renamed from: f */
        public void mo1481f() {
            f fVar;
            long jNow = this.f2884g.now(this.f2886i) - this.f2885h;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            int i7 = 0;
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 != null) {
                    int i8 = this.f2860f;
                    if (i8 <= this.f2887j) {
                        if (((C2013b) fVar2.f2868e).f5861b > jNow) {
                            break;
                        }
                        i7++;
                        this.f2860f = i8 - 1;
                        fVar3 = fVar2.get();
                    } else {
                        i7++;
                        this.f2860f = i8 - 1;
                        fVar3 = fVar2.get();
                    }
                } else {
                    break;
                }
            }
            if (i7 != 0) {
                set(fVar);
            }
        }

        @Override // p088k4.C1248b3.a
        /* renamed from: h */
        public void mo1483h() {
            f fVar;
            int i7;
            long jNow = this.f2884g.now(this.f2886i) - this.f2885h;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            int i8 = 0;
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 == null || (i7 = this.f2860f) <= 1 || ((C2013b) fVar2.f2868e).f5861b > jNow) {
                    break;
                }
                i8++;
                this.f2860f = i7 - 1;
                fVar3 = fVar2.get();
            }
            if (i8 != 0) {
                set(fVar);
            }
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$n */
    public static final class n<T> extends a<T> {
        private static final long serialVersionUID = -5898283885385201806L;

        /* renamed from: g */
        public final int f2888g;

        public n(int i7) {
            this.f2888g = i7;
        }

        @Override // p088k4.C1248b3.a
        /* renamed from: f */
        public void mo1481f() {
            if (this.f2860f > this.f2888g) {
                this.f2860f--;
                set(get().get());
            }
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$o */
    public static final class o implements b<Object> {
        @Override // p088k4.C1248b3.b
        public h<Object> call() {
            return new p(16);
        }
    }

    /* compiled from: ObservableReplay.java */
    /* renamed from: k4.b3$p */
    public static final class p<T> extends ArrayList<Object> implements h<T> {
        private static final long serialVersionUID = 7063189396499112664L;

        /* renamed from: e */
        public volatile int f2889e;

        public p(int i7) {
            super(i7);
        }

        @Override // p088k4.C1248b3.h
        /* renamed from: b */
        public void mo1477b(d<T> dVar) {
            if (dVar.getAndIncrement() != 0) {
                return;
            }
            InterfaceC2127s<? super T> interfaceC2127s = dVar.f2863f;
            int iAddAndGet = 1;
            while (!dVar.f2865h) {
                int i7 = this.f2889e;
                Integer num = (Integer) dVar.f2864g;
                int iIntValue = num != null ? num.intValue() : 0;
                while (iIntValue < i7) {
                    if (EnumC1776h.m1962a(get(iIntValue), interfaceC2127s) || dVar.f2865h) {
                        return;
                    } else {
                        iIntValue++;
                    }
                }
                dVar.f2864g = Integer.valueOf(iIntValue);
                iAddAndGet = dVar.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }

        @Override // p088k4.C1248b3.h
        public void complete() {
            add(EnumC1776h.COMPLETE);
            this.f2889e++;
        }

        @Override // p088k4.C1248b3.h
        /* renamed from: d */
        public void mo1479d(T t6) {
            add(t6);
            this.f2889e++;
        }

        @Override // p088k4.C1248b3.h
        /* renamed from: g */
        public void mo1482g(Throwable th) {
            add(new EnumC1776h.b(th));
            this.f2889e++;
        }
    }

    public C1248b3(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<T> interfaceC2125q2, AtomicReference<j<T>> atomicReference, b<T> bVar) {
        this.f2858h = interfaceC2125q;
        this.f2855e = interfaceC2125q2;
        this.f2856f = atomicReference;
        this.f2857g = bVar;
    }

    /* renamed from: c */
    public static <T> AbstractC1837a<T> m1474c(InterfaceC2125q<T> interfaceC2125q, b<T> bVar) {
        AtomicReference atomicReference = new AtomicReference();
        return new C1248b3(new k(atomicReference, bVar), interfaceC2125q, atomicReference, bVar);
    }

    @Override // p022c4.InterfaceC0517e
    /* renamed from: a */
    public void mo332a(InterfaceC2153b interfaceC2153b) {
        this.f2856f.compareAndSet((j) interfaceC2153b, null);
    }

    @Override // p146r4.AbstractC1837a
    /* renamed from: b */
    public void mo1475b(InterfaceC0446f<? super InterfaceC2153b> interfaceC0446f) {
        j<T> jVar;
        while (true) {
            jVar = this.f2856f.get();
            if (jVar != null && !jVar.isDisposed()) {
                break;
            }
            j<T> jVar2 = new j<>(this.f2857g.call());
            if (this.f2856f.compareAndSet(jVar, jVar2)) {
                jVar = jVar2;
                break;
            }
        }
        boolean z6 = !jVar.f2877h.get() && jVar.f2877h.compareAndSet(false, true);
        try {
            interfaceC0446f.accept(jVar);
            if (z6) {
                this.f2855e.subscribe(jVar);
            }
        } catch (Throwable th) {
            if (z6) {
                jVar.f2877h.compareAndSet(true, false);
            }
            C2074b.m2470J(th);
            throw C1774f.m1961d(th);
        }
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2858h.subscribe(interfaceC2127s);
    }
}
