package com.ctvit.network.cache;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.support.constraint.C0072a;
import com.ctvit.network.cache.converter.IDiskConverter;
import com.ctvit.network.cache.converter.SerializableDiskConverter;
import com.ctvit.network.cache.core.CacheCore;
import com.ctvit.network.cache.core.LruDiskCache;
import com.ctvit.network.cache.model.CacheMode;
import com.ctvit.network.cache.model.CacheResult;
import com.ctvit.network.cache.stategy.IStrategy;
import com.ctvit.network.utils.Utils;
import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import p009b.C0413b;
import p022c4.EnumC0515c;
import p088k4.C1239a0;
import p186x2.C2073a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2122n;
import p194y3.InterfaceC2123o;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2126r;

/* loaded from: classes.dex */
public final class RxCache {
    private final int appVersion;
    private final CacheCore cacheCore;
    private final String cacheKey;
    private final long cacheTime;
    private final Context context;
    private final IDiskConverter diskConverter;
    private final File diskDir;
    private final long diskMaxSize;

    public static abstract class SimpleSubscribe<T> implements InterfaceC2123o<T> {
        private SimpleSubscribe() {
        }

        public abstract T execute();

        @Override // p194y3.InterfaceC2123o
        public void subscribe(InterfaceC2122n<T> interfaceC2122n) {
            try {
                T tExecute = execute();
                C1239a0.a aVar = (C1239a0.a) interfaceC2122n;
                if (!aVar.isDisposed()) {
                    if (tExecute == null) {
                        aVar.m1461a(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    } else if (!aVar.isDisposed()) {
                        aVar.f2774e.onNext(tExecute);
                    }
                }
                if (aVar.isDisposed() || aVar.isDisposed()) {
                    return;
                }
                try {
                    aVar.f2774e.onComplete();
                } finally {
                    EnumC0515c.m323a(aVar);
                }
            } catch (Throwable th) {
                C2073a.m2456a(th.getMessage());
                C1239a0.a aVar2 = (C1239a0.a) interfaceC2122n;
                if (!aVar2.isDisposed()) {
                    aVar2.m1461a(th);
                }
                C2074b.m2470J(th);
            }
        }
    }

    private IStrategy loadStrategy(CacheMode cacheMode) {
        try {
            return (IStrategy) Class.forName(IStrategy.class.getPackage().getName() + "." + cacheMode.getClassName()).newInstance();
        } catch (Exception e7) {
            throw new RuntimeException("loadStrategy(" + cacheMode + ") err!!" + e7.getMessage());
        }
    }

    public AbstractC2120l<Boolean> clear() {
        return AbstractC2120l.create(new SimpleSubscribe<Boolean>() { // from class: com.ctvit.network.cache.RxCache.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ctvit.network.cache.RxCache.SimpleSubscribe
            public Boolean execute() {
                return Boolean.valueOf(RxCache.this.cacheCore.clear());
            }
        });
    }

    public AbstractC2120l<Boolean> containsKey(final String str) {
        return AbstractC2120l.create(new SimpleSubscribe<Boolean>() { // from class: com.ctvit.network.cache.RxCache.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ctvit.network.cache.RxCache.SimpleSubscribe
            public Boolean execute() {
                return Boolean.valueOf(RxCache.this.cacheCore.containsKey(str));
            }
        });
    }

    public int getAppVersion() {
        return this.appVersion;
    }

    public CacheCore getCacheCore() {
        return this.cacheCore;
    }

    public String getCacheKey() {
        return this.cacheKey;
    }

    public long getCacheTime() {
        return this.cacheTime;
    }

    public Context getContext() {
        return this.context;
    }

    public IDiskConverter getDiskConverter() {
        return this.diskConverter;
    }

    public File getDiskDir() {
        return this.diskDir;
    }

    public long getDiskMaxSize() {
        return this.diskMaxSize;
    }

    public long getSize() {
        return this.cacheCore.getSize();
    }

