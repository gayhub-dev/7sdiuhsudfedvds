package org.fourthline.cling.model.gena;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.model.ServiceManager;
import org.fourthline.cling.model.meta.LocalService;
import org.fourthline.cling.model.meta.StateVariable;
import org.fourthline.cling.model.state.StateVariableValue;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import p009b.C0413b;
import p186x2.C2074b;

/* loaded from: classes.dex */
public abstract class LocalGENASubscription extends GENASubscription<LocalService> implements PropertyChangeListener {
    private static Logger log = Logger.getLogger(LocalGENASubscription.class.getName());
    public final List<URL> callbackURLs;
    public final Map<String, Long> lastSentNumericValue;
    public final Map<String, Long> lastSentTimestamp;

    public LocalGENASubscription(LocalService localService, List<URL> list) {
        super(localService);
        this.lastSentTimestamp = new HashMap();
        this.lastSentNumericValue = new HashMap();
        this.callbackURLs = list;
    }

    public synchronized void end(CancelReason cancelReason) {
        try {
            getService().getManager().getPropertyChangeSupport().removePropertyChangeListener(this);
        } catch (Exception e7) {
            log.warning("Removal of local service property change listener failed: " + C2074b.m2475O(e7));
        }
        ended(cancelReason);
    }

    public abstract void ended(CancelReason cancelReason);

    public synchronized void establish() {
        established();
    }

    public synchronized List<URL> getCallbackURLs() {
        return this.callbackURLs;
    }

    public synchronized void incrementSequence() {
        this.currentSequence.increment(true);
    }

    public synchronized Set<String> moderateStateVariables(long j7, Collection<StateVariableValue> collection) {
        HashSet hashSet;
        hashSet = new HashSet();
        for (StateVariableValue stateVariableValue : collection) {
            StateVariable stateVariable = stateVariableValue.getStateVariable();
            String name = stateVariableValue.getStateVariable().getName();
            if (stateVariable.getEventDetails().getEventMaximumRateMilliseconds() == 0 && stateVariable.getEventDetails().getEventMinimumDelta() == 0) {
                log.finer("Variable is not moderated: " + stateVariable);
            } else if (!this.lastSentTimestamp.containsKey(name)) {
                log.finer("Variable is moderated but was never sent before: " + stateVariable);
            } else if (stateVariable.getEventDetails().getEventMaximumRateMilliseconds() > 0 && j7 <= this.lastSentTimestamp.get(name).longValue() + stateVariable.getEventDetails().getEventMaximumRateMilliseconds()) {
                log.finer("Excluding state variable with maximum rate: " + stateVariable);
                hashSet.add(name);
            } else if (stateVariable.isModeratedNumericType() && this.lastSentNumericValue.get(name) != null) {
                long jLongValue = Long.valueOf(this.lastSentNumericValue.get(name).longValue()).longValue();
                long jLongValue2 = Long.valueOf(stateVariableValue.toString()).longValue();
                long eventMinimumDelta = stateVariable.getEventDetails().getEventMinimumDelta();
                if (jLongValue2 > jLongValue && jLongValue2 - jLongValue < eventMinimumDelta) {
                    log.finer("Excluding state variable with minimum delta: " + stateVariable);
                    hashSet.add(name);
                } else if (jLongValue2 < jLongValue && jLongValue - jLongValue2 < eventMinimumDelta) {
                    log.finer("Excluding state variable with minimum delta: " + stateVariable);
                    hashSet.add(name);
                }
            }
        }
        return hashSet;
    }

    @Override // java.beans.PropertyChangeListener
    public synchronized void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals(ServiceManager.EVENTED_STATE_VARIABLES)) {
            log.fine("Eventing triggered, getting state for subscription: " + getSubscriptionId());
            long time = new Date().getTime();
            Collection<StateVariableValue> collection = (Collection) propertyChangeEvent.getNewValue();
            Set<String> setModerateStateVariables = moderateStateVariables(time, collection);
            this.currentValues.clear();
            for (StateVariableValue stateVariableValue : collection) {
                String name = stateVariableValue.getStateVariable().getName();
                if (!setModerateStateVariables.contains(name)) {
                    log.fine("Adding state variable value to current values of event: " + stateVariableValue.getStateVariable() + " = " + stateVariableValue);
                    this.currentValues.put(stateVariableValue.getStateVariable().getName(), stateVariableValue);
                    this.lastSentTimestamp.put(name, Long.valueOf(time));
                    if (stateVariableValue.getStateVariable().isModeratedNumericType()) {
                        this.lastSentNumericValue.put(name, Long.valueOf(stateVariableValue.toString()));
                    }
                }
            }
            if (this.currentValues.size() > 0) {
                log.fine("Propagating new state variable values to subscription: " + this);
                eventReceived();
            } else {
                log.fine("No state variable values for event (all moderated out?), not triggering event");
            }
        }
    }

    public synchronized void registerOnService() {
        getService().getManager().getPropertyChangeSupport().addPropertyChangeListener(this);
    }

    public synchronized void setSubscriptionDuration(Integer num) {
        int iIntValue = num == null ? 1800 : num.intValue();
        this.requestedDurationSeconds = iIntValue;
        setActualSubscriptionDurationSeconds(iIntValue);
    }

    public LocalGENASubscription(LocalService localService, Integer num, List<URL> list) {
        super(localService);
        this.lastSentTimestamp = new HashMap();
        this.lastSentNumericValue = new HashMap();
        setSubscriptionDuration(num);
        log.fine("Reading initial state of local service at subscription time");
        long time = new Date().getTime();
        this.currentValues.clear();
        Collection<StateVariableValue> currentState = getService().getManager().getCurrentState();
        Logger logger = log;
        StringBuilder sbM112a = C0413b.m112a("Got evented state variable values: ");
        sbM112a.append(currentState.size());
        logger.finer(sbM112a.toString());
        for (StateVariableValue stateVariableValue : currentState) {
            this.currentValues.put(stateVariableValue.getStateVariable().getName(), stateVariableValue);
            if (log.isLoggable(Level.FINEST)) {
                Logger logger2 = log;
                StringBuilder sbM112a2 = C0413b.m112a("Read state variable value '");
                sbM112a2.append(stateVariableValue.getStateVariable().getName());
                sbM112a2.append("': ");
                sbM112a2.append(stateVariableValue.toString());
                logger2.finer(sbM112a2.toString());
            }
            this.lastSentTimestamp.put(stateVariableValue.getStateVariable().getName(), Long.valueOf(time));
            if (stateVariableValue.getStateVariable().isModeratedNumericType()) {
                this.lastSentNumericValue.put(stateVariableValue.getStateVariable().getName(), Long.valueOf(stateVariableValue.toString()));
            }
        }
        StringBuilder sbM112a3 = C0413b.m112a("uuid:");
        sbM112a3.append(UUID.randomUUID());
        this.subscriptionId = sbM112a3.toString();
        this.currentSequence = new UnsignedIntegerFourBytes(0L);
        this.callbackURLs = list;
    }
}
