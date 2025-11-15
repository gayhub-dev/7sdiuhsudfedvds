package org.fourthline.cling.controlpoint;

import android.support.constraint.motion.C0080b;
import org.fourthline.cling.model.action.ActionException;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.message.control.IncomingActionResponseMessage;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.RemoteService;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.protocol.sync.SendingAction;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class ActionCallback implements Runnable {
    public final ActionInvocation actionInvocation;
    public ControlPoint controlPoint;

    public static final class Default extends ActionCallback {
        public Default(ActionInvocation actionInvocation, ControlPoint controlPoint) {
            super(actionInvocation, controlPoint);
        }

        @Override // org.fourthline.cling.controlpoint.ActionCallback
        public void failure(ActionInvocation actionInvocation, UpnpResponse upnpResponse, String str) {
        }

        @Override // org.fourthline.cling.controlpoint.ActionCallback
        public void success(ActionInvocation actionInvocation) {
        }
    }

    public ActionCallback(ActionInvocation actionInvocation, ControlPoint controlPoint) {
        this.actionInvocation = actionInvocation;
        this.controlPoint = controlPoint;
    }

    public String createDefaultFailureMessage(ActionInvocation actionInvocation, UpnpResponse upnpResponse) {
        ActionException failure = actionInvocation.getFailure();
        String string = "Error: ";
        if (failure != null) {
            StringBuilder sbM112a = C0413b.m112a("Error: ");
            sbM112a.append(failure.getMessage());
            string = sbM112a.toString();
        }
        if (upnpResponse == null) {
            return string;
        }
        StringBuilder sbM94a = C0080b.m94a(string, " (HTTP response was: ");
        sbM94a.append(upnpResponse.getResponseDetails());
        sbM94a.append(")");
        return sbM94a.toString();
    }

    public void failure(ActionInvocation actionInvocation, UpnpResponse upnpResponse) {
        failure(actionInvocation, upnpResponse, createDefaultFailureMessage(actionInvocation, upnpResponse));
    }

    public abstract void failure(ActionInvocation actionInvocation, UpnpResponse upnpResponse, String str);

    public ActionInvocation getActionInvocation() {
        return this.actionInvocation;
    }

    public synchronized ControlPoint getControlPoint() {
        return this.controlPoint;
    }

    @Override // java.lang.Runnable
    public void run() {
        Service service = this.actionInvocation.getAction().getService();
        if (service instanceof LocalService) {
            ((LocalService) service).getExecutor(this.actionInvocation.getAction()).execute(this.actionInvocation);
            if (this.actionInvocation.getFailure() != null) {
                failure(this.actionInvocation, null);
                return;
            } else {
                success(this.actionInvocation);
                return;
            }
        }
        if (service instanceof RemoteService) {
            if (getControlPoint() == null) {
                throw new IllegalStateException("Callback must be executed through ControlPoint");
            }
            RemoteService remoteService = (RemoteService) service;
            try {
                SendingAction sendingActionCreateSendingAction = getControlPoint().getProtocolFactory().createSendingAction(this.actionInvocation, remoteService.getDevice().normalizeURI(remoteService.getControlURI()));
                sendingActionCreateSendingAction.run();
                IncomingActionResponseMessage outputMessage = sendingActionCreateSendingAction.getOutputMessage();
                if (outputMessage == null) {
                    failure(this.actionInvocation, null);
                } else if (outputMessage.getOperation().isFailed()) {
                    failure(this.actionInvocation, outputMessage.getOperation());
                } else {
                    success(this.actionInvocation);
                }
            } catch (IllegalArgumentException unused) {
                ActionInvocation actionInvocation = this.actionInvocation;
                StringBuilder sbM112a = C0413b.m112a("bad control URL: ");
                sbM112a.append(remoteService.getControlURI());
                failure(actionInvocation, null, sbM112a.toString());
            }
        }
    }

    public synchronized ActionCallback setControlPoint(ControlPoint controlPoint) {
        this.controlPoint = controlPoint;
        return this;
    }

    public abstract void success(ActionInvocation actionInvocation);

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(ActionCallback) ");
        sbM112a.append(this.actionInvocation);
        return sbM112a.toString();
    }

    public ActionCallback(ActionInvocation actionInvocation) {
        this.actionInvocation = actionInvocation;
    }
}
