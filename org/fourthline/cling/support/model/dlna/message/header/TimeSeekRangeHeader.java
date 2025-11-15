package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import android.support.constraint.motion.C0081c;
import org.fourthline.cling.model.message.header.InvalidHeaderException;
import org.fourthline.cling.model.types.BytesRange;
import org.fourthline.cling.model.types.InvalidValueException;
import org.fourthline.cling.support.model.dlna.types.NormalPlayTimeRange;
import org.fourthline.cling.support.model.dlna.types.TimeSeekRangeType;

/* loaded from: classes.dex */
public class TimeSeekRangeHeader extends DLNAHeader<TimeSeekRangeType> {
    public TimeSeekRangeHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        TimeSeekRangeType value = getValue();
        String string = value.getNormalPlayTimeRange().getString();
        if (value.getBytesRange() == null) {
            return string;
        }
        StringBuilder sbM94a = C0080b.m94a(string, " ");
        sbM94a.append(value.getBytesRange().getString(true));
        return sbM94a.toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        if (str.length() != 0) {
            String[] strArrSplit = str.split(" ");
            if (strArrSplit.length > 0) {
                try {
                    TimeSeekRangeType timeSeekRangeType = new TimeSeekRangeType(NormalPlayTimeRange.valueOf(strArrSplit[0]));
                    if (strArrSplit.length > 1) {
                        timeSeekRangeType.setBytesRange(BytesRange.valueOf(strArrSplit[1]));
                    }
                    setValue(timeSeekRangeType);
                    return;
                } catch (InvalidValueException e7) {
                    StringBuilder sbM95a = C0081c.m95a("Invalid TimeSeekRange header value: ", str, "; ");
                    sbM95a.append(e7.getMessage());
                    throw new InvalidHeaderException(sbM95a.toString());
                }
            }
        }
        throw new InvalidHeaderException(C0063n.m88a("Invalid TimeSeekRange header value: ", str));
    }

    public TimeSeekRangeHeader(TimeSeekRangeType timeSeekRangeType) {
        setValue(timeSeekRangeType);
    }
}
