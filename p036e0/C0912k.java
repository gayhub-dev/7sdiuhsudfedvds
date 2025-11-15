package p036e0;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import p009b.C0413b;
import p043f.C0988e;
import p142r0.C1823h;
import p162u.C1965i;
import p162u.C1966j;
import p162u.EnumC1958b;
import p162u.InterfaceC1962f;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2084b;
import p190y.InterfaceC2086d;

/* compiled from: Downsampler.java */
/* renamed from: e0.k */
/* loaded from: classes.dex */
public final class C0912k {

    /* renamed from: f */
    public static final C1965i<EnumC1958b> f1644f = C1965i.m2295a("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", EnumC1958b.PREFER_ARGB_8888_DISALLOW_HARDWARE);

    /* renamed from: g */
    public static final C1965i<AbstractC0911j> f1645g = C1965i.m2295a("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", AbstractC0911j.f1642c);

    /* renamed from: h */
    public static final C1965i<Boolean> f1646h = C1965i.m2295a("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", Boolean.FALSE);

    /* renamed from: i */
    public static final C1965i<Boolean> f1647i = C1965i.m2295a("com.bumtpech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", null);

    /* renamed from: j */
    public static final Set<String> f1648j = Collections.unmodifiableSet(new HashSet(Arrays.asList("image/vnd.wap.wbmp", "image/x-ico")));

    /* renamed from: k */
    public static final b f1649k = new a();

    /* renamed from: l */
    public static final Queue<BitmapFactory.Options> f1650l;

    /* renamed from: a */
    public final InterfaceC2086d f1651a;

    /* renamed from: b */
    public final DisplayMetrics f1652b;

    /* renamed from: c */
    public final InterfaceC2084b f1653c;

    /* renamed from: d */
    public final List<InterfaceC1962f> f1654d;

    /* renamed from: e */
    public final C0914m f1655e;

    /* compiled from: Downsampler.java */
    /* renamed from: e0.k$a */
    public static class a implements b {
        @Override // p036e0.C0912k.b
        /* renamed from: a */
        public void mo845a(InterfaceC2086d interfaceC2086d, Bitmap bitmap) {
        }

        @Override // p036e0.C0912k.b
        /* renamed from: b */
        public void mo846b() {
        }
    }

    /* compiled from: Downsampler.java */
    /* renamed from: e0.k$b */
    public interface b {
        /* renamed from: a */
        void mo845a(InterfaceC2086d interfaceC2086d, Bitmap bitmap);

        /* renamed from: b */
        void mo846b();
    }

    static {
        Collections.unmodifiableSet(EnumSet.of(InterfaceC1962f.a.JPEG, InterfaceC1962f.a.PNG_A, InterfaceC1962f.a.PNG));
        char[] cArr = C1823h.f5300a;
        f1650l = new ArrayDeque(0);
    }

