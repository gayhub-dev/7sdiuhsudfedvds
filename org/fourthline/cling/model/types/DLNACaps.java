package org.fourthline.cling.model.types;

import java.util.Arrays;
import org.fourthline.cling.model.ModelUtil;

/* loaded from: classes.dex */
public class DLNACaps {
    public final String[] caps;

    public DLNACaps(String[] strArr) {
        this.caps = strArr;
    }

    public static DLNACaps valueOf(String str) {
        if (str == null || str.length() == 0) {
            return new DLNACaps(new String[0]);
        }
        String[] strArrSplit = str.split(",");
        String[] strArr = new String[strArrSplit.length];
        for (int i7 = 0; i7 < strArrSplit.length; i7++) {
            strArr[i7] = strArrSplit[i7].trim();
        }
        return new DLNACaps(strArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Arrays.equals(this.caps, ((DLNACaps) obj).caps);
    }

    public String[] getCaps() {
        return this.caps;
    }

    public int hashCode() {
        return Arrays.hashCode(this.caps);
    }

    public String toString() {
        return ModelUtil.toCommaSeparatedList(getCaps());
    }
}
