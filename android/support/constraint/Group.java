package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class Group extends ConstraintHelper {
    public Group(Context context) {
        super(context);
    }

    @Override // android.support.constraint.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mUseViewMeasure = false;
    }

    @Override // android.support.constraint.ConstraintHelper, android.view.View
    public void onAttachedToWindow() throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        super.onAttachedToWindow();
        applyLayoutFeatures();
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        applyLayoutFeatures();
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        applyLayoutFeatures();
    }

    @Override // android.support.constraint.ConstraintHelper
    public void updatePostLayout(ConstraintLayout constraintLayout) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.widget.setWidth(0);
        layoutParams.widget.setHeight(0);
    }

    public Group(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Group(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }
}
