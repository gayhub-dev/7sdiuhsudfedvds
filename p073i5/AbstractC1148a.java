package p073i5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Properties;
import p009b.C0413b;
import p073i5.C1158k;
import p073i5.InterfaceC1152e;
import p161t5.C1927s;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: AbstractBuffer.java */
/* renamed from: i5.a */
/* loaded from: classes.dex */
public abstract class AbstractC1148a implements InterfaceC1152e {

    /* renamed from: o */
    public static final InterfaceC2016c f2521o;

    /* renamed from: e */
    public int f2522e;

    /* renamed from: f */
    public boolean f2523f;

    /* renamed from: g */
    public int f2524g;

    /* renamed from: h */
    public int f2525h;

    /* renamed from: i */
    public int f2526i;

    /* renamed from: j */
    public int f2527j;

    /* renamed from: k */
    public int f2528k;

    /* renamed from: l */
    public int f2529l;

    /* renamed from: m */
    public String f2530m;

    /* renamed from: n */
    public C1166s f2531n;

    static {
        Properties properties = C2015b.f5863a;
        f2521o = C2015b.m2349a(AbstractC1148a.class.getName());
        Boolean.getBoolean("org.eclipse.jetty.io.AbstractBuffer.boundsChecking");
    }

    public AbstractC1148a(int i7, boolean z6) {
        if (i7 == 0 && z6) {
            throw new IllegalArgumentException("IMMUTABLE && VOLATILE");
        }
        this.f2529l = -1;
        this.f2522e = i7;
        this.f2523f = z6;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: A */
    public boolean mo1314A() {
        return this.f2525h > this.f2524g;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: B */
    public boolean mo1315B(InterfaceC1152e interfaceC1152e) {
        int i7;
        if (interfaceC1152e == this) {
            return true;
        }
        if (interfaceC1152e.length() != length()) {
            return false;
        }
        int i8 = this.f2526i;
        if (i8 != 0 && (interfaceC1152e instanceof AbstractC1148a) && (i7 = ((AbstractC1148a) interfaceC1152e).f2526i) != 0 && i8 != i7) {
            return false;
        }
        int i9 = this.f2524g;
        int iMo1322M = interfaceC1152e.mo1322M();
        byte[] bArrMo1349P = mo1349P();
        byte[] bArrMo1349P2 = interfaceC1152e.mo1349P();
        if (bArrMo1349P != null && bArrMo1349P2 != null) {
            int i10 = this.f2525h;
            while (true) {
                int i11 = i10 - 1;
                if (i10 <= i9) {
                    break;
                }
                byte b7 = bArrMo1349P[i11];
                iMo1322M--;
                byte b8 = bArrMo1349P2[iMo1322M];
                if (b7 != b8) {
                    if (97 <= b7 && b7 <= 122) {
                        b7 = (byte) ((b7 - 97) + 65);
                    }
                    if (97 <= b8 && b8 <= 122) {
                        b8 = (byte) ((b8 - 97) + 65);
                    }
                    if (b7 != b8) {
                        return false;
                    }
                }
                i10 = i11;
            }
        } else {
            int i12 = this.f2525h;
            while (true) {
                int i13 = i12 - 1;
                if (i12 <= i9) {
                    break;
                }
                byte bMo1348H = mo1348H(i13);
                iMo1322M--;
                byte bMo1348H2 = interfaceC1152e.mo1348H(iMo1322M);
                if (bMo1348H != bMo1348H2) {
                    if (97 <= bMo1348H && bMo1348H <= 122) {
                        bMo1348H = (byte) ((bMo1348H - 97) + 65);
                    }
                    if (97 <= bMo1348H2 && bMo1348H2 <= 122) {
                        bMo1348H2 = (byte) ((bMo1348H2 - 97) + 65);
                    }
                    if (bMo1348H != bMo1348H2) {
                        return false;
                    }
                }
                i12 = i13;
            }
        }
        return true;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: C */
    public final int mo1316C() {
        return this.f2524g;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: D */
    public int mo1317D() {
        return mo1350a() - this.f2525h;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: E */
    public boolean mo1318E() {
        return this.f2522e <= 1;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: F */
    public InterfaceC1152e mo1319F() {
        int i7 = this.f2524g;
        int i8 = this.f2529l;
        int i9 = (i7 - i8) - 1;
        if (i8 < 0) {
            return null;
        }
        InterfaceC1152e interfaceC1152eMo1338u = mo1338u(i8, i9);
        this.f2529l = -1;
        return interfaceC1152eMo1338u;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: G */
    public String mo1320G(Charset charset) {
        try {
            byte[] bArrMo1349P = mo1349P();
            return bArrMo1349P != null ? new String(bArrMo1349P, this.f2524g, length(), charset) : new String(mo1339v(), 0, length(), charset);
        } catch (Exception e7) {
            f2521o.mo2358i(e7);
            return new String(mo1339v(), 0, length());
        }
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: L */
    public void mo1321L(byte b7) {
        int i7 = this.f2525h;
        mo1351e(i7, b7);
        mo1324Q(i7 + 1);
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: M */
    public final int mo1322M() {
        return this.f2525h;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: O */
    public int mo1323O() {
        return this.f2529l;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: Q */
    public void mo1324Q(int i7) {
        this.f2525h = i7;
        this.f2526i = 0;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: T */
    public InterfaceC1152e mo1325T() {
        if (mo1329f()) {
            return this;
        }
        return ((this instanceof InterfaceC1152e.a) || (buffer() instanceof InterfaceC1152e.a)) ? new C1158k.a(mo1339v(), 0, length(), 0) : new C1158k(mo1339v(), 0, length(), 0);
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: V */
    public void mo1326V(int i7) {
        this.f2529l = i7;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: W */
    public boolean mo1327W() {
        return this.f2523f;
    }

    @Override // p073i5.InterfaceC1152e
    public InterfaceC1152e buffer() {
        return this;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: c */
    public int mo1328c(byte[] bArr) {
        int i7 = this.f2525h;
        int iMo1336s = mo1336s(i7, bArr, 0, bArr.length);
        mo1324Q(i7 + iMo1336s);
        return iMo1336s;
    }

    @Override // p073i5.InterfaceC1152e
    public void clear() {
        this.f2529l = -1;
        mo1331i(0);
        mo1324Q(0);
    }

    public boolean equals(Object obj) {
        int i7;
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof InterfaceC1152e)) {
            return false;
        }
        InterfaceC1152e interfaceC1152e = (InterfaceC1152e) obj;
        if ((this instanceof InterfaceC1152e.a) || (interfaceC1152e instanceof InterfaceC1152e.a)) {
            return mo1315B(interfaceC1152e);
        }
        if (interfaceC1152e.length() != length()) {
            return false;
        }
        int i8 = this.f2526i;
        if (i8 != 0 && (obj instanceof AbstractC1148a) && (i7 = ((AbstractC1148a) obj).f2526i) != 0 && i8 != i7) {
            return false;
        }
        int i9 = this.f2524g;
        int iMo1322M = interfaceC1152e.mo1322M();
        int i10 = this.f2525h;
        while (true) {
            int i11 = i10 - 1;
            if (i10 <= i9) {
                return true;
            }
            iMo1322M--;
            if (mo1348H(i11) != interfaceC1152e.mo1348H(iMo1322M)) {
                return false;
            }
            i10 = i11;
        }
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: f */
    public boolean mo1329f() {
        return this.f2522e <= 0;
    }

    @Override // p073i5.InterfaceC1152e
    public byte get() {
        int i7 = this.f2524g;
        this.f2524g = i7 + 1;
        return mo1348H(i7);
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: h */
    public int mo1330h(int i7) {
        if (length() < i7) {
            i7 = length();
        }
        mo1331i(this.f2524g + i7);
        return i7;
    }

    public int hashCode() {
        if (this.f2526i == 0 || this.f2527j != this.f2524g || this.f2528k != this.f2525h) {
            int i7 = this.f2524g;
            byte[] bArrMo1349P = mo1349P();
            if (bArrMo1349P != null) {
                int i8 = this.f2525h;
                while (true) {
                    int i9 = i8 - 1;
                    if (i8 <= i7) {
                        break;
                    }
                    byte b7 = bArrMo1349P[i9];
                    if (97 <= b7 && b7 <= 122) {
                        b7 = (byte) ((b7 - 97) + 65);
                    }
                    this.f2526i = (this.f2526i * 31) + b7;
                    i8 = i9;
                }
            } else {
                int i10 = this.f2525h;
                while (true) {
                    int i11 = i10 - 1;
                    if (i10 <= i7) {
                        break;
                    }
                    byte bMo1348H = mo1348H(i11);
                    if (97 <= bMo1348H && bMo1348H <= 122) {
                        bMo1348H = (byte) ((bMo1348H - 97) + 65);
                    }
                    this.f2526i = (this.f2526i * 31) + bMo1348H;
                    i10 = i11;
                }
            }
            if (this.f2526i == 0) {
                this.f2526i = -1;
            }
            this.f2527j = this.f2524g;
            this.f2528k = this.f2525h;
        }
        return this.f2526i;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: i */
    public void mo1331i(int i7) {
        this.f2524g = i7;
        this.f2526i = 0;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: l */
    public void mo1332l() {
        this.f2529l = this.f2524g - 1;
    }

    @Override // p073i5.InterfaceC1152e
    public int length() {
        return this.f2525h - this.f2524g;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: n */
    public int mo1333n(InputStream inputStream, int i7) throws IOException {
        byte[] bArrMo1349P = mo1349P();
        int iMo1317D = mo1317D();
        if (iMo1317D <= i7) {
            i7 = iMo1317D;
        }
        if (bArrMo1349P != null) {
            int i8 = inputStream.read(bArrMo1349P, this.f2525h, i7);
            if (i8 > 0) {
                this.f2525h += i8;
            }
            return i8;
        }
        int i9 = i7 <= 1024 ? i7 : 1024;
        byte[] bArr = new byte[i9];
        while (i7 > 0) {
            int i10 = inputStream.read(bArr, 0, i9);
            if (i10 < 0) {
                return -1;
            }
            int i11 = this.f2525h;
            mo1324Q(mo1336s(i11, bArr, 0, i10) + i11);
            i7 -= i10;
        }
        return 0;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: p */
    public int mo1334p(int i7, InterfaceC1152e interfaceC1152e) {
        int i8 = 0;
        this.f2526i = 0;
        int length = interfaceC1152e.length();
        if (i7 + length > mo1350a()) {
            length = mo1350a() - i7;
        }
        byte[] bArrMo1349P = interfaceC1152e.mo1349P();
        byte[] bArrMo1349P2 = mo1349P();
        if (bArrMo1349P != null && bArrMo1349P2 != null) {
            System.arraycopy(bArrMo1349P, interfaceC1152e.mo1316C(), bArrMo1349P2, i7, length);
        } else if (bArrMo1349P != null) {
            int iMo1316C = interfaceC1152e.mo1316C();
            while (i8 < length) {
                mo1351e(i7, bArrMo1349P[iMo1316C]);
                i8++;
                i7++;
                iMo1316C++;
            }
        } else if (bArrMo1349P2 != null) {
            int iMo1316C2 = interfaceC1152e.mo1316C();
            while (i8 < length) {
                bArrMo1349P2[i7] = interfaceC1152e.mo1348H(iMo1316C2);
                i8++;
                i7++;
                iMo1316C2++;
            }
        } else {
            int iMo1316C3 = interfaceC1152e.mo1316C();
            while (i8 < length) {
                mo1351e(i7, interfaceC1152e.mo1348H(iMo1316C3));
                i8++;
                i7++;
                iMo1316C3++;
            }
        }
        return length;
    }

    @Override // p073i5.InterfaceC1152e
    public byte peek() {
        return mo1348H(this.f2524g);
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: r */
    public void mo1335r(OutputStream outputStream) throws IOException {
        byte[] bArrMo1349P = mo1349P();
        if (bArrMo1349P != null) {
            outputStream.write(bArrMo1349P, this.f2524g, length());
        } else {
            int length = length();
            int i7 = length <= 1024 ? length : 1024;
            byte[] bArr = new byte[i7];
            int i8 = this.f2524g;
            while (length > 0) {
                int iMo1352m = mo1352m(i8, bArr, 0, length > i7 ? i7 : length);
                outputStream.write(bArr, 0, iMo1352m);
                i8 += iMo1352m;
                length -= iMo1352m;
            }
        }
        clear();
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: s */
    public int mo1336s(int i7, byte[] bArr, int i8, int i9) {
        int i10 = 0;
        this.f2526i = 0;
        if (i7 + i9 > mo1350a()) {
            i9 = mo1350a() - i7;
        }
        byte[] bArrMo1349P = mo1349P();
        if (bArrMo1349P != null) {
            System.arraycopy(bArr, i8, bArrMo1349P, i7, i9);
        } else {
            while (i10 < i9) {
                mo1351e(i7, bArr[i8]);
                i10++;
                i7++;
                i8++;
            }
        }
        return i9;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: t */
    public int mo1337t(InterfaceC1152e interfaceC1152e) {
        int i7 = this.f2525h;
        int iMo1334p = mo1334p(i7, interfaceC1152e);
        mo1324Q(i7 + iMo1334p);
        return iMo1334p;
    }

    public String toString() {
        if (!mo1329f()) {
            return new String(mo1339v(), 0, length());
        }
        if (this.f2530m == null) {
            this.f2530m = new String(mo1339v(), 0, length());
        }
        return this.f2530m;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: u */
    public InterfaceC1152e mo1338u(int i7, int i8) {
        C1166s c1166s = this.f2531n;
        if (c1166s == null) {
            this.f2531n = new C1166s(this, -1, i7, i7 + i8, mo1318E() ? 1 : 2);
        } else {
            c1166s.m1373d(buffer());
            C1166s c1166s2 = this.f2531n;
            c1166s2.f2529l = -1;
            c1166s2.mo1331i(0);
            this.f2531n.mo1324Q(i8 + i7);
            C1166s c1166s3 = this.f2531n;
            c1166s3.f2524g = i7;
            c1166s3.f2526i = 0;
        }
        return this.f2531n;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: v */
    public byte[] mo1339v() {
        int length = length();
        byte[] bArr = new byte[length];
        byte[] bArrMo1349P = mo1349P();
        if (bArrMo1349P != null) {
            System.arraycopy(bArrMo1349P, this.f2524g, bArr, 0, length);
        } else {
            mo1352m(this.f2524g, bArr, 0, length());
        }
        return bArr;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: w */
    public void mo1340w() {
        if (mo1318E()) {
            throw new IllegalStateException("READONLY");
        }
        int i7 = this.f2529l;
        if (i7 < 0) {
            i7 = this.f2524g;
        }
        if (i7 > 0) {
            byte[] bArrMo1349P = mo1349P();
            int i8 = this.f2525h - i7;
            if (i8 > 0) {
                if (bArrMo1349P != null) {
                    System.arraycopy(mo1349P(), i7, mo1349P(), 0, i8);
                } else {
                    mo1334p(0, mo1338u(i7, i8));
                }
            }
            int i9 = this.f2529l;
            if (i9 > 0) {
                this.f2529l = i9 - i7;
            }
            mo1331i(this.f2524g - i7);
            mo1324Q(this.f2525h - i7);
        }
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: y */
    public String mo1341y(String str) {
        try {
            byte[] bArrMo1349P = mo1349P();
            return bArrMo1349P != null ? new String(bArrMo1349P, this.f2524g, length(), str) : new String(mo1339v(), 0, length(), str);
        } catch (Exception e7) {
            f2521o.mo2358i(e7);
            return new String(mo1339v(), 0, length());
        }
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: z */
    public String mo1342z() {
        StringBuilder sbM112a = C0413b.m112a("[");
        sbM112a.append(super.hashCode());
        sbM112a.append(",");
        sbM112a.append(buffer().hashCode());
        sbM112a.append(",m=");
        sbM112a.append(this.f2529l);
        sbM112a.append(",g=");
        sbM112a.append(this.f2524g);
        sbM112a.append(",p=");
        sbM112a.append(this.f2525h);
        sbM112a.append(",c=");
        sbM112a.append(mo1350a());
        sbM112a.append("]={");
        int i7 = this.f2529l;
        if (i7 >= 0) {
            while (i7 < this.f2524g) {
                C1927s.m2263f(mo1348H(i7), sbM112a);
                i7++;
            }
            sbM112a.append("}{");
        }
        int i8 = 0;
        int i9 = this.f2524g;
        while (i9 < this.f2525h) {
            C1927s.m2263f(mo1348H(i9), sbM112a);
            int i10 = i8 + 1;
            if (i8 == 50 && this.f2525h - i9 > 20) {
                sbM112a.append(" ... ");
                i9 = this.f2525h - 20;
            }
            i9++;
            i8 = i10;
        }
        sbM112a.append('}');
        return sbM112a.toString();
    }

    @Override // p073i5.InterfaceC1152e
    public InterfaceC1152e get(int i7) {
        int i8 = this.f2524g;
        InterfaceC1152e interfaceC1152eMo1338u = mo1338u(i8, i7);
        mo1331i(i8 + i7);
        return interfaceC1152eMo1338u;
    }
}
