package p161t5;

import java.io.ByteArrayOutputStream;

/* compiled from: ByteArrayOutputStream2.java */
/* renamed from: t5.f */
/* loaded from: classes.dex */
public class C1914f extends ByteArrayOutputStream {
    public C1914f() {
    }

    /* renamed from: a */
    public byte[] m2212a() {
        return ((ByteArrayOutputStream) this).buf;
    }

    /* renamed from: b */
    public int m2213b() {
        return ((ByteArrayOutputStream) this).count;
    }

    /* renamed from: c */
    public void m2214c(int i7) {
        ((ByteArrayOutputStream) this).count = i7;
    }

    public C1914f(int i7) {
        super(i7);
    }
}
