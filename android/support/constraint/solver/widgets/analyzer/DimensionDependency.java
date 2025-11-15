package android.support.constraint.solver.widgets.analyzer;

import android.support.constraint.solver.widgets.analyzer.DependencyNode;

/* loaded from: classes.dex */
class DimensionDependency extends DependencyNode {
    public int wrapValue;

    public DimensionDependency(WidgetRun widgetRun) {
        super(widgetRun);
        if (widgetRun instanceof HorizontalWidgetRun) {
            this.type = DependencyNode.Type.HORIZONTAL_DIMENSION;
        } else {
            this.type = DependencyNode.Type.VERTICAL_DIMENSION;
        }
    }

    @Override // android.support.constraint.solver.widgets.analyzer.DependencyNode
    public void resolve(int i7) {
        if (this.resolved) {
            return;
        }
        this.resolved = true;
        this.value = i7;
        for (Dependency dependency : this.dependencies) {
            dependency.update(dependency);
        }
    }
}
