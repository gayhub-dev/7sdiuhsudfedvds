package p073i5;

import java.util.ArrayList;
import java.util.HashMap;
import p009b.C0413b;
import p073i5.C1158k;
import p073i5.InterfaceC1152e;
import p161t5.C1925q;

/* compiled from: BufferCache.java */
/* renamed from: i5.f */
/* loaded from: classes.dex */
public class C1153f {

    /* renamed from: a */
    public final HashMap f2539a = new HashMap();

    /* renamed from: b */
    public final C1925q f2540b;

    /* renamed from: c */
    public final ArrayList f2541c;

    /* compiled from: BufferCache.java */
    /* renamed from: i5.f$a */
    public static class a extends C1158k.a {

        /* renamed from: r */
        public final int f2542r;

        /* renamed from: s */
        public HashMap f2543s;

        public a(String str, int i7) {
            super(str);
            this.f2543s = null;
            this.f2542r = i7;
        }

        /* renamed from: b */
        public a m1361b(Object obj) {
            HashMap map = this.f2543s;
            if (map == null) {
                return null;
            }
            return (a) map.get(obj);
        }

        /* renamed from: d */
        public void m1362d(Object obj, a aVar) {
            if (this.f2543s == null) {
                this.f2543s = new HashMap();
            }
            this.f2543s.put(obj, aVar);
        }
    }

    public C1153f() {
        C1925q c1925q = new C1925q();
        c1925q.f5686g = true;
        this.f2540b = c1925q;
        this.f2541c = new ArrayList();
    }

    /* renamed from: a */
    public a m1353a(String str, int i7) {
        a aVar = new a(str, i7);
        this.f2539a.put(aVar, aVar);
        this.f2540b.m2247c(str, aVar);
        while (i7 - this.f2541c.size() >= 0) {
            this.f2541c.add(null);
        }
        if (this.f2541c.get(i7) == null) {
            this.f2541c.add(i7, aVar);
        }
        return aVar;
    }

    /* renamed from: b */
    public a m1354b(InterfaceC1152e interfaceC1152e) {
        return (a) this.f2539a.get(interfaceC1152e);
    }

    /* renamed from: c */
    public a m1355c(String str) {
        return (a) this.f2540b.m2245a(str);
    }

    /* renamed from: d */
    public a m1356d(byte[] bArr, int i7, int i8) {
        C1925q c1925q = this.f2540b;
        C1925q.b bVar = c1925q.f5685f;
        int i9 = 0;
        int i10 = -1;
        loop0: while (true) {
            if (i9 < i8) {
                char c7 = (char) bArr[i7 + i9];
                if (i10 == -1) {
                    C1925q.b[] bVarArr = bVar.f5694h;
                    C1925q.b bVar2 = bVarArr == null ? null : bVarArr[c7 % c1925q.f5684e];
                    if (bVar2 == null && i9 > 0) {
                        break;
                    }
                    bVar = bVar2;
                    i10 = 0;
                }
                while (bVar != null) {
                    char[] cArr = bVar.f5691e;
                    if (cArr[i10] == c7 || (c1925q.f5686g && bVar.f5692f[i10] == c7)) {
                        i10++;
                        if (i10 == cArr.length) {
                            i10 = -1;
                        }
                        i9++;
                    } else {
                        if (i10 > 0) {
                            break loop0;
                        }
                        bVar = bVar.f5693g;
                    }
                }
                break loop0;
            }
            if (i10 > 0 || (bVar != null && bVar.f5695i == null)) {
                break loop0;
            }
        }
        bVar = null;
        if (bVar != null) {
            return (a) bVar.getValue();
        }
        return null;
    }

    /* renamed from: e */
    public int m1357e(InterfaceC1152e interfaceC1152e) {
        if (interfaceC1152e instanceof a) {
            return ((a) interfaceC1152e).f2542r;
        }
        InterfaceC1152e interfaceC1152eM1359g = m1359g(interfaceC1152e);
        if (interfaceC1152eM1359g == null || !(interfaceC1152eM1359g instanceof a)) {
            return -1;
        }
        return ((a) interfaceC1152eM1359g).f2542r;
    }

    /* renamed from: f */
    public int m1358f(String str) {
        a aVar = (a) this.f2540b.m2245a(str);
        if (aVar == null) {
            return -1;
        }
        return aVar.f2542r;
    }

    /* renamed from: g */
    public InterfaceC1152e m1359g(InterfaceC1152e interfaceC1152e) {
        if (interfaceC1152e instanceof a) {
            return interfaceC1152e;
        }
        a aVar = (a) this.f2539a.get(interfaceC1152e);
        return aVar == null ? interfaceC1152e instanceof InterfaceC1152e.a ? interfaceC1152e : new C1158k.a(interfaceC1152e.mo1339v(), 0, interfaceC1152e.length(), 0) : aVar;
    }

    /* renamed from: h */
    public InterfaceC1152e m1360h(String str) {
        a aVar = (a) this.f2540b.m2245a(str);
        return aVar == null ? new a(str, -1) : aVar;
    }

    public String toString() {
        StringBuilder sbM112a = C0413b.m112a("CACHE[bufferMap=");
        sbM112a.append(this.f2539a);
        sbM112a.append(",stringMap=");
        sbM112a.append(this.f2540b);
        sbM112a.append(",index=");
        sbM112a.append(this.f2541c);
        sbM112a.append("]");
        return sbM112a.toString();
    }
}
