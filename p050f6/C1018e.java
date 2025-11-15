package p050f6;

import p016b6.AbstractC0477h;
import p016b6.AbstractC0478i;

/* compiled from: DecoratedDurationField.java */
/* renamed from: f6.e */
/* loaded from: classes.dex */
public class C1018e extends AbstractC1016c {
    private static final long serialVersionUID = 8019982251647420015L;

    /* renamed from: f */
    public final AbstractC0477h f1914f;

    public C1018e(AbstractC0477h abstractC0477h, AbstractC0478i abstractC0478i) {
        super(abstractC0478i);
        if (abstractC0477h == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (!abstractC0477h.mo258l()) {
            throw new IllegalArgumentException("The field must be supported");
        }
        this.f1914f = abstractC0477h;
    }

    @Override // p016b6.AbstractC0477h
    /* renamed from: i */
    public boolean mo257i() {
        return this.f1914f.mo257i();
    }
}
