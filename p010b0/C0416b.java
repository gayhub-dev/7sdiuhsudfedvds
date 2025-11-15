package p010b0;

import android.support.annotation.NonNull;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import p010b0.InterfaceC0426l;
import p134q0.C1756a;
import p141r.EnumC1811f;
import p162u.C1966j;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: ByteArrayLoader.java */
/* renamed from: b0.b */
/* loaded from: classes.dex */
public class C0416b<Data> implements InterfaceC0426l<byte[], Data> {

    /* renamed from: a */
    public final b<Data> f194a;

    /* compiled from: ByteArrayLoader.java */
    /* renamed from: b0.b$a */
    public static class a implements InterfaceC0427m<byte[], ByteBuffer> {

        /* compiled from: ByteArrayLoader.java */
        /* renamed from: b0.b$a$a, reason: collision with other inner class name */
        public class C2161a implements b<ByteBuffer> {
            public C2161a(a aVar) {
            }

            @Override // p010b0.C0416b.b
            /* renamed from: a */
            public Class<ByteBuffer> mo121a() {
                return ByteBuffer.class;
            }

            @Override // p010b0.C0416b.b
            /* renamed from: b */
            public ByteBuffer mo122b(byte[] bArr) {
                return ByteBuffer.wrap(bArr);
            }
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<byte[], ByteBuffer> mo120b(C0430p c0430p) {
            return new C0416b(new C2161a(this));
        }
    }

    /* compiled from: ByteArrayLoader.java */
    /* renamed from: b0.b$b */
    public interface b<Data> {
        /* renamed from: a */
        Class<Data> mo121a();

        /* renamed from: b */
        Data mo122b(byte[] bArr);
    }

    /* compiled from: ByteArrayLoader.java */
    /* renamed from: b0.b$c */
    public static class c<Data> implements InterfaceC1986b<Data> {

        /* renamed from: e */
        public final byte[] f195e;

        /* renamed from: f */
        public final b<Data> f196f;

        public c(byte[] bArr, b<Data> bVar) {
            this.f195e = bArr;
            this.f196f = bVar;
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        /* renamed from: a */
        public Class<Data> mo123a() {
            return this.f196f.mo121a();
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: b */
        public void mo124b() {
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: c */
        public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super Data> aVar) {
            aVar.mo141e(this.f196f.mo122b(this.f195e));
        }

        @Override // p169v.InterfaceC1986b
        public void cancel() {
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        public EnumC1957a getDataSource() {
            return EnumC1957a.LOCAL;
        }
    }

    /* compiled from: ByteArrayLoader.java */
    /* renamed from: b0.b$d */
    public static class d implements InterfaceC0427m<byte[], InputStream> {

        /* compiled from: ByteArrayLoader.java */
        /* renamed from: b0.b$d$a */
        public class a implements b<InputStream> {
            public a(d dVar) {
            }

            @Override // p010b0.C0416b.b
            /* renamed from: a */
            public Class<InputStream> mo121a() {
                return InputStream.class;
            }

            @Override // p010b0.C0416b.b
            /* renamed from: b */
            public InputStream mo122b(byte[] bArr) {
                return new ByteArrayInputStream(bArr);
            }
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<byte[], InputStream> mo120b(C0430p c0430p) {
            return new C0416b(new a(this));
        }
    }

    public C0416b(b<Data> bVar) {
        this.f194a = bVar;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a mo117a(byte[] bArr, int i7, int i8, C1966j c1966j) {
        return new InterfaceC0426l.a(C1756a.f4997b, new c(bArr, this.f194a));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public /* bridge */ /* synthetic */ boolean mo118b(byte[] bArr) {
        return true;
    }
}
