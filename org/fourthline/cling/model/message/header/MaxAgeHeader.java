package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p009b.C0413b;

/* loaded from: classes.dex */
public class MaxAgeHeader extends UpnpHeader<Integer> {
    public static final Pattern MAX_AGE_REGEX = Pattern.compile(".*max-age\\s*=\\s*([0-9]+).*");

    public MaxAgeHeader(Integer num) {
        setValue(num);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        StringBuilder sbM112a = C0413b.m112a("max-age=");
        sbM112a.append(getValue().toString());
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        Matcher matcher = MAX_AGE_REGEX.matcher(str.toLowerCase(Locale.ROOT));
        if (!matcher.matches()) {
            throw new InvalidHeaderException(C0063n.m88a("Invalid cache-control value, can't parse max-age seconds: ", str));
        }
        setValue(Integer.valueOf(Integer.parseInt(matcher.group(1))));
    }

    public MaxAgeHeader() {
        setValue(1800);
    }
}
