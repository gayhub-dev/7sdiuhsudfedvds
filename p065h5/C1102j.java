package p065h5;

import java.util.Enumeration;

/* compiled from: HttpFields.java */
/* renamed from: h5.j */
/* loaded from: classes.dex */
public class C1102j implements Enumeration<String> {

    /* renamed from: a */
    public final /* synthetic */ Enumeration f2304a;

    public C1102j(C1101i c1101i, Enumeration enumeration) {
        this.f2304a = enumeration;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.f2304a.hasMoreElements();
    }

    @Override // java.util.Enumeration
    public String nextElement() {
        return this.f2304a.nextElement().toString();
    }
}
