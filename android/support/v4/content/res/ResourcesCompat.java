package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.util.Preconditions;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import p009b.C0413b;

/* loaded from: classes.dex */
public final class ResourcesCompat {
    private static final String TAG = "ResourcesCompat";

    public static abstract class FontCallback {
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final void callbackFailAsync(final int i7, @Nullable Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() { // from class: android.support.v4.content.res.ResourcesCompat.FontCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(i7);
                }
            });
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final void callbackSuccessAsync(final Typeface typeface, @Nullable Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() { // from class: android.support.v4.content.res.ResourcesCompat.FontCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        public abstract void onFontRetrievalFailed(int i7);

        public abstract void onFontRetrieved(@NonNull Typeface typeface);
    }

    private ResourcesCompat() {
    }

    @ColorInt
    public static int getColor(@NonNull Resources resources, @ColorRes int i7, @Nullable Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 23 ? resources.getColor(i7, theme) : resources.getColor(i7);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Resources resources, @ColorRes int i7, @Nullable Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 23 ? resources.getColorStateList(i7, theme) : resources.getColorStateList(i7);
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Resources resources, @DrawableRes int i7, @Nullable Resources.Theme theme) {
        return resources.getDrawable(i7, theme);
    }

    @Nullable
    public static Drawable getDrawableForDensity(@NonNull Resources resources, @DrawableRes int i7, int i8, @Nullable Resources.Theme theme) {
        return resources.getDrawableForDensity(i7, i8, theme);
    }

    @Nullable
    public static Typeface getFont(@NonNull Context context, @FontRes int i7) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i7, new TypedValue(), 0, null, null, false);
    }

    private static Typeface loadFont(@NonNull Context context, int i7, TypedValue typedValue, int i8, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean z6) throws Resources.NotFoundException {
        Resources resources = context.getResources();
        resources.getValue(i7, typedValue, true);
        Typeface typefaceLoadFont = loadFont(context, resources, typedValue, i7, i8, fontCallback, handler, z6);
        if (typefaceLoadFont != null || fontCallback != null) {
            return typefaceLoadFont;
        }
        StringBuilder sbM112a = C0413b.m112a("Font resource ID #0x");
        sbM112a.append(Integer.toHexString(i7));
        sbM112a.append(" could not be retrieved.");
        throw new Resources.NotFoundException(sbM112a.toString());
    }

    public static void getFont(@NonNull Context context, @FontRes int i7, @NonNull FontCallback fontCallback, @Nullable Handler handler) throws Resources.NotFoundException {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
        } else {
            loadFont(context, i7, new TypedValue(), 0, fontCallback, handler, false);
        }
    }

    private static Typeface loadFont(@NonNull Context context, Resources resources, TypedValue typedValue, int i7, int i8, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean z6) {
        CharSequence charSequence = typedValue.string;
        if (charSequence != null) {
            String string = charSequence.toString();
            if (!string.startsWith("res/")) {
                if (fontCallback != null) {
                    fontCallback.callbackFailAsync(-3, handler);
                }
                return null;
            }
            Typeface typefaceFindFromCache = TypefaceCompat.findFromCache(resources, i7, i8);
            if (typefaceFindFromCache != null) {
                if (fontCallback != null) {
                    fontCallback.callbackSuccessAsync(typefaceFindFromCache, handler);
                }
                return typefaceFindFromCache;
            }
            try {
                if (string.toLowerCase().endsWith(".xml")) {
                    FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry = FontResourcesParserCompat.parse(resources.getXml(i7), resources);
                    if (familyResourceEntry != null) {
                        return TypefaceCompat.createFromResourcesFamilyXml(context, familyResourceEntry, resources, i7, i8, fontCallback, handler, z6);
                    }
                    if (fontCallback != null) {
                        fontCallback.callbackFailAsync(-3, handler);
                    }
                    return null;
                }
                Typeface typefaceCreateFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(context, resources, i7, string, i8);
                if (fontCallback != null) {
                    if (typefaceCreateFromResourcesFontFile != null) {
                        fontCallback.callbackSuccessAsync(typefaceCreateFromResourcesFontFile, handler);
                    } else {
                        fontCallback.callbackFailAsync(-3, handler);
                    }
                }
                return typefaceCreateFromResourcesFontFile;
            } catch (IOException | XmlPullParserException unused) {
                if (fontCallback != null) {
                    fontCallback.callbackFailAsync(-3, handler);
                }
                return null;
            }
        }
        StringBuilder sbM112a = C0413b.m112a("Resource \"");
        sbM112a.append(resources.getResourceName(i7));
        sbM112a.append("\" (");
        sbM112a.append(Integer.toHexString(i7));
        sbM112a.append(") is not a Font: ");
        sbM112a.append(typedValue);
        throw new Resources.NotFoundException(sbM112a.toString());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Typeface getFont(@NonNull Context context, @FontRes int i7, TypedValue typedValue, int i8, @Nullable FontCallback fontCallback) {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, i7, typedValue, i8, fontCallback, null, true);
    }
}
