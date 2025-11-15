package android.support.v7.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.app.ActionBar;
import android.support.v7.appcompat.C0308R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.ActionMode;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import p009b.C0413b;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long FADE_IN_DURATION_MS = 200;
    private static final long FADE_OUT_DURATION_MS = 100;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "WindowDecorActionBar";
    private static final Interpolator sHideInterpolator = new AccelerateInterpolator();
    private static final Interpolator sShowInterpolator = new DecelerateInterpolator();
    public ActionModeImpl mActionMode;
    private Activity mActivity;
    public ActionBarContainer mContainerView;
    public View mContentView;
    public Context mContext;
    public ActionBarContextView mContextView;
    public ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    public DecorToolbar mDecorToolbar;
    public ActionMode mDeferredDestroyActionMode;
    public ActionMode.Callback mDeferredModeDestroyCallback;
    private Dialog mDialog;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    public boolean mHiddenByApp;
    public boolean mHiddenBySystem;
    public boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    public ActionBarOverlayLayout mOverlayLayout;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    private boolean mShowingForMode;
    public ScrollingTabContainerView mTabScrollView;
    private Context mThemedContext;
    private ArrayList<TabImpl> mTabs = new ArrayList<>();
    private int mSavedTabPosition = -1;
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<>();
    private int mCurWindowVisibility = 0;
    public boolean mContentAnimations = true;
    private boolean mNowShowing = true;
    public final ViewPropertyAnimatorListener mHideListener = new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.app.WindowDecorActionBar.1
        public C03051() {
        }

        @Override // android.support.v4.view.ViewPropertyAnimatorListenerAdapter, android.support.v4.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            View view2;
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.mContentAnimations && (view2 = windowDecorActionBar.mContentView) != null) {
                view2.setTranslationY(0.0f);
                WindowDecorActionBar.this.mContainerView.setTranslationY(0.0f);
            }
            WindowDecorActionBar.this.mContainerView.setVisibility(8);
            WindowDecorActionBar.this.mContainerView.setTransitioning(false);
            WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
            windowDecorActionBar2.mCurrentShowAnim = null;
            windowDecorActionBar2.completeDeferredDestroyActionMode();
            ActionBarOverlayLayout actionBarOverlayLayout = WindowDecorActionBar.this.mOverlayLayout;
            if (actionBarOverlayLayout != null) {
                ViewCompat.requestApplyInsets(actionBarOverlayLayout);
            }
        }
    };
    public final ViewPropertyAnimatorListener mShowListener = new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.app.WindowDecorActionBar.2
        public C03062() {
        }

        @Override // android.support.v4.view.ViewPropertyAnimatorListenerAdapter, android.support.v4.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            windowDecorActionBar.mCurrentShowAnim = null;
            windowDecorActionBar.mContainerView.requestLayout();
        }
    };
    public final ViewPropertyAnimatorUpdateListener mUpdateListener = new ViewPropertyAnimatorUpdateListener() { // from class: android.support.v7.app.WindowDecorActionBar.3
        public C03073() {
        }

        @Override // android.support.v4.view.ViewPropertyAnimatorUpdateListener
        public void onAnimationUpdate(View view) {
            ((View) WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
        }
    };

    /* renamed from: android.support.v7.app.WindowDecorActionBar$1 */
    public class C03051 extends ViewPropertyAnimatorListenerAdapter {
        public C03051() {
        }

        @Override // android.support.v4.view.ViewPropertyAnimatorListenerAdapter, android.support.v4.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            View view2;
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.mContentAnimations && (view2 = windowDecorActionBar.mContentView) != null) {
                view2.setTranslationY(0.0f);
                WindowDecorActionBar.this.mContainerView.setTranslationY(0.0f);
            }
            WindowDecorActionBar.this.mContainerView.setVisibility(8);
            WindowDecorActionBar.this.mContainerView.setTransitioning(false);
            WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
            windowDecorActionBar2.mCurrentShowAnim = null;
            windowDecorActionBar2.completeDeferredDestroyActionMode();
            ActionBarOverlayLayout actionBarOverlayLayout = WindowDecorActionBar.this.mOverlayLayout;
            if (actionBarOverlayLayout != null) {
                ViewCompat.requestApplyInsets(actionBarOverlayLayout);
            }
        }
    }

    /* renamed from: android.support.v7.app.WindowDecorActionBar$2 */
    public class C03062 extends ViewPropertyAnimatorListenerAdapter {
        public C03062() {
        }

        @Override // android.support.v4.view.ViewPropertyAnimatorListenerAdapter, android.support.v4.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            windowDecorActionBar.mCurrentShowAnim = null;
            windowDecorActionBar.mContainerView.requestLayout();
        }
    }

    /* renamed from: android.support.v7.app.WindowDecorActionBar$3 */
    public class C03073 implements ViewPropertyAnimatorUpdateListener {
        public C03073() {
        }

        @Override // android.support.v4.view.ViewPropertyAnimatorUpdateListener
        public void onAnimationUpdate(View view) {
            ((View) WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        private final Context mActionModeContext;
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private final MenuBuilder mMenu;

        public ActionModeImpl(Context context, ActionMode.Callback callback) {
            this.mActionModeContext = context;
            this.mCallback = callback;
            MenuBuilder defaultShowAsAction = new MenuBuilder(context).setDefaultShowAsAction(1);
            this.mMenu = defaultShowAsAction;
            defaultShowAsAction.setCallback(this);
        }

        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                return this.mCallback.onCreateActionMode(this, this.mMenu);
            } finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        @Override // android.support.v7.view.ActionMode
        public void finish() {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.mActionMode != this) {
                return;
            }
            if (WindowDecorActionBar.checkShowingFlags(windowDecorActionBar.mHiddenByApp, windowDecorActionBar.mHiddenBySystem, false)) {
                this.mCallback.onDestroyActionMode(this);
            } else {
                WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
                windowDecorActionBar2.mDeferredDestroyActionMode = this;
                windowDecorActionBar2.mDeferredModeDestroyCallback = this.mCallback;
            }
            this.mCallback = null;
            WindowDecorActionBar.this.animateToMode(false);
            WindowDecorActionBar.this.mContextView.closeMode();
            WindowDecorActionBar.this.mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
            WindowDecorActionBar windowDecorActionBar3 = WindowDecorActionBar.this;
            windowDecorActionBar3.mOverlayLayout.setHideOnContentScrollEnabled(windowDecorActionBar3.mHideOnContentScroll);
            WindowDecorActionBar.this.mActionMode = null;
        }

        @Override // android.support.v7.view.ActionMode
        public View getCustomView() {
            WeakReference<View> weakReference = this.mCustomView;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // android.support.v7.view.ActionMode
        public Menu getMenu() {
            return this.mMenu;
        }

        @Override // android.support.v7.view.ActionMode
        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.mActionModeContext);
        }

        @Override // android.support.v7.view.ActionMode
        public CharSequence getSubtitle() {
            return WindowDecorActionBar.this.mContextView.getSubtitle();
        }

        @Override // android.support.v7.view.ActionMode
        public CharSequence getTitle() {
            return WindowDecorActionBar.this.mContextView.getTitle();
        }

        @Override // android.support.v7.view.ActionMode
        public void invalidate() {
            if (WindowDecorActionBar.this.mActionMode != this) {
                return;
            }
            this.mMenu.stopDispatchingItemsChanged();
            try {
                this.mCallback.onPrepareActionMode(this, this.mMenu);
            } finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        @Override // android.support.v7.view.ActionMode
        public boolean isTitleOptional() {
            return WindowDecorActionBar.this.mContextView.isTitleOptional();
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z6) {
        }

        public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
        }

        @Override // android.support.v7.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            ActionMode.Callback callback = this.mCallback;
            if (callback != null) {
                return callback.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        @Override // android.support.v7.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.mCallback == null) {
                return;
            }
            invalidate();
            WindowDecorActionBar.this.mContextView.showOverflowMenu();
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            if (this.mCallback == null) {
                return false;
            }
            if (!subMenuBuilder.hasVisibleItems()) {
                return true;
            }
            new MenuPopupHelper(WindowDecorActionBar.this.getThemedContext(), subMenuBuilder).show();
            return true;
        }

        @Override // android.support.v7.view.ActionMode
        public void setCustomView(View view) {
            WindowDecorActionBar.this.mContextView.setCustomView(view);
            this.mCustomView = new WeakReference<>(view);
        }

        @Override // android.support.v7.view.ActionMode
        public void setSubtitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setSubtitle(charSequence);
        }

        @Override // android.support.v7.view.ActionMode
        public void setTitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setTitle(charSequence);
        }

        @Override // android.support.v7.view.ActionMode
        public void setTitleOptionalHint(boolean z6) {
            super.setTitleOptionalHint(z6);
            WindowDecorActionBar.this.mContextView.setTitleOptional(z6);
        }

        @Override // android.support.v7.view.ActionMode
        public void setSubtitle(int i7) {
            setSubtitle(WindowDecorActionBar.this.mContext.getResources().getString(i7));
        }

        @Override // android.support.v7.view.ActionMode
        public void setTitle(int i7) {
            setTitle(WindowDecorActionBar.this.mContext.getResources().getString(i7));
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public class TabImpl extends ActionBar.Tab {
        private ActionBar.TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private int mPosition = -1;
        private Object mTag;
        private CharSequence mText;

        public TabImpl() {
        }

        public ActionBar.TabListener getCallback() {
            return this.mCallback;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public View getCustomView() {
            return this.mCustomView;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public Drawable getIcon() {
            return this.mIcon;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public int getPosition() {
            return this.mPosition;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public Object getTag() {
            return this.mTag;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public CharSequence getText() {
            return this.mText;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public void select() {
            WindowDecorActionBar.this.selectTab(this);
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(int i7) {
            return setContentDescription(WindowDecorActionBar.this.mContext.getResources().getText(i7));
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(View view) {
            this.mCustomView = view;
            int i7 = this.mPosition;
            if (i7 >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(i7);
            }
            return this;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setIcon(Drawable drawable) {
            this.mIcon = drawable;
            int i7 = this.mPosition;
            if (i7 >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(i7);
            }
            return this;
        }

        public void setPosition(int i7) {
            this.mPosition = i7;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setTabListener(ActionBar.TabListener tabListener) {
            this.mCallback = tabListener;
            return this;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setText(CharSequence charSequence) {
            this.mText = charSequence;
            int i7 = this.mPosition;
            if (i7 >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(i7);
            }
            return this;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(CharSequence charSequence) {
            this.mContentDesc = charSequence;
            int i7 = this.mPosition;
            if (i7 >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(i7);
            }
            return this;
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(int i7) {
            return setCustomView(LayoutInflater.from(WindowDecorActionBar.this.getThemedContext()).inflate(i7, (ViewGroup) null));
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setIcon(int i7) {
            return setIcon(AppCompatResources.getDrawable(WindowDecorActionBar.this.mContext, i7));
        }

        @Override // android.support.v7.app.ActionBar.Tab
        public ActionBar.Tab setText(int i7) {
            return setText(WindowDecorActionBar.this.mContext.getResources().getText(i7));
        }
    }

    public WindowDecorActionBar(Activity activity, boolean z6) {
        this.mActivity = activity;
        View decorView = activity.getWindow().getDecorView();
        init(decorView);
        if (z6) {
            return;
        }
        this.mContentView = decorView.findViewById(R.id.content);
    }

    public static boolean checkShowingFlags(boolean z6, boolean z7, boolean z8) {
        if (z8) {
            return true;
        }
        return (z6 || z7) ? false : true;
    }

    private void cleanupTabs() {
        if (this.mSelectedTab != null) {
            selectTab(null);
        }
        this.mTabs.clear();
        ScrollingTabContainerView scrollingTabContainerView = this.mTabScrollView;
        if (scrollingTabContainerView != null) {
            scrollingTabContainerView.removeAllTabs();
        }
        this.mSavedTabPosition = -1;
    }

    private void configureTab(ActionBar.Tab tab, int i7) {
        TabImpl tabImpl = (TabImpl) tab;
        if (tabImpl.getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        tabImpl.setPosition(i7);
        this.mTabs.add(i7, tabImpl);
        int size = this.mTabs.size();
        while (true) {
            i7++;
            if (i7 >= size) {
                return;
            } else {
                this.mTabs.get(i7).setPosition(i7);
            }
        }
    }

    private void ensureTabsExist() {
        if (this.mTabScrollView != null) {
            return;
        }
        ScrollingTabContainerView scrollingTabContainerView = new ScrollingTabContainerView(this.mContext);
        if (this.mHasEmbeddedTabs) {
            scrollingTabContainerView.setVisibility(0);
            this.mDecorToolbar.setEmbeddedTabView(scrollingTabContainerView);
        } else {
            if (getNavigationMode() == 2) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
            this.mContainerView.setTabContainer(scrollingTabContainerView);
        }
        this.mTabScrollView = scrollingTabContainerView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DecorToolbar getDecorToolbar(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sbM112a = C0413b.m112a("Can't make a decor toolbar out of ");
        sbM112a.append(view != 0 ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sbM112a.toString());
    }

    private void hideForActionMode() {
        if (this.mShowingForMode) {
            this.mShowingForMode = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            updateVisibility(false);
        }
    }

    private void init(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(C0308R.id.decor_content_parent);
        this.mOverlayLayout = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.mDecorToolbar = getDecorToolbar(view.findViewById(C0308R.id.action_bar));
        this.mContextView = (ActionBarContextView) view.findViewById(C0308R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(C0308R.id.action_bar_container);
        this.mContainerView = actionBarContainer;
        DecorToolbar decorToolbar = this.mDecorToolbar;
        if (decorToolbar == null || this.mContextView == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.mContext = decorToolbar.getContext();
        boolean z6 = (this.mDecorToolbar.getDisplayOptions() & 4) != 0;
        if (z6) {
            this.mDisplayHomeAsUpSet = true;
        }
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(this.mContext);
        setHomeButtonEnabled(actionBarPolicy.enableHomeButtonByDefault() || z6);
        setHasEmbeddedTabs(actionBarPolicy.hasEmbeddedTabs());
        TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(null, C0308R.styleable.ActionBar, C0308R.attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.ActionBar_hideOnContentScroll, false)) {
            setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0308R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            setElevation(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void setHasEmbeddedTabs(boolean z6) {
        this.mHasEmbeddedTabs = z6;
        if (z6) {
            this.mContainerView.setTabContainer(null);
            this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
        } else {
            this.mDecorToolbar.setEmbeddedTabView(null);
            this.mContainerView.setTabContainer(this.mTabScrollView);
        }
        boolean z7 = getNavigationMode() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.mTabScrollView;
        if (scrollingTabContainerView != null) {
            if (z7) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.mDecorToolbar.setCollapsible(!this.mHasEmbeddedTabs && z7);
        this.mOverlayLayout.setHasNonEmbeddedTabs(!this.mHasEmbeddedTabs && z7);
    }

    private boolean shouldAnimateContextView() {
        return ViewCompat.isLaidOut(this.mContainerView);
    }

    private void showForActionMode() {
        if (this.mShowingForMode) {
            return;
        }
        this.mShowingForMode = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        updateVisibility(false);
    }

    private void updateVisibility(boolean z6) {
        if (checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
            if (this.mNowShowing) {
                return;
            }
            this.mNowShowing = true;
            doShow(z6);
            return;
        }
        if (this.mNowShowing) {
            this.mNowShowing = false;
            doHide(z6);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(onMenuVisibilityListener);
    }

    @Override // android.support.v7.app.ActionBar
    public void addTab(ActionBar.Tab tab) {
        addTab(tab, this.mTabs.isEmpty());
    }

    public void animateToMode(boolean z6) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2;
        if (z6) {
            showForActionMode();
        } else {
            hideForActionMode();
        }
        if (!shouldAnimateContextView()) {
            if (z6) {
                this.mDecorToolbar.setVisibility(4);
                this.mContextView.setVisibility(0);
                return;
            } else {
                this.mDecorToolbar.setVisibility(0);
                this.mContextView.setVisibility(8);
                return;
            }
        }
        if (z6) {
            viewPropertyAnimatorCompat2 = this.mDecorToolbar.setupAnimatorToVisibility(4, FADE_OUT_DURATION_MS);
            viewPropertyAnimatorCompat = this.mContextView.setupAnimatorToVisibility(0, FADE_IN_DURATION_MS);
        } else {
            viewPropertyAnimatorCompat = this.mDecorToolbar.setupAnimatorToVisibility(0, FADE_IN_DURATION_MS);
            viewPropertyAnimatorCompat2 = this.mContextView.setupAnimatorToVisibility(8, FADE_OUT_DURATION_MS);
        }
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
        viewPropertyAnimatorCompatSet.playSequentially(viewPropertyAnimatorCompat2, viewPropertyAnimatorCompat);
        viewPropertyAnimatorCompatSet.start();
    }

    @Override // android.support.v7.app.ActionBar
    public boolean collapseActionView() {
        DecorToolbar decorToolbar = this.mDecorToolbar;
        if (decorToolbar == null || !decorToolbar.hasExpandedActionView()) {
            return false;
        }
        this.mDecorToolbar.collapseActionView();
        return true;
    }

    public void completeDeferredDestroyActionMode() {
        ActionMode.Callback callback = this.mDeferredModeDestroyCallback;
        if (callback != null) {
            callback.onDestroyActionMode(this.mDeferredDestroyActionMode);
            this.mDeferredDestroyActionMode = null;
            this.mDeferredModeDestroyCallback = null;
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void dispatchMenuVisibilityChanged(boolean z6) {
        if (z6 == this.mLastMenuVisibility) {
            return;
        }
        this.mLastMenuVisibility = z6;
        int size = this.mMenuVisibilityListeners.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.mMenuVisibilityListeners.get(i7).onMenuVisibilityChanged(z6);
        }
    }

    public void doHide(boolean z6) {
        View view;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentShowAnim;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
        }
        if (this.mCurWindowVisibility != 0 || (!this.mShowHideAnimationEnabled && !z6)) {
            this.mHideListener.onAnimationEnd(null);
            return;
        }
        this.mContainerView.setAlpha(1.0f);
        this.mContainerView.setTransitioning(true);
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
        float f7 = -this.mContainerView.getHeight();
        if (z6) {
            this.mContainerView.getLocationInWindow(new int[]{0, 0});
            f7 -= r5[1];
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompatTranslationY = ViewCompat.animate(this.mContainerView).translationY(f7);
        viewPropertyAnimatorCompatTranslationY.setUpdateListener(this.mUpdateListener);
        viewPropertyAnimatorCompatSet2.play(viewPropertyAnimatorCompatTranslationY);
        if (this.mContentAnimations && (view = this.mContentView) != null) {
            viewPropertyAnimatorCompatSet2.play(ViewCompat.animate(view).translationY(f7));
        }
        viewPropertyAnimatorCompatSet2.setInterpolator(sHideInterpolator);
        viewPropertyAnimatorCompatSet2.setDuration(250L);
        viewPropertyAnimatorCompatSet2.setListener(this.mHideListener);
        this.mCurrentShowAnim = viewPropertyAnimatorCompatSet2;
        viewPropertyAnimatorCompatSet2.start();
    }

    public void doShow(boolean z6) {
        View view;
        View view2;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentShowAnim;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
        }
        this.mContainerView.setVisibility(0);
        if (this.mCurWindowVisibility == 0 && (this.mShowHideAnimationEnabled || z6)) {
            this.mContainerView.setTranslationY(0.0f);
            float f7 = -this.mContainerView.getHeight();
            if (z6) {
                this.mContainerView.getLocationInWindow(new int[]{0, 0});
                f7 -= r5[1];
            }
            this.mContainerView.setTranslationY(f7);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompatTranslationY = ViewCompat.animate(this.mContainerView).translationY(0.0f);
            viewPropertyAnimatorCompatTranslationY.setUpdateListener(this.mUpdateListener);
            viewPropertyAnimatorCompatSet2.play(viewPropertyAnimatorCompatTranslationY);
            if (this.mContentAnimations && (view2 = this.mContentView) != null) {
                view2.setTranslationY(f7);
                viewPropertyAnimatorCompatSet2.play(ViewCompat.animate(this.mContentView).translationY(0.0f));
            }
            viewPropertyAnimatorCompatSet2.setInterpolator(sShowInterpolator);
            viewPropertyAnimatorCompatSet2.setDuration(250L);
            viewPropertyAnimatorCompatSet2.setListener(this.mShowListener);
            this.mCurrentShowAnim = viewPropertyAnimatorCompatSet2;
            viewPropertyAnimatorCompatSet2.start();
        } else {
            this.mContainerView.setAlpha(1.0f);
            this.mContainerView.setTranslationY(0.0f);
            if (this.mContentAnimations && (view = this.mContentView) != null) {
                view.setTranslationY(0.0f);
            }
            this.mShowListener.onAnimationEnd(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
        if (actionBarOverlayLayout != null) {
            ViewCompat.requestApplyInsets(actionBarOverlayLayout);
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void enableContentAnimations(boolean z6) {
        this.mContentAnimations = z6;
    }

    @Override // android.support.v7.app.ActionBar
    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }

    @Override // android.support.v7.app.ActionBar
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    @Override // android.support.v7.app.ActionBar
    public float getElevation() {
        return ViewCompat.getElevation(this.mContainerView);
    }

    @Override // android.support.v7.app.ActionBar
    public int getHeight() {
        return this.mContainerView.getHeight();
    }

    @Override // android.support.v7.app.ActionBar
    public int getHideOffset() {
        return this.mOverlayLayout.getActionBarHideOffset();
    }

    @Override // android.support.v7.app.ActionBar
    public int getNavigationItemCount() {
        int navigationMode = this.mDecorToolbar.getNavigationMode();
        if (navigationMode == 1) {
            return this.mDecorToolbar.getDropdownItemCount();
        }
        if (navigationMode != 2) {
            return 0;
        }
        return this.mTabs.size();
    }

    @Override // android.support.v7.app.ActionBar
    public int getNavigationMode() {
        return this.mDecorToolbar.getNavigationMode();
    }

    @Override // android.support.v7.app.ActionBar
    public int getSelectedNavigationIndex() {
        TabImpl tabImpl;
        int navigationMode = this.mDecorToolbar.getNavigationMode();
        if (navigationMode == 1) {
            return this.mDecorToolbar.getDropdownSelectedPosition();
        }
        if (navigationMode == 2 && (tabImpl = this.mSelectedTab) != null) {
            return tabImpl.getPosition();
        }
        return -1;
    }

    @Override // android.support.v7.app.ActionBar
    public ActionBar.Tab getSelectedTab() {
        return this.mSelectedTab;
    }

    @Override // android.support.v7.app.ActionBar
    public CharSequence getSubtitle() {
        return this.mDecorToolbar.getSubtitle();
    }

    @Override // android.support.v7.app.ActionBar
    public ActionBar.Tab getTabAt(int i7) {
        return this.mTabs.get(i7);
    }

    @Override // android.support.v7.app.ActionBar
    public int getTabCount() {
        return this.mTabs.size();
    }

    @Override // android.support.v7.app.ActionBar
    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(C0308R.attr.actionBarWidgetTheme, typedValue, true);
            int i7 = typedValue.resourceId;
            if (i7 != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mContext, i7);
            } else {
                this.mThemedContext = this.mContext;
            }
        }
        return this.mThemedContext;
    }

    @Override // android.support.v7.app.ActionBar
    public CharSequence getTitle() {
        return this.mDecorToolbar.getTitle();
    }

    public boolean hasIcon() {
        return this.mDecorToolbar.hasIcon();
    }

    public boolean hasLogo() {
        return this.mDecorToolbar.hasLogo();
    }

    @Override // android.support.v7.app.ActionBar
    public void hide() {
        if (this.mHiddenByApp) {
            return;
        }
        this.mHiddenByApp = true;
        updateVisibility(false);
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void hideForSystem() {
        if (this.mHiddenBySystem) {
            return;
        }
        this.mHiddenBySystem = true;
        updateVisibility(true);
    }

    @Override // android.support.v7.app.ActionBar
    public boolean isHideOnContentScrollEnabled() {
        return this.mOverlayLayout.isHideOnContentScrollEnabled();
    }

    @Override // android.support.v7.app.ActionBar
    public boolean isShowing() {
        int height = getHeight();
        return this.mNowShowing && (height == 0 || getHideOffset() < height);
    }

    @Override // android.support.v7.app.ActionBar
    public boolean isTitleTruncated() {
        DecorToolbar decorToolbar = this.mDecorToolbar;
        return decorToolbar != null && decorToolbar.isTitleTruncated();
    }

    @Override // android.support.v7.app.ActionBar
    public ActionBar.Tab newTab() {
        return new TabImpl();
    }

    @Override // android.support.v7.app.ActionBar
    public void onConfigurationChanged(Configuration configuration) {
        setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onContentScrollStarted() {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.mCurrentShowAnim;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.cancel();
            this.mCurrentShowAnim = null;
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onContentScrollStopped() {
    }

    @Override // android.support.v7.app.ActionBar
    public boolean onKeyShortcut(int i7, KeyEvent keyEvent) {
        Menu menu;
        ActionModeImpl actionModeImpl = this.mActionMode;
        if (actionModeImpl == null || (menu = actionModeImpl.getMenu()) == null) {
            return false;
        }
        menu.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menu.performShortcut(i7, keyEvent, 0);
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void onWindowVisibilityChanged(int i7) {
        this.mCurWindowVisibility = i7;
    }

    @Override // android.support.v7.app.ActionBar
    public void removeAllTabs() {
        cleanupTabs();
    }

    @Override // android.support.v7.app.ActionBar
    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(onMenuVisibilityListener);
    }

    @Override // android.support.v7.app.ActionBar
    public void removeTab(ActionBar.Tab tab) {
        removeTabAt(tab.getPosition());
    }

    @Override // android.support.v7.app.ActionBar
    public void removeTabAt(int i7) {
        if (this.mTabScrollView == null) {
            return;
        }
        TabImpl tabImpl = this.mSelectedTab;
        int position = tabImpl != null ? tabImpl.getPosition() : this.mSavedTabPosition;
        this.mTabScrollView.removeTabAt(i7);
        TabImpl tabImplRemove = this.mTabs.remove(i7);
        if (tabImplRemove != null) {
            tabImplRemove.setPosition(-1);
        }
        int size = this.mTabs.size();
        for (int i8 = i7; i8 < size; i8++) {
            this.mTabs.get(i8).setPosition(i8);
        }
        if (position == i7) {
            selectTab(this.mTabs.isEmpty() ? null : this.mTabs.get(Math.max(0, i7 - 1)));
        }
    }

    @Override // android.support.v7.app.ActionBar
    public boolean requestFocus() {
        ViewGroup viewGroup = this.mDecorToolbar.getViewGroup();
        if (viewGroup == null || viewGroup.hasFocus()) {
            return false;
        }
        viewGroup.requestFocus();
        return true;
    }

    @Override // android.support.v7.app.ActionBar
    public void selectTab(ActionBar.Tab tab) {
        if (getNavigationMode() != 2) {
            this.mSavedTabPosition = tab != null ? tab.getPosition() : -1;
            return;
        }
        FragmentTransaction fragmentTransactionDisallowAddToBackStack = (!(this.mActivity instanceof FragmentActivity) || this.mDecorToolbar.getViewGroup().isInEditMode()) ? null : ((FragmentActivity) this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        TabImpl tabImpl = this.mSelectedTab;
        if (tabImpl != tab) {
            this.mTabScrollView.setTabSelected(tab != null ? tab.getPosition() : -1);
            TabImpl tabImpl2 = this.mSelectedTab;
            if (tabImpl2 != null) {
                tabImpl2.getCallback().onTabUnselected(this.mSelectedTab, fragmentTransactionDisallowAddToBackStack);
            }
            TabImpl tabImpl3 = (TabImpl) tab;
            this.mSelectedTab = tabImpl3;
            if (tabImpl3 != null) {
                tabImpl3.getCallback().onTabSelected(this.mSelectedTab, fragmentTransactionDisallowAddToBackStack);
            }
        } else if (tabImpl != null) {
            tabImpl.getCallback().onTabReselected(this.mSelectedTab, fragmentTransactionDisallowAddToBackStack);
            this.mTabScrollView.animateToTab(tab.getPosition());
        }
        if (fragmentTransactionDisallowAddToBackStack == null || fragmentTransactionDisallowAddToBackStack.isEmpty()) {
            return;
        }
        fragmentTransactionDisallowAddToBackStack.commit();
    }

    @Override // android.support.v7.app.ActionBar
    public void setBackgroundDrawable(Drawable drawable) {
        this.mContainerView.setPrimaryBackground(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void setCustomView(int i7) {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(i7, this.mDecorToolbar.getViewGroup(), false));
    }

    @Override // android.support.v7.app.ActionBar
    public void setDefaultDisplayHomeAsUpEnabled(boolean z6) {
        if (this.mDisplayHomeAsUpSet) {
            return;
        }
        setDisplayHomeAsUpEnabled(z6);
    }

    @Override // android.support.v7.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean z6) {
        setDisplayOptions(z6 ? 4 : 0, 4);
    }

    @Override // android.support.v7.app.ActionBar
    public void setDisplayOptions(int i7) {
        if ((i7 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions(i7);
    }

    @Override // android.support.v7.app.ActionBar
    public void setDisplayShowCustomEnabled(boolean z6) {
        setDisplayOptions(z6 ? 16 : 0, 16);
    }

    @Override // android.support.v7.app.ActionBar
    public void setDisplayShowHomeEnabled(boolean z6) {
        setDisplayOptions(z6 ? 2 : 0, 2);
    }

    @Override // android.support.v7.app.ActionBar
    public void setDisplayShowTitleEnabled(boolean z6) {
        setDisplayOptions(z6 ? 8 : 0, 8);
    }

    @Override // android.support.v7.app.ActionBar
    public void setDisplayUseLogoEnabled(boolean z6) {
        setDisplayOptions(z6 ? 1 : 0, 1);
    }

    @Override // android.support.v7.app.ActionBar
    public void setElevation(float f7) {
        ViewCompat.setElevation(this.mContainerView, f7);
    }

    @Override // android.support.v7.app.ActionBar
    public void setHideOffset(int i7) {
        if (i7 != 0 && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
        }
        this.mOverlayLayout.setActionBarHideOffset(i7);
    }

    @Override // android.support.v7.app.ActionBar
    public void setHideOnContentScrollEnabled(boolean z6) {
        if (z6 && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.mHideOnContentScroll = z6;
        this.mOverlayLayout.setHideOnContentScrollEnabled(z6);
    }

    @Override // android.support.v7.app.ActionBar
    public void setHomeActionContentDescription(CharSequence charSequence) {
        this.mDecorToolbar.setNavigationContentDescription(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void setHomeAsUpIndicator(Drawable drawable) {
        this.mDecorToolbar.setNavigationIcon(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void setHomeButtonEnabled(boolean z6) {
        this.mDecorToolbar.setHomeButtonEnabled(z6);
    }

    @Override // android.support.v7.app.ActionBar
    public void setIcon(int i7) {
        this.mDecorToolbar.setIcon(i7);
    }

    @Override // android.support.v7.app.ActionBar
    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
        this.mDecorToolbar.setDropdownParams(spinnerAdapter, new NavItemSelectedListener(onNavigationListener));
    }

    @Override // android.support.v7.app.ActionBar
    public void setLogo(int i7) {
        this.mDecorToolbar.setLogo(i7);
    }

    @Override // android.support.v7.app.ActionBar
    public void setNavigationMode(int i7) {
        ActionBarOverlayLayout actionBarOverlayLayout;
        int navigationMode = this.mDecorToolbar.getNavigationMode();
        if (navigationMode == 2) {
            this.mSavedTabPosition = getSelectedNavigationIndex();
            selectTab(null);
            this.mTabScrollView.setVisibility(8);
        }
        if (navigationMode != i7 && !this.mHasEmbeddedTabs && (actionBarOverlayLayout = this.mOverlayLayout) != null) {
            ViewCompat.requestApplyInsets(actionBarOverlayLayout);
        }
        this.mDecorToolbar.setNavigationMode(i7);
        boolean z6 = false;
        if (i7 == 2) {
            ensureTabsExist();
            this.mTabScrollView.setVisibility(0);
            int i8 = this.mSavedTabPosition;
            if (i8 != -1) {
                setSelectedNavigationItem(i8);
                this.mSavedTabPosition = -1;
            }
        }
        this.mDecorToolbar.setCollapsible(i7 == 2 && !this.mHasEmbeddedTabs);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.mOverlayLayout;
        if (i7 == 2 && !this.mHasEmbeddedTabs) {
            z6 = true;
        }
        actionBarOverlayLayout2.setHasNonEmbeddedTabs(z6);
    }

    @Override // android.support.v7.app.ActionBar
    public void setSelectedNavigationItem(int i7) {
        int navigationMode = this.mDecorToolbar.getNavigationMode();
        if (navigationMode == 1) {
            this.mDecorToolbar.setDropdownSelectedPosition(i7);
        } else {
            if (navigationMode != 2) {
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
            }
            selectTab(this.mTabs.get(i7));
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void setShowHideAnimationEnabled(boolean z6) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        this.mShowHideAnimationEnabled = z6;
        if (z6 || (viewPropertyAnimatorCompatSet = this.mCurrentShowAnim) == null) {
            return;
        }
        viewPropertyAnimatorCompatSet.cancel();
    }

    @Override // android.support.v7.app.ActionBar
    public void setSplitBackgroundDrawable(Drawable drawable) {
    }

    @Override // android.support.v7.app.ActionBar
    public void setStackedBackgroundDrawable(Drawable drawable) {
        this.mContainerView.setStackedBackground(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void setSubtitle(int i7) {
        setSubtitle(this.mContext.getString(i7));
    }

    @Override // android.support.v7.app.ActionBar
    public void setTitle(int i7) {
        setTitle(this.mContext.getString(i7));
    }

    @Override // android.support.v7.app.ActionBar
    public void setWindowTitle(CharSequence charSequence) {
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void show() {
        if (this.mHiddenByApp) {
            this.mHiddenByApp = false;
            updateVisibility(false);
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public void showForSystem() {
        if (this.mHiddenBySystem) {
            this.mHiddenBySystem = false;
            updateVisibility(true);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionModeImpl actionModeImpl = this.mActionMode;
        if (actionModeImpl != null) {
            actionModeImpl.finish();
        }
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        ActionModeImpl actionModeImpl2 = new ActionModeImpl(this.mContextView.getContext(), callback);
        if (!actionModeImpl2.dispatchOnCreate()) {
            return null;
        }
        this.mActionMode = actionModeImpl2;
        actionModeImpl2.invalidate();
        this.mContextView.initForMode(actionModeImpl2);
        animateToMode(true);
        this.mContextView.sendAccessibilityEvent(32);
        return actionModeImpl2;
    }

    @Override // android.support.v7.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i7) {
        addTab(tab, i7, this.mTabs.isEmpty());
    }

    @Override // android.support.v7.app.ActionBar
    public void setHomeActionContentDescription(int i7) {
        this.mDecorToolbar.setNavigationContentDescription(i7);
    }

    @Override // android.support.v7.app.ActionBar
    public void setHomeAsUpIndicator(int i7) {
        this.mDecorToolbar.setNavigationIcon(i7);
    }

    @Override // android.support.v7.app.ActionBar
    public void setIcon(Drawable drawable) {
        this.mDecorToolbar.setIcon(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void setLogo(Drawable drawable) {
        this.mDecorToolbar.setLogo(drawable);
    }

    @Override // android.support.v7.app.ActionBar
    public void setSubtitle(CharSequence charSequence) {
        this.mDecorToolbar.setSubtitle(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void setTitle(CharSequence charSequence) {
        this.mDecorToolbar.setTitle(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public void addTab(ActionBar.Tab tab, boolean z6) {
        ensureTabsExist();
        this.mTabScrollView.addTab(tab, z6);
        configureTab(tab, this.mTabs.size());
        if (z6) {
            selectTab(tab);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public void setDisplayOptions(int i7, int i8) {
        int displayOptions = this.mDecorToolbar.getDisplayOptions();
        if ((i8 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions((i7 & i8) | ((~i8) & displayOptions));
    }

    @Override // android.support.v7.app.ActionBar
    public void setCustomView(View view) {
        this.mDecorToolbar.setCustomView(view);
    }

    @Override // android.support.v7.app.ActionBar
    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        this.mDecorToolbar.setCustomView(view);
    }

    @Override // android.support.v7.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i7, boolean z6) {
        ensureTabsExist();
        this.mTabScrollView.addTab(tab, i7, z6);
        configureTab(tab, i7);
        if (z6) {
            selectTab(tab);
        }
    }

    public WindowDecorActionBar(Dialog dialog) {
        this.mDialog = dialog;
        init(dialog.getWindow().getDecorView());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WindowDecorActionBar(View view) {
        init(view);
    }
}
