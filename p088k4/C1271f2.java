package p088k4;

import java.util.Objects;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2124p;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;

/* compiled from: ObservableLift.java */
/* renamed from: k4.f2 */
/* loaded from: classes.dex */
public final class C1271f2<R, T> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final InterfaceC2124p<? extends R, ? super T> f3055f;

    public C1271f2(InterfaceC2125q<T> interfaceC2125q, InterfaceC2124p<? extends R, ? super T> interfaceC2124p) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3055f = interfaceC2124p;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        try {
            InterfaceC2127s<? super Object> interfaceC2127sM2561a = this.f3055f.m2561a(interfaceC2127s);
            Objects.requireNonNull(interfaceC2127sM2561a, "Operator " + this.f3055f + " returned a null Observer");
            this.f2772e.subscribe(interfaceC2127sM2561a);
        } catch (NullPointerException e7) {
            throw e7;
        } catch (Throwable th) {
            C2074b.m2470J(th);
            C1908a.m2205b(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }
}
