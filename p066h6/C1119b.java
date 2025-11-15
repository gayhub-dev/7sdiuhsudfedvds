package p066h6;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;
import p009b.C0413b;
import p016b6.AbstractC0475f;
import p034d6.AbstractC0874a;
import p034d6.C0885l;
import p159t3.AbstractC1904c;

/* compiled from: DateTimeZoneBuilder.java */
/* renamed from: h6.b */
/* loaded from: classes.dex */
public class C1119b {

    /* compiled from: DateTimeZoneBuilder.java */
    /* renamed from: h6.b$a */
    public static final class a extends AbstractC0475f {
        private static final long serialVersionUID = 6941492635554961361L;

        /* renamed from: j */
        public final int f2420j;

        /* renamed from: k */
        public final d f2421k;

        /* renamed from: l */
        public final d f2422l;

        public a(String str, int i7, d dVar, d dVar2) {
            super(str);
            this.f2420j = i7;
            this.f2421k = dVar;
            this.f2422l = dVar2;
        }

        @Override // p016b6.AbstractC0475f
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f318e.equals(aVar.f318e) && this.f2420j == aVar.f2420j && this.f2421k.equals(aVar.f2421k) && this.f2422l.equals(aVar.f2422l);
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: i */
        public String mo244i(long j7) {
            return m1268u(j7).f2435b;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: k */
        public int mo245k(long j7) {
            return this.f2420j + m1268u(j7).f2436c;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: n */
        public int mo247n(long j7) {
            return this.f2420j;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: o */
        public boolean mo248o() {
            return false;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: p */
        public long mo249p(long j7) {
            long jM1275a;
            int i7 = this.f2420j;
            d dVar = this.f2421k;
            d dVar2 = this.f2422l;
            try {
                jM1275a = dVar.m1275a(j7, i7, dVar2.f2436c);
            } catch (ArithmeticException | IllegalArgumentException unused) {
            }
            if (j7 > 0 && jM1275a < 0) {
                jM1275a = j7;
            }
            try {
                long jM1275a2 = dVar2.m1275a(j7, i7, dVar.f2436c);
                if (j7 <= 0 || jM1275a2 >= 0) {
                    j7 = jM1275a2;
                }
            } catch (ArithmeticException | IllegalArgumentException unused2) {
            }
            return jM1275a > j7 ? j7 : jM1275a;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: r */
        public long mo250r(long j7) {
            long jM1276b;
            long j8 = j7 + 1;
            int i7 = this.f2420j;
            d dVar = this.f2421k;
            d dVar2 = this.f2422l;
            try {
                jM1276b = dVar.m1276b(j8, i7, dVar2.f2436c);
            } catch (ArithmeticException | IllegalArgumentException unused) {
            }
            if (j8 < 0 && jM1276b > 0) {
                jM1276b = j8;
            }
            try {
                long jM1276b2 = dVar2.m1276b(j8, i7, dVar.f2436c);
                if (j8 >= 0 || jM1276b2 <= 0) {
                    j8 = jM1276b2;
                }
            } catch (ArithmeticException | IllegalArgumentException unused2) {
            }
            if (jM1276b <= j8) {
                jM1276b = j8;
            }
            return jM1276b - 1;
        }

        /* renamed from: u */
        public final d m1268u(long j7) {
            long jM1275a;
            int i7 = this.f2420j;
            d dVar = this.f2421k;
            d dVar2 = this.f2422l;
            try {
                jM1275a = dVar.m1275a(j7, i7, dVar2.f2436c);
            } catch (ArithmeticException | IllegalArgumentException unused) {
                jM1275a = j7;
            }
            try {
                j7 = dVar2.m1275a(j7, i7, dVar.f2436c);
            } catch (ArithmeticException | IllegalArgumentException unused2) {
            }
            return jM1275a > j7 ? dVar : dVar2;
        }
    }

    /* compiled from: DateTimeZoneBuilder.java */
    /* renamed from: h6.b$b */
    public static final class b {

        /* renamed from: a */
        public final char f2423a;

        /* renamed from: b */
        public final int f2424b;

        /* renamed from: c */
        public final int f2425c;

        /* renamed from: d */
        public final int f2426d;

        /* renamed from: e */
        public final boolean f2427e;

        /* renamed from: f */
        public final int f2428f;

        public b(char c7, int i7, int i8, int i9, boolean z6, int i10) {
            if (c7 != 'u' && c7 != 'w' && c7 != 's') {
                throw new IllegalArgumentException("Unknown mode: " + c7);
            }
            this.f2423a = c7;
            this.f2424b = i7;
            this.f2425c = i8;
            this.f2426d = i9;
            this.f2427e = z6;
            this.f2428f = i10;
        }

        /* renamed from: a */
        public final long m1269a(AbstractC1904c abstractC1904c, long j7) {
            if (this.f2425c >= 0) {
                return abstractC1904c.mo700e().mo219v(j7, this.f2425c);
            }
            return abstractC1904c.mo700e().mo198a(abstractC1904c.mo718z().mo198a(abstractC1904c.mo700e().mo219v(j7, 1), 1), this.f2425c);
        }

        /* renamed from: b */
        public final long m1270b(AbstractC1904c abstractC1904c, long j7) {
            try {
                return m1269a(abstractC1904c, j7);
            } catch (IllegalArgumentException e7) {
                if (this.f2424b != 2 || this.f2425c != 29) {
                    throw e7;
                }
                while (true) {
                    AbstractC0874a abstractC0874a = (AbstractC0874a) abstractC1904c;
                    if (abstractC0874a.f1356K.mo214q(j7)) {
                        return m1269a(abstractC1904c, j7);
                    }
                    j7 = abstractC0874a.f1356K.mo198a(j7, 1);
                }
            }
        }

        /* renamed from: c */
        public final long m1271c(AbstractC1904c abstractC1904c, long j7) {
            try {
                return m1269a(abstractC1904c, j7);
            } catch (IllegalArgumentException e7) {
                if (this.f2424b != 2 || this.f2425c != 29) {
                    throw e7;
                }
                while (true) {
                    AbstractC0874a abstractC0874a = (AbstractC0874a) abstractC1904c;
                    if (abstractC0874a.f1356K.mo214q(j7)) {
                        return m1269a(abstractC1904c, j7);
                    }
                    j7 = abstractC0874a.f1356K.mo198a(j7, -1);
                }
            }
        }

        /* renamed from: d */
        public final long m1272d(AbstractC1904c abstractC1904c, long j7) {
            AbstractC0874a abstractC0874a = (AbstractC0874a) abstractC1904c;
            int iMo199b = this.f2426d - abstractC0874a.f1349D.mo199b(j7);
            if (iMo199b == 0) {
                return j7;
            }
            if (this.f2427e) {
                if (iMo199b < 0) {
                    iMo199b += 7;
                }
            } else if (iMo199b > 0) {
                iMo199b -= 7;
            }
            return abstractC0874a.f1349D.mo198a(j7, iMo199b);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f2423a == bVar.f2423a && this.f2424b == bVar.f2424b && this.f2425c == bVar.f2425c && this.f2426d == bVar.f2426d && this.f2427e == bVar.f2427e && this.f2428f == bVar.f2428f;
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("[OfYear]\nMode: ");
            sbM112a.append(this.f2423a);
            sbM112a.append('\n');
            sbM112a.append("MonthOfYear: ");
            sbM112a.append(this.f2424b);
            sbM112a.append('\n');
            sbM112a.append("DayOfMonth: ");
            sbM112a.append(this.f2425c);
            sbM112a.append('\n');
            sbM112a.append("DayOfWeek: ");
            sbM112a.append(this.f2426d);
            sbM112a.append('\n');
            sbM112a.append("AdvanceDayOfWeek: ");
            sbM112a.append(this.f2427e);
            sbM112a.append('\n');
            sbM112a.append("MillisOfDay: ");
            sbM112a.append(this.f2428f);
            sbM112a.append('\n');
            return sbM112a.toString();
        }
    }

    /* compiled from: DateTimeZoneBuilder.java */
    /* renamed from: h6.b$c */
    public static final class c extends AbstractC0475f {
        private static final long serialVersionUID = 7811976468055766265L;

        /* renamed from: j */
        public final long[] f2429j;

        /* renamed from: k */
        public final int[] f2430k;

        /* renamed from: l */
        public final int[] f2431l;

        /* renamed from: m */
        public final String[] f2432m;

        /* renamed from: n */
        public final a f2433n;

        public c(String str, long[] jArr, int[] iArr, int[] iArr2, String[] strArr, a aVar) {
            super(str);
            this.f2429j = jArr;
            this.f2430k = iArr;
            this.f2431l = iArr2;
            this.f2432m = strArr;
            this.f2433n = aVar;
        }

        /* renamed from: u */
        public static c m1273u(DataInput dataInput, String str) throws IOException {
            int unsignedByte;
            int unsignedShort = dataInput.readUnsignedShort();
            String[] strArr = new String[unsignedShort];
            for (int i7 = 0; i7 < unsignedShort; i7++) {
                strArr[i7] = dataInput.readUTF();
            }
            int i8 = dataInput.readInt();
            long[] jArr = new long[i8];
            int[] iArr = new int[i8];
            int[] iArr2 = new int[i8];
            String[] strArr2 = new String[i8];
            for (int i9 = 0; i9 < i8; i9++) {
                jArr[i9] = C1119b.m1267b(dataInput);
                iArr[i9] = (int) C1119b.m1267b(dataInput);
                iArr2[i9] = (int) C1119b.m1267b(dataInput);
                if (unsignedShort < 256) {
                    try {
                        unsignedByte = dataInput.readUnsignedByte();
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        throw new IOException("Invalid encoding");
                    }
                } else {
                    unsignedByte = dataInput.readUnsignedShort();
                }
                strArr2[i9] = strArr[unsignedByte];
            }
            return new c(str, jArr, iArr, iArr2, strArr2, dataInput.readBoolean() ? new a(str, (int) C1119b.m1267b(dataInput), d.m1274c(dataInput), d.m1274c(dataInput)) : null);
        }

        @Override // p016b6.AbstractC0475f
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (this.f318e.equals(cVar.f318e) && Arrays.equals(this.f2429j, cVar.f2429j) && Arrays.equals(this.f2432m, cVar.f2432m) && Arrays.equals(this.f2430k, cVar.f2430k) && Arrays.equals(this.f2431l, cVar.f2431l)) {
                a aVar = this.f2433n;
                a aVar2 = cVar.f2433n;
                if (aVar == null) {
                    if (aVar2 == null) {
                        return true;
                    }
                } else if (aVar.equals(aVar2)) {
                    return true;
                }
            }
            return false;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: i */
        public String mo244i(long j7) {
            long[] jArr = this.f2429j;
            int iBinarySearch = Arrays.binarySearch(jArr, j7);
            if (iBinarySearch >= 0) {
                return this.f2432m[iBinarySearch];
            }
            int i7 = ~iBinarySearch;
            if (i7 < jArr.length) {
                return i7 > 0 ? this.f2432m[i7 - 1] : "UTC";
            }
            a aVar = this.f2433n;
            return aVar == null ? this.f2432m[i7 - 1] : aVar.m1268u(j7).f2435b;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: k */
        public int mo245k(long j7) {
            long[] jArr = this.f2429j;
            int iBinarySearch = Arrays.binarySearch(jArr, j7);
            if (iBinarySearch >= 0) {
                return this.f2430k[iBinarySearch];
            }
            int i7 = ~iBinarySearch;
            if (i7 >= jArr.length) {
                a aVar = this.f2433n;
                return aVar == null ? this.f2430k[i7 - 1] : aVar.mo245k(j7);
            }
            if (i7 > 0) {
                return this.f2430k[i7 - 1];
            }
            return 0;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: n */
        public int mo247n(long j7) {
            long[] jArr = this.f2429j;
            int iBinarySearch = Arrays.binarySearch(jArr, j7);
            if (iBinarySearch >= 0) {
                return this.f2431l[iBinarySearch];
            }
            int i7 = ~iBinarySearch;
            if (i7 >= jArr.length) {
                a aVar = this.f2433n;
                return aVar == null ? this.f2431l[i7 - 1] : aVar.f2420j;
            }
            if (i7 > 0) {
                return this.f2431l[i7 - 1];
            }
            return 0;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: o */
        public boolean mo248o() {
            return false;
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: p */
        public long mo249p(long j7) {
            long[] jArr = this.f2429j;
            int iBinarySearch = Arrays.binarySearch(jArr, j7);
            int i7 = iBinarySearch >= 0 ? iBinarySearch + 1 : ~iBinarySearch;
            if (i7 < jArr.length) {
                return jArr[i7];
            }
            a aVar = this.f2433n;
            if (aVar == null) {
                return j7;
            }
            long j8 = jArr[jArr.length - 1];
            if (j7 < j8) {
                j7 = j8;
            }
            return aVar.mo249p(j7);
        }

        @Override // p016b6.AbstractC0475f
        /* renamed from: r */
        public long mo250r(long j7) {
            long[] jArr = this.f2429j;
            int iBinarySearch = Arrays.binarySearch(jArr, j7);
            if (iBinarySearch >= 0) {
                return j7 > Long.MIN_VALUE ? j7 - 1 : j7;
            }
            int i7 = ~iBinarySearch;
            if (i7 < jArr.length) {
                if (i7 > 0) {
                    long j8 = jArr[i7 - 1];
                    if (j8 > Long.MIN_VALUE) {
                        return j8 - 1;
                    }
                }
                return j7;
            }
            a aVar = this.f2433n;
            if (aVar != null) {
                long jMo250r = aVar.mo250r(j7);
                if (jMo250r < j7) {
                    return jMo250r;
                }
            }
            long j9 = jArr[i7 - 1];
            return j9 > Long.MIN_VALUE ? j9 - 1 : j7;
        }
    }

    /* compiled from: DateTimeZoneBuilder.java */
    /* renamed from: h6.b$d */
    public static final class d {

        /* renamed from: a */
        public final b f2434a;

        /* renamed from: b */
        public final String f2435b;

        /* renamed from: c */
        public final int f2436c;

        public d(b bVar, String str, int i7) {
            this.f2434a = bVar;
            this.f2435b = str;
            this.f2436c = i7;
        }

        /* renamed from: c */
        public static d m1274c(DataInput dataInput) {
            return new d(new b((char) dataInput.readUnsignedByte(), dataInput.readUnsignedByte(), dataInput.readByte(), dataInput.readUnsignedByte(), dataInput.readBoolean(), (int) C1119b.m1267b(dataInput)), dataInput.readUTF(), (int) C1119b.m1267b(dataInput));
        }

        /* renamed from: a */
        public long m1275a(long j7, int i7, int i8) {
            b bVar = this.f2434a;
            char c7 = bVar.f2423a;
            if (c7 == 'w') {
                i7 += i8;
            } else if (c7 != 's') {
                i7 = 0;
            }
            long j8 = i7;
            long j9 = j7 + j8;
            C0885l c0885l = C0885l.f1469Q;
            long jM1270b = bVar.m1270b(c0885l, c0885l.f1377t.mo198a(c0885l.f1377t.mo219v(c0885l.f1355J.mo219v(j9, bVar.f2424b), 0), bVar.f2428f));
            if (bVar.f2426d != 0) {
                jM1270b = bVar.m1272d(c0885l, jM1270b);
                if (jM1270b <= j9) {
                    jM1270b = bVar.m1272d(c0885l, bVar.m1270b(c0885l, c0885l.f1355J.mo219v(c0885l.f1356K.mo198a(jM1270b, 1), bVar.f2424b)));
                }
            } else if (jM1270b <= j9) {
                jM1270b = bVar.m1270b(c0885l, c0885l.f1356K.mo198a(jM1270b, 1));
            }
            return c0885l.f1377t.mo198a(c0885l.f1377t.mo219v(jM1270b, 0), bVar.f2428f) - j8;
        }

        /* renamed from: b */
        public long m1276b(long j7, int i7, int i8) {
            b bVar = this.f2434a;
            char c7 = bVar.f2423a;
            if (c7 == 'w') {
                i7 += i8;
            } else if (c7 != 's') {
                i7 = 0;
            }
            long j8 = i7;
            long j9 = j7 + j8;
            C0885l c0885l = C0885l.f1469Q;
            long jM1271c = bVar.m1271c(c0885l, c0885l.f1377t.mo198a(c0885l.f1377t.mo219v(c0885l.f1355J.mo219v(j9, bVar.f2424b), 0), bVar.f2428f));
            if (bVar.f2426d != 0) {
                jM1271c = bVar.m1272d(c0885l, jM1271c);
                if (jM1271c >= j9) {
                    jM1271c = bVar.m1272d(c0885l, bVar.m1271c(c0885l, c0885l.f1355J.mo219v(c0885l.f1356K.mo198a(jM1271c, -1), bVar.f2424b)));
                }
            } else if (jM1271c >= j9) {
                jM1271c = bVar.m1271c(c0885l, c0885l.f1356K.mo198a(jM1271c, -1));
            }
            return c0885l.f1377t.mo198a(c0885l.f1377t.mo219v(jM1271c, 0), bVar.f2428f) - j8;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            return this.f2436c == dVar.f2436c && this.f2435b.equals(dVar.f2435b) && this.f2434a.equals(dVar.f2434a);
        }

        public String toString() {
            return this.f2434a + " named " + this.f2435b + " at " + this.f2436c;
        }
    }

    /* renamed from: a */
    public static AbstractC0475f m1266a(DataInput dataInput, String str) throws IOException {
        int unsignedByte = dataInput.readUnsignedByte();
        if (unsignedByte == 67) {
            AbstractC0475f abstractC0475fM1273u = c.m1273u(dataInput, str);
            int i7 = C1118a.f2411l;
            return abstractC0475fM1273u instanceof C1118a ? (C1118a) abstractC0475fM1273u : new C1118a(abstractC0475fM1273u);
        }
        if (unsignedByte != 70) {
            if (unsignedByte == 80) {
                return c.m1273u(dataInput, str);
            }
            throw new IOException("Invalid encoding");
        }
        C1121d c1121d = new C1121d(str, dataInput.readUTF(), (int) m1267b(dataInput), (int) m1267b(dataInput));
        AbstractC0475f abstractC0475f = AbstractC0475f.f314f;
        return c1121d.equals(abstractC0475f) ? abstractC0475f : c1121d;
    }

    /* renamed from: b */
    public static long m1267b(DataInput dataInput) throws IOException {
        long unsignedByte;
        long j7;
        int unsignedByte2 = dataInput.readUnsignedByte();
        int i7 = unsignedByte2 >> 6;
        if (i7 == 1) {
            unsignedByte = dataInput.readUnsignedByte() | ((unsignedByte2 << 26) >> 2) | (dataInput.readUnsignedByte() << 16) | (dataInput.readUnsignedByte() << 8);
            j7 = 60000;
        } else if (i7 == 2) {
            unsignedByte = ((unsignedByte2 << 58) >> 26) | (dataInput.readUnsignedByte() << 24) | (dataInput.readUnsignedByte() << 16) | (dataInput.readUnsignedByte() << 8) | dataInput.readUnsignedByte();
            j7 = 1000;
        } else {
            if (i7 == 3) {
                return dataInput.readLong();
            }
            unsignedByte = (unsignedByte2 << 26) >> 26;
            j7 = 1800000;
        }
        return unsignedByte * j7;
    }
}
