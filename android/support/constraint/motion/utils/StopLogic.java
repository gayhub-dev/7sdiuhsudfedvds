package android.support.constraint.motion.utils;

import android.support.constraint.motion.MotionInterpolator;

/* loaded from: classes.dex */
public class StopLogic extends MotionInterpolator {
    private boolean mBackwards = false;
    private float mLastPosition;
    private int mNumberOfStages;
    private float mStage1Duration;
    private float mStage1EndPosition;
    private float mStage1Velocity;
    private float mStage2Duration;
    private float mStage2EndPosition;
    private float mStage2Velocity;
    private float mStage3Duration;
    private float mStage3EndPosition;
    private float mStage3Velocity;
    private float mStartPosition;
    private String mType;

    private float calcY(float f7) {
        float f8 = this.mStage1Duration;
        if (f7 <= f8) {
            float f9 = this.mStage1Velocity;
            return ((((this.mStage2Velocity - f9) * f7) * f7) / (f8 * 2.0f)) + (f9 * f7);
        }
        int i7 = this.mNumberOfStages;
        if (i7 == 1) {
            return this.mStage1EndPosition;
        }
        float f10 = f7 - f8;
        float f11 = this.mStage2Duration;
        if (f10 < f11) {
            float f12 = this.mStage1EndPosition;
            float f13 = this.mStage2Velocity;
            return ((((this.mStage3Velocity - f13) * f10) * f10) / (f11 * 2.0f)) + (f13 * f10) + f12;
        }
        if (i7 == 2) {
            return this.mStage2EndPosition;
        }
        float f14 = f10 - f11;
        float f15 = this.mStage3Duration;
        if (f14 >= f15) {
            return this.mStage3EndPosition;
        }
        float f16 = this.mStage2EndPosition;
        float f17 = this.mStage3Velocity;
        return ((f17 * f14) + f16) - (((f17 * f14) * f14) / (f15 * 2.0f));
    }

