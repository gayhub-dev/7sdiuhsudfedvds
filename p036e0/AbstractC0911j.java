package p036e0;

/* compiled from: DownsampleStrategy.java */
/* renamed from: e0.j */
/* loaded from: classes.dex */
public abstract class AbstractC0911j {

    /* renamed from: a */
    public static final AbstractC0911j f1640a = new d();

    /* renamed from: b */
    public static final AbstractC0911j f1641b = new c();

    /* renamed from: c */
    public static final AbstractC0911j f1642c = new a();

    /* renamed from: d */
    public static final AbstractC0911j f1643d = new b();

    /* compiled from: DownsampleStrategy.java */
    /* renamed from: e0.j$a */
    public static class a extends AbstractC0911j {
        @Override // p036e0.AbstractC0911j
        /* renamed from: a */
        public int mo836a(int i7, int i8, int i9, int i10) {
            return 2;
        }

        @Override // p036e0.AbstractC0911j
        /* renamed from: b */
        public float mo837b(int i7, int i8, int i9, int i10) {
            if (Math.min(i8 / i10, i7 / i9) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r1);
        }
    }

    /* compiled from: DownsampleStrategy.java */
    /* renamed from: e0.j$b */
    public static class b extends AbstractC0911j {
        @Override // p036e0.AbstractC0911j
        /* renamed from: a */
        public int mo836a(int i7, int i8, int i9, int i10) {
            return 2;
        }

        @Override // p036e0.AbstractC0911j
        /* renamed from: b */
        public float mo837b(int i7, int i8, int i9, int i10) {
            return Math.min(1.0f, AbstractC0911j.f1640a.mo837b(i7, i8, i9, i10));
        }
    }

    /* compiled from: DownsampleStrategy.java */
    /* renamed from: e0.j$c */
    public static class c extends AbstractC0911j {
        @Override // p036e0.AbstractC0911j
        /* renamed from: a */
        public int mo836a(int i7, int i8, int i9, int i10) {
            return 2;
        }

        @Override // p036e0.AbstractC0911j
        /* renamed from: b */
        public float mo837b(int i7, int i8, int i9, int i10) {
            return Math.max(i9 / i7, i10 / i8);
        }
    }

    /* compiled from: DownsampleStrategy.java */
    /* renamed from: e0.j$d */
    public static class d extends AbstractC0911j {
        @Override // p036e0.AbstractC0911j
        /* renamed from: a */
        public int mo836a(int i7, int i8, int i9, int i10) {
            return 2;
        }

        @Override // p036e0.AbstractC0911j
        /* renamed from: b */
        public float mo837b(int i7, int i8, int i9, int i10) {
            return Math.min(i9 / i7, i10 / i8);
        }
    }

    /* renamed from: a */
    public abstract int mo836a(int i7, int i8, int i9, int i10);

    /* renamed from: b */
    public abstract float mo837b(int i7, int i8, int i9, int i10);
}
