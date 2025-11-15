package p035e;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.View;
import com.google.android.apps.muzei.render.GLTextureView;
import java.util.Objects;

/* compiled from: MDGLScreenWrapper.java */
/* renamed from: e.h */
/* loaded from: classes.dex */
public abstract class AbstractC0895h {

    /* compiled from: MDGLScreenWrapper.java */
    /* renamed from: e.h$b */
    public static class b extends AbstractC0895h {

        /* renamed from: a */
        public GLSurfaceView f1538a;

        public b(GLSurfaceView gLSurfaceView, a aVar) {
            this.f1538a = gLSurfaceView;
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: a */
        public View mo799a() {
            return this.f1538a;
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: b */
        public void mo800b(Context context) {
            this.f1538a.setEGLContextClientVersion(2);
            this.f1538a.setPreserveEGLContextOnPause(true);
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: c */
        public void mo801c() {
            this.f1538a.onPause();
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: d */
        public void mo802d() {
            this.f1538a.onResume();
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: e */
        public void mo803e(GLSurfaceView.Renderer renderer) {
            this.f1538a.setRenderer(renderer);
        }
    }

    /* compiled from: MDGLScreenWrapper.java */
    /* renamed from: e.h$c */
    public static class c extends AbstractC0895h {

        /* renamed from: a */
        public GLTextureView f1539a;

        public c(GLTextureView gLTextureView) {
            this.f1539a = gLTextureView;
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: a */
        public View mo799a() {
            return this.f1539a;
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: b */
        public void mo800b(Context context) {
            this.f1539a.setEGLContextClientVersion(2);
            this.f1539a.setPreserveEGLContextOnPause(true);
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: c */
        public void mo801c() {
            GLTextureView.C0767j c0767j = this.f1539a.f1220f;
            Objects.requireNonNull(c0767j);
            GLTextureView.C0768k c0768k = GLTextureView.f1218p;
            synchronized (c0768k) {
                c0767j.f1249g = true;
                c0768k.notifyAll();
                while (!c0767j.f1248f && !c0767j.f1250h) {
                    try {
                        GLTextureView.f1218p.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: d */
        public void mo802d() {
            GLTextureView.C0767j c0767j = this.f1539a.f1220f;
            Objects.requireNonNull(c0767j);
            GLTextureView.C0768k c0768k = GLTextureView.f1218p;
            synchronized (c0768k) {
                c0767j.f1249g = false;
                c0767j.f1261s = true;
                c0767j.f1262t = false;
                c0768k.notifyAll();
                while (!c0767j.f1248f && c0767j.f1250h && !c0767j.f1262t) {
                    try {
                        GLTextureView.f1218p.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        @Override // p035e.AbstractC0895h
        /* renamed from: e */
        public void mo803e(GLSurfaceView.Renderer renderer) {
            this.f1539a.setRenderer(renderer);
        }
    }

    /* renamed from: a */
    public abstract View mo799a();

    /* renamed from: b */
    public abstract void mo800b(Context context);

    /* renamed from: c */
    public abstract void mo801c();

    /* renamed from: d */
    public abstract void mo802d();

    /* renamed from: e */
    public abstract void mo803e(GLSurfaceView.Renderer renderer);
}
