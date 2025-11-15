package android.support.v4.content.res;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TypedArrayUtils {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    private TypedArrayUtils() {
    }

    public static int getAttr(@NonNull Context context, int i7, int i8) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i7, typedValue, true);
        return typedValue.resourceId != 0 ? i7 : i8;
    }

    public static boolean getBoolean(@NonNull TypedArray typedArray, @StyleableRes int i7, @StyleableRes int i8, boolean z6) {
        return typedArray.getBoolean(i7, typedArray.getBoolean(i8, z6));
    }

    @Nullable
    public static Drawable getDrawable(@NonNull TypedArray typedArray, @StyleableRes int i7, @StyleableRes int i8) {
        Drawable drawable = typedArray.getDrawable(i7);
        return drawable == null ? typedArray.getDrawable(i8) : drawable;
    }

    public static int getInt(@NonNull TypedArray typedArray, @StyleableRes int i7, @StyleableRes int i8, int i9) {
        return typedArray.getInt(i7, typedArray.getInt(i8, i9));
    }

    public static boolean getNamedBoolean(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i7, boolean z6) {
        return !hasAttribute(xmlPullParser, str) ? z6 : typedArray.getBoolean(i7, z6);
    }

    @ColorInt
    public static int getNamedColor(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i7, @ColorInt int i8) {
        return !hasAttribute(xmlPullParser, str) ? i8 : typedArray.getColor(i7, i8);
    }

    public static ComplexColorCompat getNamedComplexColor(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @Nullable Resources.Theme theme, @NonNull String str, @StyleableRes int i7, @ColorInt int i8) {
        if (hasAttribute(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i7, typedValue);
            int i9 = typedValue.type;
            if (i9 >= 28 && i9 <= 31) {
                return ComplexColorCompat.from(typedValue.data);
            }
            ComplexColorCompat complexColorCompatInflate = ComplexColorCompat.inflate(typedArray.getResources(), typedArray.getResourceId(i7, 0), theme);
            if (complexColorCompatInflate != null) {
                return complexColorCompatInflate;
            }
        }
        return ComplexColorCompat.from(i8);
    }

    public static float getNamedFloat(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i7, float f7) {
        return !hasAttribute(xmlPullParser, str) ? f7 : typedArray.getFloat(i7, f7);
    }

    public static int getNamedInt(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i7, int i8) {
        return !hasAttribute(xmlPullParser, str) ? i8 : typedArray.getInt(i7, i8);
    }

    @AnyRes
    public static int getNamedResourceId(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i7, @AnyRes int i8) {
        return !hasAttribute(xmlPullParser, str) ? i8 : typedArray.getResourceId(i7, i8);
    }

    @Nullable
    public static String getNamedString(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, @StyleableRes int i7) {
        if (hasAttribute(xmlPullParser, str)) {
            return typedArray.getString(i7);
        }
        return null;
    }

    @AnyRes
    public static int getResourceId(@NonNull TypedArray typedArray, @StyleableRes int i7, @StyleableRes int i8, @AnyRes int i9) {
        return typedArray.getResourceId(i7, typedArray.getResourceId(i8, i9));
    }

    @Nullable
    public static String getString(@NonNull TypedArray typedArray, @StyleableRes int i7, @StyleableRes int i8) {
        String string = typedArray.getString(i7);
        return string == null ? typedArray.getString(i8) : string;
    }

    @Nullable
    public static CharSequence getText(@NonNull TypedArray typedArray, @StyleableRes int i7, @StyleableRes int i8) {
        CharSequence text = typedArray.getText(i7);
        return text == null ? typedArray.getText(i8) : text;
    }

    @Nullable
    public static CharSequence[] getTextArray(@NonNull TypedArray typedArray, @StyleableRes int i7, @StyleableRes int i8) {
        CharSequence[] textArray = typedArray.getTextArray(i7);
        return textArray == null ? typedArray.getTextArray(i8) : textArray;
    }

    public static boolean hasAttribute(@NonNull XmlPullParser xmlPullParser, @NonNull String str) {
        return xmlPullParser.getAttributeValue(NAMESPACE, str) != null;
    }

    @NonNull
    public static TypedArray obtainAttributes(@NonNull Resources resources, @Nullable Resources.Theme theme, @NonNull AttributeSet attributeSet, @NonNull int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    @Nullable
    public static TypedValue peekNamedValue(@NonNull TypedArray typedArray, @NonNull XmlPullParser xmlPullParser, @NonNull String str, int i7) {
        if (hasAttribute(xmlPullParser, str)) {
            return typedArray.peekValue(i7);
        }
        return null;
    }
}
