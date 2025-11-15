package com.ctvit.network.model;

import java.util.Objects;
import p194y3.AbstractC2120l;

/* loaded from: classes.dex */
public class Optional<T> {
    public AbstractC2120l<T> obs;

    public Optional(AbstractC2120l<T> abstractC2120l) {
        this.obs = abstractC2120l;
    }

    /* renamed from: of */
    public static <T> Optional<T> m528of(T t6) {
        Objects.requireNonNull(t6);
        return new Optional<>(AbstractC2120l.just(t6));
    }

    public static <T> Optional<T> ofNullable(T t6) {
        return t6 == null ? new Optional<>(AbstractC2120l.empty()) : new Optional<>(AbstractC2120l.just(t6));
    }

    public T get() {
        return this.obs.blockingSingle();
    }

    public T orElse(T t6) {
        return this.obs.defaultIfEmpty(t6).blockingSingle();
    }
}
