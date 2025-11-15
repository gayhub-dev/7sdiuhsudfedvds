package p098l6;

import java.net.URI;
import java.net.URL;
import java.util.BitSet;
import org.fourthline.cling.model.ServiceReference;

/* compiled from: URIUtil.java */
/* renamed from: l6.c */
/* loaded from: classes.dex */
public class C1449c {

    /* renamed from: a */
    public static final BitSet f4207a = new a();

    /* renamed from: b */
    public static final BitSet f4208b = new b();

    /* compiled from: URIUtil.java */
    /* renamed from: l6.c$a */
    public static class a extends BitSet {
        public a() {
            for (int i7 = 97; i7 <= 122; i7++) {
                set(i7);
            }
            for (int i8 = 65; i8 <= 90; i8++) {
                set(i8);
            }
            for (int i9 = 48; i9 <= 57; i9++) {
                set(i9);
            }
            set(33);
            set(36);
            set(38);
            set(39);
            set(40);
            set(41);
            set(42);
            set(43);
            set(44);
            set(59);
            set(61);
            set(45);
            set(46);
            set(95);
            set(126);
            set(58);
            set(64);
        }
    }

    /* compiled from: URIUtil.java */
    /* renamed from: l6.c$b */
    public static class b extends BitSet {
        public b() {
            or(C1449c.f4207a);
            clear(59);
        }
    }

    /* compiled from: URIUtil.java */
    /* renamed from: l6.c$c */
    public static class c extends BitSet {
        public c() {
            or(C1449c.f4207a);
            clear(59);
            clear(61);
        }
    }

    /* compiled from: URIUtil.java */
    /* renamed from: l6.c$d */
    public static class d extends BitSet {
        public d() {
            or(C1449c.f4207a);
            clear(59);
        }
    }

    /* compiled from: URIUtil.java */
    /* renamed from: l6.c$e */
    public static class e extends BitSet {
        public e() {
            or(C1449c.f4207a);
            set(47);
            set(63);
            clear(61);
            clear(38);
            clear(43);
        }
    }

    /* compiled from: URIUtil.java */
    /* renamed from: l6.c$f */
    public static class f extends BitSet {
        public f() {
            or(C1449c.f4207a);
            set(47);
            set(63);
        }
    }

    static {
        new c();
        new d();
        new e();
        new f();
    }

    /* renamed from: a */
    public static URI m1634a(URI uri, URI uri2) {
        if (uri == null && !uri2.isAbsolute()) {
            throw new IllegalArgumentException("Base URI is null and given URI is not absolute");
        }
        if (uri == null && uri2.isAbsolute()) {
            return uri2;
        }
        if (uri.getPath().length() == 0) {
            try {
                uri = new URI(uri.getScheme(), uri.getAuthority(), ServiceReference.DELIMITER, uri.getQuery(), uri.getFragment());
            } catch (Exception e7) {
                throw new IllegalArgumentException(e7);
            }
        }
        return uri.resolve(uri2);
    }

    /* renamed from: b */
    public static URL m1635b(URL url, URI uri) {
        if (url == null && !uri.isAbsolute()) {
            throw new IllegalArgumentException("Base URL is null and given URI is not absolute");
        }
        if (url == null && uri.isAbsolute()) {
            try {
                return uri.toURL();
            } catch (Exception unused) {
                throw new IllegalArgumentException("Base URL was null and given URI can't be converted to URL");
            }
        }
        try {
            return m1634a(url.toURI(), uri).toURL();
        } catch (Exception e7) {
            throw new IllegalArgumentException("Base URL is not an URI, or can't create absolute URI (null?), or absolute URI can not be converted to URL", e7);
        }
    }
}
