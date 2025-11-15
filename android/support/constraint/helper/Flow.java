package android.support.constraint.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.C0071R;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.VirtualLayout;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.HelperWidget;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;

/* loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int HORIZONTAL = 0;
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    private static final String TAG = "Flow";
    public static final int VERTICAL = 1;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private android.support.constraint.solver.widgets.Flow mFlow;

    public Flow(Context context) {
        super(context);
    }

    @Override // android.support.constraint.VirtualLayout, android.support.constraint.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mFlow = new android.support.constraint.solver.widgets.Flow();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0071R.styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.ConstraintLayout_Layout_android_orientation) {
                    this.mFlow.setOrientation(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_android_padding) {
                    this.mFlow.setPadding(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_android_paddingStart) {
                    this.mFlow.setPaddingStart(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.mFlow.setPaddingEnd(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.mFlow.setPaddingLeft(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.mFlow.setPaddingTop(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.mFlow.setPaddingRight(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.mFlow.setPaddingBottom(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.mFlow.setWrapMode(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.mFlow.setHorizontalStyle(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.mFlow.setVerticalStyle(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.mFlow.setFirstHorizontalStyle(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.mFlow.setLastHorizontalStyle(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.mFlow.setFirstVerticalStyle(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.mFlow.setLastVerticalStyle(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.mFlow.setHorizontalBias(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.mFlow.setFirstHorizontalBias(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.mFlow.setLastHorizontalBias(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.mFlow.setFirstVerticalBias(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.mFlow.setLastVerticalBias(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.mFlow.setVerticalBias(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.mFlow.setHorizontalAlign(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.mFlow.setVerticalAlign(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.mFlow.setHorizontalGap(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.mFlow.setVerticalGap(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.mFlow.setMaxElementsWrap(typedArrayObtainStyledAttributes.getInt(index, -1));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.mHelperWidget = this.mFlow;
        validateParams();
    }

    @Override // android.support.constraint.ConstraintHelper
    public void loadParameters(ConstraintSet.Constraint constraint, HelperWidget helperWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        super.loadParameters(constraint, helperWidget, layoutParams, sparseArray);
        if (helperWidget instanceof android.support.constraint.solver.widgets.Flow) {
            android.support.constraint.solver.widgets.Flow flow = (android.support.constraint.solver.widgets.Flow) helperWidget;
            int i7 = layoutParams.orientation;
            if (i7 != -1) {
                flow.setOrientation(i7);
            }
        }
    }

    @Override // android.support.constraint.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    public void onMeasure(int i7, int i8) {
        onMeasure(this.mFlow, i7, i8);
    }

    @Override // android.support.constraint.ConstraintHelper
    public void resolveRtl(ConstraintWidget constraintWidget, boolean z6) {
        this.mFlow.applyRtl(z6);
    }

    public void setFirstHorizontalBias(float f7) {
        this.mFlow.setFirstHorizontalBias(f7);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i7) {
        this.mFlow.setFirstHorizontalStyle(i7);
        requestLayout();
    }

    public void setFirstVerticalBias(float f7) {
        this.mFlow.setFirstVerticalBias(f7);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i7) {
        this.mFlow.setFirstVerticalStyle(i7);
        requestLayout();
    }

    public void setHorizontalAlign(int i7) {
        this.mFlow.setHorizontalAlign(i7);
        requestLayout();
    }

    public void setHorizontalBias(float f7) {
        this.mFlow.setHorizontalBias(f7);
        requestLayout();
    }

    public void setHorizontalGap(int i7) {
        this.mFlow.setHorizontalGap(i7);
        requestLayout();
    }

    public void setHorizontalStyle(int i7) {
        this.mFlow.setHorizontalStyle(i7);
        requestLayout();
    }

    public void setMaxElementsWrap(int i7) {
        this.mFlow.setMaxElementsWrap(i7);
        requestLayout();
    }

    public void setOrientation(int i7) {
        this.mFlow.setOrientation(i7);
        requestLayout();
    }

    public void setPadding(int i7) {
        this.mFlow.setPadding(i7);
        requestLayout();
    }

    public void setPaddingBottom(int i7) {
        this.mFlow.setPaddingBottom(i7);
        requestLayout();
    }

    public void setPaddingLeft(int i7) {
        this.mFlow.setPaddingLeft(i7);
        requestLayout();
    }

    public void setPaddingRight(int i7) {
        this.mFlow.setPaddingRight(i7);
        requestLayout();
    }

    public void setPaddingTop(int i7) {
        this.mFlow.setPaddingTop(i7);
        requestLayout();
    }

    public void setVerticalAlign(int i7) {
        this.mFlow.setVerticalAlign(i7);
        requestLayout();
    }

    public void setVerticalBias(float f7) {
        this.mFlow.setVerticalBias(f7);
        requestLayout();
    }

    public void setVerticalGap(int i7) {
        this.mFlow.setVerticalGap(i7);
        requestLayout();
    }

    public void setVerticalStyle(int i7) {
        this.mFlow.setVerticalStyle(i7);
        requestLayout();
    }

    public void setWrapMode(int i7) {
        this.mFlow.setWrapMode(i7);
        requestLayout();
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.support.constraint.VirtualLayout
    public void onMeasure(android.support.constraint.solver.widgets.VirtualLayout virtualLayout, int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        int mode2 = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i8);
        if (virtualLayout == null) {
            setMeasuredDimension(0, 0);
        } else {
            virtualLayout.measure(mode, size, mode2, size2);
            setMeasuredDimension(virtualLayout.getMeasuredWidth(), virtualLayout.getMeasuredHeight());
        }
    }

    public Flow(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }
}
