package p084k0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import p084k0.InterfaceC1216b;
import p108n0.InterfaceC1510a;
import p141r.C1814i;
import p142r0.C1823h;

/* compiled from: DefaultConnectivityMonitor.java */
/* renamed from: k0.d */
/* loaded from: classes.dex */
public class C1218d implements InterfaceC1216b {

    /* renamed from: a */
    public final Context f2741a;

    /* renamed from: b */
    public final InterfaceC1216b.a f2742b;

    /* renamed from: c */
    public boolean f2743c;

    /* renamed from: d */
    public boolean f2744d;

    /* renamed from: e */
    public final BroadcastReceiver f2745e = new a();

    /* compiled from: DefaultConnectivityMonitor.java */
    /* renamed from: k0.d$a */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            C1218d c1218d = C1218d.this;
            boolean z6 = c1218d.f2743c;
            c1218d.f2743c = c1218d.m1448k(context);
            C1218d c1218d2 = C1218d.this;
            boolean z7 = c1218d2.f2743c;
            if (z6 != z7) {
                C1814i.c cVar = (C1814i.c) c1218d2.f2742b;
                Objects.requireNonNull(cVar);
                if (z7) {
                    C1225k c1225k = cVar.f5279a;
                    Iterator it = ((ArrayList) C1823h.m2061e(c1225k.f2753a)).iterator();
                    while (it.hasNext()) {
                        InterfaceC1510a interfaceC1510a = (InterfaceC1510a) it.next();
                        if (!interfaceC1510a.mo1673d() && !interfaceC1510a.isCancelled()) {
                            interfaceC1510a.pause();
                            if (c1225k.f2755c) {
                                c1225k.f2754b.add(interfaceC1510a);
                            } else {
                                interfaceC1510a.mo1672c();
                            }
                        }
                    }
                }
            }
        }
    }

    public C1218d(Context context, InterfaceC1216b.a aVar) {
        this.f2741a = context.getApplicationContext();
        this.f2742b = aVar;
    }

    @Override // p084k0.InterfaceC1221g
    /* renamed from: a */
    public void mo1446a() {
        if (this.f2744d) {
            return;
        }
        this.f2743c = m1448k(this.f2741a);
        this.f2741a.registerReceiver(this.f2745e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.f2744d = true;
    }

    @Override // p084k0.InterfaceC1221g
    /* renamed from: j */
    public void mo1447j() {
    }

    /* renamed from: k */
    public boolean m1448k(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override // p084k0.InterfaceC1221g
    public void onStop() {
        if (this.f2744d) {
            this.f2741a.unregisterReceiver(this.f2745e);
            this.f2744d = false;
        }
    }
}
