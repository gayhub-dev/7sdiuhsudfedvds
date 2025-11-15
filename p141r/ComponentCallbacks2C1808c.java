package p141r;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.support.v7.widget.ActivityChooserView;
import android.util.Log;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;
import java.util.regex.Pattern;
import p001a0.C0003a;
import p001a0.C0004b;
import p010b0.C0415a;
import p010b0.C0416b;
import p010b0.C0417c;
import p010b0.C0418d;
import p010b0.C0419e;
import p010b0.C0420f;
import p010b0.C0424j;
import p010b0.C0431q;
import p010b0.C0432r;
import p010b0.C0433s;
import p010b0.C0434t;
import p010b0.C0435u;
import p018c0.C0499a;
import p018c0.C0500b;
import p018c0.C0501c;
import p018c0.C0502d;
import p018c0.C0503e;
import p035e.C0892e;
import p036e0.C0902a;
import p036e0.C0904c;
import p036e0.C0907f;
import p036e0.C0910i;
import p036e0.C0912k;
import p036e0.C0917p;
import p036e0.C0919r;
import p043f.C0984a;
import p044f0.C0990a;
import p051g.C1041k;
import p060h0.C1079a;
import p068i0.C1135a;
import p068i0.C1137c;
import p068i0.C1141g;
import p075j.C1180h;
import p076j0.C1181a;
import p084k0.C1219e;
import p084k0.C1223i;
import p084k0.InterfaceC1217c;
import p092l0.C1413d;
import p092l0.InterfaceC1412c;
import p108n0.C1512c;
import p142r0.C1820e;
import p142r0.C1823h;
import p155t.InterfaceC1891a;
import p169v.C1993i;
import p183x.C2048i;
import p190y.C2087e;
import p190y.C2090h;
import p190y.C2091i;
import p190y.InterfaceC2084b;
import p190y.InterfaceC2086d;
import p197z.C2140g;
import p197z.C2141h;
import p197z.C2143j;
import p197z.InterfaceC2142i;

/* compiled from: Glide.java */
@TargetApi(14)
/* renamed from: r.c */
/* loaded from: classes.dex */
public class ComponentCallbacks2C1808c implements ComponentCallbacks2 {

    /* renamed from: m */
    public static volatile ComponentCallbacks2C1808c f5211m;

    /* renamed from: n */
    public static volatile boolean f5212n;

    /* renamed from: e */
    public final InterfaceC2086d f5213e;

    /* renamed from: f */
    public final InterfaceC2142i f5214f;

    /* renamed from: g */
    public final C1810e f5215g;

    /* renamed from: h */
    public final C1812g f5216h;

    /* renamed from: i */
    public final InterfaceC2084b f5217i;

    /* renamed from: j */
    public final C1223i f5218j;

    /* renamed from: k */
    public final InterfaceC1217c f5219k;

    /* renamed from: l */
    public final List<C1814i> f5220l = new ArrayList();

