package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0308R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {
    private static final int PEEK_BUTTON_DP = 16;
    private boolean mAllowStacking;
    private int mLastWidthSize;
    private int mMinimumHeight;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastWidthSize = -1;
        this.mMinimumHeight = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0308R.styleable.ButtonBarLayout);
        this.mAllowStacking = typedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.ButtonBarLayout_allowStacking, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    private int getNextVisibleChildIndex(int i7) {
        int childCount = getChildCount();
        while (i7 < childCount) {
            if (getChildAt(i7).getVisibility() == 0) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    private boolean isStacked() {
        return getOrientation() == 1;
    }

    private void setStacked(boolean z6) {
        setOrientation(z6 ? 1 : 0);
        setGravity(z6 ? 5 : 80);
        View viewFindViewById = findViewById(C0308R.id.spacer);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z6 ? 8 : 4);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }

    @Override // android.view.View
    public int getMinimumHeight() {
        return Math.max(this.mMinimumHeight, super.getMinimumHeight());
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i7, int i8) {
        int iMakeMeasureSpec;
        boolean z6;
        int size = View.MeasureSpec.getSize(i7);
        int paddingBottom = 0;
        if (this.mAllowStacking) {
            if (size > this.mLastWidthSize && isStacked()) {
                setStacked(false);
            }
            this.mLastWidthSize = size;
        }
        if (isStacked() || View.MeasureSpec.getMode(i7) != 1073741824) {
            iMakeMeasureSpec = i7;
            z6 = false;
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z6 = true;
        }
        super.onMeasure(iMakeMeasureSpec, i8);
        if (this.mAllowStacking && !isStacked()) {
            if ((getMeasuredWidthAndState() & ViewCompat.MEASURED_STATE_MASK) == 16777216) {
                setStacked(true);
                z6 = true;
            }
        }
        if (z6) {
            super.onMeasure(i7, i8);
        }
        int nextVisibleChildIndex = getNextVisibleChildIndex(0);
        if (nextVisibleChildIndex >= 0) {
            View childAt = getChildAt(nextVisibleChildIndex);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + getPaddingTop() + layoutParams.topMargin + layoutParams.bottomMargin + 0;
            if (isStacked()) {
                int nextVisibleChildIndex2 = getNextVisibleChildIndex(nextVisibleChildIndex + 1);
                paddingBottom = nextVisibleChildIndex2 >= 0 ? getChildAt(nextVisibleChildIndex2).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f)) + measuredHeight : measuredHeight;
            } else {
                paddingBottom = getPaddingBottom() + measuredHeight;
            }
        }
        if (ViewCompat.getMinimumHeight(this) != paddingBottom) {
            setMinimumHeight(paddingBottom);
        }
    }

    public void setAllowStacking(boolean z6) {
        if (this.mAllowStacking != z6) {
            this.mAllowStacking = z6;
            if (!z6 && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
