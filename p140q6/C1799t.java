package p140q6;

import java.io.IOException;
import java.nio.channels.Selector;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.LinkedList;
import java.util.ListIterator;

/* compiled from: SelectorCache.java */
/* renamed from: q6.t */
/* loaded from: classes.dex */
public class C1799t {

    /* renamed from: b */
    public static C1799t f5158b;

    /* renamed from: a */
    public LinkedList<c> f5159a = new LinkedList<>();

    /* compiled from: SelectorCache.java */
    /* renamed from: q6.t$a */
    public class a implements PrivilegedAction<b> {
        public a() {
        }

        @Override // java.security.PrivilegedAction
        public b run() {
            b bVar = C1799t.this.new b();
            bVar.setDaemon(true);
            return bVar;
        }
    }

    /* compiled from: SelectorCache.java */
    /* renamed from: q6.t$b */
    public class b extends Thread {
        public b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            long j7 = C1800u.f5168e * 1000;
            while (true) {
                try {
                    Thread.sleep(j7);
                } catch (Exception unused) {
                }
                synchronized (C1799t.this.f5159a) {
                    ListIterator<c> listIterator = C1799t.this.f5159a.listIterator();
                    while (listIterator.hasNext()) {
                        c next = listIterator.next();
                        if (next.f5163b) {
                            try {
                                next.f5162a.close();
                            } catch (IOException unused2) {
                            }
                            listIterator.remove();
                        } else {
                            next.f5163b = true;
                        }
                    }
                }
            }
        }
    }

    /* compiled from: SelectorCache.java */
    /* renamed from: q6.t$c */
    public static class c {

        /* renamed from: a */
        public Selector f5162a;

        /* renamed from: b */
        public boolean f5163b = false;

        public c(Selector selector, a aVar) {
            this.f5162a = selector;
        }
    }

    public C1799t() {
        ((b) AccessController.doPrivileged(new a())).start();
    }

    /* renamed from: c */
    public static C1799t m2003c() {
        synchronized (C1799t.class) {
            if (f5158b == null) {
                f5158b = new C1799t();
            }
        }
        return f5158b;
    }

    /* renamed from: a */
    public synchronized void m2004a(Selector selector) {
        this.f5159a.add(new c(selector, null));
    }

    /* renamed from: b */
    public synchronized Selector m2005b() {
        return this.f5159a.size() > 0 ? this.f5159a.remove().f5162a : Selector.open();
    }
}
