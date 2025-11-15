package com.alibaba.fastjson.util;

import java.lang.ref.SoftReference;
import java.nio.charset.CharsetDecoder;

/* loaded from: classes.dex */
public class ThreadLocalCache {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int BYTES_CACH_INIT_SIZE = 1024;
    public static final int BYTES_CACH_INIT_SIZE_EXP = 10;
    public static final int BYTES_CACH_MAX_SIZE = 131072;
    public static final int BYTES_CACH_MAX_SIZE_EXP = 17;
    public static final int CHARS_CACH_INIT_SIZE = 1024;
    public static final int CHARS_CACH_INIT_SIZE_EXP = 10;
    public static final int CHARS_CACH_MAX_SIZE = 131072;
    public static final int CHARS_CACH_MAX_SIZE_EXP = 17;
    private static final ThreadLocal<SoftReference<char[]>> charsBufLocal = new ThreadLocal<>();
    private static final ThreadLocal<CharsetDecoder> decoderLocal = new ThreadLocal<>();
    private static final ThreadLocal<SoftReference<byte[]>> bytesBufLocal = new ThreadLocal<>();

    private static char[] allocate(int i7) {
        if (i7 > 131072) {
            return new char[i7];
        }
        char[] cArr = new char[getAllocateLengthExp(10, 17, i7)];
        charsBufLocal.set(new SoftReference<>(cArr));
        return cArr;
    }

    private static byte[] allocateBytes(int i7) {
        if (i7 > 131072) {
            return new byte[i7];
        }
        byte[] bArr = new byte[getAllocateLengthExp(10, 17, i7)];
        bytesBufLocal.set(new SoftReference<>(bArr));
        return bArr;
    }

    public static void clearBytes() {
        bytesBufLocal.set(null);
    }

    public static void clearChars() {
        charsBufLocal.set(null);
    }

    private static int getAllocateLengthExp(int i7, int i8, int i9) {
        return (i9 >>> i7) <= 0 ? 1 << i7 : 1 << (32 - Integer.numberOfLeadingZeros(i9 - 1));
    }

    public static byte[] getBytes(int i7) {
        SoftReference<byte[]> softReference = bytesBufLocal.get();
        if (softReference == null) {
            return allocateBytes(i7);
        }
        byte[] bArr = softReference.get();
        return bArr == null ? allocateBytes(i7) : bArr.length < i7 ? allocateBytes(i7) : bArr;
    }

    public static char[] getChars(int i7) {
        SoftReference<char[]> softReference = charsBufLocal.get();
        if (softReference == null) {
            return allocate(i7);
        }
        char[] cArr = softReference.get();
        return cArr == null ? allocate(i7) : cArr.length < i7 ? allocate(i7) : cArr;
    }

    public static CharsetDecoder getUTF8Decoder() {
        ThreadLocal<CharsetDecoder> threadLocal = decoderLocal;
        CharsetDecoder charsetDecoder = threadLocal.get();
        if (charsetDecoder != null) {
            return charsetDecoder;
        }
        UTF8Decoder uTF8Decoder = new UTF8Decoder();
        threadLocal.set(uTF8Decoder);
        return uTF8Decoder;
    }
}
