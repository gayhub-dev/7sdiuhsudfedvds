package org.fourthline.cling.model.types;

import java.util.logging.Logger;
import okhttp3.internal.p124ws.WebSocketProtocol;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class UnsignedVariableInteger {
    private static final Logger log = Logger.getLogger(UnsignedVariableInteger.class.getName());
    public long value;

    public enum Bits {
        EIGHT(255),
        SIXTEEN(WebSocketProtocol.PAYLOAD_SHORT_MAX),
        TWENTYFOUR(16777215),
        THIRTYTWO(4294967295L);

        private long maxValue;

        Bits(long j7) {
            this.maxValue = j7;
        }

        public long getMaxValue() {
            return this.maxValue;
        }
    }

    public UnsignedVariableInteger() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.value == ((UnsignedVariableInteger) obj).value;
    }

    public abstract Bits getBits();

    public int getMinValue() {
        return 0;
    }

    public Long getValue() {
        return Long.valueOf(this.value);
    }

    public int hashCode() {
        long j7 = this.value;
        return (int) (j7 ^ (j7 >>> 32));
    }

    public UnsignedVariableInteger increment(boolean z6) {
        if (this.value + 1 > getBits().getMaxValue()) {
            this.value = z6 ? 1L : 0L;
        } else {
            this.value++;
        }
        return this;
    }

    public void isInRange(long j7) {
        if (j7 < getMinValue() || j7 > getBits().getMaxValue()) {
            StringBuilder sbM112a = C0413b.m112a("Value must be between ");
            sbM112a.append(getMinValue());
            sbM112a.append(" and ");
            sbM112a.append(getBits().getMaxValue());
            sbM112a.append(": ");
            sbM112a.append(j7);
            throw new NumberFormatException(sbM112a.toString());
        }
    }

    public UnsignedVariableInteger setValue(long j7) {
        isInRange(j7);
        this.value = j7;
        return this;
    }

    public String toString() {
        return Long.toString(this.value);
    }

    public UnsignedVariableInteger(long j7) {
        setValue(j7);
    }

    public UnsignedVariableInteger(String str) {
        if (str.startsWith("-")) {
            log.warning("Invalid negative integer value '" + str + "', assuming value 0!");
            str = "0";
        }
        setValue(Long.parseLong(str.trim()));
    }
}
