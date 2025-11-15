package p045f1;

import android.text.TextUtils;
import java.util.List;
import java.util.Map;
import org.fourthline.cling.model.ServiceReference;

/* compiled from: CCTVMainPlayEntity.java */
/* renamed from: f1.a */
/* loaded from: classes.dex */
public class C0992a {

    /* renamed from: a */
    public String f1837a;

    /* renamed from: b */
    public String f1838b;

    /* renamed from: c */
    public boolean f1839c;

    /* renamed from: d */
    public boolean f1840d;

    /* renamed from: e */
    public boolean f1841e;

    /* renamed from: f */
    public Map<String, String> f1842f;

    /* renamed from: g */
    public List<C0993b> f1843g;

    /* renamed from: h */
    public Object f1844h;

    /* renamed from: i */
    public boolean f1845i;

    /* renamed from: j */
    public String f1846j;

    /* renamed from: k */
    public String f1847k;

    /* renamed from: l */
    public boolean f1848l;

    /* renamed from: m */
    public String f1849m;

    /* renamed from: a */
    public void m1007a(String str) {
        String strReplace = str.replace("://", "");
        String strSubstring = strReplace.substring(strReplace.indexOf(ServiceReference.DELIMITER), strReplace.indexOf("?"));
        for (int i7 = 0; i7 < this.f1843g.size(); i7++) {
            C0993b c0993b = this.f1843g.get(i7);
            String str2 = c0993b.f1850a;
            String str3 = TextUtils.isEmpty(null) ? c0993b.f1850a : null;
            if ((str2.contains(strSubstring) || str3.contains(strSubstring)) && m1010d() != i7) {
                m1012f(i7);
                if ((this.f1843g.size() - 1) - i7 > 0) {
                    for (int size = (this.f1843g.size() - 1) - i7; size > 0; size--) {
                        this.f1843g.remove(i7 + size);
                    }
                    return;
                }
                return;
            }
        }
    }

    /* renamed from: b */
    public C0993b m1008b() {
        List<C0993b> list = this.f1843g;
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (C0993b c0993b : this.f1843g) {
            if (c0993b.f1853d) {
                return c0993b;
            }
        }
        this.f1843g.get(0).f1853d = true;
        return this.f1843g.get(0);
    }

    /* renamed from: c */
    public String m1009c() {
        if (m1008b() == null) {
            return null;
        }
        return m1008b().f1852c;
    }

    /* renamed from: d */
    public int m1010d() {
        List<C0993b> list = this.f1843g;
        if (list != null && !list.isEmpty()) {
            for (int i7 = 0; i7 < this.f1843g.size(); i7++) {
                if (this.f1843g.get(i7).f1852c.equals(m1009c())) {
                    return i7;
                }
            }
        }
        return -1;
    }

    /* renamed from: e */
    public String m1011e(boolean z6) {
        if (m1008b() == null) {
            return null;
        }
        return (z6 || !this.f1839c) ? m1008b().f1850a : m1008b().f1850a;
    }

    /* renamed from: f */
    public void m1012f(int i7) {
        List<C0993b> list = this.f1843g;
        if (list == null || list.size() <= i7) {
            return;
        }
        this.f1843g.get(m1010d()).f1853d = false;
        this.f1843g.get(i7).f1853d = true;
    }
}
