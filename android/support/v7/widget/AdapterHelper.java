package android.support.v7.widget;

import android.support.v4.util.Pools;
import android.support.v7.widget.OpReorderer;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
class AdapterHelper implements OpReorderer.Callback {
    private static final boolean DEBUG = false;
    public static final int POSITION_TYPE_INVISIBLE = 0;
    public static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
    private static final String TAG = "AHT";
    public final Callback mCallback;
    public final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    public Runnable mOnItemProcessedCallback;
    public final OpReorderer mOpReorderer;
    public final ArrayList<UpdateOp> mPendingUpdates;
    public final ArrayList<UpdateOp> mPostponedList;
    private Pools.Pool<UpdateOp> mUpdateOpPool;

    public interface Callback {
        RecyclerView.ViewHolder findViewHolder(int i7);

        void markViewHoldersUpdated(int i7, int i8, Object obj);

        void offsetPositionsForAdd(int i7, int i8);

        void offsetPositionsForMove(int i7, int i8);

        void offsetPositionsForRemovingInvisible(int i7, int i8);

        void offsetPositionsForRemovingLaidOutOrNewView(int i7, int i8);

        void onDispatchFirstPass(UpdateOp updateOp);

        void onDispatchSecondPass(UpdateOp updateOp);
    }

    public static class UpdateOp {
        public static final int ADD = 1;
        public static final int MOVE = 8;
        public static final int POOL_SIZE = 30;
        public static final int REMOVE = 2;
        public static final int UPDATE = 4;
        public int cmd;
        public int itemCount;
        public Object payload;
        public int positionStart;

        public UpdateOp(int i7, int i8, int i9, Object obj) {
            this.cmd = i7;
            this.positionStart = i8;
            this.itemCount = i9;
            this.payload = obj;
        }

        public String cmdToString() {
            int i7 = this.cmd;
            return i7 != 1 ? i7 != 2 ? i7 != 4 ? i7 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UpdateOp updateOp = (UpdateOp) obj;
            int i7 = this.cmd;
            if (i7 != updateOp.cmd) {
                return false;
            }
            if (i7 == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == updateOp.positionStart && this.positionStart == updateOp.itemCount) {
                return true;
            }
            if (this.itemCount != updateOp.itemCount || this.positionStart != updateOp.positionStart) {
                return false;
            }
            Object obj2 = this.payload;
            if (obj2 != null) {
                if (!obj2.equals(updateOp.payload)) {
                    return false;
                }
            } else if (updateOp.payload != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
        }
    }

    public AdapterHelper(Callback callback) {
        this(callback, false);
    }

