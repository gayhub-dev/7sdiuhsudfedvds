package p161t5;

import android.arch.lifecycle.C0063n;
import java.io.UnsupportedEncodingException;
import org.fourthline.cling.model.ServiceReference;

/* compiled from: URIUtil.java */
/* renamed from: t5.t */
/* loaded from: classes.dex */
public class C1928t implements Cloneable {

    /* renamed from: e */
    public static final String f5703e = System.getProperty("org.eclipse.jetty.util.URI.charset", "UTF-8");

    /* renamed from: b */
    public static String m2264b(String str, String str2) {
        if (str == null || str.length() == 0) {
            return (str == null || str2 != null) ? str2 : str;
        }
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        int iIndexOf = str.indexOf(59);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(63);
        }
        if (iIndexOf == 0) {
            return C0063n.m88a(str2, str);
        }
        if (iIndexOf < 0) {
            iIndexOf = str.length();
        }
        StringBuilder sb = new StringBuilder(str2.length() + str.length() + 2);
        sb.append(str);
        int i7 = iIndexOf - 1;
        if (sb.charAt(i7) == '/') {
            if (str2.startsWith(ServiceReference.DELIMITER)) {
                sb.deleteCharAt(i7);
                sb.insert(i7, str2);
            } else {
                sb.insert(iIndexOf, str2);
            }
        } else if (str2.startsWith(ServiceReference.DELIMITER)) {
            sb.insert(iIndexOf, str2);
        } else {
            sb.insert(iIndexOf, '/');
            sb.insert(iIndexOf + 1, str2);
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0128 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0072 A[PHI: r8 r10
      0x0072: PHI (r8v5 int) = (r8v2 int), (r8v7 int), (r8v8 int) binds: [B:76:0x00da, B:37:0x0070, B:62:0x00b6] A[DONT_GENERATE, DONT_INLINE]
      0x0072: PHI (r10v17 int) = (r10v11 int), (r10v21 int), (r10v24 int) binds: [B:76:0x00da, B:37:0x0070, B:62:0x00b6] A[DONT_GENERATE, DONT_INLINE]] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m2265d(java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p161t5.C1928t.m2265d(java.lang.String):java.lang.String");
    }

    /* renamed from: g */
    public static String m2266g(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            char cCharAt = str.charAt(i7);
            if (cCharAt == '/') {
                i8++;
                if (i8 == 2) {
                    break;
                }
            } else {
                if (cCharAt == '?') {
                    return str;
                }
                i8 = 0;
            }
            i7++;
        }
        if (i8 < 2) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length());
        stringBuffer.append((CharSequence) str, 0, i7);
        while (true) {
            if (i7 >= length) {
                break;
            }
            char cCharAt2 = str.charAt(i7);
            if (cCharAt2 == '/') {
                int i9 = i8 + 1;
                if (i8 == 0) {
                    stringBuffer.append(cCharAt2);
                }
                i8 = i9;
            } else {
                if (cCharAt2 == '?') {
                    stringBuffer.append((CharSequence) str, i7, length);
                    break;
                }
                stringBuffer.append(cCharAt2);
                i8 = 0;
            }
            i7++;
        }
        return stringBuffer.toString();
    }

    /* renamed from: j */
    public static String m2267j(String str) {
        String str2;
        String str3;
        int i7;
        char[] cArr = null;
        if (str == null) {
            return null;
        }
        int length = str.length();
        byte[] bArr = null;
        int length2 = 0;
        int i8 = 0;
        int length3 = 0;
        while (true) {
            if (length2 >= length) {
                break;
            }
            char cCharAt = str.charAt(length2);
            if (cCharAt == '%' && (i7 = length2 + 2) < length) {
                if (cArr == null) {
                    cArr = new char[length];
                    bArr = new byte[length];
                    str.getChars(0, length2, cArr, 0);
                }
                bArr[i8] = (byte) (C1927s.m2261d(str, length2 + 1, 2, 16) & 255);
                i8++;
                length2 = i7;
            } else if (cCharAt == ';') {
                if (cArr == null) {
                    cArr = new char[length];
                    str.getChars(0, length2, cArr, 0);
                }
            } else if (bArr == null) {
                length3++;
            } else {
                if (i8 > 0) {
                    try {
                        str3 = new String(bArr, 0, i8, f5703e);
                    } catch (UnsupportedEncodingException unused) {
                        str3 = new String(bArr, 0, i8);
                    }
                    str3.getChars(0, str3.length(), cArr, length3);
                    length3 += str3.length();
                    i8 = 0;
                }
                cArr[length3] = cCharAt;
                length3++;
            }
            length2++;
        }
        length2 = length3;
        if (cArr == null) {
            return str;
        }
        if (i8 > 0) {
            try {
                str2 = new String(bArr, 0, i8, f5703e);
            } catch (UnsupportedEncodingException unused2) {
                str2 = new String(bArr, 0, i8);
            }
            str2.getChars(0, str2.length(), cArr, length2);
            length2 += str2.length();
        }
        return new String(cArr, 0, length2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:130:?, code lost:
    
        return r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
    
        r11 = new java.lang.StringBuilder(r14.length() * 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0069, code lost:
    
        r10 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006a, code lost:
    
        if (r11 != null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006e, code lost:
    
        monitor-enter(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006f, code lost:
    
        if (r10 == null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0072, code lost:
    
        if (r9 >= r10.length) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0074, code lost:
    
        r12 = r10[r9];
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0076, code lost:
    
        if (r12 == 32) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0078, code lost:
    
        if (r12 == 37) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x007a, code lost:
    
        if (r12 == 39) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007c, code lost:
    
        if (r12 == 34) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007e, code lost:
    
        if (r12 == 35) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0080, code lost:
    
        if (r12 == 59) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0082, code lost:
    
        if (r12 == 60) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0084, code lost:
    
        if (r12 == 62) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0086, code lost:
    
        if (r12 == 63) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0088, code lost:
    
        if (r12 >= 0) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x008a, code lost:
    
        r11.append('%');
        p161t5.C1927s.m2263f(r12, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0091, code lost:
    
        r11.append((char) r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0096, code lost:
    
        r11.append("%3F");
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x009c, code lost:
    
        r11.append("%3E");
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00a2, code lost:
    
        r11.append("%3C");
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a8, code lost:
    
        r11.append("%3B");
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ae, code lost:
    
        r11.append("%23");
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b4, code lost:
    
        r11.append("%22");
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ba, code lost:
    
        r11.append("%27");
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c0, code lost:
    
        r11.append("%25");
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c6, code lost:
    
        r11.append("%20");
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00cb, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ce, code lost:
    
        r14 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00d5, code lost:
    
        if (r9 >= r14.length()) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00d7, code lost:
    
        r10 = r14.charAt(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00db, code lost:
    
        if (r10 == ' ') goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00dd, code lost:
    
        if (r10 == '%') goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00df, code lost:
    
        if (r10 == '\'') goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e1, code lost:
    
        if (r10 == '\"') goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e3, code lost:
    
        if (r10 == '#') goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e5, code lost:
    
        if (r10 == ';') goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e7, code lost:
    
        if (r10 == '<') goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00e9, code lost:
    
        if (r10 == '>') goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00eb, code lost:
    
        if (r10 == '?') goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ed, code lost:
    
        r11.append(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00f1, code lost:
    
        r11.append("%3F");
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00f7, code lost:
    
        r11.append("%3E");
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00fd, code lost:
    
        r11.append("%3C");
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0103, code lost:
    
        r11.append("%3B");
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0109, code lost:
    
        r11.append("%23");
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x010f, code lost:
    
        r11.append("%22");
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0115, code lost:
    
        r11.append("%27");
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x011b, code lost:
    
        r11.append("%25");
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0121, code lost:
    
        r11.append("%20");
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0126, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0129, code lost:
    
        monitor-exit(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x012a, code lost:
    
        r12 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x012b, code lost:
    
        if (r12 != null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0132, code lost:
    
        return r12.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0134, code lost:
    
        throw r14;
     */
    /* renamed from: k */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m2268k(java.lang.String r14) throws java.io.UnsupportedEncodingException {
        /*
            Method dump skipped, instructions count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p161t5.C1928t.m2268k(java.lang.String):java.lang.String");
    }

    /* renamed from: q */
    public static boolean m2269q(String str) {
        for (int i7 = 0; i7 < str.length(); i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt == ':') {
                return true;
            }
            if ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < 'A' || cCharAt > 'Z') && (i7 <= 0 || !((cCharAt >= '0' && cCharAt <= '9') || cCharAt == '.' || cCharAt == '+' || cCharAt == '-')))) {
                break;
            }
        }
        return false;
    }
}
