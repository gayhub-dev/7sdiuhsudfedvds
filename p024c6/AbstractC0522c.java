package p024c6;

import org.joda.convert.ToString;
import p016b6.InterfaceC0491v;
import p058g6.C1071i;
import p186x2.C2074b;

/* compiled from: AbstractInstant.java */
/* renamed from: c6.c */
/* loaded from: classes.dex */
public abstract class AbstractC0522c implements InterfaceC0491v {
    @Override // java.lang.Comparable
    public int compareTo(InterfaceC0491v interfaceC0491v) {
        InterfaceC0491v interfaceC0491v2 = interfaceC0491v;
        if (this == interfaceC0491v2) {
            return 0;
        }
        long jMo261g = interfaceC0491v2.mo261g();
        long jMo261g2 = mo261g();
        if (jMo261g2 == jMo261g) {
            return 0;
        }
        return jMo261g2 < jMo261g ? -1 : 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InterfaceC0491v)) {
            return false;
        }
        InterfaceC0491v interfaceC0491v = (InterfaceC0491v) obj;
        return mo261g() == interfaceC0491v.mo261g() && C2074b.m2483f(mo262j(), interfaceC0491v.mo262j());
    }

    public int hashCode() {
        return mo262j().hashCode() + ((int) (mo261g() ^ (mo261g() >>> 32)));
    }

    @ToString
    public String toString() {
        return C1071i.f2082E.m1061d(this);
    }
}
