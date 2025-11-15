package p106m6;

import android.support.constraint.motion.C0079a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import p082j6.C1212a;

/* compiled from: Base64Coder.java */
/* renamed from: m6.a */
/* loaded from: classes.dex */
public class C1498a {

    /* renamed from: a */
    public static final byte[] f4284a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: b */
    public static final byte[] f4285b = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, C1212a.f2735CR, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: c */
    public static final byte[] f4286c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    /* renamed from: d */
    public static final byte[] f4287d = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, C1212a.f2735CR, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* renamed from: e */
    public static final byte[] f4288e = {45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};

    /* renamed from: f */
    public static final byte[] f4289f = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, C1212a.f2735CR, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static byte[] m1657a(String str) throws Throwable {
        byte[] bytes;
        byte[] byteArray;
        GZIPInputStream gZIPInputStream;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        GZIPInputStream gZIPInputStream2;
        ByteArrayInputStream byteArrayInputStream2;
        ByteArrayOutputStream byteArrayOutputStream3;
        try {
            bytes = str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        int length = bytes.length;
        int i7 = length + 0;
        if (i7 > bytes.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bytes.length), 0, Integer.valueOf(length)));
        }
        if (length == 0) {
            byteArray = new byte[0];
        } else {
            if (length < 4) {
                throw new IllegalArgumentException(C0079a.m93a("Base64-encoded string must have at least four characters, but length specified was ", length));
            }
            byte[] bArrM1661e = m1661e(0);
            byte[] bArr = new byte[(length * 3) / 4];
            byte[] bArr2 = new byte[4];
            int i8 = 0;
            int iM1658b = 0;
            for (int i9 = 0; i9 < i7; i9++) {
                byte b7 = bArrM1661e[bytes[i9] & 255];
                if (b7 < -5) {
                    throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", Integer.valueOf(bytes[i9] & 255), Integer.valueOf(i9)));
                }
                if (b7 >= -1) {
                    int i10 = i8 + 1;
                    bArr2[i8] = bytes[i9];
                    if (i10 > 3) {
                        iM1658b += m1658b(bArr2, 0, bArr, iM1658b, 0);
                        if (bytes[i9] == 61) {
                            break;
                        }
                        i8 = 0;
                    } else {
                        i8 = i10;
                    }
                }
            }
            byteArray = new byte[iM1658b];
            System.arraycopy(bArr, 0, byteArray, 0, iM1658b);
        }
        if (byteArray.length >= 4 && 35615 == ((byteArray[0] & 255) | ((byteArray[1] << 8) & 65280))) {
            byte[] bArr3 = new byte[2048];
            ByteArrayOutputStream byteArrayOutputStream4 = null;
            try {
                ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
                try {
                    byteArrayInputStream2 = new ByteArrayInputStream(byteArray);
                    try {
                        gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream2);
                        while (true) {
                            try {
                                int i11 = gZIPInputStream2.read(bArr3);
                                if (i11 < 0) {
                                    break;
                                }
                                byteArrayOutputStream5.write(bArr3, 0, i11);
                            } catch (IOException e7) {
                                e = e7;
                                byteArrayOutputStream4 = gZIPInputStream2;
                                byteArrayOutputStream2 = byteArrayOutputStream4;
                                byteArrayOutputStream4 = byteArrayInputStream2;
                                byteArrayOutputStream = byteArrayOutputStream4;
                                byteArrayOutputStream4 = byteArrayOutputStream5;
                                try {
                                    e.printStackTrace();
                                    try {
                                        byteArrayOutputStream4.close();
                                    } catch (Exception unused2) {
                                    }
                                    gZIPInputStream2 = byteArrayOutputStream2;
                                    byteArrayInputStream2 = byteArrayOutputStream;
                                    gZIPInputStream2.close();
                                    byteArrayInputStream2.close();
                                    return byteArray;
                                } catch (Throwable th) {
                                    th = th;
                                    gZIPInputStream = byteArrayOutputStream2;
                                    byteArrayInputStream = byteArrayOutputStream;
                                    try {
                                        byteArrayOutputStream4.close();
                                    } catch (Exception unused3) {
                                    }
                                    try {
                                        gZIPInputStream.close();
                                    } catch (Exception unused4) {
                                    }
                                    try {
                                        byteArrayInputStream.close();
                                        throw th;
                                    } catch (Exception unused5) {
                                        throw th;
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                byteArrayOutputStream4 = gZIPInputStream2;
                                byteArrayOutputStream3 = byteArrayOutputStream4;
                                byteArrayOutputStream4 = byteArrayInputStream2;
                                byteArrayInputStream = byteArrayOutputStream4;
                                byteArrayOutputStream4 = byteArrayOutputStream5;
                                gZIPInputStream = byteArrayOutputStream3;
                                byteArrayOutputStream4.close();
                                gZIPInputStream.close();
                                byteArrayInputStream.close();
                                throw th;
                            }
                        }
                        byteArray = byteArrayOutputStream5.toByteArray();
                        try {
                            byteArrayOutputStream5.close();
                        } catch (Exception unused6) {
                        }
                    } catch (IOException e8) {
                        e = e8;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (IOException e9) {
                    e = e9;
                    byteArrayOutputStream2 = null;
                } catch (Throwable th4) {
                    th = th4;
                    byteArrayOutputStream3 = null;
                }
            } catch (IOException e10) {
                e = e10;
                byteArrayOutputStream = null;
                byteArrayOutputStream2 = null;
            } catch (Throwable th5) {
                th = th5;
                gZIPInputStream = 0;
                byteArrayInputStream = 0;
            }
            try {
                gZIPInputStream2.close();
            } catch (Exception unused7) {
            }
            try {
                byteArrayInputStream2.close();
            } catch (Exception unused8) {
            }
        }
        return byteArray;
    }

    /* renamed from: b */
    public static int m1658b(byte[] bArr, int i7, byte[] bArr2, int i8, int i9) {
        int i10;
        int i11;
        Objects.requireNonNull(bArr, "Source array was null.");
        Objects.requireNonNull(bArr2, "Destination array was null.");
        if (i7 < 0 || (i10 = i7 + 3) >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i7)));
        }
        if (i8 < 0 || (i11 = i8 + 2) >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i8)));
        }
        byte[] bArrM1661e = m1661e(i9);
        int i12 = i7 + 2;
        if (bArr[i12] == 61) {
            bArr2[i8] = (byte) ((((bArrM1661e[bArr[i7 + 1]] & 255) << 12) | ((bArrM1661e[bArr[i7]] & 255) << 18)) >>> 16);
            return 1;
        }
        if (bArr[i10] == 61) {
            int i13 = ((bArrM1661e[bArr[i12]] & 255) << 6) | ((bArrM1661e[bArr[i7 + 1]] & 255) << 12) | ((bArrM1661e[bArr[i7]] & 255) << 18);
            bArr2[i8] = (byte) (i13 >>> 16);
            bArr2[i8 + 1] = (byte) (i13 >>> 8);
            return 2;
        }
        int i14 = (bArrM1661e[bArr[i10]] & 255) | ((bArrM1661e[bArr[i7 + 1]] & 255) << 12) | ((bArrM1661e[bArr[i7]] & 255) << 18) | ((bArrM1661e[bArr[i12]] & 255) << 6);
        bArr2[i8] = (byte) (i14 >> 16);
        bArr2[i8 + 1] = (byte) (i14 >> 8);
        bArr2[i11] = (byte) i14;
        return 3;
    }

    /* renamed from: c */
    public static byte[] m1659c(byte[] bArr, int i7, int i8, byte[] bArr2, int i9, int i10) {
        byte[] bArr3 = (i10 & 16) == 16 ? f4286c : (i10 & 32) == 32 ? f4288e : f4284a;
        int i11 = (i8 > 1 ? (bArr[i7 + 1] << 24) >>> 16 : 0) | (i8 > 0 ? (bArr[i7] << 24) >>> 8 : 0) | (i8 > 2 ? (bArr[i7 + 2] << 24) >>> 24 : 0);
        if (i8 == 1) {
            bArr2[i9] = bArr3[i11 >>> 18];
            bArr2[i9 + 1] = bArr3[(i11 >>> 12) & 63];
            bArr2[i9 + 2] = 61;
            bArr2[i9 + 3] = 61;
            return bArr2;
        }
        if (i8 == 2) {
            bArr2[i9] = bArr3[i11 >>> 18];
            bArr2[i9 + 1] = bArr3[(i11 >>> 12) & 63];
            bArr2[i9 + 2] = bArr3[(i11 >>> 6) & 63];
            bArr2[i9 + 3] = 61;
            return bArr2;
        }
        if (i8 != 3) {
            return bArr2;
        }
        bArr2[i9] = bArr3[i11 >>> 18];
        bArr2[i9 + 1] = bArr3[(i11 >>> 12) & 63];
        bArr2[i9 + 2] = bArr3[(i11 >>> 6) & 63];
        bArr2[i9 + 3] = bArr3[i11 & 63];
        return bArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.io.ByteArrayOutputStream, java.io.OutputStream] */
    /* renamed from: d */
    public static String m1660d(byte[] bArr, int i7, int i8, int i9) throws Throwable {
        byte[] byteArray;
        a aVar;
        if (i7 < 0) {
            throw new IllegalArgumentException(C0079a.m93a("Cannot have negative offset: ", i7));
        }
        if (i8 < 0) {
            throw new IllegalArgumentException(C0079a.m93a("Cannot have length offset: ", i8));
        }
        int i10 = i7 + i8;
        ?? length = bArr.length;
        if (i10 > length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(bArr.length)));
        }
        if ((i9 & 2) != 0) {
            GZIPOutputStream gZIPOutputStream = null;
            try {
                try {
                    length = new ByteArrayOutputStream();
                    try {
                        aVar = new a(length, i9 | 1);
                        try {
                            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(aVar);
                            try {
                                gZIPOutputStream2.write(bArr, i7, i8);
                                gZIPOutputStream2.close();
                                try {
                                    gZIPOutputStream2.close();
                                } catch (Exception unused) {
                                }
                                try {
                                    aVar.close();
                                } catch (Exception unused2) {
                                }
                                try {
                                    length.close();
                                } catch (Exception unused3) {
                                }
                                byteArray = length.toByteArray();
                            } catch (IOException e7) {
                                gZIPOutputStream = gZIPOutputStream2;
                                throw e7;
                            } catch (Throwable th) {
                                th = th;
                                gZIPOutputStream = gZIPOutputStream2;
                                try {
                                    gZIPOutputStream.close();
                                } catch (Exception unused4) {
                                }
                                try {
                                    aVar.close();
                                } catch (Exception unused5) {
                                }
                                try {
                                    length.close();
                                    throw th;
                                } catch (Exception unused6) {
                                    throw th;
                                }
                            }
                        } catch (IOException e8) {
                            throw e8;
                        }
                    } catch (IOException e9) {
                        throw e9;
                    } catch (Throwable th2) {
                        th = th2;
                        aVar = null;
                    }
                } catch (IOException e10) {
                    throw e10;
                } catch (Throwable th3) {
                    th = th3;
                    length = 0;
                    aVar = null;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } else {
            boolean z6 = (i9 & 8) != 0;
            int i11 = ((i8 / 3) * 4) + (i8 % 3 > 0 ? 4 : 0);
            if (z6) {
                i11 += i11 / 76;
            }
            int i12 = i11;
            byte[] bArr2 = new byte[i12];
            int i13 = i8 - 2;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            while (i14 < i13) {
                m1659c(bArr, i14 + i7, 3, bArr2, i15, i9);
                int i17 = i16 + 4;
                if (!z6 || i17 < 76) {
                    i16 = i17;
                } else {
                    bArr2[i15 + 4] = 10;
                    i15++;
                    i16 = 0;
                }
                i14 += 3;
                i15 += 4;
            }
            if (i14 < i8) {
                m1659c(bArr, i14 + i7, i8 - i14, bArr2, i15, i9);
                i15 += 4;
            }
            if (i15 <= i12 - 1) {
                byteArray = new byte[i15];
                System.arraycopy(bArr2, 0, byteArray, 0, i15);
            } else {
                byteArray = bArr2;
            }
        }
        try {
            return new String(byteArray, "US-ASCII");
        } catch (UnsupportedEncodingException unused7) {
            return new String(byteArray);
        }
    }

    /* renamed from: e */
    public static final byte[] m1661e(int i7) {
        return (i7 & 16) == 16 ? f4287d : (i7 & 32) == 32 ? f4289f : f4285b;
    }

    /* compiled from: Base64Coder.java */
    /* renamed from: m6.a$a */
    public static class a extends FilterOutputStream {

        /* renamed from: e */
        public boolean f4290e;

        /* renamed from: f */
        public int f4291f;

        /* renamed from: g */
        public byte[] f4292g;

        /* renamed from: h */
        public int f4293h;

        /* renamed from: i */
        public int f4294i;

        /* renamed from: j */
        public boolean f4295j;

        /* renamed from: k */
        public byte[] f4296k;

        /* renamed from: l */
        public int f4297l;

        /* renamed from: m */
        public byte[] f4298m;

        public a(OutputStream outputStream, int i7) {
            super(outputStream);
            this.f4295j = (i7 & 8) != 0;
            boolean z6 = (i7 & 1) != 0;
            this.f4290e = z6;
            int i8 = z6 ? 3 : 4;
            this.f4293h = i8;
            this.f4292g = new byte[i8];
            this.f4291f = 0;
            this.f4294i = 0;
            this.f4296k = new byte[4];
            this.f4297l = i7;
            this.f4298m = C1498a.m1661e(i7);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            int i7 = this.f4291f;
            if (i7 > 0) {
                if (!this.f4290e) {
                    throw new IOException("Base64 input not properly padded.");
                }
                OutputStream outputStream = ((FilterOutputStream) this).out;
                byte[] bArr = this.f4296k;
                C1498a.m1659c(this.f4292g, 0, i7, bArr, 0, this.f4297l);
                outputStream.write(bArr);
                this.f4291f = 0;
            }
            super.close();
            this.f4292g = null;
            ((FilterOutputStream) this).out = null;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i7) throws IOException {
            if (!this.f4290e) {
                byte[] bArr = this.f4298m;
                int i8 = i7 & 127;
                if (bArr[i8] <= -5) {
                    if (bArr[i8] != -5) {
                        throw new IOException("Invalid character in Base64 data.");
                    }
                    return;
                }
                byte[] bArr2 = this.f4292g;
                int i9 = this.f4291f;
                int i10 = i9 + 1;
                this.f4291f = i10;
                bArr2[i9] = (byte) i7;
                if (i10 >= this.f4293h) {
                    ((FilterOutputStream) this).out.write(this.f4296k, 0, C1498a.m1658b(bArr2, 0, this.f4296k, 0, this.f4297l));
                    this.f4291f = 0;
                    return;
                }
                return;
            }
            byte[] bArr3 = this.f4292g;
            int i11 = this.f4291f;
            int i12 = i11 + 1;
            this.f4291f = i12;
            bArr3[i11] = (byte) i7;
            int i13 = this.f4293h;
            if (i12 >= i13) {
                OutputStream outputStream = ((FilterOutputStream) this).out;
                byte[] bArr4 = this.f4296k;
                C1498a.m1659c(bArr3, 0, i13, bArr4, 0, this.f4297l);
                outputStream.write(bArr4);
                int i14 = this.f4294i + 4;
                this.f4294i = i14;
                if (this.f4295j && i14 >= 76) {
                    ((FilterOutputStream) this).out.write(10);
                    this.f4294i = 0;
                }
                this.f4291f = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i7, int i8) throws IOException {
            for (int i9 = 0; i9 < i8; i9++) {
                write(bArr[i7 + i9]);
            }
        }
    }
}
