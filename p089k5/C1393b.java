package p089k5;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.Properties;
import p073i5.InterfaceC1152e;
import p073i5.InterfaceC1161n;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: ChannelEndPoint.java */
/* renamed from: k5.b */
/* loaded from: classes.dex */
public class C1393b implements InterfaceC1161n {

    /* renamed from: m */
    public static final InterfaceC2016c f4048m;

    /* renamed from: e */
    public final ByteChannel f4049e;

    /* renamed from: f */
    public final ByteBuffer[] f4050f = new ByteBuffer[2];

    /* renamed from: g */
    public final Socket f4051g;

    /* renamed from: h */
    public final InetSocketAddress f4052h;

    /* renamed from: i */
    public final InetSocketAddress f4053i;

    /* renamed from: j */
    public volatile int f4054j;

    /* renamed from: k */
    public volatile boolean f4055k;

    /* renamed from: l */
    public volatile boolean f4056l;

    static {
        Properties properties = C2015b.f5863a;
        f4048m = C2015b.m2349a(C1393b.class.getName());
    }

    public C1393b(ByteChannel byteChannel, int i7) throws SocketException {
        this.f4049e = byteChannel;
        this.f4054j = i7;
        Socket socket = byteChannel instanceof SocketChannel ? ((SocketChannel) byteChannel).socket() : null;
        this.f4051g = socket;
        if (socket == null) {
            this.f4053i = null;
            this.f4052h = null;
        } else {
            this.f4052h = (InetSocketAddress) socket.getLocalSocketAddress();
            this.f4053i = (InetSocketAddress) socket.getRemoteSocketAddress();
            socket.setSoTimeout(this.f4054j);
        }
    }

