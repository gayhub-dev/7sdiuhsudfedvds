package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes.dex */
public abstract class SimpleItemAnimator extends RecyclerView.ItemAnimator {
    private static final boolean DEBUG = false;
    private static final String TAG = "SimpleItemAnimator";
    public boolean mSupportsChangeAnimations = true;

    public abstract boolean animateAdd(RecyclerView.ViewHolder viewHolder);

    @Override // android.support.v7.widget.RecyclerView.ItemAnimator
    public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i7;
        int i8;
        return (itemHolderInfo == null || ((i7 = itemHolderInfo.left) == (i8 = itemHolderInfo2.left) && itemHolderInfo.top == itemHolderInfo2.top)) ? animateAdd(viewHolder) : animateMove(viewHolder, i7, itemHolderInfo.top, i8, itemHolderInfo2.top);
    }

    public abstract boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i7, int i8, int i9, int i10);

    @Override // android.support.v7.widget.RecyclerView.ItemAnimator
    public boolean animateChange(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i7;
        int i8;
        int i9 = itemHolderInfo.left;
        int i10 = itemHolderInfo.top;
        if (viewHolder2.shouldIgnore()) {
            int i11 = itemHolderInfo.left;
            i8 = itemHolderInfo.top;
            i7 = i11;
        } else {
            i7 = itemHolderInfo2.left;
            i8 = itemHolderInfo2.top;
        }
        return animateChange(viewHolder, viewHolder2, i9, i10, i7, i8);
    }

    @Override // android.support.v7.widget.RecyclerView.ItemAnimator
    public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i7 = itemHolderInfo.left;
        int i8 = itemHolderInfo.top;
        View view = viewHolder.itemView;
        int left = itemHolderInfo2 == null ? view.getLeft() : itemHolderInfo2.left;
        int top = itemHolderInfo2 == null ? view.getTop() : itemHolderInfo2.top;
        if (viewHolder.isRemoved() || (i7 == left && i8 == top)) {
            return animateRemove(viewHolder);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return animateMove(viewHolder, i7, i8, left, top);
    }

    public abstract boolean animateMove(RecyclerView.ViewHolder viewHolder, int i7, int i8, int i9, int i10);

    @Override // android.support.v7.widget.RecyclerView.ItemAnimator
    public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2) {
        int i7 = itemHolderInfo.left;
        int i8 = itemHolderInfo2.left;
        if (i7 != i8 || itemHolderInfo.top != itemHolderInfo2.top) {
            return animateMove(viewHolder, i7, itemHolderInfo.top, i8, itemHolderInfo2.top);
        }
        dispatchMoveFinished(viewHolder);
        return false;
    }

    public abstract boolean animateRemove(RecyclerView.ViewHolder viewHolder);

    @Override // android.support.v7.widget.RecyclerView.ItemAnimator
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        return !this.mSupportsChangeAnimations || viewHolder.isInvalid();
    }

    public final void dispatchAddFinished(RecyclerView.ViewHolder viewHolder) {
        onAddFinished(viewHolder);
        dispatchAnimationFinished(viewHolder);
    }

    public final void dispatchAddStarting(RecyclerView.ViewHolder viewHolder) {
        onAddStarting(viewHolder);
    }

    public final void dispatchChangeFinished(RecyclerView.ViewHolder viewHolder, boolean z6) {
        onChangeFinished(viewHolder, z6);
        dispatchAnimationFinished(viewHolder);
    }

    public final void dispatchChangeStarting(RecyclerView.ViewHolder viewHolder, boolean z6) {
        onChangeStarting(viewHolder, z6);
    }

    public final void dispatchMoveFinished(RecyclerView.ViewHolder viewHolder) {
        onMoveFinished(viewHolder);
        dispatchAnimationFinished(viewHolder);
    }

    public final void dispatchMoveStarting(RecyclerView.ViewHolder viewHolder) {
        onMoveStarting(viewHolder);
    }

    public final void dispatchRemoveFinished(RecyclerView.ViewHolder viewHolder) {
        onRemoveFinished(viewHolder);
        dispatchAnimationFinished(viewHolder);
    }

    public final void dispatchRemoveStarting(RecyclerView.ViewHolder viewHolder) {
        onRemoveStarting(viewHolder);
    }

    public boolean getSupportsChangeAnimations() {
        return this.mSupportsChangeAnimations;
    }

    public void onAddFinished(RecyclerView.ViewHolder viewHolder) {
    }

    public void onAddStarting(RecyclerView.ViewHolder viewHolder) {
    }

    public void onChangeFinished(RecyclerView.ViewHolder viewHolder, boolean z6) {
    }

    public void onChangeStarting(RecyclerView.ViewHolder viewHolder, boolean z6) {
    }

    public void onMoveFinished(RecyclerView.ViewHolder viewHolder) {
    }

    public void onMoveStarting(RecyclerView.ViewHolder viewHolder) {
    }

    public void onRemoveFinished(RecyclerView.ViewHolder viewHolder) {
    }

    public void onRemoveStarting(RecyclerView.ViewHolder viewHolder) {
    }

    public void setSupportsChangeAnimations(boolean z6) {
        this.mSupportsChangeAnimations = z6;
    }
}
