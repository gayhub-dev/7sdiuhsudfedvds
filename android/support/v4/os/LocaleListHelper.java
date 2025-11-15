package android.support.v4.os;

import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.Size;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v7.widget.ActivityChooserView;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import p009b.C0413b;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
final class LocaleListHelper {
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    private final Locale[] mList;

    @NonNull
    private final String mStringRepresentation;
    private static final Locale[] sEmptyList = new Locale[0];
    private static final LocaleListHelper sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
    private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
    private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
    private static final Locale EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
    private static final Object sLock = new Object();

    @GuardedBy("sLock")
    private static LocaleListHelper sLastExplicitlySetLocaleList = null;

    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultLocaleList = null;

    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultAdjustedLocaleList = null;

    @GuardedBy("sLock")
    private static Locale sLastDefaultLocale = null;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public LocaleListHelper(@NonNull Locale... localeArr) {
        if (localeArr.length == 0) {
            this.mList = sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        Locale[] localeArr2 = new Locale[localeArr.length];
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (int i7 = 0; i7 < localeArr.length; i7++) {
            Locale locale = localeArr[i7];
            if (locale == null) {
                throw new NullPointerException("list[" + i7 + "] is null");
            }
            if (hashSet.contains(locale)) {
                throw new IllegalArgumentException("list[" + i7 + "] is a repetition");
            }
            Locale locale2 = (Locale) locale.clone();
            localeArr2[i7] = locale2;
            sb.append(LocaleHelper.toLanguageTag(locale2));
            if (i7 < localeArr.length - 1) {
                sb.append(',');
            }
            hashSet.add(locale2);
        }
        this.mList = localeArr2;
        this.mStringRepresentation = sb.toString();
    }

    private Locale computeFirstMatch(Collection<String> collection, boolean z6) {
        int iComputeFirstMatchIndex = computeFirstMatchIndex(collection, z6);
        if (iComputeFirstMatchIndex == -1) {
            return null;
        }
        return this.mList[iComputeFirstMatchIndex];
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int computeFirstMatchIndex(java.util.Collection<java.lang.String> r5, boolean r6) {
        /*
            r4 = this;
            java.util.Locale[] r0 = r4.mList
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L8
            return r2
        L8:
            int r0 = r0.length
            if (r0 != 0) goto Ld
            r5 = -1
            return r5
        Ld:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r6 == 0) goto L1e
            java.util.Locale r6 = android.support.v4.os.LocaleListHelper.EN_LATN
            int r6 = r4.findFirstMatchIndex(r6)
            if (r6 != 0) goto L1b
            return r2
        L1b:
            if (r6 >= r0) goto L1e
            goto L21
        L1e:
            r6 = 2147483647(0x7fffffff, float:NaN)
        L21:
            java.util.Iterator r5 = r5.iterator()
        L25:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L40
            java.lang.Object r1 = r5.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Locale r1 = android.support.v4.os.LocaleHelper.forLanguageTag(r1)
            int r1 = r4.findFirstMatchIndex(r1)
            if (r1 != 0) goto L3c
            return r2
        L3c:
            if (r1 >= r6) goto L25
            r6 = r1
            goto L25
        L40:
            if (r6 != r0) goto L43
            return r2
        L43:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.os.LocaleListHelper.computeFirstMatchIndex(java.util.Collection, boolean):int");
    }

    private int findFirstMatchIndex(Locale locale) {
        int i7 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i7 >= localeArr.length) {
                return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            if (matchScore(locale, localeArr[i7]) > 0) {
                return i7;
            }
            i7++;
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static LocaleListHelper forLanguageTags(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] strArrSplit = str.split(",", -1);
        int length = strArrSplit.length;
        Locale[] localeArr = new Locale[length];
        for (int i7 = 0; i7 < length; i7++) {
            localeArr[i7] = LocaleHelper.forLanguageTag(strArrSplit[i7]);
        }
        return new LocaleListHelper(localeArr);
    }

    @Size(min = 1)
    @NonNull
    public static LocaleListHelper getAdjustedDefault() {
        LocaleListHelper localeListHelper;
        getDefault();
        synchronized (sLock) {
            localeListHelper = sDefaultAdjustedLocaleList;
        }
        return localeListHelper;
    }

    @Size(min = 1)
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static LocaleListHelper getDefault() {
        Locale locale = Locale.getDefault();
        synchronized (sLock) {
            if (!locale.equals(sLastDefaultLocale)) {
                sLastDefaultLocale = locale;
                LocaleListHelper localeListHelper = sDefaultLocaleList;
                if (localeListHelper != null && locale.equals(localeListHelper.get(0))) {
                    return sDefaultLocaleList;
                }
                LocaleListHelper localeListHelper2 = new LocaleListHelper(locale, sLastExplicitlySetLocaleList);
                sDefaultLocaleList = localeListHelper2;
                sDefaultAdjustedLocaleList = localeListHelper2;
            }
            return sDefaultLocaleList;
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static LocaleListHelper getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    private static String getLikelyScript(Locale locale) {
        String script = locale.getScript();
        return !script.isEmpty() ? script : "";
    }

    private static boolean isPseudoLocale(String str) {
        return STRING_EN_XA.equals(str) || STRING_AR_XB.equals(str);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static boolean isPseudoLocalesOnly(@Nullable String[] strArr) {
        if (strArr == null) {
            return true;
        }
        if (strArr.length > 3) {
            return false;
        }
        for (String str : strArr) {
            if (!str.isEmpty() && !isPseudoLocale(str)) {
                return false;
            }
        }
        return true;
    }

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED, m91to = 1)
    private static int matchScore(Locale locale, Locale locale2) {
        if (locale.equals(locale2)) {
            return 1;
        }
        if (!locale.getLanguage().equals(locale2.getLanguage()) || isPseudoLocale(locale) || isPseudoLocale(locale2)) {
            return 0;
        }
        String likelyScript = getLikelyScript(locale);
        if (!likelyScript.isEmpty()) {
            return likelyScript.equals(getLikelyScript(locale2)) ? 1 : 0;
        }
        String country = locale.getCountry();
        return (country.isEmpty() || country.equals(locale2.getCountry())) ? 1 : 0;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void setDefault(@Size(min = 1) @NonNull LocaleListHelper localeListHelper) {
        setDefault(localeListHelper, 0);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocaleListHelper)) {
            return false;
        }
        Locale[] localeArr = ((LocaleListHelper) obj).mList;
        if (this.mList.length != localeArr.length) {
            return false;
        }
        int i7 = 0;
        while (true) {
            Locale[] localeArr2 = this.mList;
            if (i7 >= localeArr2.length) {
                return true;
            }
            if (!localeArr2[i7].equals(localeArr[i7])) {
                return false;
            }
            i7++;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Locale get(int i7) {
        if (i7 >= 0) {
            Locale[] localeArr = this.mList;
            if (i7 < localeArr.length) {
                return localeArr[i7];
            }
        }
        return null;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Locale getFirstMatch(String[] strArr) {
        return computeFirstMatch(Arrays.asList(strArr), false);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getFirstMatchIndex(String[] strArr) {
        return computeFirstMatchIndex(Arrays.asList(strArr), false);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getFirstMatchIndexWithEnglishSupported(Collection<String> collection) {
        return computeFirstMatchIndex(collection, true);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Locale getFirstMatchWithEnglishSupported(String[] strArr) {
        return computeFirstMatch(Arrays.asList(strArr), true);
    }

    public int hashCode() {
        int iHashCode = 1;
        int i7 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i7 >= localeArr.length) {
                return iHashCode;
            }
            iHashCode = (iHashCode * 31) + localeArr[i7].hashCode();
            i7++;
        }
    }

    @IntRange(from = -1)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int indexOf(Locale locale) {
        int i7 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i7 >= localeArr.length) {
                return -1;
            }
            if (localeArr[i7].equals(locale)) {
                return i7;
            }
            i7++;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isEmpty() {
        return this.mList.length == 0;
    }

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int size() {
        return this.mList.length;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String toLanguageTags() {
        return this.mStringRepresentation;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("[");
        int i7 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i7 >= localeArr.length) {
                sbM112a.append("]");
                return sbM112a.toString();
            }
            sbM112a.append(localeArr[i7]);
            if (i7 < this.mList.length - 1) {
                sbM112a.append(',');
            }
            i7++;
        }
    }

    private static boolean isPseudoLocale(Locale locale) {
        return LOCALE_EN_XA.equals(locale) || LOCALE_AR_XB.equals(locale);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void setDefault(@Size(min = 1) @NonNull LocaleListHelper localeListHelper, int i7) {
        Objects.requireNonNull(localeListHelper, "locales is null");
        if (localeListHelper.isEmpty()) {
            throw new IllegalArgumentException("locales is empty");
        }
        synchronized (sLock) {
            Locale locale = localeListHelper.get(i7);
            sLastDefaultLocale = locale;
            Locale.setDefault(locale);
            sLastExplicitlySetLocaleList = localeListHelper;
            sDefaultLocaleList = localeListHelper;
            if (i7 == 0) {
                sDefaultAdjustedLocaleList = localeListHelper;
            } else {
                sDefaultAdjustedLocaleList = new LocaleListHelper(sLastDefaultLocale, localeListHelper);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getFirstMatchIndexWithEnglishSupported(String[] strArr) {
        return getFirstMatchIndexWithEnglishSupported(Arrays.asList(strArr));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public LocaleListHelper(@NonNull Locale locale, LocaleListHelper localeListHelper) {
        Objects.requireNonNull(locale, "topLocale is null");
        int length = localeListHelper == null ? 0 : localeListHelper.mList.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                i7 = -1;
                break;
            } else if (locale.equals(localeListHelper.mList[i7])) {
                break;
            } else {
                i7++;
            }
        }
        int i8 = (i7 == -1 ? 1 : 0) + length;
        Locale[] localeArr = new Locale[i8];
        localeArr[0] = (Locale) locale.clone();
        if (i7 == -1) {
            int i9 = 0;
            while (i9 < length) {
                int i10 = i9 + 1;
                localeArr[i10] = (Locale) localeListHelper.mList[i9].clone();
                i9 = i10;
            }
        } else {
            int i11 = 0;
            while (i11 < i7) {
                int i12 = i11 + 1;
                localeArr[i12] = (Locale) localeListHelper.mList[i11].clone();
                i11 = i12;
            }
            for (int i13 = i7 + 1; i13 < length; i13++) {
                localeArr[i13] = (Locale) localeListHelper.mList[i13].clone();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i14 = 0; i14 < i8; i14++) {
            sb.append(LocaleHelper.toLanguageTag(localeArr[i14]));
            if (i14 < i8 - 1) {
                sb.append(',');
            }
        }
        this.mList = localeArr;
        this.mStringRepresentation = sb.toString();
    }
}
