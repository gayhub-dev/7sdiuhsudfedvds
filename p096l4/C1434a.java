package p096l4;

import java.util.Objects;
import p014b4.InterfaceC0454n;
import p186x2.C2074b;
import p194y3.AbstractC2129u;
import p194y3.InterfaceC2130v;
import p194y3.InterfaceC2131w;
import p201z3.InterfaceC2153b;

/* compiled from: SingleMap.java */
/* renamed from: l4.a */
/* loaded from: classes.dex */
public final class C1434a<T, R> extends AbstractC2129u<R> {

    /* renamed from: a */
    public final InterfaceC2131w<? extends T> f4175a;

    /* renamed from: b */
    public final InterfaceC0454n<? super T, ? extends R> f4176b;

    /* compiled from: SingleMap.java */
    /* renamed from: l4.a$a */
    public static final class a<T, R> implements InterfaceC2130v<T> {

        /* renamed from: e */
        public final InterfaceC2130v<? super R> f4177e;

        /* renamed from: f */
        public final InterfaceC0454n<? super T, ? extends R> f4178f;

        public a(InterfaceC2130v<? super R> interfaceC2130v, InterfaceC0454n<? super T, ? extends R> interfaceC0454n) {
            this.f4177e = interfaceC2130v;
            this.f4178f = interfaceC0454n;
        }

        @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2117i
        /* renamed from: a */
        public void mo1016a(T t6) {
            try {
                R rApply = this.f4178f.apply(t6);
                Objects.requireNonNull(rApply, "The mapper function returned a null value.");
                this.f4177e.mo1016a(rApply);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f4177e.onError(th);
            }
        }

        @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
        public void onError(Throwable th) {
            this.f4177e.onError(th);
        }

        @Override // p194y3.InterfaceC2130v, p194y3.InterfaceC2111c, p194y3.InterfaceC2117i
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            this.f4177e.onSubscribe(interfaceC2153b);
        }
    }

    public C1434a(InterfaceC2131w<? extends T> interfaceC2131w, InterfaceC0454n<? super T, ? extends R> interfaceC0454n) {
        this.f4175a = interfaceC2131w;
        this.f4176b = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2129u
    /* renamed from: c */
    public void mo1492c(InterfaceC2130v<? super R> interfaceC2130v) {
        this.f4175a.mo2562b(new a(interfaceC2130v, this.f4176b));
    }
}
