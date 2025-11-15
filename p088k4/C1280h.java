package p088k4;

import io.reactivex.ObservableSource;
import io.reactivex.internal.operators.observable.ObservableAmb;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableAmb.java */
/* renamed from: k4.h */
/* loaded from: classes.dex */
public final class C1280h<T> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final ObservableSource<? extends T>[] f3138e;

    /* renamed from: f */
    public final Iterable<? extends InterfaceC2125q<? extends T>> f3139f;

    /* compiled from: ObservableAmb.java */
    /* renamed from: k4.h$a */
    public static final class a<T> implements InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3140e;

        /* renamed from: f */
        public final ObservableAmb.AmbInnerObserver<T>[] f3141f;

        /* renamed from: g */
        public final AtomicInteger f3142g = new AtomicInteger();

        public a(InterfaceC2127s<? super T> interfaceC2127s, int i7) {
            this.f3140e = interfaceC2127s;
            this.f3141f = new b[i7];
        }

        /* renamed from: a */
        public boolean m1506a(int i7) {
            int i8 = this.f3142g.get();
            int i9 = 0;
            if (i8 != 0) {
                return i8 == i7;
            }
            if (!this.f3142g.compareAndSet(0, i7)) {
                return false;
            }
            AtomicReference[] atomicReferenceArr = this.f3141f;
            int length = atomicReferenceArr.length;
            while (i9 < length) {
                int i10 = i9 + 1;
                if (i10 != i7) {
                    EnumC0515c.m323a(atomicReferenceArr[i9]);
                }
                i9 = i10;
            }
            return true;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f3142g.get() != -1) {
                this.f3142g.lazySet(-1);
                for (AtomicReference atomicReference : this.f3141f) {
                    EnumC0515c.m323a(atomicReference);
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3142g.get() == -1;
        }
    }

    /* compiled from: ObservableAmb.java */
    /* renamed from: k4.h$b */
    public static final class b<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T> {
        private static final long serialVersionUID = -1185974347409665484L;

        /* renamed from: e */
        public final a<T> f3143e;

        /* renamed from: f */
        public final int f3144f;

        /* renamed from: g */
        public final InterfaceC2127s<? super T> f3145g;

        /* renamed from: h */
        public boolean f3146h;

        public b(a<T> aVar, int i7, InterfaceC2127s<? super T> interfaceC2127s) {
            this.f3143e = aVar;
            this.f3144f = i7;
            this.f3145g = interfaceC2127s;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3146h) {
                this.f3145g.onComplete();
            } else if (this.f3143e.m1506a(this.f3144f)) {
                this.f3146h = true;
                this.f3145g.onComplete();
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3146h) {
                this.f3145g.onError(th);
            } else if (!this.f3143e.m1506a(this.f3144f)) {
                C1908a.m2205b(th);
            } else {
                this.f3146h = true;
                this.f3145g.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3146h) {
                this.f3145g.onNext(t6);
            } else if (!this.f3143e.m1506a(this.f3144f)) {
                get().dispose();
            } else {
                this.f3146h = true;
                this.f3145g.onNext(t6);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this, interfaceC2153b);
        }
    }

    public C1280h(ObservableSource<? extends T>[] observableSourceArr, Iterable<? extends InterfaceC2125q<? extends T>> iterable) {
        this.f3138e = observableSourceArr;
        this.f3139f = iterable;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        int length;
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        ObservableSource<? extends T>[] observableSourceArr = this.f3138e;
        if (observableSourceArr == null) {
            observableSourceArr = new AbstractC2120l[8];
            try {
                Iterator<? extends InterfaceC2125q<? extends T>> it = this.f3139f.iterator();
                length = 0;
                while (it.hasNext()) {
                    ObservableSource<? extends T> observableSource = (InterfaceC2125q) it.next();
                    if (observableSource == null) {
                        NullPointerException nullPointerException = new NullPointerException("One of the sources is null");
                        interfaceC2127s.onSubscribe(enumC0516d);
                        interfaceC2127s.onError(nullPointerException);
                        return;
                    } else {
                        if (length == observableSourceArr.length) {
                            ObservableSource<? extends T>[] observableSourceArr2 = new InterfaceC2125q[(length >> 2) + length];
                            System.arraycopy(observableSourceArr, 0, observableSourceArr2, 0, length);
                            observableSourceArr = observableSourceArr2;
                        }
                        int i7 = length + 1;
                        observableSourceArr[length] = observableSource;
                        length = i7;
                    }
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                interfaceC2127s.onSubscribe(enumC0516d);
                interfaceC2127s.onError(th);
                return;
            }
        } else {
            length = observableSourceArr.length;
        }
        if (length == 0) {
            interfaceC2127s.onSubscribe(enumC0516d);
            interfaceC2127s.onComplete();
            return;
        }
        if (length == 1) {
            observableSourceArr[0].subscribe(interfaceC2127s);
            return;
        }
        a aVar = new a(interfaceC2127s, length);
        InterfaceC2127s<? super T>[] interfaceC2127sArr = aVar.f3141f;
        int length2 = interfaceC2127sArr.length;
        int i8 = 0;
        while (i8 < length2) {
            int i9 = i8 + 1;
            interfaceC2127sArr[i8] = new b(aVar, i9, aVar.f3140e);
            i8 = i9;
        }
        aVar.f3142g.lazySet(0);
        aVar.f3140e.onSubscribe(aVar);
        for (int i10 = 0; i10 < length2 && aVar.f3142g.get() == 0; i10++) {
            observableSourceArr[i10].subscribe(interfaceC2127sArr[i10]);
        }
    }
}
