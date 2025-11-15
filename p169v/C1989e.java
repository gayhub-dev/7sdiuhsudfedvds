package p169v;

import android.support.constraint.motion.C0079a;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ExifOrientationStream.java */
/* renamed from: v.e */
/* loaded from: classes.dex */
public final class C1989e extends FilterInputStream {

    /* renamed from: g */
    public static final byte[] f5808g = {-1, -31, 0, 28, 69, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};

    /* renamed from: h */
    public static final int f5809h = 31;

    /* renamed from: e */
    public final byte f5810e;

    /* renamed from: f */
    public int f5811f;

    public C1989e(InputStream inputStream, int i7) {
        super(inputStream);
        if (i7 < -1 || i7 > 8) {
            throw new IllegalArgumentException(C0079a.m93a("Cannot add invalid orientation: ", i7));
        }
        this.f5810e = (byte) i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i7) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int i7;
        int i8 = this.f5811f;
        int i9 = (i8 < 2 || i8 > (i7 = f5809h)) ? super.read() : i8 == i7 ? this.f5810e : f5808g[i8 - 2] & 255;
        if (i9 != -1) {
            this.f5811f++;
        }
        return i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j7) throws IOException {
        long jSkip = super.skip(j7);
        if (jSkip > 0) {
            this.f5811f = (int) (this.f5811f + jSkip);
        }
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i7, int i8) throws IOException {
        int i9;
        int i10 = this.f5811f;
        int i11 = f5809h;
        if (i10 > i11) {
            i9 = super.read(bArr, i7, i8);
        } else if (i10 == i11) {
            bArr[i7] = this.f5810e;
            i9 = 1;
        } else if (i10 < 2) {
            i9 = super.read(bArr, i7, 2 - i10);
        } else {
            int iMin = Math.min(i11 - i10, i8);
            System.arraycopy(f5808g, this.f5811f - 2, bArr, i7, iMin);
            i9 = iMin;
        }
        if (i9 > 0) {
            this.f5811f += i9;
        }
        return i9;
    }
}
