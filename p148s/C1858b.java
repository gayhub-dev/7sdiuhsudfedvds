package p148s;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* compiled from: StrictLineReader.java */
/* renamed from: s.b */
/* loaded from: classes.dex */
public class C1858b implements Closeable {

    /* renamed from: e */
    public final InputStream f5427e;

    /* renamed from: f */
    public final Charset f5428f;

    /* renamed from: g */
    public byte[] f5429g;

    /* renamed from: h */
    public int f5430h;

    /* renamed from: i */
    public int f5431i;

    /* compiled from: StrictLineReader.java */
    /* renamed from: s.b$a */
    public class a extends ByteArrayOutputStream {
        public a(int i7) {
            super(i7);
        }

        @Override // java.io.ByteArrayOutputStream
        public String toString() {
            int i7 = ((ByteArrayOutputStream) this).count;
            if (i7 > 0 && ((ByteArrayOutputStream) this).buf[i7 - 1] == 13) {
                i7--;
            }
            try {
                return new String(((ByteArrayOutputStream) this).buf, 0, i7, C1858b.this.f5428f.name());
            } catch (UnsupportedEncodingException e7) {
                throw new AssertionError(e7);
            }
        }
    }

    public C1858b(InputStream inputStream, Charset charset) {
        if (charset == null) {
            throw null;
        }
        if (!charset.equals(C1859c.f5433a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f5427e = inputStream;
        this.f5428f = charset;
        this.f5429g = new byte[8192];
    }

    /* renamed from: a */
    public final void m2128a() throws IOException {
        InputStream inputStream = this.f5427e;
        byte[] bArr = this.f5429g;
        int i7 = inputStream.read(bArr, 0, bArr.length);
        if (i7 == -1) {
            throw new EOFException();
        }
        this.f5430h = 0;
        this.f5431i = i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m2129b() {
        /*
            r7 = this;
            java.io.InputStream r0 = r7.f5427e
            monitor-enter(r0)
            byte[] r1 = r7.f5429g     // Catch: java.lang.Throwable -> L87
            if (r1 == 0) goto L7f
            int r1 = r7.f5430h     // Catch: java.lang.Throwable -> L87
            int r2 = r7.f5431i     // Catch: java.lang.Throwable -> L87
            if (r1 < r2) goto L10
            r7.m2128a()     // Catch: java.lang.Throwable -> L87
        L10:
            int r1 = r7.f5430h     // Catch: java.lang.Throwable -> L87
        L12:
            int r2 = r7.f5431i     // Catch: java.lang.Throwable -> L87
            r3 = 10
            if (r1 == r2) goto L41
            byte[] r2 = r7.f5429g     // Catch: java.lang.Throwable -> L87
            r4 = r2[r1]     // Catch: java.lang.Throwable -> L87
            if (r4 != r3) goto L3e
            int r3 = r7.f5430h     // Catch: java.lang.Throwable -> L87
            if (r1 == r3) goto L2b
            int r4 = r1 + (-1)
            r5 = r2[r4]     // Catch: java.lang.Throwable -> L87
            r6 = 13
            if (r5 != r6) goto L2b
            goto L2c
        L2b:
            r4 = r1
        L2c:
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L87
            int r4 = r4 - r3
            java.nio.charset.Charset r6 = r7.f5428f     // Catch: java.lang.Throwable -> L87
            java.lang.String r6 = r6.name()     // Catch: java.lang.Throwable -> L87
            r5.<init>(r2, r3, r4, r6)     // Catch: java.lang.Throwable -> L87
            int r1 = r1 + 1
            r7.f5430h = r1     // Catch: java.lang.Throwable -> L87
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L87
            return r5
        L3e:
            int r1 = r1 + 1
            goto L12
        L41:
            s.b$a r1 = new s.b$a     // Catch: java.lang.Throwable -> L87
            int r2 = r7.f5431i     // Catch: java.lang.Throwable -> L87
            int r4 = r7.f5430h     // Catch: java.lang.Throwable -> L87
            int r2 = r2 - r4
            int r2 = r2 + 80
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L87
        L4d:
            byte[] r2 = r7.f5429g     // Catch: java.lang.Throwable -> L87
            int r4 = r7.f5430h     // Catch: java.lang.Throwable -> L87
            int r5 = r7.f5431i     // Catch: java.lang.Throwable -> L87
            int r5 = r5 - r4
            r1.write(r2, r4, r5)     // Catch: java.lang.Throwable -> L87
            r2 = -1
            r7.f5431i = r2     // Catch: java.lang.Throwable -> L87
            r7.m2128a()     // Catch: java.lang.Throwable -> L87
            int r2 = r7.f5430h     // Catch: java.lang.Throwable -> L87
        L5f:
            int r4 = r7.f5431i     // Catch: java.lang.Throwable -> L87
            if (r2 == r4) goto L4d
            byte[] r4 = r7.f5429g     // Catch: java.lang.Throwable -> L87
            r5 = r4[r2]     // Catch: java.lang.Throwable -> L87
            if (r5 != r3) goto L7c
            int r3 = r7.f5430h     // Catch: java.lang.Throwable -> L87
            if (r2 == r3) goto L72
            int r5 = r2 - r3
            r1.write(r4, r3, r5)     // Catch: java.lang.Throwable -> L87
        L72:
            int r2 = r2 + 1
            r7.f5430h = r2     // Catch: java.lang.Throwable -> L87
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L87
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L87
            return r1
        L7c:
            int r2 = r2 + 1
            goto L5f
        L7f:
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L87
            java.lang.String r2 = "LineReader is closed"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L87
            throw r1     // Catch: java.lang.Throwable -> L87
        L87:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L87
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p148s.C1858b.m2129b():java.lang.String");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.f5427e) {
            if (this.f5429g != null) {
                this.f5429g = null;
                this.f5427e.close();
            }
        }
    }
}
