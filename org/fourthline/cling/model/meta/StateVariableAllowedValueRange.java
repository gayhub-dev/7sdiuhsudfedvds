package org.fourthline.cling.model.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.fourthline.cling.model.Validatable;
import org.fourthline.cling.model.ValidationError;
import p009b.C0413b;

/* loaded from: classes.dex */
public class StateVariableAllowedValueRange implements Validatable {
    private static final Logger log = Logger.getLogger(StateVariableAllowedValueRange.class.getName());
    private final long maximum;
    private final long minimum;
    private final long step;

    public StateVariableAllowedValueRange(long j7, long j8) {
        this(j7, j8, 1L);
    }

    public long getMaximum() {
        return this.maximum;
    }

    public long getMinimum() {
        return this.minimum;
    }

    public long getStep() {
        return this.step;
    }

    public boolean isInRange(long j7) {
        return j7 >= getMinimum() && j7 <= getMaximum() && j7 % this.step == 0;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("Range Min: ");
        sbM112a.append(getMinimum());
        sbM112a.append(" Max: ");
        sbM112a.append(getMaximum());
        sbM112a.append(" Step: ");
        sbM112a.append(getStep());
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.Validatable
    public List<ValidationError> validate() {
        return new ArrayList();
    }

    public StateVariableAllowedValueRange(long j7, long j8, long j9) {
        if (j7 > j8) {
            log.warning("UPnP specification violation, allowed value range minimum '" + j7 + "' is greater than maximum '" + j8 + "', switching values.");
            this.minimum = j8;
            this.maximum = j7;
        } else {
            this.minimum = j7;
            this.maximum = j8;
        }
        this.step = j9;
    }
}
