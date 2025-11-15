package p005a4;

/* compiled from: UndeliverableException.java */
/* renamed from: a4.e */
/* loaded from: classes.dex */
public final class C0013e extends IllegalStateException {
    private static final long serialVersionUID = 1644750035281290266L;

    public C0013e(Throwable th) {
        super("The exception could not be delivered to the consumer because it has already canceled/disposed the flow or the exception has nowhere to go to begin with. Further reading: https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling | " + th, th);
    }
}
