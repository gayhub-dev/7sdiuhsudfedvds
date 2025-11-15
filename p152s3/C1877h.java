package p152s3;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* compiled from: Utils.java */
/* renamed from: s3.h */
/* loaded from: classes.dex */
public final class C1877h {
    /* renamed from: a */
    public static String m2150a(Throwable th) {
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
            if (cause instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    /* renamed from: b */
    public static boolean m2151b(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
