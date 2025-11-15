package p194y3;

import java.util.Objects;
import p074i6.InterfaceC1167a;
import p074i6.InterfaceC1168b;
import p120o4.C1590c;
import p160t4.C1908a;
import p186x2.C2074b;

/* compiled from: Flowable.java */
/* renamed from: y3.f */
/* loaded from: classes.dex */
public abstract class AbstractC2114f<T> implements InterfaceC1167a<T> {

    /* renamed from: e */
    public static final int f6246e = Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());

    /* renamed from: a */
    public final void m2553a(InterfaceC1168b<? super T> interfaceC1168b) {
        if (interfaceC1168b instanceof InterfaceC2115g) {
            m2554c((InterfaceC2115g) interfaceC1168b);
        } else {
            Objects.requireNonNull(interfaceC1168b, "s is null");
            m2554c(new C1590c(interfaceC1168b));
        }
    }

    /* renamed from: c */
    public final void m2554c(InterfaceC2115g<? super T> interfaceC2115g) {
        Objects.requireNonNull(interfaceC2115g, "s is null");
        try {
            mo1175d(interfaceC2115g);
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

    /* renamed from: d */
    public abstract void mo1175d(InterfaceC1168b<? super T> interfaceC1168b);
}
