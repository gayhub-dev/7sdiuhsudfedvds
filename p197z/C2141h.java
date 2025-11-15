package p197z;

import p142r0.C1820e;
import p142r0.C1823h;
import p162u.InterfaceC1964h;
import p183x.C2048i;
import p183x.InterfaceC2057r;
import p197z.InterfaceC2142i;

/* compiled from: LruResourceCache.java */
/* renamed from: z.h */
/* loaded from: classes.dex */
public class C2141h extends C1820e<InterfaceC1964h, InterfaceC2057r<?>> implements InterfaceC2142i {

    /* renamed from: d */
    public InterfaceC2142i.a f6295d;

    public C2141h(int i7) {
        super(i7);
    }

    @Override // p142r0.C1820e
    /* renamed from: b */
    public int mo2052b(InterfaceC2057r<?> interfaceC2057r) {
        return interfaceC2057r.getSize();
    }

    @Override // p142r0.C1820e
    /* renamed from: c */
    public void mo136c(InterfaceC1964h interfaceC1964h, InterfaceC2057r<?> interfaceC2057r) {
        InterfaceC2057r<?> interfaceC2057r2 = interfaceC2057r;
        InterfaceC2142i.a aVar = this.f6295d;
        if (aVar != null) {
            C1823h.m2057a();
            ((C2048i) aVar).f6051f.m2447a(interfaceC2057r2);
        }
    }
}
