package android.support.v7.widget.util;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class SortedListAdapterCallback<T2> extends SortedList.Callback<T2> {
    public final RecyclerView.Adapter mAdapter;

    public SortedListAdapterCallback(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override // android.support.v7.util.SortedList.Callback
    public void onChanged(int i7, int i8) {
        this.mAdapter.notifyItemRangeChanged(i7, i8);
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

    @Override // android.support.v7.util.SortedList.Callback, android.support.v7.util.ListUpdateCallback
    public void onChanged(int i7, int i8, Object obj) {
        this.mAdapter.notifyItemRangeChanged(i7, i8, obj);
    }
}
