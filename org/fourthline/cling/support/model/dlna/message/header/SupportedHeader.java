package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import org.fourthline.cling.model.message.header.InvalidHeaderException;

/* loaded from: classes.dex */
public class SupportedHeader extends DLNAHeader<String[]> {
    public SupportedHeader() {
        setValue(new String[0]);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        String[] value = getValue();
        String string = value.length > 0 ? value[0] : "";
        for (int i7 = 1; i7 < value.length; i7++) {
            StringBuilder sbM94a = C0080b.m94a(string, ",");
            sbM94a.append(value[i7]);
            string = sbM94a.toString();
        }
        return string;
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str.length() == 0) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid Supported header value: ", str));
        }
        if (str.endsWith(";")) {
            str = str.substring(0, str.length() - 1);
        }
        setValue(str.split("\\s*,\\s*"));
    }
}
