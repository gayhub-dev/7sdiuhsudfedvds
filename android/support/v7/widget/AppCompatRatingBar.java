package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.appcompat.C0308R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;

/* loaded from: classes.dex */
public class AppCompatRatingBar extends RatingBar {
    private final AppCompatProgressBarHelper mAppCompatProgressBarHelper;

    public AppCompatRatingBar(Context context) {
        this(context, null);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        Bitmap sampleTime = this.mAppCompatProgressBarHelper.getSampleTime();
        if (sampleTime != null) {
            setMeasuredDimension(View.resolveSizeAndState(sampleTime.getWidth() * getNumStars(), i7, 0), getMeasuredHeight());
        }
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0308R.attr.ratingBarStyle);
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        AppCompatProgressBarHelper appCompatProgressBarHelper = new AppCompatProgressBarHelper(this);
        this.mAppCompatProgressBarHelper = appCompatProgressBarHelper;
        appCompatProgressBarHelper.loadFromAttributes(attributeSet, i7);
    }
}
