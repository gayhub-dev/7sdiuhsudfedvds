package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    public static final int GENERATED_ITEM_PADDING = 4;
    public static final int MIN_CELL_SIZE = 56;
    private static final String TAG = "ActionMenuView";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    public MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    public OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    public static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        @Override // android.support.v7.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z6) {
        }

        @Override // android.support.v7.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }
    }

    public static class LayoutParams extends LinearLayoutCompat.LayoutParams {

        @ViewDebug.ExportedProperty
        public int cellsUsed;

        @ViewDebug.ExportedProperty
        public boolean expandable;
        public boolean expanded;

        @ViewDebug.ExportedProperty
        public int extraPixels;

        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;

        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }

        public LayoutParams(int i7, int i8) {
            super(i7, i8);
            this.isOverflowButton = false;
        }

        public LayoutParams(int i7, int i8, boolean z6) {
            super(i7, i8);
            this.isOverflowButton = z6;
        }
    }

    public class MenuBuilderCallback implements MenuBuilder.Callback {
        public MenuBuilderCallback() {
        }

        @Override // android.support.v7.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            OnMenuItemClickListener onMenuItemClickListener = ActionMenuView.this.mOnMenuItemClickListener;
            return onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(menuItem);
        }

        @Override // android.support.v7.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menuBuilder) {
            MenuBuilder.Callback callback = ActionMenuView.this.mMenuBuilderCallback;
            if (callback != null) {
                callback.onMenuModeChange(menuBuilder);
            }
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public static int measureChildForCells(View view, int i7, int i8, int i9, int i10) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i9) - i10, View.MeasureSpec.getMode(i9));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z6 = actionMenuItemView != null && actionMenuItemView.hasText();
        int i11 = 2;
        if (i8 <= 0 || (z6 && i8 < 2)) {
            i11 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(i8 * i7, Integer.MIN_VALUE), iMakeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int i12 = measuredWidth / i7;
            if (measuredWidth % i7 != 0) {
                i12++;
            }
            if (!z6 || i12 >= 2) {
                i11 = i12;
            }
        }
        layoutParams.expandable = !layoutParams.isOverflowButton && z6;
        layoutParams.cellsUsed = i11;
        view.measure(View.MeasureSpec.makeMeasureSpec(i7 * i11, 1073741824), iMakeMeasureSpec);
        return i11;
    }

    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8, types: [boolean, int] */
    private void onMeasureExactFormat(int i7, int i8) {
        int i9;
        int i10;
        boolean z6;
        int i11;
        boolean z7;
        boolean z8;
        int i12;
        ?? r14;
        int i13;
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i7);
        int size2 = View.MeasureSpec.getSize(i8);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i8, paddingBottom, -2);
        int i14 = size - paddingRight;
        int i15 = this.mMinCellSize;
        int i16 = i14 / i15;
        int i17 = i14 % i15;
        if (i16 == 0) {
            setMeasuredDimension(i14, 0);
            return;
        }
        int i18 = (i17 / i16) + i15;
        int childCount = getChildCount();
        int iMax = 0;
        int i19 = 0;
        boolean z9 = false;
        int i20 = 0;
        int iMax2 = 0;
        int i21 = 0;
        long j7 = 0;
        while (i19 < childCount) {
            View childAt = getChildAt(i19);
            int i22 = size2;
            if (childAt.getVisibility() == 8) {
                i13 = paddingBottom;
            } else {
                boolean z10 = childAt instanceof ActionMenuItemView;
                int i23 = i20 + 1;
                if (z10) {
                    int i24 = this.mGeneratedItemPadding;
                    i12 = i23;
                    r14 = 0;
                    childAt.setPadding(i24, 0, i24, 0);
                } else {
                    i12 = i23;
                    r14 = 0;
                }
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.expanded = r14;
                layoutParams.extraPixels = r14;
                layoutParams.cellsUsed = r14;
                layoutParams.expandable = r14;
                ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = r14;
                ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = r14;
                layoutParams.preventEdgeOffset = z10 && ((ActionMenuItemView) childAt).hasText();
                int iMeasureChildForCells = measureChildForCells(childAt, i18, layoutParams.isOverflowButton ? 1 : i16, childMeasureSpec, paddingBottom);
                iMax2 = Math.max(iMax2, iMeasureChildForCells);
                if (layoutParams.expandable) {
                    i21++;
                }
                if (layoutParams.isOverflowButton) {
                    z9 = true;
                }
                i16 -= iMeasureChildForCells;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (iMeasureChildForCells == 1) {
                    i13 = paddingBottom;
                    j7 |= 1 << i19;
                    i16 = i16;
                } else {
                    i13 = paddingBottom;
                }
                i20 = i12;
            }
            i19++;
            paddingBottom = i13;
            size2 = i22;
        }
        int i25 = size2;
        boolean z11 = z9 && i20 == 2;
        boolean z12 = false;
        while (i21 > 0 && i16 > 0) {
            int i26 = 0;
            int i27 = 0;
            int i28 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            long j8 = 0;
            while (i27 < childCount) {
                boolean z13 = z12;
                LayoutParams layoutParams2 = (LayoutParams) getChildAt(i27).getLayoutParams();
                int i29 = iMax;
                if (layoutParams2.expandable) {
                    int i30 = layoutParams2.cellsUsed;
                    if (i30 < i28) {
                        j8 = 1 << i27;
                        i28 = i30;
                        i26 = 1;
                    } else if (i30 == i28) {
                        i26++;
                        j8 |= 1 << i27;
                    }
                }
                i27++;
                iMax = i29;
                z12 = z13;
            }
            z6 = z12;
            i11 = iMax;
            j7 |= j8;
            if (i26 > i16) {
                i9 = mode;
                i10 = i14;
                break;
            }
            int i31 = i28 + 1;
            int i32 = 0;
            while (i32 < childCount) {
                View childAt2 = getChildAt(i32);
                LayoutParams layoutParams3 = (LayoutParams) childAt2.getLayoutParams();
                int i33 = i14;
                int i34 = mode;
                long j9 = 1 << i32;
                if ((j8 & j9) == 0) {
                    if (layoutParams3.cellsUsed == i31) {
                        j7 |= j9;
                    }
                    z8 = z11;
                } else {
                    if (z11 && layoutParams3.preventEdgeOffset && i16 == 1) {
                        int i35 = this.mGeneratedItemPadding;
                        z8 = z11;
                        childAt2.setPadding(i35 + i18, 0, i35, 0);
                    } else {
                        z8 = z11;
                    }
                    layoutParams3.cellsUsed++;
                    layoutParams3.expanded = true;
                    i16--;
                }
                i32++;
                mode = i34;
                i14 = i33;
                z11 = z8;
            }
            iMax = i11;
            z12 = true;
        }
        i9 = mode;
        i10 = i14;
        z6 = z12;
        i11 = iMax;
        boolean z14 = !z9 && i20 == 1;
        if (i16 <= 0 || j7 == 0 || (i16 >= i20 - 1 && !z14 && iMax2 <= 1)) {
            z7 = z6;
        } else {
            float fBitCount = Long.bitCount(j7);
            if (!z14) {
                if ((j7 & 1) != 0 && !((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                    fBitCount -= 0.5f;
                }
                int i36 = childCount - 1;
                if ((j7 & (1 << i36)) != 0 && !((LayoutParams) getChildAt(i36).getLayoutParams()).preventEdgeOffset) {
                    fBitCount -= 0.5f;
                }
            }
            int i37 = fBitCount > 0.0f ? (int) ((i16 * i18) / fBitCount) : 0;
            z7 = z6;
            for (int i38 = 0; i38 < childCount; i38++) {
                if ((j7 & (1 << i38)) != 0) {
                    View childAt3 = getChildAt(i38);
                    LayoutParams layoutParams4 = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        layoutParams4.extraPixels = i37;
                        layoutParams4.expanded = true;
                        if (i38 == 0 && !layoutParams4.preventEdgeOffset) {
                            ((ViewGroup.MarginLayoutParams) layoutParams4).leftMargin = (-i37) / 2;
                        }
                    } else if (layoutParams4.isOverflowButton) {
                        layoutParams4.extraPixels = i37;
                        layoutParams4.expanded = true;
                        ((ViewGroup.MarginLayoutParams) layoutParams4).rightMargin = (-i37) / 2;
                    } else {
                        if (i38 != 0) {
                            ((ViewGroup.MarginLayoutParams) layoutParams4).leftMargin = i37 / 2;
                        }
                        if (i38 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) layoutParams4).rightMargin = i37 / 2;
                        }
                    }
                    z7 = true;
                }
            }
        }
        if (z7) {
            for (int i39 = 0; i39 < childCount; i39++) {
                View childAt4 = getChildAt(i39);
                LayoutParams layoutParams5 = (LayoutParams) childAt4.getLayoutParams();
                if (layoutParams5.expanded) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((layoutParams5.cellsUsed * i18) + layoutParams5.extraPixels, 1073741824), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i10, i9 != 1073741824 ? i11 : i25);
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    public void dismissPopupMenus() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.dismissPopupMenus();
        }
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams.isOverflowButton = true;
        return layoutParamsGenerateDefaultLayoutParams;
    }

    public Menu getMenu() {
        if (this.mMenu == null) {
            Context context = getContext();
            MenuBuilder menuBuilder = new MenuBuilder(context);
            this.mMenu = menuBuilder;
            menuBuilder.setCallback(new MenuBuilderCallback());
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.mPresenter = actionMenuPresenter;
            actionMenuPresenter.setReserveOverflow(true);
            ActionMenuPresenter actionMenuPresenter2 = this.mPresenter;
            MenuPresenter.Callback actionMenuPresenterCallback = this.mActionMenuPresenterCallback;
            if (actionMenuPresenterCallback == null) {
                actionMenuPresenterCallback = new ActionMenuPresenterCallback();
            }
            actionMenuPresenter2.setCallback(actionMenuPresenterCallback);
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return this.mMenu;
    }

    @Nullable
    public Drawable getOverflowIcon() {
        getMenu();
        return this.mPresenter.getOverflowIcon();
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    @Override // android.support.v7.view.menu.MenuView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getWindowAnimations() {
        return 0;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean hasSupportDividerBeforeChildAt(int i7) {
        boolean zNeedsDividerAfter = false;
        if (i7 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i7 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i7);
        if (i7 < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            zNeedsDividerAfter = false | ((ActionMenuChildView) childAt).needsDividerAfter();
        }
        return (i7 <= 0 || !(childAt2 instanceof ActionMenuChildView)) ? zNeedsDividerAfter : zNeedsDividerAfter | ((ActionMenuChildView) childAt2).needsDividerBefore();
    }

    public boolean hideOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.hideOverflowMenu();
    }

    @Override // android.support.v7.view.menu.MenuView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override // android.support.v7.view.menu.MenuBuilder.ItemInvoker
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isOverflowMenuShowPending() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowing();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissPopupMenus();
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int width;
        int paddingLeft;
        if (!this.mFormatItems) {
            super.onLayout(z6, i7, i8, i9, i10);
            return;
        }
        int childCount = getChildCount();
        int i11 = (i10 - i8) / 2;
        int dividerWidth = getDividerWidth();
        int i12 = i9 - i7;
        int paddingRight = (i12 - getPaddingRight()) - getPaddingLeft();
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isOverflowButton) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (hasSupportDividerBeforeChildAt(i15)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zIsLayoutRtl) {
                        paddingLeft = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i16 = i11 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i16, width, measuredHeight + i16);
                    paddingRight -= measuredWidth;
                    i13 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                    hasSupportDividerBeforeChildAt(i15);
                    i14++;
                }
            }
        }
        if (childCount == 1 && i13 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i17 = (i12 / 2) - (measuredWidth2 / 2);
            int i18 = i11 - (measuredHeight2 / 2);
            childAt2.layout(i17, i18, measuredWidth2 + i17, measuredHeight2 + i18);
            return;
        }
        int i19 = i14 - (i13 ^ 1);
        int iMax = Math.max(0, i19 > 0 ? paddingRight / i19 : 0);
        if (zIsLayoutRtl) {
            int width2 = getWidth() - getPaddingRight();
            for (int i20 = 0; i20 < childCount; i20++) {
                View childAt3 = getChildAt(i20);
                LayoutParams layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !layoutParams2.isOverflowButton) {
                    int i21 = width2 - ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i22 = i11 - (measuredHeight3 / 2);
                    childAt3.layout(i21 - measuredWidth3, i22, i21, measuredHeight3 + i22);
                    width2 = i21 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i23 = 0; i23 < childCount; i23++) {
            View childAt4 = getChildAt(i23);
            LayoutParams layoutParams3 = (LayoutParams) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !layoutParams3.isOverflowButton) {
                int i24 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i25 = i11 - (measuredHeight4 / 2);
                childAt4.layout(i24, i25, i24 + measuredWidth4, measuredHeight4 + i25);
                paddingLeft2 = measuredWidth4 + ((ViewGroup.MarginLayoutParams) layoutParams3).rightMargin + iMax + i24;
            }
        }
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i7, int i8) {
        MenuBuilder menuBuilder;
        boolean z6 = this.mFormatItems;
        boolean z7 = View.MeasureSpec.getMode(i7) == 1073741824;
        this.mFormatItems = z7;
        if (z6 != z7) {
            this.mFormatItemsWidth = 0;
        }
        int size = View.MeasureSpec.getSize(i7);
        if (this.mFormatItems && (menuBuilder = this.mMenu) != null && size != this.mFormatItemsWidth) {
            this.mFormatItemsWidth = size;
            menuBuilder.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (this.mFormatItems && childCount > 0) {
            onMeasureExactFormat(i7, i8);
            return;
        }
        for (int i9 = 0; i9 < childCount; i9++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i9).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = 0;
        }
        super.onMeasure(i7, i8);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setExpandedActionViewsExclusive(boolean z6) {
        this.mPresenter.setExpandedActionViewsExclusive(z6);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setOverflowIcon(@Nullable Drawable drawable) {
        getMenu();
        this.mPresenter.setOverflowIcon(drawable);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setOverflowReserved(boolean z6) {
        this.mReserveOverflow = z6;
    }

    public void setPopupTheme(@StyleRes int i7) {
        if (this.mPopupTheme != i7) {
            this.mPopupTheme = i7;
            if (i7 == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i7);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
        actionMenuPresenter.setMenuView(this);
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.showOverflowMenu();
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f7 = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * f7);
        this.mGeneratedItemPadding = (int) (f7 * 4.0f);
        this.mPopupContext = context;
        this.mPopupTheme = 0;
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        return layoutParams;
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null) {
            LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : new LayoutParams(layoutParams);
            if (layoutParams2.gravity <= 0) {
                layoutParams2.gravity = 16;
            }
            return layoutParams2;
        }
        return generateDefaultLayoutParams();
    }
}
