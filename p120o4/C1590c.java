package p120o4;

import com.alibaba.fastjson.parser.deserializer.C0534b;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p005a4.C0012d;
import p074i6.InterfaceC1168b;
import p074i6.InterfaceC1169c;
import p130p4.EnumC1740c;
import p138q4.C1771c;
import p138q4.C1774f;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2115g;

/* compiled from: StrictSubscriber.java */
/* renamed from: o4.c */
/* loaded from: classes.dex */
public class C1590c<T> extends AtomicInteger implements InterfaceC2115g<T>, InterfaceC1169c {
    private static final long serialVersionUID = -4945028590049415624L;

    /* renamed from: e */
    public final InterfaceC1168b<? super T> f4833e;

    /* renamed from: f */
    public final C1771c f4834f = new C1771c();

    /* renamed from: g */
    public final AtomicLong f4835g = new AtomicLong();

    /* renamed from: h */
    public final AtomicReference<InterfaceC1169c> f4836h = new AtomicReference<>();

    /* renamed from: i */
    public final AtomicBoolean f4837i = new AtomicBoolean();

    /* renamed from: j */
    public volatile boolean f4838j;

    public C1590c(InterfaceC1168b<? super T> interfaceC1168b) {
        this.f4833e = interfaceC1168b;
    }

    @Override // p194y3.InterfaceC2115g, p074i6.InterfaceC1168b
    /* renamed from: b */
    public void mo1177b(InterfaceC1169c interfaceC1169c) {
        boolean z6 = false;
        if (!this.f4837i.compareAndSet(false, true)) {
            interfaceC1169c.cancel();
            cancel();
            onError(new IllegalStateException("§2.12 violated: onSubscribe must be called at most once"));
            return;
        }
        this.f4833e.mo1177b(this);
        AtomicReference<InterfaceC1169c> atomicReference = this.f4836h;
        AtomicLong atomicLong = this.f4835g;
        Objects.requireNonNull(interfaceC1169c, "s is null");
        if (atomicReference.compareAndSet(null, interfaceC1169c)) {
            z6 = true;
        } else {
            interfaceC1169c.cancel();
            if (atomicReference.get() != EnumC1740c.CANCELLED) {
                C1908a.m2205b(new C0012d("Subscription already set!"));
            }
        }
        if (z6) {
            long andSet = atomicLong.getAndSet(0L);
            if (andSet != 0) {
                interfaceC1169c.request(andSet);
            }
        }
    }

    @Override // p074i6.InterfaceC1169c
    public void cancel() {
        InterfaceC1169c andSet;
        if (this.f4838j) {
            return;
        }
        AtomicReference<InterfaceC1169c> atomicReference = this.f4836h;
        InterfaceC1169c interfaceC1169c = atomicReference.get();
        EnumC1740c enumC1740c = EnumC1740c.CANCELLED;
        if (interfaceC1169c == enumC1740c || (andSet = atomicReference.getAndSet(enumC1740c)) == enumC1740c || andSet == null) {
            return;
        }
        andSet.cancel();
    }

    @Override // p074i6.InterfaceC1168b
    public void onComplete() {
        this.f4838j = true;
        InterfaceC1168b<? super T> interfaceC1168b = this.f4833e;
        C1771c c1771c = this.f4834f;
        if (getAndIncrement() == 0) {
            Throwable thM1959b = C1774f.m1959b(c1771c);
            if (thM1959b != null) {
                interfaceC1168b.onError(thM1959b);
            } else {
                interfaceC1168b.onComplete();
            }
        }
    }

    @Override // p074i6.InterfaceC1168b
    public void onError(Throwable th) {
        this.f4838j = true;
        InterfaceC1168b<? super T> interfaceC1168b = this.f4833e;
        C1771c c1771c = this.f4834f;
        if (!C1774f.m1958a(c1771c, th)) {
            C1908a.m2205b(th);
        } else if (getAndIncrement() == 0) {
            interfaceC1168b.onError(C1774f.m1959b(c1771c));
        }
    }

    @Override // p074i6.InterfaceC1168b
    public void onNext(T t6) {
        InterfaceC1168b<? super T> interfaceC1168b = this.f4833e;
        C1771c c1771c = this.f4834f;
        if (get() == 0 && compareAndSet(0, 1)) {
            interfaceC1168b.onNext(t6);
            if (decrementAndGet() != 0) {
                Throwable thM1959b = C1774f.m1959b(c1771c);
                if (thM1959b != null) {
                    interfaceC1168b.onError(thM1959b);
                } else {
                    interfaceC1168b.onComplete();
                }
            }
        }
    }

    @Override // p074i6.InterfaceC1169c
    public void request(long j7) {
        if (j7 <= 0) {
            cancel();
            onError(new IllegalArgumentException(C0534b.m341a("§3.9 violated: positive request amount required but it was ", j7)));
            return;
        }
        AtomicReference<InterfaceC1169c> atomicReference = this.f4836h;
        AtomicLong atomicLong = this.f4835g;
        InterfaceC1169c interfaceC1169c = atomicReference.get();
        if (interfaceC1169c != null) {
            interfaceC1169c.request(j7);
            return;
        }
        if (EnumC1740c.m1889a(j7)) {
            C2074b.m2478a(atomicLong, j7);
            InterfaceC1169c interfaceC1169c2 = atomicReference.get();
            if (interfaceC1169c2 != null) {
                long andSet = atomicLong.getAndSet(0L);
                if (andSet != 0) {
                    interfaceC1169c2.request(andSet);
                }
            }
        }
    }
}
