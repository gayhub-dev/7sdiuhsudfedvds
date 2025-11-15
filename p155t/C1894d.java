package p155t;

import android.support.v4.view.ViewCompat;
import android.util.Log;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/* compiled from: GifHeaderParser.java */
/* renamed from: t.d */
/* loaded from: classes.dex */
public class C1894d {

    /* renamed from: b */
    public ByteBuffer f5567b;

    /* renamed from: c */
    public C1893c f5568c;

    /* renamed from: a */
    public final byte[] f5566a = new byte[256];

    /* renamed from: d */
    public int f5569d = 0;

    /* renamed from: a */
    public final boolean m2187a() {
        return this.f5568c.f5556b != 0;
    }

    /* renamed from: b */
    public final int m2188b() {
        try {
            return this.f5567b.get() & 255;
        } catch (Exception unused) {
            this.f5568c.f5556b = 1;
            return 0;
        }
    }

    /* renamed from: c */
    public final int m2189c() {
        int iM2188b = m2188b();
        this.f5569d = iM2188b;
        int i7 = 0;
        if (iM2188b > 0) {
            while (true) {
                try {
                    int i8 = this.f5569d;
                    if (i7 >= i8) {
                        break;
                    }
                    int i9 = i8 - i7;
                    this.f5567b.get(this.f5566a, i7, i9);
                    i7 += i9;
                } catch (Exception unused) {
                    Log.isLoggable("GifHeaderParser", 3);
                    this.f5568c.f5556b = 1;
                }
            }
        }
        return i7;
    }

    /* renamed from: d */
    public final int[] m2190d(int i7) {
        byte[] bArr = new byte[i7 * 3];
        int[] iArr = null;
        try {
            this.f5567b.get(bArr);
            iArr = new int[256];
            int i8 = 0;
            int i9 = 0;
            while (i8 < i7) {
                int i10 = i9 + 1;
                int i11 = bArr[i9] & 255;
                int i12 = i10 + 1;
                int i13 = bArr[i10] & 255;
                int i14 = i12 + 1;
                int i15 = i8 + 1;
                iArr[i8] = (i11 << 16) | ViewCompat.MEASURED_STATE_MASK | (i13 << 8) | (bArr[i12] & 255);
                i9 = i14;
                i8 = i15;
            }
        } catch (BufferUnderflowException unused) {
            Log.isLoggable("GifHeaderParser", 3);
            this.f5568c.f5556b = 1;
        }
        return iArr;
    }

    /* renamed from: e */
    public final int m2191e() {
        return this.f5567b.getShort();
    }

    /* renamed from: f */
    public final void m2192f() {
        int iM2188b;
        do {
            iM2188b = m2188b();
            this.f5567b.position(Math.min(this.f5567b.position() + iM2188b, this.f5567b.limit()));
        } while (iM2188b > 0);
    }
}
