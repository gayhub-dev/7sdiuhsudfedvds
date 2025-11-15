package p169v;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.support.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: StreamLocalUriFetcher.java */
/* renamed from: v.k */
/* loaded from: classes.dex */
public class C1995k extends AbstractC1994j<InputStream> {

    /* renamed from: h */
    public static final UriMatcher f5824h;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f5824h = uriMatcher;
        uriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        uriMatcher.addURI("com.android.contacts", "contacts/lookup/*", 1);
        uriMatcher.addURI("com.android.contacts", "contacts/#/photo", 2);
        uriMatcher.addURI("com.android.contacts", "contacts/#", 3);
        uriMatcher.addURI("com.android.contacts", "contacts/#/display_photo", 4);
        uriMatcher.addURI("com.android.contacts", "phone_lookup/*", 5);
    }

    public C1995k(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    @Override // p169v.InterfaceC1986b
    @NonNull
    /* renamed from: a */
    public Class<InputStream> mo123a() {
        return InputStream.class;
    }

    @Override // p169v.AbstractC1994j
    /* renamed from: d */
    public void mo2326d(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    @Override // p169v.AbstractC1994j
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.io.InputStream mo2327e(android.net.Uri r4, android.content.ContentResolver r5) throws java.io.FileNotFoundException {
        /*
            r3 = this;
            android.content.UriMatcher r0 = p169v.C1995k.f5824h
            int r0 = r0.match(r4)
            r1 = 1
            if (r0 == r1) goto L19
            r2 = 3
            if (r0 == r2) goto L14
            r2 = 5
            if (r0 == r2) goto L19
            java.io.InputStream r5 = r5.openInputStream(r4)
            goto L23
        L14:
            java.io.InputStream r5 = android.provider.ContactsContract.Contacts.openContactPhotoInputStream(r5, r4, r1)
            goto L23
        L19:
            android.net.Uri r0 = android.provider.ContactsContract.Contacts.lookupContact(r5, r4)
            if (r0 == 0) goto L3d
            java.io.InputStream r5 = android.provider.ContactsContract.Contacts.openContactPhotoInputStream(r5, r0, r1)
        L23:
            if (r5 == 0) goto L26
            return r5
        L26:
            java.io.FileNotFoundException r5 = new java.io.FileNotFoundException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "InputStream is null for "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r5.<init>(r4)
            throw r5
        L3d:
            java.io.FileNotFoundException r4 = new java.io.FileNotFoundException
            java.lang.String r5 = "Contact cannot be found"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p169v.C1995k.mo2327e(android.net.Uri, android.content.ContentResolver):java.lang.Object");
    }
}
