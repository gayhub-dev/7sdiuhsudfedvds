package android.support.v7.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ColorStateListInflaterCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class AppCompatResources {
    private static final String LOG_TAG = "AppCompatResources";
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal<>();
    private static final WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> sColorStateCaches = new WeakHashMap<>(0);
    private static final Object sColorStateCacheLock = new Object();

    public static class ColorStateListCacheEntry {
        public final Configuration configuration;
        public final ColorStateList value;

        public ColorStateListCacheEntry(@NonNull ColorStateList colorStateList, @NonNull Configuration configuration) {
            this.value = colorStateList;
            this.configuration = configuration;
        }
    }

    private AppCompatResources() {
    }

    private static void addColorStateListToCache(@NonNull Context context, @ColorRes int i7, @NonNull ColorStateList colorStateList) {
        synchronized (sColorStateCacheLock) {
            WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> weakHashMap = sColorStateCaches;
            SparseArray<ColorStateListCacheEntry> sparseArray = weakHashMap.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                weakHashMap.put(context, sparseArray);
            }
            sparseArray.append(i7, new ColorStateListCacheEntry(colorStateList, context.getResources().getConfiguration()));
        }
    }

    @Nullable
    private static ColorStateList getCachedColorStateList(@NonNull Context context, @ColorRes int i7) {
        ColorStateListCacheEntry colorStateListCacheEntry;
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> sparseArray = sColorStateCaches.get(context);
            if (sparseArray != null && sparseArray.size() > 0 && (colorStateListCacheEntry = sparseArray.get(i7)) != null) {
                if (colorStateListCacheEntry.configuration.equals(context.getResources().getConfiguration())) {
                    return colorStateListCacheEntry.value;
                }
                sparseArray.remove(i7);
            }
            return null;
        }
    }

    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int i7) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i7);
        }
        ColorStateList cachedColorStateList = getCachedColorStateList(context, i7);
        if (cachedColorStateList != null) {
            return cachedColorStateList;
        }
        ColorStateList colorStateListInflateColorStateList = inflateColorStateList(context, i7);
        if (colorStateListInflateColorStateList == null) {
            return ContextCompat.getColorStateList(context, i7);
        }
        addColorStateListToCache(context, i7, colorStateListInflateColorStateList);
        return colorStateListInflateColorStateList;
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int i7) {
        return AppCompatDrawableManager.get().getDrawable(context, i7);
    }

    @NonNull
    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> threadLocal = TL_TYPED_VALUE;
        TypedValue typedValue = threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    @Nullable
    private static ColorStateList inflateColorStateList(Context context, int i7) throws Resources.NotFoundException {
        if (isColorInt(context, i7)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return ColorStateListInflaterCompat.createFromXml(resources, resources.getXml(i7), context.getTheme());
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean isColorInt(@NonNull Context context, @ColorRes int i7) throws Resources.NotFoundException {
        Resources resources = context.getResources();
        TypedValue typedValue = getTypedValue();
        resources.getValue(i7, typedValue, true);
        int i8 = typedValue.type;
        return i8 >= 28 && i8 <= 31;
    }
}
