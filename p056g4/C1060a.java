package p056g4;

import java.util.Objects;
import java.util.concurrent.Callable;
import p022c4.EnumC0516d;
import p186x2.C2074b;
import p194y3.AbstractC2110b;
import p194y3.InterfaceC2111c;
import p194y3.InterfaceC2112d;

/* compiled from: CompletableDefer.java */
/* renamed from: g4.a */
/* loaded from: classes.dex */
public final class C1060a extends AbstractC2110b {

    /* renamed from: a */
    public final /* synthetic */ int f2008a = 1;

    /* renamed from: b */
    public final Callable<? extends InterfaceC2112d> f2009b;

    /* JADX WARN: Multi-variable type inference failed */
    public C1060a(Throwable th) {
        this.f2009b = th;
    }

    @Override // p194y3.AbstractC2110b
    /* renamed from: c */
    public void mo1054c(InterfaceC2111c interfaceC2111c) {
        EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
        switch (this.f2008a) {
            case 0:
                try {
                    InterfaceC2112d interfaceC2112dCall = this.f2009b.call();
                    Objects.requireNonNull(interfaceC2112dCall, "The completableSupplier returned a null CompletableSource");
                    interfaceC2112dCall.mo2552b(interfaceC2111c);
                    break;
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    interfaceC2111c.onSubscribe(enumC0516d);
                    interfaceC2111c.onError(th);
                    return;
                }
            default:
                Throwable th2 = (Throwable) this.f2009b;
                interfaceC2111c.onSubscribe(enumC0516d);
                interfaceC2111c.onError(th2);
                break;
        }
    }

    public C1060a(Callable callable) {
        this.f2009b = callable;
    }
}
