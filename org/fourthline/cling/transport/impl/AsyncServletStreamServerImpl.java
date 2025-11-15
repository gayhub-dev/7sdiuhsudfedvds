package org.fourthline.cling.transport.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.message.Connection;
import org.fourthline.cling.protocol.ProtocolFactory;
import org.fourthline.cling.transport.Router;
import org.fourthline.cling.transport.spi.InitializationException;
import org.fourthline.cling.transport.spi.StreamServer;
import p006a5.C0016b;
import p006a5.InterfaceC0014a;
import p006a5.InterfaceC0017c;
import p006a5.InterfaceC0024j;
import p015b5.AbstractC0457b;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;
import p113n5.C1542c;

/* loaded from: classes.dex */
public class AsyncServletStreamServerImpl implements StreamServer<AsyncServletStreamServerConfigurationImpl> {
    private static final Logger log = Logger.getLogger(StreamServer.class.getName());
    public final AsyncServletStreamServerConfigurationImpl configuration;
    public String hostAddress;
    public int localPort;
    private int mCounter = 0;

    /* renamed from: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl$1 */
    public class C17151 extends AbstractC0457b {
        public final /* synthetic */ Router val$router;

        /* renamed from: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl$1$1 */
        public class AnonymousClass1 implements InterfaceC0017c {
            public final /* synthetic */ int val$counter;
            public final /* synthetic */ long val$startTime;

            public AnonymousClass1(long j7, int i7) {
                j = j7;
                i = i7;
            }

