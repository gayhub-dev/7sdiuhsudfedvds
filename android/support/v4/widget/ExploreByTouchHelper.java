package android.support.v4.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.FocusStrategy;
import android.support.v7.widget.ActivityChooserView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect INVALID_PARENT_BOUNDS = new Rect(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER = new FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat>() { // from class: android.support.v4.widget.ExploreByTouchHelper.1
        @Override // android.support.v4.widget.FocusStrategy.BoundsAdapter
        public void obtainBounds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Rect rect) {
            accessibilityNodeInfoCompat.getBoundsInParent(rect);
        }
    };
    private static final FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER = new FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat>() { // from class: android.support.v4.widget.ExploreByTouchHelper.2
        @Override // android.support.v4.widget.FocusStrategy.CollectionAdapter
        public AccessibilityNodeInfoCompat get(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat, int i7) {
            return sparseArrayCompat.valueAt(i7);
        }

        @Override // android.support.v4.widget.FocusStrategy.CollectionAdapter
        public int size(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat) {
            return sparseArrayCompat.size();
        }
    };
    private final View mHost;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();
    private final int[] mTempGlobalRect = new int[2];
    public int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    public int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;

    public class MyNodeProvider extends AccessibilityNodeProviderCompat {
        public MyNodeProvider() {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i7) {
            return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(i7));
        }

        @Override // android.support.v4.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat findFocus(int i7) {
            int i8 = i7 == 2 ? ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId : ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
            if (i8 == Integer.MIN_VALUE) {
                return null;
            }
            return createAccessibilityNodeInfo(i8);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityNodeProviderCompat
        public boolean performAction(int i7, int i8, Bundle bundle) {
            return ExploreByTouchHelper.this.performAction(i7, i8, bundle);
        }
    }

    public ExploreByTouchHelper(@NonNull View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.mHost = view;
        this.mManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (ViewCompat.getImportantForAccessibility(view) == 0) {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
    }

    private boolean clearAccessibilityFocus(int i7) {
        if (this.mAccessibilityFocusedVirtualViewId != i7) {
            return false;
        }
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHost.invalidate();
        sendEventForVirtualView(i7, 65536);
        return true;
    }

    private boolean clickKeyboardFocusedVirtualView() {
        int i7 = this.mKeyboardFocusedVirtualViewId;
        return i7 != Integer.MIN_VALUE && onPerformActionForVirtualView(i7, 16, null);
    }

    private AccessibilityEvent createEvent(int i7, int i8) {
        return i7 != -1 ? createEventForChild(i7, i8) : createEventForHost(i8);
    }

    private AccessibilityEvent createEventForChild(int i7, int i8) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i8);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(i7);
        accessibilityEventObtain.getText().add(accessibilityNodeInfoCompatObtainAccessibilityNodeInfo.getText());
        accessibilityEventObtain.setContentDescription(accessibilityNodeInfoCompatObtainAccessibilityNodeInfo.getContentDescription());
        accessibilityEventObtain.setScrollable(accessibilityNodeInfoCompatObtainAccessibilityNodeInfo.isScrollable());
        accessibilityEventObtain.setPassword(accessibilityNodeInfoCompatObtainAccessibilityNodeInfo.isPassword());
        accessibilityEventObtain.setEnabled(accessibilityNodeInfoCompatObtainAccessibilityNodeInfo.isEnabled());
        accessibilityEventObtain.setChecked(accessibilityNodeInfoCompatObtainAccessibilityNodeInfo.isChecked());
        onPopulateEventForVirtualView(i7, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(accessibilityNodeInfoCompatObtainAccessibilityNodeInfo.getClassName());
        AccessibilityRecordCompat.setSource(accessibilityEventObtain, this.mHost, i7);
        accessibilityEventObtain.setPackageName(this.mHost.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    private AccessibilityEvent createEventForHost(int i7) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i7);
        this.mHost.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    @NonNull
    private AccessibilityNodeInfoCompat createNodeForChild(int i7) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain();
        accessibilityNodeInfoCompatObtain.setEnabled(true);
        accessibilityNodeInfoCompatObtain.setFocusable(true);
        accessibilityNodeInfoCompatObtain.setClassName(DEFAULT_CLASS_NAME);
        Rect rect = INVALID_PARENT_BOUNDS;
        accessibilityNodeInfoCompatObtain.setBoundsInParent(rect);
        accessibilityNodeInfoCompatObtain.setBoundsInScreen(rect);
        accessibilityNodeInfoCompatObtain.setParent(this.mHost);
        onPopulateNodeForVirtualView(i7, accessibilityNodeInfoCompatObtain);
        if (accessibilityNodeInfoCompatObtain.getText() == null && accessibilityNodeInfoCompatObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        accessibilityNodeInfoCompatObtain.getBoundsInParent(this.mTempParentRect);
        if (this.mTempParentRect.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int actions = accessibilityNodeInfoCompatObtain.getActions();
        if ((actions & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((actions & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        accessibilityNodeInfoCompatObtain.setPackageName(this.mHost.getContext().getPackageName());
        accessibilityNodeInfoCompatObtain.setSource(this.mHost, i7);
        if (this.mAccessibilityFocusedVirtualViewId == i7) {
            accessibilityNodeInfoCompatObtain.setAccessibilityFocused(true);
            accessibilityNodeInfoCompatObtain.addAction(128);
        } else {
            accessibilityNodeInfoCompatObtain.setAccessibilityFocused(false);
            accessibilityNodeInfoCompatObtain.addAction(64);
        }
        boolean z6 = this.mKeyboardFocusedVirtualViewId == i7;
        if (z6) {
            accessibilityNodeInfoCompatObtain.addAction(2);
        } else if (accessibilityNodeInfoCompatObtain.isFocusable()) {
            accessibilityNodeInfoCompatObtain.addAction(1);
        }
        accessibilityNodeInfoCompatObtain.setFocused(z6);
        this.mHost.getLocationOnScreen(this.mTempGlobalRect);
        accessibilityNodeInfoCompatObtain.getBoundsInScreen(this.mTempScreenRect);
        if (this.mTempScreenRect.equals(rect)) {
            accessibilityNodeInfoCompatObtain.getBoundsInParent(this.mTempScreenRect);
            if (accessibilityNodeInfoCompatObtain.mParentVirtualDescendantId != -1) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain2 = AccessibilityNodeInfoCompat.obtain();
                for (int i8 = accessibilityNodeInfoCompatObtain.mParentVirtualDescendantId; i8 != -1; i8 = accessibilityNodeInfoCompatObtain2.mParentVirtualDescendantId) {
                    accessibilityNodeInfoCompatObtain2.setParent(this.mHost, -1);
                    accessibilityNodeInfoCompatObtain2.setBoundsInParent(INVALID_PARENT_BOUNDS);
                    onPopulateNodeForVirtualView(i8, accessibilityNodeInfoCompatObtain2);
                    accessibilityNodeInfoCompatObtain2.getBoundsInParent(this.mTempParentRect);
                    Rect rect2 = this.mTempScreenRect;
                    Rect rect3 = this.mTempParentRect;
                    rect2.offset(rect3.left, rect3.top);
                }
                accessibilityNodeInfoCompatObtain2.recycle();
            }
            this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
        }
        if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
            this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
            if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                accessibilityNodeInfoCompatObtain.setBoundsInScreen(this.mTempScreenRect);
                if (isVisibleToUser(this.mTempScreenRect)) {
                    accessibilityNodeInfoCompatObtain.setVisibleToUser(true);
                }
            }
        }
        return accessibilityNodeInfoCompatObtain;
    }

    @NonNull
    private AccessibilityNodeInfoCompat createNodeForHost() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain(this.mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, accessibilityNodeInfoCompatObtain);
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        if (accessibilityNodeInfoCompatObtain.getChildCount() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            accessibilityNodeInfoCompatObtain.addChild(this.mHost, ((Integer) arrayList.get(i7)).intValue());
        }
        return accessibilityNodeInfoCompatObtain;
    }

    private SparseArrayCompat<AccessibilityNodeInfoCompat> getAllNodes() {
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat = new SparseArrayCompat<>();
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            sparseArrayCompat.put(i7, createNodeForChild(i7));
        }
        return sparseArrayCompat;
    }

    private void getBoundsInParent(int i7, Rect rect) {
        obtainAccessibilityNodeInfo(i7).getBoundsInParent(rect);
    }

    private static Rect guessPreviouslyFocusedRect(@NonNull View view, int i7, @NonNull Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i7 == 17) {
            rect.set(width, 0, width, height);
        } else if (i7 == 33) {
            rect.set(0, height, width, height);
        } else if (i7 == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    private boolean isVisibleToUser(Rect rect) {
        if (rect == null || rect.isEmpty() || this.mHost.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.mHost.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    private static int keyToDirection(int i7) {
        if (i7 == 19) {
            return 33;
        }
        if (i7 != 21) {
            return i7 != 22 ? 130 : 66;
        }
        return 17;
    }

    private boolean moveFocus(int i7, @Nullable Rect rect) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        SparseArrayCompat<AccessibilityNodeInfoCompat> allNodes = getAllNodes();
        int i8 = this.mKeyboardFocusedVirtualViewId;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = i8 == Integer.MIN_VALUE ? null : allNodes.get(i8);
        if (i7 == 1 || i7 == 2) {
            accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInRelativeDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, accessibilityNodeInfoCompat2, i7, ViewCompat.getLayoutDirection(this.mHost) == 1, false);
        } else {
            if (i7 != 17 && i7 != 33 && i7 != 66 && i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i9 = this.mKeyboardFocusedVirtualViewId;
            if (i9 != Integer.MIN_VALUE) {
                getBoundsInParent(i9, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                guessPreviouslyFocusedRect(this.mHost, i7, rect2);
            }
            accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInAbsoluteDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, accessibilityNodeInfoCompat2, rect2, i7);
        }
        return requestKeyboardFocusForVirtualView(accessibilityNodeInfoCompat != null ? allNodes.keyAt(allNodes.indexOfValue(accessibilityNodeInfoCompat)) : Integer.MIN_VALUE);
    }

    private boolean performActionForChild(int i7, int i8, Bundle bundle) {
        return i8 != 1 ? i8 != 2 ? i8 != 64 ? i8 != 128 ? onPerformActionForVirtualView(i7, i8, bundle) : clearAccessibilityFocus(i7) : requestAccessibilityFocus(i7) : clearKeyboardFocusForVirtualView(i7) : requestKeyboardFocusForVirtualView(i7);
    }

    private boolean performActionForHost(int i7, Bundle bundle) {
        return ViewCompat.performAccessibilityAction(this.mHost, i7, bundle);
    }

    private boolean requestAccessibilityFocus(int i7) {
        int i8;
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled() || (i8 = this.mAccessibilityFocusedVirtualViewId) == i7) {
            return false;
        }
        if (i8 != Integer.MIN_VALUE) {
            clearAccessibilityFocus(i8);
        }
        this.mAccessibilityFocusedVirtualViewId = i7;
        this.mHost.invalidate();
        sendEventForVirtualView(i7, 32768);
        return true;
    }

    private void updateHoveredVirtualView(int i7) {
        int i8 = this.mHoveredVirtualViewId;
        if (i8 == i7) {
            return;
        }
        this.mHoveredVirtualViewId = i7;
        sendEventForVirtualView(i7, 128);
        sendEventForVirtualView(i8, 256);
    }

    public final boolean clearKeyboardFocusForVirtualView(int i7) {
        if (this.mKeyboardFocusedVirtualViewId != i7) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(i7, false);
        sendEventForVirtualView(i7, 8);
        return true;
    }

    public final boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent) {
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int virtualViewAt = getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
            updateHoveredVirtualView(virtualViewAt);
            return virtualViewAt != Integer.MIN_VALUE;
        }
        if (action != 10 || this.mHoveredVirtualViewId == Integer.MIN_VALUE) {
            return false;
        }
        updateHoveredVirtualView(Integer.MIN_VALUE);
        return true;
    }

    public final boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        int i7 = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 61) {
            if (keyEvent.hasNoModifiers()) {
                return moveFocus(2, null);
            }
            if (keyEvent.hasModifiers(1)) {
                return moveFocus(1, null);
            }
            return false;
        }
        if (keyCode != 66) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    if (!keyEvent.hasNoModifiers()) {
                        return false;
                    }
                    int iKeyToDirection = keyToDirection(keyCode);
                    int repeatCount = keyEvent.getRepeatCount() + 1;
                    boolean z6 = false;
                    while (i7 < repeatCount && moveFocus(iKeyToDirection, null)) {
                        i7++;
                        z6 = true;
                    }
                    return z6;
                case 23:
                    break;
                default:
                    return false;
            }
        }
        if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
            return false;
        }
        clickKeyboardFocusedVirtualView();
        return true;
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    @Override // android.support.v4.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider();
        }
        return this.mNodeProvider;
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    public abstract int getVirtualViewAt(float f7, float f8);

    public abstract void getVisibleVirtualViews(List<Integer> list);

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int i7) {
        invalidateVirtualView(i7, 0);
    }

    @NonNull
    public AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i7) {
        return i7 == -1 ? createNodeForHost() : createNodeForChild(i7);
    }

    public final void onFocusChanged(boolean z6, int i7, @Nullable Rect rect) {
        int i8 = this.mKeyboardFocusedVirtualViewId;
        if (i8 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i8);
        }
        if (z6) {
            moveFocus(i7, rect);
        }
    }

    @Override // android.support.v4.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        onPopulateEventForHost(accessibilityEvent);
    }

    @Override // android.support.v4.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        onPopulateNodeForHost(accessibilityNodeInfoCompat);
    }

    public abstract boolean onPerformActionForVirtualView(int i7, int i8, @Nullable Bundle bundle);

    public void onPopulateEventForHost(@NonNull AccessibilityEvent accessibilityEvent) {
    }

    public void onPopulateEventForVirtualView(int i7, @NonNull AccessibilityEvent accessibilityEvent) {
    }

    public void onPopulateNodeForHost(@NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    public abstract void onPopulateNodeForVirtualView(int i7, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    public void onVirtualViewKeyboardFocusChanged(int i7, boolean z6) {
    }

    public boolean performAction(int i7, int i8, Bundle bundle) {
        return i7 != -1 ? performActionForChild(i7, i8, bundle) : performActionForHost(i8, bundle);
    }

    public final boolean requestKeyboardFocusForVirtualView(int i7) {
        int i8;
        if ((!this.mHost.isFocused() && !this.mHost.requestFocus()) || (i8 = this.mKeyboardFocusedVirtualViewId) == i7) {
            return false;
        }
        if (i8 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i8);
        }
        this.mKeyboardFocusedVirtualViewId = i7;
        onVirtualViewKeyboardFocusChanged(i7, true);
        sendEventForVirtualView(i7, 8);
        return true;
    }

    public final boolean sendEventForVirtualView(int i7, int i8) {
        ViewParent parent;
        if (i7 == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return false;
        }
        return ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, createEvent(i7, i8));
    }

    public final void invalidateVirtualView(int i7, int i8) {
        ViewParent parent;
        if (i7 == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventCreateEvent = createEvent(i7, 2048);
        AccessibilityEventCompat.setContentChangeTypes(accessibilityEventCreateEvent, i8);
        ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, accessibilityEventCreateEvent);
    }
}
