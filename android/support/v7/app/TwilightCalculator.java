package android.support.v7.app;

/* loaded from: classes.dex */
class TwilightCalculator {
    private static final float ALTIDUTE_CORRECTION_CIVIL_TWILIGHT = -0.10471976f;

    /* renamed from: C1 */
    private static final float f161C1 = 0.0334196f;

    /* renamed from: C2 */
    private static final float f162C2 = 3.49066E-4f;

    /* renamed from: C3 */
    private static final float f163C3 = 5.236E-6f;
    public static final int DAY = 0;
    private static final float DEGREES_TO_RADIANS = 0.017453292f;

    /* renamed from: J0 */
    private static final float f164J0 = 9.0E-4f;
    public static final int NIGHT = 1;
    private static final float OBLIQUITY = 0.4092797f;
    private static final long UTC_2000 = 946728000000L;
    private static TwilightCalculator sInstance;
    public int state;
    public long sunrise;
    public long sunset;

    public static TwilightCalculator getInstance() {
        if (sInstance == null) {
            sInstance = new TwilightCalculator();
        }
        return sInstance;
    }

    public void calculateTwilight(long j7, double d7, double d8) {
        double d9 = (0.01720197f * ((j7 - UTC_2000) / 8.64E7f)) + 6.24006f;
        double dSin = (Math.sin(r4 * 3.0f) * 5.236000106378924E-6d) + (Math.sin(2.0f * r4) * 3.4906598739326E-4d) + (Math.sin(d9) * 0.03341960161924362d) + d9 + 1.796593063d + 3.141592653589793d;
        double dSin2 = (Math.sin(2.0d * dSin) * (-0.0069d)) + (Math.sin(d9) * 0.0053d) + Math.round((r3 - f164J0) - r9) + f164J0 + ((-d8) / 360.0d);
        double dAsin = Math.asin(Math.sin(0.4092797040939331d) * Math.sin(dSin));
        double d10 = 0.01745329238474369d * d7;
        double dSin3 = (Math.sin(-0.10471975803375244d) - (Math.sin(dAsin) * Math.sin(d10))) / (Math.cos(dAsin) * Math.cos(d10));
        if (dSin3 >= 1.0d) {
            this.state = 1;
            this.sunset = -1L;
            this.sunrise = -1L;
        } else {
            if (dSin3 <= -1.0d) {
                this.state = 0;
                this.sunset = -1L;
                this.sunrise = -1L;
                return;
            }
            double dAcos = (float) (Math.acos(dSin3) / 6.283185307179586d);
            this.sunset = Math.round((dSin2 + dAcos) * 8.64E7d) + UTC_2000;
            long jRound = Math.round((dSin2 - dAcos) * 8.64E7d) + UTC_2000;
            this.sunrise = jRound;
            if (jRound >= j7 || this.sunset <= j7) {
                this.state = 1;
            } else {
                this.state = 0;
            }
        }
    }
}
