package p203z5;

import java.util.concurrent.atomic.AtomicLong;
import p186x2.C2074b;

/* compiled from: CounterStatistic.java */
/* renamed from: z5.a */
/* loaded from: classes.dex */
public class C2157a {

    /* renamed from: a */
    public final AtomicLong f6331a = new AtomicLong();

    /* renamed from: b */
    public final AtomicLong f6332b = new AtomicLong();

    /* renamed from: c */
    public final AtomicLong f6333c = new AtomicLong();

    /* renamed from: a */
    public void m2598a(long j7) {
        long jAddAndGet = this.f6332b.addAndGet(j7);
        if (j7 > 0) {
            this.f6333c.addAndGet(j7);
        }
        C2074b.m2476P(this.f6331a, jAddAndGet);
    }
}
