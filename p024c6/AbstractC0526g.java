package p024c6;

import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.InterfaceC0493x;
import p159t3.AbstractC1904c;
import p186x2.C2074b;

/* compiled from: BaseLocal.java */
/* renamed from: c6.g */
/* loaded from: classes.dex */
public abstract class AbstractC0526g implements InterfaceC0493x, Comparable {
    private static final long serialVersionUID = 276453175381783L;

    /* renamed from: a */
    public int m335a(InterfaceC0493x interfaceC0493x) {
        if (this == interfaceC0493x) {
            return 0;
        }
        if (size() != interfaceC0493x.size()) {
            throw new ClassCastException("ReadablePartial objects must have matching field types");
        }
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            if (mo291d(i7) != interfaceC0493x.mo291d(i7)) {
                throw new ClassCastException("ReadablePartial objects must have matching field types");
            }
        }
        int size2 = size();
        for (int i8 = 0; i8 < size2; i8++) {
            if (mo266k(i8) > interfaceC0493x.mo266k(i8)) {
                return 1;
            }
            if (mo266k(i8) < interfaceC0493x.mo266k(i8)) {
                return -1;
            }
        }
        return 0;
    }

    /* renamed from: b */
    public abstract AbstractC0471b mo264b(int i7, AbstractC1904c abstractC1904c);

    @Override // p016b6.InterfaceC0493x
    /* renamed from: d */
    public AbstractC0472c mo291d(int i7) {
        return mo264b(i7, mo265j()).mo213p();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InterfaceC0493x)) {
            return false;
        }
        InterfaceC0493x interfaceC0493x = (InterfaceC0493x) obj;
        if (size() != interfaceC0493x.size()) {
            return false;
        }
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            if (mo266k(i7) != interfaceC0493x.mo266k(i7) || mo291d(i7) != interfaceC0493x.mo291d(i7)) {
                return false;
            }
        }
        return C2074b.m2483f(mo265j(), interfaceC0493x.mo265j());
    }

    public int hashCode() {
        int size = size();
        int iHashCode = 157;
        for (int i7 = 0; i7 < size; i7++) {
            iHashCode = mo291d(i7).hashCode() + ((mo266k(i7) + (iHashCode * 23)) * 23);
        }
        return mo265j().hashCode() + iHashCode;
    }
}
