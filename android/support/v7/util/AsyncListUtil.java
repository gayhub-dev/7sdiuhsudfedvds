package android.support.v7.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.util.ThreadUtil;
import android.support.v7.util.TileList;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

/* loaded from: classes.dex */
public class AsyncListUtil<T> {
    public static final boolean DEBUG = false;
    public static final String TAG = "AsyncListUtil";
    public boolean mAllowScrollHints;
    private final ThreadUtil.BackgroundCallback<T> mBackgroundCallback;
    public final ThreadUtil.BackgroundCallback<T> mBackgroundProxy;
    public final DataCallback<T> mDataCallback;
    private final ThreadUtil.MainThreadCallback<T> mMainThreadCallback;
    public final ThreadUtil.MainThreadCallback<T> mMainThreadProxy;
    public final Class<T> mTClass;
    public final TileList<T> mTileList;
    public final int mTileSize;
    public final ViewCallback mViewCallback;
    public final int[] mTmpRange = new int[2];
    public final int[] mPrevRange = new int[2];
    public final int[] mTmpRangeExtended = new int[2];
    private int mScrollHint = 0;
    public int mItemCount = 0;
    public int mDisplayedGeneration = 0;
    public int mRequestedGeneration = 0;
    public final SparseIntArray mMissingPositions = new SparseIntArray();

    public static abstract class DataCallback<T> {
        @WorkerThread
        public abstract void fillData(@NonNull T[] tArr, int i7, int i8);

        @WorkerThread
        public int getMaxCachedTiles() {
            return 10;
        }

        @WorkerThread
        public void recycleData(@NonNull T[] tArr, int i7) {
        }

        @WorkerThread
        public abstract int refreshData();
    }

    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE = 0;

        @UiThread
        public void extendRangeInto(@NonNull int[] iArr, @NonNull int[] iArr2, int i7) {
            int i8 = (iArr[1] - iArr[0]) + 1;
            int i9 = i8 / 2;
            iArr2[0] = iArr[0] - (i7 == 1 ? i8 : i9);
            int i10 = iArr[1];
            if (i7 != 2) {
                i8 = i9;
            }
            iArr2[1] = i10 + i8;
        }

        @UiThread
        public abstract void getItemRangeInto(@NonNull int[] iArr);

        @UiThread
        public abstract void onDataRefresh();

