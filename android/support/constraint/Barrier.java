package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.HelperWidget;
import android.util.AttributeSet;
import android.util.SparseArray;

/* loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public static final int BOTTOM = 3;
    public static final int END = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int START = 5;
    public static final int TOP = 2;
    private android.support.constraint.solver.widgets.Barrier mBarrier;
    private int mIndicatedType;
    private int mResolvedType;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    private void updateType(ConstraintWidget constraintWidget, int i7, boolean z6) {
        this.mResolvedType = i7;
        if (z6) {
            int i8 = this.mIndicatedType;
            if (i8 == 5) {
                this.mResolvedType = 1;
            } else if (i8 == 6) {
                this.mResolvedType = 0;
            }
        } else {
            int i9 = this.mIndicatedType;
            if (i9 == 5) {
                this.mResolvedType = 0;
            } else if (i9 == 6) {
                this.mResolvedType = 1;
            }
        }
        if (constraintWidget instanceof android.support.constraint.solver.widgets.Barrier) {
            ((android.support.constraint.solver.widgets.Barrier) constraintWidget).setBarrierType(this.mResolvedType);
        }
    }

    public boolean allowsGoneWidget() {
        return this.mBarrier.allowsGoneWidget();
    }

    public int getMargin() {
        return this.mBarrier.getMargin();
    }

    public int getType() {
        return this.mIndicatedType;
    }

    @Override // android.support.constraint.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mBarrier = new android.support.constraint.solver.widgets.Barrier();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0071R.styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.mBarrier.setAllowsGoneWidget(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.mBarrier.setMargin(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.mHelperWidget = this.mBarrier;
        validateParams();
    }

    @Override // android.support.constraint.ConstraintHelper
    public void loadParameters(ConstraintSet.Constraint constraint, HelperWidget helperWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        super.loadParameters(constraint, helperWidget, layoutParams, sparseArray);
        if (helperWidget instanceof android.support.constraint.solver.widgets.Barrier) {
            android.support.constraint.solver.widgets.Barrier barrier = (android.support.constraint.solver.widgets.Barrier) helperWidget;
            updateType(barrier, constraint.layout.mBarrierDirection, ((ConstraintWidgetContainer) helperWidget.getParent()).isRtl());
            barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
            barrier.setMargin(constraint.layout.mBarrierMargin);
        }
    }

    @Override // android.support.constraint.ConstraintHelper
    public void resolveRtl(ConstraintWidget constraintWidget, boolean z6) {
        updateType(constraintWidget, this.mIndicatedType, z6);
    }

    public void setAllowsGoneWidget(boolean z6) {
        this.mBarrier.setAllowsGoneWidget(z6);
    }

    public void setDpMargin(int i7) {
        this.mBarrier.setMargin((int) ((i7 * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i7) {
        this.mBarrier.setMargin(i7);
    }

    public void setType(int i7) {
        this.mIndicatedType = i7;
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        super.setVisibility(8);
    }
}
