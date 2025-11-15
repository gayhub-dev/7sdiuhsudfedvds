package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SyncFailedException;
import p009b.C0413b;

/* loaded from: classes.dex */
public class AtomicFile {
    private final File mBackupName;
    private final File mBaseName;

    public AtomicFile(@NonNull File file) {
        this.mBaseName = file;
        this.mBackupName = new File(file.getPath() + ".bak");
    }

    private static boolean sync(@NonNull FileOutputStream fileOutputStream) throws SyncFailedException {
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public void delete() {
        this.mBaseName.delete();
        this.mBackupName.delete();
    }

    public void failWrite(@Nullable FileOutputStream fileOutputStream) throws IOException {
        if (fileOutputStream != null) {
            sync(fileOutputStream);
            try {
                fileOutputStream.close();
                this.mBaseName.delete();
                this.mBackupName.renameTo(this.mBaseName);
            } catch (IOException unused) {
            }
        }
    }

    public void finishWrite(@Nullable FileOutputStream fileOutputStream) throws IOException {
        if (fileOutputStream != null) {
            sync(fileOutputStream);
            try {
                fileOutputStream.close();
                this.mBackupName.delete();
            } catch (IOException unused) {
            }
        }
    }

    @NonNull
    public File getBaseFile() {
        return this.mBaseName;
    }

    @NonNull
    public FileInputStream openRead() {
        if (this.mBackupName.exists()) {
            this.mBaseName.delete();
            this.mBackupName.renameTo(this.mBaseName);
        }
        return new FileInputStream(this.mBaseName);
    }

    @NonNull
    public byte[] readFully() throws IOException {
        FileInputStream fileInputStreamOpenRead = openRead();
        try {
            byte[] bArr = new byte[fileInputStreamOpenRead.available()];
            int i7 = 0;
            while (true) {
                int i8 = fileInputStreamOpenRead.read(bArr, i7, bArr.length - i7);
                if (i8 <= 0) {
                    return bArr;
                }
                i7 += i8;
                int iAvailable = fileInputStreamOpenRead.available();
                if (iAvailable > bArr.length - i7) {
                    byte[] bArr2 = new byte[iAvailable + i7];
                    System.arraycopy(bArr, 0, bArr2, 0, i7);
                    bArr = bArr2;
                }
            }
        } finally {
            fileInputStreamOpenRead.close();
        }
    }

    @NonNull
    public FileOutputStream startWrite() throws IOException {
        if (this.mBaseName.exists()) {
            if (this.mBackupName.exists()) {
                this.mBaseName.delete();
            } else if (!this.mBaseName.renameTo(this.mBackupName)) {
                StringBuilder sbM112a = C0413b.m112a("Couldn't rename file ");
                sbM112a.append(this.mBaseName);
                sbM112a.append(" to backup file ");
                sbM112a.append(this.mBackupName);
            }
        }
        try {
            return new FileOutputStream(this.mBaseName);
        } catch (FileNotFoundException unused) {
            if (!this.mBaseName.getParentFile().mkdirs()) {
                StringBuilder sbM112a2 = C0413b.m112a("Couldn't create directory ");
                sbM112a2.append(this.mBaseName);
                throw new IOException(sbM112a2.toString());
            }
            try {
                return new FileOutputStream(this.mBaseName);
            } catch (FileNotFoundException unused2) {
                StringBuilder sbM112a3 = C0413b.m112a("Couldn't create ");
                sbM112a3.append(this.mBaseName);
                throw new IOException(sbM112a3.toString());
            }
        }
    }
}
