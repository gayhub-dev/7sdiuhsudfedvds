package com.ctvit.network.cache.stategy;

import com.ctvit.network.cache.RxCache;
import com.ctvit.network.cache.model.CacheResult;
import java.lang.reflect.Type;
import p014b4.InterfaceC0454n;
import p194y3.AbstractC2120l;

/* loaded from: classes.dex */
public class NoStrategy implements IStrategy {
    @Override // com.ctvit.network.cache.stategy.IStrategy
    public <T> AbstractC2120l<CacheResult<T>> execute(RxCache rxCache, String str, long j7, AbstractC2120l<T> abstractC2120l, Type type) {
        return (AbstractC2120l<CacheResult<T>>) abstractC2120l.map(new InterfaceC0454n<T, CacheResult<T>>() { // from class: com.ctvit.network.cache.stategy.NoStrategy.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // p014b4.InterfaceC0454n
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((C06491<T>) obj);
            }

            @Override // p014b4.InterfaceC0454n
            public CacheResult<T> apply(T t6) {
                return new CacheResult<>(false, t6);
            }
        });
    }
}
