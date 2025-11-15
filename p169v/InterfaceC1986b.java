package p169v;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import p141r.EnumC1811f;
import p162u.EnumC1957a;

/* compiled from: DataFetcher.java */
/* renamed from: v.b */
/* loaded from: classes.dex */
public interface InterfaceC1986b<T> {

    /* compiled from: DataFetcher.java */
    /* renamed from: v.b$a */
    public interface a<T> {
        /* renamed from: d */
        void mo140d(Exception exc);

        /* renamed from: e */
        void mo141e(@Nullable T t6);
    }

    @NonNull
    /* renamed from: a */
    Class<T> mo123a();

    /* renamed from: b */
    void mo124b();

    /* renamed from: c */
    void mo125c(EnumC1811f enumC1811f, a<? super T> aVar);

    void cancel();

    @NonNull
    EnumC1957a getDataSource();
}
