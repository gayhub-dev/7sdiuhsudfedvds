package android.support.multidex;

import android.arch.lifecycle.C0063n;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.C0072a;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import p009b.C0413b;

/* loaded from: classes.dex */
final class MultiDexExtractor implements Closeable {
    private static final int BUFFER_SIZE = 16384;
    private static final String DEX_PREFIX = "classes";
    public static final String DEX_SUFFIX = ".dex";
    private static final String EXTRACTED_NAME_EXT = ".classes";
    public static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_CRC = "crc";
    private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_TIME = "dex.time.";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String LOCK_FILENAME = "MultiDex.lock";
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final long NO_VALUE = -1;
    private static final String PREFS_FILE = "multidex.version";
    private static final String TAG = "MultiDex";
    private final FileLock cacheLock;
    private final File dexDir;
    private final FileChannel lockChannel;
    private final RandomAccessFile lockRaf;
    private final File sourceApk;
    private final long sourceCrc;

    public static class ExtractedDex extends File {
        public long crc;

        public ExtractedDex(File file, String str) {
            super(file, str);
            this.crc = -1L;
        }
    }

    public MultiDexExtractor(File file, File file2) throws Throwable {
        file.getPath();
        file2.getPath();
        this.sourceApk = file;
        this.dexDir = file2;
        this.sourceCrc = getZipCrc(file);
        File file3 = new File(file2, LOCK_FILENAME);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
        this.lockRaf = randomAccessFile;
        try {
            FileChannel channel = randomAccessFile.getChannel();
            this.lockChannel = channel;
            try {
                file3.getPath();
                this.cacheLock = channel.lock();
                file3.getPath();
            } catch (IOException e7) {
                e = e7;
                closeQuietly(this.lockChannel);
                throw e;
            } catch (Error e8) {
                e = e8;
                closeQuietly(this.lockChannel);
                throw e;
            } catch (RuntimeException e9) {
                e = e9;
                closeQuietly(this.lockChannel);
                throw e;
            }
        } catch (IOException | Error | RuntimeException e10) {
            closeQuietly(this.lockRaf);
            throw e10;
        }
    }

