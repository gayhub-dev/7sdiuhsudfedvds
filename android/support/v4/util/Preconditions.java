package android.support.v4.util;

import android.arch.lifecycle.C0063n;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.media.MediaDescriptionCompat;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import p009b.C0413b;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean z6) {
        if (!z6) {
            throw new IllegalArgumentException();
        }
    }

    public static float checkArgumentFinite(float f7, String str) {
        if (Float.isNaN(f7)) {
            throw new IllegalArgumentException(C0063n.m88a(str, " must not be NaN"));
        }
        if (Float.isInfinite(f7)) {
            throw new IllegalArgumentException(C0063n.m88a(str, " must not be infinite"));
        }
        return f7;
    }

    public static float checkArgumentInRange(float f7, float f8, float f9, String str) {
        if (Float.isNaN(f7)) {
            throw new IllegalArgumentException(C0063n.m88a(str, " must not be NaN"));
        }
        if (f7 < f8) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", str, Float.valueOf(f8), Float.valueOf(f9)));
        }
        if (f7 <= f9) {
            return f7;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", str, Float.valueOf(f8), Float.valueOf(f9)));
    }

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
    public static int checkArgumentNonnegative(int i7, String str) {
        if (i7 >= 0) {
            return i7;
        }
        throw new IllegalArgumentException(str);
    }

    public static int checkArgumentPositive(int i7, String str) {
        if (i7 > 0) {
            return i7;
        }
        throw new IllegalArgumentException(str);
    }

    public static float[] checkArrayElementsInRange(float[] fArr, float f7, float f8, String str) {
        checkNotNull(fArr, str + " must not be null");
        for (int i7 = 0; i7 < fArr.length; i7++) {
            float f9 = fArr[i7];
            if (Float.isNaN(f9)) {
                throw new IllegalArgumentException(str + "[" + i7 + "] must not be NaN");
            }
            if (f9 < f7) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too low)", str, Integer.valueOf(i7), Float.valueOf(f7), Float.valueOf(f8)));
            }
            if (f9 > f8) {
                throw new IllegalArgumentException(String.format(Locale.US, "%s[%d] is out of range of [%f, %f] (too high)", str, Integer.valueOf(i7), Float.valueOf(f7), Float.valueOf(f8)));
            }
        }
        return fArr;
    }

    public static <T> T[] checkArrayElementsNotNull(T[] tArr, String str) {
        if (tArr == null) {
            throw new NullPointerException(C0063n.m88a(str, " must not be null"));
        }
        for (int i7 = 0; i7 < tArr.length; i7++) {
            if (tArr[i7] == null) {
                throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", str, Integer.valueOf(i7)));
            }
        }
        return tArr;
    }

    @NonNull
    public static <C extends Collection<T>, T> C checkCollectionElementsNotNull(C c7, String str) {
        if (c7 == null) {
            throw new NullPointerException(C0063n.m88a(str, " must not be null"));
        }
        long j7 = 0;
        Iterator it = c7.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new NullPointerException(String.format(Locale.US, "%s[%d] must not be null", str, Long.valueOf(j7)));
            }
            j7++;
        }
        return c7;
    }

    public static <T> Collection<T> checkCollectionNotEmpty(Collection<T> collection, String str) {
        if (collection == null) {
            throw new NullPointerException(C0063n.m88a(str, " must not be null"));
        }
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(C0063n.m88a(str, " is empty"));
        }
        return collection;
    }

    public static int checkFlagsArgument(int i7, int i8) {
        if ((i7 & i8) == i7) {
            return i7;
        }
        StringBuilder sbM112a = C0413b.m112a("Requested flags 0x");
        sbM112a.append(Integer.toHexString(i7));
        sbM112a.append(", but only 0x");
        sbM112a.append(Integer.toHexString(i8));
        sbM112a.append(" are allowed");
        throw new IllegalArgumentException(sbM112a.toString());
    }

    @NonNull
    public static <T> T checkNotNull(T t6) {
        Objects.requireNonNull(t6);
        return t6;
    }

    public static void checkState(boolean z6, String str) {
        if (!z6) {
            throw new IllegalStateException(str);
        }
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t6) {
        if (TextUtils.isEmpty(t6)) {
            throw new IllegalArgumentException();
        }
        return t6;
    }

    public static void checkArgument(boolean z6, Object obj) {
        if (!z6) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
    public static int checkArgumentNonnegative(int i7) {
        if (i7 >= 0) {
            return i7;
        }
        throw new IllegalArgumentException();
    }

    @NonNull
    public static <T> T checkNotNull(T t6, Object obj) {
        if (t6 != null) {
            return t6;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void checkState(boolean z6) {
        checkState(z6, null);
    }

    public static long checkArgumentNonnegative(long j7) {
        if (j7 >= 0) {
            return j7;
        }
        throw new IllegalArgumentException();
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t6, Object obj) {
        if (TextUtils.isEmpty(t6)) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
        return t6;
    }

    public static long checkArgumentNonnegative(long j7, String str) {
        if (j7 >= 0) {
            return j7;
        }
        throw new IllegalArgumentException(str);
    }

    public static int checkArgumentInRange(int i7, int i8, int i9, String str) {
        if (i7 < i8) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Integer.valueOf(i8), Integer.valueOf(i9)));
        }
        if (i7 <= i9) {
            return i7;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Integer.valueOf(i8), Integer.valueOf(i9)));
    }

    public static long checkArgumentInRange(long j7, long j8, long j9, String str) {
        if (j7 < j8) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Long.valueOf(j8), Long.valueOf(j9)));
        }
        if (j7 <= j9) {
            return j7;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Long.valueOf(j8), Long.valueOf(j9)));
    }
}
