package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.appcompat.C0308R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class DrawerArrowDrawable extends Drawable {
    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;
    private static final float ARROW_HEAD_ANGLE = (float) Math.toRadians(45.0d);
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private int mDirection;
    private float mMaxCutForBarSize;
    private final Paint mPaint;
    private final Path mPath;
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    private boolean mVerticalMirror;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface ArrowDirection {
    }

    public DrawerArrowDrawable(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mPath = new Path();
        this.mVerticalMirror = false;
        this.mDirection = 2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, C0308R.styleable.DrawerArrowToggle, C0308R.attr.drawerArrowStyle, C0308R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        setColor(typedArrayObtainStyledAttributes.getColor(C0308R.styleable.DrawerArrowToggle_color, 0));
        setBarThickness(typedArrayObtainStyledAttributes.getDimension(C0308R.styleable.DrawerArrowToggle_thickness, 0.0f));
        setSpinEnabled(typedArrayObtainStyledAttributes.getBoolean(C0308R.styleable.DrawerArrowToggle_spinBars, true));
        setGapSize(Math.round(typedArrayObtainStyledAttributes.getDimension(C0308R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.mSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0308R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.mBarLength = Math.round(typedArrayObtainStyledAttributes.getDimension(C0308R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.mArrowHeadLength = Math.round(typedArrayObtainStyledAttributes.getDimension(C0308R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.mArrowShaftLength = typedArrayObtainStyledAttributes.getDimension(C0308R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    private static float lerp(float f7, float f8, float f9) {
        return ((f8 - f7) * f9) + f7;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i7 = this.mDirection;
        boolean z6 = false;
        if (i7 != 0 && (i7 == 1 || (i7 == 3 ? DrawableCompat.getLayoutDirection(this) == 0 : DrawableCompat.getLayoutDirection(this) == 1))) {
            z6 = true;
        }
        float f7 = this.mArrowHeadLength;
        float fLerp = lerp(this.mBarLength, (float) Math.sqrt(f7 * f7 * 2.0f), this.mProgress);
        float fLerp2 = lerp(this.mBarLength, this.mArrowShaftLength, this.mProgress);
        float fRound = Math.round(lerp(0.0f, this.mMaxCutForBarSize, this.mProgress));
        float fLerp3 = lerp(0.0f, ARROW_HEAD_ANGLE, this.mProgress);
        float fLerp4 = lerp(z6 ? 0.0f : -180.0f, z6 ? 180.0f : 0.0f, this.mProgress);
        double d7 = fLerp;
        double d8 = fLerp3;
        boolean z7 = z6;
        float fRound2 = Math.round(Math.cos(d8) * d7);
        float fRound3 = Math.round(Math.sin(d8) * d7);
        this.mPath.rewind();
        float fLerp5 = lerp(this.mPaint.getStrokeWidth() + this.mBarGap, -this.mMaxCutForBarSize, this.mProgress);
        float f8 = (-fLerp2) / 2.0f;
        this.mPath.moveTo(f8 + fRound, 0.0f);
        this.mPath.rLineTo(fLerp2 - (fRound * 2.0f), 0.0f);
        this.mPath.moveTo(f8, fLerp5);
        this.mPath.rLineTo(fRound2, fRound3);
        this.mPath.moveTo(f8, -fLerp5);
        this.mPath.rLineTo(fRound2, -fRound3);
        this.mPath.close();
        canvas.save();
        float strokeWidth = this.mPaint.getStrokeWidth();
        float fHeight = bounds.height() - (3.0f * strokeWidth);
        canvas.translate(bounds.centerX(), (strokeWidth * 1.5f) + this.mBarGap + ((((int) (fHeight - (2.0f * r5))) / 4) * 2));
        if (this.mSpin) {
            canvas.rotate(fLerp4 * (this.mVerticalMirror ^ z7 ? -1 : 1));
        } else if (z7) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restore();
    }

    public float getArrowHeadLength() {
        return this.mArrowHeadLength;
    }

    public float getArrowShaftLength() {
        return this.mArrowShaftLength;
    }

    public float getBarLength() {
        return this.mBarLength;
    }

    public float getBarThickness() {
        return this.mPaint.getStrokeWidth();
    }

    @ColorInt
    public int getColor() {
        return this.mPaint.getColor();
    }

    public int getDirection() {
        return this.mDirection;
    }

    public float getGapSize() {
        return this.mBarGap;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mSize;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mSize;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final Paint getPaint() {
        return this.mPaint;
    }

    @FloatRange(from = 0.0d, m90to = 1.0d)
    public float getProgress() {
        return this.mProgress;
    }

    public boolean isSpinEnabled() {
        return this.mSpin;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        if (i7 != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i7);
            invalidateSelf();
        }
    }

    public void setArrowHeadLength(float f7) {
        if (this.mArrowHeadLength != f7) {
            this.mArrowHeadLength = f7;
            invalidateSelf();
        }
    }

    public void setArrowShaftLength(float f7) {
        if (this.mArrowShaftLength != f7) {
            this.mArrowShaftLength = f7;
            invalidateSelf();
        }
    }

    public void setBarLength(float f7) {
        if (this.mBarLength != f7) {
            this.mBarLength = f7;
            invalidateSelf();
        }
    }

    public void setBarThickness(float f7) {
        if (this.mPaint.getStrokeWidth() != f7) {
            this.mPaint.setStrokeWidth(f7);
            this.mMaxCutForBarSize = (float) (Math.cos(ARROW_HEAD_ANGLE) * (f7 / 2.0f));
            invalidateSelf();
        }
    }

    public void setColor(@ColorInt int i7) {
        if (i7 != this.mPaint.getColor()) {
            this.mPaint.setColor(i7);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDirection(int i7) {
        if (i7 != this.mDirection) {
            this.mDirection = i7;
            invalidateSelf();
        }
    }

    public void setGapSize(float f7) {
        if (f7 != this.mBarGap) {
            this.mBarGap = f7;
            invalidateSelf();
        }
    }

    public void setProgress(@FloatRange(from = 0.0d, m90to = 1.0d) float f7) {
        if (this.mProgress != f7) {
            this.mProgress = f7;
            invalidateSelf();
        }
    }

    public void setSpinEnabled(boolean z6) {
        if (this.mSpin != z6) {
            this.mSpin = z6;
            invalidateSelf();
        }
    }

    public void setVerticalMirror(boolean z6) {
        if (this.mVerticalMirror != z6) {
            this.mVerticalMirror = z6;
            invalidateSelf();
        }
    }
}
