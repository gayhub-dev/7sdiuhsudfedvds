package p140q6;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/* compiled from: HttpConnection.java */
/* renamed from: q6.j */
/* loaded from: classes.dex */
public class C1789j {

    /* renamed from: a */
    public C1790k f5102a;

    /* renamed from: b */
    public C1798s f5103b;

    /* renamed from: c */
    public InputStream f5104c;

    /* renamed from: d */
    public InputStream f5105d;

    /* renamed from: e */
    public OutputStream f5106e;

    /* renamed from: f */
    public SocketChannel f5107f;

    /* renamed from: g */
    public SelectionKey f5108g;

    /* renamed from: h */
    public long f5109h;

    /* renamed from: i */
    public boolean f5110i = false;

    /* renamed from: j */
    public Logger f5111j;

    /* JADX WARN: Removed duplicated region for block: B:34:0x005a A[Catch: IOException -> 0x005e, all -> 0x0087, TRY_LEAVE, TryCatch #5 {IOException -> 0x005e, blocks: (B:32:0x0056, B:34:0x005a), top: B:62:0x0056, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0066 A[Catch: IOException -> 0x0077, all -> 0x0087, TRY_LEAVE, TryCatch #0 {IOException -> 0x0077, blocks: (B:38:0x0062, B:40:0x0066), top: B:53:0x0062, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m1983a() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.f5110i     // Catch: java.lang.Throwable -> L87
            if (r0 == 0) goto L7
            monitor-exit(r3)
            return
        L7:
            r0 = 1
            r3.f5110i = r0     // Catch: java.lang.Throwable -> L87
            java.util.logging.Logger r0 = r3.f5111j     // Catch: java.lang.Throwable -> L87
            if (r0 == 0) goto L2c
            java.nio.channels.SocketChannel r1 = r3.f5107f     // Catch: java.lang.Throwable -> L87
            if (r1 == 0) goto L2c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87
            r1.<init>()     // Catch: java.lang.Throwable -> L87
            java.lang.String r2 = "Closing connection: "
            r1.append(r2)     // Catch: java.lang.Throwable -> L87
            java.nio.channels.SocketChannel r2 = r3.f5107f     // Catch: java.lang.Throwable -> L87
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L87
            r1.append(r2)     // Catch: java.lang.Throwable -> L87
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L87
            r0.finest(r1)     // Catch: java.lang.Throwable -> L87
        L2c:
            java.nio.channels.SocketChannel r0 = r3.f5107f     // Catch: java.lang.Throwable -> L87
            boolean r0 = r0.isOpen()     // Catch: java.lang.Throwable -> L87
            if (r0 != 0) goto L4a
            java.lang.String r0 = "Channel already closed"
            int r1 = p140q6.C1801v.f5172v     // Catch: java.lang.Throwable -> L87
            java.lang.Class<q6.v> r1 = p140q6.C1801v.class
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L87
            boolean r2 = p140q6.C1801v.f5175y     // Catch: java.lang.Throwable -> L47
            if (r2 == 0) goto L44
            java.io.PrintStream r2 = java.lang.System.out     // Catch: java.lang.Throwable -> L47
            r2.println(r0)     // Catch: java.lang.Throwable -> L47
        L44:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L87
            monitor-exit(r3)
            return
        L47:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L87
            throw r0     // Catch: java.lang.Throwable -> L87
        L4a:
            java.io.InputStream r0 = r3.f5105d     // Catch: java.io.IOException -> L52 java.lang.Throwable -> L87
            if (r0 == 0) goto L56
            r0.close()     // Catch: java.io.IOException -> L52 java.lang.Throwable -> L87
            goto L56
        L52:
            r0 = move-exception
            p140q6.C1801v.m2006b(r0)     // Catch: java.lang.Throwable -> L87
        L56:
            java.io.OutputStream r0 = r3.f5106e     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L87
            if (r0 == 0) goto L62
            r0.close()     // Catch: java.io.IOException -> L5e java.lang.Throwable -> L87
            goto L62
        L5e:
            r0 = move-exception
            p140q6.C1801v.m2006b(r0)     // Catch: java.lang.Throwable -> L87
        L62:
            q6.s r0 = r3.f5103b     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L87
            if (r0 == 0) goto L7b
            q6.s$a r0 = r0.f5154a     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L87
            q6.t r1 = r0.f5155a     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L87
            java.nio.channels.Selector r2 = r0.f5156b     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L87
            r1.m2004a(r2)     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L87
            q6.t r1 = r0.f5155a     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L87
            java.nio.channels.Selector r0 = r0.f5157c     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L87
            r1.m2004a(r0)     // Catch: java.io.IOException -> L77 java.lang.Throwable -> L87
            goto L7b
        L77:
            r0 = move-exception
            p140q6.C1801v.m2006b(r0)     // Catch: java.lang.Throwable -> L87
        L7b:
            java.nio.channels.SocketChannel r0 = r3.f5107f     // Catch: java.io.IOException -> L81 java.lang.Throwable -> L87
            r0.close()     // Catch: java.io.IOException -> L81 java.lang.Throwable -> L87
            goto L85
        L81:
            r0 = move-exception
            p140q6.C1801v.m2006b(r0)     // Catch: java.lang.Throwable -> L87
        L85:
            monitor-exit(r3)
            return
        L87:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p140q6.C1789j.m1983a():void");
    }

    /* renamed from: b */
    public void m1984b(InputStream inputStream, OutputStream outputStream, SocketChannel socketChannel, SSLEngine sSLEngine, C1798s c1798s, SSLContext sSLContext, String str, C1790k c1790k, InputStream inputStream2) {
        this.f5102a = c1790k;
        this.f5104c = inputStream;
        this.f5106e = outputStream;
        this.f5105d = inputStream2;
        this.f5107f = socketChannel;
        this.f5103b = null;
        this.f5111j = c1790k.f5115d.f5194s;
    }

    public String toString() {
        SocketChannel socketChannel = this.f5107f;
        if (socketChannel != null) {
            return socketChannel.toString();
        }
        return null;
    }
}
