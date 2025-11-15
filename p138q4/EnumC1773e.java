package p138q4;

import p074i6.InterfaceC1169c;
import p160t4.C1908a;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2115g;
import p194y3.InterfaceC2117i;
import p194y3.InterfaceC2127s;
import p194y3.InterfaceC2130v;
import p201z3.InterfaceC2153b;

/* compiled from: EmptyComponent.java */
/* renamed from: q4.e */
/* loaded from: classes.dex */
public enum EnumC1773e implements InterfaceC2115g<Object>, InterfaceC2127s<Object>, InterfaceC2117i<Object>, InterfaceC2130v<Object>, InterfaceC2111c, InterfaceC1169c, InterfaceC2153b {
    INSTANCE;

    @Override // p194y3.InterfaceC2117i
    /* renamed from: a */
    public void mo1016a(Object obj) {
    }

    @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
    /* renamed from: b */
    public void mo1177b(InterfaceC1169c interfaceC1169c) {
        interfaceC1169c.cancel();
    }

    @Override // p074i6.InterfaceC1169c
    public void cancel() {
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return true;
    }

    @Override // p074i6.InterfaceC1168b
    public void onComplete() {
    }

    @Override // p074i6.InterfaceC1168b
    public void onError(Throwable th) {
        C1908a.m2205b(th);
    }

    @Override // p074i6.InterfaceC1168b
    public void onNext(Object obj) {
    }

    @Override // p194y3.InterfaceC2127s
    public void onSubscribe(InterfaceC2153b interfaceC2153b) {
        interfaceC2153b.dispose();
    }

    @Override // p074i6.InterfaceC1169c
    public void request(long j7) {
    }
}
