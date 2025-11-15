package p148s;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: Util.java */
/* renamed from: s.c */
/* loaded from: classes.dex */
public final class C1859c {

    /* renamed from: a */
    public static final Charset f5433a = Charset.forName("US-ASCII");

    static {
        Charset.forName("UTF-8");
    }

    /* renamed from: a */
    public static void m2130a(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                m2130a(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }
}
