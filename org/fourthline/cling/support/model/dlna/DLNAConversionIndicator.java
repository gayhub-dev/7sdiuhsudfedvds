package org.fourthline.cling.support.model.dlna;

/* loaded from: classes.dex */
public enum DLNAConversionIndicator {
    NONE(0),
    TRANSCODED(1);

    private int code;

    DLNAConversionIndicator(int i7) {
        this.code = i7;
    }

    public int getCode() {
        return this.code;
    }

    public static DLNAConversionIndicator valueOf(int i7) {
        for (DLNAConversionIndicator dLNAConversionIndicator : values()) {
            if (dLNAConversionIndicator.getCode() == i7) {
                return dLNAConversionIndicator;
            }
        }
        return null;
    }
}
