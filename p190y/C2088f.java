package p190y;

/* compiled from: ByteArrayAdapter.java */
/* renamed from: y.f */
/* loaded from: classes.dex */
public final class C2088f implements InterfaceC2083a<byte[]> {

    /* renamed from: a */
    public final /* synthetic */ int f6180a;

    public C2088f(int i7) {
        this.f6180a = i7;
    }

    @Override // p190y.InterfaceC2083a
    /* renamed from: a */
    public int mo2506a(byte[] bArr) {
        switch (this.f6180a) {
        }
        return bArr.length;
    }

    @Override // p190y.InterfaceC2083a
    /* renamed from: b */
    public int mo2507b() {
        switch (this.f6180a) {
            case 0:
                return 1;
            default:
                return 4;
        }
    }

    @Override // p190y.InterfaceC2083a
    public String getTag() {
        switch (this.f6180a) {
            case 0:
                return "ByteArrayPool";
            default:
                return "IntegerArrayPool";
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [byte[], int[]] */
    @Override // p190y.InterfaceC2083a
    public byte[] newArray(int i7) {
        switch (this.f6180a) {
            case 0:
                return new byte[i7];
            default:
                return new int[i7];
        }
    }
}
