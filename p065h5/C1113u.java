package p065h5;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import p009b.C0413b;
import p161t5.C1926r;
import p161t5.C1927s;
import p161t5.C1928t;
import p161t5.C1929u;
import p161t5.C1931w;
import p161t5.ConcurrentMapC1920l;
import p175v5.InterfaceC2016c;

/* compiled from: HttpURI.java */
/* renamed from: h5.u */
/* loaded from: classes.dex */
public class C1113u {

    /* renamed from: n */
    public static final byte[] f2366n = new byte[0];

    /* renamed from: b */
    public String f2368b;

    /* renamed from: c */
    public int f2369c;

    /* renamed from: d */
    public int f2370d;

    /* renamed from: e */
    public int f2371e;

    /* renamed from: f */
    public int f2372f;

    /* renamed from: g */
    public int f2373g;

    /* renamed from: h */
    public int f2374h;

    /* renamed from: i */
    public int f2375i;

    /* renamed from: j */
    public int f2376j;

    /* renamed from: k */
    public int f2377k;

    /* renamed from: l */
    public int f2378l;

    /* renamed from: a */
    public byte[] f2367a = f2366n;

    /* renamed from: m */
    public final C1931w f2379m = new C1931w(64, 1);

    public C1113u() {
    }

    /* renamed from: a */
    public void mo1200a(ConcurrentMapC1920l concurrentMapC1920l) {
        if (this.f2376j == this.f2377k) {
            return;
        }
        this.f2379m.m2282i();
        C1929u.m2273k(this.f2367a, this.f2376j + 1, (this.f2377k - r1) - 1, concurrentMapC1920l, this.f2379m);
    }

    /* renamed from: b */
    public void mo1201b(ConcurrentMapC1920l concurrentMapC1920l, String str) {
        if (this.f2376j == this.f2377k) {
            return;
        }
        if (str != null && !C1926r.m2254d(str)) {
            C1929u.m2272j(C1926r.m2257g(this.f2367a, this.f2376j + 1, (this.f2377k - r1) - 1, str), concurrentMapC1920l, str, -1);
        } else {
            byte[] bArr = this.f2367a;
            int i7 = this.f2376j;
            int i8 = i7 + 1;
            int i9 = (this.f2377k - i7) - 1;
            InterfaceC2016c interfaceC2016c = C1929u.f5704g;
            C1929u.m2273k(bArr, i8, i9, concurrentMapC1920l, new C1931w());
        }
    }

    /* renamed from: c */
    public String mo1202c() {
        int i7 = this.f2374h;
        int i8 = this.f2375i;
        if (i7 == i8) {
            return null;
        }
        int i9 = i8 - i7;
        boolean z6 = false;
        while (i7 < this.f2375i) {
            byte b7 = this.f2367a[i7];
            if (b7 == 37) {
                if (!z6) {
                    this.f2379m.m2282i();
                    C1931w c1931w = this.f2379m;
                    byte[] bArr = this.f2367a;
                    int i10 = this.f2374h;
                    c1931w.m2275b(bArr, i10, i7 - i10);
                    z6 = true;
                }
                int i11 = i7 + 2;
                int i12 = this.f2375i;
                if (i11 >= i12) {
                    throw new IllegalArgumentException("Bad % encoding: " + this);
                }
                byte[] bArr2 = this.f2367a;
                int i13 = i7 + 1;
                if (bArr2[i13] == 117) {
                    i7 += 5;
                    if (i7 >= i12) {
                        throw new IllegalArgumentException("Bad %u encoding: " + this);
                    }
                    try {
                        this.f2379m.m2280g().append(new String(Character.toChars(C1927s.m2262e(bArr2, i11, 4, 16))));
                    } catch (Exception e7) {
                        throw new RuntimeException(e7);
                    }
                } else {
                    this.f2379m.m2274a((byte) (C1927s.m2262e(bArr2, i13, 2, 16) & 255));
                    i7 = i11;
                }
            } else if (z6) {
                this.f2379m.m2274a(b7);
            }
            i7++;
        }
        return !z6 ? m1256o(this.f2374h, i9) : this.f2379m.toString();
    }

