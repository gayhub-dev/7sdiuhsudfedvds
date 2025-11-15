package p193y2;

import android.net.TrafficStats;
import android.support.v7.app.AppCompatActivity;
import java.util.TimerTask;
import p070i2.C1145c;
import p070i2.RunnableC1144b;
import p193y2.C2108c;
import p200z2.EnumC2151b;

/* compiled from: CtvitNetSpeedUtils.java */
/* renamed from: y2.b */
/* loaded from: classes.dex */
public class C2107b extends TimerTask {

    /* renamed from: e */
    public final /* synthetic */ C2108c f6239e;

    public C2107b(C2108c c2108c) {
        this.f6239e = c2108c;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        C2108c c2108c = this.f6239e;
        C2108c.a aVar = c2108c.f6242c;
        if (aVar != null) {
            long totalRxBytes = TrafficStats.getTotalRxBytes() - c2108c.f6240a;
            c2108c.f6240a = TrafficStats.getTotalRxBytes();
            C1145c c1145c = (C1145c) aVar;
            ((AppCompatActivity) c1145c.f2517a.getContext()).runOnUiThread(new RunnableC1144b(c1145c, EnumC2151b.f6321m.mo2594c((((int) totalRxBytes) / c2108c.f6241b) / 1024)));
        }
    }
}
