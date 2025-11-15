package p042e6;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import p016b6.AbstractC0475f;
import p016b6.C0473d;
import p016b6.InterfaceC0489t;
import p016b6.InterfaceC0490u;
import p159t3.AbstractC1904c;

/* compiled from: ReadableDurationConverter.java */
/* renamed from: e6.k */
/* loaded from: classes.dex */
public class C0978k extends AbstractC0968a implements InterfaceC0974g, InterfaceC0977j {

    /* renamed from: a */
    public static final C0978k f1810a = new C0978k();

    @Override // p042e6.InterfaceC0970c
    /* renamed from: a */
    public Class<?> mo935a() {
        return InterfaceC0490u.class;
    }

    @Override // p042e6.InterfaceC0977j
    /* renamed from: b */
    public void mo939b(InterfaceC0489t interfaceC0489t, Object obj, AbstractC1904c abstractC1904c) {
        AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
        int[] iArrMo722k = abstractC1904c.mo722k(interfaceC0489t, ((InterfaceC0490u) obj).mo287g());
        for (int i7 = 0; i7 < iArrMo722k.length; i7++) {
            interfaceC0489t.mo281b(i7, iArrMo722k[i7]);
        }
    }

    @Override // p042e6.InterfaceC0974g
    /* renamed from: d */
    public long mo938d(Object obj) {
        return ((InterfaceC0490u) obj).mo287g();
    }
}
