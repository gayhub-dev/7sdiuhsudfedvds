package p088k4;

import p014b4.InterfaceC0441a;
import p022c4.EnumC0515c;
import p040e4.InterfaceC0950c;
import p048f4.AbstractC0997b;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableDoFinally.java */
/* renamed from: k4.l0 */
/* loaded from: classes.dex */
public final class C1305l0<T> extends AbstractC1238a<T, T> {

    /* renamed from: f */
    public final InterfaceC0441a f3324f;

    /* compiled from: ObservableDoFinally.java */
    /* renamed from: k4.l0$a */
    public static final class a<T> extends AbstractC0997b<T> implements InterfaceC2127s<T> {
        private static final long serialVersionUID = 4109457741734051389L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f3325e;

        /* renamed from: f */
        public final InterfaceC0441a f3326f;

        /* renamed from: g */
        public InterfaceC2153b f3327g;

        /* renamed from: h */
        public InterfaceC0950c<T> f3328h;

        /* renamed from: i */
        public boolean f3329i;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0441a interfaceC0441a) {
            this.f3325e = interfaceC2127s;
            this.f3326f = interfaceC0441a;
        }

        /* renamed from: b */
        public void m1517b() {
            if (compareAndSet(0, 1)) {
                try {
                    this.f3326f.run();
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    C1908a.m2205b(th);
                }
            }
        }

        @Override // p040e4.InterfaceC0951d
        /* renamed from: c */
        public int mo331c(int i7) {
            InterfaceC0950c<T> interfaceC0950c = this.f3328h;
            if (interfaceC0950c == null || (i7 & 4) != 0) {
                return 0;
            }
            int iMo331c = interfaceC0950c.mo331c(i7);
            if (iMo331c != 0) {
                this.f3329i = iMo331c == 1;
            }
            return iMo331c;
        }

        @Override // p040e4.InterfaceC0955h
        public void clear() {
            this.f3328h.clear();
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f3327g.dispose();
            m1517b();
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f3327g.isDisposed();
        }

        @Override // p040e4.InterfaceC0955h
        public boolean isEmpty() {
            return this.f3328h.isEmpty();
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            this.f3325e.onComplete();
            m1517b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            this.f3325e.onError(th);
            m1517b();
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            this.f3325e.onNext(t6);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            if (EnumC0515c.m328h(this.f3327g, interfaceC2153b)) {
                this.f3327g = interfaceC2153b;
                if (interfaceC2153b instanceof InterfaceC0950c) {
                    this.f3328h = (InterfaceC0950c) interfaceC2153b;
                }
                this.f3325e.onSubscribe(this);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public T poll() {
            T tPoll = this.f3328h.poll();
            if (tPoll == null && this.f3329i) {
                m1517b();
            }
            return tPoll;
        }
    }

    public C1305l0(InterfaceC2125q<T> interfaceC2125q, InterfaceC0441a interfaceC0441a) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3324f = interfaceC0441a;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f2772e.subscribe(new a(interfaceC2127s, this.f3324f));
    }
}
