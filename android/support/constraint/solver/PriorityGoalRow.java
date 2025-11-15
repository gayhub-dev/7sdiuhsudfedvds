package android.support.constraint.solver;

import android.support.constraint.motion.C0080b;
import android.support.constraint.solver.ArrayRow;
import java.util.Arrays;
import java.util.Comparator;
import p009b.C0413b;

/* loaded from: classes.dex */
public class PriorityGoalRow extends ArrayRow {
    private static final boolean DEBUG = false;
    public static final int NOT_FOUND = -1;
    private static final float epsilon = 1.0E-4f;
    private int TABLE_SIZE;
    public GoalVariableAccessor accessor;
    private SolverVariable[] arrayGoals;
    public Cache mCache;
    private int numGoals;
    private SolverVariable[] sortArray;

    public class GoalVariableAccessor implements Comparable {
        public PriorityGoalRow row;
        public SolverVariable variable;

        public GoalVariableAccessor(PriorityGoalRow priorityGoalRow) {
            this.row = priorityGoalRow;
        }

        public void add(SolverVariable solverVariable) {
            for (int i7 = 0; i7 < 9; i7++) {
                float[] fArr = this.variable.goalStrengthVector;
                fArr[i7] = fArr[i7] + solverVariable.goalStrengthVector[i7];
                if (Math.abs(fArr[i7]) < PriorityGoalRow.epsilon) {
                    this.variable.goalStrengthVector[i7] = 0.0f;
                }
            }
        }

        public boolean addToGoal(SolverVariable solverVariable, float f7) {
            boolean z6 = true;
            if (!this.variable.inGoal) {
                for (int i7 = 0; i7 < 9; i7++) {
                    float f8 = solverVariable.goalStrengthVector[i7];
                    if (f8 != 0.0f) {
                        float f9 = f8 * f7;
                        if (Math.abs(f9) < PriorityGoalRow.epsilon) {
                            f9 = 0.0f;
                        }
                        this.variable.goalStrengthVector[i7] = f9;
                    } else {
                        this.variable.goalStrengthVector[i7] = 0.0f;
                    }
                }
                return true;
            }
            for (int i8 = 0; i8 < 9; i8++) {
                float[] fArr = this.variable.goalStrengthVector;
                fArr[i8] = (solverVariable.goalStrengthVector[i8] * f7) + fArr[i8];
                if (Math.abs(fArr[i8]) < PriorityGoalRow.epsilon) {
                    this.variable.goalStrengthVector[i8] = 0.0f;
                } else {
                    z6 = false;
                }
            }
            if (z6) {
                PriorityGoalRow.this.removeGoal(this.variable);
            }
            return false;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.variable.f133id - ((SolverVariable) obj).f133id;
        }

        public void init(SolverVariable solverVariable) {
            this.variable = solverVariable;
        }

