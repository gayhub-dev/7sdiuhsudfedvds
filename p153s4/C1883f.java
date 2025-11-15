package p153s4;

import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* JADX WARN: Unexpected interfaces in signature: [y3.c] */
/* compiled from: TestObserver.java */
/* renamed from: s4.f */
/* loaded from: classes.dex */
public class C1883f<T> extends AbstractC1878a<T, C1883f<T>> implements InterfaceC2127s<T>, InterfaceC2153b, InterfaceC2117i<T>, InterfaceC2130v<T> {

    /* renamed from: j */
    public final InterfaceC2127s<? super T> f5478j;

    /* renamed from: k */
    public final AtomicReference<InterfaceC2153b> f5479k;

    /* compiled from: TestObserver.java */
    /* renamed from: s4.f$a */
    public enum a implements InterfaceC2127s<Object> {
        INSTANCE;

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        }
    }

    public C1883f() {
        a aVar = a.INSTANCE;
        this.f5479k = new AtomicReference<>();
        this.f5478j = aVar;
    }

    @Override // p194y3.InterfaceC2117i
    /* renamed from: a */
    public void mo1016a(T t6) {
        onNext(t6);
        onComplete();
    }

    @Override // p201z3.InterfaceC2153b
    public final void dispose() {
        EnumC0515c.m323a(this.f5479k);
    }

    @Override // p201z3.InterfaceC2153b
    public final boolean isDisposed() {
        return EnumC0515c.m324b(this.f5479k.get());
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        if (!this.f5468i) {
            this.f5468i = true;
            if (this.f5479k.get() == null) {
                this.f5466g.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            Thread.currentThread();
            this.f5467h++;
            this.f5478j.onComplete();
        } finally {
            this.f5464e.countDown();
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        if (!this.f5468i) {
            this.f5468i = true;
            if (this.f5479k.get() == null) {
                this.f5466g.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            Thread.currentThread();
            if (th == null) {
                this.f5466g.add(new NullPointerException("onError received a null Throwable"));
            } else {
                this.f5466g.add(th);
            }
            this.f5478j.onError(th);
        } finally {
            this.f5464e.countDown();
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        if (!this.f5468i) {
            this.f5468i = true;
            if (this.f5479k.get() == null) {
                this.f5466g.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        Thread.currentThread();
        this.f5465f.add(t6);
        if (t6 == null) {
            this.f5466g.add(new NullPointerException("onNext received a null value"));
        }
        this.f5478j.onNext(t6);
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        Thread.currentThread();
        if (interfaceC2153b == null) {
            this.f5466g.add(new NullPointerException("onSubscribe received a null Subscription"));
            return;
        }
        if (this.f5479k.compareAndSet(null, interfaceC2153b)) {
            this.f5478j.onSubscribe(interfaceC2153b);
            return;
        }
        interfaceC2153b.dispose();
        if (this.f5479k.get() != EnumC0515c.DISPOSED) {
            this.f5466g.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + interfaceC2153b));
        }
    }
}
