package org.fourthline.cling.model.meta;

import java.util.ArrayList;
import java.util.List;
import org.fourthline.cling.model.Validatable;
import org.fourthline.cling.model.ValidationError;
import org.fourthline.cling.model.types.UDN;
import p009b.C0413b;

/* loaded from: classes.dex */
public class DeviceIdentity implements Validatable {
    private final Integer maxAgeSeconds;
    private final UDN udn;

    public DeviceIdentity(UDN udn, DeviceIdentity deviceIdentity) {
        this.udn = udn;
        this.maxAgeSeconds = deviceIdentity.getMaxAgeSeconds();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.udn.equals(((DeviceIdentity) obj).udn);
    }

    public Integer getMaxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public UDN getUdn() {
        return this.udn;
    }

    public int hashCode() {
        return this.udn.hashCode();
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") UDN: ");
        sbM112a.append(getUdn());
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.Validatable
    public List<ValidationError> validate() {
        ArrayList arrayList = new ArrayList();
        if (getUdn() == null) {
            arrayList.add(new ValidationError(getClass(), "major", "Device has no UDN"));
        }
        return arrayList;
    }

    public DeviceIdentity(UDN udn) {
        this.udn = udn;
        this.maxAgeSeconds = 1800;
    }

    public DeviceIdentity(UDN udn, Integer num) {
        this.udn = udn;
        this.maxAgeSeconds = num;
    }
}
