package com.alibaba.sdk.android.oss;

import com.alibaba.sdk.android.oss.common.OSSLog;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ServiceException extends Exception {
    public static final String PARSE_RESPONSE_FAIL = "SDKParseResponseFail";
    private static final long serialVersionUID = 430933593095358673L;
    private String errorCode;
    private String hostId;
    private String partEtag;
    private String partNumber;
    private String rawMessage;
    private String requestId;
    private int statusCode;

    public ServiceException(int i7, String str, String str2, String str3, String str4, String str5) {
        super(str);
        this.statusCode = i7;
        this.errorCode = str2;
        this.requestId = str3;
        this.hostId = str4;
        this.rawMessage = str5;
        OSSLog.logThrowable2Local(this);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getHostId() {
        return this.hostId;
    }

    public String getPartEtag() {
        return this.partEtag;
    }

    public String getPartNumber() {
        return this.partNumber;
    }

    public String getRawMessage() {
        return this.rawMessage;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setPartEtag(String str) {
        this.partEtag = str;
    }

    public void setPartNumber(String str) {
        this.partNumber = str;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("[StatusCode]: ");
        sbM112a.append(this.statusCode);
        sbM112a.append(", [Code]: ");
        sbM112a.append(getErrorCode());
        sbM112a.append(", [Message]: ");
        sbM112a.append(getMessage());
        sbM112a.append(", [Requestid]: ");
        sbM112a.append(getRequestId());
        sbM112a.append(", [HostId]: ");
        sbM112a.append(getHostId());
        sbM112a.append(", [RawMessage]: ");
        sbM112a.append(getRawMessage());
        return sbM112a.toString();
    }
}
