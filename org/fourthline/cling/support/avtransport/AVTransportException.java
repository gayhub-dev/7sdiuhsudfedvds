package org.fourthline.cling.support.avtransport;

import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.types.ErrorCode;

/* loaded from: classes.dex */
public class AVTransportException extends ActionException {
    public AVTransportException(int i7, String str) {
        super(i7, str);
    }

    public AVTransportException(int i7, String str, Throwable th) {
        super(i7, str, th);
    }

    public AVTransportException(ErrorCode errorCode, String str) {
        super(errorCode, str);
    }

    public AVTransportException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AVTransportException(AVTransportErrorCode aVTransportErrorCode, String str) {
        super(aVTransportErrorCode.getCode(), aVTransportErrorCode.getDescription() + ". " + str + ".");
    }

    public AVTransportException(AVTransportErrorCode aVTransportErrorCode) {
        super(aVTransportErrorCode.getCode(), aVTransportErrorCode.getDescription());
    }
}
