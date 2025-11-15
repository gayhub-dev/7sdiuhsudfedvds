package android.support.v4.view;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.UiThread;
import android.support.compat.C0068R;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class ViewCompat {
    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;

    @Deprecated
    public static final int LAYER_TYPE_HARDWARE = 2;

    @Deprecated
    public static final int LAYER_TYPE_NONE = 0;

    @Deprecated
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;

    @Deprecated
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;

    @Deprecated
    public static final int MEASURED_SIZE_MASK = 16777215;

    @Deprecated
    public static final int MEASURED_STATE_MASK = -16777216;

    @Deprecated
    public static final int MEASURED_STATE_TOO_SMALL = 16777216;

    @Deprecated
    public static final int OVER_SCROLL_ALWAYS = 0;

    @Deprecated
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;

    @Deprecated
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    private static final String TAG = "ViewCompat";
    public static final int TYPE_NON_TOUCH = 1;
    public static final int TYPE_TOUCH = 0;
    private static Field sAccessibilityDelegateField;
    private static Method sChildrenDrawingOrderMethod;
    private static Method sDispatchFinishTemporaryDetach;
    private static Method sDispatchStartTemporaryDetach;
    private static Field sMinHeightField;
    private static boolean sMinHeightFieldFetched;
    private static Field sMinWidthField;
    private static boolean sMinWidthFieldFetched;
    private static boolean sTempDetachBound;
    private static ThreadLocal<Rect> sThreadLocalRect;
    private static WeakHashMap<View, String> sTransitionNameMap;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private static WeakHashMap<View, ViewPropertyAnimatorCompat> sViewPropertyAnimatorMap = null;
    private static boolean sAccessibilityDelegateCheckFailed = false;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface FocusDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface FocusRealDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface FocusRelativeDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface NestedScrollType {
    }

    public interface OnUnhandledKeyEventListenerCompat {
        boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent);
    }

    @RequiresApi(28)
    public static class OnUnhandledKeyEventListenerWrapper implements View.OnUnhandledKeyEventListener {
        private OnUnhandledKeyEventListenerCompat mCompatListener;

        public OnUnhandledKeyEventListenerWrapper(OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            this.mCompatListener = onUnhandledKeyEventListenerCompat;
        }

        @Override // android.view.View.OnUnhandledKeyEventListener
        public boolean onUnhandledKeyEvent(View view, KeyEvent keyEvent) {
            return this.mCompatListener.onUnhandledKeyEvent(view, keyEvent);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface ScrollAxis {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface ScrollIndicators {
    }

    public static class UnhandledKeyEventManager {
        private static final ArrayList<WeakReference<View>> sViewsWithListeners = new ArrayList<>();

        @Nullable
        private WeakHashMap<View, Boolean> mViewsContainingListeners = null;
        private SparseArray<WeakReference<View>> mCapturedKeys = null;
        private WeakReference<KeyEvent> mLastDispatchedPreViewKeyEvent = null;

        /* renamed from: at */
        public static UnhandledKeyEventManager m103at(View view) {
            int i7 = C0068R.id.tag_unhandled_key_event_manager;
            UnhandledKeyEventManager unhandledKeyEventManager = (UnhandledKeyEventManager) view.getTag(i7);
            if (unhandledKeyEventManager != null) {
                return unhandledKeyEventManager;
            }
            UnhandledKeyEventManager unhandledKeyEventManager2 = new UnhandledKeyEventManager();
            view.setTag(i7, unhandledKeyEventManager2);
            return unhandledKeyEventManager2;
        }

        @Nullable
        private View dispatchInOrder(View view, KeyEvent keyEvent) {
            WeakHashMap<View, Boolean> weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null && weakHashMap.containsKey(view)) {
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        View viewDispatchInOrder = dispatchInOrder(viewGroup.getChildAt(childCount), keyEvent);
                        if (viewDispatchInOrder != null) {
                            return viewDispatchInOrder;
                        }
                    }
                }
                if (onUnhandledKeyEvent(view, keyEvent)) {
                    return view;
                }
            }
            return null;
        }

        private SparseArray<WeakReference<View>> getCapturedKeys() {
            if (this.mCapturedKeys == null) {
                this.mCapturedKeys = new SparseArray<>();
            }
            return this.mCapturedKeys;
        }

        private boolean onUnhandledKeyEvent(@NonNull View view, @NonNull KeyEvent keyEvent) {
            ArrayList arrayList = (ArrayList) view.getTag(C0068R.id.tag_unhandled_key_listeners);
            if (arrayList == null) {
                return false;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((OnUnhandledKeyEventListenerCompat) arrayList.get(size)).onUnhandledKeyEvent(view, keyEvent)) {
                    return true;
                }
            }
            return false;
        }

        private void recalcViewsWithUnhandled() {
            WeakHashMap<View, Boolean> weakHashMap = this.mViewsContainingListeners;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
            if (arrayList.isEmpty()) {
                return;
            }
            synchronized (arrayList) {
                if (this.mViewsContainingListeners == null) {
                    this.mViewsContainingListeners = new WeakHashMap<>();
                }
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    ArrayList<WeakReference<View>> arrayList2 = sViewsWithListeners;
                    View view = arrayList2.get(size).get();
                    if (view == null) {
                        arrayList2.remove(size);
                    } else {
                        this.mViewsContainingListeners.put(view, Boolean.TRUE);
                        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                            this.mViewsContainingListeners.put((View) parent, Boolean.TRUE);
                        }
                    }
                }
            }
        }

        public static void registerListeningView(View view) {
            ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
            synchronized (arrayList) {
                Iterator<WeakReference<View>> it = arrayList.iterator();
                while (it.hasNext()) {
                    if (it.next().get() == view) {
                        return;
                    }
                }
                sViewsWithListeners.add(new WeakReference<>(view));
            }
        }

        public static void unregisterListeningView(View view) {
            synchronized (sViewsWithListeners) {
                int i7 = 0;
                while (true) {
                    ArrayList<WeakReference<View>> arrayList = sViewsWithListeners;
                    if (i7 >= arrayList.size()) {
                        return;
                    }
                    if (arrayList.get(i7).get() == view) {
                        arrayList.remove(i7);
                        return;
                    }
                    i7++;
                }
            }
        }

        public boolean dispatch(View view, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0) {
                recalcViewsWithUnhandled();
            }
            View viewDispatchInOrder = dispatchInOrder(view, keyEvent);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (viewDispatchInOrder != null && !KeyEvent.isModifierKey(keyCode)) {
                    getCapturedKeys().put(keyCode, new WeakReference<>(viewDispatchInOrder));
                }
            }
            return viewDispatchInOrder != null;
        }

        public boolean preDispatch(KeyEvent keyEvent) {
            int iIndexOfKey;
            WeakReference<KeyEvent> weakReference = this.mLastDispatchedPreViewKeyEvent;
            if (weakReference != null && weakReference.get() == keyEvent) {
                return false;
            }
            this.mLastDispatchedPreViewKeyEvent = new WeakReference<>(keyEvent);
            WeakReference<View> weakReferenceValueAt = null;
            SparseArray<WeakReference<View>> capturedKeys = getCapturedKeys();
            if (keyEvent.getAction() == 1 && (iIndexOfKey = capturedKeys.indexOfKey(keyEvent.getKeyCode())) >= 0) {
                weakReferenceValueAt = capturedKeys.valueAt(iIndexOfKey);
                capturedKeys.removeAt(iIndexOfKey);
            }
            if (weakReferenceValueAt == null) {
                weakReferenceValueAt = capturedKeys.get(keyEvent.getKeyCode());
            }
            if (weakReferenceValueAt == null) {
                return false;
            }
            View view = weakReferenceValueAt.get();
            if (view != null && ViewCompat.isAttachedToWindow(view)) {
                onUnhandledKeyEvent(view, keyEvent);
            }
            return true;
        }
    }

    public static void addKeyboardNavigationClusters(@NonNull View view, @NonNull Collection<View> collection, int i7) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.addKeyboardNavigationClusters(collection, i7);
        }
    }

    public static void addOnUnhandledKeyEventListener(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
        if (Build.VERSION.SDK_INT >= 28) {
            int i7 = C0068R.id.tag_unhandled_key_listeners;
            Map arrayMap = (Map) view.getTag(i7);
            if (arrayMap == null) {
                arrayMap = new ArrayMap();
                view.setTag(i7, arrayMap);
            }
            OnUnhandledKeyEventListenerWrapper onUnhandledKeyEventListenerWrapper = new OnUnhandledKeyEventListenerWrapper(onUnhandledKeyEventListenerCompat);
            arrayMap.put(onUnhandledKeyEventListenerCompat, onUnhandledKeyEventListenerWrapper);
            view.addOnUnhandledKeyEventListener(onUnhandledKeyEventListenerWrapper);
            return;
        }
        int i8 = C0068R.id.tag_unhandled_key_listeners;
        ArrayList arrayList = (ArrayList) view.getTag(i8);
        if (arrayList == null) {
            arrayList = new ArrayList();
            view.setTag(i8, arrayList);
        }
        arrayList.add(onUnhandledKeyEventListenerCompat);
        if (arrayList.size() == 1) {
            UnhandledKeyEventManager.registerListeningView(view);
        }
    }

    @NonNull
    public static ViewPropertyAnimatorCompat animate(@NonNull View view) {
        if (sViewPropertyAnimatorMap == null) {
            sViewPropertyAnimatorMap = new WeakHashMap<>();
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = sViewPropertyAnimatorMap.get(view);
        if (viewPropertyAnimatorCompat != null) {
            return viewPropertyAnimatorCompat;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = new ViewPropertyAnimatorCompat(view);
        sViewPropertyAnimatorMap.put(view, viewPropertyAnimatorCompat2);
        return viewPropertyAnimatorCompat2;
    }

    private static void bindTempDetach() {
        try {
            sDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", new Class[0]);
            sDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", new Class[0]);
        } catch (NoSuchMethodException unused) {
        }
        sTempDetachBound = true;
    }

    @Deprecated
    public static boolean canScrollHorizontally(View view, int i7) {
        return view.canScrollHorizontally(i7);
    }

    @Deprecated
    public static boolean canScrollVertically(View view, int i7) {
        return view.canScrollVertically(i7);
    }

    public static void cancelDragAndDrop(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.cancelDragAndDrop();
        }
    }

    @Deprecated
    public static int combineMeasuredStates(int i7, int i8) {
        return View.combineMeasuredStates(i7, i8);
    }

    private static void compatOffsetLeftAndRight(View view, int i7) {
        view.offsetLeftAndRight(i7);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    private static void compatOffsetTopAndBottom(View view, int i7) {
        view.offsetTopAndBottom(i7);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View) parent);
            }
        }
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(@NonNull View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = (WindowInsets) WindowInsetsCompat.unwrap(windowInsetsCompat);
        WindowInsets windowInsetsDispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsets);
        if (windowInsetsDispatchApplyWindowInsets != windowInsets) {
            windowInsets = new WindowInsets(windowInsetsDispatchApplyWindowInsets);
        }
        return WindowInsetsCompat.wrap(windowInsets);
    }

    public static void dispatchFinishTemporaryDetach(@NonNull View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Build.VERSION.SDK_INT >= 24) {
            view.dispatchFinishTemporaryDetach();
            return;
        }
        if (!sTempDetachBound) {
            bindTempDetach();
        }
        Method method = sDispatchFinishTemporaryDetach;
        if (method == null) {
            view.onFinishTemporaryDetach();
        } else {
            try {
                method.invoke(view, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    public static boolean dispatchNestedFling(@NonNull View view, float f7, float f8, boolean z6) {
        return view.dispatchNestedFling(f7, f8, z6);
    }

    public static boolean dispatchNestedPreFling(@NonNull View view, float f7, float f8) {
        return view.dispatchNestedPreFling(f7, f8);
    }

    public static boolean dispatchNestedPreScroll(@NonNull View view, int i7, int i8, @Nullable int[] iArr, @Nullable int[] iArr2) {
        return view.dispatchNestedPreScroll(i7, i8, iArr, iArr2);
    }

    public static boolean dispatchNestedScroll(@NonNull View view, int i7, int i8, int i9, int i10, @Nullable int[] iArr) {
        return view.dispatchNestedScroll(i7, i8, i9, i10, iArr);
    }

    public static void dispatchStartTemporaryDetach(@NonNull View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Build.VERSION.SDK_INT >= 24) {
            view.dispatchStartTemporaryDetach();
            return;
        }
        if (!sTempDetachBound) {
            bindTempDetach();
        }
        Method method = sDispatchStartTemporaryDetach;
        if (method == null) {
            view.onStartTemporaryDetach();
        } else {
            try {
                method.invoke(view, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    @UiThread
    public static boolean dispatchUnhandledKeyEventBeforeCallback(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.m103at(view).dispatch(view, keyEvent);
    }

    @UiThread
    public static boolean dispatchUnhandledKeyEventBeforeHierarchy(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return UnhandledKeyEventManager.m103at(view).preDispatch(keyEvent);
    }

    public static int generateViewId() {
        return View.generateViewId();
    }

    public static int getAccessibilityLiveRegion(@NonNull View view) {
        return view.getAccessibilityLiveRegion();
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(@NonNull View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = view.getAccessibilityNodeProvider();
        if (accessibilityNodeProvider != null) {
            return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
        }
        return null;
    }

    @Deprecated
    public static float getAlpha(View view) {
        return view.getAlpha();
    }

    public static ColorStateList getBackgroundTintList(@NonNull View view) {
        return view.getBackgroundTintList();
    }

    public static PorterDuff.Mode getBackgroundTintMode(@NonNull View view) {
        return view.getBackgroundTintMode();
    }

    @Nullable
    public static Rect getClipBounds(@NonNull View view) {
        return view.getClipBounds();
    }

    @Nullable
    public static Display getDisplay(@NonNull View view) {
        return view.getDisplay();
    }

    public static float getElevation(@NonNull View view) {
        return view.getElevation();
    }

    private static Rect getEmptyTempRect() {
        if (sThreadLocalRect == null) {
            sThreadLocalRect = new ThreadLocal<>();
        }
        Rect rect = sThreadLocalRect.get();
        if (rect == null) {
            rect = new Rect();
            sThreadLocalRect.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static boolean getFitsSystemWindows(@NonNull View view) {
        return view.getFitsSystemWindows();
    }

    public static int getImportantForAccessibility(@NonNull View view) {
        return view.getImportantForAccessibility();
    }

    @SuppressLint({"InlinedApi"})
    public static int getImportantForAutofill(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getImportantForAutofill();
        }
        return 0;
    }

    public static int getLabelFor(@NonNull View view) {
        return view.getLabelFor();
    }

    @Deprecated
    public static int getLayerType(View view) {
        return view.getLayerType();
    }

    public static int getLayoutDirection(@NonNull View view) {
        return view.getLayoutDirection();
    }

    @Nullable
    @Deprecated
    public static Matrix getMatrix(View view) {
        return view.getMatrix();
    }

    @Deprecated
    public static int getMeasuredHeightAndState(View view) {
        return view.getMeasuredHeightAndState();
    }

    @Deprecated
    public static int getMeasuredState(View view) {
        return view.getMeasuredState();
    }

    @Deprecated
    public static int getMeasuredWidthAndState(View view) {
        return view.getMeasuredWidthAndState();
    }

    public static int getMinimumHeight(@NonNull View view) {
        return view.getMinimumHeight();
    }

    public static int getMinimumWidth(@NonNull View view) {
        return view.getMinimumWidth();
    }

    public static int getNextClusterForwardId(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.getNextClusterForwardId();
        }
        return -1;
    }

    @Deprecated
    public static int getOverScrollMode(View view) {
        return view.getOverScrollMode();
    }

    @Px
    public static int getPaddingEnd(@NonNull View view) {
        return view.getPaddingEnd();
    }

    @Px
    public static int getPaddingStart(@NonNull View view) {
        return view.getPaddingStart();
    }

    public static ViewParent getParentForAccessibility(@NonNull View view) {
        return view.getParentForAccessibility();
    }

    @Deprecated
    public static float getPivotX(View view) {
        return view.getPivotX();
    }

    @Deprecated
    public static float getPivotY(View view) {
        return view.getPivotY();
    }

    @Deprecated
    public static float getRotation(View view) {
        return view.getRotation();
    }

    @Deprecated
    public static float getRotationX(View view) {
        return view.getRotationX();
    }

    @Deprecated
    public static float getRotationY(View view) {
        return view.getRotationY();
    }

    @Deprecated
    public static float getScaleX(View view) {
        return view.getScaleX();
    }

    @Deprecated
    public static float getScaleY(View view) {
        return view.getScaleY();
    }

    public static int getScrollIndicators(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getScrollIndicators();
        }
        return 0;
    }

    @Nullable
    public static String getTransitionName(@NonNull View view) {
        return view.getTransitionName();
    }

    @Deprecated
    public static float getTranslationX(View view) {
        return view.getTranslationX();
    }

    @Deprecated
    public static float getTranslationY(View view) {
        return view.getTranslationY();
    }

    public static float getTranslationZ(@NonNull View view) {
        return view.getTranslationZ();
    }

    public static int getWindowSystemUiVisibility(@NonNull View view) {
        return view.getWindowSystemUiVisibility();
    }

    @Deprecated
    public static float getX(View view) {
        return view.getX();
    }

    @Deprecated
    public static float getY(View view) {
        return view.getY();
    }

    public static float getZ(@NonNull View view) {
        return view.getZ();
    }

    public static boolean hasAccessibilityDelegate(@NonNull View view) {
        if (sAccessibilityDelegateCheckFailed) {
            return false;
        }
        if (sAccessibilityDelegateField == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                sAccessibilityDelegateField = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                sAccessibilityDelegateCheckFailed = true;
                return false;
            }
        }
        try {
            return sAccessibilityDelegateField.get(view) != null;
        } catch (Throwable unused2) {
            sAccessibilityDelegateCheckFailed = true;
            return false;
        }
    }

    public static boolean hasExplicitFocusable(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 26 ? view.hasExplicitFocusable() : view.hasFocusable();
    }

    public static boolean hasNestedScrollingParent(@NonNull View view) {
        return view.hasNestedScrollingParent();
    }

    public static boolean hasOnClickListeners(@NonNull View view) {
        return view.hasOnClickListeners();
    }

    public static boolean hasOverlappingRendering(@NonNull View view) {
        return view.hasOverlappingRendering();
    }

    public static boolean hasTransientState(@NonNull View view) {
        return view.hasTransientState();
    }

    public static boolean isAttachedToWindow(@NonNull View view) {
        return view.isAttachedToWindow();
    }

    public static boolean isFocusedByDefault(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.isFocusedByDefault();
        }
        return false;
    }

    public static boolean isImportantForAccessibility(@NonNull View view) {
        return view.isImportantForAccessibility();
    }

    public static boolean isImportantForAutofill(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.isImportantForAutofill();
        }
        return true;
    }

    public static boolean isInLayout(@NonNull View view) {
        return view.isInLayout();
    }

    public static boolean isKeyboardNavigationCluster(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.isKeyboardNavigationCluster();
        }
        return false;
    }

    public static boolean isLaidOut(@NonNull View view) {
        return view.isLaidOut();
    }

    public static boolean isLayoutDirectionResolved(@NonNull View view) {
        return view.isLayoutDirectionResolved();
    }

    public static boolean isNestedScrollingEnabled(@NonNull View view) {
        return view.isNestedScrollingEnabled();
    }

    @Deprecated
    public static boolean isOpaque(View view) {
        return view.isOpaque();
    }

    public static boolean isPaddingRelative(@NonNull View view) {
        return view.isPaddingRelative();
    }

    @Deprecated
    public static void jumpDrawablesToCurrentState(View view) {
        view.jumpDrawablesToCurrentState();
    }

    public static View keyboardNavigationClusterSearch(@NonNull View view, View view2, int i7) {
        if (Build.VERSION.SDK_INT >= 26) {
            return view.keyboardNavigationClusterSearch(view2, i7);
        }
        return null;
    }

    public static void offsetLeftAndRight(@NonNull View view, int i7) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetLeftAndRight(i7);
            return;
        }
        Rect emptyTempRect = getEmptyTempRect();
        boolean z6 = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z6 = !emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        compatOffsetLeftAndRight(view, i7);
        if (z6 && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(emptyTempRect);
        }
    }

    public static void offsetTopAndBottom(@NonNull View view, int i7) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.offsetTopAndBottom(i7);
            return;
        }
        Rect emptyTempRect = getEmptyTempRect();
        boolean z6 = false;
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            emptyTempRect.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z6 = !emptyTempRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
        compatOffsetTopAndBottom(view, i7);
        if (z6 && emptyTempRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(emptyTempRect);
        }
    }

    public static WindowInsetsCompat onApplyWindowInsets(@NonNull View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets = (WindowInsets) WindowInsetsCompat.unwrap(windowInsetsCompat);
        WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        if (windowInsetsOnApplyWindowInsets != windowInsets) {
            windowInsets = new WindowInsets(windowInsetsOnApplyWindowInsets);
        }
        return WindowInsetsCompat.wrap(windowInsets);
    }

    @Deprecated
    public static void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        view.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public static void onInitializeAccessibilityNodeInfo(@NonNull View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat.unwrap());
    }

    @Deprecated
    public static void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        view.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    public static boolean performAccessibilityAction(@NonNull View view, int i7, Bundle bundle) {
        return view.performAccessibilityAction(i7, bundle);
    }

    public static void postInvalidateOnAnimation(@NonNull View view) {
        view.postInvalidateOnAnimation();
    }

    public static void postOnAnimation(@NonNull View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void postOnAnimationDelayed(@NonNull View view, Runnable runnable, long j7) {
        view.postOnAnimationDelayed(runnable, j7);
    }

    public static void removeOnUnhandledKeyEventListener(@NonNull View view, @NonNull OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
        if (Build.VERSION.SDK_INT >= 28) {
            Map map = (Map) view.getTag(C0068R.id.tag_unhandled_key_listeners);
            if (map == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) map.get(onUnhandledKeyEventListenerCompat)) == null) {
                return;
            }
            view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
            return;
        }
        ArrayList arrayList = (ArrayList) view.getTag(C0068R.id.tag_unhandled_key_listeners);
        if (arrayList != null) {
            arrayList.remove(onUnhandledKeyEventListenerCompat);
            if (arrayList.size() == 0) {
                UnhandledKeyEventManager.unregisterListeningView(view);
            }
        }
    }

    public static void requestApplyInsets(@NonNull View view) {
        view.requestApplyInsets();
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull View view, @IdRes int i7) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) view.requireViewById(i7);
        }
        T t6 = (T) view.findViewById(i7);
        if (t6 != null) {
            return t6;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this View");
    }

    @Deprecated
    public static int resolveSizeAndState(int i7, int i8, int i9) {
        return View.resolveSizeAndState(i7, i8, i9);
    }

    public static boolean restoreDefaultFocus(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 26 ? view.restoreDefaultFocus() : view.requestFocus();
    }

    public static void setAccessibilityDelegate(@NonNull View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        view.setAccessibilityDelegate(accessibilityDelegateCompat == null ? null : accessibilityDelegateCompat.getBridge());
    }

    public static void setAccessibilityLiveRegion(@NonNull View view, int i7) {
        view.setAccessibilityLiveRegion(i7);
    }

    @Deprecated
    public static void setActivated(View view, boolean z6) {
        view.setActivated(z6);
    }

    @Deprecated
    public static void setAlpha(View view, @FloatRange(from = 0.0d, m90to = 1.0d) float f7) {
        view.setAlpha(f7);
    }

    public static void setAutofillHints(@NonNull View view, @Nullable String... strArr) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setAutofillHints(strArr);
        }
    }

    public static void setBackground(@NonNull View view, @Nullable Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void setBackgroundTintList(@NonNull View view, ColorStateList colorStateList) {
        int i7 = Build.VERSION.SDK_INT;
        view.setBackgroundTintList(colorStateList);
        if (i7 == 21) {
            Drawable background = view.getBackground();
            boolean z6 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z6) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static void setBackgroundTintMode(@NonNull View view, PorterDuff.Mode mode) {
        int i7 = Build.VERSION.SDK_INT;
        view.setBackgroundTintMode(mode);
        if (i7 == 21) {
            Drawable background = view.getBackground();
            boolean z6 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z6) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    @Deprecated
    public static void setChildrenDrawingOrderEnabled(ViewGroup viewGroup, boolean z6) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (sChildrenDrawingOrderMethod == null) {
            try {
                sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
            }
            sChildrenDrawingOrderMethod.setAccessible(true);
        }
        try {
            sChildrenDrawingOrderMethod.invoke(viewGroup, Boolean.valueOf(z6));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
        }
    }

    public static void setClipBounds(@NonNull View view, Rect rect) {
        view.setClipBounds(rect);
    }

    public static void setElevation(@NonNull View view, float f7) {
        view.setElevation(f7);
    }

    @Deprecated
    public static void setFitsSystemWindows(View view, boolean z6) {
        view.setFitsSystemWindows(z6);
    }

    public static void setFocusedByDefault(@NonNull View view, boolean z6) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setFocusedByDefault(z6);
        }
    }

    public static void setHasTransientState(@NonNull View view, boolean z6) {
        view.setHasTransientState(z6);
    }

    public static void setImportantForAccessibility(@NonNull View view, int i7) {
        view.setImportantForAccessibility(i7);
    }

    public static void setImportantForAutofill(@NonNull View view, int i7) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setImportantForAutofill(i7);
        }
    }

    public static void setKeyboardNavigationCluster(@NonNull View view, boolean z6) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setKeyboardNavigationCluster(z6);
        }
    }

    public static void setLabelFor(@NonNull View view, @IdRes int i7) {
        view.setLabelFor(i7);
    }

    public static void setLayerPaint(@NonNull View view, Paint paint) {
        view.setLayerPaint(paint);
    }

    @Deprecated
    public static void setLayerType(View view, int i7, Paint paint) {
        view.setLayerType(i7, paint);
    }

    public static void setLayoutDirection(@NonNull View view, int i7) {
        view.setLayoutDirection(i7);
    }

    public static void setNestedScrollingEnabled(@NonNull View view, boolean z6) {
        view.setNestedScrollingEnabled(z6);
    }

    public static void setNextClusterForwardId(@NonNull View view, int i7) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setNextClusterForwardId(i7);
        }
    }

    public static void setOnApplyWindowInsetsListener(@NonNull View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        if (onApplyWindowInsetsListener == null) {
            view.setOnApplyWindowInsetsListener(null);
        } else {
            view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: android.support.v4.view.ViewCompat.1
                @Override // android.view.View.OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                    return (WindowInsets) WindowInsetsCompat.unwrap(onApplyWindowInsetsListener.onApplyWindowInsets(view2, WindowInsetsCompat.wrap(windowInsets)));
                }
            });
        }
    }

    @Deprecated
    public static void setOverScrollMode(View view, int i7) {
        view.setOverScrollMode(i7);
    }

    public static void setPaddingRelative(@NonNull View view, @Px int i7, @Px int i8, @Px int i9, @Px int i10) {
        view.setPaddingRelative(i7, i8, i9, i10);
    }

    @Deprecated
    public static void setPivotX(View view, float f7) {
        view.setPivotX(f7);
    }

    @Deprecated
    public static void setPivotY(View view, float f7) {
        view.setPivotY(f7);
    }

    public static void setPointerIcon(@NonNull View view, PointerIconCompat pointerIconCompat) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.setPointerIcon((PointerIcon) (pointerIconCompat != null ? pointerIconCompat.getPointerIcon() : null));
        }
    }

    @Deprecated
    public static void setRotation(View view, float f7) {
        view.setRotation(f7);
    }

    @Deprecated
    public static void setRotationX(View view, float f7) {
        view.setRotationX(f7);
    }

    @Deprecated
    public static void setRotationY(View view, float f7) {
        view.setRotationY(f7);
    }

    @Deprecated
    public static void setSaveFromParentEnabled(View view, boolean z6) {
        view.setSaveFromParentEnabled(z6);
    }

    @Deprecated
    public static void setScaleX(View view, float f7) {
        view.setScaleX(f7);
    }

    @Deprecated
    public static void setScaleY(View view, float f7) {
        view.setScaleY(f7);
    }

    public static void setScrollIndicators(@NonNull View view, int i7) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i7);
        }
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        }
    }

    public static void setTransitionName(@NonNull View view, String str) {
        view.setTransitionName(str);
    }

    @Deprecated
    public static void setTranslationX(View view, float f7) {
        view.setTranslationX(f7);
    }

    @Deprecated
    public static void setTranslationY(View view, float f7) {
        view.setTranslationY(f7);
    }

    public static void setTranslationZ(@NonNull View view, float f7) {
        view.setTranslationZ(f7);
    }

    @Deprecated
    public static void setX(View view, float f7) {
        view.setX(f7);
    }

    @Deprecated
    public static void setY(View view, float f7) {
        view.setY(f7);
    }

    public static void setZ(@NonNull View view, float f7) {
        view.setZ(f7);
    }

    public static boolean startDragAndDrop(@NonNull View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i7) {
        return Build.VERSION.SDK_INT >= 24 ? view.startDragAndDrop(clipData, dragShadowBuilder, obj, i7) : view.startDrag(clipData, dragShadowBuilder, obj, i7);
    }

    public static boolean startNestedScroll(@NonNull View view, int i7) {
        return view.startNestedScroll(i7);
    }

    public static void stopNestedScroll(@NonNull View view) {
        view.stopNestedScroll();
    }

    private static void tickleInvalidationFlag(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }

    public static void updateDragShadow(@NonNull View view, View.DragShadowBuilder dragShadowBuilder) {
        if (Build.VERSION.SDK_INT >= 24) {
            view.updateDragShadow(dragShadowBuilder);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedPreScroll(@NonNull View view, int i7, int i8, @Nullable int[] iArr, @Nullable int[] iArr2, int i9) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedPreScroll(i7, i8, iArr, iArr2, i9);
        }
        if (i9 == 0) {
            return dispatchNestedPreScroll(view, i7, i8, iArr, iArr2);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean dispatchNestedScroll(@NonNull View view, int i7, int i8, int i9, int i10, @Nullable int[] iArr, int i11) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).dispatchNestedScroll(i7, i8, i9, i10, iArr, i11);
        }
        if (i11 == 0) {
            return dispatchNestedScroll(view, i7, i8, i9, i10, iArr);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean hasNestedScrollingParent(@NonNull View view, int i7) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).hasNestedScrollingParent(i7);
            return false;
        }
        if (i7 == 0) {
            return hasNestedScrollingParent(view);
        }
        return false;
    }

    public static void postInvalidateOnAnimation(@NonNull View view, int i7, int i8, int i9, int i10) {
        view.postInvalidateOnAnimation(i7, i8, i9, i10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean startNestedScroll(@NonNull View view, int i7, int i8) {
        if (view instanceof NestedScrollingChild2) {
            return ((NestedScrollingChild2) view).startNestedScroll(i7, i8);
        }
        if (i8 == 0) {
            return startNestedScroll(view, i7);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void stopNestedScroll(@NonNull View view, int i7) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).stopNestedScroll(i7);
        } else if (i7 == 0) {
            stopNestedScroll(view);
        }
    }

    public static void setScrollIndicators(@NonNull View view, int i7, int i8) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i7, i8);
        }
    }
}
