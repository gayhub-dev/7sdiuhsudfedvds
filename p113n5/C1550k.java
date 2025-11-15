package p113n5;

import java.io.InputStream;
import p065h5.C1109q;
import p073i5.C1166s;
import p073i5.InterfaceC1161n;
import p081j5.C1211b;

/* compiled from: HttpInput.java */
/* renamed from: n5.k */
/* loaded from: classes.dex */
public class C1550k extends InputStream {

    /* renamed from: e */
    public final AbstractC1541b f4604e;

    /* renamed from: f */
    public final C1109q f4605f;

    public C1550k(AbstractC1541b abstractC1541b) {
        this.f4604e = abstractC1541b;
        this.f4605f = (C1109q) abstractC1541b.f4546g;
    }

    @Override // java.io.InputStream
    public int available() {
        C1109q c1109q = this.f4605f;
        C1166s c1166s = c1109q.f2353m;
        if (c1166s != null && c1166s.length() > 0) {
            return c1109q.f2353m.length();
        }
        if (!c1109q.f2343c.mo923p()) {
            c1109q.m1248h();
            C1166s c1166s2 = c1109q.f2353m;
            if (c1166s2 != null) {
                return c1166s2.length();
            }
        } else if (c1109q.f2354n > 0) {
            InterfaceC1161n interfaceC1161n = c1109q.f2343c;
            if ((interfaceC1161n instanceof C1211b) && ((C1211b) interfaceC1161n).f2730e.available() > 0) {
                return 1;
            }
        }
        return 0;
    }

    @Override // java.io.InputStream
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) < 0) {
            return -1;
        }
        return bArr[0] & 255;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0078  */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read(byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            h5.q r0 = r6.f4605f
            n5.b r1 = r6.f4604e
            int r1 = r1.m1737h()
            long r1 = (long) r1
            i5.s r3 = r0.f2353m
            int r3 = r3.length()
            if (r3 <= 0) goto L15
            i5.s r0 = r0.f2353m
            goto L81
        L15:
            int r3 = r0.f2354n
            r4 = 0
            if (r3 <= 0) goto L78
            r3 = 7
            boolean r5 = r0.m1246f(r3)
            if (r5 == 0) goto L22
            goto L78
        L22:
            r0.m1248h()     // Catch: java.io.IOException -> L7a
        L25:
            i5.s r5 = r0.f2353m     // Catch: java.io.IOException -> L7a
            int r5 = r5.length()     // Catch: java.io.IOException -> L7a
            if (r5 != 0) goto L6d
            r5 = 0
            boolean r5 = r0.m1246f(r5)     // Catch: java.io.IOException -> L7a
            if (r5 != 0) goto L6d
            boolean r5 = r0.m1246f(r3)     // Catch: java.io.IOException -> L7a
            if (r5 != 0) goto L6d
            i5.n r5 = r0.f2343c     // Catch: java.io.IOException -> L7a
            if (r5 == 0) goto L6d
            boolean r5 = r5.isOpen()     // Catch: java.io.IOException -> L7a
            if (r5 == 0) goto L6d
            i5.n r5 = r0.f2343c     // Catch: java.io.IOException -> L7a
            boolean r5 = r5.mo923p()     // Catch: java.io.IOException -> L7a
            if (r5 != 0) goto L69
            int r5 = r0.m1248h()     // Catch: java.io.IOException -> L7a
            if (r5 <= 0) goto L53
            goto L25
        L53:
            i5.n r5 = r0.f2343c     // Catch: java.io.IOException -> L7a
            boolean r5 = r5.mo922o(r1)     // Catch: java.io.IOException -> L7a
            if (r5 == 0) goto L5c
            goto L69
        L5c:
            i5.n r7 = r0.f2343c     // Catch: java.io.IOException -> L7a
            r7.close()     // Catch: java.io.IOException -> L7a
            i5.o r7 = new i5.o     // Catch: java.io.IOException -> L7a
            java.lang.String r8 = "timeout"
            r7.<init>(r8)     // Catch: java.io.IOException -> L7a
            throw r7     // Catch: java.io.IOException -> L7a
        L69:
            r0.m1248h()     // Catch: java.io.IOException -> L7a
            goto L25
        L6d:
            i5.s r1 = r0.f2353m
            int r1 = r1.length()
            if (r1 <= 0) goto L78
            i5.s r0 = r0.f2353m
            goto L81
        L78:
            r0 = r4
            goto L81
        L7a:
            r7 = move-exception
            i5.n r8 = r0.f2343c
            r8.close()
            throw r7
        L81:
            r1 = -1
            if (r0 == 0) goto L9c
            int r2 = r0.f2524g
            int r3 = r0.length()
            if (r3 != 0) goto L8d
            goto La2
        L8d:
            if (r9 <= r3) goto L90
            r9 = r3
        L90:
            int r7 = r0.mo1352m(r2, r7, r8, r9)
            if (r7 <= 0) goto L9a
            int r2 = r2 + r7
            r0.mo1331i(r2)
        L9a:
            r1 = r7
            goto La2
        L9c:
            n5.b r7 = r6.f4604e
            boolean r7 = r7.f4564y
            if (r7 != 0) goto La3
        La2:
            return r1
        La3:
            i5.o r7 = new i5.o
            java.lang.String r8 = "early EOF"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p113n5.C1550k.read(byte[], int, int):int");
    }
}
