package p141r;

import p141r.AbstractC1815j;

/* compiled from: TransitionOptions.java */
/* renamed from: r.j */
/* loaded from: classes.dex */
public abstract class AbstractC1815j<CHILD extends AbstractC1815j<CHILD, TranscodeType>, TranscodeType> implements Cloneable {
    /* renamed from: b */
    public final CHILD m2045b() {
        try {
            return (CHILD) super.clone();
        } catch (CloneNotSupportedException e7) {
            throw new RuntimeException(e7);
        }
    }

    public Object clone() {
        try {
            return (AbstractC1815j) super.clone();
        } catch (CloneNotSupportedException e7) {
            throw new RuntimeException(e7);
        }
    }
}
