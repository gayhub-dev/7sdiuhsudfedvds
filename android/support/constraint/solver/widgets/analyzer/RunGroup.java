package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
class RunGroup {
    public static final int BASELINE = 2;
    public static final int END = 1;
    public static final int START = 0;
    public static int index;
    public int direction;
    public WidgetRun firstRun;
    public int groupIndex;
    public WidgetRun lastRun;
    public int position = 0;
    public boolean dual = false;
    public ArrayList<WidgetRun> runs = new ArrayList<>();

    public RunGroup(WidgetRun widgetRun, int i7) {
        this.firstRun = null;
        this.lastRun = null;
        this.groupIndex = 0;
        int i8 = index;
        this.groupIndex = i8;
        index = i8 + 1;
        this.firstRun = widgetRun;
        this.lastRun = widgetRun;
        this.direction = i7;
    }

    private boolean defineTerminalWidget(WidgetRun widgetRun, int i7) {
        DependencyNode dependencyNode;
        WidgetRun widgetRun2;
        DependencyNode dependencyNode2;
        WidgetRun widgetRun3;
        if (!widgetRun.widget.isTerminalWidget[i7]) {
            return false;
        }
        for (Dependency dependency : widgetRun.start.dependencies) {
            if ((dependency instanceof DependencyNode) && (widgetRun3 = (dependencyNode2 = (DependencyNode) dependency).run) != widgetRun && dependencyNode2 == widgetRun3.start) {
                if (widgetRun instanceof ChainRun) {
                    Iterator<WidgetRun> it = ((ChainRun) widgetRun).widgets.iterator();
                    while (it.hasNext()) {
                        defineTerminalWidget(it.next(), i7);
                    }
                } else if (!(widgetRun instanceof HelperReferences)) {
                    widgetRun.widget.isTerminalWidget[i7] = false;
                }
                defineTerminalWidget(dependencyNode2.run, i7);
            }
        }
        for (Dependency dependency2 : widgetRun.end.dependencies) {
            if ((dependency2 instanceof DependencyNode) && (widgetRun2 = (dependencyNode = (DependencyNode) dependency2).run) != widgetRun && dependencyNode == widgetRun2.start) {
                if (widgetRun instanceof ChainRun) {
                    Iterator<WidgetRun> it2 = ((ChainRun) widgetRun).widgets.iterator();
                    while (it2.hasNext()) {
                        defineTerminalWidget(it2.next(), i7);
                    }
                } else if (!(widgetRun instanceof HelperReferences)) {
                    widgetRun.widget.isTerminalWidget[i7] = false;
                }
                defineTerminalWidget(dependencyNode.run, i7);
            }
        }
        return false;
    }

    private long traverseEnd(DependencyNode dependencyNode, long j7) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun instanceof HelperReferences) {
            return j7;
        }
        int size = dependencyNode.dependencies.size();
        long jMin = j7;
        for (int i7 = 0; i7 < size; i7++) {
            Dependency dependency = dependencyNode.dependencies.get(i7);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.run != widgetRun) {
                    jMin = Math.min(jMin, traverseEnd(dependencyNode2, dependencyNode2.margin + j7));
                }
            }
        }
        if (dependencyNode != widgetRun.end) {
            return jMin;
        }
        long wrapDimension = j7 - widgetRun.getWrapDimension();
        return Math.min(Math.min(jMin, traverseEnd(widgetRun.start, wrapDimension)), wrapDimension - widgetRun.start.margin);
    }

    private long traverseStart(DependencyNode dependencyNode, long j7) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun instanceof HelperReferences) {
            return j7;
        }
        int size = dependencyNode.dependencies.size();
        long jMax = j7;
        for (int i7 = 0; i7 < size; i7++) {
            Dependency dependency = dependencyNode.dependencies.get(i7);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.run != widgetRun) {
                    jMax = Math.max(jMax, traverseStart(dependencyNode2, dependencyNode2.margin + j7));
                }
            }
        }
        if (dependencyNode != widgetRun.start) {
            return jMax;
        }
        long wrapDimension = j7 + widgetRun.getWrapDimension();
        return Math.max(Math.max(jMax, traverseStart(widgetRun.end, wrapDimension)), wrapDimension - widgetRun.end.margin);
    }

    public void add(WidgetRun widgetRun) {
        this.runs.add(widgetRun);
        this.lastRun = widgetRun;
    }

    public long computeWrapSize(ConstraintWidgetContainer constraintWidgetContainer, int i7) {
        WidgetRun widgetRun = this.firstRun;
        if (widgetRun instanceof ChainRun) {
            if (((ChainRun) widgetRun).orientation != i7) {
                return 0L;
            }
        } else if (i7 == 0) {
            if (!(widgetRun instanceof HorizontalWidgetRun)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof VerticalWidgetRun)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i7 == 0 ? constraintWidgetContainer.horizontalRun : constraintWidgetContainer.verticalRun).start;
        DependencyNode dependencyNode2 = (i7 == 0 ? constraintWidgetContainer.horizontalRun : constraintWidgetContainer.verticalRun).end;
        boolean zContains = widgetRun.start.targets.contains(dependencyNode);
        boolean zContains2 = this.firstRun.end.targets.contains(dependencyNode2);
        long wrapDimension = this.firstRun.getWrapDimension();
        if (!zContains || !zContains2) {
            if (zContains) {
                return Math.max(traverseStart(this.firstRun.start, r13.margin), this.firstRun.start.margin + wrapDimension);
            }
            if (zContains2) {
                return Math.max(-traverseEnd(this.firstRun.end, r13.margin), (-this.firstRun.end.margin) + wrapDimension);
            }
            return (this.firstRun.getWrapDimension() + r13.start.margin) - this.firstRun.end.margin;
        }
        long jTraverseStart = traverseStart(this.firstRun.start, 0L);
        long jTraverseEnd = traverseEnd(this.firstRun.end, 0L);
        long j7 = jTraverseStart - wrapDimension;
        WidgetRun widgetRun2 = this.firstRun;
        int i8 = widgetRun2.end.margin;
        if (j7 >= (-i8)) {
            j7 += i8;
        }
        int i9 = widgetRun2.start.margin;
        long j8 = ((-jTraverseEnd) - wrapDimension) - i9;
        if (j8 >= i9) {
            j8 -= i9;
        }
        float biasPercent = widgetRun2.widget.getBiasPercent(i7);
        float f7 = biasPercent > 0.0f ? (long) ((j7 / (1.0f - biasPercent)) + (j8 / biasPercent)) : 0L;
        long j9 = ((long) ((f7 * biasPercent) + 0.5f)) + wrapDimension + ((long) (((1.0f - biasPercent) * f7) + 0.5f));
        WidgetRun widgetRun3 = this.firstRun;
        return (widgetRun3.start.margin + j9) - widgetRun3.end.margin;
    }

    public void defineTerminalWidgets(boolean z6, boolean z7) {
        if (z6) {
            WidgetRun widgetRun = this.firstRun;
            if (widgetRun instanceof HorizontalWidgetRun) {
                defineTerminalWidget(widgetRun, 0);
            }
        }
        if (z7) {
            WidgetRun widgetRun2 = this.firstRun;
            if (widgetRun2 instanceof VerticalWidgetRun) {
                defineTerminalWidget(widgetRun2, 1);
            }
        }
    }
}
