package p058g6;

import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import p016b6.AbstractC0478i;
import p016b6.C0488s;
import p016b6.InterfaceC0489t;
import p016b6.InterfaceC0494y;
import p203z5.C2158b;

/* compiled from: PeriodFormatterBuilder.java */
/* renamed from: g6.m */
/* loaded from: classes.dex */
public class C1075m {

    /* renamed from: a */
    public int f2138a = 1;

    /* renamed from: b */
    public int f2139b = 2;

    /* renamed from: c */
    public int f2140c = 10;

    /* renamed from: d */
    public List<Object> f2141d;

    /* renamed from: e */
    public boolean f2142e;

    /* renamed from: f */
    public boolean f2143f;

    /* renamed from: g */
    public c[] f2144g;

    /* compiled from: PeriodFormatterBuilder.java */
    /* renamed from: g6.m$a */
    public static class a implements InterfaceC1077o, InterfaceC1076n {

        /* renamed from: a */
        public final InterfaceC1077o[] f2145a;

        /* renamed from: b */
        public final InterfaceC1076n[] f2146b;

        public a(List<Object> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = list.size();
            for (int i7 = 0; i7 < size; i7 += 2) {
                Object obj = list.get(i7);
                if (obj instanceof InterfaceC1077o) {
                    if (obj instanceof a) {
                        InterfaceC1077o[] interfaceC1077oArr = ((a) obj).f2145a;
                        if (interfaceC1077oArr != null) {
                            for (InterfaceC1077o interfaceC1077o : interfaceC1077oArr) {
                                arrayList.add(interfaceC1077o);
                            }
                        }
                    } else {
                        arrayList.add(obj);
                    }
                }
                Object obj2 = list.get(i7 + 1);
                if (obj2 instanceof InterfaceC1076n) {
                    if (obj2 instanceof a) {
                        InterfaceC1076n[] interfaceC1076nArr = ((a) obj2).f2146b;
                        if (interfaceC1076nArr != null) {
                            for (InterfaceC1076n interfaceC1076n : interfaceC1076nArr) {
                                arrayList2.add(interfaceC1076n);
                            }
                        }
                    } else {
                        arrayList2.add(obj2);
                    }
                }
            }
            if (arrayList.size() <= 0) {
                this.f2145a = null;
            } else {
                this.f2145a = (InterfaceC1077o[]) arrayList.toArray(new InterfaceC1077o[arrayList.size()]);
            }
            if (arrayList2.size() <= 0) {
                this.f2146b = null;
            } else {
                this.f2146b = (InterfaceC1076n[]) arrayList2.toArray(new InterfaceC1076n[arrayList2.size()]);
            }
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: a */
        public void mo1154a(StringBuffer stringBuffer, InterfaceC0494y interfaceC0494y, Locale locale) {
            for (InterfaceC1077o interfaceC1077o : this.f2145a) {
                interfaceC1077o.mo1154a(stringBuffer, interfaceC0494y, locale);
            }
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: b */
        public int mo1155b(InterfaceC0494y interfaceC0494y, Locale locale) {
            InterfaceC1077o[] interfaceC1077oArr = this.f2145a;
            int length = interfaceC1077oArr.length;
            int iMo1155b = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return iMo1155b;
                }
                iMo1155b += interfaceC1077oArr[length].mo1155b(interfaceC0494y, locale);
            }
        }

