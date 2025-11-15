package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;
import android.support.v7.widget.ActivityChooserView;
import p009b.C0413b;

/* loaded from: classes.dex */
public class IntegerDatatype extends AbstractDatatype<Integer> {
    private int byteSize;

    public IntegerDatatype(int i7) {
        this.byteSize = i7;
    }

    public int getByteSize() {
        return this.byteSize;
    }

    public int getMaxValue() {
        int byteSize = getByteSize();
        if (byteSize == 1) {
            return 127;
        }
        if (byteSize == 2) {
            return 32767;
        }
        if (byteSize == 4) {
            return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        StringBuilder sbM112a = C0413b.m112a("Invalid integer byte size: ");
        sbM112a.append(getByteSize());
        throw new IllegalArgumentException(sbM112a.toString());
    }

    public int getMinValue() {
        int byteSize = getByteSize();
        if (byteSize == 1) {
            return -128;
        }
        if (byteSize == 2) {
            return -32768;
        }
        if (byteSize == 4) {
            return Integer.MIN_VALUE;
        }
        StringBuilder sbM112a = C0413b.m112a("Invalid integer byte size: ");
        sbM112a.append(getByteSize());
        throw new IllegalArgumentException(sbM112a.toString());
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public boolean isHandlingJavaType(Class cls) {
        return cls == Integer.TYPE || Integer.class.isAssignableFrom(cls);
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public boolean isValid(Integer num) {
        return num == null || (num.intValue() >= getMinValue() && num.intValue() <= getMaxValue());
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public Integer valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        try {
            Integer numValueOf = Integer.valueOf(Integer.parseInt(str.trim()));
            if (isValid(numValueOf)) {
                return numValueOf;
            }
            throw new InvalidValueException("Not a " + getByteSize() + " byte(s) integer: " + str);
        } catch (NumberFormatException e7) {
            if (str.equals("NOT_IMPLEMENTED")) {
                return Integer.valueOf(getMaxValue());
            }
            throw new InvalidValueException(C0063n.m88a("Can't convert string to number: ", str), e7);
        }
    }
}
