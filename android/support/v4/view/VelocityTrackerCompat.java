package android.support.v4.view;

import android.view.VelocityTracker;

@Deprecated
/* loaded from: classes.dex */
public final class VelocityTrackerCompat {
    private VelocityTrackerCompat() {
    }

    @Deprecated
    public static float getXVelocity(VelocityTracker velocityTracker, int i7) {
        return velocityTracker.getXVelocity(i7);
    }

    @Deprecated
    public static float getYVelocity(VelocityTracker velocityTracker, int i7) {
        return velocityTracker.getYVelocity(i7);
    }
}
