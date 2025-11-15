package com.ctvit.network.cache.model;

/* loaded from: classes.dex */
public enum CacheMode {
    NO_CACHE("NoStrategy"),
    DEFAULT("NoStrategy"),
    FIRSTREMOTE("FirstRemoteStrategy"),
    FIRSTCACHE("FirstCacheStategy"),
    ONLYREMOTE("OnlyRemoteStrategy"),
    ONLYCACHE("OnlyCacheStrategy"),
    CACHEANDREMOTE("CacheAndRemoteStrategy"),
    CACHEANDREMOTEDISTINCT("CacheAndRemoteDistinctStrategy");

    private final String className;

    CacheMode(String str) {
        this.className = str;
    }

    public String getClassName() {
        return this.className;
    }
}
