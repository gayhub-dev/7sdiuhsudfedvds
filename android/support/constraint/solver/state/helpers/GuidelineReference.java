package android.support.constraint.solver.state.helpers;

import android.support.constraint.solver.state.Reference;
import android.support.constraint.solver.state.State;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.Guideline;

/* loaded from: classes.dex */
public class GuidelineReference implements Reference {
    private Object key;
    private Guideline mGuidelineWidget;
    private int mOrientation;
    public final State mState;
    private int mStart = -1;
    private int mEnd = -1;
    private float mPercent = 0.0f;

    public GuidelineReference(State state) {
        this.mState = state;
    }

    @Override // android.support.constraint.solver.state.Reference
    public void apply() {
        this.mGuidelineWidget.setOrientation(this.mOrientation);
        int i7 = this.mStart;
        if (i7 != -1) {
            this.mGuidelineWidget.setGuideBegin(i7);
            return;
        }
        int i8 = this.mEnd;
        if (i8 != -1) {
            this.mGuidelineWidget.setGuideEnd(i8);
        } else {
            this.mGuidelineWidget.setGuidePercent(this.mPercent);
        }
    }

    public void end(Object obj) {
        this.mStart = -1;
        this.mEnd = this.mState.convertDimension(obj);
        this.mPercent = 0.0f;
    }

    @Override // android.support.constraint.solver.state.Reference
    public ConstraintWidget getConstraintWidget() {
        if (this.mGuidelineWidget == null) {
            this.mGuidelineWidget = new Guideline();
        }
        return this.mGuidelineWidget;
    }

    @Override // android.support.constraint.solver.state.Reference
    public Object getKey() {
        return this.key;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void percent(float f7) {
        this.mStart = -1;
        this.mEnd = -1;
        this.mPercent = f7;
    }

    @Override // android.support.constraint.solver.state.Reference
    public void setConstraintWidget(ConstraintWidget constraintWidget) {
        if (constraintWidget instanceof Guideline) {
            this.mGuidelineWidget = (Guideline) constraintWidget;
        } else {
            this.mGuidelineWidget = null;
        }
    }

    @Override // android.support.constraint.solver.state.Reference
    public void setKey(Object obj) {
        this.key = obj;
    }

    public void setOrientation(int i7) {
        this.mOrientation = i7;
    }

    public void start(Object obj) {
        this.mStart = this.mState.convertDimension(obj);
        this.mEnd = -1;
        this.mPercent = 0.0f;
    }
}
