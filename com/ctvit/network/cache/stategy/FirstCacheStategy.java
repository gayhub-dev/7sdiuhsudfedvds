package com.ctvit.network.cache.stategy;

import com.ctvit.network.cache.RxCache;
import com.ctvit.network.cache.model.CacheResult;
import java.lang.reflect.Type;
import p194y3.AbstractC2120l;

/* loaded from: classes.dex */
public final class FirstCacheStategy extends BaseStrategy {
    @Override // com.ctvit.network.cache.stategy.IStrategy
    public <T> AbstractC2120l<CacheResult<T>> execute(RxCache rxCache, String str, long j7, AbstractC2120l<T> abstractC2120l, Type type) {
        return loadCache(rxCache, type, str, j7, true).switchIfEmpty(loadRemote(rxCache, str, abstractC2120l, false));
    }
}
