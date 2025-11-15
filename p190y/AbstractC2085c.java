package p190y;

import java.lang.ref.Reference;
import java.util.ArrayDeque;
import java.util.Queue;
import p142r0.C1823h;
import p190y.InterfaceC2093k;

/* compiled from: BaseKeyPool.java */
/* renamed from: y.c */
/* loaded from: classes.dex */
public abstract class AbstractC2085c<T extends InterfaceC2093k> {

    /* renamed from: a */
    public Queue<T> f6179a;

    public AbstractC2085c(int i7) {
        if (i7 != 1) {
            char[] cArr = C1823h.f5300a;
            this.f6179a = new ArrayDeque(20);
        }
    }

    /* renamed from: a */
    public abstract T mo2512a();

    /* renamed from: b */
    public T m2513b() {
        T tPoll = this.f6179a.poll();
        return tPoll == null ? (T) mo2512a() : tPoll;
    }

    /* renamed from: c */
    public boolean m2514c() {
        Queue<T> queue = this.f6179a;
        return (((Reference) queue) == null || ((Reference) queue).get() == null) ? false : true;
    }

    /* renamed from: d */
    public void m2515d(T t6) {
        if (this.f6179a.size() < 20) {
            this.f6179a.offer(t6);
        }
    }
}
