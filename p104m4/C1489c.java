package p104m4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p040e4.InterfaceC0954g;
import p186x2.C2074b;

/* compiled from: SpscLinkedArrayQueue.java */
/* renamed from: m4.c */
/* loaded from: classes.dex */
public final class C1489c<T> implements InterfaceC0954g<T> {

    /* renamed from: m */
    public static final int f4239m = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();

    /* renamed from: n */
    public static final Object f4240n = new Object();

    /* renamed from: e */
    public final AtomicLong f4241e;

    /* renamed from: f */
    public int f4242f;

    /* renamed from: g */
    public long f4243g;

    /* renamed from: h */
    public final int f4244h;

    /* renamed from: i */
    public AtomicReferenceArray<Object> f4245i;

    /* renamed from: j */
    public final int f4246j;

    /* renamed from: k */
    public AtomicReferenceArray<Object> f4247k;

    /* renamed from: l */
    public final AtomicLong f4248l;

    public C1489c(int i7) {
        AtomicLong atomicLong = new AtomicLong();
        this.f4241e = atomicLong;
        this.f4248l = new AtomicLong();
        int iM2462B = C2074b.m2462B(Math.max(8, i7));
        int i8 = iM2462B - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(iM2462B + 1);
        this.f4245i = atomicReferenceArray;
        this.f4244h = i8;
        this.f4242f = Math.min(iM2462B / 4, f4239m);
        this.f4247k = atomicReferenceArray;
        this.f4246j = i8;
        this.f4243g = i8 - 1;
        atomicLong.lazySet(0L);
    }

    /* renamed from: a */
    public final long m1647a() {
        return this.f4248l.get();
    }

    /* renamed from: b */
    public final long m1648b() {
        return this.f4241e.get();
    }

    @Override // p040e4.InterfaceC0955h
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    /* renamed from: d */
    public boolean m1649d(T t6, T t7) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.f4245i;
        long jM1648b = m1648b();
        int i7 = this.f4244h;
        long j7 = 2 + jM1648b;
        if (atomicReferenceArray.get(((int) j7) & i7) == null) {
            int i8 = ((int) jM1648b) & i7;
            atomicReferenceArray.lazySet(i8 + 1, t7);
            atomicReferenceArray.lazySet(i8, t6);
            this.f4241e.lazySet(j7);
            return true;
        }
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f4245i = atomicReferenceArray2;
        int i9 = ((int) jM1648b) & i7;
        atomicReferenceArray2.lazySet(i9 + 1, t7);
        atomicReferenceArray2.lazySet(i9, t6);
        atomicReferenceArray.lazySet(atomicReferenceArray.length() - 1, atomicReferenceArray2);
        atomicReferenceArray.lazySet(i9, f4240n);
        this.f4241e.lazySet(j7);
        return true;
    }

    /* renamed from: e */
    public T m1650e() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.f4247k;
        long j7 = this.f4248l.get();
        int i7 = this.f4246j;
        int i8 = ((int) j7) & i7;
        T t6 = (T) atomicReferenceArray.get(i8);
        if (t6 != f4240n) {
            return t6;
        }
        int i9 = i7 + 1;
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) atomicReferenceArray.get(i9);
        atomicReferenceArray.lazySet(i9, null);
        this.f4247k = atomicReferenceArray2;
        return (T) atomicReferenceArray2.get(i8);
    }

    @Override // p040e4.InterfaceC0955h
    public boolean isEmpty() {
        return m1648b() == m1647a();
    }

    @Override // p040e4.InterfaceC0955h
    public boolean offer(T t6) {
        Objects.requireNonNull(t6, "Null is not a valid element");
        AtomicReferenceArray<Object> atomicReferenceArray = this.f4245i;
        long j7 = this.f4241e.get();
        int i7 = this.f4244h;
        int i8 = ((int) j7) & i7;
        if (j7 < this.f4243g) {
            atomicReferenceArray.lazySet(i8, t6);
            this.f4241e.lazySet(j7 + 1);
            return true;
        }
        long j8 = this.f4242f + j7;
        if (atomicReferenceArray.get(((int) j8) & i7) == null) {
            this.f4243g = j8 - 1;
            atomicReferenceArray.lazySet(i8, t6);
            this.f4241e.lazySet(j7 + 1);
            return true;
        }
        long j9 = j7 + 1;
        if (atomicReferenceArray.get(((int) j9) & i7) == null) {
            atomicReferenceArray.lazySet(i8, t6);
            this.f4241e.lazySet(j9);
            return true;
        }
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f4245i = atomicReferenceArray2;
        this.f4243g = (i7 + j7) - 1;
        atomicReferenceArray2.lazySet(i8, t6);
        atomicReferenceArray.lazySet(atomicReferenceArray.length() - 1, atomicReferenceArray2);
        atomicReferenceArray.lazySet(i8, f4240n);
        this.f4241e.lazySet(j9);
        return true;
    }

    @Override // p040e4.InterfaceC0954g, p040e4.InterfaceC0955h
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.f4247k;
        long j7 = this.f4248l.get();
        int i7 = this.f4246j;
        int i8 = ((int) j7) & i7;
        T t6 = (T) atomicReferenceArray.get(i8);
        boolean z6 = t6 == f4240n;
        if (t6 != null && !z6) {
            atomicReferenceArray.lazySet(i8, null);
            this.f4248l.lazySet(j7 + 1);
            return t6;
        }
        if (!z6) {
            return null;
        }
        int i9 = i7 + 1;
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) atomicReferenceArray.get(i9);
        atomicReferenceArray.lazySet(i9, null);
        this.f4247k = atomicReferenceArray2;
        T t7 = (T) atomicReferenceArray2.get(i8);
        if (t7 != null) {
            atomicReferenceArray2.lazySet(i8, null);
            this.f4248l.lazySet(j7 + 1);
        }
        return t7;
    }
}
