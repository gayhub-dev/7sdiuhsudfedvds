package org.fourthline.cling.model.gena;

import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.fourthline.cling.model.Location;
import org.fourthline.cling.model.Namespace;
import org.fourthline.cling.model.NetworkAddress;
import org.fourthline.cling.model.UnsupportedDataException;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.RemoteService;
import org.fourthline.cling.model.state.StateVariableValue;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class RemoteGENASubscription extends GENASubscription<RemoteService> {
    public PropertyChangeSupport propertyChangeSupport;

    public RemoteGENASubscription(RemoteService remoteService, int i7) {
        super(remoteService, i7);
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public synchronized void end(CancelReason cancelReason, UpnpResponse upnpResponse) {
        ended(cancelReason, upnpResponse);
    }

    public abstract void ended(CancelReason cancelReason, UpnpResponse upnpResponse);

    public synchronized void establish() {
        established();
    }

    public abstract void eventsMissed(int i7);

    public synchronized void fail(UpnpResponse upnpResponse) {
        failed(upnpResponse);
    }

    public abstract void failed(UpnpResponse upnpResponse);

    public synchronized List<URL> getEventCallbackURLs(List<NetworkAddress> list, Namespace namespace) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<NetworkAddress> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new Location(it.next(), namespace.getEventCallbackPathString(getService())).getURL());
        }
        return arrayList;
    }

    public synchronized URL getEventSubscriptionURL() {
        return getService().getDevice().normalizeURI(getService().getEventSubscriptionURI());
    }

    public abstract void invalidMessage(UnsupportedDataException unsupportedDataException);

    public synchronized void receive(UnsignedIntegerFourBytes unsignedIntegerFourBytes, Collection<StateVariableValue> collection) {
        UnsignedIntegerFourBytes unsignedIntegerFourBytes2 = this.currentSequence;
        if (unsignedIntegerFourBytes2 != null) {
            if (unsignedIntegerFourBytes2.getValue().equals(Long.valueOf(this.currentSequence.getBits().getMaxValue())) && unsignedIntegerFourBytes.getValue().longValue() == 1) {
                System.err.println("TODO: HANDLE ROLLOVER");
                return;
            } else {
                if (this.currentSequence.getValue().longValue() >= unsignedIntegerFourBytes.getValue().longValue()) {
                    return;
                }
                int iLongValue = (int) (unsignedIntegerFourBytes.getValue().longValue() - (this.currentSequence.getValue().longValue() + 1));
                if (iLongValue != 0) {
                    eventsMissed(iLongValue);
                }
            }
        }
        this.currentSequence = unsignedIntegerFourBytes;
        for (StateVariableValue stateVariableValue : collection) {
            this.currentValues.put(stateVariableValue.getStateVariable().getName(), stateVariableValue);
        }
        eventReceived();
    }

    @Override // org.fourthline.cling.model.gena.GENASubscription
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(SID: ");
        sbM112a.append(getSubscriptionId());
        sbM112a.append(") ");
        sbM112a.append(getService());
        return sbM112a.toString();
    }
}
