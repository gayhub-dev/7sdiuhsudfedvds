package p167u4;

import p074i6.InterfaceC1168b;
import p074i6.InterfaceC1169c;
import p138q4.C1769a;
import p138q4.EnumC1776h;
import p160t4.C1908a;

/* compiled from: SerializedProcessor.java */
/* renamed from: u4.b */
/* loaded from: classes.dex */
public final class C1978b<T> extends AbstractC1977a<T> {

    /* renamed from: f */
    public final AbstractC1977a<T> f5777f;

    /* renamed from: g */
    public boolean f5778g;

    /* renamed from: h */
    public C1769a<Object> f5779h;

    /* renamed from: i */
    public volatile boolean f5780i;

    public C1978b(AbstractC1977a<T> abstractC1977a) {
        this.f5777f = abstractC1977a;
    }

    @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
    /* renamed from: b */
    public void mo1177b(InterfaceC1169c interfaceC1169c) {
        boolean z6 = true;
        if (!this.f5780i) {
            synchronized (this) {
                if (!this.f5780i) {
                    if (this.f5778g) {
                        C1769a<Object> c1769a = this.f5779h;
                        if (c1769a == null) {
                            c1769a = new C1769a<>(4);
                            this.f5779h = c1769a;
                        }
                        c1769a.m1956a(new EnumC1776h.c(interfaceC1169c));
                        return;
                    }
                    this.f5778g = true;
                    z6 = false;
                }
            }
        }
        if (z6) {
            interfaceC1169c.cancel();
        } else {
            this.f5777f.mo1177b(interfaceC1169c);
            m2302e();
        }
    }

    @Override // p194y3.AbstractC2114f
    /* renamed from: d */
    public void mo1175d(InterfaceC1168b<? super T> interfaceC1168b) {
        this.f5777f.m2553a(interfaceC1168b);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0048, code lost:
    
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0045 A[LOOP:2: B:13:0x0016->B:31:0x0045, LOOP_END] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m2302e() {
        /*
            r7 = this;
        L0:
            monitor-enter(r7)
            q4.a<java.lang.Object> r0 = r7.f5779h     // Catch: java.lang.Throwable -> L4d
            r1 = 0
            if (r0 != 0) goto La
            r7.f5778g = r1     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4d
            return
        La:
            r2 = 0
            r7.f5779h = r2     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4d
            u4.a<T> r2 = r7.f5777f
            java.lang.Object[] r0 = r0.f5047a
            r3 = 4
        L13:
            if (r0 == 0) goto L0
            r4 = 0
        L16:
            if (r4 >= r3) goto L48
            r5 = r0[r4]
            if (r5 != 0) goto L1d
            goto L48
        L1d:
            q4.h r6 = p138q4.EnumC1776h.COMPLETE
            if (r5 != r6) goto L25
            r2.onComplete()
            goto L30
        L25:
            boolean r6 = r5 instanceof p138q4.EnumC1776h.b
            if (r6 == 0) goto L32
            q4.h$b r5 = (p138q4.EnumC1776h.b) r5
            java.lang.Throwable r5 = r5.f5061e
            r2.onError(r5)
        L30:
            r5 = 1
            goto L42
        L32:
            boolean r6 = r5 instanceof p138q4.EnumC1776h.c
            if (r6 == 0) goto L3e
            q4.h$c r5 = (p138q4.EnumC1776h.c) r5
            i6.c r5 = r5.f5062e
            r2.mo1177b(r5)
            goto L41
        L3e:
            r2.onNext(r5)
        L41:
            r5 = 0
        L42:
            if (r5 == 0) goto L45
            goto L0
        L45:
            int r4 = r4 + 1
            goto L16
        L48:
            r0 = r0[r3]
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            goto L13
        L4d:
            r0 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4d
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p167u4.C1978b.m2302e():void");
    }

    @Override // p074i6.InterfaceC1168b
    public void onComplete() {
        if (this.f5780i) {
            return;
        }
        synchronized (this) {
            if (this.f5780i) {
                return;
            }
            this.f5780i = true;
            if (!this.f5778g) {
                this.f5778g = true;
                this.f5777f.onComplete();
                return;
            }
            C1769a<Object> c1769a = this.f5779h;
            if (c1769a == null) {
                c1769a = new C1769a<>(4);
                this.f5779h = c1769a;
            }
            c1769a.m1956a(EnumC1776h.COMPLETE);
        }
    }

    @Override // p074i6.InterfaceC1168b
    public void onError(Throwable th) {
        if (this.f5780i) {
            C1908a.m2205b(th);
            return;
        }
        synchronized (this) {
            boolean z6 = false;
            if (this.f5780i) {
                z6 = true;
            } else {
                this.f5780i = true;
                if (this.f5778g) {
                    C1769a<Object> c1769a = this.f5779h;
                    if (c1769a == null) {
                        c1769a = new C1769a<>(4);
                        this.f5779h = c1769a;
                    }
                    c1769a.f5047a[0] = new EnumC1776h.b(th);
                    return;
                }
                this.f5778g = true;
            }
            if (z6) {
                C1908a.m2205b(th);
            } else {
                this.f5777f.onError(th);
            }
        }
    }

    @Override // p074i6.InterfaceC1168b
    public void onNext(T t6) {
        if (this.f5780i) {
            return;
        }
        synchronized (this) {
            if (this.f5780i) {
                return;
            }
            if (!this.f5778g) {
                this.f5778g = true;
                this.f5777f.onNext(t6);
                m2302e();
            } else {
                C1769a<Object> c1769a = this.f5779h;
                if (c1769a == null) {
                    c1769a = new C1769a<>(4);
                    this.f5779h = c1769a;
                }
                c1769a.m1956a(t6);
            }
        }
    }
}
