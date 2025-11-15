package p089k5;

/* compiled from: SelectChannelEndPoint.java */
/* renamed from: k5.h */
/* loaded from: classes.dex */
public class RunnableC1399h implements Runnable {

    /* renamed from: e */
    public final /* synthetic */ long f4082e;

    /* renamed from: f */
    public final /* synthetic */ C1398g f4083f;

    public RunnableC1399h(C1398g c1398g, long j7) {
        this.f4083f = c1398g;
        this.f4082e = j7;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f4083f.m1565B(this.f4082e);
        } finally {
            this.f4083f.m1567D(true);
        }
    }
}
