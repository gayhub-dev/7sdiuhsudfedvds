package p010b0;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import p010b0.InterfaceC0426l;
import p134q0.C1757b;
import p162u.C1966j;
import p169v.C1991g;
import p169v.C1995k;
import p169v.InterfaceC1986b;

/* compiled from: UriLoader.java */
/* renamed from: b0.t */
/* loaded from: classes.dex */
public class C0434t<Data> implements InterfaceC0426l<Uri, Data> {

    /* renamed from: b */
    public static final Set<String> f261b = Collections.unmodifiableSet(new HashSet(Arrays.asList("file", "android.resource", "content")));

    /* renamed from: a */
    public final b<Data> f262a;

    /* compiled from: UriLoader.java */
    /* renamed from: b0.t$a */
    public static class a implements InterfaceC0427m<Uri, ParcelFileDescriptor>, b<ParcelFileDescriptor> {

        /* renamed from: a */
        public final ContentResolver f263a;

        public a(ContentResolver contentResolver) {
            this.f263a = contentResolver;
        }

        @Override // p010b0.C0434t.b
        /* renamed from: a */
        public InterfaceC1986b<ParcelFileDescriptor> mo146a(Uri uri) {
            return new C1991g(this.f263a, uri);
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Uri, ParcelFileDescriptor> mo120b(C0430p c0430p) {
            return new C0434t(this);
        }
    }

    /* compiled from: UriLoader.java */
    /* renamed from: b0.t$b */
    public interface b<Data> {
        /* renamed from: a */
        InterfaceC1986b<Data> mo146a(Uri uri);
    }

    /* compiled from: UriLoader.java */
    /* renamed from: b0.t$c */
    public static class c implements InterfaceC0427m<Uri, InputStream>, b<InputStream> {

        /* renamed from: a */
        public final ContentResolver f264a;

        public c(ContentResolver contentResolver) {
            this.f264a = contentResolver;
        }

        @Override // p010b0.C0434t.b
        /* renamed from: a */
        public InterfaceC1986b<InputStream> mo146a(Uri uri) {
            return new C1995k(this.f264a, uri);
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Uri, InputStream> mo120b(C0430p c0430p) {
            return new C0434t(this);
        }
    }

    public C0434t(b<Data> bVar) {
        this.f262a = bVar;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a mo117a(Uri uri, int i7, int i8, C1966j c1966j) {
        Uri uri2 = uri;
        return new InterfaceC0426l.a(new C1757b(uri2), this.f262a.mo146a(uri2));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public boolean mo118b(Uri uri) {
        return f261b.contains(uri.getScheme());
    }
}
