package android.support.v4.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* loaded from: classes.dex */
public final class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
    }

    public static Interpolator create(Path path) {
        return new PathInterpolator(path);
    }

    public static Interpolator create(float f7, float f8) {
        return new PathInterpolator(f7, f8);
    }

    public static Interpolator create(float f7, float f8, float f9, float f10) {
        return new PathInterpolator(f7, f8, f9, f10);
    }
}
