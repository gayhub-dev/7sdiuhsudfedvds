package com.ctvit.network.cache.model;

import java.io.Serializable;
import p009b.C0413b;

/* loaded from: classes.dex */
public class CacheResult<T> implements Serializable {
    public T data;
    private boolean isFromCache;

    public CacheResult() {
    }

    public boolean isCache() {
        return this.isFromCache;
    }

    public void setCache(boolean z6) {
        this.isFromCache = z6;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("CacheResult{isCache=");
        sbM112a.append(this.isFromCache);
        sbM112a.append(", data=");
        sbM112a.append(this.data);
        sbM112a.append('}');
        return sbM112a.toString();
    }

    public CacheResult(boolean z6) {
        this.isFromCache = z6;
    }

    public CacheResult(boolean z6, T t6) {
        this.isFromCache = z6;
        this.data = t6;
    }
}
