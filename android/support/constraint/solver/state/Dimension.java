package android.support.constraint.solver.state;

import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.v7.widget.ActivityChooserView;

/* loaded from: classes.dex */
public class Dimension {
    private final int WRAP_CONTENT;
    public Object mInitialValue;
    public boolean mIsSuggested;
    public int mMax;
    public int mMin;
    public float mPercent;
    public float mRatio;
    public int mValue;
    public static final Object FIXED_DIMENSION = new Object();
    public static final Object WRAP_DIMENSION = new Object();
    public static final Object SPREAD_DIMENSION = new Object();
    public static final Object PARENT_DIMENSION = new Object();
    public static final Object PERCENT_DIMENSION = new Object();

    public enum Type {
        FIXED,
        WRAP,
        MATCH_PARENT,
        MATCH_CONSTRAINT
    }

    private Dimension() {
        this.WRAP_CONTENT = -2;
        this.mMin = 0;
        this.mMax = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mPercent = 1.0f;
        this.mValue = 0;
        this.mRatio = 1.0f;
        this.mInitialValue = WRAP_DIMENSION;
        this.mIsSuggested = false;
    }

    public static Dimension Fixed(int i7) {
        Dimension dimension = new Dimension(FIXED_DIMENSION);
        dimension.fixed(i7);
        return dimension;
    }

    public static Dimension Parent() {
        return new Dimension(PARENT_DIMENSION);
    }

    public static Dimension Percent(Object obj, float f7) {
        Dimension dimension = new Dimension(PERCENT_DIMENSION);
        dimension.percent(obj, f7);
        return dimension;
    }

    public static Dimension Spread() {
        return new Dimension(SPREAD_DIMENSION);
    }

    public static Dimension Suggested(int i7) {
        Dimension dimension = new Dimension();
        dimension.suggested(i7);
        return dimension;
    }

    public static Dimension Wrap() {
        return new Dimension(WRAP_DIMENSION);
    }

    public void apply(State state, ConstraintWidget constraintWidget, int i7) {
        int i8 = 2;
        if (i7 == 0) {
            if (this.mIsSuggested) {
                constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                Object obj = this.mInitialValue;
                if (obj == WRAP_DIMENSION) {
                    i8 = 1;
                } else if (obj != PERCENT_DIMENSION) {
                    i8 = 0;
                }
                constraintWidget.setHorizontalMatchStyle(i8, this.mMin, this.mMax, this.mPercent);
                return;
            }
            int i9 = this.mMin;
            if (i9 > 0) {
                constraintWidget.setMinWidth(i9);
            }
            int i10 = this.mMax;
            if (i10 < Integer.MAX_VALUE) {
                constraintWidget.setMaxWidth(i10);
            }
            Object obj2 = this.mInitialValue;
            if (obj2 == WRAP_DIMENSION) {
                constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                return;
            }
            if (obj2 == PARENT_DIMENSION) {
                constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
                return;
            } else {
                if (obj2 == null) {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    constraintWidget.setWidth(this.mValue);
                    return;
                }
                return;
            }
        }
        if (this.mIsSuggested) {
            constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            Object obj3 = this.mInitialValue;
            if (obj3 == WRAP_DIMENSION) {
                i8 = 1;
            } else if (obj3 != PERCENT_DIMENSION) {
                i8 = 0;
            }
            constraintWidget.setVerticalMatchStyle(i8, this.mMin, this.mMax, this.mPercent);
            return;
        }
        int i11 = this.mMin;
        if (i11 > 0) {
            constraintWidget.setMinHeight(i11);
        }
        int i12 = this.mMax;
        if (i12 < Integer.MAX_VALUE) {
            constraintWidget.setMaxHeight(i12);
        }
        Object obj4 = this.mInitialValue;
        if (obj4 == WRAP_DIMENSION) {
            constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
            return;
        }
        if (obj4 == PARENT_DIMENSION) {
            constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
        } else if (obj4 == null) {
            constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            constraintWidget.setHeight(this.mValue);
        }
    }

    public Dimension fixed(Object obj) {
        this.mInitialValue = obj;
        if (obj instanceof Integer) {
            this.mValue = ((Integer) obj).intValue();
            this.mInitialValue = null;
        }
        return this;
    }

    public float getRatio() {
        return this.mRatio;
    }

    public int getValue() {
        return this.mValue;
    }

    public Dimension max(int i7) {
        if (this.mMax >= 0) {
            this.mMax = i7;
        }
        return this;
    }

    public Dimension min(int i7) {
        if (i7 >= 0) {
            this.mMin = i7;
        }
        return this;
    }

    public Dimension percent(Object obj, float f7) {
        this.mPercent = f7;
        return this;
    }

    public Dimension ratio(float f7) {
        return this;
    }

    public void setRatio(float f7) {
        this.mRatio = f7;
    }

    public void setValue(int i7) {
        this.mIsSuggested = false;
        this.mInitialValue = null;
        this.mValue = i7;
    }

    public Dimension suggested(int i7) {
        this.mIsSuggested = true;
        return this;
    }

    public Dimension min(Object obj) {
        if (obj == WRAP_DIMENSION) {
            this.mMin = -2;
        }
        return this;
    }

    public Dimension suggested(Object obj) {
        this.mInitialValue = obj;
        this.mIsSuggested = true;
        return this;
    }

    public static Dimension Fixed(Object obj) {
        Dimension dimension = new Dimension(FIXED_DIMENSION);
        dimension.fixed(obj);
        return dimension;
    }

    public static Dimension Suggested(Object obj) {
        Dimension dimension = new Dimension();
        dimension.suggested(obj);
        return dimension;
    }

    public Dimension max(Object obj) {
        Object obj2 = WRAP_DIMENSION;
        if (obj == obj2 && this.mIsSuggested) {
            this.mInitialValue = obj2;
            this.mMax = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        return this;
    }

    public Dimension fixed(int i7) {
        this.mInitialValue = null;
        this.mValue = i7;
        return this;
    }

    private Dimension(Object obj) {
        this.WRAP_CONTENT = -2;
        this.mMin = 0;
        this.mMax = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mPercent = 1.0f;
        this.mValue = 0;
        this.mRatio = 1.0f;
        this.mInitialValue = WRAP_DIMENSION;
        this.mIsSuggested = false;
        this.mInitialValue = obj;
    }
}
