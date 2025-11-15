package p042e6;

import p016b6.InterfaceC0489t;
import p016b6.InterfaceC0492w;
import p159t3.AbstractC1904c;

/* compiled from: ReadableIntervalConverter.java */
/* renamed from: e6.m */
/* loaded from: classes.dex */
public class C0980m extends AbstractC0968a implements InterfaceC0974g, InterfaceC0977j {

    /* renamed from: a */
    public static final C0980m f1812a = new C0980m();

    @Override // p042e6.InterfaceC0970c
    /* renamed from: a */
    public Class<?> mo935a() {
        return InterfaceC0492w.class;
    }

    @Override // p042e6.InterfaceC0977j
    /* renamed from: b */
    public void mo939b(InterfaceC0489t interfaceC0489t, Object obj, AbstractC1904c abstractC1904c) {
        InterfaceC0492w interfaceC0492w = (InterfaceC0492w) obj;
        int[] iArrMo723l = abstractC1904c.mo723l(interfaceC0489t, interfaceC0492w.m289b(), interfaceC0492w.m290c());
        for (int i7 = 0; i7 < iArrMo723l.length; i7++) {
            interfaceC0489t.mo281b(i7, iArrMo723l[i7]);
        }
    }

    @Override // p042e6.InterfaceC0974g
    /* renamed from: d */
    public long mo938d(Object obj) {
        return ((InterfaceC0492w) obj).m288a();
    }
}
