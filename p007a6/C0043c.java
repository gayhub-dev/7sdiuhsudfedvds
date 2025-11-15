package p007a6;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import p168u5.InterfaceC1983d;
import p168u5.InterfaceC1984e;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: ShutdownThread.java */
/* renamed from: a6.c */
/* loaded from: classes.dex */
public class C0043c extends Thread {

    /* renamed from: f */
    public static final InterfaceC2016c f55f;

    /* renamed from: g */
    public static final C0043c f56g;

    /* renamed from: e */
    public final List<InterfaceC1984e> f57e = new CopyOnWriteArrayList();

    static {
        Properties properties = C2015b.f5863a;
        f55f = C2015b.m2349a(C0043c.class.getName());
        f56g = new C0043c();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        for (InterfaceC1984e interfaceC1984e : f56g.f57e) {
            try {
                if (interfaceC1984e.isStarted()) {
                    interfaceC1984e.stop();
                    f55f.mo2351a("Stopped {}", interfaceC1984e);
                }
                if (interfaceC1984e instanceof InterfaceC1983d) {
                    ((InterfaceC1983d) interfaceC1984e).destroy();
                    f55f.mo2351a("Destroyed {}", interfaceC1984e);
                }
            } catch (Exception e7) {
                f55f.mo2359j(e7);
            }
        }
    }
}
