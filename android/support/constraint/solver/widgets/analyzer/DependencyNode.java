package android.support.constraint.solver.widgets.analyzer;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DependencyNode implements Dependency {
    public int margin;
    public WidgetRun run;
    public int value;
    public Dependency updateDelegate = null;
    public boolean delegateToWidgetRun = false;
    public boolean readyToSolve = false;
    public Type type = Type.UNKNOWN;
    public int marginFactor = 1;
    public DimensionDependency marginDependency = null;
    public boolean resolved = false;
    public List<Dependency> dependencies = new ArrayList();
    public List<DependencyNode> targets = new ArrayList();

    public enum Type {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public DependencyNode(WidgetRun widgetRun) {
        this.run = widgetRun;
    }

    public void addDependency(Dependency dependency) {
        this.dependencies.add(dependency);
        if (this.resolved) {
            dependency.update(dependency);
        }
    }

    public void clear() {
        this.targets.clear();
        this.dependencies.clear();
        this.resolved = false;
        this.value = 0;
        this.readyToSolve = false;
        this.delegateToWidgetRun = false;
    }

    public String name() {
        String debugName = this.run.widget.getDebugName();
        Type type = this.type;
        StringBuilder sbM94a = C0080b.m94a((type == Type.LEFT || type == Type.RIGHT) ? C0063n.m88a(debugName, "_HORIZONTAL") : C0063n.m88a(debugName, "_VERTICAL"), ":");
        sbM94a.append(this.type.name());
        return sbM94a.toString();
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.run.widget.getDebugName());
        sb.append(":");
        sb.append(this.type);
        sb.append("(");
        sb.append(this.resolved ? Integer.valueOf(this.value) : "unresolved");
        sb.append(") <t=");
        sb.append(this.targets.size());
        sb.append(":d=");
        sb.append(this.dependencies.size());
        sb.append(">");
        return sb.toString();
    }

    @Override // android.support.constraint.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        Iterator<DependencyNode> it = this.targets.iterator();
        while (it.hasNext()) {
            if (!it.next().resolved) {
                return;
            }
        }
        this.readyToSolve = true;
        Dependency dependency2 = this.updateDelegate;
        if (dependency2 != null) {
            dependency2.update(this);
        }
        if (this.delegateToWidgetRun) {
            this.run.update(this);
            return;
        }
        DependencyNode dependencyNode = null;
        int i7 = 0;
        for (DependencyNode dependencyNode2 : this.targets) {
            if (!(dependencyNode2 instanceof DimensionDependency)) {
                i7++;
                dependencyNode = dependencyNode2;
            }
        }
        if (dependencyNode != null && i7 == 1 && dependencyNode.resolved) {
            DimensionDependency dimensionDependency = this.marginDependency;
            if (dimensionDependency != null) {
                if (!dimensionDependency.resolved) {
                    return;
                } else {
                    this.margin = this.marginFactor * dimensionDependency.value;
                }
            }
            resolve(dependencyNode.value + this.margin);
        }
        Dependency dependency3 = this.updateDelegate;
        if (dependency3 != null) {
            dependency3.update(this);
        }
    }
}
