package p034d6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import p016b6.AbstractC0471b;
import p016b6.AbstractC0472c;
import p016b6.AbstractC0475f;
import p034d6.AbstractC0874a;
import p050f6.C1019f;
import p050f6.C1026m;
import p159t3.AbstractC1904c;

/* compiled from: ISOChronology.java */
/* renamed from: d6.l */
/* loaded from: classes.dex */
public final class C0885l extends AbstractC0874a {

    /* renamed from: Q */
    public static final C0885l f1469Q;

    /* renamed from: R */
    public static final ConcurrentHashMap<AbstractC0475f, C0885l> f1470R;
    private static final long serialVersionUID = -6212696554273812441L;

    /* compiled from: ISOChronology.java */
    /* renamed from: d6.l$a */
    public static final class a implements Serializable {
        private static final long serialVersionUID = -6212696554273812441L;

        /* renamed from: e */
        public transient AbstractC0475f f1471e;

        public a(AbstractC0475f abstractC0475f) {
            this.f1471e = abstractC0475f;
        }

        private void readObject(ObjectInputStream objectInputStream) {
            this.f1471e = (AbstractC0475f) objectInputStream.readObject();
        }

        private Object readResolve() {
            return C0885l.m762T(this.f1471e);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(this.f1471e);
        }
    }

    static {
        ConcurrentHashMap<AbstractC0475f, C0885l> concurrentHashMap = new ConcurrentHashMap<>();
        f1470R = concurrentHashMap;
        C0885l c0885l = new C0885l(C0884k.f1467n0);
        f1469Q = c0885l;
        concurrentHashMap.put(AbstractC0475f.f314f, c0885l);
    }

    public C0885l(AbstractC1904c abstractC1904c) {
        super(abstractC1904c, null);
    }

    /* renamed from: S */
    public static C0885l m761S() {
        return m762T(AbstractC0475f.m235g());
    }

    /* renamed from: T */
    public static C0885l m762T(AbstractC0475f abstractC0475f) throws ClassNotFoundException {
        if (abstractC0475f == null) {
            abstractC0475f = AbstractC0475f.m235g();
        }
        ConcurrentHashMap<AbstractC0475f, C0885l> concurrentHashMap = f1470R;
        C0885l c0885l = concurrentHashMap.get(abstractC0475f);
        if (c0885l != null) {
            return c0885l;
        }
        C0885l c0885l2 = new C0885l(C0887n.m763U(f1469Q, abstractC0475f));
        C0885l c0885lPutIfAbsent = concurrentHashMap.putIfAbsent(abstractC0475f, c0885l2);
        return c0885lPutIfAbsent != null ? c0885lPutIfAbsent : c0885l2;
    }

    private Object writeReplace() {
        return new a(mo230n());
    }

    @Override // p159t3.AbstractC1904c
    /* renamed from: K */
    public AbstractC1904c mo228K() {
        return f1469Q;
    }

    @Override // p159t3.AbstractC1904c
    /* renamed from: L */
    public AbstractC1904c mo229L(AbstractC0475f abstractC0475f) throws ClassNotFoundException {
        if (abstractC0475f == null) {
            abstractC0475f = AbstractC0475f.m235g();
        }
        return abstractC0475f == mo230n() ? this : m762T(abstractC0475f);
    }

    @Override // p034d6.AbstractC0874a
    /* renamed from: Q */
    public void mo694Q(AbstractC0874a.a aVar) {
        if (this.f1362e.mo230n() == AbstractC0475f.f314f) {
            AbstractC0471b abstractC0471b = C0886m.f1472c;
            AbstractC0472c abstractC0472c = AbstractC0472c.f288f;
            C1019f c1019f = new C1019f(abstractC0471b, AbstractC0472c.f290h, 100);
            aVar.f1391H = c1019f;
            aVar.f1403k = c1019f.f1916d;
            aVar.f1390G = new C1026m(c1019f, AbstractC0472c.f291i);
            aVar.f1386C = new C1026m((C1019f) aVar.f1391H, aVar.f1400h, AbstractC0472c.f296n);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0885l) {
            return mo230n().equals(((C0885l) obj).mo230n());
        }
        return false;
    }

    public int hashCode() {
        return mo230n().hashCode() + 800855;
    }

    public String toString() {
        AbstractC0475f abstractC0475fMo230n = mo230n();
        if (abstractC0475fMo230n == null) {
            return "ISOChronology";
        }
        return "ISOChronology[" + abstractC0475fMo230n.f318e + ']';
    }
}
