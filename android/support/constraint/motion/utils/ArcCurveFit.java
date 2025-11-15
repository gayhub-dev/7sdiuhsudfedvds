package android.support.constraint.motion.utils;

import java.util.Arrays;

/* loaded from: classes.dex */
class ArcCurveFit extends CurveFit {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    public Arc[] mArcs;
    private final double[] mTime;

    public static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] ourPercent = new double[91];
        public boolean linear;
        public double mArcDistance;
        public double mArcVelocity;
        public double mEllipseA;
        public double mEllipseB;
        public double mEllipseCenterX;
        public double mEllipseCenterY;
        public double[] mLut;
        public double mOneOverDeltaTime;
        public double mTime1;
        public double mTime2;
        public double mTmpCosAngle;
        public double mTmpSinAngle;
        public boolean mVertical;
        public double mX1;
        public double mX2;
        public double mY1;
        public double mY2;

        public Arc(int i7, double d7, double d8, double d9, double d10, double d11, double d12) {
            this.linear = false;
            this.mVertical = i7 == 1;
            this.mTime1 = d7;
            this.mTime2 = d8;
            this.mOneOverDeltaTime = 1.0d / (d8 - d7);
            if (3 == i7) {
                this.linear = true;
            }
            double d13 = d11 - d9;
            double d14 = d12 - d10;
            if (!this.linear && Math.abs(d13) >= EPSILON && Math.abs(d14) >= EPSILON) {
                this.mLut = new double[101];
                boolean z6 = this.mVertical;
                this.mEllipseA = d13 * (z6 ? -1 : 1);
                this.mEllipseB = d14 * (z6 ? 1 : -1);
                this.mEllipseCenterX = z6 ? d11 : d9;
                this.mEllipseCenterY = z6 ? d10 : d12;
                buildTable(d9, d10, d11, d12);
                this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
                return;
            }
            this.linear = true;
            this.mX1 = d9;
            this.mX2 = d11;
            this.mY1 = d10;
            this.mY2 = d12;
            double dHypot = Math.hypot(d14, d13);
            this.mArcDistance = dHypot;
            this.mArcVelocity = dHypot * this.mOneOverDeltaTime;
            double d15 = this.mTime2;
            double d16 = this.mTime1;
            this.mEllipseCenterX = d13 / (d15 - d16);
            this.mEllipseCenterY = d14 / (d15 - d16);
        }

        private void buildTable(double d7, double d8, double d9, double d10) {
            double dHypot;
            double d11 = d9 - d7;
            double d12 = d8 - d10;
            int i7 = 0;
            double d13 = 0.0d;
            double d14 = 0.0d;
            double d15 = 0.0d;
            while (true) {
                if (i7 >= ourPercent.length) {
                    break;
                }
                double d16 = d13;
                double radians = Math.toRadians((i7 * 90.0d) / (r15.length - 1));
                double dSin = Math.sin(radians) * d11;
                double dCos = Math.cos(radians) * d12;
                if (i7 > 0) {
                    dHypot = Math.hypot(dSin - d14, dCos - d15) + d16;
                    ourPercent[i7] = dHypot;
                } else {
                    dHypot = d16;
                }
                i7++;
                d15 = dCos;
                d13 = dHypot;
                d14 = dSin;
            }
            double d17 = d13;
            this.mArcDistance = d17;
            int i8 = 0;
            while (true) {
                double[] dArr = ourPercent;
                if (i8 >= dArr.length) {
                    break;
                }
                dArr[i8] = dArr[i8] / d17;
                i8++;
            }
            int i9 = 0;
            while (true) {
                if (i9 >= this.mLut.length) {
                    return;
                }
                double length = i9 / (r1.length - 1);
                int iBinarySearch = Arrays.binarySearch(ourPercent, length);
                if (iBinarySearch >= 0) {
                    this.mLut[i9] = iBinarySearch / (ourPercent.length - 1);
                } else if (iBinarySearch == -1) {
                    this.mLut[i9] = 0.0d;
                } else {
                    int i10 = -iBinarySearch;
                    int i11 = i10 - 2;
                    double[] dArr2 = ourPercent;
                    this.mLut[i9] = (((length - dArr2[i11]) / (dArr2[i10 - 1] - dArr2[i11])) + i11) / (dArr2.length - 1);
                }
                i9++;
            }
        }

        public double getDX() {
            double d7 = this.mEllipseA * this.mTmpCosAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d7, (-this.mEllipseB) * this.mTmpSinAngle);
            if (this.mVertical) {
                d7 = -d7;
            }
            return d7 * dHypot;
        }

        public double getDY() {
            double d7 = this.mEllipseA * this.mTmpCosAngle;
            double d8 = (-this.mEllipseB) * this.mTmpSinAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d7, d8);
            return this.mVertical ? (-d8) * dHypot : d8 * dHypot;
        }

        public double getLinearDX(double d7) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double d7) {
            return this.mEllipseCenterY;
        }

        public double getLinearX(double d7) {
            double d8 = (d7 - this.mTime1) * this.mOneOverDeltaTime;
            double d9 = this.mX1;
            return ((this.mX2 - d9) * d8) + d9;
        }

        public double getLinearY(double d7) {
            double d8 = (d7 - this.mTime1) * this.mOneOverDeltaTime;
            double d9 = this.mY1;
            return ((this.mY2 - d9) * d8) + d9;
        }

        public double getX() {
            return (this.mEllipseA * this.mTmpSinAngle) + this.mEllipseCenterX;
        }

        public double getY() {
            return (this.mEllipseB * this.mTmpCosAngle) + this.mEllipseCenterY;
        }

        public double lookup(double d7) {
            if (d7 <= 0.0d) {
                return 0.0d;
            }
            if (d7 >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double length = d7 * (dArr.length - 1);
            int i7 = (int) length;
            return ((dArr[i7 + 1] - dArr[i7]) * (length - i7)) + dArr[i7];
        }

        public void setPoint(double d7) {
            double dLookup = lookup((this.mVertical ? this.mTime2 - d7 : d7 - this.mTime1) * this.mOneOverDeltaTime) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(dLookup);
            this.mTmpCosAngle = Math.cos(dLookup);
        }
    }

    public ArcCurveFit(int[] iArr, double[] dArr, double[][] dArr2) {
        this.mTime = dArr;
        this.mArcs = new Arc[dArr.length - 1];
        int i7 = 0;
        int i8 = 1;
        int i9 = 1;
        while (true) {
            Arc[] arcArr = this.mArcs;
            if (i7 >= arcArr.length) {
                return;
            }
            int i10 = iArr[i7];
            if (i10 == 0) {
                i9 = 3;
            } else if (i10 == 1) {
                i8 = 1;
                i9 = 1;
            } else if (i10 == 2) {
                i8 = 2;
                i9 = 2;
            } else if (i10 == 3) {
                i8 = i8 == 1 ? 2 : 1;
                i9 = i8;
            }
            int i11 = i7 + 1;
            arcArr[i7] = new Arc(i9, dArr[i7], dArr[i11], dArr2[i7][0], dArr2[i7][1], dArr2[i11][0], dArr2[i11][1]);
            i7 = i11;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getPos(double d7, double[] dArr) {
        Arc[] arcArr = this.mArcs;
        if (d7 < arcArr[0].mTime1) {
            d7 = arcArr[0].mTime1;
        }
        if (d7 > arcArr[arcArr.length - 1].mTime2) {
            d7 = arcArr[arcArr.length - 1].mTime2;
        }
        int i7 = 0;
        while (true) {
            Arc[] arcArr2 = this.mArcs;
            if (i7 >= arcArr2.length) {
                return;
            }
            if (d7 <= arcArr2[i7].mTime2) {
                if (arcArr2[i7].linear) {
                    dArr[0] = arcArr2[i7].getLinearX(d7);
                    dArr[1] = this.mArcs[i7].getLinearY(d7);
                    return;
                } else {
                    arcArr2[i7].setPoint(d7);
                    dArr[0] = this.mArcs[i7].getX();
                    dArr[1] = this.mArcs[i7].getY();
                    return;
                }
            }
            i7++;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getSlope(double d7, double[] dArr) {
        Arc[] arcArr = this.mArcs;
        if (d7 < arcArr[0].mTime1) {
            d7 = arcArr[0].mTime1;
        } else if (d7 > arcArr[arcArr.length - 1].mTime2) {
            d7 = arcArr[arcArr.length - 1].mTime2;
        }
        int i7 = 0;
        while (true) {
            Arc[] arcArr2 = this.mArcs;
            if (i7 >= arcArr2.length) {
                return;
            }
            if (d7 <= arcArr2[i7].mTime2) {
                if (arcArr2[i7].linear) {
                    dArr[0] = arcArr2[i7].getLinearDX(d7);
                    dArr[1] = this.mArcs[i7].getLinearDY(d7);
                    return;
                } else {
                    arcArr2[i7].setPoint(d7);
                    dArr[0] = this.mArcs[i7].getDX();
                    dArr[1] = this.mArcs[i7].getDY();
                    return;
                }
            }
            i7++;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mTime;
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public void getPos(double d7, float[] fArr) {
        Arc[] arcArr = this.mArcs;
        if (d7 < arcArr[0].mTime1) {
            d7 = arcArr[0].mTime1;
        } else if (d7 > arcArr[arcArr.length - 1].mTime2) {
            d7 = arcArr[arcArr.length - 1].mTime2;
        }
        int i7 = 0;
        while (true) {
            Arc[] arcArr2 = this.mArcs;
            if (i7 >= arcArr2.length) {
                return;
            }
            if (d7 <= arcArr2[i7].mTime2) {
                if (arcArr2[i7].linear) {
                    fArr[0] = (float) arcArr2[i7].getLinearX(d7);
                    fArr[1] = (float) this.mArcs[i7].getLinearY(d7);
                    return;
                } else {
                    arcArr2[i7].setPoint(d7);
                    fArr[0] = (float) this.mArcs[i7].getX();
                    fArr[1] = (float) this.mArcs[i7].getY();
                    return;
                }
            }
            i7++;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double getSlope(double d7, int i7) {
        Arc[] arcArr = this.mArcs;
        int i8 = 0;
        if (d7 < arcArr[0].mTime1) {
            d7 = arcArr[0].mTime1;
        }
        if (d7 > arcArr[arcArr.length - 1].mTime2) {
            d7 = arcArr[arcArr.length - 1].mTime2;
        }
        while (true) {
            Arc[] arcArr2 = this.mArcs;
            if (i8 >= arcArr2.length) {
                return Double.NaN;
            }
            if (d7 <= arcArr2[i8].mTime2) {
                if (arcArr2[i8].linear) {
                    if (i7 == 0) {
                        return arcArr2[i8].getLinearDX(d7);
                    }
                    return arcArr2[i8].getLinearDY(d7);
                }
                arcArr2[i8].setPoint(d7);
                if (i7 == 0) {
                    return this.mArcs[i8].getDX();
                }
                return this.mArcs[i8].getDY();
            }
            i8++;
        }
    }

    @Override // android.support.constraint.motion.utils.CurveFit
    public double getPos(double d7, int i7) {
        Arc[] arcArr = this.mArcs;
        int i8 = 0;
        if (d7 < arcArr[0].mTime1) {
            d7 = arcArr[0].mTime1;
        } else if (d7 > arcArr[arcArr.length - 1].mTime2) {
            d7 = arcArr[arcArr.length - 1].mTime2;
        }
        while (true) {
            Arc[] arcArr2 = this.mArcs;
            if (i8 >= arcArr2.length) {
                return Double.NaN;
            }
            if (d7 <= arcArr2[i8].mTime2) {
                if (arcArr2[i8].linear) {
                    if (i7 == 0) {
                        return arcArr2[i8].getLinearX(d7);
                    }
                    return arcArr2[i8].getLinearY(d7);
                }
                arcArr2[i8].setPoint(d7);
                if (i7 == 0) {
                    return this.mArcs[i8].getX();
                }
                return this.mArcs[i8].getY();
            }
            i8++;
        }
    }
}
