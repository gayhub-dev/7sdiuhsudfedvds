package p024c6;

import android.support.v7.widget.RecyclerView;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import p016b6.AbstractC0475f;
import p016b6.C0473d;
import p034d6.C0885l;
import p159t3.AbstractC1904c;

/* compiled from: BaseDateTime.java */
/* renamed from: c6.e */
/* loaded from: classes.dex */
public abstract class AbstractC0524e extends AbstractC0520a implements Serializable {
    private static final long serialVersionUID = -6728882245981L;

    /* renamed from: e */
    public volatile long f397e;

    /* renamed from: f */
    public volatile AbstractC1904c f398f;

    public AbstractC0524e(long j7) {
        C0885l c0885lM761S = C0885l.m761S();
        AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
        this.f398f = c0885lM761S;
        this.f397e = j7;
        m334b();
    }

    /* renamed from: b */
    public final void m334b() {
        if (this.f397e == Long.MIN_VALUE || this.f397e == RecyclerView.FOREVER_NS) {
            this.f398f = this.f398f.mo228K();
        }
    }

    @Override // p016b6.InterfaceC0491v
    /* renamed from: g */
    public long mo261g() {
        return this.f397e;
    }

    @Override // p016b6.InterfaceC0491v
    /* renamed from: j */
    public AbstractC1904c mo262j() {
        return this.f398f;
    }

    public AbstractC0524e(long j7, AbstractC0475f abstractC0475f) throws ClassNotFoundException {
        C0885l c0885lM762T = C0885l.m762T(abstractC0475f);
        AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
        this.f398f = c0885lM762T;
        this.f397e = j7;
        m334b();
    }

    public AbstractC0524e(long j7, AbstractC1904c abstractC1904c) {
        this.f398f = C0473d.m225a(abstractC1904c);
        this.f397e = j7;
        m334b();
    }
}
