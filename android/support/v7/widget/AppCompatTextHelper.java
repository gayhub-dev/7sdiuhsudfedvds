package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v7.appcompat.C0308R;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class AppCompatTextHelper {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private boolean mAsyncFontPending;

    @NonNull
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    private int mStyle = 0;
    private final TextView mView;

    public AppCompatTextHelper(TextView textView) {
        this.mView = textView;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(textView);
    }

    private void applyCompoundDrawableTint(Drawable drawable, TintInfo tintInfo) {
        if (drawable == null || tintInfo == null) {
            return;
        }
        AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
    }

    private static TintInfo createTintInfo(Context context, AppCompatDrawableManager appCompatDrawableManager, int i7) {
        ColorStateList tintList = appCompatDrawableManager.getTintList(context, i7);
        if (tintList == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.mHasTintList = true;
        tintInfo.mTintList = tintList;
        return tintInfo;
    }

    private void setTextSizeInternal(int i7, float f7) {
        this.mAutoSizeTextHelper.setTextSizeInternal(i7, f7);
    }

    private void updateTypefaceAndStyle(Context context, TintTypedArray tintTypedArray) {
        String string;
        this.mStyle = tintTypedArray.getInt(C0308R.styleable.TextAppearance_android_textStyle, this.mStyle);
        int i7 = C0308R.styleable.TextAppearance_android_fontFamily;
        if (tintTypedArray.hasValue(i7) || tintTypedArray.hasValue(C0308R.styleable.TextAppearance_fontFamily)) {
            this.mFontTypeface = null;
            int i8 = C0308R.styleable.TextAppearance_fontFamily;
            if (tintTypedArray.hasValue(i8)) {
                i7 = i8;
            }
            if (!context.isRestricted()) {
                final WeakReference weakReference = new WeakReference(this.mView);
                try {
                    Typeface font = tintTypedArray.getFont(i7, this.mStyle, new ResourcesCompat.FontCallback() { // from class: android.support.v7.widget.AppCompatTextHelper.1
                        @Override // android.support.v4.content.res.ResourcesCompat.FontCallback
                        public void onFontRetrievalFailed(int i9) {
                        }

                        @Override // android.support.v4.content.res.ResourcesCompat.FontCallback
                        public void onFontRetrieved(@NonNull Typeface typeface) {
                            AppCompatTextHelper.this.onAsyncTypefaceReceived(weakReference, typeface);
                        }
                    });
                    this.mFontTypeface = font;
                    this.mAsyncFontPending = font == null;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.mFontTypeface != null || (string = tintTypedArray.getString(i7)) == null) {
                return;
            }
            this.mFontTypeface = Typeface.create(string, this.mStyle);
            return;
        }
        int i9 = C0308R.styleable.TextAppearance_android_typeface;
        if (tintTypedArray.hasValue(i9)) {
            this.mAsyncFontPending = false;
            int i10 = tintTypedArray.getInt(i9, 1);
            if (i10 == 1) {
                this.mFontTypeface = Typeface.SANS_SERIF;
            } else if (i10 == 2) {
                this.mFontTypeface = Typeface.SERIF;
            } else {
                if (i10 != 3) {
                    return;
                }
                this.mFontTypeface = Typeface.MONOSPACE;
            }
        }
    }

    public void applyCompoundDrawablesTints() {
        if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
            applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
            applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
            applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
        }
        if (this.mDrawableStartTint == null && this.mDrawableEndTint == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
        applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
        applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    public int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    public int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    public int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }

    public int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.getAutoSizeTextType();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00b2  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void loadFromAttributes(android.util.AttributeSet r19, int r20) {
        /*
            Method dump skipped, instructions count: 484
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatTextHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    public void onAsyncTypefaceReceived(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.mAsyncFontPending) {
            this.mFontTypeface = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.mStyle);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
            return;
        }
        autoSizeText();
    }

    public void onSetTextAppearance(Context context, int i7) {
        ColorStateList colorStateList;
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i7, C0308R.styleable.TextAppearance);
        int i8 = C0308R.styleable.TextAppearance_textAllCaps;
        if (tintTypedArrayObtainStyledAttributes.hasValue(i8)) {
            setAllCaps(tintTypedArrayObtainStyledAttributes.getBoolean(i8, false));
        }
        if (Build.VERSION.SDK_INT < 23) {
            int i9 = C0308R.styleable.TextAppearance_android_textColor;
            if (tintTypedArrayObtainStyledAttributes.hasValue(i9) && (colorStateList = tintTypedArrayObtainStyledAttributes.getColorStateList(i9)) != null) {
                this.mView.setTextColor(colorStateList);
            }
        }
        int i10 = C0308R.styleable.TextAppearance_android_textSize;
        if (tintTypedArrayObtainStyledAttributes.hasValue(i10) && tintTypedArrayObtainStyledAttributes.getDimensionPixelSize(i10, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        updateTypefaceAndStyle(context, tintTypedArrayObtainStyledAttributes);
        tintTypedArrayObtainStyledAttributes.recycle();
        Typeface typeface = this.mFontTypeface;
        if (typeface != null) {
            this.mView.setTypeface(typeface, this.mStyle);
        }
    }

    public void setAllCaps(boolean z6) {
        this.mView.setAllCaps(z6);
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i7, int i8, int i9, int i10) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i7, i8, i9, i10);
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i7) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i7);
    }

    public void setAutoSizeTextTypeWithDefaults(int i7) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(i7);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setTextSize(int i7, float f7) {
        if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE || isAutoSizeEnabled()) {
            return;
        }
        setTextSizeInternal(i7, f7);
    }
}
