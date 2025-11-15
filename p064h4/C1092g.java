package p064h4;

import android.support.v7.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p074i6.InterfaceC1168b;
import p074i6.InterfaceC1169c;
import p130p4.EnumC1740c;
import p186x2.C2074b;
import p194y3.AbstractC2114f;
import p194y3.InterfaceC2115g;

/* compiled from: FlowableOnBackpressureLatest.java */
/* renamed from: h4.g */
/* loaded from: classes.dex */
public final class C1092g<T> extends AbstractC1086a<T, T> {

    /* compiled from: FlowableOnBackpressureLatest.java */
    /* renamed from: h4.g$a */
    public static final class a<T> extends AtomicInteger implements InterfaceC2115g<T>, InterfaceC1169c {
        private static final long serialVersionUID = 163080509307634843L;

        /* renamed from: e */
        public final InterfaceC1168b<? super T> f2234e;

        /* renamed from: f */
        public InterfaceC1169c f2235f;

        /* renamed from: g */
        public volatile boolean f2236g;

        /* renamed from: h */
        public Throwable f2237h;

        /* renamed from: i */
        public volatile boolean f2238i;

        /* renamed from: j */
        public final AtomicLong f2239j = new AtomicLong();

        /* renamed from: k */
        public final AtomicReference<T> f2240k = new AtomicReference<>();

        public a(InterfaceC1168b<? super T> interfaceC1168b) {
            this.f2234e = interfaceC1168b;
        }

        /* renamed from: a */
        public boolean m1179a(boolean z6, boolean z7, InterfaceC1168b<?> interfaceC1168b, AtomicReference<T> atomicReference) {
            if (this.f2238i) {
                atomicReference.lazySet(null);
                return true;
            }
            if (!z6) {
                return false;
            }
            Throwable th = this.f2237h;
            if (th != null) {
                atomicReference.lazySet(null);
                interfaceC1168b.onError(th);
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
            if (EnumC1740c.m1890b(this.f2235f, interfaceC1169c)) {
                this.f2235f = interfaceC1169c;
                this.f2234e.mo1177b(this);
                interfaceC1169c.request(RecyclerView.FOREVER_NS);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
        
            if (r7 != r1.get()) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
        
            r9 = r13.f2236g;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0046, code lost:
        
            if (r2.get() != null) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0048, code lost:
        
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
        
            if (m1179a(r9, r11, r0, r2) == false) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x004f, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0052, code lost:
        
            if (r7 == 0) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0054, code lost:
        
            p186x2.C2074b.m2503z(r1, r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0057, code lost:
        
            r4 = addAndGet(-r4);
         */
        /* renamed from: c */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void m1180c() {
            /*
                r13 = this;
                int r0 = r13.getAndIncrement()
                if (r0 == 0) goto L7
                return
            L7:
                i6.b<? super T> r0 = r13.f2234e
                java.util.concurrent.atomic.AtomicLong r1 = r13.f2239j
                java.util.concurrent.atomic.AtomicReference<T> r2 = r13.f2240k
                r3 = 1
                r4 = 1
            Lf:
                r5 = 0
                r7 = r5
            L12:
                long r9 = r1.get()
                r11 = 0
                int r12 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r12 == 0) goto L38
                boolean r9 = r13.f2236g
                r10 = 0
                java.lang.Object r10 = r2.getAndSet(r10)
                if (r10 != 0) goto L26
                r12 = 1
                goto L27
            L26:
                r12 = 0
            L27:
                boolean r9 = r13.m1179a(r9, r12, r0, r2)
                if (r9 == 0) goto L2e
                return
            L2e:
                if (r12 == 0) goto L31
                goto L38
            L31:
                r0.onNext(r10)
                r9 = 1
                long r7 = r7 + r9
                goto L12
            L38:
                long r9 = r1.get()
                int r12 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                if (r12 != 0) goto L50
                boolean r9 = r13.f2236g
                java.lang.Object r10 = r2.get()
                if (r10 != 0) goto L49
                r11 = 1
            L49:
                boolean r9 = r13.m1179a(r9, r11, r0, r2)
                if (r9 == 0) goto L50
                return
            L50:
                int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r9 == 0) goto L57
                p186x2.C2074b.m2503z(r1, r7)
            L57:
                int r4 = -r4
                int r4 = r13.addAndGet(r4)
                if (r4 != 0) goto Lf
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p064h4.C1092g.a.m1180c():void");
        }

        @Override // p074i6.InterfaceC1169c
        public void cancel() {
            if (this.f2238i) {
                return;
            }
            this.f2238i = true;
            this.f2235f.cancel();
            if (getAndIncrement() == 0) {
                this.f2240k.lazySet(null);
            }
        }

        @Override // p074i6.InterfaceC1168b
        public void onComplete() {
            this.f2236g = true;
            m1180c();
        }

        @Override // p074i6.InterfaceC1168b
        public void onError(Throwable th) {
            this.f2237h = th;
            this.f2236g = true;
            m1180c();
        }

        @Override // p074i6.InterfaceC1168b
        public void onNext(T t6) {
            this.f2240k.lazySet(t6);
            m1180c();
        }

        @Override // p074i6.InterfaceC1169c
        public void request(long j7) {
            if (EnumC1740c.m1889a(j7)) {
                C2074b.m2478a(this.f2239j, j7);
                m1180c();
            }
        }
    }

    public C1092g(AbstractC2114f<T> abstractC2114f) {
        super(abstractC2114f);
    }

    @Override // p194y3.AbstractC2114f
    /* renamed from: d */
    public void mo1175d(InterfaceC1168b<? super T> interfaceC1168b) {
        this.f2206f.m2554c(new a(interfaceC1168b));
    }
}
