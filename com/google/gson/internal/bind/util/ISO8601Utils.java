package com.google.gson.internal.bind.util;

import com.ctvit.network.CtvitHttp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import p009b.C0413b;

/* loaded from: classes.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    private static boolean checkOffset(String str, int i7, char c7) {
        return i7 < str.length() && str.charAt(i7) == c7;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    private static int indexOfNonDigit(String str, int i7) {
        while (i7 < str.length()) {
            char cCharAt = str.charAt(i7);
            if (cCharAt < '0' || cCharAt > '9') {
                return i7;
            }
            i7++;
        }
        return str.length();
    }

    private static void padInt(StringBuilder sb, int i7, int i8) {
        String string = Integer.toString(i7);
        for (int length = i8 - string.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(string);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00d3 A[Catch: IllegalArgumentException -> 0x01bf, NumberFormatException -> 0x01c1, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01c3, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01c3, blocks: (B:3:0x0006, B:5:0x0018, B:6:0x001a, B:8:0x0026, B:9:0x0028, B:11:0x0037, B:13:0x003d, B:17:0x0052, B:19:0x0062, B:20:0x0064, B:22:0x0070, B:23:0x0072, B:25:0x0078, B:29:0x0082, B:34:0x0092, B:36:0x009a, B:47:0x00cd, B:49:0x00d3, B:51:0x00da, B:75:0x0185, B:56:0x00e6, B:57:0x00ff, B:58:0x0100, B:62:0x011c, B:64:0x0129, B:67:0x0132, B:69:0x0151, B:72:0x0160, B:73:0x0182, B:74:0x0183, B:61:0x010b, B:77:0x01b7, B:78:0x01be, B:40:0x00b2, B:41:0x00b5), top: B:94:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b7 A[Catch: IllegalArgumentException -> 0x01bf, NumberFormatException -> 0x01c1, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01c3, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01c3, blocks: (B:3:0x0006, B:5:0x0018, B:6:0x001a, B:8:0x0026, B:9:0x0028, B:11:0x0037, B:13:0x003d, B:17:0x0052, B:19:0x0062, B:20:0x0064, B:22:0x0070, B:23:0x0072, B:25:0x0078, B:29:0x0082, B:34:0x0092, B:36:0x009a, B:47:0x00cd, B:49:0x00d3, B:51:0x00da, B:75:0x0185, B:56:0x00e6, B:57:0x00ff, B:58:0x0100, B:62:0x011c, B:64:0x0129, B:67:0x0132, B:69:0x0151, B:72:0x0160, B:73:0x0182, B:74:0x0183, B:61:0x010b, B:77:0x01b7, B:78:0x01be, B:40:0x00b2, B:41:0x00b5), top: B:94:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Date parse(java.lang.String r18, java.text.ParsePosition r19) throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 552
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static int parseInt(String str, int i7, int i8) {
        int i9;
        int i10;
        if (i7 < 0 || i8 > str.length() || i7 > i8) {
            throw new NumberFormatException(str);
        }
        if (i7 < i8) {
            i10 = i7 + 1;
            int iDigit = Character.digit(str.charAt(i7), 10);
            if (iDigit < 0) {
                StringBuilder sbM112a = C0413b.m112a("Invalid number: ");
                sbM112a.append(str.substring(i7, i8));
                throw new NumberFormatException(sbM112a.toString());
            }
            i9 = -iDigit;
        } else {
            i9 = 0;
            i10 = i7;
        }
        while (i10 < i8) {
            int i11 = i10 + 1;
            int iDigit2 = Character.digit(str.charAt(i10), 10);
            if (iDigit2 < 0) {
                StringBuilder sbM112a2 = C0413b.m112a("Invalid number: ");
                sbM112a2.append(str.substring(i7, i8));
                throw new NumberFormatException(sbM112a2.toString());
            }
            i9 = (i9 * 10) - iDigit2;
            i10 = i11;
        }
        return -i9;
    }

    public static String format(Date date, boolean z6) {
        return format(date, z6, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z6, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z6 ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z6) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i7 = offset / CtvitHttp.DEFAULT_MILLISECONDS;
            int iAbs = Math.abs(i7 / 60);
            int iAbs2 = Math.abs(i7 % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, iAbs, 2);
            sb.append(':');
            padInt(sb, iAbs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }
}
