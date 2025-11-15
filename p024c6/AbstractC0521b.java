package p024c6;

import p016b6.InterfaceC0490u;

/* compiled from: AbstractDuration.java */
/* renamed from: c6.b */
/* loaded from: classes.dex */
public abstract class AbstractC0521b implements InterfaceC0490u {
    @Override // java.lang.Comparable
    public int compareTo(InterfaceC0490u interfaceC0490u) {
        long j7 = ((AbstractC0525f) this).f399e;
        long jMo287g = interfaceC0490u.mo287g();
        if (j7 < jMo287g) {
            return -1;
        }
        return j7 > jMo287g ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InterfaceC0490u) && ((AbstractC0525f) this).f399e == ((InterfaceC0490u) obj).mo287g();
    }

    public int hashCode() {
        long j7 = ((AbstractC0525f) this).f399e;
        return (int) (j7 ^ (j7 >>> 32));
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x002d, code lost:
    
        r2.insert(3, "0");
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0043  */
    @org.joda.convert.ToString
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r9 = this;
            r0 = r9
            c6.f r0 = (p024c6.AbstractC0525f) r0
            long r0 = r0.f399e
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "PT"
            r2.append(r3)
            r3 = 0
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 >= 0) goto L17
            r3 = 1
            goto L18
        L17:
            r3 = 0
        L18:
            int r4 = p058g6.C1070h.f2077b
            int r4 = (int) r0
            long r5 = (long) r4
            int r7 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r7 != 0) goto L24
            p058g6.C1070h.m1111b(r2, r4)     // Catch: java.io.IOException -> L2c
            goto L2d
        L24:
            java.lang.String r4 = java.lang.Long.toString(r0)     // Catch: java.io.IOException -> L2c
            r2.append(r4)     // Catch: java.io.IOException -> L2c
            goto L2d
        L2c:
        L2d:
            int r4 = r2.length()
            if (r3 == 0) goto L35
            r5 = 7
            goto L36
        L35:
            r5 = 6
        L36:
            r6 = 3
            if (r4 >= r5) goto L43
            if (r3 == 0) goto L3c
            goto L3d
        L3c:
            r6 = 2
        L3d:
            java.lang.String r4 = "0"
            r2.insert(r6, r4)
            goto L2d
        L43:
            r3 = 1000(0x3e8, double:4.94E-321)
            long r7 = r0 / r3
            long r7 = r7 * r3
            int r3 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r3 != 0) goto L56
            int r0 = r2.length()
            int r0 = r0 - r6
            r2.setLength(r0)
            goto L60
        L56:
            int r0 = r2.length()
            int r0 = r0 - r6
            java.lang.String r1 = "."
            r2.insert(r0, r1)
        L60:
            r0 = 83
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p024c6.AbstractC0521b.toString():java.lang.String");
    }
}
