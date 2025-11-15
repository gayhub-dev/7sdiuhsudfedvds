package p016b6;

import android.support.constraint.C0072a;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import p009b.C0413b;

/* compiled from: PeriodType.java */
/* renamed from: b6.s */
/* loaded from: classes.dex */
public class C0488s implements Serializable {

    /* renamed from: g */
    public static C0488s f351g = null;
    private static final long serialVersionUID = 2274324892792009998L;

    /* renamed from: e */
    public final String f352e;

    /* renamed from: f */
    public final AbstractC0478i[] f353f;

    static {
        new HashMap(32);
    }

    public C0488s(String str, AbstractC0478i[] abstractC0478iArr, int[] iArr) {
        this.f352e = str;
        this.f353f = abstractC0478iArr;
    }

    /* renamed from: b */
    public static C0488s m285b() {
        C0488s c0488s = f351g;
        if (c0488s != null) {
            return c0488s;
        }
        C0488s c0488s2 = new C0488s("Standard", new AbstractC0478i[]{AbstractC0478i.f325i, AbstractC0478i.f326j, AbstractC0478i.f327k, AbstractC0478i.f328l, AbstractC0478i.f330n, AbstractC0478i.f331o, AbstractC0478i.f332p, AbstractC0478i.f333q}, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        f351g = c0488s2;
        return c0488s2;
    }

    /* renamed from: a */
    public int m286a(AbstractC0478i abstractC0478i) {
        int length = this.f353f.length;
        for (int i7 = 0; i7 < length; i7++) {
            if (this.f353f[i7] == abstractC0478i) {
                return i7;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0488s) {
            return Arrays.equals(this.f353f, ((C0488s) obj).f353f);
        }
        return false;
    }

    public int hashCode() {
        int i7 = 0;
        int iHashCode = 0;
        while (true) {
            AbstractC0478i[] abstractC0478iArr = this.f353f;
            if (i7 >= abstractC0478iArr.length) {
                return iHashCode;
            }
            iHashCode += abstractC0478iArr[i7].hashCode();
            i7++;
        }
    }

    public String toString() {
        return C0072a.m92a(C0413b.m112a("PeriodType["), this.f352e, "]");
    }
}