    @TargetApi(14)
    public ComponentCallbacks2C1808c(Context context, C2048i c2048i, InterfaceC2142i interfaceC2142i, InterfaceC2086d interfaceC2086d, InterfaceC2084b interfaceC2084b, C1223i c1223i, InterfaceC1217c interfaceC1217c, int i7, C1512c c1512c, Map<Class<?>, AbstractC1815j<?, ?>> map) {
        this.f5213e = interfaceC2086d;
        this.f5217i = interfaceC2084b;
        this.f5214f = interfaceC2142i;
        this.f5218j = c1223i;
        this.f5219k = interfaceC1217c;
        new Handler(Looper.getMainLooper());
        Resources resources = context.getResources();
        C1812g c1812g = new C1812g();
        this.f5216h = c1812g;
        C0910i c0910i = new C0910i();
        C1180h c1180h = c1812g.f5252g;
        synchronized (c1180h) {
            c1180h.f2594a.add(c0910i);
        }
        C0912k c0912k = new C0912k(c1812g.m2028c(), resources.getDisplayMetrics(), interfaceC2086d, interfaceC2084b);
        C1135a c1135a = new C1135a(context, c1812g.m2028c(), interfaceC2086d, interfaceC2084b);
        c1812g.m2032g(ByteBuffer.class, new C0984a(3));
        c1812g.m2032g(InputStream.class, new C0892e(interfaceC2084b));
        c1812g.m2027b(ByteBuffer.class, Bitmap.class, new C0907f(c0912k));
        c1812g.m2027b(InputStream.class, Bitmap.class, new C0917p(c0912k, interfaceC2084b));
        c1812g.m2027b(ParcelFileDescriptor.class, Bitmap.class, new C0919r(interfaceC2086d));
        c1812g.m2033h(Bitmap.class, new C0904c());
        c1812g.m2027b(ByteBuffer.class, BitmapDrawable.class, new C0902a(resources, interfaceC2086d, new C0907f(c0912k)));
        c1812g.m2027b(InputStream.class, BitmapDrawable.class, new C0902a(resources, interfaceC2086d, new C0917p(c0912k, interfaceC2084b)));
        c1812g.m2027b(ParcelFileDescriptor.class, BitmapDrawable.class, new C0902a(resources, interfaceC2086d, new C0919r(interfaceC2086d)));
        c1812g.m2033h(BitmapDrawable.class, new C1041k(interfaceC2086d, new C0904c()));
        c1812g.m2030e(InputStream.class, C1137c.class, new C1141g(c1812g.m2028c(), c1135a, interfaceC2084b));
        c1812g.m2030e(ByteBuffer.class, C1137c.class, c1135a);
        c1812g.m2033h(C1137c.class, new C0984a(4));
        c1812g.m2026a(InterfaceC1891a.class, InterfaceC1891a.class, new C0433s.a());
        c1812g.m2027b(InterfaceC1891a.class, Bitmap.class, new C0907f(interfaceC2086d));
        c1812g.m2034i(new C0990a.a());
        c1812g.m2026a(File.class, ByteBuffer.class, new C0417c.b());
        c1812g.m2026a(File.class, InputStream.class, new C0419e.e());
        c1812g.m2027b(File.class, File.class, new C1079a());
        c1812g.m2026a(File.class, ParcelFileDescriptor.class, new C0419e.b());
        c1812g.m2026a(File.class, File.class, new C0433s.a());
        c1812g.m2034i(new C1993i.a(interfaceC2084b));
        Class cls = Integer.TYPE;
        c1812g.m2026a(cls, InputStream.class, new C0431q.b(resources));
        c1812g.m2026a(cls, ParcelFileDescriptor.class, new C0431q.a(resources));
        c1812g.m2026a(Integer.class, InputStream.class, new C0431q.b(resources));
        c1812g.m2026a(Integer.class, ParcelFileDescriptor.class, new C0431q.a(resources));
        c1812g.m2026a(String.class, InputStream.class, new C0418d.c());
        c1812g.m2026a(String.class, InputStream.class, new C0432r.b());
        c1812g.m2026a(String.class, ParcelFileDescriptor.class, new C0432r.a());
        c1812g.m2026a(Uri.class, InputStream.class, new C0500b.a());
        c1812g.m2026a(Uri.class, InputStream.class, new C0415a.c(context.getAssets()));
        c1812g.m2026a(Uri.class, ParcelFileDescriptor.class, new C0415a.b(context.getAssets()));
        c1812g.m2026a(Uri.class, InputStream.class, new C0501c.a(context));
        c1812g.m2026a(Uri.class, InputStream.class, new C0502d.a(context));
        c1812g.m2026a(Uri.class, InputStream.class, new C0434t.c(context.getContentResolver()));
        c1812g.m2026a(Uri.class, ParcelFileDescriptor.class, new C0434t.a(context.getContentResolver()));
        c1812g.m2026a(Uri.class, InputStream.class, new C0435u.a());
        c1812g.m2026a(URL.class, InputStream.class, new C0503e.a());
        c1812g.m2026a(Uri.class, File.class, new C0424j.a(context));
        c1812g.m2026a(C0420f.class, InputStream.class, new C0499a.a());
        c1812g.m2026a(byte[].class, ByteBuffer.class, new C0416b.a());
        c1812g.m2026a(byte[].class, InputStream.class, new C0416b.d());
        c1812g.m2031f(Bitmap.class, BitmapDrawable.class, new C1041k(resources, interfaceC2086d));
        c1812g.m2031f(Bitmap.class, byte[].class, new C1181a());
        c1812g.m2031f(C1137c.class, byte[].class, new C0984a(5));
        this.f5215g = new C1810e(context, c1812g, new C0984a(8), c1512c, map, c2048i, i7);
    }

