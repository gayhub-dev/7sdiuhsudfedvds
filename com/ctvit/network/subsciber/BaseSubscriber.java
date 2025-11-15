package com.ctvit.network.subsciber;

import android.content.Context;
import com.ctvit.network.CtvitHttp;
import com.ctvit.network.exception.ApiException;
import com.ctvit.network.utils.Utils;
import java.lang.ref.WeakReference;
import p153s4.AbstractC1880c;
import p186x2.C2073a;

/* loaded from: classes.dex */
public abstract class BaseSubscriber<T> extends AbstractC1880c<T> {
    public WeakReference<Context> contextWeakReference = new WeakReference<>(CtvitHttp.getContext());

    @Override // p194y3.InterfaceC2127s
    public void onComplete() {
        C2073a.m2459d("-->http is onComplete");
    }

    public abstract void onError(ApiException apiException);

    @Override // p194y3.InterfaceC2127s
    public final void onError(Throwable th) {
        C2073a.m2456a("-->http is onError");
        if (th instanceof ApiException) {
            C2073a.m2456a("--> e instanceof ApiException err:" + th);
            onError((ApiException) th);
            return;
        }
        C2073a.m2456a("--> e !instanceof ApiException err:" + th);
        onError(ApiException.handleException(th));
    }

    @Override // p194y3.InterfaceC2127s
    public void onNext(T t6) {
        C2073a.m2459d("-->http is onNext");
    }

    @Override // p153s4.AbstractC1880c
    public void onStart() {
        C2073a.m2459d("-->http is onStart");
        WeakReference<Context> weakReference = this.contextWeakReference;
        if (weakReference == null || weakReference.get() == null || Utils.isNetworkAvailable(this.contextWeakReference.get())) {
            return;
        }
        onComplete();
    }
}
