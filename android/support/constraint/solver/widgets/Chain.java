package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i7) {
        ChainHead[] chainHeadArr;
        int i8;
        int i9;
        if (i7 == 0) {
            i8 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i9 = 0;
        } else {
            int i10 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i8 = i10;
            i9 = 2;
        }
        for (int i11 = 0; i11 < i8; i11++) {
            ChainHead chainHead = chainHeadArr[i11];
            chainHead.define();
            if (arrayList == null || arrayList.contains(chainHead.mFirst)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i7, i9, chainHead);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e A[PHI: r14 r15
      0x003e: PHI (r14v3 boolean) = (r14v1 boolean), (r14v30 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]
      0x003e: PHI (r15v3 boolean) = (r15v1 boolean), (r15v37 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0040 A[PHI: r14 r15
      0x0040: PHI (r14v28 boolean) = (r14v1 boolean), (r14v30 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]
      0x0040: PHI (r15v35 boolean) = (r15v1 boolean), (r15v37 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x016a  */
    /* JADX WARN: Type inference failed for: r2v56, types: [android.support.constraint.solver.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [android.support.constraint.solver.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v33 */
    /* JADX WARN: Type inference failed for: r7v34 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r37, android.support.constraint.solver.LinearSystem r38, int r39, int r40, android.support.constraint.solver.widgets.ChainHead r41) {
        /*
            Method dump skipped, instructions count: 1323
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
