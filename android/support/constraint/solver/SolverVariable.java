package android.support.constraint.solver;

import android.arch.lifecycle.C0063n;
import java.util.Arrays;
import java.util.HashSet;
import p009b.C0413b;

/* loaded from: classes.dex */
public class SolverVariable {
    private static final boolean INTERNAL_DEBUG = false;
    public static final int MAX_STRENGTH = 9;
    public static final int STRENGTH_BARRIER = 6;
    public static final int STRENGTH_CENTERING = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 8;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static final boolean VAR_USE_HASH = false;
    private static int uniqueConstantId = 1;
    private static int uniqueErrorId = 1;
    private static int uniqueId = 1;
    private static int uniqueSlackId = 1;
    private static int uniqueUnrestrictedId = 1;
    public float computedValue;
    public int definitionId;
    public float[] goalStrengthVector;

    /* renamed from: id */
    public int f133id;
    public boolean inGoal;
    public HashSet<ArrayRow> inRows;
    public boolean isFinalValue;
    public boolean isSynonym;
    public ArrayRow[] mClientEquations;
    public int mClientEquationsCount;
    private String mName;
    public Type mType;
    public int strength;
    public float[] strengthVector;
    public int synonym;
    public float synonymDelta;
    public int usageInRowCount;

    /* renamed from: android.support.constraint.solver.SolverVariable$1 */
    public static /* synthetic */ class C00831 {
        public static final /* synthetic */ int[] $SwitchMap$android$support$constraint$solver$SolverVariable$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$android$support$constraint$solver$SolverVariable$Type = iArr;
            try {
                iArr[Type.UNRESTRICTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$SolverVariable$Type[Type.CONSTANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$SolverVariable$Type[Type.SLACK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$SolverVariable$Type[Type.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$SolverVariable$Type[Type.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(String str, Type type) {
        this.f133id = -1;
        this.definitionId = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.strengthVector = new float[9];
        this.goalStrengthVector = new float[9];
        this.mClientEquations = new ArrayRow[16];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.isSynonym = false;
        this.synonym = -1;
        this.synonymDelta = 0.0f;
        this.inRows = null;
        this.mName = str;
        this.mType = type;
    }

    private static String getUniqueName(Type type, String str) {
        if (str != null) {
            StringBuilder sbM112a = C0413b.m112a(str);
            sbM112a.append(uniqueErrorId);
            return sbM112a.toString();
        }
        int i7 = C00831.$SwitchMap$android$support$constraint$solver$SolverVariable$Type[type.ordinal()];
        if (i7 == 1) {
            StringBuilder sbM112a2 = C0413b.m112a("U");
            int i8 = uniqueUnrestrictedId + 1;
            uniqueUnrestrictedId = i8;
            sbM112a2.append(i8);
            return sbM112a2.toString();
        }
        if (i7 == 2) {
            StringBuilder sbM112a3 = C0413b.m112a("C");
            int i9 = uniqueConstantId + 1;
            uniqueConstantId = i9;
            sbM112a3.append(i9);
            return sbM112a3.toString();
        }
        if (i7 == 3) {
            StringBuilder sbM112a4 = C0413b.m112a("S");
            int i10 = uniqueSlackId + 1;
            uniqueSlackId = i10;
            sbM112a4.append(i10);
            return sbM112a4.toString();
        }
        if (i7 == 4) {
            StringBuilder sbM112a5 = C0413b.m112a("e");
            int i11 = uniqueErrorId + 1;
            uniqueErrorId = i11;
            sbM112a5.append(i11);
            return sbM112a5.toString();
        }
        if (i7 != 5) {
            throw new AssertionError(type.name());
        }
        StringBuilder sbM112a6 = C0413b.m112a("V");
        int i12 = uniqueId + 1;
        uniqueId = i12;
        sbM112a6.append(i12);
        return sbM112a6.toString();
    }

    public static void increaseErrorId() {
        uniqueErrorId++;
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i7 = 0;
        while (true) {
            int i8 = this.mClientEquationsCount;
            if (i7 >= i8) {
                ArrayRow[] arrayRowArr = this.mClientEquations;
                if (i8 >= arrayRowArr.length) {
                    this.mClientEquations = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.mClientEquations;
                int i9 = this.mClientEquationsCount;
                arrayRowArr2[i9] = arrayRow;
                this.mClientEquationsCount = i9 + 1;
                return;
            }
            if (this.mClientEquations[i7] == arrayRow) {
                return;
            } else {
                i7++;
            }
        }
    }

    public void clearStrengths() {
        for (int i7 = 0; i7 < 9; i7++) {
            this.strengthVector[i7] = 0.0f;
        }
    }

    public String getName() {
        return this.mName;
    }

    public final void removeFromRow(ArrayRow arrayRow) {
        int i7 = this.mClientEquationsCount;
        int i8 = 0;
        while (i8 < i7) {
            if (this.mClientEquations[i8] == arrayRow) {
                while (i8 < i7 - 1) {
                    ArrayRow[] arrayRowArr = this.mClientEquations;
                    int i9 = i8 + 1;
                    arrayRowArr[i8] = arrayRowArr[i9];
                    i8 = i9;
                }
                this.mClientEquationsCount--;
                return;
            }
            i8++;
        }
    }

    public void reset() {
        this.mName = null;
        this.mType = Type.UNKNOWN;
        this.strength = 0;
        this.f133id = -1;
        this.definitionId = -1;
        this.computedValue = 0.0f;
        this.isFinalValue = false;
        this.isSynonym = false;
        this.synonym = -1;
        this.synonymDelta = 0.0f;
        int i7 = this.mClientEquationsCount;
        for (int i8 = 0; i8 < i7; i8++) {
            this.mClientEquations[i8] = null;
        }
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.inGoal = false;
        Arrays.fill(this.goalStrengthVector, 0.0f);
    }

    public void setFinalValue(LinearSystem linearSystem, float f7) {
        this.computedValue = f7;
        this.isFinalValue = true;
        this.isSynonym = false;
        this.synonym = -1;
        this.synonymDelta = 0.0f;
        int i7 = this.mClientEquationsCount;
        this.definitionId = -1;
        for (int i8 = 0; i8 < i7; i8++) {
            this.mClientEquations[i8].updateFromFinalVariable(linearSystem, this, false);
        }
        this.mClientEquationsCount = 0;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setSynonym(LinearSystem linearSystem, SolverVariable solverVariable, float f7) {
        this.isSynonym = true;
        this.synonym = solverVariable.f133id;
        this.synonymDelta = f7;
        int i7 = this.mClientEquationsCount;
        this.definitionId = -1;
        for (int i8 = 0; i8 < i7; i8++) {
            this.mClientEquations[i8].updateFromSynonymVariable(linearSystem, this, false);
        }
        this.mClientEquationsCount = 0;
        linearSystem.displayReadableRows();
    }

    public void setType(Type type, String str) {
        this.mType = type;
    }

    public String strengthsToString() {
        String strM88a = this + "[";
        int i7 = 0;
        boolean z6 = false;
        boolean z7 = true;
        while (i7 < this.strengthVector.length) {
            StringBuilder sbM112a = C0413b.m112a(strM88a);
            sbM112a.append(this.strengthVector[i7]);
            String string = sbM112a.toString();
            float[] fArr = this.strengthVector;
            if (fArr[i7] > 0.0f) {
                z6 = false;
            } else if (fArr[i7] < 0.0f) {
                z6 = true;
            }
            if (fArr[i7] != 0.0f) {
                z7 = false;
            }
            strM88a = i7 < fArr.length + (-1) ? C0063n.m88a(string, ", ") : C0063n.m88a(string, "] ");
            i7++;
        }
        if (z6) {
            strM88a = C0063n.m88a(strM88a, " (-)");
        }
        return z7 ? C0063n.m88a(strM88a, " (*)") : strM88a;
    }

    public String toString() {
        if (this.mName != null) {
            StringBuilder sbM112a = C0413b.m112a("");
            sbM112a.append(this.mName);
            return sbM112a.toString();
        }
        StringBuilder sbM112a2 = C0413b.m112a("");
        sbM112a2.append(this.f133id);
        return sbM112a2.toString();
    }

    public final void updateReferencesWithNewDefinition(LinearSystem linearSystem, ArrayRow arrayRow) {
        int i7 = this.mClientEquationsCount;
        for (int i8 = 0; i8 < i7; i8++) {
            this.mClientEquations[i8].updateFromRow(linearSystem, arrayRow, false);
        }
        this.mClientEquationsCount = 0;
    }

    public SolverVariable(Type type, String str) {
        this.f133id = -1;
        this.definitionId = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.strengthVector = new float[9];
        this.goalStrengthVector = new float[9];
        this.mClientEquations = new ArrayRow[16];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.isSynonym = false;
        this.synonym = -1;
        this.synonymDelta = 0.0f;
        this.inRows = null;
        this.mType = type;
    }
}
