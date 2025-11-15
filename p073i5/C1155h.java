package p073i5;

import p073i5.C1153f;
import p161t5.C1926r;

/* compiled from: BufferUtil.java */
/* renamed from: i5.h */
/* loaded from: classes.dex */
public class C1155h {

    /* renamed from: a */
    public static final byte[] f2544a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};

    /* renamed from: b */
    public static final int[] f2545b = {268435456, 16777216, 1048576, 65536, 4096, 256, 16, 1};

    /* renamed from: c */
    public static final long[] f2546c = {1000000000000000000L, 100000000000000000L, 10000000000000000L, 1000000000000000L, 100000000000000L, 10000000000000L, 1000000000000L, 100000000000L, 10000000000L, 1000000000, 100000000, 10000000, 1000000, 100000, 10000, 1000, 100, 10, 1};

    /* renamed from: a */
    public static void m1363a(InterfaceC1152e interfaceC1152e, long j7) {
        if (j7 < 0) {
            interfaceC1152e.mo1321L((byte) 45);
            if (j7 == Long.MIN_VALUE) {
                interfaceC1152e.mo1321L((byte) 57);
                j7 = 223372036854775808L;
            } else {
                j7 = -j7;
            }
        }
        if (j7 < 10) {
            interfaceC1152e.mo1321L(f2544a[(int) j7]);
            return;
        }
        int i7 = 0;
        boolean z6 = false;
        while (true) {
            long[] jArr = f2546c;
            if (i7 >= jArr.length) {
                return;
            }
            if (j7 >= jArr[i7]) {
                long j8 = j7 / jArr[i7];
                interfaceC1152e.mo1321L(f2544a[(int) j8]);
                j7 -= j8 * jArr[i7];
                z6 = true;
            } else if (z6) {
                interfaceC1152e.mo1321L((byte) 48);
            }
            i7++;
        }
    }

    /* renamed from: b */
    public static void m1364b(InterfaceC1152e interfaceC1152e, int i7) {
        if (i7 < 0) {
            interfaceC1152e.mo1321L((byte) 45);
            if (i7 == Integer.MIN_VALUE) {
                interfaceC1152e.mo1321L((byte) 56);
                interfaceC1152e.mo1321L((byte) 48);
                interfaceC1152e.mo1321L((byte) 48);
                interfaceC1152e.mo1321L((byte) 48);
                interfaceC1152e.mo1321L((byte) 48);
                interfaceC1152e.mo1321L((byte) 48);
                interfaceC1152e.mo1321L((byte) 48);
                interfaceC1152e.mo1321L((byte) 48);
                return;
            }
            i7 = -i7;
        }
        if (i7 < 16) {
            interfaceC1152e.mo1321L(f2544a[i7]);
            return;
        }
        int i8 = 0;
        boolean z6 = false;
        while (true) {
            int[] iArr = f2545b;
            if (i8 >= iArr.length) {
                return;
            }
            if (i7 >= iArr[i8]) {
                int i9 = i7 / iArr[i8];
                interfaceC1152e.mo1321L(f2544a[i9]);
                i7 -= i9 * iArr[i8];
                z6 = true;
            } else if (z6) {
                interfaceC1152e.mo1321L((byte) 48);
            }
            i8++;
        }
    }

    /* renamed from: c */
    public static String m1365c(InterfaceC1152e interfaceC1152e) {
        return interfaceC1152e instanceof C1153f.a ? interfaceC1152e.toString() : interfaceC1152e.mo1320G(C1926r.f5700c);
    }

    /* renamed from: d */
    public static int m1366d(InterfaceC1152e interfaceC1152e) {
        boolean z6 = false;
        int i7 = 0;
        boolean z7 = false;
        for (int iMo1316C = interfaceC1152e.mo1316C(); iMo1316C < interfaceC1152e.mo1322M(); iMo1316C++) {
            byte bMo1348H = interfaceC1152e.mo1348H(iMo1316C);
            if (bMo1348H > 32) {
                if (bMo1348H >= 48 && bMo1348H <= 57) {
                    i7 = (bMo1348H - 48) + (i7 * 10);
                    z6 = true;
                } else {
                    if (bMo1348H != 45 || z6) {
                        break;
                    }
                    z7 = true;
                }
            } else {
                if (z6) {
                    break;
                }
            }
        }
        if (z6) {
            return z7 ? -i7 : i7;
        }
        throw new NumberFormatException(interfaceC1152e.toString());
    }

    /* renamed from: e */
    public static long m1367e(InterfaceC1152e interfaceC1152e) {
        boolean z6 = false;
        long j7 = 0;
        boolean z7 = false;
        for (int iMo1316C = interfaceC1152e.mo1316C(); iMo1316C < interfaceC1152e.mo1322M(); iMo1316C++) {
            byte bMo1348H = interfaceC1152e.mo1348H(iMo1316C);
            if (bMo1348H > 32) {
                if (bMo1348H >= 48 && bMo1348H <= 57) {
                    j7 = (j7 * 10) + (bMo1348H - 48);
                    z6 = true;
                } else {
                    if (bMo1348H != 45 || z6) {
                        break;
                    }
                    z7 = true;
                }
            } else {
                if (z6) {
                    break;
                }
            }
        }
        if (z6) {
            return z7 ? -j7 : j7;
        }
        throw new NumberFormatException(interfaceC1152e.toString());
    }
}
