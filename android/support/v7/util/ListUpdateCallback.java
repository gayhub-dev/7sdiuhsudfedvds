package android.support.v7.util;

import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public interface ListUpdateCallback {
    void onChanged(int i7, int i8, @Nullable Object obj);

    void onInserted(int i7, int i8);

    void onMoved(int i7, int i8);

    void onRemoved(int i7, int i8);
}
