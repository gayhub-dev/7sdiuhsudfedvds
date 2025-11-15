package p161t5;

import java.util.Properties;
import p161t5.AbstractC1930v;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: UrlEncoded.java */
/* renamed from: t5.u */
/* loaded from: classes.dex */
public class C1929u extends ConcurrentMapC1920l implements Cloneable {

    /* renamed from: g */
    public static final InterfaceC2016c f5704g;

    /* renamed from: h */
    public static final String f5705h;

    static {
        Properties properties = C2015b.f5863a;
        f5704g = C2015b.m2349a(C1929u.class.getName());
        f5705h = System.getProperty("org.eclipse.jetty.util.UrlEncoding.charset", "UTF-8");
    }

    public C1929u(C1929u c1929u) {
        super(c1929u);
    }

    /* JADX WARN: Removed duplicated region for block: B:165:0x00c8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00c9 A[Catch: UnsupportedEncodingException -> 0x011a, LOOP:1: B:23:0x0051->B:58:0x00c9, LOOP_END, TryCatch #7 {UnsupportedEncodingException -> 0x011a, blocks: (B:10:0x0026, B:16:0x0034, B:17:0x003c, B:65:0x00f7, B:20:0x0045, B:21:0x004d, B:29:0x0061, B:34:0x006d, B:58:0x00c9, B:48:0x00a2, B:39:0x008a, B:44:0x0095, B:49:0x00ac, B:52:0x00b7, B:54:0x00c3, B:53:0x00be, B:59:0x00d5, B:61:0x00e3, B:63:0x00e9, B:64:0x00f4, B:68:0x0107, B:71:0x010e, B:73:0x0115), top: B:149:0x0026 }] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m2270d(java.lang.String r16, int r17, int r18, java.lang.String r19) throws java.io.UnsupportedEncodingException {
        /*
            Method dump skipped, instructions count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p161t5.C1929u.m2270d(java.lang.String, int, int, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:256:0x011c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010d A[Catch: all -> 0x0148, TryCatch #1 {, blocks: (B:9:0x0025, B:10:0x002d, B:18:0x003d, B:60:0x010d, B:63:0x0114, B:64:0x011b, B:58:0x00fd, B:20:0x0044, B:21:0x004a, B:22:0x0053, B:23:0x0058, B:27:0x0065, B:29:0x006a, B:35:0x007d, B:38:0x0084, B:39:0x00a1, B:31:0x0070, B:33:0x0076, B:26:0x0061, B:43:0x00a7, B:45:0x00ad, B:47:0x00b3, B:49:0x00b9, B:51:0x00bf, B:53:0x00e6, B:55:0x00ec, B:67:0x0123, B:71:0x0130, B:75:0x0146, B:70:0x012c, B:72:0x0137, B:74:0x013d), top: B:247:0x0025 }] */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m2271g(java.io.InputStream r21, p161t5.ConcurrentMapC1920l r22, java.lang.String r23, int r24, int r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1011
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p161t5.C1929u.m2271g(java.io.InputStream, t5.l, java.lang.String, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0094  */
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m2272j(java.lang.String r9, p161t5.ConcurrentMapC1920l r10, java.lang.String r11, int r12) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p161t5.C1929u.m2272j(java.lang.String, t5.l, java.lang.String, int):void");
    }

    /* renamed from: k */
    public static void m2273k(byte[] bArr, int i7, int i8, ConcurrentMapC1920l concurrentMapC1920l, C1931w c1931w) {
        int i9;
        StringBuilder sbM2280g;
        synchronized (concurrentMapC1920l) {
            int i10 = i8 + i7;
            String string = null;
            while (i7 < i10) {
                try {
                    byte b7 = bArr[i7];
                    char c7 = (char) (b7 & 255);
                    if (c7 == '%') {
                        if (i7 + 2 < i10) {
                            int i11 = i7 + 1;
                            if (117 != bArr[i11]) {
                                i9 = i11 + 1;
                                c1931w.m2274a((byte) ((C1927s.m2259b(bArr[i11]) << 4) + C1927s.m2259b(bArr[i9])));
                            } else if (i11 + 4 < i10) {
                                try {
                                    sbM2280g = c1931w.m2280g();
                                    i9 = i11 + 1;
                                } catch (AbstractC1930v.a e7) {
                                    e = e7;
                                    i7 = i11;
                                }
                                try {
                                    int iM2259b = C1927s.m2259b(bArr[i9]) << 12;
                                    int i12 = i9 + 1;
                                    int iM2259b2 = iM2259b + (C1927s.m2259b(bArr[i12]) << 8);
                                    int i13 = i12 + 1;
                                    int iM2259b3 = iM2259b2 + (C1927s.m2259b(bArr[i13]) << 4);
                                    i9 = i13 + 1;
                                    sbM2280g.append(Character.toChars(iM2259b3 + C1927s.m2259b(bArr[i9])));
                                } catch (AbstractC1930v.a e8) {
                                    int i14 = i9;
                                    e = e8;
                                    i7 = i14;
                                    InterfaceC2016c interfaceC2016c = f5704g;
                                    interfaceC2016c.mo2356g(e.toString(), new Object[0]);
                                    interfaceC2016c.mo2359j(e);
                                    i7++;
                                }
                            } else {
                                c1931w.m2280g().append((char) 65533);
                            }
                            i7 = i9;
                        } else {
                            c1931w.m2280g().append((char) 65533);
                        }
                        i7 = i10;
                    } else if (c7 == '&') {
                        String string2 = c1931w.m2281h() == 0 ? "" : c1931w.toString();
                        c1931w.m2282i();
                        if (string != null) {
                            concurrentMapC1920l.m2231b(string, string2);
                        } else if (string2 != null && string2.length() > 0) {
                            concurrentMapC1920l.m2231b(string2, "");
                        }
                        string = null;
                    } else if (c7 == '+') {
                        c1931w.m2274a((byte) 32);
                    } else if (c7 != '=') {
                        try {
                            c1931w.m2274a(b7);
                        } catch (AbstractC1930v.a e9) {
                            e = e9;
                            InterfaceC2016c interfaceC2016c2 = f5704g;
                            interfaceC2016c2.mo2356g(e.toString(), new Object[0]);
                            interfaceC2016c2.mo2359j(e);
                            i7++;
                        }
                    } else if (string != null) {
                        c1931w.m2274a(b7);
                    } else {
                        string = c1931w.toString();
                        c1931w.m2282i();
                    }
                    i7++;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (string != null) {
                String strM2278e = c1931w.m2281h() == 0 ? "" : c1931w.m2278e();
                c1931w.m2282i();
                concurrentMapC1920l.m2231b(string, strM2278e);
            } else if (c1931w.m2281h() > 0) {
                concurrentMapC1920l.m2231b(c1931w.m2278e(), "");
            }
        }
    }

    public Object clone() {
        return new C1929u(this);
    }

    public C1929u() {
        super(6);
    }
}
