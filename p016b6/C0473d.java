package p016b6;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import p034d6.C0885l;
import p159t3.AbstractC1904c;

/* compiled from: DateTimeUtils.java */
/* renamed from: b6.d */
/* loaded from: classes.dex */
public class C0473d {

    /* renamed from: a */
    public static final AtomicReference<Map<String, AbstractC0475f>> f313a = new AtomicReference<>();

    /* renamed from: a */
    public static final AbstractC1904c m225a(AbstractC1904c abstractC1904c) {
        return abstractC1904c == null ? C0885l.m761S() : abstractC1904c;
    }

    /* renamed from: b */
    public static final DateFormatSymbols m226b(Locale locale) {
        try {
            return (DateFormatSymbols) DateFormatSymbols.class.getMethod("getInstance", Locale.class).invoke(null, locale);
        } catch (Exception unused) {
            return new DateFormatSymbols(locale);
        }
    }

    /* renamed from: c */
    public static void m227c(Map<String, AbstractC0475f> map, String str, String str2) {
        try {
            map.put(str, AbstractC0475f.m232d(str2));
        } catch (RuntimeException unused) {
        }
    }
}
