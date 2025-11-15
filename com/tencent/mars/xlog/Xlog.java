package com.tencent.mars.xlog;

import com.tencent.mars.xlog.Log;

/* loaded from: classes.dex */
public class Xlog implements Log.LogImp {
    public static final int AppednerModeAsync = 0;
    public static final int AppednerModeSync = 1;
    public static final int COMPRESS_LEVEL1 = 1;
    public static final int COMPRESS_LEVEL2 = 2;
    public static final int COMPRESS_LEVEL3 = 3;
    public static final int COMPRESS_LEVEL4 = 4;
    public static final int COMPRESS_LEVEL5 = 5;
    public static final int COMPRESS_LEVEL6 = 6;
    public static final int COMPRESS_LEVEL7 = 7;
    public static final int COMPRESS_LEVEL8 = 8;
    public static final int COMPRESS_LEVEL9 = 9;
    public static final int LEVEL_ALL = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_NONE = 6;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    public static final int ZLIB_MODE = 0;
    public static final int ZSTD_MODE = 1;

    public static class XLogConfig {
        public String cachedir;
        public String logdir;
        public String nameprefix;
        public int level = 2;
        public int mode = 0;
        public String pubkey = "";
        public int compressmode = 0;
        public int compresslevel = 0;
        public int cachedays = 0;
    }

    public static class XLoggerInfo {
        public String filename;
        public String funcname;
        public int level;
        public int line;
        public long maintid;
        public long pid;
        public String tag;
        public long tid;
    }

    private static native void appenderOpen(XLogConfig xLogConfig);

    private static String decryptTag(String str) {
        return str;
    }

    public static native void logWrite(XLoggerInfo xLoggerInfo, String str);

    public static void logWrite2(int i7, String str, String str2, String str3, int i8, int i9, long j7, long j8, String str4) {
        logWrite2(0L, i7, str, str2, str3, i8, i9, j7, j8, str4);
    }

    public static native void logWrite2(long j7, int i7, String str, String str2, String str3, int i8, int i9, long j8, long j9, String str4);

    public static void open(boolean z6, int i7, int i8, String str, String str2, String str3, String str4) {
        if (z6) {
            System.loadLibrary("c++_shared");
            System.loadLibrary("marsxlog");
        }
        XLogConfig xLogConfig = new XLogConfig();
        xLogConfig.level = i7;
        xLogConfig.mode = i8;
        xLogConfig.logdir = str2;
        xLogConfig.nameprefix = str3;
        xLogConfig.pubkey = str4;
        xLogConfig.compressmode = 0;
        xLogConfig.compresslevel = 0;
        xLogConfig.cachedir = str;
        xLogConfig.cachedays = 0;
        appenderOpen(xLogConfig);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native void appenderClose();

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native void appenderFlush(long j7, boolean z6);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void appenderOpen(int i7, int i8, String str, String str2, String str3, int i9) {
        XLogConfig xLogConfig = new XLogConfig();
        xLogConfig.level = i7;
        xLogConfig.mode = i8;
        xLogConfig.logdir = str2;
        xLogConfig.nameprefix = str3;
        xLogConfig.compressmode = 0;
        xLogConfig.pubkey = "";
        xLogConfig.cachedir = str;
        xLogConfig.cachedays = i9;
        appenderOpen(xLogConfig);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native int getLogLevel(long j7);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native long getXlogInstance(String str);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logD(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
        logWrite2(j7, 1, decryptTag(str), str2, str3, i7, i8, j8, j9, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logE(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
        logWrite2(j7, 4, decryptTag(str), str2, str3, i7, i8, j8, j9, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logF(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
        logWrite2(j7, 5, decryptTag(str), str2, str3, i7, i8, j8, j9, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logI(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
        logWrite2(j7, 2, decryptTag(str), str2, str3, i7, i8, j8, j9, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logV(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
        logWrite2(j7, 0, decryptTag(str), str2, str3, i7, i8, j8, j9, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logW(long j7, String str, String str2, String str3, int i7, int i8, long j8, long j9, String str4) {
        logWrite2(j7, 3, decryptTag(str), str2, str3, i7, i8, j8, j9, str4);
    }

    public native long newXlogInstance(XLogConfig xLogConfig);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public long openLogInstance(int i7, int i8, String str, String str2, String str3, int i9) {
        XLogConfig xLogConfig = new XLogConfig();
        xLogConfig.level = i7;
        xLogConfig.mode = i8;
        xLogConfig.logdir = str2;
        xLogConfig.nameprefix = str3;
        xLogConfig.compressmode = 0;
        xLogConfig.pubkey = "";
        xLogConfig.cachedir = str;
        xLogConfig.cachedays = i9;
        return newXlogInstance(xLogConfig);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native void releaseXlogInstance(String str);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native void setAppenderMode(long j7, int i7);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native void setConsoleLogOpen(long j7, boolean z6);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native void setMaxAliveTime(long j7, long j8);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native void setMaxFileSize(long j7, long j8);
}
