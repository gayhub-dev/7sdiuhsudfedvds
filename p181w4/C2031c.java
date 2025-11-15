package p181w4;

import p138q4.C1769a;
import p138q4.EnumC1776h;
import p160t4.C1908a;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: SerializedSubject.java */
/* renamed from: w4.c */
/* loaded from: classes.dex */
public final class C2031c<T> extends AbstractC2032d<T> implements C1769a.a<Object> {

    /* renamed from: e */
    public final AbstractC2032d<T> f5920e;

    /* renamed from: f */
    public boolean f5921f;

    /* renamed from: g */
    public C1769a<Object> f5922g;

    /* renamed from: h */
    public volatile boolean f5923h;

    public C2031c(AbstractC2032d<T> abstractC2032d) {
        this.f5920e = abstractC2032d;
    }

    /* renamed from: b */
    public void m2386b() {
        C1769a<Object> c1769a;
        while (true) {
            synchronized (this) {
                c1769a = this.f5922g;
                if (c1769a == null) {
                    this.f5921f = false;
                    return;
                }
                this.f5922g = null;
            }
            c1769a.m1957b(this);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        if (this.f5923h) {
            return;
        }
        synchronized (this) {
            if (this.f5923h) {
                return;
            }
            this.f5923h = true;
            if (!this.f5921f) {
                this.f5921f = true;
                this.f5920e.onComplete();
                return;
            }
            C1769a<Object> c1769a = this.f5922g;
            if (c1769a == null) {
                c1769a = new C1769a<>(4);
                this.f5922g = c1769a;
            }
            c1769a.m1956a(EnumC1776h.COMPLETE);
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onError(Throwable th) {
        if (this.f5923h) {
            C1908a.m2205b(th);
            return;
        }
        synchronized (this) {
            boolean z6 = false;
            if (this.f5923h) {
                z6 = true;
            } else {
                this.f5923h = true;
                if (this.f5921f) {
                    C1769a<Object> c1769a = this.f5922g;
                    if (c1769a == null) {
                        c1769a = new C1769a<>(4);
                        this.f5922g = c1769a;
                    }
                    c1769a.f5047a[0] = new EnumC1776h.b(th);
                    return;
                }
                this.f5921f = true;
            }
            if (z6) {
                C1908a.m2205b(th);
            } else {
                this.f5920e.onError(th);
            }
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        if (this.f5923h) {
            return;
        }
        synchronized (this) {
            if (this.f5923h) {
                return;
            }
            if (!this.f5921f) {
                this.f5921f = true;
                this.f5920e.onNext(t6);
                m2386b();
            } else {
                C1769a<Object> c1769a = this.f5922g;
                if (c1769a == null) {
                    c1769a = new C1769a<>(4);
                    this.f5922g = c1769a;
                }
                c1769a.m1956a(t6);
            }
        }
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        boolean z6 = true;
        if (!this.f5923h) {
            synchronized (this) {
                if (!this.f5923h) {
                    if (this.f5921f) {
                        C1769a<Object> c1769a = this.f5922g;
                        if (c1769a == null) {
                            c1769a = new C1769a<>(4);
                            this.f5922g = c1769a;
                        }
                        c1769a.m1956a(new EnumC1776h.a(interfaceC2153b));
                        return;
                    }
                    this.f5921f = true;
                    z6 = false;
                }
            }
        }
        if (z6) {
            interfaceC2153b.dispose();
        } else {
            this.f5920e.onSubscribe(interfaceC2153b);
            m2386b();
        }
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        this.f5920e.subscribe(interfaceC2127s);
    }

    @Override // p138q4.C1769a.a, p014b4.InterfaceC0455o
    public boolean test(Object obj) {
        return EnumC1776h.m1963b(obj, this.f5920e);
    }
}