    @Override // p073i5.InterfaceC1161n
    public void close() {
        f4048m.mo2351a("close {}", this);
        this.f4049e.close();
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: e */
    public String mo912e() {
        InetSocketAddress inetSocketAddress;
        if (this.f4051g == null || (inetSocketAddress = this.f4053i) == null) {
            return null;
        }
        return inetSocketAddress.getAddress().getHostAddress();
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: f */
    public int mo913f() {
        if (this.f4051g == null) {
            return 0;
        }
        InetSocketAddress inetSocketAddress = this.f4052h;
        if (inetSocketAddress == null) {
            return -1;
        }
        return inetSocketAddress.getPort();
    }

    @Override // p073i5.InterfaceC1161n
    public void flush() {
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: g */
    public String mo914g() {
        if (this.f4051g == null) {
            return null;
        }
        InetSocketAddress inetSocketAddress = this.f4052h;
        return (inetSocketAddress == null || inetSocketAddress.getAddress() == null || this.f4052h.getAddress().isAnyLocalAddress()) ? "0.0.0.0" : this.f4052h.getAddress().getHostAddress();
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: i */
    public int mo916i() {
        return this.f4054j;
    }

    @Override // p073i5.InterfaceC1161n
    public boolean isOpen() {
        return this.f4049e.isOpen();
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: j */
    public int mo917j(InterfaceC1152e interfaceC1152e, InterfaceC1152e interfaceC1152e2, InterfaceC1152e interfaceC1152e3) throws IOException {
        int iWrite;
        InterfaceC1152e interfaceC1152eBuffer = interfaceC1152e == null ? null : interfaceC1152e.buffer();
        InterfaceC1152e interfaceC1152eBuffer2 = interfaceC1152e2 != null ? interfaceC1152e2.buffer() : null;
        int iMo931x = 0;
        if (!(this.f4049e instanceof GatheringByteChannel) || interfaceC1152e == null || interfaceC1152e.length() == 0 || !(interfaceC1152eBuffer instanceof InterfaceC1396e) || interfaceC1152e2 == null || interfaceC1152e2.length() == 0 || !(interfaceC1152eBuffer2 instanceof InterfaceC1396e)) {
            if (interfaceC1152e != null && interfaceC1152e.length() > 0) {
                iMo931x = mo931x(interfaceC1152e);
            }
            int iMo931x2 = ((interfaceC1152e == null || interfaceC1152e.length() == 0) && interfaceC1152e2 != null && interfaceC1152e2.length() > 0) ? mo931x(interfaceC1152e2) + iMo931x : iMo931x;
            return ((interfaceC1152e == null || interfaceC1152e.length() == 0) && (interfaceC1152e2 == null || interfaceC1152e2.length() == 0) && interfaceC1152e3 != null && interfaceC1152e3.length() > 0) ? mo931x(interfaceC1152e3) + iMo931x2 : iMo931x2;
        }
        ByteBuffer byteBufferMo1563o = ((InterfaceC1396e) interfaceC1152eBuffer).mo1563o();
        ByteBuffer byteBufferMo1563o2 = ((InterfaceC1396e) interfaceC1152eBuffer2).mo1563o();
        synchronized (this) {
            ByteBuffer byteBufferAsReadOnlyBuffer = byteBufferMo1563o.asReadOnlyBuffer();
            byteBufferAsReadOnlyBuffer.position(interfaceC1152e.mo1316C());
            byteBufferAsReadOnlyBuffer.limit(interfaceC1152e.mo1322M());
            ByteBuffer byteBufferAsReadOnlyBuffer2 = byteBufferMo1563o2.asReadOnlyBuffer();
            byteBufferAsReadOnlyBuffer2.position(interfaceC1152e2.mo1316C());
            byteBufferAsReadOnlyBuffer2.limit(interfaceC1152e2.mo1322M());
            ByteBuffer[] byteBufferArr = this.f4050f;
            byteBufferArr[0] = byteBufferAsReadOnlyBuffer;
            byteBufferArr[1] = byteBufferAsReadOnlyBuffer2;
            iWrite = (int) ((GatheringByteChannel) this.f4049e).write(byteBufferArr);
            int length = interfaceC1152e.length();
            if (iWrite > length) {
                interfaceC1152e.clear();
                interfaceC1152e2.mo1330h(iWrite - length);
            } else if (iWrite > 0) {
                interfaceC1152e.mo1330h(iWrite);
            }
        }
        return iWrite;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: k */
    public void mo918k(int i7) throws SocketException {
        if (this.f4051g != null && i7 != this.f4054j) {
            this.f4051g.setSoTimeout(i7 > 0 ? i7 : 0);
        }
        this.f4054j = i7;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: l */
    public Object mo919l() {
        return this.f4049e;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: m */
    public void mo920m() {
        Socket socket;
        f4048m.mo2351a("ishut {}", this);
        this.f4055k = true;
        if (!this.f4049e.isOpen() || (socket = this.f4051g) == null) {
            return;
        }
        try {
            try {
                if (!socket.isInputShutdown()) {
                    this.f4051g.shutdownInput();
                }
                if (!this.f4056l) {
                    return;
                }
            } catch (SocketException e7) {
                InterfaceC2016c interfaceC2016c = f4048m;
                interfaceC2016c.mo2351a(e7.toString(), new Object[0]);
                interfaceC2016c.mo2360k(e7);
                if (!this.f4056l) {
                    return;
                }
            }
            close();
        } catch (Throwable th) {
            if (this.f4056l) {
                close();
            }
            throw th;
        }
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: n */
    public String mo921n() {
        if (this.f4051g == null) {
            return null;
        }
        InetSocketAddress inetSocketAddress = this.f4052h;
        return (inetSocketAddress == null || inetSocketAddress.getAddress() == null || this.f4052h.getAddress().isAnyLocalAddress()) ? "0.0.0.0" : this.f4052h.getAddress().getCanonicalHostName();
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: o */
    public boolean mo922o(long j7) {
        return true;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: p */
    public boolean mo923p() {
        Closeable closeable = this.f4049e;
        return !(closeable instanceof SelectableChannel) || ((SelectableChannel) closeable).isBlocking();
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: q */
    public int mo924q() {
        if (this.f4051g == null) {
            return 0;
        }
        InetSocketAddress inetSocketAddress = this.f4053i;
        if (inetSocketAddress == null) {
            return -1;
        }
        return inetSocketAddress.getPort();
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: r */
    public boolean mo925r() {
        Socket socket;
        return this.f4056l || !this.f4049e.isOpen() || ((socket = this.f4051g) != null && socket.isOutputShutdown());
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: s */
    public boolean mo926s() {
        Socket socket;
        return this.f4055k || !this.f4049e.isOpen() || ((socket = this.f4051g) != null && socket.isInputShutdown());
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: t */
    public void mo927t() {
        Socket socket;
        f4048m.mo2351a("oshut {}", this);
        this.f4056l = true;
        if (!this.f4049e.isOpen() || (socket = this.f4051g) == null) {
            return;
        }
        try {
            try {
                if (!socket.isOutputShutdown()) {
                    this.f4051g.shutdownOutput();
                }
                if (!this.f4055k) {
                    return;
                }
            } catch (SocketException e7) {
                InterfaceC2016c interfaceC2016c = f4048m;
                interfaceC2016c.mo2351a(e7.toString(), new Object[0]);
                interfaceC2016c.mo2360k(e7);
                if (!this.f4055k) {
                    return;
                }
            }
            close();
        } catch (Throwable th) {
            if (this.f4055k) {
                close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        if (r3 >= 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
    
        if (isOpen() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
    
        if (mo926s() != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003c, code lost:
    
        mo920m();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
    
        if (mo925r() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
    
        r5.f4049e.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
    
        r6 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
    
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0063, code lost:
    
        p089k5.C1393b.f4048m.mo2355f("Exception while filling", r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0070, code lost:
    
        if (r5.f4049e.isOpen() != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0072, code lost:
    
        r5.f4049e.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0078, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0079, code lost:
    
        p089k5.C1393b.f4048m.mo2360k(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007e, code lost:
    
        if (r2 <= 0) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0080, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0081, code lost:
    
        throw r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:?, code lost:
    
        return -1;
     */
    @Override // p073i5.InterfaceC1161n
    /* renamed from: v */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int mo929v(p073i5.InterfaceC1152e r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r5.f4055k
            r1 = -1
            if (r0 == 0) goto L6
            return r1
        L6:
            i5.e r0 = r6.buffer()
            boolean r2 = r0 instanceof p089k5.InterfaceC1396e
            if (r2 == 0) goto L82
            k5.e r0 = (p089k5.InterfaceC1396e) r0
            java.nio.ByteBuffer r0 = r0.mo1563o()
            r2 = 0
            monitor-enter(r0)     // Catch: java.io.IOException -> L62
            int r3 = r6.mo1322M()     // Catch: java.lang.Throwable -> L53
            r0.position(r3)     // Catch: java.lang.Throwable -> L53
            java.nio.channels.ByteChannel r3 = r5.f4049e     // Catch: java.lang.Throwable -> L53
            int r3 = r3.read(r0)     // Catch: java.lang.Throwable -> L53
            int r4 = r0.position()     // Catch: java.lang.Throwable -> L50
            r6.mo1324Q(r4)     // Catch: java.lang.Throwable -> L50
            r0.position(r2)     // Catch: java.lang.Throwable -> L50
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L50
            if (r3 >= 0) goto L4e
            boolean r6 = r5.isOpen()     // Catch: java.io.IOException -> L4b
            if (r6 == 0) goto L4e
            boolean r6 = r5.mo926s()     // Catch: java.io.IOException -> L4b
            if (r6 != 0) goto L3f
            r5.mo920m()     // Catch: java.io.IOException -> L4b
        L3f:
            boolean r6 = r5.mo925r()     // Catch: java.io.IOException -> L4b
            if (r6 == 0) goto L4e
            java.nio.channels.ByteChannel r6 = r5.f4049e     // Catch: java.io.IOException -> L4b
            r6.close()     // Catch: java.io.IOException -> L4b
            goto L4e
        L4b:
            r6 = move-exception
            r2 = r3
            goto L63
        L4e:
            r1 = r3
            goto L80
        L50:
            r6 = move-exception
            r2 = r3
            goto L60
        L53:
            r3 = move-exception
            int r4 = r0.position()     // Catch: java.lang.Throwable -> L5f
            r6.mo1324Q(r4)     // Catch: java.lang.Throwable -> L5f
            r0.position(r2)     // Catch: java.lang.Throwable -> L5f
            throw r3     // Catch: java.lang.Throwable -> L5f
        L5f:
            r6 = move-exception
        L60:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5f
            throw r6     // Catch: java.io.IOException -> L62
        L62:
            r6 = move-exception
        L63:
            v5.c r0 = p089k5.C1393b.f4048m
            java.lang.String r3 = "Exception while filling"
            r0.mo2355f(r3, r6)
            java.nio.channels.ByteChannel r0 = r5.f4049e     // Catch: java.lang.Exception -> L78
            boolean r0 = r0.isOpen()     // Catch: java.lang.Exception -> L78
            if (r0 == 0) goto L7e
            java.nio.channels.ByteChannel r0 = r5.f4049e     // Catch: java.lang.Exception -> L78
            r0.close()     // Catch: java.lang.Exception -> L78
            goto L7e
        L78:
            r0 = move-exception
            v5.c r3 = p089k5.C1393b.f4048m
            r3.mo2360k(r0)
        L7e:
            if (r2 > 0) goto L81
        L80:
            return r1
        L81:
            throw r6
        L82:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r0 = "Not Implemented"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p089k5.C1393b.mo929v(i5.e):int");
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: w */
    public boolean mo930w(long j7) {
        return true;
    }

    @Override // p073i5.InterfaceC1161n
    /* renamed from: x */
    public int mo931x(InterfaceC1152e interfaceC1152e) throws IOException {
        int iWrite;
        InterfaceC1152e interfaceC1152eBuffer = interfaceC1152e.buffer();
        if (interfaceC1152eBuffer instanceof InterfaceC1396e) {
            ByteBuffer byteBufferAsReadOnlyBuffer = ((InterfaceC1396e) interfaceC1152eBuffer).mo1563o().asReadOnlyBuffer();
            byteBufferAsReadOnlyBuffer.position(interfaceC1152e.mo1316C());
            byteBufferAsReadOnlyBuffer.limit(interfaceC1152e.mo1322M());
            iWrite = this.f4049e.write(byteBufferAsReadOnlyBuffer);
            if (iWrite > 0) {
                interfaceC1152e.mo1330h(iWrite);
            }
        } else {
            if (interfaceC1152eBuffer instanceof C1397f) {
                interfaceC1152e.mo1316C();
                interfaceC1152e.length();
                Objects.requireNonNull((C1397f) interfaceC1152eBuffer);
                throw null;
            }
            if (interfaceC1152e.mo1349P() == null) {
                throw new IOException("Not Implemented");
            }
            iWrite = this.f4049e.write(ByteBuffer.wrap(interfaceC1152e.mo1349P(), interfaceC1152e.mo1316C(), interfaceC1152e.length()));
            if (iWrite > 0) {
                interfaceC1152e.mo1330h(iWrite);
            }
        }
        return iWrite;
    }
}