    public C0912k(List<InterfaceC1962f> list, DisplayMetrics displayMetrics, InterfaceC2086d interfaceC2086d, InterfaceC2084b interfaceC2084b) {
        if (C0914m.f1658d == null) {
            synchronized (C0914m.class) {
                if (C0914m.f1658d == null) {
                    C0914m.f1658d = new C0914m();
                }
            }
        }
        this.f1655e = C0914m.f1658d;
        this.f1654d = list;
        Objects.requireNonNull(displayMetrics, "Argument must not be null");
        this.f1652b = displayMetrics;
        Objects.requireNonNull(interfaceC2086d, "Argument must not be null");
        this.f1651a = interfaceC2086d;
        Objects.requireNonNull(interfaceC2084b, "Argument must not be null");
        this.f1653c = interfaceC2084b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:?, code lost:
    
        throw r0;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap m838c(java.io.InputStream r5, android.graphics.BitmapFactory.Options r6, p036e0.C0912k.b r7, p190y.InterfaceC2086d r8) throws java.io.IOException {
        /*
            boolean r0 = r6.inJustDecodeBounds
            if (r0 == 0) goto La
            r0 = 5242880(0x500000, float:7.34684E-39)
            r5.mark(r0)
            goto Ld
        La:
            r7.mo846b()
        Ld:
            int r0 = r6.outWidth
            int r1 = r6.outHeight
            java.lang.String r2 = r6.outMimeType
            java.util.concurrent.locks.Lock r3 = p036e0.C0918q.f1675b
            r3.lock()
            r4 = 0
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r5, r4, r6)     // Catch: java.lang.Throwable -> L28 java.lang.IllegalArgumentException -> L2a
            r3.unlock()
            boolean r6 = r6.inJustDecodeBounds
            if (r6 == 0) goto L27
            r5.reset()
        L27:
            return r7
        L28:
            r5 = move-exception
            goto L4f
        L2a:
            r3 = move-exception
            java.io.IOException r0 = m840e(r3, r0, r1, r2, r6)     // Catch: java.lang.Throwable -> L28
            java.lang.String r1 = "Downsampler"
            r2 = 3
            android.util.Log.isLoggable(r1, r2)     // Catch: java.lang.Throwable -> L28
            android.graphics.Bitmap r1 = r6.inBitmap     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L4e
            r5.reset()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L4d
            android.graphics.Bitmap r1 = r6.inBitmap     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L4d
            r8.mo2520e(r1)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L4d
            r6.inBitmap = r4     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L4d
            android.graphics.Bitmap r5 = m838c(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L4d
            java.util.concurrent.locks.Lock r6 = p036e0.C0918q.f1675b
            r6.unlock()
            return r5
        L4d:
            throw r0     // Catch: java.lang.Throwable -> L28
        L4e:
            throw r0     // Catch: java.lang.Throwable -> L28
        L4f:
            java.util.concurrent.locks.Lock r6 = p036e0.C0918q.f1675b
            r6.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p036e0.C0912k.m838c(java.io.InputStream, android.graphics.BitmapFactory$Options, e0.k$b, y.d):android.graphics.Bitmap");
    }

    @TargetApi(19)
    @Nullable
    /* renamed from: d */
    public static String m839d(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        StringBuilder sbM112a = C0413b.m112a(" (");
        sbM112a.append(bitmap.getAllocationByteCount());
        sbM112a.append(")");
        String string = sbM112a.toString();
        StringBuilder sbM112a2 = C0413b.m112a("[");
        sbM112a2.append(bitmap.getWidth());
        sbM112a2.append("x");
        sbM112a2.append(bitmap.getHeight());
        sbM112a2.append("] ");
        sbM112a2.append(bitmap.getConfig());
        sbM112a2.append(string);
        return sbM112a2.toString();
    }

    /* renamed from: e */
    public static IOException m840e(IllegalArgumentException illegalArgumentException, int i7, int i8, String str, BitmapFactory.Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + i7 + ", outHeight: " + i8 + ", outMimeType: " + str + ", inBitmap: " + m839d(options.inBitmap), illegalArgumentException);
    }

    /* renamed from: f */
    public static void m841f(BitmapFactory.Options options) {
        m842g(options);
        Queue<BitmapFactory.Options> queue = f1650l;
        synchronized (queue) {
            ((ArrayDeque) queue).offer(options);
        }
    }

    /* renamed from: g */
    public static void m842g(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }

    /* renamed from: a */
    public InterfaceC2057r<Bitmap> m843a(InputStream inputStream, int i7, int i8, C1966j c1966j, b bVar) {
        BitmapFactory.Options options;
        BitmapFactory.Options options2;
        C0988e.m978d(inputStream.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bArr = (byte[]) this.f1653c.mo2511d(65536, byte[].class);
        synchronized (C0912k.class) {
            Queue<BitmapFactory.Options> queue = f1650l;
            synchronized (queue) {
                options = (BitmapFactory.Options) ((ArrayDeque) queue).poll();
            }
            if (options == null) {
                options = new BitmapFactory.Options();
                m842g(options);
            }
            options2 = options;
        }
        options2.inTempStorage = bArr;
        EnumC1958b enumC1958b = (EnumC1958b) c1966j.m2296c(f1644f);
        AbstractC0911j abstractC0911j = (AbstractC0911j) c1966j.m2296c(f1645g);
        boolean zBooleanValue = ((Boolean) c1966j.m2296c(f1646h)).booleanValue();
        C1965i<Boolean> c1965i = f1647i;
        try {
            return C0905d.m822c(m844b(inputStream, options2, abstractC0911j, enumC1958b, enumC1958b == EnumC1958b.PREFER_ARGB_8888_DISALLOW_HARDWARE ? false : c1966j.m2296c(c1965i) != null && ((Boolean) c1966j.m2296c(c1965i)).booleanValue(), i7, i8, zBooleanValue, bVar), this.f1651a);
        } finally {
            m841f(options2);
            this.f1653c.mo2510c(bArr, byte[].class);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0165  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap m844b(java.io.InputStream r17, android.graphics.BitmapFactory.Options r18, p036e0.AbstractC0911j r19, p162u.EnumC1958b r20, boolean r21, int r22, int r23, boolean r24, p036e0.C0912k.b r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 768
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p036e0.C0912k.m844b(java.io.InputStream, android.graphics.BitmapFactory$Options, e0.j, u.b, boolean, int, int, boolean, e0.k$b):android.graphics.Bitmap");
    }
}
