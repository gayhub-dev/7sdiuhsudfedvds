package p088k4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import p138q4.C1774f;
import p153s4.AbstractC1880c;
import p160t4.C1908a;
import p194y3.C2119k;
import p194y3.InterfaceC2125q;

/* compiled from: BlockingObservableNext.java */
/* renamed from: k4.e */
/* loaded from: classes.dex */
public final class C1262e<T> implements Iterable<T> {

    /* renamed from: e */
    public final InterfaceC2125q<T> f2960e;

    /* compiled from: BlockingObservableNext.java */
    /* renamed from: k4.e$a */
    public static final class a<T> implements Iterator<T> {

        /* renamed from: e */
        public final b<T> f2961e;

        /* renamed from: f */
        public final InterfaceC2125q<T> f2962f;

        /* renamed from: g */
        public T f2963g;

        /* renamed from: h */
        public boolean f2964h = true;

        /* renamed from: i */
        public boolean f2965i = true;

        /* renamed from: j */
        public Throwable f2966j;

        /* renamed from: k */
        public boolean f2967k;

        public a(InterfaceC2125q<T> interfaceC2125q, b<T> bVar) {
            this.f2962f = interfaceC2125q;
            this.f2961e = bVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() throws InterruptedException {
            boolean z6;
            Throwable th = this.f2966j;
            if (th != null) {
                throw C1774f.m1961d(th);
            }
            if (!this.f2964h) {
                return false;
            }
            if (this.f2965i) {
                if (!this.f2967k) {
                    this.f2967k = true;
                    this.f2961e.f2969f.set(1);
                    new C1289i2(this.f2962f).subscribe(this.f2961e);
                }
                try {
                    b<T> bVar = this.f2961e;
                    bVar.f2969f.set(1);
                    C2119k<T> c2119kTake = bVar.f2968e.take();
                    if (c2119kTake.m2558c()) {
                        this.f2965i = false;
                        this.f2963g = c2119kTake.m2557b();
                        z6 = true;
                    } else {
                        this.f2964h = false;
                        if (!(c2119kTake.f6248a == null)) {
                            Throwable thM2556a = c2119kTake.m2556a();
                            this.f2966j = thM2556a;
                            throw C1774f.m1961d(thM2556a);
                        }
                        z6 = false;
                    }
                    if (!z6) {
                        return false;
                    }
                } catch (InterruptedException e7) {
                    this.f2961e.dispose();
                    this.f2966j = e7;
                    throw C1774f.m1961d(e7);
                }
            }
            return true;
        }

        @Override // java.util.Iterator
        public T next() {
            Throwable th = this.f2966j;
            if (th != null) {
                throw C1774f.m1961d(th);
            }
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            this.f2965i = true;
            return this.f2963g;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }

    /* compiled from: BlockingObservableNext.java */
    /* renamed from: k4.e$b */
    public static final class b<T> extends AbstractC1880c<C2119k<T>> {

        /* renamed from: e */
        public final BlockingQueue<C2119k<T>> f2968e = new ArrayBlockingQueue(1);

        /* renamed from: f */
        public final AtomicInteger f2969f = new AtomicInteger();

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            C1908a.m2205b(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            C2119k<T> c2119k = (C2119k) obj;
            if (this.f2969f.getAndSet(0) == 1 || !c2119k.m2558c()) {
                while (!this.f2968e.offer(c2119k)) {
                    C2119k<T> c2119kPoll = this.f2968e.poll();
                    if (c2119kPoll != null && !c2119kPoll.m2558c()) {
                        c2119k = c2119kPoll;
                    }
                }
            }
        }
    }

    public C1262e(InterfaceC2125q<T> interfaceC2125q) {
        this.f2960e = interfaceC2125q;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new a(this.f2960e, new b());
    }
}
