package android.support.constraint;

import android.content.Context;
import android.graphics.Canvas;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class Guideline extends View {
    public Guideline(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        setMeasuredDimension(0, 0);
    }

    public void setGuidelineBegin(int i7) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.guideBegin = i7;
        setLayoutParams(layoutParams);
    }

    public void setGuidelineEnd(int i7) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.guideEnd = i7;
        setLayoutParams(layoutParams);
    }

    public void setGuidelinePercent(float f7) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.guidePercent = f7;
        setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
    }

    public Guideline(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeSet, int i7, int i8) {
        super(context, attributeSet, i7);
        super.setVisibility(8);
    }
}
