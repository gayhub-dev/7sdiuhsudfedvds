package p016b6;

import android.support.constraint.motion.C0079a;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.joda.convert.ToString;
import p024c6.AbstractC0526g;
import p034d6.C0885l;
import p058g6.C1071i;
import p159t3.AbstractC1904c;

/* compiled from: LocalTime.java */
/* renamed from: b6.o */
/* loaded from: classes.dex */
public final class C0484o extends AbstractC0526g implements Serializable {

    /* renamed from: g */
    public static final C0484o f344g = new C0484o(0, 0, 0, 0);

    /* renamed from: h */
    public static final Set<AbstractC0478i> f345h;
    private static final long serialVersionUID = -12873158713873L;

    /* renamed from: e */
    public final long f346e;

    /* renamed from: f */
    public final AbstractC1904c f347f;

    static {
        HashSet hashSet = new HashSet();
        f345h = hashSet;
        hashSet.add(AbstractC0478i.f333q);
        hashSet.add(AbstractC0478i.f332p);
        hashSet.add(AbstractC0478i.f331o);
        hashSet.add(AbstractC0478i.f330n);
    }

    public C0484o(int i7, int i8, int i9, int i10) {
        AbstractC1904c abstractC1904cMo228K = C0473d.m225a(C0885l.f1469Q).mo228K();
        long jMo706m = abstractC1904cMo228K.mo706m(0L, i7, i8, i9, i10);
        this.f347f = abstractC1904cMo228K;
        this.f346e = jMo706m;
    }

    private Object readResolve() {
        AbstractC1904c abstractC1904c = this.f347f;
        if (abstractC1904c == null) {
            return new C0484o(this.f346e, C0885l.f1469Q);
        }
        AbstractC0475f abstractC0475f = AbstractC0475f.f314f;
        AbstractC0475f abstractC0475fMo230n = abstractC1904c.mo230n();
        Objects.requireNonNull((C0495z) abstractC0475f);
        return !(abstractC0475fMo230n instanceof C0495z) ? new C0484o(this.f346e, this.f347f.mo228K()) : this;
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: I */
    public int mo263I(AbstractC0472c abstractC0472c) {
        if (abstractC0472c == null) {
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");
        }
        if (mo267x(abstractC0472c)) {
            return abstractC0472c.mo223b(this.f347f).mo199b(this.f346e);
        }
        throw new IllegalArgumentException("Field '" + abstractC0472c + "' is not supported");
    }

    @Override // p024c6.AbstractC0526g
    /* renamed from: b */
    public AbstractC0471b mo264b(int i7, AbstractC1904c abstractC1904c) {
        if (i7 == 0) {
            return abstractC1904c.mo709q();
        }
        if (i7 == 1) {
            return abstractC1904c.mo716x();
        }
        if (i7 == 2) {
            return abstractC1904c.mo683C();
        }
        if (i7 == 3) {
            return abstractC1904c.mo714v();
        }
        throw new IndexOutOfBoundsException(C0079a.m93a("Invalid index: ", i7));
    }

    /* renamed from: c */
    public boolean m271c(AbstractC0478i abstractC0478i) {
        if (abstractC0478i == null) {
            return false;
        }
        AbstractC0477h abstractC0477hMo259a = abstractC0478i.mo259a(this.f347f);
        if (((HashSet) f345h).contains(abstractC0478i) || abstractC0477hMo259a.mo256h() < this.f347f.mo703h().mo256h()) {
            return abstractC0477hMo259a.mo258l();
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceC0493x interfaceC0493x) {
        InterfaceC0493x interfaceC0493x2 = interfaceC0493x;
        if (this == interfaceC0493x2) {
            return 0;
        }
        if (interfaceC0493x2 instanceof C0484o) {
            C0484o c0484o = (C0484o) interfaceC0493x2;
            if (this.f347f.equals(c0484o.f347f)) {
                long j7 = this.f346e;
                long j8 = c0484o.f346e;
                if (j7 < j8) {
                    return -1;
                }
                return j7 == j8 ? 0 : 1;
            }
        }
        return super.m335a(interfaceC0493x2);
    }

    @Override // p024c6.AbstractC0526g
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0484o) {
            C0484o c0484o = (C0484o) obj;
            if (this.f347f.equals(c0484o.f347f)) {
                return this.f346e == c0484o.f346e;
            }
        }
        return super.equals(obj);
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: j */
    public AbstractC1904c mo265j() {
        return this.f347f;
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: k */
    public int mo266k(int i7) {
        if (i7 == 0) {
            return this.f347f.mo709q().mo199b(this.f346e);
        }
        if (i7 == 1) {
            return this.f347f.mo716x().mo199b(this.f346e);
        }
        if (i7 == 2) {
            return this.f347f.mo683C().mo199b(this.f346e);
        }
        if (i7 == 3) {
            return this.f347f.mo714v().mo199b(this.f346e);
        }
        throw new IndexOutOfBoundsException(C0079a.m93a("Invalid index: ", i7));
    }

    @Override // p016b6.InterfaceC0493x
    public int size() {
        return 4;
    }

    @ToString
    public String toString() {
        return C1071i.f2078A.m1062e(this);
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: x */
    public boolean mo267x(AbstractC0472c abstractC0472c) {
        if (abstractC0472c == null || !m271c(abstractC0472c.mo222a())) {
            return false;
        }
        AbstractC0478i abstractC0478iMo224c = abstractC0472c.mo224c();
        return m271c(abstractC0478iMo224c) || abstractC0478iMo224c == AbstractC0478i.f328l;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0484o() {
        this(System.currentTimeMillis(), C0885l.m761S());
        AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
    }

    public C0484o(long j7, AbstractC1904c abstractC1904c) throws ClassNotFoundException {
        AbstractC1904c abstractC1904cM225a = C0473d.m225a(abstractC1904c);
        long jM243h = abstractC1904cM225a.mo230n().m243h(AbstractC0475f.f314f, j7);
        AbstractC1904c abstractC1904cMo228K = abstractC1904cM225a.mo228K();
        this.f346e = abstractC1904cMo228K.mo713u().mo199b(jM243h);
        this.f347f = abstractC1904cMo228K;
    }
}
