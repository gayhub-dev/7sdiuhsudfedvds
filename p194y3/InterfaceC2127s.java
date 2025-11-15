package p194y3;

import p201z3.InterfaceC2153b;

/* compiled from: Observer.java */
/* renamed from: y3.s */
/* loaded from: classes.dex */
public interface InterfaceC2127s<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t6);

    void onSubscribe(InterfaceC2153b interfaceC2153b);
}
