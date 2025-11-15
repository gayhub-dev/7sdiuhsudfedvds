package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Guideline extends ConstraintWidget {
    public static final int HORIZONTAL = 0;
    public static final int RELATIVE_BEGIN = 1;
    public static final int RELATIVE_END = 2;
    public static final int RELATIVE_PERCENT = 0;
    public static final int RELATIVE_UNKNWON = -1;
    public static final int VERTICAL = 1;
    private boolean resolved;
    public float mRelativePercent = -1.0f;
    public int mRelativeBegin = -1;
    public int mRelativeEnd = -1;
    private ConstraintAnchor mAnchor = this.mTop;
    private int mOrientation = 0;
    private int mMinimumPosition = 0;

    /* renamed from: android.support.constraint.solver.widgets.Guideline$1 */
    public static /* synthetic */ class C00921 {

        /* renamed from: $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type */
        public static final /* synthetic */ int[] f141x1d400623;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f141x1d400623 = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f141x1d400623[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f141x1d400623[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f141x1d400623[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f141x1d400623[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f141x1d400623[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f141x1d400623[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f141x1d400623[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f141x1d400623[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public Guideline() {
        this.mAnchors.clear();
        this.mAnchors.add(this.mAnchor);
        int length = this.mListAnchors.length;
        for (int i7 = 0; i7 < length; i7++) {
            this.mListAnchors[i7] = this.mAnchor;
        }
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem, boolean z6) {
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) getParent();
        if (constraintWidgetContainer == null) {
            return;
        }
        ConstraintAnchor anchor = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintWidget constraintWidget = this.mParent;
        boolean z7 = constraintWidget != null && constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (this.mOrientation == 0) {
            anchor = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.TOP);
            anchor2 = constraintWidgetContainer.getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintWidget constraintWidget2 = this.mParent;
            z7 = constraintWidget2 != null && constraintWidget2.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.resolved && this.mAnchor.hasFinalValue()) {
            SolverVariable solverVariableCreateObjectVariable = linearSystem.createObjectVariable(this.mAnchor);
            linearSystem.addEquality(solverVariableCreateObjectVariable, this.mAnchor.getFinalValue());
            if (this.mRelativeBegin != -1) {
                if (z7) {
                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(anchor2), solverVariableCreateObjectVariable, 0, 5);
                }
            } else if (this.mRelativeEnd != -1 && z7) {
                SolverVariable solverVariableCreateObjectVariable2 = linearSystem.createObjectVariable(anchor2);
                linearSystem.addGreaterThan(solverVariableCreateObjectVariable, linearSystem.createObjectVariable(anchor), 0, 5);
                linearSystem.addGreaterThan(solverVariableCreateObjectVariable2, solverVariableCreateObjectVariable, 0, 5);
            }
            this.resolved = false;
            return;
        }
        if (this.mRelativeBegin != -1) {
            SolverVariable solverVariableCreateObjectVariable3 = linearSystem.createObjectVariable(this.mAnchor);
            linearSystem.addEquality(solverVariableCreateObjectVariable3, linearSystem.createObjectVariable(anchor), this.mRelativeBegin, 8);
            if (z7) {
                linearSystem.addGreaterThan(linearSystem.createObjectVariable(anchor2), solverVariableCreateObjectVariable3, 0, 5);
                return;
            }
            return;
        }
        if (this.mRelativeEnd == -1) {
            if (this.mRelativePercent != -1.0f) {
                linearSystem.addConstraint(LinearSystem.createRowDimensionPercent(linearSystem, linearSystem.createObjectVariable(this.mAnchor), linearSystem.createObjectVariable(anchor2), this.mRelativePercent));
                return;
            }
            return;
        }
        SolverVariable solverVariableCreateObjectVariable4 = linearSystem.createObjectVariable(this.mAnchor);
        SolverVariable solverVariableCreateObjectVariable5 = linearSystem.createObjectVariable(anchor2);
        linearSystem.addEquality(solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable5, -this.mRelativeEnd, 8);
        if (z7) {
            linearSystem.addGreaterThan(solverVariableCreateObjectVariable4, linearSystem.createObjectVariable(anchor), 0, 5);
            linearSystem.addGreaterThan(solverVariableCreateObjectVariable5, solverVariableCreateObjectVariable4, 0, 5);
        }
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.copy(constraintWidget, map);
        Guideline guideline = (Guideline) constraintWidget;
        this.mRelativePercent = guideline.mRelativePercent;
        this.mRelativeBegin = guideline.mRelativeBegin;
        this.mRelativeEnd = guideline.mRelativeEnd;
        setOrientation(guideline.mOrientation);
    }

    public void cyclePosition() {
        if (this.mRelativeBegin != -1) {
            inferRelativePercentPosition();
        } else if (this.mRelativePercent != -1.0f) {
            inferRelativeEndPosition();
        } else if (this.mRelativeEnd != -1) {
            inferRelativeBeginPosition();
        }
    }

    public ConstraintAnchor getAnchor() {
        return this.mAnchor;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getRelativeBegin() {
        return this.mRelativeBegin;
    }

    public int getRelativeBehaviour() {
        if (this.mRelativePercent != -1.0f) {
            return 0;
        }
        if (this.mRelativeBegin != -1) {
            return 1;
        }
        return this.mRelativeEnd != -1 ? 2 : -1;
    }

    public int getRelativeEnd() {
        return this.mRelativeEnd;
    }

    public float getRelativePercent() {
        return this.mRelativePercent;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public String getType() {
        return "Guideline";
    }

    public void inferRelativeBeginPosition() {
        int x6 = getX();
        if (this.mOrientation == 0) {
            x6 = getY();
        }
        setGuideBegin(x6);
    }

    public void inferRelativeEndPosition() {
        int width = getParent().getWidth() - getX();
        if (this.mOrientation == 0) {
            width = getParent().getHeight() - getY();
        }
        setGuideEnd(width);
    }

    public void inferRelativePercentPosition() {
        float x6 = getX() / getParent().getWidth();
        if (this.mOrientation == 0) {
            x6 = getY() / getParent().getHeight();
        }
        setGuidePercent(x6);
    }

    public boolean isPercent() {
        return this.mRelativePercent != -1.0f && this.mRelativeBegin == -1 && this.mRelativeEnd == -1;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.resolved;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.resolved;
    }

    public void setFinalValue(int i7) {
        this.mAnchor.setFinalValue(i7);
        this.resolved = true;
    }

    public void setGuideBegin(int i7) {
        if (i7 > -1) {
            this.mRelativePercent = -1.0f;
            this.mRelativeBegin = i7;
            this.mRelativeEnd = -1;
        }
    }

    public void setGuideEnd(int i7) {
        if (i7 > -1) {
            this.mRelativePercent = -1.0f;
            this.mRelativeBegin = -1;
            this.mRelativeEnd = i7;
        }
    }

    public void setGuidePercent(int i7) {
        setGuidePercent(i7 / 100.0f);
    }

    public void setMinimumPosition(int i7) {
        this.mMinimumPosition = i7;
    }

    public void setOrientation(int i7) {
        if (this.mOrientation == i7) {
            return;
        }
        this.mOrientation = i7;
        this.mAnchors.clear();
        if (this.mOrientation == 1) {
            this.mAnchor = this.mLeft;
        } else {
            this.mAnchor = this.mTop;
        }
        this.mAnchors.add(this.mAnchor);
        int length = this.mListAnchors.length;
        for (int i8 = 0; i8 < length; i8++) {
            this.mListAnchors[i8] = this.mAnchor;
        }
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public void updateFromSolver(LinearSystem linearSystem, boolean z6) {
        if (getParent() == null) {
            return;
        }
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mAnchor);
        if (this.mOrientation == 1) {
            setX(objectVariableValue);
            setY(0);
            setHeight(getParent().getHeight());
            setWidth(0);
            return;
        }
        setX(0);
        setY(objectVariableValue);
        setWidth(getParent().getWidth());
        setHeight(0);
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (C00921.f141x1d400623[type.ordinal()]) {
            case 1:
            case 2:
                if (this.mOrientation == 1) {
                    return this.mAnchor;
                }
                break;
            case 3:
            case 4:
                if (this.mOrientation == 0) {
                    return this.mAnchor;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
        }
        throw new AssertionError(type.name());
    }

    public void setGuidePercent(float f7) {
        if (f7 > -1.0f) {
            this.mRelativePercent = f7;
            this.mRelativeBegin = -1;
            this.mRelativeEnd = -1;
        }
    }
}
