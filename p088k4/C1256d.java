package p088k4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import p138q4.C1774f;
import p138q4.EnumC1776h;
import p153s4.AbstractC1879b;
import p194y3.InterfaceC2125q;

/* compiled from: BlockingObservableMostRecent.java */
/* renamed from: k4.d */
/* loaded from: classes.dex */
public final class C1256d<T> implements Iterable<T> {

    /* renamed from: e */
    public final InterfaceC2125q<T> f2934e;

    /* renamed from: f */
    public final T f2935f;

    /* compiled from: BlockingObservableMostRecent.java */
    /* renamed from: k4.d$a */
    public static final class a<T> extends AbstractC1879b<T> {

        /* renamed from: f */
        public volatile Object f2936f;

        /* compiled from: BlockingObservableMostRecent.java */
        /* renamed from: k4.d$a$a, reason: collision with other inner class name */
        public final class C2173a implements Iterator<T> {

            /* renamed from: e */
            public Object f2937e;

            public C2173a() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                this.f2937e = a.this.f2936f;
                return !EnumC1776h.m1964c(r0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Iterator
            public T next() {
                try {
                    if (this.f2937e == null) {
                        this.f2937e = a.this.f2936f;
                    }
                    if (EnumC1776h.m1964c(this.f2937e)) {
                        throw new NoSuchElementException();
                    }
                    T t6 = (T) this.f2937e;
                    if (t6 instanceof EnumC1776h.b) {
                        throw C1774f.m1961d(((EnumC1776h.b) t6).f5061e);
                    }
                    return t6;
                } finally {
                    this.f2937e = null;
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Read only iterator");
            }
        }

        public a(T t6) {
            this.f2936f = t6;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f2936f = EnumC1776h.COMPLETE;
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f2936f = new EnumC1776h.b(th);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f2936f = t6;
        }
    }

    public C1256d(InterfaceC2125q<T> interfaceC2125q, T t6) {
        this.f2934e = interfaceC2125q;
        this.f2935f = t6;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        a aVar = new a(this.f2935f);
        this.f2934e.subscribe(aVar);
        return new a.C2173a();
    }
}
