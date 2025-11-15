package p194y3;

import java.util.Objects;
import p186x2.C2074b;

/* compiled from: Single.java */
/* renamed from: y3.u */
/* loaded from: classes.dex */
public abstract class AbstractC2129u<T> implements InterfaceC2131w<T> {
    @Override // p194y3.InterfaceC2131w
    /* renamed from: b */
    public final void mo2562b(InterfaceC2130v<? super T> interfaceC2130v) {
        Objects.requireNonNull(interfaceC2130v, "observer is null");
        try {
            mo1492c(interfaceC2130v);
        } catch (NullPointerException e7) {
            throw e7;
        } catch (Throwable th) {
            C2074b.m2470J(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    /* renamed from: c */
    public abstract void mo1492c(InterfaceC2130v<? super T> interfaceC2130v);
}
