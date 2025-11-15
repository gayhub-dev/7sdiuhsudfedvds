package p073i5;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PooledBuffers.java */
/* renamed from: i5.p */
/* loaded from: classes.dex */
public class C1163p extends AbstractC1149b {

    /* renamed from: f */
    public final Queue<InterfaceC1152e> f2549f;

    /* renamed from: g */
    public final Queue<InterfaceC1152e> f2550g;

    /* renamed from: h */
    public final Queue<InterfaceC1152e> f2551h;

    /* renamed from: i */
    public final AtomicInteger f2552i;

    /* renamed from: j */
    public final int f2553j;

    /* renamed from: k */
    public final boolean f2554k;

    /* renamed from: l */
    public final boolean f2555l;

    public C1163p(int i7, int i8, int i9, int i10, int i11, int i12) {
        super(i7, i8, i9, i10, i11);
        this.f2552i = new AtomicInteger();
        this.f2549f = new ConcurrentLinkedQueue();
        this.f2550g = new ConcurrentLinkedQueue();
        this.f2551h = new ConcurrentLinkedQueue();
        this.f2554k = i7 == i11;
        this.f2555l = i9 == i11;
        this.f2553j = i12;
    }

    @Override // p073i5.InterfaceC1156i
    /* renamed from: a */
    public void mo1368a(InterfaceC1152e interfaceC1152e) {
        interfaceC1152e.clear();
        if (interfaceC1152e.mo1327W() || interfaceC1152e.mo1329f()) {
            return;
        }
        if (this.f2552i.incrementAndGet() > this.f2553j) {
            this.f2552i.decrementAndGet();
            return;
        }
        if (m1344e(interfaceC1152e)) {
            this.f2549f.add(interfaceC1152e);
        } else if (m1343d(interfaceC1152e)) {
            this.f2550g.add(interfaceC1152e);
        } else {
            this.f2551h.add(interfaceC1152e);
        }
    }

    @Override // p073i5.InterfaceC1156i
    /* renamed from: b */
    public InterfaceC1152e mo1369b(int i7) {
        if (this.f2554k && i7 == this.f2533b) {
            return mo1370c();
        }
        if (this.f2555l && i7 == this.f2535d) {
            return getBuffer();
        }
        InterfaceC1152e interfaceC1152ePoll = this.f2551h.poll();
        while (interfaceC1152ePoll != null && interfaceC1152ePoll.mo1350a() != i7) {
            this.f2552i.decrementAndGet();
            interfaceC1152ePoll = this.f2551h.poll();
        }
        if (interfaceC1152ePoll == null) {
            return m1346g(i7);
        }
        this.f2552i.decrementAndGet();
        return interfaceC1152ePoll;
    }

    @Override // p073i5.InterfaceC1156i
    /* renamed from: c */
    public InterfaceC1152e mo1370c() {
        InterfaceC1152e interfaceC1152ePoll = this.f2549f.poll();
        if (interfaceC1152ePoll == null) {
            return m1347h();
        }
        this.f2552i.decrementAndGet();
        return interfaceC1152ePoll;
    }

    @Override // p073i5.InterfaceC1156i
    public InterfaceC1152e getBuffer() {
        InterfaceC1152e interfaceC1152ePoll = this.f2550g.poll();
        if (interfaceC1152ePoll == null) {
            return m1345f();
        }
        this.f2552i.decrementAndGet();
        return interfaceC1152ePoll;
    }

    public String toString() {
        return String.format("%s [%d/%d@%d,%d/%d@%d,%d/%d@-]", C1163p.class.getSimpleName(), Integer.valueOf(this.f2549f.size()), Integer.valueOf(this.f2553j), Integer.valueOf(this.f2533b), Integer.valueOf(this.f2550g.size()), Integer.valueOf(this.f2553j), Integer.valueOf(this.f2535d), Integer.valueOf(this.f2551h.size()), Integer.valueOf(this.f2553j));
    }
}
