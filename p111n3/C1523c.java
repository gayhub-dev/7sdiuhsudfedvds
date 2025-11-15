package p111n3;

import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Looper;
import java.util.ArrayList;

/* compiled from: DeviceSensorLooper.java */
/* renamed from: n3.c */
/* loaded from: classes.dex */
public class C1523c implements InterfaceC1525e {

    /* renamed from: g */
    public static final /* synthetic */ int f4396g = 0;

    /* renamed from: a */
    public boolean f4397a;

    /* renamed from: b */
    public SensorManager f4398b;

    /* renamed from: c */
    public Looper f4399c;

    /* renamed from: d */
    public SensorEventListener f4400d;

    /* renamed from: e */
    public final ArrayList<SensorEventListener> f4401e = new ArrayList<>();

    /* renamed from: f */
    public int f4402f;

    public C1523c(SensorManager sensorManager, int i7) {
        this.f4398b = sensorManager;
        this.f4402f = i7;
    }
}
