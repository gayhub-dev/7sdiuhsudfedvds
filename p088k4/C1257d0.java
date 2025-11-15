package p088k4;

import java.util.Objects;
import java.util.concurrent.Callable;
import p022c4.EnumC0516d;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableDefer.java */
/* renamed from: k4.d0 */
/* loaded from: classes.dex */
public final class C1257d0<T> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final /* synthetic */ int f2939e;

    /* renamed from: f */
    public final Callable<? extends InterfaceC2125q<? extends T>> f2940f;

    /* JADX WARN: Multi-variable type inference failed */
    public C1257d0(InterfaceC2125q interfaceC2125q) {
        this.f2939e = 2;
        this.f2940f = interfaceC2125q;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s interfaceC2127s) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        switch (this.f2939e) {
            case 0:
                try {
                    InterfaceC2125q<? extends T> interfaceC2125qCall = this.f2940f.call();
                    Objects.requireNonNull(interfaceC2125qCall, "null ObservableSource supplied");
                    interfaceC2125qCall.subscribe(interfaceC2127s);
                    break;
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    interfaceC2127s.onSubscribe(enumC0516d);
                    interfaceC2127s.onError(th);
                    return;
                }
            case 1:
                try {
                    InterfaceC2125q<? extends T> interfaceC2125qCall2 = this.f2940f.call();
                    Objects.requireNonNull(interfaceC2125qCall2, "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
                    th = (Throwable) interfaceC2125qCall2;
                } catch (Throwable th2) {
                    th = th2;
                    C2074b.m2470J(th);
                }
                interfaceC2127s.onSubscribe(enumC0516d);
                interfaceC2127s.onError(th);
                break;
            default:
                ((InterfaceC2125q) this.f2940f).subscribe(interfaceC2127s);
                break;
        }
    }

    public C1257d0(Callable callable, int i7) {
        this.f2939e = i7;
        if (i7 != 1) {
            this.f2940f = callable;
        } else {
            this.f2940f = callable;
        }
    }
}
