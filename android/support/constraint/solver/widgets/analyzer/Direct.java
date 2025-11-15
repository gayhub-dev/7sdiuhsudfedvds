package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.widgets.Barrier;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class Direct {
    private static final boolean APPLY_MATCH_PARENT = false;
    private static final boolean DEBUG = false;
    private static BasicMeasure.Measure measure = new BasicMeasure.Measure();

    private static boolean canMeasure(ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidget.getVerticalDimensionBehaviour();
        ConstraintWidgetContainer constraintWidgetContainer = constraintWidget.getParent() != null ? (ConstraintWidgetContainer) constraintWidget.getParent() : null;
        if (constraintWidgetContainer != null) {
            constraintWidgetContainer.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (constraintWidgetContainer != null) {
            constraintWidgetContainer.getVerticalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.FIXED;
        boolean z6 = horizontalDimensionBehaviour == dimensionBehaviour3 || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(0)) || constraintWidget.isResolvedHorizontally();
        boolean z7 = verticalDimensionBehaviour == dimensionBehaviour3 || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(1)) || constraintWidget.isResolvedVertically();
        if (constraintWidget.mDimensionRatio <= 0.0f || !(z6 || z7)) {
            return z6 && z7;
        }
        return true;
    }

    private static void horizontalSolvingPass(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, boolean z6) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested() && canMeasure(constraintWidget)) {
            ConstraintWidgetContainer.measure(constraintWidget, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
        }
        ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
        int finalValue = anchor.getFinalValue();
        int finalValue2 = anchor2.getFinalValue();
        if (anchor.getDependents() != null && anchor.hasFinalValue()) {
            Iterator<ConstraintAnchor> it = anchor.getDependents().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.mOwner;
                boolean zCanMeasure = canMeasure(constraintWidget2);
                if (constraintWidget2.isMeasureRequested() && zCanMeasure) {
                    ConstraintWidgetContainer.measure(constraintWidget2, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget2.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (horizontalDimensionBehaviour != dimensionBehaviour || zCanMeasure) {
                    if (!constraintWidget2.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor6 = constraintWidget2.mLeft;
                        if (next == constraintAnchor6 && constraintWidget2.mRight.mTarget == null) {
                            int margin = constraintAnchor6.getMargin() + finalValue;
                            constraintWidget2.setFinalHorizontal(margin, constraintWidget2.getWidth() + margin);
                            horizontalSolvingPass(constraintWidget2, measurer, z6);
                        } else {
                            ConstraintAnchor constraintAnchor7 = constraintWidget2.mRight;
                            if (next == constraintAnchor7 && constraintAnchor6.mTarget == null) {
                                int margin2 = finalValue - constraintAnchor7.getMargin();
                                constraintWidget2.setFinalHorizontal(margin2 - constraintWidget2.getWidth(), margin2);
                                horizontalSolvingPass(constraintWidget2, measurer, z6);
                            } else if (next == constraintAnchor6 && (constraintAnchor3 = constraintAnchor7.mTarget) != null && constraintAnchor3.hasFinalValue() && !constraintWidget2.isInHorizontalChain()) {
                                solveHorizontalCenterConstraints(measurer, constraintWidget2, z6);
                            }
                        }
                    }
                } else if (constraintWidget2.getHorizontalDimensionBehaviour() == dimensionBehaviour && constraintWidget2.mMatchConstraintMaxWidth >= 0 && constraintWidget2.mMatchConstraintMinWidth >= 0 && (constraintWidget2.getVisibility() == 8 || (constraintWidget2.mMatchConstraintDefaultWidth == 0 && constraintWidget2.getDimensionRatio() == 0.0f))) {
                    if (!constraintWidget2.isInHorizontalChain() && !constraintWidget2.isInVirtualLayout()) {
                        if (((next == constraintWidget2.mLeft && (constraintAnchor5 = constraintWidget2.mRight.mTarget) != null && constraintAnchor5.hasFinalValue()) || (next == constraintWidget2.mRight && (constraintAnchor4 = constraintWidget2.mLeft.mTarget) != null && constraintAnchor4.hasFinalValue())) && !constraintWidget2.isInHorizontalChain()) {
                            solveHorizontalMatchConstraint(constraintWidget, measurer, constraintWidget2, z6);
                        }
                    }
                }
            }
        }
        if ((constraintWidget instanceof Guideline) || anchor2.getDependents() == null || !anchor2.hasFinalValue()) {
            return;
        }
        Iterator<ConstraintAnchor> it2 = anchor2.getDependents().iterator();
        while (it2.hasNext()) {
            ConstraintAnchor next2 = it2.next();
            ConstraintWidget constraintWidget3 = next2.mOwner;
            boolean zCanMeasure2 = canMeasure(constraintWidget3);
            if (constraintWidget3.isMeasureRequested() && zCanMeasure2) {
                ConstraintWidgetContainer.measure(constraintWidget3, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
            }
            boolean z7 = (next2 == constraintWidget3.mLeft && (constraintAnchor2 = constraintWidget3.mRight.mTarget) != null && constraintAnchor2.hasFinalValue()) || (next2 == constraintWidget3.mRight && (constraintAnchor = constraintWidget3.mLeft.mTarget) != null && constraintAnchor.hasFinalValue());
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidget3.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (horizontalDimensionBehaviour2 != dimensionBehaviour2 || zCanMeasure2) {
                if (!constraintWidget3.isMeasureRequested()) {
                    ConstraintAnchor constraintAnchor8 = constraintWidget3.mLeft;
                    if (next2 == constraintAnchor8 && constraintWidget3.mRight.mTarget == null) {
                        int margin3 = constraintAnchor8.getMargin() + finalValue2;
                        constraintWidget3.setFinalHorizontal(margin3, constraintWidget3.getWidth() + margin3);
                        horizontalSolvingPass(constraintWidget3, measurer, z6);
                    } else {
                        ConstraintAnchor constraintAnchor9 = constraintWidget3.mRight;
                        if (next2 == constraintAnchor9 && constraintAnchor8.mTarget == null) {
                            int margin4 = finalValue2 - constraintAnchor9.getMargin();
                            constraintWidget3.setFinalHorizontal(margin4 - constraintWidget3.getWidth(), margin4);
                            horizontalSolvingPass(constraintWidget3, measurer, z6);
                        } else if (z7 && !constraintWidget3.isInHorizontalChain()) {
                            solveHorizontalCenterConstraints(measurer, constraintWidget3, z6);
                        }
                    }
                }
            } else if (constraintWidget3.getHorizontalDimensionBehaviour() == dimensionBehaviour2 && constraintWidget3.mMatchConstraintMaxWidth >= 0 && constraintWidget3.mMatchConstraintMinWidth >= 0 && (constraintWidget3.getVisibility() == 8 || (constraintWidget3.mMatchConstraintDefaultWidth == 0 && constraintWidget3.getDimensionRatio() == 0.0f))) {
                if (!constraintWidget3.isInHorizontalChain() && !constraintWidget3.isInVirtualLayout() && z7 && !constraintWidget3.isInHorizontalChain()) {
                    solveHorizontalMatchConstraint(constraintWidget, measurer, constraintWidget3, z6);
                }
            }
        }
    }

    private static void solveBarrier(Barrier barrier, BasicMeasure.Measurer measurer, int i7, boolean z6) {
        if (barrier.allSolved()) {
            if (i7 == 0) {
                horizontalSolvingPass(barrier, measurer, z6);
            } else {
                verticalSolvingPass(barrier, measurer);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0150  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean solveChain(android.support.constraint.solver.widgets.ConstraintWidgetContainer r18, android.support.constraint.solver.LinearSystem r19, int r20, int r21, android.support.constraint.solver.widgets.ChainHead r22, boolean r23, boolean r24, boolean r25) {
        /*
            Method dump skipped, instructions count: 562
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.analyzer.Direct.solveChain(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead, boolean, boolean, boolean):boolean");
    }

    private static void solveHorizontalCenterConstraints(BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget, boolean z6) {
        float horizontalBiasPercent = constraintWidget.getHorizontalBiasPercent();
        int finalValue = constraintWidget.mLeft.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mRight.mTarget.getFinalValue();
        int margin = constraintWidget.mLeft.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mRight.getMargin();
        if (finalValue == finalValue2) {
            horizontalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int width = constraintWidget.getWidth();
        int i7 = (finalValue2 - finalValue) - width;
        if (finalValue > finalValue2) {
            i7 = (finalValue - finalValue2) - width;
        }
        int i8 = ((int) ((horizontalBiasPercent * i7) + 0.5f)) + finalValue;
        int i9 = i8 + width;
        if (finalValue > finalValue2) {
            i9 = i8 - width;
        }
        constraintWidget.setFinalHorizontal(i8, i9);
        horizontalSolvingPass(constraintWidget, measurer, z6);
    }

    private static void solveHorizontalMatchConstraint(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2, boolean z6) {
        float horizontalBiasPercent = constraintWidget2.getHorizontalBiasPercent();
        int margin = constraintWidget2.mLeft.getMargin() + constraintWidget2.mLeft.mTarget.getFinalValue();
        int finalValue = constraintWidget2.mRight.mTarget.getFinalValue() - constraintWidget2.mRight.getMargin();
        if (finalValue >= margin) {
            int width = constraintWidget2.getWidth();
            if (constraintWidget2.getVisibility() != 8) {
                int i7 = constraintWidget2.mMatchConstraintDefaultWidth;
                if (i7 == 2) {
                    width = (int) (constraintWidget2.getHorizontalBiasPercent() * 0.5f * (constraintWidget instanceof ConstraintWidgetContainer ? constraintWidget.getWidth() : constraintWidget.getParent().getWidth()));
                } else if (i7 == 0) {
                    width = finalValue - margin;
                }
                width = Math.max(constraintWidget2.mMatchConstraintMinWidth, width);
                int i8 = constraintWidget2.mMatchConstraintMaxWidth;
                if (i8 > 0) {
                    width = Math.min(i8, width);
                }
            }
            int i9 = margin + ((int) ((horizontalBiasPercent * ((finalValue - margin) - width)) + 0.5f));
            constraintWidget2.setFinalHorizontal(i9, width + i9);
            horizontalSolvingPass(constraintWidget2, measurer, z6);
        }
    }

    private static void solveVerticalCenterConstraints(BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget) {
        float verticalBiasPercent = constraintWidget.getVerticalBiasPercent();
        int finalValue = constraintWidget.mTop.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mBottom.mTarget.getFinalValue();
        int margin = constraintWidget.mTop.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mBottom.getMargin();
        if (finalValue == finalValue2) {
            verticalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int height = constraintWidget.getHeight();
        int i7 = (finalValue2 - finalValue) - height;
        if (finalValue > finalValue2) {
            i7 = (finalValue - finalValue2) - height;
        }
        int i8 = (int) ((verticalBiasPercent * i7) + 0.5f);
        int i9 = finalValue + i8;
        int i10 = i9 + height;
        if (finalValue > finalValue2) {
            i9 = finalValue - i8;
            i10 = i9 - height;
        }
        constraintWidget.setFinalVertical(i9, i10);
        verticalSolvingPass(constraintWidget, measurer);
    }

    private static void solveVerticalMatchConstraint(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2) {
        float verticalBiasPercent = constraintWidget2.getVerticalBiasPercent();
        int margin = constraintWidget2.mTop.getMargin() + constraintWidget2.mTop.mTarget.getFinalValue();
        int finalValue = constraintWidget2.mBottom.mTarget.getFinalValue() - constraintWidget2.mBottom.getMargin();
        if (finalValue >= margin) {
            int height = constraintWidget2.getHeight();
            if (constraintWidget2.getVisibility() != 8) {
                int i7 = constraintWidget2.mMatchConstraintDefaultHeight;
                if (i7 == 2) {
                    height = (int) (verticalBiasPercent * 0.5f * (constraintWidget instanceof ConstraintWidgetContainer ? constraintWidget.getHeight() : constraintWidget.getParent().getHeight()));
                } else if (i7 == 0) {
                    height = finalValue - margin;
                }
                height = Math.max(constraintWidget2.mMatchConstraintMinHeight, height);
                int i8 = constraintWidget2.mMatchConstraintMaxHeight;
                if (i8 > 0) {
                    height = Math.min(i8, height);
                }
            }
            int i9 = margin + ((int) ((verticalBiasPercent * ((finalValue - margin) - height)) + 0.5f));
            constraintWidget2.setFinalVertical(i9, height + i9);
            verticalSolvingPass(constraintWidget2, measurer);
        }
    }

    public static void solvingPass(ConstraintWidgetContainer constraintWidgetContainer, BasicMeasure.Measurer measurer) {
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidgetContainer.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidgetContainer.getVerticalDimensionBehaviour();
        constraintWidgetContainer.resetFinalResolution();
        ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i7 = 0; i7 < size; i7++) {
            children.get(i7).resetFinalResolution();
        }
        boolean zIsRtl = constraintWidgetContainer.isRtl();
        if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalHorizontal(0, constraintWidgetContainer.getWidth());
        } else {
            constraintWidgetContainer.setFinalLeft(0);
        }
        boolean z6 = false;
        boolean z7 = false;
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget = children.get(i8);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    if (guideline.getRelativeBegin() != -1) {
                        guideline.setFinalValue(guideline.getRelativeBegin());
                    } else if (guideline.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue(constraintWidgetContainer.getWidth() - guideline.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue((int) ((guideline.getRelativePercent() * constraintWidgetContainer.getWidth()) + 0.5f));
                    }
                    z6 = true;
                }
            } else if ((constraintWidget instanceof Barrier) && ((Barrier) constraintWidget).getOrientation() == 0) {
                z7 = true;
            }
        }
        if (z6) {
            for (int i9 = 0; i9 < size; i9++) {
                ConstraintWidget constraintWidget2 = children.get(i9);
                if (constraintWidget2 instanceof Guideline) {
                    Guideline guideline2 = (Guideline) constraintWidget2;
                    if (guideline2.getOrientation() == 1) {
                        horizontalSolvingPass(guideline2, measurer, zIsRtl);
                    }
                }
            }
        }
        horizontalSolvingPass(constraintWidgetContainer, measurer, zIsRtl);
        if (z7) {
            for (int i10 = 0; i10 < size; i10++) {
                ConstraintWidget constraintWidget3 = children.get(i10);
                if (constraintWidget3 instanceof Barrier) {
                    Barrier barrier = (Barrier) constraintWidget3;
                    if (barrier.getOrientation() == 0) {
                        solveBarrier(barrier, measurer, 0, zIsRtl);
                    }
                }
            }
        }
        if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalVertical(0, constraintWidgetContainer.getHeight());
        } else {
            constraintWidgetContainer.setFinalTop(0);
        }
        boolean z8 = false;
        boolean z9 = false;
        for (int i11 = 0; i11 < size; i11++) {
            ConstraintWidget constraintWidget4 = children.get(i11);
            if (constraintWidget4 instanceof Guideline) {
                Guideline guideline3 = (Guideline) constraintWidget4;
                if (guideline3.getOrientation() == 0) {
                    if (guideline3.getRelativeBegin() != -1) {
                        guideline3.setFinalValue(guideline3.getRelativeBegin());
                    } else if (guideline3.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue(constraintWidgetContainer.getHeight() - guideline3.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue((int) ((guideline3.getRelativePercent() * constraintWidgetContainer.getHeight()) + 0.5f));
                    }
                    z8 = true;
                }
            } else if ((constraintWidget4 instanceof Barrier) && ((Barrier) constraintWidget4).getOrientation() == 1) {
                z9 = true;
            }
        }
        if (z8) {
            for (int i12 = 0; i12 < size; i12++) {
                ConstraintWidget constraintWidget5 = children.get(i12);
                if (constraintWidget5 instanceof Guideline) {
                    Guideline guideline4 = (Guideline) constraintWidget5;
                    if (guideline4.getOrientation() == 0) {
                        verticalSolvingPass(guideline4, measurer);
                    }
                }
            }
        }
        verticalSolvingPass(constraintWidgetContainer, measurer);
        if (z9) {
            for (int i13 = 0; i13 < size; i13++) {
                ConstraintWidget constraintWidget6 = children.get(i13);
                if (constraintWidget6 instanceof Barrier) {
                    Barrier barrier2 = (Barrier) constraintWidget6;
                    if (barrier2.getOrientation() == 1) {
                        solveBarrier(barrier2, measurer, 1, zIsRtl);
                    }
                }
            }
        }
        for (int i14 = 0; i14 < size; i14++) {
            ConstraintWidget constraintWidget7 = children.get(i14);
            if (constraintWidget7.isMeasureRequested() && canMeasure(constraintWidget7)) {
                ConstraintWidgetContainer.measure(constraintWidget7, measurer, measure, BasicMeasure.Measure.SELF_DIMENSIONS);
                horizontalSolvingPass(constraintWidget7, measurer, zIsRtl);
                verticalSolvingPass(constraintWidget7, measurer);
            }
        }
    }

    private static void verticalSolvingPass(ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested() && canMeasure(constraintWidget)) {
            ConstraintWidgetContainer.measure(constraintWidget, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
        }
        ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor anchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM);
        int finalValue = anchor.getFinalValue();
        int finalValue2 = anchor2.getFinalValue();
        if (anchor.getDependents() != null && anchor.hasFinalValue()) {
            Iterator<ConstraintAnchor> it = anchor.getDependents().iterator();
            while (it.hasNext()) {
                ConstraintAnchor next = it.next();
                ConstraintWidget constraintWidget2 = next.mOwner;
                boolean zCanMeasure = canMeasure(constraintWidget2);
                if (constraintWidget2.isMeasureRequested() && zCanMeasure) {
                    ConstraintWidgetContainer.measure(constraintWidget2, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidget2.getVerticalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (verticalDimensionBehaviour != dimensionBehaviour || zCanMeasure) {
                    if (!constraintWidget2.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor6 = constraintWidget2.mTop;
                        if (next == constraintAnchor6 && constraintWidget2.mBottom.mTarget == null) {
                            int margin = constraintAnchor6.getMargin() + finalValue;
                            constraintWidget2.setFinalVertical(margin, constraintWidget2.getHeight() + margin);
                            verticalSolvingPass(constraintWidget2, measurer);
                        } else {
                            ConstraintAnchor constraintAnchor7 = constraintWidget2.mBottom;
                            if (next == constraintAnchor7 && constraintAnchor7.mTarget == null) {
                                int margin2 = finalValue - constraintAnchor7.getMargin();
                                constraintWidget2.setFinalVertical(margin2 - constraintWidget2.getHeight(), margin2);
                                verticalSolvingPass(constraintWidget2, measurer);
                            } else if (next == constraintAnchor6 && (constraintAnchor3 = constraintAnchor7.mTarget) != null && constraintAnchor3.hasFinalValue()) {
                                solveVerticalCenterConstraints(measurer, constraintWidget2);
                            }
                        }
                    }
                } else if (constraintWidget2.getVerticalDimensionBehaviour() == dimensionBehaviour && constraintWidget2.mMatchConstraintMaxHeight >= 0 && constraintWidget2.mMatchConstraintMinHeight >= 0 && (constraintWidget2.getVisibility() == 8 || (constraintWidget2.mMatchConstraintDefaultHeight == 0 && constraintWidget2.getDimensionRatio() == 0.0f))) {
                    if (!constraintWidget2.isInVerticalChain() && !constraintWidget2.isInVirtualLayout()) {
                        if (((next == constraintWidget2.mTop && (constraintAnchor5 = constraintWidget2.mBottom.mTarget) != null && constraintAnchor5.hasFinalValue()) || (next == constraintWidget2.mBottom && (constraintAnchor4 = constraintWidget2.mTop.mTarget) != null && constraintAnchor4.hasFinalValue())) && !constraintWidget2.isInVerticalChain()) {
                            solveVerticalMatchConstraint(constraintWidget, measurer, constraintWidget2);
                        }
                    }
                }
            }
        }
        if (constraintWidget instanceof Guideline) {
            return;
        }
        if (anchor2.getDependents() != null && anchor2.hasFinalValue()) {
            Iterator<ConstraintAnchor> it2 = anchor2.getDependents().iterator();
            while (it2.hasNext()) {
                ConstraintAnchor next2 = it2.next();
                ConstraintWidget constraintWidget3 = next2.mOwner;
                boolean zCanMeasure2 = canMeasure(constraintWidget3);
                if (constraintWidget3.isMeasureRequested() && zCanMeasure2) {
                    ConstraintWidgetContainer.measure(constraintWidget3, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
                boolean z6 = (next2 == constraintWidget3.mTop && (constraintAnchor2 = constraintWidget3.mBottom.mTarget) != null && constraintAnchor2.hasFinalValue()) || (next2 == constraintWidget3.mBottom && (constraintAnchor = constraintWidget3.mTop.mTarget) != null && constraintAnchor.hasFinalValue());
                ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour2 = constraintWidget3.getVerticalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (verticalDimensionBehaviour2 != dimensionBehaviour2 || zCanMeasure2) {
                    if (!constraintWidget3.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor8 = constraintWidget3.mTop;
                        if (next2 == constraintAnchor8 && constraintWidget3.mBottom.mTarget == null) {
                            int margin3 = constraintAnchor8.getMargin() + finalValue2;
                            constraintWidget3.setFinalVertical(margin3, constraintWidget3.getHeight() + margin3);
                            verticalSolvingPass(constraintWidget3, measurer);
                        } else {
                            ConstraintAnchor constraintAnchor9 = constraintWidget3.mBottom;
                            if (next2 == constraintAnchor9 && constraintAnchor8.mTarget == null) {
                                int margin4 = finalValue2 - constraintAnchor9.getMargin();
                                constraintWidget3.setFinalVertical(margin4 - constraintWidget3.getHeight(), margin4);
                                verticalSolvingPass(constraintWidget3, measurer);
                            } else if (z6 && !constraintWidget3.isInVerticalChain()) {
                                solveVerticalCenterConstraints(measurer, constraintWidget3);
                            }
                        }
                    }
                } else if (constraintWidget3.getVerticalDimensionBehaviour() == dimensionBehaviour2 && constraintWidget3.mMatchConstraintMaxHeight >= 0 && constraintWidget3.mMatchConstraintMinHeight >= 0 && (constraintWidget3.getVisibility() == 8 || (constraintWidget3.mMatchConstraintDefaultHeight == 0 && constraintWidget3.getDimensionRatio() == 0.0f))) {
                    if (!constraintWidget3.isInVerticalChain() && !constraintWidget3.isInVirtualLayout() && z6 && !constraintWidget3.isInVerticalChain()) {
                        solveVerticalMatchConstraint(constraintWidget, measurer, constraintWidget3);
                    }
                }
            }
        }
        ConstraintAnchor anchor3 = constraintWidget.getAnchor(ConstraintAnchor.Type.BASELINE);
        if (anchor3.getDependents() == null || !anchor3.hasFinalValue()) {
            return;
        }
        int finalValue3 = anchor3.getFinalValue();
        Iterator<ConstraintAnchor> it3 = anchor3.getDependents().iterator();
        while (it3.hasNext()) {
            ConstraintAnchor next3 = it3.next();
            ConstraintWidget constraintWidget4 = next3.mOwner;
            boolean zCanMeasure3 = canMeasure(constraintWidget4);
            if (constraintWidget4.isMeasureRequested() && zCanMeasure3) {
                ConstraintWidgetContainer.measure(constraintWidget4, measurer, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
            }
            if (constraintWidget4.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || zCanMeasure3) {
                if (!constraintWidget4.isMeasureRequested() && next3 == constraintWidget4.mBaseline) {
                    constraintWidget4.setFinalBaseline(finalValue3);
                    verticalSolvingPass(constraintWidget4, measurer);
                }
            }
        }
    }
}
