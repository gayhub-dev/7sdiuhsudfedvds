package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;
import p009b.C0413b;

/* loaded from: classes.dex */
public class NamedServiceType {
    private ServiceType serviceType;
    private UDN udn;

    public NamedServiceType(UDN udn, ServiceType serviceType) {
        this.udn = udn;
        this.serviceType = serviceType;
    }

    public static NamedServiceType valueOf(String str) {
        String[] strArrSplit = str.split("::");
        if (strArrSplit.length != 2) {
            throw new InvalidValueException(C0063n.m88a("Can't parse UDN::ServiceType from: ", str));
        }
        try {
            return new NamedServiceType(UDN.valueOf(strArrSplit[0]), ServiceType.valueOf(strArrSplit[1]));
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
        if (obj == null || !(obj instanceof NamedServiceType)) {
            return false;
        }
        NamedServiceType namedServiceType = (NamedServiceType) obj;
        return this.serviceType.equals(namedServiceType.serviceType) && this.udn.equals(namedServiceType.udn);
    }

    public ServiceType getServiceType() {
        return this.serviceType;
    }

    public UDN getUdn() {
        return this.udn;
    }

    public int hashCode() {
        return this.serviceType.hashCode() + (this.udn.hashCode() * 31);
    }

    public String toString() {
        return getUdn().toString() + "::" + getServiceType().toString();
    }
}
