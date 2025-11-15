package p066h6;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import p016b6.C0473d;

/* compiled from: DefaultNameProvider.java */
/* renamed from: h6.c */
/* loaded from: classes.dex */
public class C1120c implements InterfaceC1122e {

    /* renamed from: a */
    public HashMap<Locale, Map<String, Map<String, Object>>> f2437a = m1279c();

    /* renamed from: b */
    public HashMap<Locale, Map<String, Map<Boolean, Object>>> f2438b = m1279c();

    @Override // p066h6.InterfaceC1122e
    /* renamed from: a */
    public String mo1277a(Locale locale, String str, String str2) {
        String[] strArrM1280d = m1280d(locale, str, str2);
        if (strArrM1280d == null) {
            return null;
        }
        return strArrM1280d[1];
    }

    @Override // p066h6.InterfaceC1122e
    /* renamed from: b */
    public String mo1278b(Locale locale, String str, String str2) {
        String[] strArrM1280d = m1280d(locale, str, str2);
        if (strArrM1280d == null) {
            return null;
        }
        return strArrM1280d[0];
    }

    /* renamed from: c */
    public final HashMap m1279c() {
        return new HashMap(7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.util.HashMap] */
    /* renamed from: d */
    public final synchronized String[] m1280d(Locale locale, String str, String str2) {
        String[] strArr;
        String[] strArr2 = null;
        if (locale == null || str == 0 || str2 == null) {
            return null;
        }
        Map map = this.f2437a.get(locale);
        if (map == null) {
            HashMap<Locale, Map<String, Map<String, Object>>> map2 = this.f2437a;
            HashMap mapM1279c = m1279c();
            map2.put(locale, mapM1279c);
            map = mapM1279c;
        }
        ?? M1279c = (Map) map.get(str);
        if (M1279c == 0) {
            M1279c = m1279c();
            map.put(str, M1279c);
            String[][] zoneStrings = C0473d.m226b(Locale.ENGLISH).getZoneStrings();
            int length = zoneStrings.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    strArr = null;
                    break;
                }
                strArr = zoneStrings[i7];
                if (strArr != null && strArr.length >= 5 && str.equals(strArr[0])) {
                    break;
                }
                i7++;
            }
            String[][] zoneStrings2 = C0473d.m226b(locale).getZoneStrings();
            int length2 = zoneStrings2.length;
            int i8 = 0;
            while (true) {
                if (i8 < length2) {
                    String[] strArr3 = zoneStrings2[i8];
                    if (strArr3 != null && strArr3.length >= 5 && str.equals(strArr3[0])) {
                        strArr2 = strArr3;
                        break;
                    }
                    i8++;
                } else {
                    break;
                }
            }
            if (strArr != null && strArr2 != null) {
                M1279c.put(strArr[2], new String[]{strArr2[2], strArr2[1]});
                if (strArr[2].equals(strArr[4])) {
                    M1279c.put(strArr[4] + "-Summer", new String[]{strArr2[4], strArr2[3]});
                } else {
                    M1279c.put(strArr[4], new String[]{strArr2[4], strArr2[3]});
                }
            }
        }
        return (String[]) M1279c.get(str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object, java.util.HashMap] */
    /* renamed from: e */
    public final synchronized String[] m1281e(Locale locale, String str, String str2, boolean z6) {
        String[] strArr;
        String[] strArr2 = null;
        if (locale == null || str == null || str2 == null) {
            return null;
        }
        if (str.startsWith("Etc/")) {
            str = str.substring(4);
        }
        Map map = this.f2438b.get(locale);
        if (map == null) {
            HashMap<Locale, Map<String, Map<Boolean, Object>>> map2 = this.f2438b;
            HashMap mapM1279c = m1279c();
            map2.put(locale, mapM1279c);
            map = mapM1279c;
        }
        ?? M1279c = (Map) map.get(str);
        if (M1279c == 0) {
            M1279c = m1279c();
            map.put(str, M1279c);
            String[][] zoneStrings = C0473d.m226b(Locale.ENGLISH).getZoneStrings();
            int length = zoneStrings.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    strArr = null;
                    break;
                }
                strArr = zoneStrings[i7];
                if (strArr != null && strArr.length >= 5 && str.equals(strArr[0])) {
                    break;
                }
                i7++;
            }
            String[][] zoneStrings2 = C0473d.m226b(locale).getZoneStrings();
            int length2 = zoneStrings2.length;
            int i8 = 0;
            while (true) {
                if (i8 < length2) {
                    String[] strArr3 = zoneStrings2[i8];
                    if (strArr3 != null && strArr3.length >= 5 && str.equals(strArr3[0])) {
                        strArr2 = strArr3;
                        break;
                    }
                    i8++;
                } else {
                    break;
                }
            }
            if (strArr != null && strArr2 != null) {
                M1279c.put(Boolean.TRUE, new String[]{strArr2[2], strArr2[1]});
                M1279c.put(Boolean.FALSE, new String[]{strArr2[4], strArr2[3]});
            }
        }
        return (String[]) M1279c.get(Boolean.valueOf(z6));
    }
}
