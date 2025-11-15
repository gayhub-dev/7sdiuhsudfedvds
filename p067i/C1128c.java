package p067i;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* renamed from: i.c */
/* loaded from: classes.dex */
public final /* synthetic */ class C1128c {
    /* renamed from: a */
    public static FloatBuffer m1293a(ByteBuffer byteBuffer, float[] fArr, int i7) {
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer = byteBuffer.asFloatBuffer();
        floatBufferAsFloatBuffer.put(fArr);
        floatBufferAsFloatBuffer.position(i7);
        return floatBufferAsFloatBuffer;
    }
}
