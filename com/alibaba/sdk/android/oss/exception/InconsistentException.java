package com.alibaba.sdk.android.oss.exception;

import java.io.IOException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class InconsistentException extends IOException {
    private Long clientChecksum;
    private String requestId;
    private Long serverChecksum;

    public InconsistentException(Long l7, Long l8, String str) {
        this.clientChecksum = l7;
        this.serverChecksum = l8;
        this.requestId = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sbM112a = C0413b.m112a("InconsistentException: inconsistent object\n[RequestId]: ");
        sbM112a.append(this.requestId);
        sbM112a.append("\n[ClientChecksum]: ");
        sbM112a.append(this.clientChecksum);
        sbM112a.append("\n[ServerChecksum]: ");
        sbM112a.append(this.serverChecksum);
        return sbM112a.toString();
    }
}
