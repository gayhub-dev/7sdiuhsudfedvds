package p088k4;

import java.util.NoSuchElementException;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableElementAt.java */
/* renamed from: k4.o0 */
/* loaded from: classes.dex */
public final class C1323o0<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final long f3472f;

    /* renamed from: g */
    public final T f3473g;

    /* renamed from: h */
    public final boolean f3474h;

    /* compiled from: ObservableElementAt.java */
    /* renamed from: k4.o0$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3475e;

        /* renamed from: f */
        public final long f3476f;

        /* renamed from: g */
        public final T f3477g;

        /* renamed from: h */
        public final boolean f3478h;

        /* renamed from: i */
        public InterfaceC2153b f3479i;

        /* renamed from: j */
        public long f3480j;

        /* renamed from: k */
        public boolean f3481k;

        public a(InterfaceC2127s<? super T> interfaceC2127s, long j7, T t6, boolean z6) {
            this.f3475e = interfaceC2127s;
            this.f3476f = j7;
            this.f3477g = t6;
            this.f3478h = z6;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3479i.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3479i.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3481k) {
                return;
            }
            this.f3481k = true;
            T t6 = this.f3477g;
            if (t6 == null && this.f3478h) {
                this.f3475e.onError(new NoSuchElementException());
                return;
            }
            if (t6 != null) {
                this.f3475e.onNext(t6);
            }
            this.f3475e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3481k) {
                C1908a.m2205b(th);
            } else {
                this.f3481k = true;
                this.f3475e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3481k) {
                return;
            }
            long j7 = this.f3480j;
            if (j7 != this.f3476f) {
                this.f3480j = j7 + 1;
                return;
            }
            this.f3481k = true;
            this.f3479i.dispose();
            this.f3475e.onNext(t6);
            this.f3475e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3479i, interfaceC2153b)) {
                this.f3479i = interfaceC2153b;
                this.f3475e.onSubscribe(this);
            }
        }
    }

    public C1323o0(InterfaceC2125q<T> interfaceC2125q, long j7, T t6, boolean z6) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3472f = j7;
        this.f3473g = t6;
        this.f3474h = z6;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3472f, this.f3473g, this.f3474h));
    }
}
