package android.support.v7.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/* loaded from: classes.dex */
public final class AdapterListUpdateCallback implements ListUpdateCallback {

    @NonNull
    private final RecyclerView.Adapter mAdapter;

    public AdapterListUpdateCallback(@NonNull RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onChanged(int i7, int i8, Object obj) {
        this.mAdapter.notifyItemRangeChanged(i7, i8, obj);
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onInserted(int i7, int i8) {
        this.mAdapter.notifyItemRangeInserted(i7, i8);
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onMoved(int i7, int i8) {
        this.mAdapter.notifyItemMoved(i7, i8);
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onRemoved(int i7, int i8) {
        this.mAdapter.notifyItemRangeRemoved(i7, i8);
    }
}
