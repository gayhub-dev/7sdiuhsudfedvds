package p152s3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: LoggerPrinter.java */
/* renamed from: s3.f */
/* loaded from: classes.dex */
public class C1875f {

    /* renamed from: a */
    public final ThreadLocal<String> f5452a = new ThreadLocal<>();

    /* renamed from: b */
    public final List<InterfaceC1872c> f5453b = new ArrayList();

    /* renamed from: a */
    public void m2145a(@Nullable Object obj) {
        m2147c(3, null, obj == null ? "null" : !obj.getClass().isArray() ? obj.toString() : obj instanceof boolean[] ? Arrays.toString((boolean[]) obj) : obj instanceof byte[] ? Arrays.toString((byte[]) obj) : obj instanceof char[] ? Arrays.toString((char[]) obj) : obj instanceof short[] ? Arrays.toString((short[]) obj) : obj instanceof int[] ? Arrays.toString((int[]) obj) : obj instanceof long[] ? Arrays.toString((long[]) obj) : obj instanceof float[] ? Arrays.toString((float[]) obj) : obj instanceof double[] ? Arrays.toString((double[]) obj) : obj instanceof Object[] ? Arrays.deepToString((Object[]) obj) : "Couldn't find a correct type for the object", new Object[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039 A[Catch: all -> 0x004b, TryCatch #0 {, blocks: (B:5:0x0005, B:8:0x0021, B:9:0x0025, B:12:0x002d, B:13:0x0033, B:15:0x0039, B:17:0x0045), top: B:24:0x0005 }] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m2146b(int r3, @android.support.annotation.Nullable java.lang.String r4, @android.support.annotation.Nullable java.lang.String r5, @android.support.annotation.Nullable java.lang.Throwable r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r6 == 0) goto L1d
            if (r5 == 0) goto L1d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4b
            r0.<init>()     // Catch: java.lang.Throwable -> L4b
            r0.append(r5)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r5 = " : "
            r0.append(r5)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r5 = p152s3.C1877h.m2150a(r6)     // Catch: java.lang.Throwable -> L4b
            r0.append(r5)     // Catch: java.lang.Throwable -> L4b
            java.lang.String r5 = r0.toString()     // Catch: java.lang.Throwable -> L4b
        L1d:
            if (r6 == 0) goto L25
            if (r5 != 0) goto L25
            java.lang.String r5 = p152s3.C1877h.m2150a(r6)     // Catch: java.lang.Throwable -> L4b
        L25:
            boolean r6 = p152s3.C1877h.m2151b(r5)     // Catch: java.lang.Throwable -> L4b
            if (r6 == 0) goto L2d
            java.lang.String r5 = "Empty/NULL log message"
        L2d:
            java.util.List<s3.c> r6 = r2.f5453b     // Catch: java.lang.Throwable -> L4b
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Throwable -> L4b
        L33:
            boolean r0 = r6.hasNext()     // Catch: java.lang.Throwable -> L4b
            if (r0 == 0) goto L49
            java.lang.Object r0 = r6.next()     // Catch: java.lang.Throwable -> L4b
            s3.c r0 = (p152s3.InterfaceC1872c) r0     // Catch: java.lang.Throwable -> L4b
            boolean r1 = r0.mo1453b(r3, r4)     // Catch: java.lang.Throwable -> L4b
            if (r1 == 0) goto L33
            r0.mo2143a(r3, r4, r5)     // Catch: java.lang.Throwable -> L4b
            goto L33
        L49:
            monitor-exit(r2)
            return
        L4b:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p152s3.C1875f.m2146b(int, java.lang.String, java.lang.String, java.lang.Throwable):void");
    }

    /* renamed from: c */
    public final synchronized void m2147c(int i7, @Nullable Throwable th, @NonNull String str, @Nullable Object... objArr) {
        Objects.requireNonNull(str);
        String str2 = this.f5452a.get();
        if (str2 != null) {
            this.f5452a.remove();
        } else {
            str2 = null;
        }
        if (objArr != null && objArr.length != 0) {
            str = String.format(str, objArr);
        }
        m2146b(i7, str2, str, th);
    }
}
