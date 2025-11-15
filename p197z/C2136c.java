package p197z;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import p162u.InterfaceC1964h;

/* compiled from: DiskCacheWriteLocker.java */
/* renamed from: z.c */
/* loaded from: classes.dex */
public final class C2136c {

    /* renamed from: a */
    public final Map<InterfaceC1964h, a> f6280a = new HashMap();

    /* renamed from: b */
    public final b f6281b = new b();

    /* compiled from: DiskCacheWriteLocker.java */
    /* renamed from: z.c$a */
    public static class a {

        /* renamed from: a */
        public final Lock f6282a = new ReentrantLock();

        /* renamed from: b */
        public int f6283b;
    }

    /* compiled from: DiskCacheWriteLocker.java */
    /* renamed from: z.c$b */
    public static class b {

        /* renamed from: a */
        public final Queue<a> f6284a = new ArrayDeque();
    }

    /* renamed from: a */
    public void m2568a(InterfaceC1964h interfaceC1964h) {
        a aVar;
        synchronized (this) {
            a aVar2 = this.f6280a.get(interfaceC1964h);
            Objects.requireNonNull(aVar2, "Argument must not be null");
            aVar = aVar2;
            int i7 = aVar.f6283b;
            if (i7 < 1) {
                throw new IllegalStateException("Cannot release a lock that is not held, key: " + interfaceC1964h + ", interestedThreads: " + aVar.f6283b);
            }
            int i8 = i7 - 1;
            aVar.f6283b = i8;
            if (i8 == 0) {
                a aVarRemove = this.f6280a.remove(interfaceC1964h);
                if (!aVarRemove.equals(aVar)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + aVar + ", but actually removed: " + aVarRemove + ", key: " + interfaceC1964h);
                }
                b bVar = this.f6281b;
                synchronized (bVar.f6284a) {
                    if (bVar.f6284a.size() < 10) {
                        bVar.f6284a.offer(aVarRemove);
                    }
                }
            }
        }
        aVar.f6282a.unlock();
    }
}
