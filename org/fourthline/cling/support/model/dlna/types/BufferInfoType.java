package org.fourthline.cling.support.model.dlna.types;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.cache.DiskLruCache;
import org.fourthline.cling.model.types.InvalidValueException;
import org.fourthline.cling.support.model.dlna.types.CodedDataBuffer;
import p009b.C0413b;

/* loaded from: classes.dex */
public class BufferInfoType {
    public static final Pattern pattern = Pattern.compile("^dejitter=(\\d{1,10})(;CDB=(\\d{1,10});BTM=(0|1|2))?(;TD=(\\d{1,10}))?(;BFR=(0|1))?$", 2);
    private CodedDataBuffer cdb;
    private Long dejitterSize;
    private Boolean fullnessReports;
    private Long targetDuration;

    public BufferInfoType(Long l7) {
        this.dejitterSize = l7;
    }

    public static BufferInfoType valueOf(String str) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            try {
                return new BufferInfoType(Long.valueOf(Long.parseLong(matcher.group(1))), matcher.group(2) != null ? new CodedDataBuffer(Long.valueOf(Long.parseLong(matcher.group(3))), CodedDataBuffer.TransferMechanism.values()[Integer.parseInt(matcher.group(4))]) : null, matcher.group(5) != null ? Long.valueOf(Long.parseLong(matcher.group(6))) : null, matcher.group(7) != null ? Boolean.valueOf(matcher.group(8).equals(DiskLruCache.VERSION_1)) : null);
            } catch (NumberFormatException unused) {
            }
        }
        throw new InvalidValueException(C0063n.m88a("Can't parse BufferInfoType: ", str));
    }

    public CodedDataBuffer getCdb() {
        return this.cdb;
    }

    public Long getDejitterSize() {
        return this.dejitterSize;
    }

    public String getString() {
        StringBuilder sbM112a = C0413b.m112a("dejitter=");
        sbM112a.append(this.dejitterSize.toString());
        String string = sbM112a.toString();
        if (this.cdb != null) {
            StringBuilder sbM94a = C0080b.m94a(string, ";CDB=");
            sbM94a.append(this.cdb.getSize().toString());
            sbM94a.append(";BTM=");
            sbM94a.append(this.cdb.getTranfer().ordinal());
            string = sbM94a.toString();
        }
        if (this.targetDuration != null) {
            StringBuilder sbM94a2 = C0080b.m94a(string, ";TD=");
            sbM94a2.append(this.targetDuration.toString());
            string = sbM94a2.toString();
        }
        if (this.fullnessReports == null) {
            return string;
        }
        StringBuilder sbM94a3 = C0080b.m94a(string, ";BFR=");
        sbM94a3.append(this.fullnessReports.booleanValue() ? DiskLruCache.VERSION_1 : "0");
        return sbM94a3.toString();
    }

    public Long getTargetDuration() {
        return this.targetDuration;
    }

    public Boolean isFullnessReports() {
        return this.fullnessReports;
    }

    public BufferInfoType(Long l7, CodedDataBuffer codedDataBuffer, Long l8, Boolean bool) {
        this.dejitterSize = l7;
        this.cdb = codedDataBuffer;
        this.targetDuration = l8;
        this.fullnessReports = bool;
    }
}
