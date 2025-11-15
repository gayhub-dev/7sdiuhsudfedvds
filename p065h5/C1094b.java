package p065h5;

import p161t5.C1926r;
import p161t5.C1927s;
import p161t5.C1928t;
import p161t5.C1929u;
import p161t5.ConcurrentMapC1920l;

/* compiled from: EncodedHttpURI.java */
/* renamed from: h5.b */
/* loaded from: classes.dex */
public class C1094b extends C1113u {

    /* renamed from: o */
    public final String f2261o;

    public C1094b(String str) {
        this.f2261o = str;
    }

    @Override // p065h5.C1113u
    /* renamed from: a */
    public void mo1200a(ConcurrentMapC1920l concurrentMapC1920l) {
        int i7 = this.f2376j;
        if (i7 == this.f2377k) {
            return;
        }
        C1929u.m2272j(C1926r.m2257g(this.f2367a, i7 + 1, (r1 - i7) - 1, this.f2261o), concurrentMapC1920l, this.f2261o, -1);
    }

    @Override // p065h5.C1113u
    /* renamed from: b */
    public void mo1201b(ConcurrentMapC1920l concurrentMapC1920l, String str) {
        int i7 = this.f2376j;
        if (i7 == this.f2377k) {
            return;
        }
        if (str == null) {
            str = this.f2261o;
        }
        C1929u.m2272j(C1926r.m2257g(this.f2367a, i7 + 1, (r1 - i7) - 1, str), concurrentMapC1920l, str, -1);
    }

    @Override // p065h5.C1113u
    /* renamed from: c */
    public String mo1202c() {
        int i7;
        int i8 = this.f2374h;
        int i9 = this.f2375i;
        byte[] bArr = null;
        if (i8 == i9) {
            return null;
        }
        byte[] bArr2 = this.f2367a;
        int i10 = i9 - i8;
        String str = C1928t.f5703e;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            if (i11 >= i10) {
                break;
            }
            int i13 = i11 + i8;
            byte bM2262e = bArr2[i13];
            if (bM2262e == 37 && (i7 = i11 + 2) < i10) {
                bM2262e = (byte) (C1927s.m2262e(bArr2, i13 + 1, 2, 16) & 255);
                i11 = i7;
            } else {
                if (bM2262e == 59) {
                    i10 = i11;
                    break;
                }
                if (bArr == null) {
                    i12++;
                }
                i11++;
            }
            if (bArr == null) {
                bArr = new byte[i10];
                for (int i14 = 0; i14 < i12; i14++) {
                    bArr[i14] = bArr2[i14 + i8];
                }
            }
            bArr[i12] = bM2262e;
            i12++;
            i11++;
        }
        return bArr == null ? C1926r.m2257g(bArr2, i8, i10, C1928t.f5703e) : C1926r.m2257g(bArr, 0, i12, C1928t.f5703e);
    }

    @Override // p065h5.C1113u
    /* renamed from: e */
    public String mo1203e() {
        int i7 = this.f2371e;
        int i8 = this.f2372f;
        if (i7 == i8) {
            return null;
        }
        return C1926r.m2257g(this.f2367a, i7, i8 - i7, this.f2261o);
    }

    @Override // p065h5.C1113u
    /* renamed from: f */
    public String mo1204f() {
        int i7 = this.f2374h;
        int i8 = this.f2375i;
        if (i7 == i8) {
            return null;
        }
        return C1926r.m2257g(this.f2367a, i7, i8 - i7, this.f2261o);
    }

    @Override // p065h5.C1113u
    /* renamed from: g */
    public String mo1205g() {
        int i7 = this.f2374h;
        int i8 = this.f2376j;
        if (i7 == i8) {
            return null;
        }
        return C1926r.m2257g(this.f2367a, i7, i8 - i7, this.f2261o);
    }

    @Override // p065h5.C1113u
    /* renamed from: h */
    public int mo1206h() {
        int i7 = this.f2372f;
        if (i7 == this.f2374h) {
            return -1;
        }
        return C1927s.m2262e(this.f2367a, i7 + 1, (r1 - i7) - 1, 10);
    }

    @Override // p065h5.C1113u
    /* renamed from: i */
    public String mo1207i() {
        int i7 = this.f2376j;
        if (i7 == this.f2377k) {
            return null;
        }
        return C1926r.m2257g(this.f2367a, i7 + 1, (r1 - i7) - 1, this.f2261o);
    }

    @Override // p065h5.C1113u
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
        return C1926r.m2257g(this.f2367a, i7, (i8 - i7) - 1, this.f2261o);
    }

    @Override // p065h5.C1113u
    /* renamed from: k */
    public boolean mo1209k() {
        return this.f2377k > this.f2376j;
    }

    @Override // p065h5.C1113u
    public String toString() {
        if (this.f2368b == null) {
            byte[] bArr = this.f2367a;
            int i7 = this.f2369c;
            this.f2368b = C1926r.m2257g(bArr, i7, this.f2378l - i7, this.f2261o);
        }
        return this.f2368b;
    }
}
