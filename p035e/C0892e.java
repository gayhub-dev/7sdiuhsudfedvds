package p035e;

import android.hardware.SensorEvent;
import android.support.constraint.C0072a;
import android.util.Log;
import android.util.SparseArray;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import p006a5.AbstractC0030p;
import p006a5.InterfaceC0014a;
import p006a5.InterfaceC0023i;
import p006a5.InterfaceC0026l;
import p006a5.InterfaceC0032r;
import p006a5.InterfaceC0037w;
import p009b.C0413b;
import p113n5.C1550k;
import p119o3.C1587f;
import p162u.C1966j;
import p162u.InterfaceC1960d;
import p190y.InterfaceC2084b;

/* compiled from: MDDirectorCamUpdate.java */
/* renamed from: e.e */
/* loaded from: classes.dex */
public class C0892e implements InterfaceC1960d, InterfaceC0032r, InterfaceC0037w {

    /* renamed from: a */
    public final /* synthetic */ int f1522a;

    /* renamed from: b */
    public Object f1523b;

    public C0892e(int i7) {
        this.f1522a = i7;
        if (i7 == 1) {
            this.f1523b = new SparseArray();
            return;
        }
        if (i7 == 3) {
            this.f1523b = new double[9];
            return;
        }
        C0893f c0893f = new C0893f();
        this.f1523b = c0893f;
        c0893f.f1528e = 0.0f;
        c0893f.f1524a = true;
        c0893f.f1529f = 0.0f;
        c0893f.f1524a = true;
        c0893f.f1525b = 0.0f;
        c0893f.f1524a = true;
        c0893f.f1526c = 0.0f;
        c0893f.f1524a = true;
        c0893f.f1527d = 0.0f;
        c0893f.f1524a = true;
        c0893f.f1531h = 0.0f;
        c0893f.f1530g = true;
        c0893f.f1536m.m1170b(0.0f);
        c0893f.f1535l = true;
        C0893f c0893f2 = (C0893f) this.f1523b;
        c0893f2.f1536m.m1172d(0.0f);
        c0893f2.f1535l = true;
        C0893f c0893f3 = (C0893f) this.f1523b;
        c0893f3.f1536m.m1171c(0.0f);
        c0893f3.f1535l = true;
    }

    /* renamed from: V */
    public static void m781V(C0892e c0892e, C0892e c0892e2, C0892e c0892e3) {
        Object obj = c0892e3.f1523b;
        Object obj2 = c0892e.f1523b;
        double d7 = ((double[]) obj2)[0];
        Object obj3 = c0892e2.f1523b;
        ((double[]) obj)[0] = d7 + ((double[]) obj3)[0];
        ((double[]) obj)[1] = ((double[]) obj2)[1] + ((double[]) obj3)[1];
        ((double[]) obj)[2] = ((double[]) obj2)[2] + ((double[]) obj3)[2];
        ((double[]) obj)[3] = ((double[]) obj2)[3] + ((double[]) obj3)[3];
        ((double[]) obj)[4] = ((double[]) obj2)[4] + ((double[]) obj3)[4];
        ((double[]) obj)[5] = ((double[]) obj2)[5] + ((double[]) obj3)[5];
        ((double[]) obj)[6] = ((double[]) obj2)[6] + ((double[]) obj3)[6];
        ((double[]) obj)[7] = ((double[]) obj2)[7] + ((double[]) obj3)[7];
        ((double[]) obj)[8] = ((double[]) obj2)[8] + ((double[]) obj3)[8];
    }

    /* renamed from: Z */
    public static void m782Z(C0892e c0892e, C0892e c0892e2, C0892e c0892e3) {
        Object obj = c0892e.f1523b;
        double d7 = ((double[]) obj)[0];
        Object obj2 = c0892e2.f1523b;
        c0892e3.m788b0((((double[]) obj)[2] * ((double[]) obj2)[6]) + (((double[]) obj)[1] * ((double[]) obj2)[3]) + (d7 * ((double[]) obj2)[0]), (((double[]) obj)[2] * ((double[]) obj2)[7]) + (((double[]) obj)[1] * ((double[]) obj2)[4]) + (((double[]) obj)[0] * ((double[]) obj2)[1]), (((double[]) obj)[2] * ((double[]) obj2)[8]) + (((double[]) obj)[1] * ((double[]) obj2)[5]) + (((double[]) obj)[0] * ((double[]) obj2)[2]), (((double[]) obj)[5] * ((double[]) obj2)[6]) + (((double[]) obj)[4] * ((double[]) obj2)[3]) + (((double[]) obj)[3] * ((double[]) obj2)[0]), (((double[]) obj)[5] * ((double[]) obj2)[7]) + (((double[]) obj)[4] * ((double[]) obj2)[4]) + (((double[]) obj)[3] * ((double[]) obj2)[1]), (((double[]) obj)[5] * ((double[]) obj2)[8]) + (((double[]) obj)[4] * ((double[]) obj2)[5]) + (((double[]) obj)[3] * ((double[]) obj2)[2]), (((double[]) obj)[8] * ((double[]) obj2)[6]) + (((double[]) obj)[7] * ((double[]) obj2)[3]) + (((double[]) obj)[6] * ((double[]) obj2)[0]), (((double[]) obj)[8] * ((double[]) obj2)[7]) + (((double[]) obj)[7] * ((double[]) obj2)[4]) + (((double[]) obj)[6] * ((double[]) obj2)[1]), (((double[]) obj)[8] * ((double[]) obj2)[8]) + (((double[]) obj)[7] * ((double[]) obj2)[5]) + (((double[]) obj)[6] * ((double[]) obj2)[2]));
    }

