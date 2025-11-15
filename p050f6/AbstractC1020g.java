package p050f6;

import p016b6.AbstractC0472c;
import p016b6.AbstractC0477h;
import p016b6.AbstractC0478i;
import p186x2.C2074b;

/* compiled from: ImpreciseDateTimeField.java */
/* renamed from: f6.g */
/* loaded from: classes.dex */
public abstract class AbstractC1020g extends AbstractC1015b {

    /* renamed from: b */
    public final long f1920b;

    /* renamed from: c */
    public final AbstractC0477h f1921c;

    /* compiled from: ImpreciseDateTimeField.java */
    /* renamed from: f6.g$a */
    public final class a extends AbstractC1016c {
        private static final long serialVersionUID = -203813474600094134L;

        public a(AbstractC0478i abstractC0478i) {
            super(abstractC0478i);
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: a */
        public long mo251a(long j7, int i7) {
            return AbstractC1020g.this.mo198a(j7, i7);
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: b */
        public long mo252b(long j7, long j8) {
            return AbstractC1020g.this.mo754A(j7, j8);
        }

        @Override // p050f6.AbstractC1016c, p016b6.AbstractC0477h
        /* renamed from: c */
        public int mo253c(long j7, long j8) {
            return C2074b.m2466F(AbstractC1020g.this.mo755C(j7, j8));
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: e */
        public long mo254e(long j7, long j8) {
            return AbstractC1020g.this.mo755C(j7, j8);
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: h */
        public long mo256h() {
            return AbstractC1020g.this.f1920b;
        }

        @Override // p016b6.AbstractC0477h
        /* renamed from: i */
        public boolean mo257i() {
            return false;
        }
    }

    public AbstractC1020g(AbstractC0472c abstractC0472c, long j7) {
        super(abstractC0472c);
        this.f1920b = j7;
        this.f1921c = new a(((AbstractC0472c.a) abstractC0472c).f311D);
    }

    /* renamed from: A */
    public abstract long mo754A(long j7, long j8);

    /* renamed from: B */
    public int m1031B(long j7, long j8) {
        return C2074b.m2466F(mo755C(j7, j8));
    }

    /* renamed from: C */
    public abstract long mo755C(long j7, long j8);

    @Override // p016b6.AbstractC0471b
    /* renamed from: i */
    public final AbstractC0477h mo206i() {
        return this.f1921c;
    }
}
