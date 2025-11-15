package android.support.constraint.motion;

import android.view.animation.Interpolator;

/* loaded from: classes.dex */
public abstract class MotionInterpolator implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public abstract float getInterpolation(float f7);

    public abstract float getVelocity();
}
