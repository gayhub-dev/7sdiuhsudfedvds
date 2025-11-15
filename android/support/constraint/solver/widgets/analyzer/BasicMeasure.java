package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.Helper;
import android.support.constraint.solver.widgets.Optimizer;
import android.support.constraint.solver.widgets.VirtualLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer constraintWidgetContainer;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();
    private Measure mMeasure = new Measure();

    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.constraintWidgetContainer = constraintWidgetContainer;
    }

    private boolean measure(Measurer measurer, ConstraintWidget constraintWidget, int i7) {
        this.mMeasure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = constraintWidget.getWidth();
        this.mMeasure.verticalDimension = constraintWidget.getHeight();
        Measure measure = this.mMeasure;
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = i7;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = measure.horizontalBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z6 = dimensionBehaviour == dimensionBehaviour2;
        boolean z7 = measure.verticalBehavior == dimensionBehaviour2;
        boolean z8 = z6 && constraintWidget.mDimensionRatio > 0.0f;
        boolean z9 = z7 && constraintWidget.mDimensionRatio > 0.0f;
        if (z8 && constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z9 && constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
        Measure measure2 = this.mMeasure;
        measure2.measureStrategy = Measure.SELF_DIMENSIONS;
        return measure2.measuredNeedsSolverPass;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0098 A[PHI: r10
      0x0098: PHI (r10v2 boolean) = (r10v1 boolean), (r10v1 boolean), (r10v1 boolean), (r10v4 boolean), (r10v4 boolean) binds: [B:32:0x0062, B:34:0x0068, B:36:0x006c, B:54:0x0095, B:52:0x008e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ac A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void measureChildren(android.support.constraint.solver.widgets.ConstraintWidgetContainer r13) {
        /*
            r12 = this;
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r0 = r13.mChildren
            int r0 = r0.size()
            r1 = 64
            boolean r1 = r13.optimizeFor(r1)
            android.support.constraint.solver.widgets.analyzer.BasicMeasure$Measurer r2 = r13.getMeasurer()
            r3 = 0
            r4 = 0
        L12:
            if (r4 >= r0) goto Lb0
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r5 = r13.mChildren
            java.lang.Object r5 = r5.get(r4)
            android.support.constraint.solver.widgets.ConstraintWidget r5 = (android.support.constraint.solver.widgets.ConstraintWidget) r5
            boolean r6 = r5 instanceof android.support.constraint.solver.widgets.Guideline
            if (r6 == 0) goto L22
            goto Lac
        L22:
            boolean r6 = r5 instanceof android.support.constraint.solver.widgets.Barrier
            if (r6 == 0) goto L28
            goto Lac
        L28:
            boolean r6 = r5.isInVirtualLayout()
            if (r6 == 0) goto L30
            goto Lac
        L30:
            if (r1 == 0) goto L48
            android.support.constraint.solver.widgets.analyzer.HorizontalWidgetRun r6 = r5.horizontalRun
            if (r6 == 0) goto L48
            android.support.constraint.solver.widgets.analyzer.VerticalWidgetRun r7 = r5.verticalRun
            if (r7 == 0) goto L48
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r6 = r6.dimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L48
            android.support.constraint.solver.widgets.analyzer.DimensionDependency r6 = r7.dimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L48
            goto Lac
        L48:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = r5.getDimensionBehaviour(r3)
            r7 = 1
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.getDimensionBehaviour(r7)
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r9) goto L61
            int r10 = r5.mMatchConstraintDefaultWidth
            if (r10 == r7) goto L61
            if (r8 != r9) goto L61
            int r10 = r5.mMatchConstraintDefaultHeight
            if (r10 == r7) goto L61
            r10 = 1
            goto L62
        L61:
            r10 = 0
        L62:
            if (r10 != 0) goto L98
            boolean r11 = r13.optimizeFor(r7)
            if (r11 == 0) goto L98
            boolean r11 = r5 instanceof android.support.constraint.solver.widgets.VirtualLayout
            if (r11 != 0) goto L98
            if (r6 != r9) goto L7d
            int r11 = r5.mMatchConstraintDefaultWidth
            if (r11 != 0) goto L7d
            if (r8 == r9) goto L7d
            boolean r11 = r5.isInHorizontalChain()
            if (r11 != 0) goto L7d
            r10 = 1
        L7d:
            if (r8 != r9) goto L8c
            int r11 = r5.mMatchConstraintDefaultHeight
            if (r11 != 0) goto L8c
            if (r6 == r9) goto L8c
            boolean r11 = r5.isInHorizontalChain()
            if (r11 != 0) goto L8c
            r10 = 1
        L8c:
            if (r6 == r9) goto L90
            if (r8 != r9) goto L98
        L90:
            float r6 = r5.mDimensionRatio
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L98
            goto L99
        L98:
            r7 = r10
        L99:
            if (r7 == 0) goto L9c
            goto Lac
        L9c:
            int r6 = android.support.constraint.solver.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r12.measure(r2, r5, r6)
            android.support.constraint.solver.Metrics r5 = r13.mMetrics
            if (r5 == 0) goto Lac
            long r6 = r5.measuredWidgets
            r8 = 1
            long r6 = r6 + r8
            r5.measuredWidgets = r6
        Lac:
            int r4 = r4 + 1
            goto L12
        Lb0:
            r2.didMeasures()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.analyzer.BasicMeasure.measureChildren(android.support.constraint.solver.widgets.ConstraintWidgetContainer):void");
    }

    private void solveLinearSystem(ConstraintWidgetContainer constraintWidgetContainer, String str, int i7, int i8) {
        int minWidth = constraintWidgetContainer.getMinWidth();
        int minHeight = constraintWidgetContainer.getMinHeight();
        constraintWidgetContainer.setMinWidth(0);
        constraintWidgetContainer.setMinHeight(0);
        constraintWidgetContainer.setWidth(i7);
        constraintWidgetContainer.setHeight(i8);
        constraintWidgetContainer.setMinWidth(minWidth);
        constraintWidgetContainer.setMinHeight(minHeight);
        this.constraintWidgetContainer.layout();
    }

    public long solverMeasure(ConstraintWidgetContainer constraintWidgetContainer, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        boolean zDirectMeasureWithOrientation;
        int i16;
        int i17;
        boolean z6;
        boolean z7;
        boolean z8;
        int i18;
        Measurer measurer;
        int i19;
        int i20;
        int i21;
        boolean z9;
        Metrics metrics;
        Measurer measurer2 = constraintWidgetContainer.getMeasurer();
        int size = constraintWidgetContainer.mChildren.size();
        int width = constraintWidgetContainer.getWidth();
        int height = constraintWidgetContainer.getHeight();
        boolean zEnabled = Optimizer.enabled(i7, 128);
        boolean z10 = zEnabled || Optimizer.enabled(i7, 64);
        if (z10) {
            for (int i22 = 0; i22 < size; i22++) {
                ConstraintWidget constraintWidget = constraintWidgetContainer.mChildren.get(i22);
                ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean z11 = (horizontalDimensionBehaviour == dimensionBehaviour) && (constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour) && constraintWidget.getDimensionRatio() > 0.0f;
                if ((constraintWidget.isInHorizontalChain() && z11) || ((constraintWidget.isInVerticalChain() && z11) || (constraintWidget instanceof VirtualLayout) || constraintWidget.isInHorizontalChain() || constraintWidget.isInVerticalChain())) {
                    z10 = false;
                    break;
                }
            }
        }
        if (z10 && (metrics = LinearSystem.sMetrics) != null) {
            metrics.measures++;
        }
        boolean z12 = z10 & ((i10 == 1073741824 && i12 == 1073741824) || zEnabled);
        if (z12) {
            int iMin = Math.min(constraintWidgetContainer.getMaxWidth(), i11);
            int iMin2 = Math.min(constraintWidgetContainer.getMaxHeight(), i13);
            if (i10 == 1073741824 && constraintWidgetContainer.getWidth() != iMin) {
                constraintWidgetContainer.setWidth(iMin);
                constraintWidgetContainer.invalidateGraph();
            }
            if (i12 == 1073741824 && constraintWidgetContainer.getHeight() != iMin2) {
                constraintWidgetContainer.setHeight(iMin2);
                constraintWidgetContainer.invalidateGraph();
            }
            if (i10 == 1073741824 && i12 == 1073741824) {
                zDirectMeasureWithOrientation = constraintWidgetContainer.directMeasure(zEnabled);
                i16 = 2;
            } else {
                boolean zDirectMeasureSetup = constraintWidgetContainer.directMeasureSetup(zEnabled);
                if (i10 == 1073741824) {
                    zDirectMeasureSetup &= constraintWidgetContainer.directMeasureWithOrientation(zEnabled, 0);
                    i16 = 1;
                } else {
                    i16 = 0;
                }
                if (i12 == 1073741824) {
                    zDirectMeasureWithOrientation = constraintWidgetContainer.directMeasureWithOrientation(zEnabled, 1) & zDirectMeasureSetup;
                    i16++;
                } else {
                    zDirectMeasureWithOrientation = zDirectMeasureSetup;
                }
            }
            if (zDirectMeasureWithOrientation) {
                constraintWidgetContainer.updateFromRuns(i10 == 1073741824, i12 == 1073741824);
            }
        } else {
            zDirectMeasureWithOrientation = false;
            i16 = 0;
        }
        if (zDirectMeasureWithOrientation && i16 == 2) {
            return 0L;
        }
        int optimizationLevel = constraintWidgetContainer.getOptimizationLevel();
        if (size > 0) {
            measureChildren(constraintWidgetContainer);
        }
        updateHierarchy(constraintWidgetContainer);
        int size2 = this.mVariableDimensionsWidgets.size();
        if (size > 0) {
            solveLinearSystem(constraintWidgetContainer, "First pass", width, height);
        }
        if (size2 > 0) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidgetContainer.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z13 = horizontalDimensionBehaviour2 == dimensionBehaviour2;
            boolean z14 = constraintWidgetContainer.getVerticalDimensionBehaviour() == dimensionBehaviour2;
            int iMax = Math.max(constraintWidgetContainer.getWidth(), this.constraintWidgetContainer.getMinWidth());
            int iMax2 = Math.max(constraintWidgetContainer.getHeight(), this.constraintWidgetContainer.getMinHeight());
            int i23 = 0;
            boolean zNeedSolverPass = false;
            while (i23 < size2) {
                ConstraintWidget constraintWidget2 = this.mVariableDimensionsWidgets.get(i23);
                if (constraintWidget2 instanceof VirtualLayout) {
                    int width2 = constraintWidget2.getWidth();
                    i19 = optimizationLevel;
                    int height2 = constraintWidget2.getHeight();
                    i20 = width;
                    boolean zMeasure = measure(measurer2, constraintWidget2, Measure.TRY_GIVEN_DIMENSIONS) | zNeedSolverPass;
                    Metrics metrics2 = constraintWidgetContainer.mMetrics;
                    i21 = height;
                    if (metrics2 != null) {
                        metrics2.measuredMatchWidgets++;
                    }
                    int width3 = constraintWidget2.getWidth();
                    int height3 = constraintWidget2.getHeight();
                    if (width3 != width2) {
                        constraintWidget2.setWidth(width3);
                        if (z13 && constraintWidget2.getRight() > iMax) {
                            iMax = Math.max(iMax, constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin() + constraintWidget2.getRight());
                        }
                        z9 = true;
                    } else {
                        z9 = zMeasure;
                    }
                    if (height3 != height2) {
                        constraintWidget2.setHeight(height3);
                        if (z14 && constraintWidget2.getBottom() > iMax2) {
                            iMax2 = Math.max(iMax2, constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin() + constraintWidget2.getBottom());
                        }
                        z9 = true;
                    }
                    zNeedSolverPass = z9 | ((VirtualLayout) constraintWidget2).needSolverPass();
                } else {
                    i19 = optimizationLevel;
                    i20 = width;
                    i21 = height;
                }
                i23++;
                optimizationLevel = i19;
                width = i20;
                height = i21;
            }
            int i24 = optimizationLevel;
            int i25 = width;
            int i26 = height;
            int i27 = 0;
            int i28 = 2;
            while (i27 < i28) {
                int i29 = 0;
                while (i29 < size2) {
                    ConstraintWidget constraintWidget3 = this.mVariableDimensionsWidgets.get(i29);
                    if (((constraintWidget3 instanceof Helper) && !(constraintWidget3 instanceof VirtualLayout)) || (constraintWidget3 instanceof Guideline) || constraintWidget3.getVisibility() == 8 || ((z12 && constraintWidget3.horizontalRun.dimension.resolved && constraintWidget3.verticalRun.dimension.resolved) || (constraintWidget3 instanceof VirtualLayout))) {
                        z8 = z12;
                        i18 = size2;
                        measurer = measurer2;
                    } else {
                        int width4 = constraintWidget3.getWidth();
                        int height4 = constraintWidget3.getHeight();
                        int baselineDistance = constraintWidget3.getBaselineDistance();
                        int i30 = Measure.TRY_GIVEN_DIMENSIONS;
                        z8 = z12;
                        if (i27 == 1) {
                            i30 = Measure.USE_GIVEN_DIMENSIONS;
                        }
                        boolean zMeasure2 = measure(measurer2, constraintWidget3, i30) | zNeedSolverPass;
                        Metrics metrics3 = constraintWidgetContainer.mMetrics;
                        i18 = size2;
                        measurer = measurer2;
                        if (metrics3 != null) {
                            metrics3.measuredMatchWidgets++;
                        }
                        int width5 = constraintWidget3.getWidth();
                        int height5 = constraintWidget3.getHeight();
                        if (width5 != width4) {
                            constraintWidget3.setWidth(width5);
                            if (z13 && constraintWidget3.getRight() > iMax) {
                                iMax = Math.max(iMax, constraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin() + constraintWidget3.getRight());
                            }
                            zMeasure2 = true;
                        }
                        if (height5 != height4) {
                            constraintWidget3.setHeight(height5);
                            if (z14 && constraintWidget3.getBottom() > iMax2) {
                                iMax2 = Math.max(iMax2, constraintWidget3.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin() + constraintWidget3.getBottom());
                            }
                            zMeasure2 = true;
                        }
                        zNeedSolverPass = (!constraintWidget3.hasBaseline() || baselineDistance == constraintWidget3.getBaselineDistance()) ? zMeasure2 : true;
                    }
                    i29++;
                    size2 = i18;
                    measurer2 = measurer;
                    z12 = z8;
                }
                boolean z15 = z12;
                int i31 = size2;
                Measurer measurer3 = measurer2;
                if (!zNeedSolverPass) {
                    break;
                }
                solveLinearSystem(constraintWidgetContainer, "intermediate pass", i25, i26);
                i27++;
                measurer2 = measurer3;
                z12 = z15;
                i28 = 2;
                zNeedSolverPass = false;
                size2 = i31;
            }
            if (zNeedSolverPass) {
                solveLinearSystem(constraintWidgetContainer, "2nd pass", i25, i26);
                if (constraintWidgetContainer.getWidth() < iMax) {
                    constraintWidgetContainer.setWidth(iMax);
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (constraintWidgetContainer.getHeight() < iMax2) {
                    constraintWidgetContainer.setHeight(iMax2);
                    z7 = true;
                } else {
                    z7 = z6;
                }
                if (z7) {
                    solveLinearSystem(constraintWidgetContainer, "3rd pass", i25, i26);
                }
            }
            i17 = i24;
        } else {
            i17 = optimizationLevel;
        }
        constraintWidgetContainer.setOptimizationLevel(i17);
        return 0L;
    }

    public void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer) {
        this.mVariableDimensionsWidgets.clear();
        int size = constraintWidgetContainer.mChildren.size();
        for (int i7 = 0; i7 < size; i7++) {
            ConstraintWidget constraintWidget = constraintWidgetContainer.mChildren.get(i7);
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            if (horizontalDimensionBehaviour == dimensionBehaviour || constraintWidget.getVerticalDimensionBehaviour() == dimensionBehaviour) {
                this.mVariableDimensionsWidgets.add(constraintWidget);
            }
        }
        constraintWidgetContainer.invalidateGraph();
    }
}
