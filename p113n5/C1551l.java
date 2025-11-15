package p113n5;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;
import p006a5.AbstractC0030p;
import p065h5.AbstractC1093a;
import p065h5.C1105m;
import p073i5.AbstractC1148a;
import p073i5.C1158k;
import p073i5.C1162o;
import p073i5.InterfaceC1152e;
import p161t5.C1914f;

/* compiled from: HttpOutput.java */
/* renamed from: n5.l */
/* loaded from: classes.dex */
public class C1551l extends AbstractC0030p {

    /* renamed from: f */
    public final AbstractC1541b f4606f;

    /* renamed from: g */
    public final AbstractC1093a f4607g;

    /* renamed from: h */
    public boolean f4608h;

    /* renamed from: i */
    public C1158k f4609i;

    /* renamed from: j */
    public String f4610j;

    /* renamed from: k */
    public Writer f4611k;

    /* renamed from: l */
    public char[] f4612l;

    /* renamed from: m */
    public C1914f f4613m;

    public C1551l(AbstractC1541b abstractC1541b) {
        this.f4606f = abstractC1541b;
        this.f4607g = (AbstractC1093a) abstractC1541b.f4550k;
    }

    /* renamed from: b */
    public int m1775b() {
        return this.f4606f.m1737h();
    }

    /* renamed from: c */
    public void m1776c() {
        this.f4608h = false;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw null;
    }

    /* renamed from: d */
    public final void m1777d(InterfaceC1152e interfaceC1152e) throws IOException {
        if (this.f4608h) {
            throw new IOException("Closed");
        }
        if (!this.f4607g.m1190j()) {
            throw new C1162o();
        }
        while (this.f4607g.mo1186f()) {
            this.f4607g.m1181a(m1775b());
            if (this.f4608h) {
                throw new IOException("Closed");
            }
            if (!this.f4607g.m1190j()) {
                throw new C1162o();
            }
        }
        ((C1105m) this.f4607g).m1235t(interfaceC1152e, false);
        if (this.f4607g.m1185e()) {
            flush();
            close();
        } else if (this.f4607g.mo1186f()) {
            this.f4606f.m1736f(false);
        }
        while (((AbstractC1148a) interfaceC1152e).length() > 0 && this.f4607g.m1190j()) {
            this.f4607g.m1181a(m1775b());
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        AbstractC1093a abstractC1093a = this.f4607g;
        long jM1775b = m1775b();
        Objects.requireNonNull(abstractC1093a);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j7 = jM1775b + jCurrentTimeMillis;
        InterfaceC1152e interfaceC1152e = abstractC1093a.f2258q;
        InterfaceC1152e interfaceC1152e2 = abstractC1093a.f2257p;
        if ((interfaceC1152e == null || interfaceC1152e.length() <= 0) && ((interfaceC1152e2 == null || interfaceC1152e2.length() <= 0) && !abstractC1093a.mo1186f())) {
            return;
        }
        abstractC1093a.mo1184d();
        while (jCurrentTimeMillis < j7) {
            if ((interfaceC1152e == null || interfaceC1152e.length() <= 0) && (interfaceC1152e2 == null || interfaceC1152e2.length() <= 0)) {
                return;
            }
            if (!abstractC1093a.f2243b.isOpen() || abstractC1093a.f2243b.mo925r()) {
                throw new C1162o();
            }
            abstractC1093a.m1181a(j7 - jCurrentTimeMillis);
            jCurrentTimeMillis = System.currentTimeMillis();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i7, int i8) throws IOException {
        m1777d(new C1158k(bArr, i7, i8, 2));
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        m1777d(new C1158k(bArr, 0, bArr.length, 2));
    }

    @Override // java.io.OutputStream
    public void write(int i7) throws IOException {
        C1158k c1158k = this.f4609i;
        if (c1158k == null) {
            this.f4609i = new C1158k(1);
        } else {
            c1158k.clear();
        }
        this.f4609i.mo1321L((byte) i7);
        m1777d(this.f4609i);
    }
}