    /* renamed from: a */
    public static void m2021a(Context context) throws PackageManager.NameNotFoundException {
        AbstractC1806a abstractC1806a;
        if (f5212n) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        f5212n = true;
        Context applicationContext = context.getApplicationContext();
        File[] fileArrListFiles = null;
        try {
            abstractC1806a = (AbstractC1806a) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").newInstance();
        } catch (ClassNotFoundException unused) {
            Log.isLoggable("Glide", 5);
            abstractC1806a = null;
        } catch (IllegalAccessException e7) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e7);
        } catch (InstantiationException e8) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e8);
        }
        Collections.emptyList();
        Log.isLoggable("ManifestParser", 3);
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                Log.isLoggable("ManifestParser", 3);
            } else {
                if (Log.isLoggable("ManifestParser", 2)) {
                    Objects.toString(applicationInfo.metaData);
                }
                for (String str : applicationInfo.metaData.keySet()) {
                    if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                        arrayList.add(C1413d.m1598a(str));
                        Log.isLoggable("ManifestParser", 3);
                    }
                }
                Log.isLoggable("ManifestParser", 3);
            }
            if (abstractC1806a != null && !abstractC1806a.m2020c().isEmpty()) {
                Set<Class<?>> setM2020c = abstractC1806a.m2020c();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    InterfaceC1412c interfaceC1412c = (InterfaceC1412c) it.next();
                    if (setM2020c.contains(interfaceC1412c.getClass())) {
                        if (Log.isLoggable("Glide", 3)) {
                            interfaceC1412c.toString();
                        }
                        it.remove();
                    }
                }
            }
            if (Log.isLoggable("Glide", 3)) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ((InterfaceC1412c) it2.next()).getClass().toString();
                }
            }
            C1809d c1809d = new C1809d();
            c1809d.f5233m = null;
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                ((InterfaceC1412c) it3.next()).mo1597b(applicationContext, c1809d);
            }
            C0004b.b bVar = C0004b.b.f14e;
            if (c1809d.f5226f == null) {
                int i7 = C0004b.f7g;
                StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    try {
                        fileArrListFiles = new File("/sys/devices/system/cpu/").listFiles(new C0003a(Pattern.compile("cpu[0-9]+")));
                    } finally {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    }
                } catch (Throwable unused2) {
                    Log.isLoggable("GlideExecutor", 6);
                }
                c1809d.f5226f = new C0004b(Math.min(4, Math.max(Math.max(1, Runtime.getRuntime().availableProcessors()), fileArrListFiles != null ? fileArrListFiles.length : 0)), "source", bVar, false, false);
            }
            if (c1809d.f5227g == null) {
                c1809d.f5227g = new C0004b(1, "disk-cache", bVar, true, false);
            }
            if (c1809d.f5229i == null) {
                c1809d.f5229i = new C2143j(new C2143j.a(applicationContext));
            }
            if (c1809d.f5230j == null) {
                c1809d.f5230j = new C1219e();
            }
            if (c1809d.f5223c == null) {
                int i8 = c1809d.f5229i.f6296a;
                if (i8 > 0) {
                    c1809d.f5223c = new C2091i(i8);
                } else {
                    c1809d.f5223c = new C2087e();
                }
            }
            if (c1809d.f5224d == null) {
                c1809d.f5224d = new C2090h(c1809d.f5229i.f6299d);
            }
            if (c1809d.f5225e == null) {
                c1809d.f5225e = new C2141h(c1809d.f5229i.f6297b);
            }
            if (c1809d.f5228h == null) {
                c1809d.f5228h = new C2140g(applicationContext);
            }
            if (c1809d.f5222b == null) {
                c1809d.f5222b = new C2048i(c1809d.f5225e, c1809d.f5228h, c1809d.f5227g, c1809d.f5226f, new C0004b(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, C0004b.f6f, "source-unlimited", bVar, false, false, new SynchronousQueue()));
            }
            C1223i c1223i = new C1223i(c1809d.f5233m);
            C2048i c2048i = c1809d.f5222b;
            InterfaceC2142i interfaceC2142i = c1809d.f5225e;
            InterfaceC2086d interfaceC2086d = c1809d.f5223c;
            InterfaceC2084b interfaceC2084b = c1809d.f5224d;
            InterfaceC1217c interfaceC1217c = c1809d.f5230j;
            int i9 = c1809d.f5231k;
            C1512c c1512c = c1809d.f5232l;
            c1512c.f4361x = true;
            ComponentCallbacks2C1808c componentCallbacks2C1808c = new ComponentCallbacks2C1808c(applicationContext, c2048i, interfaceC2142i, interfaceC2086d, interfaceC2084b, c1223i, interfaceC1217c, i9, c1512c, c1809d.f5221a);
            Iterator it4 = arrayList.iterator();
            while (it4.hasNext()) {
                ((InterfaceC1412c) it4.next()).mo1596a(applicationContext, componentCallbacks2C1808c, componentCallbacks2C1808c.f5216h);
            }
            context.getApplicationContext().registerComponentCallbacks(componentCallbacks2C1808c);
            f5211m = componentCallbacks2C1808c;
            f5212n = false;
        } catch (PackageManager.NameNotFoundException e9) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e9);
        }
    }

    /* renamed from: c */
    public static ComponentCallbacks2C1808c m2022c(Context context) {
        if (f5211m == null) {
            synchronized (ComponentCallbacks2C1808c.class) {
                if (f5211m == null) {
                    m2021a(context);
                }
            }
        }
        return f5211m;
    }

    /* renamed from: e */
    public static C1814i m2023e(Context context) {
        Objects.requireNonNull(context, "You cannot start a load on a not yet attached View or a  Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return m2022c(context).f5218j.m1449a(context);
    }

    /* renamed from: b */
    public void m2024b() {
        C1823h.m2057a();
        ((C1820e) this.f5214f).m2054e(0);
        this.f5213e.mo2517b();
        this.f5217i.mo2509b();
    }

    /* renamed from: d */
    public void m2025d(int i7) {
        int i8;
        C1823h.m2057a();
        C2141h c2141h = (C2141h) this.f5214f;
        Objects.requireNonNull(c2141h);
        if (i7 >= 40) {
            c2141h.m2054e(0);
        } else if (i7 >= 20) {
            synchronized (c2141h) {
                i8 = c2141h.f5295c;
            }
            c2141h.m2054e(i8 / 2);
        }
        this.f5213e.mo2516a(i7);
        this.f5217i.mo2508a(i7);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        m2024b();
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i7) {
        m2025d(i7);
    }
}
