package p137q3;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: Util.java */
/* renamed from: q3.c */
/* loaded from: classes.dex */
public final class C1768c {

    /* renamed from: a */
    public static final Charset f5046a = Charset.forName("US-ASCII");

    static {
        Charset.forName("UTF-8");
    }

    /* renamed from: a */
    public static void m1954a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e7) {
                throw e7;
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: b */
    public static void m1955b(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                m1955b(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }
}
