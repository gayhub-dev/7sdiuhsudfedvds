package com.alibaba.sdk.android.oss.common;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.constraint.C0072a;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import p009b.C0413b;

/* loaded from: classes.dex */
public class OSSLogToFileUtils {
    private static final String LOG_DIR_NAME = "OSSLog";
    private static OSSLogToFileUtils instance;
    private static Context sContext;
    private static File sLogFile;
    private static Uri sLogUri;
    private boolean useSdCard = true;
    private static LogThreadPoolManager logService = LogThreadPoolManager.newInstance();
    private static SimpleDateFormat sLogSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static long LOG_MAX_SIZE = 5242880;

    public static class WriteCall implements Runnable {
        private Object mStr;

        public WriteCall(Object obj) {
            this.mStr = obj;
        }

        private PrintWriter printEx(PrintWriter printWriter) {
            StringBuilder sbM112a = C0413b.m112a("crash_time：");
            sbM112a.append(OSSLogToFileUtils.sLogSDF.format(new Date()));
            printWriter.println(sbM112a.toString());
            ((Throwable) this.mStr).printStackTrace(printWriter);
            return printWriter;
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            if (OSSLogToFileUtils.sLogFile != null) {
                OSSLogToFileUtils.getInstance();
                if (OSSLogToFileUtils.getLogFileSize(OSSLogToFileUtils.sLogFile) > OSSLogToFileUtils.LOG_MAX_SIZE) {
                    OSSLogToFileUtils.getInstance().resetLogFile();
                }
                try {
                    PrintWriter printWriter = new PrintWriter((Writer) new FileWriter(OSSLogToFileUtils.sLogFile, true), true);
                    if (this.mStr instanceof Throwable) {
                        printEx(printWriter);
                    } else {
                        printWriter.println(OSSLogToFileUtils.getInstance().getFunctionInfo(null) + " - " + this.mStr.toString());
                    }
                    printWriter.println("------>end of log");
                    printWriter.println();
                    printWriter.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
        }
    }

    private OSSLogToFileUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getFunctionInfo(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr != null) {
            return null;
        }
        StringBuilder sbM112a = C0413b.m112a("[");
        sbM112a.append(sLogSDF.format(new Date()));
        sbM112a.append("]");
        return sbM112a.toString();
    }

    public static OSSLogToFileUtils getInstance() {
        if (instance == null) {
            synchronized (OSSLogToFileUtils.class) {
                if (instance == null) {
                    instance = new OSSLogToFileUtils();
                }
            }
        }
        return instance;
    }

    public static long getLocalLogFileSize() {
        return getLogFileSize(sLogFile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getLogFile() throws IOException {
        File file;
        File file2 = null;
        boolean z6 = false;
        try {
            boolean z7 = true;
            if (this.useSdCard && Environment.getExternalStorageState().equals("mounted") && Build.VERSION.SDK_INT < 29) {
                if (readSDCardSpace() <= LOG_MAX_SIZE / 1024) {
                    z7 = false;
                }
                file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + LOG_DIR_NAME);
            } else {
                if (readSystemSpace() <= LOG_MAX_SIZE / 1024) {
                    z7 = false;
                }
                file = new File(sContext.getFilesDir().getPath() + File.separator + LOG_DIR_NAME);
            }
            z6 = z7;
        } catch (Exception unused) {
            file = null;
        }
        if (z6) {
            if (!file.exists()) {
                file.mkdirs();
            }
            file2 = new File(file.getPath() + "/logs.csv");
            if (!file2.exists()) {
                createNewFile(file2);
            }
        }
        return file2;
    }

    public static long getLogFileSize(File file) {
        if (file == null || !file.exists()) {
            return 0L;
        }
        return file.length();
    }

    private Uri getLogUri() throws FileNotFoundException {
        ContentResolver contentResolver = sContext.getContentResolver();
        Uri uriQueryLogUri = queryLogUri();
        if (uriQueryLogUri == null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", "logs.csv");
            contentValues.put("mime_type", "file/csv");
            contentValues.put("title", "logs.csv");
            contentValues.put("relative_path", "Documents/OSSLog");
            uriQueryLogUri = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues);
            try {
                contentResolver.openFileDescriptor(uriQueryLogUri, "w");
            } catch (Exception unused) {
                return null;
            }
        }
        return uriQueryLogUri;
    }

