package p016b6;

import android.support.constraint.motion.C0079a;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import p024c6.AbstractC0526g;
import p034d6.C0885l;
import p058g6.C1071i;
import p159t3.AbstractC1904c;

/* compiled from: LocalDateTime.java */
/* renamed from: b6.n */
/* loaded from: classes.dex */
public final class C0483n extends AbstractC0526g implements Serializable {
    private static final long serialVersionUID = -268716875315837168L;

    /* renamed from: e */
    public final long f342e;

    /* renamed from: f */
    public final AbstractC1904c f343f;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0483n() {
        this(System.currentTimeMillis(), C0885l.m761S());
        AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
    }

    @FromString
    /* renamed from: c */
    public static C0483n m268c(String str) {
        return C1071i.f2117g0.m1060c(str);
    }

    private Object readResolve() {
        AbstractC1904c abstractC1904c = this.f343f;
        if (abstractC1904c == null) {
            return new C0483n(this.f342e, C0885l.f1469Q);
        }
        AbstractC0475f abstractC0475f = AbstractC0475f.f314f;
        AbstractC0475f abstractC0475fMo230n = abstractC1904c.mo230n();
        Objects.requireNonNull((C0495z) abstractC0475f);
        return !(abstractC0475fMo230n instanceof C0495z) ? new C0483n(this.f342e, this.f343f.mo228K()) : this;
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: I */
    public int mo263I(AbstractC0472c abstractC0472c) {
        if (abstractC0472c != null) {
            return abstractC0472c.mo223b(this.f343f).mo199b(this.f342e);
        }
        throw new IllegalArgumentException("The DateTimeFieldType must not be null");
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
        if (i7 == 3) {
            return abstractC1904c.mo713u();
        }
        throw new IndexOutOfBoundsException(C0079a.m93a("Invalid index: ", i7));
    }

    @Override // java.lang.Comparable
    public int compareTo(InterfaceC0493x interfaceC0493x) {
        InterfaceC0493x interfaceC0493x2 = interfaceC0493x;
        if (this == interfaceC0493x2) {
            return 0;
        }
        if (interfaceC0493x2 instanceof C0483n) {
            C0483n c0483n = (C0483n) interfaceC0493x2;
            if (this.f343f.equals(c0483n.f343f)) {
                long j7 = this.f342e;
                long j8 = c0483n.f342e;
                if (j7 < j8) {
                    return -1;
                }
                return j7 == j8 ? 0 : 1;
            }
        }
        return super.m335a(interfaceC0493x2);
    }

    /* renamed from: e */
    public C0482m m269e() {
        return new C0482m(this.f342e, this.f343f);
    }

    @Override // p024c6.AbstractC0526g
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0483n) {
            C0483n c0483n = (C0483n) obj;
            if (this.f343f.equals(c0483n.f343f)) {
                return this.f342e == c0483n.f342e;
            }
        }
        return super.equals(obj);
    }

    /* renamed from: f */
    public C0484o m270f() {
        return new C0484o(this.f342e, this.f343f);
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: j */
    public AbstractC1904c mo265j() {
        return this.f343f;
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: k */
    public int mo266k(int i7) {
        if (i7 == 0) {
            return this.f343f.mo690M().mo199b(this.f342e);
        }
        if (i7 == 1) {
            return this.f343f.mo718z().mo199b(this.f342e);
        }
        if (i7 == 2) {
            return this.f343f.mo700e().mo199b(this.f342e);
        }
        if (i7 == 3) {
            return this.f343f.mo713u().mo199b(this.f342e);
        }
        throw new IndexOutOfBoundsException(C0079a.m93a("Invalid index: ", i7));
    }

    @Override // p016b6.InterfaceC0493x
    public int size() {
        return 4;
    }

    @ToString
    public String toString() {
        return C1071i.f2082E.m1062e(this);
    }

    @Override // p016b6.InterfaceC0493x
    /* renamed from: x */
    public boolean mo267x(AbstractC0472c abstractC0472c) {
        if (abstractC0472c == null) {
            return false;
        }
        return abstractC0472c.mo223b(this.f343f).mo216s();
    }

    public C0483n(long j7, AbstractC1904c abstractC1904c) {
        AbstractC1904c abstractC1904cM225a = C0473d.m225a(abstractC1904c);
        this.f342e = abstractC1904cM225a.mo230n().m243h(AbstractC0475f.f314f, j7);
        this.f343f = abstractC1904cM225a.mo228K();
    }
}
