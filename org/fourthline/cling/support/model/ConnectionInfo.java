package org.fourthline.cling.support.model;

import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ConnectionInfo {
    public final int avTransportID;
    public final int connectionID;
    public Status connectionStatus;
    public final Direction direction;
    public final int peerConnectionID;
    public final ServiceReference peerConnectionManager;
    public final ProtocolInfo protocolInfo;
    public final int rcsID;

    public enum Direction {
        Output,
        Input;

        public Direction getOpposite() {
            Direction direction = Output;
            return equals(direction) ? Input : direction;
        }
    }

    public enum Status {
        OK,
        ContentFormatMismatch,
        InsufficientBandwidth,
        UnreliableChannel,
        Unknown
    }

    public ConnectionInfo() {
        this(0, 0, 0, null, null, -1, Direction.Input, Status.Unknown);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ConnectionInfo connectionInfo = (ConnectionInfo) obj;
        if (this.avTransportID != connectionInfo.avTransportID || this.connectionID != connectionInfo.connectionID || this.peerConnectionID != connectionInfo.peerConnectionID || this.rcsID != connectionInfo.rcsID || this.connectionStatus != connectionInfo.connectionStatus || this.direction != connectionInfo.direction) {
            return false;
        }
        ServiceReference serviceReference = this.peerConnectionManager;
        if (serviceReference == null ? connectionInfo.peerConnectionManager != null : !serviceReference.equals(connectionInfo.peerConnectionManager)) {
            return false;
        }
        ProtocolInfo protocolInfo = this.protocolInfo;
        ProtocolInfo protocolInfo2 = connectionInfo.protocolInfo;
        return protocolInfo == null ? protocolInfo2 == null : protocolInfo.equals(protocolInfo2);
    }

    public int getAvTransportID() {
        return this.avTransportID;
    }

    public int getConnectionID() {
        return this.connectionID;
    }

    public synchronized Status getConnectionStatus() {
        return this.connectionStatus;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getPeerConnectionID() {
        return this.peerConnectionID;
    }

    public ServiceReference getPeerConnectionManager() {
        return this.peerConnectionManager;
    }

    public ProtocolInfo getProtocolInfo() {
        return this.protocolInfo;
    }

    public int getRcsID() {
        return this.rcsID;
    }

    public int hashCode() {
        int i7 = ((((this.connectionID * 31) + this.rcsID) * 31) + this.avTransportID) * 31;
        ProtocolInfo protocolInfo = this.protocolInfo;
        int iHashCode = (i7 + (protocolInfo != null ? protocolInfo.hashCode() : 0)) * 31;
        ServiceReference serviceReference = this.peerConnectionManager;
        return this.connectionStatus.hashCode() + ((this.direction.hashCode() + ((((iHashCode + (serviceReference != null ? serviceReference.hashCode() : 0)) * 31) + this.peerConnectionID) * 31)) * 31);
    }

    public synchronized void setConnectionStatus(Status status) {
        this.connectionStatus = status;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") ID: ");
        sbM112a.append(getConnectionID());
        sbM112a.append(", Status: ");
        sbM112a.append(getConnectionStatus());
        return sbM112a.toString();
    }

    public ConnectionInfo(int i7, int i8, int i9, ProtocolInfo protocolInfo, ServiceReference serviceReference, int i10, Direction direction, Status status) {
        this.connectionStatus = Status.Unknown;
        this.connectionID = i7;
        this.rcsID = i8;
        this.avTransportID = i9;
        this.protocolInfo = protocolInfo;
        this.peerConnectionManager = serviceReference;
        this.peerConnectionID = i10;
        this.direction = direction;
        this.connectionStatus = status;
    }
}
