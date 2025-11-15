package p068i0;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import p108n0.C1512c;
import p116o0.AbstractC1575d;
import p126p0.InterfaceC1733b;
import p141r.C1813h;
import p141r.C1814i;
import p141r.ComponentCallbacks2C1808c;
import p155t.InterfaceC1891a;
import p162u.InterfaceC1964h;
import p162u.InterfaceC1969m;
import p183x.AbstractC2047h;
import p190y.InterfaceC2086d;

/* compiled from: GifFrameLoader.java */
/* renamed from: i0.f */
/* loaded from: classes.dex */
public class C1140f {

    /* renamed from: a */
    public final InterfaceC1891a f2491a;

    /* renamed from: b */
    public final Handler f2492b;

    /* renamed from: c */
    public final List<b> f2493c;

    /* renamed from: d */
    public final C1814i f2494d;

    /* renamed from: e */
    public final InterfaceC2086d f2495e;

    /* renamed from: f */
    public boolean f2496f;

    /* renamed from: g */
    public boolean f2497g;

    /* renamed from: h */
    public C1813h<Bitmap> f2498h;

    /* renamed from: i */
    public a f2499i;

    /* renamed from: j */
    public boolean f2500j;

    /* renamed from: k */
    public a f2501k;

    /* renamed from: l */
    public Bitmap f2502l;

    /* renamed from: m */
    public InterfaceC1969m<Bitmap> f2503m;

    /* compiled from: GifFrameLoader.java */
    /* renamed from: i0.f$a */
    public static class a extends AbstractC1575d<Bitmap> {

        /* renamed from: d */
        public final Handler f2504d;

        /* renamed from: e */
        public final int f2505e;

        /* renamed from: f */
        public final long f2506f;

        /* renamed from: g */
        public Bitmap f2507g;

        public a(Handler handler, int i7, long j7) {
            this.f2504d = handler;
            this.f2505e = i7;
            this.f2506f = j7;
        }

        @Override // p116o0.InterfaceC1577f
        /* renamed from: e */
        public void mo1308e(Object obj, InterfaceC1733b interfaceC1733b) {
            this.f2507g = (Bitmap) obj;
            this.f2504d.sendMessageAtTime(this.f2504d.obtainMessage(1, this), this.f2506f);
        }
    }

    /* compiled from: GifFrameLoader.java */
    /* renamed from: i0.f$b */
    public interface b {
        /* renamed from: a */
        void mo1301a();
    }

    /* compiled from: GifFrameLoader.java */
    /* renamed from: i0.f$c */
    public class c implements Handler.Callback {
        public c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i7 = message.what;
            if (i7 != 1) {
                if (i7 == 2) {
                    C1140f.this.f2494d.m2041k((a) message.obj);
                }
                return false;
            }
            a aVar = (a) message.obj;
            C1140f c1140f = C1140f.this;
            if (c1140f.f2500j) {
                c1140f.f2492b.obtainMessage(2, aVar).sendToTarget();
            } else {
                if (aVar.f2507g != null) {
                    Bitmap bitmap = c1140f.f2502l;
                    if (bitmap != null) {
                        c1140f.f2495e.mo2520e(bitmap);
                        c1140f.f2502l = null;
                    }
                    a aVar2 = c1140f.f2499i;
                    c1140f.f2499i = aVar;
                    int size = c1140f.f2493c.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        c1140f.f2493c.get(size).mo1301a();
                    }
                    if (aVar2 != null) {
                        c1140f.f2492b.obtainMessage(2, aVar2).sendToTarget();
                    }
                }
                c1140f.f2497g = false;
                c1140f.m1306b();
            }
            return true;
        }
    }

    /* compiled from: GifFrameLoader.java */
    /* renamed from: i0.f$d */
    public static class d implements InterfaceC1964h {

        /* renamed from: b */
        public final UUID f2509b = UUID.randomUUID();

        @Override // p162u.InterfaceC1964h
        /* renamed from: b */
        public void mo130b(MessageDigest messageDigest) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override // p162u.InterfaceC1964h
        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return ((d) obj).f2509b.equals(this.f2509b);
            }
            return false;
        }

        @Override // p162u.InterfaceC1964h
        public int hashCode() {
            return this.f2509b.hashCode();
        }
    }

    public C1140f(ComponentCallbacks2C1808c componentCallbacks2C1808c, InterfaceC1891a interfaceC1891a, int i7, int i8, InterfaceC1969m<Bitmap> interfaceC1969m, Bitmap bitmap) {
        InterfaceC2086d interfaceC2086d = componentCallbacks2C1808c.f5213e;
        C1814i c1814iM2023e = ComponentCallbacks2C1808c.m2023e(componentCallbacks2C1808c.f5215g.getBaseContext());
        C1814i c1814iM2023e2 = ComponentCallbacks2C1808c.m2023e(componentCallbacks2C1808c.f5215g.getBaseContext());
        Objects.requireNonNull(c1814iM2023e2);
        C1813h<Bitmap> c1813h = new C1813h<>(c1814iM2023e2.f5267a, c1814iM2023e2, Bitmap.class);
        c1813h.m2035b(C1814i.f5266j);
        c1813h.m2035b(new C1512c().m1689j(AbstractC2047h.f6043a).m1684X(true).m1678J(i7, i8));
        this.f2493c = new ArrayList();
        this.f2496f = false;
        this.f2497g = false;
        this.f2494d = c1814iM2023e;
        Handler handler = new Handler(Looper.getMainLooper(), new c());
        this.f2495e = interfaceC2086d;
        this.f2492b = handler;
        this.f2498h = c1813h;
        this.f2491a = interfaceC1891a;
        m1307c(interfaceC1969m, bitmap);
    }

    /* renamed from: a */
    public Bitmap m1305a() {
        a aVar = this.f2499i;
        return aVar != null ? aVar.f2507g : this.f2502l;
    }

    /* renamed from: b */
    public final void m1306b() {
        if (!this.f2496f || this.f2497g) {
            return;
        }
        this.f2497g = true;
        long jUptimeMillis = SystemClock.uptimeMillis() + this.f2491a.mo2184e();
        this.f2491a.mo2182c();
        this.f2501k = new a(this.f2492b, this.f2491a.mo2180a(), jUptimeMillis);
        C1813h<Bitmap> c1813hM2037g = this.f2498h.clone();
        c1813hM2037g.m2035b(new C1512c().m1683U(new d()));
        c1813hM2037g.f5262k = this.f2491a;
        c1813hM2037g.f5263l = true;
        c1813hM2037g.m2039k(this.f2501k);
    }

    /* renamed from: c */
    public void m1307c(InterfaceC1969m<Bitmap> interfaceC1969m, Bitmap bitmap) {
        Objects.requireNonNull(interfaceC1969m, "Argument must not be null");
        this.f2503m = interfaceC1969m;
        Objects.requireNonNull(bitmap, "Argument must not be null");
        this.f2502l = bitmap;
        C1813h<Bitmap> c1813h = this.f2498h;
        c1813h.m2035b(new C1512c().m1685Y(interfaceC1969m));
        this.f2498h = c1813h;
    }
}
