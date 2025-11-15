package p060h0;

import java.io.File;
import java.util.Objects;
import p183x.InterfaceC2057r;

/* compiled from: FileResource.java */
/* renamed from: h0.b */
/* loaded from: classes.dex */
public class C1080b implements InterfaceC2057r {

    /* renamed from: e */
    public final T f2177e;

    /* JADX WARN: Multi-variable type inference failed */
    public C1080b(File file) {
        Objects.requireNonNull(file, "Argument must not be null");
        this.f2177e = file;
    }

    @Override // p183x.InterfaceC2057r
    /* renamed from: b */
    public Class mo824b() {
        return this.f2177e.getClass();
    }

    @Override // p183x.InterfaceC2057r
    public final Object get() {
        return this.f2177e;
    }

    @Override // p183x.InterfaceC2057r
    public final /* bridge */ /* synthetic */ int getSize() {
        return 1;
    }

    @Override // p183x.InterfaceC2057r
    public /* bridge */ /* synthetic */ void recycle() {
    }
}
