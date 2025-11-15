package p010b0;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.InputStream;
import p010b0.InterfaceC0426l;
import p134q0.C1757b;
import p162u.C1966j;
import p169v.C1990f;
import p169v.InterfaceC1986b;

/* compiled from: AssetUriLoader.java */
/* renamed from: b0.a */
/* loaded from: classes.dex */
public class C0415a<Data> implements InterfaceC0426l<Uri, Data> {

    /* renamed from: a */
    public final AssetManager f190a;

    /* renamed from: b */
    public final a<Data> f191b;

    /* compiled from: AssetUriLoader.java */
    /* renamed from: b0.a$a */
    public interface a<Data> {
        /* renamed from: a */
        InterfaceC1986b<Data> mo119a(AssetManager assetManager, String str);
    }

    /* compiled from: AssetUriLoader.java */
    /* renamed from: b0.a$b */
    public static class b implements InterfaceC0427m<Uri, ParcelFileDescriptor>, a<ParcelFileDescriptor> {

        /* renamed from: a */
        public final AssetManager f192a;

        public b(AssetManager assetManager) {
            this.f192a = assetManager;
        }

        @Override // p010b0.C0415a.a
        /* renamed from: a */
        public InterfaceC1986b<ParcelFileDescriptor> mo119a(AssetManager assetManager, String str) {
            return new C1990f(assetManager, str, 0);
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Uri, ParcelFileDescriptor> mo120b(C0430p c0430p) {
            return new C0415a(this.f192a, this);
        }
    }

    /* compiled from: AssetUriLoader.java */
    /* renamed from: b0.a$c */
    public static class c implements InterfaceC0427m<Uri, InputStream>, a<InputStream> {

        /* renamed from: a */
        public final AssetManager f193a;

        public c(AssetManager assetManager) {
            this.f193a = assetManager;
        }

        @Override // p010b0.C0415a.a
        /* renamed from: a */
        public InterfaceC1986b<InputStream> mo119a(AssetManager assetManager, String str) {
            return new C1990f(assetManager, str, 1);
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Uri, InputStream> mo120b(C0430p c0430p) {
            return new C0415a(this.f193a, this);
        }
    }

    public C0415a(AssetManager assetManager, a<Data> aVar) {
        this.f190a = assetManager;
        this.f191b = aVar;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a mo117a(Uri uri, int i7, int i8, C1966j c1966j) {
        Uri uri2 = uri;
        return new InterfaceC0426l.a(new C1757b(uri2), this.f191b.mo119a(this.f190a, uri2.toString().substring(22)));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public boolean mo118b(Uri uri) {
        Uri uri2 = uri;
        return "file".equals(uri2.getScheme()) && !uri2.getPathSegments().isEmpty() && "android_asset".equals(uri2.getPathSegments().get(0));
    }
}
