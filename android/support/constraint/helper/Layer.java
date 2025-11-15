package android.support.constraint.helper;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.constraint.C0071R;
import android.support.constraint.ConstraintHelper;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    private static final String TAG = "Layer";
    private boolean mApplyElevationOnAttach;
    private boolean mApplyVisibilityOnAttach;
    public float mComputedCenterX;
    public float mComputedCenterY;
    public float mComputedMaxX;
    public float mComputedMaxY;
    public float mComputedMinX;
    public float mComputedMinY;
    public ConstraintLayout mContainer;
    private float mGroupRotateAngle;
    public boolean mNeedBounds;
    private float mRotationCenterX;
    private float mRotationCenterY;
    private float mScaleX;
    private float mScaleY;
    private float mShiftX;
    private float mShiftY;
    public View[] mViews;

    public Layer(Context context) {
        super(context);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    private void reCacheViews() {
        int i7;
        if (this.mContainer == null || (i7 = this.mCount) == 0) {
            return;
        }
        View[] viewArr = this.mViews;
        if (viewArr == null || viewArr.length != i7) {
            this.mViews = new View[i7];
        }
        for (int i8 = 0; i8 < this.mCount; i8++) {
            this.mViews[i8] = this.mContainer.getViewById(this.mIds[i8]);
        }
    }

    private void transform() {
        if (this.mContainer == null) {
            return;
        }
        if (this.mViews == null) {
            reCacheViews();
        }
        calcCenters();
        double radians = Float.isNaN(this.mGroupRotateAngle) ? 0.0d : Math.toRadians(this.mGroupRotateAngle);
        float fSin = (float) Math.sin(radians);
        float fCos = (float) Math.cos(radians);
        float f7 = this.mScaleX;
        float f8 = f7 * fCos;
        float f9 = this.mScaleY;
        float f10 = (-f9) * fSin;
        float f11 = f7 * fSin;
        float f12 = f9 * fCos;
        for (int i7 = 0; i7 < this.mCount; i7++) {
            View view = this.mViews[i7];
            int right = (view.getRight() + view.getLeft()) / 2;
            int bottom = (view.getBottom() + view.getTop()) / 2;
            float f13 = right - this.mComputedCenterX;
            float f14 = bottom - this.mComputedCenterY;
            float f15 = (((f10 * f14) + (f8 * f13)) - f13) + this.mShiftX;
            float f16 = (((f12 * f14) + (f13 * f11)) - f14) + this.mShiftY;
            view.setTranslationX(f15);
            view.setTranslationY(f16);
            view.setScaleY(this.mScaleY);
            view.setScaleX(this.mScaleX);
            if (!Float.isNaN(this.mGroupRotateAngle)) {
                view.setRotation(this.mGroupRotateAngle);
            }
        }
    }

    public void calcCenters() {
        if (this.mContainer == null) {
            return;
        }
        if (this.mNeedBounds || Float.isNaN(this.mComputedCenterX) || Float.isNaN(this.mComputedCenterY)) {
            if (!Float.isNaN(this.mRotationCenterX) && !Float.isNaN(this.mRotationCenterY)) {
                this.mComputedCenterY = this.mRotationCenterY;
                this.mComputedCenterX = this.mRotationCenterX;
                return;
            }
            View[] views = getViews(this.mContainer);
            int left = views[0].getLeft();
            int top = views[0].getTop();
            int right = views[0].getRight();
            int bottom = views[0].getBottom();
            for (int i7 = 0; i7 < this.mCount; i7++) {
                View view = views[i7];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.mComputedMaxX = right;
            this.mComputedMaxY = bottom;
            this.mComputedMinX = left;
            this.mComputedMinY = top;
            if (Float.isNaN(this.mRotationCenterX)) {
                this.mComputedCenterX = (left + right) / 2;
            } else {
                this.mComputedCenterX = this.mRotationCenterX;
            }
            if (Float.isNaN(this.mRotationCenterY)) {
                this.mComputedCenterY = (top + bottom) / 2;
            } else {
                this.mComputedCenterY = this.mRotationCenterY;
            }
        }
    }

    @Override // android.support.constraint.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mUseViewMeasure = false;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0071R.styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == C0071R.styleable.ConstraintLayout_Layout_android_visibility) {
                    this.mApplyVisibilityOnAttach = true;
                } else if (index == C0071R.styleable.ConstraintLayout_Layout_android_elevation) {
                    this.mApplyElevationOnAttach = true;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.support.constraint.ConstraintHelper, android.view.View
    public void onAttachedToWindow() throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException {
        super.onAttachedToWindow();
        this.mContainer = (ConstraintLayout) getParent();
        if (this.mApplyVisibilityOnAttach || this.mApplyElevationOnAttach) {
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i7 = 0; i7 < this.mCount; i7++) {
                View viewById = this.mContainer.getViewById(this.mIds[i7]);
                if (viewById != null) {
                    if (this.mApplyVisibilityOnAttach) {
                        viewById.setVisibility(visibility);
                    }
                    if (this.mApplyElevationOnAttach && elevation > 0.0f) {
                        viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        applyLayoutFeatures();
    }

    @Override // android.view.View
    public void setPivotX(float f7) {
        this.mRotationCenterX = f7;
        transform();
    }

    @Override // android.view.View
    public void setPivotY(float f7) {
        this.mRotationCenterY = f7;
        transform();
    }

    @Override // android.view.View
    public void setRotation(float f7) {
        this.mGroupRotateAngle = f7;
        transform();
    }

    @Override // android.view.View
    public void setScaleX(float f7) {
        this.mScaleX = f7;
        transform();
    }

    @Override // android.view.View
    public void setScaleY(float f7) {
        this.mScaleY = f7;
        transform();
    }

    @Override // android.view.View
    public void setTranslationX(float f7) {
        this.mShiftX = f7;
        transform();
    }

    @Override // android.view.View
    public void setTranslationY(float f7) {
        this.mShiftY = f7;
        transform();
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        applyLayoutFeatures();
    }

    @Override // android.support.constraint.ConstraintHelper
    public void updatePostLayout(ConstraintLayout constraintLayout) {
        reCacheViews();
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        ConstraintWidget constraintWidget = ((ConstraintLayout.LayoutParams) getLayoutParams()).getConstraintWidget();
        constraintWidget.setWidth(0);
        constraintWidget.setHeight(0);
        calcCenters();
        layout(((int) this.mComputedMinX) - getPaddingLeft(), ((int) this.mComputedMinY) - getPaddingTop(), getPaddingRight() + ((int) this.mComputedMaxX), getPaddingBottom() + ((int) this.mComputedMaxY));
        transform();
    }

    @Override // android.support.constraint.ConstraintHelper
    public void updatePreDraw(ConstraintLayout constraintLayout) {
        this.mContainer = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.mGroupRotateAngle = rotation;
        } else {
            if (Float.isNaN(this.mGroupRotateAngle)) {
                return;
            }
            this.mGroupRotateAngle = rotation;
        }
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }
}
