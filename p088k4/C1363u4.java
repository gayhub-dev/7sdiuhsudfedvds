package p088k4;

import io.reactivex.ObservableSource;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p014b4.InterfaceC0454n;
import p022c4.EnumC0515c;
import p022c4.EnumC0516d;
import p088k4.C1277g2;
import p138q4.C1771c;
import p160t4.C1908a;
import p186x2.C2074b;
import p194y3.InterfaceC2125q;
import p194y3.InterfaceC2127s;
import p201z3.InterfaceC2153b;

/* compiled from: ObservableWithLatestFromMany.java */
/* renamed from: k4.u4 */
/* loaded from: classes.dex */
public final class C1363u4<T, R> extends AbstractC1238a<T, R> {

    /* renamed from: f */
    public final ObservableSource<?>[] f3863f;

    /* renamed from: g */
    public final Iterable<? extends InterfaceC2125q<?>> f3864g;

    /* renamed from: h */
    public final InterfaceC0454n<? super Object[], R> f3865h;

    /* compiled from: ObservableWithLatestFromMany.java */
    /* renamed from: k4.u4$a */
    public final class a implements InterfaceC0454n<T, R> {
        public a() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.lang.Object[]] */
        @Override // p014b4.InterfaceC0454n
        public R apply(T t6) {
            R rApply = C1363u4.this.f3865h.apply(new Object[]{t6});
            Objects.requireNonNull(rApply, "The combiner returned a null value");
            return rApply;
        }
    }

    /* compiled from: ObservableWithLatestFromMany.java */
    /* renamed from: k4.u4$b */
    public static final class b<T, R> extends AtomicInteger implements InterfaceC2127s<T>, InterfaceC2153b {
        private static final long serialVersionUID = 1577321883966341961L;

        /* renamed from: e */
        public final InterfaceC2127s<? super R> f3867e;

        /* renamed from: f */
        public final InterfaceC0454n<? super Object[], R> f3868f;

        /* renamed from: g */
        public final c[] f3869g;

        /* renamed from: h */
        public final AtomicReferenceArray<Object> f3870h;

        /* renamed from: i */
        public final AtomicReference<InterfaceC2153b> f3871i;

        /* renamed from: j */
        public final C1771c f3872j;

        /* renamed from: k */
        public volatile boolean f3873k;

        public b(InterfaceC2127s<? super R> interfaceC2127s, InterfaceC0454n<? super Object[], R> interfaceC0454n, int i7) {
            this.f3867e = interfaceC2127s;
            this.f3868f = interfaceC0454n;
            c[] cVarArr = new c[i7];
            for (int i8 = 0; i8 < i7; i8++) {
                cVarArr[i8] = new c(this, i8);
            }
            this.f3869g = cVarArr;
            this.f3870h = new AtomicReferenceArray<>(i7);
            this.f3871i = new AtomicReference<>();
            this.f3872j = new C1771c();
        }

        /* renamed from: a */
        public void m1550a(int i7) {
            c[] cVarArr = this.f3869g;
            for (int i8 = 0; i8 < cVarArr.length; i8++) {
                if (i8 != i7) {
                    EnumC0515c.m323a(cVarArr[i8]);
                }
            }
        }

        @Override // p201z3.InterfaceC2153b
        public void dispose() {
            EnumC0515c.m323a(this.f3871i);
            for (c cVar : this.f3869g) {
                EnumC0515c.m323a(cVar);
            }
        }

        @Override // p201z3.InterfaceC2153b
        public boolean isDisposed() {
            return EnumC0515c.m324b(this.f3871i.get());
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            if (this.f3873k) {
                return;
            }
            this.f3873k = true;
            m1550a(-1);
            C2074b.m2500w(this.f3867e, this, this.f3872j);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            if (this.f3873k) {
                C1908a.m2205b(th);
                return;
            }
            this.f3873k = true;
            m1550a(-1);
            C2074b.m2501x(this.f3867e, th, this, this.f3872j);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(T t6) {
            if (this.f3873k) {
                return;
            }
            AtomicReferenceArray<Object> atomicReferenceArray = this.f3870h;
            int length = atomicReferenceArray.length();
            Object[] objArr = new Object[length + 1];
            int i7 = 0;
            objArr[0] = t6;
            while (i7 < length) {
                Object obj = atomicReferenceArray.get(i7);
                if (obj == null) {
                    return;
                }
                i7++;
                objArr[i7] = obj;
            }
            try {
                R rApply = this.f3868f.apply(objArr);
                Objects.requireNonNull(rApply, "combiner returned a null value");
                C2074b.m2502y(this.f3867e, rApply, this, this.f3872j);
            } catch (Throwable th) {
                C2074b.m2470J(th);
                dispose();
                onError(th);
            }
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this.f3871i, interfaceC2153b);
        }
    }

    /* compiled from: ObservableWithLatestFromMany.java */
    /* renamed from: k4.u4$c */
    public static final class c extends AtomicReference<InterfaceC2153b> implements InterfaceC2127s<Object> {
        private static final long serialVersionUID = 3256684027868224024L;

        /* renamed from: e */
        public final b<?, ?> f3874e;

        /* renamed from: f */
        public final int f3875f;

        /* renamed from: g */
        public boolean f3876g;

        public c(b<?, ?> bVar, int i7) {
            this.f3874e = bVar;
            this.f3875f = i7;
        }

        @Override // p194y3.InterfaceC2127s
        public void onComplete() {
            b<?, ?> bVar = this.f3874e;
            int i7 = this.f3875f;
            boolean z6 = this.f3876g;
            Objects.requireNonNull(bVar);
            if (z6) {
                return;
            }
            bVar.f3873k = true;
            bVar.m1550a(i7);
            C2074b.m2500w(bVar.f3867e, bVar, bVar.f3872j);
        }

        @Override // p194y3.InterfaceC2127s
        public void onError(Throwable th) {
            b<?, ?> bVar = this.f3874e;
            int i7 = this.f3875f;
            bVar.f3873k = true;
            EnumC0515c.m323a(bVar.f3871i);
            bVar.m1550a(i7);
            C2074b.m2501x(bVar.f3867e, th, bVar, bVar.f3872j);
        }

        @Override // p194y3.InterfaceC2127s
        public void onNext(Object obj) {
            if (!this.f3876g) {
                this.f3876g = true;
            }
            b<?, ?> bVar = this.f3874e;
            bVar.f3870h.set(this.f3875f, obj);
        }

        @Override // p194y3.InterfaceC2127s
        public void onSubscribe(InterfaceC2153b interfaceC2153b) {
            EnumC0515c.m327f(this, interfaceC2153b);
        }
    }

    public C1363u4(InterfaceC2125q<T> interfaceC2125q, ObservableSource<?>[] observableSourceArr, InterfaceC0454n<? super Object[], R> interfaceC0454n) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3863f = observableSourceArr;
        this.f3864g = null;
        this.f3865h = interfaceC0454n;
    }

    @Override // p194y3.AbstractC2120l
    public void subscribeActual(InterfaceC2127s<? super R> interfaceC2127s) {
        int length;
        InterfaceC2125q[] interfaceC2125qArr = this.f3863f;
        if (interfaceC2125qArr == null) {
            interfaceC2125qArr = new InterfaceC2125q[8];
            try {
                length = 0;
                for (InterfaceC2125q<?> interfaceC2125q : this.f3864g) {
                    if (length == interfaceC2125qArr.length) {
                        interfaceC2125qArr = (InterfaceC2125q[]) Arrays.copyOf(interfaceC2125qArr, (length >> 1) + length);
                    }
                    int i7 = length + 1;
                    interfaceC2125qArr[length] = interfaceC2125q;
                    length = i7;
                }
            } catch (Throwable th) {
                C2074b.m2470J(th);
                interfaceC2127s.onSubscribe(EnumC0516d.INSTANCE);
                interfaceC2127s.onError(th);
                return;
            }
        } else {
            length = interfaceC2125qArr.length;
        }
        if (length == 0) {
            C1277g2 c1277g2 = new C1277g2(this.f2772e, new a());
            c1277g2.f2772e.subscribe(new C1277g2.a(interfaceC2127s, c1277g2.f3120f));
            return;
        }
        b bVar = new b(interfaceC2127s, this.f3865h, length);
        interfaceC2127s.onSubscribe(bVar);
        c[] cVarArr = bVar.f3869g;
        AtomicReference<InterfaceC2153b> atomicReference = bVar.f3871i;
        for (int i8 = 0; i8 < length && !EnumC0515c.m324b(atomicReference.get()) && !bVar.f3873k; i8++) {
            interfaceC2125qArr[i8].subscribe(cVarArr[i8]);
        }
        this.f2772e.subscribe(bVar);
    }

    public C1363u4(InterfaceC2125q<T> interfaceC2125q, Iterable<? extends InterfaceC2125q<?>> iterable, InterfaceC0454n<? super Object[], R> interfaceC0454n) {
        super((InterfaceC2125q) interfaceC2125q);
        this.f3863f = null;
        this.f3864g = iterable;
        this.f3865h = interfaceC0454n;
    }
}
