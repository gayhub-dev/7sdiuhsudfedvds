package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.util.TypedValue;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TintTypedArray {
    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.mWrapped = typedArray;
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public boolean getBoolean(int i7, boolean z6) {
        return this.mWrapped.getBoolean(i7, z6);
    }

    @RequiresApi(21)
    public int getChangingConfigurations() {
        return this.mWrapped.getChangingConfigurations();
    }

    public int getColor(int i7, int i8) {
        return this.mWrapped.getColor(i7, i8);
    }

    public ColorStateList getColorStateList(int i7) {
        int resourceId;
        ColorStateList colorStateList;
        return (!this.mWrapped.hasValue(i7) || (resourceId = this.mWrapped.getResourceId(i7, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(this.mContext, resourceId)) == null) ? this.mWrapped.getColorStateList(i7) : colorStateList;
    }

    public float getDimension(int i7, float f7) {
        return this.mWrapped.getDimension(i7, f7);
    }

    public int getDimensionPixelOffset(int i7, int i8) {
        return this.mWrapped.getDimensionPixelOffset(i7, i8);
    }

    public int getDimensionPixelSize(int i7, int i8) {
        return this.mWrapped.getDimensionPixelSize(i7, i8);
    }

    public Drawable getDrawable(int i7) {
        int resourceId;
        return (!this.mWrapped.hasValue(i7) || (resourceId = this.mWrapped.getResourceId(i7, 0)) == 0) ? this.mWrapped.getDrawable(i7) : AppCompatResources.getDrawable(this.mContext, resourceId);
    }

    public Drawable getDrawableIfKnown(int i7) {
        int resourceId;
        if (!this.mWrapped.hasValue(i7) || (resourceId = this.mWrapped.getResourceId(i7, 0)) == 0) {
            return null;
        }
        return AppCompatDrawableManager.get().getDrawable(this.mContext, resourceId, true);
    }

    public float getFloat(int i7, float f7) {
        return this.mWrapped.getFloat(i7, f7);
    }

    @Nullable
    public Typeface getFont(@StyleableRes int i7, int i8, @Nullable ResourcesCompat.FontCallback fontCallback) {
        int resourceId = this.mWrapped.getResourceId(i7, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        return ResourcesCompat.getFont(this.mContext, resourceId, this.mTypedValue, i8, fontCallback);
    }

    public float getFraction(int i7, int i8, int i9, float f7) {
        return this.mWrapped.getFraction(i7, i8, i9, f7);
    }

    public int getIndex(int i7) {
        return this.mWrapped.getIndex(i7);
    }

    public int getIndexCount() {
        return this.mWrapped.getIndexCount();
    }

    public int getInt(int i7, int i8) {
        return this.mWrapped.getInt(i7, i8);
    }

    public int getInteger(int i7, int i8) {
        return this.mWrapped.getInteger(i7, i8);
    }

    public int getLayoutDimension(int i7, String str) {
        return this.mWrapped.getLayoutDimension(i7, str);
    }

    public String getNonResourceString(int i7) {
        return this.mWrapped.getNonResourceString(i7);
    }

    public String getPositionDescription() {
        return this.mWrapped.getPositionDescription();
    }

    public int getResourceId(int i7, int i8) {
        return this.mWrapped.getResourceId(i7, i8);
    }

    public Resources getResources() {
        return this.mWrapped.getResources();
    }

    public String getString(int i7) {
        return this.mWrapped.getString(i7);
    }

    public CharSequence getText(int i7) {
        return this.mWrapped.getText(i7);
    }

    public CharSequence[] getTextArray(int i7) {
        return this.mWrapped.getTextArray(i7);
    }

    public int getType(int i7) {
        return this.mWrapped.getType(i7);
    }

    public boolean getValue(int i7, TypedValue typedValue) {
        return this.mWrapped.getValue(i7, typedValue);
    }

    public boolean hasValue(int i7) {
        return this.mWrapped.hasValue(i7);
    }

    public int length() {
        return this.mWrapped.length();
    }

    public TypedValue peekValue(int i7) {
        return this.mWrapped.peekValue(i7);
    }

    public void recycle() {
        this.mWrapped.recycle();
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i7, int i8) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i7, i8));
    }

    public int getLayoutDimension(int i7, int i8) {
        return this.mWrapped.getLayoutDimension(i7, i8);
    }

    public static TintTypedArray obtainStyledAttributes(Context context, int i7, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(i7, iArr));
    }
}
