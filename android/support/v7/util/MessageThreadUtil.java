package android.support.v7.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.util.ThreadUtil;
import android.support.v7.util.TileList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
class MessageThreadUtil<T> implements ThreadUtil<T> {

    /* renamed from: android.support.v7.util.MessageThreadUtil$1 */
    public class C03161 implements ThreadUtil.MainThreadCallback<T> {
        public static final int ADD_TILE = 2;
        public static final int REMOVE_TILE = 3;
        public static final int UPDATE_ITEM_COUNT = 1;
        public final /* synthetic */ ThreadUtil.MainThreadCallback val$callback;
        public final MessageQueue mQueue = new MessageQueue();
        private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
        private Runnable mMainThreadRunnable = new Runnable() { // from class: android.support.v7.util.MessageThreadUtil.1.1
            @Override // java.lang.Runnable
            public void run() {
                SyncQueueItem next = C03161.this.mQueue.next();
                while (next != null) {
                    int i7 = next.what;
                    if (i7 == 1) {
                        C03161.this.val$callback.updateItemCount(next.arg1, next.arg2);
                    } else if (i7 == 2) {
                        C03161.this.val$callback.addTile(next.arg1, (TileList.Tile) next.data);
                    } else if (i7 == 3) {
                        C03161.this.val$callback.removeTile(next.arg1, next.arg2);
                    }
                    next = C03161.this.mQueue.next();
                }
            }
        };

        public C03161(ThreadUtil.MainThreadCallback mainThreadCallback) {
            this.val$callback = mainThreadCallback;
        }

        private void sendMessage(SyncQueueItem syncQueueItem) {
            this.mQueue.sendMessage(syncQueueItem);
            this.mMainThreadHandler.post(this.mMainThreadRunnable);
        }

        @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
        public void addTile(int i7, TileList.Tile<T> tile) {
            sendMessage(SyncQueueItem.obtainMessage(2, i7, tile));
        }

        @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
        public void removeTile(int i7, int i8) {
            sendMessage(SyncQueueItem.obtainMessage(3, i7, i8));
        }

        @Override // android.support.v7.util.ThreadUtil.MainThreadCallback
        public void updateItemCount(int i7, int i8) {
            sendMessage(SyncQueueItem.obtainMessage(1, i7, i8));
        }
    }

