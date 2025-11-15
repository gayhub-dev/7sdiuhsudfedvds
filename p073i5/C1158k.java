package p073i5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import p073i5.InterfaceC1152e;
import p161t5.C1926r;

/* compiled from: ByteArrayBuffer.java */
/* renamed from: i5.k */
/* loaded from: classes.dex */
public class C1158k extends AbstractC1148a {

    /* renamed from: q */
    public static final int f2547q = Integer.getInteger("org.eclipse.jetty.io.ByteArrayBuffer.MAX_WRITE", 131072).intValue();

    /* renamed from: p */
    public final byte[] f2548p;

    /* compiled from: ByteArrayBuffer.java */
    /* renamed from: i5.k$a */
    public static class a extends C1158k implements InterfaceC1152e.a {
        public a(String str) {
            super(str);
        }

        @Override // p073i5.C1158k, p073i5.AbstractC1148a
        public boolean equals(Object obj) {
            return (obj instanceof InterfaceC1152e) && mo1315B((InterfaceC1152e) obj);
        }

        public a(byte[] bArr, int i7, int i8, int i9) {
            super(bArr, i7, i8, i9);
        }
    }

    public C1158k(int i7, int i8, boolean z6) {
        super(2, z6);
        this.f2548p = new byte[i7];
        mo1324Q(0);
        this.f2524g = 0;
        this.f2526i = 0;
        this.f2522e = i8;
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: B */
    public boolean mo1315B(InterfaceC1152e interfaceC1152e) {
        int i7;
        if (interfaceC1152e == this) {
            return true;
        }
        if (interfaceC1152e == null || interfaceC1152e.length() != length()) {
            return false;
        }
        int i8 = this.f2526i;
        if (i8 != 0 && (interfaceC1152e instanceof AbstractC1148a) && (i7 = ((AbstractC1148a) interfaceC1152e).f2526i) != 0 && i8 != i7) {
            return false;
        }
        int i9 = this.f2524g;
        int iMo1322M = interfaceC1152e.mo1322M();
        byte[] bArrMo1349P = interfaceC1152e.mo1349P();
        if (bArrMo1349P != null) {
            int i10 = this.f2525h;
            while (true) {
                int i11 = i10 - 1;
                if (i10 <= i9) {
                    break;
                }
                byte b7 = this.f2548p[i11];
                iMo1322M--;
                byte b8 = bArrMo1349P[iMo1322M];
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
                byte b9 = this.f2548p[i13];
                iMo1322M--;
                byte bMo1348H = interfaceC1152e.mo1348H(iMo1322M);
                if (b9 != bMo1348H) {
                    if (97 <= b9 && b9 <= 122) {
                        b9 = (byte) ((b9 - 97) + 65);
                    }
                    if (97 <= bMo1348H && bMo1348H <= 122) {
                        bMo1348H = (byte) ((bMo1348H - 97) + 65);
                    }
                    if (b9 != bMo1348H) {
                        return false;
                    }
                }
                i12 = i13;
            }
        }
        return true;
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: D */
    public int mo1317D() {
        return this.f2548p.length - this.f2525h;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: H */
    public byte mo1348H(int i7) {
        return this.f2548p[i7];
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: P */
    public byte[] mo1349P() {
        return this.f2548p;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: a */
    public int mo1350a() {
        return this.f2548p.length;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: e */
    public void mo1351e(int i7, byte b7) {
        this.f2548p[i7] = b7;
    }

    @Override // p073i5.AbstractC1148a
    public boolean equals(Object obj) {
        int i7;
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof InterfaceC1152e)) {
            return false;
        }
        if (obj instanceof InterfaceC1152e.a) {
            return mo1315B((InterfaceC1152e) obj);
        }
        InterfaceC1152e interfaceC1152e = (InterfaceC1152e) obj;
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
            if (this.f2548p[i11] != interfaceC1152e.mo1348H(iMo1322M)) {
                return false;
            }
            i10 = i11;
        }
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    public byte get() {
        byte[] bArr = this.f2548p;
        int i7 = this.f2524g;
        this.f2524g = i7 + 1;
        return bArr[i7];
    }

    @Override // p073i5.AbstractC1148a
    public int hashCode() {
        if (this.f2526i == 0 || this.f2527j != this.f2524g || this.f2528k != this.f2525h) {
            int i7 = this.f2524g;
            int i8 = this.f2525h;
            while (true) {
                int i9 = i8 - 1;
                if (i8 <= i7) {
                    break;
                }
                byte b7 = this.f2548p[i9];
                if (97 <= b7 && b7 <= 122) {
                    b7 = (byte) ((b7 - 97) + 65);
                }
                this.f2526i = (this.f2526i * 31) + b7;
                i8 = i9;
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
    /* renamed from: m */
    public int mo1352m(int i7, byte[] bArr, int i8, int i9) {
        int i10 = i7 + i9;
        byte[] bArr2 = this.f2548p;
        if ((i10 > bArr2.length && (i9 = bArr2.length - i7) == 0) || i9 < 0) {
            return -1;
        }
        System.arraycopy(bArr2, i7, bArr, i8, i9);
        return i9;
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: n */
    public int mo1333n(InputStream inputStream, int i7) throws IOException {
        if (i7 < 0 || i7 > mo1317D()) {
            i7 = mo1317D();
        }
        int i8 = this.f2525h;
        int i9 = 0;
        int i10 = i7;
        int i11 = 0;
        while (i9 < i7) {
            i11 = inputStream.read(this.f2548p, i8, i10);
            if (i11 < 0) {
                break;
            }
            if (i11 > 0) {
                i8 += i11;
                i9 += i11;
                i10 -= i11;
                mo1324Q(i8);
            }
            if (inputStream.available() <= 0) {
                break;
            }
        }
        if (i11 >= 0 || i9 != 0) {
            return i9;
        }
        return -1;
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: p */
    public int mo1334p(int i7, InterfaceC1152e interfaceC1152e) {
        int i8 = 0;
        this.f2526i = 0;
        int length = interfaceC1152e.length();
        int i9 = i7 + length;
        byte[] bArr = this.f2548p;
        if (i9 > bArr.length) {
            length = bArr.length - i7;
        }
        byte[] bArrMo1349P = interfaceC1152e.mo1349P();
        if (bArrMo1349P != null) {
            System.arraycopy(bArrMo1349P, interfaceC1152e.mo1316C(), this.f2548p, i7, length);
        } else {
            int iMo1316C = interfaceC1152e.mo1316C();
            while (i8 < length) {
                this.f2548p[i7] = interfaceC1152e.mo1348H(iMo1316C);
                i8++;
                i7++;
                iMo1316C++;
            }
        }
        return length;
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: r */
    public void mo1335r(OutputStream outputStream) throws IOException {
        int length = length();
        int i7 = f2547q;
        if (i7 <= 0 || length <= i7) {
            outputStream.write(this.f2548p, this.f2524g, length);
        } else {
            int i8 = this.f2524g;
            while (length > 0) {
                int i9 = f2547q;
                if (length <= i9) {
                    i9 = length;
                }
                outputStream.write(this.f2548p, i8, i9);
                i8 += i9;
                length -= i9;
            }
        }
        if (mo1329f()) {
            return;
        }
        clear();
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: s */
    public int mo1336s(int i7, byte[] bArr, int i8, int i9) {
        this.f2526i = 0;
        int i10 = i7 + i9;
        byte[] bArr2 = this.f2548p;
        if (i10 > bArr2.length) {
            i9 = bArr2.length - i7;
        }
        System.arraycopy(bArr, i8, bArr2, i7, i9);
        return i9;
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
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
            int i8 = this.f2525h - i7;
            if (i8 > 0) {
                byte[] bArr = this.f2548p;
                System.arraycopy(bArr, i7, bArr, 0, i8);
            }
            int i9 = this.f2529l;
            if (i9 > 0) {
                this.f2529l = i9 - i7;
            }
            mo1331i(this.f2524g - i7);
            mo1324Q(this.f2525h - i7);
        }
    }

    public C1158k(byte[] bArr, int i7, int i8, int i9) {
        super(2, false);
        this.f2548p = bArr;
        mo1324Q(i8 + i7);
        this.f2524g = i7;
        this.f2526i = 0;
        this.f2522e = i9;
    }

    public C1158k(byte[] bArr, int i7, int i8, int i9, boolean z6) {
        super(2, z6);
        this.f2548p = bArr;
        mo1324Q(i8 + i7);
        this.f2524g = i7;
        this.f2526i = 0;
        this.f2522e = i9;
    }

    public C1158k(int i7) {
        this(new byte[i7], 0, 0, 2);
        mo1324Q(0);
    }

    public C1158k(String str) {
        super(2, false);
        byte[] bArrM2253c = C1926r.m2253c(str);
        this.f2548p = bArrM2253c;
        mo1331i(0);
        mo1324Q(bArrM2253c.length);
        this.f2522e = 0;
        this.f2530m = str;
    }

    public C1158k(String str, String str2) throws UnsupportedEncodingException {
        super(2, false);
        byte[] bytes = str.getBytes(str2);
        this.f2548p = bytes;
        mo1331i(0);
        mo1324Q(bytes.length);
        this.f2522e = 0;
        this.f2530m = str;
    }
}
