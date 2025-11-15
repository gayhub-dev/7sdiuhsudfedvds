package org.fourthline.cling.support.model;

import android.arch.lifecycle.C0063n;
import java.util.Objects;
import org.fourthline.cling.model.types.InvalidValueException;
import p098l6.C1448b;

/* loaded from: classes.dex */
public class ProtocolInfo {
    public static final String WILDCARD = "*";
    public String additionalInfo;
    public String contentFormat;
    public String network;
    public Protocol protocol;

    public ProtocolInfo(String str) {
        this.protocol = Protocol.ALL;
        this.network = "*";
        this.contentFormat = "*";
        this.additionalInfo = "*";
        Objects.requireNonNull(str);
        String strTrim = str.trim();
        String[] strArrSplit = strTrim.split(":");
        if (strArrSplit.length != 4) {
            throw new InvalidValueException(C0063n.m88a("Can't parse ProtocolInfo string: ", strTrim));
        }
        this.protocol = Protocol.value(strArrSplit[0]);
        this.network = strArrSplit[1];
        this.contentFormat = strArrSplit[2];
        this.additionalInfo = strArrSplit[3];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProtocolInfo protocolInfo = (ProtocolInfo) obj;
        return this.additionalInfo.equals(protocolInfo.additionalInfo) && this.contentFormat.equals(protocolInfo.contentFormat) && this.network.equals(protocolInfo.network) && this.protocol == protocolInfo.protocol;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public String getContentFormat() {
        return this.contentFormat;
    }

    public C1448b getContentFormatMimeType() {
        return C1448b.m1633a(this.contentFormat);
    }

    public String getNetwork() {
        return this.network;
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    public int hashCode() {
        return this.additionalInfo.hashCode() + ((this.contentFormat.hashCode() + ((this.network.hashCode() + (this.protocol.hashCode() * 31)) * 31)) * 31);
    }

    public String toString() {
        return this.protocol.toString() + ":" + this.network + ":" + this.contentFormat + ":" + this.additionalInfo;
    }

    public ProtocolInfo(C1448b c1448b) {
        this.protocol = Protocol.ALL;
        this.network = "*";
        this.contentFormat = "*";
        this.additionalInfo = "*";
        this.protocol = Protocol.HTTP_GET;
        this.contentFormat = c1448b.toString();
    }

    public ProtocolInfo(Protocol protocol, String str, String str2, String str3) {
        this.protocol = Protocol.ALL;
        this.network = "*";
        this.contentFormat = "*";
        this.additionalInfo = "*";
        this.protocol = protocol;
        this.network = str;
        this.contentFormat = str2;
        this.additionalInfo = str3;
    }
}
