package p058g6;

/* compiled from: DateTimeParserInternalParser.java */
/* renamed from: g6.f */
/* loaded from: classes.dex */
public class C1068f implements InterfaceC1072j {

    /* renamed from: e */
    public final InterfaceC1066d f2075e;

    public C1068f(InterfaceC1066d interfaceC1066d) {
        this.f2075e = interfaceC1066d;
    }

    /* renamed from: b */
    public static InterfaceC1072j m1109b(InterfaceC1066d interfaceC1066d) {
        if (interfaceC1066d instanceof C1073k) {
            return (InterfaceC1072j) interfaceC1066d;
        }
        if (interfaceC1066d == null) {
            return null;
        }
        return new C1068f(interfaceC1066d);
    }

    @Override // p058g6.InterfaceC1072j
    /* renamed from: a */
    public int mo1093a() {
        return this.f2075e.mo1093a();
    }

    @Override // p058g6.InterfaceC1072j
    /* renamed from: f */
    public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) {
        return this.f2075e.mo1100b(c1067e, charSequence.toString(), i7);
    }
}
