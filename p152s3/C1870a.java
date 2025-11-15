package p152s3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Objects;

/* compiled from: AndroidLogAdapter.java */
/* renamed from: s3.a */
/* loaded from: classes.dex */
public class C1870a implements InterfaceC1872c {

    /* renamed from: a */
    @NonNull
    public final InterfaceC1871b f5450a;

    public C1870a(@NonNull InterfaceC1871b interfaceC1871b) {
        Objects.requireNonNull(interfaceC1871b);
        this.f5450a = interfaceC1871b;
    }

    @Override // p152s3.InterfaceC1872c
    /* renamed from: a */
    public void mo2143a(int i7, @Nullable String str, @NonNull String str2) {
        this.f5450a.mo2144a(i7, str, str2);
    }
}
