package android.support.v4.app;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.C0063n;
import android.arch.lifecycle.C0065p;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.motion.C0079a;
import android.support.constraint.motion.Key;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import android.support.graphics.drawable.C0116a;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import p009b.C0413b;

/* compiled from: FragmentManager.java */
/* loaded from: classes.dex */
final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
    public static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    public static boolean DEBUG = false;
    public static final String TAG = "FragmentManager";
    public static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    public static final String TARGET_STATE_TAG = "android:target_state";
    public static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    public static final String VIEW_STATE_TAG = "android:view_state";
    public static Field sAnimationListenerField;
    public SparseArray<Fragment> mActive;
    public ArrayList<Integer> mAvailBackStackIndices;
    public ArrayList<BackStackRecord> mBackStack;
    public ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
    public ArrayList<BackStackRecord> mBackStackIndices;
    public FragmentContainer mContainer;
    public ArrayList<Fragment> mCreatedMenus;
    public boolean mDestroyed;
    public boolean mExecutingActions;
    public boolean mHavePendingDeferredStart;
    public FragmentHostCallback mHost;
    public boolean mNeedMenuInvalidate;
    public String mNoTransactionsBecause;
    public Fragment mParent;
    public ArrayList<OpGenerator> mPendingActions;
    public ArrayList<StartEnterTransitionListener> mPostponedTransactions;

    @Nullable
    public Fragment mPrimaryNav;
    public FragmentManagerNonConfig mSavedNonConfig;
    public boolean mStateSaved;
    public boolean mStopped;
    public ArrayList<Fragment> mTmpAddedFragments;
    public ArrayList<Boolean> mTmpIsPop;
    public ArrayList<BackStackRecord> mTmpRecords;
    public static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
    public static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5f);
    public static final Interpolator ACCELERATE_QUINT = new AccelerateInterpolator(2.5f);
    public static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5f);
    public int mNextFragmentIndex = 0;
    public final ArrayList<Fragment> mAdded = new ArrayList<>();
    private final CopyOnWriteArrayList<FragmentLifecycleCallbacksHolder> mLifecycleCallbacks = new CopyOnWriteArrayList<>();
    public int mCurState = 0;
    public Bundle mStateBundle = null;
    public SparseArray<Parcelable> mStateArray = null;
    public Runnable mExecCommit = new Runnable() { // from class: android.support.v4.app.FragmentManagerImpl.1
        @Override // java.lang.Runnable
        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    };

    /* compiled from: FragmentManager.java */
    public static class AnimateOnHWLayerIfNeededListener extends AnimationListenerWrapper {
        public View mView;

        public AnimateOnHWLayerIfNeededListener(View view, Animation.AnimationListener animationListener) {
            super(animationListener);
            this.mView = view;
        }

        @Override // android.support.v4.app.FragmentManagerImpl.AnimationListenerWrapper, android.view.animation.Animation.AnimationListener
        @CallSuper
        public void onAnimationEnd(Animation animation) {
            if (ViewCompat.isAttachedToWindow(this.mView) || Build.VERSION.SDK_INT >= 24) {
                this.mView.post(new Runnable() { // from class: android.support.v4.app.FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnimateOnHWLayerIfNeededListener.this.mView.setLayerType(0, null);
                    }
                });
            } else {
                this.mView.setLayerType(0, null);
            }
            super.onAnimationEnd(animation);
        }
    }

    /* compiled from: FragmentManager.java */
    public static class AnimationListenerWrapper implements Animation.AnimationListener {
        private final Animation.AnimationListener mWrapped;

        public AnimationListenerWrapper(Animation.AnimationListener animationListener) {
            this.mWrapped = animationListener;
        }

        @Override // android.view.animation.Animation.AnimationListener
        @CallSuper
        public void onAnimationEnd(Animation animation) {
            Animation.AnimationListener animationListener = this.mWrapped;
            if (animationListener != null) {
                animationListener.onAnimationEnd(animation);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        @CallSuper
        public void onAnimationRepeat(Animation animation) {
            Animation.AnimationListener animationListener = this.mWrapped;
            if (animationListener != null) {
                animationListener.onAnimationRepeat(animation);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        @CallSuper
        public void onAnimationStart(Animation animation) {
            Animation.AnimationListener animationListener = this.mWrapped;
            if (animationListener != null) {
                animationListener.onAnimationStart(animation);
            }
        }
    }

    /* compiled from: FragmentManager.java */
    public static class AnimatorOnHWLayerIfNeededListener extends AnimatorListenerAdapter {
        public View mView;

        public AnimatorOnHWLayerIfNeededListener(View view) {
            this.mView = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.mView.setLayerType(0, null);
            animator.removeListener(this);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.mView.setLayerType(2, null);
        }
    }

    /* compiled from: FragmentManager.java */
    public static final class FragmentLifecycleCallbacksHolder {
        public final FragmentManager.FragmentLifecycleCallbacks mCallback;
        public final boolean mRecursive;

        public FragmentLifecycleCallbacksHolder(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z6) {
            this.mCallback = fragmentLifecycleCallbacks;
            this.mRecursive = z6;
        }
    }

    /* compiled from: FragmentManager.java */
    public static class FragmentTag {
        public static final int[] Fragment = {R.attr.name, R.attr.id, R.attr.tag};
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        private FragmentTag() {
        }
    }

    /* compiled from: FragmentManager.java */
    public interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* compiled from: FragmentManager.java */
    public class PopBackStackState implements OpGenerator {
        public final int mFlags;
        public final int mId;
        public final String mName;

        public PopBackStackState(String str, int i7, int i8) {
            this.mName = str;
            this.mId = i7;
            this.mFlags = i8;
        }

        @Override // android.support.v4.app.FragmentManagerImpl.OpGenerator
        public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
            FragmentManager fragmentManagerPeekChildFragmentManager;
            Fragment fragment = FragmentManagerImpl.this.mPrimaryNav;
            if (fragment == null || this.mId >= 0 || this.mName != null || (fragmentManagerPeekChildFragmentManager = fragment.peekChildFragmentManager()) == null || !fragmentManagerPeekChildFragmentManager.popBackStackImmediate()) {
                return FragmentManagerImpl.this.popBackStackState(arrayList, arrayList2, this.mName, this.mId, this.mFlags);
            }
            return false;
        }
    }

    /* compiled from: FragmentManager.java */
    public static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
        public final boolean mIsBack;
        private int mNumPostponed;
        public final BackStackRecord mRecord;

        public StartEnterTransitionListener(BackStackRecord backStackRecord, boolean z6) {
            this.mIsBack = z6;
            this.mRecord = backStackRecord;
        }

        public void cancelTransaction() {
            BackStackRecord backStackRecord = this.mRecord;
            backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, false, false);
        }

        public void completeTransaction() {
            boolean z6 = this.mNumPostponed > 0;
            FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
            int size = fragmentManagerImpl.mAdded.size();
            for (int i7 = 0; i7 < size; i7++) {
                Fragment fragment = fragmentManagerImpl.mAdded.get(i7);
                fragment.setOnStartEnterTransitionListener(null);
                if (z6 && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            BackStackRecord backStackRecord = this.mRecord;
            backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, !z6, true);
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        @Override // android.support.v4.app.Fragment.OnStartEnterTransitionListener
        public void onStartEnterTransition() {
            int i7 = this.mNumPostponed - 1;
            this.mNumPostponed = i7;
            if (i7 != 0) {
                return;
            }
            this.mRecord.mManager.scheduleCommit();
        }

        @Override // android.support.v4.app.Fragment.OnStartEnterTransitionListener
        public void startListening() {
            this.mNumPostponed++;
        }
    }

    private void addAddedFragments(ArraySet<Fragment> arraySet) throws NoSuchFieldException, Resources.NotFoundException {
        int i7 = this.mCurState;
        if (i7 < 1) {
            return;
        }
        int iMin = Math.min(i7, 3);
        int size = this.mAdded.size();
        for (int i8 = 0; i8 < size; i8++) {
            Fragment fragment = this.mAdded.get(i8);
            if (fragment.mState < iMin) {
                moveToState(fragment, iMin, fragment.getNextAnim(), fragment.getNextTransition(), false);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    arraySet.add(fragment);
                }
            }
        }
    }

    private void animateRemoveFragment(@NonNull final Fragment fragment, @NonNull AnimationOrAnimator animationOrAnimator, int i7) throws NoSuchFieldException {
        final View view = fragment.mView;
        final ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i7);
        if (animationOrAnimator.animation != null) {
            EndViewTransitionAnimator endViewTransitionAnimator = new EndViewTransitionAnimator(animationOrAnimator.animation, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            endViewTransitionAnimator.setAnimationListener(new AnimationListenerWrapper(getAnimationListener(endViewTransitionAnimator)) { // from class: android.support.v4.app.FragmentManagerImpl.2
                @Override // android.support.v4.app.FragmentManagerImpl.AnimationListenerWrapper, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    viewGroup.post(new Runnable() { // from class: android.support.v4.app.FragmentManagerImpl.2.1
                        @Override // java.lang.Runnable
                        public void run() throws NoSuchFieldException, Resources.NotFoundException {
                            if (fragment.getAnimatingAway() != null) {
                                fragment.setAnimatingAway(null);
                                C01422 c01422 = C01422.this;
                                FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
                                Fragment fragment2 = fragment;
                                fragmentManagerImpl.moveToState(fragment2, fragment2.getStateAfterAnimating(), 0, 0, false);
                            }
                        }
                    });
                }
            });
            setHWLayerAnimListenerIfAlpha(view, animationOrAnimator);
            fragment.mView.startAnimation(endViewTransitionAnimator);
            return;
        }
        Animator animator = animationOrAnimator.animator;
        fragment.setAnimator(animator);
        animator.addListener(new AnimatorListenerAdapter() { // from class: android.support.v4.app.FragmentManagerImpl.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) throws NoSuchFieldException, Resources.NotFoundException {
                viewGroup.endViewTransition(view);
                Animator animator3 = fragment.getAnimator();
                fragment.setAnimator(null);
                if (animator3 == null || viewGroup.indexOfChild(view) >= 0) {
                    return;
                }
                FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
                Fragment fragment2 = fragment;
                fragmentManagerImpl.moveToState(fragment2, fragment2.getStateAfterAnimating(), 0, 0, false);
            }
        });
        animator.setTarget(fragment.mView);
        setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimator);
        animator.start();
    }

    private void burpActive() {
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray != null) {
            for (int size = sparseArray.size() - 1; size >= 0; size--) {
                if (this.mActive.valueAt(size) == null) {
                    SparseArray<Fragment> sparseArray2 = this.mActive;
                    sparseArray2.delete(sparseArray2.keyAt(size));
                }
            }
        }
    }

    private void checkStateLoss() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.mNoTransactionsBecause == null) {
            return;
        }
        StringBuilder sbM112a = C0413b.m112a("Can not perform this action inside of ");
        sbM112a.append(this.mNoTransactionsBecause);
        throw new IllegalStateException(sbM112a.toString());
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    private void dispatchStateChange(int i7) {
        try {
            this.mExecutingActions = true;
            moveToState(i7, false);
            this.mExecutingActions = false;
            execPendingActions();
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    private void endAnimatingAwayFragments() throws NoSuchFieldException, Resources.NotFoundException {
        SparseArray<Fragment> sparseArray = this.mActive;
        int size = sparseArray == null ? 0 : sparseArray.size();
        for (int i7 = 0; i7 < size; i7++) {
            Fragment fragmentValueAt = this.mActive.valueAt(i7);
            if (fragmentValueAt != null) {
                if (fragmentValueAt.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragmentValueAt.getStateAfterAnimating();
                    View animatingAway = fragmentValueAt.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    fragmentValueAt.setAnimatingAway(null);
                    moveToState(fragmentValueAt, stateAfterAnimating, 0, 0, false);
                } else if (fragmentValueAt.getAnimator() != null) {
                    fragmentValueAt.getAnimator().end();
                }
            }
        }
    }

    private void ensureExecReady(boolean z6) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z6) {
            checkStateLoss();
        }
        if (this.mTmpRecords == null) {
            this.mTmpRecords = new ArrayList<>();
            this.mTmpIsPop = new ArrayList<>();
        }
        this.mExecutingActions = true;
        try {
            executePostponedTransaction(null, null);
        } finally {
            this.mExecutingActions = false;
        }
    }

    private static void executeOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i7, int i8) {
        while (i7 < i8) {
            BackStackRecord backStackRecord = arrayList.get(i7);
            if (arrayList2.get(i7).booleanValue()) {
                backStackRecord.bumpBackStackNesting(-1);
                backStackRecord.executePopOps(i7 == i8 + (-1));
            } else {
                backStackRecord.bumpBackStackNesting(1);
                backStackRecord.executeOps();
            }
            i7++;
        }
    }

    private void executeOpsTogether(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i7, int i8) throws NoSuchFieldException, Resources.NotFoundException {
        int i9;
        int i10;
        int i11 = i7;
        boolean z6 = arrayList.get(i11).mReorderingAllowed;
        ArrayList<Fragment> arrayList3 = this.mTmpAddedFragments;
        if (arrayList3 == null) {
            this.mTmpAddedFragments = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.mTmpAddedFragments.addAll(this.mAdded);
        Fragment primaryNavigationFragment = getPrimaryNavigationFragment();
        boolean z7 = false;
        for (int i12 = i11; i12 < i8; i12++) {
            BackStackRecord backStackRecord = arrayList.get(i12);
            primaryNavigationFragment = !arrayList2.get(i12).booleanValue() ? backStackRecord.expandOps(this.mTmpAddedFragments, primaryNavigationFragment) : backStackRecord.trackAddedFragmentsInPop(this.mTmpAddedFragments, primaryNavigationFragment);
            z7 = z7 || backStackRecord.mAddToBackStack;
        }
        this.mTmpAddedFragments.clear();
        if (!z6) {
            FragmentTransition.startTransitions(this, arrayList, arrayList2, i7, i8, false);
        }
        executeOps(arrayList, arrayList2, i7, i8);
        if (z6) {
            ArraySet<Fragment> arraySet = new ArraySet<>();
            addAddedFragments(arraySet);
            int iPostponePostponableTransactions = postponePostponableTransactions(arrayList, arrayList2, i7, i8, arraySet);
            makeRemovedFragmentsInvisible(arraySet);
            i9 = iPostponePostponableTransactions;
        } else {
            i9 = i8;
        }
        if (i9 != i11 && z6) {
            FragmentTransition.startTransitions(this, arrayList, arrayList2, i7, i9, true);
            moveToState(this.mCurState, true);
        }
        while (i11 < i8) {
            BackStackRecord backStackRecord2 = arrayList.get(i11);
            if (arrayList2.get(i11).booleanValue() && (i10 = backStackRecord2.mIndex) >= 0) {
                freeBackStackIndex(i10);
                backStackRecord2.mIndex = -1;
            }
            backStackRecord2.runOnCommitRunnables();
            i11++;
        }
        if (z7) {
            reportBackStackChanged();
        }
    }

    private void executePostponedTransaction(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<StartEnterTransitionListener> arrayList3 = this.mPostponedTransactions;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i7 = 0;
        while (i7 < size) {
            StartEnterTransitionListener startEnterTransitionListener = this.mPostponedTransactions.get(i7);
            if (arrayList != null && !startEnterTransitionListener.mIsBack && (iIndexOf2 = arrayList.indexOf(startEnterTransitionListener.mRecord)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                startEnterTransitionListener.cancelTransaction();
            } else if (startEnterTransitionListener.isReady() || (arrayList != null && startEnterTransitionListener.mRecord.interactsWith(arrayList, 0, arrayList.size()))) {
                this.mPostponedTransactions.remove(i7);
                i7--;
                size--;
                if (arrayList == null || startEnterTransitionListener.mIsBack || (iIndexOf = arrayList.indexOf(startEnterTransitionListener.mRecord)) == -1 || !arrayList2.get(iIndexOf).booleanValue()) {
                    startEnterTransitionListener.completeTransaction();
                } else {
                    startEnterTransitionListener.cancelTransaction();
                }
            }
            i7++;
        }
    }

    private Fragment findFragmentUnder(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.mAdded.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.mAdded.get(iIndexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    private void forcePostponedTransactions() {
        if (this.mPostponedTransactions != null) {
            while (!this.mPostponedTransactions.isEmpty()) {
                this.mPostponedTransactions.remove(0).completeTransaction();
            }
        }
    }

    private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            ArrayList<OpGenerator> arrayList3 = this.mPendingActions;
            if (arrayList3 != null && arrayList3.size() != 0) {
                int size = this.mPendingActions.size();
                boolean zGenerateOps = false;
                for (int i7 = 0; i7 < size; i7++) {
                    zGenerateOps |= this.mPendingActions.get(i7).generateOps(arrayList, arrayList2);
                }
                this.mPendingActions.clear();
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                return zGenerateOps;
            }
            return false;
        }
    }

    private static Animation.AnimationListener getAnimationListener(Animation animation) throws NoSuchFieldException {
        try {
            if (sAnimationListenerField == null) {
                Field declaredField = Animation.class.getDeclaredField("mListener");
                sAnimationListenerField = declaredField;
                declaredField.setAccessible(true);
            }
            return (Animation.AnimationListener) sAnimationListenerField.get(animation);
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }

    public static AnimationOrAnimator makeFadeAnimation(Context context, float f7, float f8) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f7, f8);
        alphaAnimation.setInterpolator(DECELERATE_CUBIC);
        alphaAnimation.setDuration(220L);
        return new AnimationOrAnimator(alphaAnimation);
    }

    public static AnimationOrAnimator makeOpenCloseAnimation(Context context, float f7, float f8, float f9, float f10) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f7, f8, f7, f8, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(DECELERATE_QUINT);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f9, f10);
        alphaAnimation.setInterpolator(DECELERATE_CUBIC);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new AnimationOrAnimator(animationSet);
    }

    private void makeRemovedFragmentsInvisible(ArraySet<Fragment> arraySet) {
        int size = arraySet.size();
        for (int i7 = 0; i7 < size; i7++) {
            Fragment fragmentValueAt = arraySet.valueAt(i7);
            if (!fragmentValueAt.mAdded) {
                View view = fragmentValueAt.getView();
                fragmentValueAt.mPostponedAlpha = view.getAlpha();
                view.setAlpha(0.0f);
            }
        }
    }

    public static boolean modifiesAlpha(AnimationOrAnimator animationOrAnimator) {
        Animation animation = animationOrAnimator.animation;
        if (animation instanceof AlphaAnimation) {
            return true;
        }
        if (!(animation instanceof AnimationSet)) {
            return modifiesAlpha(animationOrAnimator.animator);
        }
        List<Animation> animations = ((AnimationSet) animation).getAnimations();
        for (int i7 = 0; i7 < animations.size(); i7++) {
            if (animations.get(i7) instanceof AlphaAnimation) {
                return true;
            }
        }
        return false;
    }

    private int postponePostponableTransactions(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i7, int i8, ArraySet<Fragment> arraySet) throws NoSuchFieldException, Resources.NotFoundException {
        int i9 = i8;
        for (int i10 = i8 - 1; i10 >= i7; i10--) {
            BackStackRecord backStackRecord = arrayList.get(i10);
            boolean zBooleanValue = arrayList2.get(i10).booleanValue();
            if (backStackRecord.isPostponed() && !backStackRecord.interactsWith(arrayList, i10 + 1, i8)) {
                if (this.mPostponedTransactions == null) {
                    this.mPostponedTransactions = new ArrayList<>();
                }
                StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, zBooleanValue);
                this.mPostponedTransactions.add(startEnterTransitionListener);
                backStackRecord.setOnStartPostponedListener(startEnterTransitionListener);
                if (zBooleanValue) {
                    backStackRecord.executeOps();
                } else {
                    backStackRecord.executePopOps(false);
                }
                i9--;
                if (i10 != i9) {
                    arrayList.remove(i10);
                    arrayList.add(i9, backStackRecord);
                }
                addAddedFragments(arraySet);
            }
        }
        return i9;
    }

    private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) throws NoSuchFieldException, Resources.NotFoundException {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        executePostponedTransaction(arrayList, arrayList2);
        int size = arrayList.size();
        int i7 = 0;
        int i8 = 0;
        while (i7 < size) {
            if (!arrayList.get(i7).mReorderingAllowed) {
                if (i8 != i7) {
                    executeOpsTogether(arrayList, arrayList2, i8, i7);
                }
                i8 = i7 + 1;
                if (arrayList2.get(i7).booleanValue()) {
                    while (i8 < size && arrayList2.get(i8).booleanValue() && !arrayList.get(i8).mReorderingAllowed) {
                        i8++;
                    }
                }
                executeOpsTogether(arrayList, arrayList2, i7, i8);
                i7 = i8 - 1;
            }
            i7++;
        }
        if (i8 != size) {
            executeOpsTogether(arrayList, arrayList2, i8, size);
        }
    }

    public static int reverseTransit(int i7) {
        if (i7 == 4097) {
            return 8194;
        }
        if (i7 == 4099) {
            return FragmentTransaction.TRANSIT_FRAGMENT_FADE;
        }
        if (i7 != 8194) {
            return 0;
        }
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    private static void setHWLayerAnimListenerIfAlpha(View view, AnimationOrAnimator animationOrAnimator) throws NoSuchFieldException {
        if (view == null || animationOrAnimator == null || !shouldRunOnHWLayer(view, animationOrAnimator)) {
            return;
        }
        Animator animator = animationOrAnimator.animator;
        if (animator != null) {
            animator.addListener(new AnimatorOnHWLayerIfNeededListener(view));
            return;
        }
        Animation.AnimationListener animationListener = getAnimationListener(animationOrAnimator.animation);
        view.setLayerType(2, null);
        animationOrAnimator.animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(view, animationListener));
    }

    private static void setRetaining(FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (fragmentManagerNonConfig == null) {
            return;
        }
        List<Fragment> fragments = fragmentManagerNonConfig.getFragments();
        if (fragments != null) {
            Iterator<Fragment> it = fragments.iterator();
            while (it.hasNext()) {
                it.next().mRetaining = true;
            }
        }
        List<FragmentManagerNonConfig> childNonConfigs = fragmentManagerNonConfig.getChildNonConfigs();
        if (childNonConfigs != null) {
            Iterator<FragmentManagerNonConfig> it2 = childNonConfigs.iterator();
            while (it2.hasNext()) {
                setRetaining(it2.next());
            }
        }
    }

    public static boolean shouldRunOnHWLayer(View view, AnimationOrAnimator animationOrAnimator) {
        return view != null && animationOrAnimator != null && view.getLayerType() == 0 && ViewCompat.hasOverlappingRendering(view) && modifiesAlpha(animationOrAnimator);
    }

    private void throwException(RuntimeException runtimeException) {
        runtimeException.getMessage();
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        FragmentHostCallback fragmentHostCallback = this.mHost;
        try {
            if (fragmentHostCallback != null) {
                fragmentHostCallback.onDump("  ", null, printWriter, new String[0]);
            } else {
                dump("  ", null, printWriter, new String[0]);
            }
            throw runtimeException;
        } catch (Exception unused) {
            throw runtimeException;
        }
    }

    public static int transitToStyleIndex(int i7, boolean z6) {
        if (i7 == 4097) {
            return z6 ? 1 : 2;
        }
        if (i7 == 4099) {
            return z6 ? 5 : 6;
        }
        if (i7 != 8194) {
            return -1;
        }
        return z6 ? 3 : 4;
    }

    public void addBackStackState(BackStackRecord backStackRecord) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList<>();
        }
        this.mBackStack.add(backStackRecord);
    }

    public void addFragment(Fragment fragment, boolean z6) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("add: ");
            sb.append(fragment);
        }
        makeActive(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (this.mAdded.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.mAdded) {
            this.mAdded.add(fragment);
        }
        fragment.mAdded = true;
        fragment.mRemoving = false;
        if (fragment.mView == null) {
            fragment.mHiddenChanged = false;
        }
        if (fragment.mHasMenu && fragment.mMenuVisible) {
            this.mNeedMenuInvalidate = true;
        }
        if (z6) {
            moveToState(fragment);
        }
    }

    @Override // android.support.v4.app.FragmentManager
    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onBackStackChangedListener) {
        if (this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList<>();
        }
        this.mBackStackChangeListeners.add(onBackStackChangedListener);
    }

    public int allocBackStackIndex(BackStackRecord backStackRecord) {
        synchronized (this) {
            ArrayList<Integer> arrayList = this.mAvailBackStackIndices;
            if (arrayList != null && arrayList.size() > 0) {
                int iIntValue = this.mAvailBackStackIndices.remove(r0.size() - 1).intValue();
                if (DEBUG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Adding back stack index ");
                    sb.append(iIntValue);
                    sb.append(" with ");
                    sb.append(backStackRecord);
                }
                this.mBackStackIndices.set(iIntValue, backStackRecord);
                return iIntValue;
            }
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<>();
            }
            int size = this.mBackStackIndices.size();
            if (DEBUG) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Setting back stack index ");
                sb2.append(size);
                sb2.append(" to ");
                sb2.append(backStackRecord);
            }
            this.mBackStackIndices.add(backStackRecord);
            return size;
        }
    }

    public void attachController(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment) {
        if (this.mHost != null) {
            throw new IllegalStateException("Already attached");
        }
        this.mHost = fragmentHostCallback;
        this.mContainer = fragmentContainer;
        this.mParent = fragment;
    }

    public void attachFragment(Fragment fragment) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("attach: ");
            sb.append(fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (this.mAdded.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            if (DEBUG) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("add from attach: ");
                sb2.append(fragment);
            }
            synchronized (this.mAdded) {
                this.mAdded.add(fragment);
            }
            fragment.mAdded = true;
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
        }
    }

    @Override // android.support.v4.app.FragmentManager
    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    public void completeExecute(BackStackRecord backStackRecord, boolean z6, boolean z7, boolean z8) {
        if (z6) {
            backStackRecord.executePopOps(z8);
        } else {
            backStackRecord.executeOps();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(backStackRecord);
        arrayList2.add(Boolean.valueOf(z6));
        if (z7) {
            FragmentTransition.startTransitions(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z8) {
            moveToState(this.mCurState, true);
        }
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i7 = 0; i7 < size; i7++) {
                Fragment fragmentValueAt = this.mActive.valueAt(i7);
                if (fragmentValueAt != null && fragmentValueAt.mView != null && fragmentValueAt.mIsNewlyAdded && backStackRecord.interactsWith(fragmentValueAt.mContainerId)) {
                    float f7 = fragmentValueAt.mPostponedAlpha;
                    if (f7 > 0.0f) {
                        fragmentValueAt.mView.setAlpha(f7);
                    }
                    if (z8) {
                        fragmentValueAt.mPostponedAlpha = 0.0f;
                    } else {
                        fragmentValueAt.mPostponedAlpha = -1.0f;
                        fragmentValueAt.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    public void completeShowHideFragment(final Fragment fragment) throws NoSuchFieldException, Resources.NotFoundException {
        Animator animator;
        if (fragment.mView != null) {
            AnimationOrAnimator animationOrAnimatorLoadAnimation = loadAnimation(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (animationOrAnimatorLoadAnimation == null || (animator = animationOrAnimatorLoadAnimation.animator) == null) {
                if (animationOrAnimatorLoadAnimation != null) {
                    setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimatorLoadAnimation);
                    fragment.mView.startAnimation(animationOrAnimatorLoadAnimation.animation);
                    animationOrAnimatorLoadAnimation.animation.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    final ViewGroup viewGroup = fragment.mContainer;
                    final View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    animationOrAnimatorLoadAnimation.animator.addListener(new AnimatorListenerAdapter() { // from class: android.support.v4.app.FragmentManagerImpl.4
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator2) {
                            viewGroup.endViewTransition(view);
                            animator2.removeListener(this);
                            View view2 = fragment.mView;
                            if (view2 != null) {
                                view2.setVisibility(8);
                            }
                        }
                    });
                }
                setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimatorLoadAnimation);
                animationOrAnimatorLoadAnimation.animator.start();
            }
        }
        if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
            this.mNeedMenuInvalidate = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    public void detachFragment(Fragment fragment) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("detach: ");
            sb.append(fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (DEBUG) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("remove from detach: ");
                sb2.append(fragment);
            }
            synchronized (this.mAdded) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
        }
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(2);
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        for (int i7 = 0; i7 < this.mAdded.size(); i7++) {
            Fragment fragment = this.mAdded.get(i7);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i7 = 0; i7 < this.mAdded.size(); i7++) {
            Fragment fragment = this.mAdded.get(i7);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mCurState < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z6 = false;
        for (int i7 = 0; i7 < this.mAdded.size(); i7++) {
            Fragment fragment = this.mAdded.get(i7);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z6 = true;
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i8 = 0; i8 < this.mCreatedMenus.size(); i8++) {
                Fragment fragment2 = this.mCreatedMenus.get(i8);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return z6;
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions();
        dispatchStateChange(0);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
    }

    public void dispatchDestroyView() {
        dispatchStateChange(1);
    }

    public void dispatchLowMemory() {
        for (int i7 = 0; i7 < this.mAdded.size(); i7++) {
            Fragment fragment = this.mAdded.get(i7);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z6) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z6);
            }
        }
    }

    public void dispatchOnFragmentActivityCreated(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentActivityCreated(fragment, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentActivityCreated(this, fragment, bundle);
            }
        }
    }

    public void dispatchOnFragmentAttached(@NonNull Fragment fragment, @NonNull Context context, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentAttached(fragment, context, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentAttached(this, fragment, context);
            }
        }
    }

    public void dispatchOnFragmentCreated(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentCreated(fragment, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentCreated(this, fragment, bundle);
            }
        }
    }

    public void dispatchOnFragmentDestroyed(@NonNull Fragment fragment, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentDestroyed(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentDestroyed(this, fragment);
            }
        }
    }

    public void dispatchOnFragmentDetached(@NonNull Fragment fragment, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentDetached(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentDetached(this, fragment);
            }
        }
    }

    public void dispatchOnFragmentPaused(@NonNull Fragment fragment, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentPaused(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentPaused(this, fragment);
            }
        }
    }

    public void dispatchOnFragmentPreAttached(@NonNull Fragment fragment, @NonNull Context context, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentPreAttached(fragment, context, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentPreAttached(this, fragment, context);
            }
        }
    }

    public void dispatchOnFragmentPreCreated(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentPreCreated(fragment, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentPreCreated(this, fragment, bundle);
            }
        }
    }

    public void dispatchOnFragmentResumed(@NonNull Fragment fragment, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentResumed(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentResumed(this, fragment);
            }
        }
    }

    public void dispatchOnFragmentSaveInstanceState(@NonNull Fragment fragment, @NonNull Bundle bundle, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentSaveInstanceState(fragment, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentSaveInstanceState(this, fragment, bundle);
            }
        }
    }

    public void dispatchOnFragmentStarted(@NonNull Fragment fragment, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentStarted(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentStarted(this, fragment);
            }
        }
    }

    public void dispatchOnFragmentStopped(@NonNull Fragment fragment, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentStopped(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentStopped(this, fragment);
            }
        }
    }

    public void dispatchOnFragmentViewCreated(@NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentViewCreated(fragment, view, bundle, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentViewCreated(this, fragment, view, bundle);
            }
        }
    }

    public void dispatchOnFragmentViewDestroyed(@NonNull Fragment fragment, boolean z6) {
        Fragment fragment2 = this.mParent;
        if (fragment2 != null) {
            FragmentManager fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) fragmentManager).dispatchOnFragmentViewDestroyed(fragment, true);
            }
        }
        Iterator<FragmentLifecycleCallbacksHolder> it = this.mLifecycleCallbacks.iterator();
        while (it.hasNext()) {
            FragmentLifecycleCallbacksHolder next = it.next();
            if (!z6 || next.mRecursive) {
                next.mCallback.onFragmentViewDestroyed(this, fragment);
            }
        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (int i7 = 0; i7 < this.mAdded.size(); i7++) {
            Fragment fragment = this.mAdded.get(i7);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mCurState < 1) {
            return;
        }
        for (int i7 = 0; i7 < this.mAdded.size(); i7++) {
            Fragment fragment = this.mAdded.get(i7);
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public void dispatchPause() {
        dispatchStateChange(3);
    }

    public void dispatchPictureInPictureModeChanged(boolean z6) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z6);
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        if (this.mCurState < 1) {
            return false;
        }
        boolean z6 = false;
        for (int i7 = 0; i7 < this.mAdded.size(); i7++) {
            Fragment fragment = this.mAdded.get(i7);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z6 = true;
            }
        }
        return z6;
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(4);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        dispatchStateChange(3);
    }

    public void dispatchStop() {
        this.mStopped = true;
        dispatchStateChange(2);
    }

    public void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    @Override // android.support.v4.app.FragmentManager
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        String strM88a = C0063n.m88a(str, "    ");
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray != null && (size5 = sparseArray.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i7 = 0; i7 < size5; i7++) {
                Fragment fragmentValueAt = this.mActive.valueAt(i7);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println(fragmentValueAt);
                if (fragmentValueAt != null) {
                    fragmentValueAt.dump(strM88a, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size6 = this.mAdded.size();
        if (size6 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i8 = 0; i8 < size6; i8++) {
                Fragment fragment = this.mAdded.get(i8);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i8);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.mCreatedMenus;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i9 = 0; i9 < size4; i9++) {
                Fragment fragment2 = this.mCreatedMenus.get(i9);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i9);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<BackStackRecord> arrayList2 = this.mBackStack;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i10 = 0; i10 < size3; i10++) {
                BackStackRecord backStackRecord = this.mBackStack.get(i10);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i10);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.dump(strM88a, fileDescriptor, printWriter, strArr);
            }
        }
        synchronized (this) {
            ArrayList<BackStackRecord> arrayList3 = this.mBackStackIndices;
            if (arrayList3 != null && (size2 = arrayList3.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i11 = 0; i11 < size2; i11++) {
                    Object obj = (BackStackRecord) this.mBackStackIndices.get(i11);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i11);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            ArrayList<Integer> arrayList4 = this.mAvailBackStackIndices;
            if (arrayList4 != null && arrayList4.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        ArrayList<OpGenerator> arrayList5 = this.mPendingActions;
        if (arrayList5 != null && (size = arrayList5.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i12 = 0; i12 < size; i12++) {
                Object obj2 = (OpGenerator) this.mPendingActions.get(i12);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i12);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
        if (this.mNoTransactionsBecause != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.mNoTransactionsBecause);
        }
    }

    public void enqueueAction(OpGenerator opGenerator, boolean z6) {
        if (!z6) {
            checkStateLoss();
        }
        synchronized (this) {
            if (!this.mDestroyed && this.mHost != null) {
                if (this.mPendingActions == null) {
                    this.mPendingActions = new ArrayList<>();
                }
                this.mPendingActions.add(opGenerator);
                scheduleCommit();
                return;
            }
            if (!z6) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public void ensureInflatedFragmentView(Fragment fragment) {
        if (!fragment.mFromLayout || fragment.mPerformedCreateView) {
            return;
        }
        fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
        View view = fragment.mView;
        if (view == null) {
            fragment.mInnerView = null;
            return;
        }
        fragment.mInnerView = view;
        view.setSaveFromParentEnabled(false);
        if (fragment.mHidden) {
            fragment.mView.setVisibility(8);
        }
        fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
        dispatchOnFragmentViewCreated(fragment, fragment.mView, fragment.mSavedFragmentState, false);
    }

    public boolean execPendingActions() {
        ensureExecReady(true);
        boolean z6 = false;
        while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                cleanupExec();
                z6 = true;
            } catch (Throwable th) {
                cleanupExec();
                throw th;
            }
        }
        doPendingDeferredStart();
        burpActive();
        return z6;
    }

    public void execSingleAction(OpGenerator opGenerator, boolean z6) {
        if (z6 && (this.mHost == null || this.mDestroyed)) {
            return;
        }
        ensureExecReady(z6);
        if (opGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        doPendingDeferredStart();
        burpActive();
    }

    @Override // android.support.v4.app.FragmentManager
    public boolean executePendingTransactions() {
        boolean zExecPendingActions = execPendingActions();
        forcePostponedTransactions();
        return zExecPendingActions;
    }

    @Override // android.support.v4.app.FragmentManager
    @Nullable
    public Fragment findFragmentById(int i7) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = this.mAdded.get(size);
            if (fragment != null && fragment.mFragmentId == i7) {
                return fragment;
            }
        }
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            Fragment fragmentValueAt = this.mActive.valueAt(size2);
            if (fragmentValueAt != null && fragmentValueAt.mFragmentId == i7) {
                return fragmentValueAt;
            }
        }
        return null;
    }

    @Override // android.support.v4.app.FragmentManager
    @Nullable
    public Fragment findFragmentByTag(@Nullable String str) {
        if (str != null) {
            for (int size = this.mAdded.size() - 1; size >= 0; size--) {
                Fragment fragment = this.mAdded.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            Fragment fragmentValueAt = this.mActive.valueAt(size2);
            if (fragmentValueAt != null && str.equals(fragmentValueAt.mTag)) {
                return fragmentValueAt;
            }
        }
        return null;
    }

    public Fragment findFragmentByWho(String str) {
        Fragment fragmentFindFragmentByWho;
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null || str == null) {
            return null;
        }
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            Fragment fragmentValueAt = this.mActive.valueAt(size);
            if (fragmentValueAt != null && (fragmentFindFragmentByWho = fragmentValueAt.findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    public void freeBackStackIndex(int i7) {
        synchronized (this) {
            this.mBackStackIndices.set(i7, null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList<>();
            }
            boolean z6 = DEBUG;
            this.mAvailBackStackIndices.add(Integer.valueOf(i7));
        }
    }

    public int getActiveFragmentCount() {
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null) {
            return 0;
        }
        return sparseArray.size();
    }

    public List<Fragment> getActiveFragments() {
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null) {
            return null;
        }
        int size = sparseArray.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i7 = 0; i7 < size; i7++) {
            arrayList.add(this.mActive.valueAt(i7));
        }
        return arrayList;
    }

    @Override // android.support.v4.app.FragmentManager
    public FragmentManager.BackStackEntry getBackStackEntryAt(int i7) {
        return this.mBackStack.get(i7);
    }

    @Override // android.support.v4.app.FragmentManager
    public int getBackStackEntryCount() {
        ArrayList<BackStackRecord> arrayList = this.mBackStack;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // android.support.v4.app.FragmentManager
    @Nullable
    public Fragment getFragment(Bundle bundle, String str) {
        int i7 = bundle.getInt(str, -1);
        if (i7 == -1) {
            return null;
        }
        Fragment fragment = this.mActive.get(i7);
        if (fragment == null) {
            throwException(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i7));
        }
        return fragment;
    }

    @Override // android.support.v4.app.FragmentManager
    public List<Fragment> getFragments() {
        List<Fragment> list;
        if (this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.mAdded) {
            list = (List) this.mAdded.clone();
        }
        return list;
    }

    public LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return this;
    }

    @Override // android.support.v4.app.FragmentManager
    @Nullable
    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    public void hideFragment(Fragment fragment) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("hide: ");
            sb.append(fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
    }

    @Override // android.support.v4.app.FragmentManager
    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public boolean isStateAtLeast(int i7) {
        return this.mCurState >= i7;
    }

    @Override // android.support.v4.app.FragmentManager
    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    public AnimationOrAnimator loadAnimation(Fragment fragment, int i7, boolean z6, int i8) throws Resources.NotFoundException {
        int iTransitToStyleIndex;
        int nextAnim = fragment.getNextAnim();
        Animation animationOnCreateAnimation = fragment.onCreateAnimation(i7, z6, nextAnim);
        if (animationOnCreateAnimation != null) {
            return new AnimationOrAnimator(animationOnCreateAnimation);
        }
        Animator animatorOnCreateAnimator = fragment.onCreateAnimator(i7, z6, nextAnim);
        if (animatorOnCreateAnimator != null) {
            return new AnimationOrAnimator(animatorOnCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean zEquals = "anim".equals(this.mHost.getContext().getResources().getResourceTypeName(nextAnim));
            boolean z7 = false;
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mHost.getContext(), nextAnim);
                    if (animationLoadAnimation != null) {
                        return new AnimationOrAnimator(animationLoadAnimation);
                    }
                    z7 = true;
                } catch (Resources.NotFoundException e7) {
                    throw e7;
                } catch (RuntimeException unused) {
                }
            }
            if (!z7) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.mHost.getContext(), nextAnim);
                    if (animatorLoadAnimator != null) {
                        return new AnimationOrAnimator(animatorLoadAnimator);
                    }
                } catch (RuntimeException e8) {
                    if (zEquals) {
                        throw e8;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.mHost.getContext(), nextAnim);
                    if (animationLoadAnimation2 != null) {
                        return new AnimationOrAnimator(animationLoadAnimation2);
                    }
                }
            }
        }
        if (i7 == 0 || (iTransitToStyleIndex = transitToStyleIndex(i7, z6)) < 0) {
            return null;
        }
        switch (iTransitToStyleIndex) {
            case 1:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return makeOpenCloseAnimation(this.mHost.getContext(), 0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return makeFadeAnimation(this.mHost.getContext(), 0.0f, 1.0f);
            case 6:
                return makeFadeAnimation(this.mHost.getContext(), 1.0f, 0.0f);
            default:
                if (i8 != 0 || !this.mHost.onHasWindowAnimations()) {
                    return null;
                }
                this.mHost.onGetWindowAnimations();
                return null;
        }
    }

    public void makeActive(Fragment fragment) {
        if (fragment.mIndex >= 0) {
            return;
        }
        int i7 = this.mNextFragmentIndex;
        this.mNextFragmentIndex = i7 + 1;
        fragment.setIndex(i7, this.mParent);
        if (this.mActive == null) {
            this.mActive = new SparseArray<>();
        }
        this.mActive.put(fragment.mIndex, fragment);
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("Allocated fragment index ");
            sb.append(fragment);
        }
    }

    public void makeInactive(Fragment fragment) {
        if (fragment.mIndex < 0) {
            return;
        }
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("Freeing fragment index ");
            sb.append(fragment);
        }
        this.mActive.put(fragment.mIndex, null);
        fragment.initState();
    }

    public void moveFragmentToExpectedState(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        int iMin = this.mCurState;
        if (fragment.mRemoving) {
            iMin = fragment.isInBackStack() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        moveToState(fragment, iMin, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
        if (fragment.mView != null) {
            Fragment fragmentFindFragmentUnder = findFragmentUnder(fragment);
            if (fragmentFindFragmentUnder != null) {
                View view = fragmentFindFragmentUnder.mView;
                ViewGroup viewGroup = fragment.mContainer;
                int iIndexOfChild = viewGroup.indexOfChild(view);
                int iIndexOfChild2 = viewGroup.indexOfChild(fragment.mView);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup.removeViewAt(iIndexOfChild2);
                    viewGroup.addView(fragment.mView, iIndexOfChild);
                }
            }
            if (fragment.mIsNewlyAdded && fragment.mContainer != null) {
                float f7 = fragment.mPostponedAlpha;
                if (f7 > 0.0f) {
                    fragment.mView.setAlpha(f7);
                }
                fragment.mPostponedAlpha = 0.0f;
                fragment.mIsNewlyAdded = false;
                AnimationOrAnimator animationOrAnimatorLoadAnimation = loadAnimation(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                if (animationOrAnimatorLoadAnimation != null) {
                    setHWLayerAnimListenerIfAlpha(fragment.mView, animationOrAnimatorLoadAnimation);
                    Animation animation = animationOrAnimatorLoadAnimation.animation;
                    if (animation != null) {
                        fragment.mView.startAnimation(animation);
                    } else {
                        animationOrAnimatorLoadAnimation.animator.setTarget(fragment.mView);
                        animationOrAnimatorLoadAnimation.animator.start();
                    }
                }
            }
        }
        if (fragment.mHiddenChanged) {
            completeShowHideFragment(fragment);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:222:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void moveToState(android.support.v4.app.Fragment r16, int r17, int r18, int r19, boolean r20) throws java.lang.NoSuchFieldException, android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 1000
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.moveToState(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    public void noteStateNotSaved() {
        this.mSavedNonConfig = null;
        this.mStateSaved = false;
        this.mStopped = false;
        int size = this.mAdded.size();
        for (int i7 = 0; i7 < size; i7++) {
            Fragment fragment = this.mAdded.get(i7);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) throws NoSuchFieldException, Resources.NotFoundException {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FragmentTag.Fragment);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), str2)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        Fragment fragmentFindFragmentById = resourceId != -1 ? findFragmentById(resourceId) : null;
        if (fragmentFindFragmentById == null && string != null) {
            fragmentFindFragmentById = findFragmentByTag(string);
        }
        if (fragmentFindFragmentById == null && id != -1) {
            fragmentFindFragmentById = findFragmentById(id);
        }
        if (DEBUG) {
            StringBuilder sbM112a = C0413b.m112a("onCreateView: id=0x");
            sbM112a.append(Integer.toHexString(resourceId));
            sbM112a.append(" fname=");
            sbM112a.append(str2);
            sbM112a.append(" existing=");
            sbM112a.append(fragmentFindFragmentById);
        }
        if (fragmentFindFragmentById == null) {
            fragmentFindFragmentById = this.mContainer.instantiate(context, str2, null);
            fragmentFindFragmentById.mFromLayout = true;
            fragmentFindFragmentById.mFragmentId = resourceId != 0 ? resourceId : id;
            fragmentFindFragmentById.mContainerId = id;
            fragmentFindFragmentById.mTag = string;
            fragmentFindFragmentById.mInLayout = true;
            fragmentFindFragmentById.mFragmentManager = this;
            FragmentHostCallback fragmentHostCallback = this.mHost;
            fragmentFindFragmentById.mHost = fragmentHostCallback;
            fragmentFindFragmentById.onInflate(fragmentHostCallback.getContext(), attributeSet, fragmentFindFragmentById.mSavedFragmentState);
            addFragment(fragmentFindFragmentById, true);
        } else {
            if (fragmentFindFragmentById.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentFindFragmentById.mInLayout = true;
            FragmentHostCallback fragmentHostCallback2 = this.mHost;
            fragmentFindFragmentById.mHost = fragmentHostCallback2;
            if (!fragmentFindFragmentById.mRetaining) {
                fragmentFindFragmentById.onInflate(fragmentHostCallback2.getContext(), attributeSet, fragmentFindFragmentById.mSavedFragmentState);
            }
        }
        Fragment fragment = fragmentFindFragmentById;
        if (this.mCurState >= 1 || !fragment.mFromLayout) {
            moveToState(fragment);
        } else {
            moveToState(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.mView;
        if (view2 == null) {
            throw new IllegalStateException(C0096a.m97a("Fragment ", str2, " did not create a view."));
        }
        if (resourceId != 0) {
            view2.setId(resourceId);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(string);
        }
        return fragment.mView;
    }

    public void performPendingDeferredStart(Fragment fragment) {
        if (fragment.mDeferStart) {
            if (this.mExecutingActions) {
                this.mHavePendingDeferredStart = true;
            } else {
                fragment.mDeferStart = false;
                moveToState(fragment, this.mCurState, 0, 0, false);
            }
        }
    }

    @Override // android.support.v4.app.FragmentManager
    public void popBackStack() {
        enqueueAction(new PopBackStackState(null, -1, 0), false);
    }

    @Override // android.support.v4.app.FragmentManager
    public boolean popBackStackImmediate() {
        checkStateLoss();
        return popBackStackImmediate(null, -1, 0);
    }

    public boolean popBackStackState(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, String str, int i7, int i8) {
        int i9;
        ArrayList<BackStackRecord> arrayList3 = this.mBackStack;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i7 < 0 && (i8 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.mBackStack.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i7 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    BackStackRecord backStackRecord = this.mBackStack.get(size2);
                    if ((str != null && str.equals(backStackRecord.getName())) || (i7 >= 0 && i7 == backStackRecord.mIndex)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i8 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        BackStackRecord backStackRecord2 = this.mBackStack.get(size2);
                        if (str == null || !str.equals(backStackRecord2.getName())) {
                            if (i7 < 0 || i7 != backStackRecord2.mIndex) {
                                break;
                            }
                        }
                    }
                }
                i9 = size2;
            } else {
                i9 = -1;
            }
            if (i9 == this.mBackStack.size() - 1) {
                return false;
            }
            for (int size3 = this.mBackStack.size() - 1; size3 > i9; size3--) {
                arrayList.add(this.mBackStack.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    @Override // android.support.v4.app.FragmentManager
    public void putFragment(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException(C0165b.m100a("Fragment ", fragment, " is not currently in the FragmentManager")));
        }
        bundle.putInt(str, fragment.mIndex);
    }

    @Override // android.support.v4.app.FragmentManager
    public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z6) {
        this.mLifecycleCallbacks.add(new FragmentLifecycleCallbacksHolder(fragmentLifecycleCallbacks, z6));
    }

    public void removeFragment(Fragment fragment) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("remove: ");
            sb.append(fragment);
            sb.append(" nesting=");
            sb.append(fragment.mBackStackNesting);
        }
        boolean z6 = !fragment.isInBackStack();
        if (!fragment.mDetached || z6) {
            synchronized (this.mAdded) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    @Override // android.support.v4.app.FragmentManager
    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onBackStackChangedListener) {
        ArrayList<FragmentManager.OnBackStackChangedListener> arrayList = this.mBackStackChangeListeners;
        if (arrayList != null) {
            arrayList.remove(onBackStackChangedListener);
        }
    }

    public void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i7 = 0; i7 < this.mBackStackChangeListeners.size(); i7++) {
                this.mBackStackChangeListeners.get(i7).onBackStackChanged();
            }
        }
    }

    public void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentManagerNonConfig) {
        List<FragmentManagerNonConfig> childNonConfigs;
        List<C0065p> viewModelStores;
        FragmentState[] fragmentStateArr;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.mActive == null) {
            return;
        }
        if (fragmentManagerNonConfig != null) {
            List<Fragment> fragments = fragmentManagerNonConfig.getFragments();
            childNonConfigs = fragmentManagerNonConfig.getChildNonConfigs();
            viewModelStores = fragmentManagerNonConfig.getViewModelStores();
            int size = fragments != null ? fragments.size() : 0;
            for (int i7 = 0; i7 < size; i7++) {
                Fragment fragment = fragments.get(i7);
                if (DEBUG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("restoreAllState: re-attaching retained ");
                    sb.append(fragment);
                }
                int i8 = 0;
                while (true) {
                    fragmentStateArr = fragmentManagerState.mActive;
                    if (i8 >= fragmentStateArr.length || fragmentStateArr[i8].mIndex == fragment.mIndex) {
                        break;
                    } else {
                        i8++;
                    }
                }
                if (i8 == fragmentStateArr.length) {
                    StringBuilder sbM112a = C0413b.m112a("Could not find active fragment with index ");
                    sbM112a.append(fragment.mIndex);
                    throwException(new IllegalStateException(sbM112a.toString()));
                }
                FragmentState fragmentState = fragmentManagerState.mActive[i8];
                fragmentState.mInstance = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                fragment.mTarget = null;
                Bundle bundle = fragmentState.mSavedFragmentState;
                if (bundle != null) {
                    bundle.setClassLoader(this.mHost.getContext().getClassLoader());
                    fragment.mSavedViewState = fragmentState.mSavedFragmentState.getSparseParcelableArray(VIEW_STATE_TAG);
                    fragment.mSavedFragmentState = fragmentState.mSavedFragmentState;
                }
            }
        } else {
            childNonConfigs = null;
            viewModelStores = null;
        }
        this.mActive = new SparseArray<>(fragmentManagerState.mActive.length);
        int i9 = 0;
        while (true) {
            FragmentState[] fragmentStateArr2 = fragmentManagerState.mActive;
            if (i9 >= fragmentStateArr2.length) {
                break;
            }
            FragmentState fragmentState2 = fragmentStateArr2[i9];
            if (fragmentState2 != null) {
                Fragment fragmentInstantiate = fragmentState2.instantiate(this.mHost, this.mContainer, this.mParent, (childNonConfigs == null || i9 >= childNonConfigs.size()) ? null : childNonConfigs.get(i9), (viewModelStores == null || i9 >= viewModelStores.size()) ? null : viewModelStores.get(i9));
                if (DEBUG) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("restoreAllState: active #");
                    sb2.append(i9);
                    sb2.append(": ");
                    sb2.append(fragmentInstantiate);
                }
                this.mActive.put(fragmentInstantiate.mIndex, fragmentInstantiate);
                fragmentState2.mInstance = null;
            }
            i9++;
        }
        if (fragmentManagerNonConfig != null) {
            List<Fragment> fragments2 = fragmentManagerNonConfig.getFragments();
            int size2 = fragments2 != null ? fragments2.size() : 0;
            for (int i10 = 0; i10 < size2; i10++) {
                Fragment fragment2 = fragments2.get(i10);
                int i11 = fragment2.mTargetIndex;
                if (i11 >= 0) {
                    Fragment fragment3 = this.mActive.get(i11);
                    fragment2.mTarget = fragment3;
                    if (fragment3 == null) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Re-attaching retained fragment ");
                        sb3.append(fragment2);
                        sb3.append(" target no longer exists: ");
                        sb3.append(fragment2.mTargetIndex);
                    }
                }
            }
        }
        this.mAdded.clear();
        if (fragmentManagerState.mAdded != null) {
            int i12 = 0;
            while (true) {
                int[] iArr = fragmentManagerState.mAdded;
                if (i12 >= iArr.length) {
                    break;
                }
                Fragment fragment4 = this.mActive.get(iArr[i12]);
                if (fragment4 == null) {
                    StringBuilder sbM112a2 = C0413b.m112a("No instantiated fragment for index #");
                    sbM112a2.append(fragmentManagerState.mAdded[i12]);
                    throwException(new IllegalStateException(sbM112a2.toString()));
                }
                fragment4.mAdded = true;
                if (DEBUG) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("restoreAllState: added #");
                    sb4.append(i12);
                    sb4.append(": ");
                    sb4.append(fragment4);
                }
                if (this.mAdded.contains(fragment4)) {
                    throw new IllegalStateException("Already added!");
                }
                synchronized (this.mAdded) {
                    this.mAdded.add(fragment4);
                }
                i12++;
            }
        }
        if (fragmentManagerState.mBackStack != null) {
            this.mBackStack = new ArrayList<>(fragmentManagerState.mBackStack.length);
            int i13 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.mBackStack;
                if (i13 >= backStackStateArr.length) {
                    break;
                }
                BackStackRecord backStackRecordInstantiate = backStackStateArr[i13].instantiate(this);
                if (DEBUG) {
                    StringBuilder sbM98a = C0116a.m98a("restoreAllState: back stack #", i13, " (index ");
                    sbM98a.append(backStackRecordInstantiate.mIndex);
                    sbM98a.append("): ");
                    sbM98a.append(backStackRecordInstantiate);
                    PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
                    backStackRecordInstantiate.dump("  ", printWriter, false);
                    printWriter.close();
                }
                this.mBackStack.add(backStackRecordInstantiate);
                int i14 = backStackRecordInstantiate.mIndex;
                if (i14 >= 0) {
                    setBackStackIndex(i14, backStackRecordInstantiate);
                }
                i13++;
            }
        } else {
            this.mBackStack = null;
        }
        int i15 = fragmentManagerState.mPrimaryNavActiveIndex;
        if (i15 >= 0) {
            this.mPrimaryNav = this.mActive.get(i15);
        }
        this.mNextFragmentIndex = fragmentManagerState.mNextFragmentIndex;
    }

    public FragmentManagerNonConfig retainNonConfig() {
        setRetaining(this.mSavedNonConfig);
        return this.mSavedNonConfig;
    }

    public Parcelable saveAllState() throws NoSuchFieldException, Resources.NotFoundException {
        int[] iArr;
        int size;
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions();
        this.mStateSaved = true;
        BackStackState[] backStackStateArr = null;
        this.mSavedNonConfig = null;
        SparseArray<Fragment> sparseArray = this.mActive;
        if (sparseArray == null || sparseArray.size() <= 0) {
            return null;
        }
        int size2 = this.mActive.size();
        FragmentState[] fragmentStateArr = new FragmentState[size2];
        boolean z6 = false;
        for (int i7 = 0; i7 < size2; i7++) {
            Fragment fragmentValueAt = this.mActive.valueAt(i7);
            if (fragmentValueAt != null) {
                if (fragmentValueAt.mIndex < 0) {
                    throwException(new IllegalStateException("Failure saving state: active " + fragmentValueAt + " has cleared index: " + fragmentValueAt.mIndex));
                }
                FragmentState fragmentState = new FragmentState(fragmentValueAt);
                fragmentStateArr[i7] = fragmentState;
                if (fragmentValueAt.mState <= 0 || fragmentState.mSavedFragmentState != null) {
                    fragmentState.mSavedFragmentState = fragmentValueAt.mSavedFragmentState;
                } else {
                    fragmentState.mSavedFragmentState = saveFragmentBasicState(fragmentValueAt);
                    Fragment fragment = fragmentValueAt.mTarget;
                    if (fragment != null) {
                        if (fragment.mIndex < 0) {
                            throwException(new IllegalStateException("Failure saving state: " + fragmentValueAt + " has target not in fragment manager: " + fragmentValueAt.mTarget));
                        }
                        if (fragmentState.mSavedFragmentState == null) {
                            fragmentState.mSavedFragmentState = new Bundle();
                        }
                        putFragment(fragmentState.mSavedFragmentState, TARGET_STATE_TAG, fragmentValueAt.mTarget);
                        int i8 = fragmentValueAt.mTargetRequestCode;
                        if (i8 != 0) {
                            fragmentState.mSavedFragmentState.putInt(TARGET_REQUEST_CODE_STATE_TAG, i8);
                        }
                    }
                }
                if (DEBUG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Saved state of ");
                    sb.append(fragmentValueAt);
                    sb.append(": ");
                    sb.append(fragmentState.mSavedFragmentState);
                }
                z6 = true;
            }
        }
        if (!z6) {
            return null;
        }
        int size3 = this.mAdded.size();
        if (size3 > 0) {
            iArr = new int[size3];
            for (int i9 = 0; i9 < size3; i9++) {
                iArr[i9] = this.mAdded.get(i9).mIndex;
                if (iArr[i9] < 0) {
                    StringBuilder sbM112a = C0413b.m112a("Failure saving state: active ");
                    sbM112a.append(this.mAdded.get(i9));
                    sbM112a.append(" has cleared index: ");
                    sbM112a.append(iArr[i9]);
                    throwException(new IllegalStateException(sbM112a.toString()));
                }
                if (DEBUG) {
                    C0116a.m98a("saveAllState: adding fragment #", i9, ": ").append(this.mAdded.get(i9));
                }
            }
        } else {
            iArr = null;
        }
        ArrayList<BackStackRecord> arrayList = this.mBackStack;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i10 = 0; i10 < size; i10++) {
                backStackStateArr[i10] = new BackStackState(this.mBackStack.get(i10));
                if (DEBUG) {
                    C0116a.m98a("saveAllState: adding back stack #", i10, ": ").append(this.mBackStack.get(i10));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.mActive = fragmentStateArr;
        fragmentManagerState.mAdded = iArr;
        fragmentManagerState.mBackStack = backStackStateArr;
        Fragment fragment2 = this.mPrimaryNav;
        if (fragment2 != null) {
            fragmentManagerState.mPrimaryNavActiveIndex = fragment2.mIndex;
        }
        fragmentManagerState.mNextFragmentIndex = this.mNextFragmentIndex;
        saveNonConfig();
        return fragmentManagerState;
    }

    public Bundle saveFragmentBasicState(Fragment fragment) {
        if (this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        fragment.performSaveInstanceState(this.mStateBundle);
        dispatchOnFragmentSaveInstanceState(fragment, this.mStateBundle, false);
        Bundle bundle = null;
        if (!this.mStateBundle.isEmpty()) {
            Bundle bundle2 = this.mStateBundle;
            this.mStateBundle = null;
            bundle = bundle2;
        }
        if (fragment.mView != null) {
            saveFragmentViewState(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray(VIEW_STATE_TAG, fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean(USER_VISIBLE_HINT_TAG, fragment.mUserVisibleHint);
        }
        return bundle;
    }

    @Override // android.support.v4.app.FragmentManager
    @Nullable
    public Fragment.SavedState saveFragmentInstanceState(Fragment fragment) {
        Bundle bundleSaveFragmentBasicState;
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException(C0165b.m100a("Fragment ", fragment, " is not currently in the FragmentManager")));
        }
        if (fragment.mState <= 0 || (bundleSaveFragmentBasicState = saveFragmentBasicState(fragment)) == null) {
            return null;
        }
        return new Fragment.SavedState(bundleSaveFragmentBasicState);
    }

    public void saveFragmentViewState(Fragment fragment) {
        if (fragment.mInnerView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = this.mStateArray;
        if (sparseArray == null) {
            this.mStateArray = new SparseArray<>();
        } else {
            sparseArray.clear();
        }
        fragment.mInnerView.saveHierarchyState(this.mStateArray);
        if (this.mStateArray.size() > 0) {
            fragment.mSavedViewState = this.mStateArray;
            this.mStateArray = null;
        }
    }

    public void saveNonConfig() {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        FragmentManagerNonConfig fragmentManagerNonConfig;
        if (this.mActive != null) {
            arrayList = null;
            arrayList2 = null;
            arrayList3 = null;
            for (int i7 = 0; i7 < this.mActive.size(); i7++) {
                Fragment fragmentValueAt = this.mActive.valueAt(i7);
                if (fragmentValueAt != null) {
                    if (fragmentValueAt.mRetainInstance) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(fragmentValueAt);
                        Fragment fragment = fragmentValueAt.mTarget;
                        fragmentValueAt.mTargetIndex = fragment != null ? fragment.mIndex : -1;
                        if (DEBUG) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("retainNonConfig: keeping retained ");
                            sb.append(fragmentValueAt);
                        }
                    }
                    FragmentManagerImpl fragmentManagerImpl = fragmentValueAt.mChildFragmentManager;
                    if (fragmentManagerImpl != null) {
                        fragmentManagerImpl.saveNonConfig();
                        fragmentManagerNonConfig = fragmentValueAt.mChildFragmentManager.mSavedNonConfig;
                    } else {
                        fragmentManagerNonConfig = fragmentValueAt.mChildNonConfig;
                    }
                    if (arrayList2 == null && fragmentManagerNonConfig != null) {
                        arrayList2 = new ArrayList(this.mActive.size());
                        for (int i8 = 0; i8 < i7; i8++) {
                            arrayList2.add(null);
                        }
                    }
                    if (arrayList2 != null) {
                        arrayList2.add(fragmentManagerNonConfig);
                    }
                    if (arrayList3 == null && fragmentValueAt.mViewModelStore != null) {
                        arrayList3 = new ArrayList(this.mActive.size());
                        for (int i9 = 0; i9 < i7; i9++) {
                            arrayList3.add(null);
                        }
                    }
                    if (arrayList3 != null) {
                        arrayList3.add(fragmentValueAt.mViewModelStore);
                    }
                }
            }
        } else {
            arrayList = null;
            arrayList2 = null;
            arrayList3 = null;
        }
        if (arrayList == null && arrayList2 == null && arrayList3 == null) {
            this.mSavedNonConfig = null;
        } else {
            this.mSavedNonConfig = new FragmentManagerNonConfig(arrayList, arrayList2, arrayList3);
        }
    }

    public void scheduleCommit() {
        synchronized (this) {
            ArrayList<StartEnterTransitionListener> arrayList = this.mPostponedTransactions;
            boolean z6 = false;
            boolean z7 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            ArrayList<OpGenerator> arrayList2 = this.mPendingActions;
            if (arrayList2 != null && arrayList2.size() == 1) {
                z6 = true;
            }
            if (z7 || z6) {
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                this.mHost.getHandler().post(this.mExecCommit);
            }
        }
    }

    public void setBackStackIndex(int i7, BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<>();
            }
            int size = this.mBackStackIndices.size();
            if (i7 < size) {
                if (DEBUG) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Setting back stack index ");
                    sb.append(i7);
                    sb.append(" to ");
                    sb.append(backStackRecord);
                }
                this.mBackStackIndices.set(i7, backStackRecord);
            } else {
                while (size < i7) {
                    this.mBackStackIndices.add(null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList<>();
                    }
                    boolean z6 = DEBUG;
                    this.mAvailBackStackIndices.add(Integer.valueOf(size));
                    size++;
                }
                if (DEBUG) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Adding back stack index ");
                    sb2.append(i7);
                    sb2.append(" with ");
                    sb2.append(backStackRecord);
                }
                this.mBackStackIndices.add(backStackRecord);
            }
        }
    }

    public void setPrimaryNavigationFragment(Fragment fragment) {
        if (fragment == null || (this.mActive.get(fragment.mIndex) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            this.mPrimaryNav = fragment;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void showFragment(Fragment fragment) {
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("show: ");
            sb.append(fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    public void startPendingDeferredFragments() {
        if (this.mActive == null) {
            return;
        }
        for (int i7 = 0; i7 < this.mActive.size(); i7++) {
            Fragment fragmentValueAt = this.mActive.valueAt(i7);
            if (fragmentValueAt != null) {
                performPendingDeferredStart(fragmentValueAt);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.mParent;
        if (fragment != null) {
            DebugUtils.buildShortClassTag(fragment, sb);
        } else {
            DebugUtils.buildShortClassTag(this.mHost, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    @Override // android.support.v4.app.FragmentManager
    public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        synchronized (this.mLifecycleCallbacks) {
            int i7 = 0;
            int size = this.mLifecycleCallbacks.size();
            while (true) {
                if (i7 >= size) {
                    break;
                }
                if (this.mLifecycleCallbacks.get(i7).mCallback == fragmentLifecycleCallbacks) {
                    this.mLifecycleCallbacks.remove(i7);
                    break;
                }
                i7++;
            }
        }
    }

    @Override // android.support.v4.app.FragmentManager
    public void popBackStack(@Nullable String str, int i7) {
        enqueueAction(new PopBackStackState(str, -1, i7), false);
    }

    @Override // android.support.v4.app.FragmentManager
    public void popBackStack(int i7, int i8) {
        if (i7 >= 0) {
            enqueueAction(new PopBackStackState(null, i7, i8), false);
            return;
        }
        throw new IllegalArgumentException(C0079a.m93a("Bad id: ", i7));
    }

    @Override // android.support.v4.app.FragmentManager
    public boolean popBackStackImmediate(@Nullable String str, int i7) {
        checkStateLoss();
        return popBackStackImmediate(str, -1, i7);
    }

    /* compiled from: FragmentManager.java */
    public static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        public AnimationOrAnimator(Animation animation) {
            this.animation = animation;
            this.animator = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public AnimationOrAnimator(Animator animator) {
            this.animation = null;
            this.animator = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    @Override // android.support.v4.app.FragmentManager
    public boolean popBackStackImmediate(int i7, int i8) {
        checkStateLoss();
        execPendingActions();
        if (i7 >= 0) {
            return popBackStackImmediate(null, i7, i8);
        }
        throw new IllegalArgumentException(C0079a.m93a("Bad id: ", i7));
    }

    /* compiled from: FragmentManager.java */
    public static class EndViewTransitionAnimator extends AnimationSet implements Runnable {
        private boolean mAnimating;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        public EndViewTransitionAnimator(@NonNull Animation animation, @NonNull ViewGroup viewGroup, @NonNull View view) {
            super(false);
            this.mAnimating = true;
            this.mParent = viewGroup;
            this.mChild = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j7, Transformation transformation) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j7, transformation)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mEnded || !this.mAnimating) {
                this.mParent.endViewTransition(this.mChild);
                this.mTransitionEnded = true;
            } else {
                this.mAnimating = false;
                this.mParent.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j7, Transformation transformation, float f7) {
            this.mAnimating = true;
            if (this.mEnded) {
                return !this.mTransitionEnded;
            }
            if (!super.getTransformation(j7, transformation, f7)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }
    }

    public static boolean modifiesAlpha(Animator animator) {
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            for (PropertyValuesHolder propertyValuesHolder : ((ValueAnimator) animator).getValues()) {
                if (Key.ALPHA.equals(propertyValuesHolder.getPropertyName())) {
                    return true;
                }
            }
        } else if (animator instanceof AnimatorSet) {
            ArrayList<Animator> childAnimations = ((AnimatorSet) animator).getChildAnimations();
            for (int i7 = 0; i7 < childAnimations.size(); i7++) {
                if (modifiesAlpha(childAnimations.get(i7))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean popBackStackImmediate(String str, int i7, int i8) {
        FragmentManager fragmentManagerPeekChildFragmentManager;
        execPendingActions();
        ensureExecReady(true);
        Fragment fragment = this.mPrimaryNav;
        if (fragment != null && i7 < 0 && str == null && (fragmentManagerPeekChildFragmentManager = fragment.peekChildFragmentManager()) != null && fragmentManagerPeekChildFragmentManager.popBackStackImmediate()) {
            return true;
        }
        boolean zPopBackStackState = popBackStackState(this.mTmpRecords, this.mTmpIsPop, str, i7, i8);
        if (zPopBackStackState) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        doPendingDeferredStart();
        burpActive();
        return zPopBackStackState;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public void moveToState(Fragment fragment) throws NoSuchFieldException, Resources.NotFoundException {
        moveToState(fragment, this.mCurState, 0, 0, false);
    }

    public void moveToState(int i7, boolean z6) {
        FragmentHostCallback fragmentHostCallback;
        if (this.mHost == null && i7 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z6 || i7 != this.mCurState) {
            this.mCurState = i7;
            if (this.mActive != null) {
                int size = this.mAdded.size();
                for (int i8 = 0; i8 < size; i8++) {
                    moveFragmentToExpectedState(this.mAdded.get(i8));
                }
                int size2 = this.mActive.size();
                for (int i9 = 0; i9 < size2; i9++) {
                    Fragment fragmentValueAt = this.mActive.valueAt(i9);
                    if (fragmentValueAt != null && ((fragmentValueAt.mRemoving || fragmentValueAt.mDetached) && !fragmentValueAt.mIsNewlyAdded)) {
                        moveFragmentToExpectedState(fragmentValueAt);
                    }
                }
                startPendingDeferredFragments();
                if (this.mNeedMenuInvalidate && (fragmentHostCallback = this.mHost) != null && this.mCurState == 4) {
                    fragmentHostCallback.onSupportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }
}
