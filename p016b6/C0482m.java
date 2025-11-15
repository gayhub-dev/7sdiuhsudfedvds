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

/* compiled from: LocalDate.java */
/* renamed from: b6.m */
/* loaded from: classes.dex */
public final class C0482m extends AbstractC0526g implements Serializable {

    /* renamed from: h */
    public static final Set<AbstractC0478i> f338h;
    private static final long serialVersionUID = -8775358157899L;

    /* renamed from: e */
    public final long f339e;

    /* renamed from: f */
    public final AbstractC1904c f340f;

    /* renamed from: g */
    public transient int f341g;

    static {
        HashSet hashSet = new HashSet();
        f338h = hashSet;
        hashSet.add(AbstractC0478i.f328l);
        hashSet.add(AbstractC0478i.f327k);
        hashSet.add(AbstractC0478i.f326j);
        hashSet.add(AbstractC0478i.f324h);
        hashSet.add(AbstractC0478i.f325i);
        hashSet.add(AbstractC0478i.f323g);
        hashSet.add(AbstractC0478i.f322f);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0482m() {
        this(System.currentTimeMillis(), C0885l.m761S());
        AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
    }

    private Object readResolve() {
        AbstractC1904c abstractC1904c = this.f340f;
        if (abstractC1904c == null) {
            return new C0482m(this.f339e, C0885l.f1469Q);
        }
        AbstractC0475f abstractC0475f = AbstractC0475f.f314f;
        AbstractC0475f abstractC0475fMo230n = abstractC1904c.mo230n();
        Objects.requireNonNull((C0495z) abstractC0475f);
        return !(abstractC0475fMo230n instanceof C0495z) ? new C0482m(this.f339e, this.f340f.mo228K()) : this;
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: I */
    public int mo263I(AbstractC0472c abstractC0472c) {
        if (abstractC0472c == null) {
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");
        }
        if (mo267x(abstractC0472c)) {
            return abstractC0472c.mo223b(this.f340f).mo199b(this.f339e);
        }
        throw new IllegalArgumentException("Field '" + abstractC0472c + "' is not supported");
    }

    @Override // p024c6.AbstractC0526g
    /* renamed from: b */
    public AbstractC0471b mo264b(int i7, AbstractC1904c abstractC1904c) {
        if (i7 == 0) {
            return abstractC1904c.mo690M();
        }
        if (i7 == 1) {
            return abstractC1904c.mo718z();
        }
        if (i7 == 2) {
            return abstractC1904c.mo700e();
        }
        throw new IndexOutOfBoundsException(C0079a.m93a("Invalid index: ", i7));
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceC0493x interfaceC0493x) {
        InterfaceC0493x interfaceC0493x2 = interfaceC0493x;
        if (this == interfaceC0493x2) {
            return 0;
        }
        if (interfaceC0493x2 instanceof C0482m) {
            C0482m c0482m = (C0482m) interfaceC0493x2;
            if (this.f340f.equals(c0482m.f340f)) {
                long j7 = this.f339e;
                long j8 = c0482m.f339e;
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
        if (obj instanceof C0482m) {
            C0482m c0482m = (C0482m) obj;
            if (this.f340f.equals(c0482m.f340f)) {
                return this.f339e == c0482m.f339e;
            }
        }
        return super.equals(obj);
    }

    @Override // p024c6.AbstractC0526g
    public int hashCode() {
        int i7 = this.f341g;
        if (i7 != 0) {
            return i7;
        }
        int iHashCode = super.hashCode();
        this.f341g = iHashCode;
        return iHashCode;
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: j */
    public AbstractC1904c mo265j() {
        return this.f340f;
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: k */
    public int mo266k(int i7) {
        if (i7 == 0) {
            return this.f340f.mo690M().mo199b(this.f339e);
        }
        if (i7 == 1) {
            return this.f340f.mo718z().mo199b(this.f339e);
        }
        if (i7 == 2) {
            return this.f340f.mo700e().mo199b(this.f339e);
        }
        throw new IndexOutOfBoundsException(C0079a.m93a("Invalid index: ", i7));
    }

    @Override // p016b6.InterfaceC0493x
    public int size() {
        return 3;
    }

    @ToString
    public String toString() {
        return C1071i.f2125o.m1062e(this);
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: x */
    public boolean mo267x(AbstractC0472c abstractC0472c) {
        if (abstractC0472c == null) {
            return false;
        }
        AbstractC0478i abstractC0478iMo222a = abstractC0472c.mo222a();
        if (((HashSet) f338h).contains(abstractC0478iMo222a) || abstractC0478iMo222a.mo259a(this.f340f).mo256h() >= this.f340f.mo703h().mo256h()) {
            return abstractC0472c.mo223b(this.f340f).mo216s();
        }
        return false;
    }

    public C0482m(long j7, AbstractC1904c abstractC1904c) throws ClassNotFoundException {
        AbstractC1904c abstractC1904cM225a = C0473d.m225a(abstractC1904c);
        long jM243h = abstractC1904cM225a.mo230n().m243h(AbstractC0475f.f314f, j7);
        AbstractC1904c abstractC1904cMo228K = abstractC1904cM225a.mo228K();
        this.f339e = abstractC1904cMo228K.mo700e().mo218u(jM243h);
        this.f340f = abstractC1904cMo228K;
    }
}
