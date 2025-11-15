package android.support.constraint.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.constraint.C0071R;
import android.support.constraint.motion.MotionLayout;
import android.util.AttributeSet;
import android.view.ViewParent;

/* loaded from: classes.dex */
public class MotionTelltales extends MockView {
    private static final String TAG = "MotionTelltales";
    public Matrix mInvertMatrix;
    public MotionLayout mMotionLayout;
    private Paint mPaintTelltales;
    public int mTailColor;
    public float mTailScale;
    public int mVelocityMode;
    public float[] velocity;

    public MotionTelltales(Context context) {
        super(context);
        this.mPaintTelltales = new Paint();
        this.velocity = new float[2];
        this.mInvertMatrix = new Matrix();
        this.mVelocityMode = 0;
        this.mTailColor = -65281;
        this.mTailScale = 0.25f;
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0071R.styleable.MotionTelltales);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.MotionTelltales_telltales_tailColor) {
                    this.mTailColor = typedArrayObtainStyledAttributes.getColor(index, this.mTailColor);
                } else if (index == C0071R.styleable.MotionTelltales_telltales_velocityMode) {
                    this.mVelocityMode = typedArrayObtainStyledAttributes.getInt(index, this.mVelocityMode);
                } else if (index == C0071R.styleable.MotionTelltales_telltales_tailScale) {
                    this.mTailScale = typedArrayObtainStyledAttributes.getFloat(index, this.mTailScale);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.mPaintTelltales.setColor(this.mTailColor);
        this.mPaintTelltales.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.support.constraint.utils.MockView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getMatrix().invert(this.mInvertMatrix);
        if (this.mMotionLayout == null) {
            ViewParent parent = getParent();
            if (parent instanceof MotionLayout) {
                this.mMotionLayout = (MotionLayout) parent;
                return;
            }
            return;
        }
        int width = getWidth();
        int height = getHeight();
        float[] fArr = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
        for (int i7 = 0; i7 < 5; i7++) {
            float f7 = fArr[i7];
            for (int i8 = 0; i8 < 5; i8++) {
                float f8 = fArr[i8];
                this.mMotionLayout.getViewVelocity(this, f8, f7, this.velocity, this.mVelocityMode);
                this.mInvertMatrix.mapVectors(this.velocity);
                float f9 = width * f8;
                float f10 = height * f7;
                float[] fArr2 = this.velocity;
                float f11 = fArr2[0];
                float f12 = this.mTailScale;
                float f13 = f10 - (fArr2[1] * f12);
                this.mInvertMatrix.mapVectors(fArr2);
                canvas.drawLine(f9, f10, f9 - (f11 * f12), f13, this.mPaintTelltales);
            }
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.mText = charSequence.toString();
        requestLayout();
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaintTelltales = new Paint();
        this.velocity = new float[2];
        this.mInvertMatrix = new Matrix();
        this.mVelocityMode = 0;
        this.mTailColor = -65281;
        this.mTailScale = 0.25f;
        init(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.mPaintTelltales = new Paint();
        this.velocity = new float[2];
        this.mInvertMatrix = new Matrix();
        this.mVelocityMode = 0;
        this.mTailColor = -65281;
        this.mTailScale = 0.25f;
        init(context, attributeSet);
    }
}
