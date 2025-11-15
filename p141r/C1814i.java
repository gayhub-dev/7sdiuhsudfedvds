package p141r;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import p068i0.C1137c;
import p084k0.C1218d;
import p084k0.C1219e;
import p084k0.C1222h;
import p084k0.C1225k;
import p084k0.C1226l;
import p084k0.InterfaceC1216b;
import p084k0.InterfaceC1217c;
import p084k0.InterfaceC1220f;
import p084k0.InterfaceC1221g;
import p084k0.InterfaceC1224j;
import p108n0.C1512c;
import p108n0.InterfaceC1510a;
import p116o0.InterfaceC1577f;
import p142r0.C1823h;
import p183x.AbstractC2047h;

/* compiled from: RequestManager.java */
/* renamed from: r.i */
/* loaded from: classes.dex */
public class C1814i implements InterfaceC1221g {

    /* renamed from: j */
    public static final C1512c f5266j;

    /* renamed from: a */
    public final ComponentCallbacks2C1808c f5267a;

    /* renamed from: b */
    public final InterfaceC1220f f5268b;

    /* renamed from: c */
    public final C1225k f5269c;

    /* renamed from: d */
    public final InterfaceC1224j f5270d;

    /* renamed from: e */
    public final C1226l f5271e;

    /* renamed from: f */
    public final Runnable f5272f;

    /* renamed from: g */
    public final Handler f5273g;

    /* renamed from: h */
    public final InterfaceC1216b f5274h;

    /* renamed from: i */
    @NonNull
    public C1512c f5275i;

    /* compiled from: RequestManager.java */
    /* renamed from: r.i$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C1814i c1814i = C1814i.this;
            c1814i.f5268b.mo941a(c1814i);
        }
    }

    /* compiled from: RequestManager.java */
    /* renamed from: r.i$b */
    public class b implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ InterfaceC1577f f5277e;

        public b(InterfaceC1577f interfaceC1577f) {
            this.f5277e = interfaceC1577f;
        }

