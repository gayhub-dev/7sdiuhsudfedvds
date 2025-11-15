package org.fourthline.cling.controlpoint;

import java.util.concurrent.Future;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpServiceConfiguration;
import org.fourthline.cling.controlpoint.event.ExecuteAction;
import org.fourthline.cling.controlpoint.event.Search;
import org.fourthline.cling.model.message.header.MXHeader;
import org.fourthline.cling.model.message.header.STAllHeader;
import org.fourthline.cling.model.message.header.UpnpHeader;
import org.fourthline.cling.protocol.ProtocolFactory;
import org.fourthline.cling.registry.Registry;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ControlPointImpl implements ControlPoint {
    private static Logger log = Logger.getLogger(ControlPointImpl.class.getName());
    public UpnpServiceConfiguration configuration;
    public ProtocolFactory protocolFactory;
    public Registry registry;

    public ControlPointImpl() {
    }

    public void execute(ExecuteAction executeAction) {
        execute(executeAction.getCallback());
    }

    @Override // org.fourthline.cling.controlpoint.ControlPoint
    public UpnpServiceConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // org.fourthline.cling.controlpoint.ControlPoint
    public ProtocolFactory getProtocolFactory() {
        return this.protocolFactory;
    }

    @Override // org.fourthline.cling.controlpoint.ControlPoint
    public Registry getRegistry() {
        return this.registry;
    }

    public void search(Search search) {
        search(search.getSearchType(), search.getMxSeconds());
    }

    public ControlPointImpl(UpnpServiceConfiguration upnpServiceConfiguration, ProtocolFactory protocolFactory, Registry registry) {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Creating ControlPoint: ");
        sbM112a.append(getClass().getName());
        logger.fine(sbM112a.toString());
        this.configuration = upnpServiceConfiguration;
        this.protocolFactory = protocolFactory;
        this.registry = registry;
    }

    @Override // org.fourthline.cling.controlpoint.ControlPoint
    public Future execute(ActionCallback actionCallback) {
        log.fine("Invoking action in background: " + actionCallback);
        actionCallback.setControlPoint(this);
        return getConfiguration().getSyncProtocolExecutorService().submit(actionCallback);
    }

    @Override // org.fourthline.cling.controlpoint.ControlPoint
    public void search() {
        search(new STAllHeader(), MXHeader.DEFAULT_VALUE.intValue());
    }

    @Override // org.fourthline.cling.controlpoint.ControlPoint
    public void search(UpnpHeader upnpHeader) {
        search(upnpHeader, MXHeader.DEFAULT_VALUE.intValue());
    }

    @Override // org.fourthline.cling.controlpoint.ControlPoint
    public void search(int i7) {
        search(new STAllHeader(), i7);
    }

    @Override // org.fourthline.cling.controlpoint.ControlPoint
    public void search(UpnpHeader upnpHeader, int i7) {
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Sending asynchronous search for: ");
        sbM112a.append(upnpHeader.getString());
        logger.fine(sbM112a.toString());
        getConfiguration().getAsyncProtocolExecutor().execute(getProtocolFactory().createSendingSearch(upnpHeader, i7));
    }

    @Override // org.fourthline.cling.controlpoint.ControlPoint
    public void execute(SubscriptionCallback subscriptionCallback) {
        log.fine("Invoking subscription in background: " + subscriptionCallback);
        subscriptionCallback.setControlPoint(this);
        getConfiguration().getSyncProtocolExecutorService().execute(subscriptionCallback);
    }
}