        @UiThread
        public abstract void onItemLoaded(int i7);
    }

    public AsyncListUtil(@NonNull Class<T> cls, int i7, @NonNull DataCallback<T> dataCallback, @NonNull ViewCallback viewCallback) {
        ThreadUtil.MainThreadCallback<T> mainThreadCallback = (ThreadUtil.MainThreadCallback<T>) new ThreadUtil.MainThreadCallback<Object>() { // from class: android.support.v7.util.AsyncListUtil.1
            private boolean isRequestedGeneration(int i8) {
                return i8 == AsyncListUtil.this.mRequestedGeneration;
            }

            private void recycleAllTiles() {
                for (int i8 = 0; i8 < AsyncListUtil.this.mTileList.size(); i8++) {
                    AsyncListUtil asyncListUtil = AsyncListUtil.this;
                    asyncListUtil.mBackgroundProxy.recycleTile(asyncListUtil.mTileList.getAtIndex(i8));
                }
                AsyncListUtil.this.mTileList.clear();
            }

            @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
            public void addTile(int i8, TileList.Tile<Object> tile) {
                if (!isRequestedGeneration(i8)) {
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(tile);
                    return;
                }
                TileList.Tile<T> tileAddOrReplace = AsyncListUtil.this.mTileList.addOrReplace(tile);
                if (tileAddOrReplace != null) {
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(tileAddOrReplace);
                }
                int i9 = tile.mStartPosition + tile.mItemCount;
                int i10 = 0;
                while (i10 < AsyncListUtil.this.mMissingPositions.size()) {
                    int iKeyAt = AsyncListUtil.this.mMissingPositions.keyAt(i10);
                    if (tile.mStartPosition > iKeyAt || iKeyAt >= i9) {
                        i10++;
                    } else {
                        AsyncListUtil.this.mMissingPositions.removeAt(i10);
                        AsyncListUtil.this.mViewCallback.onItemLoaded(iKeyAt);
                    }
                }
            }

            @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
            public void removeTile(int i8, int i9) {
                TileList.Tile<T> tileRemoveAtPos;
                if (isRequestedGeneration(i8) && (tileRemoveAtPos = AsyncListUtil.this.mTileList.removeAtPos(i9)) != null) {
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(tileRemoveAtPos);
                }
            }

            @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
            public void updateItemCount(int i8, int i9) {
                if (isRequestedGeneration(i8)) {
                    AsyncListUtil asyncListUtil = AsyncListUtil.this;
                    asyncListUtil.mItemCount = i9;
                    asyncListUtil.mViewCallback.onDataRefresh();
                    AsyncListUtil asyncListUtil2 = AsyncListUtil.this;
                    asyncListUtil2.mDisplayedGeneration = asyncListUtil2.mRequestedGeneration;
                    recycleAllTiles();
                    AsyncListUtil asyncListUtil3 = AsyncListUtil.this;
                    asyncListUtil3.mAllowScrollHints = false;
                    asyncListUtil3.updateRange();
                }
            }
        };
        this.mMainThreadCallback = mainThreadCallback;
        ThreadUtil.BackgroundCallback<T> backgroundCallback = (ThreadUtil.BackgroundCallback<T>) new ThreadUtil.BackgroundCallback<Object>() { // from class: android.support.v7.util.AsyncListUtil.2
            private int mFirstRequiredTileStart;
            private int mGeneration;
            private int mItemCount;
            private int mLastRequiredTileStart;
            public final SparseBooleanArray mLoadedTiles = new SparseBooleanArray();
            private TileList.Tile<Object> mRecycledRoot;

            private TileList.Tile<Object> acquireTile() {
                TileList.Tile<Object> tile = this.mRecycledRoot;
                if (tile != null) {
                    this.mRecycledRoot = tile.mNext;
                    return tile;
                }
                AsyncListUtil asyncListUtil = AsyncListUtil.this;
                return new TileList.Tile<>(asyncListUtil.mTClass, asyncListUtil.mTileSize);
            }

            private void addTile(TileList.Tile<Object> tile) {
                this.mLoadedTiles.put(tile.mStartPosition, true);
                AsyncListUtil.this.mMainThreadProxy.addTile(this.mGeneration, tile);
            }

            private void flushTileCache(int i8) {
                int maxCachedTiles = AsyncListUtil.this.mDataCallback.getMaxCachedTiles();
                while (this.mLoadedTiles.size() >= maxCachedTiles) {
                    int iKeyAt = this.mLoadedTiles.keyAt(0);
                    SparseBooleanArray sparseBooleanArray = this.mLoadedTiles;
                    int iKeyAt2 = sparseBooleanArray.keyAt(sparseBooleanArray.size() - 1);
                    int i9 = this.mFirstRequiredTileStart - iKeyAt;
                    int i10 = iKeyAt2 - this.mLastRequiredTileStart;
                    if (i9 > 0 && (i9 >= i10 || i8 == 2)) {
                        removeTile(iKeyAt);
                    } else {
                        if (i10 <= 0) {
                            return;
                        }
                        if (i9 >= i10 && i8 != 1) {
                            return;
                        } else {
                            removeTile(iKeyAt2);
                        }
                    }
                }
            }

            private int getTileStart(int i8) {
                return i8 - (i8 % AsyncListUtil.this.mTileSize);
            }

            private boolean isTileLoaded(int i8) {
                return this.mLoadedTiles.get(i8);
            }

            private void log(String str, Object... objArr) {
                String.format(str, objArr);
            }

            private void removeTile(int i8) {
                this.mLoadedTiles.delete(i8);
                AsyncListUtil.this.mMainThreadProxy.removeTile(this.mGeneration, i8);
            }

            private void requestTiles(int i8, int i9, int i10, boolean z6) {
                int i11 = i8;
                while (i11 <= i9) {
                    AsyncListUtil.this.mBackgroundProxy.loadTile(z6 ? (i9 + i8) - i11 : i11, i10);
                    i11 += AsyncListUtil.this.mTileSize;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
            public void loadTile(int i8, int i9) {
                if (isTileLoaded(i8)) {
                    return;
                }
                TileList.Tile<Object> tileAcquireTile = acquireTile();
                tileAcquireTile.mStartPosition = i8;
                int iMin = Math.min(AsyncListUtil.this.mTileSize, this.mItemCount - i8);
                tileAcquireTile.mItemCount = iMin;
                AsyncListUtil.this.mDataCallback.fillData(tileAcquireTile.mItems, tileAcquireTile.mStartPosition, iMin);
                flushTileCache(i9);
                addTile(tileAcquireTile);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
            public void recycleTile(TileList.Tile<Object> tile) {
                AsyncListUtil.this.mDataCallback.recycleData(tile.mItems, tile.mItemCount);
                tile.mNext = (TileList.Tile<T>) this.mRecycledRoot;
                this.mRecycledRoot = tile;
            }

            @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
            public void refresh(int i8) {
                this.mGeneration = i8;
                this.mLoadedTiles.clear();
                int iRefreshData = AsyncListUtil.this.mDataCallback.refreshData();
                this.mItemCount = iRefreshData;
                AsyncListUtil.this.mMainThreadProxy.updateItemCount(this.mGeneration, iRefreshData);
            }

            @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
            public void updateRange(int i8, int i9, int i10, int i11, int i12) {
                if (i8 > i9) {
                    return;
                }
                int tileStart = getTileStart(i8);
                int tileStart2 = getTileStart(i9);
                this.mFirstRequiredTileStart = getTileStart(i10);
                int tileStart3 = getTileStart(i11);
                this.mLastRequiredTileStart = tileStart3;
                if (i12 == 1) {
                    requestTiles(this.mFirstRequiredTileStart, tileStart2, i12, true);
                    requestTiles(tileStart2 + AsyncListUtil.this.mTileSize, this.mLastRequiredTileStart, i12, false);
                } else {
                    requestTiles(tileStart, tileStart3, i12, false);
                    requestTiles(this.mFirstRequiredTileStart, tileStart - AsyncListUtil.this.mTileSize, i12, true);
                }
            }
        };
        this.mBackgroundCallback = backgroundCallback;
        this.mTClass = cls;
        this.mTileSize = i7;
        this.mDataCallback = dataCallback;
        this.mViewCallback = viewCallback;
        this.mTileList = new TileList<>(i7);
        MessageThreadUtil messageThreadUtil = new MessageThreadUtil();
        this.mMainThreadProxy = messageThreadUtil.getMainThreadProxy(mainThreadCallback);
        this.mBackgroundProxy = messageThreadUtil.getBackgroundProxy(backgroundCallback);
        refresh();
    }

    private boolean isRefreshPending() {
        return this.mRequestedGeneration != this.mDisplayedGeneration;
    }

    @Nullable
    public T getItem(int i7) {
        if (i7 < 0 || i7 >= this.mItemCount) {
            throw new IndexOutOfBoundsException(i7 + " is not within 0 and " + this.mItemCount);
        }
        T itemAt = this.mTileList.getItemAt(i7);
        if (itemAt == null && !isRefreshPending()) {
            this.mMissingPositions.put(i7, 0);
        }
        return itemAt;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    public void log(String str, Object... objArr) {
        String.format(str, objArr);
    }

    public void onRangeChanged() {
        if (isRefreshPending()) {
            return;
        }
        updateRange();
        this.mAllowScrollHints = true;
    }

    public void refresh() {
        this.mMissingPositions.clear();
        ThreadUtil.BackgroundCallback<T> backgroundCallback = this.mBackgroundProxy;
        int i7 = this.mRequestedGeneration + 1;
        this.mRequestedGeneration = i7;
        backgroundCallback.refresh(i7);
    }

    public void updateRange() {
        this.mViewCallback.getItemRangeInto(this.mTmpRange);
        int[] iArr = this.mTmpRange;
        if (iArr[0] > iArr[1] || iArr[0] < 0 || iArr[1] >= this.mItemCount) {
            return;
        }
        if (this.mAllowScrollHints) {
            int i7 = iArr[0];
            int[] iArr2 = this.mPrevRange;
            if (i7 > iArr2[1] || iArr2[0] > iArr[1]) {
                this.mScrollHint = 0;
            } else if (iArr[0] < iArr2[0]) {
                this.mScrollHint = 1;
            } else if (iArr[0] > iArr2[0]) {
                this.mScrollHint = 2;
            }
        } else {
            this.mScrollHint = 0;
        }
        int[] iArr3 = this.mPrevRange;
        iArr3[0] = iArr[0];
        iArr3[1] = iArr[1];
        this.mViewCallback.extendRangeInto(iArr, this.mTmpRangeExtended, this.mScrollHint);
        int[] iArr4 = this.mTmpRangeExtended;
        iArr4[0] = Math.min(this.mTmpRange[0], Math.max(iArr4[0], 0));
        int[] iArr5 = this.mTmpRangeExtended;
        iArr5[1] = Math.max(this.mTmpRange[1], Math.min(iArr5[1], this.mItemCount - 1));
        ThreadUtil.BackgroundCallback<T> backgroundCallback = this.mBackgroundProxy;
        int[] iArr6 = this.mTmpRange;
        int i8 = iArr6[0];
        int i9 = iArr6[1];
        int[] iArr7 = this.mTmpRangeExtended;
        backgroundCallback.updateRange(i8, i9, iArr7[0], iArr7[1], this.mScrollHint);
    }
}
