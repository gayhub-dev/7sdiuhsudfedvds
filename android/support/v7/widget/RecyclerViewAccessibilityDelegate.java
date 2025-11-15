package android.support.v7.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: classes.dex */
public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat {
    public final AccessibilityDelegateCompat mItemDelegate = new ItemDelegate(this);
    public final RecyclerView mRecyclerView;

    public static class ItemDelegate extends AccessibilityDelegateCompat {
        public final RecyclerViewAccessibilityDelegate mRecyclerViewDelegate;

        public ItemDelegate(@NonNull RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
            this.mRecyclerViewDelegate = recyclerViewAccessibilityDelegate;
        }

        @Override // android.support.v4.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (this.mRecyclerViewDelegate.shouldIgnore() || this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() == null) {
                return;
            }
            this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
        }

        @Override // android.support.v4.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i7, Bundle bundle) {
            if (super.performAccessibilityAction(view, i7, bundle)) {
                return true;
            }
            if (this.mRecyclerViewDelegate.shouldIgnore() || this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager() == null) {
                return false;
            }
            return this.mRecyclerViewDelegate.mRecyclerView.getLayoutManager().performAccessibilityActionForItem(view, i7, bundle);
        }
    }

    public RecyclerViewAccessibilityDelegate(@NonNull RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    @NonNull
    public AccessibilityDelegateCompat getItemDelegate() {
        return this.mItemDelegate;
    }

    @Override // android.support.v4.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName(RecyclerView.class.getName());
        if (!(view instanceof RecyclerView) || shouldIgnore()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().onInitializeAccessibilityEvent(accessibilityEvent);
        }
    }

    @Override // android.support.v4.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(RecyclerView.class.getName());
        if (shouldIgnore() || this.mRecyclerView.getLayoutManager() == null) {
            return;
        }
        this.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat);
    }

    @Override // android.support.v4.view.AccessibilityDelegateCompat
    public boolean performAccessibilityAction(View view, int i7, Bundle bundle) {
        if (super.performAccessibilityAction(view, i7, bundle)) {
            return true;
        }
        if (shouldIgnore() || this.mRecyclerView.getLayoutManager() == null) {
            return false;
        }
        return this.mRecyclerView.getLayoutManager().performAccessibilityAction(i7, bundle);
    }

    public boolean shouldIgnore() {
        return this.mRecyclerView.hasPendingAdapterUpdates();
    }
}
