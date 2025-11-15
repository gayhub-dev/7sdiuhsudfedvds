package org.fourthline.cling.support.model.dlna.types;

import android.support.constraint.motion.C0080b;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class NormalPlayTimeRange {
    public static final String PREFIX = "npt=";
    private NormalPlayTime timeDuration;
    private NormalPlayTime timeEnd;
    private NormalPlayTime timeStart;

    public NormalPlayTimeRange(long j7, long j8) {
        this.timeStart = new NormalPlayTime(j7);
        this.timeEnd = new NormalPlayTime(j8);
    }

    public static NormalPlayTimeRange valueOf(String str) {
        return valueOf(str, false);
    }

    public String getString() {
        return getString(true);
    }

    public NormalPlayTime getTimeDuration() {
        return this.timeDuration;
    }

    public NormalPlayTime getTimeEnd() {
        return this.timeEnd;
    }

    public NormalPlayTime getTimeStart() {
        return this.timeStart;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.fourthline.cling.support.model.dlna.types.NormalPlayTimeRange valueOf(java.lang.String r7, boolean r8) {
        /*
            java.lang.String r0 = "npt="
            boolean r0 = r7.startsWith(r0)
            if (r0 == 0) goto L67
            r0 = 4
            java.lang.String r0 = r7.substring(r0)
            java.lang.String r1 = "[-/]"
            java.lang.String[] r0 = r0.split(r1)
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 == r3) goto L4a
            r4 = 2
            if (r1 == r4) goto L37
            r5 = 3
            if (r1 != r5) goto L67
            r1 = r0[r4]
            int r1 = r1.length()
            if (r1 == 0) goto L37
            r1 = r0[r4]
            java.lang.String r5 = "*"
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L37
            r1 = r0[r4]
            org.fourthline.cling.support.model.dlna.types.NormalPlayTime r1 = org.fourthline.cling.support.model.dlna.types.NormalPlayTime.valueOf(r1)
            goto L38
        L37:
            r1 = r2
        L38:
            r4 = r0[r3]
            int r4 = r4.length()
            if (r4 == 0) goto L46
            r2 = r0[r3]
            org.fourthline.cling.support.model.dlna.types.NormalPlayTime r2 = org.fourthline.cling.support.model.dlna.types.NormalPlayTime.valueOf(r2)
        L46:
            r6 = r2
            r2 = r1
            r1 = r6
            goto L4b
        L4a:
            r1 = r2
        L4b:
            r4 = 0
            r5 = r0[r4]
            int r5 = r5.length()
            if (r5 == 0) goto L67
            if (r8 == 0) goto L5b
            if (r8 == 0) goto L67
            int r8 = r0.length
            if (r8 <= r3) goto L67
        L5b:
            r7 = r0[r4]
            org.fourthline.cling.support.model.dlna.types.NormalPlayTime r7 = org.fourthline.cling.support.model.dlna.types.NormalPlayTime.valueOf(r7)
            org.fourthline.cling.support.model.dlna.types.NormalPlayTimeRange r8 = new org.fourthline.cling.support.model.dlna.types.NormalPlayTimeRange
            r8.<init>(r7, r1, r2)
            return r8
        L67:
            org.fourthline.cling.model.types.InvalidValueException r8 = new org.fourthline.cling.model.types.InvalidValueException
            java.lang.String r0 = "Can't parse NormalPlayTimeRange: "
            java.lang.String r7 = android.arch.lifecycle.C0063n.m88a(r0, r7)
            r8.<init>(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fourthline.cling.support.model.dlna.types.NormalPlayTimeRange.valueOf(java.lang.String, boolean):org.fourthline.cling.support.model.dlna.types.NormalPlayTimeRange");
    }

    public String getString(boolean z6) {
        StringBuilder sbM112a = C0413b.m112a(PREFIX);
        sbM112a.append(this.timeStart.getString());
        sbM112a.append("-");
        String string = sbM112a.toString();
        if (this.timeEnd != null) {
            StringBuilder sbM112a2 = C0413b.m112a(string);
            sbM112a2.append(this.timeEnd.getString());
            string = sbM112a2.toString();
        }
        if (!z6) {
            return string;
        }
        StringBuilder sbM94a = C0080b.m94a(string, ServiceReference.DELIMITER);
        NormalPlayTime normalPlayTime = this.timeDuration;
        sbM94a.append(normalPlayTime != null ? normalPlayTime.getString() : "*");
        return sbM94a.toString();
    }

    public NormalPlayTimeRange(NormalPlayTime normalPlayTime, NormalPlayTime normalPlayTime2) {
        this.timeStart = normalPlayTime;
        this.timeEnd = normalPlayTime2;
    }

    public NormalPlayTimeRange(NormalPlayTime normalPlayTime, NormalPlayTime normalPlayTime2, NormalPlayTime normalPlayTime3) {
        this.timeStart = normalPlayTime;
        this.timeEnd = normalPlayTime2;
        this.timeDuration = normalPlayTime3;
    }
}
