package p169v;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;
import java.io.InputStream;

/* compiled from: FileDescriptorAssetPathFetcher.java */
/* renamed from: v.f */
/* loaded from: classes.dex */
public class C1990f extends AbstractC1985a<ParcelFileDescriptor> {

    /* renamed from: h */
    public final /* synthetic */ int f5812h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1990f(AssetManager assetManager, String str, int i7) {
        super(assetManager, str);
        this.f5812h = i7;
        if (i7 != 1) {
        } else {
            super(assetManager, str);
        }
    }

    @Override // p169v.InterfaceC1986b
    /* renamed from: a */
    public Class mo123a() {
        switch (this.f5812h) {
            case 0:
                return ParcelFileDescriptor.class;
            default:
                return InputStream.class;
        }
    }
}
