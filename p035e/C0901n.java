package p035e;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActivityChooserModel;
import android.view.MotionEvent;
import android.view.Surface;
import android.widget.Toast;
import java.util.Iterator;
import java.util.Objects;
import p035e.AbstractC0889b;
import p035e.C0891d;
import p035e.C0896i;
import p043f.C0984a;
import p043f.C0985b;
import p043f.C0987d;
import p043f.C0988e;
import p051g.C1031a;
import p051g.C1034d;
import p051g.C1037g;
import p051g.C1038h;
import p075j.AbstractC1174b;
import p075j.C1180h;
import p099m.C1451b;
import p107n.C1506f;
import p115o.AbstractC1564a;
import p115o.C1570g;
import p125p.AbstractC1730a;
import p125p.C1731b;

/* compiled from: MDVRLibrary.java */
/* renamed from: e.n */
/* loaded from: classes.dex */
public class C0901n {

    /* renamed from: a */
    public RectF f1594a = new RectF(0.0f, 0.0f, 1024.0f, 1024.0f);

    /* renamed from: b */
    public C1506f f1595b;

    /* renamed from: c */
    public C1451b f1596c;

    /* renamed from: d */
    public C1570g f1597d;

    /* renamed from: e */
    public C1180h f1598e;

    /* renamed from: f */
    public C0896i f1599f;

    /* renamed from: g */
    public AbstractC0895h f1600g;

    /* renamed from: h */
    public C0897j f1601h;

    /* renamed from: i */
    @Nullable
    public AbstractC1730a f1602i;

    /* renamed from: j */
    public C0987d f1603j;

    /* renamed from: k */
    public C0892e f1604k;

