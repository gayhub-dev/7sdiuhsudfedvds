package p058g6;

import android.support.constraint.motion.C0079a;
import com.ctvit.network.CtvitHttp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p009b.C0413b;
import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0475f;
import p016b6.C0485p;
import p016b6.InterfaceC0493x;
import p050f6.C1021h;
import p050f6.C1023j;
import p058g6.C1067e;
import p066h6.C1120c;
import p066h6.InterfaceC1122e;
import p159t3.AbstractC1904c;

/* compiled from: DateTimeFormatterBuilder.java */
/* renamed from: g6.c */
/* loaded from: classes.dex */
public class C1065c {

    /* renamed from: a */
    public ArrayList<Object> f2019a = new ArrayList<>();

    /* renamed from: b */
    public Object f2020b;

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$a */
    public static class a implements InterfaceC1074l, InterfaceC1072j {

        /* renamed from: e */
        public final char f2021e;

        public a(char c7) {
            this.f2021e = c7;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return 1;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws IOException {
            appendable.append(this.f2021e);
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return 1;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) throws IOException {
            appendable.append(this.f2021e);
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) {
            char upperCase;
            char upperCase2;
            if (i7 >= charSequence.length()) {
                return ~i7;
            }
            char cCharAt = charSequence.charAt(i7);
            char c7 = this.f2021e;
            return (cCharAt == c7 || (upperCase = Character.toUpperCase(cCharAt)) == (upperCase2 = Character.toUpperCase(c7)) || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2)) ? i7 + 1 : ~i7;
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$b */
    public static class b implements InterfaceC1074l, InterfaceC1072j {

        /* renamed from: e */
        public final InterfaceC1074l[] f2022e;

        /* renamed from: f */
        public final InterfaceC1072j[] f2023f;

        /* renamed from: g */
        public final int f2024g;

        /* renamed from: h */
        public final int f2025h;

        public b(List<Object> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = list.size();
            for (int i7 = 0; i7 < size; i7 += 2) {
                Object obj = list.get(i7);
                if (obj instanceof b) {
                    InterfaceC1074l[] interfaceC1074lArr = ((b) obj).f2022e;
                    if (interfaceC1074lArr != null) {
                        for (InterfaceC1074l interfaceC1074l : interfaceC1074lArr) {
                            arrayList.add(interfaceC1074l);
                        }
                    }
                } else {
                    arrayList.add(obj);
                }
                Object obj2 = list.get(i7 + 1);
                if (obj2 instanceof b) {
                    InterfaceC1072j[] interfaceC1072jArr = ((b) obj2).f2023f;
                    if (interfaceC1072jArr != null) {
                        for (InterfaceC1072j interfaceC1072j : interfaceC1072jArr) {
                            arrayList2.add(interfaceC1072j);
                        }
                    }
                } else {
                    arrayList2.add(obj2);
                }
            }
            if (arrayList.contains(null) || arrayList.isEmpty()) {
                this.f2022e = null;
                this.f2024g = 0;
            } else {
                int size2 = arrayList.size();
                this.f2022e = new InterfaceC1074l[size2];
                int iMo1095c = 0;
                for (int i8 = 0; i8 < size2; i8++) {
                    InterfaceC1074l interfaceC1074l2 = (InterfaceC1074l) arrayList.get(i8);
                    iMo1095c += interfaceC1074l2.mo1095c();
                    this.f2022e[i8] = interfaceC1074l2;
                }
                this.f2024g = iMo1095c;
            }
            if (arrayList2.contains(null) || arrayList2.isEmpty()) {
                this.f2023f = null;
                this.f2025h = 0;
                return;
            }
            int size3 = arrayList2.size();
            this.f2023f = new InterfaceC1072j[size3];
            int iMo1093a = 0;
            for (int i9 = 0; i9 < size3; i9++) {
                InterfaceC1072j interfaceC1072j2 = (InterfaceC1072j) arrayList2.get(i9);
                iMo1093a += interfaceC1072j2.mo1093a();
                this.f2023f[i9] = interfaceC1072j2;
            }
            this.f2025h = iMo1093a;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return this.f2025h;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) {
            InterfaceC1074l[] interfaceC1074lArr = this.f2022e;
            if (interfaceC1074lArr == null) {
                throw new UnsupportedOperationException();
            }
            Locale locale2 = locale == null ? Locale.getDefault() : locale;
            for (InterfaceC1074l interfaceC1074l : interfaceC1074lArr) {
                interfaceC1074l.mo1094b(appendable, j7, abstractC1904c, i7, abstractC0475f, locale2);
            }
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return this.f2024g;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) {
            InterfaceC1074l[] interfaceC1074lArr = this.f2022e;
            if (interfaceC1074lArr == null) {
                throw new UnsupportedOperationException();
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            for (InterfaceC1074l interfaceC1074l : interfaceC1074lArr) {
                interfaceC1074l.mo1096e(appendable, interfaceC0493x, locale);
            }
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) {
            InterfaceC1072j[] interfaceC1072jArr = this.f2023f;
            if (interfaceC1072jArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = interfaceC1072jArr.length;
            for (int i8 = 0; i8 < length && i7 >= 0; i8++) {
                i7 = interfaceC1072jArr[i8].mo1097f(c1067e, charSequence, i7);
            }
            return i7;
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$c */
    public static class c extends g {
        public c(AbstractC0472c abstractC0472c, int i7, boolean z6) {
            super(abstractC0472c, i7, z6, i7);
        }

        @Override // p058g6.C1065c.f, p058g6.InterfaceC1072j
        /* renamed from: f */
        public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) throws NumberFormatException {
            int i8;
            char cCharAt;
            int iMo1097f = super.mo1097f(c1067e, charSequence, i7);
            if (iMo1097f < 0 || iMo1097f == (i8 = this.f2032f + i7)) {
                return iMo1097f;
            }
            if (this.f2033g && ((cCharAt = charSequence.charAt(i7)) == '-' || cCharAt == '+')) {
                i8++;
            }
            return iMo1097f > i8 ? ~(i8 + 1) : iMo1097f < i8 ? ~iMo1097f : iMo1097f;
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$d */
    public static class d implements InterfaceC1074l, InterfaceC1072j {

        /* renamed from: e */
        public final AbstractC0472c f2026e;

        /* renamed from: f */
        public int f2027f;

        /* renamed from: g */
        public int f2028g;

        public d(AbstractC0472c abstractC0472c, int i7, int i8) {
            this.f2026e = abstractC0472c;
            i8 = i8 > 18 ? 18 : i8;
            this.f2027f = i7;
            this.f2028g = i8;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return this.f2028g;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws IOException {
            m1098d(appendable, j7, abstractC1904c);
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return this.f2028g;
        }

        /* renamed from: d */
        public void m1098d(Appendable appendable, long j7, AbstractC1904c abstractC1904c) throws IOException {
            long j8;
            AbstractC0471b abstractC0471bMo223b = this.f2026e.mo223b(abstractC1904c);
            int i7 = this.f2027f;
            try {
                long jMo217t = abstractC0471bMo223b.mo217t(j7);
                if (jMo217t == 0) {
                    while (true) {
                        i7--;
                        if (i7 < 0) {
                            return;
                        } else {
                            appendable.append('0');
                        }
                    }
                } else {
                    long jMo256h = abstractC0471bMo223b.mo206i().mo256h();
                    int i8 = this.f2028g;
                    while (true) {
                        switch (i8) {
                            case 1:
                                j8 = 10;
                                break;
                            case 2:
                                j8 = 100;
                                break;
                            case 3:
                                j8 = 1000;
                                break;
                            case 4:
                                j8 = 10000;
                                break;
                            case 5:
                                j8 = 100000;
                                break;
                            case 6:
                                j8 = 1000000;
                                break;
                            case 7:
                                j8 = 10000000;
                                break;
                            case 8:
                                j8 = 100000000;
                                break;
                            case 9:
                                j8 = 1000000000;
                                break;
                            case 10:
                                j8 = 10000000000L;
                                break;
                            case 11:
                                j8 = 100000000000L;
                                break;
                            case 12:
                                j8 = 1000000000000L;
                                break;
                            case 13:
                                j8 = 10000000000000L;
                                break;
                            case 14:
                                j8 = 100000000000000L;
                                break;
                            case 15:
                                j8 = 1000000000000000L;
                                break;
                            case 16:
                                j8 = 10000000000000000L;
                                break;
                            case 17:
                                j8 = 100000000000000000L;
                                break;
                            case 18:
                                j8 = 1000000000000000000L;
                                break;
                            default:
                                j8 = 1;
                                break;
                        }
                        if ((jMo256h * j8) / j8 == jMo256h) {
                            long j9 = (jMo217t * j8) / jMo256h;
                            long[] jArr = {j9, i8};
                            long j10 = jArr[0];
                            int i9 = (int) jArr[1];
                            String string = (2147483647L & j10) == j10 ? Integer.toString((int) j10) : Long.toString(j10);
                            int length = string.length();
                            while (length < i9) {
                                appendable.append('0');
                                i7--;
                                i9--;
                            }
                            if (i7 < i9) {
                                while (i7 < i9 && length > 1 && string.charAt(length - 1) == '0') {
                                    i9--;
                                    length--;
                                }
                                if (length < string.length()) {
                                    for (int i10 = 0; i10 < length; i10++) {
                                        appendable.append(string.charAt(i10));
                                    }
                                    return;
                                }
                            }
                            appendable.append(string);
                            return;
                        }
                        i8--;
                    }
                }
            } catch (RuntimeException unused) {
                C1065c.m1068q(appendable, i7);
            }
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) throws IOException {
            m1098d(appendable, interfaceC0493x.mo265j().mo721E(interfaceC0493x, 0L), interfaceC0493x.mo265j());
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) {
            AbstractC0471b abstractC0471bMo223b = this.f2026e.mo223b(c1067e.f2055a);
            int iMin = Math.min(this.f2028g, charSequence.length() - i7);
            long j7 = 0;
            long jMo256h = abstractC0471bMo223b.mo206i().mo256h() * 10;
            int i8 = 0;
            while (i8 < iMin) {
                char cCharAt = charSequence.charAt(i7 + i8);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i8++;
                jMo256h /= 10;
                j7 += (cCharAt - '0') * jMo256h;
            }
            long j8 = j7 / 10;
            if (i8 != 0 && j8 <= 2147483647L) {
                AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
                C1023j c1023j = new C1023j(AbstractC0472c.f287B, C1021h.f1923e, abstractC0471bMo223b.mo206i());
                C1067e.a aVarM1103c = c1067e.m1103c();
                aVarM1103c.f2066e = c1023j;
                aVarM1103c.f2067f = (int) j8;
                aVarM1103c.f2068g = null;
                aVarM1103c.f2069h = null;
                return i7 + i8;
            }
            return ~i7;
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$e */
    public static class e implements InterfaceC1072j {

        /* renamed from: e */
        public final InterfaceC1072j[] f2029e;

        /* renamed from: f */
        public final int f2030f;

        public e(InterfaceC1072j[] interfaceC1072jArr) {
            int iMo1093a;
            this.f2029e = interfaceC1072jArr;
            int length = interfaceC1072jArr.length;
            int i7 = 0;
            while (true) {
                length--;
                if (length < 0) {
                    this.f2030f = i7;
                    return;
                }
                InterfaceC1072j interfaceC1072j = interfaceC1072jArr[length];
                if (interfaceC1072j != null && (iMo1093a = interfaceC1072j.mo1093a()) > i7) {
                    i7 = iMo1093a;
                }
            }
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return this.f2030f;
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0053, code lost:
        
            if (r6 > r12) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0055, code lost:
        
            if (r6 != r12) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0057, code lost:
        
            if (r4 == false) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x005b, code lost:
        
            return ~r7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x005c, code lost:
        
            if (r3 == null) goto L40;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x005e, code lost:
        
            r10.m1104d(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0061, code lost:
        
            return r6;
         */
        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int mo1097f(p058g6.C1067e r10, java.lang.CharSequence r11, int r12) {
            /*
                r9 = this;
                g6.j[] r0 = r9.f2029e
                int r1 = r0.length
                java.lang.Object r2 = r10.f2065k
                if (r2 != 0) goto Le
                g6.e$b r2 = new g6.e$b
                r2.<init>()
                r10.f2065k = r2
            Le:
                java.lang.Object r2 = r10.f2065k
                r3 = 0
                r4 = 0
                r6 = r12
                r7 = r6
                r5 = 0
            L15:
                if (r5 >= r1) goto L53
                r8 = r0[r5]
                if (r8 != 0) goto L20
                if (r6 > r12) goto L1e
                return r12
            L1e:
                r4 = 1
                goto L53
            L20:
                int r8 = r8.mo1097f(r10, r11, r12)
                if (r8 < r12) goto L47
                if (r8 <= r6) goto L4d
                int r3 = r11.length()
                if (r8 >= r3) goto L46
                int r3 = r5 + 1
                if (r3 >= r1) goto L46
                r3 = r0[r3]
                if (r3 != 0) goto L37
                goto L46
            L37:
                java.lang.Object r3 = r10.f2065k
                if (r3 != 0) goto L42
                g6.e$b r3 = new g6.e$b
                r3.<init>()
                r10.f2065k = r3
            L42:
                java.lang.Object r3 = r10.f2065k
                r6 = r8
                goto L4d
            L46:
                return r8
            L47:
                if (r8 >= 0) goto L4d
                int r8 = ~r8
                if (r8 <= r7) goto L4d
                r7 = r8
            L4d:
                r10.m1104d(r2)
                int r5 = r5 + 1
                goto L15
            L53:
                if (r6 > r12) goto L5c
                if (r6 != r12) goto L5a
                if (r4 == 0) goto L5a
                goto L5c
            L5a:
                int r10 = ~r7
                return r10
            L5c:
                if (r3 == 0) goto L61
                r10.m1104d(r3)
            L61:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: p058g6.C1065c.e.mo1097f(g6.e, java.lang.CharSequence, int):int");
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$f */
    public static abstract class f implements InterfaceC1074l, InterfaceC1072j {

        /* renamed from: e */
        public final AbstractC0472c f2031e;

        /* renamed from: f */
        public final int f2032f;

        /* renamed from: g */
        public final boolean f2033g;

        public f(AbstractC0472c abstractC0472c, int i7, boolean z6) {
            this.f2031e = abstractC0472c;
            this.f2032f = i7;
            this.f2033g = z6;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return this.f2032f;
        }

        /* renamed from: f */
        public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) throws NumberFormatException {
            int i8;
            int i9;
            char cCharAt;
            int iMin = Math.min(this.f2032f, charSequence.length() - i7);
            int i10 = 0;
            boolean z6 = false;
            boolean z7 = false;
            while (i10 < iMin) {
                int i11 = i7 + i10;
                char cCharAt2 = charSequence.charAt(i11);
                if (i10 == 0 && ((cCharAt2 == '-' || cCharAt2 == '+') && this.f2033g)) {
                    boolean z8 = cCharAt2 == '-';
                    boolean z9 = cCharAt2 == '+';
                    int i12 = i10 + 1;
                    if (i12 >= iMin || (cCharAt = charSequence.charAt(i11 + 1)) < '0' || cCharAt > '9') {
                        boolean z10 = z8;
                        z7 = z9;
                        z6 = z10;
                        break;
                    }
                    iMin = Math.min(iMin + 1, charSequence.length() - i7);
                    i10 = i12;
                    boolean z11 = z8;
                    z7 = z9;
                    z6 = z11;
                } else {
                    if (cCharAt2 < '0' || cCharAt2 > '9') {
                        break;
                    }
                    i10++;
                }
            }
            if (i10 == 0) {
                return ~i7;
            }
            if (i10 < 9) {
                int i13 = (z6 || z7) ? i7 + 1 : i7;
                int i14 = i13 + 1;
                try {
                    int iCharAt = charSequence.charAt(i13) - '0';
                    i8 = i7 + i10;
                    while (i14 < i8) {
                        int i15 = (iCharAt << 3) + (iCharAt << 1);
                        int i16 = i14 + 1;
                        int iCharAt2 = (charSequence.charAt(i14) + i15) - 48;
                        i14 = i16;
                        iCharAt = iCharAt2;
                    }
                    i9 = z6 ? -iCharAt : iCharAt;
                } catch (StringIndexOutOfBoundsException unused) {
                    return ~i7;
                }
            } else if (z7) {
                i8 = i7 + i10;
                i9 = Integer.parseInt(charSequence.subSequence(i7 + 1, i8).toString());
            } else {
                int i17 = i7 + i10;
                i9 = Integer.parseInt(charSequence.subSequence(i7, i17).toString());
                i8 = i17;
            }
            c1067e.m1105e(this.f2031e, i9);
            return i8;
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$g */
    public static class g extends f {

        /* renamed from: h */
        public final int f2034h;

        public g(AbstractC0472c abstractC0472c, int i7, boolean z6, int i8) {
            super(abstractC0472c, i7, z6);
            this.f2034h = i8;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws IOException {
            try {
                C1070h.m1110a(appendable, this.f2031e.mo223b(abstractC1904c).mo199b(j7), this.f2034h);
            } catch (RuntimeException unused) {
                C1065c.m1068q(appendable, this.f2034h);
            }
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return this.f2032f;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) throws IOException {
            if (!interfaceC0493x.mo267x(this.f2031e)) {
                C1065c.m1068q(appendable, this.f2034h);
                return;
            }
            try {
                C1070h.m1110a(appendable, interfaceC0493x.mo263I(this.f2031e), this.f2034h);
            } catch (RuntimeException unused) {
                C1065c.m1068q(appendable, this.f2034h);
            }
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$h */
    public static class h implements InterfaceC1074l, InterfaceC1072j {

        /* renamed from: e */
        public final String f2035e;

        public h(String str) {
            this.f2035e = str;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return this.f2035e.length();
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws IOException {
            appendable.append(this.f2035e);
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return this.f2035e.length();
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) throws IOException {
            appendable.append(this.f2035e);
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) {
            return C1065c.m1070u(charSequence, i7, this.f2035e) ? this.f2035e.length() + i7 : ~i7;
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$i */
    public static class i implements InterfaceC1074l, InterfaceC1072j {

        /* renamed from: g */
        public static Map<Locale, Map<AbstractC0472c, Object[]>> f2036g = new ConcurrentHashMap();

        /* renamed from: e */
        public final AbstractC0472c f2037e;

        /* renamed from: f */
        public final boolean f2038f;

        public i(AbstractC0472c abstractC0472c, boolean z6) {
            this.f2037e = abstractC0472c;
            this.f2038f = z6;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return mo1095c();
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws IOException {
            try {
                AbstractC0471b abstractC0471bMo223b = this.f2037e.mo223b(abstractC1904c);
                appendable.append(this.f2038f ? abstractC0471bMo223b.mo201d(j7, locale) : abstractC0471bMo223b.mo204g(j7, locale));
            } catch (RuntimeException unused) {
                appendable.append((char) 65533);
            }
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return this.f2038f ? 6 : 20;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) throws IOException {
            String strMo202e;
            try {
                if (interfaceC0493x.mo267x(this.f2037e)) {
                    AbstractC0471b abstractC0471bMo223b = this.f2037e.mo223b(interfaceC0493x.mo265j());
                    strMo202e = this.f2038f ? abstractC0471bMo223b.mo202e(interfaceC0493x, locale) : abstractC0471bMo223b.mo205h(interfaceC0493x, locale);
                } else {
                    strMo202e = "�";
                }
                appendable.append(strMo202e);
            } catch (RuntimeException unused) {
                appendable.append((char) 65533);
            }
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) {
            int iIntValue;
            Map map;
            Locale locale = c1067e.f2057c;
            Map concurrentHashMap = (Map) ((ConcurrentHashMap) f2036g).get(locale);
            if (concurrentHashMap == null) {
                concurrentHashMap = new ConcurrentHashMap();
                ((ConcurrentHashMap) f2036g).put(locale, concurrentHashMap);
            }
            Object[] objArr = (Object[]) concurrentHashMap.get(this.f2037e);
            if (objArr == null) {
                ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap(32);
                C0485p c0485p = new C0485p(0L, AbstractC0475f.f314f);
                AbstractC0472c abstractC0472c = this.f2037e;
                if (abstractC0472c == null) {
                    throw new IllegalArgumentException("The DateTimeFieldType must not be null");
                }
                AbstractC0471b abstractC0471bMo223b = abstractC0472c.mo223b(c0485p.f398f);
                if (!abstractC0471bMo223b.mo216s()) {
                    throw new IllegalArgumentException("Field '" + abstractC0472c + "' is not supported");
                }
                C0485p.a aVar = new C0485p.a(c0485p, abstractC0471bMo223b);
                int iMo210m = aVar.mo273e().mo210m();
                int iMo209l = aVar.mo273e().mo209l();
                if (iMo209l - iMo210m > 32) {
                    return ~i7;
                }
                iIntValue = aVar.mo273e().mo208k(locale);
                while (iMo210m <= iMo209l) {
                    C0485p c0485p2 = aVar.f348e;
                    c0485p2.f397e = aVar.f349f.mo219v(c0485p2.f397e, iMo210m);
                    String strM1028b = aVar.m1028b(locale);
                    Boolean bool = Boolean.TRUE;
                    concurrentHashMap2.put(strM1028b, bool);
                    concurrentHashMap2.put(aVar.m1028b(locale).toLowerCase(locale), bool);
                    concurrentHashMap2.put(aVar.m1028b(locale).toUpperCase(locale), bool);
                    concurrentHashMap2.put(aVar.m1029c(locale), bool);
                    concurrentHashMap2.put(aVar.m1029c(locale).toLowerCase(locale), bool);
                    concurrentHashMap2.put(aVar.m1029c(locale).toUpperCase(locale), bool);
                    iMo210m++;
                }
                if ("en".equals(locale.getLanguage())) {
                    AbstractC0472c abstractC0472c2 = this.f2037e;
                    AbstractC0472c abstractC0472c3 = AbstractC0472c.f288f;
                    if (abstractC0472c2 == AbstractC0472c.f288f) {
                        Boolean bool2 = Boolean.TRUE;
                        concurrentHashMap2.put("BCE", bool2);
                        concurrentHashMap2.put("bce", bool2);
                        concurrentHashMap2.put("CE", bool2);
                        concurrentHashMap2.put("ce", bool2);
                        iIntValue = 3;
                    }
                }
                concurrentHashMap.put(this.f2037e, new Object[]{concurrentHashMap2, Integer.valueOf(iIntValue)});
                map = concurrentHashMap2;
            } else {
                Map map2 = (Map) objArr[0];
                iIntValue = ((Integer) objArr[1]).intValue();
                map = map2;
            }
            for (int iMin = Math.min(charSequence.length(), i7 + iIntValue); iMin > i7; iMin--) {
                String string = charSequence.subSequence(i7, iMin).toString();
                if (map.containsKey(string)) {
                    AbstractC0472c abstractC0472c4 = this.f2037e;
                    C1067e.a aVarM1103c = c1067e.m1103c();
                    aVarM1103c.f2066e = abstractC0472c4.mo223b(c1067e.f2055a);
                    aVarM1103c.f2067f = 0;
                    aVarM1103c.f2068g = string;
                    aVarM1103c.f2069h = locale;
                    return iMin;
                }
            }
            return ~i7;
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$j */
    public enum j implements InterfaceC1074l, InterfaceC1072j {
        INSTANCE;


        /* renamed from: f */
        public static final Map<String, List<String>> f2040f;

        /* renamed from: g */
        public static final List<String> f2041g = new ArrayList();

        /* renamed from: h */
        public static final int f2042h;

        /* renamed from: i */
        public static final int f2043i;

        static {
            ArrayList arrayList = new ArrayList(AbstractC0475f.m237m().mo1283b());
            Collections.sort(arrayList);
            f2040f = new HashMap();
            Iterator it = arrayList.iterator();
            int iMax = 0;
            int iMax2 = 0;
            while (it.hasNext()) {
                String str = (String) it.next();
                int iIndexOf = str.indexOf(47);
                if (iIndexOf >= 0) {
                    iIndexOf = iIndexOf < str.length() ? iIndexOf + 1 : iIndexOf;
                    iMax2 = Math.max(iMax2, iIndexOf);
                    String strSubstring = str.substring(0, iIndexOf + 1);
                    String strSubstring2 = str.substring(iIndexOf);
                    HashMap map = (HashMap) f2040f;
                    if (!map.containsKey(strSubstring)) {
                        map.put(strSubstring, new ArrayList());
                    }
                    ((List) map.get(strSubstring)).add(strSubstring2);
                } else {
                    ((ArrayList) f2041g).add(str);
                }
                iMax = Math.max(iMax, str.length());
            }
            f2042h = iMax;
            f2043i = iMax2;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return f2042h;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws IOException {
            appendable.append(abstractC0475f != null ? abstractC0475f.f318e : "");
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return f2042h;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) {
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) {
            String string;
            int length;
            String string2;
            List<String> list = f2041g;
            int length2 = charSequence.length();
            int iMin = Math.min(length2, f2043i + i7);
            int i8 = i7;
            while (true) {
                if (i8 >= iMin) {
                    string = "";
                    length = i7;
                    break;
                }
                if (charSequence.charAt(i8) == '/') {
                    int i9 = i8 + 1;
                    string = charSequence.subSequence(i7, i9).toString();
                    length = string.length() + i7;
                    if (i8 < length2) {
                        StringBuilder sbM112a = C0413b.m112a(string);
                        sbM112a.append(charSequence.charAt(i9));
                        string2 = sbM112a.toString();
                    } else {
                        string2 = string;
                    }
                    list = (List) ((HashMap) f2040f).get(string2);
                    if (list == null) {
                        return ~i7;
                    }
                } else {
                    i8++;
                }
            }
            String str = null;
            for (int i10 = 0; i10 < list.size(); i10++) {
                String str2 = list.get(i10);
                if (C1065c.m1069t(charSequence, length, str2) && (str == null || str2.length() > str.length())) {
                    str = str2;
                }
            }
            if (str == null) {
                return ~i7;
            }
            AbstractC0475f abstractC0475fM232d = AbstractC0475f.m232d(string + str);
            c1067e.f2065k = null;
            c1067e.f2059e = abstractC0475fM232d;
            return str.length() + length;
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$k */
    public static class k implements InterfaceC1074l, InterfaceC1072j {

        /* renamed from: e */
        public final Map<String, AbstractC0475f> f2045e;

        /* renamed from: f */
        public final int f2046f;

        public k(int i7, Map<String, AbstractC0475f> map) {
            this.f2046f = i7;
            this.f2045e = map;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return this.f2046f == 1 ? 4 : 20;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws ClassNotFoundException, IOException {
            String strM239s;
            long j8 = j7 - i7;
            String str = "";
            if (abstractC0475f != null) {
                int i8 = this.f2046f;
                String strMo1277a = null;
                if (i8 == 0) {
                    if (locale == null) {
                        locale = Locale.getDefault();
                    }
                    String strMo244i = abstractC0475f.mo244i(j8);
                    if (strMo244i == null) {
                        strM239s = abstractC0475f.f318e;
                    } else {
                        InterfaceC1122e interfaceC1122eM236j = AbstractC0475f.m236j();
                        if (interfaceC1122eM236j instanceof C1120c) {
                            String[] strArrM1281e = ((C1120c) interfaceC1122eM236j).m1281e(locale, abstractC0475f.f318e, strMo244i, abstractC0475f.mo245k(j8) == abstractC0475f.mo247n(j8));
                            if (strArrM1281e != null) {
                                strMo1277a = strArrM1281e[1];
                            }
                        } else {
                            strMo1277a = interfaceC1122eM236j.mo1277a(locale, abstractC0475f.f318e, strMo244i);
                        }
                        if (strMo1277a == null) {
                            strM239s = AbstractC0475f.m239s(abstractC0475f.mo245k(j8));
                        }
                        str = strMo1277a;
                    }
                    str = strM239s;
                } else if (i8 == 1) {
                    if (locale == null) {
                        locale = Locale.getDefault();
                    }
                    String strMo244i2 = abstractC0475f.mo244i(j8);
                    if (strMo244i2 == null) {
                        strM239s = abstractC0475f.f318e;
                    } else {
                        InterfaceC1122e interfaceC1122eM236j2 = AbstractC0475f.m236j();
                        if (interfaceC1122eM236j2 instanceof C1120c) {
                            String[] strArrM1281e2 = ((C1120c) interfaceC1122eM236j2).m1281e(locale, abstractC0475f.f318e, strMo244i2, abstractC0475f.mo245k(j8) == abstractC0475f.mo247n(j8));
                            if (strArrM1281e2 != null) {
                                strMo1277a = strArrM1281e2[0];
                            }
                        } else {
                            strMo1277a = interfaceC1122eM236j2.mo1278b(locale, abstractC0475f.f318e, strMo244i2);
                        }
                        if (strMo1277a == null) {
                            strM239s = AbstractC0475f.m239s(abstractC0475f.mo245k(j8));
                        }
                        str = strMo1277a;
                    }
                    str = strM239s;
                }
            }
            appendable.append(str);
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return this.f2046f == 1 ? 4 : 20;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) {
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0067 A[PHI: r2
          0x0067: PHI (r2v4 java.util.Map<java.lang.String, b6.f>) = (r2v3 java.util.Map<java.lang.String, b6.f>), (r2v6 java.util.Map<java.lang.String, b6.f>) binds: [B:6:0x000e, B:8:0x005e] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int mo1097f(p058g6.C1067e r8, java.lang.CharSequence r9, int r10) {
            /*
                r7 = this;
                java.util.Map<java.lang.String, b6.f> r0 = r7.f2045e
                r1 = 0
                if (r0 == 0) goto L6
                goto L68
            L6:
                java.util.concurrent.atomic.AtomicReference<java.util.Map<java.lang.String, b6.f>> r0 = p016b6.C0473d.f313a
                java.lang.Object r2 = r0.get()
                java.util.Map r2 = (java.util.Map) r2
                if (r2 != 0) goto L67
                java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
                r2.<init>()
                b6.f r3 = p016b6.AbstractC0475f.f314f
                java.lang.String r4 = "UT"
                r2.put(r4, r3)
                java.lang.String r4 = "UTC"
                r2.put(r4, r3)
                java.lang.String r4 = "GMT"
                r2.put(r4, r3)
                java.lang.String r3 = "America/New_York"
                java.lang.String r4 = "EST"
                p016b6.C0473d.m227c(r2, r4, r3)
                java.lang.String r4 = "EDT"
                p016b6.C0473d.m227c(r2, r4, r3)
                java.lang.String r3 = "America/Chicago"
                java.lang.String r4 = "CST"
                p016b6.C0473d.m227c(r2, r4, r3)
                java.lang.String r4 = "CDT"
                p016b6.C0473d.m227c(r2, r4, r3)
                java.lang.String r3 = "America/Denver"
                java.lang.String r4 = "MST"
                p016b6.C0473d.m227c(r2, r4, r3)
                java.lang.String r4 = "MDT"
                p016b6.C0473d.m227c(r2, r4, r3)
                java.lang.String r3 = "America/Los_Angeles"
                java.lang.String r4 = "PST"
                p016b6.C0473d.m227c(r2, r4, r3)
                java.lang.String r4 = "PDT"
                p016b6.C0473d.m227c(r2, r4, r3)
                java.util.Map r2 = java.util.Collections.unmodifiableMap(r2)
                boolean r3 = r0.compareAndSet(r1, r2)
                if (r3 != 0) goto L67
                java.lang.Object r0 = r0.get()
                java.util.Map r0 = (java.util.Map) r0
                goto L68
            L67:
                r0 = r2
            L68:
                java.util.Set r2 = r0.keySet()
                java.util.Iterator r2 = r2.iterator()
                r3 = r1
            L71:
                boolean r4 = r2.hasNext()
                if (r4 == 0) goto L91
                java.lang.Object r4 = r2.next()
                java.lang.String r4 = (java.lang.String) r4
                boolean r5 = p058g6.C1065c.m1069t(r9, r10, r4)
                if (r5 == 0) goto L71
                if (r3 == 0) goto L8f
                int r5 = r4.length()
                int r6 = r3.length()
                if (r5 <= r6) goto L71
            L8f:
                r3 = r4
                goto L71
            L91:
                if (r3 == 0) goto La3
                java.lang.Object r9 = r0.get(r3)
                b6.f r9 = (p016b6.AbstractC0475f) r9
                r8.f2065k = r1
                r8.f2059e = r9
                int r8 = r3.length()
                int r8 = r8 + r10
                return r8
            La3:
                int r8 = ~r10
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: p058g6.C1065c.k.mo1097f(g6.e, java.lang.CharSequence, int):int");
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$l */
    public static class l implements InterfaceC1074l, InterfaceC1072j {

        /* renamed from: e */
        public final String f2047e;

        /* renamed from: f */
        public final String f2048f;

        /* renamed from: g */
        public final boolean f2049g;

        /* renamed from: h */
        public final int f2050h;

        /* renamed from: i */
        public final int f2051i;

        public l(String str, String str2, boolean z6, int i7, int i8) {
            this.f2047e = str;
            this.f2048f = str2;
            this.f2049g = z6;
            if (i7 <= 0 || i8 < i7) {
                throw new IllegalArgumentException();
            }
            if (i7 > 4) {
                i7 = 4;
                i8 = 4;
            }
            this.f2050h = i7;
            this.f2051i = i8;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return mo1095c();
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws IOException {
            String str;
            if (abstractC0475f == null) {
                return;
            }
            if (i7 == 0 && (str = this.f2047e) != null) {
                appendable.append(str);
                return;
            }
            if (i7 >= 0) {
                appendable.append('+');
            } else {
                appendable.append('-');
                i7 = -i7;
            }
            int i8 = i7 / 3600000;
            C1070h.m1110a(appendable, i8, 2);
            if (this.f2051i == 1) {
                return;
            }
            int i9 = i7 - (i8 * 3600000);
            if (i9 != 0 || this.f2050h > 1) {
                int i10 = i9 / CtvitHttp.DEFAULT_MILLISECONDS;
                if (this.f2049g) {
                    appendable.append(':');
                }
                C1070h.m1110a(appendable, i10, 2);
                if (this.f2051i == 2) {
                    return;
                }
                int i11 = i9 - (i10 * CtvitHttp.DEFAULT_MILLISECONDS);
                if (i11 != 0 || this.f2050h > 2) {
                    int i12 = i11 / 1000;
                    if (this.f2049g) {
                        appendable.append(':');
                    }
                    C1070h.m1110a(appendable, i12, 2);
                    if (this.f2051i == 3) {
                        return;
                    }
                    int i13 = i11 - (i12 * 1000);
                    if (i13 != 0 || this.f2050h > 3) {
                        if (this.f2049g) {
                            appendable.append('.');
                        }
                        C1070h.m1110a(appendable, i13, 3);
                    }
                }
            }
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            int i7 = this.f2050h;
            int i8 = (i7 + 1) << 1;
            if (this.f2049g) {
                i8 += i7 - 1;
            }
            String str = this.f2047e;
            return (str == null || str.length() <= i8) ? i8 : this.f2047e.length();
        }

        /* renamed from: d */
        public final int m1099d(CharSequence charSequence, int i7, int i8) {
            int i9 = 0;
            for (int iMin = Math.min(charSequence.length() - i7, i8); iMin > 0; iMin--) {
                char cCharAt = charSequence.charAt(i7 + i9);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i9++;
            }
            return i9;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) {
        }

        /* JADX WARN: Removed duplicated region for block: B:66:0x00bf  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x00d5  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x00f4  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x00f6  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x00f8  */
        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int mo1097f(p058g6.C1067e r12, java.lang.CharSequence r13, int r14) {
            /*
                Method dump skipped, instructions count: 296
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p058g6.C1065c.l.mo1097f(g6.e, java.lang.CharSequence, int):int");
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$m */
    public static class m implements InterfaceC1074l, InterfaceC1072j {

        /* renamed from: e */
        public final AbstractC0472c f2052e;

        /* renamed from: f */
        public final int f2053f;

        /* renamed from: g */
        public final boolean f2054g;

        public m(AbstractC0472c abstractC0472c, int i7, boolean z6) {
            this.f2052e = abstractC0472c;
            this.f2053f = i7;
            this.f2054g = z6;
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: a */
        public int mo1093a() {
            return this.f2054g ? 4 : 2;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws IOException {
            int i8;
            try {
                int iMo199b = this.f2052e.mo223b(abstractC1904c).mo199b(j7);
                if (iMo199b < 0) {
                    iMo199b = -iMo199b;
                }
                i8 = iMo199b % 100;
            } catch (RuntimeException unused) {
                i8 = -1;
            }
            if (i8 >= 0) {
                C1070h.m1110a(appendable, i8, 2);
            } else {
                appendable.append((char) 65533);
                appendable.append((char) 65533);
            }
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return 2;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) throws IOException {
            int i7;
            if (interfaceC0493x.mo267x(this.f2052e)) {
                try {
                    int iMo263I = interfaceC0493x.mo263I(this.f2052e);
                    if (iMo263I < 0) {
                        iMo263I = -iMo263I;
                    }
                    i7 = iMo263I % 100;
                } catch (RuntimeException unused) {
                }
            } else {
                i7 = -1;
            }
            if (i7 >= 0) {
                C1070h.m1110a(appendable, i7, 2);
            } else {
                appendable.append((char) 65533);
                appendable.append((char) 65533);
            }
        }

        @Override // p058g6.InterfaceC1072j
        /* renamed from: f */
        public int mo1097f(C1067e c1067e, CharSequence charSequence, int i7) throws NumberFormatException {
            int i8;
            int i9;
            int length = charSequence.length() - i7;
            if (this.f2054g) {
                int i10 = 0;
                boolean z6 = false;
                boolean z7 = false;
                while (i10 < length) {
                    char cCharAt = charSequence.charAt(i7 + i10);
                    if (i10 == 0 && (cCharAt == '-' || cCharAt == '+')) {
                        z7 = cCharAt == '-';
                        if (z7) {
                            i10++;
                        } else {
                            i7++;
                            length--;
                        }
                        z6 = true;
                    } else {
                        if (cCharAt < '0' || cCharAt > '9') {
                            break;
                        }
                        i10++;
                    }
                }
                if (i10 == 0) {
                    return ~i7;
                }
                if (z6 || i10 != 2) {
                    if (i10 >= 9) {
                        i8 = i10 + i7;
                        i9 = Integer.parseInt(charSequence.subSequence(i7, i8).toString());
                    } else {
                        int i11 = z7 ? i7 + 1 : i7;
                        int i12 = i11 + 1;
                        try {
                            int iCharAt = charSequence.charAt(i11) - '0';
                            i8 = i10 + i7;
                            while (i12 < i8) {
                                int iCharAt2 = (charSequence.charAt(i12) + ((iCharAt << 3) + (iCharAt << 1))) - 48;
                                i12++;
                                iCharAt = iCharAt2;
                            }
                            i9 = z7 ? -iCharAt : iCharAt;
                        } catch (StringIndexOutOfBoundsException unused) {
                            return ~i7;
                        }
                    }
                    c1067e.m1105e(this.f2052e, i9);
                    return i8;
                }
            } else if (Math.min(2, length) < 2) {
                return ~i7;
            }
            char cCharAt2 = charSequence.charAt(i7);
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                return ~i7;
            }
            int i13 = cCharAt2 - '0';
            char cCharAt3 = charSequence.charAt(i7 + 1);
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                return ~i7;
            }
            int i14 = (((i13 << 3) + (i13 << 1)) + cCharAt3) - 48;
            int iIntValue = this.f2053f;
            Integer num = c1067e.f2061g;
            if (num != null) {
                iIntValue = num.intValue();
            }
            int i15 = iIntValue - 50;
            int i16 = i15 >= 0 ? i15 % 100 : ((i15 + 1) % 100) + 99;
            c1067e.m1105e(this.f2052e, ((i15 + (i14 < i16 ? 100 : 0)) - i16) + i14);
            return i7 + 2;
        }
    }

    /* compiled from: DateTimeFormatterBuilder.java */
    /* renamed from: g6.c$n */
    public static class n extends f {
        public n(AbstractC0472c abstractC0472c, int i7, boolean z6) {
            super(abstractC0472c, i7, z6);
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: b */
        public void mo1094b(Appendable appendable, long j7, AbstractC1904c abstractC1904c, int i7, AbstractC0475f abstractC0475f, Locale locale) throws IOException {
            try {
                C1070h.m1111b(appendable, this.f2031e.mo223b(abstractC1904c).mo199b(j7));
            } catch (RuntimeException unused) {
                appendable.append((char) 65533);
            }
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: c */
        public int mo1095c() {
            return this.f2032f;
        }

        @Override // p058g6.InterfaceC1074l
        /* renamed from: e */
        public void mo1096e(Appendable appendable, InterfaceC0493x interfaceC0493x, Locale locale) throws IOException {
            if (!interfaceC0493x.mo267x(this.f2031e)) {
                appendable.append((char) 65533);
                return;
            }
            try {
                C1070h.m1111b(appendable, interfaceC0493x.mo263I(this.f2031e));
            } catch (RuntimeException unused) {
                appendable.append((char) 65533);
            }
        }
    }

    /* renamed from: q */
    public static void m1068q(Appendable appendable, int i7) throws IOException {
        while (true) {
            i7--;
            if (i7 < 0) {
                return;
            } else {
                appendable.append((char) 65533);
            }
        }
    }

    /* renamed from: t */
    public static boolean m1069t(CharSequence charSequence, int i7, String str) {
        int length = str.length();
        if (charSequence.length() - i7 < length) {
            return false;
        }
        for (int i8 = 0; i8 < length; i8++) {
            if (charSequence.charAt(i7 + i8) != str.charAt(i8)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: u */
    public static boolean m1070u(CharSequence charSequence, int i7, String str) {
        char upperCase;
        char upperCase2;
        int length = str.length();
        if (charSequence.length() - i7 < length) {
            return false;
        }
        for (int i8 = 0; i8 < length; i8++) {
            char cCharAt = charSequence.charAt(i7 + i8);
            char cCharAt2 = str.charAt(i8);
            if (cCharAt != cCharAt2 && (upperCase = Character.toUpperCase(cCharAt)) != (upperCase2 = Character.toUpperCase(cCharAt2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public C1065c m1071a(C1064b c1064b) {
        if (c1064b == null) {
            throw new IllegalArgumentException("No formatter supplied");
        }
        m1074d(c1064b.f2011a, c1064b.f2012b);
        return this;
    }

    /* renamed from: b */
    public C1065c m1072b(InterfaceC1066d interfaceC1066d) {
        if (interfaceC1066d == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
        m1074d(null, C1068f.m1109b(interfaceC1066d));
        return this;
    }

    /* renamed from: c */
    public C1065c m1073c(InterfaceC1069g interfaceC1069g, InterfaceC1066d[] interfaceC1066dArr) {
        int length = interfaceC1066dArr.length;
        int i7 = 0;
        if (length == 1) {
            if (interfaceC1066dArr[0] == null) {
                throw new IllegalArgumentException("No parser supplied");
            }
            m1074d(null, C1068f.m1109b(interfaceC1066dArr[0]));
            return this;
        }
        InterfaceC1072j[] interfaceC1072jArr = new InterfaceC1072j[length];
        while (i7 < length - 1) {
            InterfaceC1072j interfaceC1072jM1109b = C1068f.m1109b(interfaceC1066dArr[i7]);
            interfaceC1072jArr[i7] = interfaceC1072jM1109b;
            if (interfaceC1072jM1109b == null) {
                throw new IllegalArgumentException("Incomplete parser array");
            }
            i7++;
        }
        interfaceC1072jArr[i7] = C1068f.m1109b(interfaceC1066dArr[i7]);
        m1074d(null, new e(interfaceC1072jArr));
        return this;
    }

    /* renamed from: d */
    public final C1065c m1074d(InterfaceC1074l interfaceC1074l, InterfaceC1072j interfaceC1072j) {
        this.f2020b = null;
        this.f2019a.add(interfaceC1074l);
        this.f2019a.add(interfaceC1072j);
        return this;
    }

    /* renamed from: e */
    public C1065c m1075e(AbstractC0472c abstractC0472c, int i7, int i8) {
        if (i8 < i7) {
            i8 = i7;
        }
        if (i7 < 0 || i8 <= 0) {
            throw new IllegalArgumentException();
        }
        if (i7 <= 1) {
            n nVar = new n(abstractC0472c, i8, false);
            this.f2020b = null;
            this.f2019a.add(nVar);
            this.f2019a.add(nVar);
            return this;
        }
        g gVar = new g(abstractC0472c, i8, false, i7);
        this.f2020b = null;
        this.f2019a.add(gVar);
        this.f2019a.add(gVar);
        return this;
    }

    /* renamed from: f */
    public C1065c m1076f(AbstractC0472c abstractC0472c, int i7) {
        if (i7 <= 0) {
            throw new IllegalArgumentException(C0079a.m93a("Illegal number of digits: ", i7));
        }
        c cVar = new c(abstractC0472c, i7, false);
        this.f2020b = null;
        this.f2019a.add(cVar);
        this.f2019a.add(cVar);
        return this;
    }

    /* renamed from: g */
    public C1065c m1077g(AbstractC0472c abstractC0472c, int i7, int i8) {
        if (i8 < i7) {
            i8 = i7;
        }
        if (i7 < 0 || i8 <= 0) {
            throw new IllegalArgumentException();
        }
        d dVar = new d(abstractC0472c, i7, i8);
        this.f2020b = null;
        this.f2019a.add(dVar);
        this.f2019a.add(dVar);
        return this;
    }

    /* renamed from: h */
    public C1065c m1078h(int i7, int i8) {
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        m1077g(AbstractC0472c.f307y, i7, i8);
        return this;
    }

    /* renamed from: i */
    public C1065c m1079i(char c7) {
        a aVar = new a(c7);
        this.f2020b = null;
        this.f2019a.add(aVar);
        this.f2019a.add(aVar);
        return this;
    }

    /* renamed from: j */
    public C1065c m1080j(String str) {
        int length = str.length();
        if (length != 0) {
            if (length != 1) {
                h hVar = new h(str);
                this.f2020b = null;
                this.f2019a.add(hVar);
                this.f2019a.add(hVar);
                return this;
            }
            a aVar = new a(str.charAt(0));
            this.f2020b = null;
            this.f2019a.add(aVar);
            this.f2019a.add(aVar);
        }
        return this;
    }

    /* renamed from: k */
    public C1065c m1081k(InterfaceC1066d interfaceC1066d) {
        if (interfaceC1066d == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
        m1074d(null, new e(new InterfaceC1072j[]{C1068f.m1109b(interfaceC1066d), null}));
        return this;
    }

    /* renamed from: l */
    public C1065c m1082l(AbstractC0472c abstractC0472c) {
        i iVar = new i(abstractC0472c, true);
        this.f2020b = null;
        this.f2019a.add(iVar);
        this.f2019a.add(iVar);
        return this;
    }

    /* renamed from: m */
    public C1065c m1083m(AbstractC0472c abstractC0472c, int i7, int i8) {
        if (i8 < i7) {
            i8 = i7;
        }
        if (i7 < 0 || i8 <= 0) {
            throw new IllegalArgumentException();
        }
        if (i7 <= 1) {
            n nVar = new n(abstractC0472c, i8, true);
            this.f2020b = null;
            this.f2019a.add(nVar);
            this.f2019a.add(nVar);
            return this;
        }
        g gVar = new g(abstractC0472c, i8, true, i7);
        this.f2020b = null;
        this.f2019a.add(gVar);
        this.f2019a.add(gVar);
        return this;
    }

    /* renamed from: n */
    public C1065c m1084n(AbstractC0472c abstractC0472c) {
        i iVar = new i(abstractC0472c, false);
        this.f2020b = null;
        this.f2019a.add(iVar);
        this.f2019a.add(iVar);
        return this;
    }

    /* renamed from: o */
    public C1065c m1085o(String str, String str2, boolean z6, int i7, int i8) {
        l lVar = new l(null, str2, z6, i7, i8);
        this.f2020b = null;
        this.f2019a.add(lVar);
        this.f2019a.add(lVar);
        return this;
    }

    /* renamed from: p */
    public C1065c m1086p(String str, boolean z6, int i7, int i8) {
        l lVar = new l(str, str, z6, i7, i8);
        this.f2020b = null;
        this.f2019a.add(lVar);
        this.f2019a.add(lVar);
        return this;
    }

    /* renamed from: r */
    public C1065c m1087r(int i7, int i8) {
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        return m1083m(AbstractC0472c.f297o, i7, i8);
    }

    /* renamed from: s */
    public C1065c m1088s(int i7, int i8) {
        AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
        return m1083m(AbstractC0472c.f292j, i7, i8);
    }

    /* renamed from: v */
    public final Object m1089v() {
        Object bVar = this.f2020b;
        if (bVar == null) {
            if (this.f2019a.size() == 2) {
                Object obj = this.f2019a.get(0);
                Object obj2 = this.f2019a.get(1);
                if (obj == null) {
                    bVar = obj2;
                } else if (obj == obj2 || obj2 == null) {
                    bVar = obj;
                }
            }
            if (bVar == null) {
                bVar = new b(this.f2019a);
            }
            this.f2020b = bVar;
        }
        return bVar;
    }

    /* renamed from: w */
    public final boolean m1090w(Object obj) {
        if (obj instanceof InterfaceC1072j) {
            return ((obj instanceof b) && ((b) obj).f2023f == null) ? false : true;
        }
        return false;
    }

    /* renamed from: x */
    public C1064b m1091x() {
        Object objM1089v = m1089v();
        boolean z6 = false;
        if ((objM1089v instanceof InterfaceC1074l) && (!(objM1089v instanceof b) || ((b) objM1089v).f2022e != null)) {
            z6 = true;
        }
        InterfaceC1074l interfaceC1074l = z6 ? (InterfaceC1074l) objM1089v : null;
        InterfaceC1072j interfaceC1072j = m1090w(objM1089v) ? (InterfaceC1072j) objM1089v : null;
        if (interfaceC1074l == null && interfaceC1072j == null) {
            throw new UnsupportedOperationException("Both printing and parsing not supported");
        }
        return new C1064b(interfaceC1074l, interfaceC1072j);
    }

    /* renamed from: y */
    public InterfaceC1066d m1092y() {
        Object objM1089v = m1089v();
        if (m1090w(objM1089v)) {
            return C1073k.m1147c((InterfaceC1072j) objM1089v);
        }
        throw new UnsupportedOperationException("Parsing is not supported");
    }
}