        @Override // p058g6.InterfaceC1076n
        /* renamed from: c */
        public int mo1156c(InterfaceC0489t interfaceC0489t, String str, int i7, Locale locale) {
            InterfaceC1076n[] interfaceC1076nArr = this.f2146b;
            if (interfaceC1076nArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = interfaceC1076nArr.length;
            for (int i8 = 0; i8 < length && i7 >= 0; i8++) {
                i7 = interfaceC1076nArr[i8].mo1156c(interfaceC0489t, str, i7, locale);
            }
            return i7;
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: d */
        public int mo1157d(InterfaceC0494y interfaceC0494y, int i7, Locale locale) {
            InterfaceC1077o[] interfaceC1077oArr = this.f2145a;
            int length = interfaceC1077oArr.length;
            int iMo1157d = 0;
            while (iMo1157d < i7) {
                length--;
                if (length < 0) {
                    break;
                }
                iMo1157d += interfaceC1077oArr[length].mo1157d(interfaceC0494y, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, locale);
            }
            return iMo1157d;
        }
    }

    /* compiled from: PeriodFormatterBuilder.java */
    /* renamed from: g6.m$b */
    public static class b extends d {

        /* renamed from: b */
        public final f f2147b;

        /* renamed from: c */
        public final f f2148c;

        /* renamed from: d */
        public final String[] f2149d;

        public b(f fVar, f fVar2) {
            this.f2147b = fVar;
            this.f2148c = fVar2;
            HashSet hashSet = new HashSet();
            for (String str : fVar.mo1162f()) {
                for (String str2 : this.f2148c.mo1162f()) {
                    hashSet.add(str + str2);
                }
            }
            this.f2149d = (String[]) hashSet.toArray(new String[hashSet.size()]);
        }

        @Override // p058g6.C1075m.f
        /* renamed from: a */
        public int mo1158a(String str, int i7) {
            int iMo1158a;
            int iMo1158a2 = this.f2147b.mo1158a(str, i7);
            return (iMo1158a2 < 0 || ((iMo1158a = this.f2148c.mo1158a(str, this.f2147b.mo1160d(str, iMo1158a2))) >= 0 && m1169g(this.f2148c.mo1160d(str, iMo1158a) - iMo1158a2, str, i7))) ? ~i7 : iMo1158a2 > 0 ? iMo1158a2 : iMo1158a;
        }

        @Override // p058g6.C1075m.f
        /* renamed from: b */
        public int mo1159b(int i7) {
            return this.f2148c.mo1159b(i7) + this.f2147b.mo1159b(i7);
        }

        @Override // p058g6.C1075m.f
        /* renamed from: d */
        public int mo1160d(String str, int i7) {
            int iMo1160d = this.f2147b.mo1160d(str, i7);
            return (iMo1160d < 0 || (iMo1160d = this.f2148c.mo1160d(str, iMo1160d)) < 0 || !m1169g(mo1160d(str, iMo1160d) - iMo1160d, str, i7)) ? iMo1160d : ~i7;
        }

        @Override // p058g6.C1075m.f
        /* renamed from: e */
        public void mo1161e(StringBuffer stringBuffer, int i7) {
            this.f2147b.mo1161e(stringBuffer, i7);
            this.f2148c.mo1161e(stringBuffer, i7);
        }

        @Override // p058g6.C1075m.f
        /* renamed from: f */
        public String[] mo1162f() {
            return (String[]) this.f2149d.clone();
        }
    }

    /* compiled from: PeriodFormatterBuilder.java */
    /* renamed from: g6.m$d */
    public static abstract class d implements f {

        /* renamed from: a */
        public volatile String[] f2158a;

        @Override // p058g6.C1075m.f
        /* renamed from: c */
        public void mo1168c(Set<f> set) {
            if (this.f2158a == null) {
                int length = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
                String str = null;
                for (String str2 : mo1162f()) {
                    if (str2.length() < length) {
                        length = str2.length();
                        str = str2;
                    }
                }
                HashSet hashSet = new HashSet();
                for (f fVar : set) {
                    if (fVar != null) {
                        for (String str3 : fVar.mo1162f()) {
                            if (str3.length() > length || (str3.equalsIgnoreCase(str) && !str3.equals(str))) {
                                hashSet.add(str3);
                            }
                        }
                    }
                }
                this.f2158a = (String[]) hashSet.toArray(new String[hashSet.size()]);
            }
        }

        /* renamed from: g */
        public boolean m1169g(int i7, String str, int i8) {
            if (this.f2158a != null) {
                for (String str2 : this.f2158a) {
                    int length = str2.length();
                    if (i7 < length && str.regionMatches(true, i8, str2, 0, length)) {
                        return true;
                    }
                    if (i7 == length && str.regionMatches(false, i8, str2, 0, length)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* compiled from: PeriodFormatterBuilder.java */
    /* renamed from: g6.m$e */
    public static class e implements InterfaceC1077o, InterfaceC1076n {

        /* renamed from: b */
        public static final e f2159b = new e("");

        /* renamed from: a */
        public final String f2160a;

        public e(String str) {
            this.f2160a = str;
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: a */
        public void mo1154a(StringBuffer stringBuffer, InterfaceC0494y interfaceC0494y, Locale locale) {
            stringBuffer.append(this.f2160a);
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: b */
        public int mo1155b(InterfaceC0494y interfaceC0494y, Locale locale) {
            return this.f2160a.length();
        }

        @Override // p058g6.InterfaceC1076n
        /* renamed from: c */
        public int mo1156c(InterfaceC0489t interfaceC0489t, String str, int i7, Locale locale) {
            String str2 = this.f2160a;
            return str.regionMatches(true, i7, str2, 0, str2.length()) ? this.f2160a.length() + i7 : ~i7;
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: d */
        public int mo1157d(InterfaceC0494y interfaceC0494y, int i7, Locale locale) {
            return 0;
        }
    }

    /* compiled from: PeriodFormatterBuilder.java */
    /* renamed from: g6.m$f */
    public interface f {
        /* renamed from: a */
        int mo1158a(String str, int i7);

        /* renamed from: b */
        int mo1159b(int i7);

        /* renamed from: c */
        void mo1168c(Set<f> set);

        /* renamed from: d */
        int mo1160d(String str, int i7);

        /* renamed from: e */
        void mo1161e(StringBuffer stringBuffer, int i7);

        /* renamed from: f */
        String[] mo1162f();
    }

    /* compiled from: PeriodFormatterBuilder.java */
    /* renamed from: g6.m$g */
    public static class g implements InterfaceC1077o, InterfaceC1076n {

        /* renamed from: a */
        public final String f2161a;

        /* renamed from: b */
        public final String f2162b;

        /* renamed from: c */
        public final String[] f2163c;

        /* renamed from: d */
        public final boolean f2164d;

        /* renamed from: e */
        public final boolean f2165e;

        /* renamed from: f */
        public final InterfaceC1077o f2166f;

        /* renamed from: g */
        public volatile InterfaceC1077o f2167g;

        /* renamed from: h */
        public final InterfaceC1076n f2168h;

        /* renamed from: i */
        public volatile InterfaceC1076n f2169i;

        public g(String str, String str2, String[] strArr, InterfaceC1077o interfaceC1077o, InterfaceC1076n interfaceC1076n, boolean z6, boolean z7) {
            this.f2161a = str;
            this.f2162b = str2;
            if (str.equals(str2) && (strArr == null || strArr.length == 0)) {
                this.f2163c = new String[]{str};
            } else {
                TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                treeSet.add(str);
                treeSet.add(str2);
                if (strArr != null) {
                    int length = strArr.length;
                    while (true) {
                        length--;
                        if (length < 0) {
                            break;
                        } else {
                            treeSet.add(strArr[length]);
                        }
                    }
                }
                ArrayList arrayList = new ArrayList(treeSet);
                Collections.reverse(arrayList);
                this.f2163c = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            this.f2166f = interfaceC1077o;
            this.f2168h = interfaceC1076n;
            this.f2164d = z6;
            this.f2165e = z7;
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: a */
        public void mo1154a(StringBuffer stringBuffer, InterfaceC0494y interfaceC0494y, Locale locale) {
            InterfaceC1077o interfaceC1077o = this.f2166f;
            InterfaceC1077o interfaceC1077o2 = this.f2167g;
            interfaceC1077o.mo1154a(stringBuffer, interfaceC0494y, locale);
            if (this.f2164d) {
                if (interfaceC1077o.mo1157d(interfaceC0494y, 1, locale) > 0) {
                    if (this.f2165e) {
                        int iMo1157d = interfaceC1077o2.mo1157d(interfaceC0494y, 2, locale);
                        if (iMo1157d > 0) {
                            stringBuffer.append(iMo1157d > 1 ? this.f2161a : this.f2162b);
                        }
                    } else {
                        stringBuffer.append(this.f2161a);
                    }
                }
            } else if (this.f2165e && interfaceC1077o2.mo1157d(interfaceC0494y, 1, locale) > 0) {
                stringBuffer.append(this.f2161a);
            }
            interfaceC1077o2.mo1154a(stringBuffer, interfaceC0494y, locale);
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: b */
        public int mo1155b(InterfaceC0494y interfaceC0494y, Locale locale) {
            int length;
            InterfaceC1077o interfaceC1077o = this.f2166f;
            InterfaceC1077o interfaceC1077o2 = this.f2167g;
            int iMo1155b = interfaceC1077o2.mo1155b(interfaceC0494y, locale) + interfaceC1077o.mo1155b(interfaceC0494y, locale);
            if (this.f2164d) {
                if (interfaceC1077o.mo1157d(interfaceC0494y, 1, locale) <= 0) {
                    return iMo1155b;
                }
                if (this.f2165e) {
                    int iMo1157d = interfaceC1077o2.mo1157d(interfaceC0494y, 2, locale);
                    if (iMo1157d > 0) {
                        return (iMo1157d > 1 ? this.f2161a : this.f2162b).length() + iMo1155b;
                    }
                    return iMo1155b;
                }
                length = this.f2161a.length();
            } else {
                if (!this.f2165e || interfaceC1077o2.mo1157d(interfaceC0494y, 1, locale) <= 0) {
                    return iMo1155b;
                }
                length = this.f2161a.length();
            }
            return iMo1155b + length;
        }

        @Override // p058g6.InterfaceC1076n
        /* renamed from: c */
        public int mo1156c(InterfaceC0489t interfaceC0489t, String str, int i7, Locale locale) {
            int iMo1156c = this.f2168h.mo1156c(interfaceC0489t, str, i7, locale);
            if (iMo1156c < 0) {
                return iMo1156c;
            }
            int length = -1;
            boolean z6 = false;
            if (iMo1156c > i7) {
                String[] strArr = this.f2163c;
                int length2 = strArr.length;
                for (int i8 = 0; i8 < length2; i8++) {
                    String str2 = strArr[i8];
                    if (str2 == null || str2.length() == 0 || str.regionMatches(true, iMo1156c, str2, 0, str2.length())) {
                        length = str2 == null ? 0 : str2.length();
                        iMo1156c += length;
                        z6 = true;
                    }
                }
            }
            int iMo1156c2 = this.f2169i.mo1156c(interfaceC0489t, str, iMo1156c, locale);
            return iMo1156c2 < 0 ? iMo1156c2 : (z6 && iMo1156c2 == iMo1156c && length > 0) ? ~iMo1156c : (iMo1156c2 <= iMo1156c || z6 || this.f2164d) ? iMo1156c2 : ~iMo1156c;
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: d */
        public int mo1157d(InterfaceC0494y interfaceC0494y, int i7, Locale locale) {
            int iMo1157d = this.f2166f.mo1157d(interfaceC0494y, i7, locale);
            return iMo1157d < i7 ? iMo1157d + this.f2167g.mo1157d(interfaceC0494y, i7, locale) : iMo1157d;
        }
    }

    /* compiled from: PeriodFormatterBuilder.java */
    /* renamed from: g6.m$h */
    public static class h extends d {

        /* renamed from: b */
        public final String f2170b;

        public h(String str) {
            this.f2170b = str;
        }

        @Override // p058g6.C1075m.f
        /* renamed from: a */
        public int mo1158a(String str, int i7) {
            String str2 = this.f2170b;
            int length = str2.length();
            int length2 = str.length();
            for (int i8 = i7; i8 < length2; i8++) {
                if (str.regionMatches(true, i8, str2, 0, length) && !m1169g(length, str, i8)) {
                    return i8;
                }
                switch (str.charAt(i8)) {
                    case '+':
                    case ',':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                }
                return ~i7;
            }
            return ~i7;
        }

        @Override // p058g6.C1075m.f
        /* renamed from: b */
        public int mo1159b(int i7) {
            return this.f2170b.length();
        }

        @Override // p058g6.C1075m.f
        /* renamed from: d */
        public int mo1160d(String str, int i7) {
            String str2 = this.f2170b;
            int length = str2.length();
            return (!str.regionMatches(true, i7, str2, 0, length) || m1169g(length, str, i7)) ? ~i7 : i7 + length;
        }

        @Override // p058g6.C1075m.f
        /* renamed from: e */
        public void mo1161e(StringBuffer stringBuffer, int i7) {
            stringBuffer.append(this.f2170b);
        }

        @Override // p058g6.C1075m.f
        /* renamed from: f */
        public String[] mo1162f() {
            return new String[]{this.f2170b};
        }
    }

    static {
        new ConcurrentHashMap();
    }

    public C1075m() {
        List<Object> list = this.f2141d;
        if (list == null) {
            this.f2141d = new ArrayList();
        } else {
            list.clear();
        }
        this.f2142e = false;
        this.f2143f = false;
        this.f2144g = new c[10];
    }

    /* renamed from: e */
    public static Object[] m1148e(List<Object> list) {
        int size = list.size();
        if (size == 0) {
            e eVar = e.f2159b;
            return new Object[]{eVar, eVar};
        }
        if (size == 1) {
            return new Object[]{list.get(0), list.get(1)};
        }
        a aVar = new a(list);
        return new Object[]{aVar, aVar};
    }

    /* renamed from: f */
    public static C2158b m1149f(List<Object> list, boolean z6, boolean z7) {
        if (z6 && z7) {
            throw new IllegalStateException("Builder has created neither a printer nor a parser");
        }
        int size = list.size();
        if (size >= 2 && (list.get(0) instanceof g)) {
            g gVar = (g) list.get(0);
            if (gVar.f2169i == null && gVar.f2167g == null) {
                C2158b c2158bM1149f = m1149f(list.subList(2, size), z6, z7);
                InterfaceC1077o interfaceC1077o = (InterfaceC1077o) c2158bM1149f.f6334a;
                InterfaceC1076n interfaceC1076n = (InterfaceC1076n) c2158bM1149f.f6335b;
                gVar.f2167g = interfaceC1077o;
                gVar.f2169i = interfaceC1076n;
                return new C2158b(gVar, gVar);
            }
        }
        Object[] objArrM1148e = m1148e(list);
        return z6 ? new C2158b(null, (InterfaceC1076n) objArrM1148e[1]) : z7 ? new C2158b((InterfaceC1077o) objArrM1148e[0], null) : new C2158b((InterfaceC1077o) objArrM1148e[0], (InterfaceC1076n) objArrM1148e[1]);
    }

    /* renamed from: a */
    public final C1075m m1150a(InterfaceC1077o interfaceC1077o, InterfaceC1076n interfaceC1076n) {
        this.f2141d.add(interfaceC1077o);
        this.f2141d.add(interfaceC1076n);
        this.f2142e |= false;
        this.f2143f |= false;
        return this;
    }

    /* renamed from: b */
    public final void m1151b(int i7) {
        c cVar = new c(this.f2138a, this.f2139b, this.f2140c, false, i7, this.f2144g, null, null);
        m1150a(cVar, cVar);
        this.f2144g[i7] = cVar;
    }

    /* renamed from: c */
    public C1075m m1152c(String str) {
        Object obj;
        h hVar = new h(str);
        Object obj2 = null;
        if (this.f2141d.size() > 0) {
            obj2 = this.f2141d.get(r4.size() - 2);
            obj = this.f2141d.get(r4.size() - 1);
        } else {
            obj = null;
        }
        if (obj2 == null || obj == null || obj2 != obj || !(obj2 instanceof c)) {
            throw new IllegalStateException("No field to apply suffix to");
        }
        c cVar = new c((c) obj2, hVar);
        this.f2141d.set(r0.size() - 2, cVar);
        this.f2141d.set(r0.size() - 1, cVar);
        this.f2144g[cVar.f2154e] = cVar;
        return this;
    }

    /* renamed from: d */
    public final void m1153d() {
    }

    /* compiled from: PeriodFormatterBuilder.java */
    /* renamed from: g6.m$c */
    public static class c implements InterfaceC1077o, InterfaceC1076n {

        /* renamed from: a */
        public final int f2150a;

        /* renamed from: b */
        public final int f2151b;

        /* renamed from: c */
        public final int f2152c;

        /* renamed from: d */
        public final boolean f2153d;

        /* renamed from: e */
        public final int f2154e;

        /* renamed from: f */
        public final c[] f2155f;

        /* renamed from: g */
        public final f f2156g;

        /* renamed from: h */
        public final f f2157h;

        public c(int i7, int i8, int i9, boolean z6, int i10, c[] cVarArr, f fVar, f fVar2) {
            this.f2150a = i7;
            this.f2151b = i8;
            this.f2152c = i9;
            this.f2153d = z6;
            this.f2154e = i10;
            this.f2155f = cVarArr;
            this.f2156g = fVar;
            this.f2157h = null;
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: a */
        public void mo1154a(StringBuffer stringBuffer, InterfaceC0494y interfaceC0494y, Locale locale) {
            long jM1163e = m1163e(interfaceC0494y);
            if (jM1163e == RecyclerView.FOREVER_NS) {
                return;
            }
            int i7 = (int) jM1163e;
            if (this.f2154e >= 8) {
                i7 = (int) (jM1163e / 1000);
            }
            f fVar = this.f2156g;
            if (fVar != null) {
                fVar.mo1161e(stringBuffer, i7);
            }
            int length = stringBuffer.length();
            int i8 = this.f2150a;
            try {
                if (i8 <= 1) {
                    int i9 = C1070h.f2077b;
                    C1070h.m1111b(stringBuffer, i7);
                } else {
                    int i10 = C1070h.f2077b;
                    C1070h.m1110a(stringBuffer, i7, i8);
                }
            } catch (IOException unused) {
            }
            if (this.f2154e >= 8) {
                int iAbs = (int) (Math.abs(jM1163e) % 1000);
                if (this.f2154e == 8 || iAbs > 0) {
                    if (jM1163e < 0 && jM1163e > -1000) {
                        stringBuffer.insert(length, '-');
                    }
                    stringBuffer.append('.');
                    int i11 = C1070h.f2077b;
                    try {
                        C1070h.m1110a(stringBuffer, iAbs, 3);
                    } catch (IOException unused2) {
                    }
                }
            }
            f fVar2 = this.f2157h;
            if (fVar2 != null) {
                fVar2.mo1161e(stringBuffer, i7);
            }
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: b */
        public int mo1155b(InterfaceC0494y interfaceC0494y, Locale locale) {
            long jM1163e = m1163e(interfaceC0494y);
            if (jM1163e == RecyclerView.FOREVER_NS) {
                return 0;
            }
            int iMax = Math.max(C1070h.m1112c(jM1163e), this.f2150a);
            if (this.f2154e >= 8) {
                iMax = Math.max(iMax, jM1163e < 0 ? 5 : 4) + 1;
                if (this.f2154e == 9 && Math.abs(jM1163e) % 1000 == 0) {
                    iMax -= 4;
                }
                jM1163e /= 1000;
            }
            int i7 = (int) jM1163e;
            f fVar = this.f2156g;
            if (fVar != null) {
                iMax += fVar.mo1159b(i7);
            }
            f fVar2 = this.f2157h;
            return fVar2 != null ? iMax + fVar2.mo1159b(i7) : iMax;
        }

        @Override // p058g6.InterfaceC1076n
        /* renamed from: c */
        public int mo1156c(InterfaceC0489t interfaceC0489t, String str, int i7, Locale locale) {
            int iMo1158a;
            int iM1166h;
            f fVar;
            int i8;
            char cCharAt;
            int iMo1160d = i7;
            boolean z6 = this.f2151b == 4;
            if (iMo1160d >= str.length()) {
                return z6 ? ~iMo1160d : iMo1160d;
            }
            f fVar2 = this.f2156g;
            if (fVar2 != null) {
                iMo1160d = fVar2.mo1160d(str, iMo1160d);
                if (iMo1160d < 0) {
                    return !z6 ? ~iMo1160d : iMo1160d;
                }
                z6 = true;
            }
            f fVar3 = this.f2157h;
            int i9 = -1;
            if (fVar3 == null || z6) {
                iMo1158a = -1;
            } else {
                iMo1158a = fVar3.mo1158a(str, iMo1160d);
                if (iMo1158a < 0) {
                    return !z6 ? ~iMo1158a : iMo1158a;
                }
                z6 = true;
            }
            if (!z6 && !m1164f(interfaceC0489t.mo295q(), this.f2154e)) {
                return iMo1160d;
            }
            int iMin = iMo1158a > 0 ? Math.min(this.f2152c, iMo1158a - iMo1160d) : Math.min(this.f2152c, str.length() - iMo1160d);
            int i10 = 0;
            boolean z7 = false;
            boolean z8 = false;
            while (i10 < iMin) {
                int i11 = iMo1160d + i10;
                char cCharAt2 = str.charAt(i11);
                if (i10 != 0 || (!(cCharAt2 == '-' || cCharAt2 == '+') || this.f2153d)) {
                    if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                        z7 = true;
                    } else {
                        if ((cCharAt2 != '.' && cCharAt2 != ',') || (((i8 = this.f2154e) != 8 && i8 != 9) || i9 >= 0)) {
                            break;
                        }
                        iMin = Math.min(iMin + 1, str.length() - iMo1160d);
                        i9 = i11 + 1;
                    }
                    i10++;
                } else {
                    z8 = cCharAt2 == '-';
                    int i12 = i10 + 1;
                    if (i12 >= iMin || (cCharAt = str.charAt(i11 + 1)) < '0' || cCharAt > '9') {
                        break;
                    }
                    if (z8) {
                        i10 = i12;
                    } else {
                        iMo1160d++;
                    }
                    iMin = Math.min(iMin + 1, str.length() - iMo1160d);
                }
            }
            if (!z7) {
                return ~iMo1160d;
            }
            if (iMo1158a >= 0 && iMo1160d + i10 != iMo1158a) {
                return iMo1160d;
            }
            int i13 = this.f2154e;
            if (i13 != 8 && i13 != 9) {
                m1167i(interfaceC0489t, i13, m1166h(str, iMo1160d, i10));
            } else if (i9 < 0) {
                m1167i(interfaceC0489t, 6, m1166h(str, iMo1160d, i10));
                m1167i(interfaceC0489t, 7, 0);
            } else {
                int iM1166h2 = m1166h(str, iMo1160d, (i9 - iMo1160d) - 1);
                m1167i(interfaceC0489t, 6, iM1166h2);
                int i14 = (iMo1160d + i10) - i9;
                if (i14 <= 0) {
                    iM1166h = 0;
                } else {
                    if (i14 >= 3) {
                        iM1166h = m1166h(str, i9, 3);
                    } else {
                        int iM1166h3 = m1166h(str, i9, i14);
                        iM1166h = i14 == 1 ? iM1166h3 * 100 : iM1166h3 * 10;
                    }
                    if (z8 || iM1166h2 < 0) {
                        iM1166h = -iM1166h;
                    }
                }
                m1167i(interfaceC0489t, 7, iM1166h);
            }
            int i15 = iMo1160d + i10;
            return (i15 < 0 || (fVar = this.f2157h) == null) ? i15 : fVar.mo1160d(str, i15);
        }

        @Override // p058g6.InterfaceC1077o
        /* renamed from: d */
        public int mo1157d(InterfaceC0494y interfaceC0494y, int i7, Locale locale) {
            if (i7 <= 0) {
                return 0;
            }
            return (this.f2151b == 4 || m1163e(interfaceC0494y) != RecyclerView.FOREVER_NS) ? 1 : 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0073  */
        /* renamed from: e */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long m1163e(p016b6.InterfaceC0494y r10) {
            /*
                r9 = this;
                int r0 = r9.f2151b
                r1 = 4
                if (r0 != r1) goto L7
                r0 = 0
                goto Lb
            L7:
                b6.s r0 = r10.mo295q()
            Lb:
                r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                if (r0 == 0) goto L1b
                int r3 = r9.f2154e
                boolean r3 = r9.m1164f(r0, r3)
                if (r3 != 0) goto L1b
                return r1
            L1b:
                int r3 = r9.f2154e
                switch(r3) {
                    case 0: goto L66;
                    case 1: goto L5f;
                    case 2: goto L58;
                    case 3: goto L51;
                    case 4: goto L4a;
                    case 5: goto L43;
                    case 6: goto L3c;
                    case 7: goto L35;
                    case 8: goto L21;
                    case 9: goto L21;
                    default: goto L20;
                }
            L20:
                return r1
            L21:
                b6.i r3 = p016b6.AbstractC0478i.f332p
                int r3 = r10.mo292N(r3)
                b6.i r4 = p016b6.AbstractC0478i.f333q
                int r4 = r10.mo292N(r4)
                long r5 = (long) r3
                r7 = 1000(0x3e8, double:4.94E-321)
                long r5 = r5 * r7
                long r3 = (long) r4
                long r5 = r5 + r3
                goto L6d
            L35:
                b6.i r3 = p016b6.AbstractC0478i.f333q
                int r3 = r10.mo292N(r3)
                goto L6c
            L3c:
                b6.i r3 = p016b6.AbstractC0478i.f332p
                int r3 = r10.mo292N(r3)
                goto L6c
            L43:
                b6.i r3 = p016b6.AbstractC0478i.f331o
                int r3 = r10.mo292N(r3)
                goto L6c
            L4a:
                b6.i r3 = p016b6.AbstractC0478i.f330n
                int r3 = r10.mo292N(r3)
                goto L6c
            L51:
                b6.i r3 = p016b6.AbstractC0478i.f328l
                int r3 = r10.mo292N(r3)
                goto L6c
            L58:
                b6.i r3 = p016b6.AbstractC0478i.f327k
                int r3 = r10.mo292N(r3)
                goto L6c
            L5f:
                b6.i r3 = p016b6.AbstractC0478i.f326j
                int r3 = r10.mo292N(r3)
                goto L6c
            L66:
                b6.i r3 = p016b6.AbstractC0478i.f325i
                int r3 = r10.mo292N(r3)
            L6c:
                long r5 = (long) r3
            L6d:
                r3 = 0
                int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r7 != 0) goto Lcb
                int r3 = r9.f2151b
                r4 = 9
                r7 = 1
                if (r3 == r7) goto La4
                r8 = 2
                if (r3 == r8) goto L82
                r10 = 5
                if (r3 == r10) goto L81
                goto Lcb
            L81:
                return r1
            L82:
                boolean r10 = r9.m1165g(r10)
                if (r10 == 0) goto La3
                g6.m$c[] r10 = r9.f2155f
                int r3 = r9.f2154e
                r10 = r10[r3]
                if (r10 != r9) goto La3
                int r3 = r3 + r7
            L91:
                if (r3 > r4) goto Lcb
                boolean r10 = r9.m1164f(r0, r3)
                if (r10 == 0) goto La0
                g6.m$c[] r10 = r9.f2155f
                r10 = r10[r3]
                if (r10 == 0) goto La0
                return r1
            La0:
                int r3 = r3 + 1
                goto L91
            La3:
                return r1
            La4:
                boolean r10 = r9.m1165g(r10)
                if (r10 == 0) goto Lca
                g6.m$c[] r10 = r9.f2155f
                int r3 = r9.f2154e
                r10 = r10[r3]
                if (r10 != r9) goto Lca
                r10 = 8
                int r10 = java.lang.Math.min(r3, r10)
            Lb8:
                int r10 = r10 + (-1)
                if (r10 < 0) goto Lcb
                if (r10 > r4) goto Lcb
                boolean r3 = r9.m1164f(r0, r10)
                if (r3 == 0) goto Lb8
                g6.m$c[] r3 = r9.f2155f
                r3 = r3[r10]
                if (r3 == 0) goto Lb8
            Lca:
                return r1
            Lcb:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: p058g6.C1075m.c.m1163e(b6.y):long");
        }

        /* renamed from: f */
        public boolean m1164f(C0488s c0488s, int i7) {
            switch (i7) {
                case 0:
                    return c0488s.m286a(AbstractC0478i.f325i) >= 0;
                case 1:
                    return c0488s.m286a(AbstractC0478i.f326j) >= 0;
                case 2:
                    return c0488s.m286a(AbstractC0478i.f327k) >= 0;
                case 3:
                    return c0488s.m286a(AbstractC0478i.f328l) >= 0;
                case 4:
                    return c0488s.m286a(AbstractC0478i.f330n) >= 0;
                case 5:
                    return c0488s.m286a(AbstractC0478i.f331o) >= 0;
                case 6:
                    return c0488s.m286a(AbstractC0478i.f332p) >= 0;
                case 7:
                    return c0488s.m286a(AbstractC0478i.f333q) >= 0;
                case 8:
                case 9:
                    if (c0488s.m286a(AbstractC0478i.f332p) >= 0) {
                        return true;
                    }
                    return c0488s.m286a(AbstractC0478i.f333q) >= 0;
                default:
                    return false;
            }
        }

        /* renamed from: g */
        public boolean m1165g(InterfaceC0494y interfaceC0494y) {
            int size = interfaceC0494y.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (interfaceC0494y.mo294k(i7) != 0) {
                    return false;
                }
            }
            return true;
        }

        /* renamed from: h */
        public final int m1166h(String str, int i7, int i8) {
            if (i8 >= 10) {
                return Integer.parseInt(str.substring(i7, i8 + i7));
            }
            boolean z6 = false;
            if (i8 <= 0) {
                return 0;
            }
            int i9 = i7 + 1;
            char cCharAt = str.charAt(i7);
            int i10 = i8 - 1;
            if (cCharAt == '-') {
                i10--;
                if (i10 < 0) {
                    return 0;
                }
                char cCharAt2 = str.charAt(i9);
                i9++;
                cCharAt = cCharAt2;
                z6 = true;
            }
            int i11 = cCharAt - '0';
            while (true) {
                int i12 = i10 - 1;
                if (i10 <= 0) {
                    break;
                }
                int iCharAt = (str.charAt(i9) + ((i11 << 3) + (i11 << 1))) - 48;
                i9++;
                i11 = iCharAt;
                i10 = i12;
            }
            return z6 ? -i11 : i11;
        }

        /* renamed from: i */
        public void m1167i(InterfaceC0489t interfaceC0489t, int i7, int i8) {
            switch (i7) {
                case 0:
                    interfaceC0489t.mo276J(i8);
                    break;
                case 1:
                    interfaceC0489t.mo280U(i8);
                    break;
                case 2:
                    interfaceC0489t.mo284x(i8);
                    break;
                case 3:
                    interfaceC0489t.mo278R(i8);
                    break;
                case 4:
                    interfaceC0489t.mo277K(i8);
                    break;
                case 5:
                    interfaceC0489t.mo282g(i8);
                    break;
                case 6:
                    interfaceC0489t.mo279S(i8);
                    break;
                case 7:
                    interfaceC0489t.mo283j(i8);
                    break;
            }
        }

        public c(c cVar, f fVar) {
            this.f2150a = cVar.f2150a;
            this.f2151b = cVar.f2151b;
            this.f2152c = cVar.f2152c;
            this.f2153d = cVar.f2153d;
            this.f2154e = cVar.f2154e;
            this.f2155f = cVar.f2155f;
            this.f2156g = cVar.f2156g;
            f fVar2 = cVar.f2157h;
            this.f2157h = fVar2 != null ? new b(fVar2, fVar) : fVar;
        }
    }
}
