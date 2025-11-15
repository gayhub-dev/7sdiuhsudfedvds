package p093l1;

import android.os.Process;
import android.text.TextUtils;
import com.tencent.mars.xlog.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.HashMap;
import p186x2.C2073a;
import p200z2.C2150a;

/* compiled from: CrashHandler.java */
/* renamed from: l1.c */
/* loaded from: classes.dex */
public class C1417c implements Thread.UncaughtExceptionHandler {

    /* renamed from: b */
    public static volatile C1417c f4151b;

    /* renamed from: a */
    public Thread.UncaughtExceptionHandler f4152a;

    public C1417c() {
        new HashMap();
    }

    /* renamed from: a */
    public static C1417c m1601a() {
        if (f4151b == null) {
            synchronized (C1417c.class) {
                if (f4151b == null) {
                    f4151b = new C1417c();
                }
            }
        }
        return f4151b;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (th == null && (uncaughtExceptionHandler = this.f4152a) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            String string = stringWriter.toString();
            C2073a.m2456a("error = " + string);
            Log.m651e("XLog_CRASH ", "errorLog = " + string);
            if (TextUtils.isEmpty(string)) {
                System.exit(0);
                Process.killProcess(Process.myPid());
            } else {
                C2150a.m2591b("error_info", string);
                System.exit(0);
                Process.killProcess(Process.myPid());
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }
}
