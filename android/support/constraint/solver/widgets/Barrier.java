package android.support.constraint.solver.widgets;

import android.arch.lifecycle.C0063n;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.HashMap;
import p009b.C0413b;

/* loaded from: classes.dex */
public class Barrier extends HelperWidget {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    private static final boolean USE_RELAX_GONE = false;
    private static final boolean USE_RESOLUTION = true;
    private int mBarrierType = 0;
    private boolean mAllowsGoneWidget = true;
    private int mMargin = 0;
    public boolean resolved = false;

    public Barrier() {
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z6) {
        ConstraintAnchor[] constraintAnchorArr;
        boolean z7;
        int i7;
        int i8;
        int i9;
        ConstraintAnchor[] constraintAnchorArr2 = this.mListAnchors;
        constraintAnchorArr2[0] = this.mLeft;
        constraintAnchorArr2[2] = this.mTop;
        constraintAnchorArr2[1] = this.mRight;
        constraintAnchorArr2[3] = this.mBottom;
        int i10 = 0;
        while (true) {
            constraintAnchorArr = this.mListAnchors;
            if (i10 >= constraintAnchorArr.length) {
                break;
            }
            constraintAnchorArr[i10].mSolverVariable = linearSystem.createObjectVariable(constraintAnchorArr[i10]);
            i10++;
        }
        int i11 = this.mBarrierType;
        if (i11 < 0 || i11 >= 4) {
            return;
        }
        ConstraintAnchor constraintAnchor = constraintAnchorArr[i11];
        if (!this.resolved) {
            allSolved();
        }
        if (this.resolved) {
            this.resolved = false;
            int i12 = this.mBarrierType;
            if (i12 == 0 || i12 == 1) {
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.f137mX);
                linearSystem.addEquality(this.mRight.mSolverVariable, this.f137mX);
                return;
            } else {
                if (i12 == 2 || i12 == 3) {
                    linearSystem.addEquality(this.mTop.mSolverVariable, this.f138mY);
                    linearSystem.addEquality(this.mBottom.mSolverVariable, this.f138mY);
                    return;
                }
                return;
            }
        }
        for (int i13 = 0; i13 < this.mWidgetsCount; i13++) {
            ConstraintWidget constraintWidget = this.mWidgets[i13];
            if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i8 = this.mBarrierType) == 0 || i8 == 1) && constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget != null) || (((i9 = this.mBarrierType) == 2 || i9 == 3) && constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null))) {
                z7 = true;
                break;
            }
        }
        z7 = false;
        boolean z8 = this.mLeft.hasCenteredDependents() || this.mRight.hasCenteredDependents();
        boolean z9 = this.mTop.hasCenteredDependents() || this.mBottom.hasCenteredDependents();
        int i14 = !z7 && (((i7 = this.mBarrierType) == 0 && z8) || ((i7 == 2 && z9) || ((i7 == 1 && z8) || (i7 == 3 && z9)))) ? 5 : 4;
        for (int i15 = 0; i15 < this.mWidgetsCount; i15++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i15];
            if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                SolverVariable solverVariableCreateObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.mBarrierType]);
                ConstraintAnchor[] constraintAnchorArr3 = constraintWidget2.mListAnchors;
                int i16 = this.mBarrierType;
                constraintAnchorArr3[i16].mSolverVariable = solverVariableCreateObjectVariable;
                int i17 = (constraintAnchorArr3[i16].mTarget == null || constraintAnchorArr3[i16].mTarget.mOwner != this) ? 0 : constraintAnchorArr3[i16].mMargin + 0;
                if (i16 == 0 || i16 == 2) {
                    linearSystem.addLowerBarrier(constraintAnchor.mSolverVariable, solverVariableCreateObjectVariable, this.mMargin - i17, z7);
                } else {
                    linearSystem.addGreaterBarrier(constraintAnchor.mSolverVariable, solverVariableCreateObjectVariable, this.mMargin + i17, z7);
                }
                linearSystem.addEquality(constraintAnchor.mSolverVariable, solverVariableCreateObjectVariable, this.mMargin + i17, i14);
            }
        }
        int i18 = this.mBarrierType;
        if (i18 == 0) {
            linearSystem.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 8);
            linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 4);
            linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 0);
            return;
        }
        if (i18 == 1) {
            linearSystem.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 8);
            linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 4);
            linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 0);
        } else if (i18 == 2) {
            linearSystem.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 8);
            linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 4);
            linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 0);
        } else if (i18 == 3) {
            linearSystem.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 8);
            linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 4);
            linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 0);
        }
    }

    public boolean allSolved() {
        int i7;
        int i8;
        int i9;
        int i10 = 0;
        boolean z6 = true;
        while (true) {
            i7 = this.mWidgetsCount;
            if (i10 >= i7) {
                break;
            }
            ConstraintWidget constraintWidget = this.mWidgets[i10];
            if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i8 = this.mBarrierType) == 0 || i8 == 1) && !constraintWidget.isResolvedHorizontally()) || (((i9 = this.mBarrierType) == 2 || i9 == 3) && !constraintWidget.isResolvedVertically()))) {
                z6 = false;
            }
            i10++;
        }
        if (!z6 || i7 <= 0) {
            return false;
        }
        int iMax = 0;
        boolean z7 = false;
        for (int i11 = 0; i11 < this.mWidgetsCount; i11++) {
            ConstraintWidget constraintWidget2 = this.mWidgets[i11];
            if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                if (!z7) {
                    int i12 = this.mBarrierType;
                    if (i12 == 0) {
                        iMax = constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT).getFinalValue();
                    } else if (i12 == 1) {
                        iMax = constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getFinalValue();
                    } else if (i12 == 2) {
                        iMax = constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP).getFinalValue();
                    } else if (i12 == 3) {
                        iMax = constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getFinalValue();
                    }
                    z7 = true;
                }
                int i13 = this.mBarrierType;
                if (i13 == 0) {
                    iMax = Math.min(iMax, constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT).getFinalValue());
                } else if (i13 == 1) {
                    iMax = Math.max(iMax, constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getFinalValue());
                } else if (i13 == 2) {
                    iMax = Math.min(iMax, constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP).getFinalValue());
                } else if (i13 == 3) {
                    iMax = Math.max(iMax, constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getFinalValue());
                }
            }
        }
        int i14 = iMax + this.mMargin;
        int i15 = this.mBarrierType;
        if (i15 == 0 || i15 == 1) {
            setFinalHorizontal(i14, i14);
        } else {
            setFinalVertical(i14, i14);
        }
        this.resolved = true;
        return true;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    public boolean allowsGoneWidget() {
        return this.mAllowsGoneWidget;
    }

    @Override // android.support.constraint.solver.widgets.HelperWidget, android.support.constraint.solver.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.copy(constraintWidget, map);
        Barrier barrier = (Barrier) constraintWidget;
        this.mBarrierType = barrier.mBarrierType;
        this.mAllowsGoneWidget = barrier.mAllowsGoneWidget;
        this.mMargin = barrier.mMargin;
    }

    public int getBarrierType() {
        return this.mBarrierType;
    }

    public int getMargin() {
        return this.mMargin;
    }

    public int getOrientation() {
        int i7 = this.mBarrierType;
        if (i7 == 0 || i7 == 1) {
            return 0;
        }
        return (i7 == 2 || i7 == 3) ? 1 : -1;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.resolved;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.resolved;
    }

    public void markWidgets() {
        for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
            ConstraintWidget constraintWidget = this.mWidgets[i7];
            int i8 = this.mBarrierType;
            if (i8 == 0 || i8 == 1) {
                constraintWidget.setInBarrier(0, true);
            } else if (i8 == 2 || i8 == 3) {
                constraintWidget.setInBarrier(1, true);
            }
        }
    }

    public void setAllowsGoneWidget(boolean z6) {
        this.mAllowsGoneWidget = z6;
    }

    public void setBarrierType(int i7) {
        this.mBarrierType = i7;
    }

    public void setMargin(int i7) {
        this.mMargin = i7;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("[Barrier] ");
        sbM112a.append(getDebugName());
        sbM112a.append(" {");
        String string = sbM112a.toString();
        for (int i7 = 0; i7 < this.mWidgetsCount; i7++) {
            ConstraintWidget constraintWidget = this.mWidgets[i7];
            if (i7 > 0) {
                string = C0063n.m88a(string, ", ");
            }
            StringBuilder sbM112a2 = C0413b.m112a(string);
            sbM112a2.append(constraintWidget.getDebugName());
            string = sbM112a2.toString();
        }
        return C0063n.m88a(string, "}");
    }

    public Barrier(String str) {
        setDebugName(str);
    }
}
