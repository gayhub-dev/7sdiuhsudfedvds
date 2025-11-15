package p099m;

import p043f.C0987d;
import p051g.C1031a;
import p091l.AbstractC1408c;
import p091l.InterfaceC1406a;

/* compiled from: DisplayModeManager.java */
/* renamed from: m.b */
/* loaded from: classes.dex */
public class C1451b extends AbstractC1408c<AbstractC1450a> {

    /* renamed from: e */
    public boolean f4209e;

    /* renamed from: f */
    public C1031a f4210f;

    public C1451b(int i7, C0987d c0987d) {
        super(i7, c0987d);
    }

    @Override // p091l.AbstractC1408c
    /* renamed from: a */
    public InterfaceC1406a mo1593a(int i7) {
        return i7 != 102 ? new C1452c(1) : new C1452c(0);
    }

    /* renamed from: e */
    public int m1636e() {
        switch (((C1452c) ((AbstractC1450a) this.f4142b)).f4211a) {
            case 0:
                return 2;
            default:
                return 1;
        }
    }
}
