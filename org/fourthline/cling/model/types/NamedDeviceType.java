package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;
import p009b.C0413b;

/* loaded from: classes.dex */
public class NamedDeviceType {
    private DeviceType deviceType;
    private UDN udn;

    public NamedDeviceType(UDN udn, DeviceType deviceType) {
        this.udn = udn;
        this.deviceType = deviceType;
    }

    public static NamedDeviceType valueOf(String str) {
        String[] strArrSplit = str.split("::");
        if (strArrSplit.length != 2) {
            throw new InvalidValueException(C0063n.m88a("Can't parse UDN::DeviceType from: ", str));
        }
        try {
            return new NamedDeviceType(UDN.valueOf(strArrSplit[0]), DeviceType.valueOf(strArrSplit[1]));
        } catch (Exception unused) {
            StringBuilder sbM112a = C0413b.m112a("Can't parse UDN: ");
            sbM112a.append(strArrSplit[0]);
            throw new InvalidValueException(sbM112a.toString());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NamedDeviceType)) {
            return false;
        }
        NamedDeviceType namedDeviceType = (NamedDeviceType) obj;
        return this.deviceType.equals(namedDeviceType.deviceType) && this.udn.equals(namedDeviceType.udn);
    }

    public DeviceType getDeviceType() {
        return this.deviceType;
    }

    public UDN getUdn() {
        return this.udn;
    }

    public int hashCode() {
        return this.deviceType.hashCode() + (this.udn.hashCode() * 31);
    }

    public String toString() {
        return getUdn().toString() + "::" + getDeviceType().toString();
    }
}
