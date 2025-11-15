package p010b0;

import android.net.Uri;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import p010b0.InterfaceC0426l;
import p162u.C1966j;

/* compiled from: UrlUriLoader.java */
/* renamed from: b0.u */
/* loaded from: classes.dex */
public class C0435u<Data> implements InterfaceC0426l<Uri, Data> {

    /* renamed from: b */
    public static final Set<String> f265b = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));

    /* renamed from: a */
    public final InterfaceC0426l<C0420f, Data> f266a;

    /* compiled from: UrlUriLoader.java */
    /* renamed from: b0.u$a */
    public static class a implements InterfaceC0427m<Uri, InputStream> {
        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Uri, InputStream> mo120b(C0430p c0430p) {
            return new C0435u(c0430p.m144b(C0420f.class, InputStream.class));
        }
    }

    public C0435u(InterfaceC0426l<C0420f, Data> interfaceC0426l) {
        this.f266a = interfaceC0426l;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a mo117a(Uri uri, int i7, int i8, C1966j c1966j) {
        return this.f266a.mo117a(new C0420f(uri.toString()), i7, i8, c1966j);
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public boolean mo118b(Uri uri) {
        return f265b.contains(uri.getScheme());
    }
}
