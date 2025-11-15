package com.alibaba.sdk.android.oss.model;

/* loaded from: classes.dex */
public class PartETag {
    private long crc64;
    private String eTag;
    private int partNumber;
    private long partSize;

    public PartETag(int i7, String str) {
        setPartNumber(i7);
        setETag(str);
    }

    public long getCRC64() {
        return this.crc64;
    }

    public String getETag() {
        return this.eTag;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public long getPartSize() {
        return this.partSize;
    }

    public void setCRC64(long j7) {
        this.crc64 = j7;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    public void setPartNumber(int i7) {
        this.partNumber = i7;
    }

    public void setPartSize(long j7) {
        this.partSize = j7;
    }
}
