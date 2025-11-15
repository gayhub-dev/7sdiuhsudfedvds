package android.support.constraint.solver;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import org.fourthline.cling.model.ServiceReference;
import p009b.C0413b;

/* loaded from: classes.dex */
public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static final boolean DEBUG = false;
    private static final boolean DEBUG_CONSTRAINTS = false;
    public static final boolean FULL_DEBUG = false;
    public static final boolean MEASURE = false;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = false;
    private static int POOL_SIZE = 1000;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public static boolean USE_SYNONYMS = true;
    public static Metrics sMetrics;
    public final Cache mCache;
    private Row mGoal;
    public ArrayRow[] mRows;
    private Row mTempGoal;
    public boolean hasSimpleDefinition = false;
    public int mVariablesID = 0;
    private HashMap<String, SolverVariable> mVariables = null;
    private int TABLE_SIZE = 32;
    private int mMaxColumns = 32;
    public boolean graphOptimizer = false;
    public boolean newgraphOptimizer = false;
    private boolean[] mAlreadyTestedCandidates = new boolean[32];
    public int mNumColumns = 1;
    public int mNumRows = 0;
    private int mMaxRows = 32;
    private SolverVariable[] mPoolVariables = new SolverVariable[POOL_SIZE];
    private int mPoolVariablesCount = 0;

    public interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();

        void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z6);

        void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z6);

        void updateFromSystem(LinearSystem linearSystem);
    }

    public class ValuesRow extends ArrayRow {
        public ValuesRow(Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        this.mRows = null;
        this.mRows = new ArrayRow[32];
        releaseRows();
        Cache cache = new Cache();
        this.mCache = cache;
        this.mGoal = new PriorityGoalRow(cache);
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(cache);
        } else {
            this.mTempGoal = new ArrayRow(cache);
        }
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String str) {
        SolverVariable solverVariableAcquire = this.mCache.solverVariablePool.acquire();
        if (solverVariableAcquire == null) {
            solverVariableAcquire = new SolverVariable(type, str);
            solverVariableAcquire.setType(type, str);
        } else {
            solverVariableAcquire.reset();
            solverVariableAcquire.setType(type, str);
        }
        int i7 = this.mPoolVariablesCount;
        int i8 = POOL_SIZE;
        if (i7 >= i8) {
            int i9 = i8 * 2;
            POOL_SIZE = i9;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, i9);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i10 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i10 + 1;
        solverVariableArr[i10] = solverVariableAcquire;
        return solverVariableAcquire;
    }

    private void addError(ArrayRow arrayRow) {
        arrayRow.addError(this, 0);
    }

    private final void addRow(ArrayRow arrayRow) {
        int i7;
        if (SIMPLIFY_SYNONYMS && arrayRow.isSimpleDefinition) {
            arrayRow.variable.setFinalValue(this, arrayRow.constantValue);
        } else {
            ArrayRow[] arrayRowArr = this.mRows;
            int i8 = this.mNumRows;
            arrayRowArr[i8] = arrayRow;
            SolverVariable solverVariable = arrayRow.variable;
            solverVariable.definitionId = i8;
            this.mNumRows = i8 + 1;
            solverVariable.updateReferencesWithNewDefinition(this, arrayRow);
        }
        if (SIMPLIFY_SYNONYMS && this.hasSimpleDefinition) {
            int i9 = 0;
            while (i9 < this.mNumRows) {
                if (this.mRows[i9] == null) {
                    System.out.println("WTF");
                }
                ArrayRow[] arrayRowArr2 = this.mRows;
                if (arrayRowArr2[i9] != null && arrayRowArr2[i9].isSimpleDefinition) {
                    ArrayRow arrayRow2 = arrayRowArr2[i9];
                    arrayRow2.variable.setFinalValue(this, arrayRow2.constantValue);
                    if (OPTIMIZED_ENGINE) {
                        this.mCache.optimizedArrayRowPool.release(arrayRow2);
                    } else {
                        this.mCache.arrayRowPool.release(arrayRow2);
                    }
                    this.mRows[i9] = null;
                    int i10 = i9 + 1;
                    int i11 = i10;
                    while (true) {
                        i7 = this.mNumRows;
                        if (i10 >= i7) {
                            break;
                        }
                        ArrayRow[] arrayRowArr3 = this.mRows;
                        int i12 = i10 - 1;
                        arrayRowArr3[i12] = arrayRowArr3[i10];
                        if (arrayRowArr3[i12].variable.definitionId == i10) {
                            arrayRowArr3[i12].variable.definitionId = i12;
                        }
                        i11 = i10;
                        i10++;
                    }
                    if (i11 < i7) {
                        this.mRows[i11] = null;
                    }
                    this.mNumRows = i7 - 1;
                    i9--;
                }
                i9++;
            }
            this.hasSimpleDefinition = false;
        }
    }

    private void addSingleError(ArrayRow arrayRow, int i7) {
        addSingleError(arrayRow, i7, 0);
    }

    private void computeValues() {
        for (int i7 = 0; i7 < this.mNumRows; i7++) {
            ArrayRow arrayRow = this.mRows[i7];
            arrayRow.variable.computedValue = arrayRow.constantValue;
        }
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, float f7) {
        return linearSystem.createRow().createRowDimensionPercent(solverVariable, solverVariable2, f7);
    }

    private SolverVariable createVariable(String str, SolverVariable.Type type) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.variables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable solverVariableAcquireSolverVariable = acquireSolverVariable(type, null);
        solverVariableAcquireSolverVariable.setName(str);
        int i7 = this.mVariablesID + 1;
        this.mVariablesID = i7;
        this.mNumColumns++;
        solverVariableAcquireSolverVariable.f133id = i7;
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        this.mVariables.put(str, solverVariableAcquireSolverVariable);
        this.mCache.mIndexedVariables[this.mVariablesID] = solverVariableAcquireSolverVariable;
        return solverVariableAcquireSolverVariable;
    }

    private void displayRows() {
        displaySolverVariables();
        String strM88a = "";
        for (int i7 = 0; i7 < this.mNumRows; i7++) {
            StringBuilder sbM112a = C0413b.m112a(strM88a);
            sbM112a.append(this.mRows[i7]);
            strM88a = C0063n.m88a(sbM112a.toString(), "\n");
        }
        StringBuilder sbM112a2 = C0413b.m112a(strM88a);
        sbM112a2.append(this.mGoal);
        sbM112a2.append("\n");
        System.out.println(sbM112a2.toString());
    }

    private void displaySolverVariables() {
        StringBuilder sbM112a = C0413b.m112a("Display Rows (");
        sbM112a.append(this.mNumRows);
        sbM112a.append("x");
        System.out.println(C0084a.m96a(sbM112a, this.mNumColumns, ")\n"));
    }

    private int enforceBFS(Row row) {
        boolean z6;
        int i7 = 0;
        while (true) {
            if (i7 >= this.mNumRows) {
                z6 = false;
                break;
            }
            ArrayRow[] arrayRowArr = this.mRows;
            if (arrayRowArr[i7].variable.mType != SolverVariable.Type.UNRESTRICTED && arrayRowArr[i7].constantValue < 0.0f) {
                z6 = true;
                break;
            }
            i7++;
        }
        if (!z6) {
            return 0;
        }
        boolean z7 = false;
        int i8 = 0;
        while (!z7) {
            Metrics metrics = sMetrics;
            if (metrics != null) {
                metrics.bfs++;
            }
            i8++;
            float f7 = Float.MAX_VALUE;
            int i9 = -1;
            int i10 = -1;
            int i11 = 0;
            for (int i12 = 0; i12 < this.mNumRows; i12++) {
                ArrayRow arrayRow = this.mRows[i12];
                if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow.isSimpleDefinition && arrayRow.constantValue < 0.0f) {
                    int i13 = 9;
                    if (SKIP_COLUMNS) {
                        int currentSize = arrayRow.variables.getCurrentSize();
                        int i14 = 0;
                        while (i14 < currentSize) {
                            SolverVariable variable = arrayRow.variables.getVariable(i14);
                            float f8 = arrayRow.variables.get(variable);
                            if (f8 > 0.0f) {
                                int i15 = 0;
                                while (i15 < i13) {
                                    float f9 = variable.strengthVector[i15] / f8;
                                    if ((f9 < f7 && i15 == i11) || i15 > i11) {
                                        i10 = variable.f133id;
                                        i11 = i15;
                                        i9 = i12;
                                        f7 = f9;
                                    }
                                    i15++;
                                    i13 = 9;
                                }
                            }
                            i14++;
                            i13 = 9;
                        }
                    } else {
                        for (int i16 = 1; i16 < this.mNumColumns; i16++) {
                            SolverVariable solverVariable = this.mCache.mIndexedVariables[i16];
                            float f10 = arrayRow.variables.get(solverVariable);
                            if (f10 > 0.0f) {
                                for (int i17 = 0; i17 < 9; i17++) {
                                    float f11 = solverVariable.strengthVector[i17] / f10;
                                    if ((f11 < f7 && i17 == i11) || i17 > i11) {
                                        i10 = i16;
                                        i11 = i17;
                                        i9 = i12;
                                        f7 = f11;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (i9 != -1) {
                ArrayRow arrayRow2 = this.mRows[i9];
                arrayRow2.variable.definitionId = -1;
                Metrics metrics2 = sMetrics;
                if (metrics2 != null) {
                    metrics2.pivots++;
                }
                arrayRow2.pivot(this.mCache.mIndexedVariables[i10]);
                SolverVariable solverVariable2 = arrayRow2.variable;
                solverVariable2.definitionId = i9;
                solverVariable2.updateReferencesWithNewDefinition(this, arrayRow2);
            } else {
                z7 = true;
            }
            if (i8 > this.mNumColumns / 2) {
                z7 = true;
            }
        }
        return i8;
    }

    private String getDisplaySize(int i7) {
        int i8 = i7 * 4;
        int i9 = i8 / 1024;
        int i10 = i9 / 1024;
        if (i10 > 0) {
            return "" + i10 + " Mb";
        }
        if (i9 > 0) {
            return "" + i9 + " Kb";
        }
        return "" + i8 + " bytes";
    }

    private String getDisplayStrength(int i7) {
        return i7 == 1 ? "LOW" : i7 == 2 ? "MEDIUM" : i7 == 3 ? "HIGH" : i7 == 4 ? "HIGHEST" : i7 == 5 ? "EQUALITY" : i7 == 8 ? "FIXED" : i7 == 6 ? "BARRIER" : "NONE";
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        int i7 = this.TABLE_SIZE * 2;
        this.TABLE_SIZE = i7;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, i7);
        Cache cache = this.mCache;
        cache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(cache.mIndexedVariables, this.TABLE_SIZE);
        int i8 = this.TABLE_SIZE;
        this.mAlreadyTestedCandidates = new boolean[i8];
        this.mMaxColumns = i8;
        this.mMaxRows = i8;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.tableSizeIncrease++;
            metrics.maxTableSize = Math.max(metrics.maxTableSize, i8);
            Metrics metrics2 = sMetrics;
            metrics2.lastTableSize = metrics2.maxTableSize;
        }
    }

    private final int optimize(Row row, boolean z6) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.optimize++;
        }
        for (int i7 = 0; i7 < this.mNumColumns; i7++) {
            this.mAlreadyTestedCandidates[i7] = false;
        }
        boolean z7 = false;
        int i8 = 0;
        while (!z7) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.iterations++;
            }
            i8++;
            if (i8 >= this.mNumColumns * 2) {
                return i8;
            }
            if (row.getKey() != null) {
                this.mAlreadyTestedCandidates[row.getKey().f133id] = true;
            }
            SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                boolean[] zArr = this.mAlreadyTestedCandidates;
                int i9 = pivotCandidate.f133id;
                if (zArr[i9]) {
                    return i8;
                }
                zArr[i9] = true;
            }
            if (pivotCandidate != null) {
                float f7 = Float.MAX_VALUE;
                int i10 = -1;
                for (int i11 = 0; i11 < this.mNumRows; i11++) {
                    ArrayRow arrayRow = this.mRows[i11];
                    if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow.isSimpleDefinition && arrayRow.hasVariable(pivotCandidate)) {
                        float f8 = arrayRow.variables.get(pivotCandidate);
                        if (f8 < 0.0f) {
                            float f9 = (-arrayRow.constantValue) / f8;
                            if (f9 < f7) {
                                i10 = i11;
                                f7 = f9;
                            }
                        }
                    }
                }
                if (i10 > -1) {
                    ArrayRow arrayRow2 = this.mRows[i10];
                    arrayRow2.variable.definitionId = -1;
                    Metrics metrics3 = sMetrics;
                    if (metrics3 != null) {
                        metrics3.pivots++;
                    }
                    arrayRow2.pivot(pivotCandidate);
                    SolverVariable solverVariable = arrayRow2.variable;
                    solverVariable.definitionId = i10;
                    solverVariable.updateReferencesWithNewDefinition(this, arrayRow2);
                }
            } else {
                z7 = true;
            }
        }
        return i8;
    }

    private void releaseRows() {
        int i7 = 0;
        if (OPTIMIZED_ENGINE) {
            while (i7 < this.mNumRows) {
                ArrayRow arrayRow = this.mRows[i7];
                if (arrayRow != null) {
                    this.mCache.optimizedArrayRowPool.release(arrayRow);
                }
                this.mRows[i7] = null;
                i7++;
            }
            return;
        }
        while (i7 < this.mNumRows) {
            ArrayRow arrayRow2 = this.mRows[i7];
            if (arrayRow2 != null) {
                this.mCache.arrayRowPool.release(arrayRow2);
            }
            this.mRows[i7] = null;
            i7++;
        }
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f7, int i7) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable solverVariableCreateObjectVariable = createObjectVariable(constraintWidget.getAnchor(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable solverVariableCreateObjectVariable2 = createObjectVariable(constraintWidget.getAnchor(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable solverVariableCreateObjectVariable3 = createObjectVariable(constraintWidget.getAnchor(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable solverVariableCreateObjectVariable4 = createObjectVariable(constraintWidget.getAnchor(type4));
        SolverVariable solverVariableCreateObjectVariable5 = createObjectVariable(constraintWidget2.getAnchor(type));
        SolverVariable solverVariableCreateObjectVariable6 = createObjectVariable(constraintWidget2.getAnchor(type2));
        SolverVariable solverVariableCreateObjectVariable7 = createObjectVariable(constraintWidget2.getAnchor(type3));
        SolverVariable solverVariableCreateObjectVariable8 = createObjectVariable(constraintWidget2.getAnchor(type4));
        ArrayRow arrayRowCreateRow = createRow();
        double d7 = f7;
        double d8 = i7;
        arrayRowCreateRow.createRowWithAngle(solverVariableCreateObjectVariable2, solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable6, solverVariableCreateObjectVariable8, (float) (Math.sin(d7) * d8));
        addConstraint(arrayRowCreateRow);
        ArrayRow arrayRowCreateRow2 = createRow();
        arrayRowCreateRow2.createRowWithAngle(solverVariableCreateObjectVariable, solverVariableCreateObjectVariable3, solverVariableCreateObjectVariable5, solverVariableCreateObjectVariable7, (float) (Math.cos(d7) * d8));
        addConstraint(arrayRowCreateRow2);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i7, float f7, SolverVariable solverVariable3, SolverVariable solverVariable4, int i8, int i9) {
        ArrayRow arrayRowCreateRow = createRow();
        arrayRowCreateRow.createRowCentering(solverVariable, solverVariable2, i7, f7, solverVariable3, solverVariable4, i8);
        if (i9 != 8) {
            arrayRowCreateRow.addError(this, i9);
        }
        addConstraint(arrayRowCreateRow);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addConstraint(android.support.constraint.solver.ArrayRow r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L3
            return
        L3:
            android.support.constraint.solver.Metrics r0 = android.support.constraint.solver.LinearSystem.sMetrics
            r1 = 1
            if (r0 == 0) goto L17
            long r3 = r0.constraints
            long r3 = r3 + r1
            r0.constraints = r3
            boolean r3 = r8.isSimpleDefinition
            if (r3 == 0) goto L17
            long r3 = r0.simpleconstraints
            long r3 = r3 + r1
            r0.simpleconstraints = r3
        L17:
            int r0 = r7.mNumRows
            r3 = 1
            int r0 = r0 + r3
            int r4 = r7.mMaxRows
            if (r0 >= r4) goto L26
            int r0 = r7.mNumColumns
            int r0 = r0 + r3
            int r4 = r7.mMaxColumns
            if (r0 < r4) goto L29
        L26:
            r7.increaseTableSize()
        L29:
            r0 = 0
            boolean r4 = r8.isSimpleDefinition
            if (r4 != 0) goto La1
            r8.updateFromSystem(r7)
            boolean r4 = r8.isEmpty()
            if (r4 == 0) goto L38
            return
        L38:
            r8.ensurePositiveConstant()
            boolean r4 = r8.chooseSubject(r7)
            if (r4 == 0) goto L98
            android.support.constraint.solver.SolverVariable r4 = r7.createExtraVariable()
            r8.variable = r4
            int r5 = r7.mNumRows
            r7.addRow(r8)
            int r6 = r7.mNumRows
            int r5 = r5 + r3
            if (r6 != r5) goto L98
            android.support.constraint.solver.LinearSystem$Row r0 = r7.mTempGoal
            r0.initFromRow(r8)
            android.support.constraint.solver.LinearSystem$Row r0 = r7.mTempGoal
            r7.optimize(r0, r3)
            int r0 = r4.definitionId
            r5 = -1
            if (r0 != r5) goto L99
            android.support.constraint.solver.SolverVariable r0 = r8.variable
            if (r0 != r4) goto L76
            android.support.constraint.solver.SolverVariable r0 = r8.pickPivot(r4)
            if (r0 == 0) goto L76
            android.support.constraint.solver.Metrics r4 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r4 == 0) goto L73
            long r5 = r4.pivots
            long r5 = r5 + r1
            r4.pivots = r5
        L73:
            r8.pivot(r0)
        L76:
            boolean r0 = r8.isSimpleDefinition
            if (r0 != 0) goto L7f
            android.support.constraint.solver.SolverVariable r0 = r8.variable
            r0.updateReferencesWithNewDefinition(r7, r8)
        L7f:
            boolean r0 = android.support.constraint.solver.LinearSystem.OPTIMIZED_ENGINE
            if (r0 == 0) goto L8b
            android.support.constraint.solver.Cache r0 = r7.mCache
            android.support.constraint.solver.Pools$Pool<android.support.constraint.solver.ArrayRow> r0 = r0.optimizedArrayRowPool
            r0.release(r8)
            goto L92
        L8b:
            android.support.constraint.solver.Cache r0 = r7.mCache
            android.support.constraint.solver.Pools$Pool<android.support.constraint.solver.ArrayRow> r0 = r0.arrayRowPool
            r0.release(r8)
        L92:
            int r0 = r7.mNumRows
            int r0 = r0 - r3
            r7.mNumRows = r0
            goto L99
        L98:
            r3 = 0
        L99:
            boolean r0 = r8.hasKeyVariable()
            if (r0 != 0) goto La0
            return
        La0:
            r0 = r3
        La1:
            if (r0 != 0) goto La6
            r7.addRow(r8)
        La6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.LinearSystem.addConstraint(android.support.constraint.solver.ArrayRow):void");
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i7, int i8) {
        if (USE_BASIC_SYNONYMS && i8 == 8 && solverVariable2.isFinalValue && solverVariable.definitionId == -1) {
            solverVariable.setFinalValue(this, solverVariable2.computedValue + i7);
            return null;
        }
        ArrayRow arrayRowCreateRow = createRow();
        arrayRowCreateRow.createRowEquals(solverVariable, solverVariable2, i7);
        if (i8 != 8) {
            arrayRowCreateRow.addError(this, i8);
        }
        addConstraint(arrayRowCreateRow);
        return arrayRowCreateRow;
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i7, boolean z6) {
        ArrayRow arrayRowCreateRow = createRow();
        SolverVariable solverVariableCreateSlackVariable = createSlackVariable();
        solverVariableCreateSlackVariable.strength = 0;
        arrayRowCreateRow.createRowGreaterThan(solverVariable, solverVariable2, solverVariableCreateSlackVariable, i7);
        addConstraint(arrayRowCreateRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i7, int i8) {
        ArrayRow arrayRowCreateRow = createRow();
        SolverVariable solverVariableCreateSlackVariable = createSlackVariable();
        solverVariableCreateSlackVariable.strength = 0;
        arrayRowCreateRow.createRowGreaterThan(solverVariable, solverVariable2, solverVariableCreateSlackVariable, i7);
        if (i8 != 8) {
            addSingleError(arrayRowCreateRow, (int) (arrayRowCreateRow.variables.get(solverVariableCreateSlackVariable) * (-1.0f)), i8);
        }
        addConstraint(arrayRowCreateRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i7, boolean z6) {
        ArrayRow arrayRowCreateRow = createRow();
        SolverVariable solverVariableCreateSlackVariable = createSlackVariable();
        solverVariableCreateSlackVariable.strength = 0;
        arrayRowCreateRow.createRowLowerThan(solverVariable, solverVariable2, solverVariableCreateSlackVariable, i7);
        addConstraint(arrayRowCreateRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i7, int i8) {
        ArrayRow arrayRowCreateRow = createRow();
        SolverVariable solverVariableCreateSlackVariable = createSlackVariable();
        solverVariableCreateSlackVariable.strength = 0;
        arrayRowCreateRow.createRowLowerThan(solverVariable, solverVariable2, solverVariableCreateSlackVariable, i7);
        if (i8 != 8) {
            addSingleError(arrayRowCreateRow, (int) (arrayRowCreateRow.variables.get(solverVariableCreateSlackVariable) * (-1.0f)), i8);
        }
        addConstraint(arrayRowCreateRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f7, int i7) {
        ArrayRow arrayRowCreateRow = createRow();
        arrayRowCreateRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f7);
        if (i7 != 8) {
            arrayRowCreateRow.addError(this, i7);
        }
        addConstraint(arrayRowCreateRow);
    }

    public void addSynonym(SolverVariable solverVariable, SolverVariable solverVariable2, int i7) {
        if (solverVariable.definitionId != -1 || i7 != 0) {
            addEquality(solverVariable, solverVariable2, i7, 8);
            return;
        }
        if (solverVariable2.isSynonym) {
            solverVariable2 = this.mCache.mIndexedVariables[solverVariable2.synonym];
        }
        if (solverVariable.isSynonym) {
            SolverVariable solverVariable3 = this.mCache.mIndexedVariables[solverVariable.synonym];
        } else {
            solverVariable.setSynonym(this, solverVariable2, 0.0f);
        }
    }

    public final void cleanupRows() {
        int i7;
        int i8 = 0;
        while (i8 < this.mNumRows) {
            ArrayRow arrayRow = this.mRows[i8];
            if (arrayRow.variables.getCurrentSize() == 0) {
                arrayRow.isSimpleDefinition = true;
            }
            if (arrayRow.isSimpleDefinition) {
                SolverVariable solverVariable = arrayRow.variable;
                solverVariable.computedValue = arrayRow.constantValue;
                solverVariable.removeFromRow(arrayRow);
                int i9 = i8;
                while (true) {
                    i7 = this.mNumRows;
                    if (i9 >= i7 - 1) {
                        break;
                    }
                    ArrayRow[] arrayRowArr = this.mRows;
                    int i10 = i9 + 1;
                    arrayRowArr[i9] = arrayRowArr[i10];
                    i9 = i10;
                }
                this.mRows[i7 - 1] = null;
                this.mNumRows = i7 - 1;
                i8--;
                if (OPTIMIZED_ENGINE) {
                    this.mCache.optimizedArrayRowPool.release(arrayRow);
                } else {
                    this.mCache.arrayRowPool.release(arrayRow);
                }
            }
            i8++;
        }
    }

    public SolverVariable createErrorVariable(int i7, String str) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.errors++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable solverVariableAcquireSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, str);
        int i8 = this.mVariablesID + 1;
        this.mVariablesID = i8;
        this.mNumColumns++;
        solverVariableAcquireSolverVariable.f133id = i8;
        solverVariableAcquireSolverVariable.strength = i7;
        this.mCache.mIndexedVariables[i8] = solverVariableAcquireSolverVariable;
        this.mGoal.addError(solverVariableAcquireSolverVariable);
        return solverVariableAcquireSolverVariable;
    }

    public SolverVariable createExtraVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.extravariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable solverVariableAcquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int i7 = this.mVariablesID + 1;
        this.mVariablesID = i7;
        this.mNumColumns++;
        solverVariableAcquireSolverVariable.f133id = i7;
        this.mCache.mIndexedVariables[i7] = solverVariableAcquireSolverVariable;
        return solverVariableAcquireSolverVariable;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.mCache);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            int i7 = solverVariable.f133id;
            if (i7 == -1 || i7 > this.mVariablesID || this.mCache.mIndexedVariables[i7] == null) {
                if (i7 != -1) {
                    solverVariable.reset();
                }
                int i8 = this.mVariablesID + 1;
                this.mVariablesID = i8;
                this.mNumColumns++;
                solverVariable.f133id = i8;
                solverVariable.mType = SolverVariable.Type.UNRESTRICTED;
                this.mCache.mIndexedVariables[i8] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow arrayRowAcquire;
        if (OPTIMIZED_ENGINE) {
            arrayRowAcquire = this.mCache.optimizedArrayRowPool.acquire();
            if (arrayRowAcquire == null) {
                arrayRowAcquire = new ValuesRow(this.mCache);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            } else {
                arrayRowAcquire.reset();
            }
        } else {
            arrayRowAcquire = this.mCache.arrayRowPool.acquire();
            if (arrayRowAcquire == null) {
                arrayRowAcquire = new ArrayRow(this.mCache);
                ARRAY_ROW_CREATION++;
            } else {
                arrayRowAcquire.reset();
            }
        }
        SolverVariable.increaseErrorId();
        return arrayRowAcquire;
    }

    public SolverVariable createSlackVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.slackvariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable solverVariableAcquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int i7 = this.mVariablesID + 1;
        this.mVariablesID = i7;
        this.mNumColumns++;
        solverVariableAcquireSolverVariable.f133id = i7;
        this.mCache.mIndexedVariables[i7] = solverVariableAcquireSolverVariable;
        return solverVariableAcquireSolverVariable;
    }

    public void displayReadableRows() {
        displaySolverVariables();
        String strM96a = C0084a.m96a(C0413b.m112a(" num vars "), this.mVariablesID, "\n");
        for (int i7 = 0; i7 < this.mVariablesID + 1; i7++) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[i7];
            if (solverVariable != null && solverVariable.isFinalValue) {
                strM96a = strM96a + " $[" + i7 + "] => " + solverVariable + " = " + solverVariable.computedValue + "\n";
            }
        }
        String strM88a = C0063n.m88a(strM96a, "\n");
        for (int i8 = 0; i8 < this.mVariablesID + 1; i8++) {
            SolverVariable[] solverVariableArr = this.mCache.mIndexedVariables;
            SolverVariable solverVariable2 = solverVariableArr[i8];
            if (solverVariable2 != null && solverVariable2.isSynonym) {
                strM88a = strM88a + " ~[" + i8 + "] => " + solverVariable2 + " = " + solverVariableArr[solverVariable2.synonym] + " + " + solverVariable2.synonymDelta + "\n";
            }
        }
        String strM88a2 = C0063n.m88a(strM88a, "\n\n #  ");
        for (int i9 = 0; i9 < this.mNumRows; i9++) {
            StringBuilder sbM112a = C0413b.m112a(strM88a2);
            sbM112a.append(this.mRows[i9].toReadableString());
            strM88a2 = C0063n.m88a(sbM112a.toString(), "\n #  ");
        }
        if (this.mGoal != null) {
            StringBuilder sbM94a = C0080b.m94a(strM88a2, "Goal: ");
            sbM94a.append(this.mGoal);
            sbM94a.append("\n");
            strM88a2 = sbM94a.toString();
        }
        System.out.println(strM88a2);
    }

    public void displaySystemInformations() {
        int iSizeInBytes = 0;
        for (int i7 = 0; i7 < this.TABLE_SIZE; i7++) {
            ArrayRow[] arrayRowArr = this.mRows;
            if (arrayRowArr[i7] != null) {
                iSizeInBytes = arrayRowArr[i7].sizeInBytes() + iSizeInBytes;
            }
        }
        int iSizeInBytes2 = 0;
        for (int i8 = 0; i8 < this.mNumRows; i8++) {
            ArrayRow[] arrayRowArr2 = this.mRows;
            if (arrayRowArr2[i8] != null) {
                iSizeInBytes2 = arrayRowArr2[i8].sizeInBytes() + iSizeInBytes2;
            }
        }
        PrintStream printStream = System.out;
        StringBuilder sbM112a = C0413b.m112a("Linear System -> Table size: ");
        sbM112a.append(this.TABLE_SIZE);
        sbM112a.append(" (");
        int i9 = this.TABLE_SIZE;
        sbM112a.append(getDisplaySize(i9 * i9));
        sbM112a.append(") -- row sizes: ");
        sbM112a.append(getDisplaySize(iSizeInBytes));
        sbM112a.append(", actual size: ");
        sbM112a.append(getDisplaySize(iSizeInBytes2));
        sbM112a.append(" rows: ");
        sbM112a.append(this.mNumRows);
        sbM112a.append(ServiceReference.DELIMITER);
        sbM112a.append(this.mMaxRows);
        sbM112a.append(" cols: ");
        sbM112a.append(this.mNumColumns);
        sbM112a.append(ServiceReference.DELIMITER);
        sbM112a.append(this.mMaxColumns);
        sbM112a.append(" ");
        sbM112a.append(0);
        sbM112a.append(" occupied cells, ");
        sbM112a.append(getDisplaySize(0));
        printStream.println(sbM112a.toString());
    }

    public void displayVariablesReadableRows() {
        displaySolverVariables();
        String strM88a = "";
        for (int i7 = 0; i7 < this.mNumRows; i7++) {
            if (this.mRows[i7].variable.mType == SolverVariable.Type.UNRESTRICTED) {
                StringBuilder sbM112a = C0413b.m112a(strM88a);
                sbM112a.append(this.mRows[i7].toReadableString());
                strM88a = C0063n.m88a(sbM112a.toString(), "\n");
            }
        }
        StringBuilder sbM112a2 = C0413b.m112a(strM88a);
        sbM112a2.append(this.mGoal);
        sbM112a2.append("\n");
        System.out.println(sbM112a2.toString());
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public Cache getCache() {
        return this.mCache;
    }

    public Row getGoal() {
        return this.mGoal;
    }

    public int getMemoryUsed() {
        int iSizeInBytes = 0;
        for (int i7 = 0; i7 < this.mNumRows; i7++) {
            ArrayRow[] arrayRowArr = this.mRows;
            if (arrayRowArr[i7] != null) {
                iSizeInBytes = arrayRowArr[i7].sizeInBytes() + iSizeInBytes;
            }
        }
        return iSizeInBytes;
    }

    public int getNumEquations() {
        return this.mNumRows;
    }

    public int getNumVariables() {
        return this.mVariablesID;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    public ArrayRow getRow(int i7) {
        return this.mRows[i7];
    }

    public float getValueFor(String str) {
        SolverVariable variable = getVariable(str, SolverVariable.Type.UNRESTRICTED);
        if (variable == null) {
            return 0.0f;
        }
        return variable.computedValue;
    }

    public SolverVariable getVariable(String str, SolverVariable.Type type) {
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        SolverVariable solverVariable = this.mVariables.get(str);
        return solverVariable == null ? createVariable(str, type) : solverVariable;
    }

    public void minimize() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimize++;
        }
        if (this.mGoal.isEmpty()) {
            computeValues();
            return;
        }
        if (!this.graphOptimizer && !this.newgraphOptimizer) {
            minimizeGoal(this.mGoal);
            return;
        }
        Metrics metrics2 = sMetrics;
        if (metrics2 != null) {
            metrics2.graphOptimizer++;
        }
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            if (i7 >= this.mNumRows) {
                z6 = true;
                break;
            } else if (!this.mRows[i7].isSimpleDefinition) {
                break;
            } else {
                i7++;
            }
        }
        if (!z6) {
            minimizeGoal(this.mGoal);
            return;
        }
        Metrics metrics3 = sMetrics;
        if (metrics3 != null) {
            metrics3.fullySolved++;
        }
        computeValues();
    }

    public void minimizeGoal(Row row) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimizeGoal++;
            metrics.maxVariables = Math.max(metrics.maxVariables, this.mNumColumns);
            Metrics metrics2 = sMetrics;
            metrics2.maxRows = Math.max(metrics2.maxRows, this.mNumRows);
        }
        enforceBFS(row);
        optimize(row, false);
        computeValues();
    }

    public void removeRow(ArrayRow arrayRow) {
        SolverVariable solverVariable;
        int i7;
        if (!arrayRow.isSimpleDefinition || (solverVariable = arrayRow.variable) == null) {
            return;
        }
        int i8 = solverVariable.definitionId;
        if (i8 != -1) {
            while (true) {
                i7 = this.mNumRows;
                if (i8 >= i7 - 1) {
                    break;
                }
                ArrayRow[] arrayRowArr = this.mRows;
                int i9 = i8 + 1;
                SolverVariable solverVariable2 = arrayRowArr[i9].variable;
                if (solverVariable2.definitionId == i9) {
                    solverVariable2.definitionId = i8;
                }
                arrayRowArr[i8] = arrayRowArr[i9];
                i8 = i9;
            }
            this.mNumRows = i7 - 1;
        }
        SolverVariable solverVariable3 = arrayRow.variable;
        if (!solverVariable3.isFinalValue) {
            solverVariable3.setFinalValue(this, arrayRow.constantValue);
        }
        if (OPTIMIZED_ENGINE) {
            this.mCache.optimizedArrayRowPool.release(arrayRow);
        } else {
            this.mCache.arrayRowPool.release(arrayRow);
        }
    }

    public void reset() {
        Cache cache;
        int i7 = 0;
        while (true) {
            cache = this.mCache;
            SolverVariable[] solverVariableArr = cache.mIndexedVariables;
            if (i7 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i7];
            if (solverVariable != null) {
                solverVariable.reset();
            }
            i7++;
        }
        cache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        HashMap<String, SolverVariable> map = this.mVariables;
        if (map != null) {
            map.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (int i8 = 0; i8 < this.mNumRows; i8++) {
            ArrayRow[] arrayRowArr = this.mRows;
            if (arrayRowArr[i8] != null) {
                arrayRowArr[i8].used = false;
            }
        }
        releaseRows();
        this.mNumRows = 0;
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this.mCache);
        } else {
            this.mTempGoal = new ArrayRow(this.mCache);
        }
    }

    public void addSingleError(ArrayRow arrayRow, int i7, int i8) {
        arrayRow.addSingleError(createErrorVariable(i8, null), i7);
    }

    public void addEquality(SolverVariable solverVariable, int i7) {
        if (USE_BASIC_SYNONYMS && solverVariable.definitionId == -1) {
            float f7 = i7;
            solverVariable.setFinalValue(this, f7);
            for (int i8 = 0; i8 < this.mVariablesID + 1; i8++) {
                SolverVariable solverVariable2 = this.mCache.mIndexedVariables[i8];
                if (solverVariable2 != null && solverVariable2.isSynonym && solverVariable2.synonym == solverVariable.f133id) {
                    solverVariable2.setFinalValue(this, solverVariable2.synonymDelta + f7);
                }
            }
            return;
        }
        int i9 = solverVariable.definitionId;
        if (i9 != -1) {
            ArrayRow arrayRow = this.mRows[i9];
            if (arrayRow.isSimpleDefinition) {
                arrayRow.constantValue = i7;
                return;
            }
            if (arrayRow.variables.getCurrentSize() == 0) {
                arrayRow.isSimpleDefinition = true;
                arrayRow.constantValue = i7;
                return;
            } else {
                ArrayRow arrayRowCreateRow = createRow();
                arrayRowCreateRow.createRowEquals(solverVariable, i7);
                addConstraint(arrayRowCreateRow);
                return;
            }
        }
        ArrayRow arrayRowCreateRow2 = createRow();
        arrayRowCreateRow2.createRowDefinition(solverVariable, i7);
        addConstraint(arrayRowCreateRow2);
    }
}
