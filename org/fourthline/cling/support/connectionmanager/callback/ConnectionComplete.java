package org.fourthline.cling.support.connectionmanager.callback;

import org.fourthline.cling.controlpoint.ActionCallback;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.meta.Service;

/* loaded from: classes.dex */
public abstract class ConnectionComplete extends ActionCallback {
    public ConnectionComplete(Service service, int i7) {
        this(service, null, i7);
    }

    public ConnectionComplete(Service service, ControlPoint controlPoint, int i7) {
        super(new ActionInvocation(service.getAction("ConnectionComplete")), controlPoint);
        getActionInvocation().setInput("ConnectionID", Integer.valueOf(i7));
    }
}
