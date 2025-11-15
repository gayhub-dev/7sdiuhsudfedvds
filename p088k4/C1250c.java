package p088k4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import p138q4.C1774f;
import p138q4.EnumC1776h;
import p153s4.AbstractC1880c;
import p160t4.C1908a;
import p194y3.AbstractC2120l;
import p194y3.C2119k;
import p194y3.InterfaceC2125q;

/* compiled from: BlockingObservableLatest.java */
/* renamed from: k4.c */
/* loaded from: classes.dex */
public final class C1250c<T> implements Iterable<T> {

    /* renamed from: e */
    public final InterfaceC2125q<T> f2896e;

    /* compiled from: BlockingObservableLatest.java */
    /* renamed from: k4.c$a */
    public static final class a<T> extends AbstractC1880c<C2119k<T>> implements Iterator<T> {

        /* renamed from: e */
        public C2119k<T> f2897e;

        /* renamed from: f */
        public final Semaphore f2898f = new Semaphore(0);

        /* renamed from: g */
        public final AtomicReference<C2119k<T>> f2899g = new AtomicReference<>();

        @Override // java.util.Iterator
        public boolean hasNext() throws InterruptedException {
            C2119k<T> c2119k = this.f2897e;
            if (c2119k != null && (c2119k.f6248a instanceof EnumC1776h.b)) {
                throw C1774f.m1961d(c2119k.m2556a());
            }
            if (c2119k == null) {
                try {
                    this.f2898f.acquire();
                    C2119k<T> andSet = this.f2899g.getAndSet(null);
                    this.f2897e = andSet;
                    if (andSet.f6248a instanceof EnumC1776h.b) {
                        throw C1774f.m1961d(andSet.m2556a());
                    }
                } catch (InterruptedException e7) {
                    dispose();
                    this.f2897e = new C2119k<>(new EnumC1776h.b(e7));
                    throw C1774f.m1961d(e7);
                }
            }
            return this.f2897e.m2558c();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T tM2557b = this.f2897e.m2557b();
            this.f2897e = null;
            return tM2557b;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            C1908a.m2205b(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            if (this.f2899g.getAndSet((C2119k) obj) == null) {
                this.f2898f.release();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }

    public C1250c(InterfaceC2125q<T> interfaceC2125q) {
        this.f2896e = interfaceC2125q;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        a aVar = new a();
        AbstractC2120l.wrap(this.f2896e).materialize().subscribe(aVar);
        return aVar;
    }
}
