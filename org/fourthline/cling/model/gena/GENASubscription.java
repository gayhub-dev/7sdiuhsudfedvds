package org.fourthline.cling.model.gena;

import java.util.LinkedHashMap;
import java.util.Map;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.state.StateVariableValue;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class GENASubscription<S extends Service> {
    public int actualDurationSeconds;
    public UnsignedIntegerFourBytes currentSequence;
    public Map<String, StateVariableValue<S>> currentValues;
    public int requestedDurationSeconds;
    public S service;
    public String subscriptionId;

    public GENASubscription(S s6) {
        this.requestedDurationSeconds = 1800;
        this.currentValues = new LinkedHashMap();
        this.service = s6;
    }

    public abstract void established();

    public abstract void eventReceived();

    public synchronized int getActualDurationSeconds() {
        return this.actualDurationSeconds;
    }

    public synchronized UnsignedIntegerFourBytes getCurrentSequence() {
        return this.currentSequence;
    }

    public synchronized Map<String, StateVariableValue<S>> getCurrentValues() {
        return this.currentValues;
    }

    public synchronized int getRequestedDurationSeconds() {
        return this.requestedDurationSeconds;
    }

    public synchronized S getService() {
        return this.service;
    }

    public synchronized String getSubscriptionId() {
        return this.subscriptionId;
    }

    public synchronized void setActualSubscriptionDurationSeconds(int i7) {
        this.actualDurationSeconds = i7;
    }

    public synchronized void setSubscriptionId(String str) {
        this.subscriptionId = str;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(GENASubscription, SID: ");
        sbM112a.append(getSubscriptionId());
        sbM112a.append(", SEQUENCE: ");
        sbM112a.append(getCurrentSequence());
        sbM112a.append(")");
        return sbM112a.toString();
    }

    public GENASubscription(S s6, int i7) {
        this(s6);
        this.requestedDurationSeconds = i7;
    }
}
