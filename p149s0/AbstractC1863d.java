package p149s0;

/* compiled from: StateVerifier.java */
/* renamed from: s0.d */
/* loaded from: classes.dex */
public abstract class AbstractC1863d {

    /* compiled from: StateVerifier.java */
    /* renamed from: s0.d$b */
    public static class b extends AbstractC1863d {

        /* renamed from: a */
        public volatile boolean f5438a;

        public b() {
            super(null);
        }

        @Override // p149s0.AbstractC1863d
        /* renamed from: a */
        public void mo2133a() {
            if (this.f5438a) {
                throw new IllegalStateException("Already released");
            }
        }
    }

    public AbstractC1863d(a aVar) {
    }

    /* renamed from: a */
    public abstract void mo2133a();
}