        @Override // java.lang.Runnable
        public void run() {
            C1814i.this.m2041k(this.f5277e);
        }
    }

    /* compiled from: RequestManager.java */
    /* renamed from: r.i$c */
    public static class c implements InterfaceC1216b.a {

        /* renamed from: a */
        public final C1225k f5279a;

        public c(C1225k c1225k) {
            this.f5279a = c1225k;
        }
    }

    static {
        C1512c c1512cM1688g = new C1512c().m1688g(Bitmap.class);
        c1512cM1688g.f4361x = true;
        f5266j = c1512cM1688g;
        new C1512c().m1688g(C1137c.class).f4361x = true;
        new C1512c().m1689j(AbstractC2047h.f6044b).m1680N(EnumC1811f.LOW).m1684X(true);
    }

    public C1814i(ComponentCallbacks2C1808c componentCallbacks2C1808c, InterfaceC1220f interfaceC1220f, InterfaceC1224j interfaceC1224j) {
        C1225k c1225k = new C1225k();
        InterfaceC1217c interfaceC1217c = componentCallbacks2C1808c.f5219k;
        this.f5271e = new C1226l();
        a aVar = new a();
        this.f5272f = aVar;
        Handler handler = new Handler(Looper.getMainLooper());
        this.f5273g = handler;
        this.f5267a = componentCallbacks2C1808c;
        this.f5268b = interfaceC1220f;
        this.f5270d = interfaceC1224j;
        this.f5269c = c1225k;
        Context baseContext = componentCallbacks2C1808c.f5215g.getBaseContext();
        c cVar = new c(c1225k);
        Objects.requireNonNull((C1219e) interfaceC1217c);
        InterfaceC1216b c1218d = ContextCompat.checkSelfPermission(baseContext, "android.permission.ACCESS_NETWORK_STATE") == 0 ? new C1218d(baseContext, cVar) : new C1222h();
        this.f5274h = c1218d;
        if (C1823h.m2063g()) {
            handler.post(aVar);
        } else {
            interfaceC1220f.mo941a(this);
        }
        interfaceC1220f.mo941a(c1218d);
        m2043m(componentCallbacks2C1808c.f5215g.f5237c);
        synchronized (componentCallbacks2C1808c.f5220l) {
            if (componentCallbacks2C1808c.f5220l.contains(this)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            componentCallbacks2C1808c.f5220l.add(this);
        }
    }

    @Override // p084k0.InterfaceC1221g
    /* renamed from: a */
    public void mo1446a() {
        C1823h.m2057a();
        C1225k c1225k = this.f5269c;
        c1225k.f2755c = false;
        Iterator it = ((ArrayList) C1823h.m2061e(c1225k.f2753a)).iterator();
        while (it.hasNext()) {
            InterfaceC1510a interfaceC1510a = (InterfaceC1510a) it.next();
            if (!interfaceC1510a.mo1673d() && !interfaceC1510a.isCancelled() && !interfaceC1510a.isRunning()) {
                interfaceC1510a.mo1672c();
            }
        }
        c1225k.f2754b.clear();
        this.f5271e.mo1446a();
    }

    @Override // p084k0.InterfaceC1221g
    /* renamed from: j */
    public void mo1447j() {
        this.f5271e.mo1447j();
        C1226l c1226l = this.f5271e;
        Objects.requireNonNull(c1226l);
        Iterator it = new ArrayList(c1226l.f2756a).iterator();
        while (it.hasNext()) {
            m2041k((InterfaceC1577f) it.next());
        }
        this.f5271e.f2756a.clear();
        C1225k c1225k = this.f5269c;
        Iterator it2 = ((ArrayList) C1823h.m2061e(c1225k.f2753a)).iterator();
        while (it2.hasNext()) {
            c1225k.m1452a((InterfaceC1510a) it2.next());
        }
        c1225k.f2754b.clear();
        this.f5268b.mo943c(this);
        this.f5268b.mo943c(this.f5274h);
        this.f5273g.removeCallbacks(this.f5272f);
        ComponentCallbacks2C1808c componentCallbacks2C1808c = this.f5267a;
        synchronized (componentCallbacks2C1808c.f5220l) {
            if (!componentCallbacks2C1808c.f5220l.contains(this)) {
                throw new IllegalStateException("Cannot register not yet registered manager");
            }
            componentCallbacks2C1808c.f5220l.remove(this);
        }
    }

    /* renamed from: k */
    public void m2041k(@Nullable InterfaceC1577f<?> interfaceC1577f) {
        if (interfaceC1577f == null) {
            return;
        }
        if (!C1823h.m2064h()) {
            this.f5273g.post(new b(interfaceC1577f));
            return;
        }
        if (m2044n(interfaceC1577f)) {
            return;
        }
        ComponentCallbacks2C1808c componentCallbacks2C1808c = this.f5267a;
        synchronized (componentCallbacks2C1808c.f5220l) {
            Iterator<C1814i> it = componentCallbacks2C1808c.f5220l.iterator();
            while (it.hasNext()) {
                if (it.next().m2044n(interfaceC1577f)) {
                    return;
                }
            }
            throw new IllegalStateException("Failed to remove target from managers");
        }
    }

    /* renamed from: l */
    public C1813h<Drawable> m2042l(@Nullable Object obj) {
        C1813h<Drawable> c1813h = new C1813h<>(this.f5267a, this, Drawable.class);
        c1813h.f5262k = obj;
        c1813h.f5263l = true;
        return c1813h;
    }

    /* renamed from: m */
    public void m2043m(@NonNull C1512c c1512c) {
        C1512c c1512cClone = c1512c.clone();
        if (c1512cClone.f4361x && !c1512cClone.f4363z) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        c1512cClone.f4363z = true;
        c1512cClone.f4361x = true;
        this.f5275i = c1512cClone;
    }

    /* renamed from: n */
    public boolean m2044n(InterfaceC1577f<?> interfaceC1577f) {
        InterfaceC1510a interfaceC1510aMo1819h = interfaceC1577f.mo1819h();
        if (interfaceC1510aMo1819h == null) {
            return true;
        }
        if (!this.f5269c.m1452a(interfaceC1510aMo1819h)) {
            return false;
        }
        this.f5271e.f2756a.remove(interfaceC1577f);
        interfaceC1577f.mo1817f(null);
        return true;
    }

    @Override // p084k0.InterfaceC1221g
    public void onStop() {
        C1823h.m2057a();
        C1225k c1225k = this.f5269c;
        c1225k.f2755c = true;
        Iterator it = ((ArrayList) C1823h.m2061e(c1225k.f2753a)).iterator();
        while (it.hasNext()) {
            InterfaceC1510a interfaceC1510a = (InterfaceC1510a) it.next();
            if (interfaceC1510a.isRunning()) {
                interfaceC1510a.pause();
                c1225k.f2754b.add(interfaceC1510a);
            }
        }
        this.f5271e.onStop();
    }

    public String toString() {
        return super.toString() + "{tracker=" + this.f5269c + ", treeNode=" + this.f5270d + "}";
    }
}
