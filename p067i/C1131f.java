package p067i;

import android.content.Context;

/* compiled from: MDObject3DHelper.java */
/* renamed from: i.f */
/* loaded from: classes.dex */
public class C1131f {

    /* compiled from: MDObject3DHelper.java */
    /* renamed from: i.f$a */
    public interface a {
        /* renamed from: a */
        void m1295a(AbstractC1126a abstractC1126a);
    }

    /* renamed from: a */
    public static void m1294a(Context context, AbstractC1126a abstractC1126a) {
        new Thread(new RunnableC1130e(abstractC1126a, context, null)).start();
    }
}
