package p078j2;

import android.os.Message;
import java.util.TimerTask;

/* compiled from: CtvitNetSpeedUtils.java */
/* renamed from: j2.b */
/* loaded from: classes.dex */
public class C1187b extends TimerTask {

    /* renamed from: e */
    public final /* synthetic */ C1188c f2606e;

    public C1187b(C1188c c1188c) {
        this.f2606e = c1188c;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        C1188c c1188c = this.f2606e;
        long jM1397b = c1188c.m1397b();
        C1188c c1188c2 = this.f2606e;
        c1188c.f2607a = jM1397b - c1188c2.f2608b;
        c1188c2.f2608b = c1188c2.m1397b();
        Message message = new Message();
        message.what = 0;
        message.obj = Long.valueOf(this.f2606e.f2607a);
        this.f2606e.f2613g.sendMessage(message);
    }
}
