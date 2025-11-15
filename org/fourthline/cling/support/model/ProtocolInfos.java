package org.fourthline.cling.support.model;

import java.util.ArrayList;
import org.fourthline.cling.model.ModelUtil;

/* loaded from: classes.dex */
public class ProtocolInfos extends ArrayList<ProtocolInfo> {
    public ProtocolInfos(ProtocolInfo... protocolInfoArr) {
        for (ProtocolInfo protocolInfo : protocolInfoArr) {
            add(protocolInfo);
        }
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return ModelUtil.toCommaSeparatedList(toArray(new ProtocolInfo[size()]));
    }

    public ProtocolInfos(String str) {
        String[] strArrFromCommaSeparatedList = ModelUtil.fromCommaSeparatedList(str);
        if (strArrFromCommaSeparatedList != null) {
            for (String str2 : strArrFromCommaSeparatedList) {
                add(new ProtocolInfo(str2));
            }
        }
    }
}
