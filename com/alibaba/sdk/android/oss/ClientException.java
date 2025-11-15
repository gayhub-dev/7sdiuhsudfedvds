package com.alibaba.sdk.android.oss;

import android.arch.lifecycle.C0063n;
import com.alibaba.sdk.android.oss.common.OSSLog;

/* loaded from: classes.dex */
public class ClientException extends Exception {
    private Boolean canceled;

    public ClientException() {
        this.canceled = Boolean.FALSE;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (getCause() == null) {
            return message;
        }
        return getCause().getMessage() + "\n" + message;
    }

    public Boolean isCanceledException() {
        return this.canceled;
    }

    public ClientException(String str) {
        super(C0063n.m88a("[ErrorMessage]: ", str));
        this.canceled = Boolean.FALSE;
    }

    public ClientException(Throwable th) {
        super(th);
        this.canceled = Boolean.FALSE;
    }

    public ClientException(String str, Throwable th) {
        this(str, th, Boolean.FALSE);
    }

    public ClientException(String str, Throwable th, Boolean bool) {
        super(C0063n.m88a("[ErrorMessage]: ", str), th);
        this.canceled = Boolean.FALSE;
        this.canceled = bool;
        OSSLog.logThrowable2Local(this);
    }
}
