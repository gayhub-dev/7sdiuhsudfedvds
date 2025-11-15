package android.support.v4.app;

import android.arch.lifecycle.AbstractC0062m;
import android.arch.lifecycle.C0059j;
import android.arch.lifecycle.C0063n;
import android.arch.lifecycle.C0065p;
import android.arch.lifecycle.InterfaceC0054e;
import android.arch.lifecycle.InterfaceC0060k;
import android.arch.lifecycle.InterfaceC0064o;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import p009b.C0413b;

/* loaded from: classes.dex */
class LoaderManagerImpl extends LoaderManager {
    public static boolean DEBUG = false;
    public static final String TAG = "LoaderManager";

    @NonNull
    private final InterfaceC0054e mLifecycleOwner;

    @NonNull
    private final LoaderViewModel mLoaderViewModel;

    public static class LoaderInfo<D> extends C0059j<D> implements Loader.OnLoadCompleteListener<D> {

        @Nullable
        private final Bundle mArgs;
        private final int mId;
        private InterfaceC0054e mLifecycleOwner;

        @NonNull
        private final Loader<D> mLoader;
        private LoaderObserver<D> mObserver;
        private Loader<D> mPriorLoader;

        public LoaderInfo(int i7, @Nullable Bundle bundle, @NonNull Loader<D> loader, @Nullable Loader<D> loader2) {
            this.mId = i7;
            this.mArgs = bundle;
            this.mLoader = loader;
            this.mPriorLoader = loader2;
            loader.registerListener(i7, this);
        }

