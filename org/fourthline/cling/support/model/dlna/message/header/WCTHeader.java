package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import java.util.regex.Pattern;
import okhttp3.internal.cache.DiskLruCache;
import org.fourthline.cling.model.message.header.InvalidHeaderException;

/* loaded from: classes.dex */
public class WCTHeader extends DLNAHeader<Boolean> {
    public static final Pattern pattern = Pattern.compile("^[01]{1}$", 2);

    public WCTHeader() {
        setValue(Boolean.FALSE);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().booleanValue() ? DiskLruCache.VERSION_1 : "0";
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (!pattern.matcher(str).matches()) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid SCID header value: ", str));
        }
        setValue(Boolean.valueOf(str.equals(DiskLruCache.VERSION_1)));
    }
}
