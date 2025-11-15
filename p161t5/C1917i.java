package p161t5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Properties;
import p175v5.C2015b;
import p175v5.InterfaceC2016c;

/* compiled from: IO.java */
/* renamed from: t5.i */
/* loaded from: classes.dex */
public class C1917i {

    /* renamed from: a */
    public static final InterfaceC2016c f5644a;

    /* renamed from: b */
    public static int f5645b;

    /* renamed from: c */
    public static b f5646c;

    /* renamed from: d */
    public static d f5647d;

    /* renamed from: e */
    public static PrintWriter f5648e;

    /* compiled from: IO.java */
    /* renamed from: t5.i$b */
    public static class b extends InputStream {
        public b(a aVar) {
        }

        @Override // java.io.InputStream
        public int read() {
            return -1;
        }
    }

    /* compiled from: IO.java */
    /* renamed from: t5.i$c */
    public static class c extends OutputStream {
        public c(a aVar) {
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.OutputStream
        public void write(int i7) {
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i7, int i8) {
        }
    }

    /* compiled from: IO.java */
    /* renamed from: t5.i$d */
    public static class d extends Writer {
        public d(a aVar) {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(int i7) {
        }

        @Override // java.io.Writer
        public void write(String str) {
        }

        @Override // java.io.Writer
        public void write(String str, int i7, int i8) {
        }

        @Override // java.io.Writer
        public void write(char[] cArr) {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i7, int i8) {
        }
    }

    static {
        Properties properties = C2015b.f5863a;
        f5644a = C2015b.m2349a(C1917i.class.getName());
        f5645b = 65536;
        new c(null);
        f5646c = new b(null);
        f5647d = new d(null);
        f5648e = new PrintWriter(f5647d);
    }

    /* renamed from: a */
    public static void m2219a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e7) {
                f5644a.mo2360k(e7);
            }
        }
    }

    /* renamed from: b */
    public static void m2220b(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e7) {
                f5644a.mo2360k(e7);
            }
        }
    }
}
