package android.support.v4.graphics;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
public final class PathUtils {
    private PathUtils() {
    }

    @NonNull
    @RequiresApi(26)
    public static Collection<PathSegment> flatten(@NonNull Path path) {
        return flatten(path, 0.5f);
    }

    @NonNull
    @RequiresApi(26)
    public static Collection<PathSegment> flatten(@NonNull Path path, @FloatRange(from = 0.0d) float f7) {
        float[] fArrApproximate = path.approximate(f7);
        int length = fArrApproximate.length / 3;
        ArrayList arrayList = new ArrayList(length);
        for (int i7 = 1; i7 < length; i7++) {
            int i8 = i7 * 3;
            int i9 = (i7 - 1) * 3;
            float f8 = fArrApproximate[i8];
            float f9 = fArrApproximate[i8 + 1];
            float f10 = fArrApproximate[i8 + 2];
            float f11 = fArrApproximate[i9];
            float f12 = fArrApproximate[i9 + 1];
            float f13 = fArrApproximate[i9 + 2];
            if (f8 != f11 && (f9 != f12 || f10 != f13)) {
                arrayList.add(new PathSegment(new PointF(f12, f13), f11, new PointF(f9, f10), f8));
            }
        }
        return arrayList;
    }
}
