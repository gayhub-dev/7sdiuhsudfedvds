package com.alibaba.fastjson.util;

import com.alibaba.fastjson.asm.Opcodes;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/* loaded from: classes.dex */
public class UTF8Decoder extends CharsetDecoder {
    private static final Charset charset = Charset.forName("UTF-8");

    public UTF8Decoder() {
        super(charset, 1.0f, 1.0f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0094, code lost:
    
        return xflow(r13, r2, r6, r14, r8, 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00cc, code lost:
    
        return xflow(r13, r2, r6, r14, r8, 3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0132, code lost:
    
        return xflow(r13, r2, r6, r14, r8, 4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.nio.charset.CoderResult decodeArrayLoop(java.nio.ByteBuffer r13, java.nio.CharBuffer r14) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.UTF8Decoder.decodeArrayLoop(java.nio.ByteBuffer, java.nio.CharBuffer):java.nio.charset.CoderResult");
    }

    private static boolean isMalformed2(int i7, int i8) {
        return (i7 & 30) == 0 || (i8 & Opcodes.CHECKCAST) != 128;
    }

    private static boolean isMalformed3(int i7, int i8, int i9) {
        return ((i7 != -32 || (i8 & 224) != 128) && (i8 & Opcodes.CHECKCAST) == 128 && (i9 & Opcodes.CHECKCAST) == 128) ? false : true;
    }

    private static boolean isMalformed4(int i7, int i8, int i9) {
        return ((i7 & Opcodes.CHECKCAST) == 128 && (i8 & Opcodes.CHECKCAST) == 128 && (i9 & Opcodes.CHECKCAST) == 128) ? false : true;
    }

    private static boolean isNotContinuation(int i7) {
        return (i7 & Opcodes.CHECKCAST) != 128;
    }

    private static CoderResult lookupN(ByteBuffer byteBuffer, int i7) {
        for (int i8 = 1; i8 < i7; i8++) {
            if (isNotContinuation(byteBuffer.get())) {
                return CoderResult.malformedForLength(i8);
            }
        }
        return CoderResult.malformedForLength(i7);
    }

    private static CoderResult malformed(ByteBuffer byteBuffer, int i7, CharBuffer charBuffer, int i8, int i9) {
        byteBuffer.position(i7 - byteBuffer.arrayOffset());
        CoderResult coderResultMalformedN = malformedN(byteBuffer, i9);
        byteBuffer.position(i7);
        charBuffer.position(i8);
        return coderResultMalformedN;
    }

    public static CoderResult malformedN(ByteBuffer byteBuffer, int i7) {
        int i8 = 1;
        if (i7 == 1) {
            byte b7 = byteBuffer.get();
            return (b7 >> 2) == -2 ? byteBuffer.remaining() < 4 ? CoderResult.UNDERFLOW : lookupN(byteBuffer, 5) : (b7 >> 1) == -2 ? byteBuffer.remaining() < 5 ? CoderResult.UNDERFLOW : lookupN(byteBuffer, 6) : CoderResult.malformedForLength(1);
        }
        if (i7 == 2) {
            return CoderResult.malformedForLength(1);
        }
        if (i7 != 3) {
            if (i7 != 4) {
                throw new IllegalStateException();
            }
            int i9 = byteBuffer.get() & 255;
            int i10 = byteBuffer.get() & 255;
            return (i9 > 244 || (i9 == 240 && (i10 < 144 || i10 > 191)) || ((i9 == 244 && (i10 & 240) != 128) || isNotContinuation(i10))) ? CoderResult.malformedForLength(1) : isNotContinuation(byteBuffer.get()) ? CoderResult.malformedForLength(2) : CoderResult.malformedForLength(3);
        }
        byte b8 = byteBuffer.get();
        byte b9 = byteBuffer.get();
        if ((b8 != -32 || (b9 & 224) != 128) && !isNotContinuation(b9)) {
            i8 = 2;
        }
        return CoderResult.malformedForLength(i8);
    }

    private static CoderResult xflow(Buffer buffer, int i7, int i8, Buffer buffer2, int i9, int i10) {
        buffer.position(i7);
        buffer2.position(i9);
        return (i10 == 0 || i8 - i7 < i10) ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW;
    }

    @Override // java.nio.charset.CharsetDecoder
    public CoderResult decodeLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        return decodeArrayLoop(byteBuffer, charBuffer);
    }
}
