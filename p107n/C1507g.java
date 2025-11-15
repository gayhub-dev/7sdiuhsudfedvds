package p107n;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import android.view.WindowManager;
import java.util.Iterator;
import java.util.Objects;
import p035e.C0888a;
import p035e.C0892e;
import p043f.C0988e;
import p043f.C0989f;
import p107n.C1506f;

/* compiled from: MotionStrategy.java */
/* renamed from: n.g */
/* loaded from: classes.dex */
public class C1507g extends AbstractC1501a implements SensorEventListener {

    /* renamed from: c */
    public WindowManager f4325c;

    /* renamed from: d */
    public float[] f4326d;

    /* renamed from: e */
    public float[] f4327e;

    /* renamed from: f */
    public boolean f4328f;

    /* renamed from: g */
    public Boolean f4329g;

    /* renamed from: h */
    public final Object f4330h;

    /* renamed from: i */
    public boolean f4331i;

    /* renamed from: j */
    public C0892e f4332j;

    /* renamed from: k */
    public Runnable f4333k;

    /* compiled from: MotionStrategy.java */
    /* renamed from: n.g$a */
    public class a implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ Context f4334e;

        public a(Context context) {
            this.f4334e = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            C1507g.this.m1671i(this.f4334e);
        }
    }

    /* compiled from: MotionStrategy.java */
    /* renamed from: n.g$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C1507g c1507g = C1507g.this;
            if (c1507g.f4328f && c1507g.f4331i) {
                synchronized (c1507g.f4330h) {
                    Iterator<C0888a> it = C1507g.this.m1666f().iterator();
                    while (it.hasNext()) {
                        it.next().mo778i(C1507g.this.f4327e);
                    }
                }
            }
        }
    }

    public C1507g(C1506f.a aVar) {
        super(aVar);
        this.f4326d = new float[16];
        this.f4327e = new float[16];
        this.f4328f = false;
        this.f4329g = null;
        this.f4330h = new Object();
        this.f4332j = new C0892e(1);
        this.f4333k = new b();
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: a */
    public boolean mo1588a(Context context) {
        if (this.f4329g == null) {
            this.f4329g = Boolean.valueOf(((SensorManager) context.getSystemService("sensor")).getDefaultSensor(11) != null);
        }
        return this.f4329g.booleanValue();
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: b */
    public void mo1589b(Context context) {
        SensorManager sensorManager;
        Sensor defaultSensor;
        if (this.f4328f || (defaultSensor = (sensorManager = (SensorManager) context.getSystemService("sensor")).getDefaultSensor(11)) == null) {
            return;
        }
        sensorManager.registerListener(this, defaultSensor, this.f4302a.f4319a, C0988e.f1823a);
        this.f4328f = true;
    }

    @Override // p107n.InterfaceC1504d
    /* renamed from: c */
    public boolean mo1668c(int i7, int i8) {
        return false;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: d */
    public void mo1590d(Context context) {
        m1671i(context);
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: e */
    public void mo1591e(Context context) {
        this.f4331i = true;
        this.f4325c = (WindowManager) context.getSystemService("window");
        Iterator<C0888a> it = m1666f().iterator();
        while (it.hasNext()) {
            it.next().m772c();
        }
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: h */
    public void mo1592h(Context context) {
        this.f4331i = false;
        m1667g(new a(context));
    }

    /* renamed from: i */
    public void m1671i(Context context) {
        if (this.f4328f) {
            ((SensorManager) context.getSystemService("sensor")).unregisterListener(this);
            this.f4328f = false;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i7) {
        Objects.requireNonNull(this.f4302a);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.f4332j.m785W(sensorEvent);
        if (!this.f4331i || sensorEvent.accuracy == 0) {
            return;
        }
        Objects.requireNonNull(this.f4302a);
        if (sensorEvent.sensor.getType() != 11) {
            return;
        }
        int rotation = this.f4325c.getDefaultDisplay().getRotation();
        float[] fArr = this.f4326d;
        if (!C0989f.f1834c) {
            try {
                SensorManager.getRotationMatrixFromVector(C0989f.f1832a, sensorEvent.values);
            } catch (Exception unused) {
                C0989f.f1834c = true;
            }
        }
        if (C0989f.f1834c) {
            float[] fArr2 = sensorEvent.values;
            float[] fArr3 = C0989f.f1833b;
            System.arraycopy(fArr2, 0, fArr3, 0, 4);
            SensorManager.getRotationMatrixFromVector(C0989f.f1832a, fArr3);
        }
        float[] fArr4 = sensorEvent.values;
        if (rotation == 0) {
            SensorManager.getRotationMatrixFromVector(fArr, fArr4);
        } else if (rotation == 1) {
            float[] fArr5 = C0989f.f1832a;
            SensorManager.getRotationMatrixFromVector(fArr5, fArr4);
            SensorManager.remapCoordinateSystem(fArr5, 2, 129, fArr);
        } else if (rotation == 2) {
            float[] fArr6 = C0989f.f1832a;
            SensorManager.getRotationMatrixFromVector(fArr6, fArr4);
            SensorManager.remapCoordinateSystem(fArr6, 129, 130, fArr);
        } else if (rotation == 3) {
            float[] fArr7 = C0989f.f1832a;
            SensorManager.getRotationMatrixFromVector(fArr7, fArr4);
            SensorManager.remapCoordinateSystem(fArr7, 130, 1, fArr);
        }
        Matrix.rotateM(fArr, 0, 90.0f, 1.0f, 0.0f, 0.0f);
        synchronized (this.f4330h) {
            System.arraycopy(this.f4326d, 0, this.f4327e, 0, 16);
        }
        this.f4302a.f4321c.m956b(this.f4333k);
    }
}
