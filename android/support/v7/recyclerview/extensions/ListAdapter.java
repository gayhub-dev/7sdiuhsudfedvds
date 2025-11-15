package android.support.v7.recyclerview.extensions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final AsyncListDiffer<T> mHelper;

    public ListAdapter(@NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this.mHelper = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), new AsyncDifferConfig.Builder(itemCallback).build());
    }

    public T getItem(int i7) {
        return this.mHelper.getCurrentList().get(i7);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mHelper.getCurrentList().size();
    }

    public void submitList(@Nullable List<T> list) {
        this.mHelper.submitList(list);
    }

    public ListAdapter(@NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mHelper = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), asyncDifferConfig);
    }
}
