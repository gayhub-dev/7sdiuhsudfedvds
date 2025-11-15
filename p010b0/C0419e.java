package p010b0;

import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import p010b0.InterfaceC0426l;
import p134q0.C1757b;
import p141r.EnumC1811f;
import p162u.C1966j;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: FileLoader.java */
/* renamed from: b0.e */
/* loaded from: classes.dex */
public class C0419e<Data> implements InterfaceC0426l<File, Data> {

    /* renamed from: a */
    public final d<Data> f203a;

    /* compiled from: FileLoader.java */
    /* renamed from: b0.e$a */
    public static class a<Data> implements InterfaceC0427m<File, Data> {

        /* renamed from: a */
        public final d<Data> f204a;

        public a(d<Data> dVar) {
            this.f204a = dVar;
        }

        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public final InterfaceC0426l<File, Data> mo120b(C0430p c0430p) {
            return new C0419e(this.f204a);
        }
    }

    /* compiled from: FileLoader.java */
    /* renamed from: b0.e$b */
    public static class b extends a<ParcelFileDescriptor> {

        /* compiled from: FileLoader.java */
        /* renamed from: b0.e$b$a */
        public class a implements d<ParcelFileDescriptor> {
            @Override // p010b0.C0419e.d
            /* renamed from: a */
            public Class<ParcelFileDescriptor> mo127a() {
                return ParcelFileDescriptor.class;
            }

            @Override // p010b0.C0419e.d
            /* renamed from: b */
            public ParcelFileDescriptor mo128b(File file) {
                return ParcelFileDescriptor.open(file, 268435456);
            }

            @Override // p010b0.C0419e.d
            /* renamed from: c */
            public void mo129c(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
                parcelFileDescriptor.close();
            }
        }

        public b() {
            super(new a());
        }
    }

    /* compiled from: FileLoader.java */
    /* renamed from: b0.e$c */
    public static class c<Data> implements InterfaceC1986b<Data> {

        /* renamed from: e */
        public final File f205e;

        /* renamed from: f */
        public final d<Data> f206f;

        /* renamed from: g */
        public Data f207g;

        public c(File file, d<Data> dVar) {
            this.f205e = file;
            this.f206f = dVar;
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        /* renamed from: a */
        public Class<Data> mo123a() {
            return this.f206f.mo127a();
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: b */
        public void mo124b() {
            Data data = this.f207g;
            if (data != null) {
                try {
                    this.f206f.mo129c(data);
                } catch (IOException unused) {
                }
            }
        }

        /* JADX WARN: Type inference failed for: r3v3, types: [Data, java.lang.Object] */
        @Override // p169v.InterfaceC1986b
        /* renamed from: c */
        public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super Data> aVar) {
            try {
                Data dataMo128b = this.f206f.mo128b(this.f205e);
                this.f207g = dataMo128b;
                aVar.mo141e(dataMo128b);
            } catch (FileNotFoundException e7) {
                Log.isLoggable("FileLoader", 3);
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

    /* compiled from: FileLoader.java */
    /* renamed from: b0.e$d */
    public interface d<Data> {
        /* renamed from: a */
        Class<Data> mo127a();

        /* renamed from: b */
        Data mo128b(File file);

        /* renamed from: c */
        void mo129c(Data data);
    }

    /* compiled from: FileLoader.java */
    /* renamed from: b0.e$e */
    public static class e extends a<InputStream> {

        /* compiled from: FileLoader.java */
        /* renamed from: b0.e$e$a */
        public class a implements d<InputStream> {
            @Override // p010b0.C0419e.d
            /* renamed from: a */
            public Class<InputStream> mo127a() {
                return InputStream.class;
            }

            @Override // p010b0.C0419e.d
            /* renamed from: b */
            public InputStream mo128b(File file) {
                return new FileInputStream(file);
            }

            @Override // p010b0.C0419e.d
            /* renamed from: c */
            public void mo129c(InputStream inputStream) throws IOException {
                inputStream.close();
            }
        }

        public e() {
            super(new a());
        }
    }

    public C0419e(d<Data> dVar) {
        this.f203a = dVar;
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a mo117a(File file, int i7, int i8, C1966j c1966j) {
        File file2 = file;
        return new InterfaceC0426l.a(new C1757b(file2), new c(file2, this.f203a));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public /* bridge */ /* synthetic */ boolean mo118b(File file) {
        return true;
    }
}
