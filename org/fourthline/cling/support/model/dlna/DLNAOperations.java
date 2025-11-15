package org.fourthline.cling.support.model.dlna;

/* loaded from: classes.dex */
public enum DLNAOperations {
    NONE(0),
    RANGE(1),
    TIMESEEK(16);

    private int code;

    DLNAOperations(int i7) {
        this.code = i7;
    }

    public int getCode() {
        return this.code;
    }

    public static DLNAOperations valueOf(int i7) {
        for (DLNAOperations dLNAOperations : values()) {
            if (dLNAOperations.getCode() == i7) {
                return dLNAOperations;
            }
        }
        return null;
    }
}