            @Override // p006a5.InterfaceC0017c
            public void onComplete(C0016b c0016b) {
                long jCurrentTimeMillis = System.currentTimeMillis() - j;
                if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                    AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onComplete(): id: %3d, duration: %,4d, response: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis), c0016b.f28b));
                }
            }

            @Override // p006a5.InterfaceC0017c
            public void onError(C0016b c0016b) {
                long jCurrentTimeMillis = System.currentTimeMillis() - j;
                if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                    AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onError(): id: %3d, duration: %,4d, response: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis), c0016b.f28b));
                }
            }

            @Override // p006a5.InterfaceC0017c
            public void onStartAsync(C0016b c0016b) {
                if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                    AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onStartAsync(): id: %3d, request: %s", Integer.valueOf(i), c0016b.f27a));
                }
            }

            @Override // p006a5.InterfaceC0017c
            public void onTimeout(C0016b c0016b) {
                long jCurrentTimeMillis = System.currentTimeMillis() - j;
                if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                    AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onTimeout(): id: %3d, duration: %,4d, request: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis), c0016b.f27a));
                }
            }
        }

        /* renamed from: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl$1$2 */
        public class AnonymousClass2 extends AsyncServletUpnpStream {
            public AnonymousClass2(ProtocolFactory protocolFactory, InterfaceC0014a interfaceC0014a, InterfaceC0458c interfaceC0458c) {
                super(protocolFactory, interfaceC0014a, interfaceC0458c);
            }

            @Override // org.fourthline.cling.transport.impl.AsyncServletUpnpStream
            public Connection createConnection() {
                return AsyncServletStreamServerImpl.this.new AsyncServletConnection(getRequest());
            }
        }

        public C17151(Router router) {
            router = router;
        }

        @Override // p015b5.AbstractC0457b
        public void service(InterfaceC0458c interfaceC0458c, InterfaceC0460e interfaceC0460e) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            int iAccess$008 = AsyncServletStreamServerImpl.access$008(AsyncServletStreamServerImpl.this);
            if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                AsyncServletStreamServerImpl.log.fine(String.format("HttpServlet.service(): id: %3d, request URI: %s", Integer.valueOf(iAccess$008), interfaceC0458c.mo167U()));
            }
            InterfaceC0014a interfaceC0014aMo25R = interfaceC0458c.mo25R();
            long asyncTimeoutSeconds = AsyncServletStreamServerImpl.this.getConfiguration().getAsyncTimeoutSeconds() * 1000;
            C1542c c1542c = (C1542c) interfaceC0014aMo25R;
            synchronized (c1542c) {
                c1542c.f4575h = asyncTimeoutSeconds;
            }
            ((C1542c) interfaceC0014aMo25R).m1743a(new InterfaceC0017c() { // from class: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl.1.1
                public final /* synthetic */ int val$counter;
                public final /* synthetic */ long val$startTime;

                public AnonymousClass1(long jCurrentTimeMillis2, int iAccess$0082) {
                    j = jCurrentTimeMillis2;
                    i = iAccess$0082;
                }

                @Override // p006a5.InterfaceC0017c
                public void onComplete(C0016b c0016b) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                        AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onComplete(): id: %3d, duration: %,4d, response: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis2), c0016b.f28b));
                    }
                }

                @Override // p006a5.InterfaceC0017c
                public void onError(C0016b c0016b) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                        AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onError(): id: %3d, duration: %,4d, response: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis2), c0016b.f28b));
                    }
                }

                @Override // p006a5.InterfaceC0017c
                public void onStartAsync(C0016b c0016b) {
                    if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                        AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onStartAsync(): id: %3d, request: %s", Integer.valueOf(i), c0016b.f27a));
                    }
                }

                @Override // p006a5.InterfaceC0017c
                public void onTimeout(C0016b c0016b) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                        AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onTimeout(): id: %3d, duration: %,4d, request: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis2), c0016b.f27a));
                    }
                }
            });
            router.received(new AsyncServletUpnpStream(router.getProtocolFactory(), interfaceC0014aMo25R, interfaceC0458c) { // from class: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl.1.2
                public AnonymousClass2(ProtocolFactory protocolFactory, InterfaceC0014a interfaceC0014aMo25R2, InterfaceC0458c interfaceC0458c2) {
                    super(protocolFactory, interfaceC0014aMo25R2, interfaceC0458c2);
                }

                @Override // org.fourthline.cling.transport.impl.AsyncServletUpnpStream
                public Connection createConnection() {
                    return AsyncServletStreamServerImpl.this.new AsyncServletConnection(getRequest());
                }
            });
        }
    }

    public class AsyncServletConnection implements Connection {
        public InterfaceC0458c request;

        public AsyncServletConnection(InterfaceC0458c interfaceC0458c) {
            this.request = interfaceC0458c;
        }

        @Override // org.fourthline.cling.model.message.Connection
        public InetAddress getLocalAddress() {
            try {
                return InetAddress.getByName(getRequest().mo31g());
            } catch (UnknownHostException e7) {
                throw new RuntimeException(e7);
            }
        }

        @Override // org.fourthline.cling.model.message.Connection
        public InetAddress getRemoteAddress() {
            try {
                return InetAddress.getByName(getRequest().mo29e());
            } catch (UnknownHostException e7) {
                throw new RuntimeException(e7);
            }
        }

        public InterfaceC0458c getRequest() {
            return this.request;
        }

        @Override // org.fourthline.cling.model.message.Connection
        public boolean isOpen() {
            return AsyncServletStreamServerImpl.this.isConnectionOpen(getRequest());
        }
    }

    public AsyncServletStreamServerImpl(AsyncServletStreamServerConfigurationImpl asyncServletStreamServerConfigurationImpl) {
        this.configuration = asyncServletStreamServerConfigurationImpl;
    }

    public static /* synthetic */ int access$008(AsyncServletStreamServerImpl asyncServletStreamServerImpl) {
        int i7 = asyncServletStreamServerImpl.mCounter;
        asyncServletStreamServerImpl.mCounter = i7 + 1;
        return i7;
    }

    public InterfaceC0024j createServlet(Router router) {
        return new AbstractC0457b() { // from class: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl.1
            public final /* synthetic */ Router val$router;

            /* renamed from: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl$1$1 */
            public class AnonymousClass1 implements InterfaceC0017c {
                public final /* synthetic */ int val$counter;
                public final /* synthetic */ long val$startTime;

                public AnonymousClass1(long jCurrentTimeMillis2, int iAccess$0082) {
                    j = jCurrentTimeMillis2;
                    i = iAccess$0082;
                }

                @Override // p006a5.InterfaceC0017c
                public void onComplete(C0016b c0016b) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                        AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onComplete(): id: %3d, duration: %,4d, response: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis2), c0016b.f28b));
                    }
                }

                @Override // p006a5.InterfaceC0017c
                public void onError(C0016b c0016b) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                        AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onError(): id: %3d, duration: %,4d, response: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis2), c0016b.f28b));
                    }
                }

                @Override // p006a5.InterfaceC0017c
                public void onStartAsync(C0016b c0016b) {
                    if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                        AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onStartAsync(): id: %3d, request: %s", Integer.valueOf(i), c0016b.f27a));
                    }
                }

                @Override // p006a5.InterfaceC0017c
                public void onTimeout(C0016b c0016b) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - j;
                    if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                        AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onTimeout(): id: %3d, duration: %,4d, request: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis2), c0016b.f27a));
                    }
                }
            }

            /* renamed from: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl$1$2 */
            public class AnonymousClass2 extends AsyncServletUpnpStream {
                public AnonymousClass2(ProtocolFactory protocolFactory, InterfaceC0014a interfaceC0014aMo25R2, InterfaceC0458c interfaceC0458c2) {
                    super(protocolFactory, interfaceC0014aMo25R2, interfaceC0458c2);
                }

                @Override // org.fourthline.cling.transport.impl.AsyncServletUpnpStream
                public Connection createConnection() {
                    return AsyncServletStreamServerImpl.this.new AsyncServletConnection(getRequest());
                }
            }

            public C17151(Router router2) {
                router = router2;
            }

            @Override // p015b5.AbstractC0457b
            public void service(InterfaceC0458c interfaceC0458c2, InterfaceC0460e interfaceC0460e) {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                int iAccess$0082 = AsyncServletStreamServerImpl.access$008(AsyncServletStreamServerImpl.this);
                if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                    AsyncServletStreamServerImpl.log.fine(String.format("HttpServlet.service(): id: %3d, request URI: %s", Integer.valueOf(iAccess$0082), interfaceC0458c2.mo167U()));
                }
                InterfaceC0014a interfaceC0014aMo25R2 = interfaceC0458c2.mo25R();
                long asyncTimeoutSeconds = AsyncServletStreamServerImpl.this.getConfiguration().getAsyncTimeoutSeconds() * 1000;
                C1542c c1542c = (C1542c) interfaceC0014aMo25R2;
                synchronized (c1542c) {
                    c1542c.f4575h = asyncTimeoutSeconds;
                }
                ((C1542c) interfaceC0014aMo25R2).m1743a(new InterfaceC0017c() { // from class: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl.1.1
                    public final /* synthetic */ int val$counter;
                    public final /* synthetic */ long val$startTime;

                    public AnonymousClass1(long jCurrentTimeMillis22, int iAccess$00822) {
                        j = jCurrentTimeMillis22;
                        i = iAccess$00822;
                    }

                    @Override // p006a5.InterfaceC0017c
                    public void onComplete(C0016b c0016b) {
                        long jCurrentTimeMillis22 = System.currentTimeMillis() - j;
                        if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                            AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onComplete(): id: %3d, duration: %,4d, response: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis22), c0016b.f28b));
                        }
                    }

                    @Override // p006a5.InterfaceC0017c
                    public void onError(C0016b c0016b) {
                        long jCurrentTimeMillis22 = System.currentTimeMillis() - j;
                        if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                            AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onError(): id: %3d, duration: %,4d, response: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis22), c0016b.f28b));
                        }
                    }

                    @Override // p006a5.InterfaceC0017c
                    public void onStartAsync(C0016b c0016b) {
                        if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                            AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onStartAsync(): id: %3d, request: %s", Integer.valueOf(i), c0016b.f27a));
                        }
                    }

                    @Override // p006a5.InterfaceC0017c
                    public void onTimeout(C0016b c0016b) {
                        long jCurrentTimeMillis22 = System.currentTimeMillis() - j;
                        if (AsyncServletStreamServerImpl.log.isLoggable(Level.FINE)) {
                            AsyncServletStreamServerImpl.log.fine(String.format("AsyncListener.onTimeout(): id: %3d, duration: %,4d, request: %s", Integer.valueOf(i), Long.valueOf(jCurrentTimeMillis22), c0016b.f27a));
                        }
                    }
                });
                router.received(new AsyncServletUpnpStream(router.getProtocolFactory(), interfaceC0014aMo25R2, interfaceC0458c2) { // from class: org.fourthline.cling.transport.impl.AsyncServletStreamServerImpl.1.2
                    public AnonymousClass2(ProtocolFactory protocolFactory, InterfaceC0014a interfaceC0014aMo25R22, InterfaceC0458c interfaceC0458c22) {
                        super(protocolFactory, interfaceC0014aMo25R22, interfaceC0458c22);
                    }

                    @Override // org.fourthline.cling.transport.impl.AsyncServletUpnpStream
                    public Connection createConnection() {
                        return AsyncServletStreamServerImpl.this.new AsyncServletConnection(getRequest());
                    }
                });
            }
        };
    }

    @Override // org.fourthline.cling.transport.spi.StreamServer
    public synchronized int getPort() {
        return this.localPort;
    }

    @Override // org.fourthline.cling.transport.spi.StreamServer
    public synchronized void init(InetAddress inetAddress, Router router) {
        try {
            Logger logger = log;
            Level level = Level.FINE;
            if (logger.isLoggable(level)) {
                logger.fine("Setting executor service on servlet container adapter");
            }
            getConfiguration().getServletContainerAdapter().setExecutorService(router.getConfiguration().getStreamServerExecutorService());
            if (logger.isLoggable(level)) {
                logger.fine("Adding connector: " + inetAddress + ":" + getConfiguration().getListenPort());
            }
            this.hostAddress = inetAddress.getHostAddress();
            this.localPort = getConfiguration().getServletContainerAdapter().addConnector(this.hostAddress, getConfiguration().getListenPort());
            getConfiguration().getServletContainerAdapter().registerServlet(router.getConfiguration().getNamespace().getBasePath().getPath(), createServlet(router));
        } catch (Exception e7) {
            throw new InitializationException("Could not initialize " + getClass().getSimpleName() + ": " + e7.toString(), e7);
        }
    }

    public boolean isConnectionOpen(InterfaceC0458c interfaceC0458c) {
        return true;
    }

    @Override // java.lang.Runnable
    public void run() {
        getConfiguration().getServletContainerAdapter().startIfNotRunning();
    }

    @Override // org.fourthline.cling.transport.spi.StreamServer
    public synchronized void stop() {
        getConfiguration().getServletContainerAdapter().removeConnector(this.hostAddress, this.localPort);
    }

    @Override // org.fourthline.cling.transport.spi.StreamServer
    public AsyncServletStreamServerConfigurationImpl getConfiguration() {
        return this.configuration;
    }
}
