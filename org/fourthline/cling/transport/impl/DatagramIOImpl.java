package org.fourthline.cling.transport.impl;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.message.OutgoingDatagramMessage;
import org.fourthline.cling.transport.Router;
import org.fourthline.cling.transport.spi.DatagramIO;
import org.fourthline.cling.transport.spi.DatagramProcessor;
import org.fourthline.cling.transport.spi.InitializationException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class DatagramIOImpl implements DatagramIO<DatagramIOConfigurationImpl> {
    private static Logger log = Logger.getLogger(DatagramIO.class.getName());
    public final DatagramIOConfigurationImpl configuration;
    public DatagramProcessor datagramProcessor;
    public InetSocketAddress localAddress;
    public Router router;
    public MulticastSocket socket;

    public DatagramIOImpl(DatagramIOConfigurationImpl datagramIOConfigurationImpl) {
        this.configuration = datagramIOConfigurationImpl;
    }

    @Override // org.fourthline.cling.transport.spi.DatagramIO
    public synchronized void init(InetAddress inetAddress, Router router, DatagramProcessor datagramProcessor) {
        this.router = router;
        this.datagramProcessor = datagramProcessor;
        try {
            log.info("Creating bound socket (for datagram input/output) on: " + inetAddress);
            this.localAddress = new InetSocketAddress(inetAddress, 0);
            MulticastSocket multicastSocket = new MulticastSocket(this.localAddress);
            this.socket = multicastSocket;
            multicastSocket.setTimeToLive(this.configuration.getTimeToLive());
            this.socket.setReceiveBufferSize(262144);
        } catch (Exception e7) {
            throw new InitializationException("Could not initialize " + getClass().getSimpleName() + ": " + e7);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Entering blocking receiving loop, listening for UDP datagrams on: ");
        sbM112a.append(this.socket.getLocalAddress());
        logger.fine(sbM112a.toString());
        while (true) {
            try {
                int maxDatagramBytes = getConfiguration().getMaxDatagramBytes();
                DatagramPacket datagramPacket = new DatagramPacket(new byte[maxDatagramBytes], maxDatagramBytes);
                this.socket.receive(datagramPacket);
                log.fine("UDP datagram received from: " + datagramPacket.getAddress().getHostAddress() + ":" + datagramPacket.getPort() + " on: " + this.localAddress);
                this.router.received(this.datagramProcessor.read(this.localAddress.getAddress(), datagramPacket));
            } catch (SocketException unused) {
                log.fine("Socket closed");
                try {
                    if (this.socket.isClosed()) {
                        return;
                    }
                    log.fine("Closing unicast socket");
                    this.socket.close();
                    return;
                } catch (Exception e7) {
                    throw new RuntimeException(e7);
                }
            } catch (UnsupportedDataException e8) {
                Logger logger2 = log;
                StringBuilder sbM112a2 = C0413b.m112a("Could not read datagram: ");
                sbM112a2.append(e8.getMessage());
                logger2.info(sbM112a2.toString());
            } catch (Exception e9) {
                throw new RuntimeException(e9);
            }
        }
    }

    @Override // org.fourthline.cling.transport.spi.DatagramIO
    public synchronized void send(OutgoingDatagramMessage outgoingDatagramMessage) {
        Logger logger = log;
        Level level = Level.FINE;
        if (logger.isLoggable(level)) {
            log.fine("Sending message from address: " + this.localAddress);
        }
        DatagramPacket datagramPacketWrite = this.datagramProcessor.write(outgoingDatagramMessage);
        if (log.isLoggable(level)) {
            log.fine("Sending UDP datagram packet to: " + outgoingDatagramMessage.getDestinationAddress() + ":" + outgoingDatagramMessage.getDestinationPort());
        }
        send(datagramPacketWrite);
    }

    @Override // org.fourthline.cling.transport.spi.DatagramIO
    public synchronized void stop() {
        MulticastSocket multicastSocket = this.socket;
        if (multicastSocket != null && !multicastSocket.isClosed()) {
            this.socket.close();
        }
    }

    @Override // org.fourthline.cling.transport.spi.DatagramIO
    public DatagramIOConfigurationImpl getConfiguration() {
        return this.configuration;
    }

    @Override // org.fourthline.cling.transport.spi.DatagramIO
    public synchronized void send(DatagramPacket datagramPacket) {
        if (log.isLoggable(Level.FINE)) {
            log.fine("Sending message from address: " + this.localAddress);
        }
        try {
            this.socket.send(datagramPacket);
        } catch (RuntimeException e7) {
            throw e7;
        } catch (SocketException unused) {
            log.fine("Socket closed, aborting datagram send to: " + datagramPacket.getAddress());
        } catch (Exception e8) {
            log.log(Level.SEVERE, "Exception sending datagram to: " + datagramPacket.getAddress() + ": " + e8, (Throwable) e8);
        }
    }
}
