package p094l2;

import android.content.Context;
import android.support.v4.app.C0164a;
import com.ctvit.network.CtvitHttp;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p186x2.C2074b;
import p201z3.InterfaceC2153b;

/* compiled from: CtvitAppUpdate.java */
/* renamed from: l2.a */
/* loaded from: classes.dex */
public class C1420a {

    /* renamed from: d */
    public static String f4156d = "";

    /* renamed from: e */
    public static volatile C1420a f4157e;

    /* renamed from: a */
    public Context f4158a;

    /* renamed from: b */
    public String f4159b;

    /* renamed from: c */
    public List<InterfaceC2153b> f4160c = new ArrayList();

    public C1420a() {
        StringBuilder sb = new StringBuilder();
        sb.append(C2074b.m2487j());
        String str = File.separator;
        f4156d = C0164a.m99a(sb, str, "appupdate", str);
    }

    /* renamed from: a */
    public static void m1604a() {
        Iterator<InterfaceC2153b> it = m1606c().f4160c.iterator();
        while (it.hasNext()) {
            CtvitHttp.cancelSubscription(it.next());
        }
    }

    /* renamed from: b */
    public static Context m1605b() {
        return m1606c().f4158a;
    }

    /* renamed from: c */
    public static C1420a m1606c() {
        if (f4157e == null) {
            synchronized (C1420a.class) {
                if (f4157e == null) {
                    f4157e = new C1420a();
                }
            }
        }
        return f4157e;
    }
}
