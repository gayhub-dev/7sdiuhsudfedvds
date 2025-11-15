package p005a4;

/* compiled from: OnErrorNotImplementedException.java */
/* renamed from: a4.c */
/* loaded from: classes.dex */
public final class C0011c extends RuntimeException {
    private static final long serialVersionUID = -6298857009889503852L;

    public C0011c(Throwable th) {
        super("The exception was not handled due to missing onError handler in the subscribe() method call. Further reading: https://github.com/ReactiveX/RxJava/wiki/Error-Handling | " + th, th == null ? new NullPointerException() : th);
    }
}
