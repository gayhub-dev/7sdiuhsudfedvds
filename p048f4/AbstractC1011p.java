package p048f4;

import p040e4.InterfaceC0954g;
import p186x2.C2074b;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: QueueDrainObserver.java */
/* renamed from: f4.p */
/* loaded from: classes.dex */
public abstract class AbstractC1011p<T, U, V> implements InterfaceC2127s<T> {

    /* renamed from: f */
    public final InterfaceC2127s<? super V> f1905f;

    /* renamed from: g */
    public final InterfaceC0954g<U> f1906g;

    /* renamed from: h */
    public volatile boolean f1907h;

    /* renamed from: i */
    public volatile boolean f1908i;

    /* renamed from: j */
    public Throwable f1909j;

    public AbstractC1011p(InterfaceC2127s<? super V> interfaceC2127s, InterfaceC0954g<U> interfaceC0954g) {
        this.f1905f = interfaceC2127s;
        this.f1906g = interfaceC0954g;
    }

    /* renamed from: a */
    public void mo1020a(InterfaceC2127s<? super V> interfaceC2127s, U u6) {
    }

    /* renamed from: b */
    public final boolean m1021b() {
        return this.f1910e.getAndIncrement() == 0;
    }

    /* renamed from: c */
    public final boolean m1022c() {
        return this.f1910e.get() == 0 && this.f1910e.compareAndSet(0, 1);
    }

    /* renamed from: d */
    public final void m1023d(U u6, boolean z6, InterfaceC2153b interfaceC2153b) {
        InterfaceC2127s<? super V> interfaceC2127s = this.f1905f;
        InterfaceC0954g<U> interfaceC0954g = this.f1906g;
        if (this.f1910e.get() == 0 && this.f1910e.compareAndSet(0, 1)) {
            mo1020a(interfaceC2127s, u6);
            if (m1025f(-1) == 0) {
                return;
            }
        } else {
            interfaceC0954g.offer(u6);
            if (!m1021b()) {
                return;
            }
        }
        C2074b.m2482e(interfaceC0954g, interfaceC2127s, z6, interfaceC2153b, this);
    }

    /* renamed from: e */
    public final void m1024e(U u6, boolean z6, InterfaceC2153b interfaceC2153b) {
        InterfaceC2127s<? super V> interfaceC2127s = this.f1905f;
        InterfaceC0954g<U> interfaceC0954g = this.f1906g;
        if (this.f1910e.get() != 0 || !this.f1910e.compareAndSet(0, 1)) {
            interfaceC0954g.offer(u6);
            if (!m1021b()) {
                return;
            }
        } else if (interfaceC0954g.isEmpty()) {
            mo1020a(interfaceC2127s, u6);
            if (m1025f(-1) == 0) {
                return;
            }
        } else {
            interfaceC0954g.offer(u6);
        }
        C2074b.m2482e(interfaceC0954g, interfaceC2127s, z6, interfaceC2153b, this);
    }

    /* renamed from: f */
    public final int m1025f(int i7) {
        return this.f1910e.addAndGet(i7);
    }
}
