package p064h4;

import java.util.Objects;
import p014b4.InterfaceC0454n;
import p040e4.InterfaceC0948a;
import p074i6.InterfaceC1168b;
import p120o4.AbstractC1588a;
import p120o4.AbstractC1589b;
import p186x2.C2074b;
import p194y3.AbstractC2114f;

/* compiled from: FlowableMap.java */
/* renamed from: h4.c */
/* loaded from: classes.dex */
public final class C1088c<T, U> extends AbstractC1086a<T, U> {

    /* renamed from: g */
    public final InterfaceC0454n<? super T, ? extends U> f2210g;

    /* compiled from: FlowableMap.java */
    /* renamed from: h4.c$a */
    public static final class a<T, U> extends AbstractC1588a<T, U> {

        /* renamed from: i */
        public final InterfaceC0454n<? super T, ? extends U> f2211i;

        public a(InterfaceC0948a<? super U> interfaceC0948a, InterfaceC0454n<? super T, ? extends U> interfaceC0454n) {
            super(interfaceC0948a);
            this.f2211i = interfaceC0454n;
        }

        @Override // p074i6.InterfaceC1168b
        public void onNext(T t6) {
            if (this.f4828h) {
                return;
            }
            try {
                U uApply = this.f2211i.apply(t6);
                Objects.requireNonNull(uApply, "The mapper function returned a null value.");
                this.f4825e.onNext(uApply);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f4826f.cancel();
                onError(th);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public U poll() {
            T tPoll = this.f4827g.poll();
            if (tPoll == null) {
                return null;
            }
            U uApply = this.f2211i.apply(tPoll);
            Objects.requireNonNull(uApply, "The mapper function returned a null value.");
            return uApply;
        }
    }

    /* compiled from: FlowableMap.java */
    /* renamed from: h4.c$b */
    public static final class b<T, U> extends AbstractC1589b<T, U> {

        /* renamed from: i */
        public final InterfaceC0454n<? super T, ? extends U> f2212i;

        public b(InterfaceC1168b<? super U> interfaceC1168b, InterfaceC0454n<? super T, ? extends U> interfaceC0454n) {
            super(interfaceC1168b);
            this.f2212i = interfaceC0454n;
        }

        @Override // p074i6.InterfaceC1168b
        public void onNext(T t6) {
            if (this.f4832h) {
                return;
            }
            try {
                U uApply = this.f2212i.apply(t6);
                Objects.requireNonNull(uApply, "The mapper function returned a null value.");
                this.f4829e.onNext(uApply);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                this.f4830f.cancel();
                onError(th);
            }
        }

        @Override // p040e4.InterfaceC0955h
        public U poll() {
            T tPoll = this.f4831g.poll();
            if (tPoll == null) {
                return null;
            }
            U uApply = this.f2212i.apply(tPoll);
            Objects.requireNonNull(uApply, "The mapper function returned a null value.");
            return uApply;
        }
    }

    public C1088c(AbstractC2114f<T> abstractC2114f, InterfaceC0454n<? super T, ? extends U> interfaceC0454n) {
        super(abstractC2114f);
        this.f2210g = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2114f
    /* renamed from: d */
    public void mo1175d(InterfaceC1168b<? super U> interfaceC1168b) {
        if (interfaceC1168b instanceof InterfaceC0948a) {
            this.f2206f.m2554c(new a((InterfaceC0948a) interfaceC1168b, this.f2210g));
        } else {
            this.f2206f.m2554c(new b(interfaceC1168b, this.f2210g));
        }
    }
}
