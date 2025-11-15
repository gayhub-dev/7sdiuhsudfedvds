package p067i;

import android.content.Context;
import p067i.C1131f;

/* compiled from: MDObject3DHelper.java */
/* renamed from: i.e */
/* loaded from: classes.dex */
public class RunnableC1130e implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ AbstractC1126a f2459e;

    /* renamed from: f */
    public final /* synthetic */ Context f2460f;

    /* renamed from: g */
    public final /* synthetic */ C1131f.a f2461g;

    public RunnableC1130e(AbstractC1126a abstractC1126a, Context context, C1131f.a aVar) {
        this.f2459e = abstractC1126a;
        this.f2460f = context;
        this.f2461g = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f2459e.mo1289b(this.f2460f);
        C1131f.a aVar = this.f2461g;
        if (aVar != null) {
            aVar.m1295a(this.f2459e);
        }
    }
}
