package android.support.v4.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

/* loaded from: classes.dex */
class PathInterpolatorApi14 implements Interpolator {
    private static final float PRECISION = 0.002f;

    /* renamed from: mX */
    private final float[] f156mX;

    /* renamed from: mY */
    private final float[] f157mY;

    public PathInterpolatorApi14(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int i7 = ((int) (length / PRECISION)) + 1;
        this.f156mX = new float[i7];
        this.f157mY = new float[i7];
        float[] fArr = new float[2];
        for (int i8 = 0; i8 < i7; i8++) {
            pathMeasure.getPosTan((i8 * length) / (i7 - 1), fArr, null);
            this.f156mX[i8] = fArr[0];
            this.f157mY[i8] = fArr[1];
        }
    }

    private static Path createCubic(float f7, float f8, float f9, float f10) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f7, f8, f9, f10, 1.0f, 1.0f);
        return path;
    }

    private static Path createQuad(float f7, float f8) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f7, f8, 1.0f, 1.0f);
        return path;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f7) {
        if (f7 <= 0.0f) {
            return 0.0f;
        }
        if (f7 >= 1.0f) {
            return 1.0f;
        }
        int i7 = 0;
        int length = this.f156mX.length - 1;
        while (length - i7 > 1) {
            int i8 = (i7 + length) / 2;
            if (f7 < this.f156mX[i8]) {
                length = i8;
            } else {
                i7 = i8;
            }
        }
        float[] fArr = this.f156mX;
        float f8 = fArr[length] - fArr[i7];
        if (f8 == 0.0f) {
            return this.f157mY[i7];
        }
        float f9 = (f7 - fArr[i7]) / f8;
        float[] fArr2 = this.f157mY;
        float f10 = fArr2[i7];
        return ((fArr2[length] - f10) * f9) + f10;
    }

    public PathInterpolatorApi14(float f7, float f8) {
        this(createQuad(f7, f8));
    }

    public PathInterpolatorApi14(float f7, float f8, float f9, float f10) {
        this(createCubic(f7, f8, f9, f10));
    }
}