    /* renamed from: d */
    public String m1252d(String str) throws UnsupportedEncodingException {
        int i7 = this.f2374h;
        int i8 = this.f2375i;
        byte[] bArr = null;
        if (i7 == i8) {
            return null;
        }
        int i9 = i8 - i7;
        int length = 0;
        while (true) {
            int i10 = this.f2375i;
            if (i7 >= i10) {
                if (bArr != null) {
                    return C1926r.m2257g(bArr, 0, length, str);
                }
                byte[] bArr2 = this.f2367a;
                int i11 = this.f2374h;
                return C1926r.m2257g(bArr2, i11, i10 - i11, str);
            }
            byte[] bArr3 = this.f2367a;
            byte b7 = bArr3[i7];
            if (b7 == 37) {
                if (bArr == null) {
                    bArr = new byte[i9];
                    System.arraycopy(bArr3, this.f2374h, bArr, 0, length);
                }
                int i12 = i7 + 2;
                int i13 = this.f2375i;
                if (i12 >= i13) {
                    throw new IllegalArgumentException("Bad % encoding: " + this);
                }
                byte[] bArr4 = this.f2367a;
                int i14 = i7 + 1;
                if (bArr4[i14] == 117) {
                    i7 += 5;
                    if (i7 >= i13) {
                        throw new IllegalArgumentException("Bad %u encoding: " + this);
                    }
                    try {
                        byte[] bytes = new String(Character.toChars(C1927s.m2262e(bArr4, i12, 4, 16))).getBytes(str);
                        System.arraycopy(bytes, 0, bArr, length, bytes.length);
                        length += bytes.length;
                    } catch (Exception e7) {
                        throw new RuntimeException(e7);
                    }
                } else {
                    bArr[length] = (byte) (C1927s.m2262e(bArr4, i14, 2, 16) & 255);
                    i7 = i12;
                    length++;
                }
            } else if (bArr == null) {
                length++;
            } else {
                bArr[length] = b7;
                length++;
            }
            i7++;
        }
    }

    /* renamed from: e */
    public String mo1203e() {
        int i7 = this.f2371e;
        int i8 = this.f2372f;
        if (i7 == i8) {
            return null;
        }
        return m1256o(i7, i8 - i7);
    }

    /* renamed from: f */
    public String mo1204f() {
        int i7 = this.f2374h;
        int i8 = this.f2375i;
        if (i7 == i8) {
            return null;
        }
        return m1256o(i7, i8 - i7);
    }

    /* renamed from: g */
    public String mo1205g() {
        int i7 = this.f2374h;
        int i8 = this.f2376j;
        if (i7 == i8) {
            return null;
        }
        return m1256o(i7, i8 - i7);
    }

    /* renamed from: h */
    public int mo1206h() {
        return this.f2373g;
    }

    /* renamed from: i */
    public String mo1207i() {
        int i7 = this.f2376j;
        if (i7 == this.f2377k) {
            return null;
        }
        return m1256o(i7 + 1, (r1 - i7) - 1);
    }

    /* renamed from: j */
    public String mo1208j() {
        int i7 = this.f2369c;
        int i8 = this.f2370d;
        if (i7 == i8) {
            return null;
        }
        int i9 = i8 - i7;
        if (i9 == 5) {
            byte[] bArr = this.f2367a;
            if (bArr[i7] == 104 && bArr[i7 + 1] == 116 && bArr[i7 + 2] == 116 && bArr[i7 + 3] == 112) {
                return "http";
            }
        }
        if (i9 == 6) {
            byte[] bArr2 = this.f2367a;
            if (bArr2[i7] == 104 && bArr2[i7 + 1] == 116 && bArr2[i7 + 2] == 116 && bArr2[i7 + 3] == 112 && bArr2[i7 + 4] == 115) {
                return "https";
            }
        }
        return m1256o(i7, (i8 - i7) - 1);
    }

    /* renamed from: k */
    public boolean mo1209k() {
        return this.f2377k > this.f2376j;
    }

    /* renamed from: l */
    public void m1253l(byte[] bArr, int i7, int i8) {
        this.f2368b = null;
        m1254m(bArr, i7, i8);
    }

