package com.ctvit.network.subsciber;

import com.ctvit.network.callback.CallBack;
import com.ctvit.network.callback.ProgressDialogCallBack;
import com.ctvit.network.exception.ApiException;

/* loaded from: classes.dex */
public class CallBackSubsciber<T> extends BaseSubscriber<T> {
    public CallBack<T> mCallBack;

    public CallBackSubsciber(CallBack<T> callBack) {
        this.mCallBack = callBack;
        if (callBack instanceof ProgressDialogCallBack) {
            ((ProgressDialogCallBack) callBack).subscription(this);
        }
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber, p194y3.InterfaceC2127s
    public void onComplete() {
        super.onComplete();
        CallBack<T> callBack = this.mCallBack;
        if (callBack != null) {
            callBack.onCompleted();
        }
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber
    public void onError(ApiException apiException) {
        CallBack<T> callBack = this.mCallBack;
        if (callBack != null) {
            callBack.onError(apiException);
        }
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber, p194y3.InterfaceC2127s
    public void onNext(T t6) {
        super.onNext(t6);
        CallBack<T> callBack = this.mCallBack;
        if (callBack != null) {
            callBack.onSuccess(t6);
        }
    }

    @Override // com.ctvit.network.subsciber.BaseSubscriber, p153s4.AbstractC1880c
    public void onStart() {
        super.onStart();
        CallBack<T> callBack = this.mCallBack;
        if (callBack != null) {
            callBack.onStart();
        }
    }
}
