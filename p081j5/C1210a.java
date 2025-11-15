package p081j5;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;
import javax.net.ssl.SSLSocket;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: SocketEndPoint.java */
/* renamed from: j5.a */
/* loaded from: classes.dex */
public class C1210a extends C1211b {

    /* renamed from: m */
    public static final InterfaceC2016c f2726m;

    /* renamed from: j */
    public final Socket f2727j;

    /* renamed from: k */
    public final InetSocketAddress f2728k;

    /* renamed from: l */
    public final InetSocketAddress f2729l;

    static {
        Properties properties = C2015b.f5863a;
        f2726m = C2015b.m2349a(C1210a.class.getName());
    }

    public C1210a(Socket socket) {
        super(socket.getInputStream(), socket.getOutputStream());
        this.f2727j = socket;
        this.f2728k = (InetSocketAddress) socket.getLocalSocketAddress();
        this.f2729l = (InetSocketAddress) socket.getRemoteSocketAddress();
        this.f2732g = socket.getSoTimeout();
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    public void close() throws IOException {
        this.f2727j.close();
        this.f2730e = null;
        this.f2731f = null;
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: e */
    public String mo912e() {
        InetAddress address;
        InetSocketAddress inetSocketAddress = this.f2729l;
        if (inetSocketAddress == null || (address = inetSocketAddress.getAddress()) == null) {
            return null;
        }
        return address.getHostAddress();
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: f */
    public int mo913f() {
        InetSocketAddress inetSocketAddress = this.f2728k;
        if (inetSocketAddress == null) {
            return -1;
        }
        return inetSocketAddress.getPort();
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: g */
    public String mo914g() {
        InetSocketAddress inetSocketAddress = this.f2728k;
        return (inetSocketAddress == null || inetSocketAddress.getAddress() == null || this.f2728k.getAddress().isAnyLocalAddress()) ? "0.0.0.0" : this.f2728k.getAddress().getHostAddress();
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    public boolean isOpen() {
        Socket socket;
        return (!super.isOpen() || (socket = this.f2727j) == null || socket.isClosed()) ? false : true;
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: k */
    public void mo918k(int i7) throws SocketException {
        if (i7 != this.f2732g) {
            this.f2727j.setSoTimeout(i7 > 0 ? i7 : 0);
        }
        this.f2732g = i7;
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: l */
    public Object mo919l() {
        return this.f2727j;
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: m */
    public void mo920m() throws IOException {
        Socket socket = this.f2727j;
        if (socket instanceof SSLSocket) {
            super.mo920m();
            return;
        }
        if (socket.isClosed()) {
            return;
        }
        if (!this.f2727j.isInputShutdown()) {
            this.f2727j.shutdownInput();
        }
        if (this.f2727j.isOutputShutdown()) {
            this.f2727j.close();
        }
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: n */
    public String mo921n() {
        InetSocketAddress inetSocketAddress = this.f2728k;
        return (inetSocketAddress == null || inetSocketAddress.getAddress() == null || this.f2728k.getAddress().isAnyLocalAddress()) ? "0.0.0.0" : this.f2728k.getAddress().getCanonicalHostName();
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: r */
    public boolean mo925r() {
        Socket socket = this.f2727j;
        return socket instanceof SSLSocket ? this.f2734i : socket.isClosed() || this.f2727j.isOutputShutdown();
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: s */
    public boolean mo926s() {
        Socket socket = this.f2727j;
        return socket instanceof SSLSocket ? this.f2733h : socket.isClosed() || this.f2727j.isInputShutdown();
    }

    @Override // p081j5.C1211b, p073i5.InterfaceC1161n
    /* renamed from: t */
    public void mo927t() throws IOException {
        Socket socket = this.f2727j;
        if (socket instanceof SSLSocket) {
            super.mo927t();
            return;
        }
        if (socket.isClosed()) {
            return;
        }
        if (!this.f2727j.isOutputShutdown()) {
            this.f2727j.shutdownOutput();
        }
        if (this.f2727j.isInputShutdown()) {
            this.f2727j.close();
        }
    }

    public String toString() {
        return this.f2728k + " <--> " + this.f2729l;
    }

    @Override // p081j5.C1211b
    /* renamed from: z */
    public void mo1438z() throws IOException {
        try {
            if (mo926s()) {
                return;
            }
            mo920m();
        } catch (IOException e7) {
            f2726m.mo2360k(e7);
            this.f2727j.close();
        }
    }

    public C1210a(Socket socket, int i7) throws SocketException {
        super(socket.getInputStream(), socket.getOutputStream());
        this.f2727j = socket;
        this.f2728k = (InetSocketAddress) socket.getLocalSocketAddress();
        this.f2729l = (InetSocketAddress) socket.getRemoteSocketAddress();
        socket.setSoTimeout(i7 > 0 ? i7 : 0);
        this.f2732g = i7;
    }
}
