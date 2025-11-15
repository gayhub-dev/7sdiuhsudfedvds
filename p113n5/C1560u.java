package p113n5;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Properties;
import p007a6.C0043c;
import p009b.C0413b;

/* compiled from: ShutdownMonitor.java */
/* renamed from: n5.u */
/* loaded from: classes.dex */
public class C1560u {

    /* renamed from: a */
    public boolean f4677a;

    /* renamed from: b */
    public int f4678b;

    /* renamed from: c */
    public String f4679c;

    /* renamed from: d */
    public boolean f4680d;

    /* renamed from: e */
    public ServerSocket f4681e;

    /* renamed from: f */
    public c f4682f;

    /* compiled from: ShutdownMonitor.java */
    /* renamed from: n5.u$b */
    public static class b {

        /* renamed from: a */
        public static C1560u f4683a = new C1560u(null);
    }

    /* compiled from: ShutdownMonitor.java */
    /* renamed from: n5.u$c */
    public class c extends Thread {
        public c() {
            setDaemon(true);
            setName("ShutdownMonitor");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws Throwable {
            Exception e7;
            Socket socketAccept;
            LineNumberReader lineNumberReader;
            if (C1560u.this.f4681e == null) {
                return;
            }
            while (true) {
                ServerSocket serverSocket = C1560u.this.f4681e;
                if (serverSocket == null) {
                    return;
                }
                Socket socket = null;
                try {
                    socketAccept = serverSocket.accept();
                } catch (Exception e8) {
                    e7 = e8;
                    socketAccept = null;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    try {
                        lineNumberReader = new LineNumberReader(new InputStreamReader(socketAccept.getInputStream()));
                    } catch (Exception e9) {
                        e7 = e9;
                        if (C1560u.this.f4677a) {
                            e7.printStackTrace(System.err);
                        }
                        System.err.println(e7.toString());
                        C1560u.m1807b(C1560u.this, socketAccept);
                    }
                    if (C1560u.this.f4679c.equals(lineNumberReader.readLine())) {
                        OutputStream outputStream = socketAccept.getOutputStream();
                        String line = lineNumberReader.readLine();
                        C1560u.m1806a(C1560u.this, "command=%s", new Object[]{line});
                        if ("stop".equals(line)) {
                            C1560u.m1806a(C1560u.this, "Issuing graceful shutdown..", new Object[0]);
                            C0043c.f56g.run();
                            C1560u.m1806a(C1560u.this, "Informing client that we are stopped.", new Object[0]);
                            outputStream.write("Stopped\r\n".getBytes("UTF-8"));
                            outputStream.flush();
                            C1560u.m1806a(C1560u.this, "Shutting down monitor", new Object[0]);
                            C1560u.m1807b(C1560u.this, socketAccept);
                            ServerSocket serverSocket2 = C1560u.this.f4681e;
                            if (serverSocket2 != null) {
                                try {
                                    serverSocket2.close();
                                } catch (IOException unused) {
                                }
                            }
                            C1560u c1560u = C1560u.this;
                            c1560u.f4681e = null;
                            if (c1560u.f4680d) {
                                C1560u.m1806a(c1560u, "Killing JVM", new Object[0]);
                                System.exit(0);
                                c1560u = C1560u.this;
                            }
                            C1560u.m1807b(c1560u, null);
                        } else if (NotificationCompat.CATEGORY_STATUS.equals(line)) {
                            outputStream.write("OK\r\n".getBytes("UTF-8"));
                            outputStream.flush();
                        }
                    } else {
                        System.err.println("Ignoring command with incorrect key");
                    }
                    C1560u.m1807b(C1560u.this, socketAccept);
                } catch (Throwable th2) {
                    socket = socketAccept;
                    th = th2;
                    C1560u.m1807b(C1560u.this, socket);
                    throw th;
                }
            }
        }

        @Override // java.lang.Thread
        public void start() {
            if (isAlive()) {
                System.err.printf("ShutdownMonitorThread already started", new Object[0]);
                return;
            }
            C1560u c1560u = C1560u.this;
            if (c1560u.f4678b >= 0) {
                try {
                    c1560u.f4681e = new ServerSocket(C1560u.this.f4678b, 1, InetAddress.getByName("127.0.0.1"));
                    C1560u c1560u2 = C1560u.this;
                    if (c1560u2.f4678b == 0) {
                        c1560u2.f4678b = c1560u2.f4681e.getLocalPort();
                        System.out.printf("STOP.PORT=%d%n", Integer.valueOf(C1560u.this.f4678b));
                    }
                    C1560u c1560u3 = C1560u.this;
                    if (c1560u3.f4679c == null) {
                        c1560u3.f4679c = Long.toString((long) ((Math.random() * 9.223372036854776E18d) + hashCode() + System.currentTimeMillis()), 36);
                        System.out.printf("STOP.KEY=%s%n", C1560u.this.f4679c);
                    }
                } catch (Exception e7) {
                    try {
                        if (C1560u.this.f4677a) {
                            e7.printStackTrace(System.err);
                        }
                        System.err.println("Error binding monitor port " + C1560u.this.f4678b + ": " + e7.toString());
                        C1560u c1560u4 = C1560u.this;
                        c1560u4.f4681e = null;
                        C1560u.m1806a(c1560u4, "STOP.PORT=%d", new Object[]{Integer.valueOf(c1560u4.f4678b)});
                        C1560u c1560u5 = C1560u.this;
                        C1560u.m1806a(c1560u5, "STOP.KEY=%s", new Object[]{c1560u5.f4679c});
                        C1560u c1560u6 = C1560u.this;
                        C1560u.m1806a(c1560u6, "%s", new Object[]{c1560u6.f4681e});
                    } catch (Throwable th) {
                        C1560u c1560u7 = C1560u.this;
                        C1560u.m1806a(c1560u7, "STOP.PORT=%d", new Object[]{Integer.valueOf(c1560u7.f4678b)});
                        C1560u c1560u8 = C1560u.this;
                        C1560u.m1806a(c1560u8, "STOP.KEY=%s", new Object[]{c1560u8.f4679c});
                        C1560u c1560u9 = C1560u.this;
                        C1560u.m1806a(c1560u9, "%s", new Object[]{c1560u9.f4681e});
                        throw th;
                    }
                }
                C1560u c1560u10 = C1560u.this;
                C1560u.m1806a(c1560u10, "STOP.PORT=%d", new Object[]{Integer.valueOf(c1560u10.f4678b)});
                C1560u c1560u11 = C1560u.this;
                C1560u.m1806a(c1560u11, "STOP.KEY=%s", new Object[]{c1560u11.f4679c});
                C1560u c1560u12 = C1560u.this;
                C1560u.m1806a(c1560u12, "%s", new Object[]{c1560u12.f4681e});
            } else if (c1560u.f4677a) {
                PrintStream printStream = System.err;
                StringBuilder sbM112a = C0413b.m112a("ShutdownMonitor not in use (port < 0): ");
                sbM112a.append(C1560u.this.f4678b);
                printStream.println(sbM112a.toString());
            }
            C1560u c1560u13 = C1560u.this;
            if (c1560u13.f4681e == null) {
                return;
            }
            if (c1560u13.f4677a) {
                System.err.println("Starting ShutdownMonitorThread");
            }
            super.start();
        }
    }

    public C1560u(a aVar) {
        Properties properties = System.getProperties();
        this.f4677a = properties.containsKey("DEBUG");
        this.f4678b = Integer.parseInt(properties.getProperty("STOP.PORT", "-1"));
        this.f4679c = properties.getProperty("STOP.KEY", null);
        this.f4680d = true;
    }

    /* renamed from: a */
    public static void m1806a(C1560u c1560u, String str, Object[] objArr) {
        if (c1560u.f4677a) {
            System.err.printf("[ShutdownMonitor] " + str + "%n", objArr);
        }
    }

    /* renamed from: b */
    public static void m1807b(C1560u c1560u, Socket socket) throws IOException {
        Objects.requireNonNull(c1560u);
        if (socket == null) {
            return;
        }
        try {
            socket.close();
        } catch (IOException unused) {
        }
    }

    public String toString() {
        return String.format("%s[port=%d]", C1560u.class.getName(), Integer.valueOf(this.f4678b));
    }
}
