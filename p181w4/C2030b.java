package p181w4;

import io.reactivex.subjects.PublishSubject;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p160t4.C1908a;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: PublishSubject.java */
/* renamed from: w4.b */
/* loaded from: classes.dex */
public final class C2030b<T> extends AbstractC2032d<T> {

    /* renamed from: g */
    public static final a[] f5914g = new a[0];

    /* renamed from: h */
    public static final a[] f5915h = new a[0];

    /* renamed from: e */
    public final AtomicReference<PublishSubject.PublishDisposable<T>[]> f5916e = new AtomicReference<>(f5915h);

    /* renamed from: f */
    public Throwable f5917f;

    /* compiled from: PublishSubject.java */
    /* renamed from: w4.b$a */
    public static final class a<T> extends AtomicBoolean implements InterfaceC2153b {
        private static final long serialVersionUID = 3562861878281475070L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f5918e;

        /* renamed from: f */
        public final C2030b<T> f5919f;

        public a(InterfaceC2127s<? super T> interfaceC2127s, C2030b<T> c2030b) {
            this.f5918e = interfaceC2127s;
            this.f5919f = c2030b;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.f5919f.m2385b(this);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get();
        }
    }

    /* renamed from: b */
    public void m2385b(a<T> aVar) {
        PublishSubject.PublishDisposable<T>[] publishDisposableArr;
        a[] aVarArr;
        do {
            publishDisposableArr = (a[]) this.f5916e.get();
            if (publishDisposableArr == f5914g || publishDisposableArr == f5915h) {
                return;
            }
            int length = publishDisposableArr.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    i7 = -1;
                    break;
                } else if (publishDisposableArr[i7] == aVar) {
                    break;
                } else {
                    i7++;
                }
            }
            if (i7 < 0) {
                return;
            }
            if (length == 1) {
                aVarArr = f5915h;
            } else {
                a[] aVarArr2 = new a[length - 1];
                System.arraycopy(publishDisposableArr, 0, aVarArr2, 0, i7);
                System.arraycopy(publishDisposableArr, i7 + 1, aVarArr2, i7, (length - i7) - 1);
                aVarArr = aVarArr2;
            }
        } while (!this.f5916e.compareAndSet(publishDisposableArr, aVarArr));
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        PublishSubject.PublishDisposable<T>[] publishDisposableArr = this.f5916e.get();
        PublishSubject.PublishDisposable<T>[] publishDisposableArr2 = f5914g;
        if (publishDisposableArr == publishDisposableArr2) {
            return;
        }
        for (a aVar : this.f5916e.getAndSet(publishDisposableArr2)) {
            if (!aVar.get()) {
                aVar.f5918e.onComplete();
            }
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        Objects.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        PublishSubject.PublishDisposable<T>[] publishDisposableArr = this.f5916e.get();
        PublishSubject.PublishDisposable<T>[] publishDisposableArr2 = f5914g;
        if (publishDisposableArr == publishDisposableArr2) {
            C1908a.m2205b(th);
            return;
        }
        this.f5917f = th;
        for (a aVar : this.f5916e.getAndSet(publishDisposableArr2)) {
            if (aVar.get()) {
                C1908a.m2205b(th);
            } else {
                aVar.f5918e.onError(th);
            }
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        Objects.requireNonNull(t6, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        for (a aVar : this.f5916e.get()) {
            if (!aVar.get()) {
                aVar.f5918e.onNext(t6);
            }
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (this.f5916e.get() == f5914g) {
            interfaceC2153b.dispose();
        }
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        boolean z6;
        PublishSubject.PublishDisposable<T> aVar = new a<>(interfaceC2127s, this);
        interfaceC2127s.onSubscribe(aVar);
        while (true) {
            PublishSubject.PublishDisposable<T>[] publishDisposableArr = (a[]) this.f5916e.get();
            z6 = false;
            if (publishDisposableArr == f5914g) {
                break;
            }
            int length = publishDisposableArr.length;
            PublishSubject.PublishDisposable<T>[] publishDisposableArr2 = new a[length + 1];
            System.arraycopy(publishDisposableArr, 0, publishDisposableArr2, 0, length);
            publishDisposableArr2[length] = aVar;
            if (this.f5916e.compareAndSet(publishDisposableArr, publishDisposableArr2)) {
                z6 = true;
                break;
            }
        }
        if (z6) {
            if (aVar.get()) {
                m2385b(aVar);
            }
        } else {
            Throwable th = this.f5917f;
            if (th != null) {
                interfaceC2127s.onError(th);
            } else {
                interfaceC2127s.onComplete();
            }
        }
    }
}
