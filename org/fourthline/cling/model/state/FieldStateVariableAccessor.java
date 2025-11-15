package org.fourthline.cling.model.state;

import java.lang.reflect.Field;
import p186x2.C2074b;

/* loaded from: classes.dex */
public class FieldStateVariableAccessor extends StateVariableAccessor {
    public Field field;

    public FieldStateVariableAccessor(Field field) {
        this.field = field;
    }

    public Field getField() {
        return this.field;
    }

    @Override // org.fourthline.cling.model.state.StateVariableAccessor
    public Class<?> getReturnType() {
        return getField().getType();
    }

    @Override // org.fourthline.cling.model.state.StateVariableAccessor
    public Object read(Object obj) {
        Field field = this.field;
        boolean zIsAccessible = field.isAccessible();
        try {
            try {
                field.setAccessible(true);
                return field.get(obj);
            } catch (IllegalArgumentException e7) {
                throw new IllegalArgumentException("Could not get field value by reflection: " + C2074b.m2471K(field) + " on: " + obj.getClass().getName(), e7);
            }
        } finally {
            field.setAccessible(zIsAccessible);
        }
    }

    @Override // org.fourthline.cling.model.state.StateVariableAccessor
    public String toString() {
        return super.toString() + " Field: " + getField();
    }
}