        public final boolean isNegative() {
            for (int i7 = 8; i7 >= 0; i7--) {
                float f7 = this.variable.goalStrengthVector[i7];
                if (f7 > 0.0f) {
                    return false;
                }
                if (f7 < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isNull() {
            for (int i7 = 0; i7 < 9; i7++) {
                if (this.variable.goalStrengthVector[i7] != 0.0f) {
                    return false;
                }
            }
            return true;
        }

        public final boolean isSmallerThan(SolverVariable solverVariable) {
            int i7 = 8;
            while (true) {
                if (i7 < 0) {
                    break;
                }
                float f7 = solverVariable.goalStrengthVector[i7];
                float f8 = this.variable.goalStrengthVector[i7];
                if (f8 == f7) {
                    i7--;
                } else if (f8 < f7) {
                    return true;
                }
            }
            return false;
        }

        public void reset() {
            Arrays.fill(this.variable.goalStrengthVector, 0.0f);
        }

        public String toString() {
            String string = "[ ";
            if (this.variable != null) {
                for (int i7 = 0; i7 < 9; i7++) {
                    StringBuilder sbM112a = C0413b.m112a(string);
                    sbM112a.append(this.variable.goalStrengthVector[i7]);
                    sbM112a.append(" ");
                    string = sbM112a.toString();
                }
            }
            StringBuilder sbM94a = C0080b.m94a(string, "] ");
            sbM94a.append(this.variable);
            return sbM94a.toString();
        }
    }

    public PriorityGoalRow(Cache cache) {
        super(cache);
        this.TABLE_SIZE = 128;
        this.arrayGoals = new SolverVariable[128];
        this.sortArray = new SolverVariable[128];
        this.numGoals = 0;
        this.accessor = new GoalVariableAccessor(this);
        this.mCache = cache;
    }

    private final void addToGoal(SolverVariable solverVariable) {
        int i7;
        int i8 = this.numGoals + 1;
        SolverVariable[] solverVariableArr = this.arrayGoals;
        if (i8 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.arrayGoals = solverVariableArr2;
            this.sortArray = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.arrayGoals;
        int i9 = this.numGoals;
        solverVariableArr3[i9] = solverVariable;
        int i10 = i9 + 1;
        this.numGoals = i10;
        if (i10 > 1 && solverVariableArr3[i10 - 1].f133id > solverVariable.f133id) {
            int i11 = 0;
            while (true) {
                i7 = this.numGoals;
                if (i11 >= i7) {
                    break;
                }
                this.sortArray[i11] = this.arrayGoals[i11];
                i11++;
            }
            Arrays.sort(this.sortArray, 0, i7, new Comparator<SolverVariable>() { // from class: android.support.constraint.solver.PriorityGoalRow.1
                @Override // java.util.Comparator
                public int compare(SolverVariable solverVariable2, SolverVariable solverVariable3) {
                    return solverVariable2.f133id - solverVariable3.f133id;
                }
            });
            for (int i12 = 0; i12 < this.numGoals; i12++) {
                this.arrayGoals[i12] = this.sortArray[i12];
            }
        }
        solverVariable.inGoal = true;
        solverVariable.addToRow(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeGoal(SolverVariable solverVariable) {
        int i7 = 0;
        while (i7 < this.numGoals) {
            if (this.arrayGoals[i7] == solverVariable) {
                while (true) {
                    int i8 = this.numGoals;
                    if (i7 >= i8 - 1) {
                        this.numGoals = i8 - 1;
                        solverVariable.inGoal = false;
                        return;
                    } else {
                        SolverVariable[] solverVariableArr = this.arrayGoals;
                        int i9 = i7 + 1;
                        solverVariableArr[i7] = solverVariableArr[i9];
                        i7 = i9;
                    }
                }
            } else {
                i7++;
            }
        }
    }

    @Override // android.support.constraint.solver.ArrayRow, android.support.constraint.solver.LinearSystem.Row
    public void addError(SolverVariable solverVariable) {
        this.accessor.init(solverVariable);
        this.accessor.reset();
        solverVariable.goalStrengthVector[solverVariable.strength] = 1.0f;
        addToGoal(solverVariable);
    }

    @Override // android.support.constraint.solver.ArrayRow, android.support.constraint.solver.LinearSystem.Row
    public void clear() {
        this.numGoals = 0;
        this.constantValue = 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
    @Override // android.support.constraint.solver.ArrayRow, android.support.constraint.solver.LinearSystem.Row
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.support.constraint.solver.SolverVariable getPivotCandidate(android.support.constraint.solver.LinearSystem r5, boolean[] r6) {
        /*
            r4 = this;
            r5 = -1
            r0 = 0
            r1 = -1
        L3:
            int r2 = r4.numGoals
            if (r0 >= r2) goto L32
            android.support.constraint.solver.SolverVariable[] r2 = r4.arrayGoals
            r2 = r2[r0]
            int r3 = r2.f133id
            boolean r3 = r6[r3]
            if (r3 == 0) goto L12
            goto L2f
        L12:
            android.support.constraint.solver.PriorityGoalRow$GoalVariableAccessor r3 = r4.accessor
            r3.init(r2)
            if (r1 != r5) goto L22
            android.support.constraint.solver.PriorityGoalRow$GoalVariableAccessor r2 = r4.accessor
            boolean r2 = r2.isNegative()
            if (r2 == 0) goto L2f
            goto L2e
        L22:
            android.support.constraint.solver.PriorityGoalRow$GoalVariableAccessor r2 = r4.accessor
            android.support.constraint.solver.SolverVariable[] r3 = r4.arrayGoals
            r3 = r3[r1]
            boolean r2 = r2.isSmallerThan(r3)
            if (r2 == 0) goto L2f
        L2e:
            r1 = r0
        L2f:
            int r0 = r0 + 1
            goto L3
        L32:
            if (r1 != r5) goto L36
            r5 = 0
            return r5
        L36:
            android.support.constraint.solver.SolverVariable[] r5 = r4.arrayGoals
            r5 = r5[r1]
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.PriorityGoalRow.getPivotCandidate(android.support.constraint.solver.LinearSystem, boolean[]):android.support.constraint.solver.SolverVariable");
    }

    @Override // android.support.constraint.solver.ArrayRow, android.support.constraint.solver.LinearSystem.Row
    public boolean isEmpty() {
        return this.numGoals == 0;
    }

    @Override // android.support.constraint.solver.ArrayRow
    public String toString() {
        StringBuilder sbM94a = C0080b.m94a("", " goal -> (");
        sbM94a.append(this.constantValue);
        sbM94a.append(") : ");
        String string = sbM94a.toString();
        for (int i7 = 0; i7 < this.numGoals; i7++) {
            this.accessor.init(this.arrayGoals[i7]);
            StringBuilder sbM112a = C0413b.m112a(string);
            sbM112a.append(this.accessor);
            sbM112a.append(" ");
            string = sbM112a.toString();
        }
        return string;
    }

    @Override // android.support.constraint.solver.ArrayRow, android.support.constraint.solver.LinearSystem.Row
    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z6) {
        SolverVariable solverVariable = arrayRow.variable;
        if (solverVariable == null) {
            return;
        }
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
        int currentSize = arrayRowVariables.getCurrentSize();
        for (int i7 = 0; i7 < currentSize; i7++) {
            SolverVariable variable = arrayRowVariables.getVariable(i7);
            float variableValue = arrayRowVariables.getVariableValue(i7);
            this.accessor.init(variable);
            if (this.accessor.addToGoal(solverVariable, variableValue)) {
                addToGoal(variable);
            }
            this.constantValue = (arrayRow.constantValue * variableValue) + this.constantValue;
        }
        removeGoal(solverVariable);
    }
}
