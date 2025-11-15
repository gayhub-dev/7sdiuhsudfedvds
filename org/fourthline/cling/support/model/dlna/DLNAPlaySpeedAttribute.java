package org.fourthline.cling.support.model.dlna;

import android.arch.lifecycle.C0063n;
import okhttp3.internal.cache.DiskLruCache;
import org.fourthline.cling.model.types.InvalidValueException;
import org.fourthline.cling.support.avtransport.lastchange.AVTransportVariable;
import p009b.C0413b;

/* loaded from: classes.dex */
public class DLNAPlaySpeedAttribute extends DLNAAttribute<AVTransportVariable.TransportPlaySpeed[]> {
    public DLNAPlaySpeedAttribute() {
        setValue(new AVTransportVariable.TransportPlaySpeed[0]);
    }

    @Override // org.fourthline.cling.support.model.dlna.DLNAAttribute
    public String getString() {
        String string = "";
        for (AVTransportVariable.TransportPlaySpeed transportPlaySpeed : getValue()) {
            if (!transportPlaySpeed.getValue().equals(DiskLruCache.VERSION_1)) {
                StringBuilder sbM112a = C0413b.m112a(string);
                sbM112a.append(string.length() == 0 ? "" : ",");
                sbM112a.append(transportPlaySpeed);
                string = sbM112a.toString();
            }
        }
        return string;
    }

    @Override // org.fourthline.cling.support.model.dlna.DLNAAttribute
    public void setString(String str, String str2) {
        AVTransportVariable.TransportPlaySpeed[] transportPlaySpeedArr = null;
        if (str != null && str.length() != 0) {
            String[] strArrSplit = str.split(",");
            try {
                AVTransportVariable.TransportPlaySpeed[] transportPlaySpeedArr2 = new AVTransportVariable.TransportPlaySpeed[strArrSplit.length];
                for (int i7 = 0; i7 < strArrSplit.length; i7++) {
                    transportPlaySpeedArr2[i7] = new AVTransportVariable.TransportPlaySpeed(strArrSplit[i7]);
                }
                transportPlaySpeedArr = transportPlaySpeedArr2;
            } catch (InvalidValueException unused) {
            }
        }
        if (transportPlaySpeedArr == null) {
            throw new InvalidDLNAProtocolAttributeException(C0063n.m88a("Can't parse DLNA play speeds from: ", str));
        }
        setValue(transportPlaySpeedArr);
    }

    public DLNAPlaySpeedAttribute(AVTransportVariable.TransportPlaySpeed[] transportPlaySpeedArr) {
        setValue(transportPlaySpeedArr);
    }

    public DLNAPlaySpeedAttribute(String[] strArr) {
        AVTransportVariable.TransportPlaySpeed[] transportPlaySpeedArr = new AVTransportVariable.TransportPlaySpeed[strArr.length];
        for (int i7 = 0; i7 < strArr.length; i7++) {
            try {
                transportPlaySpeedArr[i7] = new AVTransportVariable.TransportPlaySpeed(strArr[i7]);
            } catch (InvalidValueException unused) {
                throw new InvalidDLNAProtocolAttributeException("Can't parse DLNA play speeds.");
            }
        }
        setValue(transportPlaySpeedArr);
    }
}
