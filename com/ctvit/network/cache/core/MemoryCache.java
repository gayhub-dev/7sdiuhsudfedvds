package com.ctvit.network.cache.core;

import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class MemoryCache extends BaseCache {
    @Override // com.ctvit.network.cache.core.BaseCache
    public boolean doClear() {
        return false;
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public boolean doContainsKey(String str) {
        return false;
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public <T> T doLoad(Type type, String str) {
        return null;
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public boolean doRemove(String str) {
        return false;
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public <T> boolean doSave(String str, T t6) {
        return false;
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public long getSize() {
        return 0L;
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public boolean isExpiry(String str, long j7) {
        return false;
    }
}
