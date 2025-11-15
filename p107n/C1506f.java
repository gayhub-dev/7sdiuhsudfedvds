package p107n;

import android.content.Context;
import p043f.C0987d;
import p091l.AbstractC1408c;
import p091l.InterfaceC1406a;
import p115o.C1570g;

/* compiled from: InteractiveModeManager.java */
/* renamed from: n.f */
/* loaded from: classes.dex */
public class C1506f extends AbstractC1408c<AbstractC1501a> implements InterfaceC1504d {

    /* renamed from: e */
    public boolean f4316e;

    /* renamed from: f */
    public a f4317f;

    /* renamed from: g */
    public b f4318g;

    /* compiled from: InteractiveModeManager.java */
    /* renamed from: n.f$a */
    public static class a {

        /* renamed from: a */
        public int f4319a;

        /* renamed from: b */
        public C1570g f4320b;

        /* renamed from: c */
        public C0987d f4321c;
    }

    /* compiled from: InteractiveModeManager.java */
    /* renamed from: n.f$b */
    public class b implements Runnable {

        /* renamed from: e */
        public int f4322e;

        /* renamed from: f */
        public int f4323f;

        public b(RunnableC1505e runnableC1505e) {
        }

        @Override // java.lang.Runnable
        public void run() {
            ((AbstractC1501a) C1506f.this.f4142b).mo1668c(this.f4322e, this.f4323f);
        }
    }

    public C1506f(int i7, C0987d c0987d, a aVar) {
        super(i7, c0987d);
        this.f4318g = new b(null);
        this.f4317f = aVar;
        aVar.f4321c = c0987d;
    }

    @Override // p091l.AbstractC1408c
    /* renamed from: a */
    public InterfaceC1406a mo1593a(int i7) {
        return i7 != 1 ? i7 != 3 ? i7 != 4 ? i7 != 5 ? new C1509i(this.f4317f) : new C1502b(this.f4317f) : new C1503c(this.f4317f) : new C1508h(this.f4317f) : new C1507g(this.f4317f);
    }

    @Override // p091l.AbstractC1408c
    /* renamed from: b */
    public void mo1594b(Context context) {
        super.mo1594b(context);
        if (this.f4316e) {
            m1670e(context);
        }
    }

    @Override // p107n.InterfaceC1504d
    /* renamed from: c */
    public boolean mo1668c(int i7, int i8) {
        b bVar = this.f4318g;
        bVar.f4322e = i7;
        bVar.f4323f = i8;
        this.f4144d.m956b(bVar);
        return false;
    }

    /* renamed from: e */
    public void m1670e(Context context) {
        this.f4316e = true;
        if (((AbstractC1501a) this.f4142b).mo1588a(context)) {
            ((AbstractC1501a) this.f4142b).mo1589b(context);
        }
    }
}
