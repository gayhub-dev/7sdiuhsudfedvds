package p064h4;

import android.support.v7.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicLong;
import p005a4.C0010b;
import p014b4.InterfaceC0441a;
import p040e4.InterfaceC0954g;
import p074i6.InterfaceC1168b;
import p074i6.InterfaceC1169c;
import p104m4.C1488b;
import p104m4.C1489c;
import p130p4.AbstractC1738a;
import p130p4.EnumC1740c;
import p186x2.C2074b;
import p194y3.AbstractC2114f;
import p194y3.InterfaceC2115g;

/* compiled from: FlowableOnBackpressureBuffer.java */
/* renamed from: h4.d */
/* loaded from: classes.dex */
public final class C1089d<T> extends AbstractC1086a<T, T> {

    /* renamed from: g */
    public final int f2213g;

    /* renamed from: h */
    public final boolean f2214h;

    /* renamed from: i */
    public final boolean f2215i;

    /* renamed from: j */
    public final InterfaceC0441a f2216j;

    /* compiled from: FlowableOnBackpressureBuffer.java */
    /* renamed from: h4.d$a */
    public static final class a<T> extends AbstractC1738a<T> implements InterfaceC2115g<T> {
        private static final long serialVersionUID = -2514538129242366402L;

        /* renamed from: e */
        public final InterfaceC1168b<? super T> f2217e;

        /* renamed from: f */
        public final InterfaceC0954g<T> f2218f;

        /* renamed from: g */
        public final boolean f2219g;

        /* renamed from: h */
        public final InterfaceC0441a f2220h;

        /* renamed from: i */
        public InterfaceC1169c f2221i;

        /* renamed from: j */
        public volatile boolean f2222j;

        /* renamed from: k */
        public volatile boolean f2223k;

        /* renamed from: l */
        public Throwable f2224l;

        /* renamed from: m */
        public final AtomicLong f2225m = new AtomicLong();

        public a(InterfaceC1168b<? super T> interfaceC1168b, int i7, boolean z6, boolean z7, InterfaceC0441a interfaceC0441a) {
            this.f2217e = interfaceC1168b;
            this.f2220h = interfaceC0441a;
            this.f2219g = z7;
            this.f2218f = z6 ? new C1489c<>(i7) : new C1488b<>(i7);
        }

        /* renamed from: a */
        public boolean m1176a(boolean z6, boolean z7, InterfaceC1168b<? super T> interfaceC1168b) {
            if (this.f2222j) {
                this.f2218f.clear();
                return true;
            }
            if (!z6) {
                return false;
            }
            if (this.f2219g) {
                if (!z7) {
                    return false;
                }
                Throwable th = this.f2224l;
                if (th != null) {
                    interfaceC1168b.onError(th);
                } else {
                    interfaceC1168b.onComplete();
                }
                return true;
            }
            Throwable th2 = this.f2224l;
            if (th2 != null) {
                this.f2218f.clear();
                interfaceC1168b.onError(th2);
                return true;
            }
            if (!z7) {
                return false;
            }
            interfaceC1168b.onComplete();
            return true;
        }

        @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
        /* renamed from: b */
        public void mo1177b(InterfaceC1169c interfaceC1169c) {
            if (EnumC1740c.m1890b(this.f2221i, interfaceC1169c)) {
                this.f2221i = interfaceC1169c;
                this.f2217e.mo1177b(this);
                interfaceC1169c.request(RecyclerView.FOREVER_NS);
            }
        }

        @Override // p074i6.InterfaceC1169c
        public void cancel() {
            if (this.f2222j) {
                return;
            }
            this.f2222j = true;
            this.f2221i.cancel();
            if (getAndIncrement() == 0) {
                this.f2218f.clear();
            }
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            this.f2218f.clear();
        }

        /* renamed from: d */
        public void m1178d() {
            if (getAndIncrement() == 0) {
                InterfaceC0954g<T> interfaceC0954g = this.f2218f;
                InterfaceC1168b<? super T> interfaceC1168b = this.f2217e;
                int iAddAndGet = 1;
                while (!m1176a(this.f2223k, interfaceC0954g.isEmpty(), interfaceC1168b)) {
                    long j7 = this.f2225m.get();
                    long j8 = 0;
                    while (j8 != j7) {
                        boolean z6 = this.f2223k;
                        T tPoll = interfaceC0954g.poll();
                        boolean z7 = tPoll == null;
                        if (m1176a(z6, z7, interfaceC1168b)) {
                            return;
                        }
                        if (z7) {
                            break;
                        }
                        interfaceC1168b.onNext(tPoll);
                        j8++;
                    }
                    if (j8 == j7 && m1176a(this.f2223k, interfaceC0954g.isEmpty(), interfaceC1168b)) {
                        return;
                    }
                    if (j8 != 0 && j7 != RecyclerView.FOREVER_NS) {
                        this.f2225m.addAndGet(-j8);
                    }
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return this.f2218f.isEmpty();
        }

        @Override // p074i6.InterfaceC1168b
        public void onComplete() {
            this.f2223k = true;
            m1178d();
        }

        @Override // p074i6.InterfaceC1168b
        public void onError(Throwable th) {
            this.f2224l = th;
            this.f2223k = true;
            m1178d();
        }

        @Override // p074i6.InterfaceC1168b
        public void onNext(T t6) {
            if (this.f2218f.offer(t6)) {
                m1178d();
                return;
            }
            this.f2221i.cancel();
            C0010b c0010b = new C0010b("Buffer is full");
            try {
                this.f2220h.run();
            } catch (Throwable th) {
                C2074b.m2470J(th);
                c0010b.initCause(th);
            }
            this.f2224l = c0010b;
            this.f2223k = true;
            m1178d();
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            return this.f2218f.poll();
        }

        @Override // p074i6.InterfaceC1169c
        public void request(long j7) {
            if (EnumC1740c.m1889a(j7)) {
                C2074b.m2478a(this.f2225m, j7);
                m1178d();
            }
        }
    }

    public C1089d(AbstractC2114f<T> abstractC2114f, int i7, boolean z6, boolean z7, InterfaceC0441a interfaceC0441a) {
        super(abstractC2114f);
        this.f2213g = i7;
        this.f2214h = z6;
        this.f2215i = z7;
        this.f2216j = interfaceC0441a;
    }

    @Override // p194y3.AbstractC2114f
    /* renamed from: d */
    public void mo1175d(InterfaceC1168b<? super T> interfaceC1168b) {
        this.f2206f.m2554c(new a(interfaceC1168b, this.f2213g, this.f2214h, this.f2215i, this.f2216j));
    }
}
