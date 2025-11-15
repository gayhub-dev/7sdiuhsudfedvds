package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public final class Pools {

    public interface Pool<T> {
        @Nullable
        T acquire();

        boolean release(@NonNull T t6);
    }

    public static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int i7) {
            if (i7 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.mPool = new Object[i7];
        }

        private boolean isInPool(@NonNull T t6) {
            for (int i7 = 0; i7 < this.mPoolSize; i7++) {
                if (this.mPool[i7] == t6) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.support.v4.util.Pools.Pool
        public T acquire() {
            int i7 = this.mPoolSize;
            if (i7 <= 0) {
                return null;
            }
            int i8 = i7 - 1;
            Object[] objArr = this.mPool;
            T t6 = (T) objArr[i8];
            objArr[i8] = null;
            this.mPoolSize = i7 - 1;
            return t6;
        }

        @Override // android.support.v4.util.Pools.Pool
        public boolean release(@NonNull T t6) {
            if (isInPool(t6)) {
                throw new IllegalStateException("Already in the pool!");
            }
            int i7 = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (i7 >= objArr.length) {
                return false;
            }
            objArr[i7] = t6;
            this.mPoolSize = i7 + 1;
            return true;
        }
    }

    public static class SynchronizedPool<T> extends SimplePool<T> {
        private final Object mLock;

        public SynchronizedPool(int i7) {
            super(i7);
            this.mLock = new Object();
        }

        @Override // android.support.v4.util.Pools.SimplePool, android.support.v4.util.Pools.Pool
        public T acquire() {
            T t6;
            synchronized (this.mLock) {
                t6 = (T) super.acquire();
            }
            return t6;
        }

        @Override // android.support.v4.util.Pools.SimplePool, android.support.v4.util.Pools.Pool
        public boolean release(@NonNull T t6) {
            boolean zRelease;
            synchronized (this.mLock) {
                zRelease = super.release(t6);
            }
            return zRelease;
        }
    }

    private Pools() {
    }
}