    /* renamed from: android.support.v7.util.MessageThreadUtil$2 */
    public class C03172 implements ThreadUtil.BackgroundCallback<T> {
        public static final int LOAD_TILE = 3;
        public static final int RECYCLE_TILE = 4;
        public static final int REFRESH = 1;
        public static final int UPDATE_RANGE = 2;
        public final /* synthetic */ ThreadUtil.BackgroundCallback val$callback;
        public final MessageQueue mQueue = new MessageQueue();
        private final Executor mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
        public AtomicBoolean mBackgroundRunning = new AtomicBoolean(false);
        private Runnable mBackgroundRunnable = new Runnable() { // from class: android.support.v7.util.MessageThreadUtil.2.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    SyncQueueItem next = C03172.this.mQueue.next();
                    if (next == null) {
                        C03172.this.mBackgroundRunning.set(false);
                        return;
                    }
                    int i7 = next.what;
                    if (i7 == 1) {
                        C03172.this.mQueue.removeMessages(1);
                        C03172.this.val$callback.refresh(next.arg1);
                    } else if (i7 == 2) {
                        C03172.this.mQueue.removeMessages(2);
                        C03172.this.mQueue.removeMessages(3);
                        C03172.this.val$callback.updateRange(next.arg1, next.arg2, next.arg3, next.arg4, next.arg5);
                    } else if (i7 == 3) {
                        C03172.this.val$callback.loadTile(next.arg1, next.arg2);
                    } else if (i7 == 4) {
                        C03172.this.val$callback.recycleTile((TileList.Tile) next.data);
                    }
                }
            }
        };

        public C03172(ThreadUtil.BackgroundCallback backgroundCallback) {
            this.val$callback = backgroundCallback;
        }

        private void maybeExecuteBackgroundRunnable() {
            if (this.mBackgroundRunning.compareAndSet(false, true)) {
                this.mExecutor.execute(this.mBackgroundRunnable);
            }
        }

        private void sendMessage(SyncQueueItem syncQueueItem) {
            this.mQueue.sendMessage(syncQueueItem);
            maybeExecuteBackgroundRunnable();
        }

        private void sendMessageAtFrontOfQueue(SyncQueueItem syncQueueItem) {
            this.mQueue.sendMessageAtFrontOfQueue(syncQueueItem);
            maybeExecuteBackgroundRunnable();
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void loadTile(int i7, int i8) {
            sendMessage(SyncQueueItem.obtainMessage(3, i7, i8));
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void recycleTile(TileList.Tile<T> tile) {
            sendMessage(SyncQueueItem.obtainMessage(4, 0, tile));
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void refresh(int i7) {
            sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(1, i7, (Object) null));
        }

        @Override // android.support.v7.util.ThreadUtil.BackgroundCallback
        public void updateRange(int i7, int i8, int i9, int i10, int i11) {
            sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(2, i7, i8, i9, i10, i11, null));
        }
    }

    public static class MessageQueue {
        private SyncQueueItem mRoot;

        public synchronized SyncQueueItem next() {
            SyncQueueItem syncQueueItem = this.mRoot;
            if (syncQueueItem == null) {
                return null;
            }
            this.mRoot = syncQueueItem.next;
            return syncQueueItem;
        }

        public synchronized void removeMessages(int i7) {
            SyncQueueItem syncQueueItem;
            while (true) {
                syncQueueItem = this.mRoot;
                if (syncQueueItem == null || syncQueueItem.what != i7) {
                    break;
                }
                this.mRoot = syncQueueItem.next;
                syncQueueItem.recycle();
            }
            if (syncQueueItem != null) {
                SyncQueueItem syncQueueItem2 = syncQueueItem.next;
                while (syncQueueItem2 != null) {
                    SyncQueueItem syncQueueItem3 = syncQueueItem2.next;
                    if (syncQueueItem2.what == i7) {
                        syncQueueItem.next = syncQueueItem3;
                        syncQueueItem2.recycle();
                    } else {
                        syncQueueItem = syncQueueItem2;
                    }
                    syncQueueItem2 = syncQueueItem3;
                }
            }
        }

        public synchronized void sendMessage(SyncQueueItem syncQueueItem) {
            SyncQueueItem syncQueueItem2 = this.mRoot;
            if (syncQueueItem2 == null) {
                this.mRoot = syncQueueItem;
                return;
            }
            while (true) {
                SyncQueueItem syncQueueItem3 = syncQueueItem2.next;
                if (syncQueueItem3 == null) {
                    syncQueueItem2.next = syncQueueItem;
                    return;
                }
                syncQueueItem2 = syncQueueItem3;
            }
        }

        public synchronized void sendMessageAtFrontOfQueue(SyncQueueItem syncQueueItem) {
            syncQueueItem.next = this.mRoot;
            this.mRoot = syncQueueItem;
        }
    }

    @Override // android.support.v7.util.ThreadUtil
    public ThreadUtil.BackgroundCallback<T> getBackgroundProxy(ThreadUtil.BackgroundCallback<T> backgroundCallback) {
        return new C03172(backgroundCallback);
    }

    @Override // android.support.v7.util.ThreadUtil
    public ThreadUtil.MainThreadCallback<T> getMainThreadProxy(ThreadUtil.MainThreadCallback<T> mainThreadCallback) {
        return new C03161(mainThreadCallback);
    }

    public static class SyncQueueItem {
        private static SyncQueueItem sPool;
        private static final Object sPoolLock = new Object();
        public int arg1;
        public int arg2;
        public int arg3;
        public int arg4;
        public int arg5;
        public Object data;
        public SyncQueueItem next;
        public int what;

        public static SyncQueueItem obtainMessage(int i7, int i8, int i9, int i10, int i11, int i12, Object obj) {
            SyncQueueItem syncQueueItem;
            synchronized (sPoolLock) {
                syncQueueItem = sPool;
                if (syncQueueItem == null) {
                    syncQueueItem = new SyncQueueItem();
                } else {
                    sPool = syncQueueItem.next;
                    syncQueueItem.next = null;
                }
                syncQueueItem.what = i7;
                syncQueueItem.arg1 = i8;
                syncQueueItem.arg2 = i9;
                syncQueueItem.arg3 = i10;
                syncQueueItem.arg4 = i11;
                syncQueueItem.arg5 = i12;
                syncQueueItem.data = obj;
            }
            return syncQueueItem;
        }

        public void recycle() {
            this.next = null;
            this.arg5 = 0;
            this.arg4 = 0;
            this.arg3 = 0;
            this.arg2 = 0;
            this.arg1 = 0;
            this.what = 0;
            this.data = null;
            synchronized (sPoolLock) {
                SyncQueueItem syncQueueItem = sPool;
                if (syncQueueItem != null) {
                    this.next = syncQueueItem;
                }
                sPool = this;
            }
        }

        public static SyncQueueItem obtainMessage(int i7, int i8, int i9) {
            return obtainMessage(i7, i8, i9, 0, 0, 0, null);
        }

        public static SyncQueueItem obtainMessage(int i7, int i8, Object obj) {
            return obtainMessage(i7, i8, 0, 0, 0, 0, obj);
        }
    }
}