        @MainThread
        public Loader<D> destroy(boolean z6) {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Destroying: ");
                sb.append(this);
            }
            this.mLoader.cancelLoad();
            this.mLoader.abandon();
            LoaderObserver<D> loaderObserver = this.mObserver;
            if (loaderObserver != null) {
                removeObserver(loaderObserver);
                if (z6) {
                    loaderObserver.reset();
                }
            }
            this.mLoader.unregisterListener(this);
            if ((loaderObserver == null || loaderObserver.hasDeliveredData()) && !z6) {
                return this.mLoader;
            }
            this.mLoader.reset();
            return this.mPriorLoader;
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.mId);
            printWriter.print(" mArgs=");
            printWriter.println(this.mArgs);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.mLoader);
            this.mLoader.dump(C0063n.m88a(str, "  "), fileDescriptor, printWriter, strArr);
            if (this.mObserver != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.mObserver);
                this.mObserver.dump(C0063n.m88a(str, "  "), printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(getLoader().dataToString(getValue()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(hasActiveObservers());
        }

        @NonNull
        public Loader<D> getLoader() {
            return this.mLoader;
        }

        public boolean isCallbackWaitingForData() {
            LoaderObserver<D> loaderObserver;
            return (!hasActiveObservers() || (loaderObserver = this.mObserver) == null || loaderObserver.hasDeliveredData()) ? false : true;
        }

        public void markForRedelivery() {
            InterfaceC0054e interfaceC0054e = this.mLifecycleOwner;
            LoaderObserver<D> loaderObserver = this.mObserver;
            if (interfaceC0054e == null || loaderObserver == null) {
                return;
            }
            super.removeObserver(loaderObserver);
            observe(interfaceC0054e, loaderObserver);
        }

        @Override // android.arch.lifecycle.LiveData
        public void onActive() {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Starting: ");
                sb.append(this);
            }
            this.mLoader.startLoading();
        }

        @Override // android.arch.lifecycle.LiveData
        public void onInactive() {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Stopping: ");
                sb.append(this);
            }
            this.mLoader.stopLoading();
        }

        @Override // android.support.v4.content.Loader.OnLoadCompleteListener
        public void onLoadComplete(@NonNull Loader<D> loader, @Nullable D d7) {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("onLoadComplete: ");
                sb.append(this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(d7);
            } else {
                boolean z6 = LoaderManagerImpl.DEBUG;
                postValue(d7);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.arch.lifecycle.LiveData
        public void removeObserver(@NonNull InterfaceC0060k<? super D> interfaceC0060k) {
            super.removeObserver(interfaceC0060k);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        @NonNull
        @MainThread
        public Loader<D> setCallback(@NonNull InterfaceC0054e interfaceC0054e, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            LoaderObserver<D> loaderObserver = new LoaderObserver<>(this.mLoader, loaderCallbacks);
            observe(interfaceC0054e, loaderObserver);
            LoaderObserver<D> loaderObserver2 = this.mObserver;
            if (loaderObserver2 != null) {
                removeObserver(loaderObserver2);
            }
            this.mLifecycleOwner = interfaceC0054e;
            this.mObserver = loaderObserver;
            return this.mLoader;
        }

        @Override // android.arch.lifecycle.C0059j, android.arch.lifecycle.LiveData
        public void setValue(D d7) {
            super.setValue(d7);
            Loader<D> loader = this.mPriorLoader;
            if (loader != null) {
                loader.reset();
                this.mPriorLoader = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.mId);
            sb.append(" : ");
            DebugUtils.buildShortClassTag(this.mLoader, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    public static class LoaderObserver<D> implements InterfaceC0060k<D> {

        @NonNull
        private final LoaderManager.LoaderCallbacks<D> mCallback;
        private boolean mDeliveredData = false;

        @NonNull
        private final Loader<D> mLoader;

        public LoaderObserver(@NonNull Loader<D> loader, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            this.mLoader = loader;
            this.mCallback = loaderCallbacks;
        }

        public void dump(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.mDeliveredData);
        }

        public boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        @Override // android.arch.lifecycle.InterfaceC0060k
        public void onChanged(@Nullable D d7) {
            if (LoaderManagerImpl.DEBUG) {
                StringBuilder sbM112a = C0413b.m112a("  onLoadFinished in ");
                sbM112a.append(this.mLoader);
                sbM112a.append(": ");
                sbM112a.append(this.mLoader.dataToString(d7));
            }
            this.mCallback.onLoadFinished(this.mLoader, d7);
            this.mDeliveredData = true;
        }

        @MainThread
        public void reset() {
            if (this.mDeliveredData) {
                if (LoaderManagerImpl.DEBUG) {
                    C0413b.m112a("  Resetting: ").append(this.mLoader);
                }
                this.mCallback.onLoaderReset(this.mLoader);
            }
        }

        public String toString() {
            return this.mCallback.toString();
        }
    }

    public static class LoaderViewModel extends AbstractC0062m {
        private static final InterfaceC0064o FACTORY = new InterfaceC0064o() { // from class: android.support.v4.app.LoaderManagerImpl.LoaderViewModel.1
            @Override // android.arch.lifecycle.InterfaceC0064o
            @NonNull
            public <T extends AbstractC0062m> T create(@NonNull Class<T> cls) {
                return new LoaderViewModel();
            }
        };
        private SparseArrayCompat<LoaderInfo> mLoaders = new SparseArrayCompat<>();
        private boolean mCreatingLoader = false;

        @NonNull
        public static LoaderViewModel getInstance(C0065p c0065p) {
            AbstractC0062m abstractC0062mPut;
            InterfaceC0064o interfaceC0064o = FACTORY;
            String canonicalName = LoaderViewModel.class.getCanonicalName();
            if (canonicalName == null) {
                throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
            }
            String strM88a = C0063n.m88a("android.arch.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            AbstractC0062m abstractC0062mCreate = c0065p.f108a.get(strM88a);
            if (!LoaderViewModel.class.isInstance(abstractC0062mCreate) && (abstractC0062mPut = c0065p.f108a.put(strM88a, (abstractC0062mCreate = interfaceC0064o.create(LoaderViewModel.class)))) != null) {
                abstractC0062mPut.onCleared();
            }
            return (LoaderViewModel) abstractC0062mCreate;
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.mLoaders.size() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i7 = 0; i7 < this.mLoaders.size(); i7++) {
                    LoaderInfo loaderInfoValueAt = this.mLoaders.valueAt(i7);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.mLoaders.keyAt(i7));
                    printWriter.print(": ");
                    printWriter.println(loaderInfoValueAt.toString());
                    loaderInfoValueAt.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        public void finishCreatingLoader() {
            this.mCreatingLoader = false;
        }

        public <D> LoaderInfo<D> getLoader(int i7) {
            return this.mLoaders.get(i7);
        }

        public boolean hasRunningLoaders() {
            int size = this.mLoaders.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (this.mLoaders.valueAt(i7).isCallbackWaitingForData()) {
                    return true;
                }
            }
            return false;
        }

        public boolean isCreatingLoader() {
            return this.mCreatingLoader;
        }

        public void markForRedelivery() {
            int size = this.mLoaders.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.mLoaders.valueAt(i7).markForRedelivery();
            }
        }

        @Override // android.arch.lifecycle.AbstractC0062m
        public void onCleared() {
            super.onCleared();
            int size = this.mLoaders.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.mLoaders.valueAt(i7).destroy(true);
            }
            this.mLoaders.clear();
        }

        public void putLoader(int i7, @NonNull LoaderInfo loaderInfo) {
            this.mLoaders.put(i7, loaderInfo);
        }

        public void removeLoader(int i7) {
            this.mLoaders.remove(i7);
        }

        public void startCreatingLoader() {
            this.mCreatingLoader = true;
        }
    }

    public LoaderManagerImpl(@NonNull InterfaceC0054e interfaceC0054e, @NonNull C0065p c0065p) {
        this.mLifecycleOwner = interfaceC0054e;
        this.mLoaderViewModel = LoaderViewModel.getInstance(c0065p);
    }

    @NonNull
    @MainThread
    private <D> Loader<D> createAndInstallLoader(int i7, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks, @Nullable Loader<D> loader) {
        try {
            this.mLoaderViewModel.startCreatingLoader();
            Loader<D> loaderOnCreateLoader = loaderCallbacks.onCreateLoader(i7, bundle);
            if (loaderOnCreateLoader == null) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
            }
            if (loaderOnCreateLoader.getClass().isMemberClass() && !Modifier.isStatic(loaderOnCreateLoader.getClass().getModifiers())) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + loaderOnCreateLoader);
            }
            LoaderInfo loaderInfo = new LoaderInfo(i7, bundle, loaderOnCreateLoader, loader);
            if (DEBUG) {
                StringBuilder sb = new StringBuilder();
                sb.append("  Created new loader ");
                sb.append(loaderInfo);
            }
            this.mLoaderViewModel.putLoader(i7, loaderInfo);
            this.mLoaderViewModel.finishCreatingLoader();
            return loaderInfo.setCallback(this.mLifecycleOwner, loaderCallbacks);
        } catch (Throwable th) {
            this.mLoaderViewModel.finishCreatingLoader();
            throw th;
        }
    }

    @Override // android.support.v4.app.LoaderManager
    @MainThread
    public void destroyLoader(int i7) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("destroyLoader in ");
            sb.append(this);
            sb.append(" of ");
            sb.append(i7);
        }
        LoaderInfo loader = this.mLoaderViewModel.getLoader(i7);
        if (loader != null) {
            loader.destroy(true);
            this.mLoaderViewModel.removeLoader(i7);
        }
    }

    @Override // android.support.v4.app.LoaderManager
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mLoaderViewModel.dump(str, fileDescriptor, printWriter, strArr);
    }

    @Override // android.support.v4.app.LoaderManager
    @Nullable
    public <D> Loader<D> getLoader(int i7) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        LoaderInfo<D> loader = this.mLoaderViewModel.getLoader(i7);
        if (loader != null) {
            return loader.getLoader();
        }
        return null;
    }

    @Override // android.support.v4.app.LoaderManager
    public boolean hasRunningLoaders() {
        return this.mLoaderViewModel.hasRunningLoaders();
    }

    @Override // android.support.v4.app.LoaderManager
    @NonNull
    @MainThread
    public <D> Loader<D> initLoader(int i7, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        LoaderInfo<D> loader = this.mLoaderViewModel.getLoader(i7);
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("initLoader in ");
            sb.append(this);
            sb.append(": args=");
            sb.append(bundle);
        }
        if (loader == null) {
            return createAndInstallLoader(i7, bundle, loaderCallbacks, null);
        }
        if (DEBUG) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  Re-using existing loader ");
            sb2.append(loader);
        }
        return loader.setCallback(this.mLifecycleOwner, loaderCallbacks);
    }

    @Override // android.support.v4.app.LoaderManager
    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    @Override // android.support.v4.app.LoaderManager
    @NonNull
    @MainThread
    public <D> Loader<D> restartLoader(int i7, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("restartLoader must be called on the main thread");
        }
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("restartLoader in ");
            sb.append(this);
            sb.append(": args=");
            sb.append(bundle);
        }
        LoaderInfo<D> loader = this.mLoaderViewModel.getLoader(i7);
        return createAndInstallLoader(i7, bundle, loaderCallbacks, loader != null ? loader.destroy(false) : null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        DebugUtils.buildShortClassTag(this.mLifecycleOwner, sb);
        sb.append("}}");
        return sb.toString();
    }
}
