package org.fourthline.cling.model.types;

import com.alibaba.fastjson.asm.C0532a;
import java.lang.reflect.ParameterizedType;
import org.fourthline.cling.model.types.Datatype;
import p009b.C0413b;

/* loaded from: classes.dex */
public abstract class AbstractDatatype<V> implements Datatype<V> {
    private Datatype.Builtin builtin;

    @Override // org.fourthline.cling.model.types.Datatype
    public Datatype.Builtin getBuiltin() {
        return this.builtin;
    }

    @Override // org.fourthline.cling.model.types.Datatype
    public String getDisplayString() {
        return this instanceof CustomDatatype ? ((CustomDatatype) this).getName() : getBuiltin() != null ? getBuiltin().getDescriptorName() : getValueType().getSimpleName();
    }

    @Override // org.fourthline.cling.model.types.Datatype
    public String getString(V v6) {
        if (v6 == null) {
            return "";
        }
        if (isValid(v6)) {
            return v6.toString();
        }
        throw new InvalidValueException(C0532a.m338a("Value is not valid: ", v6));
    }

    public Class<V> getValueType() {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override // org.fourthline.cling.model.types.Datatype
    public boolean isHandlingJavaType(Class cls) {
        return getValueType().isAssignableFrom(cls);
    }

    @Override // org.fourthline.cling.model.types.Datatype
    public boolean isValid(V v6) {
        return v6 == null || getValueType().isAssignableFrom(v6.getClass());
    }

    public void setBuiltin(Datatype.Builtin builtin) {
        this.builtin = builtin;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(")");
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.types.Datatype
    public V valueOf(String str) {
        return null;
    }
}
