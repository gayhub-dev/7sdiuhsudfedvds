package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation;
import android.widget.ImageView;

/* loaded from: classes.dex */
class CircleImageView extends ImageView {
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private Animation.AnimationListener mListener;
    public int mShadowRadius;

    public class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint = new Paint();

        public OvalShadow(int i7) {
            CircleImageView.this.mShadowRadius = i7;
            updateRadialGradient((int) rect().width());
        }

        private void updateRadialGradient(int i7) {
            float f7 = i7 / 2;
            RadialGradient radialGradient = new RadialGradient(f7, f7, CircleImageView.this.mShadowRadius, new int[]{CircleImageView.FILL_SHADOW_COLOR, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.mRadialGradient = radialGradient;
            this.mShadowPaint.setShader(radialGradient);
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            float width = CircleImageView.this.getWidth() / 2;
            float height = CircleImageView.this.getHeight() / 2;
            canvas.drawCircle(width, height, width, this.mShadowPaint);
            canvas.drawCircle(width, height, r0 - CircleImageView.this.mShadowRadius, paint);
        }

        @Override // android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void onResize(float f7, float f8) {
            super.onResize(f7, f8);
            updateRadialGradient((int) f7);
        }
    }

    public CircleImageView(Context context, int i7) {
        ShapeDrawable shapeDrawable;
        super(context);
        float f7 = getContext().getResources().getDisplayMetrics().density;
        int i8 = (int) (Y_OFFSET * f7);
        int i9 = (int) (0.0f * f7);
        this.mShadowRadius = (int) (SHADOW_RADIUS * f7);
        if (elevationSupported()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(this, f7 * 4.0f);
        } else {
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new OvalShadow(this.mShadowRadius));
            setLayerType(1, shapeDrawable2.getPaint());
            shapeDrawable2.getPaint().setShadowLayer(this.mShadowRadius, i9, i8, KEY_SHADOW_COLOR);
            int i10 = this.mShadowRadius;
            setPadding(i10, i10, i10, i10);
            shapeDrawable = shapeDrawable2;
        }
        shapeDrawable.getPaint().setColor(i7);
        ViewCompat.setBackground(this, shapeDrawable);
    }

    private boolean elevationSupported() {
        return true;
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (elevationSupported()) {
            return;
        }
        setMeasuredDimension((this.mShadowRadius * 2) + getMeasuredWidth(), (this.mShadowRadius * 2) + getMeasuredHeight());
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        this.mListener = animationListener;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i7) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i7);
        }
    }

    public void setBackgroundColorRes(int i7) {
        setBackgroundColor(ContextCompat.getColor(getContext(), i7));
    }
}
