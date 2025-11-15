package p010b0;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.File;
import java.io.FileNotFoundException;
import p009b.C0413b;
import p010b0.InterfaceC0426l;
import p043f.C0988e;
import p134q0.C1757b;
import p141r.EnumC1811f;
import p162u.C1966j;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: MediaStoreFileLoader.java */
/* renamed from: b0.j */
/* loaded from: classes.dex */
public final class C0424j implements InterfaceC0426l<Uri, File> {

    /* renamed from: a */
    public final Context f221a;

    /* compiled from: MediaStoreFileLoader.java */
    /* renamed from: b0.j$a */
    public static final class a implements InterfaceC0427m<Uri, File> {

        /* renamed from: a */
        public final Context f222a;

        public a(Context context) {
            this.f222a = context;
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<Uri, File> mo120b(C0430p c0430p) {
            return new C0424j(this.f222a);
        }
    }

    /* compiled from: MediaStoreFileLoader.java */
    /* renamed from: b0.j$b */
    public static class b implements InterfaceC1986b<File> {

        /* renamed from: g */
        public static final String[] f223g = {"_data"};

        /* renamed from: e */
        public final Context f224e;

        /* renamed from: f */
        public final Uri f225f;

        public b(Context context, Uri uri) {
            this.f224e = context;
            this.f225f = uri;
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        /* renamed from: a */
        public Class<File> mo123a() {
            return File.class;
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: b */
        public void mo124b() {
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: c */
        public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super File> aVar) {
            Cursor cursorQuery = this.f224e.getContentResolver().query(this.f225f, f223g, null, null, null);
            if (cursorQuery != null) {
                try {
                    string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : null;
                } finally {
                    cursorQuery.close();
                }
            }
            if (!TextUtils.isEmpty(string)) {
                aVar.mo141e(new File(string));
                return;
            }
            StringBuilder sbM112a = C0413b.m112a("Failed to find file path for: ");
            sbM112a.append(this.f225f);
            aVar.mo140d(new FileNotFoundException(sbM112a.toString()));
        }

        @Override // p169v.InterfaceC1986b
        public void cancel() {
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        public EnumC1957a getDataSource() {
            return EnumC1957a.LOCAL;
        }
    }

    public C0424j(Context context) {
        this.f221a = context;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a<File> mo117a(Uri uri, int i7, int i8, C1966j c1966j) {
        Uri uri2 = uri;
        return new InterfaceC0426l.a<>(new C1757b(uri2), new b(this.f221a, uri2));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public boolean mo118b(Uri uri) {
        return C0988e.m961E(uri);
    }
}
