package p016b6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import p024c6.AbstractC0524e;
import p034d6.C0885l;
import p050f6.AbstractC1014a;
import p159t3.AbstractC1904c;

/* compiled from: MutableDateTime.java */
/* renamed from: b6.p */
/* loaded from: classes.dex */
public class C0485p extends AbstractC0524e implements Cloneable {
    private static final long serialVersionUID = 2852608688135209575L;

    /* compiled from: MutableDateTime.java */
    /* renamed from: b6.p$a */
    public static final class a extends AbstractC1014a {
        private static final long serialVersionUID = -4481126543819298617L;

        /* renamed from: e */
        public C0485p f348e;

        /* renamed from: f */
        public AbstractC0471b f349f;

        public a(C0485p c0485p, AbstractC0471b abstractC0471b) {
            this.f348e = c0485p;
            this.f349f = abstractC0471b;
        }

        private void readObject(ObjectInputStream objectInputStream) {
            this.f348e = (C0485p) objectInputStream.readObject();
            this.f349f = ((AbstractC0472c) objectInputStream.readObject()).mo223b(this.f348e.f398f);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(this.f348e);
            objectOutputStream.writeObject(this.f349f.mo213p());
        }

        @Override // p050f6.AbstractC1014a
        /* renamed from: d */
        public AbstractC1904c mo272d() {
            return this.f348e.f398f;
        }

        @Override // p050f6.AbstractC1014a
        /* renamed from: e */
        public AbstractC0471b mo273e() {
            return this.f349f;
        }

        @Override // p050f6.AbstractC1014a
        /* renamed from: g */
        public long mo274g() {
            return this.f348e.f397e;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0485p() {
        super(System.currentTimeMillis(), C0885l.m761S());
        AtomicReference<Map<String, AbstractC0475f>> atomicReference = C0473d.f313a;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError("Clone error");
        }
    }

    public C0485p(long j7, AbstractC0475f abstractC0475f) {
        super(j7, abstractC0475f);
    }
}