    /* renamed from: a0 */
    public static void m783a0(C0892e c0892e, C1587f c1587f, C1587f c1587f2) {
        Object obj = c0892e.f1523b;
        double d7 = ((double[]) obj)[0];
        double d8 = c1587f.f4822a;
        double d9 = ((double[]) obj)[1];
        double d10 = c1587f.f4823b;
        double d11 = (d9 * d10) + (d7 * d8);
        double d12 = ((double[]) obj)[2];
        double d13 = c1587f.f4824c;
        double d14 = (d12 * d13) + d11;
        double d15 = (((double[]) obj)[5] * d13) + (((double[]) obj)[4] * d10) + (((double[]) obj)[3] * d8);
        double d16 = (((double[]) obj)[8] * d13) + (((double[]) obj)[7] * d10) + (((double[]) obj)[6] * d8);
        c1587f2.f4822a = d14;
        c1587f2.f4823b = d15;
        c1587f2.f4824c = d16;
    }

    @Override // p162u.InterfaceC1960d
    /* renamed from: D */
    public boolean mo784D(Object obj, File file, C1966j c1966j) throws Throwable {
        FileOutputStream fileOutputStream;
        InputStream inputStream = (InputStream) obj;
        byte[] bArr = (byte[]) ((InterfaceC2084b) this.f1523b).mo2511d(65536, byte[].class);
        boolean z6 = false;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    while (true) {
                        try {
                            int i7 = inputStream.read(bArr);
                            if (i7 == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, i7);
                        } catch (IOException unused) {
                            fileOutputStream2 = fileOutputStream;
                            Log.isLoggable("StreamEncoder", 3);
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            ((InterfaceC2084b) this.f1523b).mo2510c(bArr, byte[].class);
                            return z6;
                        } catch (Throwable th) {
                            th = th;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                            ((InterfaceC2084b) this.f1523b).mo2510c(bArr, byte[].class);
                            throw th;
                        }
                    }
                    fileOutputStream.close();
                    z6 = true;
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (IOException unused3) {
            }
        } catch (IOException unused4) {
        }
        ((InterfaceC2084b) this.f1523b).mo2510c(bArr, byte[].class);
        return z6;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: E */
    public InterfaceC0014a mo23E() {
        return ((InterfaceC0032r) this.f1523b).mo23E();
    }

    /* renamed from: F */
    public PrintWriter mo41F() {
        return ((InterfaceC0037w) this.f1523b).mo41F();
    }

