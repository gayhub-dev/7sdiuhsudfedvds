package org.fourthline.cling.transport.spi;

import java.net.DatagramPacket;
import java.net.InetAddress;
import org.fourthline.cling.model.message.IncomingDatagramMessage;
import org.fourthline.cling.model.message.OutgoingDatagramMessage;

/* loaded from: classes.dex */
public interface DatagramProcessor {
    IncomingDatagramMessage read(InetAddress inetAddress, DatagramPacket datagramPacket);

    DatagramPacket write(OutgoingDatagramMessage outgoingDatagramMessage);
}
