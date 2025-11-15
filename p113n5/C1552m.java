package p113n5;

import java.io.IOException;
import java.io.Writer;
import p065h5.AbstractC1093a;

/* compiled from: HttpWriter.java */
/* renamed from: n5.m */
/* loaded from: classes.dex */
public class C1552m extends Writer {

    /* renamed from: e */
    public final C1551l f4614e;

    /* renamed from: f */
    public int f4615f;

    /* renamed from: g */
    public int f4616g;

    public C1552m(C1551l c1551l) {
        this.f4614e = c1551l;
        AbstractC1093a abstractC1093a = c1551l.f4607g;
        this.f4616g = 0;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f4614e.close();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        this.f4614e.flush();
    }

    @Override // java.io.Writer
    public void write(String str, int i7, int i8) throws IOException {
        while (i8 > 512) {
            write(str, i7, 512);
            i7 += 512;
            i8 -= 512;
        }
        C1551l c1551l = this.f4614e;
        if (c1551l.f4612l == null) {
            c1551l.f4612l = new char[512];
        }
        char[] cArr = c1551l.f4612l;
        str.getChars(i7, i7 + i8, cArr, 0);
        write(cArr, 0, i8);
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0175 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0178 A[SYNTHETIC] */
    @Override // java.io.Writer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(char[] r12, int r13, int r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 479
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p113n5.C1552m.write(char[], int, int):void");
    }
}
