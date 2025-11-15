package p153s4;

import java.util.Objects;
import p022c4.EnumC0515c;
import p186x2.C2074b;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: DefaultObserver.java */
/* renamed from: s4.b */
/* loaded from: classes.dex */
public abstract class AbstractC1879b<T> implements InterfaceC2127s<T> {

    /* renamed from: e */
    public InterfaceC2153b f5469e;

    @Override // p194y3.InterfaceC2127s
    public final void onSubscribe(InterfaceC2153b interfaceC2153b) {
        boolean z6;
        InterfaceC2153b interfaceC2153b2 = this.f5469e;
        Class<?> cls = getClass();
        Objects.requireNonNull(interfaceC2153b, "next is null");
        if (interfaceC2153b2 != null) {
            interfaceC2153b.dispose();
            if (interfaceC2153b2 != EnumC0515c.DISPOSED) {
                C2074b.m2461A(cls);
            }
            z6 = false;
        } else {
            z6 = true;
        }
        if (z6) {
            this.f5469e = interfaceC2153b;
        }
    }
}
