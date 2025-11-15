package p050f6;

import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;

/* compiled from: DecoratedDateTimeField.java */
/* renamed from: f6.d */
/* loaded from: classes.dex */
public abstract class AbstractC1017d extends AbstractC1015b {

    /* renamed from: b */
    public final AbstractC0471b f1913b;

    public AbstractC1017d(AbstractC0471b abstractC0471b, AbstractC0472c abstractC0472c) {
        super(abstractC0472c);
        if (abstractC0471b == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (!abstractC0471b.mo216s()) {
            throw new IllegalArgumentException("The field must be supported");
        }
        this.f1913b = abstractC0471b;
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: i */
    public AbstractC0477h mo206i() {
        return this.f1913b.mo206i();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: o */
    public AbstractC0477h mo212o() {
        return this.f1913b.mo212o();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: r */
    public boolean mo215r() {
        return this.f1913b.mo215r();
    }

    @Override // p016b6.AbstractC0471b
    /* renamed from: v */
    public long mo219v(long j7, int i7) {
        return this.f1913b.mo219v(j7, i7);
    }
}
