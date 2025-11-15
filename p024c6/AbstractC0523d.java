package p024c6;

import java.util.Locale;
import org.joda.convert.ToString;
import p016b6.AbstractC0478i;
import p016b6.InterfaceC0494y;
import p058g6.InterfaceC1077o;
import p186x2.C2074b;
import p203z5.C2158b;

/* compiled from: AbstractPeriod.java */
/* renamed from: c6.d */
/* loaded from: classes.dex */
public abstract class AbstractC0523d implements InterfaceC0494y {
    @Override // p016b6.InterfaceC0494y
    /* renamed from: N */
    public int mo292N(AbstractC0478i abstractC0478i) {
        int iM286a = mo295q().m286a(abstractC0478i);
        if (iM286a == -1) {
            return 0;
        }
        return mo294k(iM286a);
    }

    @Override // p016b6.InterfaceC0494y
    /* renamed from: d */
    public AbstractC0478i mo293d(int i7) {
        return mo295q().f353f[i7];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InterfaceC0494y)) {
            return false;
        }
        InterfaceC0494y interfaceC0494y = (InterfaceC0494y) obj;
        if (size() != interfaceC0494y.size()) {
            return false;
        }
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            if (mo294k(i7) != interfaceC0494y.mo294k(i7) || mo293d(i7) != interfaceC0494y.mo293d(i7)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int size = size();
        int iHashCode = 17;
        for (int i7 = 0; i7 < size; i7++) {
            iHashCode = mo293d(i7).hashCode() + ((mo294k(i7) + (iHashCode * 27)) * 27);
        }
        return iHashCode;
    }

    @Override // p016b6.InterfaceC0494y
    public int size() {
        return mo295q().f353f.length;
    }

    @ToString
    public String toString() {
        C2158b c2158bM2467G = C2074b.m2467G();
        c2158bM2467G.m2601c();
        c2158bM2467G.m2600b(this);
        InterfaceC1077o interfaceC1077o = (InterfaceC1077o) c2158bM2467G.f6334a;
        StringBuffer stringBuffer = new StringBuffer(interfaceC1077o.mo1155b(this, (Locale) c2158bM2467G.f6336c));
        interfaceC1077o.mo1154a(stringBuffer, this, (Locale) c2158bM2467G.f6336c);
        return stringBuffer.toString();
    }
}
