package p169v;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import java.io.FileNotFoundException;
import java.io.IOException;

/* compiled from: FileDescriptorLocalUriFetcher.java */
/* renamed from: v.g */
/* loaded from: classes.dex */
public class C1991g extends AbstractC1994j<ParcelFileDescriptor> {
    public C1991g(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    @Override // p169v.InterfaceC1986b
    @NonNull
    /* renamed from: a */
    public Class<ParcelFileDescriptor> mo123a() {
        return ParcelFileDescriptor.class;
    }

    @Override // p169v.AbstractC1994j
    /* renamed from: d */
    public void mo2326d(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        parcelFileDescriptor.close();
    }

    @Override // p169v.AbstractC1994j
    /* renamed from: e */
    public ParcelFileDescriptor mo2327e(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
        if (assetFileDescriptorOpenAssetFileDescriptor != null) {
            return assetFileDescriptorOpenAssetFileDescriptor.getParcelFileDescriptor();
        }
        throw new FileNotFoundException("FileDescriptor is null for: " + uri);
    }
}
