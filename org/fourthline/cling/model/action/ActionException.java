package org.fourthline.cling.model.action;

import org.fourthline.cling.model.types.ErrorCode;

/* loaded from: classes.dex */
public class ActionException extends Exception {
    private int errorCode;

    public ActionException(int i7, String str) {
        super(str);
        this.errorCode = i7;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public ActionException(int i7, String str, Throwable th) {
        super(str, th);
        this.errorCode = i7;
    }

    public ActionException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getDescription());
    }

    public ActionException(ErrorCode errorCode, String str) {
        this(errorCode, str, true);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ActionException(ErrorCode errorCode, String str, boolean z6) {
        int code = errorCode.getCode();
        if (z6) {
            str = errorCode.getDescription() + ". " + str + ".";
        }
        this(code, str);
    }

    public ActionException(ErrorCode errorCode, String str, Throwable th) {
        this(errorCode.getCode(), errorCode.getDescription() + ". " + str + ".", th);
    }
}
