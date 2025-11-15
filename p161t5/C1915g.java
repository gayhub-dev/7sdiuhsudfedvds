package p161t5;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Unexpected interfaces in signature: [java.util.Set<E>] */
/* compiled from: ConcurrentHashSet.java */
/* renamed from: t5.g */
/* loaded from: classes.dex */
public class C1915g<E> extends AbstractSet<E> {

    /* renamed from: e */
    public final Map<E, Boolean> f5629e;

    /* renamed from: f */
    public transient Set<E> f5630f;

    public C1915g() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.f5629e = concurrentHashMap;
        this.f5630f = concurrentHashMap.keySet();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E e7) {
        return this.f5629e.put(e7, Boolean.TRUE) == null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.f5629e.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.f5629e.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        return this.f5630f.containsAll(collection);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        return obj == this || this.f5630f.equals(obj);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public int hashCode() {
        return this.f5630f.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f5629e.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return this.f5630f.iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return this.f5629e.remove(obj) != null;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        return this.f5630f.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        return this.f5630f.retainAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.f5629e.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return this.f5630f.toArray();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return this.f5630f.toString();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.f5630f.toArray(tArr);
    }
}