    /* renamed from: m */
    public final void m1254m(byte[] bArr, int i7, int i8) {
        int i9;
        this.f2367a = bArr;
        int i10 = i7 + i8;
        this.f2378l = i10;
        this.f2369c = i7;
        this.f2370d = i7;
        this.f2371e = i7;
        this.f2372f = i7;
        this.f2373g = -1;
        this.f2374h = i7;
        this.f2375i = i10;
        this.f2376j = i10;
        this.f2377k = i10;
        char c7 = 0;
        int i11 = i7;
        int i12 = i11;
        while (i11 < i10) {
            byte[] bArr2 = this.f2367a;
            char c8 = (char) (bArr2[i11] & 255);
            int i13 = i11 + 1;
            switch (c7) {
                case 0:
                    if (c8 == '#') {
                        this.f2375i = i11;
                        this.f2376j = i11;
                        this.f2377k = i11;
                    } else if (c8 == '*') {
                        this.f2374h = i11;
                        c7 = '\n';
                    } else if (c8 == '/') {
                        i12 = i11;
                        i11 = i13;
                        c7 = 1;
                    } else if (c8 == ';') {
                        this.f2375i = i11;
                        i12 = i11;
                        i11 = i13;
                        c7 = '\b';
                    } else if (c8 != '?') {
                        c7 = 2;
                    } else {
                        this.f2375i = i11;
                        this.f2376j = i11;
                        i12 = i11;
                        i11 = i13;
                        c7 = '\t';
                    }
                    i12 = i11;
                    i11 = i13;
                case 1:
                    if (this.f2369c == this.f2370d || c8 != '/') {
                        if (c8 == ';' || c8 == '?' || c8 == '#') {
                            i13--;
                        } else {
                            this.f2371e = i12;
                            this.f2372f = i12;
                        }
                        i11 = i12;
                        i12 = i11;
                        i11 = i13;
                        c7 = 7;
                    } else {
                        this.f2371e = i13;
                        int i14 = this.f2378l;
                        this.f2372f = i14;
                        this.f2374h = i14;
                        c7 = 4;
                        i11 = i13;
                    }
                    break;
                case 2:
                    if (i8 > 6 && c8 == 't') {
                        int i15 = i7 + 3;
                        if (bArr2[i15] == 58) {
                            i9 = i7 + 4;
                        } else {
                            i15 = i7 + 4;
                            if (bArr2[i15] == 58) {
                                i9 = i7 + 5;
                            } else {
                                i15 = i7 + 5;
                                if (bArr2[i15] == 58) {
                                    i9 = i7 + 6;
                                }
                            }
                        }
                        int i16 = i9;
                        i11 = i15;
                        i13 = i16;
                        c8 = ':';
                    }
                    if (c8 == '#') {
                        this.f2375i = i11;
                        this.f2376j = i11;
                        this.f2377k = i11;
                    } else if (c8 == '/') {
                        i11 = i12;
                        i12 = i11;
                        i11 = i13;
                        c7 = 7;
                    } else if (c8 == '?') {
                        this.f2375i = i11;
                        this.f2376j = i11;
                        i11 = i13;
                        c7 = '\t';
                    } else if (c8 == ':') {
                        int i17 = i13 + 1;
                        this.f2370d = i13;
                        this.f2374h = i13;
                        if (((char) (bArr2[i17] & 255)) == '/') {
                            i11 = i17;
                            i12 = i13;
                            c7 = 1;
                        } else {
                            this.f2371e = i13;
                            this.f2372f = i13;
                            i11 = i17;
                            i12 = i13;
                            c7 = 7;
                        }
                    } else if (c8 == ';') {
                        this.f2375i = i11;
                        i11 = i13;
                        c7 = '\b';
                    }
                    i11 = i13;
                    break;
                case 3:
                default:
                    i11 = i13;
                case 4:
                    if (c8 != '/') {
                        if (c8 == ':') {
                            this.f2372f = i11;
                            c7 = 6;
                        } else if (c8 == '@') {
                            this.f2371e = i13;
                        } else if (c8 == '[') {
                            c7 = 5;
                        }
                        i11 = i13;
                    } else {
                        this.f2374h = i11;
                        this.f2372f = i11;
                        i12 = i11;
                        i11 = i13;
                        c7 = 7;
                    }
                case 5:
                    if (c8 == '/') {
                        StringBuilder sbM112a = C0413b.m112a("No closing ']' for ");
                        sbM112a.append(C1926r.m2257g(this.f2367a, i7, i8, C1928t.f5703e));
                        throw new IllegalArgumentException(sbM112a.toString());
                    }
                    if (c8 == ']') {
                        c7 = 4;
                    }
                    i11 = i13;
                case 6:
                    if (c8 == '/') {
                        this.f2374h = i11;
                        if (this.f2372f <= this.f2370d) {
                            this.f2372f = i11;
                        }
                        i12 = i11;
                        i11 = i13;
                        c7 = 7;
                    } else {
                        i11 = i13;
                    }
                case 7:
                    if (c8 == '#') {
                        this.f2375i = i11;
                        this.f2376j = i11;
                        this.f2377k = i11;
                    } else if (c8 == ';') {
                        this.f2375i = i11;
                        i11 = i13;
                        c7 = '\b';
                    } else if (c8 == '?') {
                        this.f2375i = i11;
                        this.f2376j = i11;
                        i11 = i13;
                        c7 = '\t';
                    }
                    i11 = i13;
                case '\b':
                    if (c8 == '#') {
                        this.f2376j = i11;
                        this.f2377k = i11;
                    } else if (c8 == '?') {
                        this.f2376j = i11;
                        i11 = i13;
                        c7 = '\t';
                    }
                    i11 = i13;
                case '\t':
                    if (c8 == '#') {
                        this.f2377k = i11;
                    }
                    i11 = i13;
                case '\n':
                    throw new IllegalArgumentException("only '*'");
            }
        }
        int i18 = this.f2372f;
        if (i18 < this.f2374h) {
            this.f2373g = C1927s.m2262e(this.f2367a, i18 + 1, (r14 - i18) - 1, 10);
        }
    }

