package p034d6;

import java.text.DateFormatSymbols;
import java.util.Comparator;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import p016b6.C0473d;

/* compiled from: GJLocaleSymbols.java */
/* renamed from: d6.h */
/* loaded from: classes.dex */
public class C0881h {

    /* renamed from: n */
    public static ConcurrentMap<Locale, C0881h> f1449n = new ConcurrentHashMap();

    /* renamed from: a */
    public final String[] f1450a;

    /* renamed from: b */
    public final String[] f1451b;

    /* renamed from: c */
    public final String[] f1452c;

    /* renamed from: d */
    public final String[] f1453d;

    /* renamed from: e */
    public final String[] f1454e;

    /* renamed from: f */
    public final String[] f1455f;

    /* renamed from: g */
    public final TreeMap<String, Integer> f1456g;

    /* renamed from: h */
    public final TreeMap<String, Integer> f1457h;

    /* renamed from: i */
    public final TreeMap<String, Integer> f1458i;

    /* renamed from: j */
    public final int f1459j;

    /* renamed from: k */
    public final int f1460k;

    /* renamed from: l */
    public final int f1461l;

    /* renamed from: m */
    public final int f1462m;

    public C0881h(Locale locale) {
        DateFormatSymbols dateFormatSymbolsM226b = C0473d.m226b(locale);
        this.f1450a = dateFormatSymbolsM226b.getEras();
        this.f1451b = m759d(dateFormatSymbolsM226b.getWeekdays());
        this.f1452c = m759d(dateFormatSymbolsM226b.getShortWeekdays());
        String[] months = dateFormatSymbolsM226b.getMonths();
        String[] strArr = new String[13];
        for (int i7 = 1; i7 < 13; i7++) {
            strArr[i7] = months[i7 - 1];
        }
        this.f1453d = strArr;
        String[] shortMonths = dateFormatSymbolsM226b.getShortMonths();
        String[] strArr2 = new String[13];
        for (int i8 = 1; i8 < 13; i8++) {
            strArr2[i8] = shortMonths[i8 - 1];
        }
        this.f1454e = strArr2;
        this.f1455f = dateFormatSymbolsM226b.getAmPmStrings();
        Integer[] numArr = new Integer[13];
        for (int i9 = 0; i9 < 13; i9++) {
            numArr[i9] = Integer.valueOf(i9);
        }
        Comparator comparator = String.CASE_INSENSITIVE_ORDER;
        TreeMap<String, Integer> treeMap = new TreeMap<>((Comparator<? super String>) comparator);
        this.f1456g = treeMap;
        m756a(treeMap, this.f1450a, numArr);
        if ("en".equals(locale.getLanguage())) {
            treeMap.put("BCE", numArr[0]);
            treeMap.put("CE", numArr[1]);
        }
        TreeMap<String, Integer> treeMap2 = new TreeMap<>((Comparator<? super String>) comparator);
        this.f1457h = treeMap2;
        m756a(treeMap2, this.f1451b, numArr);
        m756a(treeMap2, this.f1452c, numArr);
        for (int i10 = 1; i10 <= 7; i10++) {
            treeMap2.put(String.valueOf(i10).intern(), numArr[i10]);
        }
        TreeMap<String, Integer> treeMap3 = new TreeMap<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
        this.f1458i = treeMap3;
        m756a(treeMap3, this.f1453d, numArr);
        m756a(treeMap3, this.f1454e, numArr);
        for (int i11 = 1; i11 <= 12; i11++) {
            treeMap3.put(String.valueOf(i11).intern(), numArr[i11]);
        }
        this.f1459j = m758c(this.f1450a);
        this.f1460k = m758c(this.f1451b);
        m758c(this.f1452c);
        this.f1461l = m758c(this.f1453d);
        m758c(this.f1454e);
        this.f1462m = m758c(this.f1455f);
    }

    /* renamed from: a */
    public static void m756a(TreeMap<String, Integer> treeMap, String[] strArr, Integer[] numArr) {
        int length = strArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return;
            }
            String str = strArr[length];
            if (str != null) {
                treeMap.put(str, numArr[length]);
            }
        }
    }

    /* renamed from: b */
    public static C0881h m757b(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        C0881h c0881h = (C0881h) ((ConcurrentHashMap) f1449n).get(locale);
        if (c0881h != null) {
            return c0881h;
        }
        C0881h c0881h2 = new C0881h(locale);
        C0881h c0881h3 = (C0881h) ((ConcurrentHashMap) f1449n).putIfAbsent(locale, c0881h2);
        return c0881h3 != null ? c0881h3 : c0881h2;
    }

    /* renamed from: c */
    public static int m758c(String[] strArr) {
        int length;
        int length2 = strArr.length;
        int i7 = 0;
        while (true) {
            length2--;
            if (length2 < 0) {
                return i7;
            }
            String str = strArr[length2];
            if (str != null && (length = str.length()) > i7) {
                i7 = length;
            }
        }
    }

    /* renamed from: d */
    public static String[] m759d(String[] strArr) {
        String[] strArr2 = new String[8];
        int i7 = 1;
        while (i7 < 8) {
            strArr2[i7] = strArr[i7 < 7 ? i7 + 1 : 1];
            i7++;
        }
        return strArr2;
    }
}
