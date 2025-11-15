package p089k5;

import java.nio.ByteBuffer;
import p073i5.C1158k;

/* compiled from: IndirectNIOBuffer.java */
/* renamed from: k5.d */
/* loaded from: classes.dex */
public class C1395d extends C1158k implements InterfaceC1396e {

    /* renamed from: r */
    public final ByteBuffer f4063r;

    public C1395d(int i7) {
        super(i7, 2, false);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.f2548p);
        this.f4063r = byteBufferWrap;
        byteBufferWrap.position(0);
        byteBufferWrap.limit(byteBufferWrap.capacity());
    }

    @Override // p089k5.InterfaceC1396e
    /* renamed from: o */
    public ByteBuffer mo1563o() {
        return this.f4063r;
    }

    public C1395d(ByteBuffer byteBuffer, boolean z6) {
        super(byteBuffer.array(), 0, 0, z6 ? 0 : 2, false);
        if (!byteBuffer.isDirect()) {
            this.f4063r = byteBuffer;
            this.f2524g = byteBuffer.position();
            this.f2525h = byteBuffer.limit();
            byteBuffer.position(0);
            byteBuffer.limit(byteBuffer.capacity());
            return;
        }
        throw new IllegalArgumentException();
    }
}
