package android.support.constraint.solver;

import android.arch.lifecycle.C0063n;
import android.support.constraint.solver.ArrayRow;
import java.util.Arrays;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ArrayLinkedVariables implements ArrayRow.ArrayRowVariables {
    private static final boolean DEBUG = false;
    private static final boolean FULL_NEW_CHECK = false;
    public static final int NONE = -1;
    private static float epsilon = 0.001f;
    public final Cache mCache;
    private final ArrayRow mRow;
    public int currentSize = 0;
    private int ROW_SIZE = 8;
    private SolverVariable candidate = null;
    private int[] mArrayIndices = new int[8];
    private int[] mArrayNextIndices = new int[8];
    private float[] mArrayValues = new float[8];
    private int mHead = -1;
    private int mLast = -1;
    private boolean mDidFillOnce = false;

    public ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f7, boolean z6) {
        float f8 = epsilon;
        if (f7 <= (-f8) || f7 >= f8) {
            int i7 = this.mHead;
            if (i7 == -1) {
                this.mHead = 0;
                this.mArrayValues[0] = f7;
                this.mArrayIndices[0] = solverVariable.f133id;
                this.mArrayNextIndices[0] = -1;
                solverVariable.usageInRowCount++;
                solverVariable.addToRow(this.mRow);
                this.currentSize++;
                if (this.mDidFillOnce) {
                    return;
                }
                int i8 = this.mLast + 1;
                this.mLast = i8;
                int[] iArr = this.mArrayIndices;
                if (i8 >= iArr.length) {
                    this.mDidFillOnce = true;
                    this.mLast = iArr.length - 1;
                    return;
                }
                return;
            }
            int i9 = -1;
            for (int i10 = 0; i7 != -1 && i10 < this.currentSize; i10++) {
                int[] iArr2 = this.mArrayIndices;
                int i11 = iArr2[i7];
                int i12 = solverVariable.f133id;
                if (i11 == i12) {
                    float[] fArr = this.mArrayValues;
                    float f9 = fArr[i7] + f7;
                    float f10 = epsilon;
                    if (f9 > (-f10) && f9 < f10) {
                        f9 = 0.0f;
                    }
                    fArr[i7] = f9;
                    if (f9 == 0.0f) {
                        if (i7 == this.mHead) {
                            this.mHead = this.mArrayNextIndices[i7];
                        } else {
                            int[] iArr3 = this.mArrayNextIndices;
                            iArr3[i9] = iArr3[i7];
                        }
                        if (z6) {
                            solverVariable.removeFromRow(this.mRow);
                        }
                        if (this.mDidFillOnce) {
                            this.mLast = i7;
                        }
                        solverVariable.usageInRowCount--;
                        this.currentSize--;
                        return;
                    }
                    return;
                }
                if (iArr2[i7] < i12) {
                    i9 = i7;
                }
                i7 = this.mArrayNextIndices[i7];
            }
            int length = this.mLast;
            int i13 = length + 1;
            if (this.mDidFillOnce) {
                int[] iArr4 = this.mArrayIndices;
                if (iArr4[length] != -1) {
                    length = iArr4.length;
                }
            } else {
                length = i13;
            }
            int[] iArr5 = this.mArrayIndices;
            if (length >= iArr5.length && this.currentSize < iArr5.length) {
                int i14 = 0;
                while (true) {
                    int[] iArr6 = this.mArrayIndices;
                    if (i14 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i14] == -1) {
                        length = i14;
                        break;
                    }
                    i14++;
                }
            }
            int[] iArr7 = this.mArrayIndices;
            if (length >= iArr7.length) {
                length = iArr7.length;
                int i15 = this.ROW_SIZE * 2;
                this.ROW_SIZE = i15;
                this.mDidFillOnce = false;
                this.mLast = length - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, i15);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[length] = solverVariable.f133id;
            this.mArrayValues[length] = f7;
            if (i9 != -1) {
                int[] iArr8 = this.mArrayNextIndices;
                iArr8[length] = iArr8[i9];
                iArr8[i9] = length;
            } else {
                this.mArrayNextIndices[length] = this.mHead;
                this.mHead = length;
            }
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            int i16 = this.mLast;
            int[] iArr9 = this.mArrayIndices;
            if (i16 >= iArr9.length) {
                this.mDidFillOnce = true;
                this.mLast = iArr9.length - 1;
            }
        }
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public final void clear() {
        int i7 = this.mHead;
        for (int i8 = 0; i7 != -1 && i8 < this.currentSize; i8++) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i7]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.mRow);
            }
            i7 = this.mArrayNextIndices[i7];
        }
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.currentSize = 0;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        int i7 = this.mHead;
        if (i7 == -1) {
            return false;
        }
        for (int i8 = 0; i7 != -1 && i8 < this.currentSize; i8++) {
            if (this.mArrayIndices[i7] == solverVariable.f133id) {
                return true;
            }
            i7 = this.mArrayNextIndices[i7];
        }
        return false;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public void display() {
        int i7 = this.currentSize;
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
        int i7 = this.mHead;
        for (int i8 = 0; i7 != -1 && i8 < this.currentSize; i8++) {
            float[] fArr = this.mArrayValues;
            fArr[i7] = fArr[i7] / f7;
            i7 = this.mArrayNextIndices[i7];
        }
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public final float get(SolverVariable solverVariable) {
        int i7 = this.mHead;
        for (int i8 = 0; i7 != -1 && i8 < this.currentSize; i8++) {
            if (this.mArrayIndices[i7] == solverVariable.f133id) {
                return this.mArrayValues[i7];
            }
            i7 = this.mArrayNextIndices[i7];
        }
        return 0.0f;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.currentSize;
    }

    public int getHead() {
        return this.mHead;
    }

    public final int getId(int i7) {
        return this.mArrayIndices[i7];
    }

    public final int getNextIndice(int i7) {
        return this.mArrayNextIndices[i7];
    }

    public SolverVariable getPivotCandidate() {
        SolverVariable solverVariable = this.candidate;
        if (solverVariable != null) {
            return solverVariable;
        }
        int i7 = this.mHead;
        SolverVariable solverVariable2 = null;
        for (int i8 = 0; i7 != -1 && i8 < this.currentSize; i8++) {
            if (this.mArrayValues[i7] < 0.0f) {
                SolverVariable solverVariable3 = this.mCache.mIndexedVariables[this.mArrayIndices[i7]];
                if (solverVariable2 == null || solverVariable2.strength < solverVariable3.strength) {
                    solverVariable2 = solverVariable3;
                }
            }
            i7 = this.mArrayNextIndices[i7];
        }
        return solverVariable2;
    }

    public final float getValue(int i7) {
        return this.mArrayValues[i7];
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i7) {
        int i8 = this.mHead;
        for (int i9 = 0; i8 != -1 && i9 < this.currentSize; i9++) {
            if (i9 == i7) {
                return this.mCache.mIndexedVariables[this.mArrayIndices[i8]];
            }
            i8 = this.mArrayNextIndices[i8];
        }
        return null;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i7) {
        int i8 = this.mHead;
        for (int i9 = 0; i8 != -1 && i9 < this.currentSize; i9++) {
            if (i9 == i7) {
                return this.mArrayValues[i8];
            }
            i8 = this.mArrayNextIndices[i8];
        }
        return 0.0f;
    }

    public boolean hasAtLeastOnePositiveVariable() {
        int i7 = this.mHead;
        for (int i8 = 0; i7 != -1 && i8 < this.currentSize; i8++) {
            if (this.mArrayValues[i7] > 0.0f) {
                return true;
            }
            i7 = this.mArrayNextIndices[i7];
        }
        return false;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public int indexOf(SolverVariable solverVariable) {
        int i7 = this.mHead;
        if (i7 == -1) {
            return -1;
        }
        for (int i8 = 0; i7 != -1 && i8 < this.currentSize; i8++) {
            if (this.mArrayIndices[i7] == solverVariable.f133id) {
                return i7;
            }
            i7 = this.mArrayNextIndices[i7];
        }
        return -1;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public void invert() {
        int i7 = this.mHead;
        for (int i8 = 0; i7 != -1 && i8 < this.currentSize; i8++) {
            float[] fArr = this.mArrayValues;
            fArr[i7] = fArr[i7] * (-1.0f);
            i7 = this.mArrayNextIndices[i7];
        }
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public final void put(SolverVariable solverVariable, float f7) {
        if (f7 == 0.0f) {
            remove(solverVariable, true);
            return;
        }
        int i7 = this.mHead;
        if (i7 == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = f7;
            this.mArrayIndices[0] = solverVariable.f133id;
            this.mArrayNextIndices[0] = -1;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.currentSize++;
            if (this.mDidFillOnce) {
                return;
            }
            int i8 = this.mLast + 1;
            this.mLast = i8;
            int[] iArr = this.mArrayIndices;
            if (i8 >= iArr.length) {
                this.mDidFillOnce = true;
                this.mLast = iArr.length - 1;
                return;
            }
            return;
        }
        int i9 = -1;
        for (int i10 = 0; i7 != -1 && i10 < this.currentSize; i10++) {
            int[] iArr2 = this.mArrayIndices;
            int i11 = iArr2[i7];
            int i12 = solverVariable.f133id;
            if (i11 == i12) {
                this.mArrayValues[i7] = f7;
                return;
            }
            if (iArr2[i7] < i12) {
                i9 = i7;
            }
            i7 = this.mArrayNextIndices[i7];
        }
        int length = this.mLast;
        int i13 = length + 1;
        if (this.mDidFillOnce) {
            int[] iArr3 = this.mArrayIndices;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i13;
        }
        int[] iArr4 = this.mArrayIndices;
        if (length >= iArr4.length && this.currentSize < iArr4.length) {
            int i14 = 0;
            while (true) {
                int[] iArr5 = this.mArrayIndices;
                if (i14 >= iArr5.length) {
                    break;
                }
                if (iArr5[i14] == -1) {
                    length = i14;
                    break;
                }
                i14++;
            }
        }
        int[] iArr6 = this.mArrayIndices;
        if (length >= iArr6.length) {
            length = iArr6.length;
            int i15 = this.ROW_SIZE * 2;
            this.ROW_SIZE = i15;
            this.mDidFillOnce = false;
            this.mLast = length - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, i15);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
        }
        this.mArrayIndices[length] = solverVariable.f133id;
        this.mArrayValues[length] = f7;
        if (i9 != -1) {
            int[] iArr7 = this.mArrayNextIndices;
            iArr7[length] = iArr7[i9];
            iArr7[i9] = length;
        } else {
            this.mArrayNextIndices[length] = this.mHead;
            this.mHead = length;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.mRow);
        int i16 = this.currentSize + 1;
        this.currentSize = i16;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        int[] iArr8 = this.mArrayIndices;
        if (i16 >= iArr8.length) {
            this.mDidFillOnce = true;
        }
        if (this.mLast >= iArr8.length) {
            this.mDidFillOnce = true;
            this.mLast = iArr8.length - 1;
        }
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public final float remove(SolverVariable solverVariable, boolean z6) {
        if (this.candidate == solverVariable) {
            this.candidate = null;
        }
        int i7 = this.mHead;
        if (i7 == -1) {
            return 0.0f;
        }
        int i8 = 0;
        int i9 = -1;
        while (i7 != -1 && i8 < this.currentSize) {
            if (this.mArrayIndices[i7] == solverVariable.f133id) {
                if (i7 == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i7];
                } else {
                    int[] iArr = this.mArrayNextIndices;
                    iArr[i9] = iArr[i7];
                }
                if (z6) {
                    solverVariable.removeFromRow(this.mRow);
                }
                solverVariable.usageInRowCount--;
                this.currentSize--;
                this.mArrayIndices[i7] = -1;
                if (this.mDidFillOnce) {
                    this.mLast = i7;
                }
                return this.mArrayValues[i7];
            }
            i8++;
            i9 = i7;
            i7 = this.mArrayNextIndices[i7];
        }
        return 0.0f;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public int sizeInBytes() {
        return (this.mArrayIndices.length * 4 * 3) + 0 + 36;
    }

    public String toString() {
        int i7 = this.mHead;
        String string = "";
        for (int i8 = 0; i7 != -1 && i8 < this.currentSize; i8++) {
            StringBuilder sbM112a = C0413b.m112a(C0063n.m88a(string, " -> "));
            sbM112a.append(this.mArrayValues[i7]);
            sbM112a.append(" : ");
            StringBuilder sbM112a2 = C0413b.m112a(sbM112a.toString());
            sbM112a2.append(this.mCache.mIndexedVariables[this.mArrayIndices[i7]]);
            string = sbM112a2.toString();
            i7 = this.mArrayNextIndices[i7];
        }
        return string;
    }

    @Override // android.support.constraint.solver.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z6) {
        float f7 = get(arrayRow.variable);
        remove(arrayRow.variable, z6);
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
        int currentSize = arrayRowVariables.getCurrentSize();
        for (int i7 = 0; i7 < currentSize; i7++) {
            SolverVariable variable = arrayRowVariables.getVariable(i7);
            add(variable, arrayRowVariables.get(variable) * f7, z6);
        }
        return f7;
    }
}
