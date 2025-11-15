package org.fourthline.cling.model.types;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class BytesRange {
    public static final String PREFIX = "bytes=";
    private Long byteLength;
    private Long firstByte;
    private Long lastByte;

    public BytesRange(Long l7, Long l8) {
        this.firstByte = l7;
        this.lastByte = l8;
        this.byteLength = null;
    }

    public static BytesRange valueOf(String str) {
        return valueOf(str, null);
    }

    public Long getByteLength() {
        return this.byteLength;
    }

    public Long getFirstByte() {
        return this.firstByte;
    }

    public Long getLastByte() {
        return this.lastByte;
    }

    public String getString() {
        return getString(false, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.fourthline.cling.model.types.BytesRange valueOf(java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.String r0 = "bytes="
            if (r6 == 0) goto L6
            r1 = r6
            goto L7
        L6:
            r1 = r0
        L7:
            boolean r1 = r5.startsWith(r1)
            if (r1 == 0) goto L7c
            if (r6 == 0) goto L10
            goto L11
        L10:
            r6 = r0
        L11:
            int r6 = r6.length()
            java.lang.String r6 = r5.substring(r6)
            java.lang.String r0 = "[-/]"
            java.lang.String[] r6 = r6.split(r0)
            int r0 = r6.length
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L5d
            r3 = 2
            if (r0 == r3) goto L47
            r4 = 3
            if (r0 != r4) goto L7c
            r0 = r6[r3]
            int r0 = r0.length()
            if (r0 == 0) goto L47
            r0 = r6[r3]
            java.lang.String r4 = "*"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L47
            r0 = r6[r3]
            long r3 = java.lang.Long.parseLong(r0)
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
            goto L48
        L47:
            r0 = r2
        L48:
            r3 = r6[r1]
            int r3 = r3.length()
            if (r3 == 0) goto L5b
            r1 = r6[r1]
            long r3 = java.lang.Long.parseLong(r1)
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            goto L5f
        L5b:
            r1 = r2
            goto L5f
        L5d:
            r0 = r2
            r1 = r0
        L5f:
            r3 = 0
            r4 = r6[r3]
            int r4 = r4.length()
            if (r4 == 0) goto L72
            r6 = r6[r3]
            long r2 = java.lang.Long.parseLong(r6)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
        L72:
            if (r2 != 0) goto L76
            if (r1 == 0) goto L7c
        L76:
            org.fourthline.cling.model.types.BytesRange r5 = new org.fourthline.cling.model.types.BytesRange
            r5.<init>(r2, r1, r0)
            return r5
        L7c:
            org.fourthline.cling.model.types.InvalidValueException r6 = new org.fourthline.cling.model.types.InvalidValueException
            java.lang.String r0 = "Can't parse Bytes Range: "
            java.lang.String r5 = android.arch.lifecycle.C0063n.m88a(r0, r5)
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fourthline.cling.model.types.BytesRange.valueOf(java.lang.String, java.lang.String):org.fourthline.cling.model.types.BytesRange");
    }

    public String getString(boolean z6) {
        return getString(z6, null);
    }

    public String getString(boolean z6, String str) {
        if (str == null) {
            str = PREFIX;
        }
        if (this.firstByte != null) {
            StringBuilder sbM112a = C0413b.m112a(str);
            sbM112a.append(this.firstByte.toString());
            str = sbM112a.toString();
        }
        String strM88a = C0063n.m88a(str, "-");
        if (this.lastByte != null) {
            StringBuilder sbM112a2 = C0413b.m112a(strM88a);
            sbM112a2.append(this.lastByte.toString());
            strM88a = sbM112a2.toString();
        }
        if (!z6) {
            return strM88a;
        }
        StringBuilder sbM94a = C0080b.m94a(strM88a, ServiceReference.DELIMITER);
        Long l7 = this.byteLength;
        sbM94a.append(l7 != null ? l7.toString() : "*");
        return sbM94a.toString();
    }

    public BytesRange(Long l7, Long l8, Long l9) {
        this.firstByte = l7;
        this.lastByte = l8;
        this.byteLength = l9;
    }
}
