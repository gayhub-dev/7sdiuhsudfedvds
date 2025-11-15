package pub.devrel.easypermissions;

import android.support.annotation.NonNull;
import java.util.Arrays;
import p009b.C0413b;
import p122o6.AbstractC1595d;

/* compiled from: PermissionRequest.java */
/* renamed from: pub.devrel.easypermissions.b */
/* loaded from: classes.dex */
public final class C1753b {

    /* renamed from: a */
    public final AbstractC1595d f4985a;

    /* renamed from: b */
    public final String[] f4986b;

    /* renamed from: c */
    public final int f4987c;

    /* renamed from: d */
    public final String f4988d;

    /* renamed from: e */
    public final String f4989e;

    /* renamed from: f */
    public final String f4990f;

    /* renamed from: g */
    public final int f4991g;

    public C1753b(AbstractC1595d abstractC1595d, String[] strArr, int i7, String str, String str2, String str3, int i8, a aVar) {
        this.f4985a = abstractC1595d;
        this.f4986b = (String[]) strArr.clone();
        this.f4987c = i7;
        this.f4988d = str;
        this.f4989e = str2;
        this.f4990f = str3;
        this.f4991g = i8;
    }

    @NonNull
    /* renamed from: a */
    public String[] m1922a() {
        return (String[]) this.f4986b.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1753b.class != obj.getClass()) {
            return false;
        }
        C1753b c1753b = (C1753b) obj;
        return Arrays.equals(this.f4986b, c1753b.f4986b) && this.f4987c == c1753b.f4987c;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.f4986b) * 31) + this.f4987c;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("PermissionRequest{mHelper=");
        sbM112a.append(this.f4985a);
        sbM112a.append(", mPerms=");
        sbM112a.append(Arrays.toString(this.f4986b));
        sbM112a.append(", mRequestCode=");
        sbM112a.append(this.f4987c);
        sbM112a.append(", mRationale='");
        sbM112a.append(this.f4988d);
        sbM112a.append('\'');
        sbM112a.append(", mPositiveButtonText='");
        sbM112a.append(this.f4989e);
        sbM112a.append('\'');
        sbM112a.append(", mNegativeButtonText='");
        sbM112a.append(this.f4990f);
        sbM112a.append('\'');
        sbM112a.append(", mTheme=");
        sbM112a.append(this.f4991g);
        sbM112a.append('}');
        return sbM112a.toString();
    }
}
