package p120o4;

import p040e4.InterfaceC0948a;
import p040e4.InterfaceC0952e;
import p074i6.InterfaceC1169c;
import p130p4.EnumC1740c;
import p160t4.C1908a;

/* compiled from: BasicFuseableConditionalSubscriber.java */
/* renamed from: o4.a */
/* loaded from: classes.dex */
public abstract class AbstractC1588a<T, R> implements InterfaceC0948a<T>, InterfaceC0952e<R> {

    /* renamed from: e */
    public final InterfaceC0948a<? super R> f4825e;

    /* renamed from: f */
    public InterfaceC1169c f4826f;

    /* renamed from: g */
    public InterfaceC0952e<T> f4827g;

    /* renamed from: h */
    public boolean f4828h;

    public AbstractC1588a(InterfaceC0948a<? super R> interfaceC0948a) {
        this.f4825e = interfaceC0948a;
    }

    @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
    /* renamed from: b */
    public final void mo1177b(InterfaceC1169c interfaceC1169c) {
        if (EnumC1740c.m1890b(this.f4826f, interfaceC1169c)) {
            this.f4826f = interfaceC1169c;
            if (interfaceC1169c instanceof InterfaceC0952e) {
                this.f4827g = (InterfaceC0952e) interfaceC1169c;
            }
            this.f4825e.mo1177b(this);
        }
    }

    @Override // p074i6.InterfaceC1169c
    public void cancel() {
        this.f4826f.cancel();
    }

    @Override // p040e4.InterfaceC0955h
    public void clear() {
        this.f4827g.clear();
    }

    @Override // p040e4.InterfaceC0955h
    public boolean isEmpty() {
        return this.f4827g.isEmpty();
    }

    @Override // p040e4.InterfaceC0955h
    public final boolean offer(R r6) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // p074i6.InterfaceC1168b
    public void onComplete() {
        if (this.f4828h) {
            return;
        }
        this.f4828h = true;
        this.f4825e.onComplete();
    }

    @Override // p074i6.InterfaceC1168b
    public void onError(Throwable th) {
        if (this.f4828h) {
            C1908a.m2205b(th);
        } else {
            this.f4828h = true;
            this.f4825e.onError(th);
        }
    }

    @Override // p074i6.InterfaceC1169c
    public void request(long j7) {
        this.f4826f.request(j7);
    }
}
