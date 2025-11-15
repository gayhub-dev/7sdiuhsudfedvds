package org.fourthline.cling.support.lastchange;

import java.lang.Enum;
import java.util.Map;
import org.fourthline.cling.model.ModelUtil;
import org.fourthline.cling.model.types.Datatype;

/* loaded from: classes.dex */
public abstract class EventedValueEnumArray<E extends Enum> extends EventedValue<E[]> {
    public EventedValueEnumArray(E[] eArr) {
        super(eArr);
    }

    public abstract E[] enumValueOf(String[] strArr);

    @Override // org.fourthline.cling.support.lastchange.EventedValue
    public Datatype getDatatype() {
        return null;
    }

    @Override // org.fourthline.cling.support.lastchange.EventedValue
    public String toString() {
        return ModelUtil.toCommaSeparatedList(getValue());
    }

    public EventedValueEnumArray(Map.Entry<String, String>[] entryArr) {
        super(entryArr);
    }

    @Override // org.fourthline.cling.support.lastchange.EventedValue
    public E[] valueOf(String str) {
        return (E[]) enumValueOf(ModelUtil.fromCommaSeparatedList(str));
    }
}
