package org.fourthline.cling.support.model.dlna.message.header;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import android.support.constraint.motion.C0081c;
import org.fourthline.cling.model.message.header.InvalidHeaderException;
import org.fourthline.cling.model.types.BytesRange;
import org.fourthline.cling.model.types.InvalidValueException;
import org.fourthline.cling.support.model.dlna.types.AvailableSeekRangeType;
import org.fourthline.cling.support.model.dlna.types.NormalPlayTimeRange;

/* loaded from: classes.dex */
public class AvailableSeekRangeHeader extends DLNAHeader<AvailableSeekRangeType> {
    public AvailableSeekRangeHeader() {
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public String getString() {
        AvailableSeekRangeType value = getValue();
        String string = Integer.toString(value.getModeFlag().ordinal());
        if (value.getNormalPlayTimeRange() != null) {
            StringBuilder sbM94a = C0080b.m94a(string, " ");
            sbM94a.append(value.getNormalPlayTimeRange().getString(false));
            string = sbM94a.toString();
        }
        if (value.getBytesRange() == null) {
            return string;
        }
        StringBuilder sbM94a2 = C0080b.m94a(string, " ");
        sbM94a2.append(value.getBytesRange().getString(false));
        return sbM94a2.toString();
    }

    @Override // org.fourthline.cling.model.message.header.UpnpHeader
    public void setString(String str) {
        NormalPlayTimeRange normalPlayTimeRangeValueOf;
        if (str.length() != 0) {
            String[] strArrSplit = str.split(" ");
            boolean z6 = true;
            try {
                if (strArrSplit.length > 1) {
                    try {
                        AvailableSeekRangeType.Mode modeValueOf = AvailableSeekRangeType.Mode.valueOf("MODE_" + strArrSplit[0]);
                        BytesRange bytesRangeValueOf = null;
                        try {
                            try {
                                normalPlayTimeRangeValueOf = NormalPlayTimeRange.valueOf(strArrSplit[1], true);
                            } catch (InvalidValueException unused) {
                                throw new InvalidValueException("Invalid AvailableSeekRange Range");
                            }
                        } catch (InvalidValueException unused2) {
                            normalPlayTimeRangeValueOf = null;
                            bytesRangeValueOf = BytesRange.valueOf(strArrSplit[1]);
                            z6 = false;
                        }
                        if (!z6) {
                            setValue(new AvailableSeekRangeType(modeValueOf, bytesRangeValueOf));
                            return;
                        } else if (strArrSplit.length > 2) {
                            setValue(new AvailableSeekRangeType(modeValueOf, normalPlayTimeRangeValueOf, BytesRange.valueOf(strArrSplit[2])));
                            return;
                        } else {
                            setValue(new AvailableSeekRangeType(modeValueOf, normalPlayTimeRangeValueOf));
                            return;
                        }
                    } catch (IllegalArgumentException unused3) {
                        throw new InvalidValueException("Invalid AvailableSeekRange Mode");
                    }
                }
            } catch (InvalidValueException e7) {
                StringBuilder sbM95a = C0081c.m95a("Invalid AvailableSeekRange header value: ", str, "; ");
                sbM95a.append(e7.getMessage());
                throw new InvalidHeaderException(sbM95a.toString());
            }
        }
        throw new InvalidHeaderException(C0063n.m88a("Invalid AvailableSeekRange header value: ", str));
    }

    public AvailableSeekRangeHeader(AvailableSeekRangeType availableSeekRangeType) {
        setValue(availableSeekRangeType);
    }
}
