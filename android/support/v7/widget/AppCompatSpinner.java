package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0308R;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    private static final int[] ATTRS_ANDROID_SPINNERMODE = {R.attr.spinnerMode};
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "AppCompatSpinner";
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    public int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    public DropdownPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    public final Rect mTempRect;

    public static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(@Nullable SpinnerAdapter spinnerAdapter, @Nullable Resources.Theme theme) {
            this.mAdapter = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (Build.VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof android.widget.ThemedSpinnerAdapter)) {
                    android.widget.ThemedSpinnerAdapter themedSpinnerAdapter = (android.widget.ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                        return;
                    }
                    return;
                }
                if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                    ThemedSpinnerAdapter themedSpinnerAdapter2 = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter2.getDropDownViewTheme() == null) {
                        themedSpinnerAdapter2.setDropDownViewTheme(theme);
                    }
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i7, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i7, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i7) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i7);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i7) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i7);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i7) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i7, View view, ViewGroup viewGroup) {
            return getDropDownView(i7, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i7) {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i7);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    public class DropdownPopup extends ListPopupWindow {
        public ListAdapter mAdapter;
        private CharSequence mHintText;
        private final Rect mVisibleRect;

        public DropdownPopup(Context context, AttributeSet attributeSet, int i7) {
            super(context, attributeSet, i7);
            this.mVisibleRect = new Rect();
            setAnchorView(AppCompatSpinner.this);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: android.support.v7.widget.AppCompatSpinner.DropdownPopup.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i8, long j7) {
                    AppCompatSpinner.this.setSelection(i8);
                    if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                        DropdownPopup dropdownPopup = DropdownPopup.this;
                        AppCompatSpinner.this.performItemClick(view, i8, dropdownPopup.mAdapter.getItemId(i8));
                    }
                    DropdownPopup.this.dismiss();
                }
            });
        }

        public void computeContentWidth() {
            Drawable background = getBackground();
            int i7 = 0;
            if (background != null) {
                background.getPadding(AppCompatSpinner.this.mTempRect);
                i7 = ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? AppCompatSpinner.this.mTempRect.right : -AppCompatSpinner.this.mTempRect.left;
            } else {
                Rect rect = AppCompatSpinner.this.mTempRect;
                rect.right = 0;
                rect.left = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            AppCompatSpinner appCompatSpinner = AppCompatSpinner.this;
            int i8 = appCompatSpinner.mDropDownWidth;
            if (i8 == -2) {
                int iCompatMeasureContentWidth = appCompatSpinner.compatMeasureContentWidth((SpinnerAdapter) this.mAdapter, getBackground());
                int i9 = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = AppCompatSpinner.this.mTempRect;
                int i10 = (i9 - rect2.left) - rect2.right;
                if (iCompatMeasureContentWidth > i10) {
                    iCompatMeasureContentWidth = i10;
                }
                setContentWidth(Math.max(iCompatMeasureContentWidth, (width - paddingLeft) - paddingRight));
            } else if (i8 == -1) {
                setContentWidth((width - paddingLeft) - paddingRight);
            } else {
                setContentWidth(i8);
            }
            setHorizontalOffset(ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? ((width - paddingRight) - getWidth()) + i7 : i7 + paddingLeft);
        }

        public CharSequence getHintText() {
            return this.mHintText;
        }

        public boolean isVisibleToUser(View view) {
            return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(this.mVisibleRect);
        }

        @Override // android.support.v7.widget.ListPopupWindow
        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }

        public void setPromptText(CharSequence charSequence) {
            this.mHintText = charSequence;
        }

        @Override // android.support.v7.widget.ListPopupWindow, android.support.v7.view.menu.ShowableListMenu
        public void show() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
            ViewTreeObserver viewTreeObserver;
            boolean zIsShowing = isShowing();
            computeContentWidth();
            setInputMethodMode(2);
            super.show();
            getListView().setChoiceMode(1);
            setSelection(AppCompatSpinner.this.getSelectedItemPosition());
            if (zIsShowing || (viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver()) == null) {
                return;
            }
            final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: android.support.v7.widget.AppCompatSpinner.DropdownPopup.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                    DropdownPopup dropdownPopup = DropdownPopup.this;
                    if (!dropdownPopup.isVisibleToUser(AppCompatSpinner.this)) {
                        DropdownPopup.this.dismiss();
                    } else {
                        DropdownPopup.this.computeContentWidth();
                        DropdownPopup.super.show();
                    }
                }
            };
            viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
            setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: android.support.v7.widget.AppCompatSpinner.DropdownPopup.3
                @Override // android.widget.PopupWindow.OnDismissListener
                public void onDismiss() {
                    ViewTreeObserver viewTreeObserver2 = AppCompatSpinner.this.getViewTreeObserver();
                    if (viewTreeObserver2 != null) {
                        viewTreeObserver2.removeGlobalOnLayoutListener(onGlobalLayoutListener);
                    }
                }
            });
        }
    }

    public AppCompatSpinner(Context context) {
        this(context, (AttributeSet) null);
    }

    public int compatMeasureContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i7 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i7) {
                view = null;
                i7 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        drawable.getPadding(this.mTempRect);
        Rect rect = this.mTempRect;
        return iMax2 + rect.left + rect.right;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        DropdownPopup dropdownPopup = this.mPopup;
        return dropdownPopup != null ? dropdownPopup.getHorizontalOffset() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        DropdownPopup dropdownPopup = this.mPopup;
        return dropdownPopup != null ? dropdownPopup.getVerticalOffset() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.mPopup != null ? this.mDropDownWidth : super.getDropDownWidth();
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        DropdownPopup dropdownPopup = this.mPopup;
        return dropdownPopup != null ? dropdownPopup.getBackground() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        if (this.mPopup != null) {
            return this.mPopupContext;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        DropdownPopup dropdownPopup = this.mPopup;
        return dropdownPopup != null ? dropdownPopup.getHintText() : super.getPrompt();
    }

    @Override // android.support.v4.view.TintableBackgroundView
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintList();
        }
        return null;
    }

    @Override // android.support.v4.view.TintableBackgroundView
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintMode();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup == null || !dropdownPopup.isShowing()) {
            return;
        }
        this.mPopup.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (this.mPopup == null || View.MeasureSpec.getMode(i7) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(i7)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ForwardingListener forwardingListener = this.mForwardingListener;
        if (forwardingListener == null || !forwardingListener.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup == null) {
            return super.performClick();
        }
        if (dropdownPopup.isShowing()) {
            return true;
        }
        this.mPopup.show();
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundDrawable(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@DrawableRes int i7) {
        super.setBackgroundResource(i7);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundResource(i7);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i7) {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.setHorizontalOffset(i7);
        } else {
            super.setDropDownHorizontalOffset(i7);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i7) {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.setVerticalOffset(i7);
        } else {
            super.setDropDownVerticalOffset(i7);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i7) {
        if (this.mPopup != null) {
            this.mDropDownWidth = i7;
        } else {
            super.setDropDownWidth(i7);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.setBackgroundDrawable(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(@DrawableRes int i7) {
        setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), i7));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.setPromptText(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @Override // android.support.v4.view.TintableBackgroundView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // android.support.v4.view.TintableBackgroundView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintMode(mode);
        }
    }

    public AppCompatSpinner(Context context, int i7) {
        this(context, null, C0308R.attr.spinnerStyle, i7);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.mPopupSet) {
            this.mTempAdapter = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.mPopup != null) {
            Context context = this.mPopupContext;
            if (context == null) {
                context = getContext();
            }
            this.mPopup.setAdapter(new DropDownAdapter(spinnerAdapter, context.getTheme()));
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0308R.attr.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i7) {
        this(context, attributeSet, i7, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i7, int i8) {
        this(context, attributeSet, i7, i8, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AppCompatSpinner(android.content.Context r6, android.util.AttributeSet r7, int r8, int r9, android.content.res.Resources.Theme r10) throws java.lang.Throwable {
        /*
            r5 = this;
            r5.<init>(r6, r7, r8)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r5.mTempRect = r0
            int[] r0 = android.support.v7.appcompat.C0308R.styleable.Spinner
            r1 = 0
            android.support.v7.widget.TintTypedArray r0 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes(r6, r7, r0, r8, r1)
            android.support.v7.widget.AppCompatBackgroundHelper r2 = new android.support.v7.widget.AppCompatBackgroundHelper
            r2.<init>(r5)
            r5.mBackgroundTintHelper = r2
            r2 = 0
            if (r10 == 0) goto L23
            android.support.v7.view.ContextThemeWrapper r3 = new android.support.v7.view.ContextThemeWrapper
            r3.<init>(r6, r10)
            r5.mPopupContext = r3
            goto L3e
        L23:
            int r10 = android.support.v7.appcompat.C0308R.styleable.Spinner_popupTheme
            int r10 = r0.getResourceId(r10, r1)
            if (r10 == 0) goto L33
            android.support.v7.view.ContextThemeWrapper r3 = new android.support.v7.view.ContextThemeWrapper
            r3.<init>(r6, r10)
            r5.mPopupContext = r3
            goto L3e
        L33:
            int r10 = android.os.Build.VERSION.SDK_INT
            r3 = 23
            if (r10 >= r3) goto L3b
            r10 = r6
            goto L3c
        L3b:
            r10 = r2
        L3c:
            r5.mPopupContext = r10
        L3e:
            android.content.Context r10 = r5.mPopupContext
            r3 = 1
            if (r10 == 0) goto La0
            r10 = -1
            if (r9 != r10) goto L68
            int[] r10 = android.support.v7.widget.AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L64
            android.content.res.TypedArray r10 = r6.obtainStyledAttributes(r7, r10, r8, r1)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L64
            boolean r4 = r10.hasValue(r1)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L65
            if (r4 == 0) goto L56
            int r9 = r10.getInt(r1, r1)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L65
        L56:
            r10.recycle()
            goto L68
        L5a:
            r6 = move-exception
            r2 = r10
            goto L5e
        L5d:
            r6 = move-exception
        L5e:
            if (r2 == 0) goto L63
            r2.recycle()
        L63:
            throw r6
        L64:
            r10 = r2
        L65:
            if (r10 == 0) goto L68
            goto L56
        L68:
            if (r9 != r3) goto La0
            android.support.v7.widget.AppCompatSpinner$DropdownPopup r9 = new android.support.v7.widget.AppCompatSpinner$DropdownPopup
            android.content.Context r10 = r5.mPopupContext
            r9.<init>(r10, r7, r8)
            android.content.Context r10 = r5.mPopupContext
            int[] r4 = android.support.v7.appcompat.C0308R.styleable.Spinner
            android.support.v7.widget.TintTypedArray r10 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes(r10, r7, r4, r8, r1)
            int r1 = android.support.v7.appcompat.C0308R.styleable.Spinner_android_dropDownWidth
            r4 = -2
            int r1 = r10.getLayoutDimension(r1, r4)
            r5.mDropDownWidth = r1
            int r1 = android.support.v7.appcompat.C0308R.styleable.Spinner_android_popupBackground
            android.graphics.drawable.Drawable r1 = r10.getDrawable(r1)
            r9.setBackgroundDrawable(r1)
            int r1 = android.support.v7.appcompat.C0308R.styleable.Spinner_android_prompt
            java.lang.String r1 = r0.getString(r1)
            r9.setPromptText(r1)
            r10.recycle()
            r5.mPopup = r9
            android.support.v7.widget.AppCompatSpinner$1 r10 = new android.support.v7.widget.AppCompatSpinner$1
            r10.<init>(r5)
            r5.mForwardingListener = r10
        La0:
            int r9 = android.support.v7.appcompat.C0308R.styleable.Spinner_android_entries
            java.lang.CharSequence[] r9 = r0.getTextArray(r9)
            if (r9 == 0) goto Lb8
            android.widget.ArrayAdapter r10 = new android.widget.ArrayAdapter
            r1 = 17367048(0x1090008, float:2.5162948E-38)
            r10.<init>(r6, r1, r9)
            int r6 = android.support.v7.appcompat.C0308R.layout.support_simple_spinner_dropdown_item
            r10.setDropDownViewResource(r6)
            r5.setAdapter(r10)
        Lb8:
            r0.recycle()
            r5.mPopupSet = r3
            android.widget.SpinnerAdapter r6 = r5.mTempAdapter
            if (r6 == 0) goto Lc6
            r5.setAdapter(r6)
            r5.mTempAdapter = r2
        Lc6:
            android.support.v7.widget.AppCompatBackgroundHelper r6 = r5.mBackgroundTintHelper
            r6.loadFromAttributes(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }
}
