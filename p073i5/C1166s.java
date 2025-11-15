package p073i5;

import p073i5.InterfaceC1152e;

/* compiled from: View.java */
/* renamed from: i5.s */
/* loaded from: classes.dex */
public class C1166s extends AbstractC1148a {

    /* renamed from: p */
    public InterfaceC1152e f2560p;

    /* compiled from: View.java */
    /* renamed from: i5.s$a */
    public static class a extends C1166s implements InterfaceC1152e.a {
        @Override // p073i5.C1166s, p073i5.AbstractC1148a
        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof InterfaceC1152e) && ((InterfaceC1152e) obj).mo1315B(this)) || super.equals(obj);
        }
    }

    public C1166s(InterfaceC1152e interfaceC1152e, int i7, int i8, int i9, int i10) {
        super(2, !((AbstractC1148a) interfaceC1152e).mo1329f());
        this.f2560p = interfaceC1152e.buffer();
        this.f2525h = i9;
        this.f2526i = 0;
        this.f2524g = i8;
        this.f2526i = 0;
        this.f2529l = i7;
        this.f2522e = i10;
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: E */
    public boolean mo1318E() {
        return this.f2560p.mo1318E();
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: H */
    public byte mo1348H(int i7) {
        return this.f2560p.mo1348H(i7);
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: P */
    public byte[] mo1349P() {
        return this.f2560p.mo1349P();
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: W */
    public boolean mo1327W() {
        return true;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: a */
    public int mo1350a() {
        return this.f2560p.mo1350a();
    }

    /* renamed from: b */
    public void m1372b(int i7, int i8) {
        int i9 = this.f2522e;
        this.f2522e = 2;
        mo1331i(0);
        this.f2525h = i8;
        this.f2526i = 0;
        this.f2524g = i7;
        this.f2526i = 0;
        this.f2529l = -1;
        this.f2522e = i9;
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    public InterfaceC1152e buffer() {
        return this.f2560p.buffer();
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    public void clear() {
        this.f2529l = -1;
        mo1331i(0);
        mo1324Q(this.f2560p.mo1316C());
        mo1331i(this.f2560p.mo1316C());
    }

    /* renamed from: d */
    public void m1373d(InterfaceC1152e interfaceC1152e) {
        this.f2522e = 2;
        this.f2560p = interfaceC1152e.buffer();
        mo1331i(0);
        mo1324Q(interfaceC1152e.mo1322M());
        mo1331i(interfaceC1152e.mo1316C());
        this.f2529l = interfaceC1152e.mo1323O();
        this.f2522e = interfaceC1152e.mo1318E() ? 1 : 2;
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: e */
    public void mo1351e(int i7, byte b7) {
        this.f2560p.mo1351e(i7, b7);
    }

    @Override // p073i5.AbstractC1148a
    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof InterfaceC1152e) && obj.equals(this)) || super.equals(obj);
    }

    @Override // p073i5.InterfaceC1152e
    /* renamed from: m */
    public int mo1352m(int i7, byte[] bArr, int i8, int i9) {
        return this.f2560p.mo1352m(i7, bArr, i8, i9);
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: p */
    public int mo1334p(int i7, InterfaceC1152e interfaceC1152e) {
        return this.f2560p.mo1334p(i7, interfaceC1152e);
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: s */
    public int mo1336s(int i7, byte[] bArr, int i8, int i9) {
        return this.f2560p.mo1336s(i7, bArr, i8, i9);
    }

    @Override // p073i5.AbstractC1148a
    public String toString() {
        return this.f2560p == null ? "INVALID" : super.toString();
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: u */
    public InterfaceC1152e mo1338u(int i7, int i8) {
        return this.f2560p.mo1338u(i7, i8);
    }

    @Override // p073i5.AbstractC1148a, p073i5.InterfaceC1152e
    /* renamed from: w */
    public void mo1340w() {
    }

    public C1166s(InterfaceC1152e interfaceC1152e) {
        super(2, !interfaceC1152e.mo1329f());
        this.f2560p = interfaceC1152e.buffer();
        mo1324Q(interfaceC1152e.mo1322M());
        mo1331i(interfaceC1152e.mo1316C());
        this.f2529l = interfaceC1152e.mo1323O();
        this.f2522e = interfaceC1152e.mo1318E() ? 1 : 2;
    }

    public C1166s() {
        super(2, true);
    }
}
