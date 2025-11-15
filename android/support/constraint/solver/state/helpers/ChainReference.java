package android.support.constraint.solver.state.helpers;

import android.support.constraint.solver.state.HelperReference;
import android.support.constraint.solver.state.State;

/* loaded from: classes.dex */
public class ChainReference extends HelperReference {
    public float mBias;
    public State.Chain mStyle;

    public ChainReference(State state, State.Helper helper) {
        super(state, helper);
        this.mBias = 0.5f;
        this.mStyle = State.Chain.SPREAD;
    }

    public void bias(float f7) {
        this.mBias = f7;
    }

    public float getBias() {
        return this.mBias;
    }

    public State.Chain getStyle() {
        return State.Chain.SPREAD;
    }

    public void style(State.Chain chain) {
        this.mStyle = chain;
    }
}
