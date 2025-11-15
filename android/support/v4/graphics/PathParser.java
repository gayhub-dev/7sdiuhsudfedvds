package android.support.v4.graphics;

import android.arch.lifecycle.C0063n;
import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.support.constraint.solver.widgets.analyzer.C0096a;
import java.util.ArrayList;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class PathParser {
    private static final String LOGTAG = "PathParser";

    public static class ExtractFloatResult {
        public int mEndPosition;
        public boolean mEndWithNegOrDot;
    }

    private PathParser() {
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c7, float[] fArr) {
        arrayList.add(new PathDataNode(c7, fArr));
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i7 = 0; i7 < pathDataNodeArr.length; i7++) {
            if (pathDataNodeArr[i7].mType != pathDataNodeArr2[i7].mType || pathDataNodeArr[i7].mParams.length != pathDataNodeArr2[i7].mParams.length) {
                return false;
            }
        }
        return true;
    }

    public static float[] copyOfRange(float[] fArr, int i7, int i8) {
        if (i7 > i8) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (i7 < 0 || i7 > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i9 = i8 - i7;
        int iMin = Math.min(i9, length - i7);
        float[] fArr2 = new float[i9];
        System.arraycopy(fArr, i7, fArr2, 0, iMin);
        return fArr2;
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i7 = 1;
        int i8 = 0;
        while (i7 < str.length()) {
            int iNextStart = nextStart(str, i7);
            String strTrim = str.substring(i8, iNextStart).trim();
            if (strTrim.length() > 0) {
                addNode(arrayList, strTrim.charAt(0), getFloats(strTrim));
            }
            i8 = iNextStart;
            i7 = iNextStart + 1;
        }
        if (i7 - i8 == 1 && i8 < str.length()) {
            addNode(arrayList, str.charAt(i8), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] pathDataNodeArrCreateNodesFromPathData = createNodesFromPathData(str);
        if (pathDataNodeArrCreateNodesFromPathData == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(pathDataNodeArrCreateNodesFromPathData, path);
            return path;
        } catch (RuntimeException e7) {
            throw new RuntimeException(C0063n.m88a("Error in parsing ", str), e7);
        }
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i7 = 0; i7 < pathDataNodeArr.length; i7++) {
            pathDataNodeArr2[i7] = new PathDataNode(pathDataNodeArr[i7]);
        }
        return pathDataNodeArr2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003a A[LOOP:0: B:3:0x0007->B:24:0x003a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x003d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void extract(java.lang.String r8, int r9, android.support.v4.graphics.PathParser.ExtractFloatResult r10) {
        /*
            r0 = 0
            r10.mEndWithNegOrDot = r0
            r1 = r9
            r2 = 0
            r3 = 0
            r4 = 0
        L7:
            int r5 = r8.length()
            if (r1 >= r5) goto L3d
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L35
            r6 = 69
            if (r5 == r6) goto L33
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L33
            switch(r5) {
                case 44: goto L35;
                case 45: goto L2a;
                case 46: goto L22;
                default: goto L21;
            }
        L21:
            goto L31
        L22:
            if (r3 != 0) goto L27
            r2 = 0
            r3 = 1
            goto L37
        L27:
            r10.mEndWithNegOrDot = r7
            goto L35
        L2a:
            if (r1 == r9) goto L31
            if (r2 != 0) goto L31
            r10.mEndWithNegOrDot = r7
            goto L35
        L31:
            r2 = 0
            goto L37
        L33:
            r2 = 1
            goto L37
        L35:
            r2 = 0
            r4 = 1
        L37:
            if (r4 == 0) goto L3a
            goto L3d
        L3a:
            int r1 = r1 + 1
            goto L7
        L3d:
            r10.mEndPosition = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.PathParser.extract(java.lang.String, int, android.support.v4.graphics.PathParser$ExtractFloatResult):void");
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int i7 = 1;
            int i8 = 0;
            while (i7 < length) {
                extract(str, i7, extractFloatResult);
                int i9 = extractFloatResult.mEndPosition;
                if (i7 < i9) {
                    fArr[i8] = Float.parseFloat(str.substring(i7, i9));
                    i8++;
                }
                i7 = extractFloatResult.mEndWithNegOrDot ? i9 : i9 + 1;
            }
            return copyOfRange(fArr, 0, i8);
        } catch (NumberFormatException e7) {
            throw new RuntimeException(C0096a.m97a("error in parsing \"", str, "\""), e7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int nextStart(java.lang.String r3, int r4) {
        /*
        L0:
            int r0 = r3.length()
            if (r4 >= r0) goto L26
            char r0 = r3.charAt(r4)
            int r1 = r0 + (-65)
            int r2 = r0 + (-90)
            int r2 = r2 * r1
            if (r2 <= 0) goto L1a
            int r1 = r0 + (-97)
            int r2 = r0 + (-122)
            int r2 = r2 * r1
            if (r2 > 0) goto L23
        L1a:
            r1 = 101(0x65, float:1.42E-43)
            if (r0 == r1) goto L23
            r1 = 69
            if (r0 == r1) goto L23
            return r4
        L23:
            int r4 = r4 + 1
            goto L0
        L26:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.PathParser.nextStart(java.lang.String, int):int");
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i7 = 0; i7 < pathDataNodeArr2.length; i7++) {
            pathDataNodeArr[i7].mType = pathDataNodeArr2[i7].mType;
            for (int i8 = 0; i8 < pathDataNodeArr2[i7].mParams.length; i8++) {
                pathDataNodeArr[i7].mParams[i8] = pathDataNodeArr2[i7].mParams[i8];
            }
        }
    }

    public static class PathDataNode {

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public float[] mParams;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public char mType;

        public PathDataNode(char c7, float[] fArr) {
            this.mType = c7;
            this.mParams = fArr;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private static void addCommand(Path path, float[] fArr, char c7, char c8, float[] fArr2) {
            int i7;
            int i8;
            float f7;
            float f8;
            float f9;
            float f10;
            float f11;
            float f12;
            float f13;
            float f14;
            char c9 = c8;
            float f15 = fArr[0];
            float f16 = fArr[1];
            float f17 = fArr[2];
            float f18 = fArr[3];
            float f19 = fArr[4];
            float f20 = fArr[5];
            switch (c9) {
                case 'A':
                case 'a':
                    i7 = 7;
                    break;
                case 'C':
                case 'c':
                    i7 = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i7 = 1;
                    break;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                default:
                    i7 = 2;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i7 = 4;
                    break;
                case 'Z':
                case IjkMediaMeta.FF_PROFILE_H264_HIGH_422 /* 122 */:
                    path.close();
                    path.moveTo(f19, f20);
                    f15 = f19;
                    f17 = f15;
                    f16 = f20;
                    f18 = f16;
                    i7 = 2;
                    break;
            }
            float f21 = f15;
            float f22 = f16;
            float f23 = f19;
            float f24 = f20;
            int i9 = 0;
            char c10 = c7;
            while (i9 < fArr2.length) {
                if (c9 != 'A') {
                    if (c9 == 'C') {
                        i8 = i9;
                        int i10 = i8 + 2;
                        int i11 = i8 + 3;
                        int i12 = i8 + 4;
                        int i13 = i8 + 5;
                        path.cubicTo(fArr2[i8 + 0], fArr2[i8 + 1], fArr2[i10], fArr2[i11], fArr2[i12], fArr2[i13]);
                        f21 = fArr2[i12];
                        float f25 = fArr2[i13];
                        float f26 = fArr2[i10];
                        float f27 = fArr2[i11];
                        f22 = f25;
                        f18 = f27;
                        f17 = f26;
                    } else if (c9 == 'H') {
                        i8 = i9;
                        int i14 = i8 + 0;
                        path.lineTo(fArr2[i14], f22);
                        f21 = fArr2[i14];
                    } else if (c9 == 'Q') {
                        i8 = i9;
                        int i15 = i8 + 0;
                        int i16 = i8 + 1;
                        int i17 = i8 + 2;
                        int i18 = i8 + 3;
                        path.quadTo(fArr2[i15], fArr2[i16], fArr2[i17], fArr2[i18]);
                        float f28 = fArr2[i15];
                        float f29 = fArr2[i16];
                        f21 = fArr2[i17];
                        f22 = fArr2[i18];
                        f17 = f28;
                        f18 = f29;
                    } else if (c9 == 'V') {
                        i8 = i9;
                        int i19 = i8 + 0;
                        path.lineTo(f21, fArr2[i19]);
                        f22 = fArr2[i19];
                    } else if (c9 != 'a') {
                        if (c9 != 'c') {
                            if (c9 == 'h') {
                                int i20 = i9 + 0;
                                path.rLineTo(fArr2[i20], 0.0f);
                                f21 += fArr2[i20];
                            } else if (c9 != 'q') {
                                if (c9 == 'v') {
                                    int i21 = i9 + 0;
                                    path.rLineTo(0.0f, fArr2[i21]);
                                    f10 = fArr2[i21];
                                } else if (c9 == 'L') {
                                    int i22 = i9 + 0;
                                    int i23 = i9 + 1;
                                    path.lineTo(fArr2[i22], fArr2[i23]);
                                    f21 = fArr2[i22];
                                    f22 = fArr2[i23];
                                } else if (c9 == 'M') {
                                    int i24 = i9 + 0;
                                    f21 = fArr2[i24];
                                    int i25 = i9 + 1;
                                    f22 = fArr2[i25];
                                    if (i9 > 0) {
                                        path.lineTo(fArr2[i24], fArr2[i25]);
                                    } else {
                                        path.moveTo(fArr2[i24], fArr2[i25]);
                                        i8 = i9;
                                        f24 = f22;
                                        f23 = f21;
                                    }
                                } else if (c9 == 'S') {
                                    if (c10 == 'c' || c10 == 's' || c10 == 'C' || c10 == 'S') {
                                        f21 = (f21 * 2.0f) - f17;
                                        f22 = (f22 * 2.0f) - f18;
                                    }
                                    float f30 = f22;
                                    int i26 = i9 + 0;
                                    int i27 = i9 + 1;
                                    int i28 = i9 + 2;
                                    int i29 = i9 + 3;
                                    path.cubicTo(f21, f30, fArr2[i26], fArr2[i27], fArr2[i28], fArr2[i29]);
                                    f7 = fArr2[i26];
                                    f8 = fArr2[i27];
                                    f21 = fArr2[i28];
                                    f22 = fArr2[i29];
                                    f17 = f7;
                                    f18 = f8;
                                } else if (c9 == 'T') {
                                    if (c10 == 'q' || c10 == 't' || c10 == 'Q' || c10 == 'T') {
                                        f21 = (f21 * 2.0f) - f17;
                                        f22 = (f22 * 2.0f) - f18;
                                    }
                                    int i30 = i9 + 0;
                                    int i31 = i9 + 1;
                                    path.quadTo(f21, f22, fArr2[i30], fArr2[i31]);
                                    float f31 = fArr2[i30];
                                    float f32 = fArr2[i31];
                                    i8 = i9;
                                    f18 = f22;
                                    f17 = f21;
                                    f21 = f31;
                                    f22 = f32;
                                } else if (c9 == 'l') {
                                    int i32 = i9 + 0;
                                    int i33 = i9 + 1;
                                    path.rLineTo(fArr2[i32], fArr2[i33]);
                                    f21 += fArr2[i32];
                                    f10 = fArr2[i33];
                                } else if (c9 == 'm') {
                                    int i34 = i9 + 0;
                                    f21 += fArr2[i34];
                                    int i35 = i9 + 1;
                                    f22 += fArr2[i35];
                                    if (i9 > 0) {
                                        path.rLineTo(fArr2[i34], fArr2[i35]);
                                    } else {
                                        path.rMoveTo(fArr2[i34], fArr2[i35]);
                                        i8 = i9;
                                        f24 = f22;
                                        f23 = f21;
                                    }
                                } else if (c9 == 's') {
                                    if (c10 == 'c' || c10 == 's' || c10 == 'C' || c10 == 'S') {
                                        float f33 = f21 - f17;
                                        f11 = f22 - f18;
                                        f12 = f33;
                                    } else {
                                        f12 = 0.0f;
                                        f11 = 0.0f;
                                    }
                                    int i36 = i9 + 0;
                                    int i37 = i9 + 1;
                                    int i38 = i9 + 2;
                                    int i39 = i9 + 3;
                                    path.rCubicTo(f12, f11, fArr2[i36], fArr2[i37], fArr2[i38], fArr2[i39]);
                                    f7 = fArr2[i36] + f21;
                                    f8 = fArr2[i37] + f22;
                                    f21 += fArr2[i38];
                                    f9 = fArr2[i39];
                                } else if (c9 == 't') {
                                    if (c10 == 'q' || c10 == 't' || c10 == 'Q' || c10 == 'T') {
                                        f13 = f21 - f17;
                                        f14 = f22 - f18;
                                    } else {
                                        f14 = 0.0f;
                                        f13 = 0.0f;
                                    }
                                    int i40 = i9 + 0;
                                    int i41 = i9 + 1;
                                    path.rQuadTo(f13, f14, fArr2[i40], fArr2[i41]);
                                    float f34 = f13 + f21;
                                    float f35 = f14 + f22;
                                    f21 += fArr2[i40];
                                    f22 += fArr2[i41];
                                    f18 = f35;
                                    f17 = f34;
                                }
                                f22 += f10;
                            } else {
                                int i42 = i9 + 0;
                                int i43 = i9 + 1;
                                int i44 = i9 + 2;
                                int i45 = i9 + 3;
                                path.rQuadTo(fArr2[i42], fArr2[i43], fArr2[i44], fArr2[i45]);
                                f7 = fArr2[i42] + f21;
                                f8 = fArr2[i43] + f22;
                                f21 += fArr2[i44];
                                f9 = fArr2[i45];
                            }
                            i8 = i9;
                        } else {
                            int i46 = i9 + 2;
                            int i47 = i9 + 3;
                            int i48 = i9 + 4;
                            int i49 = i9 + 5;
                            path.rCubicTo(fArr2[i9 + 0], fArr2[i9 + 1], fArr2[i46], fArr2[i47], fArr2[i48], fArr2[i49]);
                            f7 = fArr2[i46] + f21;
                            f8 = fArr2[i47] + f22;
                            f21 += fArr2[i48];
                            f9 = fArr2[i49];
                        }
                        f22 += f9;
                        f17 = f7;
                        f18 = f8;
                        i8 = i9;
                    } else {
                        int i50 = i9 + 5;
                        int i51 = i9 + 6;
                        i8 = i9;
                        drawArc(path, f21, f22, fArr2[i50] + f21, fArr2[i51] + f22, fArr2[i9 + 0], fArr2[i9 + 1], fArr2[i9 + 2], fArr2[i9 + 3] != 0.0f, fArr2[i9 + 4] != 0.0f);
                        f21 += fArr2[i50];
                        f22 += fArr2[i51];
                    }
                    i9 = i8 + i7;
                    c10 = c8;
                    c9 = c10;
                } else {
                    i8 = i9;
                    int i52 = i8 + 5;
                    int i53 = i8 + 6;
                    drawArc(path, f21, f22, fArr2[i52], fArr2[i53], fArr2[i8 + 0], fArr2[i8 + 1], fArr2[i8 + 2], fArr2[i8 + 3] != 0.0f, fArr2[i8 + 4] != 0.0f);
                    f21 = fArr2[i52];
                    f22 = fArr2[i53];
                }
                f18 = f22;
                f17 = f21;
                i9 = i8 + i7;
                c10 = c8;
                c9 = c10;
            }
            fArr[0] = f21;
            fArr[1] = f22;
            fArr[2] = f17;
            fArr[3] = f18;
            fArr[4] = f23;
            fArr[5] = f24;
        }

        private static void arcToBezier(Path path, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15) {
            double d16 = d9;
            int iCeil = (int) Math.ceil(Math.abs((d15 * 4.0d) / 3.141592653589793d));
            double dCos = Math.cos(d13);
            double dSin = Math.sin(d13);
            double dCos2 = Math.cos(d14);
            double dSin2 = Math.sin(d14);
            double d17 = -d16;
            double d18 = d17 * dCos;
            double d19 = d10 * dSin;
            double d20 = (d18 * dSin2) - (d19 * dCos2);
            double d21 = d17 * dSin;
            double d22 = d10 * dCos;
            double d23 = (dCos2 * d22) + (dSin2 * d21);
            double d24 = d15 / iCeil;
            double d25 = d14;
            double d26 = d23;
            double d27 = d20;
            int i7 = 0;
            double d28 = d11;
            double d29 = d12;
            while (i7 < iCeil) {
                double d30 = d25 + d24;
                double dSin3 = Math.sin(d30);
                double dCos3 = Math.cos(d30);
                double d31 = (((d16 * dCos) * dCos3) + d7) - (d19 * dSin3);
                double d32 = (d22 * dSin3) + (d16 * dSin * dCos3) + d8;
                double d33 = (d18 * dSin3) - (d19 * dCos3);
                double d34 = (dCos3 * d22) + (dSin3 * d21);
                double d35 = d30 - d25;
                double dTan = Math.tan(d35 / 2.0d);
                double dSqrt = ((Math.sqrt(((dTan * 3.0d) * dTan) + 4.0d) - 1.0d) * Math.sin(d35)) / 3.0d;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) ((d27 * dSqrt) + d28), (float) ((d26 * dSqrt) + d29), (float) (d31 - (dSqrt * d33)), (float) (d32 - (dSqrt * d34)), (float) d31, (float) d32);
                i7++;
                d24 = d24;
                dSin = dSin;
                d28 = d31;
                d21 = d21;
                dCos = dCos;
                d25 = d30;
                d26 = d34;
                d27 = d33;
                iCeil = iCeil;
                d29 = d32;
                d16 = d9;
            }
        }

        private static void drawArc(Path path, float f7, float f8, float f9, float f10, float f11, float f12, float f13, boolean z6, boolean z7) {
            double d7;
            double d8;
            double radians = Math.toRadians(f13);
            double dCos = Math.cos(radians);
            double dSin = Math.sin(radians);
            double d9 = f7;
            double d10 = d9 * dCos;
            double d11 = f8;
            double d12 = f11;
            double d13 = ((d11 * dSin) + d10) / d12;
            double d14 = f12;
            double d15 = ((d11 * dCos) + ((-f7) * dSin)) / d14;
            double d16 = f10;
            double d17 = ((d16 * dSin) + (f9 * dCos)) / d12;
            double d18 = ((d16 * dCos) + ((-f9) * dSin)) / d14;
            double d19 = d13 - d17;
            double d20 = d15 - d18;
            double d21 = (d13 + d17) / 2.0d;
            double d22 = (d15 + d18) / 2.0d;
            double d23 = (d20 * d20) + (d19 * d19);
            if (d23 == 0.0d) {
                return;
            }
            double d24 = (1.0d / d23) - 0.25d;
            if (d24 < 0.0d) {
                float fSqrt = (float) (Math.sqrt(d23) / 1.99999d);
                drawArc(path, f7, f8, f9, f10, f11 * fSqrt, f12 * fSqrt, f13, z6, z7);
                return;
            }
            double dSqrt = Math.sqrt(d24);
            double d25 = d19 * dSqrt;
            double d26 = dSqrt * d20;
            if (z6 == z7) {
                d7 = d21 - d26;
                d8 = d22 + d25;
            } else {
                d7 = d21 + d26;
                d8 = d22 - d25;
            }
            double dAtan2 = Math.atan2(d15 - d8, d13 - d7);
            double dAtan22 = Math.atan2(d18 - d8, d17 - d7) - dAtan2;
            if (z7 != (dAtan22 >= 0.0d)) {
                dAtan22 = dAtan22 > 0.0d ? dAtan22 - 6.283185307179586d : dAtan22 + 6.283185307179586d;
            }
            double d27 = d7 * d12;
            double d28 = d8 * d14;
            arcToBezier(path, (d27 * dCos) - (d28 * dSin), (d28 * dCos) + (d27 * dSin), d12, d14, d9, d11, radians, dAtan2, dAtan22);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c7 = 'm';
            for (int i7 = 0; i7 < pathDataNodeArr.length; i7++) {
                addCommand(path, fArr, c7, pathDataNodeArr[i7].mType, pathDataNodeArr[i7].mParams);
                c7 = pathDataNodeArr[i7].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f7) {
            int i7 = 0;
            while (true) {
                float[] fArr = pathDataNode.mParams;
                if (i7 >= fArr.length) {
                    return;
                }
                this.mParams[i7] = (pathDataNode2.mParams[i7] * f7) + ((1.0f - f7) * fArr[i7]);
                i7++;
            }
        }

        public PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            float[] fArr = pathDataNode.mParams;
            this.mParams = PathParser.copyOfRange(fArr, 0, fArr.length);
        }
    }
}
