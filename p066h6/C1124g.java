package p066h6;

import java.util.Collections;
import java.util.Set;
import p016b6.AbstractC0475f;

/* compiled from: UTCProvider.java */
/* renamed from: h6.g */
/* loaded from: classes.dex */
public final class C1124g implements InterfaceC1123f {

    /* renamed from: a */
    public static final Set<String> f2442a = Collections.singleton("UTC");

    @Override // p066h6.InterfaceC1123f
    /* renamed from: a */
    public AbstractC0475f mo1282a(String str) {
        if ("UTC".equalsIgnoreCase(str)) {
            return AbstractC0475f.f314f;
        }
        return null;
    }

    @Override // p066h6.InterfaceC1123f
    /* renamed from: b */
    public Set<String> mo1283b() {
        return f2442a;
    }
}
