package p088k4;

import java.util.Objects;
import p048f4.AbstractC0998c;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableFromArray.java */
/* renamed from: k4.z0 */
/* loaded from: classes.dex */
public final class C1388z0<T> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final T[] f4028e;

    /* compiled from: ObservableFromArray.java */
    /* renamed from: k4.z0$a */
    public static final class a<T> extends AbstractC0998c<T> {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f4029e;

        /* renamed from: f */
        public final T[] f4030f;

        /* renamed from: g */
        public int f4031g;

        /* renamed from: h */
        public boolean f4032h;

        /* renamed from: i */
        public volatile boolean f4033i;

        public a(InterfaceC2127s<? super T> interfaceC2127s, T[] tArr) {
            this.f4029e = interfaceC2127s;
            this.f4030f = tArr;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            if ((i7 & 1) == 0) {
                return 0;
            }
            this.f4032h = true;
            return 1;
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            this.f4031g = this.f4030f.length;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f4033i = true;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4033i;
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return this.f4031g == this.f4030f.length;
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            int i7 = this.f4031g;
            T[] tArr = this.f4030f;
            if (i7 == tArr.length) {
                return null;
            }
            this.f4031g = i7 + 1;
            T t6 = tArr[i7];
            Objects.requireNonNull(t6, "The array element is null");
            return t6;
        }
    }

    public C1388z0(T[] tArr) {
        this.f4028e = tArr;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        T[] tArr = this.f4028e;
        a aVar = new a(interfaceC2127s, tArr);
        interfaceC2127s.onSubscribe(aVar);
        if (aVar.f4032h) {
            return;
        }
        int length = tArr.length;
        for (int i7 = 0; i7 < length && !aVar.f4033i; i7++) {
            T t6 = tArr[i7];
            if (t6 == null) {
                aVar.f4029e.onError(new NullPointerException("The " + i7 + "th element is null"));
                return;
            }
            aVar.f4029e.onNext(t6);
        }
        if (aVar.f4033i) {
            return;
        }
        aVar.f4029e.onComplete();
    }
}
