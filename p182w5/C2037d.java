package p182w5;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Properties;
import p161t5.C1917i;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: JarResource.java */
/* renamed from: w5.d */
/* loaded from: classes.dex */
public class C2037d extends C2039f {

    /* renamed from: j */
    public static final InterfaceC2016c f5946j;

    /* renamed from: i */
    public JarURLConnection f5947i;

    /* compiled from: JarResource.java */
    /* renamed from: w5.d$a */
    public class a extends FilterInputStream {
        public a(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ((FilterInputStream) this).in = C1917i.f5646c;
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f5946j = C2015b.m2349a(C2037d.class.getName());
    }

    public C2037d(URL url, boolean z6) {
        super(url, null);
        this.f5956g = z6;
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: a */
    public boolean mo2392a() {
        return this.f5953d.endsWith("!/") ? mo2396g() : super.mo2392a();
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: b */
    public InputStream mo2393b() {
        mo2396g();
        if (!this.f5953d.endsWith("!/")) {
            return new a(super.mo2393b());
        }
        return new URL(this.f5953d.substring(4, r1.length() - 2)).openStream();
    }

    @Override // p182w5.C2039f, p182w5.AbstractC2038e
    /* renamed from: f */
    public synchronized void mo2395f() {
        this.f5947i = null;
        super.mo2395f();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001d  */
    @Override // p182w5.C2039f
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean mo2396g() {
        /*
            r2 = this;
            monitor-enter(r2)
            super.mo2396g()     // Catch: java.lang.Throwable -> L20
            java.net.JarURLConnection r0 = r2.f5947i     // Catch: java.io.IOException -> Le java.lang.Throwable -> L20
            java.net.URLConnection r1 = r2.f5954e     // Catch: java.io.IOException -> Le java.lang.Throwable -> L20
            if (r0 == r1) goto L17
            r2.mo2397h()     // Catch: java.io.IOException -> Le java.lang.Throwable -> L20
            goto L17
        Le:
            r0 = move-exception
            v5.c r1 = p182w5.C2037d.f5946j     // Catch: java.lang.Throwable -> L20
            r1.mo2360k(r0)     // Catch: java.lang.Throwable -> L20
            r0 = 0
            r2.f5947i = r0     // Catch: java.lang.Throwable -> L20
        L17:
            java.net.JarURLConnection r0 = r2.f5947i     // Catch: java.lang.Throwable -> L20
            if (r0 == 0) goto L1d
            r0 = 1
            goto L1e
        L1d:
            r0 = 0
        L1e:
            monitor-exit(r2)
            return r0
        L20:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p182w5.C2037d.mo2396g():boolean");
    }

    /* renamed from: h */
    public void mo2397h() {
        this.f5947i = (JarURLConnection) this.f5954e;
    }
}
