package p097l5;

import p009b.C0413b;
import p113n5.InterfaceC1543d;
import p113n5.InterfaceC1561v;

/* compiled from: UserAuthentication.java */
/* renamed from: l5.j */
/* loaded from: classes.dex */
public class C1445j implements InterfaceC1543d.g {

    /* renamed from: e */
    public final String f4198e;

    /* renamed from: f */
    public final InterfaceC1561v f4199f;

    public C1445j(String str, InterfaceC1561v interfaceC1561v) {
        this.f4198e = str;
        this.f4199f = interfaceC1561v;
    }

    @Override // p113n5.InterfaceC1543d.g
    /* renamed from: a */
    public String mo1631a() {
        return this.f4198e;
    }

    @Override // p113n5.InterfaceC1543d.g
    /* renamed from: h */
    public InterfaceC1561v mo1632h() {
        return this.f4199f;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("{User,");
        sbM112a.append(this.f4198e);
        sbM112a.append(",");
        sbM112a.append(this.f4199f);
        sbM112a.append("}");
        return sbM112a.toString();
    }
}
