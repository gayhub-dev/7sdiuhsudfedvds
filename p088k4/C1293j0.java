package p088k4;

import java.util.Objects;
import p014b4.InterfaceC0444d;
import p014b4.InterfaceC0454n;
import p032d4.C0871b;
import p048f4.AbstractC0996a;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableDistinctUntilChanged.java */
/* renamed from: k4.j0 */
/* loaded from: classes.dex */
public final class C1293j0<T, K> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0454n<? super T, K> f3225f;

    /* renamed from: g */
    public final InterfaceC0444d<? super K, ? super K> f3226g;

    /* compiled from: ObservableDistinctUntilChanged.java */
    /* renamed from: k4.j0$a */
    public static final class a<T, K> extends AbstractC0996a<T, T> {

        /* renamed from: j */
        public final InterfaceC0454n<? super T, K> f3227j;

        /* renamed from: k */
        public final InterfaceC0444d<? super K, ? super K> f3228k;

        /* renamed from: l */
        public K f3229l;

        /* renamed from: m */
        public boolean f3230m;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0454n<? super T, K> interfaceC0454n, InterfaceC0444d<? super K, ? super K> interfaceC0444d) {
            super(interfaceC2127s);
            this.f3227j = interfaceC0454n;
            this.f3228k = interfaceC0444d;
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            return m1014b(i7);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f1870h) {
                return;
            }
            if (this.f1871i != 0) {
                this.f1867e.onNext(t6);
                return;
            }
            try {
                K kApply = this.f3227j.apply(t6);
                if (this.f3230m) {
                    InterfaceC0444d<? super K, ? super K> interfaceC0444d = this.f3228k;
                    K k7 = this.f3229l;
                    Objects.requireNonNull((C0871b.a) interfaceC0444d);
                    boolean zM676a = C0871b.m676a(k7, kApply);
                    this.f3229l = kApply;
                    if (zM676a) {
                        return;
                    }
                } else {
                    this.f3230m = true;
                    this.f3229l = kApply;
                }
                this.f1867e.onNext(t6);
            } catch (Throwable th) {
                m1013a(th);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            while (true) {
                T tPoll = this.f1869g.poll();
                if (tPoll == null) {
                    return null;
                }
                K kApply = this.f3227j.apply(tPoll);
                if (!this.f3230m) {
                    this.f3230m = true;
                    this.f3229l = kApply;
                    return tPoll;
                }
                InterfaceC0444d<? super K, ? super K> interfaceC0444d = this.f3228k;
                K k7 = this.f3229l;
                Objects.requireNonNull((C0871b.a) interfaceC0444d);
                if (!C0871b.m676a(k7, kApply)) {
                    this.f3229l = kApply;
                    return tPoll;
                }
                this.f3229l = kApply;
            }
        }
    }

    public C1293j0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0454n<? super T, K> interfaceC0454n, InterfaceC0444d<? super K, ? super K> interfaceC0444d) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3225f = interfaceC0454n;
        this.f3226g = interfaceC0444d;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3225f, this.f3226g));
    }
}
