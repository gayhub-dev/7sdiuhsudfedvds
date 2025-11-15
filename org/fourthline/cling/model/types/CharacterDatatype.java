package org.fourthline.cling.model.types;

/* loaded from: classes.dex */
public class CharacterDatatype extends AbstractDatatype<Character> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public boolean isHandlingJavaType(Class cls) {
        return cls == Character.TYPE || Character.class.isAssignableFrom(cls);
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public Character valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }
}
