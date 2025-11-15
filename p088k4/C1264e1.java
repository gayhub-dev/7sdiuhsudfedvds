package p088k4;

import java.util.concurrent.Callable;
import p014b4.InterfaceC0443c;
import p014b4.InterfaceC0446f;
import p022c4.EnumC0516d;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.AbstractC2120l;
import p194y3.InterfaceC2113e;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableGenerate.java */
/* renamed from: k4.e1 */
/* loaded from: classes.dex */
public final class C1264e1<T, S> extends AbstractC2120l<T> {

    /* renamed from: e */
    public final Callable<S> f2985e;

    /* renamed from: f */
    public final InterfaceC0443c<S, InterfaceC2113e<T>, S> f2986f;

    /* renamed from: g */
    public final InterfaceC0446f<? super S> f2987g;

    /* compiled from: ObservableGenerate.java */
    /* renamed from: k4.e1$a */
    public static final class a<T, S> implements InterfaceC2113e<T>, InterfaceC2153b {

        /* renamed from: e */
        public final InterfaceC2127s<? super T> f2988e;

        /* renamed from: f */
        public final InterfaceC0443c<S, ? super InterfaceC2113e<T>, S> f2989f;

        /* renamed from: g */
        public final InterfaceC0446f<? super S> f2990g;

        /* renamed from: h */
        public S f2991h;

        /* renamed from: i */
        public volatile boolean f2992i;

        /* renamed from: j */
        public boolean f2993j;

        public a(InterfaceC2127s<? super T> interfaceC2127s, InterfaceC0443c<S, ? super InterfaceC2113e<T>, S> interfaceC0443c, InterfaceC0446f<? super S> interfaceC0446f, S s6) {
            this.f2988e = interfaceC2127s;
            this.f2989f = interfaceC0443c;
            this.f2990g = interfaceC0446f;
            this.f2991h = s6;
        }

        /* renamed from: a */
        public final void m1490a(S s6) {
            try {
                this.f2990g.accept(s6);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                C1908a.m2205b(th);
            }
        }

        /* renamed from: b */
        public void m1491b(Throwable th) {
            if (this.f2993j) {
                C1908a.m2205b(th);
            } else {
                this.f2993j = true;
                this.f2988e.onError(th);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            this.f2992i = true;
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f2992i;
        }
    }

    public C1264e1(Callable<S> callable, InterfaceC0443c<S, InterfaceC2113e<T>, S> interfaceC0443c, InterfaceC0446f<? super S> interfaceC0446f) {
        this.f2985e = callable;
        this.f2986f = interfaceC0443c;
        this.f2987g = interfaceC0446f;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super T> interfaceC2127s) {
        try {
            S sCall = this.f2985e.call();
            InterfaceC0443c<S, InterfaceC2113e<T>, S> interfaceC0443c = this.f2986f;
            a aVar = new a(interfaceC2127s, interfaceC0443c, this.f2987g, sCall);
            interfaceC2127s.onSubscribe(aVar);
            S s6 = aVar.f2991h;
            if (aVar.f2992i) {
                aVar.f2991h = null;
                aVar.m1490a(s6);
                return;
            }
            while (!aVar.f2992i) {
                try {
                    s6 = (S) interfaceC0443c.apply(s6, aVar);
                    if (aVar.f2993j) {
                        aVar.f2992i = true;
                        aVar.f2991h = null;
                        aVar.m1490a(s6);
                        return;
                    }
                } catch (Throwable th) {
                    C2074b.m2470J(th);
                    aVar.f2991h = null;
                    aVar.f2992i = true;
                    aVar.m1491b(th);
                    aVar.m1490a(s6);
                    return;
                }
            }
            aVar.f2991h = null;
            aVar.m1490a(s6);
        } catch (Throwable th2) {
            C2074b.m2470J(th2);
            interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
            interfaceC2127s.onError(th2);
        }
    }
}
