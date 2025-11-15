package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import java.util.regex.Pattern;
import org.fourthline.cling.model.message.header.InvalidHeaderException;

/* loaded from: classes.dex */
public class EventTypeHeader extends DLNAHeader<String> {
    public static final Pattern pattern = Pattern.compile("^[0-9]{4}$", 2);

    public EventTypeHeader() {
        setValue("0000");
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        return getValue().toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (!pattern.matcher(str).matches()) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid EventType header value: ", str));
        }
        setValue(str);
    }
}
