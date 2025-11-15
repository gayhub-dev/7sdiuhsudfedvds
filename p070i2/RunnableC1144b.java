package p070i2;

import android.widget.TextView;
import p009b.C0413b;

/* compiled from: VersionDownloadingView.java */
/* renamed from: i2.b */
/* loaded from: classes.dex */
public class RunnableC1144b implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ String f2515e;

    /* renamed from: f */
    public final /* synthetic */ C1145c f2516f;

    public RunnableC1144b(C1145c c1145c, String str) {
        this.f2516f = c1145c;
        this.f2515e = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        TextView textView = this.f2516f.f2517a.f913g;
        StringBuilder sbM112a = C0413b.m112a("下载速度：");
        sbM112a.append(this.f2515e);
        sbM112a.append("/s");
        textView.setText(sbM112a.toString());
    }
}
