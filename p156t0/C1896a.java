package p156t0;

import p184x0.C2062a;

/* compiled from: CCTVAgent.java */
/* renamed from: t0.a */
/* loaded from: classes.dex */
public class C1896a {

    /* renamed from: a */
    public static C1896a f5592a;

    /* renamed from: a */
    public static C1896a m2197a() {
        if (f5592a == null) {
            synchronized (C2062a.class) {
                if (f5592a == null) {
                    f5592a = new C1896a();
                }
            }
        }
        return f5592a;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0028 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0029 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m2198b(java.lang.String r7, java.lang.String r8, java.util.Map<java.lang.String, java.lang.String> r9) {
        /*
            r6 = this;
            java.lang.String r0 = "play_duration"
            android.content.Context r1 = p163u0.C1970a.f5748f
            if (r1 != 0) goto Lc
            java.lang.String r1 = "CCTVAgent初始化失败,context不可为空"
            p043f.C0988e.m982h(r1)
            goto L23
        Lc:
            int r1 = p163u0.C1970a.f5749g
            if (r1 != 0) goto L16
            java.lang.String r1 = "CCTVAgent初始化失败,DeviceType不可为空"
            p043f.C0988e.m982h(r1)
            goto L23
        L16:
            java.lang.String r1 = p163u0.C1970a.f5745c
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L25
            java.lang.String r1 = "CCTVAgent初始化失败,appKey不可为空"
            p043f.C0988e.m982h(r1)
        L23:
            r1 = 0
            goto L26
        L25:
            r1 = 1
        L26:
            if (r1 != 0) goto L29
            return
        L29:
            boolean r1 = r9.containsKey(r0)     // Catch: java.lang.Exception -> L67
            if (r1 == 0) goto L6b
            java.lang.Object r1 = r9.get(r0)     // Catch: java.lang.Exception -> L67
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L67
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> L67
            if (r2 != 0) goto L6b
            long r1 = java.lang.Long.parseLong(r1)     // Catch: java.lang.Exception -> L67
            r3 = 10800000(0xa4cb80, double:5.335909E-317)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L5f
            java.util.Random r1 = new java.util.Random     // Catch: java.lang.Exception -> L67
            r1.<init>()     // Catch: java.lang.Exception -> L67
            float r1 = r1.nextFloat()     // Catch: java.lang.Exception -> L67
            r2 = 1069547520(0x3fc00000, float:1.5)
            float r1 = r1 * r2
            float r1 = r1 + r2
            r2 = 1114636288(0x42700000, float:60.0)
            float r1 = r1 * r2
            float r1 = r1 * r2
            r2 = 1148846080(0x447a0000, float:1000.0)
            float r1 = r1 * r2
            long r1 = (long) r1     // Catch: java.lang.Exception -> L67
        L5f:
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Exception -> L67
            r9.put(r0, r1)     // Catch: java.lang.Exception -> L67
            goto L6b
        L67:
            r0 = move-exception
            r0.printStackTrace()
        L6b:
            v0.c r0 = new v0.c
            r0.<init>()
            android.content.Context r1 = p163u0.C1970a.f5748f
            p198z0.C2146b.m2588b(r1)
            android.content.Context r1 = p163u0.C1970a.f5748f
            p198z0.C2146b.m2589c(r1)
            android.content.Context r1 = p163u0.C1970a.f5748f
            java.lang.String r1 = p198z0.C2145a.m2580h(r1)
            r0.f5829d = r1
            java.lang.String r1 = p198z0.C2145a.m2574b()
            r0.f5828c = r1
            r0.f5826a = r7
            r0.f5827b = r8
            r0.f5830e = r9
            p191y0.C2095a.m2543b(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p156t0.C1896a.m2198b(java.lang.String, java.lang.String, java.util.Map):void");
    }
}
