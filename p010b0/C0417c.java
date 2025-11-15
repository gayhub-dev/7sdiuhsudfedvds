package p010b0;

import android.support.annotation.NonNull;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import p010b0.InterfaceC0426l;
import p134q0.C1757b;
import p141r.EnumC1811f;
import p142r0.C1816a;
import p162u.C1966j;
import p162u.EnumC1957a;
import p169v.InterfaceC1986b;

/* compiled from: ByteBufferFileLoader.java */
/* renamed from: b0.c */
/* loaded from: classes.dex */
public class C0417c implements InterfaceC0426l<File, ByteBuffer> {

    /* compiled from: ByteBufferFileLoader.java */
    /* renamed from: b0.c$a */
    public static class a implements InterfaceC1986b<ByteBuffer> {

        /* renamed from: e */
        public final File f197e;

        public a(File file) {
            this.f197e = file;
        }

        @Override // p169v.InterfaceC1986b
        @NonNull
        /* renamed from: a */
        public Class<ByteBuffer> mo123a() {
            return ByteBuffer.class;
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: b */
        public void mo124b() {
        }

        @Override // p169v.InterfaceC1986b
        /* renamed from: c */
        public void mo125c(EnumC1811f enumC1811f, InterfaceC1986b.a<? super ByteBuffer> aVar) {
            try {
                aVar.mo141e(C1816a.m2046a(this.f197e));
            } catch (IOException e7) {
                Log.isLoggable("ByteBufferFileLoader", 3);
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

    /* compiled from: ByteBufferFileLoader.java */
    /* renamed from: b0.c$b */
    public static class b implements InterfaceC0427m<File, ByteBuffer> {
        @Override // p010b0.InterfaceC0427m
        /* renamed from: b */
        public InterfaceC0426l<File, ByteBuffer> mo120b(C0430p c0430p) {
            return new C0417c();
        }
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: a */
    public InterfaceC0426l.a<ByteBuffer> mo117a(File file, int i7, int i8, C1966j c1966j) {
        File file2 = file;
        return new InterfaceC0426l.a<>(new C1757b(file2), new a(file2));
    }

    @Override // p010b0.InterfaceC0426l
    /* renamed from: b */
    public /* bridge */ /* synthetic */ boolean mo118b(File file) {
        return true;
    }
}
