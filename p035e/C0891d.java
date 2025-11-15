package p035e;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import p043f.C0985b;
import p043f.C0987d;
import p051g.AbstractC1039i;
import p067i.C1131f;
import p075j.AbstractC1173a;
import p075j.AbstractC1174b;
import p075j.C1175c;
import p075j.C1180h;
import p099m.C1451b;
import p115o.AbstractC1564a;
import p115o.C1570g;

/* compiled from: MD360Renderer.java */
/* renamed from: e.d */
/* loaded from: classes.dex */
public class C0891d implements GLSurfaceView.Renderer {

    /* renamed from: a */
    public C1451b f1509a;

    /* renamed from: b */
    public C1570g f1510b;

    /* renamed from: c */
    public C1180h f1511c;

    /* renamed from: d */
    public AbstractC1173a f1512d;

    /* renamed from: e */
    public C0987d f1513e;

    /* renamed from: f */
    public int f1514f;

    /* renamed from: g */
    public int f1515g;

    /* renamed from: h */
    public final Context f1516h;

    /* compiled from: MD360Renderer.java */
    /* renamed from: e.d$b */
    public static class b {

        /* renamed from: a */
        public Context f1517a;

        /* renamed from: b */
        public C1451b f1518b;

        /* renamed from: c */
        public C1570g f1519c;

        /* renamed from: d */
        public C0987d f1520d;

        /* renamed from: e */
        public C1180h f1521e;

        public b(a aVar) {
        }
    }

    public C0891d(b bVar, a aVar) {
        this.f1516h = bVar.f1517a;
        C1451b c1451b = bVar.f1518b;
        this.f1509a = c1451b;
        this.f1510b = bVar.f1519c;
        this.f1511c = bVar.f1521e;
        this.f1513e = bVar.f1520d;
        this.f1512d = new C1175c(c1451b);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) throws IOException {
        this.f1513e.m955a();
        GLES20.glClear(16640);
        C0985b.m946b("MD360Renderer onDrawFrame begin. ");
        int iM1636e = this.f1509a.m1636e();
        int i7 = (int) ((this.f1514f * 1.0f) / iM1636e);
        int i8 = this.f1515g;
        AbstractC1173a abstractC1173a = this.f1512d;
        Context context = this.f1516h;
        Objects.requireNonNull(abstractC1173a);
        long id = Thread.currentThread().getId();
        if (abstractC1173a.f2562b != id) {
            abstractC1173a.f2562b = id;
            abstractC1173a.f2561a = false;
        }
        if (!abstractC1173a.f2561a) {
            C1175c c1175c = (C1175c) abstractC1173a;
            c1175c.f2565c.m780a(context);
            C1131f.m1294a(context, c1175c.f2566d);
            abstractC1173a.f2561a = true;
        }
        AbstractC1173a abstractC1173a2 = this.f1512d;
        int i9 = this.f1514f;
        int i10 = this.f1515g;
        C1175c c1175c2 = (C1175c) abstractC1173a2;
        boolean z6 = c1175c2.f2571i.f4209e;
        c1175c2.f2568f = z6;
        if (z6) {
            c1175c2.f2569g.m1378a(i9, i10);
            c1175c2.f2567e.m775f(i9, i10);
            c1175c2.f2566d.f2572e = iM1636e;
            GLES20.glClear(16640);
            C0985b.m946b("MDBarrelDistortionLinePipe glClear");
        }
        C1570g c1570g = this.f1510b;
        List<C0888a> list = c1570g.f4717j;
        if (c1570g.f4714g == null) {
            c1570g.f4714g = ((AbstractC1564a) c1570g.f4142b).mo1810c(c1570g.f4715h);
        }
        AbstractC1174b abstractC1174b = c1570g.f4714g;
        if (abstractC1174b != null) {
            abstractC1174b.m1377e(this.f1516h);
            abstractC1174b.mo807a(this.f1514f, this.f1515g);
        }
        for (AbstractC1174b abstractC1174b2 : this.f1511c.f2594a) {
            abstractC1174b2.m1377e(this.f1516h);
            abstractC1174b2.mo807a(this.f1514f, this.f1515g);
        }
        for (int i11 = 0; i11 < iM1636e && i11 < list.size(); i11++) {
            C0888a c0888a = list.get(i11);
            int i12 = i7 * i11;
            GLES20.glViewport(i12, 0, i7, i8);
            GLES20.glEnable(3089);
            GLES20.glScissor(i12, 0, i7, i8);
            if (abstractC1174b != null) {
                abstractC1174b.mo1376d(i11, i7, i8, c0888a);
            }
            Iterator<AbstractC1174b> it = this.f1511c.f2594a.iterator();
            while (it.hasNext()) {
                it.next().mo1376d(i11, i7, i8, c0888a);
            }
            GLES20.glDisable(3089);
        }
        AbstractC1173a abstractC1173a3 = this.f1512d;
        int i13 = this.f1514f;
        int i14 = this.f1515g;
        C1175c c1175c3 = (C1175c) abstractC1173a3;
        if (c1175c3.f2568f) {
            GLES20.glBindFramebuffer(36160, c1175c3.f2569g.f2579e[0]);
            int i15 = i13 / iM1636e;
            for (int i16 = 0; i16 < iM1636e; i16++) {
                int i17 = i15 * i16;
                GLES20.glViewport(i17, 0, i15, i14);
                GLES20.glEnable(3089);
                GLES20.glScissor(i17, 0, i15, i14);
                GLES20.glUseProgram(c1175c3.f2565c.f1505e);
                C0985b.m946b("MDBarrelDistortionLinePipe mProgram use");
                c1175c3.f2566d.mo1292e(c1175c3.f2565c, i16);
                c1175c3.f2566d.mo1291d(c1175c3.f2565c, i16);
                c1175c3.f2567e.m770a();
                c1175c3.f2567e.m776g(c1175c3.f2565c, AbstractC1039i.f1969a);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, c1175c3.f2569g.f2575a);
                c1175c3.f2566d.m1288a();
                GLES20.glDisable(3089);
            }
        }
        C0985b.m946b("MD360Renderer onDrawFrame end. ");
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i7, int i8) {
        this.f1514f = i7;
        this.f1515g = i8;
        this.f1513e.m955a();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glEnable(2884);
    }
}
