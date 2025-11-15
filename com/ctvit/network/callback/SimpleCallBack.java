package com.ctvit.network.callback;

import com.ctvit.network.exception.ApiException;

/* loaded from: classes.dex */
public abstract class SimpleCallBack<T> extends CallBack<T> {
    private T data;

    /* renamed from: e */
    private ApiException f960e;

    public boolean isError() {
        return this.f960e != null;
    }

    public boolean isSuccess() {
        return this.data != null;
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onCompleted() {
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
        this.f960e = apiException;
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onStart() {
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onSuccess(T t6) {
        this.data = t6;
    }
}
