package android.support.v7.util;

import android.support.annotation.NonNull;

/* loaded from: classes.dex */
public class BatchingListUpdateCallback implements ListUpdateCallback {
    private static final int TYPE_ADD = 1;
    private static final int TYPE_CHANGE = 3;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_REMOVE = 2;
    public final ListUpdateCallback mWrapped;
    public int mLastEventType = 0;
    public int mLastEventPosition = -1;
    public int mLastEventCount = -1;
    public Object mLastEventPayload = null;

    public BatchingListUpdateCallback(@NonNull ListUpdateCallback listUpdateCallback) {
        this.mWrapped = listUpdateCallback;
    }

    public void dispatchLastEvent() {
        int i7 = this.mLastEventType;
        if (i7 == 0) {
            return;
        }
        if (i7 == 1) {
            this.mWrapped.onInserted(this.mLastEventPosition, this.mLastEventCount);
        } else if (i7 == 2) {
            this.mWrapped.onRemoved(this.mLastEventPosition, this.mLastEventCount);
        } else if (i7 == 3) {
            this.mWrapped.onChanged(this.mLastEventPosition, this.mLastEventCount, this.mLastEventPayload);
        }
        this.mLastEventPayload = null;
        this.mLastEventType = 0;
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onChanged(int i7, int i8, Object obj) {
        int i9;
        if (this.mLastEventType == 3) {
            int i10 = this.mLastEventPosition;
            int i11 = this.mLastEventCount;
            if (i7 <= i10 + i11 && (i9 = i7 + i8) >= i10 && this.mLastEventPayload == obj) {
                this.mLastEventPosition = Math.min(i7, i10);
                this.mLastEventCount = Math.max(i11 + i10, i9) - this.mLastEventPosition;
                return;
            }
        }
        dispatchLastEvent();
        this.mLastEventPosition = i7;
        this.mLastEventCount = i8;
        this.mLastEventPayload = obj;
        this.mLastEventType = 3;
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onInserted(int i7, int i8) {
        int i9;
        if (this.mLastEventType == 1 && i7 >= (i9 = this.mLastEventPosition)) {
            int i10 = this.mLastEventCount;
            if (i7 <= i9 + i10) {
                this.mLastEventCount = i10 + i8;
                this.mLastEventPosition = Math.min(i7, i9);
                return;
            }
        }
        dispatchLastEvent();
        this.mLastEventPosition = i7;
        this.mLastEventCount = i8;
        this.mLastEventType = 1;
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onMoved(int i7, int i8) {
        dispatchLastEvent();
        this.mWrapped.onMoved(i7, i8);
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public void onRemoved(int i7, int i8) {
        int i9;
        if (this.mLastEventType == 2 && (i9 = this.mLastEventPosition) >= i7 && i9 <= i7 + i8) {
            this.mLastEventCount += i8;
            this.mLastEventPosition = i7;
        } else {
            dispatchLastEvent();
            this.mLastEventPosition = i7;
            this.mLastEventCount = i8;
            this.mLastEventType = 2;
        }
    }
}
