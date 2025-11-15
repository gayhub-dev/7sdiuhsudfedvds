package org.fourthline.cling.support.model;

import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Connection {

    public enum Error {
        ERROR_NONE,
        ERROR_COMMAND_ABORTED,
        ERROR_NOT_ENABLED_FOR_INTERNET,
        ERROR_USER_DISCONNECT,
        ERROR_ISP_DISCONNECT,
        ERROR_IDLE_DISCONNECT,
        ERROR_FORCED_DISCONNECT,
        ERROR_NO_CARRIER,
        ERROR_IP_CONFIGURATION,
        ERROR_UNKNOWN
    }

    public enum Status {
        Unconfigured,
        Connecting,
        Connected,
        PendingDisconnect,
        Disconnecting,
        Disconnected
    }

    public static class StatusInfo {
        private Error lastError;
        private Status status;
        private long uptimeSeconds;

        public StatusInfo(Status status, UnsignedIntegerFourBytes unsignedIntegerFourBytes, Error error) {
            this(status, unsignedIntegerFourBytes.getValue().longValue(), error);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            StatusInfo statusInfo = (StatusInfo) obj;
            return this.uptimeSeconds == statusInfo.uptimeSeconds && this.lastError == statusInfo.lastError && this.status == statusInfo.status;
        }

        public Error getLastError() {
            return this.lastError;
        }

        public Status getStatus() {
            return this.status;
        }

        public UnsignedIntegerFourBytes getUptime() {
            return new UnsignedIntegerFourBytes(getUptimeSeconds());
        }

        public long getUptimeSeconds() {
            return this.uptimeSeconds;
        }

        public int hashCode() {
            int iHashCode = this.status.hashCode() * 31;
            long j7 = this.uptimeSeconds;
            return this.lastError.hashCode() + ((iHashCode + ((int) (j7 ^ (j7 >>> 32)))) * 31);
        }

        public String toString() {
            StringBuilder sbM112a = C0413b.m112a("(");
            sbM112a.append(getClass().getSimpleName());
            sbM112a.append(") ");
            sbM112a.append(getStatus());
            return sbM112a.toString();
        }

        public StatusInfo(Status status, long j7, Error error) {
            this.status = status;
            this.uptimeSeconds = j7;
            this.lastError = error;
        }
    }

    public enum Type {
        Unconfigured,
        IP_Routed,
        IP_Bridged
    }
}
