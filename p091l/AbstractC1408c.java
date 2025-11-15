package p091l;

import android.content.Context;
import p035e.C0901n;
import p043f.C0987d;
import p043f.C0988e;
import p043f.C0989f;
import p091l.InterfaceC1406a;

/* compiled from: ModeManager.java */
/* renamed from: l.c */
/* loaded from: classes.dex */
public abstract class AbstractC1408c<T extends InterfaceC1406a> {

    /* renamed from: a */
    public int f4141a;

    /* renamed from: b */
    public T f4142b;

    /* renamed from: c */
    public C0901n.g f4143c;

    /* renamed from: d */
    public C0987d f4144d;

    /* compiled from: ModeManager.java */
    /* renamed from: l.c$a */
    public class a implements Runnable {

        /* renamed from: e */
        public final /* synthetic */ InterfaceC1406a f4145e;

        /* renamed from: f */
        public final /* synthetic */ Context f4146f;

        public a(AbstractC1408c abstractC1408c, InterfaceC1406a interfaceC1406a, Context context) {
            this.f4145e = interfaceC1406a;
            this.f4146f = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4145e.mo1591e(this.f4146f);
        }
    }

    public AbstractC1408c(int i7, C0987d c0987d) {
        this.f4144d = c0987d;
        this.f4141a = i7;
    }

    /* renamed from: a */
    public abstract T mo1593a(int i7);

    /* renamed from: b */
    public void mo1594b(Context context) {
        C0989f.m1001a("strategy on must call from main thread!");
        T t6 = this.f4142b;
        if (t6.mo1588a(context)) {
            this.f4144d.m956b(new a(this, t6, context));
        }
    }

    /* renamed from: d */
    public void m1595d(Context context, C0901n.g gVar) {
        this.f4143c = gVar;
        int i7 = this.f4141a;
        if (this.f4142b != null) {
            C0989f.m1001a("strategy off must call from main thread!");
            T t6 = this.f4142b;
            if (t6.mo1588a(context)) {
                this.f4144d.m956b(new RunnableC1409d(this, t6, context));
            }
        }
        T t7 = (T) mo1593a(i7);
        this.f4142b = t7;
        if (t7.mo1588a(context)) {
            mo1594b(context);
        } else {
            C0988e.f1823a.post(new RunnableC1407b(this, i7));
        }
    }
}