    public <T> AbstractC2120l<T> load(Type type, String str) {
        return load(type, str, -1L);
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public AbstractC2120l<Boolean> remove(final String str) {
        return AbstractC2120l.create(new SimpleSubscribe<Boolean>() { // from class: com.ctvit.network.cache.RxCache.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ctvit.network.cache.RxCache.SimpleSubscribe
            public Boolean execute() {
                return Boolean.valueOf(RxCache.this.cacheCore.remove(str));
            }
        });
    }

    public <T> AbstractC2120l<Boolean> save(final String str, final T t6) {
        return AbstractC2120l.create(new SimpleSubscribe<Boolean>() { // from class: com.ctvit.network.cache.RxCache.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ctvit.network.cache.RxCache.SimpleSubscribe
            public Boolean execute() {
                RxCache.this.cacheCore.save(str, t6);
                return Boolean.TRUE;
            }
        });
    }

    public <T> InterfaceC2126r<T, CacheResult<T>> transformer(CacheMode cacheMode, final Type type) {
        final IStrategy iStrategyLoadStrategy = loadStrategy(cacheMode);
        return new InterfaceC2126r<T, CacheResult<T>>() { // from class: com.ctvit.network.cache.RxCache.1
            @Override // p194y3.InterfaceC2126r
            public InterfaceC2125q<CacheResult<T>> apply(AbstractC2120l<T> abstractC2120l) {
                StringBuilder sbM112a = C0413b.m112a("cackeKey=");
                sbM112a.append(RxCache.this.cacheKey);
                C2073a.m2459d(sbM112a.toString());
                Type parameterizedType = type;
                if ((parameterizedType instanceof ParameterizedType) && CacheResult.class.isAssignableFrom((Class) ((ParameterizedType) parameterizedType).getRawType())) {
                    parameterizedType = Utils.getParameterizedType(type, 0);
                }
                Type type2 = parameterizedType;
                IStrategy iStrategy = iStrategyLoadStrategy;
                RxCache rxCache = RxCache.this;
                return iStrategy.execute(rxCache, rxCache.cacheKey, RxCache.this.cacheTime, abstractC2120l, type2);
            }
        };
    }

    public RxCache() {
        this(new Builder());
    }

    public <T> AbstractC2120l<T> load(final Type type, final String str, final long j7) {
        return AbstractC2120l.create(new SimpleSubscribe<T>() { // from class: com.ctvit.network.cache.RxCache.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.ctvit.network.cache.RxCache.SimpleSubscribe
            public T execute() {
                return (T) RxCache.this.cacheCore.load(type, str, j7);
            }
        });
    }

    private RxCache(Builder builder) {
        this.context = builder.context;
        this.cacheKey = builder.cachekey;
        this.cacheTime = builder.cacheTime;
        File file = builder.diskDir;
        this.diskDir = file;
        int i7 = builder.appVersion;
        this.appVersion = i7;
        long j7 = builder.diskMaxSize;
        this.diskMaxSize = j7;
        IDiskConverter iDiskConverter = builder.diskConverter;
        this.diskConverter = iDiskConverter;
        this.cacheCore = new CacheCore(new LruDiskCache(iDiskConverter, file, i7, j7));
    }

    public static final class Builder {
        public static final long CACHE_NEVER_EXPIRE = -1;
        private static final int MAX_DISK_CACHE_SIZE = 52428800;
        private static final int MIN_DISK_CACHE_SIZE = 5242880;
        private int appVersion;
        private long cacheTime;
        private String cachekey;
        private Context context;
        private IDiskConverter diskConverter;
        private File diskDir;
        private long diskMaxSize;

        public Builder() {
            this.diskConverter = new SerializableDiskConverter();
            this.cacheTime = -1L;
            this.appVersion = 1;
        }

        private static long calculateDiskCacheSize(File file) {
            long blockCount;
            try {
                StatFs statFs = new StatFs(file.getAbsolutePath());
                blockCount = (statFs.getBlockCount() * statFs.getBlockSize()) / 50;
            } catch (IllegalArgumentException unused) {
                blockCount = 0;
            }
            return Math.max(Math.min(blockCount, 52428800L), 5242880L);
        }

        public Builder appVersion(int i7) {
            this.appVersion = i7;
            return this;
        }

        public RxCache build() {
            Context context;
            if (this.diskDir == null && (context = this.context) != null) {
                this.diskDir = getDiskCacheDir(context, "data-cache");
            }
            Utils.checkNotNull(this.diskDir, "diskDir==null");
            if (!this.diskDir.exists()) {
                this.diskDir.mkdirs();
            }
            if (this.diskConverter == null) {
                this.diskConverter = new SerializableDiskConverter();
            }
            if (this.diskMaxSize <= 0) {
                this.diskMaxSize = calculateDiskCacheSize(this.diskDir);
            }
            this.cacheTime = Math.max(-1L, this.cacheTime);
            this.appVersion = Math.max(1, this.appVersion);
            return new RxCache(this);
        }

        public Builder cacheTime(long j7) {
            this.cacheTime = j7;
            return this;
        }

        public Builder cachekey(String str) {
            this.cachekey = str;
            return this;
        }

        public Builder diskConverter(IDiskConverter iDiskConverter) {
            this.diskConverter = iDiskConverter;
            return this;
        }

        public Builder diskDir(File file) {
            this.diskDir = file;
            return this;
        }

        public Builder diskMax(long j7) {
            this.diskMaxSize = j7;
            return this;
        }

        public File getDiskCacheDir(Context context, String str) {
            File externalCacheDir = ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir() : context.getCacheDir();
            if (externalCacheDir == null) {
                externalCacheDir = context.getCacheDir();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(externalCacheDir.getPath());
            return new File(C0072a.m92a(sb, File.separator, str));
        }

        public Builder init(Context context) {
            this.context = context;
            return this;
        }

        public Builder(RxCache rxCache) {
            this.context = rxCache.context;
            this.appVersion = rxCache.appVersion;
            this.diskMaxSize = rxCache.diskMaxSize;
            this.diskDir = rxCache.diskDir;
            this.diskConverter = rxCache.diskConverter;
            this.context = rxCache.context;
            this.cachekey = rxCache.cacheKey;
            this.cacheTime = rxCache.cacheTime;
        }
    }
}
