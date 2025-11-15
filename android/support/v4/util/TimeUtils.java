package android.support.v4.util;

import android.support.annotation.RestrictTo;
import java.io.PrintWriter;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class TimeUtils {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final Object sFormatSync = new Object();
    private static char[] sFormatStr = new char[24];

    private TimeUtils() {
    }

    private static int accumField(int i7, int i8, boolean z6, int i9) {
        if (i7 > 99 || (z6 && i9 >= 3)) {
            return i8 + 3;
        }
        if (i7 > 9 || (z6 && i9 >= 2)) {
            return i8 + 2;
        }
        if (z6 || i7 > 0) {
            return i8 + 1;
        }
        return 0;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j7, StringBuilder sb) {
        synchronized (sFormatSync) {
            sb.append(sFormatStr, 0, formatDurationLocked(j7, 0));
        }
    }

    private static int formatDurationLocked(long j7, int i7) {
        char c7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        long j8 = j7;
        if (sFormatStr.length < i7) {
            sFormatStr = new char[i7];
        }
        char[] cArr = sFormatStr;
        if (j8 == 0) {
            int i13 = i7 - 1;
            while (i13 > 0) {
                cArr[0] = ' ';
            }
            cArr[0] = '0';
            return 1;
        }
        if (j8 > 0) {
            c7 = '+';
        } else {
            c7 = '-';
            j8 = -j8;
        }
        int i14 = (int) (j8 % 1000);
        int iFloor = (int) Math.floor(j8 / 1000);
        if (iFloor > SECONDS_PER_DAY) {
            i8 = iFloor / SECONDS_PER_DAY;
            iFloor -= SECONDS_PER_DAY * i8;
        } else {
            i8 = 0;
        }
        if (iFloor > SECONDS_PER_HOUR) {
            i9 = iFloor / SECONDS_PER_HOUR;
            iFloor -= i9 * SECONDS_PER_HOUR;
        } else {
            i9 = 0;
        }
        if (iFloor > 60) {
            int i15 = iFloor / 60;
            i10 = iFloor - (i15 * 60);
            i11 = i15;
        } else {
            i10 = iFloor;
            i11 = 0;
        }
        if (i7 != 0) {
            int iAccumField = accumField(i8, 1, false, 0);
            int iAccumField2 = iAccumField + accumField(i9, 1, iAccumField > 0, 2);
            int iAccumField3 = iAccumField2 + accumField(i11, 1, iAccumField2 > 0, 2);
            int iAccumField4 = iAccumField3 + accumField(i10, 1, iAccumField3 > 0, 2);
            i12 = 0;
            for (int iAccumField5 = accumField(i14, 2, true, iAccumField4 > 0 ? 3 : 0) + 1 + iAccumField4; iAccumField5 < i7; iAccumField5++) {
                cArr[i12] = ' ';
                i12++;
            }
        } else {
            i12 = 0;
        }
        cArr[i12] = c7;
        int i16 = i12 + 1;
        boolean z6 = i7 != 0;
        int iPrintField = printField(cArr, i8, 'd', i16, false, 0);
        int iPrintField2 = printField(cArr, i9, 'h', iPrintField, iPrintField != i16, z6 ? 2 : 0);
        int iPrintField3 = printField(cArr, i11, 'm', iPrintField2, iPrintField2 != i16, z6 ? 2 : 0);
        int iPrintField4 = printField(cArr, i10, 's', iPrintField3, iPrintField3 != i16, z6 ? 2 : 0);
        int iPrintField5 = printField(cArr, i14, 'm', iPrintField4, true, (!z6 || iPrintField4 == i16) ? 0 : 3);
        cArr[iPrintField5] = 's';
        return iPrintField5 + 1;
    }

    private static int printField(char[] cArr, int i7, char c7, int i8, boolean z6, int i9) {
        int i10;
        if (!z6 && i7 <= 0) {
            return i8;
        }
        if ((!z6 || i9 < 3) && i7 <= 99) {
            i10 = i8;
        } else {
            int i11 = i7 / 100;
            cArr[i8] = (char) (i11 + 48);
            i10 = i8 + 1;
            i7 -= i11 * 100;
        }
        if ((z6 && i9 >= 2) || i7 > 9 || i8 != i10) {
            int i12 = i7 / 10;
            cArr[i10] = (char) (i12 + 48);
            i10++;
            i7 -= i12 * 10;
        }
        cArr[i10] = (char) (i7 + 48);
        int i13 = i10 + 1;
        cArr[i13] = c7;
        return i13 + 1;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j7, PrintWriter printWriter, int i7) {
        synchronized (sFormatSync) {
            printWriter.print(new String(sFormatStr, 0, formatDurationLocked(j7, i7)));
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j7, PrintWriter printWriter) {
        formatDuration(j7, printWriter, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j7, long j8, PrintWriter printWriter) {
        if (j7 == 0) {
            printWriter.print("--");
        } else {
            formatDuration(j7 - j8, printWriter, 0);
        }
    }
}
