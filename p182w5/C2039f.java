package p182w5;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: URLResource.java */
/* renamed from: w5.f */
/* loaded from: classes.dex */
public class C2039f extends AbstractC2038e {

    /* renamed from: h */
    public static final InterfaceC2016c f5951h;

    /* renamed from: c */
    public URL f5952c;

    /* renamed from: d */
    public String f5953d;

    /* renamed from: e */
    public URLConnection f5954e;

    /* renamed from: f */
    public InputStream f5955f = null;

    /* renamed from: g */
    public transient boolean f5956g = AbstractC2038e.f5950b;

    static {
        Properties properties = C2015b.f5863a;
        f5951h = C2015b.m2349a(C2039f.class.getName());
    }

    public C2039f(URL url, URLConnection uRLConnection) {
        this.f5952c = url;
        this.f5953d = url.toString();
        this.f5954e = uRLConnection;
    }

    @Override // p182w5.AbstractC2038e
    /* renamed from: a */
    public boolean mo2392a() {
        try {
            synchronized (this) {
                if (mo2396g() && this.f5955f == null) {
                    this.f5955f = this.f5954e.getInputStream();
                }
            }
        } catch (IOException e7) {
            f5951h.mo2360k(e7);
        }
        return this.f5955f != null;
    }

    @Override // p182w5.AbstractC2038e
    /* renamed from: b */
    public synchronized InputStream mo2393b() {
        if (!mo2396g()) {
            throw new IOException("Invalid resource");
        }
        try {
            InputStream inputStream = this.f5955f;
            if (inputStream != null) {
                this.f5955f = null;
                return inputStream;
            }
            return this.f5954e.getInputStream();
        } finally {
            this.f5954e = null;
        }
    }

    @Override // p182w5.AbstractC2038e
    /* renamed from: c */
    public long mo2394c() {
        if (mo2396g()) {
            return this.f5954e.getLastModified();
        }
        return -1L;
    }

    public boolean equals(Object obj) {
        return (obj instanceof C2039f) && this.f5953d.equals(((C2039f) obj).f5953d);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0016 A[Catch: all -> 0x001a, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:6:0x0006, B:10:0x0010, B:11:0x0012, B:13:0x0016, B:9:0x000b), top: B:21:0x0001, inners: #0 }] */
    @Override // p182w5.AbstractC2038e
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void mo2395f() {
        /*
            r3 = this;
            monitor-enter(r3)
            java.io.InputStream r0 = r3.f5955f     // Catch: java.lang.Throwable -> L1a
            r1 = 0
            if (r0 == 0) goto L12
            r0.close()     // Catch: java.io.IOException -> La java.lang.Throwable -> L1a
            goto L10
        La:
            r0 = move-exception
            v5.c r2 = p182w5.C2039f.f5951h     // Catch: java.lang.Throwable -> L1a
            r2.mo2360k(r0)     // Catch: java.lang.Throwable -> L1a
        L10:
            r3.f5955f = r1     // Catch: java.lang.Throwable -> L1a
        L12:
            java.net.URLConnection r0 = r3.f5954e     // Catch: java.lang.Throwable -> L1a
            if (r0 == 0) goto L18
            r3.f5954e = r1     // Catch: java.lang.Throwable -> L1a
        L18:
            monitor-exit(r3)
            return
        L1a:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p182w5.C2039f.mo2395f():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001f  */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean mo2396g() {
        /*
            r2 = this;
            monitor-enter(r2)
            java.net.URLConnection r0 = r2.f5954e     // Catch: java.lang.Throwable -> L22
            if (r0 != 0) goto L19
            java.net.URL r0 = r2.f5952c     // Catch: java.io.IOException -> L13 java.lang.Throwable -> L22
            java.net.URLConnection r0 = r0.openConnection()     // Catch: java.io.IOException -> L13 java.lang.Throwable -> L22
            r2.f5954e = r0     // Catch: java.io.IOException -> L13 java.lang.Throwable -> L22
            boolean r1 = r2.f5956g     // Catch: java.io.IOException -> L13 java.lang.Throwable -> L22
            r0.setUseCaches(r1)     // Catch: java.io.IOException -> L13 java.lang.Throwable -> L22
            goto L19
        L13:
            r0 = move-exception
            v5.c r1 = p182w5.C2039f.f5951h     // Catch: java.lang.Throwable -> L22
            r1.mo2360k(r0)     // Catch: java.lang.Throwable -> L22
        L19:
            java.net.URLConnection r0 = r2.f5954e     // Catch: java.lang.Throwable -> L22
            if (r0 == 0) goto L1f
            r0 = 1
            goto L20
        L1f:
            r0 = 0
        L20:
            monitor-exit(r2)
            return r0
        L22:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p182w5.C2039f.mo2396g():boolean");
    }

    public int hashCode() {
        return this.f5953d.hashCode();
    }

    public String toString() {
        return this.f5953d;
    }
}
