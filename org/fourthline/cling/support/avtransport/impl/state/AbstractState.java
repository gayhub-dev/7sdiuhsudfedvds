package org.fourthline.cling.support.avtransport.impl.state;

import org.fourthline.cling.support.model.AVTransport;
import org.fourthline.cling.support.model.TransportAction;

/* loaded from: classes.dex */
public abstract class AbstractState<T extends AVTransport> {
    private T transport;

    public AbstractState(T t6) {
        this.transport = t6;
    }

    public abstract TransportAction[] getCurrentTransportActions();

    public T getTransport() {
        return this.transport;
    }
}
