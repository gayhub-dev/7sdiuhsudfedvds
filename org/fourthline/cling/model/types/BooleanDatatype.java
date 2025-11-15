package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;
import java.util.Locale;
import okhttp3.internal.cache.DiskLruCache;

/* loaded from: classes.dex */
public class BooleanDatatype extends AbstractDatatype<Boolean> {
    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public boolean isHandlingJavaType(Class cls) {
        return cls == Boolean.TYPE || Boolean.class.isAssignableFrom(cls);
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public String getString(Boolean bool) {
        return bool == null ? "" : bool.booleanValue() ? DiskLruCache.VERSION_1 : "0";
    }

    @Override // org.fourthline.cling.model.types.AbstractDatatype, org.fourthline.cling.model.types.Datatype
    public Boolean valueOf(String str) {
        if (str.equals("")) {
            return null;
        }
        if (!str.equals(DiskLruCache.VERSION_1)) {
            Locale locale = Locale.ROOT;
            if (!str.toUpperCase(locale).equals("YES") && !str.toUpperCase(locale).equals("TRUE")) {
                if (str.equals("0") || str.toUpperCase(locale).equals("NO") || str.toUpperCase(locale).equals("FALSE")) {
                    return Boolean.FALSE;
                }
                throw new InvalidValueException(C0063n.m88a("Invalid boolean value string: ", str));
            }
        }
        return Boolean.TRUE;
    }
}
