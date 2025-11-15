package android.support.constraint.solver.state.helpers;

import android.support.constraint.solver.state.HelperReference;
import android.support.constraint.solver.state.State;
import android.support.constraint.solver.widgets.Barrier;
import android.support.constraint.solver.widgets.HelperWidget;

/* loaded from: classes.dex */
public class BarrierReference extends HelperReference {
    private Barrier mBarrierWidget;
    private State.Direction mDirection;
    private int mMargin;

    /* renamed from: android.support.constraint.solver.state.helpers.BarrierReference$1 */
    public static /* synthetic */ class C00871 {

        /* renamed from: $SwitchMap$android$support$constraint$solver$state$State$Direction */
        public static final /* synthetic */ int[] f135x83030585;

        static {
            int[] iArr = new int[State.Direction.values().length];
            f135x83030585 = iArr;
            try {
                iArr[State.Direction.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f135x83030585[State.Direction.START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f135x83030585[State.Direction.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f135x83030585[State.Direction.END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f135x83030585[State.Direction.TOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f135x83030585[State.Direction.BOTTOM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public BarrierReference(State state) {
        super(state, State.Helper.BARRIER);
    }

    @Override // android.support.constraint.solver.state.HelperReference
    public void apply() {
        getHelperWidget();
        int i7 = 0;
        switch (C00871.f135x83030585[this.mDirection.ordinal()]) {
            case 3:
            case 4:
                i7 = 1;
                break;
            case 5:
                i7 = 2;
                break;
            case 6:
                i7 = 3;
                break;
        }
        this.mBarrierWidget.setBarrierType(i7);
        this.mBarrierWidget.setMargin(this.mMargin);
    }

    @Override // android.support.constraint.solver.state.HelperReference
    public HelperWidget getHelperWidget() {
        if (this.mBarrierWidget == null) {
            this.mBarrierWidget = new Barrier();
        }
        return this.mBarrierWidget;
    }

    public void margin(Object obj) {
        margin(this.mState.convertDimension(obj));
    }

    public void setBarrierDirection(State.Direction direction) {
        this.mDirection = direction;
    }

    public void margin(int i7) {
        this.mMargin = i7;
    }
}
