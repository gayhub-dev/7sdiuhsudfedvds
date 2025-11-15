package p088k4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import p022c4.EnumC0515c;
import p104m4.C1489c;
import p138q4.C1774f;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: BlockingObservableIterable.java */
/* renamed from: k4.b */
/* loaded from: classes.dex */
public final class C1244b<T> implements Iterable<T> {

    /* renamed from: e */
    public final InterfaceC2125q<? extends T> f2811e;

    /* renamed from: f */
    public final int f2812f;

    /* compiled from: BlockingObservableIterable.java */
    /* renamed from: k4.b$a */
    public static final class a<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<T>, Iterator<T>, InterfaceC2153b {
        private static final long serialVersionUID = 6695226475494099826L;

        /* renamed from: e */
        public final C1489c<T> f2813e;

        /* renamed from: f */
        public final Lock f2814f;

        /* renamed from: g */
        public final Condition f2815g;

        /* renamed from: h */
        public volatile boolean f2816h;

        /* renamed from: i */
        public Throwable f2817i;

        public a(int i7) {
            this.f2813e = new C1489c<>(i7);
            ReentrantLock reentrantLock = new ReentrantLock();
            this.f2814f = reentrantLock;
            this.f2815g = reentrantLock.newCondition();
        }

        /* renamed from: a */
        public void m1464a() {
            this.f2814f.lock();
            try {
                this.f2815g.signalAll();
            } finally {
                this.f2814f.unlock();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (true) {
                boolean z6 = this.f2816h;
                boolean zIsEmpty = this.f2813e.isEmpty();
                if (z6) {
                    Throwable th = this.f2817i;
                    if (th != null) {
                        throw C1774f.m1961d(th);
                    }
                    if (zIsEmpty) {
                        return false;
                    }
                }
                if (!zIsEmpty) {
                    return true;
                }
                try {
                    this.f2814f.lock();
                    while (!this.f2816h && this.f2813e.isEmpty()) {
                        try {
                            this.f2815g.await();
                        } finally {
                        }
                    }
                    this.f2814f.unlock();
                } catch (InterruptedException e7) {
                    EnumC0515c.m323a(this);
                    m1464a();
                    throw C1774f.m1961d(e7);
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(get());
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                return this.f2813e.poll();
            }
            throw new NoSuchElementException();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2816h = true;
            m1464a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f2817i = th;
            this.f2816h = true;
            m1464a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f2813e.offer(t6);
            m1464a();
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this, interfaceC2153b);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    public C1244b(InterfaceC2125q<? extends T> interfaceC2125q, int i7) {
        this.f2811e = interfaceC2125q;
        this.f2812f = i7;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        a aVar = new a(this.f2812f);
        this.f2811e.subscribe(aVar);
        return aVar;
    }
}
