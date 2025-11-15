package p058g6;

import android.support.constraint.motion.C0081c;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.io.IOException;

/* compiled from: FormatUtils.java */
/* renamed from: g6.h */
/* loaded from: classes.dex */
public class C1070h {

    /* renamed from: a */
    public static final double f2076a = Math.log(10.0d);

    /* renamed from: b */
    public static final /* synthetic */ int f2077b = 0;

    /* renamed from: a */
    public static void m1110a(Appendable appendable, int i7, int i8) throws IOException {
        if (i7 < 0) {
            appendable.append('-');
            if (i7 == Integer.MIN_VALUE) {
                while (i8 > 10) {
                    appendable.append('0');
                    i8--;
                }
                appendable.append("2147483648");
                return;
            }
            i7 = -i7;
        }
        if (i7 < 10) {
            while (i8 > 1) {
                appendable.append('0');
                i8--;
            }
            appendable.append((char) (i7 + 48));
            return;
        }
        if (i7 >= 100) {
            int iLog = i7 < 1000 ? 3 : i7 < 10000 ? 4 : ((int) (Math.log(i7) / f2076a)) + 1;
            while (i8 > iLog) {
                appendable.append('0');
                i8--;
            }
            appendable.append(Integer.toString(i7));
            return;
        }
        while (i8 > 2) {
            appendable.append('0');
            i8--;
        }
        int i9 = ((i7 + 1) * 13421772) >> 27;
        appendable.append((char) (i9 + 48));
        appendable.append((char) (((i7 - (i9 << 3)) - (i9 << 1)) + 48));
    }

    /* renamed from: b */
    public static void m1111b(Appendable appendable, int i7) throws IOException {
        if (i7 < 0) {
            appendable.append('-');
            if (i7 == Integer.MIN_VALUE) {
                appendable.append("2147483648");
                return;
            }
            i7 = -i7;
        }
        if (i7 < 10) {
            appendable.append((char) (i7 + 48));
        } else {
            if (i7 >= 100) {
                appendable.append(Integer.toString(i7));
                return;
            }
            int i8 = ((i7 + 1) * 13421772) >> 27;
            appendable.append((char) (i8 + 48));
            appendable.append((char) (((i7 - (i8 << 3)) - (i8 << 1)) + 48));
        }
    }

    /* renamed from: c */
    public static int m1112c(long j7) {
        if (j7 < 0) {
            if (j7 != Long.MIN_VALUE) {
                return m1112c(-j7) + 1;
            }
            return 20;
        }
        if (j7 < 10) {
            return 1;
        }
        if (j7 < 100) {
            return 2;
        }
        if (j7 < 1000) {
            return 3;
        }
        if (j7 < 10000) {
            return 4;
        }
        return 1 + ((int) (Math.log(j7) / f2076a));
    }

    /* renamed from: d */
    public static String m1113d(String str, int i7) {
        int i8 = i7 + 32;
        String strConcat = str.length() <= i8 + 3 ? str : str.substring(0, i8).concat("...");
        if (i7 <= 0) {
            return "Invalid format: \"" + strConcat + '\"';
        }
        if (i7 >= str.length()) {
            return C0096a.m97a("Invalid format: \"", strConcat, "\" is too short");
        }
        StringBuilder sbM95a = C0081c.m95a("Invalid format: \"", strConcat, "\" is malformed at \"");
        sbM95a.append(strConcat.substring(i7));
        sbM95a.append('\"');
        return sbM95a.toString();
    }

    /* renamed from: e */
    public static int m1114e(CharSequence charSequence, int i7) {
        int iCharAt = charSequence.charAt(i7) - '0';
        return (charSequence.charAt(i7 + 1) + ((iCharAt << 3) + (iCharAt << 1))) - 48;
    }
}
