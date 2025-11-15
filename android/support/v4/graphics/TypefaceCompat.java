package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.util.LruCache;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TypefaceCompat {
    private static final String TAG = "TypefaceCompat";
    private static final LruCache<String, Typeface> sTypefaceCache;
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    static {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 28) {
            sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
        } else if (i7 >= 26) {
            sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        } else if (i7 < 24 || !TypefaceCompatApi24Impl.isUsable()) {
            sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        } else {
            sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
        }
        sTypefaceCache = new LruCache<>(16);
    }

    private TypefaceCompat() {
    }

    @Nullable
    public static Typeface createFromFontInfo(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i7) {
        return sTypefaceCompatImpl.createFromFontInfo(context, cancellationSignal, fontInfoArr, i7);
    }

    @Nullable
    public static Typeface createFromResourcesFamilyXml(@NonNull Context context, @NonNull FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry, @NonNull Resources resources, int i7, int i8, @Nullable ResourcesCompat.FontCallback fontCallback, @Nullable Handler handler, boolean z6) {
        Typeface typefaceCreateFromFontFamilyFilesResourceEntry;
        if (familyResourceEntry instanceof FontResourcesParserCompat.ProviderResourceEntry) {
            FontResourcesParserCompat.ProviderResourceEntry providerResourceEntry = (FontResourcesParserCompat.ProviderResourceEntry) familyResourceEntry;
            boolean z7 = false;
            if (!z6 ? fontCallback == null : providerResourceEntry.getFetchStrategy() == 0) {
                z7 = true;
            }
            typefaceCreateFromFontFamilyFilesResourceEntry = FontsContractCompat.getFontSync(context, providerResourceEntry.getRequest(), fontCallback, handler, z7, z6 ? providerResourceEntry.getTimeout() : -1, i8);
        } else {
            typefaceCreateFromFontFamilyFilesResourceEntry = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, (FontResourcesParserCompat.FontFamilyFilesResourceEntry) familyResourceEntry, resources, i8);
            if (fontCallback != null) {
                if (typefaceCreateFromFontFamilyFilesResourceEntry != null) {
                    fontCallback.callbackSuccessAsync(typefaceCreateFromFontFamilyFilesResourceEntry, handler);
                } else {
                    fontCallback.callbackFailAsync(-3, handler);
                }
            }
        }
        if (typefaceCreateFromFontFamilyFilesResourceEntry != null) {
            sTypefaceCache.put(createResourceUid(resources, i7, i8), typefaceCreateFromFontFamilyFilesResourceEntry);
        }
        return typefaceCreateFromFontFamilyFilesResourceEntry;
    }

    @Nullable
    public static Typeface createFromResourcesFontFile(@NonNull Context context, @NonNull Resources resources, int i7, String str, int i8) {
        Typeface typefaceCreateFromResourcesFontFile = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, i7, str, i8);
        if (typefaceCreateFromResourcesFontFile != null) {
            sTypefaceCache.put(createResourceUid(resources, i7, i8), typefaceCreateFromResourcesFontFile);
        }
        return typefaceCreateFromResourcesFontFile;
    }

    private static String createResourceUid(Resources resources, int i7, int i8) {
        return resources.getResourcePackageName(i7) + "-" + i7 + "-" + i8;
    }

    @Nullable
    public static Typeface findFromCache(@NonNull Resources resources, int i7, int i8) {
        return sTypefaceCache.get(createResourceUid(resources, i7, i8));
    }
}