    public static void init(Context context, ClientConfiguration clientConfiguration) {
        File file;
        OSSLog.logDebug("init ...", false);
        if (clientConfiguration != null) {
            LOG_MAX_SIZE = clientConfiguration.getMaxLogSize();
        }
        if (sContext != null && instance != null && (file = sLogFile) != null && file.exists()) {
            OSSLog.logDebug("LogToFileUtils has been init ...", false);
            return;
        }
        sContext = context.getApplicationContext();
        instance = getInstance();
        logService.addExecuteTask(new Runnable() { // from class: com.alibaba.sdk.android.oss.common.OSSLogToFileUtils.1
            @Override // java.lang.Runnable
            public void run() throws IOException {
                File unused = OSSLogToFileUtils.sLogFile = OSSLogToFileUtils.instance.getLogFile();
                if (OSSLogToFileUtils.sLogFile != null) {
                    StringBuilder sbM112a = C0413b.m112a("LogFilePath is: ");
                    sbM112a.append(OSSLogToFileUtils.sLogFile.getPath());
                    OSSLog.logInfo(sbM112a.toString(), false);
                    if (OSSLogToFileUtils.LOG_MAX_SIZE < OSSLogToFileUtils.getLogFileSize(OSSLogToFileUtils.sLogFile)) {
                        OSSLog.logInfo("init reset log file", false);
                        OSSLogToFileUtils.instance.resetLogFile();
                    }
                }
            }
        });
    }

    private Uri queryLogUri() {
        ContentResolver contentResolver = sContext.getContentResolver();
        Uri contentUri = MediaStore.Files.getContentUri("external");
        Cursor cursorQuery = contentResolver.query(contentUri, new String[]{"_id"}, "relative_path like ? AND _display_name=?", new String[]{"Documents/OSSLog%", "logs.csv"}, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return null;
        }
        Uri uriWithAppendedId = ContentUris.withAppendedId(contentUri, cursorQuery.getLong(0));
        cursorQuery.close();
        return uriWithAppendedId;
    }

    private long readSDCardSpace() {
        long availableBlocksLong = 0;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            try {
                availableBlocksLong = new StatFs(Environment.getExternalStorageDirectory().getPath()).getAvailableBlocksLong() * r3.getBlockSize();
            } catch (Exception unused) {
            }
        }
        StringBuilder sbM112a = C0413b.m112a("sd卡存储空间:");
        sbM112a.append(String.valueOf(availableBlocksLong));
        sbM112a.append("kb");
        OSSLog.logDebug(sbM112a.toString(), false);
        return availableBlocksLong;
    }

    private long readSystemSpace() {
        long availableBlocksLong;
        try {
            availableBlocksLong = (new StatFs(Environment.getDataDirectory().getPath()).getAvailableBlocksLong() * r1.getBlockSize()) / 1024;
        } catch (Exception unused) {
            availableBlocksLong = 0;
        }
        StringBuilder sbM112a = C0413b.m112a("内部存储空间:");
        sbM112a.append(String.valueOf(availableBlocksLong));
        sbM112a.append("kb");
        OSSLog.logDebug(sbM112a.toString(), false);
        return availableBlocksLong;
    }

    public static void reset() {
        sContext = null;
        instance = null;
        sLogFile = null;
    }

    public void createNewFile(File file) throws IOException {
        try {
            file.createNewFile();
        } catch (Exception e7) {
            StringBuilder sbM112a = C0413b.m112a("Create log file failure !!! ");
            sbM112a.append(e7.toString());
            OSSLog.logError(sbM112a.toString(), false);
        }
    }

    public void deleteLogFile() {
        File file = new File(sLogFile.getParent() + "/logs.csv");
        if (file.exists()) {
            OSSLog.logDebug("delete Log File ... ", false);
            file.delete();
        }
    }

    public void deleteLogFileDir() {
        deleteLogFile();
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getPath());
        File file = new File(C0072a.m92a(sb, File.separator, LOG_DIR_NAME));
        if (file.exists()) {
            OSSLog.logDebug("delete Log FileDir ... ", false);
            file.delete();
        }
    }

    public void resetLogFile() throws IOException {
        OSSLog.logDebug("Reset Log File ... ", false);
        if (!sLogFile.getParentFile().exists()) {
            OSSLog.logDebug("Reset Log make File dir ... ", false);
            sLogFile.getParentFile().mkdir();
        }
        File file = new File(sLogFile.getParent() + "/logs.csv");
        if (file.exists()) {
            file.delete();
        }
        createNewFile(file);
    }

    public void setUseSdCard(boolean z6) {
        this.useSdCard = z6;
    }

    public synchronized void write(Object obj) {
        File file;
        if (OSSLog.isEnableLog()) {
            if (sContext != null && instance != null && (file = sLogFile) != null) {
                if (!file.exists()) {
                    resetLogFile();
                }
                logService.addExecuteTask(new WriteCall(obj));
            }
        }
    }
}
