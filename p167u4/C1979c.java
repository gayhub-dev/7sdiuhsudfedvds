package p167u4;

import android.support.v7.widget.RecyclerView;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p032d4.C0871b;
import p074i6.InterfaceC1168b;
import p074i6.InterfaceC1169c;
import p104m4.C1489c;
import p130p4.AbstractC1738a;
import p130p4.EnumC1739b;
import p130p4.EnumC1740c;
import p160t4.C1908a;
import p186x2.C2074b;

/* compiled from: UnicastProcessor.java */
/* renamed from: u4.c */
/* loaded from: classes.dex */
public final class C1979c<T> extends AbstractC1977a<T> {

    /* renamed from: f */
    public final C1489c<T> f5781f;

    /* renamed from: g */
    public final AtomicReference<Runnable> f5782g;

    /* renamed from: h */
    public final boolean f5783h;

    /* renamed from: i */
    public volatile boolean f5784i;

    /* renamed from: j */
    public Throwable f5785j;

    /* renamed from: k */
    public final AtomicReference<InterfaceC1168b<? super T>> f5786k;

    /* renamed from: l */
    public volatile boolean f5787l;

    /* renamed from: m */
    public final AtomicBoolean f5788m;

    /* renamed from: n */
    public final AbstractC1738a<T> f5789n;

    /* renamed from: o */
    public final AtomicLong f5790o;

    /* compiled from: UnicastProcessor.java */
    /* renamed from: u4.c$a */
    public final class a extends AbstractC1738a<T> {
        private static final long serialVersionUID = -4896760517184205454L;

        public a() {
        }

        @Override // p074i6.InterfaceC1169c
        public void cancel() {
            if (C1979c.this.f5787l) {
                return;
            }
            C1979c.this.f5787l = true;
            C1979c.this.m2304f();
            Objects.requireNonNull(C1979c.this);
            if (C1979c.this.f5789n.getAndIncrement() == 0) {
                C1979c.this.f5781f.clear();
                C1979c.this.f5786k.lazySet(null);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            C1979c.this.f5781f.clear();
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return C1979c.this.f5781f.isEmpty();
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            return C1979c.this.f5781f.poll();
        }

        @Override // p074i6.InterfaceC1169c
        public void request(long j7) {
            if (EnumC1740c.m1889a(j7)) {
                C2074b.m2478a(C1979c.this.f5790o, j7);
                C1979c.this.m2305g();
            }
        }
    }

    public C1979c(int i7) {
        C0871b.m677b(i7, "capacityHint");
        this.f5781f = new C1489c<>(i7);
        this.f5782g = new AtomicReference<>(null);
        this.f5783h = true;
        this.f5786k = new AtomicReference<>();
        this.f5788m = new AtomicBoolean();
        this.f5789n = new a();
        this.f5790o = new AtomicLong();
    }

    @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
    /* renamed from: b */
    public void mo1177b(InterfaceC1169c interfaceC1169c) {
        if (this.f5784i || this.f5787l) {
            interfaceC1169c.cancel();
        } else {
            interfaceC1169c.request(RecyclerView.FOREVER_NS);
        }
    }

    @Override // p194y3.AbstractC2114f
    /* renamed from: d */
    public void mo1175d(InterfaceC1168b<? super T> interfaceC1168b) {
        if (this.f5788m.get() || !this.f5788m.compareAndSet(false, true)) {
            IllegalStateException illegalStateException = new IllegalStateException("This processor allows only a single Subscriber");
            interfaceC1168b.mo1177b(EnumC1739b.INSTANCE);
            interfaceC1168b.onError(illegalStateException);
        } else {
            interfaceC1168b.mo1177b(this.f5789n);
            this.f5786k.set(interfaceC1168b);
            if (this.f5787l) {
                this.f5786k.lazySet(null);
            } else {
                m2305g();
            }
        }
    }

    /* renamed from: e */
    public boolean m2303e(boolean z6, boolean z7, boolean z8, InterfaceC1168b<? super T> interfaceC1168b, C1489c<T> c1489c) {
        if (this.f5787l) {
            c1489c.clear();
            this.f5786k.lazySet(null);
            return true;
        }
        if (!z7) {
            return false;
        }
        if (z6 && this.f5785j != null) {
            c1489c.clear();
            this.f5786k.lazySet(null);
            interfaceC1168b.onError(this.f5785j);
            return true;
        }
        if (!z8) {
            return false;
        }
        Throwable th = this.f5785j;
        this.f5786k.lazySet(null);
        if (th != null) {
            interfaceC1168b.onError(th);
        } else {
            interfaceC1168b.onComplete();
        }
        return true;
    }

    /* renamed from: f */
    public void m2304f() {
        Runnable andSet = this.f5782g.getAndSet(null);
        if (andSet != null) {
            andSet.run();
        }
    }

    /* renamed from: g */
    public void m2305g() {
        long j7;
        if (this.f5789n.getAndIncrement() != 0) {
            return;
        }
        InterfaceC1168b<? super T> interfaceC1168b = this.f5786k.get();
        int iAddAndGet = 1;
        while (interfaceC1168b == null) {
            iAddAndGet = this.f5789n.addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            } else {
                interfaceC1168b = this.f5786k.get();
            }
        }
        C1489c<T> c1489c = this.f5781f;
        boolean z6 = !this.f5783h;
        int iAddAndGet2 = 1;
        do {
            long j8 = this.f5790o.get();
            long j9 = 0;
            while (true) {
                if (j8 == j9) {
                    j7 = j9;
                    break;
                }
                boolean z7 = this.f5784i;
                T tPoll = c1489c.poll();
                boolean z8 = tPoll == null;
                j7 = j9;
                if (m2303e(z6, z7, z8, interfaceC1168b, c1489c)) {
                    return;
                }
                if (z8) {
                    break;
                }
                interfaceC1168b.onNext(tPoll);
                j9 = j7 + 1;
            }
            if (j8 == j9 && m2303e(z6, this.f5784i, c1489c.isEmpty(), interfaceC1168b, c1489c)) {
                return;
            }
            if (j7 != 0 && j8 != RecyclerView.FOREVER_NS) {
                this.f5790o.addAndGet(-j7);
            }
            iAddAndGet2 = this.f5789n.addAndGet(-iAddAndGet2);
        } while (iAddAndGet2 != 0);
    }

    @Override // p074i6.InterfaceC1168b
    public void onComplete() {
        if (this.f5784i || this.f5787l) {
            return;
        }
        this.f5784i = true;
        m2304f();
        m2305g();
    }

    @Override // p074i6.InterfaceC1168b
    public void onError(Throwable th) {
        Objects.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f5784i || this.f5787l) {
            C1908a.m2205b(th);
            return;
        }
        this.f5785j = th;
        this.f5784i = true;
        m2304f();
        m2305g();
    }

    @Override // p074i6.InterfaceC1168b
    public void onNext(T t6) {
        Objects.requireNonNull(t6, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f5784i || this.f5787l) {
            return;
        }
        this.f5781f.offer(t6);
        m2305g();
    }
}
