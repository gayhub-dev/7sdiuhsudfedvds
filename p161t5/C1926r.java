package p161t5;

import com.alibaba.fastjson.parser.JSONLexer;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Properties;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: StringUtil.java */
/* renamed from: t5.r */
/* loaded from: classes.dex */
public class C1926r {

    /* renamed from: a */
    public static final InterfaceC2016c f5698a;

    /* renamed from: b */
    public static final Charset f5699b;

    /* renamed from: c */
    public static final Charset f5700c;

    /* renamed from: d */
    public static char[] f5701d;

    static {
        Properties properties = C2015b.f5863a;
        f5698a = C2015b.m2349a(C1926r.class.getName());
        System.getProperty("line.separator", "\n");
        f5699b = Charset.forName("UTF-8");
        f5700c = Charset.forName("ISO-8859-1");
        f5701d = new char[]{0, 1, 2, 3, 4, 5, 6, 7, '\b', '\t', '\n', 11, '\f', '\r', 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, JSONLexer.EOI, 27, 28, 29, 30, 31, ' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', 127};
    }

    /* renamed from: a */
    public static void m2251a(StringBuilder sb, int i7) {
        if (i7 < 100) {
            sb.append((char) ((i7 / 10) + 48));
            sb.append((char) ((i7 % 10) + 48));
        }
    }

    /* renamed from: b */
    public static String m2252b(String str) {
        int i7;
        char[] charArray;
        char c7;
        int length = str.length();
        while (true) {
            i7 = length - 1;
            if (length <= 0) {
                charArray = null;
                break;
            }
            char cCharAt = str.charAt(i7);
            if (cCharAt <= 127 && cCharAt != (c7 = f5701d[cCharAt])) {
                charArray = str.toCharArray();
                charArray[i7] = c7;
                break;
            }
            length = i7;
        }
        while (true) {
            int i8 = i7 - 1;
            if (i7 <= 0) {
                break;
            }
            if (charArray[i8] <= 127) {
                charArray[i8] = f5701d[charArray[i8]];
            }
            i7 = i8;
        }
        return charArray == null ? str : new String(charArray);
    }

    /* renamed from: c */
    public static byte[] m2253c(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (Exception e7) {
            f5698a.mo2358i(e7);
            return str.getBytes();
        }
    }

    /* renamed from: d */
    public static boolean m2254d(String str) {
        return "UTF-8".equalsIgnoreCase(str) || "UTF8".equalsIgnoreCase(str);
    }

    /* renamed from: e */
    public static String m2255e(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i7 = 0; i7 < str.length(); i7++) {
            char cCharAt = str.charAt(i7);
            if (!Character.isISOControl(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    /* renamed from: f */
    public static String m2256f(String str, String str2, String str3) {
        int length = 0;
        int iIndexOf = str.indexOf(str2, 0);
        if (iIndexOf == -1) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str3.length() + str.length());
        do {
            sb.append(str.substring(length, iIndexOf));
            sb.append(str3);
            length = str2.length() + iIndexOf;
            iIndexOf = str.indexOf(str2, length);
        } while (iIndexOf != -1);
        if (length < str.length()) {
            sb.append(str.substring(length, str.length()));
        }
        return sb.toString();
    }

    /* renamed from: g */
    public static String m2257g(byte[] bArr, int i7, int i8, String str) {
        try {
            return new String(bArr, i7, i8, str);
        } catch (UnsupportedEncodingException e7) {
            throw new IllegalArgumentException(e7);
        }
    }
}
