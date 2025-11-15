package com.ctvit.network.cache.core;

import com.ctvit.network.cache.converter.IDiskConverter;
import com.ctvit.network.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import p137q3.C1766a;
import p137q3.C1768c;

/* loaded from: classes.dex */
public class LruDiskCache extends BaseCache {
    private IDiskConverter mDiskConverter;
    private C1766a mDiskLruCache;

    public LruDiskCache(IDiskConverter iDiskConverter, File file, int i7, long j7) {
        this.mDiskConverter = (IDiskConverter) Utils.checkNotNull(iDiskConverter, "diskConverter ==null");
        try {
            this.mDiskLruCache = C1766a.m1931g(file, i7, 1, j7);
        } catch (IOException e7) {
            e7.printStackTrace();
        }
    }

    private boolean isCacheDataFailure(File file, long j7) {
        return file.exists() && System.currentTimeMillis() - file.lastModified() > j7 * 1000;
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public boolean doClear() {
        try {
            C1766a c1766a = this.mDiskLruCache;
            c1766a.close();
            C1768c.m1955b(c1766a.f5013e);
            return true;
        } catch (IOException e7) {
            e7.printStackTrace();
            return false;
        }
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public boolean doContainsKey(String str) {
        C1766a c1766a = this.mDiskLruCache;
        if (c1766a == null) {
            return false;
        }
        try {
            return c1766a.m1935e(str) != null;
        } catch (IOException e7) {
            e7.printStackTrace();
            return false;
        }
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public <T> T doLoad(Type type, String str) {
        C1766a.c cVarM1934d;
        C1766a c1766a = this.mDiskLruCache;
        if (c1766a == null) {
            return null;
        }
        try {
            cVarM1934d = c1766a.m1934d(str);
        } catch (IOException e7) {
            e7.printStackTrace();
        }
        if (cVarM1934d == null) {
            return null;
        }
        InputStream inputStreamM1946c = cVarM1934d.m1946c(0);
        if (inputStreamM1946c == null) {
            cVarM1934d.m1944a();
            return null;
        }
        T t6 = (T) this.mDiskConverter.load(inputStreamM1946c, type);
        Utils.close(inputStreamM1946c);
        cVarM1934d.m1945b();
        return t6;
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public boolean doRemove(String str) {
        C1766a c1766a = this.mDiskLruCache;
        if (c1766a == null) {
            return false;
        }
        try {
            return c1766a.m1941l(str);
        } catch (IOException e7) {
            e7.printStackTrace();
            return false;
        }
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public <T> boolean doSave(String str, T t6) {
        C1766a.c cVarM1934d;
        C1766a c1766a = this.mDiskLruCache;
        if (c1766a == null) {
            return false;
        }
        try {
            cVarM1934d = c1766a.m1934d(str);
        } catch (IOException e7) {
            e7.printStackTrace();
        }
        if (cVarM1934d == null) {
            return false;
        }
        OutputStream outputStreamM1947d = cVarM1934d.m1947d(0);
        if (outputStreamM1947d == null) {
            cVarM1934d.m1944a();
            return false;
        }
        boolean zWriter = this.mDiskConverter.writer(outputStreamM1947d, t6);
        Utils.close(outputStreamM1947d);
        cVarM1934d.m1945b();
        return zWriter;
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public long getSize() {
        long j7;
        C1766a c1766a = this.mDiskLruCache;
        if (c1766a == null) {
            return 0L;
        }
        try {
            synchronized (c1766a) {
                j7 = c1766a.f5020l;
            }
            return j7;
        } catch (Exception e7) {
            e7.printStackTrace();
            return 0L;
        }
    }

    @Override // com.ctvit.network.cache.core.BaseCache
    public boolean isExpiry(String str, long j7) {
        if (this.mDiskLruCache != null && j7 > -1) {
            if (isCacheDataFailure(new File(this.mDiskLruCache.f5013e, str + ".0"), j7)) {
                return true;
            }
        }
        return false;
    }
}
