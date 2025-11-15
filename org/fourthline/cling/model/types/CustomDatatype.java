package org.fourthline.cling.model.types;

import p009b.C0413b;

/* loaded from: classes.dex */
public class CustomDatatype extends AbstractDatatype<String> {
    private String name;

    public CustomDatatype(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("(");
        sbM112a.append(getClass().getSimpleName());
        sbM112a.append(") '");
        sbM112a.append(getName());
        sbM112a.append("'");
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public String valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        return str;
    }
}
