package p176w;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import p141r.ComponentCallbacks2C1808c;
import p141r.EnumC1811f;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: ThumbFetcher.java */
/* renamed from: w.a */
/* loaded from: classes.dex */
public class C2018a implements InterfaceC1986b<InputStream> {

    /* renamed from: e */
    public final Uri f5881e;

    /* renamed from: f */
    public final C2020c f5882f;

    /* renamed from: g */
    public InputStream f5883g;

    /* compiled from: ThumbFetcher.java */
    /* renamed from: w.a$a */
    public static class a implements InterfaceC2019b {

        /* renamed from: b */
        public static final String[] f5884b = {"_data"};

        /* renamed from: a */
        public final ContentResolver f5885a;

        public a(ContentResolver contentResolver) {
            this.f5885a = contentResolver;
        }

        @Override // p176w.InterfaceC2019b
        /* renamed from: a */
        public Cursor mo2369a(Uri uri) {
            return this.f5885a.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, f5884b, "kind = 1 AND image_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    /* compiled from: ThumbFetcher.java */
    /* renamed from: w.a$b */
    public static class b implements InterfaceC2019b {

        /* renamed from: b */
        public static final String[] f5886b = {"_data"};

        /* renamed from: a */
        public final ContentResolver f5887a;

        public b(ContentResolver contentResolver) {
            this.f5887a = contentResolver;
        }

        @Override // p176w.InterfaceC2019b
        /* renamed from: a */
        public Cursor mo2369a(Uri uri) {
            return this.f5887a.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, f5886b, "kind = 1 AND video_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    public C2018a(Uri uri, C2020c c2020c) {
        this.f5881e = uri;
        this.f5882f = c2020c;
    }

    /* renamed from: d */
    public static C2018a m2367d(Context context, Uri uri, InterfaceC2019b interfaceC2019b) {
        return new C2018a(uri, new C2020c(ComponentCallbacks2C1808c.m2022c(context).f5216h.m2028c(), interfaceC2019b, ComponentCallbacks2C1808c.m2022c(context).f5217i, context.getContentResolver()));
    }

    @Override // p169v.InterfaceC1986b
    @NonNull
    /* renamed from: a */
    public Class<InputStream> mo123a() {
        return InputStream.class;
    }

    @Override // p169v.InterfaceC1986b
    /* renamed from: b */
    public void mo124b() throws IOException {
        InputStream inputStream = this.f5883g;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // p169v.InterfaceC1986b
    /* renamed from: c */
    public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super InputStream> aVar) throws IOException {
        try {
            InputStream inputStreamM2368e = m2368e();
            this.f5883g = inputStreamM2368e;
            aVar.mo141e(inputStreamM2368e);
        } catch (FileNotFoundException e7) {
            Log.isLoggable("MediaStoreThumbFetcher", 3);
            aVar.mo140d(e7);
        }
    }

    @Override // p169v.InterfaceC1986b
    public void cancel() {
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f A[DONT_GENERATE] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.io.InputStream m2368e() throws java.io.IOException {
        /*
            r9 = this;
            w.c r0 = r9.f5882f
            android.net.Uri r1 = r9.f5881e
            w.b r2 = r0.f5890b
            android.database.Cursor r1 = r2.mo2369a(r1)
            r2 = 0
            if (r1 == 0) goto L7e
            boolean r3 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L79
            if (r3 != 0) goto L14
            goto L7e
        L14:
            r3 = 0
            java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Throwable -> L79
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L79
            if (r4 == 0) goto L23
        L1f:
            r1.close()
            goto L77
        L23:
            f.a r4 = r0.f5889a     // Catch: java.lang.Throwable -> L79
            java.util.Objects.requireNonNull(r4)     // Catch: java.lang.Throwable -> L79
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L79
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L79
            f.a r3 = r0.f5889a     // Catch: java.lang.Throwable -> L79
            java.util.Objects.requireNonNull(r3)     // Catch: java.lang.Throwable -> L79
            boolean r3 = r4.exists()     // Catch: java.lang.Throwable -> L79
            if (r3 == 0) goto L4c
            f.a r3 = r0.f5889a     // Catch: java.lang.Throwable -> L79
            java.util.Objects.requireNonNull(r3)     // Catch: java.lang.Throwable -> L79
            long r5 = r4.length()     // Catch: java.lang.Throwable -> L79
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 <= 0) goto L4c
            android.net.Uri r3 = android.net.Uri.fromFile(r4)     // Catch: java.lang.Throwable -> L79
            goto L4d
        L4c:
            r3 = r2
        L4d:
            r1.close()
            if (r3 == 0) goto L77
            android.content.ContentResolver r0 = r0.f5892d     // Catch: java.lang.NullPointerException -> L59
            java.io.InputStream r0 = r0.openInputStream(r3)     // Catch: java.lang.NullPointerException -> L59
            goto L81
        L59:
            r0 = move-exception
            java.io.FileNotFoundException r1 = new java.io.FileNotFoundException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "NPE opening uri: "
            r2.append(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            java.lang.Throwable r0 = r1.initCause(r0)
            java.io.FileNotFoundException r0 = (java.io.FileNotFoundException) r0
            throw r0
        L77:
            r0 = r2
            goto L81
        L79:
            r0 = move-exception
            r1.close()
            throw r0
        L7e:
            if (r1 == 0) goto L77
            goto L1f
        L81:
            r1 = -1
            if (r0 == 0) goto Lbb
            w.c r3 = r9.f5882f
            android.net.Uri r4 = r9.f5881e
            java.util.Objects.requireNonNull(r3)
            android.content.ContentResolver r5 = r3.f5892d     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> La3
            java.io.InputStream r2 = r5.openInputStream(r4)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> La3
            java.util.List<u.f> r5 = r3.f5893e     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> La3
            y.b r3 = r3.f5891c     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> La3
            int r3 = p162u.C1963g.m2293a(r5, r2, r3)     // Catch: java.lang.Throwable -> La1 java.lang.Throwable -> La3
            if (r2 == 0) goto Lbc
            r2.close()     // Catch: java.io.IOException -> L9f
            goto Lbc
        L9f:
            goto Lbc
        La1:
            r0 = move-exception
            goto Lb5
        La3:
            java.lang.String r3 = "ThumbStreamOpener"
            r5 = 3
            boolean r3 = android.util.Log.isLoggable(r3, r5)     // Catch: java.lang.Throwable -> La1
            if (r3 == 0) goto Laf
            java.util.Objects.toString(r4)     // Catch: java.lang.Throwable -> La1
        Laf:
            if (r2 == 0) goto Lbb
            r2.close()     // Catch: java.io.IOException -> Lbb
            goto Lbb
        Lb5:
            if (r2 == 0) goto Lba
            r2.close()     // Catch: java.io.IOException -> Lba
        Lba:
            throw r0
        Lbb:
            r3 = -1
        Lbc:
            if (r3 == r1) goto Lc4
            v.e r1 = new v.e
            r1.<init>(r0, r3)
            r0 = r1
        Lc4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p176w.C2018a.m2368e():java.io.InputStream");
    }

    @Override // p169v.InterfaceC1986b
    @NonNull
    public EnumC1957a getDataSource() {
        return EnumC1957a.LOCAL;
    }
}
