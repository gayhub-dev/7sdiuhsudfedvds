package org.fourthline.cling.support.contentdirectory.callback;

import org.fourthline.cling.controlpoint.ActionCallback;
import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.ErrorCode;

/* loaded from: classes.dex */
public abstract class GetSystemUpdateID extends ActionCallback {
    public GetSystemUpdateID(Service service) {
        super(new ActionInvocation(service.getAction("GetSystemUpdateID")));
    }

    public abstract void received(ActionInvocation actionInvocation, long j7);

    @Override // org.fourthline.cling.controlpoint.ActionCallback
    public void success(ActionInvocation actionInvocation) {
        boolean z6;
        long jLongValue;
        try {
            jLongValue = Long.valueOf(actionInvocation.getOutput("Id").getValue().toString()).longValue();
            z6 = true;
        } catch (Exception e7) {
            actionInvocation.setFailure(new ActionException(ErrorCode.ACTION_FAILED, "Can't parse GetSystemUpdateID response: " + e7, e7));
            failure(actionInvocation, null);
            z6 = false;
            jLongValue = 0;
        }
        if (z6) {
            received(actionInvocation, jLongValue);
        }
    }
}
