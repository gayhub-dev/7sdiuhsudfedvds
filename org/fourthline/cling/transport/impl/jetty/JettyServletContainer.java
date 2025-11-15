package org.fourthline.cling.transport.impl.jetty;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.transport.spi.ServletContainerAdapter;
import p006a5.InterfaceC0024j;
import p007a6.C0041a;
import p009b.C0413b;
import p015b5.InterfaceC0458c;
import p113n5.C1553n;
import p113n5.C1555p;
import p113n5.InterfaceC1545f;
import p121o5.C1591a;
import p154s5.C1887d;
import p154s5.C1889f;
import p161t5.C1918j;

/* loaded from: classes.dex */
public class JettyServletContainer implements ServletContainerAdapter {
    public C1555p server;
    private static final Logger log = Logger.getLogger(JettyServletContainer.class.getName());
    public static final JettyServletContainer INSTANCE = new JettyServletContainer();

    /* renamed from: org.fourthline.cling.transport.impl.jetty.JettyServletContainer$1 */
    public class C17191 extends C0041a {
        public C17191(ExecutorService executorService) {
            super(executorService);
        }

        @Override // p007a6.C0041a, p168u5.AbstractC1980a
        public void doStop() {
        }
    }

    private JettyServletContainer() {
        resetServer();
    }

    public static boolean isConnectionOpen(InterfaceC0458c interfaceC0458c) {
        return isConnectionOpen(interfaceC0458c, " ".getBytes());
    }

    @Override // org.fourthline.cling.transport.spi.ServletContainerAdapter
    public synchronized int addConnector(String str, int i7) {
        C1591a c1591a;
        c1591a = new C1591a();
        c1591a.f4521j = str;
        c1591a.f4522k = i7;
        c1591a.m1858O();
        C1555p c1555p = this.server;
        c1555p.m1803T((InterfaceC1545f[]) C1918j.m2223d(c1555p.f4673n, c1591a, InterfaceC1545f.class));
        if (this.server.isStarted()) {
            try {
                c1591a.start();
            } catch (Exception e7) {
                log.severe("Couldn't start connector: " + c1591a + " " + e7);
                throw new RuntimeException(e7);
            }
        }
        return c1591a.f4842D;
    }

