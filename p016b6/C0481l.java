package p016b6;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import p024c6.AbstractC0522c;
import p034d6.C0885l;
import p159t3.AbstractC1904c;

/* compiled from: Instant.java */
/* renamed from: b6.l */
/* loaded from: classes.dex */
public final class C0481l extends AbstractC0522c implements Serializable {
    private static final long serialVersionUID = 3299096530934209741L;

    /* renamed from: e */
    public final long f337e;

    public C0481l() {
        AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
        this.f337e = System.currentTimeMillis();
    }

    @Override // p016b6.InterfaceC0491v
    /* renamed from: g */
    public long mo261g() {
        return this.f337e;
    }

    @Override // p016b6.InterfaceC0491v
    /* renamed from: j */
    public AbstractC1904c mo262j() {
        return C0885l.f1469Q;
    }

    public C0481l(long j7) {
        this.f337e = j7;
    }
}
