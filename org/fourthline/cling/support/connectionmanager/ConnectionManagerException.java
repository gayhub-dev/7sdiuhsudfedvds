package org.fourthline.cling.support.connectionmanager;

import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.types.ErrorCode;

/* loaded from: classes.dex */
public class ConnectionManagerException extends ActionException {
    public ConnectionManagerException(int i7, String str) {
        super(i7, str);
    }

    public ConnectionManagerException(int i7, String str, Throwable th) {
        super(i7, str, th);
    }

    public ConnectionManagerException(ErrorCode errorCode, String str) {
        super(errorCode, str);
    }

    public ConnectionManagerException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ConnectionManagerException(ConnectionManagerErrorCode connectionManagerErrorCode, String str) {
        super(connectionManagerErrorCode.getCode(), connectionManagerErrorCode.getDescription() + ". " + str + ".");
    }

    public ConnectionManagerException(ConnectionManagerErrorCode connectionManagerErrorCode) {
        super(connectionManagerErrorCode.getCode(), connectionManagerErrorCode.getDescription());
    }
}
