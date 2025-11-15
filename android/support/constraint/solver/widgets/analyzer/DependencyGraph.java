package android.support.constraint.solver.widgets.analyzer;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import android.support.constraint.solver.C0084a;
import android.support.constraint.solver.widgets.Barrier;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.HelperWidget;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class DependencyGraph {
    private static final boolean USE_GROUPS = true;
    private ConstraintWidgetContainer container;
    private ConstraintWidgetContainer mContainer;
    private boolean mNeedBuildGraph = true;
    private boolean mNeedRedoMeasures = true;
    private ArrayList<WidgetRun> mRuns = new ArrayList<>();
    private ArrayList<RunGroup> runGroups = new ArrayList<>();
    private BasicMeasure.Measurer mMeasurer = null;
    private BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    public ArrayList<RunGroup> mGroups = new ArrayList<>();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        this.container = constraintWidgetContainer;
        this.mContainer = constraintWidgetContainer;
    }

    private void applyGroup(DependencyNode dependencyNode, int i7, int i8, DependencyNode dependencyNode2, ArrayList<RunGroup> arrayList, RunGroup runGroup) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun.runGroup == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            if (widgetRun == constraintWidgetContainer.horizontalRun || widgetRun == constraintWidgetContainer.verticalRun) {
                return;
            }
            if (runGroup == null) {
                runGroup = new RunGroup(widgetRun, i8);
                arrayList.add(runGroup);
            }
            widgetRun.runGroup = runGroup;
            runGroup.add(widgetRun);
            for (Dependency dependency : widgetRun.start.dependencies) {
                if (dependency instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency, i7, 0, dependencyNode2, arrayList, runGroup);
                }
            }
            for (Dependency dependency2 : widgetRun.end.dependencies) {
                if (dependency2 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency2, i7, 1, dependencyNode2, arrayList, runGroup);
                }
            }
            if (i7 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                    if (dependency3 instanceof DependencyNode) {
                        applyGroup((DependencyNode) dependency3, i7, 2, dependencyNode2, arrayList, runGroup);
                    }
                }
            }
            for (DependencyNode dependencyNode3 : widgetRun.start.targets) {
                if (dependencyNode3 == dependencyNode2) {
                    runGroup.dual = true;
                }
                applyGroup(dependencyNode3, i7, 0, dependencyNode2, arrayList, runGroup);
            }
            for (DependencyNode dependencyNode4 : widgetRun.end.targets) {
                if (dependencyNode4 == dependencyNode2) {
                    runGroup.dual = true;
                }
                applyGroup(dependencyNode4, i7, 1, dependencyNode2, arrayList, runGroup);
            }
            if (i7 == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                Iterator<DependencyNode> it = ((VerticalWidgetRun) widgetRun).baseline.targets.iterator();
                while (it.hasNext()) {
                    applyGroup(it.next(), i7, 2, dependencyNode2, arrayList, runGroup);
                }
            }
        }
    }

    private boolean basicMeasureWidgets(ConstraintWidgetContainer constraintWidgetContainer) {
        int width;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        int height;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4;
        Iterator<ConstraintWidget> it = constraintWidgetContainer.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = next.mListDimensionBehaviors;
            ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = dimensionBehaviourArr[0];
            ConstraintWidget.DimensionBehaviour dimensionBehaviour6 = dimensionBehaviourArr[1];
            if (next.getVisibility() == 8) {
                next.measured = true;
            } else {
                if (next.mMatchConstraintPercentWidth < 1.0f && dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    next.mMatchConstraintDefaultWidth = 2;
                }
                if (next.mMatchConstraintPercentHeight < 1.0f && dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    next.mMatchConstraintDefaultHeight = 2;
                }
                if (next.getDimensionRatio() > 0.0f) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                    if (dimensionBehaviour5 == dimensionBehaviour7 && (dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour6 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        next.mMatchConstraintDefaultWidth = 3;
                    } else if (dimensionBehaviour6 == dimensionBehaviour7 && (dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || dimensionBehaviour5 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        next.mMatchConstraintDefaultHeight = 3;
                    } else if (dimensionBehaviour5 == dimensionBehaviour7 && dimensionBehaviour6 == dimensionBehaviour7) {
                        if (next.mMatchConstraintDefaultWidth == 0) {
                            next.mMatchConstraintDefaultWidth = 3;
                        }
                        if (next.mMatchConstraintDefaultHeight == 0) {
                            next.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour8 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour5 == dimensionBehaviour8 && next.mMatchConstraintDefaultWidth == 1 && (next.mLeft.mTarget == null || next.mRight.mTarget == null)) {
                    dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour9 = dimensionBehaviour5;
                if (dimensionBehaviour6 == dimensionBehaviour8 && next.mMatchConstraintDefaultHeight == 1 && (next.mTop.mTarget == null || next.mBottom.mTarget == null)) {
                    dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                }
                ConstraintWidget.DimensionBehaviour dimensionBehaviour10 = dimensionBehaviour6;
                HorizontalWidgetRun horizontalWidgetRun = next.horizontalRun;
                horizontalWidgetRun.dimensionBehavior = dimensionBehaviour9;
                int i7 = next.mMatchConstraintDefaultWidth;
                horizontalWidgetRun.matchConstraintsType = i7;
                VerticalWidgetRun verticalWidgetRun = next.verticalRun;
                verticalWidgetRun.dimensionBehavior = dimensionBehaviour10;
                int i8 = next.mMatchConstraintDefaultHeight;
                verticalWidgetRun.matchConstraintsType = i8;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour11 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
                if ((dimensionBehaviour9 == dimensionBehaviour11 || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (dimensionBehaviour10 == dimensionBehaviour11 || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
                    int width2 = next.getWidth();
                    if (dimensionBehaviour9 == dimensionBehaviour11) {
                        width = (constraintWidgetContainer.getWidth() - next.mLeft.mMargin) - next.mRight.mMargin;
                        dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        width = width2;
                        dimensionBehaviour = dimensionBehaviour9;
                    }
                    int height2 = next.getHeight();
                    if (dimensionBehaviour10 == dimensionBehaviour11) {
                        height = (constraintWidgetContainer.getHeight() - next.mTop.mMargin) - next.mBottom.mMargin;
                        dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
                    } else {
                        height = height2;
                        dimensionBehaviour2 = dimensionBehaviour10;
                    }
                    measure(next, dimensionBehaviour, width, dimensionBehaviour2, height);
                    next.horizontalRun.dimension.resolve(next.getWidth());
                    next.verticalRun.dimension.resolve(next.getHeight());
                    next.measured = true;
                } else {
                    if (dimensionBehaviour9 == dimensionBehaviour8 && (dimensionBehaviour10 == (dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour10 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i7 == 3) {
                            if (dimensionBehaviour10 == dimensionBehaviour4) {
                                measure(next, dimensionBehaviour4, 0, dimensionBehaviour4, 0);
                            }
                            int height3 = next.getHeight();
                            int i9 = (int) ((height3 * next.mDimensionRatio) + 0.5f);
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour12 = ConstraintWidget.DimensionBehaviour.FIXED;
                            measure(next, dimensionBehaviour12, i9, dimensionBehaviour12, height3);
                            next.horizontalRun.dimension.resolve(next.getWidth());
                            next.verticalRun.dimension.resolve(next.getHeight());
                            next.measured = true;
                        } else if (i7 == 1) {
                            measure(next, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                            next.horizontalRun.dimension.wrapValue = next.getWidth();
                        } else if (i7 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour13 = dimensionBehaviourArr2[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour14 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour13 == dimensionBehaviour14 || dimensionBehaviourArr2[0] == dimensionBehaviour11) {
                                measure(next, dimensionBehaviour14, (int) ((next.mMatchConstraintPercentWidth * constraintWidgetContainer.getWidth()) + 0.5f), dimensionBehaviour10, next.getHeight());
                                next.horizontalRun.dimension.resolve(next.getWidth());
                                next.verticalRun.dimension.resolve(next.getHeight());
                                next.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = next.mListAnchors;
                            if (constraintAnchorArr[0].mTarget == null || constraintAnchorArr[1].mTarget == null) {
                                measure(next, dimensionBehaviour4, 0, dimensionBehaviour10, 0);
                                next.horizontalRun.dimension.resolve(next.getWidth());
                                next.verticalRun.dimension.resolve(next.getHeight());
                                next.measured = true;
                            }
                        }
                    }
                    if (dimensionBehaviour10 == dimensionBehaviour8 && (dimensionBehaviour9 == (dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour9 == ConstraintWidget.DimensionBehaviour.FIXED)) {
                        if (i8 == 3) {
                            if (dimensionBehaviour9 == dimensionBehaviour3) {
                                measure(next, dimensionBehaviour3, 0, dimensionBehaviour3, 0);
                            }
                            int width3 = next.getWidth();
                            float f7 = next.mDimensionRatio;
                            if (next.getDimensionRatioSide() == -1) {
                                f7 = 1.0f / f7;
                            }
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour15 = ConstraintWidget.DimensionBehaviour.FIXED;
                            measure(next, dimensionBehaviour15, width3, dimensionBehaviour15, (int) ((width3 * f7) + 0.5f));
                            next.horizontalRun.dimension.resolve(next.getWidth());
                            next.verticalRun.dimension.resolve(next.getHeight());
                            next.measured = true;
                        } else if (i8 == 1) {
                            measure(next, dimensionBehaviour9, 0, dimensionBehaviour3, 0);
                            next.verticalRun.dimension.wrapValue = next.getHeight();
                        } else if (i8 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr3 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour16 = dimensionBehaviourArr3[1];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour17 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour16 == dimensionBehaviour17 || dimensionBehaviourArr3[1] == dimensionBehaviour11) {
                                measure(next, dimensionBehaviour9, next.getWidth(), dimensionBehaviour17, (int) ((next.mMatchConstraintPercentHeight * constraintWidgetContainer.getHeight()) + 0.5f));
                                next.horizontalRun.dimension.resolve(next.getWidth());
                                next.verticalRun.dimension.resolve(next.getHeight());
                                next.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = next.mListAnchors;
                            if (constraintAnchorArr2[2].mTarget == null || constraintAnchorArr2[3].mTarget == null) {
                                measure(next, dimensionBehaviour3, 0, dimensionBehaviour10, 0);
                                next.horizontalRun.dimension.resolve(next.getWidth());
                                next.verticalRun.dimension.resolve(next.getHeight());
                                next.measured = true;
                            }
                        }
                    }
                    if (dimensionBehaviour9 == dimensionBehaviour8 && dimensionBehaviour10 == dimensionBehaviour8) {
                        if (i7 == 1 || i8 == 1) {
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour18 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                            measure(next, dimensionBehaviour18, 0, dimensionBehaviour18, 0);
                            next.horizontalRun.dimension.wrapValue = next.getWidth();
                            next.verticalRun.dimension.wrapValue = next.getHeight();
                        } else if (i8 == 2 && i7 == 2) {
                            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr4 = constraintWidgetContainer.mListDimensionBehaviors;
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour19 = dimensionBehaviourArr4[0];
                            ConstraintWidget.DimensionBehaviour dimensionBehaviour20 = ConstraintWidget.DimensionBehaviour.FIXED;
                            if (dimensionBehaviour19 == dimensionBehaviour20 || dimensionBehaviourArr4[0] == dimensionBehaviour20) {
                                if (dimensionBehaviourArr4[1] == dimensionBehaviour20 || dimensionBehaviourArr4[1] == dimensionBehaviour20) {
                                    measure(next, dimensionBehaviour20, (int) ((next.mMatchConstraintPercentWidth * constraintWidgetContainer.getWidth()) + 0.5f), dimensionBehaviour20, (int) ((next.mMatchConstraintPercentHeight * constraintWidgetContainer.getHeight()) + 0.5f));
                                    next.horizontalRun.dimension.resolve(next.getWidth());
                                    next.verticalRun.dimension.resolve(next.getHeight());
                                    next.measured = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private int computeWrap(ConstraintWidgetContainer constraintWidgetContainer, int i7) {
        int size = this.mGroups.size();
        long jMax = 0;
        for (int i8 = 0; i8 < size; i8++) {
            jMax = Math.max(jMax, this.mGroups.get(i8).computeWrapSize(constraintWidgetContainer, i7));
        }
        return (int) jMax;
    }

    private void displayGraph() {
        Iterator<WidgetRun> it = this.mRuns.iterator();
        String strGenerateDisplayGraph = "digraph {\n";
        while (it.hasNext()) {
            strGenerateDisplayGraph = generateDisplayGraph(it.next(), strGenerateDisplayGraph);
        }
        String strM88a = C0063n.m88a(strGenerateDisplayGraph, "\n}\n");
        System.out.println("content:<<\n" + strM88a + "\n>>");
    }

    private void findGroup(WidgetRun widgetRun, int i7, ArrayList<RunGroup> arrayList) {
        for (Dependency dependency : widgetRun.start.dependencies) {
            if (dependency instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency, i7, 0, widgetRun.end, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency).start, i7, 0, widgetRun.end, arrayList, null);
            }
        }
        for (Dependency dependency2 : widgetRun.end.dependencies) {
            if (dependency2 instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency2, i7, 1, widgetRun.start, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency2).end, i7, 1, widgetRun.start, arrayList, null);
            }
        }
        if (i7 == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                if (dependency3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency3, i7, 2, null, arrayList, null);
                }
            }
        }
    }

    private String generateChainDisplayGraph(ChainRun chainRun, String str) {
        int i7 = chainRun.orientation;
        StringBuilder sbM112a = C0413b.m112a("cluster_");
        sbM112a.append(chainRun.widget.getDebugName());
        String string = sbM112a.toString();
        String strM97a = C0096a.m97a("subgraph ", i7 == 0 ? C0063n.m88a(string, "_h") : C0063n.m88a(string, "_v"), " {\n");
        Iterator<WidgetRun> it = chainRun.widgets.iterator();
        String strGenerateDisplayGraph = "";
        while (it.hasNext()) {
            WidgetRun next = it.next();
            String debugName = next.widget.getDebugName();
            strM97a = C0096a.m97a(strM97a, i7 == 0 ? C0063n.m88a(debugName, "_HORIZONTAL") : C0063n.m88a(debugName, "_VERTICAL"), ";\n");
            strGenerateDisplayGraph = generateDisplayGraph(next, strGenerateDisplayGraph);
        }
        return C0096a.m97a(str, strGenerateDisplayGraph, C0063n.m88a(strM97a, "}\n"));
    }

    private String generateDisplayGraph(WidgetRun widgetRun, String str) {
        boolean z6;
        DependencyNode dependencyNode = widgetRun.start;
        DependencyNode dependencyNode2 = widgetRun.end;
        if (!(widgetRun instanceof HelperReferences) && dependencyNode.dependencies.isEmpty() && (dependencyNode2.dependencies.isEmpty() && dependencyNode.targets.isEmpty()) && dependencyNode2.targets.isEmpty()) {
            return str;
        }
        StringBuilder sbM112a = C0413b.m112a(str);
        sbM112a.append(nodeDefinition(widgetRun));
        String string = sbM112a.toString();
        boolean zIsCenteredConnection = isCenteredConnection(dependencyNode, dependencyNode2);
        String strGenerateDisplayNode = generateDisplayNode(dependencyNode2, zIsCenteredConnection, generateDisplayNode(dependencyNode, zIsCenteredConnection, string));
        boolean z7 = widgetRun instanceof VerticalWidgetRun;
        if (z7) {
            strGenerateDisplayNode = generateDisplayNode(((VerticalWidgetRun) widgetRun).baseline, zIsCenteredConnection, strGenerateDisplayNode);
        }
        if ((widgetRun instanceof HorizontalWidgetRun) || (((z6 = widgetRun instanceof ChainRun)) && ((ChainRun) widgetRun).orientation == 0)) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = widgetRun.widget.getHorizontalDimensionBehaviour();
            if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                if (!dependencyNode.targets.isEmpty() && dependencyNode2.targets.isEmpty()) {
                    StringBuilder sbM112a2 = C0413b.m112a("\n");
                    sbM112a2.append(dependencyNode2.name());
                    sbM112a2.append(" -> ");
                    sbM112a2.append(dependencyNode.name());
                    sbM112a2.append("\n");
                    strGenerateDisplayNode = C0063n.m88a(strGenerateDisplayNode, sbM112a2.toString());
                } else if (dependencyNode.targets.isEmpty() && !dependencyNode2.targets.isEmpty()) {
                    StringBuilder sbM112a3 = C0413b.m112a("\n");
                    sbM112a3.append(dependencyNode.name());
                    sbM112a3.append(" -> ");
                    sbM112a3.append(dependencyNode2.name());
                    sbM112a3.append("\n");
                    strGenerateDisplayNode = C0063n.m88a(strGenerateDisplayNode, sbM112a3.toString());
                }
            } else if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun.widget.getDimensionRatio() > 0.0f) {
                widgetRun.widget.getDebugName();
            }
        } else if (z7 || (z6 && ((ChainRun) widgetRun).orientation == 1)) {
            ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = widgetRun.widget.getVerticalDimensionBehaviour();
            if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                if (!dependencyNode.targets.isEmpty() && dependencyNode2.targets.isEmpty()) {
                    StringBuilder sbM112a4 = C0413b.m112a("\n");
                    sbM112a4.append(dependencyNode2.name());
                    sbM112a4.append(" -> ");
                    sbM112a4.append(dependencyNode.name());
                    sbM112a4.append("\n");
                    strGenerateDisplayNode = C0063n.m88a(strGenerateDisplayNode, sbM112a4.toString());
                } else if (dependencyNode.targets.isEmpty() && !dependencyNode2.targets.isEmpty()) {
                    StringBuilder sbM112a5 = C0413b.m112a("\n");
                    sbM112a5.append(dependencyNode.name());
                    sbM112a5.append(" -> ");
                    sbM112a5.append(dependencyNode2.name());
                    sbM112a5.append("\n");
                    strGenerateDisplayNode = C0063n.m88a(strGenerateDisplayNode, sbM112a5.toString());
                }
            } else if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun.widget.getDimensionRatio() > 0.0f) {
                widgetRun.widget.getDebugName();
            }
        }
        return widgetRun instanceof ChainRun ? generateChainDisplayGraph((ChainRun) widgetRun, strGenerateDisplayNode) : strGenerateDisplayNode;
    }

    private String generateDisplayNode(DependencyNode dependencyNode, boolean z6, String str) {
        for (DependencyNode dependencyNode2 : dependencyNode.targets) {
            StringBuilder sbM112a = C0413b.m112a("\n");
            sbM112a.append(dependencyNode.name());
            StringBuilder sbM94a = C0080b.m94a(sbM112a.toString(), " -> ");
            sbM94a.append(dependencyNode2.name());
            String string = sbM94a.toString();
            if (dependencyNode.margin > 0 || z6 || (dependencyNode.run instanceof HelperReferences)) {
                String strM88a = C0063n.m88a(string, "[");
                if (dependencyNode.margin > 0) {
                    strM88a = C0084a.m96a(C0080b.m94a(strM88a, "label=\""), dependencyNode.margin, "\"");
                    if (z6) {
                        strM88a = C0063n.m88a(strM88a, ",");
                    }
                }
                if (z6) {
                    strM88a = C0063n.m88a(strM88a, " style=dashed ");
                }
                if (dependencyNode.run instanceof HelperReferences) {
                    strM88a = C0063n.m88a(strM88a, " style=bold,color=gray ");
                }
                string = C0063n.m88a(strM88a, "]");
            }
            str = C0063n.m88a(str, C0063n.m88a(string, "\n"));
        }
        return str;
    }

    private boolean isCenteredConnection(DependencyNode dependencyNode, DependencyNode dependencyNode2) {
        Iterator<DependencyNode> it = dependencyNode.targets.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            if (it.next() != dependencyNode2) {
                i7++;
            }
        }
        Iterator<DependencyNode> it2 = dependencyNode2.targets.iterator();
        int i8 = 0;
        while (it2.hasNext()) {
            if (it2.next() != dependencyNode) {
                i8++;
            }
        }
        return i7 > 0 && i8 > 0;
    }

    private void measure(ConstraintWidget constraintWidget, ConstraintWidget.DimensionBehaviour dimensionBehaviour, int i7, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, int i8) {
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = dimensionBehaviour;
        measure.verticalBehavior = dimensionBehaviour2;
        measure.horizontalDimension = i7;
        measure.verticalDimension = i8;
        this.mMeasurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    private String nodeDefinition(WidgetRun widgetRun) {
        String strM88a;
        String strM96a;
        String strM88a2;
        boolean z6 = widgetRun instanceof VerticalWidgetRun;
        String debugName = widgetRun.widget.getDebugName();
        ConstraintWidget constraintWidget = widgetRun.widget;
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = !z6 ? constraintWidget.getHorizontalDimensionBehaviour() : constraintWidget.getVerticalDimensionBehaviour();
        RunGroup runGroup = widgetRun.runGroup;
        String strM88a3 = C0063n.m88a(C0063n.m88a(C0063n.m88a(!z6 ? C0063n.m88a(debugName, "_HORIZONTAL") : C0063n.m88a(debugName, "_VERTICAL"), " [shape=none, label=<"), "<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"2\">"), "  <TR>");
        if (z6) {
            String strM88a4 = C0063n.m88a(strM88a3, "    <TD ");
            if (widgetRun.start.resolved) {
                strM88a4 = C0063n.m88a(strM88a4, " BGCOLOR=\"green\"");
            }
            strM88a = C0063n.m88a(strM88a4, " PORT=\"TOP\" BORDER=\"1\">T</TD>");
        } else {
            String strM88a5 = C0063n.m88a(strM88a3, "    <TD ");
            if (widgetRun.start.resolved) {
                strM88a5 = C0063n.m88a(strM88a5, " BGCOLOR=\"green\"");
            }
            strM88a = C0063n.m88a(strM88a5, " PORT=\"LEFT\" BORDER=\"1\">L</TD>");
        }
        String strM88a6 = C0063n.m88a(strM88a, "    <TD BORDER=\"1\" ");
        boolean z7 = widgetRun.dimension.resolved;
        if (z7 && !widgetRun.widget.measured) {
            strM88a6 = C0063n.m88a(strM88a6, " BGCOLOR=\"green\" ");
        } else if (z7 && widgetRun.widget.measured) {
            strM88a6 = C0063n.m88a(strM88a6, " BGCOLOR=\"lightgray\" ");
        } else if (!z7 && widgetRun.widget.measured) {
            strM88a6 = C0063n.m88a(strM88a6, " BGCOLOR=\"yellow\" ");
        }
        if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            strM88a6 = C0063n.m88a(strM88a6, "style=\"dashed\"");
        }
        if (runGroup != null) {
            StringBuilder sbM112a = C0413b.m112a(" [");
            sbM112a.append(runGroup.groupIndex + 1);
            sbM112a.append(ServiceReference.DELIMITER);
            strM96a = C0084a.m96a(sbM112a, RunGroup.index, "]");
        } else {
            strM96a = "";
        }
        String str = strM88a6 + ">" + debugName + strM96a + " </TD>";
        if (z6) {
            String strM88a7 = C0063n.m88a(str, "    <TD ");
            if ((widgetRun instanceof VerticalWidgetRun) && ((VerticalWidgetRun) widgetRun).baseline.resolved) {
                strM88a7 = C0063n.m88a(strM88a7, " BGCOLOR=\"green\"");
            }
            String strM88a8 = C0063n.m88a(C0063n.m88a(strM88a7, " PORT=\"BASELINE\" BORDER=\"1\">b</TD>"), "    <TD ");
            if (widgetRun.end.resolved) {
                strM88a8 = C0063n.m88a(strM88a8, " BGCOLOR=\"green\"");
            }
            strM88a2 = C0063n.m88a(strM88a8, " PORT=\"BOTTOM\" BORDER=\"1\">B</TD>");
        } else {
            String strM88a9 = C0063n.m88a(str, "    <TD ");
            if (widgetRun.end.resolved) {
                strM88a9 = C0063n.m88a(strM88a9, " BGCOLOR=\"green\"");
            }
            strM88a2 = C0063n.m88a(strM88a9, " PORT=\"RIGHT\" BORDER=\"1\">R</TD>");
        }
        return C0063n.m88a(C0063n.m88a(strM88a2, "  </TR></TABLE>"), ">];\n");
    }

    public void buildGraph() {
        buildGraph(this.mRuns);
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.container.horizontalRun, 0, this.mGroups);
        findGroup(this.container.verticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public void defineTerminalWidgets(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2) {
        if (this.mNeedBuildGraph) {
            buildGraph();
            Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
            boolean z6 = false;
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                boolean[] zArr = next.isTerminalWidget;
                zArr[0] = true;
                zArr[1] = true;
                if (next instanceof Barrier) {
                    z6 = true;
                }
            }
            if (z6) {
                return;
            }
            Iterator<RunGroup> it2 = this.mGroups.iterator();
            while (it2.hasNext()) {
                RunGroup next2 = it2.next();
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                next2.defineTerminalWidgets(dimensionBehaviour == dimensionBehaviour3, dimensionBehaviour2 == dimensionBehaviour3);
            }
        }
    }

    public boolean directMeasure(boolean z6) {
        boolean z7;
        boolean z8 = true;
        boolean z9 = z6 & true;
        if (this.mNeedBuildGraph || this.mNeedRedoMeasures) {
            Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                next.horizontalRun.reset();
                next.verticalRun.reset();
            }
            this.container.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            constraintWidgetContainer.horizontalRun.reset();
            this.container.verticalRun.reset();
            this.mNeedRedoMeasures = false;
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = this.container.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.container.getDimensionBehaviour(1);
        if (this.mNeedBuildGraph) {
            buildGraph();
        }
        int x6 = this.container.getX();
        int y6 = this.container.getY();
        this.container.horizontalRun.start.resolve(x6);
        this.container.verticalRun.start.resolve(y6);
        measureWidgets();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (dimensionBehaviour == dimensionBehaviour3 || dimensionBehaviour2 == dimensionBehaviour3) {
            if (z9) {
                Iterator<WidgetRun> it2 = this.mRuns.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    if (!it2.next().supportsWrapComputation()) {
                        z9 = false;
                        break;
                    }
                }
            }
            if (z9 && dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                constraintWidgetContainer2.setWidth(computeWrap(constraintWidgetContainer2, 0));
                ConstraintWidgetContainer constraintWidgetContainer3 = this.container;
                constraintWidgetContainer3.horizontalRun.dimension.resolve(constraintWidgetContainer3.getWidth());
            }
            if (z9 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer4 = this.container;
                constraintWidgetContainer4.setHeight(computeWrap(constraintWidgetContainer4, 1));
                ConstraintWidgetContainer constraintWidgetContainer5 = this.container;
                constraintWidgetContainer5.verticalRun.dimension.resolve(constraintWidgetContainer5.getHeight());
            }
        }
        ConstraintWidgetContainer constraintWidgetContainer6 = this.container;
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidgetContainer6.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviour4 == dimensionBehaviour5 || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int width = constraintWidgetContainer6.getWidth() + x6;
            this.container.horizontalRun.end.resolve(width);
            this.container.horizontalRun.dimension.resolve(width - x6);
            measureWidgets();
            ConstraintWidgetContainer constraintWidgetContainer7 = this.container;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer7.mListDimensionBehaviors;
            if (dimensionBehaviourArr2[1] == dimensionBehaviour5 || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer7.getHeight() + y6;
                this.container.verticalRun.end.resolve(height);
                this.container.verticalRun.dimension.resolve(height - y6);
            }
            measureWidgets();
            z7 = true;
        } else {
            z7 = false;
        }
        Iterator<WidgetRun> it3 = this.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.widget != this.container || next2.resolved) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it4 = this.mRuns.iterator();
        while (it4.hasNext()) {
            WidgetRun next3 = it4.next();
            if (z7 || next3.widget != this.container) {
                if (!next3.start.resolved || ((!next3.end.resolved && !(next3 instanceof GuidelineReference)) || (!next3.dimension.resolved && !(next3 instanceof ChainRun) && !(next3 instanceof GuidelineReference)))) {
                    z8 = false;
                    break;
                }
            }
        }
        this.container.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.container.setVerticalDimensionBehaviour(dimensionBehaviour2);
        return z8;
    }

    public boolean directMeasureSetup(boolean z6) {
        if (this.mNeedBuildGraph) {
            Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                next.ensureWidgetRuns();
                next.measured = false;
                HorizontalWidgetRun horizontalWidgetRun = next.horizontalRun;
                horizontalWidgetRun.dimension.resolved = false;
                horizontalWidgetRun.resolved = false;
                horizontalWidgetRun.reset();
                VerticalWidgetRun verticalWidgetRun = next.verticalRun;
                verticalWidgetRun.dimension.resolved = false;
                verticalWidgetRun.resolved = false;
                verticalWidgetRun.reset();
            }
            this.container.ensureWidgetRuns();
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            constraintWidgetContainer.measured = false;
            HorizontalWidgetRun horizontalWidgetRun2 = constraintWidgetContainer.horizontalRun;
            horizontalWidgetRun2.dimension.resolved = false;
            horizontalWidgetRun2.resolved = false;
            horizontalWidgetRun2.reset();
            VerticalWidgetRun verticalWidgetRun2 = this.container.verticalRun;
            verticalWidgetRun2.dimension.resolved = false;
            verticalWidgetRun2.resolved = false;
            verticalWidgetRun2.reset();
            buildGraph();
        }
        if (basicMeasureWidgets(this.mContainer)) {
            return false;
        }
        this.container.setX(0);
        this.container.setY(0);
        this.container.horizontalRun.start.resolve(0);
        this.container.verticalRun.start.resolve(0);
        return true;
    }

    public boolean directMeasureWithOrientation(boolean z6, int i7) {
        boolean z7;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        boolean z8 = true;
        boolean z9 = z6 & true;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = this.container.getDimensionBehaviour(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = this.container.getDimensionBehaviour(1);
        int x6 = this.container.getX();
        int y6 = this.container.getY();
        if (z9 && (dimensionBehaviour2 == (dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || dimensionBehaviour3 == dimensionBehaviour)) {
            Iterator<WidgetRun> it = this.mRuns.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.orientation == i7 && !next.supportsWrapComputation()) {
                    z9 = false;
                    break;
                }
            }
            if (i7 == 0) {
                if (z9 && dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.container.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    ConstraintWidgetContainer constraintWidgetContainer = this.container;
                    constraintWidgetContainer.setWidth(computeWrap(constraintWidgetContainer, 0));
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.container;
                    constraintWidgetContainer2.horizontalRun.dimension.resolve(constraintWidgetContainer2.getWidth());
                }
            } else if (z9 && dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                this.container.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                ConstraintWidgetContainer constraintWidgetContainer3 = this.container;
                constraintWidgetContainer3.setHeight(computeWrap(constraintWidgetContainer3, 1));
                ConstraintWidgetContainer constraintWidgetContainer4 = this.container;
                constraintWidgetContainer4.verticalRun.dimension.resolve(constraintWidgetContainer4.getHeight());
            }
        }
        if (i7 == 0) {
            ConstraintWidgetContainer constraintWidgetContainer5 = this.container;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidgetContainer5.mListDimensionBehaviors;
            if (dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int width = constraintWidgetContainer5.getWidth() + x6;
                this.container.horizontalRun.end.resolve(width);
                this.container.horizontalRun.dimension.resolve(width - x6);
                z7 = true;
            }
            z7 = false;
        } else {
            ConstraintWidgetContainer constraintWidgetContainer6 = this.container;
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr2 = constraintWidgetContainer6.mListDimensionBehaviors;
            if (dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviourArr2[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                int height = constraintWidgetContainer6.getHeight() + y6;
                this.container.verticalRun.end.resolve(height);
                this.container.verticalRun.dimension.resolve(height - y6);
                z7 = true;
            }
            z7 = false;
        }
        measureWidgets();
        Iterator<WidgetRun> it2 = this.mRuns.iterator();
        while (it2.hasNext()) {
            WidgetRun next2 = it2.next();
            if (next2.orientation == i7 && (next2.widget != this.container || next2.resolved)) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it3 = this.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun next3 = it3.next();
            if (next3.orientation == i7 && (z7 || next3.widget != this.container)) {
                if (!next3.start.resolved || !next3.end.resolved || (!(next3 instanceof ChainRun) && !next3.dimension.resolved)) {
                    z8 = false;
                    break;
                }
            }
        }
        this.container.setHorizontalDimensionBehaviour(dimensionBehaviour2);
        this.container.setVerticalDimensionBehaviour(dimensionBehaviour3);
        return z8;
    }

    public void invalidateGraph() {
        this.mNeedBuildGraph = true;
    }

    public void invalidateMeasures() {
        this.mNeedRedoMeasures = true;
    }

    public void measureWidgets() {
        DimensionDependency dimensionDependency;
        Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (!next.measured) {
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = next.mListDimensionBehaviors;
                boolean z6 = false;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[1];
                int i7 = next.mMatchConstraintDefaultWidth;
                int i8 = next.mMatchConstraintDefaultHeight;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean z7 = dimensionBehaviour == dimensionBehaviour3 || (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i7 == 1);
                if (dimensionBehaviour2 == dimensionBehaviour3 || (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && i8 == 1)) {
                    z6 = true;
                }
                DimensionDependency dimensionDependency2 = next.horizontalRun.dimension;
                boolean z8 = dimensionDependency2.resolved;
                DimensionDependency dimensionDependency3 = next.verticalRun.dimension;
                boolean z9 = dimensionDependency3.resolved;
                if (z8 && z9) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.FIXED;
                    measure(next, dimensionBehaviour4, dimensionDependency2.value, dimensionBehaviour4, dimensionDependency3.value);
                    next.measured = true;
                } else if (z8 && z6) {
                    measure(next, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency2.value, dimensionBehaviour3, dimensionDependency3.value);
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        next.verticalRun.dimension.wrapValue = next.getHeight();
                    } else {
                        next.verticalRun.dimension.resolve(next.getHeight());
                        next.measured = true;
                    }
                } else if (z9 && z7) {
                    measure(next, dimensionBehaviour3, dimensionDependency2.value, ConstraintWidget.DimensionBehaviour.FIXED, dimensionDependency3.value);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        next.horizontalRun.dimension.wrapValue = next.getWidth();
                    } else {
                        next.horizontalRun.dimension.resolve(next.getWidth());
                        next.measured = true;
                    }
                }
                if (next.measured && (dimensionDependency = next.verticalRun.baselineDimension) != null) {
                    dimensionDependency.resolve(next.getBaselineDistance());
                }
            }
        }
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
    }

    public void buildGraph(ArrayList<WidgetRun> arrayList) {
        arrayList.clear();
        this.mContainer.horizontalRun.clear();
        this.mContainer.verticalRun.clear();
        arrayList.add(this.mContainer.horizontalRun);
        arrayList.add(this.mContainer.verticalRun);
        Iterator<ConstraintWidget> it = this.mContainer.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (next instanceof Guideline) {
                arrayList.add(new GuidelineReference(next));
            } else {
                if (next.isInHorizontalChain()) {
                    if (next.horizontalChainRun == null) {
                        next.horizontalChainRun = new ChainRun(next, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.horizontalChainRun);
                } else {
                    arrayList.add(next.horizontalRun);
                }
                if (next.isInVerticalChain()) {
                    if (next.verticalChainRun == null) {
                        next.verticalChainRun = new ChainRun(next, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.verticalChainRun);
                } else {
                    arrayList.add(next.verticalRun);
                }
                if (next instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(next));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<WidgetRun> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().clear();
        }
        Iterator<WidgetRun> it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.widget != this.mContainer) {
                next2.apply();
            }
        }
    }
}
