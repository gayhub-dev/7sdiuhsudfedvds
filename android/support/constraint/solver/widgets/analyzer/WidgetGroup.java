package android.support.constraint.solver.widgets.analyzer;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import android.support.constraint.solver.C0084a;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.Chain;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class WidgetGroup {
    private static final boolean DEBUG = false;
    public static int count;

    /* renamed from: id */
    public int f146id;
    public int orientation;
    public ArrayList<ConstraintWidget> widgets = new ArrayList<>();
    public boolean authoritative = false;
    public ArrayList<MeasureResult> results = null;
    private int moveTo = -1;

    public class MeasureResult {
        public int baseline;
        public int bottom;
        public int left;
        public int orientation;
        public int right;
        public int top;
        public WeakReference<ConstraintWidget> widgetRef;

        public MeasureResult(ConstraintWidget constraintWidget, LinearSystem linearSystem, int i7) {
            this.widgetRef = new WeakReference<>(constraintWidget);
            this.left = linearSystem.getObjectVariableValue(constraintWidget.mLeft);
            this.top = linearSystem.getObjectVariableValue(constraintWidget.mTop);
            this.right = linearSystem.getObjectVariableValue(constraintWidget.mRight);
            this.bottom = linearSystem.getObjectVariableValue(constraintWidget.mBottom);
            this.baseline = linearSystem.getObjectVariableValue(constraintWidget.mBaseline);
            this.orientation = i7;
        }

        public void apply() {
            ConstraintWidget constraintWidget = this.widgetRef.get();
            if (constraintWidget != null) {
                constraintWidget.setFinalFrame(this.left, this.top, this.right, this.bottom, this.baseline, this.orientation);
            }
        }
    }

    public WidgetGroup(int i7) {
        this.f146id = -1;
        this.orientation = 0;
        int i8 = count;
        count = i8 + 1;
        this.f146id = i8;
        this.orientation = i7;
    }

    private boolean contains(ConstraintWidget constraintWidget) {
        return this.widgets.contains(constraintWidget);
    }

    private String getOrientationString() {
        int i7 = this.orientation;
        return i7 == 0 ? "Horizontal" : i7 == 1 ? "Vertical" : i7 == 2 ? "Both" : "Unknown";
    }

    private int measureWrap(int i7, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.getDimensionBehaviour(i7);
        if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || dimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            return i7 == 0 ? constraintWidget.getWidth() : constraintWidget.getHeight();
        }
        return -1;
    }

    private int solverMeasure(LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i7) {
        int objectVariableValue;
        int objectVariableValue2;
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) arrayList.get(0).getParent();
        linearSystem.reset();
        constraintWidgetContainer.addToSolver(linearSystem, false);
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            arrayList.get(i8).addToSolver(linearSystem, false);
        }
        if (i7 == 0 && constraintWidgetContainer.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 0);
        }
        if (i7 == 1 && constraintWidgetContainer.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 1);
        }
        try {
            linearSystem.minimize();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        this.results = new ArrayList<>();
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            this.results.add(new MeasureResult(arrayList.get(i9), linearSystem, i7));
        }
        if (i7 == 0) {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mLeft);
            objectVariableValue2 = linearSystem.getObjectVariableValue(constraintWidgetContainer.mRight);
            linearSystem.reset();
        } else {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mTop);
            objectVariableValue2 = linearSystem.getObjectVariableValue(constraintWidgetContainer.mBottom);
            linearSystem.reset();
        }
        return objectVariableValue2 - objectVariableValue;
    }

    public boolean add(ConstraintWidget constraintWidget) {
        if (this.widgets.contains(constraintWidget)) {
            return false;
        }
        this.widgets.add(constraintWidget);
        return true;
    }

    public void apply() {
        if (this.results != null && this.authoritative) {
            for (int i7 = 0; i7 < this.results.size(); i7++) {
                this.results.get(i7).apply();
            }
        }
    }

    public void cleanup(ArrayList<WidgetGroup> arrayList) {
        int size = this.widgets.size();
        if (this.moveTo != -1 && size > 0) {
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                WidgetGroup widgetGroup = arrayList.get(i7);
                if (this.moveTo == widgetGroup.f146id) {
                    moveTo(this.orientation, widgetGroup);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public void clear() {
        this.widgets.clear();
    }

    public int getId() {
        return this.f146id;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean intersectWith(WidgetGroup widgetGroup) {
        for (int i7 = 0; i7 < this.widgets.size(); i7++) {
            if (widgetGroup.contains(this.widgets.get(i7))) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthoritative() {
        return this.authoritative;
    }

    public void moveTo(int i7, WidgetGroup widgetGroup) {
        Iterator<ConstraintWidget> it = this.widgets.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            widgetGroup.add(next);
            if (i7 == 0) {
                next.horizontalGroup = widgetGroup.getId();
            } else {
                next.verticalGroup = widgetGroup.getId();
            }
        }
        this.moveTo = widgetGroup.f146id;
    }

    public void setAuthoritative(boolean z6) {
        this.authoritative = z6;
    }

    public void setOrientation(int i7) {
        this.orientation = i7;
    }

    public int size() {
        return this.widgets.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getOrientationString());
        sb.append(" [");
        String strM96a = C0084a.m96a(sb, this.f146id, "] <");
        Iterator<ConstraintWidget> it = this.widgets.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            StringBuilder sbM94a = C0080b.m94a(strM96a, " ");
            sbM94a.append(next.getDebugName());
            strM96a = sbM94a.toString();
        }
        return C0063n.m88a(strM96a, " >");
    }

    public int measureWrap(LinearSystem linearSystem, int i7) {
        if (this.widgets.size() == 0) {
            return 0;
        }
        return solverMeasure(linearSystem, this.widgets, i7);
    }
}
