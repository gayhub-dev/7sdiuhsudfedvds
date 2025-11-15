package org.fourthline.cling.model.meta;

/* loaded from: classes.dex */
public class StateVariableEventDetails {
    private final int eventMaximumRateMilliseconds;
    private final int eventMinimumDelta;
    private final boolean sendEvents;

    public StateVariableEventDetails() {
        this(true, 0, 0);
    }

    public int getEventMaximumRateMilliseconds() {
        return this.eventMaximumRateMilliseconds;
    }

    public int getEventMinimumDelta() {
        return this.eventMinimumDelta;
    }

    public boolean isSendEvents() {
        return this.sendEvents;
    }

    public StateVariableEventDetails(boolean z6) {
        this(z6, 0, 0);
    }

    public StateVariableEventDetails(boolean z6, int i7, int i8) {
        this.sendEvents = z6;
        this.eventMaximumRateMilliseconds = i7;
        this.eventMinimumDelta = i8;
    }
}
