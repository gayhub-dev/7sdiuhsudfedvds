package p010b0;

import android.support.annotation.NonNull;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import p010b0.InterfaceC0426l;
import p134q0.C1757b;
import p141r.EnumC1811f;
import p162u.C1966j;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: DataUrlLoader.java */
/* renamed from: b0.d */
/* loaded from: classes.dex */
public final class C0418d<Data> implements InterfaceC0426l<String, Data> {

    /* renamed from: a */
    public final a<Data> f198a;

    /* compiled from: DataUrlLoader.java */
    /* renamed from: b0.d$a */
    public interface a<Data> {
    }

    /* compiled from: DataUrlLoader.java */
    /* renamed from: b0.d$b */
    public static final class b<Data> implements InterfaceC1986b<Data> {

        /* renamed from: e */
        public final String f199e;

        /* renamed from: f */
        public final a<Data> f200f;

        /* renamed from: g */
        public Data f201g;

        public b(String str, a<Data> aVar) {
            this.f199e = str;
            this.f200f = aVar;
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        /* renamed from: a */
        public Class<Data> mo123a() {
            Objects.requireNonNull((c.a) this.f200f);
            return InputStream.class;
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: b */
        public void mo124b() throws IOException {
            try {
                a<Data> aVar = this.f200f;
                Data data = this.f201g;
                Objects.requireNonNull((c.a) aVar);
                ((InputStream) data).close();
            } catch (IOException unused) {
            }
        }

        /* JADX WARN: Type inference failed for: r2v4, types: [Data, java.lang.Object] */
        @Override // p169v.InterfaceC1986b
        /* renamed from: c */
        public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super Data> aVar) {
            try {
                ?? r22 = (Data) ((c.a) this.f200f).m126a(this.f199e);
                this.f201g = r22;
                aVar.mo141e(r22);
            } catch (IllegalArgumentException e7) {
                aVar.mo140d(e7);
            }
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

    /* compiled from: DataUrlLoader.java */
    /* renamed from: b0.d$c */
    public static final class c implements InterfaceC0427m<String, InputStream> {

        /* renamed from: a */
        public final a<InputStream> f202a = new a(this);

        /* compiled from: DataUrlLoader.java */
        /* renamed from: b0.d$c$a */
        public class a implements a<InputStream> {
            public a(c cVar) {
            }

            /* renamed from: a */
            public Object m126a(String str) {
                if (!str.startsWith("data:image")) {
                    throw new IllegalArgumentException("Not a valid image data URL.");
                }
                int iIndexOf = str.indexOf(44);
                if (iIndexOf == -1) {
                    throw new IllegalArgumentException("Missing comma in data URL.");
                }
                if (str.substring(0, iIndexOf).endsWith(";base64")) {
                    return new ByteArrayInputStream(Base64.decode(str.substring(iIndexOf + 1), 0));
                }
                throw new IllegalArgumentException("Not a base64 image data URL.");
            }
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public final InterfaceC0426l<String, InputStream> mo120b(C0430p c0430p) {
            return new C0418d(this.f202a);
        }
    }

    public C0418d(a<Data> aVar) {
        this.f198a = aVar;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a mo117a(String str, int i7, int i8, C1966j c1966j) {
        String str2 = str;
        return new InterfaceC0426l.a(new C1757b(str2), new b(str2, this.f198a));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public boolean mo118b(String str) {
        return str.startsWith("data:image");
    }
}
