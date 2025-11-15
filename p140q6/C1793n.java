package p140q6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Objects;
import p140q6.C1801v;
import p159t3.AbstractC1904c;
import p159t3.AbstractC1907f;
import p159t3.InterfaceC1906e;

/* compiled from: HttpServerImpl.java */
/* renamed from: q6.n */
/* loaded from: classes.dex */
public class C1793n extends AbstractC1907f {

    /* renamed from: a */
    public C1801v f5119a;

    public C1793n(InetSocketAddress inetSocketAddress, int i7) {
        this.f5119a = new C1801v(this, "http", inetSocketAddress, i7);
    }

    @Override // p159t3.AbstractC1907f
    /* renamed from: b */
    public AbstractC1904c mo1995b(String str, InterfaceC1906e interfaceC1906e) {
        C1790k c1790k;
        C1801v c1801v = this.f5119a;
        synchronized (c1801v) {
            c1790k = new C1790k(c1801v.f5176a, str, interfaceC1906e, c1801v);
            C1783d c1783d = c1801v.f5179d;
            synchronized (c1783d) {
                c1783d.f5075a.add(c1790k);
            }
            c1801v.f5194s.config("context created: " + str);
        }
        return c1790k;
    }

    @Override // p159t3.AbstractC1907f
    /* renamed from: c */
    public void mo1996c() {
        C1801v c1801v = this.f5119a;
        if (!c1801v.f5189n || c1801v.f5190o || c1801v.f5187l) {
            throw new IllegalStateException("server in wrong state");
        }
        if (c1801v.f5178c == null) {
            c1801v.f5178c = new C1801v.b(null);
        }
        Thread thread = new Thread(c1801v.f5195t);
        c1801v.f5190o = true;
        thread.start();
    }

    @Override // p159t3.AbstractC1907f
    /* renamed from: d */
    public void mo1997d(int i7) throws InterruptedException {
        C1801v c1801v = this.f5119a;
        Objects.requireNonNull(c1801v);
        if (i7 < 0) {
            throw new IllegalArgumentException("negative delay parameter");
        }
        c1801v.f5188m = true;
        try {
            c1801v.f5180e.close();
        } catch (IOException unused) {
        }
        c1801v.f5181f.wakeup();
        long jCurrentTimeMillis = System.currentTimeMillis() + (i7 * 1000);
        while (System.currentTimeMillis() < jCurrentTimeMillis) {
            Thread.yield();
            try {
                Thread.sleep(200L);
            } catch (InterruptedException unused2) {
            }
            if (c1801v.f5187l) {
                break;
            }
        }
        c1801v.f5187l = true;
        c1801v.f5181f.wakeup();
        synchronized (c1801v.f5184i) {
            Iterator<C1789j> it = c1801v.f5184i.iterator();
            while (it.hasNext()) {
                it.next().m1983a();
            }
        }
        c1801v.f5184i.clear();
        c1801v.f5183h.clear();
        c1801v.f5193r.cancel();
    }
}
