package p104m4;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p040e4.InterfaceC0954g;
import p186x2.C2074b;

/* compiled from: SpscArrayQueue.java */
/* renamed from: m4.b */
/* loaded from: classes.dex */
public final class C1488b<E> extends AtomicReferenceArray<E> implements InterfaceC0954g<E> {

    /* renamed from: j */
    public static final Integer f4233j = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    private static final long serialVersionUID = -1296597691183856449L;

    /* renamed from: e */
    public final int f4234e;

    /* renamed from: f */
    public final AtomicLong f4235f;

    /* renamed from: g */
    public long f4236g;

    /* renamed from: h */
    public final AtomicLong f4237h;

    /* renamed from: i */
    public final int f4238i;

    public C1488b(int i7) {
        super(C2074b.m2462B(i7));
        this.f4234e = length() - 1;
        this.f4235f = new AtomicLong();
        this.f4237h = new AtomicLong();
        this.f4238i = Math.min(i7 / 4, f4233j.intValue());
    }

    @Override // p040e4.InterfaceC0955h
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // p040e4.InterfaceC0955h
    public boolean isEmpty() {
        return this.f4235f.get() == this.f4237h.get();
    }

    @Override // p040e4.InterfaceC0955h
    public boolean offer(E e7) {
        Objects.requireNonNull(e7, "Null is not a valid element");
        int i7 = this.f4234e;
        long j7 = this.f4235f.get();
        int i8 = ((int) j7) & i7;
        if (j7 >= this.f4236g) {
            long j8 = this.f4238i + j7;
            if (get(i7 & ((int) j8)) == null) {
                this.f4236g = j8;
            } else if (get(i8) != null) {
                return false;
            }
        }
        lazySet(i8, e7);
        this.f4235f.lazySet(j7 + 1);
        return true;
    }

    @Override // p040e4.InterfaceC0954g, p040e4.InterfaceC0955h
    public E poll() {
        long j7 = this.f4237h.get();
        int i7 = ((int) j7) & this.f4234e;
        E e7 = get(i7);
        if (e7 == null) {
            return null;
        }
        this.f4237h.lazySet(j7 + 1);
        lazySet(i7, null);
        return e7;
    }
}