    private void clearDexDir() {
        File[] fileArrListFiles = this.dexDir.listFiles(new FileFilter() { // from class: android.support.multidex.MultiDexExtractor.1
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return !file.getName().equals(MultiDexExtractor.LOCK_FILENAME);
            }
        });
        if (fileArrListFiles == null) {
            this.dexDir.getPath();
            return;
        }
        for (File file : fileArrListFiles) {
            file.getPath();
            file.length();
            if (file.delete()) {
                file.getPath();
            } else {
                file.getPath();
            }
        }
    }

    private static void closeQuietly(Closeable closeable) throws IOException {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    private static void extract(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File fileCreateTempFile = File.createTempFile(C0063n.m88a("tmp-", str), EXTRACTED_SUFFIX, file.getParentFile());
        fileCreateTempFile.getPath();
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileCreateTempFile)));
            try {
                ZipEntry zipEntry2 = new ZipEntry("classes.dex");
                zipEntry2.setTime(zipEntry.getTime());
                zipOutputStream.putNextEntry(zipEntry2);
                byte[] bArr = new byte[16384];
                for (int i7 = inputStream.read(bArr); i7 != -1; i7 = inputStream.read(bArr)) {
                    zipOutputStream.write(bArr, 0, i7);
                }
                zipOutputStream.closeEntry();
                zipOutputStream.close();
                if (!fileCreateTempFile.setReadOnly()) {
                    throw new IOException("Failed to mark readonly \"" + fileCreateTempFile.getAbsolutePath() + "\" (tmp of \"" + file.getAbsolutePath() + "\")");
                }
                file.getPath();
                if (fileCreateTempFile.renameTo(file)) {
                    return;
                }
                throw new IOException("Failed to rename \"" + fileCreateTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + "\"");
            } catch (Throwable th) {
                zipOutputStream.close();
                throw th;
            }
        } finally {
            closeQuietly(inputStream);
            fileCreateTempFile.delete();
        }
    }

    private static SharedPreferences getMultiDexPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE, 4);
    }

    private static long getTimeStamp(File file) {
        long jLastModified = file.lastModified();
        return jLastModified == -1 ? jLastModified - 1 : jLastModified;
    }

    private static long getZipCrc(File file) throws IOException {
        long zipCrc = ZipUtil.getZipCrc(file);
        return zipCrc == -1 ? zipCrc - 1 : zipCrc;
    }

    private static boolean isModified(Context context, File file, long j7, String str) {
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        if (multiDexPreferences.getLong(str + KEY_TIME_STAMP, -1L) == getTimeStamp(file)) {
            if (multiDexPreferences.getLong(str + KEY_CRC, -1L) == j7) {
                return false;
            }
        }
        return true;
    }

    private List<ExtractedDex> loadExistingExtractions(Context context, String str) throws IOException {
        String str2 = this.sourceApk.getName() + EXTRACTED_NAME_EXT;
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        int i7 = multiDexPreferences.getInt(str + KEY_DEX_NUMBER, 1);
        ArrayList arrayList = new ArrayList(i7 + (-1));
        int i8 = 2;
        while (i8 <= i7) {
            ExtractedDex extractedDex = new ExtractedDex(this.dexDir, str2 + i8 + EXTRACTED_SUFFIX);
            if (!extractedDex.isFile()) {
                StringBuilder sbM112a = C0413b.m112a("Missing extracted secondary dex file '");
                sbM112a.append(extractedDex.getPath());
                sbM112a.append("'");
                throw new IOException(sbM112a.toString());
            }
            extractedDex.crc = getZipCrc(extractedDex);
            long j7 = multiDexPreferences.getLong(str + KEY_DEX_CRC + i8, -1L);
            long j8 = multiDexPreferences.getLong(str + KEY_DEX_TIME + i8, -1L);
            long jLastModified = extractedDex.lastModified();
            if (j8 == jLastModified) {
                String str3 = str2;
                SharedPreferences sharedPreferences = multiDexPreferences;
                if (j7 == extractedDex.crc) {
                    arrayList.add(extractedDex);
                    i8++;
                    multiDexPreferences = sharedPreferences;
                    str2 = str3;
                }
            }
            throw new IOException("Invalid extracted dex: " + extractedDex + " (key \"" + str + "\"), expected modification time: " + j8 + ", modification time: " + jLastModified + ", expected crc: " + j7 + ", file crc: " + extractedDex.crc);
        }
        return arrayList;
    }

    private List<ExtractedDex> performExtractions() throws IOException {
        String str = this.sourceApk.getName() + EXTRACTED_NAME_EXT;
        clearDexDir();
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(this.sourceApk);
        int i7 = 2;
        try {
            ZipEntry entry = zipFile.getEntry("classes2.dex");
            while (entry != null) {
                ExtractedDex extractedDex = new ExtractedDex(this.dexDir, str + i7 + EXTRACTED_SUFFIX);
                arrayList.add(extractedDex);
                StringBuilder sb = new StringBuilder();
                sb.append("Extraction is needed for file ");
                sb.append(extractedDex);
                int i8 = 0;
                boolean z6 = false;
                while (i8 < 3 && !z6) {
                    i8++;
                    extract(zipFile, entry, extractedDex, str);
                    try {
                        extractedDex.crc = getZipCrc(extractedDex);
                        z6 = true;
                    } catch (IOException unused) {
                        extractedDex.getAbsolutePath();
                        z6 = false;
                    }
                    extractedDex.getAbsolutePath();
                    extractedDex.length();
                    if (!z6) {
                        extractedDex.delete();
                        if (extractedDex.exists()) {
                            extractedDex.getPath();
                        }
                    }
                }
                if (!z6) {
                    throw new IOException("Could not create zip file " + extractedDex.getAbsolutePath() + " for secondary dex (" + i7 + ")");
                }
                i7++;
                entry = zipFile.getEntry(DEX_PREFIX + i7 + DEX_SUFFIX);
            }
            return arrayList;
        } finally {
            try {
                zipFile.close();
            } catch (IOException unused2) {
            }
        }
    }

    private static void putStoredApkInfo(Context context, String str, long j7, long j8, List<ExtractedDex> list) {
        SharedPreferences.Editor editorEdit = getMultiDexPreferences(context).edit();
        editorEdit.putLong(str + KEY_TIME_STAMP, j7);
        editorEdit.putLong(C0072a.m92a(new StringBuilder(), str, KEY_CRC), j8);
        editorEdit.putInt(str + KEY_DEX_NUMBER, list.size() + 1);
        int i7 = 2;
        for (ExtractedDex extractedDex : list) {
            editorEdit.putLong(str + KEY_DEX_CRC + i7, extractedDex.crc);
            editorEdit.putLong(str + KEY_DEX_TIME + i7, extractedDex.lastModified());
            i7++;
        }
        editorEdit.commit();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.cacheLock.release();
        this.lockChannel.close();
        this.lockRaf.close();
    }

    public List<? extends File> load(Context context, String str, boolean z6) throws IOException {
        List<ExtractedDex> listPerformExtractions;
        this.sourceApk.getPath();
        if (!this.cacheLock.isValid()) {
            throw new IllegalStateException("MultiDexExtractor was closed");
        }
        if (z6 || isModified(context, this.sourceApk, this.sourceCrc, str)) {
            listPerformExtractions = performExtractions();
            putStoredApkInfo(context, str, getTimeStamp(this.sourceApk), this.sourceCrc, listPerformExtractions);
        } else {
            try {
                listPerformExtractions = loadExistingExtractions(context, str);
            } catch (IOException unused) {
                listPerformExtractions = performExtractions();
                putStoredApkInfo(context, str, getTimeStamp(this.sourceApk), this.sourceCrc, listPerformExtractions);
            }
        }
        listPerformExtractions.size();
        return listPerformExtractions;
    }
}
