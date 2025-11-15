package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v7.appcompat.C0308R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import p009b.C0413b;

/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    private void forceUniformHeight(int i7, int i8) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i9 = 0; i9 < i7; i9++) {
            View virtualChildAt = getVirtualChildAt(i9);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) layoutParams).height == -1) {
                    int i10 = ((ViewGroup.MarginLayoutParams) layoutParams).width;
                    ((ViewGroup.MarginLayoutParams) layoutParams).width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i8, 0, iMakeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) layoutParams).width = i10;
                }
            }
        }
    }

    private void forceUniformWidth(int i7, int i8) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i9 = 0; i9 < i7; i9++) {
            View virtualChildAt = getVirtualChildAt(i9);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) layoutParams).width == -1) {
                    int i10 = ((ViewGroup.MarginLayoutParams) layoutParams).height;
                    ((ViewGroup.MarginLayoutParams) layoutParams).height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, iMakeMeasureSpec, 0, i8, 0);
                    ((ViewGroup.MarginLayoutParams) layoutParams).height = i10;
                }
            }
        }
    }

    private void setChildFrame(View view, int i7, int i8, int i9, int i10) {
        view.layout(i7, i8, i9 + i7, i10 + i8);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void drawDividersHorizontal(Canvas canvas) {
        int right;
        int left;
        int i7;
        int virtualChildCount = getVirtualChildCount();
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i8 = 0; i8 < virtualChildCount; i8++) {
            View virtualChildAt = getVirtualChildAt(i8);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i8)) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                drawVerticalDivider(canvas, zIsLayoutRtl ? virtualChildAt.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin : (virtualChildAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.mDividerWidth);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (zIsLayoutRtl) {
                    left = virtualChildAt2.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin;
                    i7 = this.mDividerWidth;
                    right = left - i7;
                } else {
                    right = virtualChildAt2.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin;
                }
            } else if (zIsLayoutRtl) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i7 = this.mDividerWidth;
                right = left - i7;
            }
            drawVerticalDivider(canvas, right);
        }
    }

    public void drawDividersVertical(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i7 = 0; i7 < virtualChildCount; i7++) {
            View virtualChildAt = getVirtualChildAt(i7);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i7)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((ViewGroup.MarginLayoutParams) ((LayoutParams) virtualChildAt.getLayoutParams())).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            drawHorizontalDivider(canvas, virtualChildAt2 == null ? (getHeight() - getPaddingBottom()) - this.mDividerHeight : virtualChildAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((LayoutParams) virtualChildAt2.getLayoutParams())).bottomMargin);
        }
    }

    public void drawHorizontalDivider(Canvas canvas, int i7) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i7, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i7);
        this.mDivider.draw(canvas);
    }

    public void drawVerticalDivider(Canvas canvas, int i7) {
        this.mDivider.setBounds(i7, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i7, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i7;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i8 = this.mBaselineAlignedChildIndex;
        if (childCount <= i8) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i8);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i7 = this.mGravity & 112) != 48) {
            if (i7 == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
            } else if (i7 == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((LayoutParams) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public int getChildrenSkipCount(View view, int i7) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    public int getLocationOffset(View view) {
        return 0;
    }

    public int getNextLocationOffset(View view) {
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public View getVirtualChildAt(int i7) {
        return getChildAt(i7);
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean hasDividerBeforeChildAt(int i7) {
        if (i7 == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i7 == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        for (int i8 = i7 - 1; i8 >= 0; i8--) {
            if (getChildAt(i8).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void layoutHorizontal(int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.layoutHorizontal(int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void layoutVertical(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            r6 = r17
            int r7 = r17.getPaddingLeft()
            int r0 = r20 - r18
            int r1 = r17.getPaddingRight()
            int r8 = r0 - r1
            int r0 = r0 - r7
            int r1 = r17.getPaddingRight()
            int r9 = r0 - r1
            int r10 = r17.getVirtualChildCount()
            int r0 = r6.mGravity
            r1 = r0 & 112(0x70, float:1.57E-43)
            r2 = 8388615(0x800007, float:1.1754953E-38)
            r11 = r0 & r2
            r0 = 16
            if (r1 == r0) goto L3b
            r0 = 80
            if (r1 == r0) goto L2f
            int r0 = r17.getPaddingTop()
            goto L47
        L2f:
            int r0 = r17.getPaddingTop()
            int r0 = r0 + r21
            int r0 = r0 - r19
            int r1 = r6.mTotalLength
            int r0 = r0 - r1
            goto L47
        L3b:
            int r0 = r17.getPaddingTop()
            int r1 = r21 - r19
            int r2 = r6.mTotalLength
            int r1 = r1 - r2
            int r1 = r1 / 2
            int r0 = r0 + r1
        L47:
            r1 = 0
            r12 = 0
        L49:
            if (r12 >= r10) goto Lc7
            android.view.View r13 = r6.getVirtualChildAt(r12)
            r14 = 1
            if (r13 != 0) goto L5a
            int r1 = r6.measureNullChild(r12)
            int r1 = r1 + r0
            r0 = r1
            goto Lc4
        L5a:
            int r1 = r13.getVisibility()
            r2 = 8
            if (r1 == r2) goto Lc4
            int r4 = r13.getMeasuredWidth()
            int r15 = r13.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r1 = r13.getLayoutParams()
            r5 = r1
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5
            int r1 = r5.gravity
            if (r1 >= 0) goto L76
            r1 = r11
        L76:
            int r2 = android.support.v4.view.ViewCompat.getLayoutDirection(r17)
            int r1 = android.support.v4.view.GravityCompat.getAbsoluteGravity(r1, r2)
            r1 = r1 & 7
            if (r1 == r14) goto L8e
            r2 = 5
            if (r1 == r2) goto L89
            int r1 = r5.leftMargin
            int r1 = r1 + r7
            goto L99
        L89:
            int r1 = r8 - r4
            int r2 = r5.rightMargin
            goto L98
        L8e:
            int r1 = r9 - r4
            int r1 = r1 / 2
            int r1 = r1 + r7
            int r2 = r5.leftMargin
            int r1 = r1 + r2
            int r2 = r5.rightMargin
        L98:
            int r1 = r1 - r2
        L99:
            r2 = r1
            boolean r1 = r6.hasDividerBeforeChildAt(r12)
            if (r1 == 0) goto La3
            int r1 = r6.mDividerHeight
            int r0 = r0 + r1
        La3:
            int r1 = r5.topMargin
            int r16 = r0 + r1
            int r0 = r6.getLocationOffset(r13)
            int r3 = r0 + r16
            r0 = r17
            r1 = r13
            r14 = r5
            r5 = r15
            r0.setChildFrame(r1, r2, r3, r4, r5)
            int r0 = r14.bottomMargin
            int r15 = r15 + r0
            int r0 = r6.getNextLocationOffset(r13)
            int r0 = r0 + r15
            int r0 = r0 + r16
            int r1 = r6.getChildrenSkipCount(r13, r12)
            int r12 = r12 + r1
        Lc4:
            r1 = 1
            int r12 = r12 + r1
            goto L49
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.layoutVertical(int, int, int, int):void");
    }

    public void measureChildBeforeLayout(View view, int i7, int i8, int i9, int i10, int i11) {
        measureChildWithMargins(view, i8, i9, i10, i11);
    }

    /* JADX WARN: Removed duplicated region for block: B:197:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void measureHorizontal(int r38, int r39) {
        /*
            Method dump skipped, instructions count: 1289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureHorizontal(int, int):void");
    }

    public int measureNullChild(int i7) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0332  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void measureVertical(int r34, int r35) {
        /*
            Method dump skipped, instructions count: 913
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureVertical(int, int):void");
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        if (this.mOrientation == 1) {
            layoutVertical(i7, i8, i9, i10);
        } else {
            layoutHorizontal(i7, i8, i9, i10);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        if (this.mOrientation == 1) {
            measureVertical(i7, i8);
        } else {
            measureHorizontal(i7, i8);
        }
    }

    public void setBaselineAligned(boolean z6) {
        this.mBaselineAligned = z6;
    }

    public void setBaselineAlignedChildIndex(int i7) {
        if (i7 >= 0 && i7 < getChildCount()) {
            this.mBaselineAlignedChildIndex = i7;
            return;
        }
        StringBuilder sbM112a = C0413b.m112a("base aligned child index out of range (0, ");
        sbM112a.append(getChildCount());
        sbM112a.append(")");
        throw new IllegalArgumentException(sbM112a.toString());
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i7) {
        this.mDividerPadding = i7;
    }

    public void setGravity(int i7) {
        if (this.mGravity != i7) {
            if ((8388615 & i7) == 0) {
                i7 |= GravityCompat.START;
            }
            if ((i7 & 112) == 0) {
                i7 |= 48;
            }
            this.mGravity = i7;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i7) {
        int i8 = i7 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i9 = this.mGravity;
        if ((8388615 & i9) != i8) {
            this.mGravity = i8 | ((-8388616) & i9);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z6) {
        this.mUseLargestChild = z6;
    }

    public void setOrientation(int i7) {
        if (this.mOrientation != i7) {
            this.mOrientation = i7;
            requestLayout();
        }
    }

    public void setShowDividers(int i7) {
        if (i7 != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i7;
    }

    public void setVerticalGravity(int i7) {
        int i8 = i7 & 112;
        int i9 = this.mGravity;
        if ((i9 & 112) != i8) {
            this.mGravity = i8 | (i9 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f7) {
        this.mWeightSum = Math.max(0.0f, f7);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        int i7 = this.mOrientation;
        if (i7 == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i7 == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0308R.styleable.LinearLayoutCompat, i7, 0);
        int i8 = tintTypedArrayObtainStyledAttributes.getInt(C0308R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i8 >= 0) {
            setOrientation(i8);
        }
        int i9 = tintTypedArrayObtainStyledAttributes.getInt(C0308R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i9 >= 0) {
            setGravity(i9);
        }
        boolean z6 = tintTypedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z6) {
            setBaselineAligned(z6);
        }
        this.mWeightSum = tintTypedArrayObtainStyledAttributes.getFloat(C0308R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = tintTypedArrayObtainStyledAttributes.getInt(C0308R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = tintTypedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(C0308R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = tintTypedArrayObtainStyledAttributes.getInt(C0308R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = tintTypedArrayObtainStyledAttributes.getDimensionPixelSize(C0308R.styleable.LinearLayoutCompat_dividerPadding, 0);
        tintTypedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0308R.styleable.LinearLayoutCompat_Layout);
            this.weight = typedArrayObtainStyledAttributes.getFloat(C0308R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = typedArrayObtainStyledAttributes.getInt(C0308R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int i7, int i8) {
            super(i7, i8);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int i7, int i8, float f7) {
            super(i7, i8);
            this.gravity = -1;
            this.weight = f7;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }
}
