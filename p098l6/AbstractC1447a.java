package p098l6;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: Iterators.java */
/* renamed from: l6.a */
/* loaded from: classes.dex */
public abstract class AbstractC1447a<E> implements Iterator<E> {
    public int nextIndex = 0;
    public boolean removedCurrent = false;
    public final Iterator<E> wrapped;

    public AbstractC1447a(Collection<E> collection) {
        this.wrapped = new CopyOnWriteArrayList(collection).iterator();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.wrapped.hasNext();
    }

    @Override // java.util.Iterator
    public E next() {
        this.removedCurrent = false;
        this.nextIndex++;
        return this.wrapped.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        int i7 = this.nextIndex;
        if (i7 == 0) {
            throw new IllegalStateException("Call next() first");
        }
        if (this.removedCurrent) {
            throw new IllegalStateException("Already removed current, call next()");
        }
        synchronizedRemove(i7 - 1);
        this.removedCurrent = true;
    }

    public abstract void synchronizedRemove(int i7);
}
