package android.support.v4.view.animation;

import android.view.animation.Interpolator;

/* loaded from: classes.dex */
abstract class LookupTableInterpolator implements Interpolator {
    private final float mStepSize;
    private final float[] mValues;

    public LookupTableInterpolator(float[] fArr) {
        this.mValues = fArr;
        this.mStepSize = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f7) {
        if (f7 >= 1.0f) {
            return 1.0f;
        }
        if (f7 <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.mValues;
        int iMin = Math.min((int) ((fArr.length - 1) * f7), fArr.length - 2);
        float f8 = this.mStepSize;
        float f9 = (f7 - (iMin * f8)) / f8;
        float[] fArr2 = this.mValues;
        return ((fArr2[iMin + 1] - fArr2[iMin]) * f9) + fArr2[iMin];
    }
}
