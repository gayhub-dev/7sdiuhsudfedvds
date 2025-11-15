package p050f6;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p016b6.InterfaceC0493x;

/* compiled from: UnsupportedDateTimeField.java */
/* renamed from: f6.o */
/* loaded from: classes.dex */
public final class C1028o extends AbstractC0471b implements Serializable {

    /* renamed from: g */
    public static HashMap<AbstractC0472c, C1028o> f1936g = null;
    private static final long serialVersionUID = -1934618396111902255L;

    /* renamed from: e */
    public final AbstractC0472c f1937e;

    /* renamed from: f */
    public final AbstractC0477h f1938f;

    public C1028o(AbstractC0472c abstractC0472c, AbstractC0477h abstractC0477h) {
        if (abstractC0472c == null || abstractC0477h == null) {
            throw new IllegalArgumentException();
        }
        this.f1937e = abstractC0472c;
        this.f1938f = abstractC0477h;
    }

    private Object readResolve() {
        return m1032y(this.f1937e, this.f1938f);
    }

    /* renamed from: y */
    public static synchronized C1028o m1032y(AbstractC0472c abstractC0472c, AbstractC0477h abstractC0477h) {
        C1028o c1028o;
        HashMap<AbstractC0472c, C1028o> map = f1936g;
        c1028o = null;
        if (map == null) {
            f1936g = new HashMap<>(7);
        } else {
            C1028o c1028o2 = map.get(abstractC0472c);
            if (c1028o2 == null || c1028o2.f1938f == abstractC0477h) {
                c1028o = c1028o2;
            }
        }
        if (c1028o == null) {
            c1028o = new C1028o(abstractC0472c, abstractC0477h);
            f1936g.put(abstractC0472c, c1028o);
        }
        return c1028o;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: a */
    public long mo198a(long j7, int i7) {
        return this.f1938f.mo251a(j7, i7);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: c */
    public String mo200c(int i7, Locale locale) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: d */
    public String mo201d(long j7, Locale locale) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: e */
    public String mo202e(InterfaceC0493x interfaceC0493x, Locale locale) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: f */
    public String mo203f(int i7, Locale locale) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: g */
    public String mo204g(long j7, Locale locale) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: h */
    public String mo205h(InterfaceC0493x interfaceC0493x, Locale locale) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: i */
    public AbstractC0477h mo206i() {
        return this.f1938f;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: j */
    public AbstractC0477h mo207j() {
        return null;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: k */
    public int mo208k(Locale locale) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: n */
    public String mo211n() {
        return this.f1937e.f309e;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        return null;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: p */
    public AbstractC0472c mo213p() {
        return this.f1937e;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: q */
    public boolean mo214q(long j7) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: r */
    public boolean mo215r() {
        return false;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: s */
    public boolean mo216s() {
        return false;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: t */
    public long mo217t(long j7) {
        throw m1033z();
    }

    public String toString() {
        return "UnsupportedDateTimeField";
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: u */
    public long mo218u(long j7) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: v */
    public long mo219v(long j7, int i7) {
        throw m1033z();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: w */
    public long mo220w(long j7, String str, Locale locale) {
        throw m1033z();
    }

    /* renamed from: z */
    public final UnsupportedOperationException m1033z() {
        return new UnsupportedOperationException(this.f1937e + " field is unsupported");
    }
}
