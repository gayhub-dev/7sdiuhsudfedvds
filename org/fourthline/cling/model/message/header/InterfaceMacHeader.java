package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;
import p009b.C0413b;
import p106m6.C1499b;

/* loaded from: classes.dex */
public class InterfaceMacHeader extends UpnpHeader<byte[]> {
    public InterfaceMacHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        byte[] value = getValue();
        byte[] bArr = C1499b.f4299a;
        if (value == null) {
            return null;
        }
        String str = new String(C1499b.m1663b(value));
        StringBuilder sb = new StringBuilder();
        int i7 = 1;
        for (char c7 : str.toCharArray()) {
            sb.append(c7);
            if (i7 == 2) {
                sb.append(":");
                i7 = 1;
            } else {
                i7++;
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        byte[] bArr = C1499b.f4299a;
        byte[] bArrM1662a = C1499b.m1662a(str.replaceAll(":", "").getBytes());
        setValue(bArrM1662a);
        if (bArrM1662a.length != 6) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid MAC address: ", str));
        }
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") '");
        sbM112a.append(getString());
        sbM112a.append("'");
        return sbM112a.toString();
    }

    public InterfaceMacHeader(byte[] bArr) {
        setValue(bArr);
    }

    public InterfaceMacHeader(String str) {
        setString(str);
    }
}
