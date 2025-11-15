package p088k4;

import java.util.concurrent.atomic.AtomicReference;
import p022c4.EnumC0515c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2122n;
import p194y3.InterfaceC2123o;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableCreate.java */
/* renamed from: k4.a0 */
/* loaded from: classes.dex */
public final class C1239a0<T> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final InterfaceC2123o<T> f2773e;

    /* compiled from: ObservableCreate.java */
    /* renamed from: k4.a0$a */
    public static final class a<T> extends AtomicReference<InterfaceC2153b> implements InterfaceC2122n<T>, InterfaceC2153b {
        private static final long serialVersionUID = -3434801548987643227L;

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2774e;

        public a(InterfaceC2127s<? super T> interfaceC2127s) {
            this.f2774e = interfaceC2127s;
        }

        /* renamed from: a */
        public void m1461a(Throwable th) {
            boolean z6;
            if (isDisposed()) {
                z6 = false;
            } else {
                try {
                    this.f2774e.onError(th);
                    EnumC0515c.m323a(this);
                    z6 = true;
                } catch (Throwable th2) {
                    EnumC0515c.m323a(this);
                    throw th2;
                }
            }
            if (z6) {
                return;
            }
            C1908a.m2205b(th);
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this);
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(get());
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", a.class.getSimpleName(), super.toString());
        }
    }

    public C1239a0(InterfaceC2123o<T> interfaceC2123o) {
        this.f2773e = interfaceC2123o;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        a aVar = new a(interfaceC2127s);
        interfaceC2127s.onSubscribe(aVar);
        try {
            this.f2773e.subscribe(aVar);
        } catch (Throwable th) {
            C2074b.m2470J(th);
            aVar.m1461a(th);
        }
    }
}
