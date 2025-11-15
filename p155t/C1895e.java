package p155t;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import p068i0.C1136b;
import p155t.InterfaceC1891a;
import p190y.InterfaceC2084b;

/* compiled from: StandardGifDecoder.java */
/* renamed from: t.e */
/* loaded from: classes.dex */
public class C1895e implements InterfaceC1891a {

    /* renamed from: a */
    @ColorInt
    public int[] f5570a;

    /* renamed from: c */
    public ByteBuffer f5572c;

    /* renamed from: d */
    public byte[] f5573d;

    /* renamed from: e */
    @Nullable
    public byte[] f5574e;

    /* renamed from: h */
    public short[] f5577h;

    /* renamed from: i */
    public byte[] f5578i;

    /* renamed from: j */
    public byte[] f5579j;

    /* renamed from: k */
    public byte[] f5580k;

    /* renamed from: l */
    @ColorInt
    public int[] f5581l;

    /* renamed from: m */
    public int f5582m;

    /* renamed from: n */
    public C1893c f5583n;

    /* renamed from: o */
    public InterfaceC1891a.a f5584o;

    /* renamed from: p */
    public Bitmap f5585p;

    /* renamed from: q */
    public boolean f5586q;

    /* renamed from: r */
    public int f5587r;

    /* renamed from: s */
    public int f5588s;

    /* renamed from: t */
    public int f5589t;

    /* renamed from: u */
    public int f5590u;

    /* renamed from: v */
    public boolean f5591v;

    /* renamed from: b */
    @ColorInt
    public final int[] f5571b = new int[256];

    /* renamed from: f */
    public int f5575f = 0;

    /* renamed from: g */
    public int f5576g = 0;

