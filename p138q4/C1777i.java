package p138q4;

import p186x2.C2074b;

/* compiled from: OpenHashSet.java */
/* renamed from: q4.i */
/* loaded from: classes.dex */
public final class C1777i<T> {

    /* renamed from: a */
    public int f5063a;

    /* renamed from: b */
    public int f5064b;

    /* renamed from: c */
    public int f5065c;

    /* renamed from: d */
    public T[] f5066d;

    public C1777i() {
        int iM2462B = C2074b.m2462B(16);
        this.f5063a = iM2462B - 1;
        this.f5065c = (int) (iM2462B * 0.75f);
        this.f5066d = (T[]) new Object[iM2462B];
    }

    /* renamed from: b */
    public static int m1965b(int i7) {
        int i8 = i7 * (-1640531527);
        return i8 ^ (i8 >>> 16);
    }

    /* renamed from: a */
    public boolean m1966a(T t6) {
        T t7;
        T[] tArr = this.f5066d;
        int i7 = this.f5063a;
        int iM1965b = m1965b(t6.hashCode()) & i7;
        T t8 = tArr[iM1965b];
        if (t8 != null) {
            if (t8.equals(t6)) {
                return false;
            }
            do {
                iM1965b = (iM1965b + 1) & i7;
                t7 = tArr[iM1965b];
                if (t7 == null) {
                }
            } while (!t7.equals(t6));
            return false;
        }
        tArr[iM1965b] = t6;
        int i8 = this.f5064b + 1;
        this.f5064b = i8;
        if (i8 >= this.f5065c) {
            T[] tArr2 = this.f5066d;
            int length = tArr2.length;
            int i9 = length << 1;
            int i10 = i9 - 1;
            T[] tArr3 = (T[]) new Object[i9];
            while (true) {
                int i11 = i8 - 1;
                if (i8 == 0) {
                    break;
                }
                do {
                    length--;
                } while (tArr2[length] == null);
                int iM1965b2 = m1965b(tArr2[length].hashCode()) & i10;
                if (tArr3[iM1965b2] != null) {
                    do {
                        iM1965b2 = (iM1965b2 + 1) & i10;
                    } while (tArr3[iM1965b2] != null);
                }
                tArr3[iM1965b2] = tArr2[length];
                i8 = i11;
            }
            this.f5063a = i10;
            this.f5065c = (int) (i9 * 0.75f);
            this.f5066d = tArr3;
        }
        return true;
    }

    /* renamed from: c */
    public boolean m1967c(int i7, T[] tArr, int i8) {
        int i9;
        T t6;
        this.f5064b--;
        while (true) {
            int i10 = i7 + 1;
            while (true) {
                i9 = i10 & i8;
                t6 = tArr[i9];
                if (t6 == null) {
                    tArr[i7] = null;
                    return true;
                }
                int iM1965b = m1965b(t6.hashCode()) & i8;
                if (i7 <= i9) {
                    if (i7 >= iM1965b || iM1965b > i9) {
                        break;
                    }
                    i10 = i9 + 1;
                } else if (i7 < iM1965b || iM1965b <= i9) {
                    i10 = i9 + 1;
                }
            }
            tArr[i7] = t6;
            i7 = i9;
        }
    }
}
