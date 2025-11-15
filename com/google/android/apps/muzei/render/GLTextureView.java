package com.google.android.apps.muzei.render;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.TextureView;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import p009b.C0413b;

/* loaded from: classes.dex */
public class GLTextureView extends TextureView implements TextureView.SurfaceTextureListener {

    /* renamed from: p */
    public static final C0768k f1218p = new C0768k(null);

    /* renamed from: e */
    public final WeakReference<GLTextureView> f1219e;

    /* renamed from: f */
    public C0767j f1220f;

    /* renamed from: g */
    public GLSurfaceView.Renderer f1221g;

    /* renamed from: h */
    public boolean f1222h;

    /* renamed from: i */
    public InterfaceC0763f f1223i;

    /* renamed from: j */
    public InterfaceC0764g f1224j;

    /* renamed from: k */
    public InterfaceC0765h f1225k;

    /* renamed from: l */
    public InterfaceC0769l f1226l;

    /* renamed from: m */
    public int f1227m;

    /* renamed from: n */
    public int f1228n;

    /* renamed from: o */
    public boolean f1229o;

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$b */
    public abstract class AbstractC0759b implements InterfaceC0763f {

        /* renamed from: a */
        public int[] f1230a;

        public AbstractC0759b(int[] iArr) {
            if (GLTextureView.this.f1228n == 2) {
                int length = iArr.length;
                int[] iArr2 = new int[length + 2];
                int i7 = length - 1;
                System.arraycopy(iArr, 0, iArr2, 0, i7);
                iArr2[i7] = 12352;
                iArr2[length] = 4;
                iArr2[length + 1] = 12344;
                iArr = iArr2;
            }
            this.f1230a = iArr;
        }
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$c */
    public class C0760c extends AbstractC0759b {

        /* renamed from: c */
        public int[] f1232c;

        /* renamed from: d */
        public int f1233d;

        /* renamed from: e */
        public int f1234e;

        /* renamed from: f */
        public int f1235f;

        /* renamed from: g */
        public int f1236g;

        /* renamed from: h */
        public int f1237h;

        /* renamed from: i */
        public int f1238i;

        public C0760c(int i7, int i8, int i9, int i10, int i11, int i12) {
            super(new int[]{12324, i7, 12323, i8, 12322, i9, 12321, i10, 12325, i11, 12326, i12, 12344});
            this.f1232c = new int[1];
            this.f1233d = i7;
            this.f1234e = i8;
            this.f1235f = i9;
            this.f1236g = i10;
            this.f1237h = i11;
            this.f1238i = i12;
        }
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$d */
    public class C0761d implements InterfaceC0764g {
        public C0761d(C0758a c0758a) {
        }
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$e */
    public static class C0762e implements InterfaceC0765h {
        public C0762e(C0758a c0758a) {
        }
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$f */
    public interface InterfaceC0763f {
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$g */
    public interface InterfaceC0764g {
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$h */
    public interface InterfaceC0765h {
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$i */
    public static class C0766i {

        /* renamed from: a */
        public WeakReference<GLTextureView> f1241a;

        /* renamed from: b */
        public EGL10 f1242b;

        /* renamed from: c */
        public EGLDisplay f1243c;

        /* renamed from: d */
        public EGLSurface f1244d;

        /* renamed from: e */
        public EGLConfig f1245e;

        /* renamed from: f */
        public EGLContext f1246f;

        public C0766i(WeakReference<GLTextureView> weakReference) {
            this.f1241a = weakReference;
        }