    /* renamed from: H */
    public void mo42H(int i7) {
        ((InterfaceC0037w) this.f1523b).mo42H(i7);
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: M */
    public void mo43M(String str) {
        ((InterfaceC0037w) this.f1523b).mo43M(str);
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: P */
    public boolean mo44P() {
        return ((InterfaceC0037w) this.f1523b).mo44P();
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: Q */
    public String mo24Q() {
        return ((InterfaceC0032r) this.f1523b).mo24Q();
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: R */
    public InterfaceC0014a mo25R() {
        return ((InterfaceC0032r) this.f1523b).mo25R();
    }

    /* renamed from: W */
    public void m785W(SensorEvent sensorEvent) {
        if (((SparseArray) this.f1523b).get(sensorEvent.sensor.getType()) == null) {
            SparseArray sparseArray = (SparseArray) this.f1523b;
            int type = sensorEvent.sensor.getType();
            float[] fArr = sensorEvent.values;
            sparseArray.put(type, Arrays.copyOf(fArr, fArr.length));
            return;
        }
        float[] fArr2 = (float[]) ((SparseArray) this.f1523b).get(sensorEvent.sensor.getType());
        for (int i7 = 0; i7 < fArr2.length; i7++) {
            fArr2[i7] = (sensorEvent.values[i7] * 0.39999998f) + (fArr2[i7] * 0.6f);
        }
        System.arraycopy(fArr2, 0, sensorEvent.values, 0, fArr2.length);
    }

    /* renamed from: X */
    public double m786X(int i7, int i8) {
        return ((double[]) this.f1523b)[(i7 * 3) + i8];
    }

    /* renamed from: Y */
    public boolean m787Y(C0892e c0892e) {
        double dM786X = (((m786X(2, 1) * m786X(1, 0)) - (m786X(2, 0) * m786X(1, 1))) * m786X(0, 2)) + ((((m786X(2, 2) * m786X(1, 1)) - (m786X(1, 2) * m786X(2, 1))) * m786X(0, 0)) - (((m786X(2, 2) * m786X(1, 0)) - (m786X(2, 0) * m786X(1, 2))) * m786X(0, 1)));
        if (dM786X == 0.0d) {
            return false;
        }
        double d7 = 1.0d / dM786X;
        Object obj = this.f1523b;
        c0892e.m788b0(((((double[]) obj)[4] * ((double[]) obj)[8]) - (((double[]) obj)[7] * ((double[]) obj)[5])) * d7, (-((((double[]) obj)[1] * ((double[]) obj)[8]) - (((double[]) obj)[2] * ((double[]) obj)[7]))) * d7, ((((double[]) obj)[1] * ((double[]) obj)[5]) - (((double[]) obj)[2] * ((double[]) obj)[4])) * d7, (-((((double[]) obj)[3] * ((double[]) obj)[8]) - (((double[]) obj)[5] * ((double[]) obj)[6]))) * d7, ((((double[]) obj)[0] * ((double[]) obj)[8]) - (((double[]) obj)[2] * ((double[]) obj)[6])) * d7, (-((((double[]) obj)[0] * ((double[]) obj)[5]) - (((double[]) obj)[3] * ((double[]) obj)[2]))) * d7, ((((double[]) obj)[3] * ((double[]) obj)[7]) - (((double[]) obj)[6] * ((double[]) obj)[4])) * d7, (-((((double[]) obj)[0] * ((double[]) obj)[7]) - (((double[]) obj)[6] * ((double[]) obj)[1]))) * d7, ((((double[]) obj)[0] * ((double[]) obj)[4]) - (((double[]) obj)[3] * ((double[]) obj)[1])) * d7);
        return true;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: a */
    public Object mo26a(String str) {
        return ((InterfaceC0032r) this.f1523b).mo26a(str);
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: b */
    public void mo27b(String str, Object obj) {
        ((InterfaceC0032r) this.f1523b).mo27b(str, obj);
    }

    /* renamed from: b0 */
    public void m788b0(double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15) {
        Object obj = this.f1523b;
        ((double[]) obj)[0] = d7;
        ((double[]) obj)[1] = d8;
        ((double[]) obj)[2] = d9;
        ((double[]) obj)[3] = d10;
        ((double[]) obj)[4] = d11;
        ((double[]) obj)[5] = d12;
        ((double[]) obj)[6] = d13;
        ((double[]) obj)[7] = d14;
        ((double[]) obj)[8] = d15;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: c */
    public C1550k mo28c() {
        return ((InterfaceC0032r) this.f1523b).mo28c();
    }

    /* renamed from: c0 */
    public void m789c0(int i7, int i8, double d7) {
        ((double[]) this.f1523b)[(i7 * 3) + i8] = d7;
    }

    /* renamed from: d0 */
    public void m790d0(C0892e c0892e) {
        Object obj = this.f1523b;
        Object obj2 = c0892e.f1523b;
        ((double[]) obj)[0] = ((double[]) obj2)[0];
        ((double[]) obj)[1] = ((double[]) obj2)[1];
        ((double[]) obj)[2] = ((double[]) obj2)[2];
        ((double[]) obj)[3] = ((double[]) obj2)[3];
        ((double[]) obj)[4] = ((double[]) obj2)[4];
        ((double[]) obj)[5] = ((double[]) obj2)[5];
        ((double[]) obj)[6] = ((double[]) obj2)[6];
        ((double[]) obj)[7] = ((double[]) obj2)[7];
        ((double[]) obj)[8] = ((double[]) obj2)[8];
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: e */
    public String mo29e() {
        return ((InterfaceC0032r) this.f1523b).mo29e();
    }

    /* renamed from: e0 */
    public void m791e0(int i7, C1587f c1587f) {
        Object obj = this.f1523b;
        ((double[]) obj)[i7] = c1587f.f4822a;
        ((double[]) obj)[i7 + 3] = c1587f.f4823b;
        ((double[]) obj)[i7 + 6] = c1587f.f4824c;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: f */
    public InterfaceC0023i mo30f(String str) {
        return ((InterfaceC0032r) this.f1523b).mo30f(str);
    }

    /* renamed from: f0 */
    public void m792f0() {
        Object obj = this.f1523b;
        ((double[]) obj)[0] = 1.0d;
        ((double[]) obj)[1] = 0.0d;
        ((double[]) obj)[2] = 0.0d;
        ((double[]) obj)[3] = 0.0d;
        ((double[]) obj)[4] = 1.0d;
        ((double[]) obj)[5] = 0.0d;
        ((double[]) obj)[6] = 0.0d;
        ((double[]) obj)[7] = 0.0d;
        ((double[]) obj)[8] = 1.0d;
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: g */
    public String mo31g() {
        return ((InterfaceC0032r) this.f1523b).mo31g();
    }

    /* renamed from: g0 */
    public void m793g0(double d7) {
        Object obj = this.f1523b;
        ((double[]) obj)[0] = d7;
        ((double[]) obj)[4] = d7;
        ((double[]) obj)[8] = d7;
    }

    @Override // p006a5.InterfaceC0032r
    public String getContentType() {
        return ((InterfaceC0032r) this.f1523b).getContentType();
    }

    @Override // p006a5.InterfaceC0032r
    public InterfaceC0026l getServletContext() {
        return ((InterfaceC0032r) this.f1523b).getServletContext();
    }

    /* renamed from: h0 */
    public void m794h0() {
        Object obj = this.f1523b;
        ((double[]) obj)[0] = 0.0d;
        ((double[]) obj)[1] = 0.0d;
        ((double[]) obj)[2] = 0.0d;
        ((double[]) obj)[3] = 0.0d;
        ((double[]) obj)[4] = 0.0d;
        ((double[]) obj)[5] = 0.0d;
        ((double[]) obj)[6] = 0.0d;
        ((double[]) obj)[7] = 0.0d;
        ((double[]) obj)[8] = 0.0d;
    }

    /* renamed from: i */
    public AbstractC0030p mo45i() {
        return ((InterfaceC0037w) this.f1523b).mo45i();
    }

    /* renamed from: i0 */
    public void m795i0(C0892e c0892e) {
        Object obj = this.f1523b;
        double d7 = ((double[]) obj)[1];
        double d8 = ((double[]) obj)[2];
        double d9 = ((double[]) obj)[5];
        Object obj2 = c0892e.f1523b;
        ((double[]) obj2)[0] = ((double[]) obj)[0];
        ((double[]) obj2)[1] = ((double[]) obj)[3];
        ((double[]) obj2)[2] = ((double[]) obj)[6];
        ((double[]) obj2)[3] = d7;
        ((double[]) obj2)[4] = ((double[]) obj)[4];
        ((double[]) obj2)[5] = ((double[]) obj)[7];
        ((double[]) obj2)[6] = d8;
        ((double[]) obj2)[7] = d9;
        ((double[]) obj2)[8] = ((double[]) obj)[8];
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: j */
    public String mo46j() {
        return ((InterfaceC0037w) this.f1523b).mo46j();
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: l */
    public boolean mo32l() {
        return ((InterfaceC0032r) this.f1523b).mo32l();
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: o */
    public void mo47o() {
        ((InterfaceC0037w) this.f1523b).mo47o();
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: p */
    public boolean mo33p() {
        return ((InterfaceC0032r) this.f1523b).mo33p();
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: r */
    public String mo34r(String str) {
        return ((InterfaceC0032r) this.f1523b).mo34r(str);
    }

    public String toString() {
        switch (this.f1522a) {
            case 3:
                StringBuilder sbM112a = C0413b.m112a("{ ");
                for (int i7 = 0; i7 < 9; i7++) {
                    sbM112a.append(Double.toString(((double[]) this.f1523b)[i7]));
                    if (i7 < 8) {
                        sbM112a.append(", ");
                    }
                }
                sbM112a.append(" }");
                return sbM112a.toString();
            case 6:
                return C0072a.m92a(C0413b.m112a("RoleRunAsToken("), (String) this.f1523b, ")");
            default:
                return super.toString();
        }
    }

    @Override // p006a5.InterfaceC0032r
    /* renamed from: u */
    public String mo35u() {
        return ((InterfaceC0032r) this.f1523b).mo35u();
    }

    @Override // p006a5.InterfaceC0037w
    /* renamed from: y */
    public void mo48y(String str) {
        ((InterfaceC0037w) this.f1523b).mo48y(str);
    }

    public C0892e(InterfaceC2084b interfaceC2084b) {
        this.f1522a = 2;
        this.f1523b = interfaceC2084b;
    }

    public C0892e(InterfaceC0032r interfaceC0032r) {
        this.f1522a = 4;
        if (interfaceC0032r != null) {
            this.f1523b = interfaceC0032r;
            return;
        }
        throw new IllegalArgumentException("Request cannot be null");
    }

    public C0892e(InterfaceC0037w interfaceC0037w) {
        this.f1522a = 5;
        if (interfaceC0037w != null) {
            this.f1523b = interfaceC0037w;
            return;
        }
        throw new IllegalArgumentException("Response cannot be null");
    }
}
