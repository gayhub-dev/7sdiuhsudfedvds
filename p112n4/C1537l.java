package p112n4;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0516d;
import p048f4.C1005j;
import p064h4.C1088c;
import p138q4.C1774f;
import p167u4.AbstractC1977a;
import p167u4.C1978b;
import p167u4.C1979c;
import p194y3.AbstractC2110b;
import p194y3.AbstractC2114f;
import p194y3.AbstractC2128t;
import p194y3.InterfaceC2111c;
import p201z3.InterfaceC2153b;

/* compiled from: SchedulerWhen.java */
/* renamed from: n4.l */
/* loaded from: classes.dex */
public class C1537l extends AbstractC2128t implements InterfaceC2153b {

    /* renamed from: h */
    public static final InterfaceC2153b f4481h = new g();

    /* renamed from: e */
    public final AbstractC2128t f4482e;

    /* renamed from: f */
    public final AbstractC1977a<AbstractC2114f<AbstractC2110b>> f4483f;

    /* renamed from: g */
    public InterfaceC2153b f4484g;

    /* compiled from: SchedulerWhen.java */
    /* renamed from: n4.l$a */
    public static final class a implements InterfaceC0454n<f, AbstractC2110b> {

        /* renamed from: e */
        public final AbstractC2128t.c f4485e;

        /* compiled from: SchedulerWhen.java */
        /* renamed from: n4.l$a$a, reason: collision with other inner class name */
        public final class C2188a extends AbstractC2110b {

            /* renamed from: a */
            public final f f4486a;

            public C2188a(f fVar) {
                this.f4486a = fVar;
            }

            @Override // p194y3.AbstractC2110b
            /* renamed from: c */
            public void mo1054c(InterfaceC2111c interfaceC2111c) {
                InterfaceC2153b interfaceC2153b;
                interfaceC2111c.onSubscribe(this.f4486a);
                f fVar = this.f4486a;
                AbstractC2128t.c cVar = a.this.f4485e;
                InterfaceC2153b interfaceC2153b2 = fVar.get();
                InterfaceC2153b interfaceC2153b3 = C1537l.f4481h;
                if (interfaceC2153b2 != EnumC0516d.INSTANCE && interfaceC2153b2 == (interfaceC2153b = C1537l.f4481h)) {
                    InterfaceC2153b interfaceC2153bMo1717a = fVar.mo1717a(cVar, interfaceC2111c);
                    if (fVar.compareAndSet(interfaceC2153b, interfaceC2153bMo1717a)) {
                        return;
                    }
                    interfaceC2153bMo1717a.dispose();
                }
            }
        }

        public a(AbstractC2128t.c cVar) {
            this.f4485e = cVar;
        }

