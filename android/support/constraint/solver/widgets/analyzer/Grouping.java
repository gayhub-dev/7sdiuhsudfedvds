package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.HelperWidget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Grouping {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_GROUPING = false;

    public static WidgetGroup findDependents(ConstraintWidget constraintWidget, int i7, ArrayList<WidgetGroup> arrayList, WidgetGroup widgetGroup) {
        int iFindGroupInDependents;
        int i8 = i7 == 0 ? constraintWidget.horizontalGroup : constraintWidget.verticalGroup;
        if (i8 != -1 && (widgetGroup == null || i8 != widgetGroup.f146id)) {
            int i9 = 0;
            while (true) {
                if (i9 >= arrayList.size()) {
                    break;
                }
                WidgetGroup widgetGroup2 = arrayList.get(i9);
                if (widgetGroup2.getId() == i8) {
                    if (widgetGroup != null) {
                        widgetGroup.moveTo(i7, widgetGroup2);
                        arrayList.remove(widgetGroup);
                    }
                    widgetGroup = widgetGroup2;
                } else {
                    i9++;
                }
            }
        } else if (i8 != -1) {
            return widgetGroup;
        }
        if (widgetGroup == null) {
            if ((constraintWidget instanceof HelperWidget) && (iFindGroupInDependents = ((HelperWidget) constraintWidget).findGroupInDependents(i7)) != -1) {
                int i10 = 0;
                while (true) {
                    if (i10 >= arrayList.size()) {
                        break;
                    }
                    WidgetGroup widgetGroup3 = arrayList.get(i10);
                    if (widgetGroup3.getId() == iFindGroupInDependents) {
                        widgetGroup = widgetGroup3;
                        break;
                    }
                    i10++;
                }
            }
            if (widgetGroup == null) {
                widgetGroup = new WidgetGroup(i7);
            }
            arrayList.add(widgetGroup);
        }
        if (widgetGroup.add(constraintWidget)) {
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                guideline.getAnchor().findDependents(guideline.getOrientation() == 0 ? 1 : 0, arrayList, widgetGroup);
            }
            if (i7 == 0) {
                constraintWidget.horizontalGroup = widgetGroup.getId();
                constraintWidget.mLeft.findDependents(i7, arrayList, widgetGroup);
                constraintWidget.mRight.findDependents(i7, arrayList, widgetGroup);
            } else {
                constraintWidget.verticalGroup = widgetGroup.getId();
                constraintWidget.mTop.findDependents(i7, arrayList, widgetGroup);
                constraintWidget.mBaseline.findDependents(i7, arrayList, widgetGroup);
                constraintWidget.mBottom.findDependents(i7, arrayList, widgetGroup);
            }
            constraintWidget.mCenter.findDependents(i7, arrayList, widgetGroup);
        }
        return widgetGroup;
    }

    private static WidgetGroup findGroup(ArrayList<WidgetGroup> arrayList, int i7) {
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            WidgetGroup widgetGroup = arrayList.get(i8);
            if (i7 == widgetGroup.f146id) {
                return widgetGroup;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:179:0x0349  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean simpleSolvingPass(android.support.constraint.solver.widgets.ConstraintWidgetContainer r16, android.support.constraint.solver.widgets.analyzer.BasicMeasure.Measurer r17) {
        /*
            Method dump skipped, instructions count: 919
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.analyzer.Grouping.simpleSolvingPass(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.widgets.analyzer.BasicMeasure$Measurer):boolean");
    }

    public static boolean validInGroup(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, ConstraintWidget.DimensionBehaviour dimensionBehaviour3, ConstraintWidget.DimensionBehaviour dimensionBehaviour4) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour6;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.FIXED;
        return (dimensionBehaviour3 == dimensionBehaviour7 || dimensionBehaviour3 == (dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour != dimensionBehaviour6)) || (dimensionBehaviour4 == dimensionBehaviour7 || dimensionBehaviour4 == (dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour2 != dimensionBehaviour5));
    }
}
