package p138q4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: VolatileSizeArrayList.java */
/* renamed from: q4.j */
/* loaded from: classes.dex */
public final class C1778j<T> extends AtomicInteger implements List<T>, RandomAccess {
    private static final long serialVersionUID = 3972397474470203923L;

    /* renamed from: e */
    public final ArrayList<T> f5067e = new ArrayList<>();

    @Override // java.util.List, java.util.Collection
    public boolean add(T t6) {
        boolean zAdd = this.f5067e.add(t6);
        lazySet(this.f5067e.size());
        return zAdd;
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        boolean zAddAll = this.f5067e.addAll(collection);
        lazySet(this.f5067e.size());
        return zAddAll;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.f5067e.clear();
        lazySet(0);
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.f5067e.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.f5067e.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return obj instanceof C1778j ? this.f5067e.equals(((C1778j) obj).f5067e) : this.f5067e.equals(obj);
    }

    @Override // java.util.List
    public T get(int i7) {
        return this.f5067e.get(i7);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.f5067e.hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.f5067e.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return get() == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return this.f5067e.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.f5067e.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<T> listIterator() {
        return this.f5067e.listIterator();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        boolean zRemove = this.f5067e.remove(obj);
        lazySet(this.f5067e.size());
        return zRemove;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        boolean zRemoveAll = this.f5067e.removeAll(collection);
        lazySet(this.f5067e.size());
        return zRemoveAll;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        boolean zRetainAll = this.f5067e.retainAll(collection);
        lazySet(this.f5067e.size());
        return zRetainAll;
    }

    @Override // java.util.List
    public T set(int i7, T t6) {
        return this.f5067e.set(i7, t6);
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return get();
    }

    @Override // java.util.List
    public List<T> subList(int i7, int i8) {
        return this.f5067e.subList(i7, i8);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.f5067e.toArray();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger
    public String toString() {
        return this.f5067e.toString();
    }

    @Override // java.util.List
    public ListIterator<T> listIterator(int i7) {
        return this.f5067e.listIterator(i7);
    }

    @Override // java.util.List, java.util.Collection
    public <E> E[] toArray(E[] eArr) {
        return (E[]) this.f5067e.toArray(eArr);
    }

    @Override // java.util.List
    public void add(int i7, T t6) {
        this.f5067e.add(i7, t6);
        lazySet(this.f5067e.size());
    }

    @Override // java.util.List
    public boolean addAll(int i7, Collection<? extends T> collection) {
        boolean zAddAll = this.f5067e.addAll(i7, collection);
        lazySet(this.f5067e.size());
        return zAddAll;
    }

    @Override // java.util.List
    public T remove(int i7) {
        T tRemove = this.f5067e.remove(i7);
        lazySet(this.f5067e.size());
        return tRemove;
    }
}
