package p153s4;

import p022c4.EnumC0515c;
import p138q4.C1769a;
import p138q4.EnumC1776h;
import p160t4.C1908a;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: SerializedObserver.java */
/* renamed from: s4.e */
/* loaded from: classes.dex */
public final class C1882e<T> implements InterfaceC2127s<T>, InterfaceC2153b {

    /* renamed from: e */
    public final InterfaceC2127s<? super T> f5473e;

    /* renamed from: f */
    public InterfaceC2153b f5474f;

    /* renamed from: g */
    public boolean f5475g;

    /* renamed from: h */
    public C1769a<Object> f5476h;

    /* renamed from: i */
    public volatile boolean f5477i;

    public C1882e(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f5473e = interfaceC2127s;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        this.f5474f.dispose();
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return this.f5474f.isDisposed();
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        if (this.f5477i) {
            return;
        }
        synchronized (this) {
            if (this.f5477i) {
                return;
            }
            if (!this.f5475g) {
                this.f5477i = true;
                this.f5475g = true;
                this.f5473e.onComplete();
            } else {
                C1769a<Object> c1769a = this.f5476h;
                if (c1769a == null) {
                    c1769a = new C1769a<>(4);
                    this.f5476h = c1769a;
                }
                c1769a.m1956a(EnumC1776h.COMPLETE);
            }
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        if (this.f5477i) {
            C1908a.m2205b(th);
            return;
        }
        synchronized (this) {
            boolean z6 = false;
            if (this.f5477i) {
                z6 = true;
            } else {
                if (this.f5475g) {
                    this.f5477i = true;
                    C1769a<Object> c1769a = this.f5476h;
                    if (c1769a == null) {
                        c1769a = new C1769a<>(4);
                        this.f5476h = c1769a;
                    }
                    c1769a.f5047a[0] = new EnumC1776h.b(th);
                    return;
                }
                this.f5477i = true;
                this.f5475g = true;
            }
            if (z6) {
                C1908a.m2205b(th);
            } else {
                this.f5473e.onError(th);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0063, code lost:
    
        continue;
     */
    @Override // p194y3.InterfaceC2127s
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onNext(T r7) {
        /*
            r6 = this;
            boolean r0 = r6.f5477i
            if (r0 == 0) goto L5
            return
        L5:
            if (r7 != 0) goto L17
            z3.b r7 = r6.f5474f
            r7.dispose()
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r0 = "onNext called with null. Null values are generally not allowed in 2.x operators and sources."
            r7.<init>(r0)
            r6.onError(r7)
            return
        L17:
            monitor-enter(r6)
            boolean r0 = r6.f5477i     // Catch: java.lang.Throwable -> L6e
            if (r0 == 0) goto L1e
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6e
            return
        L1e:
            boolean r0 = r6.f5475g     // Catch: java.lang.Throwable -> L6e
            r1 = 4
            if (r0 == 0) goto L33
            q4.a<java.lang.Object> r0 = r6.f5476h     // Catch: java.lang.Throwable -> L6e
            if (r0 != 0) goto L2e
            q4.a r0 = new q4.a     // Catch: java.lang.Throwable -> L6e
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L6e
            r6.f5476h = r0     // Catch: java.lang.Throwable -> L6e
        L2e:
            r0.m1956a(r7)     // Catch: java.lang.Throwable -> L6e
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6e
            return
        L33:
            r0 = 1
            r6.f5475g = r0     // Catch: java.lang.Throwable -> L6e
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6e
            y3.s<? super T> r2 = r6.f5473e
            r2.onNext(r7)
        L3c:
            monitor-enter(r6)
            q4.a<java.lang.Object> r7 = r6.f5476h     // Catch: java.lang.Throwable -> L6b
            r2 = 0
            if (r7 != 0) goto L46
            r6.f5475g = r2     // Catch: java.lang.Throwable -> L6b
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6b
            goto L6a
        L46:
            r3 = 0
            r6.f5476h = r3     // Catch: java.lang.Throwable -> L6b
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6b
            y3.s<? super T> r3 = r6.f5473e
            java.lang.Object[] r7 = r7.f5047a
        L4e:
            if (r7 == 0) goto L68
            r4 = 0
        L51:
            if (r4 >= r1) goto L63
            r5 = r7[r4]
            if (r5 != 0) goto L58
            goto L63
        L58:
            boolean r5 = p138q4.EnumC1776h.m1963b(r5, r3)
            if (r5 == 0) goto L60
            r2 = 1
            goto L68
        L60:
            int r4 = r4 + 1
            goto L51
        L63:
            r7 = r7[r1]
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            goto L4e
        L68:
            if (r2 == 0) goto L3c
        L6a:
            return
        L6b:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6b
            throw r7
        L6e:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L6e
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p153s4.C1882e.onNext(java.lang.Object):void");
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        if (EnumC0515c.m328h(this.f5474f, interfaceC2153b)) {
            this.f5474f = interfaceC2153b;
            this.f5473e.onSubscribe(this);
        }
    }
}
