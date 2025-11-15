package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.GravityCompat;
import android.support.v7.appcompat.C0308R;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionMenuView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* loaded from: classes.dex */
class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
    private static final String TAG = "ActionMenuPresenter";
    private final SparseBooleanArray mActionButtonGroups;
    public ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    public int mOpenSubMenuId;
    public OverflowMenuButton mOverflowButton;
    public OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    public final PopupPresenterCallback mPopupPresenterCallback;
    public OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    public class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(Context context, SubMenuBuilder subMenuBuilder, View view) {
            super(context, subMenuBuilder, view, false, C0308R.attr.actionOverflowMenuStyle);
            if (!((MenuItemImpl) subMenuBuilder.getItem()).isActionButton()) {
                View view2 = ActionMenuPresenter.this.mOverflowButton;
                setAnchorView(view2 == null ? (View) ActionMenuPresenter.this.mMenuView : view2);
            }
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        @Override // android.support.v7.view.menu.MenuPopupHelper
        public void onDismiss() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.mActionButtonPopup = null;
            actionMenuPresenter.mOpenSubMenuId = 0;
            super.onDismiss();
        }
    }

    public class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
        public ActionMenuPopupCallback() {
        }

        @Override // android.support.v7.view.menu.ActionMenuItemView.PopupCallback
        public ShowableListMenu getPopup() {
            ActionButtonSubmenu actionButtonSubmenu = ActionMenuPresenter.this.mActionButtonPopup;
            if (actionButtonSubmenu != null) {
                return actionButtonSubmenu.getPopup();
            }
            return null;
        }
    }

    public class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;

        public OpenOverflowRunnable(OverflowPopup overflowPopup) {
            this.mPopup = overflowPopup;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.changeMenuMode();
            }
            View view = (View) ActionMenuPresenter.this.mMenuView;
            if (view != null && view.getWindowToken() != null && this.mPopup.tryShow()) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    public class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
        private final float[] mTempPts;

        public OverflowMenuButton(Context context) {
            super(context, null, C0308R.attr.actionOverflowButtonStyle);
            this.mTempPts = new float[2];
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            TooltipCompat.setTooltipText(this, getContentDescription());
            setOnTouchListener(new ForwardingListener(this) { // from class: android.support.v7.widget.ActionMenuPresenter.OverflowMenuButton.1
                @Override // android.support.v7.widget.ForwardingListener
                public ShowableListMenu getPopup() {
                    OverflowPopup overflowPopup = ActionMenuPresenter.this.mOverflowPopup;
                    if (overflowPopup == null) {
                        return null;
                    }
                    return overflowPopup.getPopup();
                }

                @Override // android.support.v7.widget.ForwardingListener
                public boolean onForwardingStarted() {
                    ActionMenuPresenter.this.showOverflowMenu();
                    return true;
                }

                @Override // android.support.v7.widget.ForwardingListener
                public boolean onForwardingStopped() {
                    ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                    if (actionMenuPresenter.mPostedOpenRunnable != null) {
                        return false;
                    }
                    actionMenuPresenter.hideOverflowMenu();
                    return true;
                }
            });
        }

        @Override // android.support.v7.widget.ActionMenuView.ActionMenuChildView
        public boolean needsDividerAfter() {
            return false;
        }

        @Override // android.support.v7.widget.ActionMenuView.ActionMenuChildView
        public boolean needsDividerBefore() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int i7, int i8, int i9, int i10) {
            boolean frame = super.setFrame(i7, i8, i9, i10);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    public class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(Context context, MenuBuilder menuBuilder, View view, boolean z6) {
            super(context, menuBuilder, view, z6, C0308R.attr.actionOverflowMenuStyle);
            setGravity(GravityCompat.END);
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        @Override // android.support.v7.view.menu.MenuPopupHelper
        public void onDismiss() {
            if (ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.close();
            }
            ActionMenuPresenter.this.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    public class PopupPresenterCallback implements MenuPresenter.Callback {
        public PopupPresenterCallback() {
        }

        @Override // android.support.v7.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z6) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.getRootMenu().close(false);
            }
            MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
            if (callback != null) {
                callback.onCloseMenu(menuBuilder, z6);
            }
        }

        @Override // android.support.v7.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
            if (callback != null) {
                return callback.onOpenSubMenu(menuBuilder);
            }
            return false;
        }
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v7.widget.ActionMenuPresenter.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        };
        public int openSubMenuId;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeInt(this.openSubMenuId);
        }

        public SavedState(Parcel parcel) {
            this.openSubMenuId = parcel.readInt();
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, C0308R.layout.abc_action_menu_layout, C0308R.layout.abc_action_menu_item_layout);
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback = new PopupPresenterCallback();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View findViewForItem(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            if ((childAt instanceof MenuView.ItemView) && ((MenuView.ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter
    public void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
        itemView.initialize(menuItemImpl, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.mMenuView);
        if (this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPopupCallback();
        }
        actionMenuItemView.setPopupCallback(this.mPopupCallback);
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter
    public boolean filterLeftoverView(ViewGroup viewGroup, int i7) {
        if (viewGroup.getChildAt(i7) == this.mOverflowButton) {
            return false;
        }
        return super.filterLeftoverView(viewGroup, i7);
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public boolean flagActionItems() {
        ArrayList<MenuItemImpl> visibleItems;
        int size;
        int iMeasureChildForCells;
        int i7;
        int i8;
        ActionMenuPresenter actionMenuPresenter = this;
        MenuBuilder menuBuilder = actionMenuPresenter.mMenu;
        int i9 = 0;
        if (menuBuilder != null) {
            visibleItems = menuBuilder.getVisibleItems();
            size = visibleItems.size();
        } else {
            visibleItems = null;
            size = 0;
        }
        int i10 = actionMenuPresenter.mMaxItems;
        int i11 = actionMenuPresenter.mActionItemWidthLimit;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.mMenuView;
        boolean z6 = false;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < size; i14++) {
            MenuItemImpl menuItemImpl = visibleItems.get(i14);
            if (menuItemImpl.requiresActionButton()) {
                i12++;
            } else if (menuItemImpl.requestsActionButton()) {
                i13++;
            } else {
                z6 = true;
            }
            if (actionMenuPresenter.mExpandedActionViewsExclusive && menuItemImpl.isActionViewExpanded()) {
                i10 = 0;
            }
        }
        if (actionMenuPresenter.mReserveOverflow && (z6 || i13 + i12 > i10)) {
            i10--;
        }
        int i15 = i10 - i12;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.mActionButtonGroups;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.mStrictWidthLimit) {
            int i16 = actionMenuPresenter.mMinCellSize;
            iMeasureChildForCells = i11 / i16;
            i7 = ((i11 % i16) / iMeasureChildForCells) + i16;
        } else {
            iMeasureChildForCells = 0;
            i7 = 0;
        }
        int i17 = 0;
        int i18 = 0;
        while (i17 < size) {
            MenuItemImpl menuItemImpl2 = visibleItems.get(i17);
            if (menuItemImpl2.requiresActionButton()) {
                View itemView = actionMenuPresenter.getItemView(menuItemImpl2, actionMenuPresenter.mScrapActionButtonView, viewGroup);
                if (actionMenuPresenter.mScrapActionButtonView == null) {
                    actionMenuPresenter.mScrapActionButtonView = itemView;
                }
                if (actionMenuPresenter.mStrictWidthLimit) {
                    iMeasureChildForCells -= ActionMenuView.measureChildForCells(itemView, i7, iMeasureChildForCells, iMakeMeasureSpec, i9);
                } else {
                    itemView.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = itemView.getMeasuredWidth();
                i11 -= measuredWidth;
                if (i18 == 0) {
                    i18 = measuredWidth;
                }
                int groupId = menuItemImpl2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                menuItemImpl2.setIsActionButton(true);
                i8 = size;
            } else if (menuItemImpl2.requestsActionButton()) {
                int groupId2 = menuItemImpl2.getGroupId();
                boolean z7 = sparseBooleanArray.get(groupId2);
                boolean z8 = (i15 > 0 || z7) && i11 > 0 && (!actionMenuPresenter.mStrictWidthLimit || iMeasureChildForCells > 0);
                boolean z9 = z8;
                if (z8) {
                    View itemView2 = actionMenuPresenter.getItemView(menuItemImpl2, actionMenuPresenter.mScrapActionButtonView, viewGroup);
                    i8 = size;
                    if (actionMenuPresenter.mScrapActionButtonView == null) {
                        actionMenuPresenter.mScrapActionButtonView = itemView2;
                    }
                    if (actionMenuPresenter.mStrictWidthLimit) {
                        int iMeasureChildForCells2 = ActionMenuView.measureChildForCells(itemView2, i7, iMeasureChildForCells, iMakeMeasureSpec, 0);
                        iMeasureChildForCells -= iMeasureChildForCells2;
                        if (iMeasureChildForCells2 == 0) {
                            z9 = false;
                        }
                    } else {
                        itemView2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    int measuredWidth2 = itemView2.getMeasuredWidth();
                    i11 -= measuredWidth2;
                    if (i18 == 0) {
                        i18 = measuredWidth2;
                    }
                    z8 = z9 & (!actionMenuPresenter.mStrictWidthLimit ? i11 + i18 <= 0 : i11 < 0);
                } else {
                    i8 = size;
                }
                if (z8 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z7) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i19 = 0; i19 < i17; i19++) {
                        MenuItemImpl menuItemImpl3 = visibleItems.get(i19);
                        if (menuItemImpl3.getGroupId() == groupId2) {
                            if (menuItemImpl3.isActionButton()) {
                                i15++;
                            }
                            menuItemImpl3.setIsActionButton(false);
                        }
                    }
                }
                if (z8) {
                    i15--;
                }
                menuItemImpl2.setIsActionButton(z8);
            } else {
                i8 = size;
                menuItemImpl2.setIsActionButton(false);
                i17++;
                actionMenuPresenter = this;
                size = i8;
                i9 = 0;
            }
            i17++;
            actionMenuPresenter = this;
            size = i8;
            i9 = 0;
        }
        return true;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter
    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.hasCollapsibleActionView()) {
            actionView = super.getItemView(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup) {
        MenuView menuView = this.mMenuView;
        MenuView menuView2 = super.getMenuView(viewGroup);
        if (menuView != menuView2) {
            ((ActionMenuView) menuView2).setPresenter(this);
        }
        return menuView2;
    }

    public Drawable getOverflowIcon() {
        OverflowMenuButton overflowMenuButton = this.mOverflowButton;
        if (overflowMenuButton != null) {
            return overflowMenuButton.getDrawable();
        }
        if (this.mPendingOverflowIconSet) {
            return this.mPendingOverflowIcon;
        }
        return null;
    }

    public boolean hideOverflowMenu() {
        Object obj;
        OpenOverflowRunnable openOverflowRunnable = this.mPostedOpenRunnable;
        if (openOverflowRunnable != null && (obj = this.mMenuView) != null) {
            ((View) obj).removeCallbacks(openOverflowRunnable);
            this.mPostedOpenRunnable = null;
            return true;
        }
        OverflowPopup overflowPopup = this.mOverflowPopup;
        if (overflowPopup == null) {
            return false;
        }
        overflowPopup.dismiss();
        return true;
    }

    public boolean hideSubMenus() {
        ActionButtonSubmenu actionButtonSubmenu = this.mActionButtonPopup;
        if (actionButtonSubmenu == null) {
            return false;
        }
        actionButtonSubmenu.dismiss();
        return true;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public void initForMenu(@NonNull Context context, @Nullable MenuBuilder menuBuilder) {
        super.initForMenu(context, menuBuilder);
        Resources resources = context.getResources();
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(context);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = actionBarPolicy.showsOverflowMenuButton();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = actionBarPolicy.getEmbeddedMenuWidthLimit();
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = actionBarPolicy.getMaxActionButtons();
        }
        int measuredWidth = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                OverflowMenuButton overflowMenuButton = new OverflowMenuButton(this.mSystemContext);
                this.mOverflowButton = overflowMenuButton;
                if (this.mPendingOverflowIconSet) {
                    overflowMenuButton.setImageDrawable(this.mPendingOverflowIcon);
                    this.mPendingOverflowIcon = null;
                    this.mPendingOverflowIconSet = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = measuredWidth;
        this.mMinCellSize = (int) (resources.getDisplayMetrics().density * 56.0f);
        this.mScrapActionButtonView = null;
    }

    public boolean isOverflowMenuShowPending() {
        return this.mPostedOpenRunnable != null || isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowing() {
        OverflowPopup overflowPopup = this.mOverflowPopup;
        return overflowPopup != null && overflowPopup.isShowing();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z6) {
        dismissPopupMenus();
        super.onCloseMenu(menuBuilder, z6);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mMaxItemsSet) {
            this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
        }
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder != null) {
            menuBuilder.onItemsChanged(true);
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        int i7;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof SavedState) && (i7 = ((SavedState) parcelable).openSubMenuId) > 0 && (menuItemFindItem = this.mMenu.findItem(i7)) != null) {
            onSubMenuSelected((SubMenuBuilder) menuItemFindItem.getSubMenu());
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.openSubMenuId = this.mOpenSubMenuId;
        return savedState;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        boolean z6 = false;
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.getParentMenu() != this.mMenu) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.getParentMenu();
        }
        View viewFindViewForItem = findViewForItem(subMenuBuilder2.getItem());
        if (viewFindViewForItem == null) {
            return false;
        }
        this.mOpenSubMenuId = subMenuBuilder.getItem().getItemId();
        int size = subMenuBuilder.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                break;
            }
            MenuItem item = subMenuBuilder.getItem(i7);
            if (item.isVisible() && item.getIcon() != null) {
                z6 = true;
                break;
            }
            i7++;
        }
        ActionButtonSubmenu actionButtonSubmenu = new ActionButtonSubmenu(this.mContext, subMenuBuilder, viewFindViewForItem);
        this.mActionButtonPopup = actionButtonSubmenu;
        actionButtonSubmenu.setForceShowIcon(z6);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(subMenuBuilder);
        return true;
    }

    @Override // android.support.v4.view.ActionProvider.SubUiVisibilityListener
    public void onSubUiVisibilityChanged(boolean z6) {
        if (z6) {
            super.onSubMenuSelected(null);
            return;
        }
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder != null) {
            menuBuilder.close(false);
        }
    }

    public void setExpandedActionViewsExclusive(boolean z6) {
        this.mExpandedActionViewsExclusive = z6;
    }

    public void setItemLimit(int i7) {
        this.mMaxItems = i7;
        this.mMaxItemsSet = true;
    }

    public void setMenuView(ActionMenuView actionMenuView) {
        this.mMenuView = actionMenuView;
        actionMenuView.initialize(this.mMenu);
    }

    public void setOverflowIcon(Drawable drawable) {
        OverflowMenuButton overflowMenuButton = this.mOverflowButton;
        if (overflowMenuButton != null) {
            overflowMenuButton.setImageDrawable(drawable);
        } else {
            this.mPendingOverflowIconSet = true;
            this.mPendingOverflowIcon = drawable;
        }
    }

    public void setReserveOverflow(boolean z6) {
        this.mReserveOverflow = z6;
        this.mReserveOverflowSet = true;
    }

    public void setWidthLimit(int i7, boolean z6) {
        this.mWidthLimit = i7;
        this.mStrictWidthLimit = z6;
        this.mWidthLimitSet = true;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter
    public boolean shouldIncludeItem(int i7, MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    public boolean showOverflowMenu() {
        MenuBuilder menuBuilder;
        if (!this.mReserveOverflow || isOverflowMenuShowing() || (menuBuilder = this.mMenu) == null || this.mMenuView == null || this.mPostedOpenRunnable != null || menuBuilder.getNonActionItems().isEmpty()) {
            return false;
        }
        OpenOverflowRunnable openOverflowRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, true));
        this.mPostedOpenRunnable = openOverflowRunnable;
        ((View) this.mMenuView).post(openOverflowRunnable);
        super.onSubMenuSelected(null);
        return true;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public void updateMenuView(boolean z6) {
        super.updateMenuView(z6);
        ((View) this.mMenuView).requestLayout();
        MenuBuilder menuBuilder = this.mMenu;
        boolean z7 = false;
        if (menuBuilder != null) {
            ArrayList<MenuItemImpl> actionItems = menuBuilder.getActionItems();
            int size = actionItems.size();
            for (int i7 = 0; i7 < size; i7++) {
                ActionProvider supportActionProvider = actionItems.get(i7).getSupportActionProvider();
                if (supportActionProvider != null) {
                    supportActionProvider.setSubUiVisibilityListener(this);
                }
            }
        }
        MenuBuilder menuBuilder2 = this.mMenu;
        ArrayList<MenuItemImpl> nonActionItems = menuBuilder2 != null ? menuBuilder2.getNonActionItems() : null;
        if (this.mReserveOverflow && nonActionItems != null) {
            int size2 = nonActionItems.size();
            if (size2 == 1) {
                z7 = !nonActionItems.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z7 = true;
            }
        }
        if (z7) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
            }
            ViewGroup viewGroup = (ViewGroup) this.mOverflowButton.getParent();
            if (viewGroup != this.mMenuView) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.mOverflowButton);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.mMenuView;
                actionMenuView.addView(this.mOverflowButton, actionMenuView.generateOverflowButtonLayoutParams());
            }
        } else {
            OverflowMenuButton overflowMenuButton = this.mOverflowButton;
            if (overflowMenuButton != null) {
                Object parent = overflowMenuButton.getParent();
                Object obj = this.mMenuView;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.mOverflowButton);
                }
            }
        }
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }
}
