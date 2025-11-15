package p104m4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p040e4.InterfaceC0954g;

/* compiled from: MpscLinkedQueue.java */
/* renamed from: m4.a */
/* loaded from: classes.dex */
public final class C1487a<T> implements InterfaceC0954g<T> {

    /* renamed from: e */
    public final AtomicReference<a<T>> f4230e;

    /* renamed from: f */
    public final AtomicReference<a<T>> f4231f;

    /* compiled from: MpscLinkedQueue.java */
    /* renamed from: m4.a$a */
    public static final class a<E> extends AtomicReference<a<E>> {
        private static final long serialVersionUID = 2404266111789071508L;

        /* renamed from: e */
        public E f4232e;

        public a() {
        }

        public a(E e7) {
            this.f4232e = e7;
        }
    }

    public C1487a() {
        AtomicReference<a<T>> atomicReference = new AtomicReference<>();
        this.f4230e = atomicReference;
        AtomicReference<a<T>> atomicReference2 = new AtomicReference<>();
        this.f4231f = atomicReference2;
        a<T> aVar = new a<>();
        atomicReference2.lazySet(aVar);
        atomicReference.getAndSet(aVar);
    }

    @Override // p040e4.InterfaceC0955h
    public void clear() {
        while (poll() != null && !isEmpty()) {
        }
    }

    @Override // p040e4.InterfaceC0955h
    public boolean isEmpty() {
        return this.f4231f.get() == this.f4230e.get();
    }

    @Override // p040e4.InterfaceC0955h
    public boolean offer(T t6) {
        Objects.requireNonNull(t6, "Null is not a valid element");
        a<T> aVar = new a<>(t6);
        this.f4230e.getAndSet(aVar).lazySet(aVar);
        return true;
    }

    @Override // p040e4.InterfaceC0954g, p040e4.InterfaceC0955h
    public T poll() {
        a aVar;
        a<T> aVar2 = this.f4231f.get();
        a aVar3 = aVar2.get();
        if (aVar3 != null) {
            T t6 = aVar3.f4232e;
            aVar3.f4232e = null;
            this.f4231f.lazySet(aVar3);
            return t6;
        }
        if (aVar2 == this.f4230e.get()) {
            return null;
        }
        do {
            aVar = aVar2.get();
        } while (aVar == null);
        T t7 = aVar.f4232e;
        aVar.f4232e = null;
        this.f4231f.lazySet(aVar);
        return t7;
    }
}
