package p050f6;

import java.io.Serializable;
import java.util.Locale;
import p009b.C0413b;
import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.C0485p;
import p159t3.AbstractC1904c;
import p186x2.C2074b;

/* compiled from: AbstractReadableInstantFieldProperty.java */
/* renamed from: f6.a */
/* loaded from: classes.dex */
public abstract class AbstractC1014a implements Serializable {
    private static final long serialVersionUID = 1971226328211649661L;

    /* renamed from: a */
    public int m1027a() {
        return mo273e().mo199b(mo274g());
    }

    /* renamed from: b */
    public String m1028b(Locale locale) {
        C0485p.a aVar = (C0485p.a) this;
        return aVar.f349f.mo201d(aVar.f348e.f397e, locale);
    }

    /* renamed from: c */
    public String m1029c(Locale locale) {
        C0485p.a aVar = (C0485p.a) this;
        return aVar.f349f.mo204g(aVar.f348e.f397e, locale);
    }

    /* renamed from: d */
    public AbstractC1904c mo272d() {
        throw new UnsupportedOperationException("The method getChronology() was added in v1.4 and needs to be implemented by subclasses of AbstractReadableInstantFieldProperty");
    }

    /* renamed from: e */
    public abstract AbstractC0471b mo273e();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractC1014a)) {
            return false;
        }
        AbstractC1014a abstractC1014a = (AbstractC1014a) obj;
        return m1027a() == abstractC1014a.m1027a() && m1030f().equals(abstractC1014a.m1030f()) && C2074b.m2483f(mo272d(), abstractC1014a.mo272d());
    }

    /* renamed from: f */
    public AbstractC0472c m1030f() {
        return mo273e().mo213p();
    }

    /* renamed from: g */
    public abstract long mo274g();

    public int hashCode() {
        return mo272d().hashCode() + m1030f().hashCode() + (m1027a() * 17);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Property[");
        sbM112a.append(mo273e().mo211n());
        sbM112a.append("]");
        return sbM112a.toString();
    }
}
