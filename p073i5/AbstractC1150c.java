package p073i5;

import java.io.IOException;
import java.util.Properties;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AbstractConnection.java */
/* renamed from: i5.c */
/* loaded from: classes.dex */
public abstract class AbstractC1150c implements InterfaceC1160m {

    /* renamed from: b */
    public static final InterfaceC2016c f2537b;

    /* renamed from: a */
    public final InterfaceC1161n f2538a;

    static {
        Properties properties = C2015b.f5863a;
        f2537b = C2015b.m2349a(AbstractC1150c.class.getName());
    }

    public AbstractC1150c(InterfaceC1161n interfaceC1161n) {
        this.f2538a = interfaceC1161n;
        System.currentTimeMillis();
    }

    @Override // p073i5.InterfaceC1160m
    /* renamed from: d */
    public void mo889d(long j7) {
        try {
            f2537b.mo2351a("onIdleExpired {}ms {} {}", Long.valueOf(j7), this, this.f2538a);
            if (this.f2538a.mo926s() || this.f2538a.mo925r()) {
                this.f2538a.close();
            } else {
                this.f2538a.mo927t();
            }
        } catch (IOException e7) {
            f2537b.mo2360k(e7);
            try {
                this.f2538a.close();
            } catch (IOException e8) {
                f2537b.mo2360k(e8);
            }
        }
    }

    public String toString() {
        return String.format("%s@%x", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public AbstractC1150c(InterfaceC1161n interfaceC1161n, long j7) {
        this.f2538a = interfaceC1161n;
    }
}
