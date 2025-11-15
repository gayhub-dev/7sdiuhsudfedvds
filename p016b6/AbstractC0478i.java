package p016b6;

import java.io.Serializable;
import p159t3.AbstractC1904c;

/* compiled from: DurationFieldType.java */
/* renamed from: b6.i */
/* loaded from: classes.dex */
public abstract class AbstractC0478i implements Serializable {

    /* renamed from: f */
    public static final AbstractC0478i f322f = new a("eras", (byte) 1);

    /* renamed from: g */
    public static final AbstractC0478i f323g = new a("centuries", (byte) 2);

    /* renamed from: h */
    public static final AbstractC0478i f324h = new a("weekyears", (byte) 3);

    /* renamed from: i */
    public static final AbstractC0478i f325i = new a("years", (byte) 4);

    /* renamed from: j */
    public static final AbstractC0478i f326j = new a("months", (byte) 5);

    /* renamed from: k */
    public static final AbstractC0478i f327k = new a("weeks", (byte) 6);

    /* renamed from: l */
    public static final AbstractC0478i f328l = new a("days", (byte) 7);

    /* renamed from: m */
    public static final AbstractC0478i f329m = new a("halfdays", (byte) 8);

    /* renamed from: n */
    public static final AbstractC0478i f330n = new a("hours", (byte) 9);

    /* renamed from: o */
    public static final AbstractC0478i f331o = new a("minutes", (byte) 10);

    /* renamed from: p */
    public static final AbstractC0478i f332p = new a("seconds", (byte) 11);

    /* renamed from: q */
    public static final AbstractC0478i f333q = new a("millis", (byte) 12);
    private static final long serialVersionUID = 8765135187319L;

    /* renamed from: e */
    public final String f334e;

    /* compiled from: DurationFieldType.java */
    /* renamed from: b6.i$a */
    public static class a extends AbstractC0478i {
        private static final long serialVersionUID = 31156755687123L;

        /* renamed from: r */
        public final byte f335r;

        public a(String str, byte b7) {
            super(str);
            this.f335r = b7;
        }

        private Object readResolve() {
            switch (this.f335r) {
                case 1:
                    return AbstractC0478i.f322f;
                case 2:
                    return AbstractC0478i.f323g;
                case 3:
                    return AbstractC0478i.f324h;
                case 4:
                    return AbstractC0478i.f325i;
                case 5:
                    return AbstractC0478i.f326j;
                case 6:
                    return AbstractC0478i.f327k;
                case 7:
                    return AbstractC0478i.f328l;
                case 8:
                    return AbstractC0478i.f329m;
                case 9:
                    return AbstractC0478i.f330n;
                case 10:
                    return AbstractC0478i.f331o;
                case 11:
                    return AbstractC0478i.f332p;
                case 12:
                    return AbstractC0478i.f333q;
                default:
                    return this;
            }
        }

        @Override // p016b6.AbstractC0478i
        /* renamed from: a */
        public AbstractC0477h mo259a(AbstractC1904c abstractC1904c) {
            AbstractC1904c abstractC1904cM225a = C0473d.m225a(abstractC1904c);
            switch (this.f335r) {
                case 1:
                    return abstractC1904cM225a.mo705j();
                case 2:
                    return abstractC1904cM225a.mo696a();
                case 3:
                    return abstractC1904cM225a.mo689J();
                case 4:
                    return abstractC1904cM225a.mo693P();
                case 5:
                    return abstractC1904cM225a.mo681A();
                case 6:
                    return abstractC1904cM225a.mo686G();
                case 7:
                    return abstractC1904cM225a.mo703h();
                case 8:
                    return abstractC1904cM225a.mo708p();
                case 9:
                    return abstractC1904cM225a.mo711s();
                case 10:
                    return abstractC1904cM225a.mo717y();
                case 11:
                    return abstractC1904cM225a.mo684D();
                case 12:
                    return abstractC1904cM225a.mo712t();
                default:
                    throw new InternalError();
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof a) && this.f335r == ((a) obj).f335r;
        }

        public int hashCode() {
            return 1 << this.f335r;
        }
    }

    public AbstractC0478i(String str) {
        this.f334e = str;
    }

    /* renamed from: a */
    public abstract AbstractC0477h mo259a(AbstractC1904c abstractC1904c);

    public String toString() {
        return this.f334e;
    }
}
