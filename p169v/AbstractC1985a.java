package p169v;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import p141r.EnumC1811f;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: AssetPathFetcher.java */
/* renamed from: v.a */
/* loaded from: classes.dex */
public abstract class AbstractC1985a<T> implements InterfaceC1986b<T> {

    /* renamed from: e */
    public final String f5802e;

    /* renamed from: f */
    public final AssetManager f5803f;

    /* renamed from: g */
    public T f5804g;

    public AbstractC1985a(AssetManager assetManager, String str) {
        this.f5803f = assetManager;
        this.f5802e = str;
    }

    @Override // p169v.InterfaceC1986b
    /* renamed from: b */
    public void mo124b() throws IOException {
        T t6 = this.f5804g;
        if (t6 == null) {
            return;
        }
        try {
            switch (((C1990f) this).f5812h) {
                case 0:
                    ((ParcelFileDescriptor) t6).close();
                    break;
                default:
                    ((InputStream) t6).close();
                    break;
            }
        } catch (IOException unused) {
        }
    }

    @Override // p169v.InterfaceC1986b
    /* renamed from: c */
    public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super T> aVar) {
        T t6;
        try {
            AssetManager assetManager = this.f5803f;
            String str = this.f5802e;
            switch (((C1990f) this).f5812h) {
                case 0:
                    t6 = (T) assetManager.openFd(str).getParcelFileDescriptor();
                    break;
                default:
                    t6 = (T) assetManager.open(str);
                    break;
            }
            this.f5804g = t6;
            aVar.mo141e(t6);
        } catch (IOException e7) {
            Log.isLoggable("AssetPathFetcher", 3);
            aVar.mo140d(e7);
        }
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
