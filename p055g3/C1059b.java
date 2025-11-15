package p055g3;

import p009b.C0413b;

/* compiled from: PolyvPlayerOptionParamVO.java */
/* renamed from: g3.b */
/* loaded from: classes.dex */
public class C1059b {

    /* renamed from: a */
    public final int f2005a;

    /* renamed from: b */
    public final String f2006b;

    /* renamed from: c */
    public final Object f2007c;

    public C1059b(int i7, String str, Object obj) {
        this.f2005a = i7;
        this.f2006b = str;
        this.f2007c = obj;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("{name='");
        sbM112a.append(this.f2006b);
        sbM112a.append('\'');
        sbM112a.append(", value='");
        sbM112a.append(this.f2007c);
        sbM112a.append('\'');
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
