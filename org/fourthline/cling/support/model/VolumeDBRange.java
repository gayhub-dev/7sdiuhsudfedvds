package org.fourthline.cling.support.model;

/* loaded from: classes.dex */
public class VolumeDBRange {
    private Integer maxValue;
    private Integer minValue;

    public VolumeDBRange(Integer num, Integer num2) {
        this.minValue = num;
        this.maxValue = num2;
    }

    public Integer getMaxValue() {
        return this.maxValue;
    }

    public Integer getMinValue() {
        return this.minValue;
    }
}
