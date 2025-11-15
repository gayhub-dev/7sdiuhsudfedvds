package p140q6;

import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: ContextList.java */
/* renamed from: q6.d */
/* loaded from: classes.dex */
public class C1783d {

    /* renamed from: a */
    public LinkedList<C1790k> f5075a = new LinkedList<>();

    /* renamed from: a */
    public synchronized C1790k m1974a(String str, String str2) {
        return m1975b(str, str2, false);
    }

    /* renamed from: b */
    public synchronized C1790k m1975b(String str, String str2, boolean z6) {
        C1790k c1790k;
        String lowerCase = str.toLowerCase();
        String str3 = "";
        c1790k = null;
        Iterator<C1790k> it = this.f5075a.iterator();
        while (it.hasNext()) {
            C1790k next = it.next();
            if (next.f5113b.equals(lowerCase)) {
                String str4 = next.f5112a;
                if (!z6 || str4.equals(str2)) {
                    if (z6 || str2.startsWith(str4)) {
                        if (str4.length() > str3.length()) {
                            c1790k = next;
                            str3 = str4;
                        }
                    }
                }
            }
        }
        return c1790k;
    }
}
