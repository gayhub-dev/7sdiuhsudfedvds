package p106m6;

/* compiled from: HexBin.java */
/* renamed from: m6.b */
/* loaded from: classes.dex */
public final class C1499b {

    /* renamed from: a */
    public static byte[] f4299a = new byte[255];

    /* renamed from: b */
    public static byte[] f4300b = new byte[16];

    static {
        for (int i7 = 0; i7 < 255; i7++) {
            f4299a[i7] = -1;
        }
        for (int i8 = 57; i8 >= 48; i8--) {
            f4299a[i8] = (byte) (i8 - 48);
        }
        for (int i9 = 70; i9 >= 65; i9--) {
            f4299a[i9] = (byte) ((i9 - 65) + 10);
        }
        for (int i10 = 102; i10 >= 97; i10--) {
            f4299a[i10] = (byte) ((i10 - 97) + 10);
        }
        for (int i11 = 0; i11 < 10; i11++) {
            f4300b[i11] = (byte) (i11 + 48);
        }
        for (int i12 = 10; i12 <= 15; i12++) {
            f4300b[i12] = (byte) ((i12 + 65) - 10);
        }
    }

    /* renamed from: a */
    public static byte[] m1662a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        if (length % 2 != 0) {
            return null;
        }
        int i7 = length / 2;
        byte[] bArr2 = new byte[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i8 * 2;
            byte b7 = bArr[i9];
            byte[] bArr3 = f4299a;
            if (bArr3[b7] != -1) {
                int i10 = i9 + 1;
                if (bArr3[bArr[i10]] != -1) {
                    bArr2[i8] = (byte) ((bArr3[bArr[i9]] << 4) | bArr3[bArr[i10]]);
                }
            }
            return null;
        }
        return bArr2;
    }

    /* renamed from: b */
    public static byte[] m1663b(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length * 2];
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 * 2;
            byte[] bArr3 = f4300b;
            bArr2[i8] = bArr3[(bArr[i7] >> 4) & 15];
            bArr2[i8 + 1] = bArr3[bArr[i7] & 15];
        }
        return bArr2;
    }
}
