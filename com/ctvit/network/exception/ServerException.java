package com.ctvit.network.exception;

/* loaded from: classes.dex */
public class ServerException extends RuntimeException {
    private int errCode;
    private String message;

    public ServerException(int i7, String str) {
        super(str);
        this.errCode = i7;
        this.message = str;
    }

    public int getErrCode() {
        return this.errCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
