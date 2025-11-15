package p034d6;

import java.util.Locale;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p016b6.AbstractC0478i;
import p016b6.C0479j;
import p050f6.AbstractC1015b;
import p050f6.C1029p;
import p186x2.C2074b;

/* compiled from: GJEraDateTimeField.java */
/* renamed from: d6.g */
/* loaded from: classes.dex */
public final class C0880g extends AbstractC1015b {

    /* renamed from: b */
    public final AbstractC0876c f1448b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0880g(AbstractC0876c abstractC0876c) {
        super(AbstractC0472c.f288f);
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        this.f1448b = abstractC0876c;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: b */
    public int mo199b(long j7) {
        return this.f1448b.m743l0(j7) <= 0 ? 0 : 1;
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: f */
    public String mo203f(int i7, Locale locale) {
        return C0881h.m757b(locale).f1450a[i7];
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: i */
    public AbstractC0477h mo206i() {
        return C1029p.m1034m(AbstractC0478i.f322f);
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: k */
    public int mo208k(Locale locale) {
        return C0881h.m757b(locale).f1459j;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: l */
    public int mo209l() {
        return 1;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: m */
    public int mo210m() {
        return 0;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        return null;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: r */
    public boolean mo215r() {
        return false;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: u */
    public long mo218u(long j7) {
        if (mo199b(j7) == 1) {
            return this.f1448b.mo750s0(0L, 1);
        }
        return Long.MIN_VALUE;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: v */
    public long mo219v(long j7, int i7) {
        C2074b.m2477Q(this, i7, 0, 1);
        if (mo199b(j7) == i7) {
            return j7;
        }
        return this.f1448b.mo750s0(j7, -this.f1448b.m743l0(j7));
    }

    @Override // p050f6.AbstractC1015b, p016b6.AbstractC0471b
    /* renamed from: w */
    public long mo220w(long j7, String str, Locale locale) {
        Integer num = C0881h.m757b(locale).f1456g.get(str);
        if (num != null) {
            return mo219v(j7, num.intValue());
        }
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        throw new C0479j(AbstractC0472c.f288f, str);
    }
}
