package p149s0;

import android.support.v4.util.Pools;
import android.util.Log;
import p149s0.AbstractC1863d;

/* compiled from: FactoryPools.java */
/* renamed from: s0.a */
/* loaded from: classes.dex */
public final class C1860a {

    /* renamed from: a */
    public static final e<Object> f5434a = new a();

    /* compiled from: FactoryPools.java */
    /* renamed from: s0.a$a */
    public static class a implements e<Object> {
        @Override // p149s0.C1860a.e
        /* renamed from: a */
        public void mo2132a(Object obj) {
        }
    }

    /* compiled from: FactoryPools.java */
    /* renamed from: s0.a$b */
    public interface b<T> {
        /* renamed from: a */
        T mo1703a();
    }

    /* compiled from: FactoryPools.java */
    /* renamed from: s0.a$c */
    public static final class c<T> implements Pools.Pool<T> {

        /* renamed from: a */
        public final b<T> f5435a;

        /* renamed from: b */
        public final e<T> f5436b;

        /* renamed from: c */
        public final Pools.Pool<T> f5437c;

        public c(Pools.Pool<T> pool, b<T> bVar, e<T> eVar) {
            this.f5437c = pool;
            this.f5435a = bVar;
            this.f5436b = eVar;
        }

        @Override // android.support.v4.util.Pools.Pool
        public T acquire() {
            T tAcquire = this.f5437c.acquire();
            if (tAcquire == null) {
                tAcquire = this.f5435a.mo1703a();
                if (Log.isLoggable("FactoryPools", 2)) {
                    tAcquire.getClass().toString();
                }
            }
            if (tAcquire instanceof d) {
                ((AbstractC1863d.b) tAcquire.mo1694e()).f5438a = false;
            }
            return (T) tAcquire;
        }

        @Override // android.support.v4.util.Pools.Pool
        public boolean release(T t6) {
            if (t6 instanceof d) {
                ((AbstractC1863d.b) ((d) t6).mo1694e()).f5438a = true;
            }
            this.f5436b.mo2132a(t6);
            return this.f5437c.release(t6);
        }
    }

    /* compiled from: FactoryPools.java */
    /* renamed from: s0.a$d */
    public interface d {
        /* renamed from: e */
        AbstractC1863d mo1694e();
    }

    /* compiled from: FactoryPools.java */
    /* renamed from: s0.a$e */
    public interface e<T> {
        /* renamed from: a */
        void mo2132a(T t6);
    }

    /* renamed from: a */
    public static <T extends d> Pools.Pool<T> m2131a(int i7, b<T> bVar) {
        return new c(new Pools.SimplePool(i7), bVar, f5434a);
    }
}
