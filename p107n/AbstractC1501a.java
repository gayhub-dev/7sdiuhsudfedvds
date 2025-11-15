package p107n;

import android.os.Handler;
import android.os.Looper;
import java.util.List;
import p035e.C0888a;
import p091l.InterfaceC1406a;
import p107n.C1506f;

/* compiled from: AbsInteractiveStrategy.java */
/* renamed from: n.a */
/* loaded from: classes.dex */
public abstract class AbstractC1501a implements InterfaceC1406a, InterfaceC1504d {

    /* renamed from: a */
    public C1506f.a f4302a;

    /* renamed from: b */
    public Handler f4303b = null;

    public AbstractC1501a(C1506f.a aVar) {
        this.f4302a = aVar;
    }

    /* renamed from: f */
    public List<C0888a> m1666f() {
        return this.f4302a.f4320b.f4717j;
    }

    /* renamed from: g */
    public void m1667g(Runnable runnable) {
        if (this.f4303b == null) {
            synchronized (this) {
                if (this.f4303b == null) {
                    this.f4303b = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f4303b.post(runnable);
    }
}
