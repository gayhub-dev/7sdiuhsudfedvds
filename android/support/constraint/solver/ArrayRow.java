package android.support.constraint.solver;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ArrayRow implements LinearSystem.Row {
    private static final boolean DEBUG = false;
    private static final boolean FULL_NEW_CHECK = false;
    public ArrayRowVariables variables;
    public SolverVariable variable = null;
    public float constantValue = 0.0f;
    public boolean used = false;
    public ArrayList<SolverVariable> variablesToUpdate = new ArrayList<>();
    public boolean isSimpleDefinition = false;

    public interface ArrayRowVariables {
        void add(SolverVariable solverVariable, float f7, boolean z6);

        void clear();

        boolean contains(SolverVariable solverVariable);

        void display();

        void divideByAmount(float f7);

        float get(SolverVariable solverVariable);

        int getCurrentSize();

        SolverVariable getVariable(int i7);

        float getVariableValue(int i7);

        int indexOf(SolverVariable solverVariable);

        void invert();

        void put(SolverVariable solverVariable, float f7);

        float remove(SolverVariable solverVariable, boolean z6);

        int sizeInBytes();

        float use(ArrayRow arrayRow, boolean z6);
    }

    public ArrayRow() {
    }

    private boolean isNew(SolverVariable solverVariable, LinearSystem linearSystem) {
        return solverVariable.usageInRowCount <= 1;
    }

    private SolverVariable pickPivotInVariables(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int currentSize = this.variables.getCurrentSize();
        SolverVariable solverVariable2 = null;
        float f7 = 0.0f;
        for (int i7 = 0; i7 < currentSize; i7++) {
            float variableValue = this.variables.getVariableValue(i7);
            if (variableValue < 0.0f) {
                SolverVariable variable = this.variables.getVariable(i7);
                if ((zArr == null || !zArr[variable.f133id]) && variable != solverVariable && (((type = variable.mType) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && variableValue < f7)) {
                    f7 = variableValue;
                    solverVariable2 = variable;
                }
            }
        }
        return solverVariable2;
    }

    public ArrayRow addError(LinearSystem linearSystem, int i7) {
        this.variables.put(linearSystem.createErrorVariable(i7, "ep"), 1.0f);
        this.variables.put(linearSystem.createErrorVariable(i7, "em"), -1.0f);
        return this;
    }

    public ArrayRow addSingleError(SolverVariable solverVariable, int i7) {
        this.variables.put(solverVariable, i7);
        return this;
    }

    public boolean chooseSubject(LinearSystem linearSystem) {
        boolean z6;
        SolverVariable solverVariableChooseSubjectInVariables = chooseSubjectInVariables(linearSystem);
        if (solverVariableChooseSubjectInVariables == null) {
            z6 = true;
        } else {
            pivot(solverVariableChooseSubjectInVariables);
            z6 = false;
        }
        if (this.variables.getCurrentSize() == 0) {
            this.isSimpleDefinition = true;
        }
        return z6;
    }

    public SolverVariable chooseSubjectInVariables(LinearSystem linearSystem) {
        int currentSize = this.variables.getCurrentSize();
        SolverVariable solverVariable = null;
        SolverVariable solverVariable2 = null;
        boolean z6 = false;
        boolean z7 = false;
        float f7 = 0.0f;
        float f8 = 0.0f;
        for (int i7 = 0; i7 < currentSize; i7++) {
            float variableValue = this.variables.getVariableValue(i7);
            SolverVariable variable = this.variables.getVariable(i7);
            if (variable.mType == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f7 > variableValue) {
                    boolean zIsNew = isNew(variable, linearSystem);
                    z6 = zIsNew;
                    f7 = variableValue;
                    solverVariable = variable;
                } else if (!z6 && isNew(variable, linearSystem)) {
                    f7 = variableValue;
                    solverVariable = variable;
                    z6 = true;
                }
            } else if (solverVariable == null && variableValue < 0.0f) {
                if (solverVariable2 == null || f8 > variableValue) {
                    boolean zIsNew2 = isNew(variable, linearSystem);
                    z7 = zIsNew2;
                    f8 = variableValue;
                    solverVariable2 = variable;
                } else if (!z7 && isNew(variable, linearSystem)) {
                    f8 = variableValue;
                    solverVariable2 = variable;
                    z7 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // android.support.constraint.solver.LinearSystem.Row
    public void clear() {
        this.variables.clear();
        this.variable = null;
        this.constantValue = 0.0f;
    }

    public ArrayRow createRowCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i7, float f7, SolverVariable solverVariable3, SolverVariable solverVariable4, int i8) {
        if (solverVariable2 == solverVariable3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable2, -2.0f);
            return this;
        }
        if (f7 == 0.5f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            if (i7 > 0 || i8 > 0) {
                this.constantValue = (-i7) + i8;
            }
        } else if (f7 <= 0.0f) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.constantValue = i7;
        } else if (f7 >= 1.0f) {
            this.variables.put(solverVariable4, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
            this.constantValue = -i8;
        } else {
            float f8 = 1.0f - f7;
            this.variables.put(solverVariable, f8 * 1.0f);
            this.variables.put(solverVariable2, f8 * (-1.0f));
            this.variables.put(solverVariable3, (-1.0f) * f7);
            this.variables.put(solverVariable4, 1.0f * f7);
            if (i7 > 0 || i8 > 0) {
                this.constantValue = (i8 * f7) + ((-i7) * f8);
            }
        }
        return this;
    }

    public ArrayRow createRowDefinition(SolverVariable solverVariable, int i7) {
        this.variable = solverVariable;
        float f7 = i7;
        solverVariable.computedValue = f7;
        this.constantValue = f7;
        this.isSimpleDefinition = true;
        return this;
    }

    public ArrayRow createRowDimensionPercent(SolverVariable solverVariable, SolverVariable solverVariable2, float f7) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, f7);
        return this;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f7) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        this.variables.put(solverVariable3, f7);
        this.variables.put(solverVariable4, -f7);
        return this;
    }

    public ArrayRow createRowEqualDimension(float f7, float f8, float f9, SolverVariable solverVariable, int i7, SolverVariable solverVariable2, int i8, SolverVariable solverVariable3, int i9, SolverVariable solverVariable4, int i10) {
        if (f8 == 0.0f || f7 == f9) {
            this.constantValue = ((-i7) - i8) + i9 + i10;
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else {
            float f10 = (f7 / f8) / (f9 / f8);
            this.constantValue = (i10 * f10) + (i9 * f10) + ((-i7) - i8);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f10);
            this.variables.put(solverVariable3, -f10);
        }
        return this;
    }

    public ArrayRow createRowEqualMatchDimensions(float f7, float f8, float f9, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.constantValue = 0.0f;
        if (f8 == 0.0f || f7 == f9) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else if (f7 == 0.0f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        } else if (f9 == 0.0f) {
            this.variables.put(solverVariable3, 1.0f);
            this.variables.put(solverVariable4, -1.0f);
        } else {
            float f10 = (f7 / f8) / (f9 / f8);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f10);
            this.variables.put(solverVariable3, -f10);
        }
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, int i7) {
        if (i7 < 0) {
            this.constantValue = i7 * (-1);
            this.variables.put(solverVariable, 1.0f);
        } else {
            this.constantValue = i7;
            this.variables.put(solverVariable, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i7) {
        boolean z6 = false;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            }
            this.constantValue = i7;
        }
        if (z6) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, 1.0f);
        }
        return this;
    }

    public ArrayRow createRowLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i7) {
        boolean z6 = false;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            }
            this.constantValue = i7;
        }
        if (z6) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
        } else {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowWithAngle(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f7) {
        this.variables.put(solverVariable3, 0.5f);
        this.variables.put(solverVariable4, 0.5f);
        this.variables.put(solverVariable, -0.5f);
        this.variables.put(solverVariable2, -0.5f);
        this.constantValue = -f7;
        return this;
    }

    public void ensurePositiveConstant() {
        float f7 = this.constantValue;
        if (f7 < 0.0f) {
            this.constantValue = f7 * (-1.0f);
            this.variables.invert();
        }
    }

    @Override // android.support.constraint.solver.LinearSystem.Row
    public SolverVariable getKey() {
        return this.variable;
    }

    @Override // android.support.constraint.solver.LinearSystem.Row
    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        return pickPivotInVariables(zArr, null);
    }

    public boolean hasKeyVariable() {
        SolverVariable solverVariable = this.variable;
        return solverVariable != null && (solverVariable.mType == SolverVariable.Type.UNRESTRICTED || this.constantValue >= 0.0f);
    }

    public boolean hasVariable(SolverVariable solverVariable) {
        return this.variables.contains(solverVariable);
    }

    @Override // android.support.constraint.solver.LinearSystem.Row
    public void initFromRow(LinearSystem.Row row) {
        if (row instanceof ArrayRow) {
            ArrayRow arrayRow = (ArrayRow) row;
            this.variable = null;
            this.variables.clear();
            for (int i7 = 0; i7 < arrayRow.variables.getCurrentSize(); i7++) {
                this.variables.add(arrayRow.variables.getVariable(i7), arrayRow.variables.getVariableValue(i7), true);
            }
        }
    }

    @Override // android.support.constraint.solver.LinearSystem.Row
    public boolean isEmpty() {
        return this.variable == null && this.constantValue == 0.0f && this.variables.getCurrentSize() == 0;
    }

    public SolverVariable pickPivot(SolverVariable solverVariable) {
        return pickPivotInVariables(null, solverVariable);
    }

    public void pivot(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.variable;
        if (solverVariable2 != null) {
            this.variables.put(solverVariable2, -1.0f);
            this.variable.definitionId = -1;
            this.variable = null;
        }
        float fRemove = this.variables.remove(solverVariable, true) * (-1.0f);
        this.variable = solverVariable;
        if (fRemove == 1.0f) {
            return;
        }
        this.constantValue /= fRemove;
        this.variables.divideByAmount(fRemove);
    }

    public void reset() {
        this.variable = null;
        this.variables.clear();
        this.constantValue = 0.0f;
        this.isSimpleDefinition = false;
    }

    public int sizeInBytes() {
        return this.variables.sizeInBytes() + (this.variable != null ? 4 : 0) + 4 + 4;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toReadableString() {
        /*
            r9 = this;
            android.support.constraint.solver.SolverVariable r0 = r9.variable
            if (r0 != 0) goto L7
            java.lang.String r0 = "0"
            goto L16
        L7:
            java.lang.String r0 = ""
            java.lang.StringBuilder r0 = p009b.C0413b.m112a(r0)
            android.support.constraint.solver.SolverVariable r1 = r9.variable
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L16:
            java.lang.String r1 = " = "
            java.lang.String r0 = android.arch.lifecycle.C0063n.m88a(r0, r1)
            float r1 = r9.constantValue
            r2 = 0
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L33
            java.lang.StringBuilder r0 = p009b.C0413b.m112a(r0)
            float r1 = r9.constantValue
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 1
            goto L34
        L33:
            r1 = 0
        L34:
            android.support.constraint.solver.ArrayRow$ArrayRowVariables r4 = r9.variables
            int r4 = r4.getCurrentSize()
        L3a:
            if (r2 >= r4) goto L9a
            android.support.constraint.solver.ArrayRow$ArrayRowVariables r5 = r9.variables
            android.support.constraint.solver.SolverVariable r5 = r5.getVariable(r2)
            if (r5 != 0) goto L45
            goto L97
        L45:
            android.support.constraint.solver.ArrayRow$ArrayRowVariables r6 = r9.variables
            float r6 = r6.getVariableValue(r2)
            int r7 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r7 != 0) goto L50
            goto L97
        L50:
            java.lang.String r5 = r5.toString()
            r8 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L63
            int r1 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r1 >= 0) goto L74
            java.lang.String r1 = "- "
            java.lang.String r0 = android.arch.lifecycle.C0063n.m88a(r0, r1)
            goto L72
        L63:
            if (r7 <= 0) goto L6c
            java.lang.String r1 = " + "
            java.lang.String r0 = android.arch.lifecycle.C0063n.m88a(r0, r1)
            goto L74
        L6c:
            java.lang.String r1 = " - "
            java.lang.String r0 = android.arch.lifecycle.C0063n.m88a(r0, r1)
        L72:
            float r6 = r6 * r8
        L74:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r1 != 0) goto L7f
            java.lang.String r0 = android.arch.lifecycle.C0063n.m88a(r0, r5)
            goto L96
        L7f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = " "
            r1.append(r0)
            r1.append(r5)
            java.lang.String r0 = r1.toString()
        L96:
            r1 = 1
        L97:
            int r2 = r2 + 1
            goto L3a
        L9a:
            if (r1 != 0) goto La2
            java.lang.String r1 = "0.0"
            java.lang.String r0 = android.arch.lifecycle.C0063n.m88a(r0, r1)
        La2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.ArrayRow.toReadableString():java.lang.String");
    }

    public String toString() {
        return toReadableString();
    }

    @Override // android.support.constraint.solver.LinearSystem.Row
    public void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z6) {
        if (solverVariable.isFinalValue) {
            float f7 = this.variables.get(solverVariable);
            this.constantValue = (solverVariable.computedValue * f7) + this.constantValue;
            this.variables.remove(solverVariable, z6);
            if (z6) {
                solverVariable.removeFromRow(this);
            }
            if (LinearSystem.SIMPLIFY_SYNONYMS && this.variables.getCurrentSize() == 0) {
                this.isSimpleDefinition = true;
                linearSystem.hasSimpleDefinition = true;
            }
        }
    }

    @Override // android.support.constraint.solver.LinearSystem.Row
    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z6) {
        float fUse = this.variables.use(arrayRow, z6);
        this.constantValue = (arrayRow.constantValue * fUse) + this.constantValue;
        if (z6) {
            arrayRow.variable.removeFromRow(this);
        }
        if (LinearSystem.SIMPLIFY_SYNONYMS && this.variable != null && this.variables.getCurrentSize() == 0) {
            this.isSimpleDefinition = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }

    public void updateFromSynonymVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z6) {
        if (solverVariable.isSynonym) {
            float f7 = this.variables.get(solverVariable);
            this.constantValue = (solverVariable.synonymDelta * f7) + this.constantValue;
            this.variables.remove(solverVariable, z6);
            if (z6) {
                solverVariable.removeFromRow(this);
            }
            this.variables.add(linearSystem.mCache.mIndexedVariables[solverVariable.synonym], f7, z6);
            if (LinearSystem.SIMPLIFY_SYNONYMS && this.variables.getCurrentSize() == 0) {
                this.isSimpleDefinition = true;
                linearSystem.hasSimpleDefinition = true;
            }
        }
    }

    @Override // android.support.constraint.solver.LinearSystem.Row
    public void updateFromSystem(LinearSystem linearSystem) {
        if (linearSystem.mRows.length == 0) {
            return;
        }
        boolean z6 = false;
        while (!z6) {
            int currentSize = this.variables.getCurrentSize();
            for (int i7 = 0; i7 < currentSize; i7++) {
                SolverVariable variable = this.variables.getVariable(i7);
                if (variable.definitionId != -1 || variable.isFinalValue || variable.isSynonym) {
                    this.variablesToUpdate.add(variable);
                }
            }
            int size = this.variablesToUpdate.size();
            if (size > 0) {
                for (int i8 = 0; i8 < size; i8++) {
                    SolverVariable solverVariable = this.variablesToUpdate.get(i8);
                    if (solverVariable.isFinalValue) {
                        updateFromFinalVariable(linearSystem, solverVariable, true);
                    } else if (solverVariable.isSynonym) {
                        updateFromSynonymVariable(linearSystem, solverVariable, true);
                    } else {
                        updateFromRow(linearSystem, linearSystem.mRows[solverVariable.definitionId], true);
                    }
                }
                this.variablesToUpdate.clear();
            } else {
                z6 = true;
            }
        }
        if (LinearSystem.SIMPLIFY_SYNONYMS && this.variable != null && this.variables.getCurrentSize() == 0) {
            this.isSimpleDefinition = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }

    @Override // android.support.constraint.solver.LinearSystem.Row
    public void addError(SolverVariable solverVariable) {
        int i7 = solverVariable.strength;
        float f7 = 1.0f;
        if (i7 != 1) {
            if (i7 == 2) {
                f7 = 1000.0f;
            } else if (i7 == 3) {
                f7 = 1000000.0f;
            } else if (i7 == 4) {
                f7 = 1.0E9f;
            } else if (i7 == 5) {
                f7 = 1.0E12f;
            }
        }
        this.variables.put(solverVariable, f7);
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, SolverVariable solverVariable2, int i7) {
        boolean z6 = false;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            }
            this.constantValue = i7;
        }
        if (!z6) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
        } else {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        }
        return this;
    }

    public ArrayRow(Cache cache) {
        this.variables = new ArrayLinkedVariables(this, cache);
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, int i7, SolverVariable solverVariable2) {
        this.constantValue = i7;
        this.variables.put(solverVariable, -1.0f);
        return this;
    }
}