        /* renamed from: a */
        public boolean m631a() {
            if (this.f1242b == null) {
                throw new RuntimeException("egl not initialized");
            }
            if (this.f1243c == null) {
                throw new RuntimeException("eglDisplay not initialized");
            }
            if (this.f1245e == null) {
                throw new RuntimeException("mEglConfig not initialized");
            }
            m632b();
            GLTextureView gLTextureView = this.f1241a.get();
            EGLSurface eGLSurfaceEglCreateWindowSurface = null;
            if (gLTextureView != null) {
                InterfaceC0765h interfaceC0765h = gLTextureView.f1225k;
                EGL10 egl10 = this.f1242b;
                EGLDisplay eGLDisplay = this.f1243c;
                EGLConfig eGLConfig = this.f1245e;
                SurfaceTexture surfaceTexture = gLTextureView.getSurfaceTexture();
                Objects.requireNonNull((C0762e) interfaceC0765h);
                try {
                    eGLSurfaceEglCreateWindowSurface = egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, surfaceTexture, null);
                } catch (IllegalArgumentException unused) {
                }
                this.f1244d = eGLSurfaceEglCreateWindowSurface;
            } else {
                this.f1244d = null;
            }
            EGLSurface eGLSurface = this.f1244d;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                this.f1242b.eglGetError();
                return false;
            }
            if (this.f1242b.eglMakeCurrent(this.f1243c, eGLSurface, eGLSurface, this.f1246f)) {
                return true;
            }
            this.f1242b.eglGetError();
            return false;
        }

        /* renamed from: b */
        public final void m632b() {
            EGLSurface eGLSurface;
            EGLSurface eGLSurface2 = this.f1244d;
            if (eGLSurface2 == null || eGLSurface2 == (eGLSurface = EGL10.EGL_NO_SURFACE)) {
                return;
            }
            this.f1242b.eglMakeCurrent(this.f1243c, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
            GLTextureView gLTextureView = this.f1241a.get();
            if (gLTextureView != null) {
                InterfaceC0765h interfaceC0765h = gLTextureView.f1225k;
                EGL10 egl10 = this.f1242b;
                EGLDisplay eGLDisplay = this.f1243c;
                EGLSurface eGLSurface3 = this.f1244d;
                Objects.requireNonNull((C0762e) interfaceC0765h);
                egl10.eglDestroySurface(eGLDisplay, eGLSurface3);
            }
            this.f1244d = null;
        }

        /* renamed from: c */
        public void m633c() {
            if (this.f1246f != null) {
                GLTextureView gLTextureView = this.f1241a.get();
                if (gLTextureView != null) {
                    InterfaceC0764g interfaceC0764g = gLTextureView.f1224j;
                    EGL10 egl10 = this.f1242b;
                    EGLDisplay eGLDisplay = this.f1243c;
                    EGLContext eGLContext = this.f1246f;
                    Objects.requireNonNull((C0761d) interfaceC0764g);
                    if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                        Objects.toString(eGLDisplay);
                        Objects.toString(eGLContext);
                        egl10.eglGetError();
                        throw new RuntimeException("eglDestroyContex failed");
                    }
                }
                this.f1246f = null;
            }
            EGLDisplay eGLDisplay2 = this.f1243c;
            if (eGLDisplay2 != null) {
                this.f1242b.eglTerminate(eGLDisplay2);
                this.f1243c = null;
            }
        }

