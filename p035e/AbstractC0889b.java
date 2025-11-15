package p035e;

import android.opengl.Matrix;
import p035e.C0888a;

/* compiled from: MD360DirectorFactory.java */
/* renamed from: e.b */
/* loaded from: classes.dex */
public abstract class AbstractC0889b {

    /* compiled from: MD360DirectorFactory.java */
    /* renamed from: e.b$b */
    public static class b extends AbstractC0889b {
        @Override // p035e.AbstractC0889b
        /* renamed from: a */
        public C0888a mo779a(int i7) {
            return new C0888a(new C0888a.a());
        }
    }

    /* compiled from: MD360DirectorFactory.java */
    /* renamed from: e.b$c */
    public static class c extends C0888a {
        public c(C0888a.a aVar, a aVar2) {
            super(aVar);
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
            Matrix.orthoM(this.f1483b, 0, -1.0f, 1.0f, -1.0f, 1.0f, m771b(), 500.0f);
        }

        @Override // p035e.C0888a
        /* renamed from: i */
        public void mo778i(float[] fArr) {
        }
    }

    /* renamed from: a */
    public abstract C0888a mo779a(int i7);
}
