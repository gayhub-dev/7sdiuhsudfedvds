package p018c0;

import android.content.Context;
import android.net.Uri;
import java.io.InputStream;
import p010b0.C0430p;
import p010b0.InterfaceC0426l;
import p010b0.InterfaceC0427m;
import p043f.C0988e;
import p134q0.C1757b;
import p162u.C1966j;
import p176w.C2018a;

/* compiled from: MediaStoreImageThumbLoader.java */
/* renamed from: c0.c */
/* loaded from: classes.dex */
public class C0501c implements InterfaceC0426l<Uri, InputStream> {

    /* renamed from: a */
    public final Context f367a;

    /* compiled from: MediaStoreImageThumbLoader.java */
    /* renamed from: c0.c$a */
    public static class a implements InterfaceC0427m<Uri, InputStream> {

        /* renamed from: a */
        public final Context f368a;

        public a(Context context) {
            this.f368a = context;
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Uri, InputStream> mo120b(C0430p c0430p) {
            return new C0501c(this.f368a);
        }
    }

    public C0501c(Context context) {
        this.f367a = context.getApplicationContext();
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a<InputStream> mo117a(Uri uri, int i7, int i8, C1966j c1966j) {
        Uri uri2 = uri;
        if (!(i7 <= 512 && i8 <= 384)) {
            return null;
        }
        C1757b c1757b = new C1757b(uri2);
        Context context = this.f367a;
        return new InterfaceC0426l.a<>(c1757b, C2018a.m2367d(context, uri2, new C2018a.a(context.getContentResolver())));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public boolean mo118b(Uri uri) {
        Uri uri2 = uri;
        return C0988e.m961E(uri2) && !uri2.getPathSegments().contains("video");
    }
}
