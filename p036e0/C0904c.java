package p036e0;

import android.graphics.Bitmap;
import p162u.C1965i;
import p162u.C1966j;
import p162u.EnumC1959c;
import p162u.InterfaceC1968l;

/* compiled from: BitmapEncoder.java */
/* renamed from: e0.c */
/* loaded from: classes.dex */
public class C0904c implements InterfaceC1968l<Bitmap> {

    /* renamed from: a */
    public static final C1965i<Integer> f1627a = C1965i.m2295a("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);

    /* renamed from: b */
    public static final C1965i<Bitmap.CompressFormat> f1628b = new C1965i<>("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat", null, C1965i.f5737e);

    /* JADX WARN: Removed duplicated region for block: B:29:0x0082 A[Catch: all -> 0x00a2, TRY_LEAVE, TryCatch #1 {all -> 0x00a2, blocks: (B:10:0x0048, B:15:0x0068, B:27:0x007c, B:29:0x0082, B:34:0x009e, B:35:0x00a1, B:25:0x0078), top: B:45:0x0048 }] */
    @Override // p162u.InterfaceC1960d
    /* renamed from: D */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean mo784D(java.lang.Object r9, java.io.File r10, p162u.C1966j r11) {
        /*
            r8 = this;
            x.r r9 = (p183x.InterfaceC2057r) r9
            java.lang.String r0 = "BitmapEncoder"
            java.lang.Object r9 = r9.get()
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            u.i<android.graphics.Bitmap$CompressFormat> r1 = p036e0.C0904c.f1628b
            java.lang.Object r1 = r11.m2296c(r1)
            android.graphics.Bitmap$CompressFormat r1 = (android.graphics.Bitmap.CompressFormat) r1
            if (r1 == 0) goto L15
            goto L20
        L15:
            boolean r1 = r9.hasAlpha()
            if (r1 == 0) goto L1e
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG
            goto L20
        L1e:
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
        L20:
            java.lang.String r2 = "encode: ["
            java.lang.StringBuilder r2 = p009b.C0413b.m112a(r2)
            int r3 = r9.getWidth()
            r2.append(r3)
            java.lang.String r3 = "x"
            r2.append(r3)
            int r3 = r9.getHeight()
            r2.append(r3)
            java.lang.String r3 = "] "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            android.support.v4.os.TraceCompat.beginSection(r2)
            int r2 = p142r0.C1819d.f5292b     // Catch: java.lang.Throwable -> La2
            long r2 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> La2
            u.i<java.lang.Integer> r4 = p036e0.C0904c.f1627a     // Catch: java.lang.Throwable -> La2
            java.lang.Object r4 = r11.m2296c(r4)     // Catch: java.lang.Throwable -> La2
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch: java.lang.Throwable -> La2
            int r4 = r4.intValue()     // Catch: java.lang.Throwable -> La2
            r5 = 0
            r6 = 0
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72
            r7.<init>(r10)     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72
            r9.compress(r1, r4, r7)     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e
            r7.close()     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e
            r5 = 1
            r7.close()     // Catch: java.io.IOException -> L7b java.lang.Throwable -> La2
            goto L7b
        L6c:
            r9 = move-exception
            goto L9c
        L6e:
            r6 = r7
            goto L72
        L70:
            r9 = move-exception
            goto L9b
        L72:
            r10 = 3
            android.util.Log.isLoggable(r0, r10)     // Catch: java.lang.Throwable -> L70
            if (r6 == 0) goto L7b
            r6.close()     // Catch: java.io.IOException -> L7b java.lang.Throwable -> La2
        L7b:
            r10 = 2
            boolean r10 = android.util.Log.isLoggable(r0, r10)     // Catch: java.lang.Throwable -> La2
            if (r10 == 0) goto L97
            java.util.Objects.toString(r1)     // Catch: java.lang.Throwable -> La2
            p142r0.C1823h.m2060d(r9)     // Catch: java.lang.Throwable -> La2
            p142r0.C1819d.m2050a(r2)     // Catch: java.lang.Throwable -> La2
            u.i<android.graphics.Bitmap$CompressFormat> r10 = p036e0.C0904c.f1628b     // Catch: java.lang.Throwable -> La2
            java.lang.Object r10 = r11.m2296c(r10)     // Catch: java.lang.Throwable -> La2
            java.util.Objects.toString(r10)     // Catch: java.lang.Throwable -> La2
            r9.hasAlpha()     // Catch: java.lang.Throwable -> La2
        L97:
            android.support.v4.os.TraceCompat.endSection()
            return r5
        L9b:
            r7 = r6
        L9c:
            if (r7 == 0) goto La1
            r7.close()     // Catch: java.io.IOException -> La1 java.lang.Throwable -> La2
        La1:
            throw r9     // Catch: java.lang.Throwable -> La2
        La2:
            r9 = move-exception
            android.support.v4.os.TraceCompat.endSection()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p036e0.C0904c.mo784D(java.lang.Object, java.io.File, u.j):boolean");
    }

    @Override // p162u.InterfaceC1968l
    /* renamed from: s */
    public EnumC1959c mo821s(C1966j c1966j) {
        return EnumC1959c.TRANSFORMED;
    }
}
