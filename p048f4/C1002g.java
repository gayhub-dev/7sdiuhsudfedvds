package p048f4;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p138q4.EnumC1776h;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: BlockingObserver.java */
/* renamed from: f4.g */
/* loaded from: classes.dex */
public final class C1002g<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, InterfaceC2153b {

    /* renamed from: f */
    public static final Object f1881f = new Object();
    private static final long serialVersionUID = -4875965440900746268L;

    /* renamed from: e */
    public final Queue<Object> f1882e;

    public C1002g(Queue<Object> queue) {
        this.f1882e = queue;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        if (EnumC0515c.m323a(this)) {
            this.f1882e.offer(f1881f);
        }
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return get() == EnumC0515c.DISPOSED;
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        this.f1882e.offer(EnumC1776h.COMPLETE);
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        this.f1882e.offer(new EnumC1776h.b(th));
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        this.f1882e.offer(t6);
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        EnumC0515c.m327f(this, interfaceC2153b);
    }
}
