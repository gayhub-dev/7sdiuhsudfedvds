package p010b0;

import java.util.ArrayDeque;
import java.util.Queue;
import p142r0.C1820e;
import p142r0.C1823h;

/* compiled from: ModelCache.java */
/* renamed from: b0.k */
/* loaded from: classes.dex */
public class C0425k<A, B> {

    /* renamed from: a */
    public final C1820e<b<A>, B> f226a;

    /* compiled from: ModelCache.java */
    /* renamed from: b0.k$a */
    public class a extends C1820e<b<A>, B> {
        public a(C0425k c0425k, int i7) {
            super(i7);
        }

        @Override // p142r0.C1820e
        /* renamed from: c */
        public void mo136c(Object obj, Object obj2) {
            ((b) obj).m138b();
        }
    }

    /* compiled from: ModelCache.java */
    /* renamed from: b0.k$b */
    public static final class b<A> {

        /* renamed from: d */
        public static final Queue<b<?>> f227d;

        /* renamed from: a */
        public int f228a;

        /* renamed from: b */
        public int f229b;

        /* renamed from: c */
        public A f230c;

        static {
            char[] cArr = C1823h.f5300a;
            f227d = new ArrayDeque(0);
        }

        /* renamed from: a */
        public static <A> b<A> m137a(A a7, int i7, int i8) {
            b<A> bVar;
            Queue<b<?>> queue = f227d;
            synchronized (queue) {
                bVar = (b) ((ArrayDeque) queue).poll();
            }
            if (bVar == null) {
                bVar = new b<>();
            }
            bVar.f230c = a7;
            bVar.f229b = i7;
            bVar.f228a = i8;
            return bVar;
        }

        /* renamed from: b */
        public void m138b() {
            Queue<b<?>> queue = f227d;
            synchronized (queue) {
                ((ArrayDeque) queue).offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f229b == bVar.f229b && this.f228a == bVar.f228a && this.f230c.equals(bVar.f230c);
        }

        public int hashCode() {
            return this.f230c.hashCode() + (((this.f228a * 31) + this.f229b) * 31);
        }
    }

    public C0425k(int i7) {
        this.f226a = new a(this, i7);
    }
}
