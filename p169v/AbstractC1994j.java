package p169v;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import p141r.EnumC1811f;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: LocalUriFetcher.java */
/* renamed from: v.j */
/* loaded from: classes.dex */
public abstract class AbstractC1994j<T> implements InterfaceC1986b<T> {

    /* renamed from: e */
    public final Uri f5821e;

    /* renamed from: f */
    public final ContentResolver f5822f;

    /* renamed from: g */
    public T f5823g;

    public AbstractC1994j(ContentResolver contentResolver, Uri uri) {
        this.f5822f = contentResolver;
        this.f5821e = uri;
    }

    @Override // p169v.InterfaceC1986b
    /* renamed from: b */
    public void mo124b() {
        T t6 = this.f5823g;
        if (t6 != null) {
            try {
                mo2326d(t6);
            } catch (IOException unused) {
            }
        }
    }

    @Override // p169v.InterfaceC1986b
    /* renamed from: c */
    public final void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super T> aVar) {
        try {
            T tMo2327e = mo2327e(this.f5821e, this.f5822f);
            this.f5823g = tMo2327e;
            aVar.mo141e(tMo2327e);
        } catch (FileNotFoundException e7) {
            Log.isLoggable("LocalUriFetcher", 3);
            aVar.mo140d(e7);
        }
    }

    @Override // p169v.InterfaceC1986b
    public void cancel() {
    }

    /* renamed from: d */
    public abstract void mo2326d(T t6);

    /* renamed from: e */
    public abstract T mo2327e(Uri uri, ContentResolver contentResolver);

    @Override // p169v.InterfaceC1986b
    @NonNull
    public EnumC1957a getDataSource() {
        return EnumC1957a.LOCAL;
    }
}
