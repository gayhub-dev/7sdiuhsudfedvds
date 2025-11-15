package org.fourthline.cling.support.renderingcontrol;

import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.types.ErrorCode;

/* loaded from: classes.dex */
public class RenderingControlException extends ActionException {
    public RenderingControlException(int i7, String str) {
        super(i7, str);
    }

    public RenderingControlException(int i7, String str, Throwable th) {
        super(i7, str, th);
    }

    public RenderingControlException(ErrorCode errorCode, String str) {
        super(errorCode, str);
    }

    public RenderingControlException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RenderingControlException(RenderingControlErrorCode renderingControlErrorCode, String str) {
        super(renderingControlErrorCode.getCode(), renderingControlErrorCode.getDescription() + ". " + str + ".");
    }

    public RenderingControlException(RenderingControlErrorCode renderingControlErrorCode) {
        super(renderingControlErrorCode.getCode(), renderingControlErrorCode.getDescription());
    }
}