    private void applyAdd(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyMove(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyRemove(UpdateOp updateOp) {
        boolean z6;
        char c7;
        int i7 = updateOp.positionStart;
        int i8 = updateOp.itemCount + i7;
        char c8 = 65535;
        int i9 = i7;
        int i10 = 0;
        while (i9 < i8) {
            if (this.mCallback.findViewHolder(i9) != null || canFindInPreLayout(i9)) {
                if (c8 == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, i7, i10, null));
                    z6 = true;
                } else {
                    z6 = false;
                }
                c7 = 1;
            } else {
                if (c8 == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, i7, i10, null));
                    z6 = true;
                } else {
                    z6 = false;
                }
                c7 = 0;
            }
            if (z6) {
                i9 -= i10;
                i8 -= i10;
                i10 = 1;
            } else {
                i10++;
            }
            i9++;
            c8 = c7;
        }
        if (i10 != updateOp.itemCount) {
            recycleUpdateOp(updateOp);
            updateOp = obtainUpdateOp(2, i7, i10, null);
        }
        if (c8 == 0) {
            dispatchAndUpdateViewHolders(updateOp);
        } else {
            postponeAndUpdateViewHolders(updateOp);
        }
    }

    private void applyUpdate(UpdateOp updateOp) {
        int i7 = updateOp.positionStart;
        int i8 = updateOp.itemCount + i7;
        int i9 = i7;
        char c7 = 65535;
        int i10 = 0;
        while (i7 < i8) {
            if (this.mCallback.findViewHolder(i7) != null || canFindInPreLayout(i7)) {
                if (c7 == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, i9, i10, updateOp.payload));
                    i9 = i7;
                    i10 = 0;
                }
                c7 = 1;
            } else {
                if (c7 == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, i9, i10, updateOp.payload));
                    i9 = i7;
                    i10 = 0;
                }
                c7 = 0;
            }
            i10++;
            i7++;
        }
        if (i10 != updateOp.itemCount) {
            Object obj = updateOp.payload;
            recycleUpdateOp(updateOp);
            updateOp = obtainUpdateOp(4, i9, i10, obj);
        }
        if (c7 == 0) {
            dispatchAndUpdateViewHolders(updateOp);
        } else {
            postponeAndUpdateViewHolders(updateOp);
        }
    }

    private boolean canFindInPreLayout(int i7) {
        int size = this.mPostponedList.size();
        for (int i8 = 0; i8 < size; i8++) {
            UpdateOp updateOp = this.mPostponedList.get(i8);
            int i9 = updateOp.cmd;
            if (i9 == 8) {
                if (findPositionOffset(updateOp.itemCount, i8 + 1) == i7) {
                    return true;
                }
            } else if (i9 == 1) {
                int i10 = updateOp.positionStart;
                int i11 = updateOp.itemCount + i10;
                while (i10 < i11) {
                    if (findPositionOffset(i10, i8 + 1) == i7) {
                        return true;
                    }
                    i10++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private void dispatchAndUpdateViewHolders(UpdateOp updateOp) {
        int i7;
        int i8 = updateOp.cmd;
        if (i8 == 1 || i8 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iUpdatePositionWithPostponed = updatePositionWithPostponed(updateOp.positionStart, i8);
        int i9 = updateOp.positionStart;
        int i10 = updateOp.cmd;
        if (i10 == 2) {
            i7 = 0;
        } else {
            if (i10 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + updateOp);
            }
            i7 = 1;
        }
        int i11 = 1;
        for (int i12 = 1; i12 < updateOp.itemCount; i12++) {
            int iUpdatePositionWithPostponed2 = updatePositionWithPostponed((i7 * i12) + updateOp.positionStart, updateOp.cmd);
            int i13 = updateOp.cmd;
            if (i13 == 2 ? iUpdatePositionWithPostponed2 == iUpdatePositionWithPostponed : i13 == 4 && iUpdatePositionWithPostponed2 == iUpdatePositionWithPostponed + 1) {
                i11++;
            } else {
                UpdateOp updateOpObtainUpdateOp = obtainUpdateOp(i13, iUpdatePositionWithPostponed, i11, updateOp.payload);
                dispatchFirstPassAndUpdateViewHolders(updateOpObtainUpdateOp, i9);
                recycleUpdateOp(updateOpObtainUpdateOp);
                if (updateOp.cmd == 4) {
                    i9 += i11;
                }
                iUpdatePositionWithPostponed = iUpdatePositionWithPostponed2;
                i11 = 1;
            }
        }
        Object obj = updateOp.payload;
        recycleUpdateOp(updateOp);
        if (i11 > 0) {
            UpdateOp updateOpObtainUpdateOp2 = obtainUpdateOp(updateOp.cmd, iUpdatePositionWithPostponed, i11, obj);
            dispatchFirstPassAndUpdateViewHolders(updateOpObtainUpdateOp2, i9);
            recycleUpdateOp(updateOpObtainUpdateOp2);
        }
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        this.mPostponedList.add(updateOp);
        int i7 = updateOp.cmd;
        if (i7 == 1) {
            this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
            return;
        }
        if (i7 == 2) {
            this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(updateOp.positionStart, updateOp.itemCount);
            return;
        }
        if (i7 == 4) {
            this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
        } else {
            if (i7 == 8) {
                this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + updateOp);
        }
    }

    private int updatePositionWithPostponed(int i7, int i8) {
        int i9;
        int i10;
        for (int size = this.mPostponedList.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = this.mPostponedList.get(size);
            int i11 = updateOp.cmd;
            if (i11 == 8) {
                int i12 = updateOp.positionStart;
                int i13 = updateOp.itemCount;
                if (i12 < i13) {
                    i10 = i12;
                    i9 = i13;
                } else {
                    i9 = i12;
                    i10 = i13;
                }
                if (i7 < i10 || i7 > i9) {
                    if (i7 < i12) {
                        if (i8 == 1) {
                            updateOp.positionStart = i12 + 1;
                            updateOp.itemCount = i13 + 1;
                        } else if (i8 == 2) {
                            updateOp.positionStart = i12 - 1;
                            updateOp.itemCount = i13 - 1;
                        }
                    }
                } else if (i10 == i12) {
                    if (i8 == 1) {
                        updateOp.itemCount = i13 + 1;
                    } else if (i8 == 2) {
                        updateOp.itemCount = i13 - 1;
                    }
                    i7++;
                } else {
                    if (i8 == 1) {
                        updateOp.positionStart = i12 + 1;
                    } else if (i8 == 2) {
                        updateOp.positionStart = i12 - 1;
                    }
                    i7--;
                }
            } else {
                int i14 = updateOp.positionStart;
                if (i14 <= i7) {
                    if (i11 == 1) {
                        i7 -= updateOp.itemCount;
                    } else if (i11 == 2) {
                        i7 += updateOp.itemCount;
                    }
                } else if (i8 == 1) {
                    updateOp.positionStart = i14 + 1;
                } else if (i8 == 2) {
                    updateOp.positionStart = i14 - 1;
                }
            }
        }
        for (int size2 = this.mPostponedList.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = this.mPostponedList.get(size2);
            if (updateOp2.cmd == 8) {
                int i15 = updateOp2.itemCount;
                if (i15 == updateOp2.positionStart || i15 < 0) {
                    this.mPostponedList.remove(size2);
                    recycleUpdateOp(updateOp2);
                }
            } else if (updateOp2.itemCount <= 0) {
                this.mPostponedList.remove(size2);
                recycleUpdateOp(updateOp2);
            }
        }
        return i7;
    }

    public AdapterHelper addUpdateOp(UpdateOp... updateOpArr) {
        Collections.addAll(this.mPendingUpdates, updateOpArr);
        return this;
    }

    public int applyPendingUpdatesToPosition(int i7) {
        int size = this.mPendingUpdates.size();
        for (int i8 = 0; i8 < size; i8++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i8);
            int i9 = updateOp.cmd;
            if (i9 != 1) {
                if (i9 == 2) {
                    int i10 = updateOp.positionStart;
                    if (i10 <= i7) {
                        int i11 = updateOp.itemCount;
                        if (i10 + i11 > i7) {
                            return -1;
                        }
                        i7 -= i11;
                    } else {
                        continue;
                    }
                } else if (i9 == 8) {
                    int i12 = updateOp.positionStart;
                    if (i12 == i7) {
                        i7 = updateOp.itemCount;
                    } else {
                        if (i12 < i7) {
                            i7--;
                        }
                        if (updateOp.itemCount <= i7) {
                            i7++;
                        }
                    }
                }
            } else if (updateOp.positionStart <= i7) {
                i7 += updateOp.itemCount;
            }
        }
        return i7;
    }

    public void consumePostponedUpdates() {
        int size = this.mPostponedList.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.mCallback.onDispatchSecondPass(this.mPostponedList.get(i7));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int size = this.mPendingUpdates.size();
        for (int i7 = 0; i7 < size; i7++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i7);
            int i8 = updateOp.cmd;
            if (i8 == 1) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
            } else if (i8 == 2) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForRemovingInvisible(updateOp.positionStart, updateOp.itemCount);
            } else if (i8 == 4) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
            } else if (i8 == 8) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.mExistingUpdateTypes = 0;
    }

    public void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateOp, int i7) {
        this.mCallback.onDispatchFirstPass(updateOp);
        int i8 = updateOp.cmd;
        if (i8 == 2) {
            this.mCallback.offsetPositionsForRemovingInvisible(i7, updateOp.itemCount);
        } else {
            if (i8 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.mCallback.markViewHoldersUpdated(i7, updateOp.itemCount, updateOp.payload);
        }
    }

    public int findPositionOffset(int i7) {
        return findPositionOffset(i7, 0);
    }

    public boolean hasAnyUpdateTypes(int i7) {
        return (i7 & this.mExistingUpdateTypes) != 0;
    }

    public boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    public boolean hasUpdates() {
        return (this.mPostponedList.isEmpty() || this.mPendingUpdates.isEmpty()) ? false : true;
    }

    @Override // android.support.v7.widget.OpReorderer.Callback
    public UpdateOp obtainUpdateOp(int i7, int i8, int i9, Object obj) {
        UpdateOp updateOpAcquire = this.mUpdateOpPool.acquire();
        if (updateOpAcquire == null) {
            return new UpdateOp(i7, i8, i9, obj);
        }
        updateOpAcquire.cmd = i7;
        updateOpAcquire.positionStart = i8;
        updateOpAcquire.itemCount = i9;
        updateOpAcquire.payload = obj;
        return updateOpAcquire;
    }

    public boolean onItemRangeChanged(int i7, int i8, Object obj) {
        if (i8 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(4, i7, i8, obj));
        this.mExistingUpdateTypes |= 4;
        return this.mPendingUpdates.size() == 1;
    }

    public boolean onItemRangeInserted(int i7, int i8) {
        if (i8 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(1, i7, i8, null));
        this.mExistingUpdateTypes |= 1;
        return this.mPendingUpdates.size() == 1;
    }

    public boolean onItemRangeMoved(int i7, int i8, int i9) {
        if (i7 == i8) {
            return false;
        }
        if (i9 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.mPendingUpdates.add(obtainUpdateOp(8, i7, i8, null));
        this.mExistingUpdateTypes |= 8;
        return this.mPendingUpdates.size() == 1;
    }

    public boolean onItemRangeRemoved(int i7, int i8) {
        if (i8 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(2, i7, i8, null));
        this.mExistingUpdateTypes |= 2;
        return this.mPendingUpdates.size() == 1;
    }

    public void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int size = this.mPendingUpdates.size();
        for (int i7 = 0; i7 < size; i7++) {
            UpdateOp updateOp = this.mPendingUpdates.get(i7);
            int i8 = updateOp.cmd;
            if (i8 == 1) {
                applyAdd(updateOp);
            } else if (i8 == 2) {
                applyRemove(updateOp);
            } else if (i8 == 4) {
                applyUpdate(updateOp);
            } else if (i8 == 8) {
                applyMove(updateOp);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    @Override // android.support.v7.widget.OpReorderer.Callback
    public void recycleUpdateOp(UpdateOp updateOp) {
        if (this.mDisableRecycler) {
            return;
        }
        updateOp.payload = null;
        this.mUpdateOpPool.release(updateOp);
    }

    public void recycleUpdateOpsAndClearList(List<UpdateOp> list) {
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            recycleUpdateOp(list.get(i7));
        }
        list.clear();
    }

    public void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    public AdapterHelper(Callback callback, boolean z6) {
        this.mUpdateOpPool = new Pools.SimplePool(30);
        this.mPendingUpdates = new ArrayList<>();
        this.mPostponedList = new ArrayList<>();
        this.mExistingUpdateTypes = 0;
        this.mCallback = callback;
        this.mDisableRecycler = z6;
        this.mOpReorderer = new OpReorderer(this);
    }

    public int findPositionOffset(int i7, int i8) {
        int size = this.mPostponedList.size();
        while (i8 < size) {
            UpdateOp updateOp = this.mPostponedList.get(i8);
            int i9 = updateOp.cmd;
            if (i9 == 8) {
                int i10 = updateOp.positionStart;
                if (i10 == i7) {
                    i7 = updateOp.itemCount;
                } else {
                    if (i10 < i7) {
                        i7--;
                    }
                    if (updateOp.itemCount <= i7) {
                        i7++;
                    }
                }
            } else {
                int i11 = updateOp.positionStart;
                if (i11 > i7) {
                    continue;
                } else if (i9 == 2) {
                    int i12 = updateOp.itemCount;
                    if (i7 < i11 + i12) {
                        return -1;
                    }
                    i7 -= i12;
                } else if (i9 == 1) {
                    i7 += updateOp.itemCount;
                }
            }
            i8++;
        }
        return i7;
    }
}
