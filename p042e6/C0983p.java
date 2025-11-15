package p042e6;

import java.util.Locale;
import p016b6.C0488s;
import p016b6.InterfaceC0489t;
import p058g6.InterfaceC1076n;
import p058g6.InterfaceC1077o;
import p159t3.AbstractC1904c;
import p186x2.C2074b;
import p203z5.C2158b;

/* compiled from: StringConverter.java */
/* renamed from: e6.p */
/* loaded from: classes.dex */
public class C0983p extends AbstractC0968a implements InterfaceC0974g, InterfaceC0977j {

    /* renamed from: a */
    public static final C0983p f1815a = new C0983p();

    @Override // p042e6.InterfaceC0970c
    /* renamed from: a */
    public Class<?> mo935a() {
        return String.class;
    }

    @Override // p042e6.InterfaceC0977j
    /* renamed from: b */
    public void mo939b(InterfaceC0489t interfaceC0489t, Object obj, AbstractC1904c abstractC1904c) {
        String str = (String) obj;
        C2158b c2158bM2467G = C2074b.m2467G();
        interfaceC0489t.clear();
        c2158bM2467G.m2599a();
        int iMo1156c = ((InterfaceC1076n) c2158bM2467G.f6335b).mo1156c(interfaceC0489t, str, 0, (Locale) c2158bM2467G.f6336c);
        if (iMo1156c < str.length()) {
            if (iMo1156c < 0) {
                C0488s c0488sMo295q = interfaceC0489t.mo295q();
                if (c0488sMo295q != ((C0488s) c2158bM2467G.f6337d)) {
                    c2158bM2467G = new C2158b((InterfaceC1077o) c2158bM2467G.f6334a, (InterfaceC1076n) c2158bM2467G.f6335b, (Locale) c2158bM2467G.f6336c, c0488sMo295q);
                }
                c2158bM2467G.m2602d(str);
            }
            throw new IllegalArgumentException("Invalid format: \"" + str + '\"');
        }
    }

    @Override // p042e6.InterfaceC0974g
    /* renamed from: d */
    public long mo938d(Object obj) throws NumberFormatException {
        long j7;
        long j8;
        String str = (String) obj;
        int length = str.length();
        if (length >= 4 && ((str.charAt(0) == 'P' || str.charAt(0) == 'p') && (str.charAt(1) == 'T' || str.charAt(1) == 't'))) {
            int i7 = length - 1;
            if (str.charAt(i7) == 'S' || str.charAt(i7) == 's') {
                String strSubstring = str.substring(2, i7);
                int i8 = 0;
                int i9 = -1;
                for (int i10 = 0; i10 < strSubstring.length(); i10++) {
                    if (strSubstring.charAt(i10) < '0' || strSubstring.charAt(i10) > '9') {
                        if (i10 == 0 && strSubstring.charAt(0) == '-') {
                            i8 = 1;
                        } else {
                            if (i10 <= i8 || strSubstring.charAt(i10) != '.' || i9 != -1) {
                                throw new IllegalArgumentException("Invalid format: \"" + str + '\"');
                            }
                            i9 = i10;
                        }
                    }
                }
                if (i9 > 0) {
                    j7 = Long.parseLong(strSubstring.substring(i8, i9));
                    String strSubstring2 = strSubstring.substring(i9 + 1);
                    if (strSubstring2.length() != 3) {
                        strSubstring2 = (strSubstring2 + "000").substring(0, 3);
                    }
                    j8 = Integer.parseInt(strSubstring2);
                } else {
                    j7 = i8 != 0 ? Long.parseLong(strSubstring.substring(i8, strSubstring.length())) : Long.parseLong(strSubstring);
                    j8 = 0;
                }
                return i8 != 0 ? C2074b.m2463C(C2074b.m2464D(-j7, 1000), -j8) : C2074b.m2463C(C2074b.m2464D(j7, 1000), j8);
            }
        }
        throw new IllegalArgumentException("Invalid format: \"" + str + '\"');
    }
}
