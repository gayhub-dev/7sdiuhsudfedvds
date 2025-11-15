package android.support.v4.app;

import android.arch.lifecycle.InterfaceC0054e;
import android.arch.lifecycle.InterfaceC0066q;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class LoaderManager {

    public interface LoaderCallbacks<D> {
        @NonNull
        @MainThread
        Loader<D> onCreateLoader(int i7, @Nullable Bundle bundle);

        @MainThread
        void onLoadFinished(@NonNull Loader<D> loader, D d7);

        @MainThread
        void onLoaderReset(@NonNull Loader<D> loader);
    }

    public static void enableDebugLogging(boolean z6) {
        LoaderManagerImpl.DEBUG = z6;
    }

    @NonNull
    public static <T extends InterfaceC0054e & InterfaceC0066q> LoaderManager getInstance(@NonNull T t6) {
        return new LoaderManagerImpl(t6, t6.getViewModelStore());
    }

    @MainThread
    public abstract void destroyLoader(int i7);

    @Deprecated
    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @Nullable
    public abstract <D> Loader<D> getLoader(int i7);

    public boolean hasRunningLoaders() {
        return false;
    }

    @NonNull
    @MainThread
    public abstract <D> Loader<D> initLoader(int i7, @Nullable Bundle bundle, @NonNull LoaderCallbacks<D> loaderCallbacks);

    public abstract void markForRedelivery();

    @NonNull
    @MainThread
    public abstract <D> Loader<D> restartLoader(int i7, @Nullable Bundle bundle, @NonNull LoaderCallbacks<D> loaderCallbacks);
}
