package com.google.gson.internal;

import java.util.Objects;

/* renamed from: com.google.gson.internal.$Gson$Preconditions, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$Gson$Preconditions {
    private C$Gson$Preconditions() {
        throw new UnsupportedOperationException();
    }

    public static void checkArgument(boolean z6) {
        if (!z6) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> T checkNotNull(T t6) {
        Objects.requireNonNull(t6);
        return t6;
    }
}
