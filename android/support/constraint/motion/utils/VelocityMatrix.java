package android.support.constraint.motion.utils;

import android.support.constraint.motion.KeyCycleOscillator;
import android.support.constraint.motion.SplineSet;

/* loaded from: classes.dex */
public class VelocityMatrix {
    private static String TAG = "VelocityMatrix";
    public float mDRotate;
    public float mDScaleX;
    public float mDScaleY;
    public float mDTranslateX;
    public float mDTranslateY;
    public float mRotate;

    public void applyTransform(float f7, float f8, int i7, int i8, float[] fArr) {
        float f9 = fArr[0];
        float f10 = fArr[1];
        float f11 = (f8 - 0.5f) * 2.0f;
        float f12 = f9 + this.mDTranslateX;
        float f13 = f10 + this.mDTranslateY;
        float f14 = (this.mDScaleX * (f7 - 0.5f) * 2.0f) + f12;
        float f15 = (this.mDScaleY * f11) + f13;
        float radians = (float) Math.toRadians(this.mRotate);
        float radians2 = (float) Math.toRadians(this.mDRotate);
        double d7 = radians;
        double d8 = i8 * f11;
        float fSin = (((float) ((Math.sin(d7) * ((-i7) * r7)) - (Math.cos(d7) * d8))) * radians2) + f14;
        float fCos = (radians2 * ((float) ((Math.cos(d7) * (i7 * r7)) - (Math.sin(d7) * d8)))) + f15;
        fArr[0] = fSin;
        fArr[1] = fCos;
    }

    public void clear() {
        this.mDRotate = 0.0f;
        this.mDTranslateY = 0.0f;
        this.mDTranslateX = 0.0f;
        this.mDScaleY = 0.0f;
        this.mDScaleX = 0.0f;
    }

    public void setRotationVelocity(SplineSet splineSet, float f7) {
        if (splineSet != null) {
            this.mDRotate = splineSet.getSlope(f7);
            this.mRotate = splineSet.get(f7);
        }
    }

    public void setScaleVelocity(SplineSet splineSet, SplineSet splineSet2, float f7) {
        if (splineSet != null) {
            this.mDScaleX = splineSet.getSlope(f7);
        }
        if (splineSet2 != null) {
            this.mDScaleY = splineSet2.getSlope(f7);
        }
    }

    public void setTranslationVelocity(SplineSet splineSet, SplineSet splineSet2, float f7) {
        if (splineSet != null) {
            this.mDTranslateX = splineSet.getSlope(f7);
        }
        if (splineSet2 != null) {
            this.mDTranslateY = splineSet2.getSlope(f7);
        }
    }

    public void setRotationVelocity(KeyCycleOscillator keyCycleOscillator, float f7) {
        if (keyCycleOscillator != null) {
            this.mDRotate = keyCycleOscillator.getSlope(f7);
        }
    }

    public void setScaleVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f7) {
        if (keyCycleOscillator == null && keyCycleOscillator2 == null) {
            return;
        }
        if (keyCycleOscillator == null) {
            this.mDScaleX = keyCycleOscillator.getSlope(f7);
        }
        if (keyCycleOscillator2 == null) {
            this.mDScaleY = keyCycleOscillator2.getSlope(f7);
        }
    }

    public void setTranslationVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f7) {
        if (keyCycleOscillator != null) {
            this.mDTranslateX = keyCycleOscillator.getSlope(f7);
        }
        if (keyCycleOscillator2 != null) {
            this.mDTranslateY = keyCycleOscillator2.getSlope(f7);
        }
    }
}
