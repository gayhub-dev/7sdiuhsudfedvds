package p115o;

import android.content.Context;
import android.graphics.RectF;
import android.opengl.Matrix;
import p035e.AbstractC0889b;
import p035e.C0888a;
import p051g.AbstractC1039i;
import p051g.C1037g;
import p059h.C1078a;
import p067i.AbstractC1126a;
import p067i.C1131f;
import p067i.C1132g;
import p075j.AbstractC1174b;
import p075j.C1178f;

/* compiled from: PlaneProjection.java */
/* renamed from: o.f */
/* loaded from: classes.dex */
public class C1569f extends AbstractC1564a {

    /* renamed from: c */
    public static final AbstractC1039i f4699c;

    /* renamed from: a */
    public C1132g f4700a;

    /* renamed from: b */
    public d f4701b;

    /* compiled from: PlaneProjection.java */
    /* renamed from: o.f$b */
    public class b extends C0888a {

        /* renamed from: s */
        public final float f4702s;

        public b(C0888a.a aVar, a aVar2) {
            super(aVar);
            this.f4702s = m771b();
        }

        @Override // p035e.C0888a
        /* renamed from: d */
        public void mo773d(float f7) {
        }

        @Override // p035e.C0888a
        /* renamed from: e */
        public void mo774e(float f7) {
        }

        @Override // p035e.C0888a
        /* renamed from: h */
        public void mo777h() {
            d dVar = C1569f.this.f4701b;
            dVar.f4706b = this.f1492k.f1532i;
            dVar.m1814a();
            float fM771b = this.f4702s / m771b();
            d dVar2 = C1569f.this.f4701b;
            float f7 = dVar2.f4708d;
            float f8 = dVar2.f4709e;
            Matrix.orthoM(this.f1483b, 0, ((-f7) / 2.0f) * fM771b, (f7 / 2.0f) * fM771b, ((-f8) / 2.0f) * fM771b, (f8 / 2.0f) * fM771b, 1.0f, 500.0f);
        }

        @Override // p035e.C0888a
        /* renamed from: i */
        public void mo778i(float[] fArr) {
        }
    }

    /* compiled from: PlaneProjection.java */
    /* renamed from: o.f$c */
    public class c extends AbstractC0889b {
        public c(a aVar) {
        }

        @Override // p035e.AbstractC0889b
        /* renamed from: a */
        public C0888a mo779a(int i7) {
            return C1569f.this.new b(new C0888a.a(), null);
        }
    }

    /* compiled from: PlaneProjection.java */
    /* renamed from: o.f$d */
    public static class d {

        /* renamed from: a */
        public RectF f4705a;

        /* renamed from: b */
        public float f4706b;

        /* renamed from: c */
        public int f4707c;

        /* renamed from: d */
        public float f4708d = 1.0f;

        /* renamed from: e */
        public float f4709e = 1.0f;

        /* renamed from: f */
        public float f4710f = 1.0f;

        /* renamed from: g */
        public float f4711g = 1.0f;

        public d(int i7, RectF rectF) {
            this.f4707c = i7;
            this.f4705a = rectF;
        }

        /* renamed from: a */
        public void m1814a() {
            float f7 = this.f4706b;
            float fM1815b = m1815b();
            int i7 = this.f4707c;
            if (i7 == 208) {
                if (fM1815b > f7) {
                    this.f4708d = f7 * 1.0f;
                    this.f4709e = 1.0f;
                    this.f4710f = fM1815b * 1.0f;
                    this.f4711g = 1.0f;
                    return;
                }
                this.f4708d = 1.0f;
                this.f4709e = 1.0f / f7;
                this.f4710f = 1.0f;
                this.f4711g = 1.0f / fM1815b;
                return;
            }
            if (i7 == 209) {
                this.f4711g = 1.0f;
                this.f4710f = 1.0f;
                this.f4709e = 1.0f;
                this.f4708d = 1.0f;
                return;
            }
            if (f7 > fM1815b) {
                this.f4708d = f7 * 1.0f;
                this.f4709e = 1.0f;
                this.f4710f = fM1815b * 1.0f;
                this.f4711g = 1.0f;
                return;
            }
            this.f4708d = 1.0f;
            this.f4709e = 1.0f / f7;
            this.f4710f = 1.0f;
            this.f4711g = 1.0f / fM1815b;
        }

        /* renamed from: b */
        public float m1815b() {
            return this.f4705a.width() / this.f4705a.height();
        }
    }

    static {
        C1078a c1078a = new C1078a();
        c1078a.f2176g |= c1078a.f2172c != -2.0f;
        c1078a.f2172c = -2.0f;
        f4699c = c1078a;
    }

    public C1569f(d dVar) {
        this.f4701b = dVar;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: a */
    public boolean mo1588a(Context context) {
        return true;
    }

    @Override // p115o.AbstractC1564a
    /* renamed from: c */
    public AbstractC1174b mo1810c(C1037g c1037g) {
        return new C1178f(c1037g);
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: e */
    public void mo1591e(Context context) {
        C1132g c1132g = new C1132g(this.f4701b);
        this.f4700a = c1132g;
        C1131f.m1294a(context, c1132g);
    }

    @Override // p115o.InterfaceC1567d
    /* renamed from: f */
    public AbstractC1126a mo1812f() {
        return this.f4700a;
    }

    @Override // p115o.InterfaceC1567d
    /* renamed from: g */
    public AbstractC1039i mo1813g() {
        return f4699c;
    }

    @Override // p091l.InterfaceC1406a
    /* renamed from: h */
    public void mo1592h(Context context) {
    }

    @Override // p115o.AbstractC1564a
    /* renamed from: i */
    public AbstractC0889b mo1811i() {
        return new c(null);
    }
}
