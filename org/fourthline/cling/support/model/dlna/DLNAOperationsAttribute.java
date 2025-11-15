package org.fourthline.cling.support.model.dlna;

import android.arch.lifecycle.C0063n;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes.dex */
public class DLNAOperationsAttribute extends DLNAAttribute<EnumSet<DLNAOperations>> {
    public DLNAOperationsAttribute() {
        setValue(EnumSet.of(DLNAOperations.NONE));
    }

    @Override // org.fourthline.cling.support.model.dlna.DLNAAttribute
    public String getString() {
        int code = DLNAOperations.NONE.getCode();
        Iterator it = getValue().iterator();
        while (it.hasNext()) {
            code |= ((DLNAOperations) it.next()).getCode();
        }
        return String.format(Locale.ROOT, "%02x", Integer.valueOf(code));
    }

    @Override // org.fourthline.cling.support.model.dlna.DLNAAttribute
    public void setString(String str, String str2) throws NumberFormatException {
        EnumSet enumSetNoneOf = EnumSet.noneOf(DLNAOperations.class);
        try {
            int i7 = Integer.parseInt(str, 16);
            for (DLNAOperations dLNAOperations : DLNAOperations.values()) {
                int code = dLNAOperations.getCode() & i7;
                if (dLNAOperations != DLNAOperations.NONE && dLNAOperations.getCode() == code) {
                    enumSetNoneOf.add(dLNAOperations);
                }
            }
        } catch (NumberFormatException unused) {
        }
        if (enumSetNoneOf.isEmpty()) {
            throw new InvalidDLNAProtocolAttributeException(C0063n.m88a("Can't parse DLNA operations integer from: ", str));
        }
        setValue(enumSetNoneOf);
    }

    public DLNAOperationsAttribute(DLNAOperations... dLNAOperationsArr) {
        if (dLNAOperationsArr == null || dLNAOperationsArr.length <= 0) {
            return;
        }
        DLNAOperations dLNAOperations = dLNAOperationsArr[0];
        if (dLNAOperationsArr.length > 1) {
            System.arraycopy(dLNAOperationsArr, 1, dLNAOperationsArr, 0, dLNAOperationsArr.length - 1);
            setValue(EnumSet.of(dLNAOperations, dLNAOperationsArr));
        } else {
            setValue(EnumSet.of(dLNAOperations));
        }
    }
}
