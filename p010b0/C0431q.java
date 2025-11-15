package p010b0;

import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.InputStream;
import p010b0.InterfaceC0426l;
import p162u.C1966j;

/* compiled from: ResourceLoader.java */
/* renamed from: b0.q */
/* loaded from: classes.dex */
public class C0431q<Data> implements InterfaceC0426l<Integer, Data> {

    /* renamed from: a */
    public final InterfaceC0426l<Uri, Data> f255a;

    /* renamed from: b */
    public final Resources f256b;

    /* compiled from: ResourceLoader.java */
    /* renamed from: b0.q$a */
    public static class a implements InterfaceC0427m<Integer, ParcelFileDescriptor> {

        /* renamed from: a */
        public final Resources f257a;

        public a(Resources resources) {
            this.f257a = resources;
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Integer, ParcelFileDescriptor> mo120b(C0430p c0430p) {
            return new C0431q(this.f257a, c0430p.m144b(Uri.class, ParcelFileDescriptor.class));
        }
    }

    /* compiled from: ResourceLoader.java */
    /* renamed from: b0.q$b */
    public static class b implements InterfaceC0427m<Integer, InputStream> {

        /* renamed from: a */
        public final Resources f258a;

        public b(Resources resources) {
            this.f258a = resources;
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Integer, InputStream> mo120b(C0430p c0430p) {
            return new C0431q(this.f258a, c0430p.m144b(Uri.class, InputStream.class));
        }
    }

    public C0431q(Resources resources, InterfaceC0426l<Uri, Data> interfaceC0426l) {
        this.f256b = resources;
        this.f255a = interfaceC0426l;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a mo117a(Integer num, int i7, int i8, C1966j c1966j) {
        Uri uri;
        Integer num2 = num;
        try {
            uri = Uri.parse("android.resource://" + this.f256b.getResourcePackageName(num2.intValue()) + '/' + this.f256b.getResourceTypeName(num2.intValue()) + '/' + this.f256b.getResourceEntryName(num2.intValue()));
        } catch (Resources.NotFoundException unused) {
            Log.isLoggable("ResourceLoader", 5);
            uri = null;
        }
        if (uri == null) {
            return null;
        }
        return this.f255a.mo117a(uri, i7, i8, c1966j);
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public /* bridge */ /* synthetic */ boolean mo118b(Integer num) {
        return true;
    }
}
