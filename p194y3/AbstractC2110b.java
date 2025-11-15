package p194y3;

import java.util.Objects;
import p160t4.C1908a;
import p186x2.C2074b;

/* compiled from: Completable.java */
/* renamed from: y3.b */
/* loaded from: classes.dex */
public abstract class AbstractC2110b implements InterfaceC2112d {
    @Override // p194y3.InterfaceC2112d
    /* renamed from: b */
    public final void mo2552b(InterfaceC2111c interfaceC2111c) {
        Objects.requireNonNull(interfaceC2111c, "observer is null");
        try {
            mo1054c(interfaceC2111c);
        } catch (NullPointerException e7) {
            throw e7;
        } catch (Throwable th) {
            C2074b.m2470J(th);
            C1908a.m2205b(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    /* renamed from: c */
    public abstract void mo1054c(InterfaceC2111c interfaceC2111c);
}
