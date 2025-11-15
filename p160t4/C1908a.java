package p160t4;

import java.util.Objects;
import java.util.concurrent.Callable;
import p005a4.C0009a;
import p005a4.C0010b;
import p005a4.C0011c;
import p005a4.C0013e;
import p014b4.InterfaceC0446f;
import p138q4.C1774f;
import p194y3.AbstractC2128t;

/* compiled from: RxJavaPlugins.java */
/* renamed from: t4.a */
/* loaded from: classes.dex */
public final class C1908a {

    /* renamed from: a */
    public static volatile InterfaceC0446f<? super Throwable> f5611a;

    /* renamed from: a */
    public static AbstractC2128t m2204a(Callable<AbstractC2128t> callable) {
        try {
            AbstractC2128t abstractC2128tCall = callable.call();
            Objects.requireNonNull(abstractC2128tCall, "Scheduler Callable result can't be null");
            return abstractC2128tCall;
        } catch (Throwable th) {
            throw C1774f.m1961d(th);
        }
    }

    /* renamed from: b */
    public static void m2205b(Throwable th) {
        InterfaceC0446f<? super Throwable> interfaceC0446f = f5611a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else {
            boolean z6 = true;
            if (!(th instanceof C0011c) && !(th instanceof C0010b) && !(th instanceof IllegalStateException) && !(th instanceof NullPointerException) && !(th instanceof IllegalArgumentException) && !(th instanceof C0009a)) {
                z6 = false;
            }
            if (!z6) {
                th = new C0013e(th);
            }
        }
        if (interfaceC0446f != null) {
            try {
                interfaceC0446f.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th2);
            }
        }
        th.printStackTrace();
        Thread threadCurrentThread2 = Thread.currentThread();
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }
}
