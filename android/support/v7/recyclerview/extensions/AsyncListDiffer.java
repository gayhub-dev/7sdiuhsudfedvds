package android.support.v7.recyclerview.extensions;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class AsyncListDiffer<T> {
    private static final Executor sMainThreadExecutor = new MainThreadExecutor();
    public final AsyncDifferConfig<T> mConfig;

    @Nullable
    private List<T> mList;
    public final Executor mMainThreadExecutor;
    public int mMaxScheduledGeneration;

    @NonNull
    private List<T> mReadOnlyList;
    private final ListUpdateCallback mUpdateCallback;

    public static class MainThreadExecutor implements Executor {
        public final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    public AsyncListDiffer(@NonNull RecyclerView.Adapter adapter, @NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this(new AdapterListUpdateCallback(adapter), new AsyncDifferConfig.Builder(itemCallback).build());
    }

    @NonNull
    public List<T> getCurrentList() {
        return this.mReadOnlyList;
    }

    public void latchList(@NonNull List<T> list, @NonNull DiffUtil.DiffResult diffResult) {
        this.mList = list;
        this.mReadOnlyList = Collections.unmodifiableList(list);
        diffResult.dispatchUpdatesTo(this.mUpdateCallback);
    }

    public void submitList(@Nullable final List<T> list) {
        final int i7 = this.mMaxScheduledGeneration + 1;
        this.mMaxScheduledGeneration = i7;
        final List<T> list2 = this.mList;
        if (list == list2) {
            return;
        }
        if (list == null) {
            int size = list2.size();
            this.mList = null;
            this.mReadOnlyList = Collections.emptyList();
            this.mUpdateCallback.onRemoved(0, size);
            return;
        }
        if (list2 != null) {
            this.mConfig.getBackgroundThreadExecutor().execute(new Runnable() { // from class: android.support.v7.recyclerview.extensions.AsyncListDiffer.1
                @Override // java.lang.Runnable
                public void run() {
                    final DiffUtil.DiffResult diffResultCalculateDiff = DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: android.support.v7.recyclerview.extensions.AsyncListDiffer.1.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // android.support.v7.util.DiffUtil.Callback
                        public boolean areContentsTheSame(int i8, int i9) {
                            Object obj = list2.get(i8);
                            Object obj2 = list.get(i9);
                            if (obj != null && obj2 != null) {
                                return AsyncListDiffer.this.mConfig.getDiffCallback().areContentsTheSame(obj, obj2);
                            }
                            if (obj == null && obj2 == null) {
                                return true;
                            }
                            throw new AssertionError();
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // android.support.v7.util.DiffUtil.Callback
                        public boolean areItemsTheSame(int i8, int i9) {
                            Object obj = list2.get(i8);
                            Object obj2 = list.get(i9);
                            return (obj == null || obj2 == null) ? obj == null && obj2 == null : AsyncListDiffer.this.mConfig.getDiffCallback().areItemsTheSame(obj, obj2);
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // android.support.v7.util.DiffUtil.Callback
                        @Nullable
                        public Object getChangePayload(int i8, int i9) {
                            Object obj = list2.get(i8);
                            Object obj2 = list.get(i9);
                            if (obj == null || obj2 == null) {
                                throw new AssertionError();
                            }
                            return AsyncListDiffer.this.mConfig.getDiffCallback().getChangePayload(obj, obj2);
                        }

                        @Override // android.support.v7.util.DiffUtil.Callback
                        public int getNewListSize() {
                            return list.size();
                        }

                        @Override // android.support.v7.util.DiffUtil.Callback
                        public int getOldListSize() {
                            return list2.size();
                        }
                    });
                    AsyncListDiffer.this.mMainThreadExecutor.execute(new Runnable() { // from class: android.support.v7.recyclerview.extensions.AsyncListDiffer.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            RunnableC03121 runnableC03121 = RunnableC03121.this;
                            AsyncListDiffer asyncListDiffer = AsyncListDiffer.this;
                            if (asyncListDiffer.mMaxScheduledGeneration == i7) {
                                asyncListDiffer.latchList(list, diffResultCalculateDiff);
                            }
                        }
                    });
                }
            });
            return;
        }
        this.mList = list;
        this.mReadOnlyList = Collections.unmodifiableList(list);
        this.mUpdateCallback.onInserted(0, list.size());
    }

    public AsyncListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mReadOnlyList = Collections.emptyList();
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig = asyncDifferConfig;
        if (asyncDifferConfig.getMainThreadExecutor() != null) {
            this.mMainThreadExecutor = asyncDifferConfig.getMainThreadExecutor();
        } else {
            this.mMainThreadExecutor = sMainThreadExecutor;
        }
    }
}
