package org.fourthline.cling.support.contentdirectory;

import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.types.ErrorCode;

/* loaded from: classes.dex */
public class ContentDirectoryException extends ActionException {
    public ContentDirectoryException(int i7, String str) {
        super(i7, str);
    }

    public ContentDirectoryException(int i7, String str, Throwable th) {
        super(i7, str, th);
    }

    public ContentDirectoryException(ErrorCode errorCode, String str) {
        super(errorCode, str);
    }

    public ContentDirectoryException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ContentDirectoryException(ContentDirectoryErrorCode contentDirectoryErrorCode, String str) {
        super(contentDirectoryErrorCode.getCode(), contentDirectoryErrorCode.getDescription() + ". " + str + ".");
    }

    public ContentDirectoryException(ContentDirectoryErrorCode contentDirectoryErrorCode) {
        super(contentDirectoryErrorCode.getCode(), contentDirectoryErrorCode.getDescription());
    }
}
