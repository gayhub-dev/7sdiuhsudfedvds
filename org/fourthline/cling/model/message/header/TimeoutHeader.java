package org.fourthline.cling.model.message.header;

import android.arch.lifecycle.C0063n;
import android.support.v7.widget.ActivityChooserView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p009b.C0413b;

/* loaded from: classes.dex */
public class TimeoutHeader extends UpnpHeader<Integer> {
    public static final Integer INFINITE_VALUE = Integer.valueOf(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    public static final Pattern PATTERN = Pattern.compile("Second-(?:([0-9]+)|infinite)");

    public TimeoutHeader() {
        setValue(1800);
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        StringBuilder sbM112a = C0413b.m112a("Second-");
        sbM112a.append(getValue().equals(INFINITE_VALUE) ? "infinite" : getValue());
        return sbM112a.toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        Matcher matcher = PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new InvalidHeaderException(C0063n.m88a("Can't parse timeout seconds integer from: ", str));
        }
        if (matcher.group(1) != null) {
            setValue(Integer.valueOf(Integer.parseInt(matcher.group(1))));
        } else {
            setValue(INFINITE_VALUE);
        }
    }

    public TimeoutHeader(int i7) {
        setValue(Integer.valueOf(i7));
    }

    public TimeoutHeader(Integer num) {
        setValue(num);
    }
}
