package p016b6;

import java.io.Serializable;
import p082j6.C1212a;
import p159t3.AbstractC1904c;

/* compiled from: DateTimeFieldType.java */
/* renamed from: b6.c */
/* loaded from: classes.dex */
public abstract class AbstractC0472c implements Serializable {

    /* renamed from: A */
    public static final AbstractC0472c f286A;

    /* renamed from: B */
    public static final AbstractC0472c f287B;

    /* renamed from: f */
    public static final AbstractC0472c f288f;

    /* renamed from: g */
    public static final AbstractC0472c f289g;

    /* renamed from: h */
    public static final AbstractC0472c f290h;

    /* renamed from: i */
    public static final AbstractC0472c f291i;

    /* renamed from: j */
    public static final AbstractC0472c f292j;

    /* renamed from: k */
    public static final AbstractC0472c f293k;

    /* renamed from: l */
    public static final AbstractC0472c f294l;

    /* renamed from: m */
    public static final AbstractC0472c f295m;

    /* renamed from: n */
    public static final AbstractC0472c f296n;

    /* renamed from: o */
    public static final AbstractC0472c f297o;

    /* renamed from: p */
    public static final AbstractC0472c f298p;

    /* renamed from: q */
    public static final AbstractC0472c f299q;

    /* renamed from: r */
    public static final AbstractC0472c f300r;

    /* renamed from: s */
    public static final AbstractC0472c f301s;
    private static final long serialVersionUID = -42615285973990L;

    /* renamed from: t */
    public static final AbstractC0472c f302t;

    /* renamed from: u */
    public static final AbstractC0472c f303u;

    /* renamed from: v */
    public static final AbstractC0472c f304v;

    /* renamed from: w */
    public static final AbstractC0472c f305w;

    /* renamed from: x */
    public static final AbstractC0472c f306x;

    /* renamed from: y */
    public static final AbstractC0472c f307y;

    /* renamed from: z */
    public static final AbstractC0472c f308z;

    /* renamed from: e */
    public final String f309e;

    /* compiled from: DateTimeFieldType.java */
    /* renamed from: b6.c$a */
    public static class a extends AbstractC0472c {
        private static final long serialVersionUID = -9937958251642L;

        /* renamed from: C */
        public final byte f310C;

        /* renamed from: D */
        public final transient AbstractC0478i f311D;

        /* renamed from: E */
        public final transient AbstractC0478i f312E;

        public a(String str, byte b7, AbstractC0478i abstractC0478i, AbstractC0478i abstractC0478i2) {
            super(str);
            this.f310C = b7;
            this.f311D = abstractC0478i;
            this.f312E = abstractC0478i2;
        }

        private Object readResolve() {
            switch (this.f310C) {
                case 1:
                    return AbstractC0472c.f288f;
                case 2:
                    return AbstractC0472c.f289g;
                case 3:
                    return AbstractC0472c.f290h;
                case 4:
                    return AbstractC0472c.f291i;
                case 5:
                    return AbstractC0472c.f292j;
                case 6:
                    return AbstractC0472c.f293k;
                case 7:
                    return AbstractC0472c.f294l;
                case 8:
                    return AbstractC0472c.f295m;
                case 9:
                    return AbstractC0472c.f296n;
                case 10:
                    return AbstractC0472c.f297o;
                case 11:
                    return AbstractC0472c.f298p;
                case 12:
                    return AbstractC0472c.f299q;
                case 13:
                    return AbstractC0472c.f300r;
                case 14:
                    return AbstractC0472c.f301s;
                case 15:
                    return AbstractC0472c.f302t;
                case 16:
                    return AbstractC0472c.f303u;
                case 17:
                    return AbstractC0472c.f304v;
                case 18:
                    return AbstractC0472c.f305w;
                case 19:
                    return AbstractC0472c.f306x;
                case 20:
                    return AbstractC0472c.f307y;
                case 21:
                    return AbstractC0472c.f308z;
                case 22:
                    return AbstractC0472c.f286A;
                case 23:
                    return AbstractC0472c.f287B;
                default:
                    return this;
            }
        }

        @Override // p016b6.AbstractC0472c
        /* renamed from: a */
        public AbstractC0478i mo222a() {
            return this.f311D;
        }

