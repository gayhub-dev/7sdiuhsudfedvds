package com.ctvit.network.cache.stategy;

import com.ctvit.network.cache.RxCache;
import com.ctvit.network.cache.model.CacheResult;
import java.lang.reflect.Type;
import p014b4.InterfaceC0455o;
import p194y3.AbstractC2120l;

/* loaded from: classes.dex */
public final class CacheAndRemoteStrategy extends BaseStrategy {
    @Override // com.ctvit.network.cache.stategy.IStrategy
    public <T> AbstractC2120l<CacheResult<T>> execute(RxCache rxCache, String str, long j7, AbstractC2120l<T> abstractC2120l, Type type) {
        return AbstractC2120l.concat(loadCache(rxCache, type, str, j7, true), loadRemote(rxCache, str, abstractC2120l, false)).filter(new InterfaceC0455o<CacheResult<T>>() { // from class: com.ctvit.network.cache.stategy.CacheAndRemoteStrategy.1
            @Override // p014b4.InterfaceC0455o
            public boolean test(CacheResult<T> cacheResult) {
                return (cacheResult == null || cacheResult.data == null) ? false : true;
            }
        });
    }
}
