package p174v4;

import java.util.concurrent.Callable;
import p112n4.C1527b;
import p112n4.C1529d;
import p112n4.C1530e;
import p112n4.C1538m;
import p112n4.C1539n;
import p160t4.C1908a;
import p194y3.AbstractC2128t;

/* compiled from: Schedulers.java */
/* renamed from: v4.a */
/* loaded from: classes.dex */
public final class C2012a {

    /* renamed from: a */
    public static final AbstractC2128t f5853a;

    /* renamed from: b */
    public static final AbstractC2128t f5854b;

    /* renamed from: c */
    public static final AbstractC2128t f5855c;

    /* compiled from: Schedulers.java */
    /* renamed from: v4.a$a */
    public static final class a {

        /* renamed from: a */
        public static final AbstractC2128t f5856a = new C1527b();
    }

    /* compiled from: Schedulers.java */
    /* renamed from: v4.a$b */
    public static final class b implements Callable<AbstractC2128t> {
        @Override // java.util.concurrent.Callable
        public AbstractC2128t call() {
            return a.f5856a;
        }
    }

    /* compiled from: Schedulers.java */
    /* renamed from: v4.a$c */
    public static final class c implements Callable<AbstractC2128t> {
        @Override // java.util.concurrent.Callable
        public AbstractC2128t call() {
            return d.f5857a;
        }
    }

    /* compiled from: Schedulers.java */
    /* renamed from: v4.a$d */
    public static final class d {

        /* renamed from: a */
        public static final AbstractC2128t f5857a = new C1529d();
    }

    /* compiled from: Schedulers.java */
    /* renamed from: v4.a$e */
    public static final class e {

        /* renamed from: a */
        public static final AbstractC2128t f5858a = new C1530e();
    }

    /* compiled from: Schedulers.java */
    /* renamed from: v4.a$f */
    public static final class f implements Callable<AbstractC2128t> {
        @Override // java.util.concurrent.Callable
        public AbstractC2128t call() {
            return e.f5858a;
        }
    }

    /* compiled from: Schedulers.java */
    /* renamed from: v4.a$g */
    public static final class g {

        /* renamed from: a */
        public static final AbstractC2128t f5859a = new C1538m();
    }

    /* compiled from: Schedulers.java */
    /* renamed from: v4.a$h */
    public static final class h implements Callable<AbstractC2128t> {
        @Override // java.util.concurrent.Callable
        public AbstractC2128t call() {
            return g.f5859a;
        }
    }

    static {
        C1908a.m2204a(new h());
        f5853a = C1908a.m2204a(new b());
        f5854b = C1908a.m2204a(new c());
        f5855c = C1539n.f4504a;
        C1908a.m2204a(new f());
    }
}
