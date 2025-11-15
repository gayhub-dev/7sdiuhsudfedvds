package p088k4;

import io.reactivex.internal.operators.observable.ObservablePublish;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0446f;
import p022c4.EnumC0515c;
import p138q4.C1774f;
import p146r4.AbstractC1837a;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservablePublish.java */
/* renamed from: k4.q2 */
/* loaded from: classes.dex */
public final class C1337q2<T> extends AbstractC1837a<T> {

    /* renamed from: e */
    public final InterfaceC2125q<T> f3586e;

    /* renamed from: f */
    public final AtomicReference<b<T>> f3587f;

    /* renamed from: g */
    public final InterfaceC2125q<T> f3588g;

    /* compiled from: ObservablePublish.java */
    /* renamed from: k4.q2$a */
    public static final class a<T> extends AtomicReference<Object> implements InterfaceC2153b {
        private static final long serialVersionUID = -1100270633763673112L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3589e;

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3589e = interfaceC2127s;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            Object andSet = getAndSet(this);
            if (andSet == null || andSet == this) {
                return;
            }
            ((b) andSet).m1528a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get() == this;
        }
    }

    /* compiled from: ObservablePublish.java */
    /* renamed from: k4.q2$b */
    public static final class b<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: i */
        public static final a[] f3590i = new a[0];

        /* renamed from: j */
        public static final a[] f3591j = new a[0];

        /* renamed from: e */
        public final AtomicReference<b<T>> f3592e;

        /* renamed from: h */
        public final AtomicReference<InterfaceC2153b> f3595h = new AtomicReference<>();

        /* renamed from: f */
        public final AtomicReference<ObservablePublish.InnerDisposable<T>[]> f3593f = new AtomicReference<>(f3590i);

        /* renamed from: g */
        public final AtomicBoolean f3594g = new AtomicBoolean();

        public b(AtomicReference<b<T>> atomicReference) {
            this.f3592e = atomicReference;
        }

        /* renamed from: a */
        public void m1528a(a<T> aVar) {
            ObservablePublish.InnerDisposable<T>[] innerDisposableArr;
            a[] aVarArr;
            do {
                innerDisposableArr = (a[]) this.f3593f.get();
                int length = innerDisposableArr.length;
                if (length == 0) {
                    return;
                }
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        i7 = -1;
                        break;
                    } else if (innerDisposableArr[i7].equals(aVar)) {
                        break;
                    } else {
                        i7++;
                    }
                }
                if (i7 < 0) {
                    return;
                }
                if (length == 1) {
                    aVarArr = f3590i;
                } else {
                    a[] aVarArr2 = new a[length - 1];
                    System.arraycopy(innerDisposableArr, 0, aVarArr2, 0, i7);
                    System.arraycopy(innerDisposableArr, i7 + 1, aVarArr2, i7, (length - i7) - 1);
                    aVarArr = aVarArr2;
                }
            } while (!this.f3593f.compareAndSet(innerDisposableArr, aVarArr));
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            AtomicReference<ObservablePublish.InnerDisposable<T>[]> atomicReference = this.f3593f;
            a[] aVarArr = f3591j;
            if (((a[]) atomicReference.getAndSet(aVarArr)) != aVarArr) {
                this.f3592e.compareAndSet(this, null);
                EnumC0515c.m323a(this.f3595h);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3593f.get() == f3591j;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3592e.compareAndSet(this, null);
            for (a aVar : this.f3593f.getAndSet(f3591j)) {
                aVar.f3589e.onComplete();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3592e.compareAndSet(this, null);
            a[] andSet = this.f3593f.getAndSet(f3591j);
            if (andSet.length == 0) {
                C1908a.m2205b(th);
                return;
            }
            for (a aVar : andSet) {
                aVar.f3589e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            for (a aVar : this.f3593f.get()) {
                aVar.f3589e.onNext(t6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3595h, interfaceC2153b);
        }
    }

    /* compiled from: ObservablePublish.java */
    /* renamed from: k4.q2$c */
    public static final class c<T> implements InterfaceC2125q<T> {

        /* renamed from: e */
        public final AtomicReference<b<T>> f3596e;

        public c(AtomicReference<b<T>> atomicReference) {
            this.f3596e = atomicReference;
        }

        @Override // p194y3.InterfaceC2125q
        public void subscribe(InterfaceC2127s<? super T> interfaceC2127s) {
            b<T> bVar;
            boolean z6;
            ObservablePublish.InnerDisposable<T> aVar = new a<>(interfaceC2127s);
            interfaceC2127s.onSubscribe(aVar);
            while (true) {
                bVar = this.f3596e.get();
                if (bVar == null || bVar.isDisposed()) {
                    b<T> bVar2 = new b<>(this.f3596e);
                    if (this.f3596e.compareAndSet(bVar, bVar2)) {
                        bVar = bVar2;
                    } else {
                        continue;
                    }
                }
                while (true) {
                    ObservablePublish.InnerDisposable<T>[] innerDisposableArr = (a[]) bVar.f3593f.get();
                    z6 = false;
                    if (innerDisposableArr == b.f3591j) {
                        break;
                    }
                    int length = innerDisposableArr.length;
                    ObservablePublish.InnerDisposable<T>[] innerDisposableArr2 = new a[length + 1];
                    System.arraycopy(innerDisposableArr, 0, innerDisposableArr2, 0, length);
                    innerDisposableArr2[length] = aVar;
                    if (bVar.f3593f.compareAndSet(innerDisposableArr, innerDisposableArr2)) {
                        z6 = true;
                        break;
                    }
                }
                if (z6) {
                    break;
                }
            }
            if (aVar.compareAndSet(null, bVar)) {
                return;
            }
            bVar.m1528a(aVar);
        }
    }

    public C1337q2(InterfaceC2125q<T> interfaceC2125q, InterfaceC2125q<T> interfaceC2125q2, AtomicReference<b<T>> atomicReference) {
        this.f3588g = interfaceC2125q;
        this.f3586e = interfaceC2125q2;
        this.f3587f = atomicReference;
    }

    @Override // p146r4.AbstractC1837a
    /* renamed from: b */
    public void mo1475b(InterfaceC0446f<? super InterfaceC2153b> interfaceC0446f) {
        b<T> bVar;
        while (true) {
            bVar = this.f3587f.get();
            if (bVar != null && !bVar.isDisposed()) {
                break;
            }
            b<T> bVar2 = new b<>(this.f3587f);
            if (this.f3587f.compareAndSet(bVar, bVar2)) {
                bVar = bVar2;
                break;
            }
        }
        boolean z6 = !bVar.f3594g.get() && bVar.f3594g.compareAndSet(false, true);
        try {
            interfaceC0446f.accept(bVar);
            if (z6) {
                this.f3586e.subscribe(bVar);
            }
        } catch (Throwable th) {
            C2074b.m2470J(th);
            throw C1774f.m1961d(th);
        }
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f3588g.subscribe(interfaceC2127s);
    }
}
