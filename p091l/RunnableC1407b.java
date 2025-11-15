package p091l;

import p035e.C0901n;

/* compiled from: ModeManager.java */
/* renamed from: l.b */
/* loaded from: classes.dex */
public class RunnableC1407b implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ int f4139e;

    /* renamed from: f */
    public final /* synthetic */ AbstractC1408c f4140f;

    public RunnableC1407b(AbstractC1408c abstractC1408c, int i7) {
        this.f4140f = abstractC1408c;
        this.f4139e = i7;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0901n.g gVar = this.f4140f.f4143c;
        if (gVar != null) {
            gVar.mo817a(this.f4139e);
        }
    }
}
