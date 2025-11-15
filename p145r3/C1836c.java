package p145r3;

import android.support.constraint.motion.C0079a;
import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.internal.p124ws.WebSocketProtocol;

/* compiled from: WalleChannelReader.java */
/* renamed from: r3.c */
/* loaded from: classes.dex */
public final class C1836c {
    /* renamed from: a */
    public static C1834a<ByteBuffer, Long> m2082a(FileChannel fileChannel) throws C1835b, IOException {
        long size = fileChannel.size();
        if (size < 22) {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
        long j7 = size - 22;
        long jMin = Math.min(j7, WebSocketProtocol.PAYLOAD_SHORT_MAX);
        int i7 = 0;
        while (true) {
            long j8 = i7;
            if (j8 > jMin) {
                throw new IOException("ZIP End of Central Directory (EOCD) record not found");
            }
            long j9 = j7 - j8;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            fileChannel.position(j9);
            fileChannel.read(byteBufferAllocate);
            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
            byteBufferAllocate.order(byteOrder);
            if (byteBufferAllocate.getInt(0) == 101010256) {
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(2);
                fileChannel.position(j9 + 20);
                fileChannel.read(byteBufferAllocate2);
                byteBufferAllocate2.order(byteOrder);
                short s6 = byteBufferAllocate2.getShort(0);
                if (s6 == i7) {
                    ByteBuffer byteBufferAllocate3 = ByteBuffer.allocate(4);
                    byteBufferAllocate3.order(byteOrder);
                    fileChannel.position((fileChannel.size() - s6) - 6);
                    fileChannel.read(byteBufferAllocate3);
                    long j10 = byteBufferAllocate3.getInt(0);
                    if (j10 < 32) {
                        throw new C1835b(C0534b.m341a("APK too small for APK Signing Block. ZIP Central Directory offset: ", j10));
                    }
                    fileChannel.position(j10 - 24);
                    ByteBuffer byteBufferAllocate4 = ByteBuffer.allocate(24);
                    fileChannel.read(byteBufferAllocate4);
                    byteBufferAllocate4.order(byteOrder);
                    if (byteBufferAllocate4.getLong(8) != 2334950737559900225L || byteBufferAllocate4.getLong(16) != 3617552046287187010L) {
                        throw new C1835b("No APK Signing Block before ZIP Central Directory");
                    }
                    long j11 = byteBufferAllocate4.getLong(0);
                    if (j11 < byteBufferAllocate4.capacity() || j11 > 2147483639) {
                        throw new C1835b(C0534b.m341a("APK Signing Block size out of range: ", j11));
                    }
                    int i8 = (int) (8 + j11);
                    long j12 = j10 - i8;
                    if (j12 < 0) {
                        throw new C1835b(C0534b.m341a("APK Signing Block offset out of range: ", j12));
                    }
                    fileChannel.position(j12);
                    ByteBuffer byteBufferAllocate5 = ByteBuffer.allocate(i8);
                    fileChannel.read(byteBufferAllocate5);
                    byteBufferAllocate5.order(byteOrder);
                    long j13 = byteBufferAllocate5.getLong(0);
                    if (j13 == j11) {
                        return new C1834a<>(byteBufferAllocate5, Long.valueOf(j12));
                    }
                    throw new C1835b("APK Signing Block sizes in header and footer do not match: " + j13 + " vs " + j11);
                }
            }
            i7++;
        }
    }

    /* renamed from: b */
    public static Map<Integer, ByteBuffer> m2083b(ByteBuffer byteBuffer) throws C1835b {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
        int iCapacity = byteBuffer.capacity() - 24;
        if (iCapacity < 8) {
            throw new IllegalArgumentException("end < start: " + iCapacity + " < 8");
        }
        int iCapacity2 = byteBuffer.capacity();
        if (iCapacity > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + iCapacity + " > " + iCapacity2);
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        int i7 = 0;
        try {
            byteBuffer.position(0);
            byteBuffer.limit(iCapacity);
            byteBuffer.position(8);
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            byteBuffer.position(0);
            byteBuffer.limit(iLimit);
            byteBuffer.position(iPosition);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            while (byteBufferSlice.hasRemaining()) {
                i7++;
                if (byteBufferSlice.remaining() < 8) {
                    throw new C1835b(C0079a.m93a("Insufficient data to read size of APK Signing Block entry #", i7));
                }
                long j7 = byteBufferSlice.getLong();
                if (j7 < 4 || j7 > 2147483647L) {
                    throw new C1835b("APK Signing Block entry #" + i7 + " size out of range: " + j7);
                }
                int i8 = (int) j7;
                int iPosition2 = byteBufferSlice.position() + i8;
                if (i8 > byteBufferSlice.remaining()) {
                    throw new C1835b("APK Signing Block entry #" + i7 + " size out of range: " + i8 + ", available: " + byteBufferSlice.remaining());
                }
                Integer numValueOf = Integer.valueOf(byteBufferSlice.getInt());
                int i9 = i8 - 4;
                if (i9 < 0) {
                    throw new IllegalArgumentException(C0079a.m93a("size: ", i9));
                }
                int iLimit2 = byteBufferSlice.limit();
                int iPosition3 = byteBufferSlice.position();
                int i10 = i9 + iPosition3;
                if (i10 < iPosition3 || i10 > iLimit2) {
                    throw new BufferUnderflowException();
                }
                byteBufferSlice.limit(i10);
                try {
                    ByteBuffer byteBufferSlice2 = byteBufferSlice.slice();
                    byteBufferSlice2.order(byteBufferSlice.order());
                    byteBufferSlice.position(i10);
                    byteBufferSlice.limit(iLimit2);
                    linkedHashMap.put(numValueOf, byteBufferSlice2);
                    byteBufferSlice.position(iPosition2);
                } catch (Throwable th) {
                    byteBufferSlice.limit(iLimit2);
                    throw th;
                }
            }
            return linkedHashMap;
        } catch (Throwable th2) {
            byteBuffer.position(0);
            byteBuffer.limit(iLimit);
            byteBuffer.position(iPosition);
            throw th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
    @android.support.annotation.Nullable
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m2084c(@android.support.annotation.NonNull android.content.Context r4, @android.support.annotation.NonNull java.lang.String r5) {
        /*
            r5 = 0
            android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo()     // Catch: java.lang.Throwable -> Lb
            if (r4 != 0) goto L8
            goto Lb
        L8:
            java.lang.String r4 = r4.sourceDir     // Catch: java.lang.Throwable -> Lb
            goto Lc
        Lb:
            r4 = r5
        Lc:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L13
            goto L4a
        L13:
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            java.lang.String r4 = m2085d(r0)     // Catch: org.json.JSONException -> L43
            if (r4 != 0) goto L1f
            goto L47
        L1f:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L43
            r0.<init>(r4)     // Catch: org.json.JSONException -> L43
            java.util.Iterator r4 = r0.keys()     // Catch: org.json.JSONException -> L43
            java.util.HashMap r1 = new java.util.HashMap     // Catch: org.json.JSONException -> L43
            r1.<init>()     // Catch: org.json.JSONException -> L43
        L2d:
            boolean r2 = r4.hasNext()     // Catch: org.json.JSONException -> L43
            if (r2 == 0) goto L48
            java.lang.Object r2 = r4.next()     // Catch: org.json.JSONException -> L43
            java.lang.String r2 = r2.toString()     // Catch: org.json.JSONException -> L43
            java.lang.String r3 = r0.getString(r2)     // Catch: org.json.JSONException -> L43
            r1.put(r2, r3)     // Catch: org.json.JSONException -> L43
            goto L2d
        L43:
            r4 = move-exception
            r4.printStackTrace()
        L47:
            r1 = r5
        L48:
            if (r1 != 0) goto L4c
        L4a:
            r4 = r5
            goto L5c
        L4c:
            java.lang.String r4 = "channel"
            java.lang.Object r0 = r1.get(r4)
            java.lang.String r0 = (java.lang.String) r0
            r1.remove(r4)
            g.k r4 = new g.k
            r4.<init>(r0, r1)
        L5c:
            if (r4 != 0) goto L5f
            return r5
        L5f:
            java.lang.Object r4 = r4.f1973b
            java.lang.String r4 = (java.lang.String) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p145r3.C1836c.m2084c(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m2085d(java.io.File r4) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L36
            java.lang.String r2 = "r"
            r1.<init>(r4, r2)     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L36
            java.nio.channels.FileChannel r4 = r1.getChannel()     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            r3.a r2 = m2082a(r4)     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            A r2 = r2.f5340a     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            java.util.Map r2 = m2083b(r2)     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            r4.close()     // Catch: java.io.IOException -> L42 java.lang.Throwable -> L46
            goto L42
        L1c:
            r2 = move-exception
            goto L29
        L1e:
            goto L38
        L20:
            r2 = move-exception
            r4 = r0
            goto L29
        L23:
            r4 = r0
            goto L38
        L25:
            r4 = move-exception
            r2 = r4
            r4 = r0
            r1 = r4
        L29:
            if (r4 == 0) goto L30
            r4.close()     // Catch: java.io.IOException -> L2f p145r3.C1835b -> L48
            goto L30
        L2f:
        L30:
            if (r1 == 0) goto L35
            r1.close()     // Catch: java.io.IOException -> L35 p145r3.C1835b -> L48
        L35:
            throw r2     // Catch: p145r3.C1835b -> L48
        L36:
            r4 = r0
            r1 = r4
        L38:
            if (r4 == 0) goto L3f
            r4.close()     // Catch: java.io.IOException -> L3e p145r3.C1835b -> L48
            goto L3f
        L3e:
        L3f:
            if (r1 == 0) goto L48
            r2 = r0
        L42:
            r1.close()     // Catch: java.lang.Throwable -> L46
            goto L49
        L46:
            goto L49
        L48:
            r2 = r0
        L49:
            if (r2 != 0) goto L4c
            goto L5b
        L4c:
            r4 = 1903654775(0x71777777, float:1.22539554E30)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object r4 = r2.get(r4)
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            if (r4 != 0) goto L5d
        L5b:
            r4 = r0
            goto L73
        L5d:
            byte[] r1 = r4.array()
            int r2 = r4.arrayOffset()
            int r3 = r4.position()
            int r3 = r3 + r2
            int r4 = r4.limit()
            int r4 = r4 + r2
            byte[] r4 = java.util.Arrays.copyOfRange(r1, r3, r4)
        L73:
            if (r4 != 0) goto L76
            goto L83
        L76:
            java.lang.String r1 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L7f
            java.lang.String r2 = "UTF-8"
            r1.<init>(r4, r2)     // Catch: java.io.UnsupportedEncodingException -> L7f
            r0 = r1
            goto L83
        L7f:
            r4 = move-exception
            r4.printStackTrace()
        L83:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p145r3.C1836c.m2085d(java.io.File):java.lang.String");
    }
}
