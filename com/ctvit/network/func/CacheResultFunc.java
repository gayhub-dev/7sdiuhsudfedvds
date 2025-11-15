package com.ctvit.network.func;

import com.ctvit.network.cache.model.CacheResult;
import p014b4.InterfaceC0454n;

/* loaded from: classes.dex */
public class CacheResultFunc<T> implements InterfaceC0454n<CacheResult<T>, T> {
    @Override // p014b4.InterfaceC0454n
    public T apply(CacheResult<T> cacheResult) {
        return cacheResult.data;
    }
}
