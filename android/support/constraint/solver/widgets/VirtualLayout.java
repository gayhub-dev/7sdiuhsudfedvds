package android.support.constraint.solver.widgets;

import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;

/* loaded from: classes.dex */
public class VirtualLayout extends HelperWidget {
    private int mPaddingTop = 0;
    private int mPaddingBottom = 0;
    private int mPaddingLeft = 0;
    private int mPaddingRight = 0;
    private int mPaddingStart = 0;
    private int mPaddingEnd = 0;
    private int mResolvedPaddingLeft = 0;
    private int mResolvedPaddingRight = 0;
    private boolean mNeedsCallFromSolver = false;
    private int mMeasuredWidth = 0;
    private int mMeasuredHeight = 0;
    public BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    public BasicMeasure.Measurer mMeasurer = null;

    public void applyRtl(boolean z6) {
        int i7 = this.mPaddingStart;
        if (i7 > 0 || this.mPaddingEnd > 0) {
            if (z6) {
                this.mResolvedPaddingLeft = this.mPaddingEnd;
                this.mResolvedPaddingRight = i7;
            } else {
                this.mResolvedPaddingLeft = i7;
                this.mResolvedPaddingRight = this.mPaddingEnd;
            }
        }
    }

    public void captureWidgets() {
        for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
            ConstraintWidget constraintWidget = this.mWidgets[i7];
            if (constraintWidget != null) {
                constraintWidget.setInVirtualLayout(true);
            }
        }
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mResolvedPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mResolvedPaddingRight;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public void measure(int i7, int i8, int i9, int i10) {
    }

    public void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i7, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i8) {
        while (this.mMeasurer == null && getParent() != null) {
            this.mMeasurer = ((ConstraintWidgetContainer) getParent()).getMeasurer();
        }
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i7;
        measure.verticalDimension = i8;
        this.mMeasurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    public boolean measureChildren() {
        ConstraintWidget constraintWidget = this.mParent;
        BasicMeasure.Measurer measurer = constraintWidget != null ? ((ConstraintWidgetContainer) constraintWidget).getMeasurer() : null;
        if (measurer == null) {
            return false;
        }
        int i7 = 0;
        while (true) {
            if (i7 >= this.mWidgetsCount) {
                return true;
            }
            ConstraintWidget constraintWidget2 = this.mWidgets[i7];
            if (constraintWidget2 != null && !(constraintWidget2 instanceof Guideline)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget2.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget2.getDimensionBehaviour(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (!(dimensionBehaviour == dimensionBehaviour3 && constraintWidget2.mMatchConstraintDefaultWidth != 1 && dimensionBehaviour2 == dimensionBehaviour3 && constraintWidget2.mMatchConstraintDefaultHeight != 1)) {
                    if (dimensionBehaviour == dimensionBehaviour3) {
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    if (dimensionBehaviour2 == dimensionBehaviour3) {
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    }
                    BasicMeasure.Measure measure = this.mMeasure;
                    measure.horizontalBehavior = dimensionBehaviour;
                    measure.verticalBehavior = dimensionBehaviour2;
                    measure.horizontalDimension = constraintWidget2.getWidth();
                    this.mMeasure.verticalDimension = constraintWidget2.getHeight();
                    measurer.measure(constraintWidget2, this.mMeasure);
                    constraintWidget2.setWidth(this.mMeasure.measuredWidth);
                    constraintWidget2.setHeight(this.mMeasure.measuredHeight);
                    constraintWidget2.setBaselineDistance(this.mMeasure.measuredBaseline);
                }
            }
            i7++;
        }
    }

    public boolean needSolverPass() {
        return this.mNeedsCallFromSolver;
    }

    public void needsCallbackFromSolver(boolean z6) {
        this.mNeedsCallFromSolver = z6;
    }

    public void setMeasure(int i7, int i8) {
        this.mMeasuredWidth = i7;
        this.mMeasuredHeight = i8;
    }

    public void setPadding(int i7) {
        this.mPaddingLeft = i7;
        this.mPaddingTop = i7;
        this.mPaddingRight = i7;
        this.mPaddingBottom = i7;
        this.mPaddingStart = i7;
        this.mPaddingEnd = i7;
    }

    public void setPaddingBottom(int i7) {
        this.mPaddingBottom = i7;
    }

    public void setPaddingEnd(int i7) {
        this.mPaddingEnd = i7;
    }

    public void setPaddingLeft(int i7) {
        this.mPaddingLeft = i7;
        this.mResolvedPaddingLeft = i7;
    }

    public void setPaddingRight(int i7) {
        this.mPaddingRight = i7;
        this.mResolvedPaddingRight = i7;
    }

    public void setPaddingStart(int i7) {
        this.mPaddingStart = i7;
        this.mResolvedPaddingLeft = i7;
        this.mResolvedPaddingRight = i7;
    }

    public void setPaddingTop(int i7) {
        this.mPaddingTop = i7;
    }

    @Override // android.support.constraint.solver.widgets.HelperWidget, android.support.constraint.solver.widgets.Helper
    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer) {
        captureWidgets();
    }
}