    public C1895e(InterfaceC1891a.a aVar, C1893c c1893c, ByteBuffer byteBuffer, int i7) {
        this.f5584o = aVar;
        this.f5583n = new C1893c();
        synchronized (this) {
            if (i7 <= 0) {
                throw new IllegalArgumentException("Sample size must be >=0, not: " + i7);
            }
            int iHighestOneBit = Integer.highestOneBit(i7);
            this.f5587r = 0;
            this.f5583n = c1893c;
            this.f5591v = false;
            this.f5582m = -1;
            ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
            this.f5572c = byteBufferAsReadOnlyBuffer;
            byteBufferAsReadOnlyBuffer.position(0);
            this.f5572c.order(ByteOrder.LITTLE_ENDIAN);
            this.f5586q = false;
            Iterator<C1892b> it = c1893c.f5559e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().f5550g == 3) {
                    this.f5586q = true;
                    break;
                }
            }
            this.f5588s = iHighestOneBit;
            int i8 = c1893c.f5560f;
            this.f5590u = i8 / iHighestOneBit;
            int i9 = c1893c.f5561g;
            this.f5589t = i9 / iHighestOneBit;
            this.f5580k = ((C1136b) this.f5584o).m1299a(i8 * i9);
            InterfaceC1891a.a aVar2 = this.f5584o;
            int i10 = this.f5590u * this.f5589t;
            InterfaceC2084b interfaceC2084b = ((C1136b) aVar2).f2477b;
            this.f5581l = interfaceC2084b == null ? new int[i10] : (int[]) interfaceC2084b.mo2511d(i10, int[].class);
        }
    }

    @Override // p155t.InterfaceC1891a
    /* renamed from: a */
    public int mo2180a() {
        return this.f5582m;
    }

    @Override // p155t.InterfaceC1891a
    /* renamed from: b */
    public synchronized Bitmap mo2181b() {
        if (this.f5583n.f5557c <= 0 || this.f5582m < 0) {
            if (Log.isLoggable("e", 3)) {
                int i7 = this.f5583n.f5557c;
            }
            this.f5587r = 1;
        }
        int i8 = this.f5587r;
        if (i8 != 1 && i8 != 2) {
            this.f5587r = 0;
            C1892b c1892b = this.f5583n.f5559e.get(this.f5582m);
            int i9 = this.f5582m - 1;
            C1892b c1892b2 = i9 >= 0 ? this.f5583n.f5559e.get(i9) : null;
            int[] iArr = c1892b.f5554k;
            if (iArr == null) {
                iArr = this.f5583n.f5555a;
            }
            this.f5570a = iArr;
            if (iArr == null) {
                Log.isLoggable("e", 3);
                this.f5587r = 1;
                return null;
            }
            if (c1892b.f5549f) {
                System.arraycopy(iArr, 0, this.f5571b, 0, iArr.length);
                int[] iArr2 = this.f5571b;
                this.f5570a = iArr2;
                iArr2[c1892b.f5551h] = 0;
            }
            return m2196k(c1892b, c1892b2);
        }
        Log.isLoggable("e", 3);
        return null;
    }

    @Override // p155t.InterfaceC1891a
    /* renamed from: c */
    public void mo2182c() {
        this.f5582m = (this.f5582m + 1) % this.f5583n.f5557c;
    }

    @Override // p155t.InterfaceC1891a
    public void clear() {
        InterfaceC2084b interfaceC2084b;
        this.f5583n = null;
        byte[] bArr = this.f5580k;
        if (bArr != null) {
            ((C1136b) this.f5584o).m1300b(bArr);
        }
        int[] iArr = this.f5581l;
        if (iArr != null && (interfaceC2084b = ((C1136b) this.f5584o).f2477b) != null) {
            interfaceC2084b.mo2510c(iArr, int[].class);
        }
        Bitmap bitmap = this.f5585p;
        if (bitmap != null) {
            ((C1136b) this.f5584o).f2476a.mo2520e(bitmap);
        }
        this.f5585p = null;
        this.f5572c = null;
        this.f5591v = false;
        byte[] bArr2 = this.f5573d;
        if (bArr2 != null) {
            ((C1136b) this.f5584o).m1300b(bArr2);
        }
        byte[] bArr3 = this.f5574e;
        if (bArr3 != null) {
            ((C1136b) this.f5584o).m1300b(bArr3);
        }
    }

    @Override // p155t.InterfaceC1891a
    /* renamed from: d */
    public int mo2183d() {
        return this.f5583n.f5557c;
    }

    @Override // p155t.InterfaceC1891a
    /* renamed from: e */
    public int mo2184e() {
        int i7;
        C1893c c1893c = this.f5583n;
        int i8 = c1893c.f5557c;
        if (i8 <= 0 || (i7 = this.f5582m) < 0) {
            return 0;
        }
        if (i7 < 0 || i7 >= i8) {
            return -1;
        }
        return c1893c.f5559e.get(i7).f5552i;
    }

    @Override // p155t.InterfaceC1891a
    /* renamed from: f */
    public int mo2185f() {
        return (this.f5581l.length * 4) + this.f5572c.limit() + this.f5580k.length;
    }

    @Override // p155t.InterfaceC1891a
    /* renamed from: g */
    public ByteBuffer mo2186g() {
        return this.f5572c;
    }

    /* renamed from: h */
    public final Bitmap m2193h() {
        Bitmap.Config config = this.f5591v ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmapMo2518c = ((C1136b) this.f5584o).f2476a.mo2518c(this.f5590u, this.f5589t, config);
        bitmapMo2518c.setHasAlpha(true);
        return bitmapMo2518c;
    }

    /* renamed from: i */
    public final int m2194i() {
        try {
            m2195j();
            byte[] bArr = this.f5574e;
            int i7 = this.f5576g;
            this.f5576g = i7 + 1;
            return bArr[i7] & 255;
        } catch (Exception unused) {
            this.f5587r = 1;
            return 0;
        }
    }

    /* renamed from: j */
    public final void m2195j() {
        if (this.f5575f > this.f5576g) {
            return;
        }
        if (this.f5574e == null) {
            this.f5574e = ((C1136b) this.f5584o).m1299a(16384);
        }
        this.f5576g = 0;
        int iMin = Math.min(this.f5572c.remaining(), 16384);
        this.f5575f = iMin;
        this.f5572c.get(this.f5574e, 0, iMin);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:192:0x013e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01fc A[PHI: r4
      0x01fc: PHI (r4v35 int) = (r4v30 int), (r4v37 int) binds: [B:91:0x01e4, B:93:0x01f1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r7v36 */
    /* JADX WARN: Type inference failed for: r7v37 */
    /* JADX WARN: Type inference failed for: r7v38 */
    /* JADX WARN: Type inference failed for: r7v42, types: [short] */
    /* JADX WARN: Type inference failed for: r7v44 */
    /* renamed from: k */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap m2196k(p155t.C1892b r30, p155t.C1892b r31) {
        /*
            Method dump skipped, instructions count: 963
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p155t.C1895e.m2196k(t.b, t.b):android.graphics.Bitmap");
    }
}
