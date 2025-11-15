package io.reactivex.android.plugins;

import java.util.Objects;
import java.util.concurrent.Callable;
import p014b4.InterfaceC0454n;
import p138q4.C1774f;
import p194y3.AbstractC2128t;

/* loaded from: classes.dex */
public final class RxAndroidPlugins {
    private static volatile InterfaceC0454n<Callable<AbstractC2128t>, AbstractC2128t> onInitMainThreadHandler;
    private static volatile InterfaceC0454n<AbstractC2128t, AbstractC2128t> onMainThreadHandler;

    private RxAndroidPlugins() {
        throw new AssertionError("No instances.");
    }

    public static <T, R> R apply(InterfaceC0454n<T, R> interfaceC0454n, T t6) {
        try {
            return interfaceC0454n.apply(t6);
        } catch (Throwable th) {
            throw C1774f.m1961d(th);
        }
    }

    public static AbstractC2128t applyRequireNonNull(InterfaceC0454n<Callable<AbstractC2128t>, AbstractC2128t> interfaceC0454n, Callable<AbstractC2128t> callable) {
        AbstractC2128t abstractC2128t = (AbstractC2128t) apply(interfaceC0454n, callable);
        Objects.requireNonNull(abstractC2128t, "Scheduler Callable returned null");
        return abstractC2128t;
    }

    public static AbstractC2128t callRequireNonNull(Callable<AbstractC2128t> callable) {
        try {
            AbstractC2128t abstractC2128tCall = callable.call();
            if (abstractC2128tCall != null) {
                return abstractC2128tCall;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw C1774f.m1961d(th);
        }
    }

    public static InterfaceC0454n<Callable<AbstractC2128t>, AbstractC2128t> getInitMainThreadSchedulerHandler() {
        return onInitMainThreadHandler;
    }

    public static InterfaceC0454n<AbstractC2128t, AbstractC2128t> getOnMainThreadSchedulerHandler() {
        return onMainThreadHandler;
    }

    public static AbstractC2128t initMainThreadScheduler(Callable<AbstractC2128t> callable) {
        Objects.requireNonNull(callable, "scheduler == null");
        InterfaceC0454n<Callable<AbstractC2128t>, AbstractC2128t> interfaceC0454n = onInitMainThreadHandler;
        return interfaceC0454n == null ? callRequireNonNull(callable) : applyRequireNonNull(interfaceC0454n, callable);
    }

    public static AbstractC2128t onMainThreadScheduler(AbstractC2128t abstractC2128t) {
        Objects.requireNonNull(abstractC2128t, "scheduler == null");
        InterfaceC0454n<AbstractC2128t, AbstractC2128t> interfaceC0454n = onMainThreadHandler;
        return interfaceC0454n == null ? abstractC2128t : (AbstractC2128t) apply(interfaceC0454n, abstractC2128t);
    }

    public static void reset() {
        setInitMainThreadSchedulerHandler(null);
        setMainThreadSchedulerHandler(null);
    }

    public static void setInitMainThreadSchedulerHandler(InterfaceC0454n<Callable<AbstractC2128t>, AbstractC2128t> interfaceC0454n) {
        onInitMainThreadHandler = interfaceC0454n;
    }

    public static void setMainThreadSchedulerHandler(InterfaceC0454n<AbstractC2128t, AbstractC2128t> interfaceC0454n) {
        onMainThreadHandler = interfaceC0454n;
    }
}
