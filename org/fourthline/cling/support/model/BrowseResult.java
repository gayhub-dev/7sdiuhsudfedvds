package org.fourthline.cling.support.model;

import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;

/* loaded from: classes.dex */
public class BrowseResult {
    public UnsignedIntegerFourBytes containerUpdateID;
    public UnsignedIntegerFourBytes count;
    public String result;
    public UnsignedIntegerFourBytes totalMatches;

    public BrowseResult(String str, UnsignedIntegerFourBytes unsignedIntegerFourBytes, UnsignedIntegerFourBytes unsignedIntegerFourBytes2, UnsignedIntegerFourBytes unsignedIntegerFourBytes3) {
        this.result = str;
        this.count = unsignedIntegerFourBytes;
        this.totalMatches = unsignedIntegerFourBytes2;
        this.containerUpdateID = unsignedIntegerFourBytes3;
    }

    public UnsignedIntegerFourBytes getContainerUpdateID() {
        return this.containerUpdateID;
    }

    public long getContainerUpdateIDLong() {
        return this.containerUpdateID.getValue().longValue();
    }

    public UnsignedIntegerFourBytes getCount() {
        return this.count;
    }

    public long getCountLong() {
        return this.count.getValue().longValue();
    }

    public String getResult() {
        return this.result;
    }

    public UnsignedIntegerFourBytes getTotalMatches() {
        return this.totalMatches;
    }

    public long getTotalMatchesLong() {
        return this.totalMatches.getValue().longValue();
    }

    public BrowseResult(String str, long j7, long j8) {
        this(str, j7, j8, 0L);
    }

    public BrowseResult(String str, long j7, long j8, long j9) {
        this(str, new UnsignedIntegerFourBytes(j7), new UnsignedIntegerFourBytes(j8), new UnsignedIntegerFourBytes(j9));
    }
}