    /* renamed from: l */
    public C0894g f1605l;

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0901n c0901n = C0901n.this;
            Iterator<AbstractC1174b> it = c0901n.f1598e.f2594a.iterator();
            while (it.hasNext()) {
                it.next().mo1374b();
            }
            C1570g c1570g = c0901n.f1597d;
            if (c1570g.f4714g == null) {
                c1570g.f4714g = ((AbstractC1564a) c1570g.f4142b).mo1810c(c1570g.f4715h);
            }
            AbstractC1174b abstractC1174b = c1570g.f4714g;
            if (abstractC1174b != null) {
                abstractC1174b.mo1374b();
            }
            AbstractC1730a abstractC1730a = c0901n.f1602i;
            if (abstractC1730a != null) {
                C1731b c1731b = (C1731b) abstractC1730a;
                SurfaceTexture surfaceTexture = c1731b.f4915c;
                if (surfaceTexture != null) {
                    surfaceTexture.release();
                }
                c1731b.f4915c = null;
                Surface surface = c1731b.f4914b;
                if (surface != null) {
                    surface.release();
                }
                c1731b.f4914b = null;
                ((C1731b) c0901n.f1602i).f4916d = null;
                c0901n.f1602i = null;
            }
        }
    }

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$b */
    public static class b {

        /* renamed from: c */
        public Context f1609c;

        /* renamed from: d */
        public AbstractC1730a f1610d;

        /* renamed from: e */
        public g f1611e;

        /* renamed from: f */
        public f f1612f;

        /* renamed from: g */
        public boolean f1613g;

        /* renamed from: h */
        public C1031a f1614h;

        /* renamed from: i */
        public AbstractC0889b f1615i;

        /* renamed from: j */
        public AbstractC0895h f1616j;

        /* renamed from: k */
        public C0984a f1617k;

        /* renamed from: l */
        public C1038h f1618l;

        /* renamed from: m */
        public C1034d f1619m;

        /* renamed from: a */
        public int f1607a = 101;

        /* renamed from: b */
        public int f1608b = 1;

        /* renamed from: n */
        public C1570g.b f1620n = null;

        public b(Context context, C0899l c0899l) {
            this.f1609c = context;
        }

        /* renamed from: a */
        public final C0901n m816a(AbstractC0895h abstractC0895h) {
            if (this.f1610d == null) {
                throw new RuntimeException("You must call video/bitmap function before build");
            }
            if (this.f1615i == null) {
                this.f1615i = new AbstractC0889b.b();
            }
            if (this.f1614h == null) {
                this.f1614h = new C1031a();
            }
            if (this.f1618l == null) {
                this.f1618l = new C1038h();
            }
            if (this.f1619m == null) {
                this.f1619m = new C1034d();
            }
            this.f1616j = abstractC0895h;
            return new C0901n(this, null);
        }
    }

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$c */
    public interface c {
    }

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$d */
    public interface d {
    }

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$e */
    public interface e {
    }

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$f */
    public interface f {
        /* renamed from: a */
        void mo805a(MotionEvent motionEvent);

        /* renamed from: b */
        void mo806b(MotionEvent motionEvent);
    }

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$g */
    public interface g {
        /* renamed from: a */
        void mo817a(int i7);
    }

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$h */
    public interface h {
        /* renamed from: a */
        void mo818a(Surface surface);
    }

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$i */
    public interface i {
    }

    /* compiled from: MDVRLibrary.java */
    /* renamed from: e.n$j */
    public class j implements Runnable {

        /* renamed from: e */
        public float f1621e;

        public j(C0899l c0899l) {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (C0888a c0888a : C0901n.this.f1597d.f4717j) {
                float f7 = this.f1621e;
                C0893f c0893f = c0888a.f1492k;
                c0893f.f1531h = f7;
                c0893f.f1530g = true;
            }
        }
    }

    public C0901n(b bVar, C0899l c0899l) {
        if (C0988e.f1823a == null) {
            C0988e.f1823a = new Handler(Looper.getMainLooper());
        }
        this.f1603j = new C0987d();
        C0892e c0892e = new C0892e(0);
        this.f1604k = c0892e;
        C0894g c0894g = new C0894g();
        this.f1605l = c0894g;
        c0894g.f1537a = null;
        C1570g.c cVar = new C1570g.c();
        cVar.f4722a = this.f1594a;
        cVar.f4723b = bVar.f1615i;
        cVar.f4725d = bVar.f1617k;
        C1037g c1037g = new C1037g();
        c1037g.f1964d = c0892e;
        c1037g.f1965e = c0894g;
        c1037g.f1962b = 0;
        c1037g.f1961a = bVar.f1610d;
        cVar.f4724c = c1037g;
        C1570g c1570g = new C1570g(201, this.f1603j, cVar);
        this.f1597d = c1570g;
        c1570g.m1595d(bVar.f1609c, bVar.f1611e);
        this.f1597d.f4720m = bVar.f1620n;
        C1451b c1451b = new C1451b(bVar.f1607a, this.f1603j);
        this.f1596c = c1451b;
        C1031a c1031a = bVar.f1614h;
        c1451b.f4210f = c1031a;
        c1451b.f4209e = c1031a.f1942b;
        c1451b.m1595d(bVar.f1609c, bVar.f1611e);
        C1506f.a aVar = new C1506f.a();
        aVar.f4320b = this.f1597d;
        aVar.f4319a = 1;
        C1506f c1506f = new C1506f(bVar.f1608b, this.f1603j, aVar);
        this.f1595b = c1506f;
        c1506f.m1595d(bVar.f1609c, bVar.f1611e);
        this.f1598e = new C1180h(0);
        Context context = bVar.f1609c;
        AbstractC0895h abstractC0895h = bVar.f1616j;
        float[] fArr = C0985b.f1817a;
        if (((ActivityManager) context.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY)).getDeviceConfigurationInfo().reqGlEsVersion >= 131072) {
            abstractC0895h.mo800b(context);
            C0891d.b bVar2 = new C0891d.b(null);
            bVar2.f1517a = context;
            bVar2.f1520d = this.f1603j;
            bVar2.f1521e = this.f1598e;
            bVar2.f1519c = this.f1597d;
            bVar2.f1518b = this.f1596c;
            abstractC0895h.mo803e(new C0891d(bVar2, null));
            this.f1600g = abstractC0895h;
        } else {
            this.f1600g.mo799a().setVisibility(8);
            Toast.makeText(context, "OpenGLES2 not supported.", 0).show();
        }
        this.f1602i = bVar.f1610d;
        this.f1601h = new C0897j(bVar.f1609c);
        C0897j c0897j = new C0897j(bVar.f1609c);
        this.f1601h = c0897j;
        f fVar = bVar.f1612f;
        if (fVar != null) {
            c0897j.f1571b.add(fVar);
        }
        j jVar = new j(null);
        C0897j c0897j2 = this.f1601h;
        c0897j2.f1570a = new C0899l(this, jVar);
        c0897j2.f1575f = bVar.f1613g;
        C1038h c1038h = bVar.f1618l;
        float f7 = c1038h.f1967b;
        c0897j2.f1576g = f7;
        c0897j2.f1577h = c1038h.f1966a;
        c0897j2.f1578i = 1.0f;
        float fMin = Math.min(c0897j2.f1577h, Math.max(f7, c1038h.f1968c));
        c cVar2 = c0897j2.f1570a;
        if (cVar2 != null) {
            C0899l c0899l2 = (C0899l) cVar2;
            j jVar2 = c0899l2.f1591a;
            jVar2.f1621e = fMin;
            c0899l2.f1592b.f1603j.m956b(jVar2);
        }
        c0897j2.f1579j = fMin;
        C0897j c0897j3 = this.f1601h;
        c0897j3.f1581l = true;
        c0897j3.f1582m = bVar.f1619m;
        c0897j3.f1583n = 1.0f;
        this.f1600g.mo799a().setOnTouchListener(new ViewOnTouchListenerC0900m(this));
        C0896i.c cVar3 = new C0896i.c(null);
        cVar3.f1559c = this.f1598e;
        cVar3.f1557a = this.f1596c;
        cVar3.f1558b = this.f1597d;
        C0896i c0896i = new C0896i(cVar3, null);
        this.f1599f = c0896i;
        c0896i.f1540a = true;
        c0896i.f1544e = null;
        c0896i.f1545f = null;
        C0897j c0897j4 = this.f1601h;
        f fVar2 = c0896i.f1552m;
        Objects.requireNonNull(c0897j4);
        if (fVar2 != null) {
            c0897j4.f1571b.add(fVar2);
        }
        this.f1598e.f2594a.add(this.f1597d.f4719l);
        this.f1598e.f2594a.add(this.f1599f.f1553n);
    }

    /* renamed from: a */
    public void m813a() {
        C1731b c1731b;
        Surface surface;
        h hVar;
        AbstractC1730a abstractC1730a = this.f1602i;
        if (abstractC1730a == null || (surface = (c1731b = (C1731b) abstractC1730a).f4914b) == null || (hVar = c1731b.f4916d) == null) {
            return;
        }
        hVar.mo818a(surface);
    }

    /* renamed from: b */
    public void m814b() {
        this.f1603j.m956b(new a());
        this.f1603j.f1819a = true;
    }

    /* renamed from: c */
    public void m815c(Context context) {
        this.f1595b.m1670e(context);
        AbstractC0895h abstractC0895h = this.f1600g;
        if (abstractC0895h != null) {
            abstractC0895h.mo802d();
        }
    }
}
