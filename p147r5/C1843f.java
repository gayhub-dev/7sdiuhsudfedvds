package p147r5;

import java.util.Objects;
import java.util.Properties;
import p015b5.InterfaceC0458c;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: HashedSession.java */
/* renamed from: r5.f */
/* loaded from: classes.dex */
public class C1843f extends AbstractC1838a {

    /* renamed from: q */
    public static final InterfaceC2016c f5393q;

    /* renamed from: n */
    public final C1842e f5394n;

    /* renamed from: o */
    public transient boolean f5395o;

    /* renamed from: p */
    public transient boolean f5396p;

    static {
        Properties properties = C2015b.f5863a;
        f5393q = C2015b.m2349a(C1843f.class.getName());
    }

    public C1843f(C1842e c1842e, InterfaceC0458c interfaceC0458c) {
        super(c1842e, interfaceC0458c);
        this.f5395o = false;
        this.f5396p = false;
        this.f5394n = c1842e;
    }

    @Override // p147r5.AbstractC1838a
    /* renamed from: h */
    public void mo2088h() {
        Objects.requireNonNull(this.f5394n);
        super.mo2088h();
    }

    @Override // p147r5.AbstractC1838a
    /* renamed from: j */
    public void mo2090j() {
        super.mo2090j();
        Objects.requireNonNull(this.f5394n);
    }
}
