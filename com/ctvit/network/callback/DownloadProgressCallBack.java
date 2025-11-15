package com.ctvit.network.callback;

import com.ctvit.network.exception.ApiException;

/* loaded from: classes.dex */
public abstract class DownloadProgressCallBack<T> extends CallBack<T> {
    public abstract void onComplete(String str);

    @Override // com.ctvit.network.callback.CallBack
    public void onCompleted() {
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onError(ApiException apiException) {
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onStart() {
    }

    @Override // com.ctvit.network.callback.CallBack
    public void onSuccess(T t6) {
    }

    public abstract void update(long j7, long j8, boolean z6);
}
