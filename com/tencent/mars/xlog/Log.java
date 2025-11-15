package com.tencent.mars.xlog;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.constraint.motion.C0080b;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class Log {
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_NONE = 6;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    private static final String TAG = "mars.xlog.log";
    private static LogImp debugLog = null;
    private static int level = 6;
    private static LogImp logImp;
    private static Map<String, LogInstance> sLogInstanceMap;
    public static Context toastSupportContext;

    public interface LogImp {
        void appenderClose();

        void appenderFlush(long j7, boolean z6);

        void appenderOpen(int i7, int i8, String str, String str2, String str3, int i9);

        int getLogLevel(long j7);

        long getXlogInstance(String str);

        void logD(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4);

        void logE(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4);

        void logF(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4);

        void logI(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4);

        void logV(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4);

        void logW(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4);

        long openLogInstance(int i7, int i8, String str, String str2, String str3, int i9);

        void releaseXlogInstance(String str);

        void setAppenderMode(long j7, int i7);

        void setConsoleLogOpen(long j7, boolean z6);

        void setMaxAliveTime(long j7, long j8);

        void setMaxFileSize(long j7, long j8);
    }

    public static class LogInstance {
        private long mLogInstancePtr;
        private String mPrefix;

        public void appenderFlush() {
            if (Log.logImp == null || this.mLogInstancePtr == 0) {
                return;
            }
            Log.logImp.appenderFlush(this.mLogInstancePtr, false);
        }

        public void appenderFlushSync() {
            if (Log.logImp == null || this.mLogInstancePtr == 0) {
                return;
            }
            Log.logImp.appenderFlush(this.mLogInstancePtr, true);
        }

        /* renamed from: d */
        public void m661d(String str, String str2, Object... objArr) {
            if (Log.logImp == null || getLogLevel() > 1 || this.mLogInstancePtr == 0) {
                return;
            }
            String str3 = objArr == null ? str2 : String.format(str2, objArr);
            if (str3 == null) {
                str3 = "";
            }
            Log.logImp.logD(this.mLogInstancePtr, str, "", "", Process.myTid(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str3);
        }

        /* renamed from: e */
        public void m662e(String str, String str2, Object... objArr) {
            if (Log.logImp == null || getLogLevel() > 4 || this.mLogInstancePtr == 0) {
                return;
            }
            String str3 = objArr == null ? str2 : String.format(str2, objArr);
            if (str3 == null) {
                str3 = "";
            }
            Log.logImp.logE(this.mLogInstancePtr, str, "", "", Process.myTid(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str3);
        }

        /* renamed from: f */
        public void m663f(String str, String str2, Object... objArr) {
            if (Log.logImp == null || getLogLevel() > 5 || this.mLogInstancePtr == 0) {
                return;
            }
            Log.logImp.logF(this.mLogInstancePtr, str, "", "", Process.myTid(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), objArr == null ? str2 : String.format(str2, objArr));
        }

        public int getLogLevel() {
            if (Log.logImp == null || this.mLogInstancePtr == 0) {
                return 6;
            }
            return Log.logImp.getLogLevel(this.mLogInstancePtr);
        }

        /* renamed from: i */
        public void m664i(String str, String str2, Object... objArr) {
            if (Log.logImp == null || getLogLevel() > 2 || this.mLogInstancePtr == 0) {
                return;
            }
            String str3 = objArr == null ? str2 : String.format(str2, objArr);
            if (str3 == null) {
                str3 = "";
            }
            Log.logImp.logI(this.mLogInstancePtr, str, "", "", Process.myTid(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str3);
        }

        public void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
            if (Log.logImp == null || getLogLevel() > 4 || this.mLogInstancePtr == 0) {
                return;
            }
            String str3 = objArr == null ? str2 : String.format(str2, objArr);
            if (str3 == null) {
                str3 = "";
            }
            StringBuilder sbM94a = C0080b.m94a(str3, "  ");
            sbM94a.append(android.util.Log.getStackTraceString(th));
            Log.logImp.logE(this.mLogInstancePtr, str, "", "", Process.myTid(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), sbM94a.toString());
        }

        public void setConsoleLogOpen(boolean z6) {
            if (Log.logImp == null || this.mLogInstancePtr == 0) {
                return;
            }
            Log.logImp.setConsoleLogOpen(this.mLogInstancePtr, z6);
        }

        /* renamed from: v */
        public void m665v(String str, String str2, Object... objArr) {
            if (Log.logImp == null || getLogLevel() > 0 || this.mLogInstancePtr == 0) {
                return;
            }
            String str3 = objArr == null ? str2 : String.format(str2, objArr);
            if (str3 == null) {
                str3 = "";
            }
            Log.logImp.logV(this.mLogInstancePtr, str, "", "", Process.myTid(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str3);
        }

        /* renamed from: w */
        public void m666w(String str, String str2, Object... objArr) {
            if (Log.logImp == null || getLogLevel() > 3 || this.mLogInstancePtr == 0) {
                return;
            }
            String str3 = objArr == null ? str2 : String.format(str2, objArr);
            if (str3 == null) {
                str3 = "";
            }
            Log.logImp.logW(this.mLogInstancePtr, str, "", "", Process.myTid(), Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str3);
        }

        private LogInstance(int i7, int i8, String str, String str2, String str3, int i9) {
            this.mLogInstancePtr = 0L;
            this.mPrefix = null;
            if (Log.logImp != null) {
                this.mLogInstancePtr = Log.logImp.openLogInstance(i7, i8, str, str2, str3, i9);
                this.mPrefix = str3;
            }
        }
    }

    static {
        LogImp logImp2 = new LogImp() { // from class: com.tencent.mars.xlog.Log.1
            private Handler handler = new Handler(Looper.getMainLooper());

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void appenderClose() {
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void appenderFlush(long j7, boolean z6) {
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void appenderOpen(int i7, int i8, String str, String str2, String str3, int i9) {
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public int getLogLevel(long j7) {
                return Log.level;
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public long getXlogInstance(String str) {
                return 0L;
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void logD(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
                int unused = Log.level;
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void logE(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
                int unused = Log.level;
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void logF(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, final String str4) {
                if (Log.level <= 5 && Log.toastSupportContext != null) {
                    this.handler.post(new Runnable() { // from class: com.tencent.mars.xlog.Log.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(Log.toastSupportContext, str4, 1).show();
                        }
                    });
                }
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void logI(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
                int unused = Log.level;
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void logV(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
                int unused = Log.level;
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void logW(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
                int unused = Log.level;
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public long openLogInstance(int i7, int i8, String str, String str2, String str3, int i9) {
                return 0L;
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void releaseXlogInstance(String str) {
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void setAppenderMode(long j7, int i7) {
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void setConsoleLogOpen(long j7, boolean z6) {
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void setMaxAliveTime(long j7, long j8) {
            }

            @Override // com.tencent.mars.xlog.Log.LogImp
            public void setMaxFileSize(long j7, long j8) {
            }
        };
        debugLog = logImp2;
        logImp = logImp2;
        sLogInstanceMap = new HashMap();
    }

    public static void appenderClose() {
        LogImp logImp2 = logImp;
        if (logImp2 != null) {
            logImp2.appenderClose();
            Iterator<Map.Entry<String, LogInstance>> it = sLogInstanceMap.entrySet().iterator();
            while (it.hasNext()) {
                closeLogInstance(it.next().getKey());
            }
        }
    }

    public static void appenderFlush() {
        LogImp logImp2 = logImp;
        if (logImp2 != null) {
            logImp2.appenderFlush(0L, false);
            Iterator<Map.Entry<String, LogInstance>> it = sLogInstanceMap.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().appenderFlush();
            }
        }
    }

    public static void appenderFlushSync(boolean z6) {
        LogImp logImp2 = logImp;
        if (logImp2 != null) {
            logImp2.appenderFlush(0L, z6);
        }
    }

    public static void appenderOpen(int i7, int i8, String str, String str2, String str3, int i9) {
        LogImp logImp2 = logImp;
        if (logImp2 != null) {
            logImp2.appenderOpen(i7, i8, str, str2, str3, i9);
        }
    }

    public static void closeLogInstance(String str) {
        synchronized (sLogInstanceMap) {
            if (logImp != null && sLogInstanceMap.containsKey(str)) {
                LogInstance logInstanceRemove = sLogInstanceMap.remove(str);
                logImp.releaseXlogInstance(str);
                logInstanceRemove.mLogInstancePtr = 0L;
            }
        }
    }

    /* renamed from: d */
    public static void m649d(String str, String str2) {
        m650d(str, str2, null);
    }

    /* renamed from: e */
    public static void m651e(String str, String str2) {
        m652e(str, str2, null);
    }

    /* renamed from: f */
    public static void m653f(String str, String str2) {
        m654f(str, str2, null);
    }

    public static LogImp getImpl() {
        return logImp;
    }

    public static LogInstance getLogInstance(String str) {
        synchronized (sLogInstanceMap) {
            if (!sLogInstanceMap.containsKey(str)) {
                return null;
            }
            return sLogInstanceMap.get(str);
        }
    }

    public static int getLogLevel() {
        LogImp logImp2 = logImp;
        if (logImp2 != null) {
            return logImp2.getLogLevel(0L);
        }
        return 6;
    }

    /* renamed from: i */
    public static void m655i(String str, String str2) {
        m656i(str, str2, null);
    }

    public static LogInstance openLogInstance(int i7, int i8, String str, String str2, String str3, int i9) {
        synchronized (sLogInstanceMap) {
            if (sLogInstanceMap.containsKey(str3)) {
                return sLogInstanceMap.get(str3);
            }
            LogInstance logInstance = new LogInstance(i7, i8, str, str2, str3, i9);
            sLogInstanceMap.put(str3, logInstance);
            return logInstance;
        }
    }

    public static void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        LogImp logImp2 = logImp;
        if (logImp2 == null || logImp2.getLogLevel(0L) > 4) {
            return;
        }
        String str3 = objArr == null ? str2 : String.format(str2, objArr);
        if (str3 == null) {
            str3 = "";
        }
        StringBuilder sbM94a = C0080b.m94a(str3, "  ");
        sbM94a.append(android.util.Log.getStackTraceString(th));
        logImp.logE(0L, str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), sbM94a.toString());
    }

    public static void setConsoleLogOpen(boolean z6) {
        LogImp logImp2 = logImp;
        if (logImp2 != null) {
            logImp2.setConsoleLogOpen(0L, z6);
        }
    }

    public static void setLevel(int i7, boolean z6) {
        level = i7;
    }

    public static void setLogImp(LogImp logImp2) {
        logImp = logImp2;
    }

    /* renamed from: v */
    public static void m657v(String str, String str2) {
        m658v(str, str2, null);
    }

    /* renamed from: w */
    public static void m659w(String str, String str2) {
        m660w(str, str2, null);
    }

    /* renamed from: d */
    public static void m650d(String str, String str2, Object... objArr) {
        LogImp logImp2 = logImp;
        if (logImp2 == null || logImp2.getLogLevel(0L) > 1) {
            return;
        }
        if (objArr != null) {
            str2 = String.format(str2, objArr);
        }
        if (str2 == null) {
            str2 = "";
        }
        logImp.logD(0L, str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
    }

    /* renamed from: e */
    public static void m652e(String str, String str2, Object... objArr) {
        LogImp logImp2 = logImp;
        if (logImp2 == null || logImp2.getLogLevel(0L) > 4) {
            return;
        }
        if (objArr != null) {
            str2 = String.format(str2, objArr);
        }
        if (str2 == null) {
            str2 = "";
        }
        logImp.logE(0L, str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
    }

    /* renamed from: f */
    public static void m654f(String str, String str2, Object... objArr) {
        LogImp logImp2 = logImp;
        if (logImp2 == null || logImp2.getLogLevel(0L) > 5) {
            return;
        }
        if (objArr != null) {
            str2 = String.format(str2, objArr);
        }
        logImp.logF(0L, str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
    }

    /* renamed from: i */
    public static void m656i(String str, String str2, Object... objArr) {
        LogImp logImp2 = logImp;
        if (logImp2 == null || logImp2.getLogLevel(0L) > 2) {
            return;
        }
        if (objArr != null) {
            str2 = String.format(str2, objArr);
        }
        if (str2 == null) {
            str2 = "";
        }
        logImp.logI(0L, str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
    }

    /* renamed from: v */
    public static void m658v(String str, String str2, Object... objArr) {
        LogImp logImp2 = logImp;
        if (logImp2 == null || logImp2.getLogLevel(0L) > 0) {
            return;
        }
        if (objArr != null) {
            str2 = String.format(str2, objArr);
        }
        if (str2 == null) {
            str2 = "";
        }
        logImp.logV(0L, str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
    }

    /* renamed from: w */
    public static void m660w(String str, String str2, Object... objArr) {
        LogImp logImp2 = logImp;
        if (logImp2 == null || logImp2.getLogLevel(0L) > 3) {
            return;
        }
        if (objArr != null) {
            str2 = String.format(str2, objArr);
        }
        if (str2 == null) {
            str2 = "";
        }
        logImp.logW(0L, str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
    }
}