    @Override // org.fourthline.cling.transport.spi.ServletContainerAdapter
    public synchronized void registerServlet(String str, InterfaceC0024j interfaceC0024j) {
        if (this.server.f4960j != null) {
            return;
        }
        log.info("Registering UPnP servlet under context path: " + str);
        C1887d c1887d = new C1887d(null, null, null, null, null, null);
        c1887d.f5502P = 0;
        if (str != null && str.length() > 0) {
            c1887d.m1901Y(str);
        }
        c1887d.m2154b0(new C1889f(interfaceC0024j), "/*");
        this.server.m1910P(c1887d);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x001f, code lost:
    
        if (r3.isStarted() != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0025, code lost:
    
        if (r3.isStarting() == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0027, code lost:
    
        r3.stop();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x002a, code lost:
    
        r5.server.m1802S(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0031, code lost:
    
        if (r0.length != 1) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0033, code lost:
    
        org.fourthline.cling.transport.impl.jetty.JettyServletContainer.log.info("No more connectors, stopping Jetty server");
        stopIfRunning();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x003e, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x003f, code lost:
    
        org.fourthline.cling.transport.impl.jetty.JettyServletContainer.log.severe("Couldn't stop connector: " + r3 + " " + r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0062, code lost:
    
        throw new java.lang.RuntimeException(r6);
     */
    @Override // org.fourthline.cling.transport.spi.ServletContainerAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void removeConnector(java.lang.String r6, int r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            n5.p r0 = r5.server     // Catch: java.lang.Throwable -> L68
            n5.f[] r0 = r0.f4673n     // Catch: java.lang.Throwable -> L68
            int r1 = r0.length     // Catch: java.lang.Throwable -> L68
            r2 = 0
        L7:
            if (r2 >= r1) goto L66
            r3 = r0[r2]     // Catch: java.lang.Throwable -> L68
            java.lang.String r4 = r3.mo1732s()     // Catch: java.lang.Throwable -> L68
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Throwable -> L68
            if (r4 == 0) goto L63
            int r4 = r3.mo1768f()     // Catch: java.lang.Throwable -> L68
            if (r4 != r7) goto L63
            boolean r6 = r3.isStarted()     // Catch: java.lang.Throwable -> L68
            if (r6 != 0) goto L27
            boolean r6 = r3.isStarting()     // Catch: java.lang.Throwable -> L68
            if (r6 == 0) goto L2a
        L27:
            r3.stop()     // Catch: java.lang.Exception -> L3e java.lang.Throwable -> L68
        L2a:
            n5.p r6 = r5.server     // Catch: java.lang.Throwable -> L68
            r6.m1802S(r3)     // Catch: java.lang.Throwable -> L68
            int r6 = r0.length     // Catch: java.lang.Throwable -> L68
            r7 = 1
            if (r6 != r7) goto L66
            java.util.logging.Logger r6 = org.fourthline.cling.transport.impl.jetty.JettyServletContainer.log     // Catch: java.lang.Throwable -> L68
            java.lang.String r7 = "No more connectors, stopping Jetty server"
            r6.info(r7)     // Catch: java.lang.Throwable -> L68
            r5.stopIfRunning()     // Catch: java.lang.Throwable -> L68
            goto L66
        L3e:
            r6 = move-exception
            java.util.logging.Logger r7 = org.fourthline.cling.transport.impl.jetty.JettyServletContainer.log     // Catch: java.lang.Throwable -> L68
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L68
            r0.<init>()     // Catch: java.lang.Throwable -> L68
            java.lang.String r1 = "Couldn't stop connector: "
            r0.append(r1)     // Catch: java.lang.Throwable -> L68
            r0.append(r3)     // Catch: java.lang.Throwable -> L68
            java.lang.String r1 = " "
            r0.append(r1)     // Catch: java.lang.Throwable -> L68
            r0.append(r6)     // Catch: java.lang.Throwable -> L68
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L68
            r7.severe(r0)     // Catch: java.lang.Throwable -> L68
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L68
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L68
            throw r7     // Catch: java.lang.Throwable -> L68
        L63:
            int r2 = r2 + 1
            goto L7
        L66:
            monitor-exit(r5)
            return
        L68:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fourthline.cling.transport.impl.jetty.JettyServletContainer.removeConnector(java.lang.String, int):void");
    }

    public void resetServer() {
        C1555p c1555p = new C1555p();
        this.server = c1555p;
        c1555p.f4676q = 1000;
    }

    @Override // org.fourthline.cling.transport.spi.ServletContainerAdapter
    public synchronized void setExecutorService(ExecutorService executorService) {
        C1555p c1555p = INSTANCE.server;
        if (c1555p.f4672m == null) {
            c1555p.m1804U(new C0041a(executorService) { // from class: org.fourthline.cling.transport.impl.jetty.JettyServletContainer.1
                public C17191(ExecutorService executorService2) {
                    super(executorService2);
                }

                @Override // p007a6.C0041a, p168u5.AbstractC1980a
                public void doStop() {
                }
            });
        }
    }

    @Override // org.fourthline.cling.transport.spi.ServletContainerAdapter
    public synchronized void startIfNotRunning() {
        if (!this.server.isStarted() && !this.server.isStarting()) {
            log.info("Starting Jetty server... ");
            try {
                this.server.start();
            } catch (Exception e7) {
                log.severe("Couldn't start Jetty server: " + e7);
                throw new RuntimeException(e7);
            }
        }
    }

    @Override // org.fourthline.cling.transport.spi.ServletContainerAdapter
    public synchronized void stopIfRunning() {
        if (!this.server.isStopped() && !this.server.isStopping()) {
            log.info("Stopping Jetty server...");
            try {
                try {
                    this.server.stop();
                } catch (Exception e7) {
                    log.severe("Couldn't stop Jetty server: " + e7);
                    throw new RuntimeException(e7);
                }
            } finally {
                resetServer();
            }
        }
    }

    public static boolean isConnectionOpen(InterfaceC0458c interfaceC0458c, byte[] bArr) throws IOException {
        Socket socket = (Socket) ((C1553n) interfaceC0458c).f4638g.f2538a.mo919l();
        Logger logger = log;
        if (logger.isLoggable(Level.FINE)) {
            StringBuilder sbM112a = C0413b.m112a("Checking if client connection is still open: ");
            sbM112a.append(socket.getRemoteSocketAddress());
            logger.fine(sbM112a.toString());
        }
        try {
            socket.getOutputStream().write(bArr);
            socket.getOutputStream().flush();
            return true;
        } catch (IOException unused) {
            Logger logger2 = log;
            if (!logger2.isLoggable(Level.FINE)) {
                return false;
            }
            StringBuilder sbM112a2 = C0413b.m112a("Client connection has been closed: ");
            sbM112a2.append(socket.getRemoteSocketAddress());
            logger2.fine(sbM112a2.toString());
            return false;
        }
    }
}
