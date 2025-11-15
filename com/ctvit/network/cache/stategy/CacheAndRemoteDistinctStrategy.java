package com.ctvit.network.cache.stategy;

import com.ctvit.network.cache.RxCache;
import com.ctvit.network.cache.model.CacheResult;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import okio.ByteString;
import p014b4.InterfaceC0454n;
import p014b4.InterfaceC0455o;
import p194y3.AbstractC2120l;

/* loaded from: classes.dex */
public final class CacheAndRemoteDistinctStrategy extends BaseStrategy {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> String getData(T t6) {
        return t6.getClass().equals(String.class) ? (String) t6 : new Gson().toJson(t6);
    }

    @Override // com.ctvit.network.cache.stategy.IStrategy
    public <T> AbstractC2120l<CacheResult<T>> execute(RxCache rxCache, String str, long j7, AbstractC2120l<T> abstractC2120l, Type type) {
        return AbstractC2120l.concat(loadCache(rxCache, type, str, j7, true), loadRemote(rxCache, str, abstractC2120l, false)).filter(new InterfaceC0455o<CacheResult<T>>() { // from class: com.ctvit.network.cache.stategy.CacheAndRemoteDistinctStrategy.2
            @Override // p014b4.InterfaceC0455o
            public boolean test(CacheResult<T> cacheResult) {
                return (cacheResult == null || cacheResult.data == null) ? false : true;
            }
        }).distinctUntilChanged(new InterfaceC0454n<CacheResult<T>, String>() { // from class: com.ctvit.network.cache.stategy.CacheAndRemoteDistinctStrategy.1
            @Override // p014b4.InterfaceC0454n
            public String apply(CacheResult<T> cacheResult) {
                return ByteString.m1869of(CacheAndRemoteDistinctStrategy.this.getData(cacheResult.data).getBytes()).md5().hex();
            }
        });
    }
}
