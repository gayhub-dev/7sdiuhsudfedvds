package p036e0;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import java.util.Objects;
import p142r0.C1816a;
import p155t.InterfaceC1891a;
import p162u.C1966j;
import p162u.InterfaceC1967k;
import p183x.InterfaceC2057r;
import p190y.InterfaceC2086d;

/* compiled from: ByteBufferBitmapDecoder.java */
/* renamed from: e0.f */
/* loaded from: classes.dex */
public class C0907f implements InterfaceC1967k<ByteBuffer, Bitmap> {

    /* renamed from: a */
    public final /* synthetic */ int f1631a = 0;

    /* renamed from: b */
    public final Object f1632b;

    public C0907f(C0912k c0912k) {
        this.f1632b = c0912k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p162u.InterfaceC1967k
    /* renamed from: a */
    public boolean mo819a(ByteBuffer byteBuffer, C1966j c1966j) {
        switch (this.f1631a) {
            case 0:
                Objects.requireNonNull((C0912k) this.f1632b);
                break;
            default:
                break;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p162u.InterfaceC1967k
    /* renamed from: b */
    public InterfaceC2057r<Bitmap> mo820b(ByteBuffer byteBuffer, int i7, int i8, C1966j c1966j) {
        switch (this.f1631a) {
            case 0:
                int i9 = C1816a.f5280a;
                return ((C0912k) this.f1632b).m843a(new C1816a.a(byteBuffer), i7, i8, c1966j, C0912k.f1649k);
            default:
                return C0905d.m822c(((InterfaceC1891a) byteBuffer).mo2181b(), (InterfaceC2086d) this.f1632b);
        }
    }

    public C0907f(InterfaceC2086d interfaceC2086d) {
        this.f1632b = interfaceC2086d;
    }
}
