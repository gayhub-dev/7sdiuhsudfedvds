package p058g6;

/* compiled from: InternalParserDateTimeParser.java */
/* renamed from: g6.k */
/* loaded from: classes.dex */
public class C1073k implements InterfaceC1066d, InterfaceC1072j {

    /* renamed from: e */
    public final InterfaceC1072j f2137e;

    public C1073k(InterfaceC1072j interfaceC1072j) {
        this.f2137e = interfaceC1072j;
    }

    /* renamed from: c */
    public static InterfaceC1066d m1147c(InterfaceC1072j interfaceC1072j) {
        if (interfaceC1072j instanceof C1068f) {
            return ((C1068f) interfaceC1072j).f2075e;
        }
        if (interfaceC1072j instanceof InterfaceC1066d) {
            return (InterfaceC1066d) interfaceC1072j;
        }
        if (interfaceC1072j == null) {
            return null;
        }
        return new C1073k(interfaceC1072j);
    }

    @Override // p058g6.InterfaceC1066d, p058g6.InterfaceC1072j
    /* renamed from: a */
    public int mo1093a() {
        return this.f2137e.mo1093a();
    }

    @Override // p058g6.InterfaceC1066d
    /* renamed from: b */
    public int mo1100b(C1067e c1067e, String str, int i7) {
        return this.f2137e.mo1097f(c1067e, str, i7);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C1073k) {
            return this.f2137e.equals(((C1073k) obj).f2137e);
        }
        return false;
    }

    @Override // p058g6.InterfaceC1072j
    /* renamed from: f */
    public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) {
        return this.f2137e.mo1097f(c1067e, charSequence, i7);
    }
}
