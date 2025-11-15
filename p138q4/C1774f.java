package p138q4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p005a4.C0009a;

/* compiled from: ExceptionHelper.java */
/* renamed from: q4.f */
/* loaded from: classes.dex */
public final class C1774f {

    /* renamed from: a */
    public static final Throwable f5055a = new a();

    /* compiled from: ExceptionHelper.java */
    /* renamed from: q4.f$a */
    public static final class a extends Throwable {
        private static final long serialVersionUID = -4649703670690200604L;

        public a() {
            super("No further exceptions");
        }

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }
    }

    /* renamed from: a */
    public static <T> boolean m1958a(AtomicReference<Throwable> atomicReference, Throwable th) {
        Throwable th2;
        do {
            th2 = atomicReference.get();
            if (th2 == f5055a) {
                return false;
            }
        } while (!atomicReference.compareAndSet(th2, th2 == null ? th : new C0009a(th2, th)));
        return true;
    }

    /* renamed from: b */
    public static <T> Throwable m1959b(AtomicReference<Throwable> atomicReference) {
        Throwable th = atomicReference.get();
        Throwable th2 = f5055a;
        return th != th2 ? atomicReference.getAndSet(th2) : th;
    }

    /* renamed from: c */
    public static String m1960c(long j7, TimeUnit timeUnit) {
        return "The source did not signal an event for " + j7 + " " + timeUnit.toString().toLowerCase() + " and has been terminated.";
    }

    /* renamed from: d */
    public static RuntimeException m1961d(Throwable th) {
        if (th instanceof Error) {
            throw ((Error) th);
        }
        return th instanceof RuntimeException ? (RuntimeException) th : new RuntimeException(th);
    }
}
