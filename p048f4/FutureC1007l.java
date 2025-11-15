package p048f4;

import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p138q4.C1774f;
import p160t4.C1908a;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: FutureObserver.java */
/* renamed from: f4.l */
/* loaded from: classes.dex */
public final class FutureC1007l<T> extends CountDownLatch implements InterfaceC2127s<T>, Future<T>, InterfaceC2153b {

    /* renamed from: e */
    public T f1893e;

    /* renamed from: f */
    public Throwable f1894f;

    /* renamed from: g */
    public final AtomicReference<InterfaceC2153b> f1895g;

    public FutureC1007l() {
        super(1);
        this.f1895g = new AtomicReference<>();
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z6) {
        InterfaceC2153b interfaceC2153b;
        EnumC0515c enumC0515c;
        do {
            interfaceC2153b = this.f1895g.get();
            if (interfaceC2153b == this || interfaceC2153b == (enumC0515c = EnumC0515c.DISPOSED)) {
                return false;
            }
        } while (!this.f1895g.compareAndSet(interfaceC2153b, enumC0515c));
        if (interfaceC2153b != null) {
            interfaceC2153b.dispose();
        }
        countDown();
        return true;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
    }

    @Override // java.util.concurrent.Future
    public T get() throws ExecutionException, InterruptedException {
        if (getCount() != 0) {
            await();
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.f1894f;
        if (th == null) {
            return this.f1893e;
        }
        throw new ExecutionException(th);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return EnumC0515c.m324b(this.f1895g.get());
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return isDone();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return getCount() == 0;
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        InterfaceC2153b interfaceC2153b;
        if (this.f1893e == null) {
            onError(new NoSuchElementException("The source is empty"));
            return;
        }
        do {
            interfaceC2153b = this.f1895g.get();
            if (interfaceC2153b == this || interfaceC2153b == EnumC0515c.DISPOSED) {
                return;
            }
        } while (!this.f1895g.compareAndSet(interfaceC2153b, this));
        countDown();
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        InterfaceC2153b interfaceC2153b;
        if (this.f1894f != null) {
            C1908a.m2205b(th);
            return;
        }
        this.f1894f = th;
        do {
            interfaceC2153b = this.f1895g.get();
            if (interfaceC2153b == this || interfaceC2153b == EnumC0515c.DISPOSED) {
                C1908a.m2205b(th);
                return;
            }
        } while (!this.f1895g.compareAndSet(interfaceC2153b, this));
        countDown();
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        if (this.f1893e == null) {
            this.f1893e = t6;
        } else {
            this.f1895g.get().dispose();
            onError(new IndexOutOfBoundsException("More than one element received"));
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        EnumC0515c.m327f(this.f1895g, interfaceC2153b);
    }

    @Override // java.util.concurrent.Future
    public T get(long j7, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        if (getCount() != 0 && !await(j7, timeUnit)) {
            throw new TimeoutException(C1774f.m1960c(j7, timeUnit));
        }
        if (!isCancelled()) {
            Throwable th = this.f1894f;
            if (th == null) {
                return this.f1893e;
            }
            throw new ExecutionException(th);
        }
        throw new CancellationException();
    }
}
