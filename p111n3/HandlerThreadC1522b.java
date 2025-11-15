package p111n3;

import android.hardware.Sensor;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.Objects;

/* compiled from: DeviceSensorLooper.java */
/* renamed from: n3.b */
/* loaded from: classes.dex */
public class HandlerThreadC1522b extends HandlerThread {

    /* renamed from: e */
    public final /* synthetic */ C1523c f4395e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerThreadC1522b(C1523c c1523c, String str) {
        super(str);
        this.f4395e = c1523c;
    }

    @Override // android.os.HandlerThread
    public void onLooperPrepared() {
        Handler handler = new Handler(Looper.myLooper());
        Sensor defaultSensor = this.f4395e.f4398b.getDefaultSensor(1);
        C1523c c1523c = this.f4395e;
        c1523c.f4398b.registerListener(c1523c.f4400d, defaultSensor, c1523c.f4402f, handler);
        C1523c c1523c2 = this.f4395e;
        Objects.requireNonNull(c1523c2);
        Sensor defaultSensor2 = Build.MANUFACTURER.equals("HTC") ? null : c1523c2.f4398b.getDefaultSensor(16);
        if (defaultSensor2 == null) {
            int i7 = C1523c.f4396g;
            defaultSensor2 = this.f4395e.f4398b.getDefaultSensor(4);
        }
        C1523c c1523c3 = this.f4395e;
        c1523c3.f4398b.registerListener(c1523c3.f4400d, defaultSensor2, c1523c3.f4402f, handler);
    }
}
