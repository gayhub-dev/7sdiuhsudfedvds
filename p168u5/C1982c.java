package p168u5;

import java.lang.ref.WeakReference;
import java.util.EventListener;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import p161t5.C1918j;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: Container.java */
/* renamed from: u5.c */
/* loaded from: classes.dex */
public class C1982c {

    /* renamed from: b */
    public static final InterfaceC2016c f5797b;

    /* renamed from: a */
    public final CopyOnWriteArrayList<b> f5798a = new CopyOnWriteArrayList<>();

    /* compiled from: Container.java */
    /* renamed from: u5.c$b */
    public interface b extends EventListener {
        /* renamed from: b */
        void m2317b(Object obj);

        /* renamed from: d */
        void m2318d(c cVar);

        /* renamed from: y */
        void m2319y(c cVar);

        /* renamed from: z */
        void m2320z(Object obj);
    }

    /* compiled from: Container.java */
    /* renamed from: u5.c$c */
    public static class c {

        /* renamed from: a */
        public final WeakReference<Object> f5799a;

        /* renamed from: b */
        public final WeakReference<Object> f5800b;

        /* renamed from: c */
        public String f5801c;

        public c(C1982c c1982c, Object obj, Object obj2, String str, a aVar) {
            this.f5799a = new WeakReference<>(obj);
            this.f5800b = new WeakReference<>(obj2);
            this.f5801c = str;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return cVar.f5799a.get() == this.f5799a.get() && cVar.f5800b.get() == this.f5800b.get() && cVar.f5801c.equals(this.f5801c);
        }

        public int hashCode() {
            return this.f5801c.hashCode() + this.f5800b.hashCode() + this.f5799a.hashCode();
        }

        public String toString() {
            return this.f5799a + "---" + this.f5801c + "-->" + this.f5800b;
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f5797b = C2015b.m2349a(C1982c.class.getName());
    }

    /* renamed from: a */
    public final void m2309a(Object obj, Object obj2, String str) {
        InterfaceC2016c interfaceC2016c = f5797b;
        if (interfaceC2016c.mo2353d()) {
            interfaceC2016c.mo2351a("Container " + obj + " + " + obj2 + " as " + str, new Object[0]);
        }
        if (this.f5798a != null) {
            c cVar = new c(this, obj, obj2, str, null);
            for (int i7 = 0; i7 < C1918j.m2228x(this.f5798a); i7++) {
                ((b) C1918j.m2225j(this.f5798a, i7)).m2319y(cVar);
            }
        }
    }

    /* renamed from: b */
    public void m2310b(Object obj) {
        if (this.f5798a != null) {
            for (int i7 = 0; i7 < C1918j.m2228x(this.f5798a); i7++) {
                ((b) C1918j.m2225j(this.f5798a, i7)).m2320z(obj);
            }
        }
    }

    /* renamed from: c */
    public final void m2311c(Object obj, Object obj2, String str) {
        InterfaceC2016c interfaceC2016c = f5797b;
        if (interfaceC2016c.mo2353d()) {
            interfaceC2016c.mo2351a("Container " + obj + " - " + obj2 + " as " + str, new Object[0]);
        }
        if (this.f5798a != null) {
            c cVar = new c(this, obj, obj2, str, null);
            for (int i7 = 0; i7 < C1918j.m2228x(this.f5798a); i7++) {
                ((b) C1918j.m2225j(this.f5798a, i7)).m2318d(cVar);
            }
        }
    }

    /* renamed from: d */
    public void m2312d(Object obj) {
        if (this.f5798a != null) {
            for (int i7 = 0; i7 < C1918j.m2228x(this.f5798a); i7++) {
                ((b) C1918j.m2225j(this.f5798a, i7)).m2317b(obj);
            }
        }
    }

    /* renamed from: e */
    public void m2313e(Object obj, Object obj2, Object obj3, String str) {
        if (obj2 != null && !obj2.equals(obj3)) {
            m2311c(obj, obj2, str);
        }
        if (obj3 == null || obj3.equals(obj2)) {
            return;
        }
        m2309a(obj, obj3, str);
    }

    /* renamed from: f */
    public void m2314f(Object obj, Object obj2, Object obj3, String str, boolean z6) {
        if (obj2 != null && !obj2.equals(obj3)) {
            m2311c(obj, obj2, str);
            if (z6) {
                m2312d(obj2);
            }
        }
        if (obj3 == null || obj3.equals(obj2)) {
            return;
        }
        if (z6) {
            m2310b(obj3);
        }
        m2309a(obj, obj3, str);
    }

    /* renamed from: g */
    public void m2315g(Object obj, Object[] objArr, Object[] objArr2, String str) {
        m2316h(obj, objArr, objArr2, str, false);
    }

    /* renamed from: h */
    public void m2316h(Object obj, Object[] objArr, Object[] objArr2, String str, boolean z6) {
        Object[] objArr3 = null;
        if (objArr2 != null) {
            Object[] objArr4 = new Object[objArr2.length];
            int length = objArr2.length;
            while (true) {
                int i7 = length - 1;
                if (length <= 0) {
                    break;
                }
                boolean z7 = true;
                if (objArr != null) {
                    int length2 = objArr.length;
                    while (true) {
                        int i8 = length2 - 1;
                        if (length2 <= 0) {
                            break;
                        }
                        if (objArr2[i7] == null || !objArr2[i7].equals(objArr[i8])) {
                            length2 = i8;
                        } else {
                            objArr[i8] = null;
                            length2 = i8;
                            z7 = false;
                        }
                    }
                }
                if (z7) {
                    objArr4[i7] = objArr2[i7];
                }
                length = i7;
            }
            objArr3 = objArr4;
        }
        if (objArr != null) {
            int length3 = objArr.length;
            while (true) {
                int i9 = length3 - 1;
                if (length3 <= 0) {
                    break;
                }
                if (objArr[i9] != null) {
                    m2311c(obj, objArr[i9], str);
                    if (z6) {
                        m2312d(objArr[i9]);
                    }
                }
                length3 = i9;
            }
        }
        if (objArr3 != null) {
            for (int i10 = 0; i10 < objArr3.length; i10++) {
                if (objArr3[i10] != null) {
                    if (z6) {
                        m2310b(objArr3[i10]);
                    }
                    m2309a(obj, objArr3[i10], str);
                }
            }
        }
    }
}