        /* renamed from: d */
        public void m634d() {
            EGLConfig eGLConfig;
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            this.f1242b = egl10;
            EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.f1243c = eGLDisplayEglGetDisplay;
            if (eGLDisplayEglGetDisplay == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (!this.f1242b.eglInitialize(eGLDisplayEglGetDisplay, new int[2])) {
                throw new RuntimeException("eglInitialize failed");
            }
            GLTextureView gLTextureView = this.f1241a.get();
            if (gLTextureView == null) {
                this.f1245e = null;
                this.f1246f = null;
            } else {
                InterfaceC0763f interfaceC0763f = gLTextureView.f1223i;
                EGL10 egl102 = this.f1242b;
                EGLDisplay eGLDisplay = this.f1243c;
                AbstractC0759b abstractC0759b = (AbstractC0759b) interfaceC0763f;
                int[] iArr = new int[1];
                if (!egl102.eglChooseConfig(eGLDisplay, abstractC0759b.f1230a, null, 0, iArr)) {
                    throw new IllegalArgumentException("eglChooseConfig failed");
                }
                int i7 = iArr[0];
                if (i7 <= 0) {
                    throw new IllegalArgumentException("No configs match configSpec");
                }
                EGLConfig[] eGLConfigArr = new EGLConfig[i7];
                if (!egl102.eglChooseConfig(eGLDisplay, abstractC0759b.f1230a, eGLConfigArr, i7, iArr)) {
                    throw new IllegalArgumentException("eglChooseConfig#2 failed");
                }
                C0760c c0760c = (C0760c) abstractC0759b;
                int i8 = 0;
                while (true) {
                    if (i8 >= i7) {
                        eGLConfig = null;
                        break;
                    }
                    eGLConfig = eGLConfigArr[i8];
                    int i9 = egl102.eglGetConfigAttrib(eGLDisplay, eGLConfig, 12325, c0760c.f1232c) ? c0760c.f1232c[0] : 0;
                    int i10 = egl102.eglGetConfigAttrib(eGLDisplay, eGLConfig, 12326, c0760c.f1232c) ? c0760c.f1232c[0] : 0;
                    if (i9 >= c0760c.f1237h && i10 >= c0760c.f1238i) {
                        int i11 = egl102.eglGetConfigAttrib(eGLDisplay, eGLConfig, 12324, c0760c.f1232c) ? c0760c.f1232c[0] : 0;
                        int i12 = egl102.eglGetConfigAttrib(eGLDisplay, eGLConfig, 12323, c0760c.f1232c) ? c0760c.f1232c[0] : 0;
                        int i13 = egl102.eglGetConfigAttrib(eGLDisplay, eGLConfig, 12322, c0760c.f1232c) ? c0760c.f1232c[0] : 0;
                        int i14 = egl102.eglGetConfigAttrib(eGLDisplay, eGLConfig, 12321, c0760c.f1232c) ? c0760c.f1232c[0] : 0;
                        if (i11 == c0760c.f1233d && i12 == c0760c.f1234e && i13 == c0760c.f1235f && i14 == c0760c.f1236g) {
                            break;
                        }
                    }
                    i8++;
                }
                if (eGLConfig == null) {
                    throw new IllegalArgumentException("No config chosen");
                }
                this.f1245e = eGLConfig;
                InterfaceC0764g interfaceC0764g = gLTextureView.f1224j;
                EGL10 egl103 = this.f1242b;
                EGLDisplay eGLDisplay2 = this.f1243c;
                C0761d c0761d = (C0761d) interfaceC0764g;
                Objects.requireNonNull(c0761d);
                int i15 = GLTextureView.this.f1228n;
                int[] iArr2 = {12440, i15, 12344};
                EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
                if (i15 == 0) {
                    iArr2 = null;
                }
                this.f1246f = egl103.eglCreateContext(eGLDisplay2, eGLConfig, eGLContext, iArr2);
            }
            EGLContext eGLContext2 = this.f1246f;
            if (eGLContext2 != null && eGLContext2 != EGL10.EGL_NO_CONTEXT) {
                this.f1244d = null;
            } else {
                this.f1246f = null;
                this.f1242b.eglGetError();
                throw new RuntimeException("createContext failed");
            }
        }
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$j */
    public static class C0767j extends Thread {

        /* renamed from: e */
        public boolean f1247e;

        /* renamed from: f */
        public boolean f1248f;

        /* renamed from: g */
        public boolean f1249g;

        /* renamed from: h */
        public boolean f1250h;

        /* renamed from: i */
        public boolean f1251i;

        /* renamed from: j */
        public boolean f1252j;

        /* renamed from: k */
        public boolean f1253k;

        /* renamed from: l */
        public boolean f1254l;

        /* renamed from: m */
        public boolean f1255m;

        /* renamed from: n */
        public boolean f1256n;

        /* renamed from: o */
        public boolean f1257o;

        /* renamed from: t */
        public boolean f1262t;

        /* renamed from: w */
        public C0766i f1265w;

        /* renamed from: x */
        public WeakReference<GLTextureView> f1266x;

        /* renamed from: u */
        public ArrayList<Runnable> f1263u = new ArrayList<>();

        /* renamed from: v */
        public boolean f1264v = true;

        /* renamed from: p */
        public int f1258p = 0;

        /* renamed from: q */
        public int f1259q = 0;

        /* renamed from: s */
        public boolean f1261s = true;

        /* renamed from: r */
        public int f1260r = 1;

        public C0767j(WeakReference<GLTextureView> weakReference) {
            this.f1266x = weakReference;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:199:0x0284 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:201:0x0134 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:217:0x026b A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:219:0x001b A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:224:0x0168 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:226:0x0270 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x015a A[Catch: all -> 0x027b, TryCatch #6 {, blocks: (B:5:0x0020, B:7:0x0024, B:17:0x003c, B:19:0x0044, B:108:0x0184, B:20:0x0050, B:22:0x0056, B:24:0x0061, B:26:0x0065, B:28:0x0071, B:30:0x007a, B:32:0x007e, B:34:0x0083, B:36:0x0087, B:41:0x0097, B:43:0x00a1, B:39:0x0093, B:45:0x00a6, B:47:0x00b0, B:48:0x00b5, B:50:0x00b9, B:52:0x00bd, B:54:0x00c1, B:55:0x00c4, B:56:0x00d1, B:58:0x00d5, B:60:0x00d9, B:62:0x00e5, B:63:0x00f1, B:65:0x00f7, B:96:0x0156, B:98:0x015a, B:100:0x015e, B:101:0x0164, B:103:0x0168, B:105:0x016c, B:107:0x0179, B:183:0x0270, B:69:0x0104, B:73:0x010f, B:84:0x0134, B:86:0x013a, B:88:0x0143, B:90:0x014b, B:91:0x014e, B:92:0x0151, B:76:0x0119, B:78:0x011d, B:81:0x012a), top: B:209:0x0020, outer: #7, inners: #2 }] */
        /* JADX WARN: Type inference failed for: r0v30 */
        /* JADX WARN: Type inference failed for: r0v31 */
        /* JADX WARN: Type inference failed for: r0v48 */
        /* JADX WARN: Type inference failed for: r14v51 */
        /* JADX WARN: Type inference failed for: r14v52 */
        /* JADX WARN: Type inference failed for: r14v8, types: [java.io.Writer] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void m635a() {
            /*
                Method dump skipped, instructions count: 662
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.muzei.render.GLTextureView.C0767j.m635a():void");
        }

        /* renamed from: b */
        public void m636b(int i7, int i8) {
            C0768k c0768k = GLTextureView.f1218p;
            C0768k c0768k2 = GLTextureView.f1218p;
            synchronized (c0768k2) {
                this.f1258p = i7;
                this.f1259q = i8;
                this.f1264v = true;
                this.f1261s = true;
                this.f1262t = false;
                c0768k2.notifyAll();
                while (!this.f1248f && !this.f1250h && !this.f1262t) {
                    if (!(this.f1254l && this.f1255m && m637c())) {
                        break;
                    }
                    try {
                        C0768k c0768k3 = GLTextureView.f1218p;
                        GLTextureView.f1218p.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: c */
        public final boolean m637c() {
            return !this.f1250h && this.f1251i && !this.f1252j && this.f1258p > 0 && this.f1259q > 0 && (this.f1261s || this.f1260r == 1);
        }

        /* renamed from: d */
        public void m638d() {
            C0768k c0768k = GLTextureView.f1218p;
            C0768k c0768k2 = GLTextureView.f1218p;
            synchronized (c0768k2) {
                this.f1247e = true;
                c0768k2.notifyAll();
                while (!this.f1248f) {
                    try {
                        C0768k c0768k3 = GLTextureView.f1218p;
                        GLTextureView.f1218p.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        /* renamed from: e */
        public void m639e(int i7) {
            if (i7 < 0 || i7 > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            C0768k c0768k = GLTextureView.f1218p;
            C0768k c0768k2 = GLTextureView.f1218p;
            synchronized (c0768k2) {
                this.f1260r = i7;
                c0768k2.notifyAll();
            }
        }

        /* renamed from: f */
        public final void m640f() {
            if (this.f1254l) {
                this.f1265w.m633c();
                this.f1254l = false;
                C0768k c0768k = GLTextureView.f1218p;
                C0768k c0768k2 = GLTextureView.f1218p;
                if (c0768k2.f1271e == this) {
                    c0768k2.f1271e = null;
                }
                c0768k2.notifyAll();
            }
        }

        /* renamed from: g */
        public final void m641g() {
            if (this.f1255m) {
                this.f1255m = false;
                this.f1265w.m632b();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            StringBuilder sbM112a = C0413b.m112a("GLThread ");
            sbM112a.append(getId());
            setName(sbM112a.toString());
            try {
                m635a();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                C0768k c0768k = GLTextureView.f1218p;
                GLTextureView.f1218p.m646e(this);
                throw th;
            }
            C0768k c0768k2 = GLTextureView.f1218p;
            GLTextureView.f1218p.m646e(this);
        }
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$k */
    public static class C0768k {

        /* renamed from: a */
        public boolean f1267a;

        /* renamed from: b */
        public boolean f1268b;

        /* renamed from: c */
        public boolean f1269c;

        /* renamed from: d */
        public boolean f1270d;

        /* renamed from: e */
        public C0767j f1271e;

        public C0768k(C0758a c0758a) {
        }

        /* renamed from: a */
        public synchronized void m642a(GL10 gl10) {
            if (!this.f1268b) {
                m643b();
                this.f1269c = !gl10.glGetString(7937).startsWith("Q3Dimension MSM7500 ");
                notifyAll();
                this.f1270d = this.f1269c ? false : true;
                this.f1268b = true;
            }
        }

        /* renamed from: b */
        public final void m643b() {
            if (this.f1267a) {
                return;
            }
            this.f1269c = true;
            this.f1267a = true;
        }

        /* renamed from: c */
        public synchronized boolean m644c() {
            return this.f1270d;
        }

        /* renamed from: d */
        public synchronized boolean m645d() {
            m643b();
            return !this.f1269c;
        }

        /* renamed from: e */
        public synchronized void m646e(C0767j c0767j) {
            c0767j.f1248f = true;
            if (this.f1271e == c0767j) {
                this.f1271e = null;
            }
            notifyAll();
        }
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$l */
    public interface InterfaceC0769l {
        /* renamed from: a */
        GL m647a(GL gl);
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$m */
    public static class C0770m extends Writer {

        /* renamed from: e */
        public StringBuilder f1272e = new StringBuilder();

        /* renamed from: a */
        public final void m648a() {
            if (this.f1272e.length() > 0) {
                this.f1272e.toString();
                StringBuilder sb = this.f1272e;
                sb.delete(0, sb.length());
            }
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            m648a();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            m648a();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i7, int i8) {
            for (int i9 = 0; i9 < i8; i9++) {
                char c7 = cArr[i7 + i9];
                if (c7 == '\n') {
                    m648a();
                } else {
                    this.f1272e.append(c7);
                }
            }
        }
    }

    /* renamed from: com.google.android.apps.muzei.render.GLTextureView$n */
    public class C0771n extends C0760c {
        public C0771n(boolean z6) {
            super(8, 8, 8, 0, z6 ? 16 : 0, 0);
        }
    }

    public GLTextureView(Context context) {
        super(context);
        this.f1219e = new WeakReference<>(this);
        setSurfaceTextureListener(this);
    }

    public void finalize() throws Throwable {
        try {
            C0767j c0767j = this.f1220f;
            if (c0767j != null) {
                c0767j.m638d();
            }
        } finally {
            super.finalize();
        }
    }

    /* renamed from: g */
    public final void m630g() {
        if (this.f1220f != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public int getDebugFlags() {
        return this.f1227m;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.f1229o;
    }

    public int getRenderMode() {
        int i7;
        C0767j c0767j = this.f1220f;
        Objects.requireNonNull(c0767j);
        synchronized (f1218p) {
            i7 = c0767j.f1260r;
        }
        return i7;
    }

    @Override // android.view.TextureView, android.view.View
    public void onAttachedToWindow() {
        int i7;
        int i8;
        int i9;
        super.onAttachedToWindow();
        if (this.f1222h && this.f1221g != null) {
            C0767j c0767j = this.f1220f;
            if (c0767j != null) {
                synchronized (f1218p) {
                    i7 = c0767j.f1260r;
                }
                C0767j c0767j2 = this.f1220f;
                i9 = c0767j2.f1258p;
                i8 = c0767j2.f1259q;
            } else {
                i7 = 1;
                i8 = 0;
                i9 = 0;
            }
            C0767j c0767j3 = new C0767j(this.f1219e);
            this.f1220f = c0767j3;
            if (i7 != 1) {
                c0767j3.m639e(i7);
            }
            if (i9 != 0 && i8 != 0) {
                C0767j c0767j4 = this.f1220f;
                c0767j4.f1258p = i9;
                c0767j4.f1259q = i8;
            }
            this.f1220f.start();
        }
        this.f1222h = false;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        C0767j c0767j = this.f1220f;
        if (c0767j != null) {
            c0767j.m638d();
        }
        this.f1222h = true;
        super.onDetachedFromWindow();
    }

    @Override // android.view.TextureView, android.view.View
    public void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        this.f1220f.m636b(i7, i8);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i7, int i8) {
        C0767j c0767j = this.f1220f;
        Objects.requireNonNull(c0767j);
        C0768k c0768k = f1218p;
        synchronized (c0768k) {
            c0767j.f1251i = true;
            c0767j.f1256n = false;
            c0768k.notifyAll();
            while (c0767j.f1253k && !c0767j.f1256n && !c0767j.f1248f) {
                try {
                    f1218p.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        C0767j c0767j = this.f1220f;
        Objects.requireNonNull(c0767j);
        C0768k c0768k = f1218p;
        synchronized (c0768k) {
            c0767j.f1251i = false;
            c0768k.notifyAll();
            while (!c0767j.f1253k && !c0767j.f1248f) {
                try {
                    f1218p.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i7, int i8) {
        this.f1220f.m636b(i7, i8);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        C0767j c0767j = this.f1220f;
        Objects.requireNonNull(c0767j);
        C0768k c0768k = f1218p;
        synchronized (c0768k) {
            c0767j.f1261s = true;
            c0768k.notifyAll();
        }
    }

    public void setDebugFlags(int i7) {
        this.f1227m = i7;
    }

    public void setEGLConfigChooser(InterfaceC0763f interfaceC0763f) {
        m630g();
        this.f1223i = interfaceC0763f;
    }

    public void setEGLContextClientVersion(int i7) {
        m630g();
        this.f1228n = i7;
    }

    public void setEGLContextFactory(InterfaceC0764g interfaceC0764g) {
        m630g();
        this.f1224j = interfaceC0764g;
    }

    public void setEGLWindowSurfaceFactory(InterfaceC0765h interfaceC0765h) {
        m630g();
        this.f1225k = interfaceC0765h;
    }

    public void setGLWrapper(InterfaceC0769l interfaceC0769l) {
        this.f1226l = interfaceC0769l;
    }

    public void setPreserveEGLContextOnPause(boolean z6) {
        this.f1229o = z6;
    }

    public void setRenderMode(int i7) {
        this.f1220f.m639e(i7);
    }

    public void setRenderer(GLSurfaceView.Renderer renderer) {
        m630g();
        if (this.f1223i == null) {
            this.f1223i = new C0771n(true);
        }
        if (this.f1224j == null) {
            this.f1224j = new C0761d(null);
        }
        if (this.f1225k == null) {
            this.f1225k = new C0762e(null);
        }
        this.f1221g = renderer;
        C0767j c0767j = new C0767j(this.f1219e);
        this.f1220f = c0767j;
        c0767j.start();
    }

    public void setEGLConfigChooser(boolean z6) {
        setEGLConfigChooser(new C0771n(z6));
    }

    public GLTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1219e = new WeakReference<>(this);
        setSurfaceTextureListener(this);
    }
}