        @Override // p014b4.InterfaceC0454n
        public AbstractC2110b apply(f fVar) {
            return new C2188a(fVar);
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: n4.l$b */
    public static class b extends f {

        /* renamed from: e */
        public final Runnable f4488e;

        /* renamed from: f */
        public final long f4489f;

        /* renamed from: g */
        public final TimeUnit f4490g;

        public b(Runnable runnable, long j7, TimeUnit timeUnit) {
            this.f4488e = runnable;
            this.f4489f = j7;
            this.f4490g = timeUnit;
        }

        @Override // p112n4.C1537l.f
        /* renamed from: a */
        public InterfaceC2153b mo1717a(AbstractC2128t.c cVar, InterfaceC2111c interfaceC2111c) {
            return cVar.schedule(new d(this.f4488e, interfaceC2111c), this.f4489f, this.f4490g);
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: n4.l$c */
    public static class c extends f {

        /* renamed from: e */
        public final Runnable f4491e;

        public c(Runnable runnable) {
            this.f4491e = runnable;
        }

        @Override // p112n4.C1537l.f
        /* renamed from: a */
        public InterfaceC2153b mo1717a(AbstractC2128t.c cVar, InterfaceC2111c interfaceC2111c) {
            return cVar.schedule(new d(this.f4491e, interfaceC2111c));
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: n4.l$d */
    public static class d implements Runnable {

        /* renamed from: e */
        public final InterfaceC2111c f4492e;

        /* renamed from: f */
        public final Runnable f4493f;

        public d(Runnable runnable, InterfaceC2111c interfaceC2111c) {
            this.f4493f = runnable;
            this.f4492e = interfaceC2111c;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f4493f.run();
            } finally {
                this.f4492e.onComplete();
            }
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: n4.l$f */
    public static abstract class f extends AtomicReference<InterfaceC2153b> implements InterfaceC2153b {
        public f() {
            super(C1537l.f4481h);
        }

        /* renamed from: a */
        public abstract InterfaceC2153b mo1717a(AbstractC2128t.c cVar, InterfaceC2111c interfaceC2111c);

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            InterfaceC2153b interfaceC2153b;
            EnumC0516d enumC0516d = EnumC0516d.INSTANCE;
            InterfaceC2153b interfaceC2153b2 = C1537l.f4481h;
            do {
                interfaceC2153b = get();
                InterfaceC2153b interfaceC2153b3 = C1537l.f4481h;
                if (interfaceC2153b == enumC0516d) {
                    return;
                }
            } while (!compareAndSet(interfaceC2153b, enumC0516d));
            if (interfaceC2153b != C1537l.f4481h) {
                interfaceC2153b.dispose();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return get().isDisposed();
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: n4.l$g */
    public static final class g implements InterfaceC2153b {
        @Override // p201z3.InterfaceC2153b
        public void dispose() {
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [u4.b] */
    /* JADX WARN: Type inference failed for: r2v0, types: [b4.n, b4.n<y3.f<y3.f<y3.b>>, y3.b>] */
    public C1537l(InterfaceC0454n<AbstractC2114f<AbstractC2114f<AbstractC2110b>>, AbstractC2110b> interfaceC0454n, AbstractC2128t abstractC2128t) {
        this.f4482e = abstractC2128t;
        C1979c c1979c = new C1979c(AbstractC2114f.f6246e);
        c1979c = c1979c instanceof C1978b ? c1979c : new C1978b(c1979c);
        this.f4483f = c1979c;
        try {
            AbstractC2110b abstractC2110b = (AbstractC2110b) interfaceC0454n.apply(c1979c);
            Objects.requireNonNull(abstractC2110b);
            C1005j c1005j = new C1005j();
            abstractC2110b.mo2552b(c1005j);
            this.f4484g = c1005j;
        } catch (Throwable th) {
            throw C1774f.m1961d(th);
        }
    }

    @Override // p194y3.AbstractC2128t
    public AbstractC2128t.c createWorker() {
        AbstractC2128t.c cVarCreateWorker = this.f4482e.createWorker();
        AbstractC1977a c1979c = new C1979c(AbstractC2114f.f6246e);
        if (!(c1979c instanceof C1978b)) {
            c1979c = new C1978b(c1979c);
        }
        C1088c c1088c = new C1088c(c1979c, new a(cVarCreateWorker));
        e eVar = new e(c1979c, cVarCreateWorker);
        this.f4483f.onNext(c1088c);
        return eVar;
    }

    @Override // p201z3.InterfaceC2153b
    public void dispose() {
        this.f4484g.dispose();
    }

    @Override // p201z3.InterfaceC2153b
    public boolean isDisposed() {
        return this.f4484g.isDisposed();
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: n4.l$e */
    public static final class e extends AbstractC2128t.c {

        /* renamed from: e */
        public final AtomicBoolean f4494e = new AtomicBoolean();

        /* renamed from: f */
        public final AbstractC1977a<f> f4495f;

        /* renamed from: g */
        public final AbstractC2128t.c f4496g;

        public e(AbstractC1977a<f> abstractC1977a, AbstractC2128t.c cVar) {
            this.f4495f = abstractC1977a;
            this.f4496g = cVar;
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            if (this.f4494e.compareAndSet(false, true)) {
                this.f4495f.onComplete();
                this.f4496g.dispose();
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return this.f4494e.get();
        }

        @Override // p194y3.AbstractC2128t.c
        public InterfaceC2153b schedule(Runnable runnable, long j7, TimeUnit timeUnit) {
            b bVar = new b(runnable, j7, timeUnit);
            this.f4495f.onNext(bVar);
            return bVar;
        }

        @Override // p194y3.AbstractC2128t.c
        public InterfaceC2153b schedule(Runnable runnable) {
            c cVar = new c(runnable);
            this.f4495f.onNext(cVar);
            return cVar;
        }
    }
}
