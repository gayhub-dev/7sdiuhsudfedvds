package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import java.util.ArrayList;
import java.util.List;
import org.fourthline.cling.model.message.header.InvalidHeaderException;
import org.fourthline.cling.model.types.PragmaType;
import p009b.C0413b;

/* loaded from: classes.dex */
public class PragmaHeader extends DLNAHeader<List<PragmaType>> {
    public PragmaHeader() {
        setValue(new ArrayList());
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        String string = "";
        for (PragmaType pragmaType : getValue()) {
            StringBuilder sbM112a = C0413b.m112a(string);
            sbM112a.append(string.length() == 0 ? "" : ",");
            sbM112a.append(pragmaType.getString());
            string = sbM112a.toString();
        }
        return string;
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str.length() == 0) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid Pragma header value: ", str));
        }
        if (str.endsWith(";")) {
            str = str.substring(0, str.length() - 1);
        }
        String[] strArrSplit = str.split("\\s*;\\s*");
        ArrayList arrayList = new ArrayList();
        for (String str2 : strArrSplit) {
            arrayList.add(PragmaType.valueOf(str2));
        }
    }
}
