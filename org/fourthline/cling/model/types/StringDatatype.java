package org.fourthline.cling.model.types;

/* loaded from: classes.dex */
public class StringDatatype extends AbstractDatatype<String> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public String valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        return str;
    }
}
