package p194y3;

import java.util.Objects;
import p186x2.C2074b;

/* compiled from: Maybe.java */
/* renamed from: y3.h */
/* loaded from: classes.dex */
public abstract class AbstractC2116h<T> implements InterfaceC2118j<T> {
    @Override // p194y3.InterfaceC2118j
    /* renamed from: b */
    public final void mo2555b(InterfaceC2117i<? super T> interfaceC2117i) {
        Objects.requireNonNull(interfaceC2117i, "observer is null");
        try {
            mo1488c(interfaceC2117i);
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
    public abstract void mo1488c(InterfaceC2117i<? super T> interfaceC2117i);
}
