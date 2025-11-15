package p064h4;

import p074i6.InterfaceC1168b;
import p074i6.InterfaceC1169c;
import p194y3.AbstractC2114f;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: FlowableFromObservable.java */
/* renamed from: h4.b */
/* loaded from: classes.dex */
public final class C1087b<T> extends AbstractC2114f<T> {

    /* renamed from: f */
    public final AbstractC2120l<T> f2207f;

    /* compiled from: FlowableFromObservable.java */
    /* renamed from: h4.b$a */
    public static final class a<T> implements InterfaceC2127s<T>, InterfaceC1169c {

        /* renamed from: e */
        public final InterfaceC1168b<? super T> f2208e;

        /* renamed from: f */
        public InterfaceC2153b f2209f;

        public a(InterfaceC1168b<? super T> interfaceC1168b) {
            this.f2208e = interfaceC1168b;
        }

        @Override // p074i6.InterfaceC1169c
        public void cancel() {
            this.f2209f.dispose();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2208e.onComplete();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f2208e.onError(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f2208e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            this.f2209f = interfaceC2153b;
            this.f2208e.mo1177b(this);
        }

        @Override // p074i6.InterfaceC1169c
        public void request(long j7) {
        }
    }

    public C1087b(AbstractC2120l<T> abstractC2120l) {
        this.f2207f = abstractC2120l;
    }

    @Override // p194y3.AbstractC2114f
    /* renamed from: d */
    public void mo1175d(InterfaceC1168b<? super T> interfaceC1168b) {
        this.f2207f.subscribe(new a(interfaceC1168b));
    }
}
