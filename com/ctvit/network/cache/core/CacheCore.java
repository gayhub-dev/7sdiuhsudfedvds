package com.ctvit.network.cache.core;

import com.ctvit.network.utils.Utils;
import java.lang.reflect.Type;
import okio.ByteString;
import p186x2.C2073a;

/* loaded from: classes.dex */
public class CacheCore {
    private LruDiskCache disk;

    public CacheCore(LruDiskCache lruDiskCache) {
        this.disk = (LruDiskCache) Utils.checkNotNull(lruDiskCache, "disk==null");
    }

    public synchronized boolean clear() {
        LruDiskCache lruDiskCache = this.disk;
        if (lruDiskCache == null) {
            return false;
        }
        return lruDiskCache.clear();
    }

    public synchronized boolean containsKey(String str) {
        String strHex = ByteString.m1869of(str.getBytes()).md5().hex();
        C2073a.m2459d("containsCache  key=" + strHex);
        LruDiskCache lruDiskCache = this.disk;
        if (lruDiskCache != null) {
            if (lruDiskCache.containsKey(strHex)) {
                return true;
            }
        }
        return false;
    }

    public long getSize() {
        LruDiskCache lruDiskCache = this.disk;
        if (lruDiskCache != null) {
            return lruDiskCache.getSize();
        }
        return 0L;
    }

    public synchronized <T> T load(Type type, String str, long j7) {
        String strHex = ByteString.m1869of(str.getBytes()).md5().hex();
        C2073a.m2459d("loadCache  key=" + strHex);
        LruDiskCache lruDiskCache = this.disk;
        if (lruDiskCache != null) {
            T t6 = (T) lruDiskCache.load(type, strHex, j7);
            if (t6 != null) {
                return t6;
            }
        }
        return null;
    }

    public synchronized boolean remove(String str) {
        String strHex = ByteString.m1869of(str.getBytes()).md5().hex();
        C2073a.m2459d("removeCache  key=" + strHex);
        LruDiskCache lruDiskCache = this.disk;
        if (lruDiskCache == null) {
            return true;
        }
        return lruDiskCache.remove(strHex);
    }

    public synchronized <T> boolean save(String str, T t6) {
        String strHex;
        strHex = ByteString.m1869of(str.getBytes()).md5().hex();
        C2073a.m2459d("saveCache  key=" + strHex);
        return this.disk.save(strHex, t6);
    }
}
