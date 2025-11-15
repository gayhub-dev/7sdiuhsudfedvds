package p112n4;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p022c4.InterfaceC0514b;
import p201z3.InterfaceC2153b;

/* compiled from: ScheduledRunnable.java */
/* renamed from: n4.j */
/* loaded from: classes.dex */
public final class RunnableC1535j extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, InterfaceC2153b {

    /* renamed from: f */
    public static final Object f4472f = new Object();

    /* renamed from: g */
    public static final Object f4473g = new Object();

    /* renamed from: h */
    public static final Object f4474h = new Object();

    /* renamed from: i */
    public static final Object f4475i = new Object();
    private static final long serialVersionUID = -6120223772001106981L;

    /* renamed from: e */
    public final Runnable f4476e;

    public RunnableC1535j(Runnable runnable, InterfaceC0514b interfaceC0514b) {
        super(3);
        this.f4476e = runnable;
        lazySet(0, interfaceC0514b);
    }

    /* renamed from: a */
    public void m1715a(Future<?> future) {
        Object obj;
        do {
            obj = get(1);
            if (obj == f4475i) {
                return;
            }
            if (obj == f4473g) {
                future.cancel(false);
                return;
            } else if (obj == f4474h) {
                future.cancel(true);
                return;
            }
        } while (!compareAndSet(1, obj, future));
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        run();
        return null;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        while (true) {
            Object obj5 = get(1);
            if (obj5 == f4475i || obj5 == (obj3 = f4473g) || obj5 == (obj4 = f4474h)) {
                break;
            }
            boolean z6 = get(2) != Thread.currentThread();
            if (z6) {
                obj3 = obj4;
            }
            if (compareAndSet(1, obj5, obj3)) {
                if (obj5 != null) {
                    ((Future) obj5).cancel(z6);
                }
            }
        }
        do {
            obj = get(0);
            if (obj == f4475i || obj == (obj2 = f4472f) || obj == null) {
                return;
            }
        } while (!compareAndSet(0, obj, obj2));
        ((InterfaceC0514b) obj).mo322a(this);
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        Object obj = get(0);
        return obj == f4472f || obj == f4475i;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        Object obj3;
        boolean zCompareAndSet;
        Object obj4;
        Object obj5;
        lazySet(2, Thread.currentThread());
        try {
            this.f4476e.run();
        } finally {
            try {
                lazySet(2, null);
                obj4 = get(0);
                if (obj4 != f4472f) {
                    ((InterfaceC0514b) obj4).mo322a(this);
                }
                do {
                    obj5 = get(1);
                    if (obj5 != f4473g) {
                        return;
                    } else {
                        return;
                    }
                } while (!compareAndSet(1, obj5, f4475i));
            } catch (Throwable th) {
                do {
                    if (obj == obj2) {
                        break;
                    } else if (obj == obj3) {
                        break;
                    }
                } while (!zCompareAndSet);
            }
        }
        lazySet(2, null);
        obj4 = get(0);
        if (obj4 != f4472f && compareAndSet(0, obj4, f4475i) && obj4 != null) {
            ((InterfaceC0514b) obj4).mo322a(this);
        }
        do {
            obj5 = get(1);
            if (obj5 != f4473g || obj5 == f4474h) {
                return;
            }
        } while (!compareAndSet(1, obj5, f4475i));
    }
}
