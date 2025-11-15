package org.fourthline.cling.transport.impl;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.logging.Logger;
import org.fourthline.cling.model.ServiceReference;
import org.fourthline.cling.model.message.Connection;
import org.fourthline.cling.transport.Router;
import org.fourthline.cling.transport.spi.InitializationException;
import org.fourthline.cling.transport.spi.StreamServer;
import p009b.C0413b;
import p140q6.C1793n;
import p159t3.AbstractC1905d;
import p159t3.AbstractC1907f;
import p159t3.InterfaceC1906e;

/* loaded from: classes.dex */
public class StreamServerImpl implements StreamServer<StreamServerConfigurationImpl> {
    private static Logger log = Logger.getLogger(StreamServer.class.getName());
    public final StreamServerConfigurationImpl configuration;
    public AbstractC1907f server;

    public class HttpServerConnection implements Connection {
        public AbstractC1905d exchange;

        public HttpServerConnection(AbstractC1905d abstractC1905d) {
            this.exchange = abstractC1905d;
        }

        @Override // org.fourthline.cling.model.message.Connection
        public InetAddress getLocalAddress() {
            if (this.exchange.mo1985a() != null) {
                return this.exchange.mo1985a().getAddress();
            }
            return null;
        }

        @Override // org.fourthline.cling.model.message.Connection
        public InetAddress getRemoteAddress() {
            if (this.exchange.mo1987c() != null) {
                return this.exchange.mo1987c().getAddress();
            }
            return null;
        }

        @Override // org.fourthline.cling.model.message.Connection
        public boolean isOpen() {
            return StreamServerImpl.this.isConnectionOpen(this.exchange);
        }
    }

    public class RequestHttpHandler implements InterfaceC1906e {
        private final Router router;

        public RequestHttpHandler(Router router) {
            this.router = router;
        }

        @Override // p159t3.InterfaceC1906e
        public void handle(final AbstractC1905d abstractC1905d) {
            Logger logger = StreamServerImpl.log;
            StringBuilder sbM112a = C0413b.m112a("Received HTTP exchange: ");
            sbM112a.append(abstractC1905d.mo1990f());
            sbM112a.append(" ");
            sbM112a.append(abstractC1905d.mo1991g());
            logger.fine(sbM112a.toString());
            this.router.received(new HttpExchangeUpnpStream(this.router.getProtocolFactory(), abstractC1905d) { // from class: org.fourthline.cling.transport.impl.StreamServerImpl.RequestHttpHandler.1
                @Override // org.fourthline.cling.transport.impl.HttpExchangeUpnpStream
                public Connection createConnection() {
                    return StreamServerImpl.this.new HttpServerConnection(abstractC1905d);
                }
            });
        }
    }

    public StreamServerImpl(StreamServerConfigurationImpl streamServerConfigurationImpl) {
        this.configuration = streamServerConfigurationImpl;
    }

    @Override // org.fourthline.cling.transport.spi.StreamServer
    public synchronized int getPort() {
        return ((InetSocketAddress) ((C1793n) this.server).f5119a.f5180e.socket().getLocalSocketAddress()).getPort();
    }

    @Override // org.fourthline.cling.transport.spi.StreamServer
    public synchronized void init(InetAddress inetAddress, Router router) {
        try {
            AbstractC1907f abstractC1907fM2203a = AbstractC1907f.m2203a(new InetSocketAddress(inetAddress, this.configuration.getListenPort()), this.configuration.getTcpConnectionBacklog());
            this.server = abstractC1907fM2203a;
            abstractC1907fM2203a.mo1995b(ServiceReference.DELIMITER, new RequestHttpHandler(router));
            log.info("Created server (for receiving TCP streams) on: " + ((InetSocketAddress) ((C1793n) this.server).f5119a.f5180e.socket().getLocalSocketAddress()));
        } catch (Exception e7) {
            throw new InitializationException("Could not initialize " + getClass().getSimpleName() + ": " + e7.toString(), e7);
        }
    }

    public boolean isConnectionOpen(AbstractC1905d abstractC1905d) {
        log.warning("Can't check client connection, socket access impossible on JDK webserver!");
        return true;
    }

    @Override // java.lang.Runnable
    public synchronized void run() {
        log.fine("Starting StreamServer...");
        this.server.mo1996c();
    }

    @Override // org.fourthline.cling.transport.spi.StreamServer
    public synchronized void stop() {
        log.fine("Stopping StreamServer...");
        AbstractC1907f abstractC1907f = this.server;
        if (abstractC1907f != null) {
            abstractC1907f.mo1997d(1);
        }
    }

    @Override // org.fourthline.cling.transport.spi.StreamServer
    public StreamServerConfigurationImpl getConfiguration() {
        return this.configuration;
    }
}
