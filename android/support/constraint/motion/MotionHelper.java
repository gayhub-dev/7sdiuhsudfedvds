package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.C0071R;
import android.support.constraint.ConstraintHelper;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.motion.MotionLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class MotionHelper extends ConstraintHelper implements Animatable, MotionLayout.TransitionListener {
    private float mProgress;
    private boolean mUseOnHide;
    private boolean mUseOnShow;
    public View[] views;

    public MotionHelper(Context context) {
        super(context);
        this.mUseOnShow = false;
        this.mUseOnHide = false;
    }

    @Override // android.support.constraint.motion.Animatable
    public float getProgress() {
        return this.mProgress;
    }

    @Override // android.support.constraint.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0071R.styleable.MotionHelper);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.MotionHelper_onShow) {
                    this.mUseOnShow = typedArrayObtainStyledAttributes.getBoolean(index, this.mUseOnShow);
                } else if (index == C0071R.styleable.MotionHelper_onHide) {
                    this.mUseOnHide = typedArrayObtainStyledAttributes.getBoolean(index, this.mUseOnHide);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public boolean isUseOnHide() {
        return this.mUseOnHide;
    }

    public boolean isUsedOnShow() {
        return this.mUseOnShow;
    }

    @Override // android.support.constraint.motion.MotionLayout.TransitionListener
    public void onTransitionChange(MotionLayout motionLayout, int i7, int i8, float f7) {
    }

    @Override // android.support.constraint.motion.MotionLayout.TransitionListener
    public void onTransitionCompleted(MotionLayout motionLayout, int i7) {
    }

    @Override // android.support.constraint.motion.MotionLayout.TransitionListener
    public void onTransitionStarted(MotionLayout motionLayout, int i7, int i8) {
    }

    @Override // android.support.constraint.motion.MotionLayout.TransitionListener
    public void onTransitionTrigger(MotionLayout motionLayout, int i7, boolean z6, float f7) {
    }

    @Override // android.support.constraint.motion.Animatable
    public void setProgress(float f7) {
        this.mProgress = f7;
        int i7 = 0;
        if (this.mCount > 0) {
            this.views = getViews((ConstraintLayout) getParent());
            while (i7 < this.mCount) {
                setProgress(this.views[i7], f7);
                i7++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i7 < childCount) {
            View childAt = viewGroup.getChildAt(i7);
            if (!(childAt instanceof MotionHelper)) {
                setProgress(childAt, f7);
            }
            i7++;
        }
    }

    public void setProgress(View view, float f7) {
    }

    public MotionHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUseOnShow = false;
        this.mUseOnHide = false;
        init(attributeSet);
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.mUseOnShow = false;
        this.mUseOnHide = false;
        init(attributeSet);
    }
}