    /* renamed from: n */
    public void m1255n(byte[] bArr, int i7, int i8) {
        this.f2368b = null;
        this.f2367a = bArr;
        int i9 = i7 + i8;
        this.f2378l = i9;
        this.f2369c = i7;
        this.f2370d = i7;
        this.f2371e = i7;
        this.f2372f = i9;
        this.f2373g = -1;
        this.f2374h = i9;
        this.f2375i = i9;
        this.f2376j = i9;
        this.f2377k = i9;
        char c7 = 4;
        int i10 = i7;
        while (true) {
            if (i10 >= i9) {
                break;
            }
            char c8 = (char) (this.f2367a[i10] & 255);
            int i11 = i10 + 1;
            if (c7 == 4) {
                if (c8 == ':') {
                    this.f2372f = i10;
                    break;
                } else if (c8 == '[') {
                    c7 = 5;
                }
            } else if (c7 != 5) {
                continue;
            } else {
                if (c8 == '/') {
                    StringBuilder sbM112a = C0413b.m112a("No closing ']' for ");
                    sbM112a.append(C1926r.m2257g(this.f2367a, i7, i8, C1928t.f5703e));
                    throw new IllegalArgumentException(sbM112a.toString());
                }
                if (c8 == ']') {
                    c7 = 4;
                }
            }
            i10 = i11;
        }
        int i12 = this.f2372f;
        if (i12 >= this.f2374h) {
            throw new IllegalArgumentException("No port");
        }
        this.f2373g = C1927s.m2262e(this.f2367a, i12 + 1, (r9 - i12) - 1, 10);
        this.f2374h = i7;
    }

    /* renamed from: o */
    public final String m1256o(int i7, int i8) {
        this.f2379m.m2282i();
        this.f2379m.m2275b(this.f2367a, i7, i8);
        return this.f2379m.toString();
    }

    public String toString() {
        if (this.f2368b == null) {
            int i7 = this.f2369c;
            this.f2368b = m1256o(i7, this.f2378l - i7);
        }
        return this.f2368b;
    }

    public C1113u(String str) throws UnsupportedEncodingException {
        this.f2368b = str;
        try {
            byte[] bytes = str.getBytes("UTF-8");
            int length = bytes.length;
            this.f2368b = null;
            m1254m(bytes, 0, length);
        } catch (UnsupportedEncodingException e7) {
            throw new RuntimeException(e7.getMessage());
        }
    }

    public C1113u(URI uri) {
        String aSCIIString = uri.toASCIIString();
        byte[] bytes = aSCIIString.getBytes();
        m1254m(bytes, 0, bytes.length);
        this.f2368b = aSCIIString;
    }
}
