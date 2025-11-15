package android.support.constraint.solver;

import android.arch.lifecycle.C0063n;
import android.support.constraint.motion.C0080b;
import android.support.constraint.solver.ArrayRow;
import java.util.Arrays;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SolverVariableValues implements ArrayRow.ArrayRowVariables {
    private static final boolean DEBUG = false;
    private static final boolean HASH = true;
    private static float epsilon = 0.001f;
    public final Cache mCache;
    private final ArrayRow mRow;
    private final int NONE = -1;
    private int SIZE = 16;
    private int HASH_SIZE = 16;
    public int[] keys = new int[16];
    public int[] nextKeys = new int[16];
    public int[] variables = new int[16];
    public float[] values = new float[16];
    public int[] previous = new int[16];
    public int[] next = new int[16];
    public int mCount = 0;
    public int head = -1;

    public SolverVariableValues(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
        clear();
    }

    private void addToHashMap(SolverVariable solverVariable, int i7) {
        int[] iArr;
        int i8 = solverVariable.f133id % this.HASH_SIZE;
        int[] iArr2 = this.keys;
        int i9 = iArr2[i8];
        if (i9 == -1) {
            iArr2[i8] = i7;
        } else {
            while (true) {
                iArr = this.nextKeys;
                if (iArr[i9] == -1) {
                    break;
                } else {
                    i9 = iArr[i9];
                }
            }
            iArr[i9] = i7;
        }
        this.nextKeys[i7] = -1;
    }

    private void addVariable(int i7, SolverVariable solverVariable, float f7) {
        this.variables[i7] = solverVariable.f133id;
        this.values[i7] = f7;
        this.previous[i7] = -1;
        this.next[i7] = -1;
        solverVariable.addToRow(this.mRow);
        solverVariable.usageInRowCount++;
        this.mCount++;
    }

    private void displayHash() {
        for (int i7 = 0; i7 < this.HASH_SIZE; i7++) {
            if (this.keys[i7] != -1) {
                String string = hashCode() + " hash [" + i7 + "] => ";
                int i8 = this.keys[i7];
                boolean z6 = false;
                while (!z6) {
                    StringBuilder sbM94a = C0080b.m94a(string, " ");
                    sbM94a.append(this.variables[i8]);
                    string = sbM94a.toString();
                    int[] iArr = this.nextKeys;
                    if (iArr[i8] != -1) {
                        i8 = iArr[i8];
                    } else {
                        z6 = true;
                    }
                }
                System.out.println(string);
            }
        }
    }

    private int findEmptySlot() {
        for (int i7 = 0; i7 < this.SIZE; i7++) {
            if (this.variables[i7] == -1) {
                return i7;
            }
        }
        return -1;
    }

    private void increaseSize() {
        int i7 = this.SIZE * 2;
        this.variables = Arrays.copyOf(this.variables, i7);
        this.values = Arrays.copyOf(this.values, i7);
        this.previous = Arrays.copyOf(this.previous, i7);
        this.next = Arrays.copyOf(this.next, i7);
        this.nextKeys = Arrays.copyOf(this.nextKeys, i7);
        for (int i8 = this.SIZE; i8 < i7; i8++) {
            this.variables[i8] = -1;
            this.nextKeys[i8] = -1;
        }
        this.SIZE = i7;
    }

    private void insertVariable(int i7, SolverVariable solverVariable, float f7) {
        int iFindEmptySlot = findEmptySlot();
        addVariable(iFindEmptySlot, solverVariable, f7);
        if (i7 != -1) {
            this.previous[iFindEmptySlot] = i7;
            int[] iArr = this.next;
            iArr[iFindEmptySlot] = iArr[i7];
            iArr[i7] = iFindEmptySlot;
        } else {
            this.previous[iFindEmptySlot] = -1;
            if (this.mCount > 0) {
                this.next[iFindEmptySlot] = this.head;
                this.head = iFindEmptySlot;
            } else {
                this.next[iFindEmptySlot] = -1;
            }
        }
        int[] iArr2 = this.next;
        if (iArr2[iFindEmptySlot] != -1) {
            this.previous[iArr2[iFindEmptySlot]] = iFindEmptySlot;
        }
        addToHashMap(solverVariable, iFindEmptySlot);
    }

    private void removeFromHashMap(SolverVariable solverVariable) {
        int[] iArr;
        int i7 = solverVariable.f133id;
        int i8 = i7 % this.HASH_SIZE;
        int[] iArr2 = this.keys;
        int i9 = iArr2[i8];
        if (i9 == -1) {
            return;
        }
        if (this.variables[i9] == i7) {
            int[] iArr3 = this.nextKeys;
            iArr2[i8] = iArr3[i9];
            iArr3[i9] = -1;
            return;
        }
        while (true) {
            iArr = this.nextKeys;
            if (iArr[i9] == -1 || this.variables[iArr[i9]] == i7) {
                break;
            } else {
                i9 = iArr[i9];
            }
        }
        int i10 = iArr[i9];
        if (i10 == -1 || this.variables[i10] != i7) {
            return;
        }
        iArr[i9] = iArr[i10];
        iArr[i10] = -1;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f7, boolean z6) {
        float f8 = epsilon;
        if (f7 <= (-f8) || f7 >= f8) {
            int iIndexOf = indexOf(solverVariable);
            if (iIndexOf == -1) {
                put(solverVariable, f7);
                return;
            }
            float[] fArr = this.values;
            fArr[iIndexOf] = fArr[iIndexOf] + f7;
            float f9 = fArr[iIndexOf];
            float f10 = epsilon;
            if (f9 <= (-f10) || fArr[iIndexOf] >= f10) {
                return;
            }
            fArr[iIndexOf] = 0.0f;
            remove(solverVariable, z6);
        }
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public void clear() {
        int i7 = this.mCount;
        for (int i8 = 0; i8 < i7; i8++) {
            SolverVariable variable = getVariable(i8);
            if (variable != null) {
                variable.removeFromRow(this.mRow);
            }
        }
        for (int i9 = 0; i9 < this.SIZE; i9++) {
            this.variables[i9] = -1;
            this.nextKeys[i9] = -1;
        }
        for (int i10 = 0; i10 < this.HASH_SIZE; i10++) {
            this.keys[i10] = -1;
        }
        this.mCount = 0;
        this.head = -1;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        return indexOf(solverVariable) != -1;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public void display() {
        int i7 = this.mCount;
        System.out.print("{ ");
        for (int i8 = 0; i8 < i7; i8++) {
            SolverVariable variable = getVariable(i8);
            if (variable != null) {
                System.out.print(variable + " = " + getVariableValue(i8) + " ");
            }
        }
        System.out.println(" }");
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public void divideByAmount(float f7) {
        int i7 = this.mCount;
        int i8 = this.head;
        for (int i9 = 0; i9 < i7; i9++) {
            float[] fArr = this.values;
            fArr[i8] = fArr[i8] / f7;
            i8 = this.next[i8];
            if (i8 == -1) {
                return;
            }
        }
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public float get(SolverVariable solverVariable) {
        int iIndexOf = indexOf(solverVariable);
        if (iIndexOf != -1) {
            return this.values[iIndexOf];
        }
        return 0.0f;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.mCount;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i7) {
        int i8 = this.mCount;
        if (i8 == 0) {
            return null;
        }
        int i9 = this.head;
        for (int i10 = 0; i10 < i8; i10++) {
            if (i10 == i7 && i9 != -1) {
                return this.mCache.mIndexedVariables[this.variables[i9]];
            }
            i9 = this.next[i9];
            if (i9 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i7) {
        int i8 = this.mCount;
        int i9 = this.head;
        for (int i10 = 0; i10 < i8; i10++) {
            if (i10 == i7) {
                return this.values[i9];
            }
            i9 = this.next[i9];
            if (i9 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public int indexOf(SolverVariable solverVariable) {
        int[] iArr;
        if (this.mCount != 0 && solverVariable != null) {
            int i7 = solverVariable.f133id;
            int i8 = this.keys[i7 % this.HASH_SIZE];
            if (i8 == -1) {
                return -1;
            }
            if (this.variables[i8] == i7) {
                return i8;
            }
            while (true) {
                iArr = this.nextKeys;
                if (iArr[i8] == -1 || this.variables[iArr[i8]] == i7) {
                    break;
                }
                i8 = iArr[i8];
            }
            if (iArr[i8] != -1 && this.variables[iArr[i8]] == i7) {
                return iArr[i8];
            }
        }
        return -1;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public void invert() {
        int i7 = this.mCount;
        int i8 = this.head;
        for (int i9 = 0; i9 < i7; i9++) {
            float[] fArr = this.values;
            fArr[i8] = fArr[i8] * (-1.0f);
            i8 = this.next[i8];
            if (i8 == -1) {
                return;
            }
        }
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public void put(SolverVariable solverVariable, float f7) {
        float f8 = epsilon;
        if (f7 > (-f8) && f7 < f8) {
            remove(solverVariable, true);
            return;
        }
        if (this.mCount == 0) {
            addVariable(0, solverVariable, f7);
            addToHashMap(solverVariable, 0);
            this.head = 0;
            return;
        }
        int iIndexOf = indexOf(solverVariable);
        if (iIndexOf != -1) {
            this.values[iIndexOf] = f7;
            return;
        }
        if (this.mCount + 1 >= this.SIZE) {
            increaseSize();
        }
        int i7 = this.mCount;
        int i8 = this.head;
        int i9 = -1;
        for (int i10 = 0; i10 < i7; i10++) {
            int[] iArr = this.variables;
            int i11 = iArr[i8];
            int i12 = solverVariable.f133id;
            if (i11 == i12) {
                this.values[i8] = f7;
                return;
            }
            if (iArr[i8] < i12) {
                i9 = i8;
            }
            i8 = this.next[i8];
            if (i8 == -1) {
                break;
            }
        }
        insertVariable(i9, solverVariable, f7);
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public float remove(SolverVariable solverVariable, boolean z6) {
        int iIndexOf = indexOf(solverVariable);
        if (iIndexOf == -1) {
            return 0.0f;
        }
        removeFromHashMap(solverVariable);
        float f7 = this.values[iIndexOf];
        if (this.head == iIndexOf) {
            this.head = this.next[iIndexOf];
        }
        this.variables[iIndexOf] = -1;
        int[] iArr = this.previous;
        if (iArr[iIndexOf] != -1) {
            int[] iArr2 = this.next;
            iArr2[iArr[iIndexOf]] = iArr2[iIndexOf];
        }
        int[] iArr3 = this.next;
        if (iArr3[iIndexOf] != -1) {
            iArr[iArr3[iIndexOf]] = iArr[iIndexOf];
        }
        this.mCount--;
        solverVariable.usageInRowCount--;
        if (z6) {
            solverVariable.removeFromRow(this.mRow);
        }
        return f7;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public int sizeInBytes() {
        return 0;
    }

    public String toString() {
        String strM88a;
        String strM88a2;
        String strM88a3 = hashCode() + " { ";
        int i7 = this.mCount;
        for (int i8 = 0; i8 < i7; i8++) {
            SolverVariable variable = getVariable(i8);
            if (variable != null) {
                String str = strM88a3 + variable + " = " + getVariableValue(i8) + " ";
                int iIndexOf = indexOf(variable);
                String strM88a4 = C0063n.m88a(str, "[p: ");
                if (this.previous[iIndexOf] != -1) {
                    StringBuilder sbM112a = C0413b.m112a(strM88a4);
                    sbM112a.append(this.mCache.mIndexedVariables[this.variables[this.previous[iIndexOf]]]);
                    strM88a = sbM112a.toString();
                } else {
                    strM88a = C0063n.m88a(strM88a4, "none");
                }
                String strM88a5 = C0063n.m88a(strM88a, ", n: ");
                if (this.next[iIndexOf] != -1) {
                    StringBuilder sbM112a2 = C0413b.m112a(strM88a5);
                    sbM112a2.append(this.mCache.mIndexedVariables[this.variables[this.next[iIndexOf]]]);
                    strM88a2 = sbM112a2.toString();
                } else {
                    strM88a2 = C0063n.m88a(strM88a5, "none");
                }
                strM88a3 = C0063n.m88a(strM88a2, "]");
            }
        }
        return C0063n.m88a(strM88a3, " }");
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z6) {
        float f7 = get(arrayRow.variable);
        remove(arrayRow.variable, z6);
        SolverVariableValues solverVariableValues = (SolverVariableValues) arrayRow.variables;
        int currentSize = solverVariableValues.getCurrentSize();
        int i7 = 0;
        int i8 = 0;
        while (i7 < currentSize) {
            int[] iArr = solverVariableValues.variables;
            if (iArr[i8] != -1) {
                add(this.mCache.mIndexedVariables[iArr[i8]], solverVariableValues.values[i8] * f7, z6);
                i7++;
            }
            i8++;
        }
        return f7;
    }
}