        @Override // p016b6.AbstractC0472c
        /* renamed from: b */
        public AbstractC0471b mo223b(AbstractC1904c abstractC1904c) {
            AbstractC1904c abstractC1904cM225a = C0473d.m225a(abstractC1904c);
            switch (this.f310C) {
                case 1:
                    return abstractC1904cM225a.mo704i();
                case 2:
                    return abstractC1904cM225a.mo692O();
                case 3:
                    return abstractC1904cM225a.mo697b();
                case 4:
                    return abstractC1904cM225a.mo691N();
                case 5:
                    return abstractC1904cM225a.mo690M();
                case 6:
                    return abstractC1904cM225a.mo702g();
                case 7:
                    return abstractC1904cM225a.mo718z();
                case 8:
                    return abstractC1904cM225a.mo700e();
                case 9:
                    return abstractC1904cM225a.mo688I();
                case 10:
                    return abstractC1904cM225a.mo687H();
                case 11:
                    return abstractC1904cM225a.mo685F();
                case 12:
                    return abstractC1904cM225a.mo701f();
                case 13:
                    return abstractC1904cM225a.mo707o();
                case 14:
                    return abstractC1904cM225a.mo710r();
                case 15:
                    return abstractC1904cM225a.mo699d();
                case 16:
                    return abstractC1904cM225a.mo698c();
                case 17:
                    return abstractC1904cM225a.mo709q();
                case 18:
                    return abstractC1904cM225a.mo715w();
                case 19:
                    return abstractC1904cM225a.mo716x();
                case 20:
                    return abstractC1904cM225a.mo682B();
                case 21:
                    return abstractC1904cM225a.mo683C();
                case 22:
                    return abstractC1904cM225a.mo713u();
                case 23:
                    return abstractC1904cM225a.mo714v();
                default:
                    throw new InternalError();
            }
        }

        @Override // p016b6.AbstractC0472c
        /* renamed from: c */
        public AbstractC0478i mo224c() {
            return this.f312E;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof a) && this.f310C == ((a) obj).f310C;
        }

        public int hashCode() {
            return 1 << this.f310C;
        }
    }

    static {
        AbstractC0478i abstractC0478i = AbstractC0478i.f322f;
        f288f = new a("era", (byte) 1, abstractC0478i, null);
        AbstractC0478i abstractC0478i2 = AbstractC0478i.f325i;
        f289g = new a("yearOfEra", (byte) 2, abstractC0478i2, abstractC0478i);
        AbstractC0478i abstractC0478i3 = AbstractC0478i.f323g;
        f290h = new a("centuryOfEra", (byte) 3, abstractC0478i3, abstractC0478i);
        f291i = new a("yearOfCentury", (byte) 4, abstractC0478i2, abstractC0478i3);
        f292j = new a("year", (byte) 5, abstractC0478i2, null);
        AbstractC0478i abstractC0478i4 = AbstractC0478i.f328l;
        f293k = new a("dayOfYear", (byte) 6, abstractC0478i4, abstractC0478i2);
        AbstractC0478i abstractC0478i5 = AbstractC0478i.f326j;
        f294l = new a("monthOfYear", (byte) 7, abstractC0478i5, abstractC0478i2);
        f295m = new a("dayOfMonth", (byte) 8, abstractC0478i4, abstractC0478i5);
        AbstractC0478i abstractC0478i6 = AbstractC0478i.f324h;
        f296n = new a("weekyearOfCentury", (byte) 9, abstractC0478i6, abstractC0478i3);
        f297o = new a("weekyear", (byte) 10, abstractC0478i6, null);
        AbstractC0478i abstractC0478i7 = AbstractC0478i.f327k;
        f298p = new a("weekOfWeekyear", (byte) 11, abstractC0478i7, abstractC0478i6);
        f299q = new a("dayOfWeek", (byte) 12, abstractC0478i4, abstractC0478i7);
        AbstractC0478i abstractC0478i8 = AbstractC0478i.f329m;
        f300r = new a("halfdayOfDay", C1212a.f2735CR, abstractC0478i8, abstractC0478i4);
        AbstractC0478i abstractC0478i9 = AbstractC0478i.f330n;
        f301s = new a("hourOfHalfday", (byte) 14, abstractC0478i9, abstractC0478i8);
        f302t = new a("clockhourOfHalfday", (byte) 15, abstractC0478i9, abstractC0478i8);
        f303u = new a("clockhourOfDay", (byte) 16, abstractC0478i9, abstractC0478i4);
        f304v = new a("hourOfDay", (byte) 17, abstractC0478i9, abstractC0478i4);
        AbstractC0478i abstractC0478i10 = AbstractC0478i.f331o;
        f305w = new a("minuteOfDay", (byte) 18, abstractC0478i10, abstractC0478i4);
        f306x = new a("minuteOfHour", (byte) 19, abstractC0478i10, abstractC0478i9);
        AbstractC0478i abstractC0478i11 = AbstractC0478i.f332p;
        f307y = new a("secondOfDay", (byte) 20, abstractC0478i11, abstractC0478i4);
        f308z = new a("secondOfMinute", (byte) 21, abstractC0478i11, abstractC0478i10);
        AbstractC0478i abstractC0478i12 = AbstractC0478i.f333q;
        f286A = new a("millisOfDay", (byte) 22, abstractC0478i12, abstractC0478i4);
        f287B = new a("millisOfSecond", (byte) 23, abstractC0478i12, abstractC0478i11);
    }

    public AbstractC0472c(String str) {
        this.f309e = str;
    }

    /* renamed from: a */
    public abstract AbstractC0478i mo222a();

    /* renamed from: b */
    public abstract AbstractC0471b mo223b(AbstractC1904c abstractC1904c);

    /* renamed from: c */
    public abstract AbstractC0478i mo224c();

    public String toString() {
        return this.f309e;
    }
}
