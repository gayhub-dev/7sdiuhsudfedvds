package android.support.constraint.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.RequiresApi;
import android.support.constraint.C0071R;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class ImageFilterView extends AppCompatImageView {
    private float mCrossfade;
    private ImageMatrix mImageMatrix;
    public LayerDrawable mLayer;
    public Drawable[] mLayers;
    private boolean mOverlay;
    private Path mPath;
    public RectF mRect;
    private float mRound;
    private float mRoundPercent;
    public ViewOutlineProvider mViewOutlineProvider;

    public static class ImageMatrix {

        /* renamed from: m */
        public float[] f148m = new float[20];
        public ColorMatrix mColorMatrix = new ColorMatrix();
        public ColorMatrix mTmpColorMatrix = new ColorMatrix();
        public float mBrightness = 1.0f;
        public float mSaturation = 1.0f;
        public float mContrast = 1.0f;
        public float mWarmth = 1.0f;

        private void brightness(float f7) {
            float[] fArr = this.f148m;
            fArr[0] = f7;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = f7;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = f7;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        private void saturation(float f7) {
            float f8 = 1.0f - f7;
            float f9 = 0.2999f * f8;
            float f10 = 0.587f * f8;
            float f11 = f8 * 0.114f;
            float[] fArr = this.f148m;
            fArr[0] = f9 + f7;
            fArr[1] = f10;
            fArr[2] = f11;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = f9;
            fArr[6] = f10 + f7;
            fArr[7] = f11;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = f9;
            fArr[11] = f10;
            fArr[12] = f11 + f7;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        private void warmth(float f7) {
            float fLog;
            float fPow;
            if (f7 <= 0.0f) {
                f7 = 0.01f;
            }
            float f8 = (5000.0f / f7) / 100.0f;
            if (f8 > 66.0f) {
                double d7 = f8 - 60.0f;
                fPow = ((float) Math.pow(d7, -0.13320475816726685d)) * 329.69873f;
                fLog = ((float) Math.pow(d7, 0.07551484555006027d)) * 288.12216f;
            } else {
                fLog = (((float) Math.log(f8)) * 99.4708f) - 161.11957f;
                fPow = 255.0f;
            }
            float fLog2 = f8 < 66.0f ? f8 > 19.0f ? (((float) Math.log(f8 - 10.0f)) * 138.51773f) - 305.0448f : 0.0f : 255.0f;
            float fMin = Math.min(255.0f, Math.max(fPow, 0.0f));
            float fMin2 = Math.min(255.0f, Math.max(fLog, 0.0f));
            float fMin3 = Math.min(255.0f, Math.max(fLog2, 0.0f));
            float fLog3 = (((float) Math.log(50.0f)) * 99.4708f) - 161.11957f;
            float fLog4 = (((float) Math.log(40.0f)) * 138.51773f) - 305.0448f;
            float fMin4 = Math.min(255.0f, Math.max(255.0f, 0.0f));
            float fMin5 = Math.min(255.0f, Math.max(fLog3, 0.0f));
            float fMin6 = fMin3 / Math.min(255.0f, Math.max(fLog4, 0.0f));
            float[] fArr = this.f148m;
            fArr[0] = fMin / fMin4;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = fMin2 / fMin5;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = fMin6;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        public void updateMatrix(ImageView imageView) {
            boolean z6;
            this.mColorMatrix.reset();
            float f7 = this.mSaturation;
            boolean z7 = true;
            if (f7 != 1.0f) {
                saturation(f7);
                this.mColorMatrix.set(this.f148m);
                z6 = true;
            } else {
                z6 = false;
            }
            float f8 = this.mContrast;
            if (f8 != 1.0f) {
                this.mTmpColorMatrix.setScale(f8, f8, f8, 1.0f);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                z6 = true;
            }
            float f9 = this.mWarmth;
            if (f9 != 1.0f) {
                warmth(f9);
                this.mTmpColorMatrix.set(this.f148m);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                z6 = true;
            }
            float f10 = this.mBrightness;
            if (f10 != 1.0f) {
                brightness(f10);
                this.mTmpColorMatrix.set(this.f148m);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
            } else {
                z7 = z6;
            }
            if (z7) {
                imageView.setColorFilter(new ColorMatrixColorFilter(this.mColorMatrix));
            } else {
                imageView.clearColorFilter();
            }
        }
    }

    public ImageFilterView(Context context) {
        super(context);
        this.mImageMatrix = new ImageMatrix();
        this.mOverlay = true;
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = Float.NaN;
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0071R.styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(C0071R.styleable.ImageFilterView_altSrc);
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.ImageFilterView_crossfade) {
                    this.mCrossfade = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == C0071R.styleable.ImageFilterView_warmth) {
                    setWarmth(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == C0071R.styleable.ImageFilterView_saturation) {
                    setSaturation(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == C0071R.styleable.ImageFilterView_contrast) {
                    setContrast(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == C0071R.styleable.ImageFilterView_round) {
                    setRound(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == C0071R.styleable.ImageFilterView_roundPercent) {
                    setRoundPercent(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == C0071R.styleable.ImageFilterView_overlay) {
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.mOverlay));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.mLayers = drawableArr;
                drawableArr[0] = getDrawable();
                this.mLayers[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(this.mLayers);
                this.mLayer = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.mCrossfade * 255.0f));
                super.setImageDrawable(this.mLayer);
            }
        }
    }

    private void setOverlay(boolean z6) {
        this.mOverlay = z6;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public float getBrightness() {
        return this.mImageMatrix.mBrightness;
    }

    public float getContrast() {
        return this.mImageMatrix.mContrast;
    }

    public float getCrossfade() {
        return this.mCrossfade;
    }

    public float getRound() {
        return this.mRound;
    }

    public float getRoundPercent() {
        return this.mRoundPercent;
    }

    public float getSaturation() {
        return this.mImageMatrix.mSaturation;
    }

    public float getWarmth() {
        return this.mImageMatrix.mWarmth;
    }

    public void setBrightness(float f7) {
        ImageMatrix imageMatrix = this.mImageMatrix;
        imageMatrix.mBrightness = f7;
        imageMatrix.updateMatrix(this);
    }

    public void setContrast(float f7) {
        ImageMatrix imageMatrix = this.mImageMatrix;
        imageMatrix.mContrast = f7;
        imageMatrix.updateMatrix(this);
    }

    public void setCrossfade(float f7) {
        this.mCrossfade = f7;
        if (this.mLayers != null) {
            if (!this.mOverlay) {
                this.mLayer.getDrawable(0).setAlpha((int) ((1.0f - this.mCrossfade) * 255.0f));
            }
            this.mLayer.getDrawable(1).setAlpha((int) (this.mCrossfade * 255.0f));
            super.setImageDrawable(this.mLayer);
        }
    }

    @RequiresApi(21)
    public void setRound(float f7) {
        if (Float.isNaN(f7)) {
            this.mRound = f7;
            float f8 = this.mRoundPercent;
            this.mRoundPercent = -1.0f;
            setRoundPercent(f8);
            return;
        }
        boolean z6 = this.mRound != f7;
        this.mRound = f7;
        if (f7 != 0.0f) {
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            if (this.mRect == null) {
                this.mRect = new RectF();
            }
            if (this.mViewOutlineProvider == null) {
                ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() { // from class: android.support.constraint.utils.ImageFilterView.2
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), ImageFilterView.this.mRound);
                    }
                };
                this.mViewOutlineProvider = viewOutlineProvider;
                setOutlineProvider(viewOutlineProvider);
            }
            setClipToOutline(true);
            this.mRect.set(0.0f, 0.0f, getWidth(), getHeight());
            this.mPath.reset();
            Path path = this.mPath;
            RectF rectF = this.mRect;
            float f9 = this.mRound;
            path.addRoundRect(rectF, f9, f9, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }

    @RequiresApi(21)
    public void setRoundPercent(float f7) {
        boolean z6 = this.mRoundPercent != f7;
        this.mRoundPercent = f7;
        if (f7 != 0.0f) {
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            if (this.mRect == null) {
                this.mRect = new RectF();
            }
            if (this.mViewOutlineProvider == null) {
                ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() { // from class: android.support.constraint.utils.ImageFilterView.1
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view, Outline outline) {
                        outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), (ImageFilterView.this.mRoundPercent * Math.min(r3, r4)) / 2.0f);
                    }
                };
                this.mViewOutlineProvider = viewOutlineProvider;
                setOutlineProvider(viewOutlineProvider);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.mRoundPercent) / 2.0f;
            this.mRect.set(0.0f, 0.0f, width, height);
            this.mPath.reset();
            this.mPath.addRoundRect(this.mRect, fMin, fMin, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }

    public void setSaturation(float f7) {
        ImageMatrix imageMatrix = this.mImageMatrix;
        imageMatrix.mSaturation = f7;
        imageMatrix.updateMatrix(this);
    }

    public void setWarmth(float f7) {
        ImageMatrix imageMatrix = this.mImageMatrix;
        imageMatrix.mWarmth = f7;
        imageMatrix.updateMatrix(this);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mImageMatrix = new ImageMatrix();
        this.mOverlay = true;
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = Float.NaN;
        init(context, attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.mImageMatrix = new ImageMatrix();
        this.mOverlay = true;
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = Float.NaN;
        init(context, attributeSet);
    }
}
