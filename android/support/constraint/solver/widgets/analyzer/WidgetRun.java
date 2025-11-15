package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;

/* loaded from: classes.dex */
public abstract class WidgetRun implements Dependency {
    public ConstraintWidget.DimensionBehaviour dimensionBehavior;
    public int matchConstraintsType;
    public RunGroup runGroup;
    public ConstraintWidget widget;
    public DimensionDependency dimension = new DimensionDependency(this);
    public int orientation = 0;
    public boolean resolved = false;
    public DependencyNode start = new DependencyNode(this);
    public DependencyNode end = new DependencyNode(this);
    public RunType mRunType = RunType.NONE;

    /* renamed from: android.support.constraint.solver.widgets.analyzer.WidgetRun$1 */
    public static /* synthetic */ class C00951 {

        /* renamed from: $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type */
        public static final /* synthetic */ int[] f147x1d400623;

        static {
            int[] iArr = new int[ConstraintAnchor.Type.values().length];
            f147x1d400623 = iArr;
            try {
                iArr[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f147x1d400623[ConstraintAnchor.Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f147x1d400623[ConstraintAnchor.Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f147x1d400623[ConstraintAnchor.Type.BASELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f147x1d400623[ConstraintAnchor.Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.widget = constraintWidget;
    }

    private void resolveDimension(int i7, int i8) {
        int i9 = this.matchConstraintsType;
        if (i9 == 0) {
            this.dimension.resolve(getLimitedDimension(i8, i7));
            return;
        }
        if (i9 == 1) {
            this.dimension.resolve(Math.min(getLimitedDimension(this.dimension.wrapValue, i7), i8));
            return;
        }
        if (i9 == 2) {
            ConstraintWidget parent = this.widget.getParent();
            if (parent != null) {
                if ((i7 == 0 ? parent.horizontalRun : parent.verticalRun).dimension.resolved) {
                    ConstraintWidget constraintWidget = this.widget;
                    this.dimension.resolve(getLimitedDimension((int) ((r9.value * (i7 == 0 ? constraintWidget.mMatchConstraintPercentWidth : constraintWidget.mMatchConstraintPercentHeight)) + 0.5f), i7));
                    return;
                }
                return;
            }
            return;
        }
        if (i9 != 3) {
            return;
        }
        ConstraintWidget constraintWidget2 = this.widget;
        WidgetRun widgetRun = constraintWidget2.horizontalRun;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = widgetRun.dimensionBehavior;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (dimensionBehaviour == dimensionBehaviour2 && widgetRun.matchConstraintsType == 3) {
            VerticalWidgetRun verticalWidgetRun = constraintWidget2.verticalRun;
            if (verticalWidgetRun.dimensionBehavior == dimensionBehaviour2 && verticalWidgetRun.matchConstraintsType == 3) {
                return;
            }
        }
        if (i7 == 0) {
            widgetRun = constraintWidget2.verticalRun;
        }
        if (widgetRun.dimension.resolved) {
            float dimensionRatio = constraintWidget2.getDimensionRatio();
            this.dimension.resolve(i7 == 1 ? (int) ((widgetRun.dimension.value / dimensionRatio) + 0.5f) : (int) ((dimensionRatio * widgetRun.dimension.value) + 0.5f));
        }
    }

    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i7) {
        dependencyNode.targets.add(dependencyNode2);
        dependencyNode.margin = i7;
        dependencyNode2.dependencies.add(dependencyNode);
    }

    public abstract void apply();

    public abstract void applyToWidget();

    public abstract void clear();

    public final int getLimitedDimension(int i7, int i8) {
        int iMax;
        if (i8 == 0) {
            ConstraintWidget constraintWidget = this.widget;
            int i9 = constraintWidget.mMatchConstraintMaxWidth;
            iMax = Math.max(constraintWidget.mMatchConstraintMinWidth, i7);
            if (i9 > 0) {
                iMax = Math.min(i9, i7);
            }
            if (iMax == i7) {
                return i7;
            }
        } else {
            ConstraintWidget constraintWidget2 = this.widget;
            int i10 = constraintWidget2.mMatchConstraintMaxHeight;
            iMax = Math.max(constraintWidget2.mMatchConstraintMinHeight, i7);
            if (i10 > 0) {
                iMax = Math.min(i10, i7);
            }
            if (iMax == i7) {
                return i7;
            }
        }
        return iMax;
    }

    public final DependencyNode getTarget(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        int i7 = C00951.f147x1d400623[constraintAnchor2.mType.ordinal()];
        if (i7 == 1) {
            return constraintWidget.horizontalRun.start;
        }
        if (i7 == 2) {
            return constraintWidget.horizontalRun.end;
        }
        if (i7 == 3) {
            return constraintWidget.verticalRun.start;
        }
        if (i7 == 4) {
            return constraintWidget.verticalRun.baseline;
        }
        if (i7 != 5) {
            return null;
        }
        return constraintWidget.verticalRun.end;
    }

    public long getWrapDimension() {
        if (this.dimension.resolved) {
            return r0.value;
        }
        return 0L;
    }

    public boolean isCenterConnection() {
        int size = this.start.targets.size();
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            if (this.start.targets.get(i8).run != this) {
                i7++;
            }
        }
        int size2 = this.end.targets.size();
        for (int i9 = 0; i9 < size2; i9++) {
            if (this.end.targets.get(i9).run != this) {
                i7++;
            }
        }
        return i7 >= 2;
    }

    public boolean isDimensionResolved() {
        return this.dimension.resolved;
    }

    public boolean isResolved() {
        return this.resolved;
    }

    public abstract void reset();

    public abstract boolean supportsWrapComputation();

    @Override // android.support.constraint.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
    }

    public void updateRunCenter(Dependency dependency, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i7) {
        DependencyNode target = getTarget(constraintAnchor);
        DependencyNode target2 = getTarget(constraintAnchor2);
        if (target.resolved && target2.resolved) {
            int margin = constraintAnchor.getMargin() + target.value;
            int margin2 = target2.value - constraintAnchor2.getMargin();
            int i8 = margin2 - margin;
            if (!this.dimension.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                resolveDimension(i7, i8);
            }
            DimensionDependency dimensionDependency = this.dimension;
            if (dimensionDependency.resolved) {
                if (dimensionDependency.value == i8) {
                    this.start.resolve(margin);
                    this.end.resolve(margin2);
                    return;
                }
                ConstraintWidget constraintWidget = this.widget;
                float horizontalBiasPercent = i7 == 0 ? constraintWidget.getHorizontalBiasPercent() : constraintWidget.getVerticalBiasPercent();
                if (target == target2) {
                    margin = target.value;
                    margin2 = target2.value;
                    horizontalBiasPercent = 0.5f;
                }
                this.start.resolve((int) ((((margin2 - margin) - this.dimension.value) * horizontalBiasPercent) + margin + 0.5f));
                this.end.resolve(this.start.value + this.dimension.value);
            }
        }
    }

    public void updateRunEnd(Dependency dependency) {
    }

    public void updateRunStart(Dependency dependency) {
    }

    public long wrapSize(int i7) {
        int i8;
        DimensionDependency dimensionDependency = this.dimension;
        if (!dimensionDependency.resolved) {
            return 0L;
        }
        long j7 = dimensionDependency.value;
        if (isCenterConnection()) {
            i8 = this.start.margin - this.end.margin;
        } else {
            if (i7 != 0) {
                return j7 - this.end.margin;
            }
            i8 = this.start.margin;
        }
        return j7 + i8;
    }

    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i7, DimensionDependency dimensionDependency) {
        dependencyNode.targets.add(dependencyNode2);
        dependencyNode.targets.add(this.dimension);
        dependencyNode.marginFactor = i7;
        dependencyNode.marginDependency = dimensionDependency;
        dependencyNode2.dependencies.add(dependencyNode);
        dimensionDependency.dependencies.add(dependencyNode);
    }

    public final DependencyNode getTarget(ConstraintAnchor constraintAnchor, int i7) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
        WidgetRun widgetRun = i7 == 0 ? constraintWidget.horizontalRun : constraintWidget.verticalRun;
        int i8 = C00951.f147x1d400623[constraintAnchor2.mType.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                if (i8 != 3) {
                    if (i8 != 5) {
                        return null;
                    }
                }
            }
            return widgetRun.end;
        }
        return widgetRun.start;
    }
}
