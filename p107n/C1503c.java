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
import p043f.C0984a;
import p107n.C1506f;
import p111n3.C1521a;
import p111n3.C1523c;
import p111n3.C1524d;
import p111n3.HandlerThreadC1522b;
import p119o3.C1582a;

/* compiled from: CardboardMotionStrategy.java */
/* renamed from: n.c */
/* loaded from: classes.dex */
public class C1503c extends AbstractC1501a implements SensorEventListener {

    /* renamed from: c */
    public boolean f4305c;

    /* renamed from: d */
    public Boolean f4306d;

    /* renamed from: e */
    public float[] f4307e;

    /* renamed from: f */
    public final Object f4308f;

    /* renamed from: g */
    public C1524d f4309g;

    /* renamed from: h */
    public C1523c f4310h;

    /* renamed from: i */
    public boolean f4311i;

    /* renamed from: j */
    public C0892e f4312j;

    /* renamed from: k */
    public Runnable f4313k;

    /* compiled from: CardboardMotionStrategy.java */
    /* renamed from: n.c$a */
    public class a implements Runnable {
        public a(Context context) {
        }

        @Override // java.lang.Runnable
        public void run() {
            C1503c.this.m1669i();
        }
    }

    /* compiled from: CardboardMotionStrategy.java */
    /* renamed from: n.c$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C1503c c1503c = C1503c.this;
            if (c1503c.f4305c && c1503c.f4311i) {
                synchronized (c1503c.f4308f) {
                    Iterator<C0888a> it = C1503c.this.m1666f().iterator();
                    while (it.hasNext()) {
                        it.next().mo778i(C1503c.this.f4307e);
                    }
                }
            }
        }
    }

    public C1503c(C1506f.a aVar) {
        super(aVar);
        this.f4305c = false;
        this.f4306d = null;
        this.f4307e = new float[16];
        this.f4308f = new Object();
        this.f4312j = new C0892e(1);
        this.f4313k = new b();
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: a */
    public boolean mo1588a(Context context) {
        if (this.f4306d == null) {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            boolean z6 = true;
            Sensor defaultSensor = sensorManager.getDefaultSensor(1);
            Sensor defaultSensor2 = sensorManager.getDefaultSensor(4);
            if (defaultSensor == null && defaultSensor2 == null) {
                z6 = false;
            }
            this.f4306d = Boolean.valueOf(z6);
        }
        return this.f4306d.booleanValue();
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: b */
    public void mo1589b(Context context) {
        if (this.f4305c) {
            return;
        }
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(1);
        Sensor defaultSensor2 = sensorManager.getDefaultSensor(4);
        if (defaultSensor == null && defaultSensor2 == null) {
            return;
        }
        if (this.f4310h == null) {
            this.f4310h = new C1523c(sensorManager, this.f4302a.f4319a);
        }
        if (this.f4309g == null) {
            this.f4309g = new C1524d(this.f4310h, new C0984a(12), ((WindowManager) context.getSystemService("window")).getDefaultDisplay());
        }
        C1523c c1523c = this.f4310h;
        synchronized (c1523c.f4401e) {
            c1523c.f4401e.add(this);
        }
        C1524d c1524d = this.f4309g;
        if (!c1524d.f4410h) {
            c1524d.f4411i.m1844c();
            synchronized (c1524d.f4412j) {
                C1582a c1582a = c1524d.f4413k;
                if (c1582a != null) {
                    c1582a.m1840b();
                }
            }
            c1524d.f4417o = true;
            C1523c c1523c2 = (C1523c) c1524d.f4414l;
            synchronized (c1523c2.f4401e) {
                c1523c2.f4401e.add(c1524d);
            }
            C1523c c1523c3 = (C1523c) c1524d.f4414l;
            if (!c1523c3.f4397a) {
                c1523c3.f4400d = new C1521a(c1523c3);
                HandlerThreadC1522b handlerThreadC1522b = new HandlerThreadC1522b(c1523c3, "sensor");
                handlerThreadC1522b.start();
                c1523c3.f4399c = handlerThreadC1522b.getLooper();
                c1523c3.f4397a = true;
            }
            c1524d.f4410h = true;
        }
        this.f4305c = true;
    }

    /* renamed from: c */
    public boolean mo1668c(int i7, int i8) {
        return false;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: d */
    public void mo1590d(Context context) {
        m1669i();
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: e */
    public void mo1591e(Context context) {
        this.f4311i = true;
        Iterator<C0888a> it = m1666f().iterator();
        while (it.hasNext()) {
            it.next().m772c();
        }
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: h */
    public void mo1592h(Context context) {
        this.f4311i = false;
        m1667g(new a(context));
    }

    /* renamed from: i */
    public final void m1669i() {
        if (this.f4305c) {
            C1523c c1523c = this.f4310h;
            synchronized (c1523c.f4401e) {
                c1523c.f4401e.remove(this);
            }
            C1524d c1524d = this.f4309g;
            if (c1524d.f4410h) {
                C1523c c1523c2 = (C1523c) c1524d.f4414l;
                synchronized (c1523c2.f4401e) {
                    c1523c2.f4401e.remove(c1524d);
                }
                C1523c c1523c3 = (C1523c) c1524d.f4414l;
                if (c1523c3.f4397a) {
                    c1523c3.f4398b.unregisterListener(c1523c3.f4400d);
                    c1523c3.f4400d = null;
                    c1523c3.f4399c.quit();
                    c1523c3.f4399c = null;
                    c1523c3.f4397a = false;
                }
                c1524d.f4410h = false;
            }
            this.f4305c = false;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i7) {
        Objects.requireNonNull(this.f4302a);
        synchronized (this.f4308f) {
            Matrix.setIdentityM(this.f4307e, 0);
            this.f4309g.m1708a(this.f4307e, 0);
        }
        this.f4302a.f4321c.m956b(this.f4313k);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.f4312j.m785W(sensorEvent);
        if (!this.f4311i || sensorEvent.accuracy == 0) {
            return;
        }
        Objects.requireNonNull(this.f4302a);
        synchronized (this.f4308f) {
            Matrix.setIdentityM(this.f4307e, 0);
            this.f4309g.m1708a(this.f4307e, 0);
        }
        this.f4302a.f4321c.m956b(this.f4313k);
    }
}
