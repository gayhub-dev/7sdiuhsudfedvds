package p050f6;

import java.util.Locale;
import p009b.C0413b;
import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p016b6.C0479j;
import p016b6.InterfaceC0493x;

/* compiled from: BaseDateTimeField.java */
/* renamed from: f6.b */
/* loaded from: classes.dex */
public abstract class AbstractC1015b extends AbstractC0471b {

    /* renamed from: a */
    public final AbstractC0472c f1911a;

    public AbstractC1015b(AbstractC0472c abstractC0472c) {
        if (abstractC0472c == null) {
            throw new IllegalArgumentException("The type must not be null");
        }
        this.f1911a = abstractC0472c;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: a */
    public long mo198a(long j7, int i7) {
        return mo206i().mo251a(j7, i7);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: c */
    public String mo200c(int i7, Locale locale) {
        return mo203f(i7, locale);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: d */
    public String mo201d(long j7, Locale locale) {
        return mo200c(mo199b(j7), locale);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: e */
    public final String mo202e(InterfaceC0493x interfaceC0493x, Locale locale) {
        return mo200c(interfaceC0493x.mo263I(this.f1911a), locale);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: f */
    public String mo203f(int i7, Locale locale) {
        return Integer.toString(i7);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: g */
    public String mo204g(long j7, Locale locale) {
        return mo203f(mo199b(j7), locale);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: h */
    public final String mo205h(InterfaceC0493x interfaceC0493x, Locale locale) {
        return mo203f(interfaceC0493x.mo263I(this.f1911a), locale);
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: j */
    public AbstractC0477h mo207j() {
        return null;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: k */
    public int mo208k(Locale locale) {
        int iMo209l = mo209l();
        if (iMo209l >= 0) {
            if (iMo209l < 10) {
                return 1;
            }
            if (iMo209l < 100) {
                return 2;
            }
            if (iMo209l < 1000) {
                return 3;
            }
        }
        return Integer.toString(iMo209l).length();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: n */
    public final String mo211n() {
        return this.f1911a.f309e;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: p */
    public final AbstractC0472c mo213p() {
        return this.f1911a;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: q */
    public boolean mo214q(long j7) {
        return false;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: s */
    public final boolean mo216s() {
        return true;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: t */
    public long mo217t(long j7) {
        return j7 - mo218u(j7);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("DateTimeField[");
        sbM112a.append(this.f1911a.f309e);
        sbM112a.append(']');
        return sbM112a.toString();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: w */
    public long mo220w(long j7, String str, Locale locale) {
        return mo219v(j7, mo752y(str, locale));
    }

    /* renamed from: y */
    public int mo752y(String str, Locale locale) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            throw new C0479j(this.f1911a, str);
        }
    }

    /* renamed from: z */
    public int mo753z(long j7) {
        return mo209l();
    }
}
