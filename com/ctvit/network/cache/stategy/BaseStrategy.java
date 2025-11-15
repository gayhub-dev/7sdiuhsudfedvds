package com.ctvit.network.cache.stategy;

import com.ctvit.network.cache.RxCache;
import com.ctvit.network.cache.model.CacheResult;
import java.lang.reflect.Type;
import java.util.ConcurrentModificationException;
import p014b4.InterfaceC0446f;
import p014b4.InterfaceC0454n;
import p174v4.C2012a;
import p186x2.C2073a;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2125q;

/* loaded from: classes.dex */
public abstract class BaseStrategy implements IStrategy {
    public <T> AbstractC2120l<CacheResult<T>> loadCache(RxCache rxCache, Type type, String str, long j7, boolean z6) {
        AbstractC2120l<CacheResult<T>> abstractC2120l = (AbstractC2120l<CacheResult<T>>) rxCache.load(type, str, j7).flatMap(new InterfaceC0454n<T, InterfaceC2125q<CacheResult<T>>>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // p014b4.InterfaceC0454n
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((C06401<T>) obj);
            }

            @Override // p014b4.InterfaceC0454n
            public InterfaceC2125q<CacheResult<T>> apply(T t6) {
                return t6 == null ? AbstractC2120l.error(new NullPointerException("Not find the cache!")) : AbstractC2120l.just(new CacheResult(true, t6));
            }
        });
        return z6 ? abstractC2120l.onErrorResumeNext(new InterfaceC0454n<Throwable, InterfaceC2125q<? extends CacheResult<T>>>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.2
            @Override // p014b4.InterfaceC0454n
            public InterfaceC2125q<? extends CacheResult<T>> apply(Throwable th) {
                return AbstractC2120l.empty();
            }
        }) : abstractC2120l;
    }

    public <T> AbstractC2120l<CacheResult<T>> loadRemote(final RxCache rxCache, final String str, AbstractC2120l<T> abstractC2120l, boolean z6) {
        AbstractC2120l<CacheResult<T>> abstractC2120l2 = (AbstractC2120l<CacheResult<T>>) abstractC2120l.flatMap(new InterfaceC0454n<T, InterfaceC2125q<CacheResult<T>>>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // p014b4.InterfaceC0454n
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((C06445<T>) obj);
            }

            @Override // p014b4.InterfaceC0454n
            public InterfaceC2125q<CacheResult<T>> apply(final T t6) {
                return rxCache.save(str, t6).map(new InterfaceC0454n<Boolean, CacheResult<T>>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.5.2
                    @Override // p014b4.InterfaceC0454n
                    public CacheResult<T> apply(Boolean bool) {
                        C2073a.m2459d("save status => " + bool);
                        return new CacheResult<>(false, t6);
                    }
                }).onErrorReturn(new InterfaceC0454n<Throwable, CacheResult<T>>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.5.1
                    @Override // p014b4.InterfaceC0454n
                    public CacheResult<T> apply(Throwable th) {
                        C2073a.m2459d("save status => " + th);
                        return new CacheResult<>(false, t6);
                    }
                });
            }
        });
        return z6 ? abstractC2120l2.onErrorResumeNext(new InterfaceC0454n<Throwable, InterfaceC2125q<? extends CacheResult<T>>>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.6
            @Override // p014b4.InterfaceC0454n
            public InterfaceC2125q<? extends CacheResult<T>> apply(Throwable th) {
                return AbstractC2120l.empty();
            }
        }) : abstractC2120l2;
    }

    public <T> AbstractC2120l<CacheResult<T>> loadRemote2(final RxCache rxCache, final String str, AbstractC2120l<T> abstractC2120l, boolean z6) {
        AbstractC2120l<CacheResult<T>> abstractC2120l2 = (AbstractC2120l<CacheResult<T>>) abstractC2120l.map(new InterfaceC0454n<T, CacheResult<T>>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // p014b4.InterfaceC0454n
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((C06423<T>) obj);
            }

            @Override // p014b4.InterfaceC0454n
            public CacheResult<T> apply(T t6) {
                C2073a.m2459d("loadRemote result=" + t6);
                rxCache.save(str, t6).subscribeOn(C2012a.f5854b).subscribe(new InterfaceC0446f<Boolean>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.3.1
                    @Override // p014b4.InterfaceC0446f
                    public void accept(Boolean bool) {
                        C2073a.m2459d("save status => " + bool);
                    }
                }, new InterfaceC0446f<Throwable>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.3.2
                    @Override // p014b4.InterfaceC0446f
                    public void accept(Throwable th) {
                        if (th instanceof ConcurrentModificationException) {
                            C2073a.m2457b("Save failed, please use a synchronized cache strategy :", th);
                        } else {
                            C2073a.m2459d(th.getMessage());
                        }
                    }
                });
                return new CacheResult<>(false, t6);
            }
        });
        return z6 ? abstractC2120l2.onErrorResumeNext(new InterfaceC0454n<Throwable, InterfaceC2125q<? extends CacheResult<T>>>() { // from class: com.ctvit.network.cache.stategy.BaseStrategy.4
            @Override // p014b4.InterfaceC0454n
            public InterfaceC2125q<? extends CacheResult<T>> apply(Throwable th) {
                return AbstractC2120l.empty();
            }
        }) : abstractC2120l2;
    }
}
