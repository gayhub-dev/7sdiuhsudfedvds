package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0443c;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableScan.java */
/* renamed from: k4.i3 */
/* loaded from: classes.dex */
public final class C1290i3<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0443c<T, T, T> f3191f;

    /* compiled from: ObservableScan.java */
    /* renamed from: k4.i3$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3192e;

        /* renamed from: f */
        public final InterfaceC0443c<T, T, T> f3193f;

        /* renamed from: g */
        public InterfaceC2153b f3194g;

        /* renamed from: h */
        public T f3195h;

        /* renamed from: i */
        public boolean f3196i;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0443c<T, T, T> interfaceC0443c) {
            this.f3192e = interfaceC2127s;
            this.f3193f = interfaceC0443c;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3194g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3194g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3196i) {
                return;
            }
            this.f3196i = true;
            this.f3192e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3196i) {
                C1908a.m2205b(th);
            } else {
                this.f3196i = true;
                this.f3192e.onError(th);
            }
        }

        /* JADX WARN: Type inference failed for: r4v2, types: [T, java.lang.Object] */
        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3196i) {
                return;
            }
            InterfaceC2127s<? super T> interfaceC2127s = this.f3192e;
            T t7 = this.f3195h;
            if (t7 == null) {
                this.f3195h = t6;
                interfaceC2127s.onNext(t6);
                return;
            }
            try {
                T tApply = this.f3193f.apply(t7, t6);
                Objects.requireNonNull(tApply, "The value returned by the accumulator is null");
                this.f3195h = tApply;
                interfaceC2127s.onNext(tApply);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f3194g.dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3194g, interfaceC2153b)) {
                this.f3194g = interfaceC2153b;
                this.f3192e.onSubscribe(this);
            }
        }
    }

    public C1290i3(InterfaceC2125q<T> interfaceC2125q, InterfaceC0443c<T, T, T> interfaceC0443c) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3191f = interfaceC0443c;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3191f));
    }
}
