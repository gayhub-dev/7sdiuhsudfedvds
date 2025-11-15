package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.appcompat.C0308R;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* loaded from: classes.dex */
class AppCompatCompoundButtonHelper {
    private ColorStateList mButtonTintList = null;
    private PorterDuff.Mode mButtonTintMode = null;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    public interface DirectSetButtonDrawableInterface {
        void setButtonDrawable(Drawable drawable);
    }

    public AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    public void applyButtonTint() throws NoSuchFieldException {
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView);
        if (buttonDrawable != null) {
            if (this.mHasButtonTint || this.mHasButtonTintMode) {
                Drawable drawableMutate = DrawableCompat.wrap(buttonDrawable).mutate();
                if (this.mHasButtonTint) {
                    DrawableCompat.setTintList(drawableMutate, this.mButtonTintList);
                }
                if (this.mHasButtonTintMode) {
                    DrawableCompat.setTintMode(drawableMutate, this.mButtonTintMode);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.mView.getDrawableState());
                }
                this.mView.setButtonDrawable(drawableMutate);
            }
        }
    }

    public int getCompoundPaddingLeft(int i7) {
        return i7;
    }

    public ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i7) {
        int resourceId;
        TypedArray typedArrayObtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, C0308R.styleable.CompoundButton, i7, 0);
        try {
            int i8 = C0308R.styleable.CompoundButton_android_button;
            if (typedArrayObtainStyledAttributes.hasValue(i8) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(i8, 0)) != 0) {
                CompoundButton compoundButton = this.mView;
                compoundButton.setButtonDrawable(AppCompatResources.getDrawable(compoundButton.getContext(), resourceId));
            }
            int i9 = C0308R.styleable.CompoundButton_buttonTint;
            if (typedArrayObtainStyledAttributes.hasValue(i9)) {
                CompoundButtonCompat.setButtonTintList(this.mView, typedArrayObtainStyledAttributes.getColorStateList(i9));
            }
            int i10 = C0308R.styleable.CompoundButton_buttonTintMode;
            if (typedArrayObtainStyledAttributes.hasValue(i10)) {
                CompoundButtonCompat.setButtonTintMode(this.mView, DrawableUtils.parseTintMode(typedArrayObtainStyledAttributes.getInt(i10, -1), null));
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void onSetButtonDrawable() throws NoSuchFieldException {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
        } else {
            this.mSkipNextApply = true;
            applyButtonTint();
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) throws NoSuchFieldException {
        this.mButtonTintList = colorStateList;
        this.mHasButtonTint = true;
        applyButtonTint();
    }

    public void setSupportButtonTintMode(@Nullable PorterDuff.Mode mode) throws NoSuchFieldException {
        this.mButtonTintMode = mode;
        this.mHasButtonTintMode = true;
        applyButtonTint();
    }
}
