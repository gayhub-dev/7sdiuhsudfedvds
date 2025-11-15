package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import java.util.regex.Pattern;
import okhttp3.internal.cache.DiskLruCache;
import org.fourthline.cling.model.message.header.InvalidHeaderException;
import org.fourthline.cling.support.model.dlna.types.ScmsFlagType;

/* loaded from: classes.dex */
public class ScmsFlagHeader extends DLNAHeader<ScmsFlagType> {
    public static final Pattern pattern = Pattern.compile("^[01]{2}$", 2);

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        ScmsFlagType value = getValue();
        StringBuilder sb = new StringBuilder();
        sb.append(value.isCopyright() ? "0" : DiskLruCache.VERSION_1);
        sb.append(value.isOriginal() ? "0" : DiskLruCache.VERSION_1);
        return sb.toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (!pattern.matcher(str).matches()) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid ScmsFlag header value: ", str));
        }
        setValue(new ScmsFlagType(str.charAt(0) == '0', str.charAt(1) == '0'));
    }
}
