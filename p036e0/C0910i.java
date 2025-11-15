package p036e0;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Objects;
import p162u.InterfaceC1962f;
import p190y.InterfaceC2084b;

/* compiled from: DefaultImageHeaderParser.java */
/* renamed from: e0.i */
/* loaded from: classes.dex */
public final class C0910i implements InterfaceC1962f {

    /* renamed from: a */
    public static final byte[] f1635a = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));

    /* renamed from: b */
    public static final int[] f1636b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    /* compiled from: DefaultImageHeaderParser.java */
    /* renamed from: e0.i$a */
    public static final class a implements c {

        /* renamed from: a */
        public final ByteBuffer f1637a;

        public a(ByteBuffer byteBuffer) {
            this.f1637a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // p036e0.C0910i.c
        /* renamed from: a */
        public int mo831a() {
            return ((mo832b() << 8) & 65280) | (mo832b() & 255);
        }

        @Override // p036e0.C0910i.c
        /* renamed from: b */
        public int mo832b() {
            if (this.f1637a.remaining() < 1) {
                return -1;
            }
            return this.f1637a.get();
        }

        @Override // p036e0.C0910i.c
        public int read(byte[] bArr, int i7) {
            int iMin = Math.min(i7, this.f1637a.remaining());
            if (iMin == 0) {
                return -1;
            }
            this.f1637a.get(bArr, 0, iMin);
            return iMin;
        }

        @Override // p036e0.C0910i.c
        public long skip(long j7) {
            int iMin = (int) Math.min(this.f1637a.remaining(), j7);
            ByteBuffer byteBuffer = this.f1637a;
            byteBuffer.position(byteBuffer.position() + iMin);
            return iMin;
        }
    }

    /* compiled from: DefaultImageHeaderParser.java */
    /* renamed from: e0.i$b */
    public static final class b {

        /* renamed from: a */
        public final ByteBuffer f1638a;

        public b(byte[] bArr, int i7) {
            this.f1638a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i7);
        }

        /* renamed from: a */
        public short m833a(int i7) {
            if (this.f1638a.remaining() - i7 >= 2) {
                return this.f1638a.getShort(i7);
            }
            return (short) -1;
        }

        /* renamed from: b */
        public int m834b(int i7) {
            if (this.f1638a.remaining() - i7 >= 4) {
                return this.f1638a.getInt(i7);
            }
            return -1;
        }
    }

    /* compiled from: DefaultImageHeaderParser.java */
    /* renamed from: e0.i$c */
    public interface c {
        /* renamed from: a */
        int mo831a();

        /* renamed from: b */
        int mo832b();

        int read(byte[] bArr, int i7);

        long skip(long j7);
    }

    /* compiled from: DefaultImageHeaderParser.java */
    /* renamed from: e0.i$d */
    public static final class d implements c {

        /* renamed from: a */
        public final InputStream f1639a;

        public d(InputStream inputStream) {
            this.f1639a = inputStream;
        }

        @Override // p036e0.C0910i.c
        /* renamed from: a */
        public int mo831a() {
            return ((this.f1639a.read() << 8) & 65280) | (this.f1639a.read() & 255);
        }

        @Override // p036e0.C0910i.c
        /* renamed from: b */
        public int mo832b() {
            return this.f1639a.read();
        }

        /* renamed from: c */
        public short m835c() {
            return (short) (this.f1639a.read() & 255);
        }

        @Override // p036e0.C0910i.c
        public int read(byte[] bArr, int i7) throws IOException {
            int i8 = i7;
            while (i8 > 0) {
                int i9 = this.f1639a.read(bArr, i7 - i8, i8);
                if (i9 == -1) {
                    break;
                }
                i8 -= i9;
            }
            return i7 - i8;
        }

        @Override // p036e0.C0910i.c
        public long skip(long j7) throws IOException {
            if (j7 < 0) {
                return 0L;
            }
            long j8 = j7;
            while (j8 > 0) {
                long jSkip = this.f1639a.skip(j8);
                if (jSkip <= 0) {
                    if (this.f1639a.read() == -1) {
                        break;
                    }
                    jSkip = 1;
                }
                j8 -= jSkip;
            }
            return j7 - j8;
        }
    }

    @Override // p162u.InterfaceC1962f
    /* renamed from: a */
    public InterfaceC1962f.a mo826a(ByteBuffer byteBuffer) {
        return m829d(new a(byteBuffer));
    }

    @Override // p162u.InterfaceC1962f
    /* renamed from: b */
    public int mo827b(InputStream inputStream, InterfaceC2084b interfaceC2084b) {
        int iMo831a;
        d dVar = new d(inputStream);
        Objects.requireNonNull(interfaceC2084b, "Argument must not be null");
        int iMo831a2 = dVar.mo831a();
        int iM830e = -1;
        if ((iMo831a2 & 65496) == 65496 || iMo831a2 == 19789 || iMo831a2 == 18761) {
            while (true) {
                if (dVar.m835c() == 255) {
                    short sM835c = dVar.m835c();
                    if (sM835c == 218) {
                        break;
                    }
                    if (sM835c != 217) {
                        iMo831a = dVar.mo831a() - 2;
                        if (sM835c == 225) {
                            break;
                        }
                        long j7 = iMo831a;
                        if (dVar.skip(j7) != j7) {
                            Log.isLoggable("DfltImageHeaderParser", 3);
                            break;
                        }
                    } else {
                        Log.isLoggable("DfltImageHeaderParser", 3);
                        break;
                    }
                } else {
                    Log.isLoggable("DfltImageHeaderParser", 3);
                    break;
                }
            }
            iMo831a = -1;
            if (iMo831a == -1) {
                Log.isLoggable("DfltImageHeaderParser", 3);
            } else {
                byte[] bArr = (byte[]) interfaceC2084b.mo2511d(iMo831a, byte[].class);
                try {
                    iM830e = m830e(dVar, bArr, iMo831a);
                } finally {
                    interfaceC2084b.mo2510c(bArr, byte[].class);
                }
            }
        } else {
            Log.isLoggable("DfltImageHeaderParser", 3);
        }
        return iM830e;
    }

    @Override // p162u.InterfaceC1962f
    /* renamed from: c */
    public InterfaceC1962f.a mo828c(InputStream inputStream) {
        return m829d(new d(inputStream));
    }

    /* renamed from: d */
    public final InterfaceC1962f.a m829d(c cVar) {
        InterfaceC1962f.a aVar = InterfaceC1962f.a.WEBP_A;
        InterfaceC1962f.a aVar2 = InterfaceC1962f.a.WEBP;
        InterfaceC1962f.a aVar3 = InterfaceC1962f.a.UNKNOWN;
        int iMo831a = cVar.mo831a();
        if (iMo831a == 65496) {
            return InterfaceC1962f.a.JPEG;
        }
        int iMo831a2 = ((iMo831a << 16) & SupportMenu.CATEGORY_MASK) | (cVar.mo831a() & 65535);
        if (iMo831a2 == -1991225785) {
            cVar.skip(21L);
            return cVar.mo832b() >= 3 ? InterfaceC1962f.a.PNG_A : InterfaceC1962f.a.PNG;
        }
        if ((iMo831a2 >> 8) == 4671814) {
            return InterfaceC1962f.a.GIF;
        }
        if (iMo831a2 != 1380533830) {
            return aVar3;
        }
        cVar.skip(4L);
        if ((((cVar.mo831a() << 16) & SupportMenu.CATEGORY_MASK) | (cVar.mo831a() & 65535)) != 1464156752) {
            return aVar3;
        }
        int iMo831a3 = ((cVar.mo831a() << 16) & SupportMenu.CATEGORY_MASK) | (cVar.mo831a() & 65535);
        if ((iMo831a3 & InputDeviceCompat.SOURCE_ANY) != 1448097792) {
            return aVar3;
        }
        int i7 = iMo831a3 & 255;
        if (i7 == 88) {
            cVar.skip(4L);
            return (cVar.mo832b() & 16) != 0 ? aVar : aVar2;
        }
        if (i7 != 76) {
            return aVar2;
        }
        cVar.skip(4L);
        return (cVar.mo832b() & 8) != 0 ? aVar : aVar2;
    }

    /* renamed from: e */
    public final int m830e(c cVar, byte[] bArr, int i7) {
        ByteOrder byteOrder;
        if (cVar.read(bArr, i7) != i7) {
            Log.isLoggable("DfltImageHeaderParser", 3);
            return -1;
        }
        boolean z6 = bArr != null && i7 > f1635a.length;
        if (z6) {
            int i8 = 0;
            while (true) {
                byte[] bArr2 = f1635a;
                if (i8 >= bArr2.length) {
                    break;
                }
                if (bArr[i8] != bArr2[i8]) {
                    z6 = false;
                    break;
                }
                i8++;
            }
        }
        if (!z6) {
            Log.isLoggable("DfltImageHeaderParser", 3);
            return -1;
        }
        b bVar = new b(bArr, i7);
        short sM833a = bVar.m833a(6);
        if (sM833a == 19789) {
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else if (sM833a == 18761) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            Log.isLoggable("DfltImageHeaderParser", 3);
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        bVar.f1638a.order(byteOrder);
        int iM834b = bVar.m834b(10) + 6;
        short sM833a2 = bVar.m833a(iM834b);
        for (int i9 = 0; i9 < sM833a2; i9++) {
            int i10 = (i9 * 12) + iM834b + 2;
            if (bVar.m833a(i10) == 274) {
                short sM833a3 = bVar.m833a(i10 + 2);
                if (sM833a3 < 1 || sM833a3 > 12) {
                    Log.isLoggable("DfltImageHeaderParser", 3);
                } else {
                    int iM834b2 = bVar.m834b(i10 + 4);
                    if (iM834b2 < 0) {
                        Log.isLoggable("DfltImageHeaderParser", 3);
                    } else {
                        Log.isLoggable("DfltImageHeaderParser", 3);
                        int i11 = iM834b2 + f1636b[sM833a3];
                        if (i11 > 4) {
                            Log.isLoggable("DfltImageHeaderParser", 3);
                        } else {
                            int i12 = i10 + 8;
                            if (i12 < 0 || i12 > bVar.f1638a.remaining()) {
                                Log.isLoggable("DfltImageHeaderParser", 3);
                            } else {
                                if (i11 >= 0 && i11 + i12 <= bVar.f1638a.remaining()) {
                                    return bVar.m833a(i12);
                                }
                                Log.isLoggable("DfltImageHeaderParser", 3);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
