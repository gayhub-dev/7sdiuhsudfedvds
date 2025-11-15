package p065h5;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import p073i5.C1153f;
import p073i5.InterfaceC1152e;
import p161t5.C1926r;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: MimeTypes.java */
/* renamed from: h5.w */
/* loaded from: classes.dex */
public class C1115w {

    /* renamed from: a */
    public static final InterfaceC2016c f2383a;

    /* renamed from: b */
    public static int f2384b;

    /* renamed from: c */
    public static final C1153f f2385c;

    /* renamed from: d */
    public static final C1153f.a f2386d;

    /* renamed from: e */
    public static final C1153f.a f2387e;

    /* renamed from: f */
    public static final C1153f.a f2388f;

    /* renamed from: g */
    public static final C1153f.a f2389g;

    /* renamed from: h */
    public static final C1153f.a f2390h;

    /* renamed from: i */
    public static final C1153f.a f2391i;

    /* renamed from: j */
    public static final C1153f.a f2392j;

    /* renamed from: k */
    public static final C1153f.a f2393k;

    /* renamed from: l */
    public static final C1153f.a f2394l;

    /* renamed from: m */
    public static final C1153f.a f2395m;

    /* renamed from: n */
    public static final C1153f.a f2396n;

    /* renamed from: o */
    public static final C1153f.a f2397o;

    /* renamed from: p */
    public static final Map f2398p;

    /* renamed from: q */
    public static final Map f2399q;

    static {
        Properties properties = C2015b.f5863a;
        f2383a = C2015b.m2349a(C1115w.class.getName());
        f2384b = 15;
        C1153f c1153f = new C1153f();
        f2385c = c1153f;
        c1153f.m1353a("application/x-www-form-urlencoded", 1);
        c1153f.m1353a("message/http", 2);
        f2386d = c1153f.m1353a("multipart/byteranges", 3);
        f2387e = c1153f.m1353a("text/html", 4);
        f2388f = c1153f.m1353a("text/plain", 5);
        f2389g = c1153f.m1353a("text/xml", 6);
        f2390h = c1153f.m1353a("text/json", 7);
        f2391i = c1153f.m1353a("text/html;charset=ISO-8859-1", 8);
        f2392j = c1153f.m1353a("text/plain;charset=ISO-8859-1", 9);
        f2393k = c1153f.m1353a("text/xml;charset=ISO-8859-1", 10);
        f2394l = c1153f.m1353a("text/html;charset=UTF-8", 11);
        f2395m = c1153f.m1353a("text/plain;charset=UTF-8", 12);
        f2396n = c1153f.m1353a("text/xml;charset=UTF-8", 13);
        f2397o = c1153f.m1353a("text/json;charset=UTF-8", 14);
        c1153f.m1353a("text/html; charset=ISO-8859-1", 8);
        c1153f.m1353a("text/plain; charset=ISO-8859-1", 9);
        c1153f.m1353a("text/xml; charset=ISO-8859-1", 10);
        c1153f.m1353a("text/html; charset=UTF-8", 11);
        c1153f.m1353a("text/plain; charset=UTF-8", 12);
        c1153f.m1353a("text/xml; charset=UTF-8", 13);
        c1153f.m1353a("text/json; charset=UTF-8", 14);
        f2398p = new HashMap();
        f2399q = new HashMap();
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("org/eclipse/jetty/http/mime");
            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                String strNextElement = keys.nextElement();
                f2398p.put(C1926r.m2252b(strNextElement), m1258b(bundle.getString(strNextElement)));
            }
        } catch (MissingResourceException e7) {
            InterfaceC2016c interfaceC2016c = f2383a;
            interfaceC2016c.mo2356g(e7.toString(), new Object[0]);
            interfaceC2016c.mo2359j(e7);
        }
        try {
            ResourceBundle bundle2 = ResourceBundle.getBundle("org/eclipse/jetty/http/encoding");
            Enumeration<String> keys2 = bundle2.getKeys();
            while (keys2.hasMoreElements()) {
                InterfaceC1152e interfaceC1152eM1258b = m1258b(keys2.nextElement());
                f2399q.put(interfaceC1152eM1258b, bundle2.getString(interfaceC1152eM1258b.toString()));
            }
        } catch (MissingResourceException e8) {
            InterfaceC2016c interfaceC2016c2 = f2383a;
            interfaceC2016c2.mo2356g(e8.toString(), new Object[0]);
            interfaceC2016c2.mo2359j(e8);
        }
        C1153f.a aVar = f2387e;
        C1153f.a aVar2 = f2391i;
        aVar.m1362d("ISO-8859-1", aVar2);
        aVar.m1362d("ISO_8859_1", aVar2);
        aVar.m1362d("iso-8859-1", aVar2);
        C1153f.a aVar3 = f2388f;
        C1153f.a aVar4 = f2392j;
        aVar3.m1362d("ISO-8859-1", aVar4);
        aVar3.m1362d("ISO_8859_1", aVar4);
        aVar3.m1362d("iso-8859-1", aVar4);
        C1153f.a aVar5 = f2389g;
        C1153f.a aVar6 = f2393k;
        aVar5.m1362d("ISO-8859-1", aVar6);
        aVar5.m1362d("ISO_8859_1", aVar6);
        aVar5.m1362d("iso-8859-1", aVar6);
        C1153f.a aVar7 = f2394l;
        aVar.m1362d("UTF-8", aVar7);
        aVar.m1362d("UTF8", aVar7);
        aVar.m1362d("utf8", aVar7);
        aVar.m1362d("utf-8", aVar7);
        C1153f.a aVar8 = f2395m;
        aVar3.m1362d("UTF-8", aVar8);
        aVar3.m1362d("UTF8", aVar8);
        aVar3.m1362d("utf8", aVar8);
        aVar3.m1362d("utf-8", aVar8);
        C1153f.a aVar9 = f2396n;
        aVar5.m1362d("UTF-8", aVar9);
        aVar5.m1362d("UTF8", aVar9);
        aVar5.m1362d("utf8", aVar9);
        aVar5.m1362d("utf-8", aVar9);
        C1153f.a aVar10 = f2390h;
        C1153f.a aVar11 = f2397o;
        aVar10.m1362d("UTF-8", aVar11);
        aVar10.m1362d("UTF8", aVar11);
        aVar10.m1362d("utf8", aVar11);
        aVar10.m1362d("utf-8", aVar11);
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x00a5, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0093  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m1257a(p073i5.InterfaceC1152e r13) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p065h5.C1115w.m1257a(i5.e):java.lang.String");
    }

    /* renamed from: b */
    public static synchronized InterfaceC1152e m1258b(String str) {
        C1153f.a aVarM1353a;
        C1153f c1153f = f2385c;
        aVarM1353a = (C1153f.a) c1153f.f2540b.m2245a(str);
        if (aVarM1353a == null) {
            int i7 = f2384b;
            f2384b = i7 + 1;
            aVarM1353a = c1153f.m1353a(str, i7);
        }
        return aVarM1353a;
    }
}
