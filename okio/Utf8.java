package okio;

import android.support.constraint.motion.C0079a;
import android.support.graphics.drawable.C0116a;

/* loaded from: classes.dex */
public final class Utf8 {
    private Utf8() {
    }

    public static long size(String str) {
        return size(str, 0, str.length());
    }

    public static long size(String str, int i7, int i8) {
        long j7;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i7 < 0) {
            throw new IllegalArgumentException(C0079a.m93a("beginIndex < 0: ", i7));
        }
        if (i8 < i7) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i8 + " < " + i7);
        }
        if (i8 > str.length()) {
            StringBuilder sbM98a = C0116a.m98a("endIndex > string.length: ", i8, " > ");
            sbM98a.append(str.length());
            throw new IllegalArgumentException(sbM98a.toString());
        }
        long j8 = 0;
        while (i7 < i8) {
            char cCharAt = str.charAt(i7);
            if (cCharAt < 128) {
                j8++;
            } else {
                if (cCharAt < 2048) {
                    j7 = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    j7 = 3;
                } else {
                    int i9 = i7 + 1;
                    char cCharAt2 = i9 < i8 ? str.charAt(i9) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j8++;
                        i7 = i9;
                    } else {
                        j8 += 4;
                        i7 += 2;
                    }
                }
                j8 += j7;
            }
            i7++;
        }
        return j8;
    }
}
