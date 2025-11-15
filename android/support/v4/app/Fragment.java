package android.support.v4.app;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.AbstractC0052c;
import android.arch.lifecycle.C0055f;
import android.arch.lifecycle.C0059j;
import android.arch.lifecycle.C0063n;
import android.arch.lifecycle.C0065p;
import android.arch.lifecycle.InterfaceC0054e;
import android.arch.lifecycle.InterfaceC0066q;
import android.arch.lifecycle.LiveData;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, InterfaceC0054e, InterfaceC0066q {
    public static final int ACTIVITY_CREATED = 2;
    public static final int CREATED = 1;
    public static final int INITIALIZING = 0;
    public static final int RESUMED = 4;
    public static final int STARTED = 3;
    public boolean mAdded;
    public AnimationInfo mAnimationInfo;
    public Bundle mArguments;
    public int mBackStackNesting;
    public boolean mCalled;
    public FragmentManagerImpl mChildFragmentManager;
    public FragmentManagerNonConfig mChildNonConfig;
    public ViewGroup mContainer;
    public int mContainerId;
    public boolean mDeferStart;
    public boolean mDetached;
    public int mFragmentId;
    public FragmentManagerImpl mFragmentManager;
    public boolean mFromLayout;
    public boolean mHasMenu;
    public boolean mHidden;
    public boolean mHiddenChanged;
    public FragmentHostCallback mHost;
    public boolean mInLayout;
    public View mInnerView;
    public boolean mIsCreated;
    public boolean mIsNewlyAdded;
    public LayoutInflater mLayoutInflater;
    public Fragment mParentFragment;
    public boolean mPerformedCreateView;
    public float mPostponedAlpha;
    public boolean mRemoving;
    public boolean mRestored;
    public boolean mRetainInstance;
    public boolean mRetaining;
    public Bundle mSavedFragmentState;

    @Nullable
    public Boolean mSavedUserVisibleHint;
    public SparseArray<Parcelable> mSavedViewState;
    public String mTag;
    public Fragment mTarget;
    public int mTargetRequestCode;
    public View mView;
    public InterfaceC0054e mViewLifecycleOwner;
    public C0055f mViewLifecycleRegistry;
    public C0065p mViewModelStore;
    public String mWho;
    private static final SimpleArrayMap<String, Class<?>> sClassMap = new SimpleArrayMap<>();
    public static final Object USE_DEFAULT_TRANSITION = new Object();
    public int mState = 0;
    public int mIndex = -1;
    public int mTargetIndex = -1;
    public boolean mMenuVisible = true;
    public boolean mUserVisibleHint = true;
    public C0055f mLifecycleRegistry = new C0055f(this);
    public C0059j<InterfaceC0054e> mViewLifecycleOwnerLiveData = new C0059j<>();

    public static class AnimationInfo {
        public Boolean mAllowEnterTransitionOverlap;
        public Boolean mAllowReturnTransitionOverlap;
        public View mAnimatingAway;
        public Animator mAnimator;
        public Object mEnterTransition = null;
        public SharedElementCallback mEnterTransitionCallback;
        public boolean mEnterTransitionPostponed;
        public Object mExitTransition;
        public SharedElementCallback mExitTransitionCallback;
        public boolean mIsHideReplaced;
        public int mNextAnim;
        public int mNextTransition;
        public int mNextTransitionStyle;
        public Object mReenterTransition;
        public Object mReturnTransition;
        public Object mSharedElementEnterTransition;
        public Object mSharedElementReturnTransition;
        public OnStartEnterTransitionListener mStartEnterTransitionListener;
        public int mStateAfterAnimating;

        public AnimationInfo() {
            Object obj = Fragment.USE_DEFAULT_TRANSITION;
            this.mReturnTransition = obj;
            this.mExitTransition = null;
            this.mReenterTransition = obj;
            this.mSharedElementEnterTransition = null;
            this.mSharedElementReturnTransition = obj;
            this.mEnterTransitionCallback = null;
            this.mExitTransitionCallback = null;
        }
    }

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String str, Exception exc) {
            super(str, exc);
        }
    }

    public interface OnStartEnterTransitionListener {
        void onStartEnterTransition();

        void startListening();
    }

    private AnimationInfo ensureAnimationInfo() {
        if (this.mAnimationInfo == null) {
            this.mAnimationInfo = new AnimationInfo();
        }
        return this.mAnimationInfo;
    }

    public static Fragment instantiate(Context context, String str) {
        return instantiate(context, str, null);
    }

    public static boolean isSupportFragmentClass(Context context, String str) throws ClassNotFoundException {
        try {
            SimpleArrayMap<String, Class<?>> simpleArrayMap = sClassMap;
            Class<?> clsLoadClass = simpleArrayMap.get(str);
            if (clsLoadClass == null) {
                clsLoadClass = context.getClassLoader().loadClass(str);
                simpleArrayMap.put(str, clsLoadClass);
            }
            return Fragment.class.isAssignableFrom(clsLoadClass);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public void callStartTransitionListener() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        OnStartEnterTransitionListener onStartEnterTransitionListener = null;
        if (animationInfo != null) {
            animationInfo.mEnterTransitionPostponed = false;
            OnStartEnterTransitionListener onStartEnterTransitionListener2 = animationInfo.mStartEnterTransitionListener;
            animationInfo.mStartEnterTransitionListener = null;
            onStartEnterTransitionListener = onStartEnterTransitionListener2;
        }
        if (onStartEnterTransitionListener != null) {
            onStartEnterTransitionListener.onStartEnterTransition();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mIndex=");
        printWriter.print(this.mIndex);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mRetaining=");
        printWriter.print(this.mRetaining);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        if (this.mTarget != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(this.mTarget);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        if (getNextAnim() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(getNextAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (this.mInnerView != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(getStateAfterAnimating());
        }
        if (getContext() != null) {
            LoaderManager.getInstance(this).dump(str, fileDescriptor, printWriter, strArr);
        }
        if (this.mChildFragmentManager != null) {
            printWriter.print(str);
            printWriter.println("Child " + this.mChildFragmentManager + ":");
            this.mChildFragmentManager.dump(C0063n.m88a(str, "  "), fileDescriptor, printWriter, strArr);
        }
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Fragment findFragmentByWho(String str) {
        if (str.equals(this.mWho)) {
            return this;
        }
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            return fragmentManagerImpl.findFragmentByWho(str);
        }
        return null;
    }

    @Nullable
    public final FragmentActivity getActivity() {
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback == null) {
            return null;
        }
        return (FragmentActivity) fragmentHostCallback.getActivity();
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null || (bool = animationInfo.mAllowEnterTransitionOverlap) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null || (bool = animationInfo.mAllowReturnTransitionOverlap) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public View getAnimatingAway() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.mAnimatingAway;
    }

    public Animator getAnimator() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.mAnimator;
    }

    @Nullable
    public final Bundle getArguments() {
        return this.mArguments;
    }

    @NonNull
    public final FragmentManager getChildFragmentManager() {
        if (this.mChildFragmentManager == null) {
            instantiateChildFragmentManager();
            int i7 = this.mState;
            if (i7 >= 4) {
                this.mChildFragmentManager.dispatchResume();
            } else if (i7 >= 3) {
                this.mChildFragmentManager.dispatchStart();
            } else if (i7 >= 2) {
                this.mChildFragmentManager.dispatchActivityCreated();
            } else if (i7 >= 1) {
                this.mChildFragmentManager.dispatchCreate();
            }
        }
        return this.mChildFragmentManager;
    }

    @Nullable
    public Context getContext() {
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback == null) {
            return null;
        }
        return fragmentHostCallback.getContext();
    }

    @Nullable
    public Object getEnterTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.mEnterTransition;
    }

    public SharedElementCallback getEnterTransitionCallback() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.mEnterTransitionCallback;
    }

    @Nullable
    public Object getExitTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.mExitTransition;
    }

    public SharedElementCallback getExitTransitionCallback() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.mExitTransitionCallback;
    }

    @Nullable
    public final FragmentManager getFragmentManager() {
        return this.mFragmentManager;
    }

    @Nullable
    public final Object getHost() {
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback == null) {
            return null;
        }
        return fragmentHostCallback.onGetHost();
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater(null) : layoutInflater;
    }

    @Override // android.arch.lifecycle.InterfaceC0054e
    public AbstractC0052c getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public LoaderManager getLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    public int getNextAnim() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.mNextAnim;
    }

    public int getNextTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.mNextTransition;
    }

    public int getNextTransitionStyle() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.mNextTransitionStyle;
    }

    @Nullable
    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public Object getReenterTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        Object obj = animationInfo.mReenterTransition;
        return obj == USE_DEFAULT_TRANSITION ? getExitTransition() : obj;
    }

    @NonNull
    public final Resources getResources() {
        return requireContext().getResources();
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    @Nullable
    public Object getReturnTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        Object obj = animationInfo.mReturnTransition;
        return obj == USE_DEFAULT_TRANSITION ? getEnterTransition() : obj;
    }

    @Nullable
    public Object getSharedElementEnterTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.mSharedElementEnterTransition;
    }

    @Nullable
    public Object getSharedElementReturnTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        Object obj = animationInfo.mSharedElementReturnTransition;
        return obj == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : obj;
    }

    public int getStateAfterAnimating() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.mStateAfterAnimating;
    }

    @NonNull
    public final String getString(@StringRes int i7) {
        return getResources().getString(i7);
    }

    @Nullable
    public final String getTag() {
        return this.mTag;
    }

    @Nullable
    public final Fragment getTargetFragment() {
        return this.mTarget;
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    @NonNull
    public final CharSequence getText(@StringRes int i7) {
        return getResources().getText(i7);
    }

    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    @Nullable
    public View getView() {
        return this.mView;
    }

    @NonNull
    @MainThread
    public InterfaceC0054e getViewLifecycleOwner() {
        InterfaceC0054e interfaceC0054e = this.mViewLifecycleOwner;
        if (interfaceC0054e != null) {
            return interfaceC0054e;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    @NonNull
    public LiveData<InterfaceC0054e> getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override // android.arch.lifecycle.InterfaceC0066q
    @NonNull
    public C0065p getViewModelStore() {
        if (getContext() == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (this.mViewModelStore == null) {
            this.mViewModelStore = new C0065p();
        }
        return this.mViewModelStore;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
    }

    public void instantiateChildFragmentManager() {
        if (this.mHost == null) {
            throw new IllegalStateException("Fragment has not been attached yet.");
        }
        FragmentManagerImpl fragmentManagerImpl = new FragmentManagerImpl();
        this.mChildFragmentManager = fragmentManagerImpl;
        fragmentManagerImpl.attachController(this.mHost, new FragmentContainer() { // from class: android.support.v4.app.Fragment.2
            @Override // android.support.v4.app.FragmentContainer
            public Fragment instantiate(Context context, String str, Bundle bundle) {
                return Fragment.this.mHost.instantiate(context, str, bundle);
            }

            @Override // android.support.v4.app.FragmentContainer
            @Nullable
            public View onFindViewById(int i7) {
                View view = Fragment.this.mView;
                if (view != null) {
                    return view.findViewById(i7);
                }
                throw new IllegalStateException("Fragment does not have a view");
            }

            @Override // android.support.v4.app.FragmentContainer
            public boolean onHasView() {
                return Fragment.this.mView != null;
            }
        }, this);
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    public boolean isHideReplaced() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return false;
        }
        return animationInfo.mIsHideReplaced;
    }

    public final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    public boolean isPostponed() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return false;
        }
        return animationInfo.mEnterTransitionPostponed;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        return this.mState >= 4;
    }

    public final boolean isStateSaved() {
        FragmentManagerImpl fragmentManagerImpl = this.mFragmentManager;
        if (fragmentManagerImpl == null) {
            return false;
        }
        return fragmentManagerImpl.isStateSaved();
    }

    public final boolean isVisible() {
        View view;
        return (!isAdded() || isHidden() || (view = this.mView) == null || view.getWindowToken() == null || this.mView.getVisibility() != 0) ? false : true;
    }

    public void noteStateNotSaved() {
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.noteStateNotSaved();
        }
    }

    @CallSuper
    public void onActivityCreated(@Nullable Bundle bundle) {
        this.mCalled = true;
    }

    public void onActivityResult(int i7, int i8, Intent intent) {
    }

    @CallSuper
    public void onAttach(Context context) {
        this.mCalled = true;
        FragmentHostCallback fragmentHostCallback = this.mHost;
        Activity activity = fragmentHostCallback == null ? null : fragmentHostCallback.getActivity();
        if (activity != null) {
            this.mCalled = false;
            onAttach(activity);
        }
    }

    public void onAttachFragment(Fragment fragment) {
    }

    @Override // android.content.ComponentCallbacks
    @CallSuper
    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    @CallSuper
    public void onCreate(@Nullable Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl == null || fragmentManagerImpl.isStateAtLeast(1)) {
            return;
        }
        this.mChildFragmentManager.dispatchCreate();
    }

    public Animation onCreateAnimation(int i7, boolean z6, int i8) {
        return null;
    }

    public Animator onCreateAnimator(int i7, boolean z6, int i8) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        getActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return null;
    }

    @CallSuper
    public void onDestroy() {
        this.mCalled = true;
        FragmentActivity activity = getActivity();
        boolean z6 = activity != null && activity.isChangingConfigurations();
        C0065p c0065p = this.mViewModelStore;
        if (c0065p == null || z6) {
            return;
        }
        c0065p.m89a();
    }

    public void onDestroyOptionsMenu() {
    }

    @CallSuper
    public void onDestroyView() {
        this.mCalled = true;
    }

    @CallSuper
    public void onDetach() {
        this.mCalled = true;
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z6) {
    }

    @CallSuper
    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        FragmentHostCallback fragmentHostCallback = this.mHost;
        Activity activity = fragmentHostCallback == null ? null : fragmentHostCallback.getActivity();
        if (activity != null) {
            this.mCalled = false;
            onInflate(activity, attributeSet, bundle);
        }
    }

    @Override // android.content.ComponentCallbacks
    @CallSuper
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z6) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    @CallSuper
    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z6) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onRequestPermissionsResult(int i7, @NonNull String[] strArr, @NonNull int[] iArr) {
    }

    @CallSuper
    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }

    @CallSuper
    public void onStart() {
        this.mCalled = true;
    }

    @CallSuper
    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
    }

    @CallSuper
    public void onViewStateRestored(@Nullable Bundle bundle) {
        this.mCalled = true;
    }

    @Nullable
    public FragmentManager peekChildFragmentManager() {
        return this.mChildFragmentManager;
    }

    public void performActivityCreated(Bundle bundle) {
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.noteStateNotSaved();
        }
        this.mState = 2;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onActivityCreated()"));
        }
        FragmentManagerImpl fragmentManagerImpl2 = this.mChildFragmentManager;
        if (fragmentManagerImpl2 != null) {
            fragmentManagerImpl2.dispatchActivityCreated();
        }
    }

    public void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.dispatchConfigurationChanged(configuration);
        }
    }

    public boolean performContextItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (onContextItemSelected(menuItem)) {
            return true;
        }
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        return fragmentManagerImpl != null && fragmentManagerImpl.dispatchContextItemSelected(menuItem);
    }

    public void performCreate(Bundle bundle) {
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.noteStateNotSaved();
        }
        this.mState = 1;
        this.mCalled = false;
        onCreate(bundle);
        this.mIsCreated = true;
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onCreate()"));
        }
        this.mLifecycleRegistry.m80d(AbstractC0052c.a.ON_CREATE);
    }

    public boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z6 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z6 = true;
        }
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        return fragmentManagerImpl != null ? z6 | fragmentManagerImpl.dispatchCreateOptionsMenu(menu, menuInflater) : z6;
    }

    public void performCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.noteStateNotSaved();
        }
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new InterfaceC0054e() { // from class: android.support.v4.app.Fragment.3
            @Override // android.arch.lifecycle.InterfaceC0054e
            public AbstractC0052c getLifecycle() {
                Fragment fragment = Fragment.this;
                if (fragment.mViewLifecycleRegistry == null) {
                    fragment.mViewLifecycleRegistry = new C0055f(fragment.mViewLifecycleOwner);
                }
                return Fragment.this.mViewLifecycleRegistry;
            }
        };
        this.mViewLifecycleRegistry = null;
        View viewOnCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = viewOnCreateView;
        if (viewOnCreateView != null) {
            this.mViewLifecycleOwner.getLifecycle();
            this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
        } else {
            if (this.mViewLifecycleRegistry != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.mViewLifecycleOwner = null;
        }
    }

    public void performDestroy() {
        this.mLifecycleRegistry.m80d(AbstractC0052c.a.ON_DESTROY);
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.dispatchDestroy();
        }
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onDestroy()"));
        }
        this.mChildFragmentManager = null;
    }

    public void performDestroyView() {
        if (this.mView != null) {
            this.mViewLifecycleRegistry.m80d(AbstractC0052c.a.ON_DESTROY);
        }
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.dispatchDestroyView();
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onDestroyView()"));
        }
        LoaderManager.getInstance(this).markForRedelivery();
        this.mPerformedCreateView = false;
    }

    public void performDetach() {
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onDetach()"));
        }
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            if (this.mRetaining) {
                fragmentManagerImpl.dispatchDestroy();
                this.mChildFragmentManager = null;
                return;
            }
            throw new IllegalStateException("Child FragmentManager of " + this + " was not  destroyed and this fragment is not retaining instance");
        }
    }

    @NonNull
    public LayoutInflater performGetLayoutInflater(@Nullable Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = onGetLayoutInflater(bundle);
        this.mLayoutInflater = layoutInflaterOnGetLayoutInflater;
        return layoutInflaterOnGetLayoutInflater;
    }

    public void performLowMemory() {
        onLowMemory();
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.dispatchLowMemory();
        }
    }

    public void performMultiWindowModeChanged(boolean z6) {
        onMultiWindowModeChanged(z6);
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.dispatchMultiWindowModeChanged(z6);
        }
    }

    public boolean performOptionsItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) {
            return true;
        }
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        return fragmentManagerImpl != null && fragmentManagerImpl.dispatchOptionsItemSelected(menuItem);
    }

    public void performOptionsMenuClosed(Menu menu) {
        if (this.mHidden) {
            return;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onOptionsMenuClosed(menu);
        }
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.dispatchOptionsMenuClosed(menu);
        }
    }

    public void performPause() {
        if (this.mView != null) {
            this.mViewLifecycleRegistry.m80d(AbstractC0052c.a.ON_PAUSE);
        }
        this.mLifecycleRegistry.m80d(AbstractC0052c.a.ON_PAUSE);
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.dispatchPause();
        }
        this.mState = 3;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onPause()"));
        }
    }

    public void performPictureInPictureModeChanged(boolean z6) {
        onPictureInPictureModeChanged(z6);
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.dispatchPictureInPictureModeChanged(z6);
        }
    }

    public boolean performPrepareOptionsMenu(Menu menu) {
        boolean z6 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z6 = true;
        }
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        return fragmentManagerImpl != null ? z6 | fragmentManagerImpl.dispatchPrepareOptionsMenu(menu) : z6;
    }

    public void performResume() {
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }
        this.mState = 4;
        this.mCalled = false;
        onResume();
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onResume()"));
        }
        FragmentManagerImpl fragmentManagerImpl2 = this.mChildFragmentManager;
        if (fragmentManagerImpl2 != null) {
            fragmentManagerImpl2.dispatchResume();
            this.mChildFragmentManager.execPendingActions();
        }
        C0055f c0055f = this.mLifecycleRegistry;
        AbstractC0052c.a aVar = AbstractC0052c.a.ON_RESUME;
        c0055f.m80d(aVar);
        if (this.mView != null) {
            this.mViewLifecycleRegistry.m80d(aVar);
        }
    }

    public void performSaveInstanceState(Bundle bundle) {
        Parcelable parcelableSaveAllState;
        onSaveInstanceState(bundle);
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl == null || (parcelableSaveAllState = fragmentManagerImpl.saveAllState()) == null) {
            return;
        }
        bundle.putParcelable(FragmentActivity.FRAGMENTS_TAG, parcelableSaveAllState);
    }

    public void performStart() {
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.noteStateNotSaved();
            this.mChildFragmentManager.execPendingActions();
        }
        this.mState = 3;
        this.mCalled = false;
        onStart();
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onStart()"));
        }
        FragmentManagerImpl fragmentManagerImpl2 = this.mChildFragmentManager;
        if (fragmentManagerImpl2 != null) {
            fragmentManagerImpl2.dispatchStart();
        }
        C0055f c0055f = this.mLifecycleRegistry;
        AbstractC0052c.a aVar = AbstractC0052c.a.ON_START;
        c0055f.m80d(aVar);
        if (this.mView != null) {
            this.mViewLifecycleRegistry.m80d(aVar);
        }
    }

    public void performStop() {
        if (this.mView != null) {
            this.mViewLifecycleRegistry.m80d(AbstractC0052c.a.ON_STOP);
        }
        this.mLifecycleRegistry.m80d(AbstractC0052c.a.ON_STOP);
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (fragmentManagerImpl != null) {
            fragmentManagerImpl.dispatchStop();
        }
        this.mState = 2;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onStop()"));
        }
    }

    public void postponeEnterTransition() {
        ensureAnimationInfo().mEnterTransitionPostponed = true;
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public final void requestPermissions(@NonNull String[] strArr, int i7) {
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback == null) {
            throw new IllegalStateException(C0165b.m100a("Fragment ", this, " not attached to Activity"));
        }
        fragmentHostCallback.onRequestPermissionsFromFragment(this, strArr, i7);
    }

    @NonNull
    public final FragmentActivity requireActivity() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException(C0165b.m100a("Fragment ", this, " not attached to an activity."));
    }

    @NonNull
    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException(C0165b.m100a("Fragment ", this, " not attached to a context."));
    }

    @NonNull
    public final FragmentManager requireFragmentManager() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException(C0165b.m100a("Fragment ", this, " not associated with a fragment manager."));
    }

    @NonNull
    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException(C0165b.m100a("Fragment ", this, " not attached to a host."));
    }

    public void restoreChildFragmentState(@Nullable Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable(FragmentActivity.FRAGMENTS_TAG)) == null) {
            return;
        }
        if (this.mChildFragmentManager == null) {
            instantiateChildFragmentManager();
        }
        this.mChildFragmentManager.restoreAllState(parcelable, this.mChildNonConfig);
        this.mChildNonConfig = null;
        this.mChildFragmentManager.dispatchCreate();
    }

    public final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mInnerView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (!this.mCalled) {
            throw new SuperNotCalledException(C0165b.m100a("Fragment ", this, " did not call through to super.onViewStateRestored()"));
        }
        if (this.mView != null) {
            this.mViewLifecycleRegistry.m80d(AbstractC0052c.a.ON_CREATE);
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z6) {
        ensureAnimationInfo().mAllowEnterTransitionOverlap = Boolean.valueOf(z6);
    }

    public void setAllowReturnTransitionOverlap(boolean z6) {
        ensureAnimationInfo().mAllowReturnTransitionOverlap = Boolean.valueOf(z6);
    }

    public void setAnimatingAway(View view) {
        ensureAnimationInfo().mAnimatingAway = view;
    }

    public void setAnimator(Animator animator) {
        ensureAnimationInfo().mAnimator = animator;
    }

    public void setArguments(@Nullable Bundle bundle) {
        if (this.mIndex >= 0 && isStateSaved()) {
            throw new IllegalStateException("Fragment already active and state has been saved");
        }
        this.mArguments = bundle;
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ensureAnimationInfo().mEnterTransitionCallback = sharedElementCallback;
    }

    public void setEnterTransition(@Nullable Object obj) {
        ensureAnimationInfo().mEnterTransition = obj;
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ensureAnimationInfo().mExitTransitionCallback = sharedElementCallback;
    }

    public void setExitTransition(@Nullable Object obj) {
        ensureAnimationInfo().mExitTransition = obj;
    }

    public void setHasOptionsMenu(boolean z6) {
        if (this.mHasMenu != z6) {
            this.mHasMenu = z6;
            if (!isAdded() || isHidden()) {
                return;
            }
            this.mHost.onSupportInvalidateOptionsMenu();
        }
    }

    public void setHideReplaced(boolean z6) {
        ensureAnimationInfo().mIsHideReplaced = z6;
    }

    public final void setIndex(int i7, Fragment fragment) {
        this.mIndex = i7;
        if (fragment == null) {
            StringBuilder sbM112a = C0413b.m112a("android:fragment:");
            sbM112a.append(this.mIndex);
            this.mWho = sbM112a.toString();
        } else {
            this.mWho = fragment.mWho + ":" + this.mIndex;
        }
    }

    public void setInitialSavedState(@Nullable SavedState savedState) {
        Bundle bundle;
        if (this.mIndex >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        if (savedState == null || (bundle = savedState.mState) == null) {
            bundle = null;
        }
        this.mSavedFragmentState = bundle;
    }

    public void setMenuVisibility(boolean z6) {
        if (this.mMenuVisible != z6) {
            this.mMenuVisible = z6;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    public void setNextAnim(int i7) {
        if (this.mAnimationInfo == null && i7 == 0) {
            return;
        }
        ensureAnimationInfo().mNextAnim = i7;
    }

    public void setNextTransition(int i7, int i8) {
        if (this.mAnimationInfo == null && i7 == 0 && i8 == 0) {
            return;
        }
        ensureAnimationInfo();
        AnimationInfo animationInfo = this.mAnimationInfo;
        animationInfo.mNextTransition = i7;
        animationInfo.mNextTransitionStyle = i8;
    }

    public void setOnStartEnterTransitionListener(OnStartEnterTransitionListener onStartEnterTransitionListener) {
        ensureAnimationInfo();
        AnimationInfo animationInfo = this.mAnimationInfo;
        OnStartEnterTransitionListener onStartEnterTransitionListener2 = animationInfo.mStartEnterTransitionListener;
        if (onStartEnterTransitionListener == onStartEnterTransitionListener2) {
            return;
        }
        if (onStartEnterTransitionListener != null && onStartEnterTransitionListener2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (animationInfo.mEnterTransitionPostponed) {
            animationInfo.mStartEnterTransitionListener = onStartEnterTransitionListener;
        }
        if (onStartEnterTransitionListener != null) {
            onStartEnterTransitionListener.startListening();
        }
    }

    public void setReenterTransition(@Nullable Object obj) {
        ensureAnimationInfo().mReenterTransition = obj;
    }

    public void setRetainInstance(boolean z6) {
        this.mRetainInstance = z6;
    }

    public void setReturnTransition(@Nullable Object obj) {
        ensureAnimationInfo().mReturnTransition = obj;
    }

    public void setSharedElementEnterTransition(@Nullable Object obj) {
        ensureAnimationInfo().mSharedElementEnterTransition = obj;
    }

    public void setSharedElementReturnTransition(@Nullable Object obj) {
        ensureAnimationInfo().mSharedElementReturnTransition = obj;
    }

    public void setStateAfterAnimating(int i7) {
        ensureAnimationInfo().mStateAfterAnimating = i7;
    }

    public void setTargetFragment(@Nullable Fragment fragment, int i7) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentManager fragmentManager2 = fragment != null ? fragment.getFragmentManager() : null;
        if (fragmentManager != null && fragmentManager2 != null && fragmentManager != fragmentManager2) {
            throw new IllegalArgumentException(C0165b.m100a("Fragment ", fragment, " must share the same FragmentManager to be set as a target fragment"));
        }
        for (Fragment targetFragment = fragment; targetFragment != null; targetFragment = targetFragment.getTargetFragment()) {
            if (targetFragment == this) {
                throw new IllegalArgumentException("Setting " + fragment + " as the target of " + this + " would create a target cycle");
            }
        }
        this.mTarget = fragment;
        this.mTargetRequestCode = i7;
    }

    public void setUserVisibleHint(boolean z6) {
        if (!this.mUserVisibleHint && z6 && this.mState < 3 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            this.mFragmentManager.performPendingDeferredStart(this);
        }
        this.mUserVisibleHint = z6;
        this.mDeferStart = this.mState < 3 && !z6;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z6);
        }
    }

    public boolean shouldShowRequestPermissionRationale(@NonNull String str) {
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback != null) {
            return fragmentHostCallback.onShouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    public void startActivityForResult(Intent intent, int i7) {
        startActivityForResult(intent, i7, null);
    }

    public void startIntentSenderForResult(IntentSender intentSender, int i7, @Nullable Intent intent, int i8, int i9, int i10, Bundle bundle) {
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback == null) {
            throw new IllegalStateException(C0165b.m100a("Fragment ", this, " not attached to Activity"));
        }
        fragmentHostCallback.onStartIntentSenderFromFragment(this, intentSender, i7, intent, i8, i9, i10, bundle);
    }

    public void startPostponedEnterTransition() {
        FragmentManagerImpl fragmentManagerImpl = this.mFragmentManager;
        if (fragmentManagerImpl == null || fragmentManagerImpl.mHost == null) {
            ensureAnimationInfo().mEnterTransitionPostponed = false;
        } else if (Looper.myLooper() != this.mFragmentManager.mHost.getHandler().getLooper()) {
            this.mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new Runnable() { // from class: android.support.v4.app.Fragment.1
                @Override // java.lang.Runnable
                public void run() {
                    Fragment.this.callStartTransitionListener();
                }
            });
        } else {
            callStartTransitionListener();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        DebugUtils.buildShortClassTag(this, sb);
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" ");
            sb.append(this.mTag);
        }
        sb.append('}');
        return sb.toString();
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener(null);
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: android.support.v4.app.Fragment.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
        };
        public final Bundle mState;

        public SavedState(Bundle bundle) {
            this.mState = bundle;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeBundle(this.mState);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle = parcel.readBundle();
            this.mState = bundle;
            if (classLoader == null || bundle == null) {
                return;
            }
            bundle.setClassLoader(classLoader);
        }
    }

    public static Fragment instantiate(Context context, String str, @Nullable Bundle bundle) throws ClassNotFoundException {
        try {
            SimpleArrayMap<String, Class<?>> simpleArrayMap = sClassMap;
            Class<?> clsLoadClass = simpleArrayMap.get(str);
            if (clsLoadClass == null) {
                clsLoadClass = context.getClassLoader().loadClass(str);
                simpleArrayMap.put(str, clsLoadClass);
            }
            Fragment fragment = (Fragment) clsLoadClass.getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.setArguments(bundle);
            }
            return fragment;
        } catch (ClassNotFoundException e7) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e7);
        } catch (IllegalAccessException e8) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e8);
        } catch (java.lang.InstantiationException e9) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e9);
        } catch (NoSuchMethodException e10) {
            throw new InstantiationException(C0096a.m97a("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e10);
        } catch (InvocationTargetException e11) {
            throw new InstantiationException(C0096a.m97a("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e11);
        }
    }

    @NonNull
    public final String getString(@StringRes int i7, Object... objArr) {
        return getResources().getString(i7, objArr);
    }

    public void startActivity(Intent intent, @Nullable Bundle bundle) {
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback == null) {
            throw new IllegalStateException(C0165b.m100a("Fragment ", this, " not attached to Activity"));
        }
        fragmentHostCallback.onStartActivityFromFragment(this, intent, -1, bundle);
    }

    public void startActivityForResult(Intent intent, int i7, @Nullable Bundle bundle) {
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback == null) {
            throw new IllegalStateException(C0165b.m100a("Fragment ", this, " not attached to Activity"));
        }
        fragmentHostCallback.onStartActivityFromFragment(this, intent, i7, bundle);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Deprecated
    public LayoutInflater getLayoutInflater(@Nullable Bundle bundle) {
        FragmentHostCallback fragmentHostCallback = this.mHost;
        if (fragmentHostCallback != null) {
            LayoutInflater layoutInflaterOnGetLayoutInflater = fragmentHostCallback.onGetLayoutInflater();
            getChildFragmentManager();
            LayoutInflaterCompat.setFactory2(layoutInflaterOnGetLayoutInflater, this.mChildFragmentManager.getLayoutInflaterFactory());
            return layoutInflaterOnGetLayoutInflater;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @CallSuper
    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    @CallSuper
    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }
}
