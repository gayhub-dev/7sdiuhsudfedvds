package p120o4;

import p040e4.InterfaceC0952e;
import p074i6.InterfaceC1168b;
import p074i6.InterfaceC1169c;
import p130p4.EnumC1740c;
import p160t4.C1908a;
import p194y3.InterfaceC2115g;

/* compiled from: BasicFuseableSubscriber.java */
/* renamed from: o4.b */
/* loaded from: classes.dex */
public abstract class AbstractC1589b<T, R> implements InterfaceC2115g<T>, InterfaceC0952e<R> {

    /* renamed from: e */
    public final InterfaceC1168b<? super R> f4829e;

    /* renamed from: f */
    public InterfaceC1169c f4830f;

    /* renamed from: g */
    public InterfaceC0952e<T> f4831g;

    /* renamed from: h */
    public boolean f4832h;

    public AbstractC1589b(InterfaceC1168b<? super R> interfaceC1168b) {
        this.f4829e = interfaceC1168b;
    }

    @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
    /* renamed from: b */
    public final void mo1177b(InterfaceC1169c interfaceC1169c) {
        if (EnumC1740c.m1890b(this.f4830f, interfaceC1169c)) {
            this.f4830f = interfaceC1169c;
            if (interfaceC1169c instanceof InterfaceC0952e) {
                this.f4831g = (InterfaceC0952e) interfaceC1169c;
            }
            this.f4829e.mo1177b(this);
        }
    }

    @Override // p074i6.InterfaceC1169c
    public void cancel() {
        this.f4830f.cancel();
    }

    @Override // p040e4.InterfaceC0955h
    public void clear() {
        this.f4831g.clear();
    }

    @Override // p040e4.InterfaceC0955h
    public boolean isEmpty() {
        return this.f4831g.isEmpty();
    }

    @Override // p040e4.InterfaceC0955h
    public final boolean offer(R r6) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // p074i6.InterfaceC1168b
    public void onComplete() {
        if (this.f4832h) {
            return;
        }
        this.f4832h = true;
        this.f4829e.onComplete();
    }

    @Override // p074i6.InterfaceC1168b
    public void onError(Throwable th) {
        if (this.f4832h) {
            C1908a.m2205b(th);
        } else {
            this.f4832h = true;
            this.f4829e.onError(th);
        }
    }

    @Override // p074i6.InterfaceC1169c
    public void request(long j7) {
        this.f4830f.request(j7);
    }
}