    private void setup(float f7, float f8, float f9, float f10, float f11) {
        if (f7 == 0.0f) {
            f7 = 1.0E-4f;
        }
        this.mStage1Velocity = f7;
        float f12 = f7 / f9;
        float f13 = (f12 * f7) / 2.0f;
        if (f7 < 0.0f) {
            float fSqrt = (float) Math.sqrt((f8 - ((((-f7) / f9) * f7) / 2.0f)) * f9);
            if (fSqrt < f10) {
                this.mType = "backward accelerate, decelerate";
                this.mNumberOfStages = 2;
                this.mStage1Velocity = f7;
                this.mStage2Velocity = fSqrt;
                this.mStage3Velocity = 0.0f;
                float f14 = (fSqrt - f7) / f9;
                this.mStage1Duration = f14;
                this.mStage2Duration = fSqrt / f9;
                this.mStage1EndPosition = ((f7 + fSqrt) * f14) / 2.0f;
                this.mStage2EndPosition = f8;
                this.mStage3EndPosition = f8;
                return;
            }
            this.mType = "backward accelerate cruse decelerate";
            this.mNumberOfStages = 3;
            this.mStage1Velocity = f7;
            this.mStage2Velocity = f10;
            this.mStage3Velocity = f10;
            float f15 = (f10 - f7) / f9;
            this.mStage1Duration = f15;
            float f16 = f10 / f9;
            this.mStage3Duration = f16;
            float f17 = ((f7 + f10) * f15) / 2.0f;
            float f18 = (f16 * f10) / 2.0f;
            this.mStage2Duration = ((f8 - f17) - f18) / f10;
            this.mStage1EndPosition = f17;
            this.mStage2EndPosition = f8 - f18;
            this.mStage3EndPosition = f8;
            return;
        }
        if (f13 >= f8) {
            this.mType = "hard stop";
            this.mNumberOfStages = 1;
            this.mStage1Velocity = f7;
            this.mStage2Velocity = 0.0f;
            this.mStage1EndPosition = f8;
            this.mStage1Duration = (2.0f * f8) / f7;
            return;
        }
        float f19 = f8 - f13;
        float f20 = f19 / f7;
        if (f20 + f12 < f11) {
            this.mType = "cruse decelerate";
            this.mNumberOfStages = 2;
            this.mStage1Velocity = f7;
            this.mStage2Velocity = f7;
            this.mStage3Velocity = 0.0f;
            this.mStage1EndPosition = f19;
            this.mStage2EndPosition = f8;
            this.mStage1Duration = f20;
            this.mStage2Duration = f12;
            return;
        }
        float fSqrt2 = (float) Math.sqrt(((f7 * f7) / 2.0f) + (f9 * f8));
        float f21 = (fSqrt2 - f7) / f9;
        this.mStage1Duration = f21;
        float f22 = fSqrt2 / f9;
        this.mStage2Duration = f22;
        if (fSqrt2 < f10) {
            this.mType = "accelerate decelerate";
            this.mNumberOfStages = 2;
            this.mStage1Velocity = f7;
            this.mStage2Velocity = fSqrt2;
            this.mStage3Velocity = 0.0f;
            this.mStage1Duration = f21;
            this.mStage2Duration = f22;
            this.mStage1EndPosition = ((f7 + fSqrt2) * f21) / 2.0f;
            this.mStage2EndPosition = f8;
            return;
        }
        this.mType = "accelerate cruse decelerate";
        this.mNumberOfStages = 3;
        this.mStage1Velocity = f7;
        this.mStage2Velocity = f10;
        this.mStage3Velocity = f10;
        float f23 = (f10 - f7) / f9;
        this.mStage1Duration = f23;
        float f24 = f10 / f9;
        this.mStage3Duration = f24;
        float f25 = ((f7 + f10) * f23) / 2.0f;
        float f26 = (f24 * f10) / 2.0f;
        this.mStage2Duration = ((f8 - f25) - f26) / f10;
        this.mStage1EndPosition = f25;
        this.mStage2EndPosition = f8 - f26;
        this.mStage3EndPosition = f8;
    }

    public void config(float f7, float f8, float f9, float f10, float f11, float f12) {
        this.mStartPosition = f7;
        boolean z6 = f7 > f8;
        this.mBackwards = z6;
        if (z6) {
            setup(-f9, f7 - f8, f11, f12, f10);
        } else {
            setup(f9, f8 - f7, f11, f12, f10);
        }
    }

    public void debug(String str, String str2, float f7) {
    }

    @Override // android.support.constraint.motion.MotionInterpolator, android.animation.TimeInterpolator
    public float getInterpolation(float f7) {
        float fCalcY = calcY(f7);
        this.mLastPosition = f7;
        return this.mBackwards ? this.mStartPosition - fCalcY : this.mStartPosition + fCalcY;
    }

    public float getVelocity(float f7) {
        float f8;
        float f9;
        float f10 = this.mStage1Duration;
        if (f7 <= f10) {
            f8 = this.mStage1Velocity;
            f9 = this.mStage2Velocity;
        } else {
            int i7 = this.mNumberOfStages;
            if (i7 == 1) {
                return 0.0f;
            }
            f7 -= f10;
            f10 = this.mStage2Duration;
            if (f7 >= f10) {
                if (i7 == 2) {
                    return this.mStage2EndPosition;
                }
                float f11 = f7 - f10;
                float f12 = this.mStage3Duration;
                if (f11 >= f12) {
                    return this.mStage3EndPosition;
                }
                float f13 = this.mStage3Velocity;
                return f13 - ((f11 * f13) / f12);
            }
            f8 = this.mStage2Velocity;
            f9 = this.mStage3Velocity;
        }
        return (((f9 - f8) * f7) / f10) + f8;
    }

    @Override // android.support.constraint.motion.MotionInterpolator
    public float getVelocity() {
        return this.mBackwards ? -getVelocity(this.mLastPosition) : getVelocity(this.mLastPosition);
    }
}
