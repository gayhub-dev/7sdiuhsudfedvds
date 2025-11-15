package p113n5;

import p006a5.InterfaceC0032r;
import p015b5.InterfaceC0458c;
import p015b5.InterfaceC0460e;

/* compiled from: Authentication.java */
/* renamed from: n5.d */
/* loaded from: classes.dex */
public interface InterfaceC1543d {

    /* renamed from: a */
    public static final InterfaceC1543d f4583a = new a();

    /* renamed from: b */
    public static final InterfaceC1543d f4584b = new b();

    /* renamed from: c */
    public static final InterfaceC1543d f4585c = new c();

    /* renamed from: d */
    public static final InterfaceC1543d f4586d = new d();

    /* compiled from: Authentication.java */
    /* renamed from: n5.d$a */
    public class a implements InterfaceC1543d {
        public String toString() {
            return "UNAUTHENTICATED";
        }
    }

    /* compiled from: Authentication.java */
    /* renamed from: n5.d$b */
    public class b implements InterfaceC1543d {
        public String toString() {
            return "NOT CHECKED";
        }
    }

    /* compiled from: Authentication.java */
    /* renamed from: n5.d$c */
    public class c implements f {
        public String toString() {
            return "CHALLENGE";
        }
    }

    /* compiled from: Authentication.java */
    /* renamed from: n5.d$d */
    public class d implements f {
        public String toString() {
            return "FAILURE";
        }
    }

    /* compiled from: Authentication.java */
    /* renamed from: n5.d$e */
    public interface e extends InterfaceC1543d {
        /* renamed from: A */
        InterfaceC1543d mo1652A(InterfaceC0032r interfaceC0032r);
    }

    /* compiled from: Authentication.java */
    /* renamed from: n5.d$f */
    public interface f extends InterfaceC1543d {
    }

    /* compiled from: Authentication.java */
    /* renamed from: n5.d$g */
    public interface g extends InterfaceC1543d {
        /* renamed from: a */
        String mo1631a();

        /* renamed from: h */
        InterfaceC1561v mo1632h();
    }

    /* compiled from: Authentication.java */
    /* renamed from: n5.d$h */
    public interface h extends InterfaceC1543d {
        /* renamed from: i */
        InterfaceC0460e m1766i();

        /* renamed from: j */
        InterfaceC0458c m1767j();
    }
}
