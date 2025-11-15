package p034d6;

import p159t3.AbstractC1904c;

/* compiled from: BasicGJChronology.java */
/* renamed from: d6.e */
/* loaded from: classes.dex */
public abstract class AbstractC0878e extends AbstractC0876c {

    /* renamed from: j0 */
    public static final int[] f1442j0 = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /* renamed from: k0 */
    public static final int[] f1443k0 = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /* renamed from: l0 */
    public static final long[] f1444l0 = new long[12];

    /* renamed from: m0 */
    public static final long[] f1445m0 = new long[12];
    private static final long serialVersionUID = 538276888268L;

    static {
        long j7 = 0;
        long j8 = 0;
        int i7 = 0;
        while (i7 < 11) {
            j7 += f1442j0[i7] * 86400000;
            int i8 = i7 + 1;
            f1444l0[i8] = j7;
            j8 += f1443k0[i7] * 86400000;
            f1445m0[i8] = j8;
            i7 = i8;
        }
    }

    public AbstractC0878e(AbstractC1904c abstractC1904c, Object obj, int i7) {
        super(abstractC1904c, obj, i7);
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: Z */
    public int mo731Z(long j7, int i7) {
        if (i7 <= 28 && i7 >= 1) {
            return 28;
        }
        int iM743l0 = m743l0(j7);
        return mo732a0(iM743l0, mo737f0(j7, iM743l0));
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: a0 */
    public int mo732a0(int i7, int i8) {
        return mo749r0(i7) ? f1443k0[i8 - 1] : f1442j0[i8 - 1];
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0070 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0072 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0095 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0098 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a6 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00a9 A[ORIG_RETURN, RETURN] */
    @Override // p034d6.AbstractC0876c
    /* renamed from: f0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int mo737f0(long r13, int r15) {
        /*
            r12 = this;
            long r0 = r12.m745n0(r15)
            long r13 = r13 - r0
            r0 = 10
            long r13 = r13 >> r0
            int r14 = (int) r13
            boolean r13 = r12.mo749r0(r15)
            r15 = 2
            r1 = 3
            r2 = 5
            r3 = 6
            r4 = 8
            r5 = 9
            r6 = 11
            r7 = 12
            r8 = 1
            r9 = 4
            r10 = 7
            r11 = 2615625(0x27e949, float:3.665271E-39)
            if (r13 == 0) goto L5d
            r13 = 15356250(0xea515a, float:2.151869E-38)
            if (r14 >= r13) goto L40
            r13 = 7678125(0x7528ad, float:1.0759345E-38)
            if (r14 >= r13) goto L34
            if (r14 >= r11) goto L2e
            goto L69
        L2e:
            r13 = 5062500(0x4d3f64, float:7.094073E-39)
            if (r14 >= r13) goto L72
            goto L70
        L34:
            r13 = 10209375(0x9bc85f, float:1.4306382E-38)
            if (r14 >= r13) goto L3a
            goto L79
        L3a:
            r13 = 12825000(0xc3b1a8, float:1.7971653E-38)
            if (r14 >= r13) goto L82
            goto L80
        L40:
            r13 = 23118750(0x160c39e, float:4.128265E-38)
            if (r14 >= r13) goto L51
            r13 = 17971875(0x1123aa3, float:2.6858035E-38)
            if (r14 >= r13) goto L4b
            goto L8e
        L4b:
            r13 = 20587500(0x13a23ec, float:3.4188577E-38)
            if (r14 >= r13) goto L98
            goto L95
        L51:
            r13 = 25734375(0x188ace7, float:5.020661E-38)
            if (r14 >= r13) goto L57
            goto Lab
        L57:
            r13 = 28265625(0x1af4c99, float:6.439476E-38)
            if (r14 >= r13) goto La9
            goto La6
        L5d:
            r13 = 15271875(0xe907c3, float:2.1400455E-38)
            if (r14 >= r13) goto L84
            r13 = 7593750(0x73df16, float:1.064111E-38)
            if (r14 >= r13) goto L74
            if (r14 >= r11) goto L6b
        L69:
            r0 = 1
            goto Lab
        L6b:
            r13 = 4978125(0x4bf5cd, float:6.975839E-39)
            if (r14 >= r13) goto L72
        L70:
            r0 = 2
            goto Lab
        L72:
            r0 = 3
            goto Lab
        L74:
            r13 = 10125000(0x9a7ec8, float:1.4188147E-38)
            if (r14 >= r13) goto L7b
        L79:
            r0 = 4
            goto Lab
        L7b:
            r13 = 12740625(0xc26811, float:1.7853418E-38)
            if (r14 >= r13) goto L82
        L80:
            r0 = 5
            goto Lab
        L82:
            r0 = 6
            goto Lab
        L84:
            r13 = 23034375(0x15f7a07, float:4.1046182E-38)
            if (r14 >= r13) goto L9b
            r13 = 17887500(0x110f10c, float:2.6621566E-38)
            if (r14 >= r13) goto L90
        L8e:
            r0 = 7
            goto Lab
        L90:
            r13 = 20503125(0x138da55, float:3.3952108E-38)
            if (r14 >= r13) goto L98
        L95:
            r0 = 8
            goto Lab
        L98:
            r0 = 9
            goto Lab
        L9b:
            r13 = 25650000(0x1876350, float:4.9733674E-38)
            if (r14 >= r13) goto La1
            goto Lab
        La1:
            r13 = 28181250(0x1ae0302, float:6.392182E-38)
            if (r14 >= r13) goto La9
        La6:
            r0 = 11
            goto Lab
        La9:
            r0 = 12
        Lab:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p034d6.AbstractC0878e.mo737f0(long, int):int");
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: g0 */
    public long mo738g0(int i7, int i8) {
        return mo749r0(i7) ? f1445m0[i8 - 1] : f1444l0[i8 - 1];
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: m0 */
    public long mo744m0(long j7, long j8) {
        int iM743l0 = m743l0(j7);
        int iM743l02 = m743l0(j8);
        long jM745n0 = j7 - m745n0(iM743l0);
        long jM745n02 = j8 - m745n0(iM743l02);
        if (jM745n02 >= 5097600000L) {
            if (mo749r0(iM743l02)) {
                if (!mo749r0(iM743l0)) {
                    jM745n02 -= 86400000;
                }
            } else if (jM745n0 >= 5097600000L && mo749r0(iM743l0)) {
                jM745n0 -= 86400000;
            }
        }
        int i7 = iM743l0 - iM743l02;
        if (jM745n0 < jM745n02) {
            i7--;
        }
        return i7;
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: q0 */
    public boolean mo748q0(long j7) {
        return this.f1350E.mo199b(j7) == 29 && this.f1355J.mo214q(j7);
    }

    @Override // p034d6.AbstractC0876c
    /* renamed from: s0 */
    public long mo750s0(long j7, int i7) {
        int iM743l0 = m743l0(j7);
        int iM745n0 = ((int) ((j7 - m745n0(iM743l0)) / 86400000)) + 1;
        int iM735d0 = m735d0(j7);
        if (iM745n0 > 59) {
            if (mo749r0(iM743l0)) {
                if (!mo749r0(i7)) {
                    iM745n0--;
                }
            } else if (mo749r0(i7)) {
                iM745n0++;
            }
        }
        return m746o0(i7, 1, iM745n0) + iM735d0;
    }
}
