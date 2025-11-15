package org.fourthline.cling.support.model.dlna.message.header;

import java.util.EnumMap;
import org.fourthline.cling.support.model.dlna.DLNAAttribute;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ContentFeaturesHeader extends DLNAHeader<EnumMap<DLNAAttribute.Type, DLNAAttribute>> {
    public ContentFeaturesHeader() {
        setValue(new EnumMap(DLNAAttribute.Type.class));
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        String string = "";
        for (DLNAAttribute.Type type : DLNAAttribute.Type.values()) {
            String string2 = getValue().containsKey(type) ? getValue().get(type).getString() : null;
            if (string2 != null && string2.length() != 0) {
                StringBuilder sbM112a = C0413b.m112a(string);
                sbM112a.append(string.length() == 0 ? "" : ";");
                sbM112a.append(type.getAttributeName());
                sbM112a.append("=");
                sbM112a.append(string2);
                string = sbM112a.toString();
            }
        }
        return string;
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        DLNAAttribute.Type typeValueOfAttributeName;
        if (str.length() != 0) {
            for (String str2 : str.split(";")) {
                String[] strArrSplit = str2.split("=");
                if (strArrSplit.length == 2 && (typeValueOfAttributeName = DLNAAttribute.Type.valueOfAttributeName(strArrSplit[0])) != null) {
                    getValue().put((EnumMap<DLNAAttribute.Type, DLNAAttribute>) typeValueOfAttributeName, (DLNAAttribute.Type) DLNAAttribute.newInstance(typeValueOfAttributeName, strArrSplit[1], ""));
                }
            }
        }
    }
}
