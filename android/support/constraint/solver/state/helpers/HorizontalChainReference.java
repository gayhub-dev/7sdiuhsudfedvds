package android.support.constraint.solver.state.helpers;

import android.support.constraint.solver.state.ConstraintReference;
import android.support.constraint.solver.state.State;
import java.util.Iterator;

/* loaded from: classes.dex */
public class HorizontalChainReference extends ChainReference {
    private Object mEndToEnd;
    private Object mEndToStart;
    private Object mStartToEnd;
    private Object mStartToStart;

    /* renamed from: android.support.constraint.solver.state.helpers.HorizontalChainReference$1 */
    public static /* synthetic */ class C00881 {
        public static final /* synthetic */ int[] $SwitchMap$android$support$constraint$solver$state$State$Chain;

        static {
            int[] iArr = new int[State.Chain.values().length];
            $SwitchMap$android$support$constraint$solver$state$State$Chain = iArr;
            try {
                iArr[State.Chain.SPREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Chain[State.Chain.SPREAD_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$state$State$Chain[State.Chain.PACKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HorizontalChainReference(State state) {
        super(state, State.Helper.HORIZONTAL_CHAIN);
    }

    @Override // android.support.constraint.solver.state.HelperReference
    public void apply() {
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            this.mState.constraints(it.next()).clearHorizontal();
        }
        Iterator<Object> it2 = this.mReferences.iterator();
        ConstraintReference constraintReference = null;
        ConstraintReference constraintReference2 = null;
        while (it2.hasNext()) {
            ConstraintReference constraintReferenceConstraints = this.mState.constraints(it2.next());
            if (constraintReference2 == null) {
                Object obj = this.mStartToStart;
                if (obj != null) {
                    constraintReferenceConstraints.startToStart(obj);
                } else {
                    Object obj2 = this.mStartToEnd;
                    if (obj2 != null) {
                        constraintReferenceConstraints.startToEnd(obj2);
                    } else {
                        constraintReferenceConstraints.startToStart(State.PARENT);
                    }
                }
                constraintReference2 = constraintReferenceConstraints;
            }
            if (constraintReference != null) {
                constraintReference.endToStart(constraintReferenceConstraints.getKey());
                constraintReferenceConstraints.startToEnd(constraintReference.getKey());
            }
            constraintReference = constraintReferenceConstraints;
        }
        if (constraintReference != null) {
            Object obj3 = this.mEndToStart;
            if (obj3 != null) {
                constraintReference.endToStart(obj3);
            } else {
                Object obj4 = this.mEndToEnd;
                if (obj4 != null) {
                    constraintReference.endToEnd(obj4);
                } else {
                    constraintReference.endToEnd(State.PARENT);
                }
            }
        }
        if (constraintReference2 != null) {
            float f7 = this.mBias;
            if (f7 != 0.5f) {
                constraintReference2.horizontalBias(f7);
            }
        }
        int i7 = C00881.$SwitchMap$android$support$constraint$solver$state$State$Chain[this.mStyle.ordinal()];
        if (i7 == 1) {
            constraintReference2.setHorizontalChainStyle(0);
        } else if (i7 == 2) {
            constraintReference2.setHorizontalChainStyle(1);
        } else {
            if (i7 != 3) {
                return;
            }
            constraintReference2.setHorizontalChainStyle(2);
        }
    }

    public void endToEnd(Object obj) {
        this.mEndToEnd = obj;
    }

    public void endToStart(Object obj) {
        this.mEndToStart = obj;
    }

    public void startToEnd(Object obj) {
        this.mStartToEnd = obj;
    }

    public void startToStart(Object obj) {
        this.mStartToStart = obj;
    }
}
