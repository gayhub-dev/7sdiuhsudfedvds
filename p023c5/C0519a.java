package p023c5;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import p033d5.C0872a;
import p033d5.C0873b;

/* compiled from: IOUtils.java */
/* renamed from: c5.a */
/* loaded from: classes.dex */
public class C0519a {
    static {
        char c7 = File.separatorChar;
        C0873b c0873b = new C0873b(4);
        try {
            PrintWriter printWriter = new PrintWriter(c0873b);
            try {
                printWriter.println();
                c0873b.toString();
                printWriter.close();
            } finally {
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* renamed from: a */
    public static byte[] m333a(InputStream inputStream) {
        C0872a c0872a = new C0872a();
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int i7 = inputStream.read(bArr);
                if (-1 == i7) {
                    return c0872a.m680b();
                }
                c0872a.write(bArr, 0, i7);
            }
        } finally {
        }
    }
}
