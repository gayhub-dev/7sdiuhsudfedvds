package p095l3;

import p009b.C0413b;

/* compiled from: PolyvPlayError.java */
/* renamed from: l3.c */
/* loaded from: classes.dex */
public class C1423c {

    /* renamed from: a */
    public final String f4161a;

    /* renamed from: b */
    public final int f4162b;

    /* renamed from: c */
    public final String f4163c;

    /* renamed from: d */
    public final int f4164d;

    public C1423c(String str, int i7, String str2, int i8) {
        this.f4161a = str;
        this.f4162b = i7;
        this.f4163c = str2;
        this.f4164d = i8;
    }

    /* renamed from: a */
    public static C1423c m1607a(String str, int i7, int i8) {
        return new C1423c(str, i7, i7 != -1020 ? i7 != -1006 ? i7 != -1002 ? i7 != -1000 ? i7 != 200 ? "Unknown" : "Valid Play" : "PlayPath Is Null" : "Host Is Empty" : "Headers Is Null" : "Request Timeout", i8);
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("PolyvPlayError{playPath='");
        sbM112a.append(this.f4161a);
        sbM112a.append('\'');
        sbM112a.append(", errorCode=");
        sbM112a.append(this.f4162b);
        sbM112a.append(", errorDescribe='");
        sbM112a.append(this.f4163c);
        sbM112a.append('\'');
        sbM112a.append(", playStage=");
        sbM112a.append(this.f4164d);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
