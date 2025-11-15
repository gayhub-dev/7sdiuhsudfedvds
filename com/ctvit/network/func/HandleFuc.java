package com.ctvit.network.func;

import com.ctvit.network.exception.ApiException;
import com.ctvit.network.exception.ServerException;
import com.ctvit.network.model.ApiResult;
import p014b4.InterfaceC0454n;

/* loaded from: classes.dex */
public class HandleFuc<T> implements InterfaceC0454n<ApiResult<T>, T> {
    @Override // p014b4.InterfaceC0454n
    public T apply(ApiResult<T> apiResult) {
        if (ApiException.isOk(apiResult)) {
            return apiResult.getData();
        }
        throw new ServerException(apiResult.getCode(), apiResult.getMsg());
    }
}
