package p088k4;

import p022c4.EnumC0515c;
import p040e4.InterfaceC0949b;
import p160t4.C1908a;
import p194y3.AbstractC2116h;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableElementAtMaybe.java */
/* renamed from: k4.p0 */
/* loaded from: classes.dex */
public final class C1329p0<T> extends AbstractC2116h<T> implements InterfaceC0949b<T> {

    /* renamed from: a */
    public final InterfaceC2125q<T> f3538a;

    /* renamed from: b */
    public final long f3539b;

    /* compiled from: ObservableElementAtMaybe.java */
    /* renamed from: k4.p0$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2117i<? super T> f3540e;

        /* renamed from: f */
        public final long f3541f;

        /* renamed from: g */
        public InterfaceC2153b f3542g;

        /* renamed from: h */
        public long f3543h;

        /* renamed from: i */
        public boolean f3544i;

        public a(InterfaceC2117i<? super T> interfaceC2117i, long j7) {
            this.f3540e = interfaceC2117i;
            this.f3541f = j7;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3542g.dispose();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3542g.isDisposed();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3544i) {
                return;
            }
            this.f3544i = true;
            this.f3540e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3544i) {
                C1908a.m2205b(th);
            } else {
                this.f3544i = true;
                this.f3540e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3544i) {
                return;
            }
            long j7 = this.f3543h;
            if (j7 != this.f3541f) {
                this.f3543h = j7 + 1;
                return;
            }
            this.f3544i = true;
            this.f3542g.dispose();
            this.f3540e.mo1016a(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3542g, interfaceC2153b)) {
                this.f3542g = interfaceC2153b;
                this.f3540e.onSubscribe(this);
            }
        }
    }

    public C1329p0(InterfaceC2125q<T> interfaceC2125q, long j7) {
        this.f3538a = interfaceC2125q;
        this.f3539b = j7;
    }

    @Override // p040e4.InterfaceC0949b
    /* renamed from: a */
    public AbstractC2120l<T> mo860a() {
        return new C1323o0(this.f3538a, this.f3539b, null, false);
    }

    @Override // p194y3.AbstractC2116h
    /* renamed from: c */
    public void mo1488c(InterfaceC2117i<? super T> interfaceC2117i) {
        this.f3538a.subscribe(new a(interfaceC2117i, this.f3539b));
    }
}
