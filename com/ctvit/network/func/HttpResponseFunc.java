package com.ctvit.network.func;

import com.ctvit.network.exception.ApiException;
import p014b4.InterfaceC0454n;
import p194y3.AbstractC2120l;

/* loaded from: classes.dex */
public class HttpResponseFunc<T> implements InterfaceC0454n<Throwable, AbstractC2120l<T>> {
    @Override // p014b4.InterfaceC0454n
    public AbstractC2120l<T> apply(Throwable th) {
        return AbstractC2120l.error(ApiException.handleException(th));
    }
}
