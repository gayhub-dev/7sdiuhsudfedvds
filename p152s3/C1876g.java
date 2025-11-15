package p152s3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.Objects;

/* compiled from: PrettyFormatStrategy.java */
/* renamed from: s3.g */
/* loaded from: classes.dex */
public class C1876g implements InterfaceC1871b {

    /* renamed from: a */
    public final int f5454a;

    /* renamed from: b */
    public final int f5455b;

    /* renamed from: c */
    public final boolean f5456c;

    /* renamed from: d */
    @NonNull
    public final C1873d f5457d;

    /* renamed from: e */
    @Nullable
    public final String f5458e;

    /* compiled from: PrettyFormatStrategy.java */
    /* renamed from: s3.g$b */
    public static class b {

        /* renamed from: d */
        @Nullable
        public C1873d f5462d;

        /* renamed from: a */
        public int f5459a = 2;

        /* renamed from: b */
        public int f5460b = 0;

        /* renamed from: c */
        public boolean f5461c = true;

        /* renamed from: e */
        @Nullable
        public String f5463e = "PRETTY_LOGGER";

        public b(a aVar) {
        }
    }

    public C1876g(b bVar, a aVar) {
        this.f5454a = bVar.f5459a;
        this.f5455b = bVar.f5460b;
        this.f5456c = bVar.f5461c;
        this.f5457d = bVar.f5462d;
        this.f5458e = bVar.f5463e;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    @Override // p152s3.InterfaceC1871b
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo2144a(int r12, @android.support.annotation.Nullable java.lang.String r13, @android.support.annotation.NonNull java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p152s3.C1876g.mo2144a(int, java.lang.String, java.lang.String):void");
    }

    /* renamed from: b */
    public final void m2148b(int i7, @Nullable String str, @NonNull String str2) {
        Objects.requireNonNull(str2);
        Objects.requireNonNull(this.f5457d);
        if (str == null) {
            str = "NO_TAG";
        }
        Log.println(i7, str, str2);
    }

    /* renamed from: c */
    public final void m2149c(int i7, @Nullable String str, @NonNull String str2) {
        for (String str3 : str2.split(System.getProperty("line.separator"))) {
            m2148b(i7, str, "│ " + str3);
        }
    }
}
