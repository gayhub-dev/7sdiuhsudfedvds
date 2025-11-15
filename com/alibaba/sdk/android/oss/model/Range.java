package com.alibaba.sdk.android.oss.model;

import org.fourthline.cling.model.types.BytesRange;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Range {
    public static final long INFINITE = -1;
    private long begin;
    private long end;

    public Range(long j7, long j8) {
        setBegin(j7);
        setEnd(j8);
    }

    public boolean checkIsValid() {
        long j7 = this.begin;
        if (j7 >= -1) {
            long j8 = this.end;
            if (j8 >= -1) {
                return j7 < 0 || j8 < 0 || j7 <= j8;
            }
        }
        return false;
    }

    public long getBegin() {
        return this.begin;
    }

    public long getEnd() {
        return this.end;
    }

    public void setBegin(long j7) {
        this.begin = j7;
    }

    public void setEnd(long j7) {
        this.end = j7;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a(BytesRange.PREFIX);
        long j7 = this.begin;
        sbM112a.append(j7 == -1 ? "" : String.valueOf(j7));
        sbM112a.append("-");
        long j8 = this.end;
        sbM112a.append(j8 != -1 ? String.valueOf(j8) : "");
        return sbM112a.toString();
    }
}
