package android.support.v7.util;

import android.support.v7.util.TileList;

/* loaded from: classes.dex */
interface ThreadUtil<T> {

    public interface BackgroundCallback<T> {
        void loadTile(int i7, int i8);

        void recycleTile(TileList.Tile<T> tile);

        void refresh(int i7);

        void updateRange(int i7, int i8, int i9, int i10, int i11);
    }

    public interface MainThreadCallback<T> {
        void addTile(int i7, TileList.Tile<T> tile);

        void removeTile(int i7, int i8);

        void updateItemCount(int i7, int i8);
    }

    BackgroundCallback<T> getBackgroundProxy(BackgroundCallback<T> backgroundCallback);

    MainThreadCallback<T> getMainThreadProxy(MainThreadCallback<T> mainThreadCallback);
}
