package p044f0;

import java.nio.ByteBuffer;
import p169v.InterfaceC1987c;

/* compiled from: ByteBufferRewinder.java */
/* renamed from: f0.a */
/* loaded from: classes.dex */
public class C0990a implements InterfaceC1987c<ByteBuffer> {

    /* renamed from: a */
    public final ByteBuffer f1835a;

    /* compiled from: ByteBufferRewinder.java */
    /* renamed from: f0.a$a */
    public static class a implements InterfaceC1987c.a<ByteBuffer> {
        @Override // p169v.InterfaceC1987c.a
        /* renamed from: a */
        public Class<ByteBuffer> mo1005a() {
            return ByteBuffer.class;
        }

        @Override // p169v.InterfaceC1987c.a
        /* renamed from: b */
        public InterfaceC1987c<ByteBuffer> mo1006b(ByteBuffer byteBuffer) {
            return new C0990a(byteBuffer);
        }
    }

    public C0990a(ByteBuffer byteBuffer) {
        this.f1835a = byteBuffer;
    }

    @Override // p169v.InterfaceC1987c
    /* renamed from: a */
    public ByteBuffer mo1003a() {
        this.f1835a.position(0);
        return this.f1835a;
    }

    @Override // p169v.InterfaceC1987c
    /* renamed from: b */
    public void mo1004b() {
    }
}
