package p029d1;

import com.tencent.mars.xlog.Log;
import p009b.C0413b;

/* compiled from: SaveLogUtils.java */
/* renamed from: d1.b */
/* loaded from: classes.dex */
public class C0865b {
    /* renamed from: a */
    public static void m673a() {
        try {
            new Thread(RunnableC0864a.f1289f).start();
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("remove error ");
            sbM112a.append(e7.getMessage());
            Log.m655i("XLog_APP ", sbM112a.toString());
        }
    }
}
