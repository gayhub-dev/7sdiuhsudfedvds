package p161t5;

import android.support.v7.widget.ActivityChooserView;
import java.util.AbstractList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: BlockingArrayQueue.java */
/* renamed from: t5.d */
/* loaded from: classes.dex */
public class BlockingQueueC1912d<E> extends AbstractList<E> implements BlockingQueue<E> {

    /* renamed from: e */
    public final int f5615e;

    /* renamed from: f */
    public final AtomicInteger f5616f = new AtomicInteger();

    /* renamed from: g */
    public final int f5617g;

    /* renamed from: h */
    public volatile int f5618h;

    /* renamed from: i */
    public Object[] f5619i;

    /* renamed from: j */
    public final ReentrantLock f5620j;

    /* renamed from: k */
    public final Condition f5621k;

    /* renamed from: l */
    public int f5622l;

    /* renamed from: m */
    public final ReentrantLock f5623m;

    /* renamed from: n */
    public int f5624n;

    public BlockingQueueC1912d(int i7, int i8) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f5620j = reentrantLock;
        this.f5621k = reentrantLock.newCondition();
        this.f5623m = new ReentrantLock();
        Object[] objArr = new Object[i7];
        this.f5619i = objArr;
        this.f5618h = objArr.length;
        this.f5617g = i8;
        this.f5615e = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    /* renamed from: a */
    public final boolean m2209a() {
        int i7;
        if (this.f5617g <= 0) {
            return false;
        }
        this.f5623m.lock();
        try {
            this.f5620j.lock();
            try {
                int i8 = this.f5622l;
                int i9 = this.f5624n;
                Object[] objArr = new Object[this.f5618h + this.f5617g];
                if (i8 < i9) {
                    i7 = i9 - i8;
                    System.arraycopy(this.f5619i, i8, objArr, 0, i7);
                } else if (i8 > i9 || this.f5616f.get() > 0) {
                    int i10 = (this.f5618h + i9) - i8;
                    int i11 = this.f5618h - i8;
                    System.arraycopy(this.f5619i, i8, objArr, 0, i11);
                    System.arraycopy(this.f5619i, 0, objArr, i11, i9);
                    i7 = i10;
                } else {
                    i7 = 0;
                }
                this.f5619i = objArr;
                this.f5618h = objArr.length;
                this.f5622l = 0;
                this.f5624n = i7;
                return true;
            } finally {
                this.f5620j.unlock();
            }
        } finally {
            this.f5623m.unlock();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean add(E e7) {
        return offer(e7);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.f5623m.lock();
        try {
            this.f5620j.lock();
            try {
                this.f5622l = 0;
                this.f5624n = 0;
                this.f5616f.set(0);
            } finally {
                this.f5620j.unlock();
            }
        } finally {
            this.f5623m.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue
    public E element() {
        E ePeek = peek();
        if (ePeek != null) {
            return ePeek;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i7) {
        this.f5623m.lock();
        try {
            this.f5620j.lock();
            if (i7 >= 0) {
                try {
                    if (i7 < this.f5616f.get()) {
                        int i8 = this.f5622l + i7;
                        if (i8 >= this.f5618h) {
                            i8 -= this.f5618h;
                        }
                        return (E) this.f5619i[i8];
                    }
                } finally {
                    this.f5620j.unlock();
                }
            }
            throw new IndexOutOfBoundsException("!(0<" + i7 + "<=" + this.f5616f + ")");
        } finally {
            this.f5623m.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.f5616f.get() == 0;
    }

    @Override // java.util.concurrent.BlockingQueue, java.util.Queue
    public boolean offer(E e7) {
        Objects.requireNonNull(e7);
        this.f5623m.lock();
        try {
            if (this.f5616f.get() < this.f5615e) {
                if (this.f5616f.get() == this.f5618h) {
                    this.f5620j.lock();
                    try {
                        if (m2209a()) {
                            this.f5620j.unlock();
                        } else {
                            this.f5620j.unlock();
                        }
                    } finally {
                    }
                }
                Object[] objArr = this.f5619i;
                int i7 = this.f5624n;
                objArr[i7] = e7;
                this.f5624n = (i7 + 1) % this.f5618h;
                if (this.f5616f.getAndIncrement() == 0) {
                    this.f5620j.lock();
                    try {
                        this.f5621k.signal();
                    } finally {
                    }
                }
                return true;
            }
            return false;
        } finally {
            this.f5623m.unlock();
        }
    }

    @Override // java.util.Queue
    public E peek() {
        E e7 = null;
        if (this.f5616f.get() == 0) {
            return null;
        }
        this.f5620j.lock();
        try {
            if (this.f5616f.get() > 0) {
                e7 = (E) this.f5619i[this.f5622l];
            }
            return e7;
        } finally {
            this.f5620j.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v0 */
    @Override // java.util.Queue
    public E poll() {
        E e7 = null;
        if (this.f5616f.get() == 0) {
            return null;
        }
        this.f5620j.lock();
        try {
            if (this.f5616f.get() > 0) {
                int i7 = this.f5622l;
                ?? r22 = this.f5619i;
                ?? r32 = r22[i7];
                r22[i7] = 0;
                this.f5622l = (i7 + 1) % this.f5618h;
                if (this.f5616f.decrementAndGet() > 0) {
                    this.f5621k.signal();
                }
                e7 = r32;
            }
            return e7;
        } finally {
            this.f5620j.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e7) {
        if (!offer(e7)) {
            throw new IllegalStateException("full");
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        this.f5623m.lock();
        try {
            this.f5620j.lock();
            try {
                return this.f5618h - size();
            } finally {
                this.f5620j.unlock();
            }
        } finally {
            this.f5623m.unlock();
        }
    }

    @Override // java.util.Queue
    public E remove() {
        E ePoll = poll();
        if (ePoll != null) {
            return ePoll;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i7, E e7) {
        Objects.requireNonNull(e7);
        this.f5623m.lock();
        try {
            this.f5620j.lock();
            if (i7 >= 0) {
                try {
                    if (i7 < this.f5616f.get()) {
                        int i8 = this.f5622l + i7;
                        if (i8 >= this.f5618h) {
                            i8 -= this.f5618h;
                        }
                        Object[] objArr = this.f5619i;
                        E e8 = (E) objArr[i8];
                        objArr[i8] = e7;
                        return e8;
                    }
                } finally {
                    this.f5620j.unlock();
                }
            }
            throw new IndexOutOfBoundsException("!(0<" + i7 + "<=" + this.f5616f + ")");
        } finally {
            this.f5623m.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.f5616f.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        this.f5620j.lockInterruptibly();
        while (this.f5616f.get() == 0) {
            try {
                try {
                    this.f5621k.await();
                } catch (InterruptedException e7) {
                    this.f5621k.signal();
                    throw e7;
                }
            } finally {
                this.f5620j.unlock();
            }
        }
        int i7 = this.f5622l;
        Object[] objArr = this.f5619i;
        E e8 = (E) objArr[i7];
        objArr[i7] = null;
        this.f5622l = (i7 + 1) % this.f5618h;
        if (this.f5616f.decrementAndGet() > 0) {
            this.f5621k.signal();
        }
        return e8;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i7, E e7) {
        Objects.requireNonNull(e7);
        this.f5623m.lock();
        try {
            this.f5620j.lock();
            if (i7 >= 0) {
                try {
                    if (i7 <= this.f5616f.get()) {
                        if (i7 == this.f5616f.get()) {
                            offer(e7);
                        } else {
                            if (this.f5624n == this.f5622l && !m2209a()) {
                                throw new IllegalStateException("full");
                            }
                            int i8 = this.f5622l + i7;
                            if (i8 >= this.f5618h) {
                                i8 -= this.f5618h;
                            }
                            this.f5616f.incrementAndGet();
                            int i9 = (this.f5624n + 1) % this.f5618h;
                            this.f5624n = i9;
                            if (i8 < i9) {
                                Object[] objArr = this.f5619i;
                                System.arraycopy(objArr, i8, objArr, i8 + 1, i9 - i8);
                                this.f5619i[i8] = e7;
                            } else {
                                if (i9 > 0) {
                                    Object[] objArr2 = this.f5619i;
                                    System.arraycopy(objArr2, 0, objArr2, 1, i9);
                                    Object[] objArr3 = this.f5619i;
                                    objArr3[0] = objArr3[this.f5618h - 1];
                                }
                                Object[] objArr4 = this.f5619i;
                                System.arraycopy(objArr4, i8, objArr4, i8 + 1, (this.f5618h - i8) - 1);
                                this.f5619i[i8] = e7;
                            }
                        }
                        this.f5620j.unlock();
                        return;
                    }
                } catch (Throwable th) {
                    this.f5620j.unlock();
                    throw th;
                }
            }
            throw new IndexOutOfBoundsException("!(0<" + i7 + "<=" + this.f5616f + ")");
        } finally {
            this.f5623m.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i7) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i7) {
        this.f5623m.lock();
        try {
            this.f5620j.lock();
            if (i7 >= 0) {
                try {
                    if (i7 < this.f5616f.get()) {
                        int i8 = this.f5622l + i7;
                        if (i8 >= this.f5618h) {
                            i8 -= this.f5618h;
                        }
                        Object[] objArr = this.f5619i;
                        E e7 = (E) objArr[i8];
                        int i9 = this.f5624n;
                        if (i8 < i9) {
                            System.arraycopy(objArr, i8 + 1, objArr, i8, i9 - i8);
                            this.f5624n--;
                            this.f5616f.decrementAndGet();
                        } else {
                            System.arraycopy(objArr, i8 + 1, objArr, i8, (this.f5618h - i8) - 1);
                            if (this.f5624n > 0) {
                                Object[] objArr2 = this.f5619i;
                                int i10 = this.f5618h;
                                Object[] objArr3 = this.f5619i;
                                objArr2[i10] = objArr3[0];
                                System.arraycopy(objArr3, 1, objArr3, 0, this.f5624n - 1);
                                this.f5624n--;
                            } else {
                                this.f5624n = this.f5618h - 1;
                            }
                            this.f5616f.decrementAndGet();
                        }
                        return e7;
                    }
                } finally {
                    this.f5620j.unlock();
                }
            }
            throw new IndexOutOfBoundsException("!(0<" + i7 + "<=" + this.f5616f + ")");
        } finally {
            this.f5623m.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j7, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j7);
        this.f5620j.lockInterruptibly();
        while (this.f5616f.get() == 0) {
            try {
                try {
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = this.f5621k.awaitNanos(nanos);
                } catch (InterruptedException e7) {
                    this.f5621k.signal();
                    throw e7;
                }
            } finally {
                this.f5620j.unlock();
            }
        }
        Object[] objArr = this.f5619i;
        int i7 = this.f5622l;
        E e8 = (E) objArr[i7];
        objArr[i7] = null;
        this.f5622l = (i7 + 1) % this.f5618h;
        if (this.f5616f.decrementAndGet() > 0) {
            this.f5621k.signal();
        }
        return e8;
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e7, long j7, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
}
