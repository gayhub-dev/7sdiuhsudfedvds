package com.ctvit.network.transformer;

import com.ctvit.network.func.HttpResponseFunc;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2126r;

/* loaded from: classes.dex */
public class HandleErrTransformer<T> implements InterfaceC2126r<T, T> {
    @Override // p194y3.InterfaceC2126r
    public InterfaceC2125q<T> apply(AbstractC2120l<T> abstractC2120l) {
        return abstractC2120l.onErrorResumeNext(new HttpResponseFunc());
    }
}
