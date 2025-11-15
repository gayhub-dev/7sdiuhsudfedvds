package android.support.v4.provider;

import android.arch.lifecycle.C0063n;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.constraint.motion.C0081c;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.provider.SelfDestructiveThread;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class FontsContractCompat {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARCEL_FONT_RESULTS = "font_results";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    public static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS);
    public static final Object sLock = new Object();

    @GuardedBy("sLock")
    public static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> sPendingReplies = new SimpleArrayMap<>();
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() { // from class: android.support.v4.provider.FontsContractCompat.5
        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            int length;
            int length2;
            if (bArr.length == bArr2.length) {
                for (int i7 = 0; i7 < bArr.length; i7++) {
                    if (bArr[i7] != bArr2[i7]) {
                        length = bArr[i7];
                        length2 = bArr2[i7];
                    }
                }
                return 0;
            }
            length = bArr.length;
            length2 = bArr2.length;
            return length - length2;
        }
    };

    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public FontFamilyResult(int i7, @Nullable FontInfo[] fontInfoArr) {
            this.mStatusCode = i7;
            this.mFonts = fontInfoArr;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public FontInfo(@NonNull Uri uri, @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED) int i7, @IntRange(from = 1, m91to = 1000) int i8, boolean z6, int i9) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i7;
            this.mWeight = i8;
            this.mItalic = z6;
            this.mResultCode = i9;
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 1, m91to = 1000)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static final int RESULT_OK = 0;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int i7) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }

    public static final class TypefaceResult {
        public final int mResult;
        public final Typeface mTypeface;

        public TypefaceResult(@Nullable Typeface typeface, int i7) {
            this.mTypeface = typeface;
            this.mResult = i7;
        }
    }

    private FontsContractCompat() {
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i7 = 0; i7 < list.size(); i7++) {
            if (!Arrays.equals(list.get(i7), list2.get(i7))) {
                return false;
            }
        }
        return true;
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest fontRequest) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        return provider == null ? new FontFamilyResult(1, null) : new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        return fontRequest.getCertificates() != null ? fontRequest.getCertificates() : FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    @VisibleForTesting
    @NonNull
    public static FontInfo[] getFontFromProvider(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        ArrayList arrayList = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(uriBuild, new String[]{"_id", Columns.FILE_ID, Columns.TTC_INDEX, Columns.VARIATION_SETTINGS, Columns.WEIGHT, Columns.ITALIC, Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, null, cancellationSignal);
            if (cursorQuery != null && cursorQuery.getCount() > 0) {
                int columnIndex = cursorQuery.getColumnIndex(Columns.RESULT_CODE);
                ArrayList arrayList2 = new ArrayList();
                int columnIndex2 = cursorQuery.getColumnIndex("_id");
                int columnIndex3 = cursorQuery.getColumnIndex(Columns.FILE_ID);
                int columnIndex4 = cursorQuery.getColumnIndex(Columns.TTC_INDEX);
                int columnIndex5 = cursorQuery.getColumnIndex(Columns.WEIGHT);
                int columnIndex6 = cursorQuery.getColumnIndex(Columns.ITALIC);
                while (cursorQuery.moveToNext()) {
                    int i7 = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0;
                    arrayList2.add(new FontInfo(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, i7));
                }
                arrayList = arrayList2;
            }
            return (FontInfo[]) arrayList.toArray(new FontInfo[0]);
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    @NonNull
    public static TypefaceResult getFontInternal(Context context, FontRequest fontRequest, int i7) {
        try {
            FontFamilyResult fontFamilyResultFetchFonts = fetchFonts(context, null, fontRequest);
            if (fontFamilyResultFetchFonts.getStatusCode() != 0) {
                return new TypefaceResult(null, fontFamilyResultFetchFonts.getStatusCode() == 1 ? -2 : -3);
            }
            Typeface typefaceCreateFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fontFamilyResultFetchFonts.getFonts(), i7);
            return new TypefaceResult(typefaceCreateFromFontInfo, typefaceCreateFromFontInfo != null ? 0 : -3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new TypefaceResult(null, -1);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Typeface getFontSync(final Context context, final FontRequest fontRequest, @Nullable final ResourcesCompat.FontCallback fontCallback, @Nullable final Handler handler, boolean z6, int i7, final int i8) {
        final String str = fontRequest.getIdentifier() + "-" + i8;
        Typeface typeface = sTypefaceCache.get(str);
        if (typeface != null) {
            if (fontCallback != null) {
                fontCallback.onFontRetrieved(typeface);
            }
            return typeface;
        }
        if (z6 && i7 == -1) {
            TypefaceResult fontInternal = getFontInternal(context, fontRequest, i8);
            if (fontCallback != null) {
                int i9 = fontInternal.mResult;
                if (i9 == 0) {
                    fontCallback.callbackSuccessAsync(fontInternal.mTypeface, handler);
                } else {
                    fontCallback.callbackFailAsync(i9, handler);
                }
            }
            return fontInternal.mTypeface;
        }
        Callable<TypefaceResult> callable = new Callable<TypefaceResult>() { // from class: android.support.v4.provider.FontsContractCompat.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public TypefaceResult call() {
                TypefaceResult fontInternal2 = FontsContractCompat.getFontInternal(context, fontRequest, i8);
                Typeface typeface2 = fontInternal2.mTypeface;
                if (typeface2 != null) {
                    FontsContractCompat.sTypefaceCache.put(str, typeface2);
                }
                return fontInternal2;
            }
        };
        if (z6) {
            try {
                return ((TypefaceResult) sBackgroundThread.postAndWait(callable, i7)).mTypeface;
            } catch (InterruptedException unused) {
                return null;
            }
        }
        SelfDestructiveThread.ReplyCallback<TypefaceResult> replyCallback = fontCallback == null ? null : new SelfDestructiveThread.ReplyCallback<TypefaceResult>() { // from class: android.support.v4.provider.FontsContractCompat.2
            @Override // android.support.v4.provider.SelfDestructiveThread.ReplyCallback
            public void onReply(TypefaceResult typefaceResult) {
                if (typefaceResult == null) {
                    fontCallback.callbackFailAsync(1, handler);
                    return;
                }
                int i10 = typefaceResult.mResult;
                if (i10 == 0) {
                    fontCallback.callbackSuccessAsync(typefaceResult.mTypeface, handler);
                } else {
                    fontCallback.callbackFailAsync(i10, handler);
                }
            }
        };
        synchronized (sLock) {
            SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> simpleArrayMap = sPendingReplies;
            if (simpleArrayMap.containsKey(str)) {
                if (replyCallback != null) {
                    simpleArrayMap.get(str).add(replyCallback);
                }
                return null;
            }
            if (replyCallback != null) {
                ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> arrayList = new ArrayList<>();
                arrayList.add(replyCallback);
                simpleArrayMap.put(str, arrayList);
            }
            sBackgroundThread.postAndReply(callable, new SelfDestructiveThread.ReplyCallback<TypefaceResult>() { // from class: android.support.v4.provider.FontsContractCompat.3
                @Override // android.support.v4.provider.SelfDestructiveThread.ReplyCallback
                public void onReply(TypefaceResult typefaceResult) {
                    synchronized (FontsContractCompat.sLock) {
                        SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> simpleArrayMap2 = FontsContractCompat.sPendingReplies;
                        ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> arrayList2 = simpleArrayMap2.get(str);
                        if (arrayList2 == null) {
                            return;
                        }
                        simpleArrayMap2.remove(str);
                        for (int i10 = 0; i10 < arrayList2.size(); i10++) {
                            arrayList2.get(i10).onReply(typefaceResult);
                        }
                    }
                }
            });
            return null;
        }
    }

    @VisibleForTesting
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException(C0063n.m88a("No package found for authority: ", providerAuthority));
        }
        if (!providerInfoResolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            StringBuilder sbM95a = C0081c.m95a("Found content provider ", providerAuthority, ", but package was not ");
            sbM95a.append(fontRequest.getProviderPackage());
            throw new PackageManager.NameNotFoundException(sbM95a.toString());
        }
        List<byte[]> listConvertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures);
        Collections.sort(listConvertToByteArrayList, sByteArrayComparator);
        List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
        for (int i7 = 0; i7 < certificates.size(); i7++) {
            ArrayList arrayList = new ArrayList(certificates.get(i7));
            Collections.sort(arrayList, sByteArrayComparator);
            if (equalsByteArrayList(listConvertToByteArrayList, arrayList)) {
                return providerInfoResolveContentProvider;
            }
        }
        return null;
    }

    @RequiresApi(19)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        HashMap map = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!map.containsKey(uri)) {
                    map.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(map);
    }

    public static void requestFont(@NonNull final Context context, @NonNull final FontRequest fontRequest, @NonNull final FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        final Handler handler2 = new Handler();
        handler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    FontFamilyResult fontFamilyResultFetchFonts = FontsContractCompat.fetchFonts(context, null, fontRequest);
                    if (fontFamilyResultFetchFonts.getStatusCode() != 0) {
                        int statusCode = fontFamilyResultFetchFonts.getStatusCode();
                        if (statusCode == 1) {
                            handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-2);
                                }
                            });
                            return;
                        } else if (statusCode != 2) {
                            handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        } else {
                            handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        }
                    }
                    FontInfo[] fonts = fontFamilyResultFetchFonts.getFonts();
                    if (fonts == null || fonts.length == 0) {
                        handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.5
                            @Override // java.lang.Runnable
                            public void run() {
                                fontRequestCallback.onTypefaceRequestFailed(1);
                            }
                        });
                        return;
                    }
                    for (FontInfo fontInfo : fonts) {
                        if (fontInfo.getResultCode() != 0) {
                            final int resultCode = fontInfo.getResultCode();
                            if (resultCode < 0) {
                                handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.6
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                            } else {
                                handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(resultCode);
                                    }
                                });
                                return;
                            }
                        }
                    }
                    final Typeface typefaceBuildTypeface = FontsContractCompat.buildTypeface(context, null, fonts);
                    if (typefaceBuildTypeface == null) {
                        handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.8
                            @Override // java.lang.Runnable
                            public void run() {
                                fontRequestCallback.onTypefaceRequestFailed(-3);
                            }
                        });
                    } else {
                        handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.9
                            @Override // java.lang.Runnable
                            public void run() {
                                fontRequestCallback.onTypefaceRetrieved(typefaceBuildTypeface);
                            }
                        });
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-1);
                        }
                    });
                }
            }
        });
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void resetCache() {
        sTypefaceCache.evictAll();
    }
}
