package p111n3;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import java.util.Iterator;

/* compiled from: DeviceSensorLooper.java */
/* renamed from: n3.a */
/* loaded from: classes.dex */
public class C1521a implements SensorEventListener {

    /* renamed from: a */
    public final /* synthetic */ C1523c f4394a;

    public C1521a(C1523c c1523c) {
        this.f4394a = c1523c;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i7) {
        synchronized (this.f4394a.f4401e) {
            Iterator<SensorEventListener> it = this.f4394a.f4401e.iterator();
            while (it.hasNext()) {
                it.next().onAccuracyChanged(sensor, i7);
            }
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this.f4394a.f4401e) {
            Iterator<SensorEventListener> it = this.f4394a.f4401e.iterator();
            while (it.hasNext()) {
                it.next().onSensorChanged(sensorEvent);
            }
        }
    }
}
