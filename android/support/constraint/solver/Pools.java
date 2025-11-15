package android.support.constraint.solver;

/* loaded from: classes.dex */
final class Pools {
    private static final boolean DEBUG = false;

    public interface Pool<T> {
        T acquire();

        boolean release(T t6);

        void releaseAll(T[] tArr, int i7);
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

        private boolean isInPool(T t6) {
            for (int i7 = 0; i7 < this.mPoolSize; i7++) {
                if (this.mPool[i7] == t6) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.support.constraint.solver.Pools.Pool
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

        @Override // android.support.constraint.solver.Pools.Pool
        public boolean release(T t6) {
            int i7 = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (i7 >= objArr.length) {
                return false;
            }
            objArr[i7] = t6;
            this.mPoolSize = i7 + 1;
            return true;
        }

        @Override // android.support.constraint.solver.Pools.Pool
        public void releaseAll(T[] tArr, int i7) {
            if (i7 > tArr.length) {
                i7 = tArr.length;
            }
            for (int i8 = 0; i8 < i7; i8++) {
                T t6 = tArr[i8];
                int i9 = this.mPoolSize;
                Object[] objArr = this.mPool;
                if (i9 < objArr.length) {
                    objArr[i9] = t6;
                    this.mPoolSize = i9 + 1;
                }
            }
        }
    }

    private Pools() {
    }
}
