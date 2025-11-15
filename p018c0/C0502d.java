package p018c0;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import java.io.InputStream;
import p010b0.C0430p;
import p010b0.InterfaceC0426l;
import p010b0.InterfaceC0427m;
import p036e0.C0919r;
import p043f.C0988e;
import p134q0.C1757b;
import p162u.C1966j;
import p176w.C2018a;

/* compiled from: MediaStoreVideoThumbLoader.java */
/* renamed from: c0.d */
/* loaded from: classes.dex */
public class C0502d implements InterfaceC0426l<Uri, InputStream> {

    /* renamed from: a */
    public final Context f369a;

    /* compiled from: MediaStoreVideoThumbLoader.java */
    /* renamed from: c0.d$a */
    public static class a implements InterfaceC0427m<Uri, InputStream> {

        /* renamed from: a */
        public final Context f370a;

        public a(Context context) {
            this.f370a = context;
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Uri, InputStream> mo120b(C0430p c0430p) {
            return new C0502d(this.f370a);
        }
    }

    public C0502d(Context context) {
        this.f369a = context.getApplicationContext();
    }

    @Override // p010b0.InterfaceC0426l
    @Nullable
    /* renamed from: a */
    public InterfaceC0426l.a<InputStream> mo117a(Uri uri, int i7, int i8, C1966j c1966j) {
        Uri uri2 = uri;
        boolean z6 = false;
        if (i7 <= 512 && i8 <= 384) {
            Long l7 = (Long) c1966j.m2296c(C0919r.f1676c);
            if (l7 != null && l7.longValue() == -1) {
                z6 = true;
            }
            if (z6) {
                C1757b c1757b = new C1757b(uri2);
                Context context = this.f369a;
                return new InterfaceC0426l.a<>(c1757b, C2018a.m2367d(context, uri2, new C2018a.b(context.getContentResolver())));
            }
        }
        return null;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public boolean mo118b(Uri uri) {
        Uri uri2 = uri;
        return C0988e.m961E(uri2) && uri2.getPathSegments().contains("video");
    }
}
